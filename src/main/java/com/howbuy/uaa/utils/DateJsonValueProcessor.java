/**
 * 
 */
package com.howbuy.uaa.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * @author qiankun.li
 *
 */
public class DateJsonValueProcessor implements JsonValueProcessor {

	
	public static final String Default_DATE_PATTERN = "yyyy-MM-dd";
	public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	SimpleDateFormat fromat = new SimpleDateFormat(Default_DATE_PATTERN, Locale.CHINA);
	SimpleDateFormat fromatYmd = new SimpleDateFormat(DATE_TIME_PATTERN, Locale.CHINA);
    
	/* (non-Javadoc)
	 * @see net.sf.json.processors.JsonValueProcessor#processArrayValue(java.lang.Object, net.sf.json.JsonConfig)
	 */
	@Override
	public Object processArrayValue(Object value, JsonConfig jsonConfig) {
		return process(value);

	}

	/* (non-Javadoc)
	 * @see net.sf.json.processors.JsonValueProcessor#processObjectValue(java.lang.String, java.lang.Object, net.sf.json.JsonConfig)
	 */
	@Override
	public Object processObjectValue(String key, Object value,JsonConfig jsonConfig) {
		return process(value);

	}
	private Object process(Object value) {
		if (value instanceof Timestamp || value instanceof Date) {
			return fromatYmd.format(value);
		}
		return value;
	}

}
