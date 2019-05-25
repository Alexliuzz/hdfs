package com.liubo.hadoop.dao.mapper;

import com.liubo.hadoop.dao.entity.FileInfo;
import org.apache.ibatis.annotations.Mapper;

/**
  * @author Administrator
  * @since 2019-05-25
  */
@Mapper
public interface FileInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FileInfo record);

    int insertSelective(FileInfo record);

    FileInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FileInfo record);

    int updateByPrimaryKey(FileInfo record);
}