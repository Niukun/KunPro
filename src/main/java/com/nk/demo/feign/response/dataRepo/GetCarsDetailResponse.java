package com.nk.demo.feign.response.dataRepo;

import lombok.Data;

import java.util.List;

/**
 * @title GetCarsDetailResponse
 * @Description: todo
 * @Author tianwl
 * @Company 中科美络科技股份有限公司
 * @Email tianwl@izkml.com
 * @Date 2024/9/18 9:57
 */
@Data
public class GetCarsDetailResponse {
    private String car_no;  //  车牌号
    private String own_organ_id;
    private String own_organ_name;  //  机动车所有人
    private String use_organ_id;  //  使用单位
    private String use_organ_name; //使用单位名称
    private String archives_no;  //  资产编号
    private String gain_way;  //  取得方式
    private String organizational_status;  //  编制状态
    private String car_sign_name;  //  使用性质
    private String buy_fee;  //  购置价格
    private String buy_time;  //  购置日期
    private String original_book_value;  //  账面原值
    private String certificate_time;  //  车辆注册日期
    private String vin;  //  终端号
    private String is_handle_identification;  //  是否办理标识化
    private List<String> img;  //  车辆图片
    private String date_created;  //  登记日期
    private String vehicle_egistration_number;  //  机动车登记证编号
    private String brand_model;  //  品牌型号
    private String origin_car_type;  //  车辆类型
    private String car_frame_no;  //  车架号
    private String mada_no;  //  发动机号
    private String color;  //  车身颜色
    private String production_type;  //  国产/进口
    private String oil_type;  //  动力类型
    private String mada_capacity;  //  排量/功率
    private String mada_type;  //  变速箱
    private String oil_product;  //  燃油型号
    private String oil_uint;  //  油箱容量
    private String standard_oil_wear;  //  标准油耗
    private String battery_capacity;  //  电池容量
    private String endurance;  //  续航里程

    private String net_book_value;
    private String manufacturer; // 车辆品牌
    private String manufacturing_date; // 车辆生产日期
    private String car_weight; // 车辆重量
    private String emission_standard; // 排放标准
    private String car_asset_identification; // 车辆资产标识
    private String car_asset_classification; // 车辆资产分类
    private String posting_date;

    private String hundred_KM_power_consumption;  //  百公里耗电量
    private String seat_num;  //  核定载人数
    private String seat_num2;  //  车辆座位数
    private List<String> registration_certificate_img_url;  //  机动车登记证书（附件）
    private List<String> driving_license_img;  //  行驶证
    private List<String> buy_tax_img;  //  购车发票（附件）
    private List<String> car_invoice_img;  //  购置税发票（附件）

    //自定义的模板封面数据
    private String historyKey;
    private String historyTips;
}
