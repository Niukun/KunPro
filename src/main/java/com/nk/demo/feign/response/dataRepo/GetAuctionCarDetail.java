package com.nk.demo.feign.response.dataRepo;

import lombok.Data;

/**
 * @Description: TODO
 * @Author: zc
 * @Date: 2025/6/30 11:31
 **/
@Data
public class GetAuctionCarDetail {

    private String car_no;

    private String car_sign_value;

    private String origin_cartype;

    private String brand_model;

    private String carframe_no;

    private String mada_no;

    private String assess_result;

    private String assess_price;

    private String assess_fee;

    private String value_added_tax;

    private String surcharge;

    private String remittance_type;

    private String actual_hand_over;
}
