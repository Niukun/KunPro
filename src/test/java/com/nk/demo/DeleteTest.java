package com.nk.demo;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 创建人：  @author niuk
 * 创建时间: 2025-12-01 11:48
 * 项目名称: KunPro
 * 文件名称: DeleteTest
 * 公司名称: 安徽产业互联数据智能创新中心有限公司
 */
public class DeleteTest {


    @Test
    public void testDeleteFile(){
        String fileHistoryRunnable = "true";
        String fileHistoryTotal = "true";
        String startTime = DateUtil.format(DateUtil.offsetMonth(new Date(), -7), "yyyy-MM-dd");
        //如果是归档上一年数据，则从去年初获取处置信息,全量数据归档时，覆盖当年处置的车辆，日常更新时，取七天的处置信息，防止遗漏以及避免重复处理
        if ("true".equals(fileHistoryRunnable) && "false".equals(fileHistoryTotal)) {
            int year = DateUtil.thisYear() - 1;
            startTime = year + "-01-01";
        } else if ("true".equals(fileHistoryRunnable) && "true".equals(fileHistoryTotal)) {
            startTime = DateUtil.thisYear() + "-01-01";
        }
        String endTime = DateUtil.format(new Date(), "yyyy-MM-dd");
        System.out.println("startTime: " + startTime + ", endTime: " + endTime);
    }

    @Test
    public void testDelete(){

        List<String> strings = FileUtil.readLines("E:\\data\\Intellij\\Download\\2025\\12\\01\\urls.txt", StandardCharsets.UTF_8);
        String url ="http://10.17.139.183/file/delete";
        RestTemplate restTemplate = new RestTemplate();


        for (String s : strings) {
            MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
            formData.add("fileUrl",s);

            // 设置请求头为表单格式
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);


            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(formData, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);

            System.out.println(response);
        }



    }
}
