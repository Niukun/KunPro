package com.nk.demo.feign.response.dataRepo;

import lombok.Data;

import java.util.List;

/**
 * @title GetAccidentDetailResponse
 * @Description: todo
 * @Author tianwl
 * @Company 中科美络科技股份有限公司
 * @Email tianwl@izkml.com
 * @Date 2024/9/18 11:08
 */
@Data
public class GetAccidentDetailResponse {

    private List<GetAccidentDetailItem> detail;
    private String content;
    private String fileName;

    @Data
    public static class GetAccidentDetailItem {
        private  String driver_name;    //  驾驶员
        private  String accident_time;    //  事故日期
        private  String accident_type;    //  事故类型
        private  String accident_duty;    //  事故责任
        private  String accident_level;    //  事故程度
        private  String lost_fee;    //  损失金额（元）
        private  String insurance_fee;    //  保险赔偿金额（元）
    }

}
