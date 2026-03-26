package com.nk.demo.feign.response.dataRepo.luobao;



import lombok.Data;

@Data
public class CountStatsResponse {
    private String cumulativeViolationCount;//累计违章次数
    private String currentYearViolationCount;//本年度违章次数
    private String endDate;//统计结束时间
    private String startDate;//统计开始时间
}
