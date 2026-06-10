package com.nk.demo.entity;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import junit.framework.TestCase;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

/**
 * 创建人：  @author niuk
 * 创建时间: 2026-05-19 10:01
 * 项目名称: KunPro
 * 文件名称: EmployeeTest
 * 公司名称: 安徽产业互联数据智能创新中心有限公司
 */

public class EmployeeTest extends TestCase {


    @Test
    public void testEmployee(){
        long start = System.currentTimeMillis();
        ArrayList<Employee> employees = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            employees.add(new Employee(i,"niuk"+i,1000+i *100d,new Date()));
        }
        ArrayList<Address> addresses = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            addresses.add(new Address("id"+i,"city"+i,"company_name"+i,"location"+i,"company_url"+i,"industry"+i,"company_property"+i,"source_name"+i,"company_size"+i));
        }

        File file = new File("result.xlsx");

        //我要创建两个sheet页，一个是员工信息，一个是地址信息，分别为employees和addresses两个list的数据


        try (ExcelWriter excelWriter = EasyExcel.write(file)
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                .build()) {
            WriteSheet writeSheet1 = EasyExcel.writerSheet("员工信息").head(Employee.class).build();
            WriteSheet writeSheet2 = EasyExcel.writerSheet("地址信息").head(Address.class).build();
            excelWriter.write(employees, writeSheet1);
            excelWriter.write(addresses, writeSheet2);
        }


        System.out.println("耗时：" + (System.currentTimeMillis() - start));
        System.out.println("文件大小：" + file.length());
        System.out.println("文件路径：" + file.getAbsolutePath());


    }


}