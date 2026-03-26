package com.nk.demo.feign.response.dataRepo.luobao;



import lombok.Data;

@Data
public class DisposalInformationResponse {


    public String carNo;//车牌号
    public String disposalDeprecitionAmount;//已折旧额（元）
    public String disposalIncomeAmount;//处置金额（元）
    public String disposalNetAmount;//资产净值（元）
    public String disposalNo;//处置单号
    public String disposalRawAmount;//资产原值（元）
    public String disposalRemark;//处置原因
    public String disposalTime;//处置日期
    public String transferOrganizationName;//转入单位
}
