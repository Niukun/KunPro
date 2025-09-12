package com.nk.demo;

import com.alibaba.fastjson.JSONObject;
import com.nk.demo.feign.CarFeignClient;
import com.nk.demo.feign.ConvertClient;
import com.nk.demo.feign.ZkmlStaffFeignClient;
import com.nk.demo.feign.response.dataRepo.GetAllCarsByOrganItem;
import com.nk.demo.feign.response.dataRepo.GetCarsDetailResponse;
import com.nk.demo.ml.staff.GetStaffBaseInfoResponse;
import com.nk.demo.ml.staff.JsonResult;
import com.nk.demo.ml.staff.StaffResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class FeignClientTest {



    @Autowired
    private ConvertClient convertClient;



    @Autowired
    private CarFeignClient carFeignClient;


    @Autowired
    private ZkmlStaffFeignClient zkmlStaffFeignClient;
    @Test
    public void testAdd() {
        Integer result = convertClient.add(1, 2);
        System.out.println(result);
    }


    @Test
    public void testGetStaffList() throws Exception {
        JsonResult<List<StaffResponse>> result = zkmlStaffFeignClient.getStaffList("ca7cc81dfab8471180286e7bf823c53a", "2014-01-01", "2026-09-01");
        List<StaffResponse> data = result.getData();
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("E:\\data\\Intellij\\Download\\2025\\09\\09\\staff-mljt.txt"));

        data.parallelStream().forEach(staff -> {

            JsonResult<GetStaffBaseInfoResponse> staffBaseInfo = zkmlStaffFeignClient.getStaffBaseInfo(staff.getStaffId(), null, null, null, null);
            System.out.println(staffBaseInfo.getData());
            try {
                bufferedWriter.write(staffBaseInfo.getData().getData().getStaffName() + ": " + JSONObject.toJSONString(staffBaseInfo.getData()));
                bufferedWriter.newLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

        bufferedWriter.close();

        System.out.println(result);
    }


    @Test
    public void testCarNum(){

        JsonResult<List<GetAllCarsByOrganItem>> allCarsByOrgan = carFeignClient.getAllCarsByOrgan("anh-qy-organ-212", "2010-01-01", "2025-09-09", "0100102111");

        allCarsByOrgan.getData().forEach(car -> {

            String carId = car.getCar_id();
            JsonResult<GetCarsDetailResponse> carsDetail = carFeignClient.getCarsDetail(carId, "0100102111");

//            System.out.println("carId is: " + carId + ", buy_tax_img is: " + carsDetail.getData().getBuy_tax_img());
            System.out.println("car_no is: " + carsDetail.getData().getCar_no() + ", buy_tax_img is: " + carsDetail.getData().getBuy_tax_img());

        });




    }




}
