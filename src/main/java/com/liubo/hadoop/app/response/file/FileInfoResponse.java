package com.liubo.hadoop.app.response.file;

import lombok.Data;

import java.util.Date;

@Data
public class FileInfoResponse {

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
