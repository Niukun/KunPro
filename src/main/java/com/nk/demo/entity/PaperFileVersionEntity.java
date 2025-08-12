package com.nk.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Description: 纸张文件版本信息
 * @Author: zc
 * @Date: 2024/3/1 9:50
 **/
@Data
@TableName(value = "tb_paper_file_version")
public class PaperFileVersionEntity extends BaseEntity {

    private String fileId;

    private String fileUrl;

    private Integer fileVersion;

    private String fileSummary;
}
