package com.nk.demo.feign.response.dataRepo.luobao;



import lombok.Data;

@Data
public class GetCumulativeOperationMonthResponse {
    private String chargeCost;
    private String cleanBeautyCost;
    private String depreciationCost;
    private String etcCost;
    private String examinedCost;
    private String fuelCost;
    private String insuranceCost;
    private String maintenanceCost;
    private String monthName;
    private String otherCost;
    private String repairCost;
    private String totalCost;
}
