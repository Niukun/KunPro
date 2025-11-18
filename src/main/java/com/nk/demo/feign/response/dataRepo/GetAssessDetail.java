package com.nk.demo.feign.response.dataRepo;

import lombok.Data;

import java.util.List;

/**
 * @Description: TODO
 * @Author: zc
 * @Date: 2025/6/30 11:28
 **/
@Data
public class GetAssessDetail {

    // 评估单位信息
    private String assess_organ;

    private String assess_date;

    private String assess_contact_phone;

    private String record_remark;

    private String attach_url;

    private List<String> attach_img_urls;


    // 车辆信息
    private String car_no;

    private String car_sign_value;

    private String origin_cartype;

    private String brand_model;

    private String carframe_no;

    private String mada_no;

    private String energy_type;

    private String gas_unit;

    private String purchase_date;

    private String car_age;

    private String current_watch;

    private String mot_maturity_day;

    private String is_appear_major_traffic_accident;

    private String insurance_maturity_day;

    private String dispose_type;

    private String assess_price;

    private String company;

    private String have_violate_regulations;


}
