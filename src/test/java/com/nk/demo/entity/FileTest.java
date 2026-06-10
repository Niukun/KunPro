package com.nk.demo.entity;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FileTest {

    @Test
    public void cabiaTest() throws IOException {
        String string = FileUtils.readFileToString(new File("src/main/resources/files/cabia.txt"), StandardCharsets.UTF_8);
        String[] split = string.split("\\n");
        for (int i = 0; i < split.length; i++) {
            JSONObject jsonObject = JSONObject.parseObject(split[i]);
            String string1 = jsonObject.getJSONObject("payload").getString("object");
            System.out.println(i +  ": " + JSONObject.parseObject(string1, Address.class));
        }

    }

    @Test
    public void filedownLoad() throws IOException {
        Path path = Paths.get("e587233bfd4e958d0a4cc0fb65c991.docx");

        HttpUtil.downloadFile("https://digital-paper-bucket.obs.cn-east-3.myhuaweicloud.com:443/51dc05d7985341dcbb6c146cef5d6652.docx", path.toFile());

        System.out.println(path.toAbsolutePath().toString());

    }
    @Test
    public void fileCompare() throws IOException {
        Path path1 = Paths.get("E:\\data\\Intellij\\Download\\2026\\06\\09\\11\\410.txt");
        Path path2 = Paths.get("E:\\data\\Intellij\\Download\\2026\\06\\09\\11\\408.txt");
        List<String> lines1 = Files.readAllLines(path1);
        List<String> lines2 = Files.readAllLines(path2);

        Set<String> set1 = new HashSet<>(lines1);
        Set<String> set2 = new HashSet<>(lines2);

        Set<String> onlyInFile1 = new HashSet<>(set1);
        onlyInFile1.removeAll(set2);

        Set<String> onlyInFile2 = new HashSet<>(set2);
        onlyInFile2.removeAll(set1);

        System.out.println("===== 对比结果 =====");
        System.out.println("文件1总行数: " + lines1.size());
        System.out.println("文件2总行数: " + lines2.size());
        System.out.println();

        System.out.println("===== 只存在于文件1中的字符串 (共 " + onlyInFile1.size() + " 个) =====");
        onlyInFile1.stream().sorted().forEach(line ->
                System.out.println("[410.txt] " + line)
        );
        System.out.println();

        System.out.println("===== 只存在于文件2中的字符串 (共 " + onlyInFile2.size() + " 个) =====");
        onlyInFile2.stream().sorted().forEach(line ->
                System.out.println("[408.txt] " + line)
        );
        System.out.println();

        Set<String> commonLines = new HashSet<>(set1);
        commonLines.retainAll(set2);
        System.out.println("===== 两个文件共有的字符串 (共 " + commonLines.size() + " 个) =====");

    }


}
