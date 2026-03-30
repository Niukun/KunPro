package com.nk.demo.study.milvus;

import io.milvus.v2.client.ConnectConfig;
import io.milvus.v2.client.MilvusClientV2;

/**
 * 创建人：  @author niuk
 * 创建时间: 2026-03-26 14:11
 * 项目名称: KunPro
 * 文件名称: BaseMilvus
 * 公司名称: 安徽产业互联数据智能创新中心有限公司
 */
public class BaseMilvus {

    static MilvusClientV2 client;

    static ConnectConfig config;

    static final String DB_NAME = "my_database_1";

    static final String COLLECTION_NAME = "my_collection";


    static {
        config = ConnectConfig.builder()
                .uri("http://localhost:19531")
                .build();
        client = new MilvusClientV2(config);

    }
}
