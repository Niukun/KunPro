package com.nk.demo.feign;

import com.nk.demo.feign.response.dataRepo.luobao.*;
import com.nk.demo.ml.staff.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "LuoBaoCarFeignClient",url = "${zkml.luobao.url}")
public interface LuoBaoCarFeignClient {

    //车辆基本信息通过单位id获取
    @GetMapping("/api/report/car/basicInfoByOrgId")
    JsonResult<List<BasicInfoByOrgIdResponse>> basicInfoByOrgId(@RequestParam("organId")String orgId);

    //车辆基本信息
    @GetMapping("/api/report/car/basicInfo")
    JsonResult<BasicInfoResponse> basicInfo(@RequestParam("vin")String vin);

    //车辆基本信息图片
    @GetMapping("/api/report/car/basicInfoPicture/{vin}")
    JsonResult<List<BasicInfoPictureResponse>> basicInfoPicture(@PathVariable("vin")String vin);

    //资产抵押
    @GetMapping("/api/report/car/collateral/{vehicleLedgerId}")
    JsonResult<CollateralResponse> collateral(@PathVariable("vehicleLedgerId")String vehicleLedgerId);

    //车辆处置
    @GetMapping("/api/report/car/disposal/{vehicleLedgerId}")
    JsonResult<List<DisposalResponse>> disposal(@PathVariable("vehicleLedgerId")String vehicleLedgerId);

    //查询车辆证照文件信息
    @GetMapping("/api/report/car/license-files/{vehicleLedgerId}")
    JsonResult<LincenseFilesResponse> licenseFiles(@PathVariable("vehicleLedgerId")String vehicleLedgerId);

    //    查询车辆证照文件信息通过类型
    @GetMapping("/api/report/car/license-files/register/{vehicleLedgerId}")
    JsonResult<LicenseFilesRegisterResponse> licenseFilesRegister(@PathVariable("vehicleLedgerId")String vehicleLedgerId);

    //查询车辆完整购置发票信息
    @GetMapping("/api/report/car/purchase-invoice-full/{vehicleLedgerId}")
    JsonResult<PurchaseInvoiceFullResponse> purchaseInvoiceFull(@PathVariable("vehicleLedgerId")String vehicleLedgerId);

    //车辆权属信息及资产购置
    @GetMapping("/api/report/car/vehicleOwnershipAndPurchase/{vehicleLedgerId}")
    JsonResult<VehicleOwnershipAndPurchaseResponse> vehicleOwnershipAndPurchase(@PathVariable("vehicleLedgerId")String vehicleLedgerId);

    // 查询事故等级与责任统计
    @GetMapping("/api/report/car/accident-stat/getAccidentLevelLiabilityStat")
    JsonResult<AccidentLevelLiabilityStatResponse> accidentLevelLiabilityStat(@RequestParam("vin")String vin, @RequestParam("year")String year, @RequestParam("vehicleLedgerId")String vehicleLedgerId);

     // 查询ETC消费明细信息
    @GetMapping("/api/report/car/infraction-items")
    JsonResult<List<InfractionItemsResponse>> infractionItems(@RequestParam("vin")String vin, @RequestParam("year")String year, @RequestParam("vehicleLedgerId")String vehicleLedgerId);

   //获取本年度保险信息
    @GetMapping("/api/report/car/insurance/current-year")
    JsonResult<List<InsuranceCurrentYearResponse>> insuranceCurrentYear(@RequestParam("vin")String vin, @RequestParam("year")String year, @RequestParam("vehicleLedgerId")String vehicleLedgerId);

    //车辆保险概况
    @GetMapping("/api/report/car/vehicle-insurance/core")
    JsonResult<VehicleInsuranceCoreResponse> vehicleInsuranceCore(@RequestParam("vin")String vin, @RequestParam("year")String year, @RequestParam("vehicleLedgerId")String vehicleLedgerId);

    //统计车辆本年出险次数和理赔费用
    @GetMapping("/api/report/car/yearly-claim-stats")
    JsonResult<YearlyClaimStatsResponse> yearlyClaimStats(@RequestParam("vin")String vin, @RequestParam("year")String year, @RequestParam("vehicleLedgerId")String vehicleLedgerId);

