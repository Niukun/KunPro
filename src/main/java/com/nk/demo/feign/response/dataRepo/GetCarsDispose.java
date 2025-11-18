package com.nk.demo.feign.response.dataRepo;

import lombok.Data;

/**
 * @Description: TODO
 * @Author: zc
 * @Date: 2025/6/30 11:50
 **/
@Data
public class GetCarsDispose {

    private String dispose_type;

    private Integer total;

    private Integer allTotal;

}
