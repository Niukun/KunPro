package com.nk.demo.feign.response.dataRepo;

import lombok.Data;

/**
 * @Description: 车辆处置详情
 * @Author: zc
 * @Date: 2025/4/15 8:44
 **/
@Data
public class GetCarDisposalResponse {

    private String car_id;

    private String organ_id;

    private String text;

    private String brand_model;

    private String certificate_time;

    private String disposal_time;

    private String miles;

    private String disposal_type;

    private String annex;


}
