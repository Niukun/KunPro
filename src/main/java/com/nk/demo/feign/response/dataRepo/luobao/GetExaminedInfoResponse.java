package com.nk.demo.feign.response.dataRepo.luobao;


import lombok.Data;

@Data
public class GetExaminedInfoResponse {
            private String examinedNextTime;//检验有效期止
            private ExaminedType examinedType;//年检方式,可用值:ONLINE_EXAMINED,UN_ONLINE_EXAMINED


    @Data
    private  static class ExaminedType{
        private String value;
        private String desc;
    }
}
