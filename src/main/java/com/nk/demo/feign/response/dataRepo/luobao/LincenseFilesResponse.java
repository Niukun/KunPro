package com.nk.demo.feign.response.dataRepo.luobao;


import lombok.Data;

@Data
public class LincenseFilesResponse {
     private String vehicleLedgerId;
     private String drivingLicenseRegisterDate;
     private String drivingLicenseCertificateDate;
     private String registrationCertificateImage;
     private String registrationCertificateName;
     private String vehicleCertificateImage;
     private String vehicleCertificateName;
     private String operationCertificateImage;
     private String operationCertificateName;
}
