package com.nk.demo.utils;

import org.apache.commons.io.FileUtils;
import org.apache.poi.poifs.crypt.Decryptor;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.util.IOUtils;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class ExcelPwdUtil {
    // =============== 用户配置区域 ===============
    private static final String EXCEL_FILE = "E:\\data\\Intellij\\Download\\2025\\07\\16/111.xlsx";

    // 破解模式选择：DICTIONARY(字典模式) / BRUTE_FORCE(暴力模式) / COMBINED(组合模式)
    private static final AttackMode MODE = AttackMode.COMBINED;

    // 暴力模式配置
    private static final int MAX_PASSWORD_LENGTH = 10;  // 最大密码长度
//    private static final String CHARSET = "!@#&";                     // 常用特殊字符
    private static final String CHARSET = "&";                     // 常用特殊字符

    // 字典模式配置
    private static List<String> FIXED_STRINGS = new ArrayList<>();

    private static final List<String> FIXED_MARK =  Arrays.asList(  // 固定字符串列表
            "@","#","!","*","%","$"
    );

    private static final List<String> FIXED_ML =  Arrays.asList(  // 固定字符串列表
            "zkml","Zkml","Izkml","izkml"
    );




    // ========================================

    // 破解模式枚举
    enum AttackMode {
        DICTIONARY,    // 仅使用字典/固定字符串
        BRUTE_FORCE,   // 仅使用暴力破解
        COMBINED       // 先尝试字典，再尝试暴力破解
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        FIXED_STRINGS.add("0");
        FIXED_STRINGS.add("1");
//        FIXED_STRINGS.add("2");
        FIXED_STRINGS.add("3");
//        FIXED_STRINGS.add("4");
        FIXED_STRINGS.add("5");
//        FIXED_STRINGS.add("6");
        try {
            String foundPassword = crackExcel(EXCEL_FILE);
            FileUtils.write(new File("E:\\data\\Intellij\\Download\\2025\\07\\18\\password.txt"), foundPassword, StandardCharsets.UTF_8);

            if (foundPassword != null) {
                System.out.println("\n[成功] 密码破解成功: " + foundPassword);
                System.out.printf("耗时: %.2f 秒%n", (System.currentTimeMillis() - startTime) / 1000.0);
            } else {
                System.out.println("\n[失败] 未找到匹配密码");
            }
        } catch (Exception e) {
            System.err.println("破解过程中出错: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static String crackExcel(String filePath) throws Exception {
        // 读取文件到内存以减少IO操作
        byte[] fileData = readFileToBytes(filePath);

        // 根据配置模式执行破解
        return dictionaryAttack(fileData);
    }

    // ================= 字典攻击方法 =================
    private static String dictionaryAttack(byte[] fileData) throws Exception {
        List<String> dictionary = loadDictionary();

        System.out.println("加载密码数量: " + dictionary.size());
        System.out.println("开始测试...");

        for (int i = 0; i < dictionary.size(); i++) {
            String password = dictionary.get(i);
            if (System.currentTimeMillis() % 1000 < 50) {
//                System.out.printf("尝试: %s%n", password);
                System.out.println("正在测试第" + (i + 1) + "个密码: " + password + "，进展: " + ((i+1) * 1.0/ dictionary.size() * 100 + "%"));
            }
            if (testPassword(password, fileData)) {
                return password;
            }
        }
        return null;
    }

    private static List<String> loadDictionary() throws IOException {
        Set<String> passwords = new LinkedHashSet<>();

        List<String> list = new ArrayList<>();
        for(String mark : FIXED_MARK){

            for(String ml : FIXED_ML){
                FIXED_STRINGS.add(mark);
                FIXED_STRINGS.add(ml);
                list.addAll(getAllCombinations(FIXED_STRINGS));
                FIXED_STRINGS.remove(mark);
                FIXED_STRINGS.remove(ml);
            }

        }



        System.out.println("总数：" + list.size());
        // 添加固定字符串
        passwords.addAll(list);

        return new ArrayList<>(passwords);
    }

    private static List<String> getAllCombinations(List<String> input) {
        List<String> result = new ArrayList<>();

        List<String> current = new ArrayList<>();
        boolean[] used = new boolean[input.size()];
        generate(input, used, current, result);

        return result;
    }
    private static void generate(List<String> input, boolean[] used, List<String> current, List<String> result) {
        if (!current.isEmpty()) {
            result.add(String.join("", current));
        }

        for (int i = 0; i < input.size(); i++) {
            if (used[i]) continue;
            used[i] = true;
            current.add(input.get(i));
            generate(input, used, current, result);
            current.remove(current.size() - 1);
            used[i] = false;
        }
    }

    // ================= 暴力攻击方法 =================
    private static String bruteForceAttack(byte[] fileData, int maxLength, String charset)
            throws Exception {

        System.out.println("[模式] 暴力破解");
        System.out.println("字符集: " + charset);
        System.out.println("最大长度: " + maxLength);
        System.out.println("组合总数: " + calculateCombinations(charset, maxLength));

        int threadCount = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        AtomicBoolean found = new AtomicBoolean(false);
        String[] result = new String[1];

        // 按密码长度分层处理
        for (int len = 1; len <= maxLength && !found.get(); len++) {
            final int currentLen = len;
            executor.submit(() -> {
                try {
                    generateAndTest("", currentLen, charset, fileData, found, result);
                } catch (Exception e) {
                    if (!found.get()) {
                        System.err.println("线程错误: " + e.getMessage());
                    }
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.DAYS);

        return found.get() ? result[0] : null;
    }

    private static long calculateCombinations(String charset, int maxLength) {
        long total = 0;
        int base = charset.length();
        for (int i = 1; i <= maxLength; i++) {
            total += (long) Math.pow(base, i);
        }
        return total;
    }

    // ================= 密码生成与测试 =================
    private static void generateAndTest(String base, int length, String charset,
                                        byte[] fileData, AtomicBoolean found,
                                        String[] result) throws Exception {
        if (found.get()) return;

        if (length == 0) {
            if (testPassword(base, fileData)) {
                synchronized (found) {
                    if (!found.get()) {
                        found.set(true);
                        result[0] = base;
                    }
                }
            }
            return;
        }

        for (int i = 0; i < charset.length() && !found.get(); i++) {
            generateAndTest(base + charset.charAt(i), length - 1, charset, fileData, found, result);
        }
    }

    private static boolean testPassword(String password, byte[] fileData) {
        // 显示进度


        try (InputStream in = new ByteArrayInputStream(fileData);
             POIFSFileSystem poifs = new POIFSFileSystem(in)) {

            EncryptionInfo encInfo = new EncryptionInfo(poifs);
            Decryptor decryptor = Decryptor.getInstance(encInfo);

            return decryptor.verifyPassword(password);
        } catch (Exception e) {
            return false;
        }
    }

    // ================= 辅助方法 =================
    private static byte[] readFileToBytes(String filePath) throws IOException {
        try (InputStream in = new FileInputStream(filePath)) {
            return IOUtils.toByteArray(in);
        }
    }


}
