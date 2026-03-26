package com.nk.demo.feign.response.dataRepo.luobao;


import lombok.Data;

@Data
public class VehicleAssetValueResponse {
    private String vehicleLedgerId;
    private String licensePlate;
    private String originalAssetValue;
    private String netAssetValue;
    private String depreciationRate;
    private String accumulatedDepreciation;
}
