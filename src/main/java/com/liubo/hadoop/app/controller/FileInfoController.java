package com.liubo.hadoop.app.controller;

import com.github.pagehelper.PageInfo;
import com.liubo.hadoop.app.request.file.FileQueryRequest;
import com.liubo.hadoop.app.response.Response;
import com.liubo.hadoop.app.response.file.FileInfoResponse;
import com.liubo.hadoop.dao.entity.FileInfo;
import com.liubo.hadoop.service.FileInfoService;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

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

    @PostMapping("upload")
    public Response upload(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return Response.builder().msg("失败").build();
        }

        String fileName = file.getOriginalFilename();
        InputStream in = file.getInputStream();
        Configuration configuration = new Configuration();
        String sFile = "hdfs://192.168.95.131:9000/user/" + fileName;
        FileSystem fileSystem = FileSystem.get(URI.create(sFile), configuration);
        OutputStream out = fileSystem.create(new Path(sFile), () -> System.out.print("."));
        fileInfoService.saveFile(file);
        IOUtils.copyBytes(in, out, 4096, true);
        return Response.builder().msg("成功").build();
    }
}
