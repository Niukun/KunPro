package com.nk.demo.feign.response.dataRepo;

import lombok.Data;

/**
 * @Description: 车辆变更详情
 * @Author: zc
 * @Date: 2025/5/13 14:47
 **/
@Data
public class GetChangeDetail {

    //变更内容
    private String content;

    //变更时间 2025-01-01
    private String date_created;

    //变更人
    private String user_real_name;
}
