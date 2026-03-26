package com.nk.demo.feign.response.dataRepo.luobao;


import lombok.Data;

@Data
public class BasicInfoResponse {
    //http://172.30.13.3:22020/doc.html#/home
     private String vehicleLedgerId;
     private String businessId;
     private String vin;
     private String engineNo;
     private String licensePlateNo;
     private String licensePlateColor;
     private String vehicleOwner;
     private String brand;
     private String series;
     private String model;
     private String vehicleType;
     private String dimensions;
     private String bodyColor;
     private String totalMass;
     private String curbMass;
     private String ratedLoadCapacity;
     private String approvedCapacity;
     private String powerType;
     private String transmissionType;
     private String power;
     private String batteryCapacity;
     private String powerConsumptionPer;
     private String enduranceMileage;

 //自定义的模板封面数据
 private String period;
 private String historyKey;
 private String historyTips;

}
