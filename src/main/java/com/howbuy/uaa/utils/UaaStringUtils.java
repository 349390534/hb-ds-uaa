package com.howbuy.uaa.utils;

import org.apache.commons.lang.StringUtils;

/**
 * @author qiankun.li
 * 
 */
public class UaaStringUtils extends StringUtils {

	public static String nullToString(String param, String defaultValue) {
		return isBlank(param) ? defaultValue : trim(param);
	}
	
}
