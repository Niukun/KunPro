package com.nk.demo.feign.response.dataRepo.luobao;


import lombok.Data;

import java.util.List;

@Data
public class InsuranceCurrentYearResponse {
    private String carId;
    private String carNo;
    private String carFrameNo;
    private String brand;
    private String model;
    private String policyId;
    private String policyNo;
    private String policyType;
    private String policyTypeName;
    private String insuranceCompany;
    private String insureDate;
    private String effectDate;
    private String expireDate;
    private String yxDate;
    private String validityPeriod;
    private String compulsoryAmount;
    private String taxAmount;
    private String commercialTotalAmount;
    private String totalAmount;
    private List<CommercialDetails> commercialDetails;





    @Data
  public static   class CommercialDetails {
        private Integer index;
        private String actualName;
        private String itemAmount;
        private String itemName;
        private String itemQuota;
        private String noDeductible;
        private String policyId;
    }
}
