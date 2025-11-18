package com.nk.demo.feign.response.dataRepo;

import lombok.Data;

/**
 * @Description: 车辆大事记
 * @Author: zc
 * @Date: 2025/4/15 11:18
 **/
@Data
public class GetCarBigEventResponse {

    private String time;

    private String title;

    private String content;
}
