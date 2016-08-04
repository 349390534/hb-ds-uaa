package com.howbuy.uaa.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.howbuy.uaa.dto.DisabClientConvertCustDetailDataApiResponse;
import com.howbuy.uaa.dto.PenetrateApiDataResponse;

public class ExportExcel<T> {

	private static final Logger LOGGER = Logger.getLogger(ExportExcel.class);

	private Map<String, String> normName = new HashMap<String, String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = -7627220065118870355L;

		{
			//公募数据
			put("statdt", "日期");
			put("discode", "机构代码");
			put("disname", "机构名称");
			put("tradechan", "平台代码");
			put("channame", "平台名称");
			put("hzlxcode", "合作类型代码");
			put("hzlx", "合作类型");
			put("outletcode", "网点代码");
			put("outletname", "网点名称");
			put("fundtype", "基金类型");
			put("fundtypename", "基金类型名称");
			put("drkh", "当日开户人数");
			put("drscbk", "当日首次绑卡人数");
			put("drscyk", "当日首次验卡人数");
			put("drscjq", "当日首次鉴权人数");
			put("drkhdrbk", "当日开户当日绑卡人数");
			put("drkhdryk", "当日开户当日验卡人数");
			put("drkhdrjq", "当日开户当日鉴权人数");
			put("drxzjyrs", "当日新增交易人数");
			put("drxzjyje", "当日新增交易金额");
			put("drkhdrjybs", "当日开户当日交易笔数");
			put("drkhdrjyje", "当日开户当日交易金额");
			put("drxdbs", "单日下单笔数");
			put("drxdje", "单日下单金额");
			put("drqrjycjbs", "当日确认交易的成交笔数");
			put("drqrjycjje", "当日确认交易的成交金额");
			put("drkhdrjyrs", "当日开户当日交易人数");
			put("drxdrs", "当日下单人数");
			put("drqrjycjrs", "当日确认交易的成交人数");
			put("rjxdje", "人均下单金额");
			put("rjcjje", "人均成交金额");
			put("drkhbkl", "当日开户绑卡率");
			put("drkhykl", "当日开户验卡率");
			put("drkhjql", "当日开户鉴权率");
			put("drkhjyl", "当日开户交易率");
			put("xdzhl", "下单转化率");
			put("cjzhl", "成交转化率");
			put("fundName", "基金类型");
			put("zkh", "总开户数");
			put("sczbk", "首次总绑卡数");
			put("sczyk", "首次总验卡数");
			put("sczjq", "首次总鉴权数");
			put("drkhdrzbk", "当日开户当日总绑卡数");
			put("drkhdrzyk", "当日开户当日总验卡数");
			put("drkhdrzjq", "当日开户当日总鉴权数");
			put("xzjyzrs", "新增交易总人数");
			put("xzjyzje", "新增交易总金额");
			put("drkhdrjyzbs", "当日开户当日交易总笔数");
			put("drkhdrjyzje", "当日开户当日交易总金额");
			put("xdzbs", "下单总笔数");
			put("xdzje", "下单总金额");
			put("qrjycjzbs", "确认交易成交总笔数");
			put("qrjycjzje", "确认交易成交总金额");
			put("drkhdrjyzrs", "当日开户当日交易总人数");
			put("xdzrs", "下单总人数");
			put("qrjycjzrs", "确认交易成交总人数");
			put("rjxdzje", "人均下单总金额");
			put("rjcjzje", "人均成交总金额");
			put("drkhzbkl", "当日开户总绑卡率");
			put("drkhzykl", "当日开户总验卡率");
			put("drkhzjql", "当日开户总鉴权率");
			put("drkhzjyl", "当日开户总交易率");
			put("xdzzhl", "下单总转化率");
			put("cjzzhl", "成交总转化率");
			put("childname", "名称");
			put("zzfrs", "支付总人数");
			put("zzfbs", "支付总笔数");
			put("zzfje", "支付总金额");
			put("rjzzfje", "人均支付总金额");
			put("drzfrs", "支付人数");
			put("drzfbs", "支付笔数");
			put("drzfje", "支付金额");
			put("rjzfje", "人均支付金额");
			
			
			//访问渠道数据
			put("channelName", "渠道名称");
			put("dt", "日期");
			put("enter", "进入次数");
			put("pv", "PV");
			put("uv", "UV");
			put("validuv", "有效UV");
			put("gmuv", "公募基金档案页UV");
			put("simuuv", "高端详情页UV");
			put("drkh", "开户人数");
			put("drbk", "绑卡人数");
			put("persons", "下单人数");
			put("bills", "下单笔数");
			put("amt", "下单金额");
			put("xdzhl", "下单转化率");
			put("drxdcjrs", "成交人数");
			put("drxdcjbs", "成交笔数");
			put("drxdcjje", "成交金额");
			put("cjzhl", "成交转化率");
			
			put("ljzkhs","总客户数");
			put("ljzbks","总绑卡人数");
			put("ljzyks","总验卡人数");
			put("ljzjqs","总鉴权人数");
			put("ljscjyzs","首次交易总人数");
			put("zcys","总持有人数");
			put("ljzykl","总验卡率");
			put("ljzjql","总鉴权率");
			put("ljzjyl","总交易率");
			put("ljzjqjyl","总鉴权交易率");
			
			put("ljkhs","总客户数");
			put("ljbks","总绑卡人数");
			put("ljyks","总验卡人数");
			put("ljjqs","总鉴权人数");
			put("ljscjys","首次交易总人数");
			put("cys","总持有人数");
			put("ljykl","总验卡率");
			put("ljjql","总鉴权率");
			put("ljjyl","总交易率");
			put("ljjqjyl","总鉴权交易率");
			
			
			put("activateNum","访问次数");
			put("openaccNum","开户人数");
			put("bindcardNum","绑卡人数");
			put("orderNum","下单人数");
			put("orderAmount","下单金额");
			put("createTime","时间");
			
			/*无线活动*/
			put("statdt","时间");
			put("enter","进入次数");
			put("pv","总PV");
			put("hongbaoIndexUv","领红包首页UV");
			put("h5OpenAccIndexPageUv","H5开户首页UV");
			put("authPageUv","身份验证页UV");
			put("h5OpenAccResultPageUv","H5开户结果页UV");
			put("openaccNum","开户人数");
			
			//""","","cnt1","cnt2","cnt3","cnt4","cnt5","cnt123","pct123","cnt45","pct45"
			put("consname","投顾姓名");
			put("ttl_cnt","总数");
			put("cnt0","0分客户数");
			put("cnt1","1分客户数");
			put("cnt2","2分客户数");
			put("cnt3","3分客户数");
			put("cnt4","4分客户数");
			put("cnt5","5分客户数");
			put("cnt123","1-3分客户数");
			put("pct123","1-3分客户占比");
			put("cnt45","4-5分客户数");
			put("pct45","4-5分客户占比");
			
			
			//销量数据
			put("TRADE_DT","日期");
			put("FUND_TYPE_NAME","基金类型");
			put("MARKET_AMT","存量金额");
			put("APP_AMT","申购金额(下单)");
			put("SELL_APP_VOL","赎回份额(下单)");
			put("ACK_AMT","申购金额(确认)");
			put("SELL_ACK_AMT","赎回金额(确认)");
			put("NET_ACK_AMT","净申购金额(确认)");
			put("FEE_RATE","费率");
			put("INCOME","收入");
			put("CAPITAL","成本");
			put("RAISE_FEE","认购费收入");
			put("SUBS_FEE","申购费收入");
			put("REDE_FEE","赎回费收入");
			put("AGENT_SVC_FEE","销售服务费收入");
			put("SVC_FEE","尾随收入");
			put("OTHER_FEE","额外营销费用");
			
			put("FUND_NAME","基金名称");
			put("TA_NAME","基金公司名称");
			
		}
	};
	

	/**
	 * 指标计算指标 是否使用百分比符合，1是，0否
	 */
	private static final HashMap<String, Integer> quatoCalMap = new HashMap<String, Integer>(0);
	static {
		quatoCalMap.put("drkhbkl", 1);//当日开户绑卡率
		quatoCalMap.put("drkhzbkl", 1);//当日开户总绑卡率
		quatoCalMap.put("drkhykl", 1);//当日开户验卡率
		quatoCalMap.put("drkhzykl", 1);//当日开户总验卡率
		quatoCalMap.put("drkhjql", 1);//当日开户鉴权率
		quatoCalMap.put("drkhzjql", 1);//当日开户总鉴权率
		quatoCalMap.put("drkhjyl", 1);//当日开户交易率
		quatoCalMap.put("drkhzjyl", 1);//当日开户总交易率
		quatoCalMap.put("xdzhl", 1);//下单转化率
		quatoCalMap.put("cjzhl", 1);//成交转化率
		
		quatoCalMap.put("ljzykl", 1);//总验卡率		
		quatoCalMap.put("ljzjql", 1);//总鉴权率
		quatoCalMap.put("ljzjyl", 1);//总交易率
		quatoCalMap.put("ljzjqjyl", 1);//总鉴权交易率
		
		quatoCalMap.put("ljykl", 1);//总验卡率		
		quatoCalMap.put("ljjql", 1);//总鉴权率
		quatoCalMap.put("ljjyl", 1);//总交易率
		quatoCalMap.put("ljjqjyl", 1);//总鉴权交易率
		
		quatoCalMap.put("pct123", 1);//1-3分客户占比
		quatoCalMap.put("pct45", 1);//4-5分客户占比
		
		//基金销量
		quatoCalMap.put("FEE_RATE", 1);//费率
		
	}
	
	/**
	 * 指标计算指标 是否除以100得到原始数据，1是，0否
	 */
	private static final HashMap<String, Integer> quatoDiv100 = new HashMap<String, Integer>(0);
	{
		quatoDiv100.put("pct123", 1);//1-3分客户占比
		quatoDiv100.put("pct45", 1);//4-5分客户占比
	}
	
	/**
	 * 流量指标名称
	 */
	public static String[] channelHeads = {"channelName","enter","pv","uv","validuv","gmuv","simuuv","drkh",
		       "drbk","persons","bills","amt","xdzhl","drxdcjrs","drxdcjbs","drxdcjje","cjzhl"};
	public static String[] trendHeads = {"dt","enter","pv","uv","validuv","gmuv","simuuv","drkh",
		"drbk","persons","bills","amt","xdzhl","drxdcjrs","drxdcjbs","drxdcjje","cjzhl"};
	
	public static String[] eventHeads = {"createTime","activateNum","openaccNum","bindcardNum","orderNum","orderAmount"};
	public static String[] activityHeads = {"statdt","enter","pv","uv","hongbaoIndexUv","h5OpenAccIndexPageUv","authPageUv","h5OpenAccResultPageUv","openaccNum"};
	
	public static String[] gradeHeads = {"consname","ttl_cnt","cnt0","cnt1","cnt2","cnt3","cnt4","cnt5","cnt123","pct123","cnt45","pct45"};

	//基金销量数据
	public static String[] fundsaleFundTypeHeads = {"TRADE_DT","FUND_TYPE_NAME","MARKET_AMT","APP_AMT","SELL_APP_VOL","ACK_AMT","SELL_ACK_AMT","NET_ACK_AMT","FEE_RATE"};
	public static String[] fundsaleFundTypeHeadsIncome = {"TRADE_DT","FUND_TYPE_NAME","INCOME","CAPITAL","RAISE_FEE","SUBS_FEE","REDE_FEE","SVC_FEE","AGENT_SVC_FEE","OTHER_FEE"};
	public static String[] fundsaleFundHeads = {"FUND_NAME","TA_NAME","FUND_TYPE_NAME","MARKET_AMT","APP_AMT","SELL_APP_VOL","ACK_AMT","SELL_ACK_AMT","NET_ACK_AMT","FEE_RATE"};
	public static String[] fundsaleFundHeadsIncome = {"FUND_NAME","TA_NAME","FUND_TYPE_NAME","INCOME","CAPITAL","RAISE_FEE","SUBS_FEE","REDE_FEE","AGENT_SVC_FEE","SVC_FEE","OTHER_FEE"};
	
	
	public void exportExcel(String title, String[] headers, String[] limit,
			Collection<T> dataset, File file,String tag) {
		exportExcel(title, headers, limit, dataset, file, DateUtils.FORMAT_D_YYYYMMDD,tag);
	}

	public void exportExcel(String title, String[] headers, String[] limit,
			Collection<T> dataset, File file, String pattern,String tag) {
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet(title);
		// 设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth((short) 15);
		// 生成一个样式 
		HSSFCellStyle style = workbook.createCellStyle();
		style.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0"));
		
		HSSFCellStyle styleFloat = workbook.createCellStyle();
		styleFloat.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));
		// 生成一个字体
		HSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short) 12);
		// 把字体应用到当前的样式
		style.setFont(font);
		styleFloat.setFont(font);
		//百分比的格式
		HSSFCellStyle builtinFormatCellStyle = workbook.createCellStyle();
		builtinFormatCellStyle.setFont(font);
		builtinFormatCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00%"));
		// 产生表格标题行
		HSSFRow row = sheet.createRow(0);
		for (short i = 0; i < headers.length; i++) {
			String header = headers[i];
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(style);
			String titleStr = normName.get(header).toString();
			if(i==0){
				if(StringUtils.isNotBlank(tag)){
					titleStr = tag ;
				}
			}
			HSSFRichTextString text = new HSSFRichTextString(titleStr);
			cell.setCellValue(text);
		}
		// 遍历集合数据，产生数据行
		Iterator<T> it = dataset.iterator();
		int index = 0;
		while (it.hasNext()) {
			index++;
			row = sheet.createRow(index);
			T t = (T) it.next();
			// 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
			// Field[] fields = t.getClass().getDeclaredFields();
			// for (short i = 0, j = 0; i < fields.length; i++) {
			for (short i = 0, j = 0; i < limit.length; i++) {
				// Field field = fields[i];
				// String fieldName = field.getName();
				String fieldName = limit[i];

				HSSFCell cell = row.createCell(j++);
				cell.setCellStyle(style);
				String getMethodName = "get"
						+ fieldName.substring(0, 1).toUpperCase()
						+ fieldName.substring(1);
				try {
					Class<?> tCls = t.getClass();
					Method getMethod = tCls.getMethod(getMethodName,new Class[] {});
					Object value = getMethod.invoke(t, new Object[] {});
					if(null ==value ){
						if(null!=quatoCalMap.get(fieldName) 
								&& quatoCalMap.get(fieldName)==1){
							value=new BigDecimal(0);
						}else{
							value=0;
						}
					}
					// 判断值的类型后进行强制类型转换
					String textValue = null;
					if (value instanceof Integer) {
						int intValue = (Integer) value;
						cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
						cell.setCellValue(intValue);
					} else if (value instanceof Long) {
						long longValue = (Long) value;
						cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
						cell.setCellValue(longValue);
					} else if (value instanceof BigDecimal) {
						BigDecimal bigValue = new BigDecimal(value.toString());
						cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
						cell.setCellValue(bigValue.doubleValue());
						cell.setCellStyle(styleFloat);
						if(null!=quatoCalMap.get(fieldName) 
								&& quatoCalMap.get(fieldName)==1){
							cell.setCellStyle(builtinFormatCellStyle);
							if(null!=quatoDiv100.get(fieldName) && quatoDiv100.get(fieldName)==1){
								cell.setCellValue(bigValue.divide(new BigDecimal(100)).doubleValue());
							}
						}
					}  else {
						Date date = null;
						String str = value.toString();
						if(NumberUtils.isNumber(str)){
							date =DateUtils.parseDate(str, DateUtils.FORMAT_YYYYMMDD);
							if(null!=date){//判断是否可转换为日期
								textValue = DateUtils.getFormatedDate(date,  DateUtils.FORMAT_D_YYYYMMDD);
							}
						}else if (value != null) {		// 其它数据类型都当作字符串简单处理
							textValue = value.toString();
						}

					}

					if (StringUtils.isNotBlank(textValue)) {
						Pattern p = Pattern.compile("^//d+(//.//d+)?$");
						Matcher matcher = p.matcher(textValue);
						if (matcher.matches()) {
							// 是数字当作double处理
							cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
							cell.setCellValue(Double.parseDouble(textValue));
						} else {
							HSSFRichTextString richString = new HSSFRichTextString(textValue);
							richString.applyFont(font);
							cell.setCellValue(richString);
						}
					}
				} catch (SecurityException e) {
					LOGGER.error(e);
				} catch (NoSuchMethodException e) {
					LOGGER.error(e);
				} catch (IllegalArgumentException e) {
					LOGGER.error(e);
				} catch (IllegalAccessException e) {
					LOGGER.error(e);
				} catch (InvocationTargetException e) {
					LOGGER.error(e);
				} finally {
					// 清理资源
				}
			}
		}
		BufferedOutputStream out = null;
		try {
			out = new BufferedOutputStream(new FileOutputStream(file));
			workbook.write(out);
		} catch (IOException e) {
			LOGGER.error(e);
		} finally {
			if (null != out) {
				try {
					out.flush();
					out.close();
				} catch (IOException e) {
					LOGGER.error(e);
				}
			}
		}
	}
	
	/**同时下载多个具有相同属性的数据列表
	 * @param title
	 * @param headers
	 * @param limit
	 * @param dataset
	 * @param file
	 * @param pattern
	 * @param tag
	 */
	public void exportExcel(String[] title, String[] headers, String[] limit,
			Collection<T>[] dataset, File file, String pattern,String tag) {
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		if(null!=dataset){
			int sheetIndex = 0;
			for(Collection<T> data:dataset){
				// 生成一个表格
				HSSFSheet sheet = workbook.createSheet(title[sheetIndex++]);
				// 设置表格默认列宽度为15个字节
				sheet.setDefaultColumnWidth((short) 15);
				// 生成一个样式 
				HSSFCellStyle style = workbook.createCellStyle();
				style.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0"));
				
				HSSFCellStyle styleFloat = workbook.createCellStyle();
				styleFloat.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));
				// 生成一个字体
				HSSFFont font = workbook.createFont();
				font.setFontHeightInPoints((short) 12);
				// 把字体应用到当前的样式
				style.setFont(font);
				styleFloat.setFont(font);
				//百分比的格式
				HSSFCellStyle builtinFormatCellStyle = workbook.createCellStyle();
				builtinFormatCellStyle.setFont(font);
				builtinFormatCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00%"));
				// 产生表格标题行
				HSSFRow row = sheet.createRow(0);
				for (short i = 0; i < headers.length; i++) {
					String header = headers[i];
					HSSFCell cell = row.createCell(i);
					cell.setCellStyle(style);
					String titleStr = normName.get(header).toString();
					if(i==0){
						if(StringUtils.isNotBlank(tag)){
							titleStr = tag ;
						}
					}
					HSSFRichTextString text = new HSSFRichTextString(titleStr);
					cell.setCellValue(text);
				}
				// 遍历集合数据，产生数据行
				Iterator<T> it = data.iterator();
				int index = 0;
				while (it.hasNext()) {
					index++;
					row = sheet.createRow(index);
					T t = (T) it.next();
					// 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
					// Field[] fields = t.getClass().getDeclaredFields();
					// for (short i = 0, j = 0; i < fields.length; i++) {
					for (short i = 0, j = 0; i < limit.length; i++) {
						// Field field = fields[i];
						// String fieldName = field.getName();
						String fieldName = limit[i];
						
						HSSFCell cell = row.createCell(j++);
						cell.setCellStyle(style);
						String getMethodName = "get"
								+ fieldName.substring(0, 1).toUpperCase()
								+ fieldName.substring(1);
						try {
							Class<?> tCls = t.getClass();
							Method getMethod = tCls.getMethod(getMethodName,new Class[] {});
							Object value = getMethod.invoke(t, new Object[] {});
							if(null ==value ){
								if(null!=quatoCalMap.get(fieldName) 
										&& quatoCalMap.get(fieldName)==1){
									value=new BigDecimal(0);
								}else{
									value=0;
								}
							}
							// 判断值的类型后进行强制类型转换
							String textValue = null;
							if (value instanceof Integer) {
								int intValue = (Integer) value;
								cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
								cell.setCellValue(intValue);
							} else if (value instanceof Long) {
								long longValue = (Long) value;
								cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
								cell.setCellValue(longValue);
							} else if (value instanceof BigDecimal) {
								BigDecimal bigValue = new BigDecimal(value.toString());
								cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
								cell.setCellValue(bigValue.doubleValue());
								cell.setCellStyle(styleFloat);
								if(null!=quatoCalMap.get(fieldName) 
										&& quatoCalMap.get(fieldName)==1){
									cell.setCellStyle(builtinFormatCellStyle);
								}
							}  else {
								Date date = null;
								String str = value.toString();
								if(NumberUtils.isNumber(str)){
									date =DateUtils.parseDate(str, DateUtils.FORMAT_YYYYMMDD);
									if(null!=date){//判断是否可转换为日期
										textValue = DateUtils.getFormatedDate(date,  DateUtils.FORMAT_D_YYYYMMDD);
									}
								}else if (value != null) {		// 其它数据类型都当作字符串简单处理
									textValue = value.toString();
								}
								
							}
							
							if (StringUtils.isNotBlank(textValue)) {
								Pattern p = Pattern.compile("^//d+(//.//d+)?$");
								Matcher matcher = p.matcher(textValue);
								if (matcher.matches()) {
									// 是数字当作double处理
									cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
									cell.setCellValue(Double.parseDouble(textValue));
								} else {
									HSSFRichTextString richString = new HSSFRichTextString(textValue);
									richString.applyFont(font);
									cell.setCellValue(richString);
								}
							}
						} catch (SecurityException e) {
							LOGGER.error(e);
						} catch (NoSuchMethodException e) {
							LOGGER.error(e);
						} catch (IllegalArgumentException e) {
							LOGGER.error(e);
						} catch (IllegalAccessException e) {
							LOGGER.error(e);
						} catch (InvocationTargetException e) {
							LOGGER.error(e);
						} finally {
							// 清理资源
						}
					}
				}
				
			}
		}
		
		BufferedOutputStream out = null;
		try {
			out = new BufferedOutputStream(new FileOutputStream(file));
			workbook.write(out);
		} catch (IOException e) {
			LOGGER.error(e);
		} finally {
			if (null != out) {
				try {
					out.flush();
					out.close();
				} catch (IOException e) {
					LOGGER.error(e);
				}
			}
		}
	}
	/**
	 * 导出高端用户数据
	 * @param title
	 * @param headers
	 * @param dataset
	 * @param file
	 * @throws IOException 
	 */
	public void exportPenetrateExcel(String title,List<PenetrateApiDataResponse> dataset, File file) throws IOException {
		// 声明一个工作薄
		FileInputStream inputStream = new FileInputStream(file);
		HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
		// 生成一个表格
		workbook.setSheetName(0, title);
		HSSFSheet sheet = workbook.getSheetAt(0);
		// 设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth((short) 15);
		// 生成一个样式 
		HSSFCellStyle style = workbook.createCellStyle();
		style.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0"));
		
		HSSFCellStyle styleFloat = workbook.createCellStyle();
		styleFloat.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));
		// 生成一个字体
		HSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short) 12);
		// 把字体应用到当前的样式
		style.setFont(font);
		styleFloat.setFont(font);
		//百分比的格式
		HSSFCellStyle builtinFormatCellStyle = workbook.createCellStyle();
		builtinFormatCellStyle.setFont(font);
		builtinFormatCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00%"));
		int index = 0;
		while(true){
			HSSFRow rowT = sheet.getRow(index++);
			if(null == rowT ||  index==4){
				break;//最多只判断到第四行 是否有标题数据
			}
		}
		// 遍历集合数据，产生数据行
		for (short i = 0; i < dataset.size(); i++) {
			PenetrateApiDataResponse data = dataset.get(i);
			HSSFRow row = sheet.createRow(index-1);
			index++;
			//日期
			HSSFCell cell0 = row.createCell((short)0);
			cell0.setCellStyle(style);
			HSSFRichTextString richString = new HSSFRichTextString(data.getStatdt());
			richString.applyFont(font);
			cell0.setCellValue(richString);
			//分配部门
			HSSFCell cell1 = row.createCell((short)1);
			cell1.setCellStyle(style);
			HSSFRichTextString richString1 = new HSSFRichTextString(data.getDepartcode());
			richString1.applyFont(font);
			cell1.setCellValue(richString1);
			//B
			HSSFCell cell2 = row.createCell((short)2);
			cell2.setCellStyle(style);
			cell2.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cell2.setCellValue(data.getCnt_b());
			//C
			HSSFCell cell3 = row.createCell((short)3);
			cell3.setCellStyle(style);
			cell3.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cell3.setCellValue(data.getCnt_c());
			//D
			HSSFCell cell4 = row.createCell((short)4);
			cell4.setCellStyle(style);
			cell4.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cell4.setCellValue(data.getCnt_d());
			//E
			HSSFCell cell5 = row.createCell((short)5);
			cell5.setCellStyle(style);
			cell5.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cell5.setCellValue(data.getCnt_e());
			//F
			HSSFCell cell6 = row.createCell((short)6);
			cell6.setCellStyle(style);
			cell6.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cell6.setCellValue(data.getCnt_f());
			
			//C∩E
			HSSFCell cell7 = row.createCell((short)7);
			cell7.setCellStyle(style);
			cell7.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cell7.setCellValue(data.getCnt_c_e());
			
			//(C∩E)/C
			HSSFCell cell8 = row.createCell((short)8);
			BigDecimal ce = new BigDecimal(data.getCnt_c_e());
			BigDecimal c = new BigDecimal(data.getCnt_c());
			String v8 =ce.multiply(new BigDecimal(100)).divide(c,4, BigDecimal.ROUND_HALF_DOWN).toString() +"%";
			cell8.setCellStyle(style);
			cell8.setCellValue(new HSSFRichTextString(v8));
			
			//F/B
			BigDecimal f = new BigDecimal(data.getCnt_f());
			BigDecimal b = new BigDecimal(data.getCnt_b());
			HSSFCell cell9 = row.createCell((short)9);
			cell9.setCellStyle(style);
			String v9 =f.multiply(new BigDecimal(100)).divide(b,4, BigDecimal.ROUND_HALF_DOWN).toString() +"%";
			cell9.setCellValue(new HSSFRichTextString(v9));
			
			//E∩F
			HSSFCell cell10 = row.createCell((short)10);
			cell10.setCellStyle(style);
			cell10.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cell10.setCellValue(data.getCnt_e_f());
			//(E∩F)/B
			HSSFCell cell11 = row.createCell((short)11);
			cell11.setCellStyle(builtinFormatCellStyle);
			cell11.setCellValue(new HSSFRichTextString(data.getE_f2b()));
			
			//D∩F
			HSSFCell cell12 = row.createCell((short)12);
			cell12.setCellStyle(style);
			cell12.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cell12.setCellValue(data.getCnt_d_f());
			
			//(D∩F)/B
			HSSFCell cell13 = row.createCell((short)13);
			cell13.setCellStyle(builtinFormatCellStyle);
			cell13.setCellValue(new HSSFRichTextString(data.getD_f2b()));
		}
		BufferedOutputStream out = null;
		try {
			out = new BufferedOutputStream(new FileOutputStream(file));
			workbook.write(out);
		} catch (IOException e) {
			LOGGER.error(e);
		} finally {
			if (null != out) {
				try {
					out.flush();
					out.close();
				} catch (IOException e) {
					LOGGER.error(e);
				}
			}
		}
	}
	/**
	 * 导出高端用户数据
	 * @param title
	 * @param headers
	 * @param dataset
	 * @param file
	 * @throws IOException 
	 */
	public void exportDisabClientConvertExcel(String title,List<DisabClientConvertCustDetailDataApiResponse> dataset, File file) throws IOException {
		// 声明一个工作薄
		FileInputStream inputStream = new FileInputStream(file);
		HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
		// 生成一个表格
		workbook.setSheetName(0, title);
		HSSFSheet sheet = workbook.getSheetAt(0);
		// 设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth((short) 15);
		// 生成一个样式 
		HSSFCellStyle style = workbook.createCellStyle();
		style.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0"));
		
		HSSFCellStyle styleFloat = workbook.createCellStyle();
		styleFloat.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));
		// 生成一个字体
		HSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short) 12);
		// 把字体应用到当前的样式
		style.setFont(font);
		styleFloat.setFont(font);
		//百分比的格式
		HSSFCellStyle builtinFormatCellStyle = workbook.createCellStyle();
		builtinFormatCellStyle.setFont(font);
		builtinFormatCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00%"));
		int index = 0;
		while(true){
			HSSFRow rowT = sheet.getRow(index++);
			if(null == rowT ||  index==4){
				break;//最多只判断到第四行 是否有标题数据
			}
		}
		// 遍历集合数据，产生数据行
		for (short i = 0; i < dataset.size(); i++) {
			DisabClientConvertCustDetailDataApiResponse data = dataset.get(i);
			HSSFRow row = sheet.createRow(index-1);
			index++;
			//投顾姓名
			HSSFCell cell0 = row.createCell((short)0);
			cell0.setCellStyle(style);
			HSSFRichTextString richString = new HSSFRichTextString(data.getConsname());
			richString.applyFont(font);
			cell0.setCellValue(richString);
			//一手客户-分配客户数
			HSSFCell cell1 = row.createCell((short)1);
			cell1.setCellStyle(style);
			cell1.setCellValue(data.getAssign_cnt1());
			//一手客户-成交客户数
			HSSFCell cell2 = row.createCell((short)2);
			cell2.setCellStyle(style);
			cell2.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cell2.setCellValue(data.getTrade_cnt1());
			//一手客户-成交比
			HSSFCell cell3 = row.createCell((short)3);
			cell3.setCellStyle(builtinFormatCellStyle);
			cell3.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cell3.setCellValue(data.getTrade_pct1()/100);
			
			//二手潜在客户-分配客户数
			HSSFCell cell4 = row.createCell((short)4);
			cell4.setCellStyle(style);
			cell4.setCellValue(data.getAssign_cnt2());
			
			//二手潜在客户-成交客户数
			HSSFCell cell5 = row.createCell((short)5);
			cell5.setCellStyle(style);
			cell5.setCellValue(data.getTrade_cnt2());
			
			//二手潜在客户-成交比
			HSSFCell cell6 = row.createCell((short)6);
			cell6.setCellStyle(builtinFormatCellStyle);
			cell6.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cell6.setCellValue(data.getTrade_pct2()/100);
			
			
			//二手潜在客户-分配客户数
			HSSFCell cell7 = row.createCell((short)7);
			cell7.setCellStyle(style);
			cell7.setCellValue(data.getAssign_cnt3());
			
			//二手潜在客户-成交客户数
			HSSFCell cell8 = row.createCell((short)8);
			cell8.setCellStyle(style);
			cell8.setCellValue(data.getTrade_cnt3());
			
			//二手潜在客户-成交比
			HSSFCell cell9 = row.createCell((short)9);
			cell9.setCellStyle(builtinFormatCellStyle);
			cell9.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cell9.setCellValue(data.getTrade_pct3()/100);
			 
		}
		BufferedOutputStream out = null;
		try {
			out = new BufferedOutputStream(new FileOutputStream(file));
			workbook.write(out);
		} catch (IOException e) {
			LOGGER.error(e);
		} finally {
			if (null != out) {
				try {
					out.flush();
					out.close();
				} catch (IOException e) {
					LOGGER.error(e);
				}
			}
		}
	}

	public boolean hasIt(String[] arr, String targetValue) {
		for (String s : arr) {
			if (s.equals(targetValue)) {
				return true;
			}
		}
		return false;
	}
}
