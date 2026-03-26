package com.nk.demo.feign.response.dataRepo.luobao;


import lombok.Data;

@Data
public class GetCumulativeOperationResponse {
    private String totalGdCost;
    private String totalWhCost;
    private String totalCost;
    private String totalOperationCost;
}
