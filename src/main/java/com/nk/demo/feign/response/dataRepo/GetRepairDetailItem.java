package com.nk.demo.feign.response.dataRepo;

import lombok.Data;

/**
 * @title GetRepairDetailItem
 * @Description: todo
 * @Author tianwl
 * @Company 中科美络科技股份有限公司
 * @Email tianwl@izkml.com
 * @Date 2024/9/18 10:38
 */
@Data
public class GetRepairDetailItem {
    private String apply_sn;    //  订单号
    private String send_real_name;  //  送检人
    private String repair_organ_name;   //  维保公司
    private String mile;    //  当前码表数
    private String price;   //  报价（元）
    private String cost;    //  费用（元）
    private String type;    //  类型
}
