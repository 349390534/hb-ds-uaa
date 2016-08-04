package com.howbuy.uaa.core;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.Test;

import com.howbuy.uaa.command.KeyValObj;

public class UaaTester {

	@Test
	public void test1(){
		String s1 = "book";
		
		String s2 = "";
		
		String merg = s1 + "_" + s2;
		
		String[] arr = merg.split("_");
		
		System.out.println(arr[0]);
		System.out.println(arr[1] == null);
	}
	
	
	@Test
	public void test2() throws URISyntaxException{						  
		Pattern howbuy_pattern1 = 
				Pattern.compile("(?:http|https)://((?!m|mzt|zt|data).*)\\.(?:howbuy|ehowbuy).*");
		
		Pattern internal_p = Pattern.compile("(?:http|https)://.*\\.(?:howbuy|ehowbuy)");
							
//		Pattern howbuy_pattern2 = 
//				Pattern.compile("(?:http|https)://.*howbuy.*(?=\\?)");
		
		String url1 = "https://simu.howbuy.com/xinfangcheng/P07745?htag=23r23&ertwe=234";
		String url2 = "https://www.howbuy.com/video/";
		String url3 = "https://data.howbuy.com/hws/";
		String url4 = "http://m.howbuy.com?method=success";
		String url5 = "http://mzt.howbuy.com/fund/fundranking/zhaiquan.htm?";
		String url6 = "http://zt.howbuy.com/fund/fundranking/zhaiquan.htm?";
		String url7 = "https://www.ehowbuy.com/video/";

		Matcher matcher1 = howbuy_pattern1.matcher(url1);
		System.out.println("matcher1:" + matcher1.matches());
		
		Matcher matcher2 = internal_p.matcher(url7);
		System.out.println("matcher2:" + matcher2.find());

		
	}
	
	@Test
	public void test3(){
		String str = "0.0040020008900000";
		String str2 = "0.718451";
		
        Pattern p = Pattern.compile("[0-3]\\.(?:\\d{10}90{5}|\\d{1,2}80{5})");
        
        Pattern p3 = Pattern.compile("[0-3]\\.(?:\\d{6,})");
        
        String filename = "web_pv.log";
        
        Pattern fileP = Pattern.compile("^\\w+\\.log$");
        
        Pattern p2 = Pattern.compile("(\\w+)((?=/1/1/1)(/1))+");
        
//        Matcher m = fileP.matcher(filename);
//        while(m.find()){
//            System.out.println(m.group());
//        }
        
        Matcher m1 = p3.matcher(str);
        
        Matcher m2 = p3.matcher(str2);
        
        System.out.println("str:" + m1.matches());
        
        System.out.println("str2:" + m2.matches());
		
	}
	
	@Test
	public void test4(){
		Pattern p = Pattern.compile("\\d+-\\d+-\\d+-\\d+\\.[0-3]");
		
		String s = "4800000-0-0-0.4";
		
		String s2 = "0.14800000";
		
		String s3 = "0040010007900000";
		
		String regex = "[0-9]+[8]0{5}";
		
		System.out.println(p.matcher(s).matches());
		
	}
	
	
	
	@Test
	public void test5(){
		int t1 = 1421649981;
		Date d = new Date(t1);
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(t1);
		System.out.println(cal.get(Calendar.YEAR) +  "-" + cal.get(Calendar.MONTH) + "-" + cal.get(Calendar.DAY_OF_MONTH));
		System.out.println(d);
		System.out.println(System.currentTimeMillis());
	}
	
	
	@Test
	public void test6() throws MalformedURLException, UnsupportedEncodingException, URISyntaxException{
		
		String s = " https://www.haosou.com/s?ie=utf-8&src=hao_search&shb=1&hsid=0eec0a60ccc3ea45&q=%E5%A5%BD%E4%B9%B0%E5%9F%BA%E9%87%91%E7%BD%91";
//		String sdec = URLDecoder.decode(s.trim(),"utf-8");
//		System.out.println("sdec:" + sdec);
		
//		System.out.println(s.length());
//		System.out.println(s.trim().length());
		URI url1 = new URI(s.trim());
		System.out.println(url1.getHost());
		
//		URI url2 = new URI(sdec);
//		System.out.println(url2.getHost());
		
		
//		String url = "http://cn.bing.com/search?q=%E6%96%B0%E6%96%B9%E7%A8%8B+%E5%A5%BD%E4%B9%B0&go=%E6%8F%90%E4%BA%A4&qs=n&form=QBRE&pq=%E6%96%B0%E6%96%B9%E7%A8%8B+%E5%A5%BD%E4%B9%B0&sc=0-4&sp=-1&sk=&cvid=78c9d89bac5948f79132c9e96700360d";
//		URI uri = URI.create(url);
//		String host = uri.getHost();
//		System.out.println("host:" + host);
//		String path = uri.getPath();
//		System.out.println("path:" + path);
//		String hostkey = host.substring(0,host.indexOf("."));
//		
//		System.out.println("hostkey:" + hostkey);
//		String pathkey = PatternUtils.getMatchGroup(path, "/\\w+/").replace("/","");
//		System.out.println("pathkey:" + pathkey);
	}
	
