package com.nk.demo.study.milvus;


import io.milvus.v2.service.database.request.CreateDatabaseReq;
import io.milvus.v2.service.database.response.ListDatabasesResp;

import static com.nk.demo.study.milvus.BaseMilvus.DB_NAME;
import static com.nk.demo.study.milvus.BaseMilvus.client;

/**
 * 创建人：  @author niuk
 * 创建时间: 2026-03-23 17:31
 * 项目名称: KunPro
 * 文件名称: Test01
 * 公司名称: 安徽产业互联数据智能创新中心有限公司
 */
public class Test01DB{


    public static void main(String[] args) throws InterruptedException {
//        create();
        listDB();
        useDb();
        client.close();
    }

    private static void useDb() throws InterruptedException {
        client.useDatabase(DB_NAME);

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
