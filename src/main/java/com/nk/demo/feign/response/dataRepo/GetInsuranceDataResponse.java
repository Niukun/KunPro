package com.nk.demo.feign.response.dataRepo;

import lombok.Data;

import java.util.List;

/**
 * @title GetInsuranceDataResponse
 * @Description: todo
 * @Author tianwl
 * @Company 中科美络科技股份有限公司
 * @Email tianwl@izkml.com
 * @Date 2024/9/18 10:16
 */
@Data
public class GetInsuranceDataResponse {
    private List<GetInsuranceDataItem> detail;
    private List<String> annex;

    @Data
    public static class GetInsuranceDataItem {
        private String policy_no;   //  车险单号
        private String insurance_organ_name;   //  保险公司
        private String policy_type;   //  险种类别
        private String actual_name;   //  车险名称
        private String amount;   //  费用（元）
        private String effect_date;   //  有效期
        private String insure_date;   //  投保日期
        private String policy_status;   //  状态
    }

}
