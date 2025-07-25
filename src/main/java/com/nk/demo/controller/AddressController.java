package com.nk.demo.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
public class AddressController {


    @GetMapping("/getAllAddress")
    public ResponseEntity<String> getAllAddress() {


        return ResponseEntity.ok("getAllAddress");
    }

}
