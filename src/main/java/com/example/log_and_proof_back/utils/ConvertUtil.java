package com.example.log_and_proof_back.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author ifredom
 * @Description 类型转换: Entity - Vo转换
 * @Date 2022/5/10 15:59
 * @Param [params]
 **/
public class ConvertUtil {
    public static final Logger logger = LoggerFactory.getLogger(ConvertUtil.class);
    public static <T> T entityToVo(Object source, Class<T> target) {
        if (source == null) {
            return null;
        }
        T targetObject = null;
        try {
            targetObject = target.newInstance();
            BeanUtils.copyProperties(source, targetObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return targetObject;
    }
    public static <T> List<T> entityToVoList(Collection<?> sourceList, Class<T> target) {
        if (sourceList == null) {
            return null;
        }
        List<T> targetList = new ArrayList<>(sourceList.size());
        try {
            for (Object source : sourceList) {
                T targetObject = target.newInstance();
                BeanUtils.copyProperties(source, targetObject);
                targetList.add(targetObject);
            }
        } catch (Exception e) {
            logger.error("convert error ", e);
        }
        return targetList;
    }
}