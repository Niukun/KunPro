package com.nk.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("address")
public class Address {

    @TableId
    private String _id;

    private String city;

    private String company_name;

    private String location;

    private String company_url;


    private String industry;

    private String company_property;

    private String source_name;

    private String company_size;

}
