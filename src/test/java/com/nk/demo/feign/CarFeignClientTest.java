package com.nk.demo.feign;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.nk.demo.feign.response.dataRepo.*;
import com.nk.demo.ml.staff.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 创建人：  @author niuk
 * 创建时间: 2025-11-20 15:38
 * 项目名称: KunPro
 * 文件名称: CarFeignClientTest
 * 公司名称: 安徽产业互联数据智能创新中心有限公司
 */
@Slf4j
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class CarFeignClientTest {
    @Autowired
    private CarFeignClient carFeignClient;

    @Autowired
    private ThreadPoolTaskExecutor fixThreadPoolExecutor;

    private String areaId = "0100102100";

    private String basePath = "E:\\data\\Intellij\\Download\\2025\\11\\25\\cars\\";

//commonCarFileTask.execute('0100117100','CLW-SUPERVISE-TOG','CLDA','2010-01-01',
// '','14232489-4e5a-4ed9-b614-dae03d29ad27-0100117100','2136','1','true','false','false','ALL')
    /**
     * 查询监督管理平台下所有单位一共多少车
     * 把车辆id用逗号连接起来
     */
    @Test
    public void getAllOrgans() {
        List<OrganInfoResponse> all = carFeignClient.getOrgansName(areaId, "1", "GOVERMENT").getData();

        int number = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (OrganInfoResponse organsItem : all) {
            String organId = organsItem.getOrgan_id();
            List<GetAllCarsByOrganItem> data = carFeignClient.getAllCarsByOrgan(organId, "2010-01-01", "2036-03-03", areaId).getData();
            //把data中的每个元素中的car_id取出来，放到一个list中，最后把这个list转换成字符串，格式是：'car_id1','car_id2','car_id3'，然后打印这个字符串
            List<String> carIds = data.stream().map(GetAllCarsByOrganItem::getCar_id).toList();
            if(carIds.size()> 0){
                stringBuilder.append(String.join(",", carIds));
                stringBuilder.append(",");
            }

            number += data.size();
            if (stringBuilder.length() > 0) {
                stringBuilder.append(",");
            }
//            stringBuilder.append("'").append(organId).append("'");
            System.out.println("单位id：" + organId + "当前单位：" + organsItem.getOrgan_name() + "，车辆数量：" + data.size());
            System.out.println(organsItem.getOrgan_name());
        }
        System.out.println("carIds: " + stringBuilder.toString());
//        System.out.println("delete from tb_car_statistics_real_time WHERE own_organ_id IN (" + stringBuilder.toString() + ");");
        System.out.println("车辆总数：" + number);
    }


    @Test
    public void getCarNoByCarids(){

        //1. 解析carids.json文件，获取里面所有的car_id
        String jsonPath = "src/test/java/com/nk/demo/feign/carids.json";
        String jsonContent = FileUtil.readUtf8String(new File(jsonPath));
        List<JSONObject> carIdList = JSONObject.parseArray(jsonContent, JSONObject.class);
        if (carIdList == null || carIdList.isEmpty()) {
            log.warn("carids.json文件中没有数据");
            return;
        }

        log.info("共解析到{}辆车的ID", carIdList.size());
        //2. 遍历刚刚获得的carids，用carFeignClient.getCarsDetail方法，把所有车辆详情获取到，打印出车辆详情，其中areaid是0100102102105
        String targetAreaId = "0100102102105";
        int successCount = 0;
        int failCount = 0;

        for (int i = 0; i < carIdList.size(); i++) {
            JSONObject carInfo = carIdList.get(i);
            String carId = carInfo.getString("car_id");
            String source = carInfo.getString("source");

            if (StrUtil.isBlank(carId)) {
                log.warn("第{}条数据的car_id为空，跳过", i + 1);
                continue;
            }

            try {
                JsonResult<GetCarsDetailResponse> result = carFeignClient.getCarsDetail(carId, targetAreaId, source);

                if (result != null && result.getData() != null) {
                    GetCarsDetailResponse carDetail = result.getData();
                    System.out.println("车辆[" + (i + 1) + "/" + carIdList.size() + "] - ID: " + carId + " - 车牌号: " + carDetail.getCar_no());

                    successCount++;
                } else {
                    log.warn("车辆[{}]查询返回空数据", carId);
                    failCount++;
                }
            } catch (Exception e) {
                log.error("查询车辆详情失败，carId: {}", carId, e);
                failCount++;
            }
        }

        log.info("查询完成，成功: {}, 失败: {}", successCount, failCount);


    }

    /**
     * 获取所有车辆信息
     */
    @Test
    public void testGetAllOrganCars() {
        List<OrganInfoResponse> organsItemList = carFeignClient.getOrgansName(areaId, "1", "ALL").getData();

        AtomicInteger carNum = new AtomicInteger();
        organsItemList.forEach(organsItem -> {
            List<GetAllCarsByOrganItem> allCars = carFeignClient.getAllCarsByOrgan(organsItem.getOrgan_id(), "2010-01-01", "2026-11-20", areaId).getData();

            if (null != allCars && allCars.size() > 0) {
                allCars.parallelStream().forEach(car -> {
                    System.out.println((carNum.incrementAndGet()) + "当前单位进展：" + organsItemList.indexOf(organsItem) + "/" + organsItemList.size() + ",当前车辆：" + allCars.indexOf(car) + "/" + allCars.size());

                    JSONObject result = new JSONObject();
                    String carId = car.getCar_id();
                    String source = car.getSource();
                    String et = DateUtil.format(DateUtil.date(), "yyyy-MM-dd");
                    String endTime = StrUtil.isBlank(car.getDisposal_time()) ? et : car.getDisposal_time();
                    //车辆详情
                    long start = System.currentTimeMillis();
                    GetCarsDetailResponse carDetail = carFeignClient.getCarsDetail(carId, areaId, source).getData();
                    System.out.println("车辆详情时间 = " + (System.currentTimeMillis() - start) + "ms");
                    result.put("carDetail", carDetail);

                    String carPath = basePath + areaId + File.separator + organsItem.getOrgan_name() + File.separator + carDetail.getCar_no() + File.separator;
                    File file = new File(carPath + carDetail.getCar_no() + ".json");
                    if (!file.exists()) {
                        //车辆配置信息
                        if (null != carDetail.getDate_created()) {
                            JsonResult<List<GetCarConfigResponse>> configDetail = carFeignClient.getConfigDetail(carId, carDetail.getDate_created(), endTime);
                            if (null != configDetail && null != configDetail.getData() && configDetail.getData().size() > 0) {
                                List<GetCarConfigResponse> ConfigDetail = configDetail.getData();
                                result.put("configDetail", ConfigDetail);
                            }
                        }

                        //获取车辆变更信息
                        Object changeDetail = carFeignClient.getChangeDetail(carId, carDetail.getDate_created(), endTime).getData();
                        result.put("changeDetail", changeDetail);

                        //车辆保险信息
                        GetInsuranceDataResponse insuranceData = carFeignClient.getInsuranceData(carId, carDetail.getDate_created(), endTime).getData();
                        result.put("insuranceData", insuranceData);

                        //车辆维保
                        List<GetRepairIdItem> repairInfo = carFeignClient.getRepairId(carId, carDetail.getDate_created(), endTime).getData();
                        result.put("repairInfo", repairInfo);

                        //车辆年检
                        List<GetMotIdItem> motInfo = carFeignClient.getMotId(carId, carDetail.getDate_created(), endTime).getData();
                        result.put("motInfo", motInfo);

                        //车辆处置
                        List<GetCarDisposalResponse> carDisposal = carFeignClient.getDisposalDetail(carId, areaId).getData();
                        result.put("carDisposal", carDisposal);

                        //违章季度明细
                        GetViolationDetailResponse violationDetail = carFeignClient.getViolationDetail(carId, carDetail.getDate_created(), endTime, "", areaId).getData();
                        result.put("violationDetail", violationDetail);

                        //车辆事故季度明细
                        GetAccidentDetailResponse accidentDetail = carFeignClient.getAccidentDetail(carId, carDetail.getDate_created(), endTime, "", areaId).getData();
                        result.put("accidentDetail", accidentDetail);

                        FileUtil.writeString(result.toJSONString(), carPath + carDetail.getCar_no() + ".json", "utf-8");

                        downloadImg(carDetail.getCar_invoice_img(), carPath + "car_invoice_img");
                        downloadImg(carDetail.getDriving_license_img(), carPath + "driving_license_img");
                        downloadImg(carDetail.getImg(), carPath + "img");
                        downloadImg(carDetail.getRegistration_certificate_img_url(), carPath + "registration_certificate_img_url");
                    }


                });
            }


        });

    }

    /**
     * 获取指定区域下所有车辆数
     */
    @Test
    public void getAllCarNumByAreaId() {
        List<OrganInfoResponse> organsItemList = carFeignClient.getOrgansName(areaId, "1", "ALL").getData();

        System.out.println("一共有" + organsItemList.size() + "个单位");
        int num = 0;
        for (OrganInfoResponse organsItem : organsItemList) {
            List<GetAllCarsByOrganItem> allCars = carFeignClient.getAllCarsByOrgan(organsItem.getOrgan_id(), "2010-01-01", "2036-12-20", areaId).getData();
            num += allCars.size();
            System.out.println((organsItemList.indexOf(organsItem) + 1) + "：" + organsItem.getOrgan_name() + "车辆：" + allCars.size() + ",累计辆车：" + num);
        }
        System.out.println("一共有" + num + "辆车");
    }

    /**
     * 获取指定单位下所有车辆数
     */
    @Test
    public void getAllCarNumByOrgId() {
        List<GetAllCarsByOrganItem> allCars = carFeignClient.getAllCarsByOrgan(
                "anh-qy-organ-197",
                "2010-01-01",
                "2026-12-20",
                "0100102114").getData();
        System.out.println("一共有" + allCars.size() + "辆车");
    }


    /**
     * 获取指定区域下处置的车辆
     */
    @Test
    public void getDisposalCarId() {
        List<GetCarDisposalResponse> organsItemList = carFeignClient.getDisposalCarId("2026-01-01", "2038-12-21", "0100120107100").getData();
        System.out.println("一共有" + organsItemList.size() + "辆车");
        for (GetCarDisposalResponse item : organsItemList) {
            System.out.println(item);
        }


    }


    private void downloadImg(List<String> list, String path) {
        if (null == list || list.size() == 0) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            String url = list.get(i);
            try {
                String dest = path + File.separator + url.substring(url.lastIndexOf("/") + 1);
                File file = new File(dest);
                if (file.exists()) {
                    continue;
                }
                HttpUtil.downloadFile(url, file, 10000);
                log.info("下载图片成功:" + url);
            } catch (Exception e) {
                System.out.println("下载图片异常:" + url);
            }
        }
    }

    @Test
    public void getAnhuiOrgs(){
        List<OrganInfoResponse> all = carFeignClient.getOrgansName("0100102", "0", "ALL").getData();
        System.out.println("一共有" + all.size() + "个单位");
        for (OrganInfoResponse organInfoResponse : all) {
            if(organInfoResponse.getOrgan_id().equals("c7d82217d6c64b11bbfbb1517e8f6513")){
                System.out.println(organInfoResponse.getOrgan_name() + " " + organInfoResponse.getOrgan_id());
            }
        }
    }


    @Test
    public void getAllCachedCarNum() {
        Path carsPath = Paths.get(basePath);

        File[] areaFiles = carsPath.toFile().listFiles();
        for (File areaFile : areaFiles) {
            int num = 0;
            File[] organFiles = areaFile.listFiles();
            for (File organFile : organFiles) {
                File[] carFiles = organFile.listFiles();
                num += carFiles.length;
            }
            System.out.println(areaFile.getName() + ": " + num);
        }


    }


}