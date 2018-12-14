/**
 *
 */
package com.yj.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author Huang
 * @version 创建时间：2017年4月14日 下午4:08:29
 */
public class DateUtil extends org.apache.commons.lang3.time.DateUtils {

    public static final String NORMAL_FORM = "yyyy-MM-dd HH:mm:ss";

    public static final String NORMAL_FORM_YEAR = "yyyy";

    private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);

    /***
     * Date类型转换成XMLGregorianCalendar类型
     *
     * @param date
     * @return
     */
    public static XMLGregorianCalendar convertToXMLGregorianCalendar(Date date) {
        if (date == null) {
            return null;
        }
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        XMLGregorianCalendar gc = null;
        try {
            gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
        } catch (Exception e) {
            logger.error("Date类型转换成XMLGregorianCalendar类型出错：" + e);
        }
        return gc;
    }

    /***
     * XMLGregorianCalendar类型转换成Date类型
     *
     * @param cal
     * @return
     * @throws Exception
     */
    public static Date convertToDate(XMLGregorianCalendar cal) throws Exception {
        GregorianCalendar ca = cal.toGregorianCalendar();
        return ca.getTime();
    }

    /**
     * String 转 Date
     *
     * @param str    日期字符串
     * @param format 转换格式
     * @return Date
     */
    public static Date stringToDate(String str, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = dateFormat.parse(str);
        } catch (ParseException e) {
            logger.error("String类型 转 Date类型出错：" + e);
        }
        return date;
    }

    /**
     * String 转 Date
     *
     * @param str 日期字符串
     * @return Date
     */
    public static Date stringToDate(String str) {
        return StringUtils.isFormTime(str) ? stringToDate(str, NORMAL_FORM) : null;
    }

    /**
     * Date 转 String
     *
     * @param date   日期
     * @param format 转换格式
     * @return
     */
    public static String dateToString(Date date, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        String strDate = null;
        try {
            if (date != null) {
                strDate = dateFormat.format(date);
            }
        } catch (Exception e) {
            logger.error("Date类型 转 String类型出错：" + e);
        }
        return strDate;
    }

    public static String dateToStringWithDefaultTime(Date date, String defaltTime) {
        String dateString = dateToString(date, "HH:mm:ss");
        if (dateString != null) {
            return dateString;
        } else {
            LocalDate localDate = LocalDate.now();
            dateString = localDate.toString() + " " + defaltTime;
        }
        return dateString;
    }

    /**
     * 获取当天0点的时间
     *
     * @param dateTime 时间
     * @return
     */
    public static String stringToZeroClock(String dateTime) {
        Date date = stringToDate(dateTime);
        return dateToString(date, "yyyy-MM-dd 00:00:00");
    }

    /**
     * Date 转 String
     *
     * @param date 日期
     * @return
     */
    public static String dateToString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(NORMAL_FORM);
        String strDate = null;
        try {
            if (date != null) {
                strDate = dateFormat.format(date);
            }
        } catch (Exception e) {
            logger.error("Date类型 转 String类型出错：" + e);
        }
        return strDate;
    }

    public static Integer stringToInteger(String dateStr, String strForm, String intForm) {
        try {
            return Integer.parseInt(stringToNewString(dateStr, strForm, intForm));
        } catch (Exception e) {
            return null;
        }
    }

    public static String stringToNewString(String dateStr, String strForm, String newStrForm) {
        try {
            return dateToString(stringToDate(dateStr, strForm), newStrForm);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 计算租车天数 字符串的日期格式(yyyy-mm-dd hh:mm:ss)
     */
    public static int daysBetween(String startTime, String endTime) {
        Calendar cal = Calendar.getInstance();
        if (!StringUtils.isFormTime(startTime)) {
            return -1;
        }
        cal.setTime(stringToDate(startTime, NORMAL_FORM));
        long time1 = cal.getTimeInMillis();
        if (!StringUtils.isFormTime(endTime)) {
            return -1;
        }
        cal.setTime(stringToDate(endTime, NORMAL_FORM));
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        long between_time = ((time2 - time1) - (between_days * 1000 * 3600 * 24)) / (1000 * 3600);
        if (between_time >= 4 || between_days == 0) {
            between_days++;
        }
        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 计算租车天数 字符串的日期格式的计算(yyyy-mm-dd hh:mm:ss)
     */
    public static int timesBetween(String smdate, String bdate) {
        Calendar cal = Calendar.getInstance();
        if (!StringUtils.isFormTime(smdate)) {
            return -1;
        }
        cal.setTime(stringToDate(smdate, NORMAL_FORM));
        long time1 = cal.getTimeInMillis();
        if (!StringUtils.isFormTime(bdate)) {
            return -1;
        }
        cal.setTime(stringToDate(bdate, NORMAL_FORM));
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        long between_time = ((time2 - time1) - (between_days * 1000 * 3600 * 24)) / (1000 * 3600);
        return Integer.parseInt(String.valueOf(between_time));
    }

    /**
     * 短租5,6,7为周末
     */
    public static boolean IsWorkingDay(Date date) {

        String strRestDay = "5,6,7";
        Integer dayForWeek = null;
        try {
            dayForWeek = dayForWeek(date);
        } catch (Exception e) {
            logger.error("IsWorkingDay方法发生异常：" + e.toString());
        } // 周几
        //// 判断是否有休息日
        if (strRestDay.indexOf(Integer.toString(dayForWeek)) != -1) {

            return false;
        } else {
            return true;
        }
    }

    public static int dayForWeek(Date date) throws Exception {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Calendar c = Calendar.getInstance();

        c.setTime(format.parse(dateToString(date, "yyyy-MM-dd")));

        int dayForWeek = 0;

        if (c.get(Calendar.DAY_OF_WEEK) == 1) {

            dayForWeek = 7;

        } else {

            dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;

        }

        return dayForWeek;
    }

    public static long stringToDate2(String date) {
        DateFormat dateFormat = new SimpleDateFormat(NORMAL_FORM);
        try {
            if (date != null) {
                Date d = dateFormat.parse(date);
                return d.getTime();
            }
        } catch (Exception e) {
            logger.error("Date类型 转 String类型出错：" + e);
            return -1;
        }
        return -1;
    }

    //字符串转年月日
    public static String stringToNYR(String dateString){
        String s_nd = dateString.substring(0, 4); // 年份
        String s_yf = dateString.substring(5, 7); // 月份
        String s_rq = dateString.substring(8, 10); // 日期

        String sreturn = "";
        sreturn = s_nd;
        sreturn = sreturn + "年";
        sreturn = sreturn +s_yf+ "月";
        sreturn = sreturn + s_rq+"日";
        return sreturn;
    }

    /**
     *  获取指定日期加上规定天数后的日期
     * @param d
     * @param
     * @return
     */
    public static Date getAddForDays(Date d,int days){
        Calendar ca = Calendar.getInstance();
        ca.setTime(d);
        ca.add(Calendar.DATE,days );// num为增加的天数，可以改变的
        d = ca.getTime();
       return d;
    }

    /**
     *  * 指定日期加上获取减去指定天数后的日期
     * @param startDay 指定日期
     * @param count    天数
     * @param isFlag   判断加减 true减   false 加
     * @return
     */
    public static String getBeforeDay(String startDay, int count,boolean isFlag) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(isFlag){
            count*=(-1);
        }
        try {
            Date date = sdf.parse(startDay);
            Calendar cl = Calendar.getInstance();
            cl.setTime(date);
            cl.add(Calendar.DATE, count);
            return sdf.format(cl.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }



    /**
     * 获取指定日期加上指定时间之后的时间
     * @param currdate  指定时间
     * @param days   时间单位
     * @param time   加多少
     * @return
     * @throws ParseException
     */
    public  static Date  getServiceLifeDate(Date currdate,String days ,Integer time){
        Calendar ca = Calendar.getInstance();
        //设置起始时间
        ca.setTime(currdate);
        if("天".equals(days)){
            ca.add(ca.DAY_OF_YEAR, time);//增加一天,负数为减少一天
        }
        if("年".equals(days)){
            ca.add(ca.YEAR, time);//把日期往后增加一年.整数往后推,负数往前移动
        }
        if("月".equals(days)){
            ca.add(ca.MONTH, time);//把日期往后增加一个月.整数往后推,负数往前移动
        }
        if("周".equals(days)){
            ca.add(ca.WEEK_OF_YEAR,time);//增加一个礼拜
        }
        if("小时".equals(days)){
          Integer noTiem = time/24;
          if(noTiem > 0){
              ca.add(ca.DAY_OF_YEAR, noTiem);//增加一天,负数为减少一天
          }
          Integer hourT = time - ( noTiem > 0 ? noTiem:1)*24;
          ca.add(Calendar.HOUR, hourT);// 24小时制
        }
        currdate = ca.getTime();
        return currdate;
    }

    /**
     * 判断时间是不是今天
     * @param date
     * @return    是返回true，不是返回false
     */
    public static boolean isNow(Date date) {
        //当前时间
        Date now = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        //获取今天的日期
        String nowDay = sf.format(now);
        //对比的时间
        String day = sf.format(date);
        return day.equals(nowDay);
    }

    /**
     * 获取过去的天数
     * @param date
     * @return
     */
    public static long pastDays(Date date) {
        long t = new Date().getTime()-date.getTime();
        return t/(24*60*60*1000);
    }

    /**
     * 获取过去的小时
     * @param date
     * @return
     */
    public static long pastHour(Date date) {
        long t = new Date().getTime()-date.getTime();
        return t/(60*60*1000);
    }

    /**
     * 获取过去的分钟
     * @param date
     * @return
     */
    public static long pastMinutes(Date date) {
        long t = new Date().getTime()-date.getTime();
        return t/(60*1000);
    }

    /**
     * 转换为时间（天,时:分:秒.毫秒）
     * @param timeMillis
     * @return
     */
    public static String formatDateTime(long timeMillis){
        long day = timeMillis/(24*60*60*1000);
        long hour = (timeMillis/(60*60*1000)-day*24);
        long min = ((timeMillis/(60*1000))-day*24*60-hour*60);
        long s = (timeMillis/1000-day*24*60*60-hour*60*60-min*60);
        long sss = (timeMillis-day*24*60*60*1000-hour*60*60*1000-min*60*1000-s*1000);
        return (day>0?day+",":"")+hour+":"+min+":"+s+"."+sss;
    }

    /**
     * 获取两个日期之间的天数
     *
     * @param before
     * @param after
     * @return
     */
    public static double getDistanceOfTwoDate(Date before, Date after) {
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
    }

    /**
     * 获取两个日期之间的天数
     * @param startDay 开始日期
     * @param endDay 截止日期
     * @return
     */
    public static Integer daysBetweenT(String startDay, String endDay) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = null;
        Date date2 = null;
        try {
            date1 = sdf.parse(startDay);
            date2 = sdf.parse(endDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        long time1 = cal.getTimeInMillis();
        cal.setTime(date2);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 获取两个日期之间的天数
     * @param date1
     * @param date2
     * @return
     */
    public static Integer daysBetweenThree(Date date1, Date date2) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        long time1 = cal.getTimeInMillis();
        cal.setTime(date2);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(between_days));
    }

}
