package com.nk.demo.study.milvus;

import io.milvus.v2.common.DataType;
import io.milvus.v2.common.IndexParam;
import io.milvus.v2.service.collection.request.*;
import io.milvus.v2.service.collection.response.DescribeCollectionResp;
import io.milvus.v2.service.collection.response.ListCollectionsResp;
import io.milvus.v2.service.utility.request.FlushReq;

import java.util.ArrayList;
import java.util.List;

import static com.nk.demo.study.milvus.BaseMilvus.COLLECTION_NAME;
import static com.nk.demo.study.milvus.BaseMilvus.client;

/**
 * 创建人：  @author niuk
 * 创建时间: 2026-03-26 14:12
 * 项目名称: KunPro
 * 文件名称: Test02Schema
 * 公司名称: 安徽产业互联数据智能创新中心有限公司
 */
public class Test02Schema {
    public static void main(String[] args) {
        dropCollections();
        createSchema();
        listSchema();
        client.close();

    }

    private static void createSchema() {
        // 3.1 Create schema
        CreateCollectionReq.CollectionSchema schema = client.createSchema();

// 3.2 Add fields to schema
        schema.addField(AddFieldReq.builder()
                .fieldName("id")
                .dataType(DataType.Int64)
                .isPrimaryKey(true)
                .autoID(false)
                .build());

        schema.addField(AddFieldReq.builder()
                .fieldName("vector")
                .dataType(DataType.FloatVector)
                .dimension(5)
                .build());

        CreateCollectionReq.CollectionSchema myVarchar = schema.addField(AddFieldReq.builder()
                .fieldName("color")
                .dataType(DataType.VarChar)
                .maxLength(12)
                .build());

        IndexParam indexParamForIdField = IndexParam.builder()
                .fieldName("id")
                .indexType(IndexParam.IndexType.STL_SORT)
                .build();

        IndexParam indexParamForVectorField = IndexParam.builder()
                .fieldName("vector")
                .indexType(IndexParam.IndexType.AUTOINDEX)
                .metricType(IndexParam.MetricType.L2)
                .build();

        List<IndexParam> indexParams = new ArrayList<>();
        indexParams.add(indexParamForIdField);
        indexParams.add(indexParamForVectorField);


        //创建 Collections
// 3.4 Create a collection with schema and index parameters
        CreateCollectionReq customizedSetupReq1 = CreateCollectionReq.builder()
                .collectionName(COLLECTION_NAME)
                .collectionSchema(schema)
                .indexParams(indexParams)

                .build();

        client.createCollection(customizedSetupReq1);
        System.out.println("Create collection " + COLLECTION_NAME + " successfully");

// 3.5 Get load state of the collection
        GetLoadStateReq customSetupLoadStateReq1 = GetLoadStateReq.builder()
                .collectionName(COLLECTION_NAME)
                .build();

        Boolean loaded = client.getLoadState(customSetupLoadStateReq1);
        System.out.println(loaded);


    }

    private static void listSchema() {
        ListCollectionsResp listCollectionsResp = client.listCollections();
        System.out.println(listCollectionsResp.getCollectionNames());
        for (String collectionName : listCollectionsResp.getCollectionNames()){
            DescribeCollectionReq request = DescribeCollectionReq.builder()
                    .collectionName(collectionName)
                    .build();
            DescribeCollectionResp resp = client.describeCollection(request);
            System.out.println(resp);
        }

    }

    private static void dropCollections() {

        ListCollectionsResp listCollectionsResp = client.listCollections();
        for (String collectionName : listCollectionsResp.getCollectionNames())
            if (collectionName.equals(collectionName)) {
                DropCollectionReq dropCollectionReq = DropCollectionReq.builder()
                        .collectionName(collectionName)
                        .build();
                client.dropCollection(dropCollectionReq);
                System.out.println("Drop collection " + collectionName + " successfully");
            }
    }

}
