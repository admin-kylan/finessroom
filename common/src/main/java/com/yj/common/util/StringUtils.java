package com.yj.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * @author Huang
 * @version 创建时间：2017年5月6日 下午5:03:03
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    public static boolean isNumber(String str) {
        return str.matches("-?[0-9]+.*[0-9]*");
    }

    public static boolean isFormTime(String str) {
        boolean convertSuccess = true;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            convertSuccess = false;
        }
        return convertSuccess;
    }

    /**
     * @author zhy 用,隔开的字符串组转成符合sql里in的格式
     */
    public static String transitionForSqlConditionIn(String strs) {

        StringBuffer inStr = new StringBuffer();
        for (String str : strs.split(",")) {

            inStr.append("'").append(str).append("'").append(",");
        }

        return inStr.toString().substring(0, inStr.length() - 1);
    }

    public static String getSeqAddOneAppendZeroLeft(String originalSeq, Integer digit) {

        String originalSeqRemoveLeftZero = originalSeq.replaceAll("^(0+)", "");
        Integer newSeqInteger = NumberUtils.toInt(originalSeqRemoveLeftZero) + 1;
        String result = StringUtils.leftPad(newSeqInteger.toString(), digit, "0");
        result = StringUtils.right(result, digit);
        return result;
    }

    public static String getSeqAppendZeroLeft(String seq, Integer digit) {

        String result = StringUtils.leftPad(seq, digit, "0");
        result = StringUtils.right(result, digit);
        return result;
    }

    public static String arrayToString(String[] ids) {
        StringBuilder sbd = new StringBuilder();
        for (int i = 0; i < ids.length; i++) {
            sbd.append(ids[i] + ",");
        }
        if (ids.length == 0) {
            return "";
        }
        return sbd.toString().substring(0, sbd.toString().length() - 1);
    }

    public static String createRequestUrl(String url, Map<String, String> parmMap) {
        StringBuilder sbd = new StringBuilder(url);
        if (parmMap.isEmpty()) {
            return url;
        }
        sbd.append("?");

        for (Map.Entry<String, String> entry : parmMap.entrySet()) {
            sbd.append(entry.getKey() + "=" + parmMap.get(entry.getKey()) + "&");
        }
        return sbd.toString().substring(0, sbd.length() - 1);
    }

    /**
     * 以 “-”截取日期字符串
     * @param strDate
     * @return
     */
    public static  String dateStrToString(String strDate){

        String[] arr=strDate.split("-");
        return arr[0]+"年 "+arr[1]+"月 "+arr[2].substring(0,2)+"日";
    }

    /**
     * 获取集合里的字符串
     * @param key
     * @param map
     * @return
     */
    public static String getStringObject(String key,Map<String,Object> map){
        String mess = "";
        if(map != null){
            Object str = map.get(key);
            if(str != null){
                mess = str.toString();
            }
        }
        return  mess;
    }

    public static boolean toIsEmpty(String str){
        return org.apache.commons.lang3.StringUtils.isEmpty(str);
    }



}
