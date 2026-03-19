package com.nk.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.nk.demo.entity.CarStatistics;
import com.nk.demo.service.CarStatisticsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 创建人：  @author niuk
 * 创建时间: 2026-03-18 16:56
 * 项目名称: trae
 * 文件名称: UserController
 * 公司名称: 安徽产业互联数据智能创新中心有限公司
 */
@RestController
@Slf4j
@RequestMapping("/api")
public class CarStatisticsController {

    @Value("${name}")
    private String name;

    @Autowired
    private CarStatisticsService carStatisticsService;

    @GetMapping("/carStatistics")
    public List<CarStatistics> carStatistics() {
        long start = System.currentTimeMillis();
        LambdaQueryWrapper<CarStatistics> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(CarStatistics::getOwnOrganId, "0001360707124d61990da22ef62497ff");

        System.out.println("查询耗时：" + (System.currentTimeMillis() - start));
        return carStatisticsService.list(lambdaQueryWrapper);
    }
    @GetMapping("/test")
    public String test() {
        return name;
    }
}
