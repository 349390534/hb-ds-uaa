/**
 * 
 */
package com.howbuy.uaa.utils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.howbuy.uaa.common.contants.UaaContants;
import com.howbuy.uaa.dto.CustomerResponseMapping;
import com.howbuy.uaa.dto.FundResponseMapping;

/**
 * @author qiankun.li 数据帮助类
 */
public class UaaDataUtil {
	

	/**
	 * 获取时间段内所有日期
	 * @param from
	 * @param end
	 * @return
	 */
	public static List<String> getDateXList(Date from, Date end,String dateFormat) {

		List<String> dateXList = new ArrayList<String>();
		SimpleDateFormat df = new SimpleDateFormat(dateFormat);
		dateXList.add(df.format(from));
		Calendar calBegin = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		calBegin.setTime(from);
		Calendar calEnd = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		calEnd.setTime(end);
		// 判断此日期是否在指定日期之后
		while (end.after(calBegin.getTime())) {
			// 根据日历的规则，为给定的日历字段添加或减去指定的时间量
			calBegin.add(Calendar.DAY_OF_MONTH, 1);
			dateXList.add(df.format(calBegin.getTime()));
		}
		return dateXList;
	}
	/**
	 * 获取时间段内所有日期
	 * @param from
	 * @param end
	 * @param dateFormat
	 * @return
	 */
	public static List<String> getDateXMonthList(Date from, Date end,String dateFormat) {
		
		List<String> dateXList = new ArrayList<String>();
		SimpleDateFormat df = new SimpleDateFormat(dateFormat);
		dateXList.add(df.format(from));
		Calendar calBegin = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		calBegin.setTime(from);
		Calendar calEnd = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		calEnd.setTime(end);
		// 判断此日期是否在指定日期之后
		while (end.after(calBegin.getTime())) {
			// 根据日历的规则，为给定的日历字段添加或减去指定的时间量
			calBegin.add(Calendar.MONTH, 1);
			calBegin.set(Calendar.DAY_OF_MONTH, calBegin.getActualMaximum(Calendar.DAY_OF_MONTH));
			dateXList.add(df.format(calBegin.getTime()));
		}
		return dateXList;
	}
	
