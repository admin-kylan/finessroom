package com.yj.common.util;

import java.util.Map;

/**
 * 数字工具类
 * @author xbj
 *  
 */  
public class NumberUtilsTwo{

	/**
	 * 获取map里的指定key 的value 转换Integer
	 * @param key
	 * @param map
	 * @return
	 */
	public static Integer getIntNum(String key,Map<String,Object> map){
		Integer intNum = 0;
		if(map != null ){
			Object val = map.get(key);
			if(val != null && val instanceof  Integer){
				intNum = (Integer)val;
			}
		}
		return  intNum;
	}
	/**
	 * 获取map里的指定key 的value 转换Double
	 * @param key
	 * @param map
	 * @return
	 */
	public static Double getDouNum(String key,Map<String,Object> map){
		Double douNum = 0.0;
		if(map != null ){
			Object val = map.get(key);
			if(val != null ){
				douNum = Double.valueOf(val.toString());
			}
		}
		return  douNum;
	}

	/**
	 * 获取map里的指定key 的value 转换Double
	 * @param key
	 * @param map
	 * @return
	 */
	public static Double getDoubleNum(String key,Map<String,Double> map){
		Double douNum = 0.0;
		if(map != null ){
			Object val = map.get(key);
			if(val != null ){
				douNum = Double.valueOf(val.toString());
			}
		}
		return  douNum;
	}

	public static Double getDoublPrice(Double price){
		if(price == null){
			price = 0.0;
		}
		return  price;
	}
}  