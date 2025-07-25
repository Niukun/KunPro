package com.nk.demo;

import com.nk.demo.feign.ConvertClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class FeignClientTest {


    @Autowired
    private ConvertClient convertClient;

    @Test
    public void testAdd() {
        Integer result = convertClient.add(1, 2);
        System.out.println(result);
    }


}
