package com.nk.demo.entity;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

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
}
