package com.nk.demo.feign.response.dataRepo.luobao;



import lombok.Data;

@Data
public class ViolationRecordsResponse {
    private String licensePlateNo;//车牌号
    private String deductionPoints;//扣分值
    private String driverName;//驾驶员姓名
    private String fineAmount;//罚款金额
    private String violationBehaviour;//违章行为
    private String violationTime;//违章时间

}
