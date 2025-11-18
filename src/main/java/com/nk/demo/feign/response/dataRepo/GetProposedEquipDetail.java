package com.nk.demo.feign.response.dataRepo;

import lombok.Data;

/**
 * @Description: TODO
 * @Author: zc
 * @Date: 2025/7/10 19:25
 **/
@Data
public class GetProposedEquipDetail {

    private String car_sign_value;

    private String brand_model;

    private String origin_cartype;

    private String energy_type;

    private String gas_unit;

    private String buy_fee;

    private String num;

    private String financial_allocation;

    private String self_finance;

    private String other_finance;

    private String fund_source;

    private String is_identify;

    private String identify_reject_reason;

    private String is_install_terminal;

    private String install_reject_reason;

}
