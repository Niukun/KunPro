package com.nk.demo.feign.response.dataRepo;

import lombok.Data;

import java.util.List;

/**
 * @Description: TODO
 * @Author: zc
 * @Date: 2025/6/30 11:35
 **/
@Data
public class GetAuctionDetail {


    private String car_no;

    private String car_sign_value;

    private String origin_cartype;

    private String brand_model;

    private String carframe_no;

    private String mada_no;

    private String assess_result;

    private String assess_price;

    private String deal_price;

    private String assess_fee;

    private String value_added_tax;

    private String surcharge;

    private String remittance_type;

    private String actual_hand_over;

    private String auction_company;

    private String auction_date;

    private String auction_phone;

    private String record_remark;

    private String attach_url;

    private List<String> attach_img_urls;

}
