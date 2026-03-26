package com.nk.demo.feign.response.dataRepo.luobao;



import lombok.Data;

@Data
public class GetCumulativeOperationAnalysisResponse {
    private String partRatio;//环比（百分比）
    private String totalCost;//本年度运维成本
}
