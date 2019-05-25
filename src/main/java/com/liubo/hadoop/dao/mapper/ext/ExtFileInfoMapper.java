package com.liubo.hadoop.dao.mapper.ext;

import com.liubo.hadoop.dao.entity.FileInfo;
import com.liubo.hadoop.dao.mapper.FileInfoMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ExtFileInfoMapper extends FileInfoMapper {

    List<FileInfo> selectByCondition(@Param("fileName") String fileName);
}
