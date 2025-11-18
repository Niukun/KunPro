package com.nk.demo.feign.response.dataRepo;

import lombok.Data;

import java.util.List;

/**
 * @Description: TODO
 * @Author: zc
 * @Date: 2025/6/30 11:25
 **/
@Data
public class GetCarApplyDetail {

    private String apply_organ_name;

    private String responsible_organ;

    private String contact_real_name;

    private String contact_phone;

    private String remark;

    private String apply_date;

    private String attach_url;

    //以下是需要通过处理的字段
    private List<String> attach_img_urls;

    private String apply_sn; //申请单号

    private String file_date; //归档日期

    private String file_tips; //归档提示

    private String year; //数据所属年份
}
