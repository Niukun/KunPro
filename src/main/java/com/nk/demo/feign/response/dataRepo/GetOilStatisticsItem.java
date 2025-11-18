package com.nk.demo.feign.response.dataRepo;

import lombok.Data;

/**
 * @title GetOilStatisticsItem
 * @Description: todo
 * @Author tianwl
 * @Company 中科美络科技股份有限公司
 * @Email tianwl@izkml.com
 * @Date 2024/9/18 10:50
 */
@Data
public class GetOilStatisticsItem {
    private String month;   //  月份
    private String car_no;   //  车牌号
    private String oil_total;   //  加油次数
    private String oil_volume;   //  加油量
    private String oil_amount;   //  加油费用
}
