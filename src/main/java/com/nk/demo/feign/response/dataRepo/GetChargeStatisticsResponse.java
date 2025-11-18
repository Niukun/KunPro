package com.nk.demo.feign.response.dataRepo;

import lombok.Data;

import java.util.List;

/**
 * @Description: TODO
 * @Author: zc
 * @Date: 2025/5/13 14:52
 **/
@Data
public class GetChargeStatisticsResponse {

    private List<GetChargeStatisticsItem> detail;

    private String fileName;
}
