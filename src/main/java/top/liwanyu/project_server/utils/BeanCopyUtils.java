package top.liwanyu.project_server.utils;

import org.springframework.beans.BeanUtils;

// 拷贝同名属性的值

public class BeanCopyUtils<T> {

    public static <T> T copyBean(Object source, Class<? extends T> clazz) {
        if (null == source) {
            return null;
        }
        try {
            T target = clazz.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(source, target);
            return target;
        } catch (Exception e) {
            System.err.println("JavaBean拷贝异常:----" + "[" + source.getClass().getName()
                    + "] To [" + clazz.getName() + "] :" + e);
            return null;
        }
    }
}
