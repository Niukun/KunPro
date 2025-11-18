package com.nk.demo.feign.response.dataRepo;

import lombok.Data;

/**
 * @title GetMilesItem
 * @Description: todo
 * @Author tianwl
 * @Company 中科美络科技股份有限公司
 * @Email tianwl@izkml.com
 * @Date 2024/9/18 10:56
 */
@Data
public class GetMilesItem {
    private String month;   //  月份
    private String car_no;   //  车牌号
    private String vin;   //  终端号
    private String car_sign_name;   //  车辆使用性质
    private String origin_car_type;   //  车型
    private String miles;   //  终端里程（Km）
}
