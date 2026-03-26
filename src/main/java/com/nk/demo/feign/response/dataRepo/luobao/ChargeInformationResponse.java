package com.nk.demo.feign.response.dataRepo.luobao;


import lombok.Data;

@Data
public class ChargeInformationResponse {
    private Integer index;//序号
    private String carNo;//车牌号
    private String chargeDuration;//充电时长（分钟）
    private String chargeFee;//充电费用（元）
    private String chargePower;//充电电量（kwh）
    private String chargeTime;//充电时间
    private String stationName;//电站名称

}
