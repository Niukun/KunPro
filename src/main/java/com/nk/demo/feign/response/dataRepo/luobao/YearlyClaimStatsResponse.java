package com.nk.demo.feign.response.dataRepo.luobao;



import lombok.Data;

@Data
public class YearlyClaimStatsResponse {

    private String vehicleLedgerId;
    private String currentYearClaimCount;
    private String currentYearClaimAmount;
}
