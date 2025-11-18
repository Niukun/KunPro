package com.nk.demo.feign.response.dataRepo;

import lombok.Data;

import java.util.List;

/**
 * @Description: TODO
 * @Author: zc
 * @Date: 2025/7/10 15:35
 **/
@Data
public class GetScrapDetail {


    private String car_no;

    private String car_sign_value;

    private String origin_cartype;

    private String brand_model;

    private String carframe_no;

    private String mada_no;

    private String assess_result;

    private String tonnage;

    private String unit_price;

    private String scrap_price;

    private String assess_fee;

    private String remittance_type;

    private String actual_hand_over;

    private String scrap_company;

    private String scrap_date;

    private String dispose_contact_phone;

    private String record_mark;

    private String attach_url;

    private List<String> attach_img_urls;



}
