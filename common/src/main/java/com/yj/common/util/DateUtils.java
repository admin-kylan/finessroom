package com.yj.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 〈一句话功能简述〉<br>
 * 〈日期处理类〉
 *
 * @author 欧俊俊
 * @create 2018/8/31
 */
public class DateUtils {
    private final static Logger logger = LoggerFactory.getLogger(DateUtils.class);
    /**
     * 时间格式(yyyy-MM-dd)
     */
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    /**
     * 时间格式(yyyy-MM-dd HH:mm:ss)
     */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String format(Date date) {
        return format(date, DATE_TIME_PATTERN);
    }

    public static Date format2(Date date) {
        return format2(date, DATE_PATTERN);
    }

    public static String format(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

    public static Date format2(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            try {
                return df.parse(df.format(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * @Description: 获取当前时间
     * @Author: 欧俊俊
     * @Date: 2018/9/15 9:55
     */
    public static String getNowTime() {
        return format(new Date());
    }

    /**
     * @Description: 获取当前时间 年月日
     * @Author: 欧俊俊
     * @Date: 2018/9/15 9:55
     */
    public static Date getNowTime2() {
        return format2(new Date());
    }

    /**
     * 计算距离现在多久，非精确
     *
     * @param date
     * @return
     */
    public static String getTimeBefore(Date date) {
        Date now = new Date();
        long l = now.getTime() - date.getTime();
        long day = l / (24 * 60 * 60 * 1000);
        long hour = (l / (60 * 60 * 1000) - day * 24);
        long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        String r = "";
        if (day > 0) {
            r += day + "天";
        } else if (hour > 0) {
            r += hour + "小时";
        } else if (min > 0) {
            r += min + "分";
        } else if (s > 0) {
            r += s + "秒";
        }
        r += "前";
        return r;
    }

    /**
     * 计算距离现在多久，精确
     *
     * @param date
     * @return
     */
    public static String getTimeBeforeAccurate(Date date) {
        Date now = new Date();
        long l = now.getTime() - date.getTime();
        long day = l / (24 * 60 * 60 * 1000);
        long hour = (l / (60 * 60 * 1000) - day * 24);
        long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        String r = "";
        if (day > 0) {
            r += day + "天";
        }
        if (hour > 0) {
            r += hour + "小时";
        }
        if (min > 0) {
            r += min + "分";
        }
        if (s > 0) {
            r += s + "秒";
        }
        r += "前";
        return r;
    }

    /**
     * String转换成Date 根据指定格式
     *
     * @param str
     * @param format
     * @return
     * @throws ParseException
     */
    public static Date getDataforString(String str, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = dateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
