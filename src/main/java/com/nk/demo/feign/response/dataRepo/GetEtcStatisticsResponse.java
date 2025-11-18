package com.nk.demo.feign.response.dataRepo;

import lombok.Data;

import java.util.List;

/**
 * @Description: 车辆etc统计
 * @Author: zc
 * @Date: 2025/5/13 14:11
 **/
@Data
public class GetEtcStatisticsResponse {

    private List<GetEtcStatisticsItem> detail;

    private String fileName;

}
