package com.nk.demo.feign.response.dataRepo.luobao;


import lombok.Data;

@Data
public class InfractionItemsResponse {
    private Integer index;//序号
    private String carNo;//车牌号
    private Double consumptionAmount;//消费金额
    private String etcNo;//ETC卡号
    private String exportName;//出口
    private String inlet;//入口
    private TransactionMatters transactionMatters;//交易事项
    private String transactionTime;//交易时间

    @Data
    public static class TransactionMatters{
        private String value;
        private String desc;
    }

}
