package com.nk.demo.feign.response.dataRepo;

import lombok.Data;

/**
 * @title GetOilDetailItem
 * @Description: todo
 * @Author tianwl
 * @Company 中科美络科技股份有限公司
 * @Email tianwl@izkml.com
 * @Date 2024/9/18 10:52
 */
@Data
public class GetOilDetailItem {
    private String order_sn; // 订单号
    private String company_name; // 加油公司
    private String driver_real_name; // 驾驶员
    private String oil_time; // 加油时间
    private String oil_type; // 油品
    private String oil_volume; // 加油量（L）
    private String oil_amount; // 金额（元）
    private String pay_type; // 付款方式
    private String oil_station_address; // 加油地点
    private String oil_card_no; // 加油卡信息
}
