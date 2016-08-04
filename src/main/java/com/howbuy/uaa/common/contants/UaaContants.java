/**
 * 
 */
package com.howbuy.uaa.common.contants;

/**
 * @author qiankun.li
 * 
 */
public interface UaaContants {
	/**
	 * 好买渠道号
	 */
	static final String HB = "HB000A001";

	/**
	 * UTF-8
	 */
	static final String CHARSET_UTF8 = "UTF-8";

	/**
	 * application 文件名称
	 */
	static final String APPLICATION = "application";

	/**
	 * 获取每日客户渠道对应关系 key
	 */
	static final String KEY_GET_CHANNEL_URL = "get_channel_url";

	public static Long PV_STAT_TYPE_CHANNEL = 1L;
	
	public static Long PV_STAT_TYPE_SRC_URL = 2L;
	
	public static Long PV_STAT_TYPE_DEST_URL = 3L;
	
	static final String SUCCESS= "0";
	
	static final String FAILED= "1";
	
	/**
	 * 计算结果 保留小数的位数
	 */
	static final int SCALE= 2;
	
	/**
	 * **率的计算结果保留小数位数
	 */
	static final int SCALE_LV= 4;
	
	static final String A= "A";
	
	
	static final String GA_MAP_KEY="pageMap";
	
	static final String KEY_USERID="KEY_USERID";
	
	static final String PARAM_QUERYNO="?queryNo=%s";
	
	static final String PARAM_QUERYNON="queryNo=%s";
}
