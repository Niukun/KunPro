package com.nk.demo.feign.response.dataRepo;

import lombok.Data;

/**
 * @title GetEtcDetailItem
 * @Description: todo
 * @Author tianwl
 * @Company 中科美络科技股份有限公司
 * @Email tianwl@izkml.com
 * @Date 2024/9/18 10:48
 */
@Data
public class GetEtcDetailItem {
    private String card_no; //  ETC卡号
    private String car_no; //  车牌号
    private String trans_fee; //  消费金额（元）
    private String enter_time; //  入口时间
    private String out_time; //  出口时间
    private String description; //  交易描述
}
