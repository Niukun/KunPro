package com.nk.demo.feign.response.dataRepo;

import lombok.Data;

import java.util.List;

/**
 * @Description: 车辆运行费用统计
 * @Author: zc
 * @Date: 2025/4/10 13:39
 **/
@Data
public class GetCarOperationFeeResponse {


    private String total;

    private List<CarMonthCount> monthFee;

    private String fileName;

    @Data
    public static class CarMonthCount {

        //月份 2024-01
        private String month;

        //附加费
        private String supplementFee;

        //维修费
        private String repairFee;

        //保险费
        private String insuranceFee;

        //保养费
        private String motFee;

        //etc费用
        private String etcFee;

        //停车费
        private String stopFee;

        //其他费用
        private String otherFee;

        //小计
        private String total;

    }






}
