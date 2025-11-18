package com.nk.demo.feign.response.dataRepo;

import lombok.Data;

/**
 * @Description: TODO
 * @Author: zc
 * @Date: 2025/7/2 16:06
 **/
@Data
public class GetApplyLogItem {

    private String apply_sn;

    private String apply_date;

    private String real_name;

    private String date_created;

    private String status;

    private String remark;

    private String note;


}
