/**
 * 
 */
package com.howbuy.uaa.utils;

/**
 * @author qiankun.li
 *
 */
public class RouteDataUtil {


	/**
	 * 格式化qid得到对应的渠道值 
	 * 一级渠道、二级渠道三位长度，三级四位长度，qid不足位的高位补0
	 * @param level
	 * @param qid
	 * @return
	 */
	public static String formatQid(int level,int qid){
		String tag = null;
		if(level<=2){
			tag=String.format("%03d", qid);     
		}else{
			tag=String.format("%04d", qid);     
		}
		return tag;
	}
}
