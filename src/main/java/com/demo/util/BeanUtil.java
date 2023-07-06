package com.demo.util;

import com.demo.error.DemoException;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Tianyi Fu
 * @since 2023/7/5
 */
public class BeanUtil {
    /**
     * target change
     *
     * @param source original class
     * @param target target class
     * @param <TargetClass>    the class want to be change
     * @return target object
     */
    public static <TargetClass> TargetClass sourceToTarget(Object source, Class<TargetClass> target) throws DemoException {
        if (source == null) {
            return null;
        } else {
            TargetClass targetObject;
            try {
                targetObject = target.newInstance();
                BeanUtils.copyProperties(source, targetObject);
            } catch (Exception e) {
                throw new DemoException("convert error");
            }
            return targetObject;
        }
    }

    /**
     * target list change
     *
     * @param sourceList original class
     * @param target target class
     * @param <TargetClass>    the class want to be change
     * @return target object
     */
    public static <TargetClass> List<TargetClass> convertToList(Collection<?> sourceList, Class<TargetClass> target) throws DemoException {
        if (sourceList == null) {
            return null;
        } else {
            List<TargetClass> targetList = new ArrayList<>(sourceList.size());
            try {
                for (Object source : sourceList) {
                    TargetClass targetObject = target.newInstance();
                    BeanUtils.copyProperties(source, targetObject);
                    targetList.add(targetObject);
                }
            } catch (Exception e) {
                throw new DemoException("convert error");
            }
            return targetList;
        }
    }
}
