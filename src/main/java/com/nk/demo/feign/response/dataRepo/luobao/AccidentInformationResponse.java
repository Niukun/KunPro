package com.nk.demo.feign.response.dataRepo.luobao;



import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AccidentInformationResponse {
    private List<String> liabilityCertificateImages = new ArrayList<>();
    private String accidentId;
    private String accidentTime;
    private String carNo;
    private CauseInfo cause;
    private AccidentLiability accidentLiability;
    private AccidentLevel accidentLevel;
    private String accidentAddress;
    private String accidentDetails;
    private String usageOrganizationName;
    private String driverName;
    private Integer companyUndertakeAmount;
    private Integer insuranceUndertakeAmount;
    private String maintainCompleteTime;

    private List<AccidentClaimPaper> accidentClaimPapers;
    private ClosedState closedState;
    private String maintainAmount;
    private String accidentNo;


    @Data
    public static class   AccidentClaimPaper{
      private String url;
      private String fileName;
    }
    @Data
    public static class CauseInfo{
        private String value;
        private String desc;
    }
    @Data
    public static class AccidentLiability{
        private String value;
        private String desc;
    }
    @Data
    public static class AccidentLevel{
        private String value;
        private String desc;
    }
    @Data
    public static class ClosedState{
        private String value;
        private String desc;
    }

}
