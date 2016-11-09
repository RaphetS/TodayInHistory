package org.raphets.todayinhistory.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 工具类
 */

public class DateFormatUtil {
    public static String dateFormat(Date date){
        SimpleDateFormat format=new SimpleDateFormat("yyyy年MM月dd日");
        return format.format(date);
    }

    public static String getYear(Date date){
        SimpleDateFormat format=new SimpleDateFormat("yyyy");
        return format.format(date);
    }

    public static String getMonth(Date date){
        SimpleDateFormat format=new SimpleDateFormat("MM");
        return format.format(date);
    }
    public static String getDay(Date date){
        SimpleDateFormat format=new SimpleDateFormat("dd");
        return format.format(date);
    }
}
