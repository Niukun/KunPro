package com.nk.demo.study.milvus;

import io.milvus.v2.service.collection.request.GetLoadStateReq;
import io.milvus.v2.service.collection.request.LoadCollectionReq;
import io.milvus.v2.service.collection.request.ReleaseCollectionReq;

import static com.nk.demo.study.milvus.BaseMilvus.COLLECTION_NAME;
import static com.nk.demo.study.milvus.BaseMilvus.client;

/**
 * 创建人：  @author niuk
 * 创建时间: 2026-03-26 15:07
 * 项目名称: KunPro
 * 文件名称: Test04LoadCollection
 * 公司名称: 安徽产业互联数据智能创新中心有限公司
 */
public class Test03LoadCollection {
    public static void main(String[] args) {
        LoadCollectionReq  loadCollectionReq = LoadCollectionReq.builder()
                .collectionName(COLLECTION_NAME)
                .build();
        client.loadCollection(loadCollectionReq);
        GetLoadStateReq customSetupLoadStateReq1 = GetLoadStateReq.builder()
                .collectionName(COLLECTION_NAME)
                .build();
        Boolean res = client.getLoadState(customSetupLoadStateReq1);
        System.out.println(res);

        ReleaseCollectionReq releaseCollectionReq = ReleaseCollectionReq.builder()
                .collectionName(COLLECTION_NAME)
                .build();
        client.releaseCollection(releaseCollectionReq);
        res = client.getLoadState(customSetupLoadStateReq1);
        System.out.println(res);
    }
}
