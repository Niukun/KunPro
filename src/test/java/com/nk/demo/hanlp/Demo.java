//package com.nk.demo.hanlp;
//
//import com.hankcs.hanlp.restful.HanLPClient;
//import org.junit.Test;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.Map;
//
///**
// * 创建人：  @author niuk
// * 创建时间: 2025-11-10 15:12
// * 项目名称: KunPro
// * 文件名称: Demo
// * 公司名称: 安徽产业互联数据智能创新中心有限公司
// */
//public class Demo {
//
//
//    @Test
//    public void test() throws IOException {
//        HanLPClient client = new HanLPClient("https://hanlp.hankcs.com/api", null); // Replace null with your auth
//        Map<String, List> parse = client.parse("2021年HanLPv2.1为生产环境带来次世代最先进的多语种NLP技术。晓美焰来到北京立方庭参观自然语义科技公司。");
//        parse.entrySet().forEach(System.out::println);
//
//
//
//    }
//}
