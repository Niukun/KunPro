package com.nk.demo.feign.response.dataRepo;

import lombok.Data;

/**
 * @Description: 充电统计
 * @Author: zc
 * @Date: 2025/5/13 14:51
 **/
@Data
public class GetChargeStatisticsItem {

    //月份 2024-01
    private String month;

    private String car_no;

    private String charge_total;

    private String charge_fee;

    private String charge_power;
}
