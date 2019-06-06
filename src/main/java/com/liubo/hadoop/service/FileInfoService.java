package com.liubo.hadoop.service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liubo.hadoop.dao.entity.FileInfo;
import com.liubo.hadoop.dao.mapper.ext.ExtFileInfoMapper;
import com.liubo.hadoop.util.exception.MyException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

/**
 * @author baoxiaodong
 */
@Slf4j
@Service
public class FileInfoService {

    @Autowired
    ExtFileInfoMapper fileInfoMapper;

    public PageInfo<FileInfo> getByCondition(String fileName, int page, int limit) {

        List<FileInfo> fileInfoList;
        fileName = StringUtils.isBlank(fileName) ? fileName : "%" + fileName + "%";
        try {
            PageHelper.startPage(page, limit);
            fileInfoList = fileInfoMapper.selectByCondition(fileName);
        } catch (Exception e) {
            log.error("文件查询失败！", e);
            throw new MyException("文件查询失败！");
        }
        PageInfo<FileInfo> pageInfo = PageInfo.of(fileInfoList);
        log.debug("文件查询，返回结果：{}", JSON.toJSONString(pageInfo));
        return pageInfo;
    }

    public void saveFile(MultipartFile file) {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setFileNo(new Date().toString());
        fileInfo.setFileName(file.getOriginalFilename());
        fileInfo.setFileSize(file.getSize());
        fileInfo.setFileType(getExtensionName(file.getOriginalFilename()));
        fileInfo.setFileAuthor("admin");
        fileInfoMapper.insertSelective(fileInfo);
    }

    private static String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return "unknown";
    }
}
