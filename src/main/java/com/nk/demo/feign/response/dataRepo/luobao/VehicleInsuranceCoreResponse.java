package com.nk.demo.feign.response.dataRepo.luobao;


import lombok.Data;

@Data
public class VehicleInsuranceCoreResponse {

    private String carId;
    private String carNo;
    private String currentYearCompulsory;
    private String currentYearTax;
    private String currentYearCommercial;
    private String currentYearTotal;
    private String lastYearCompulsory;
    private String lastYearTax;
    private String lastYearCommercial;
    private String lastYearTotal;
    private String premiumDecreaseRate;


}
