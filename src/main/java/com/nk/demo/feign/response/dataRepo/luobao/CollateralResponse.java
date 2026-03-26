package com.nk.demo.feign.response.dataRepo.luobao;


import lombok.Data;

@Data
public class CollateralResponse {
     private String vehicleLedgerId;
     private String collateralStartDate;
     private String collateralEndDate;
     private String collateralOrgName;
     private String collateralRate;
}
