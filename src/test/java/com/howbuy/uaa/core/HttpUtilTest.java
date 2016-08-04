/**
 * 
 */
package com.howbuy.uaa.core;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.howbuy.uaa.utils.HttpUtil;

/**
 * @author qiankun.li
 *
 */
public class HttpUtilTest {

	/**
	 * 
	 */
	public HttpUtilTest() {
		// TODO Auto-generated constructor stub
	}
	
	
	@Test
	public void testHttpUtil(){
		String url="http://10.50.50.27:8080/datax-rest/api/report/outletcode/cust";
		String s0 = HttpUtil.getHttpUtil().requestGet(url);
		String s1 = HttpUtil.getHttpUtil().requestGet(url, "a=1&b=2");
		Map<String, String> paramMap = new HashMap<String, String>();
		String sp = HttpUtil.getHttpUtil().requstPost(url,paramMap);
		System.out.println(s1);
	}

}
