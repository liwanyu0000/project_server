package top.liwanyu.project_server.utils;

import java.text.SimpleDateFormat;

public class DateUtils {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);

    public static String getNowDate() {
        return simpleDateFormat.format(System.currentTimeMillis());
    }
}
