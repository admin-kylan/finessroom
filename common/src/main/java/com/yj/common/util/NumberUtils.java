package com.yj.common.util;

import java.math.BigDecimal;

/**
 * @author zhy
 *  
 */  
public class NumberUtils extends org.apache.commons.lang3.math.NumberUtils{  
	
	public static BigDecimal toBigDecimal(String str) {
		
		return new BigDecimal(str);
	}
	
	public static BigDecimal toBigDecimal(Integer i) {
		
		return new BigDecimal(Integer.toString(i));
	}
}  