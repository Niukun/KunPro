package com.nk.demo.feign.response.dataRepo;

import lombok.Data;

/**
 * @title GetEtcStatisticsItem
 * @Description: todo
 * @Author tianwl
 * @Company 中科美络科技股份有限公司
 * @Email tianwl@izkml.com
 * @Date 2024/9/18 10:46
 */
@Data
public class GetEtcStatisticsItem {
    private String month;   //  时间
    private String car_no;   //  车牌号
    private String card_no;   //  ETC卡号
    private String total;   //  通行次数
    private String fee;   //  总费用
}
