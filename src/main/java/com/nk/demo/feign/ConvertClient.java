package com.nk.demo.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "convert-service", url = "http://localhost:8084")
@Component
public interface ConvertClient {

    @GetMapping("/network/add")
    Integer add(@RequestParam int a, @RequestParam int b);



}