	/**
	 * 计算额外指标数据
	 * @param dataList
	 * @return
	 */
	public static synchronized List<FundResponseMapping> calExtraQuotaData(List<FundResponseMapping> dataList) {
		if(CollectionUtils.isEmpty(dataList)){
			return dataList;
		}
		List<FundResponseMapping> result = Collections.synchronizedList(dataList);
		if(!CollectionUtils.isEmpty(dataList)){
			for(FundResponseMapping rm:dataList){
				/*
				 当日开户绑卡率-当日开户绑卡人数/开户人数
				当日开户验卡率-当日开户验卡人数/开户人数
				当日开户鉴权率-当日开户鉴权人数/开户人数
				*/
				long drkh = rm.getDrkh();
				if(0!=drkh){
					BigDecimal drkhd =new BigDecimal(drkh);
					BigDecimal drkhdrbkd =new BigDecimal(rm.getDrkhdrbk());
					BigDecimal num =drkhdrbkd.divide(drkhd,UaaContants.SCALE_LV,BigDecimal.ROUND_HALF_UP);
					//rm.setDrkhbkl(num.setScale(UaaContants.SCALE, BigDecimal.ROUND_HALF_UP));
					rm.setDrkhbkl(num);
					
					BigDecimal drkhdrykd =new BigDecimal(rm.getDrkhdryk());
					num =drkhdrykd.divide(drkhd,UaaContants.SCALE_LV,BigDecimal.ROUND_HALF_UP);
					//rm.setDrkhykl(num.setScale(UaaContants.SCALE, BigDecimal.ROUND_HALF_UP));
					rm.setDrkhykl(num);
					
					BigDecimal drkhdrjqd =new BigDecimal(rm.getDrkhdrjq());
					num =drkhdrjqd.divide(drkhd,UaaContants.SCALE_LV,BigDecimal.ROUND_HALF_UP);
					//rm.setDrkhjql(num.setScale(UaaContants.SCALE, BigDecimal.ROUND_HALF_UP));
					rm.setDrkhjql(num);
					
					/*当日开户交易率-当日开户当日交易人数/开户人数*/
					BigDecimal drkhdrjyrsd =new BigDecimal(rm.getDrkhdrjyrs());
					num =drkhdrjyrsd.divide(drkhd,UaaContants.SCALE_LV, BigDecimal.ROUND_HALF_UP);
					//rm.setDrkhjyl(num.setScale(UaaContants.SCALE, BigDecimal.ROUND_HALF_UP));
					rm.setDrkhjyl(num);
				}
				long drxdrs = rm.getDrxdrs();
				long drqrjycjrs = rm.getDrqrjycjrs();
				/*人均下单金额-下单金额/下单人数
				 * 人均成交金额-成交金额/成交人数*/
				if(drxdrs!=0){
					rm.setRjxdje(rm.getDrxdje().divide(new BigDecimal(drxdrs),UaaContants.SCALE,BigDecimal.ROUND_HALF_UP));
				}
				if(drqrjycjrs!=0){
					rm.setRjcjje(rm.getDrqrjycjje().divide(new BigDecimal(drqrjycjrs),UaaContants.SCALE,BigDecimal.ROUND_HALF_UP));
				}
				/**人均支付金额**/
				BigDecimal drzfzje = rm.getDrzfje();
				long drzfzrs = rm.getDrzfrs();
				if(drzfzrs!=0){
					rm.setRjzfje(drzfzje.divide(new BigDecimal(drzfzrs),UaaContants.SCALE,BigDecimal.ROUND_HALF_UP));
				}
				
				/*下单转化率-下单人数/UV TODO
				成交转化率-成交人数/UV TODO */
				//rm.setXdzhl(rm.getDrxdrs()/UV);
				//rm.setXdzhl(rm.getDrqrjycjrs()/UV);
			}
		}
		
		return result;
	}
	/**
	 * 计算额外指标数据
	 * @param dataList
	 * @return
	 */
	public static synchronized List<CustomerResponseMapping> calCustExtraQuotaData(List<CustomerResponseMapping> dataList) {
		if(CollectionUtils.isEmpty(dataList)){
			return dataList;
		}
		List<CustomerResponseMapping> result = Collections.synchronizedList(dataList);
		if(!CollectionUtils.isEmpty(dataList)){
			for(CustomerResponseMapping cust:dataList){
				long zkhs = cust.getLjkhs();// 总客户数
				long ykrs = cust.getLjyks();// 总验卡人数
				long jqrs = cust.getLjjqs();//总鉴权人数
				long jyrs = cust.getLjscjys();//首次交易总人数
				if(zkhs!=0){
					if(ykrs!=0){
						BigDecimal num =new  BigDecimal((float)ykrs / zkhs);
						BigDecimal zykl = num.setScale(UaaContants.SCALE_LV, BigDecimal.ROUND_HALF_UP);
						cust.setLjykl(zykl);//总验卡率
					}
					if(jqrs!=0){
						BigDecimal num =new  BigDecimal((float)jqrs / zkhs);
						BigDecimal zjql = num.setScale(UaaContants.SCALE_LV, BigDecimal.ROUND_HALF_UP);
						cust.setLjjql(zjql);//总鉴权率
					}
					if(jyrs!=0){
						BigDecimal num =new  BigDecimal((float)jyrs / zkhs);
						BigDecimal zjyl = num.setScale(UaaContants.SCALE_LV, BigDecimal.ROUND_HALF_UP);
						cust.setLjjyl(zjyl);//总交易率
					}
				}
				if(jqrs!=0){
					if(jyrs!=0){
						BigDecimal num =new  BigDecimal((float)jyrs / jqrs);
						BigDecimal zjqjyl = num.setScale(UaaContants.SCALE_LV, BigDecimal.ROUND_HALF_UP);
						cust.setLjjqjyl(zjqjyl);//总鉴权交易率
					}
				}
			}
		}
		
		return result;
	}
 
}
