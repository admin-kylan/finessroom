package com.yj.common.util;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈卡号生成工具〉
 *
 * @author 欧俊俊
 * @create 2018/9/4
 */
public class CardUtils {
    private static final Logger log = LoggerFactory.getLogger(CardUtils.class);

    public static void main(String[] args) throws YJException {
        String i = "1";
        //System.out.println(i.substring(0, 1));
        //genByNum(10001L,30000L,4);
        //genByBenginAndEnd(6666,8888);

        String[] position = new String[]{"1","2","3"};
        List list = genByBenginAndNum(1L, 1000, 4, position);
    }

    /**
     * 由开始数和结束数生成数字
     * @param begin
     * @param end
     */
    public static List genByBenginAndEnd(Long begin,Long end,Integer jumpNum,String[] position) throws YJException {
        if(begin>=end){
            log.error("参数错误:开始数必须小于结束数");
            throw new YJException(YJExceptionEnum.NUMBER_ERROR);
        }
        List list = new ArrayList();
        Boolean beginNoIn = false;
        Boolean middleNoIn = false;
        Boolean endNoIn = false;
        if (position.length>0){
            //检查数组是否包含某个值
            beginNoIn = Arrays.asList(position).contains("1");
            middleNoIn = Arrays.asList(position).contains("2");
            endNoIn = Arrays.asList(position).contains("3");
        }
        //i的位数
        Integer iLength = 0;
        //跳过数字的位数
        Integer jumpNumLength =String.valueOf(jumpNum).length();
        Boolean isPrint = false;
        Integer maxIndex = 0;
        for (Long i = begin; i <end+1 ; i++) {
            isPrint = true;
            iLength = String.valueOf(i).length();
            //检查是否需要去除数字(是：进入 否：跳出并放进list)
            if(beginNoIn||middleNoIn||endNoIn){
                //遍历位数(例：i = 14567 从1，4，5，6，7遍历是否满足某个数字)
                for (int j = 0; j < iLength ; j++) {
                    maxIndex = j+jumpNumLength;
                    if(maxIndex<=iLength){
                        if (String.valueOf(i).substring(j,j+jumpNumLength).equals(String.valueOf(jumpNum))) {
                            //检查开头是否需要去除jumpNum这个数字(是：进入 否：跳出并放进list)
                            if (beginNoIn){
                                //检查是否是开头
                                if (maxIndex==1){
                                    isPrint = false;
                                    log.info("开头 跳过数字 = {}",i);
                                    continue;
                                }
                            }else {
                                isPrint = true;
                            }
                            //检查中间是否需要去除jumpNum这个数字(是：进入 否：跳出并放进list)
                            if (middleNoIn){
                                //检查是否是中间
                                if (maxIndex!=1&&maxIndex!=iLength){
                                    isPrint = false;
                                    log.info("中间 跳过数字 = {}",i);
                                    continue;
                                }
                            }else {
                                isPrint = true;
                            }
                            //检查尾号是否需要去除jumpNum这个数字(是：进入 否：跳出并放进list)
                            if (endNoIn){
                                //检查是否是尾号
                                if (maxIndex==iLength){
                                    isPrint = false;
                                    log.info("尾号 跳过数字 = {}",i);
                                    continue;
                                }
                            }else {
                                isPrint = true;
                            }
                        }
                    }
                }
            }
            if (isPrint) {
                //log.info("生成的数字是{}",i);
                list.add(i);
            }
        }
        return list;
    }

    /**
     * 由开始数和数量生成数字列表
     * @param begin
     * @param num
     */
    public static List genByBenginAndNum(Long begin,Integer num,Integer jumpNum,String[] position) throws YJException {
        List list = new ArrayList();
        Boolean beginNoIn = false;
        Boolean middleNoIn = false;
        Boolean endNoIn = false;
        if (position.length>0){
            //检查数组是否包含某个值
            beginNoIn = Arrays.asList(position).contains("1");
            middleNoIn = Arrays.asList(position).contains("2");
            endNoIn = Arrays.asList(position).contains("3");
        }
        //i的位数
        Integer iLength = 0;
        //跳过数字的位数
        Integer jumpNumLength =String.valueOf(jumpNum).length();
        Boolean isPrint = false;
        //只要有一个条件满足isTrue==true则跳过
        Boolean isTrue = false;
        Integer maxIndex = 0;
        //跳过的数量
        Integer jumpCount = 0;
        //生成的数量
        Integer genCount = 0;
        for (Long i = begin; i < begin+num ; i++) {
            //下一个i值，重置数据
            isPrint = true;
            isTrue = false;
            iLength = String.valueOf(i).length();
            //检查是否需要去除数字(是：进入 否：跳出并放进list)
            if(beginNoIn||middleNoIn||endNoIn){
                //遍历位数(例：i = 14567 从1，4，5，6，7遍历是否满足某个数字)
                for (int j = 0; j < iLength ; j++) {
                    maxIndex = j+jumpNumLength;
                    if(maxIndex<=iLength){
                        if (String.valueOf(i).substring(j,j+jumpNumLength).equals(String.valueOf(jumpNum))) {
                            //检查开头是否需要去除jumpNum这个数字(是：进入 否：跳出并放进list)
                            if (beginNoIn){
                                //检查是否是开头
                                if (maxIndex==1){
                                    isPrint = false;
                                    isTrue = true;
                                    //log.info("开头 跳过数字 = {}",i);
                                    continue;
                                }
                            }else {
                                isPrint = true;
                            }
                            //检查中间是否需要去除jumpNum这个数字(是：进入 否：跳出并放进list)
                            if (middleNoIn){
                                //检查是否是中间
                                if (maxIndex!=1&&maxIndex!=iLength){
                                    isPrint = false;
                                    isTrue = true;
                                    //log.info("中间 跳过数字 = {}",i);
                                    continue;
                                }
                            }else {
                                isPrint = true;
                            }
                            //检查尾号是否需要去除jumpNum这个数字(是：进入 否：跳出并放进list)
                            if (endNoIn){
                                //检查是否是尾号
                                if (maxIndex==iLength){
                                    isPrint = false;
                                    isTrue = true;
                                    //log.info("尾号 跳过数字 = {}",i);
                                    continue;
                                }
                            }else {
                                isPrint = true;
                            }
                        }
                    }
                }

            }
            if (isPrint&&!isTrue) {
                list.add(i);
                genCount++;
                //log.info("生成的数字是{}",i);
            }
            //每跳过一次数字，num+1
            if(isTrue){
                jumpCount++;
                num++;
            }
        }
        log.info("jumpCount = "+jumpCount);
        log.info("genCount = "+genCount);
        log.info("list.size() = "+list.size());
        Long startNo = Long.valueOf(list.get(0).toString());
        Long endNo = Long.valueOf(list.get(list.size()-1).toString()) ;
        log.info("startNo = "+startNo);
        log.info("endNo = "+endNo);
        return list;
    }

    /**
     * 检查是否有交集
     * @param begin
     * @param end
     * @param startNo
     * @param endNo
     * @return
     */
    public static Boolean isCross(Long begin, Long end, Long startNo, Long endNo) {
        boolean flag = false;
        if ((( begin <= startNo) && (startNo <= end)) || (( begin <= endNo) && (endNo <= end))
                || ((startNo <=  begin) && ( begin <= endNo)) || ((startNo <= end) && (end <= endNo))) {
            flag = true;
        }
        return flag;
    }
}
