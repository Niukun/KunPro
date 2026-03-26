package com.nk.demo.feign.response.dataRepo.luobao;



import lombok.Data;

@Data
public class GetTerminalMileDetailResponse {
        private String currentYearMileage;//	本年行驶里程（km）
        private String totalMileage;//累计行驶里程（km）
}
