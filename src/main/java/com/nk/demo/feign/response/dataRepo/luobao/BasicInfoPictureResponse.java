package com.nk.demo.feign.response.dataRepo.luobao;


import lombok.Data;

@Data
public class BasicInfoPictureResponse {
    //http://172.30.13.3:22020/doc.html#/home
     private String commonFileId;
     private String name;
     private String url;
     private FileBusinessType fileBusinessType;
     private String fileBusinessId;
     private Integer sortNo;
     @Data
     class FileBusinessType {
      private String value;
      private String desc;
     }

}
