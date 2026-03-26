package com.nk.demo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * 创建人：  @author niuk
 * 创建时间: 2026-03-18 18:04
 * 项目名称: KunPro
 * 文件名称: SpringConfig
 * 公司名称: 安徽产业互联数据智能创新中心有限公司
 */
@Configuration
@Import({MybatisPlusConfig.class, ThreadPoolConfig.class})
@ComponentScan({"com.nk.demo"})
@PropertySource("classpath:test.properties")
@MapperScan({"com.nk.demo.mapper"})

public class SpringConfig {
}
