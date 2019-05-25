package com.liubo.hadoop.app.controller;

import com.github.pagehelper.PageInfo;
import com.liubo.hadoop.app.request.file.FileQueryRequest;
import com.liubo.hadoop.app.response.Response;
import com.liubo.hadoop.app.response.file.FileInfoResponse;
import com.liubo.hadoop.dao.entity.FileInfo;
import com.liubo.hadoop.service.FileInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("file")
public class FileInfoController extends BaseController {

    @Autowired
    FileInfoService fileInfoService;

    @GetMapping("list")
    public Response<FileInfoResponse> getFileList(FileQueryRequest request){
        PageInfo<FileInfo> pageInfo = fileInfoService.getByCondition(request.getFileName(),request.getPage(),request.getLimit());
        return Response.<FileInfoResponse>builder().code(0)
                .data(transferListType(pageInfo.getList(),FileInfoResponse.class))
                .count(pageInfo.getTotal())
                .build();
    }
}
