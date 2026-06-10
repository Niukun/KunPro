package com.nk.demo.entity;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.nk.demo.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;


//@SpringBootTest
//@RunWith(SpringRunner.class)
public class UserTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testInsert() {
        User user = new User();
        user.setName("nk");
        user.setAge(18);
        user.setEmail("<EMAIL>");
        int result = userMapper.insert(user);
        System.out.println("result:" + result);
        System.out.println("id:" + user.getId());
    }

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }

    @Test
    public void testNow() {
        DateTime now = DateUtil.date();
        System.out.println(String.format("%02d",(DateUtil.month(now) +1)));

    }

    @Test
    public void testFiles() {
        Path path = Paths.get("E:\\data\\Intellij\\Download\\2026\\05\\29\\test");
        File[] files = path.toFile().listFiles();
        if(!Objects.isNull( files) && files.length > 0){
            String result = Arrays.stream(files)
                    .filter(File::isDirectory)
                    .map(f -> f.getName().substring(5)) // 提取月份
                    .sorted(Comparator.reverseOrder()) // 排序
                    .collect(Collectors.joining(",")); // 用逗号连接
            System.out.println(result);

        }

    }

    @Test
    public void testLocalDate() {
        LocalDate now = LocalDate.now();
        System.out.println(now);
        System.out.println(now.getYear());
        System.out.println(now.getMonthValue());

        System.out.println(now.getDayOfMonth());

        System.out.println("===============================");
        LocalDate localDate = now.plusMonths(-1);

        System.out.println(localDate);
        System.out.println(localDate.getYear());
        System.out.println(localDate.getMonthValue());
        System.out.println(localDate.getDayOfMonth());

        System.out.println("===============================");

        LocalDate localDate2 = LocalDate.of(2026, 5, 1);
        System.out.println(localDate2);
        System.out.println(localDate2.getYear());
        System.out.println(localDate2.getMonthValue());
        System.out.println(localDate2.getDayOfMonth());

        System.out.println("===============================");
        boolean before = now.isBefore(localDate2);
        System.out.println(before);

    }


    @Test
    public void testLocalDateTime(){
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        System.out.println(now.getYear());
        System.out.println(now.getMonthValue());
        System.out.println(now.getDayOfMonth());
        System.out.println(now.getHour());
        System.out.println(now.getMinute());
        System.out.println(now.getSecond());
        System.out.println("===============================");
        LocalDateTime localDateTime = LocalDateTime.of(2026, 5, 1, 0, 0, 0);
        System.out.println(localDateTime);
        System.out.println(localDateTime.getYear());
        System.out.println(localDateTime.getMonthValue());
        System.out.println(localDateTime.getDayOfMonth());
        System.out.println(localDateTime.getHour());
        System.out.println(localDateTime.getMinute());
        System.out.println(localDateTime.getSecond());

        System.out.println("===============================");
        LocalTime now1 = LocalTime.now();
        System.out.println(now1);
        System.out.println(now1.getHour());
        System.out.println(now1.getMinute());
        System.out.println(now1.getSecond());
        System.out.println(now1.getNano());
    }


    @Test
    public void testDateUtil(){
        int i = Calendar.getInstance().get(Calendar.MONTH);
        System.out.println(i);
        System.out.println(DateUtil.month(new Date()));

    }



}
