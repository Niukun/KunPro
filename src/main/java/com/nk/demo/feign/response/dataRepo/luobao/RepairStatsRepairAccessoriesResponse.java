package com.nk.demo.feign.response.dataRepo.luobao;



import lombok.Data;

@Data
public class RepairStatsRepairAccessoriesResponse {

        private String vehicleId;
        private String currentYearPartFee;
        private String currentYearTireFee;
        private String lastYearPartFee;
        private String lastYearTireFee;
        private String partFeeRatio;
        private String tireFeeRatio;

}
