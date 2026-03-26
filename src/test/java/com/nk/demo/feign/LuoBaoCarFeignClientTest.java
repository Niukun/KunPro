package com.nk.demo.feign;

import com.nk.demo.feign.response.dataRepo.luobao.BasicInfoByOrgIdResponse;
import com.nk.demo.ml.staff.JsonResult;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * 创建人：  @author niuk
 * 创建时间: 2026-03-26 11:12
 * 项目名称: KunPro
 * 文件名称: LuoBaoCarFeignClientTest
 * 公司名称: 安徽产业互联数据智能创新中心有限公司
 */
@Slf4j
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class LuoBaoCarFeignClientTest extends TestCase {

    @Autowired
    private LuoBaoCarFeignClient luoBaoCarFeignClient;
    @Test
    public void testGetCarInfo() {
        log.info("测试开始");
        JsonResult<List<BasicInfoByOrgIdResponse>> result = luoBaoCarFeignClient.basicInfoByOrgId("a76167dd901d452f90d90126b956ff54");
        List<BasicInfoByOrgIdResponse> data = result.getData();
        for (int i = 0; i < data.size(); i++) {
            System.out.println((i+1)+": " + data.get(i).getLicensePlateNo());
        }
        log.info("车辆数：" + data.size());
        log.info("测试结束");
    }


}