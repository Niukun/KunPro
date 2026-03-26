package com.nk.demo.feign.response.dataRepo.luobao;


import lombok.Data;

import java.util.List;

@Data
public class InsuranceResponse {
   private List<InsuranceCurrentYearResponse> jqxList;
   private List<InsuranceCurrentYearResponse> syxList;

}
