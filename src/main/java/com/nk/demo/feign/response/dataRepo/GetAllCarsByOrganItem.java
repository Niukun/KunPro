package com.nk.demo.feign.response.dataRepo;

import lombok.Data;

/**
 * @title GetAllCarsByOrganItem
 * @Description: todo
 * @Author tianwl
 * @Company 中科美络科技股份有限公司
 * @Email tianwl@izkml.com
 * @Date 2024/9/18 9:55
 */
@Data
public class GetAllCarsByOrganItem {

    private String car_id;
    private String source;
    private String certificate_time; //注册时间
    private String disposal_time; //处置时间

}
