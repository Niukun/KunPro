package com.nk.demo.feign.response.dataRepo.luobao;


import lombok.Data;

@Data
public class AccidentLevelLiabilityStatResponse {

    private Integer vehicleLedgerId;
    private Integer minorFullLiability;
    private Integer minorMainLiability;
    private Integer minorEqualLiability;
    private Integer minorSecondaryLiability;
    private Integer minorNoLiability;
    private Integer minorTotal;
    private Integer generalFullLiability;
    private Integer generalMainLiability;
    private Integer generalEqualLiability;
    private Integer generalSecondaryLiability;
    private Integer generalNoLiability;
    private Integer generalTotal;
    private Integer majorFullLiability;
    private Integer majorMainLiability;
    private Integer majorEqualLiability;
    private Integer majorSecondaryLiability;
    private Integer majorNoLiability;
    private Integer majorTotal;
    private Integer seriousFullLiability;
    private Integer seriousMainLiability;
    private Integer seriousEqualLiability;
    private Integer seriousSecondaryLiability;
    private Integer seriousNoLiability;
    private Integer seriousTotal;
    private Integer fullLiabilityTotal;
    private Integer mainLiabilityTotal;
    private Integer equalLiabilityTotal;
    private Integer secondaryLiabilityTotal;
    private Integer noLiabilityTotal;
    private Integer accidentTotal;

}
