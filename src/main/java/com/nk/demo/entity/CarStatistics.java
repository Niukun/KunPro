package com.nk.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 创建人：  @author niuk
 * 创建时间: 2026-03-18 16:58
 * 项目名称: trae
 * 文件名称: CarStatistics
 * 公司名称: 安徽产业互联数据智能创新中心有限公司
 */
@Data
@TableName("car_statistics")
public class CarStatistics {
    private String ownOrganId;
    private String statisticsDay;
    private String source;
    private String ownOrganName;
    private String areaId;
}
