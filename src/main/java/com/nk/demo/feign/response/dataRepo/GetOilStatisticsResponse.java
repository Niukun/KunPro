package com.nk.demo.feign.response.dataRepo;

import lombok.Data;

import java.util.List;

/**
 * @Description: 加油统计
 * @Author: zc
 * @Date: 2025/5/13 14:13
 **/
@Data
public class GetOilStatisticsResponse {

    private List<GetOilStatisticsItem> detail;

    private String fileName;

}
