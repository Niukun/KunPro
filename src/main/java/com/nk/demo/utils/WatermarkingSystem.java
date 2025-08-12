package com.nk.demo.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.stream.IntStream;

public class WatermarkingSystem {
    // 水印参数
    private static double gamma0 = 0.856;
    private static double mu = 3.64;
    private static int channel_number = 3;

    public static void main(String[] args) throws IOException {
//        addMark();

        verify("file");
    }

    // 生成水印序列
    private static int[] generateWatermark(int N, double mu, double gamma0) {
        double[] gamma = new double[N];
        gamma[0] = gamma0;

        // 生成Logistic映射序列
        for (int i = 1; i < N; i++) {
            gamma[i] = mu * gamma[i - 1] * (1 - gamma[i - 1]);
        }

        // 转换为0/1水印序列
        int[] wmBits = new int[N];
        for (int i = 0; i < N; i++) {
            int g = (int) Math.round(255 * gamma[i]);
            int onesCount = 0;

            // 计算8位二进制中1的个数
            for (int j = 0; j < 8; j++) {
                onesCount += (g >> j) & 1;
            }

            // 水印位由1的个数的奇偶性决定
            wmBits[i] = onesCount % 2;
        }

        return wmBits;
    }

    // 使用 parallelStream 加速水印嵌入
    private static BufferedImage embedWatermark(BufferedImage image, int[] wmBits, int channel_number) {
        int width = image.getWidth();
        int height = image.getHeight();
        int[] pixels = new int[width * height];

        // 获取图像的像素数组
        image.getRGB(0, 0, width, height, pixels, 0, width);

        // 使用 parallelStream 加速像素处理
        IntStream.range(0, height).parallel().forEach(y -> {
            for (int x = 0; x < width; x++) {
                int index = (y * width + x) * channel_number;
                int argb = pixels[y * width + x];

                // 提取ARGB值（不需要 Color 对象）
                int r = (argb >> 16) & 0xFF;
                int g = (argb >> 8) & 0xFF;
                int b = argb & 0xFF;

                // 嵌入水印到RGB的LSB
                r = (r & 0xFE) | wmBits[index];
                g = (g & 0xFE) | wmBits[index + 1];
                b = (b & 0xFE) | wmBits[index + 2];

                // 将修改后的像素重新放入像素数组
                pixels[y * width + x] = (r << 16) | (g << 8) | b;
            }
        });

        // 创建一个新的 BufferedImage 并设置修改后的像素值
        BufferedImage watermarkedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        watermarkedImage.setRGB(0, 0, width, height, pixels, 0, width);

        return watermarkedImage;
    }

    // 使用 parallelStream 加速水印提取
    private static int[] extractWatermark(BufferedImage image, int channel_number) {
        int width = image.getWidth();
        int height = image.getHeight();
        int[] pixels = new int[width * height];
        int[] wmBits = new int[width * height * channel_number];

        // 获取图像的像素数组
        image.getRGB(0, 0, width, height, pixels, 0, width);

        // 使用 parallelStream 来加速水印提取过程
        IntStream.range(0, height).parallel().forEach(y -> {
            for (int x = 0; x < width; x++) {
                int index = (y * width + x) * channel_number;
                int argb = pixels[y * width + x];

                // 提取水印位（RGB各个通道的LSB）
                int r = (argb >> 16) & 0xFF;
                wmBits[index] = r & 1;
                int g = (argb >> 8) & 0xFF;
                wmBits[index + 1] = g & 1;
                int b = argb & 0xFF;
                wmBits[index + 2] = b & 1;
            }
        });

        return wmBits;
    }

