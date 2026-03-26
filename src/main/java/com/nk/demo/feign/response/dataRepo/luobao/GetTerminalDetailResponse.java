package com.nk.demo.feign.response.dataRepo.luobao;



import lombok.Data;

@Data
public class GetTerminalDetailResponse {

    private BindStatus bindStatus;//终端绑定状态
    private String terminalModel;//终端型号
    private String terminalNo;//终端编号

    @Data
    public static class BindStatus{
        private String value;
        private String desc;
    }
}
