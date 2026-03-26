package com.nk.demo.feign.response.dataRepo.luobao;



import lombok.Data;

import java.util.List;

@Data
public class LicenseFilesRegisterConvertResponse {
    private String drivingLicenseRegisterDate;
    private String drivingLicenseCertificateDate;
    private List<String> drivingImages;
    private List<String> operationCertificateImages;
    private List<String> registrationCertificateImages;
    private List<String> vehicleCertificateImages;
}
