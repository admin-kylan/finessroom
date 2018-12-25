/**
 *
 */
package com.yj.common.util;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Date;
import java.util.Random;

/**
 * @author Huang
 * @version 创建时间：2017年5月24日 下午2:26:27
 */
public class RandomUtils {
	 public static final String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	     public static final String LETTERCHAR = "abcdefghijkllmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	     public static final String NUMBERCHAR = "0123456789";

    public static final String MEMBERCHAR = "0123456789987654321123456789741258963369852147321456987147852369951357";
        /**生成随机数推荐码*/
        public static final String ALLINVATECHAR = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";


    /**
     * 生成随机数推荐码
         * 返回一个定长的随机字符串(只包含写字母、数字)
     *
         * @param length 随机字符串长度
         * @return 返回随机字符串
         */
        public static String getInvateCodeString(int length) {
            StringBuffer sb = new StringBuffer();
            Random random = new Random();
            for (int i = 0; i < length; i++) {
                sb.append(ALLINVATECHAR.charAt(random.nextInt(ALLINVATECHAR.length())));
            }
            return sb.toString();
        }


	     /**
	      * 返回一个定长的随机字符串(只包含大小写字母、数字)
	      *
     * @param length 随机字符串长度
	      * @return 随机字符串
	      */
	     public static String generateString(int length) {
             StringBuffer sb = new StringBuffer();
             Random random = new Random();
	         for (int i = 0; i < length; i++) {
	             sb.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));
	         }
	         return sb.toString();
	     }

	     /**
          * 返回一个定长的随机纯字母字符串(只包含大小写字母)
          *
     * @param length 随机字符串长度
          * @return 随机字符串
          */
         public static String generateMixString(int length) {
             StringBuffer sb = new StringBuffer();
             Random random = new Random();
             for (int i = 0; i < length; i++) {
                 sb.append(ALLCHAR.charAt(random.nextInt(LETTERCHAR.length())));
             }
             return sb.toString();
         }

         /**
          * 返回一个定长的随机纯大写字母字符串(只包含大小写字母)
          *
     * @param length 随机字符串长度
          * @return 随机字符串
          */
         public static String generateLowerString(int length) {
             return generateMixString(length).toLowerCase();
         }

         /**
          * 返回一个定长的随机纯小写字母字符串(只包含大小写字母)
          *
     * @param length 随机字符串长度
          * @return 随机字符串
          */
         public static String generateUpperString(int length) {
             return generateMixString(length).toUpperCase();
         }

         /**
          * 生成一个定长的纯0字符串
          *
     * @param length 字符串长度
          * @return 纯0字符串
          */
         public static String generateZeroString(int length) {
             StringBuffer sb = new StringBuffer();
             for (int i = 0; i < length; i++) {
                 sb.append('0');
             }
             return sb.toString();
         }

         /**
          * 根据数字生成一个定长的字符串，长度不够前面补0
          *
     * @param num       数字
     * @param fixdlenth 字符串长度
          * @return 定长的字符串
          */
         public static String toFixdLengthString(long num, int fixdlenth) {
             StringBuffer sb = new StringBuffer();
             String strNum = String.valueOf(num);
             if (fixdlenth - strNum.length() >= 0) {
                 sb.append(generateZeroString(fixdlenth - strNum.length()));
             } else {
                 throw new RuntimeException("将数字" + num + "转化为长度为" + fixdlenth
                         + "的字符串发生异常！");
             }
             sb.append(strNum);
             return sb.toString();
         }

         /**
          * 每次生成的len位数都不相同
          *
          * @param param
          * @return 定长的数字
          */
         public static int getNotSimple(int[] param, int len) {
             Random rand = new Random();
             for (int i = param.length; i > 1; i--) {
                 int index = rand.nextInt(i);
                 int tmp = param[index];
                 param[index] = param[i - 1];
                 param[i - 1] = tmp;
             }
             int result = 0;
             for (int i = 0; i < len; i++) {
                 result = result * 10 + param[i];
             }
             return result;
         }

    /**
     * 获取当前时间+六位数的随机代码
     * 并进行base64编码
     */
    public static String randomEncoder() throws UnsupportedEncodingException {
        int max = 999999;
        int min = 100000;
        Random random = new Random();
        int s = random.nextInt(max - min) + min;
        final String str = String.valueOf(s);//int转string
        //System.out.println("六位数随机数"+str);

        long time = new Date().getTime();
        final String str1 = String.valueOf(time);
        //System.out.println("当前时间随机数"+str1);

        final Base64.Decoder decoder = Base64.getDecoder();
        final Base64.Encoder encoder = Base64.getEncoder();
        final byte[] textByte = (str + str1).getBytes("UTF-8");
        //编码
        final String encodedText = encoder.encodeToString(textByte);
        System.out.println(encodedText);
        return encodedText;


    }

    /**
     * 获取当前时间+六位数的随机base64解码
     */
    public static String randomDecoder() throws UnsupportedEncodingException {
        int max = 999999;
        int min = 100000;
        Random random = new Random();
        int s = random.nextInt(max - min) + min;
        final String str = String.valueOf(s);//int转string
        //System.out.println("六位数随机数"+str);

        long time = new Date().getTime();
        final String str1 = String.valueOf(time);
        final String str2 = "6444545";
        //System.out.println("当前时间随机数"+str1);
        //System.out.println(str+str1);

        final Base64.Decoder decoder = Base64.getDecoder();
        final Base64.Encoder encoder = Base64.getEncoder();
        final byte[] textByte = (str + str2 + str1).getBytes("UTF-8");
        //编码
        final String encodedText = encoder.encodeToString(textByte);
        //解码
        System.out.println(new String(decoder.decode(encodedText), "UTF-8"));
        return (new String(decoder.decode(encodedText), "UTF-8"));
    }


    /**
     * 生成短信验证码
     *
     * @param number
     * @return
     */
    public static String getSMSCode(int number) {
        Random random = new Random();
        String code = "";
        for (int i = 0; i < 6; i++) {
            code += random.nextInt(10);
        }
        return code;
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getOrderNumber(String uid) {
        String date = DateUtil.dateToString(new Date(), "yyyyMMddHHmmss");
        StringBuffer sb = new StringBuffer();
        sb.append(date);
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            sb.append(NUMBERCHAR.charAt(random.nextInt(NUMBERCHAR.length())));
            ;
        }
        sb.append(uid);
        return sb.toString();
    }

    public static String generateNumber(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(MEMBERCHAR.charAt(random.nextInt(MEMBERCHAR.length())));
        }
        return sb.toString();
    }

//    public static void main(String[] args) {
//        System.out.println(getOrderNumber());
//    }
    public static void main(String[] args) throws UnsupportedEncodingException {

    }
}
