package com.nk.demo.study.milvus;


import io.milvus.client.MilvusServiceClient;
import io.milvus.grpc.DataType;
import io.milvus.grpc.ListDatabasesResponse;
import io.milvus.param.ConnectParam;
import io.milvus.param.R;
import io.milvus.param.RpcStatus;
import io.milvus.param.collection.CreateCollectionParam;
import io.milvus.param.collection.CreateDatabaseParam;
import io.milvus.param.collection.FieldType;
import io.milvus.param.dml.InsertParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 创建人：  @author niuk
 * 创建时间: 2026-03-23 17:31
 * 项目名称: KunPro
 * 文件名称: Test01
 * 公司名称: 安徽产业互联数据智能创新中心有限公司
 */
public class Test01 {

    private static MilvusServiceClient client;
    static {
        client = new MilvusServiceClient(
                ConnectParam.newBuilder()
                        .withHost("localhost")
                        .withPort(19530)
                        .build()
        );

    }

    public static void main(String[] args) {
//        create();
//        listDB();
        useDb();
        client.close();
    }

    private static void useDb() {
        client = new MilvusServiceClient(
                ConnectParam.newBuilder()
                        .withHost("localhost")
                        .withPort(19530)
                        .withDatabaseName("mytest")
                        .build()
        );

        FieldType fieldType1 = FieldType.newBuilder()
                .withName("book_id")
                .withDataType(DataType.Int64)
                .withPrimaryKey(true)
                .withAutoID(false)
                .build();
        FieldType fieldType2 = FieldType.newBuilder()
                .withName("word_count")
                .withDataType(DataType.Int64)
                .build();
        FieldType fieldType3 = FieldType.newBuilder()
                .withName("book_intro")
                .withDataType(DataType.FloatVector)
                .withDimension(2)
                .build();
        CreateCollectionParam createCollectionReq = CreateCollectionParam.newBuilder()
                .withCollectionName("book")
                .withDescription("Test book search")
                .withShardsNum(2)
                .addFieldType(fieldType1)
                .addFieldType(fieldType2)
                .addFieldType(fieldType3)
                .withEnableDynamicField(true)
                .build();

        client.createCollection(createCollectionReq);

        Random ran = new Random();
        List<Long> book_id_array = new ArrayList<>();
        List<Long> word_count_array = new ArrayList<>();
        List<List<Float>> book_intro_array = new ArrayList<>();
        for (long i = 0L; i < 2000; ++i) {
            book_id_array.add(i);
            word_count_array.add(i + 10000);
            List<Float> vector = new ArrayList<>();
            for (int k = 0; k < 2; ++k) {
                vector.add(ran.nextFloat());
            }
            book_intro_array.add(vector);
        }

        List<InsertParam.Field> fields = new ArrayList<>();
        fields.add(new InsertParam.Field("book_id", book_id_array));
        fields.add(new InsertParam.Field("word_count", word_count_array));
        fields.add(new InsertParam.Field("book_intro", book_intro_array));

        InsertParam insertParam = InsertParam.newBuilder()
                .withCollectionName("book")
                .withPartitionName("novel")
                .withFields(fields)
                .build();
        client.insert(insertParam);



    }


    private static void create() {
        CreateDatabaseParam createDatabaseParam = CreateDatabaseParam.newBuilder()
                .withDatabaseName("mytest")
                .build();

        R<RpcStatus> createDatabaseResponse = client.createDatabase(createDatabaseParam);

        if (createDatabaseResponse.getStatus() != R.Status.Success.getCode()) {
            System.out.println(createDatabaseResponse.getMessage());
        }

    }

    private static void listDB() {
        R<ListDatabasesResponse> response = client.listDatabases();

        int dbCounts = response.getData().getDbNamesCount();

        for (int i = 0; i < dbCounts; i++) {
            System.out.println(response.getData().getDbNames(i));
        }

    }


}
