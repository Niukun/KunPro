package com.nk.demo.utils;

import org.apache.poi.poifs.crypt.Decryptor;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WordPasswordCracker {

    private static final int MAX_PASSWORD_LENGTH = 6;
    private static final String KNOWN_PART = ""; // 替换为您已知的部分密码
    private static final String FILE_PATH = "E:\\data\\Intellij\\Download\\2025\\08\\15/TODO.docx"; // 替换为您的文件路径

    private static final String CHARS_LOWER = "";
//    private static final String CHARS_UPPER = CHARS_LOWER.toUpperCase();
    private static final String NUMBERS = "01234568";
    private static final String SYMBOLS = "@";

    // 可根据需要调整字符集
    private static final String CHARSET = CHARS_LOWER + NUMBERS + SYMBOLS;

    public static void main(String[] args) {
        System.out.println("开始尝试Word文档密码...");
        
        // 生成可能的密码组合
        List<String> passwordVariations = generatePasswordVariations(KNOWN_PART);
//        List<String> passwordVariations = new ArrayList<>();
        passwordVariations.add("Niukun@12");
        passwordVariations.add("Niukun@0813");
        passwordVariations.add("Admin@123456");
        passwordVariations.add("Admin@12345");
        passwordVariations.add("Admin@1234");
        passwordVariations.add("Admin@123");
        passwordVariations.add("Admin@12");
        passwordVariations.add("Admin@1");
        passwordVariations.add("Admin@");
        passwordVariations.add("wlw@123456");
        passwordVariations.add("Zkml@123");
        passwordVariations.add("Zkml@123456");
        passwordVariations.add("Izkml");
        passwordVariations.add("Izkml@");
        passwordVariations.add("Izkml@1");
        passwordVariations.add("Izkml@12");
        passwordVariations.add("Izkml@123");
        passwordVariations.add("Izkml@1234");
        passwordVariations.add("Izkml@12345");
        passwordVariations.add("Izkml@123456");
        passwordVariations.add("10205050217");
        passwordVariations.add("Doit");
        passwordVariations.add("doit");
        passwordVariations.add("JustDoit");
        passwordVariations.add("Kniu@0813");
        passwordVariations.add("123456");
        passwordVariations.add("1234567");
        passwordVariations.add("12345678");
        passwordVariations.add("123456789");








        // 多线程尝试密码
        int threadCount = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);

        for (String password : passwordVariations) {
            executor.execute(() -> {
                try {
                    if (tryPassword(FILE_PATH, password)) {
                        System.out.println("\n密码找到: " + password);
                        executor.shutdownNow();
                    }
                } catch (Exception e) {
                    // 忽略错误继续尝试
                }
            });
        }

        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            System.out.println("破解过程被中断");
        }
    }

    private static List<String> generatePasswordVariations(String knownPart) {
        List<String> variations = new ArrayList<>();
        int knownLength = knownPart.length();
        int remainingLength = MAX_PASSWORD_LENGTH - knownLength;

        // 生成已知部分前后添加不同字符的变体
        for (int i = 0; i <= remainingLength; i++) {
            generateVariations(knownPart, i, remainingLength - i, variations);
        }

        return variations;
    }

    private static void generateVariations(String base, int prefixLength, int suffixLength, List<String> result) {
        if (prefixLength == 0 && suffixLength == 0) {
            result.add(base);
            return;
        }

        if (prefixLength > 0) {
            for (char c : CHARSET.toCharArray()) {
                generateVariations(c + base, prefixLength - 1, suffixLength, result);
            }
        } else {
            for (char c : CHARSET.toCharArray()) {
                generateVariations(base + c, 0, suffixLength - 1, result);
            }
        }
    }

    private static boolean tryPassword(String filePath, String password) throws Exception {
        try (InputStream fis = Files.newInputStream(Paths.get(filePath));
             POIFSFileSystem poifs = new POIFSFileSystem(fis)) {

            EncryptionInfo info = new EncryptionInfo(poifs);
            Decryptor decryptor = Decryptor.getInstance(info);

            if (decryptor.verifyPassword(password)) {
                return true;
            }
        } catch (Exception e) {
            // 密码错误会抛出异常
        }
        return false;
    }
}
