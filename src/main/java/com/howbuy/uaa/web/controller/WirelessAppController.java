/**
 * 
 */
package com.howbuy.uaa.web.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.howbuy.uaa.common.execption.UaaRunTimeException;
import com.howbuy.uaa.dto.ChannelEventAccountDto;
import com.howbuy.uaa.persistence.ChannelEventAccount;
import com.howbuy.uaa.service.ChannelEventAccountService;
import com.howbuy.uaa.utils.DateUtils;
import com.howbuy.uaa.utils.ExportExcel;
import com.howbuy.uaa.utils.FileUtil;
import com.howbuy.uaa.utils.HttpUtil;
import com.howbuy.uaa.utils.JsonParse;

/**
 * 无线数据分析
 * 
 * @author qiankun.li
 * 
 */
public class WirelessAppController extends MultiActionController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(WirelessAppController.class);

	private String url_outletcode;

	private String index;

	private String dataView;

	private String wirelessOutlet;

	private ChannelEventAccountService channelEventAccountService;

	public void init() {
		// 公募客户渠道API地址如下（渠道信息）
		try {
			if (StringUtils.isBlank(url_outletcode)) {
				throw new UaaRunTimeException(
						"init channel failed,the request url is null");
			}
			String json = HttpUtil.getHttpUtil().requestGet(url_outletcode);
			JSONObject jsonobj = JSONObject.fromObject(json);
			JSONArray disArrs = jsonobj.getJSONObject("body").getJSONArray(
					"list");
			String ds = disArrs.toString();
			wirelessOutlet = ds;
			LOGGER.debug(wirelessOutlet);
		} catch (Exception e) {
			LOGGER.error("WirelessAppController init error.", e);
		}
	}

	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView view = new ModelAndView(index);
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("wirelessOutletJson", wirelessOutlet);
		Map<String,Object> map = queryData(request, response);
		modelMap.putAll(map);
		return view.addAllObjects(modelMap);
	}

	public ModelAndView graph(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView view = new ModelAndView(dataView);
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Map<String,Object> map = queryData(request, response);
		modelMap.putAll(map);
		return view.addAllObjects(modelMap);
	}

	private Map<String,Object> queryData(HttpServletRequest request,
			HttpServletResponse response){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Calendar now = Calendar.getInstance();
		now.set(Calendar.SECOND, 0);//秒归零
		String showtime = getCurrent5Min(now);
		modelMap.put("time", showtime);
		
		String proid = request.getParameter("proid");
		String outletcode = ServletRequestUtils.getStringParameter(request,"outletcode", "-1");
		String date =  DateUtils.getFormatedDate(now.getTime(),DateUtils.FORMAT_D_YYYYMMDD);// 年月日
		//String datetime = DateUtils.getFormatedDate(now.getTime(),DateUtils.FORMAT_YYYYMMDD_HHMMSS2);// 时分秒
		String datetime = date+" "+showtime ;// 时分秒
		ChannelEventAccountDto accountDto = new ChannelEventAccountDto();
		accountDto.setOutletcode(outletcode);
		accountDto.setProid(proid);
		accountDto.setBeginTime(date);
		accountDto.setEndTime(datetime);
		
		if (StringUtils.isNotBlank(proid)) {
			List<ChannelEventAccount> result = channelEventAccountService.queryChannelEventAccountList(accountDto);
			String jsonData = JsonParse.arrayToJsonStr(result);
			modelMap.put("jsonData", jsonData);
			List<ChannelEventAccount> dataDetail = null;
			if("-1".equals(outletcode)){//查询平台汇总数据
				dataDetail = channelEventAccountService.queryChannelEventAccountListDetailiAll(accountDto);
			}else{
				dataDetail = new ArrayList<ChannelEventAccount>(result);
			}
			modelMap.put("dataDetailList", dataDetail);
		}
		// 选择所有平台、不查询网点明细
		// 获取根渠道明细数据 分安卓和iPhone数据
		List<ChannelEventAccount> resultAll = channelEventAccountService.queryChannelEventAccountListAll(accountDto);
		//modelMap.put("dataListAll", resultAll);
		String jsonDataAll = JsonParse.arrayToJsonStr(resultAll);
		modelMap.put("jsonDataAll", jsonDataAll);
		// 获取根渠道汇总数据
		ChannelEventAccount accountAll = channelEventAccountService.getDataCount(accountDto);
		modelMap.put("all", accountAll);
		
		//昨天
		Date datePre = DateUtils.addDays(now.getTime(), -1);
		String dateLast=DateUtils.getFormatedDate(datePre,DateUtils.FORMAT_D_YYYYMMDD);
		//String timePre =  DateUtils.getFormatedDate(datePre,DateUtils.FORMAT_YYYYMMDD_HHMMSS2);
		String timePre = dateLast +" "+showtime;
		accountDto.setBeginTime(dateLast);
		accountDto.setEndTime(timePre);
		ChannelEventAccount accountAllPre = channelEventAccountService.getDataCount(accountDto);
		modelMap.putAll(syncRelative(accountAll, accountAllPre));
		
		
		//是否对比其他日期数据
		String compare = ServletRequestUtils.getStringParameter(request,"compare", "");
		if(StringUtils.isNotBlank(compare)){
			String compareDate =request.getParameter("compare_date");
			Date endDateCom = DateUtils.getDate(compareDate, 1);
			String compareDateEnd  = DateUtils.getFormatedDate(endDateCom,DateUtils.FORMAT_D_YYYYMMDD);
			accountDto.setBeginTime(compareDate);
			accountDto.setEndTime(compareDateEnd);
			if (StringUtils.isNotBlank(proid)) {
				List<ChannelEventAccount> result = channelEventAccountService.queryChannelEventAccountList(accountDto);
				String jsonData = JsonParse.arrayToJsonStr(result);
				modelMap.put("jsonDataCompare", jsonData);
			}
			// 选择所有平台、不查询网点明细
			// 获取根渠道明细数据 分安卓和iPhone数据
			List<ChannelEventAccount> resultAllCompare = channelEventAccountService.queryChannelEventAccountListAll(accountDto);
			String jsonDataAllCompare = JsonParse.arrayToJsonStr(resultAllCompare);
			modelMap.put("jsonDataAllCompare", jsonDataAllCompare);
			
			//计算以每隔五分钟为间隔的数据
			Calendar compareC = Calendar.getInstance();
			compareC.setTime(DateUtils.parseDate(compareDate));
			List<String> datex24=DateUtils.getDataX24(compareC);
			String datex24Json = JsonParse.arrayToJsonStr(datex24);
			modelMap.put("dataX24Compare", datex24Json);
		}
		
		//计算以每隔五分钟为间隔的数据
		List<String> datex24=DateUtils.getDataX24(now);
		String datex24Json = JsonParse.arrayToJsonStr(datex24);
		modelMap.put("dataX24", datex24Json);
		return modelMap;
		
	}
	
	
	/**
	 * 计算环比
	 * @param accountAll
	 * @param accountAllPre
	 * @return
	 */	
	private Map<String,Object> syncRelative(ChannelEventAccount accountAll,ChannelEventAccount accountAllPre){
		if(null == accountAllPre || null == accountAll){
			return null;
		}
		 Map<String,Object> map = new HashMap<String,Object>();
		 Long act1= accountAll.getActivateNum();
		 Long act2= accountAllPre.getActivateNum();
		 if(act1!=null && null!=act2 && act2.intValue()!=0){
			 float fa = (act1.floatValue()-act2.floatValue())/act2.floatValue();
			 map.put("actR", fa);
		 }
		 
		 Long open1 = accountAll.getOpenaccNum();
		 Long open2 = accountAllPre.getOpenaccNum();
		 if(null!=open1 && null!=open2 && open2.intValue()!=0){
			 float openR = (open1.floatValue()-open2.floatValue())/open2.floatValue();
			 map.put("openR", openR);
		 }
		 
		 Long bind1 = accountAll.getBindcardNum();
		 Long bind2 = accountAllPre.getBindcardNum();
		 if(null!=bind1 && null!=bind2 && bind2.intValue()!=0){
			 float bindR = (bind1.floatValue()-bind2.floatValue())/bind2.floatValue();
			 map.put("bindR", bindR);
		 }
		 
		 Long order1 = accountAll.getOrderNum();
		 Long order2 = accountAllPre.getOrderNum();
		 if(null!=order1 && null!=order2 && order2.intValue()!=0){
			 float orderR = (order1.floatValue()-order2.floatValue())/order2.floatValue();
			 map.put("orderR", orderR);
		 }
		 return map;
	}
	
	/**
	 * 下载明细数据
	 * @param request
	 * @param response
	 */
	@SuppressWarnings("unchecked")
	public void download(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		String currentParam = request.getParameter("currentParam");
		Calendar now = Calendar.getInstance();
		now.set(Calendar.SECOND, 0);//秒归零
		String showtime = getCurrent5Min(now);
		modelMap.put("time", showtime);
		
		String proid = request.getParameter("proid");
		String outletcode = ServletRequestUtils.getStringParameter(request,"outletcode", "-1");
		String date =  DateUtils.getFormatedDate(now.getTime(),DateUtils.FORMAT_D_YYYYMMDD);// 年月日
		String datetime = date+" "+showtime ;// 时分秒
		ChannelEventAccountDto accountDto = new ChannelEventAccountDto();
		accountDto.setOutletcode(outletcode);
		accountDto.setProid(proid);
		accountDto.setBeginTime(date);
		accountDto.setEndTime(datetime);

		List<ChannelEventAccount> dataDetail =  new ArrayList<ChannelEventAccount>();
		if("-1".equals(outletcode)){//查询平台汇总数据
			dataDetail = channelEventAccountService.queryChannelEventAccountListDetailiAll(accountDto);
		}else{
			List<ChannelEventAccount> result = channelEventAccountService.queryChannelEventAccountList(accountDto);
			dataDetail = new ArrayList<ChannelEventAccount>(result);
			Collections.reverse(dataDetail);
		}
		
		
		String compare = ServletRequestUtils.getStringParameter(request,"compare", "");
		List<ChannelEventAccount> dataDetailCompare = null;
		String compareDate =request.getParameter("compare_date");
		if(StringUtils.isNotBlank(compare)){
			Date endDateCom = DateUtils.getDate(compareDate, 1);
			String compareDateEnd  = DateUtils.getFormatedDate(endDateCom,DateUtils.FORMAT_D_YYYYMMDD);
			accountDto.setBeginTime(compareDate);
			accountDto.setEndTime(compareDateEnd);
			
			if("-1".equals(outletcode)){//查询平台汇总数据
				dataDetailCompare = channelEventAccountService.queryChannelEventAccountListDetailiAll(accountDto);
			}else{
				List<ChannelEventAccount> resultCompare = channelEventAccountService.queryChannelEventAccountList(accountDto);
				dataDetailCompare = new ArrayList<ChannelEventAccount>(resultCompare);
				Collections.reverse(dataDetailCompare);
			}
		}
		Collection<ChannelEventAccount>[] dataSet = null;
		if(dataDetailCompare==null){
			dataSet = new Collection[]{dataDetail};
		}else{
			dataSet = new Collection[]{dataDetail,dataDetailCompare};
		}
		String[] heads = ExportExcel.eventHeads;
		String rootpath = request.getSession().getServletContext().getRealPath("/");
		File dataFile = new File(rootpath + "resources/data_detail.xls");
		ExportExcel<ChannelEventAccount> ex = new ExportExcel<ChannelEventAccount>();
		String[] title = new String[]{currentParam,currentParam+"_"+compareDate};
		ex.exportExcel(title, heads, heads, dataSet, dataFile, "","");
		String fileName = currentParam + ".xls";
		FileUtil.down(dataFile, fileName, response);
	}
	
	
	/**
	 * 获取最近的五分钟整点时刻
	 * 
	 * @return
	 */
	private String getCurrent5Min(Calendar now) {
		int MINUTE = now.get(Calendar.MINUTE);
		if (MINUTE % 5 != 0) {
			// 当前时间往后推移1秒
			now.set(Calendar.MINUTE, now.get(Calendar.MINUTE) - 1);
			return getCurrent5Min(now);
		} else {
			int hour = now.get(Calendar.HOUR_OF_DAY);
			String h = hour + "";
			String m = MINUTE + "";
			return (h.length() == 1 ? "0" + h : h) + ":"
					+ (m.length() == 1 ? "0" + m : m);
		}
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getUrl_outletcode() {
		return url_outletcode;
	}

	public void setUrl_outletcode(String url_outletcode) {
		this.url_outletcode = url_outletcode;
	}

	public String getWirelessOutlet() {
		return wirelessOutlet;
	}

	public void setWirelessOutlet(String wirelessOutlet) {
		this.wirelessOutlet = wirelessOutlet;
	}

	public ChannelEventAccountService getChannelEventAccountService() {
		return channelEventAccountService;
	}

	public void setChannelEventAccountService(
			ChannelEventAccountService channelEventAccountService) {
		this.channelEventAccountService = channelEventAccountService;
	}

	public String getDataView() {
		return dataView;
	}

	public void setDataView(String dataView) {
		this.dataView = dataView;
	}

}
