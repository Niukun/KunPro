package com.nk.demo.feign.response.dataRepo;

import lombok.Data;

/**
 * @Description: TODO
 * @Author: zc
 * @Date: 2025/6/30 11:42
 **/
@Data
public class GetCarsCompileDetail {


    private String compileTotal;

    private String organTotal;

    private String realTotal;

    private String productionTotal;

    private String productionAcc;

    private String oilTotal;

    private String oilAcc;

    private String vacancyRate;


}
