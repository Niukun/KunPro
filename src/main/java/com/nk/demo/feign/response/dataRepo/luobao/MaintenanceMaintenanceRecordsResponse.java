package com.nk.demo.feign.response.dataRepo.luobao;



import lombok.Data;

import java.util.List;

@Data
public class MaintenanceMaintenanceRecordsResponse {
    private AccidentLevel accidentLevel;
    private AccidentLiability accidentLiability;
    private String applyMile;
    private String beginTime;
    private String businessName;
    private String lastMaintainMile;
    private String licensePlateNo;
    private String orderId;
    private String orderNo;
    private String partAmount;
    private String storeName;
    private String totalActualAmount;
    private String vehicleConditionComment;
    private List<PartDetailVO> partDetailVOList;
    private List<ProjectDetailVO> projectDetailVOList;

    private String  partDetailText=generatePartDetailText();
    private String  projectDetailText=generateProjectDetailText();


    @Data
    public static class AccidentLevel {
        private String   value;
        private String desc;;
    }
    @Data
    public static class AccidentLiability {
        private String   value;
        private String desc;;
    }

    @Data
    public static class PartDetailVO {
        private String partQuantity;
        private String projectAmount;
        private String projectName;
    }
    @Data
    public static class ProjectDetailVO {
        private String partQuantity;
        private String projectAmount;
        private String projectName;
    }

    public String generatePartDetailText() {
        if (partDetailVOList == null || partDetailVOList.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= partDetailVOList.size(); i++) {
            PartDetailVO detail = partDetailVOList.get(i-1);
            sb.append("配件"+i+"："+detail.getProjectName()+"("+detail.getPartQuantity()+"个，"+detail.getProjectAmount()+"元)");
            if (i < partDetailVOList.size() ) {
                sb.append( "\n"); // 使用换行符
            }
        }
        return sb.toString();
    }

    public String generateProjectDetailText() {
        if (projectDetailVOList == null || projectDetailVOList.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= projectDetailVOList.size(); i++) {
            ProjectDetailVO detail = projectDetailVOList.get(i-1);
            sb.append("项目"+i+"："+detail.getProjectName()+"，"+detail.getProjectAmount()+"元；");
            if (i < projectDetailVOList.size() ) {
                sb.append( "\n"); // 使用换行符
            }
        }
        return sb.toString();
    }
}
