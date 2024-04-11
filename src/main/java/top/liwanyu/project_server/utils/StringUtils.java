package top.liwanyu.project_server.utils;

/**
 * @description:字符串工具类
 */
public class StringUtils {
    //为空
    public static boolean isEmpty(String str){
        if(str == null || "".equals(str)|| "null".equals(str)){
            return true;
        }
        return false;
    }

    //不为空
    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }
}