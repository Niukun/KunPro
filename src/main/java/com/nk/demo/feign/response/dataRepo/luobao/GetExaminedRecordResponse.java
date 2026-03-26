package com.nk.demo.feign.response.dataRepo.luobao;


import lombok.Data;

@Data
public class GetExaminedRecordResponse {

    private String examinedAmount;//年检费用（元）
    private String examinedOrganization;//年检单位
    private String examinedTime;//年检时间
    private String licensePlateNo;//车牌号

}
