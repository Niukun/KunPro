package com.nk.demo.feign.response.dataRepo.luobao;


import lombok.Data;

@Data
public class RepairStatsFeeTotalResponse {
    private String currentYearCost; // 本年度维保费用
    private String licensePlateNo; // 车牌号
    private String totalCost; // 累计维保费用
    private String vehicleId; // 车辆ID
    private String year; // 统计年份
}
