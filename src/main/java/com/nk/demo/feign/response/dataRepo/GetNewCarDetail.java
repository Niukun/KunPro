package com.nk.demo.feign.response.dataRepo;

import lombok.Data;

import java.util.List;

/**
 * @Description: TODO
 * @Author: zc
 * @Date: 2025/7/10 19:33
 **/
@Data
public class GetNewCarDetail {

    private String car_no;

    private String own_organ_name;

    private String own_organ_id;

    private String use_organ_id;

    private String use_organ_name;

    private String archives_no;

    private String gain_way;

    private String organization_status;

    private String car_sign_name;

    private String buy_fee;

    private String buy_time;

    private String original_book_value;

    private String certificate_time;

    private String vin;

    private String is_handle_identification;

    private List<String> img;

    private String date_created;

    private String vehicle_egistration_number;

    private String brand_model;

    private String origin_car_type;

    private String car_frame_no;

    private String mada_no;

    private String color;

    private String production_type;

    private String oil_type;

    private String mada_capacity;

    private String mada_type;

    private String oil_product;

    private String oil_unit;

    private String standard_oil_wear;

    private String battery_capacity;

    private String endurance;

    private String net_book_value;

    private String manufacture;

    private String manufacture_date;

    private String car_weight;

    private String emission_standard;

    private String car_asset_identification;

    private String car_asset_classification;

    private String posting_date;

    private String annual_total_run_fee;

    private String car_age;

    private String hundred_KM_power_consumption;

    private String seat_num;

    private String seat_num2;

    private String registration_certificate_img_url;

    private List<String> driving_license_img;

    private List<String> buy_tax_img;

    private List<String> car_invoice_img;

    private String record_date;

    private String car_title;

}
