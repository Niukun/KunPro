package com.nk.demo.feign.response.dataRepo.luobao;



import lombok.Data;

import java.util.List;

@Data
public class LicenseFilesRegisterResponse {
    private List<LicenseFileItem> operationCertificateImage;
    private List<LicenseFileItem> registrationCertificateImage;
    private List<LicenseFileItem> vehicleCertificateImage;
    private List<VehicleDriverLicense> vehicleDriverLicense;

    @Data
    public static class LicenseFileItem{
        private String fileName;
        private String url;
    }

    @Data
    public  static  class VehicleDriverLicense{
        private String drivingImage;
        private String drivingLicenseCertificateDate;
        private String drivingLicenseRegisterDate;
        private String operationCertificateImage;
        private String operationCertificateName;
        private String registrationCertificateImage;
        private String registrationCertificateName;
        private String vehicleCertificateImage;
        private String vehicleCertificateName;
        private String vehicleLedgerId;
    }
}
