package com.howbuy.uaa.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.howbuy.uaa.command.KeyValObj;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class KeyValueJson {
	public static List<KeyValObj> transition() throws URISyntaxException, IOException{
			
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
			
			return dises;
		}


}