	@Test
	public void test7(){
		
		List list = Arrays.asList("1","2","3","4,","5","6","7","8","9","10","11","12","13","14","15","16","17","18");
				
		int total = list.size();
		int bs = 3;
		int offset = 0;
		int times = total/bs;
		for(int i = 1; i <= times; i++){
			int toindex = bs*i;
			List sublist = list.subList(offset, toindex);
			System.out.println(sublist);
			offset = toindex;
		}
		if(offset < list.size())
			System.out.println("last:" + list.subList(offset, list.size()));
	}
	
	@Test
	public void test8(){
		String host = "http://m.howbuy.com";
		System.out.println(host.contains("baidu.com"));
		
	}
	
	@Test
	public void test9() throws UnsupportedEncodingException{
		long t = 1424284274l * 1000;
		
		long t2 = System.currentTimeMillis();
		System.out.println(t2/1000);
		System.out.println((t2+"").substring(0,(t2+"").length()-3));
		
	}
	
	@Test
	public void test10(){
		String p = "[\u4E00-\u9FA5]{2,5}(?:·[\u4E00-\u9FA5]{2,5})*";
		Pattern pt = Pattern.compile(p);
		Matcher m = pt.matcher("w98");
		System.out.println(m.matches());
	}
	
	@Test
	public void test11(){
		System.out.println(1|1|1|2|2);
	}
	
	@Test
	public void test12() throws URISyntaxException, IOException{
		
		URL url = new URL("http://192.168.220.220:8008/datax-rest/api/report/outletcode/cust");
		
		InputStream input = url.openStream();
		
		byte[] bs = new byte[1024];
		
		int len = 0;
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		while((len=input.read(bs))!=-1){
			baos.write(bs, 0, len);
		}
		
		/**
		 * list jigou key/val
		 * 		list pingtai key/val
		 * 			list hzlx	key/val
		 * 				list wd		key/val
		 * 			
		 * 
		 * 
		 */
		
		JSONObject jsonobj = JSONObject.fromObject(new String(baos.toByteArray()));
		
		JSONArray disArrs = jsonobj.getJSONObject("body").getJSONArray("list");
		
		Iterator disIter = disArrs.iterator();
		
		List<KeyValObj> dises = new ArrayList<KeyValObj>();
		
		while(disIter.hasNext()){
			
			JSONObject disjson = (JSONObject)disIter.next();
			String discode = disjson.getString("discode");
			String disname = disjson.getString("disname");
			
			KeyValObj disobj = new KeyValObj();
			disobj.setKey(discode);
			disobj.setVal(disname);
			dises.add(disobj);
			
			
			JSONArray tradechanArray = disjson.getJSONArray("chan");
			
			Iterator tradechanIter = tradechanArray.iterator();
			
			
			List<KeyValObj> tradechanslist = new ArrayList<KeyValObj>();
			disobj.setChilds(tradechanslist);
			
			while(tradechanIter.hasNext()){
				
				JSONObject tranchanjson = (JSONObject)tradechanIter.next();
				String tradechancode = tranchanjson.getString("tradechan");
				String tradechanname = tranchanjson.getString("channame");
				
				KeyValObj tradechanObj = new KeyValObj();
				tradechanObj.setKey(tradechancode);
				tradechanObj.setVal(tradechanname);
				
				tradechanslist.add(tradechanObj);
				
				List<KeyValObj> hzlxlist = new ArrayList<KeyValObj>();
				tradechanObj.setChilds(hzlxlist);
				
				JSONArray hzlxArr = tranchanjson.getJSONArray("hzlxname");
				
				Iterator hzlxIter = hzlxArr.iterator();
				
				while(hzlxIter.hasNext()){
					
					JSONObject hzlxjson = (JSONObject)hzlxIter.next();
					String hzlxcode = hzlxjson.getString("hzlxcode");
					String hzlxname = hzlxjson.getString("hzlx");
					
					KeyValObj hzlxObj = new KeyValObj();
					hzlxObj.setKey(hzlxcode);
					hzlxObj.setVal(hzlxname);
					hzlxlist.add(hzlxObj);
					
					JSONArray outletArr = hzlxjson.getJSONArray("outlet");
					
					
					Iterator outletIter = outletArr.iterator();
					
					List<KeyValObj> outletlist = new ArrayList<KeyValObj>();
					hzlxObj.setChilds(outletlist);
					
					
					while(outletIter.hasNext()){
						
						JSONObject outletjson = (JSONObject)outletIter.next();
						String outletcode = outletjson.getString("outletname");
						String outletname = outletjson.getString("outletcode");
						
						KeyValObj outletobj = new KeyValObj();
						outletobj.setKey(outletcode);
						outletobj.setVal(outletname);
						outletlist.add(outletobj);
					}
				}
			}
			
		}
		
		
		System.out.println("dises size:" + dises.size());
		System.out.println("tradechan size:" + dises.get(0).getChilds().size());
		System.out.println("hzlx size:" + dises.get(0).getChilds().get(0).getChilds().size());
		System.out.println("outlet size:" + dises.get(0).getChilds().get(0).getChilds().get(0).getChilds().get(0).getKey());
		System.out.println("outlet size:" + dises.get(0).getChilds().get(0).getChilds().get(0).getChilds().get(0).getVal());
		
		
	}
	