    //查询事故信息
    @GetMapping("/api/report/car/accident-information")
    JsonResult<List<AccidentInformationResponse>> accidentInformation(@RequestParam("vin")String vin, @RequestParam("year")String year, @RequestParam("vehicleLedgerId")String vehicleLedgerId);

    //查询充电信息
    @GetMapping("/api/report/car/charge-information")
    JsonResult<List<ChargeInformationResponse>> chargeInformation(@RequestParam("vin")String vin, @RequestParam("year")String year, @RequestParam("vehicleLedgerId")String vehicleLedgerId);

    //统计本年度事故出险情况
    @GetMapping("/api/report/car/claim-stats")
    JsonResult<ClaimStatsResponse> claimStats(@RequestParam("vin")String vin, @RequestParam("year")String year,@RequestParam("vehicleLedgerId")String vehicleLedgerId);

    //统计违章次数（累计和本年度）
    @GetMapping("/api/report/car/count-stats")
    JsonResult<CountStatsResponse> countStats(@RequestParam("vin")String vin, @RequestParam("year")String year,@RequestParam("vehicleLedgerId")String vehicleLedgerId);


    //查询本年度车辆违章记录
    @GetMapping("/api/report/car/violation/records")
    JsonResult<List<ViolationRecordsResponse>> violationRecords(@RequestParam("vin")String vin, @RequestParam("year")String year, @RequestParam("vehicleLedgerId")String vehicleLedgerId);



    //查询车牌变更记录
    @GetMapping("/api/report/car/plate-change-history")
    JsonResult<List<PlateChangeHistoryResponse>> plateChangeHistory(@RequestParam("vin")String vin, @RequestParam("year")String year, @RequestParam("vehicleLedgerId")String vehicleLedgerId);



    //查询车辆处置信息
    @GetMapping("/api/report/car/disposal-information")
    JsonResult<List<DisposalInformationResponse>> disposalInformation(@RequestParam("vin")String vin, @RequestParam("year")String year, @RequestParam("vehicleLedgerId")String vehicleLedgerId);



    //查询加油信息
    @GetMapping("/api/report/car/fuelling-information")
    JsonResult<List<FuellingInformationResponse>> fuellingInformation(@RequestParam("vin")String vin, @RequestParam("year")String year, @RequestParam("vehicleLedgerId")String vehicleLedgerId);

    //车辆年检信息
    @GetMapping("/api/report/car/getExaminedInfo")
    JsonResult<GetExaminedInfoResponse> getExaminedInfo(@RequestParam("vin")String vin, @RequestParam("year")String year, @RequestParam("vehicleLedgerId")String vehicleLedgerId);

    //车辆年检记录
    @GetMapping("/api/report/car/getExaminedRecord")
    JsonResult<List<GetExaminedRecordResponse>> getExaminedRecord(@RequestParam("vin")String vin, @RequestParam("year")String year, @RequestParam("vehicleLedgerId")String vehicleLedgerId);

    //终端信息
    @GetMapping("/api/report/car/getTerminalDetail")
    JsonResult<GetTerminalDetailResponse> getTerminalDetail(@RequestParam("vin")String vin, @RequestParam("year")String year, @RequestParam("vehicleLedgerId")String vehicleLedgerId);


    //终端行驶里程
    @GetMapping("/api/report/car/getTerminalMileDetail")
    JsonResult<GetTerminalMileDetailResponse> getTerminalMileDetail(@RequestParam("vin")String vin, @RequestParam("year")String year, @RequestParam("vehicleLedgerId")String vehicleLedgerId);


    //终端行驶里程-按月分组
    @GetMapping("/api/report/car/getTerminalMileMonthDetail")
    JsonResult<List<Group>> getTerminalMileMonthDetail(@RequestParam("vin")String vin, @RequestParam("year")String year, @RequestParam("vehicleLedgerId")String vehicleLedgerId);


    //累计运维成本
    @GetMapping("/api/report/car/getCumulativeOperation")
    JsonResult<GetCumulativeOperationResponse> getCumulativeOperation(@RequestParam("vin")String vin);

    @GetMapping("/api/report/car/getCumulativeOperationBzt")
    JsonResult<List<Item>> getCumulativeOperationBzt(@RequestParam("vin")String vin);

