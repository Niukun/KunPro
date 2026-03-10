package com.nk.demo.feign;

import com.nk.demo.feign.response.dataRepo.*;
import com.nk.demo.feign.response.dataRepo.GetAllOrgansItem;
import com.nk.demo.feign.response.dataRepo.GetCarsDetailResponse;
import com.nk.demo.ml.staff.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Description: zkml员工归档接口
 * @Author: zc
 * @Date: 2024/9/19 17:34
 **/
@FeignClient(name = "carFeignClient",url = "${zkml.car.url}")
public interface CarFeignClient {

    //查询单位列表
    @GetMapping("/cars/getAllOrgans")
    JsonResult<List<GetAllOrgansItem>> getAllOrgans(String id, @RequestParam("areaId") String areaId);

    //根据区域获取组织列表
    @GetMapping("/cars/getOrgansName")
    JsonResult<List<OrganInfoResponse>> getOrgansName(@RequestParam("areaId")String areaId,@RequestParam("isDirectly") String isDirectly, @RequestParam("organType") String organType);

    //按时间查询车辆列表
    @GetMapping("/cars/getAllCarsByOrgan")
    JsonResult<List<GetAllCarsByOrganItem>> getAllCarsByOrgan(@RequestParam("organId")String organId, @RequestParam("startTime")String startTime, @RequestParam("endTime")String endTime, @RequestParam("areaId") String areaId);

    //查询车辆信息
    @GetMapping("/cars/getCarsDetail")
    JsonResult<GetCarsDetailResponse> getCarsDetail(@RequestParam("carId")String carId,@RequestParam("areaId") String areaId, @RequestParam("source") String source);

    //查询保险信息
    @GetMapping("/cars/getInsuranceData")
    JsonResult<GetInsuranceDataResponse> getInsuranceData(@RequestParam("carId")String carId, @RequestParam("startTime")String startTime, @RequestParam("endTime")String endTime);

    //查询维保记录ID
    @GetMapping("/cars/getRepairId")
    JsonResult<List<GetRepairIdItem>> getRepairId(@RequestParam("carId")String carId, @RequestParam("startTime")String startTime, @RequestParam("endTime")String endTime);

    //查询维保记录
    @GetMapping("/cars/getRepairDetail")
    JsonResult<List<GetRepairDetailItem>> getRepairDetail(@RequestParam("applyId")String applyId);

    //查询年检记录ID
    @GetMapping("/cars/getMotId")
    JsonResult<List<GetMotIdItem>> getMotId(@RequestParam("carId")String carId, @RequestParam("startTime")String startTime, @RequestParam("endTime")String endTime);

    //查询年检记录
    @GetMapping("/cars/getMotDetail")
    JsonResult<List<GetMotDetailItem>> getMotDetail(@RequestParam("motId")String motId);

    //ETC统计
    @GetMapping("/cars/getEtcStatistics")
    JsonResult<GetEtcStatisticsResponse> getEtcStatistics(@RequestParam("carId")String carId,@RequestParam("fileName") String fileName, @RequestParam("time")String time,@RequestParam("timeType") String timeType,@RequestParam("areaId") String areaId);

    //ETC明细
    @GetMapping("/cars/getEtcDetail")
    JsonResult<List<GetEtcDetailItem>> getEtcDetail(@RequestParam("carId")String carId, @RequestParam("startTime")String startTime, @RequestParam("endTime")String endTime,@RequestParam("areaId") String areaId);

    //加油统计
    @GetMapping("/cars/getOilStatistics")
    JsonResult<GetOilStatisticsResponse> getOilStatistics(@RequestParam("carId")String carId,@RequestParam("fileName") String fileName, @RequestParam("time")String time,@RequestParam("timeType") String timeType);

    //加油明细
    @GetMapping("/cars/getOilDetail")
    JsonResult<List<GetOilDetailItem>> getOilDetail(@RequestParam("carId")String carId, @RequestParam("startTime")String startTime, @RequestParam("endTime")String endTime);

    //里程统计
    @GetMapping("/cars/getMiles")
    JsonResult<GetMilesStatisticsResponse> getMiles(@RequestParam("carId")String carId,@RequestParam("fileName") String fileName, @RequestParam("time")String time,@RequestParam("timeType") String timeType,@RequestParam("areaId") String areaId);

    //违章明细
    @GetMapping("/cars/getViolationDetail")
    JsonResult<GetViolationDetailResponse> getViolationDetail(@RequestParam("carId")String carId, @RequestParam("startTime")String startTime, @RequestParam("endTime")String endTime,@RequestParam("fileName") String fileName,@RequestParam("areaId") String areaId);

    //事故明细
    @GetMapping("/cars/getAccidentDetail")
    JsonResult<GetAccidentDetailResponse> getAccidentDetail(@RequestParam("carId")String carId, @RequestParam("startTime")String startTime, @RequestParam("endTime")String endTime,@RequestParam("fileName") String fileName,@RequestParam("areaId") String areaId);

    //车辆运营费用
    @GetMapping("/cars/getRunFee")
    JsonResult<GetCarOperationFeeResponse> getCarOperationFee(@RequestParam("carId")String carId, @RequestParam("time")String time,@RequestParam("timeType") String timeType,@RequestParam("fileName") String fileName,@RequestParam("areaId") String areaId);

    //车辆大事件
    @GetMapping("/cars/getMemorabiliaDetail")
    JsonResult<List<GetCarBigEventResponse>> getCarBigEvent(@RequestParam("carId")String carId,@RequestParam("areaId") String areaId);

    //变更记录
    @GetMapping("/cars/getChangeDetail")
    JsonResult getChangeDetail(@RequestParam("carId")String carId, @RequestParam("startTime")String startTime, @RequestParam("endTime")String endTime);

    //充电明细
    @GetMapping("/cars/getChargeDetail")
    JsonResult<Object> getChargeDetail(@RequestParam("carId")String carId, @RequestParam("startTime")String startTime, @RequestParam("endTime")String endTime,@RequestParam("areaId") String areaId);

    //充电统计
    @GetMapping("/cars/getChargeStatistics")
    JsonResult<GetChargeStatisticsResponse> getChargeStatistics(@RequestParam("carId")String carId,@RequestParam("fileName") String fileName, @RequestParam("time")String time,@RequestParam("timeType") String timeType,@RequestParam("areaId") String areaId);

    //车辆配备
    @GetMapping("/cars/getConfigDetail")
    JsonResult<List<GetCarConfigResponse>> getConfigDetail(@RequestParam("carId")String carId, @RequestParam("startTime")String startTime, @RequestParam("endTime")String endTime);

    //查询处置信息
    @GetMapping("/cars/getDisposalDetail")
    JsonResult<List<GetCarDisposalResponse>> getDisposalDetail(@RequestParam("carId")String carId,@RequestParam("areaId") String areaId);

    //获取一段时间内的车辆处置记录
    @GetMapping("/cars/getDisposalCarId")
    JsonResult<List<GetCarDisposalResponse>> getDisposalCarId(@RequestParam("startTime")String startTime, @RequestParam("endTime")String endTime,@RequestParam("areaId") String areaId);


}
