package com.nk.demo.feign.response.dataRepo.luobao;


import lombok.Data;

@Data
public class ClaimStatsResponse {
        private String accidentTotal;//事故总数
        private String claimCount;//出险次数
        private String claimRate;//出险概率
}
