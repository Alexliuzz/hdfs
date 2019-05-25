package com.liubo.hadoop.dao.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
  * @author Administrator
  * @since 2019-05-25
  */
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FileInfo {
    private Integer id;

    private String fileNo;

    private String fileName;

    private String fileType;

    private String fileAuthor;

    private String fileDesc;

    private Long fileSize;

    private String fileUrl;

    private String userId;

    private Date createTime;

    private Date updateTime;
}