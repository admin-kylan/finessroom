package com.yj.common.util;

import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

/**
 * 〈一句话功能简述〉<br>
 * 〈UUID生成工具类〉
 *
 * @author 欧俊俊
 * @create 2018/9/10
 */
public class UUIDUtils {
    /**
     * 生成32位UUID去横杆
     * @return
     */
    public static final String generateUUID(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replaceAll("-", "").toUpperCase();
    }

    /**
     * 生成16位GUID
     * @return
     */
    public static final String generateGUID(){
        UUID uuid=UUID.randomUUID();
        String str = uuid.toString();
        byte[] bytes = str.getBytes();
        long i = 1;
        for (byte b:bytes) {
            i *= ((int)b + 1 );
        }
        //十六进制(i-时间戳(1970年开始计算))
        return String.format("%x", i - System.currentTimeMillis());
    }



    public static void main(String[] args) {
        for (int i = 0; i < 8 ; i++) {
            System.out.println(generateGUID());
        }

    }
}
