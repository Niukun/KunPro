package com.nk.demo.feign.response.dataRepo.luobao;



import lombok.Data;

@Data
public class FuellingInformationResponse {
    private Integer index;//序号
    private String carNo;//车牌号
    private String fuellingAmount;//加油费用（元）
    private String fuellingName;//加油站
    private String fuellingRise;//加油量（升）
    private String fuellingTime;//加油时间
    private FuellingType fuellingType;//燃油标号
    private UseCard useCard;//是否使用油卡

    @Data
    public static class FuellingType {
        private String desc;
        private String value;
    }
    @Data
    public static class UseCard {
        private String desc;
        private String value;
    }
}
