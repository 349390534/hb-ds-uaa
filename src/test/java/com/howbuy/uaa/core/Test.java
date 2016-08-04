package com.howbuy.uaa.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang.time.DateUtils;

public class Test {
	private static Pattern PAGEID_PATTERN = Pattern.compile("^\\d+(1|2|3)2$");
	private static Pattern HOWBUY_DOMAIN_PATTERN = Pattern.compile("(?:http|https)://.*(?<=(howbuy|ehowbuy))\\.com.*");
	
	//资讯URL匹配
	private static Pattern HOWBUY_ZIXUN_PATTERN = Pattern.compile("(?:http|https)://.*/[0-9]{4}-[0-9]{2}-[0-9]{2}/\\d+\\.html");

	//资讯pageId匹配规则  ID+3位newstype+4位subtype+5位作者+1位pageId(3结尾)
	private static Pattern PAGEID_PATTERN_NEWS = Pattern.compile("^\\d+\\d{12}3$");
	
	private static Pattern TAG_PATTERN = Pattern.compile("^1\\d{3}[0-9]{1}$");//^[1-9]\d*$
	
	private static TestProp prop;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		System.out.println(1^1);
		
//		System.out.println(PAGEID_PATTERN.matcher("1000204596942").matches());
//		System.out.println(HOWBUY_DOMAIN_PATTERN.matcher("http://i.howbuy.com/login/login.htm").matches());
//		System.out.println(HOWBUY_DOMAIN_PATTERN.matcher("https://www.ehowbuy.com/login/login.htm").matches());
//		System.out.println(HOWBUY_ZIXUN_PATTERN.matcher("http://www.howbuy.com/news/2015-09-08/3581934.html").matches());
//		System.out.println(PAGEID_PATTERN_NEWS.matcher("35819340010215013053").matches());
		/*System.out.println(TAG_PATTERN.matcher("10000").matches());
		System.out.println(DateUtils.addMonths(new Date(), -1).toLocaleString());
		
		Iterator<String> it = null;
		List<String> list =new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		it = list.iterator();
		while (it.hasNext()) {
			String type = it.next();
//			System.out.println(type);
			System.out.println("-");
		}*/
		new TestProp().setZookeeperlist("12345678");
		new TestProp().setZookeeperlist("9876543");
		System.out.println(prop.getZookeeperlist());
	}

}
