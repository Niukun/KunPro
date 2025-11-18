package com.nk.demo.feign.response.dataRepo;

import lombok.Data;

/**
 * @title GetViolationDetailItem
 * @Description: todo
 * @Author tianwl
 * @Company 中科美络科技股份有限公司
 * @Email tianwl@izkml.com
 * @Date 2024/9/18 10:59
 */
@Data
public class GetViolationDetailItem {
    private String violation_driver_name;   //  驾驶员
    private String violation_time;   //  违章时间
    private String violation_behavior;   //  违章行为
    private String score;   //  扣分
    private String amount;   //  罚款（元）
    private String process_status;   //  处理状态
}
