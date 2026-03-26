package com.nk.demo.feign.response.dataRepo.luobao;


import lombok.Data;

@Data
public class VehicleOwnershipAndPurchaseResponse {
    private String vehicleLedgerId;
    private String ownershipOrgName;
    private String usageOrgName;
    private String vehicleUseCharacter;
    private GainingMethod gainingMethod;
    private PurchaseType purchaseType;
    private String purchaseTotalPriceTax;
    private String guidancePrice;
    private String purchaseTaxAmount;
    private String purchasePriceWithoutTax;
    private String vatAmount;
    private String purchaseDate;
    private String sellerName;
    @Data
    public static class GainingMethod {
        private String value;
        private String desc;
    }
    @Data
    public static class PurchaseType {
        private String value;
        private String desc;
    }
}
