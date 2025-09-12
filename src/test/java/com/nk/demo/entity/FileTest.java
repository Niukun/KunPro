package com.nk.demo.entity;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

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


}