    private static BufferedImage detectTamper(BufferedImage originalImage, BufferedImage tamperedImage,
                                              double mu, double gamma0, int channel_number) {
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();

        // 生成原始水印
        int pixelCount = width * height * channel_number;
        int[] originalWm = generateWatermark(pixelCount, mu, gamma0);

        // 从篡改图像中提取水印
        int[] extractedWm = extractWatermark(tamperedImage, channel_number);

        // 初始化结果像素数组
        int[] resultPixels = new int[width * height];

        // 检测每个像素的篡改情况
        boolean[] tamperMap = new boolean[pixelCount];
        for (int i = 0; i < pixelCount; i++) {
            tamperMap[i] = originalWm[i] != extractedWm[i];
        }

        // 构建可视化结果
        int border = 1; // 篡改区域边界扩展大小

        // 使用 parallelStream 或传统的循环来遍历像素（可以根据需求选择）
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int index = (y * width + x) * channel_number;

                // 获取原始像素值
                int argb = tamperedImage.getRGB(x, y);
                Color color = new Color(argb, true);

                // 初始化结果像素
                int r = color.getRed();
                int g = color.getGreen();
                int b = color.getBlue();

                // 如果检测到篡改，标记为红色
                boolean flag = false;
                for (int h = 0; h < channel_number; h++) {
                    if (tamperMap[h + index]) {
                        flag = true;
                        break;
                    }
                }

                if (flag) {
                    r = 255;  // 将篡改区域的红色通道设为最大

                    // 扩展篡改标记区域
                    for (int dx = -border; dx <= border; dx++) {
                        for (int dy = -border; dy <= border; dy++) {
                            int nx = x + dx;
                            int ny = y + dy;

                            if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                                int neighborIndex = ny * width + nx;
                                if (!tamperMap[neighborIndex]) {
                                    resultPixels[ny * width + nx] = new Color(255, 0, 0).getRGB();
                                }
                            }
                        }
                    }
                }

                // 将结果像素存入结果数组
                resultPixels[y * width + x] = new Color(r, g, b).getRGB();
            }
        }

        // 创建一个新的 BufferedImage 并将处理后的像素数组应用到其中
        BufferedImage resultImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        resultImage.setRGB(0, 0, width, height, resultPixels, 0, width);

        return resultImage;
    }

    // 水印添加主方法
    private static BufferedImage addWatermark(BufferedImage originalImage, double gamma0, double mu, int channel_number) {
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        int pixelCount = width * height * channel_number;

        // 生成水印
        int[] wmBits = generateWatermark(pixelCount, mu, gamma0);

        // 嵌入水印
        return embedWatermark(originalImage, wmBits, channel_number);
    }

    // 篡改检测主方法
    public static BufferedImage detectImageTamper(BufferedImage tamperedImage, double gamma0, double mu, int channel_number) {
        int width = tamperedImage.getWidth();
        int height = tamperedImage.getHeight();

        // 创建一个"原始图像"的副本(实际应用中应该使用真正的原始图像)
        BufferedImage originalImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                originalImage.setRGB(x, y, tamperedImage.getRGB(x, y));
            }
        }

        // 检测篡改
        return detectTamper(originalImage, tamperedImage, mu, gamma0, channel_number);
    }


    private static void addMark() throws IOException {
        File srcFile = new File("E:\\data\\Intellij\\Download\\2025\\07\\23/file.png");
        if (!srcFile.exists()) {
            System.out.println("错误: 文件不存在!");
        }

        // 读取原始图像
        BufferedImage originalImage = ImageIO.read(srcFile);

        // 添加水印
        long start = System.currentTimeMillis();
        BufferedImage watermarkedImage = addWatermark(originalImage, gamma0, mu, channel_number);
        System.out.println("水印嵌入时间 = " + (System.currentTimeMillis() - start) + "ms");
        start = System.currentTimeMillis();
        // 保存结果
        ImageIO.write(watermarkedImage, "png", srcFile);
        System.out.println("添加完水印的图像已经保存为.png无压缩格式");
        System.out.println("png保存时间 = " + ((System.currentTimeMillis() - start) * 1.0 / 1000) + "s");
        System.out.println("水印图像已保存为: watermarked.png");
    }

    private static void verify(String fileName) throws IOException {
        File markFile = new File("E:\\data\\Intellij\\Download\\2025\\07\\23/" + fileName + ".png");
        // 读取可能被篡改的图像
        long start = System.currentTimeMillis();
        BufferedImage tamperedImage = ImageIO.read(markFile);

        // 检测篡改s
        BufferedImage tamperMapImage = detectImageTamper(tamperedImage, gamma0, mu, channel_number);
        System.out.println("篡改检测时间 = " + (System.currentTimeMillis() - start) + "ms");
        // 保存结果

        File verifyFile = new File("E:\\data\\Intellij\\Download\\2025\\07\\23/tamperMap.png");
        ImageIO.write(tamperMapImage, "png", verifyFile);


//        ImageWriter writer = ImageIO.getImageWritersByFormatName("PNG").next();
//        ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(verifyFile);
//        writer.setOutput(imageOutputStream);
//        writer.write(null, new IIOImage(tamperMapImage, null, null), null);
//        imageOutputStream.close();

        System.out.println("篡改检测已完成!保存文件tamperMap.png耗时：" + (System.currentTimeMillis() - start) + "ms");
    }

    private static void savePng(BufferedImage image, String fileName){

    }

}