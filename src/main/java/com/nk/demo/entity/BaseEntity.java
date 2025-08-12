package com.nk.demo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;


@Data
public class BaseEntity {

    @TableId(type= IdType.AUTO)
    private String id;

    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer deleteMark;

    /**
     * 创建时间
     */
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private Date creatorTime;

    /**
     * 创建用户的authId
     */
    @TableField(fill = FieldFill.INSERT)
    private String creatorUser;

    /**
     * 创建用户所属部门
     */
    @TableField(fill = FieldFill.INSERT)
    private String creatorOrgId;

    /**
     * 创建用户的staffId
     */
    @TableField(fill = FieldFill.INSERT)
    private String creatorStaffId;

    /**
     * 修改时间
     */
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date lastModifyTime;

    /**
     * 修改用户的authId
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String lastModifyUser;

}
