package com.nk.demo.feign.response.dataRepo.luobao;



import lombok.Data;

import java.util.List;

@Data
public class Group {
    private String name;
    private List<Item> group;
}