	@Test
	public void test13(){
		String s1 = "qwre";
		
		int i2 = 234;
		
		String s2 = null;
		
		System.out.println(s1 + s2 + i2 );
	}
	
	@Test
	public void test14() throws Exception{
		String s = "http://img4.cache.netease.com/photo/0008/2015-06-04/AR8VMH1D4GCN0008.550x.0.jpg";
		URL url = new URL(s);
		InputStream input = url.openStream();
		byte[] buf = new byte[1024];
		int len = 0;
		while((len = input.read(buf)) != -1){
			for(int i = 0;i<len;i++)
				System.out.print(buf[i] + ",");
			System.out.println();
			System.out.println("len:" + len);
//			System.out.println("len:" + len);
		}
	}
	
	@Test
	public void test15(){
		Date d = new Date();
		System.out.println(d);
		
		DateFormat df = new SimpleDateFormat(".27.yyyy-MM-dd.H");
		System.out.println("web" + df.format(d));
	}
	
	@Test
	public void test16(){
		HashMap<String,Map<String,Long>> map = 
				new HashMap<String, Map<String,Long>>();
		
		Map newmap = new HashMap<String,Long>();
		newmap.put("key1", 33l);
		map.put("ky", newmap);
		
		
		
		
//		Map map2 = new HashMap(map);
		
		Map map2 = (HashMap)map.clone();
		
		System.out.println("before map2: " + map2);
		change2(map);
		System.out.println("after: " + map2);
		
		System.out.println("after: " + map);
		
		((Map)map2.get("ky")).put("key12", 99l);
		
		System.out.println(map2);
		
		System.out.println(map);
		
		
		
		
	}
	
	public Map<String,Long> change1(Map<String,Long> map){
		long num = map.get("key1");
		num +=10;
//		map.put("key1", num);
		return map;
	}
	
	public void change2(Map map){
		
		Map<String,Long> inner =(Map<String,Long>) map.get("ky");
		
		Long num = inner.get("key1");
		
		if(num != null){
			
			 num+=11;
			 inner.put("key1", num);
		}
		
	}
	
	public Map<String,Map<String,Integer>> change(Map<String,Map<String,Integer>> map){
		Map<String,Integer> temp = map.get("channel");
		int num = temp.get("key");
		num +=10;
//		temp.put("key", num);
		return map;
	}
	