    @GetMapping("/api/report/car/getCumulativeOperationAnalysis")
    JsonResult<GetCumulativeOperationAnalysisResponse> getCumulativeOperationAnalysis(@RequestParam("vin")String vin, @RequestParam("year")String year, @RequestParam("vehicleLedgerId")String vehicleLedgerId);

    @GetMapping("/api/report/car/getCumulativeOperationJztAnalysis")
    JsonResult<List<Group>> getCumulativeOperationJztAnalysis(@RequestParam("vin")String vin, @RequestParam("year")String year, @RequestParam("vehicleLedgerId")String vehicleLedgerId);



    //资产价值
    @GetMapping("/api/report/car/vehicleAssetValue")
    JsonResult<VehicleAssetValueResponse> vehicleAssetValue(@RequestParam("vin")String vin, @RequestParam("year")String year, @RequestParam("vehicleLedgerId")String vehicleLedgerId);


    //车辆维保概况-本年度
    @GetMapping("/api/report/car/repair-stats/current-year")
    JsonResult<RepairStatsCurrentYearResponse> repairStatsCurrentYear(@RequestParam("vin")String vin, @RequestParam("year")String year, @RequestParam("vehicleLedgerId")String vehicleLedgerId);



    //车辆维保概况-维保费用
    @GetMapping("/api/report/car/repair-stats/feeTotal")
    JsonResult<RepairStatsFeeTotalResponse> repairStatsFeeTotal(@RequestParam("vin")String vin, @RequestParam("year")String year, @RequestParam("vehicleLedgerId")String vehicleLedgerId);




    //车辆维保概况-累计(不要传year)
    @GetMapping("/api/report/car/repair-stats/total")
    JsonResult<RepairStatsTotalResponse> repairStatsTotal(@RequestParam("vin")String vin, @RequestParam("year")String year, @RequestParam("vehicleLedgerId")String vehicleLedgerId);


    //车辆维保概况-配件费用分析
    @GetMapping("/api/report/car/repair-stats/repairAccessories")
    JsonResult<RepairStatsRepairAccessoriesResponse> repairStatsRepairAccessories(@RequestParam("vin")String vin, @RequestParam("year")String year, @RequestParam("vehicleLedgerId")String vehicleLedgerId);


    //车辆维保概况-柱状图
    @GetMapping("/api/report/car/repair-stats/totalBarchart")
    JsonResult<List<Group>> repairStatsTotalBarchart(@RequestParam("vin")String vin, @RequestParam("year")String year, @RequestParam("vehicleLedgerId")String vehicleLedgerId);


    //车辆维保概况-饼状图
    @GetMapping("/api/report/car/repair-stats/getCurrentYearPartFeeTop10")
    JsonResult<List<Item>> repairStatsGetCurrentYearPartFeeTop10(@RequestParam("vin")String vin, @RequestParam("year")String year, @RequestParam("vehicleLedgerId")String vehicleLedgerId);


    //月度成本明细
    @GetMapping("/api/report/car/getCumulativeOperationMonth")
    JsonResult<List<GetCumulativeOperationMonthResponse>> getCumulativeOperationMonth(@RequestParam("vin")String vin, @RequestParam("year")String year, @RequestParam("vehicleLedgerId")String vehicleLedgerId);


    //码表里程追踪
    @GetMapping("/api/report/car/getMileTrace")
    JsonResult<List<Group>> getMileTrace(@RequestParam("vin")String vin, @RequestParam("year")String year, @RequestParam("vehicleLedgerId")String vehicleLedgerId);



    //查询车辆保养记录-最终
    @GetMapping("/api/report/car/maintenance-maintenance-records")
    JsonResult<List<MaintenanceMaintenanceRecordsResponse>> maintenanceMaintenanceRecords(@RequestParam("vin")String vin, @RequestParam("year")String year, @RequestParam("vehicleLedgerId")String vehicleLedgerId);


    //查询车辆维保记录-最终
    @GetMapping("/api/report/car/maintenance-records")
    JsonResult<List<MaintenanceRecordsResponse>> maintenanceRecords(@RequestParam("vin")String vin, @RequestParam("year")String year, @RequestParam("vehicleLedgerId")String vehicleLedgerId);

}
