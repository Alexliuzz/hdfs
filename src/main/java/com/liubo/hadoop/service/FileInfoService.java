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

import java.util.List;

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
}