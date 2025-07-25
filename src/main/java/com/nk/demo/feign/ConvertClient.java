package com.nk.demo.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "convert-service", url = "http://localhost:8086")
@Component
public interface ConvertClient {

    @PostMapping("/demo0623/add")
    Integer add(@RequestParam int a, @RequestParam int b);



}
