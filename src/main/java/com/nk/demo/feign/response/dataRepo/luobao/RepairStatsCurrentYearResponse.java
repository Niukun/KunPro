package com.nk.demo.feign.response.dataRepo.luobao;


import lombok.Data;

@Data
public class RepairStatsCurrentYearResponse {
    private String accidentRepairCount;//事故维修次数
    private String licensePlateNo;//车牌号
    private String maintenanceCount;//保养次数
    private String regularRepairCount;//常规维修次数
    private String totalCount;//维保总次数
    private String washBeautyCount;//洗美次数
    private String year;//统计年份
}
