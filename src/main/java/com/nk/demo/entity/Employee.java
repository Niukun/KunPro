package com.nk.demo.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * 创建人：  @author niuk
 * 创建时间: 2026-05-19 10:00
 * 项目名称: KunPro
 * 文件名称: Employee
 * 公司名称: 安徽产业互联数据智能创新中心有限公司
 */
@Data
@AllArgsConstructor
public class Employee {
    // 成员变量
    @ExcelProperty("员工工号")
    private int id;
    @ExcelProperty("员工姓名")
    private String name;
    @ExcelProperty("员工工资")
    private double salary;
    @ExcelProperty("入职日期")
    private Date date;
}
