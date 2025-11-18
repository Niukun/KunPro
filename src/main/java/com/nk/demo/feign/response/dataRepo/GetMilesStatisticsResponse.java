package com.nk.demo.feign.response.dataRepo;

import lombok.Data;

import java.util.List;

/**
 * @Description: TODO
 * @Author: zc
 * @Date: 2025/5/13 14:16
 **/
@Data
public class GetMilesStatisticsResponse {

    private List<GetMilesItem> detail;

    private String total;

    private String fileName;

}