	@Test
	public void test17(){
		
		  String account = "qiankun.li";
		  String password = "!QAZ@WSX3edc";
		  String root = "OU=EC-互联网研发部,OU=电商中心,OU=staff-hk,OU=howbuy-hk,OU=howbuy,DC=howbuy,DC=domain"; // root

		  Hashtable env = new Hashtable();
		  env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		  env.put(Context.PROVIDER_URL, "ldap://192.168.230.11:389/" + root);
		  env.put(Context.SECURITY_AUTHENTICATION, "simple");
//		  env.put(Context.SECURITY_PRINCIPAL, "cn=" + account + "," + root);
		  env.put(Context.SECURITY_PRINCIPAL, account);
		  env.put(Context.SECURITY_CREDENTIALS, password);

		  DirContext ctx = null;
		  try {
		   // 链接ldap
		   ctx = new InitialDirContext(env);
		   System.out.println("ldap认证成功");

		   // 3.添加节点
//		   String newUserName = "user2";
//		   BasicAttributes attrsbu = new BasicAttributes();
//		   BasicAttribute objclassSet = new BasicAttribute("objectclass");
//		   objclassSet.add("person");
//		   objclassSet.add("top");
//		   objclassSet.add("organizationalPerson");
//		   objclassSet.add("inetOrgPerson");
//		   attrsbu.put(objclassSet);
//		   attrsbu.put("sn",   newUserName);
//		   attrsbu.put("uid",   newUserName);
//		   ctx.createSubcontext("cn=" + newUserName, attrsbu);

		   // 5.修改节点
//		   account = "user2";
//		   String newDisplayName = "newDisplayName";
//		   ModificationItem modificationItem[] = new ModificationItem[1];
//		   modificationItem[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("displayName", newDisplayName));
//		   ctx.modifyAttributes("cn=" + account, modificationItem);

		  /* // 查询节点
		   SearchControls constraints = new SearchControls();
		   constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
		   // constraints.setSearchScope(SearchControls.ONELEVEL_SCOPE);
		   NamingEnumeration en = ctx.search("", "cn=user2", constraints); // 查询所有用户
		   while (en != null && en.hasMoreElements()) {
		    Object obj = en.nextElement();
		    if (obj instanceof SearchResult) {
		     SearchResult si = (SearchResult) obj;
		     System.out.println("name:   " + si.getName());
		     Attributes attrs = si.getAttributes();
		     if (attrs == null) {
		      System.out.println("No   attributes");
		     } else {
		      for (NamingEnumeration ae = attrs.getAll(); ae.hasMoreElements();) {
		       Attribute attr = (Attribute) ae.next();
		       String attrId = attr.getID();

		       for (Enumeration vals = attr.getAll(); vals.hasMoreElements();) {
		        System.out.print(attrId + ":   ");
		        Object o = vals.nextElement();
		        if (o instanceof byte[])
		         System.out.println();// new
		               // String((byte[])o)
		        else
		         System.out.println(o);
		       }
		      }
		     }
		    } else {
		     System.out.println(obj);
		    }
		    System.out.println();
		   }
*/
		  /* // 4.删除节点
		   account = "user2";
		   ctx.destroySubcontext("cn=" + account);
*/
		  } catch (javax.naming.AuthenticationException e) {
		   System.out.println("认证失败");
		  } catch (Exception e) {
		   System.out.println("认证出错：");
		   e.printStackTrace();
		  }finally{
			  
			  if (ctx != null) {
				  try {
					  ctx.close();
				  } catch (NamingException e) {
					  // ignore
				  }
			  }
		  }
	}
	
	
	
	@Test
	public void test18() throws Exception{
		BufferedWriter bw = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream("e:/data/secondsort.txt")));
		String[] words = new String[]{"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
		
		Random random = new Random();
		
		for(int i = 1; i<=1000;i++){
			
			String line = random.nextInt(26) + " " + random.nextInt(100000);
			
			bw.write(line);
			bw.write("\n");
		}
		
		bw.close();
		
	}
	
	@Test
	public void test19() throws Exception{
		BufferedWriter bw = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream("e:/data/secondsort1.txt")));
		String[] words = new String[]{"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
		
		Random random = new Random();
		
		for(int i = 1; i<=1000;i++){
			
			String line = words[random.nextInt(26)] + "\t" + random.nextInt(100000);
			
			bw.write(line);
			bw.write("\n");
		}
		
		bw.close();
		
	}
	
}
