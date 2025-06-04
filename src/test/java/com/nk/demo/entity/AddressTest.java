package com.nk.demo.entity;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nk.demo.mapper.AddressMapper;
import com.nk.demo.service.impl.AddressServiceImpl;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressTest {
    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private AddressServiceImpl addressService;


    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        QueryWrapper<Address> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNotNull("company_url")
                .isNotNull("company_url")
                .isNotNull("company_size")
                .isNotNull("source_name")
                .isNotNull("source_name")
                .isNotNull("_id")
                .isNotNull("company_name");

        List<Address> userList = addressMapper.selectList(queryWrapper);
//        userList.forEach(System.out::println);
        System.out.println(userList.size());
    }


    @Test
    public void testPageSelect() {
        System.out.println(("----- selectAll method test ------"));

        QueryWrapper<Address> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNotNull("company_url")
                .isNotNull("company_url")
                .isNotNull("company_size")
                .isNotNull("source_name")
                .isNotNull("source_name")
                .isNotNull("_id")
                .isNotNull("company_name");

        Page<Address> addressPage = addressMapper.selectPage(new Page<>(1, 20), queryWrapper);


        List<Address> records = addressPage.getRecords();
        System.out.println("=======================");
        records.forEach(System.out::println);
        System.out.println("=======================");
    }


    @Test
    public void cabiaTest() throws IOException {
        String string = FileUtils.readFileToString(new File("src/main/resources/files/cabia.txt"), StandardCharsets.UTF_8);
        String[] split = string.split("\\n");
        long start = System.currentTimeMillis();
        for (int i = 0; i < split.length; i++) {
            JSONObject jsonObject = JSONObject.parseObject(split[i]);
            String string1 = jsonObject.getJSONObject("payload").getString("object");
//            System.out.println(i +  ": ");
            Address entity = JSONObject.parseObject(string1, Address.class);
            entity.set_id(UUID.randomUUID().toString());
            addressMapper.insert(entity);
        }
        System.out.println("耗时：" + (System.currentTimeMillis() - start));
    }


    @Test
    public void cabiaTest2() throws IOException {
        List<Address> addresses = addressMapper.selectList(null);
        long start  = System.currentTimeMillis();
        for(Address address : addresses){
            address.set_id(UUID.randomUUID().toString());
            addressMapper.insert(address);
        }
        System.out.println("耗时：" + (System.currentTimeMillis() - start));

    }



    @Test
    public void cabia3Test() throws IOException {
        List<Address> addresses = addressMapper.selectList(null);

        String string = FileUtils.readFileToString(new File("src/main/resources/files/cabia.txt"), StandardCharsets.UTF_8);
        String[] split = string.split("\\n");
        long start = System.currentTimeMillis();
        for (int i = 0; i < split.length; i++) {
            JSONObject jsonObject = JSONObject.parseObject(split[i]);
            String string1 = jsonObject.getJSONObject("payload").getString("object");
            Address entity = JSONObject.parseObject(string1, Address.class);
            entity.set_id(UUID.randomUUID().toString());
            addresses.add(entity);
        }

        addressService.saveBatch(addresses);
        System.out.println("size is: " + addresses.size());
        System.out.println("耗时：" + (System.currentTimeMillis() - start));
    }

    @Test
    public void cabia4Test() {
        List<Address> addresses = addressMapper.selectList(null);
        long start  = System.currentTimeMillis();
        List<Address> list = new ArrayList<>();
        for(Address address : addresses){
            address.set_id(UUID.randomUUID().toString());
//            addressMapper.insert(address);
            list.add(address);

        }
        addressService.saveBatch(list);


        System.out.println("size is: " + list.size());
        System.out.println("耗时：" + (System.currentTimeMillis() - start));
    }

}
