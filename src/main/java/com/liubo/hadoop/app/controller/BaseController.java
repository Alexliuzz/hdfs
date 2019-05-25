package com.liubo.hadoop.app.controller;

import com.liubo.hadoop.util.exception.MyException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller基类
 *
 * @author leibing.song
 * @since 2019-03-03
 */
@Slf4j
public class BaseController {
    protected <T> List<T> transferListType(List sourceList, Class<T> targetType) {
        try {
            if (CollectionUtils.isEmpty(sourceList) || targetType == null) {
                return new ArrayList<>();
            } else {
                ArrayList<T> targetList = new ArrayList<>();
                T target;
                for (Object source : sourceList) {
                    target = targetType.newInstance();
                    BeanUtils.copyProperties(source, target);
                    targetList.add(target);
                }
                return targetList;
            }
        } catch (Exception e) {
            log.error("集合转换失败！", e);
            throw new MyException("集合转换失败！");
        }
    }
}
