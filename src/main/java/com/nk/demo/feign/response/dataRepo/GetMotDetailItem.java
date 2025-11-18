package com.nk.demo.feign.response.dataRepo;

import lombok.Data;

/**
 * @title GetMotDetailItem
 * @Description: todo
 * @Author tianwl
 * @Company 中科美络科技股份有限公司
 * @Email tianwl@izkml.com
 * @Date 2024/9/18 10:43
 */
@Data
public class GetMotDetailItem {
    private String mot_day; //  本次年检时间
    private String mot_fee; //  年检费用（元）
    private String environment_tag; //  环保检测合格证
    private String environment_fee; //  环保检测费（元）
    private String mot_people; //  送检人
    private String mot_organ_name; //  年检公司
    private String next_mot_day; //  年检到期时间
}
