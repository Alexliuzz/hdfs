package com.liubo.hadoop.controller;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URI;

@RestController
@RequestMapping
public class TestController {

    static Configuration conf = new Configuration(true);
    static {
        // 指定hadoop fs的地址
        conf.set("fs.default.name", "hdfs://192.168.95.131:9000");
    }

    @GetMapping("ok/{name}")
    public String test(@PathVariable("name") String name) {
        String sRet = "liubo" + name;
        return sRet;
    }

    @PostMapping("upload")
    public String upload(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }

        String fileName = file.getOriginalFilename();
        InputStream in = file.getInputStream();
        Configuration configuration = new Configuration();
        String sFile = "hdfs://192.168.95.131:9000/user/" + fileName;
        FileSystem fileSystem = FileSystem.get(URI.create(sFile), configuration);

        OutputStream out = fileSystem.create(new Path(sFile), () -> System.out.print("."));

        IOUtils.copyBytes(in, out, 4096, true);
        return "成功";
    }

    @GetMapping("search")
    public String search() throws IOException {
        String direPath = "/user";
        FileSystem fs = FileSystem.get(URI.create(direPath), conf);
        FileStatus[] fileList = fs.listStatus(new Path(direPath));
        StringBuilder sb = new StringBuilder();
        for(FileStatus file : fileList){
            sb.append(file.getPath().getName()).append("\r\n");
        }
        fs.close();
        return sb.toString();
    }

//    @GetMapping("download")
//    public void download(HttpServletResponse response) throws Exception {
//        String dst = "hdfs://192.168.95.131:9000/user/text3.txt";
//        Configuration conf = new Configuration();
//        FileSystem fs = FileSystem.get(URI.create(dst), conf);
//        OutputStream out = fs.create(new Path(dst), () -> System.out.print("."));
//        ByteArrayInputStream byteArrayInputStream = parse(out);
//        response.setHeader("Content-Disposition", "attachment;filename=text3.txt");
//        response.getOutputStream().write(byteArrayInputStream.);
//    }

    public ByteArrayInputStream parse(final OutputStream out) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos = (ByteArrayOutputStream) out;
        final ByteArrayInputStream swapStream = new ByteArrayInputStream(baos.toByteArray());
        return swapStream;
    }
}
