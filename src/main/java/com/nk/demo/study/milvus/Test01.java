package com.nk.demo.study.milvus;


import io.milvus.v2.client.ConnectConfig;
import io.milvus.v2.client.MilvusClientV2;
import io.milvus.v2.service.database.request.CreateDatabaseReq;
import io.milvus.v2.service.database.response.ListDatabasesResp;

/**
 * 创建人：  @author niuk
 * 创建时间: 2026-03-23 17:31
 * 项目名称: KunPro
 * 文件名称: Test01
 * 公司名称: 安徽产业互联数据智能创新中心有限公司
 */
public class Test01 {

    private static MilvusClientV2 client;

    private static ConnectConfig config;



    static {
        config = ConnectConfig.builder()
                .uri("http://localhost:19530")
                .build();
        client = new MilvusClientV2(config);

    }

    public static void main(String[] args) {
//        create();
        listDB();
//        useDb();
        client.close();
    }

    private static void useDb() {


    }


    private static void create() {
        CreateDatabaseReq createDatabaseReq = CreateDatabaseReq.builder()
                .databaseName("my_database_1")
                .build();
        client.createDatabase(createDatabaseReq);
    }

    private static void listDB() {
        ListDatabasesResp response = client.listDatabases() ;
        response.getDatabaseNames().forEach(System.out::println);

    }


}
