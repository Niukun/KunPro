package com.nk.demo.feign.response.dataRepo.luobao;


import lombok.Data;

@Data
public class DisposalResponse {
    private String vehicleLedgerId;
    private String disposalDate;
    private String disposalAmount;
    private DisposalType disposalType;
    private String transferOrgName;

    @Data
    public static class DisposalType {
        private String value;
        private String desc;
    }
}
