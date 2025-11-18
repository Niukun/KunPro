package com.nk.demo.feign;

import com.alibaba.fastjson.JSONObject;
import com.nk.demo.ml.staff.GetStaffBaseInfoResponse;
import com.nk.demo.ml.staff.JsonResult;
import com.nk.demo.ml.staff.OrgResponse;
import com.nk.demo.ml.staff.StaffResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Description: zkml员工归档接口
 * @Author: zc
 * @Date: 2024/9/19 17:34
 **/
@FeignClient(name = "zkmlStaffFeignClient",url = "${zkml.staff.url}")
public interface ZkmlStaffFeignClient {

    //  查询员工列表
    @GetMapping(value = "/staffFiles/getStaffList")
    JsonResult<List<StaffResponse>> getStaffList(@RequestParam("orgId") String orgId, @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate);

    //  查询单位列表
    @GetMapping(value = "/staffFiles/getOrgList")
    JsonResult<List<OrgResponse>> getOrgList();

    //  查询员工基础信息
    @GetMapping(value = "/staffFiles/getStaffBaseInfo")
    JsonResult<GetStaffBaseInfoResponse> getStaffBaseInfo(@RequestParam("staffId") String staffId,
                                                          @RequestParam("nd") String nd,
                                                          @RequestParam("yfq") String yfq,
                                                          @RequestParam("yfz") String yfz,
                                                          @RequestParam("flag") String flag);

    //  查询员工职务信息
    @GetMapping(value = "/staffFiles/getStaffWorkInfo")
    JsonResult<List<StaffResponse>> getStaffWorkInfo(@RequestParam("staffId") String staffId,
                                                     @RequestParam("nd") String nd,
                                                     @RequestParam("yfq") String yfq,
                                                     @RequestParam("yfz") String yfz,
                                                     @RequestParam("flag") String flag);

    //  查询员工公司内亲属关系
    @GetMapping(value = "/staffFiles/getStaffWorkEmployRelate")
    JsonResult<List<StaffResponse>> getStaffWorkEmployRelate(@RequestParam("staffId") String staffId,
                                                     @RequestParam("nd") String nd,
                                                     @RequestParam("yfq") String yfq,
                                                     @RequestParam("yfz") String yfz,
                                                     @RequestParam("flag") String flag);

    //  查询员工成长信息汇总
    @GetMapping(value = "/staffFiles/getStaffWorkGrouth")
    JsonResult<List<StaffResponse>> getStaffWorkGrouth(@RequestParam("staffId") String staffId,
                                                             @RequestParam("nd") String nd,
                                                             @RequestParam("yfq") String yfq,
                                                             @RequestParam("yfz") String yfz,
                                                             @RequestParam("flag") String flag);

    //  查询年度员工考核信息
    @GetMapping(value = "/staffFiles/getStaffWorkKpi")
    JsonResult<List<StaffResponse>> getStaffWorkKpi(@RequestParam("staffId") String staffId,
                                                       @RequestParam("nd") String nd,
                                                       @RequestParam("yfq") String yfq,
                                                       @RequestParam("yfz") String yfz,
                                                       @RequestParam("flag") String flag);

    //  查询年度员工奖惩信息
    @GetMapping(value = "/staffFiles/getStaffWorkRewordPunish")
    JsonResult<List<StaffResponse>> getStaffWorkRewordPunish(@RequestParam("staffId") String staffId,
                                                    @RequestParam("nd") String nd,
                                                    @RequestParam("yfq") String yfq,
                                                    @RequestParam("yfz") String yfz,
                                                    @RequestParam("flag") String flag);

    //  查询员工劳动合同信息
    @GetMapping(value = "/staffFiles/getStaffWorkContract")
    JsonResult<List<StaffResponse>> getStaffWorkContract(@RequestParam("staffId") String staffId,
                                                             @RequestParam("nd") String nd,
                                                             @RequestParam("yfq") String yfq,
                                                             @RequestParam("yfz") String yfz,
                                                             @RequestParam("flag") String flag);

    //  查询附件列表信息
    @GetMapping(value = "/staffFiles/getStaffFiles")
    JsonResult<List<StaffResponse>> getStaffFiles(@RequestParam("staffId") String staffId,
                                                         @RequestParam("nd") String nd,
                                                         @RequestParam("yfq") String yfq,
                                                         @RequestParam("yfz") String yfz,
                                                         @RequestParam("flag") String flag);

}
