package com.nk.demo.study.map;

import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 创建人：  @author niuk
 * 创建时间: 2025-11-12 09:21
 * 项目名称: KunPro
 * 文件名称: GaoDe
 * 公司名称: 安徽产业互联数据智能创新中心有限公司
 */
public class GaoDe {

    private static String key = "6337a05320e33ee94a561309c774850c";

    private static String cityCode = "340100";

    public static void main(String[] args) throws IOException {

        testAllCompany();


//        getCompanyAddress();

    }

    private static void testAllCompany() throws IOException {
        List<String> companys = FileUtil.readLines("E:\\data\\Intellij\\Download\\2025\\11\\12\\companyName.txt", StandardCharsets.UTF_8);

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("company address");
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("序号");
        header.createCell(1).setCellValue("公司名称");
        header.createCell(2).setCellValue("country");
        header.createCell(3).setCellValue("formatted_address");
        header.createCell(4).setCellValue("city");
        header.createCell(5).setCellValue("adcode");
        header.createCell(6).setCellValue("province");
        header.createCell(7).setCellValue("location");

        for (int i = 0; i < companys.size(); i++) {
            Row row = sheet.createRow(i+1);
            row.createCell(0).setCellValue(i+1);
            row.createCell(1).setCellValue(companys.get(i));
            JSONArray companyArray = getCompanyAddress(companys.get(i));
            if( companyArray == null) continue;
            JSONObject companyAddress = companyArray.getJSONObject(0);
            row.createCell(2).setCellValue(companyAddress.getString("country"));
            row.createCell(3).setCellValue(companyAddress.getString("formatted_address"));
            row.createCell(4).setCellValue(companyAddress.getString("city"));
            row.createCell(5).setCellValue(companyAddress.getString("adcode"));
            row.createCell(6).setCellValue(companyAddress.getString("province"));
            row.createCell(7).setCellValue(companyAddress.getString("location"));

        }

        FileOutputStream fileOutputStream = new FileOutputStream("E:\\data\\Intellij\\Download\\2025\\11\\12\\companyAddress.xlsx");
        workbook.write(fileOutputStream);
        workbook.close();
        fileOutputStream.close();

    }

    private static JSONArray getCompanyAddress(String companyName) {
        RestTemplate restTemplate = new RestTemplate();

        String url = "https://restapi.amap.com/v3/geocode/geo?address="+companyName+"&city=" + cityCode + "&key=" + key;
        ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);
        JSONObject jsonObject = JSONObject.parseObject(forEntity.getBody());
        JSONArray result = jsonObject.getJSONArray("geocodes");
        return result;
    }


}
