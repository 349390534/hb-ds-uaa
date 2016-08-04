package com.howbuy.uaa.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * <pre>
 *  时间帮助类
 * </pre>
 *
 * @author ji.ma
 * @create 13-3-15 上午10:23
 * @modify
 * @since JDK1.6
 */
public class DateUtils extends org.apache.commons.lang.time.DateUtils{
    
    public static final String FORMAT_YYYYMMDD="yyyyMMdd";
    
    public static final String FORMAT_YYYYMMDD_HHMMSS="yyyyMMddHHmmss";
    
    public static final String FORMAT_D_YYYYMMDD="yyyy-MM-dd";
    
    public static final String FORMAT_YYYYMMDD_HHMMSS2="yyyy-MM-dd HH:mm:ss";
    
    public static final String FORMAT_HHmmss="HH:mm:ss";
    
    
    

    /**
     * 如果日期为空，返回昨天
     * @param date   昨天
     * @return   .
     */
    public static String getDefaultDateIfEmpty(String date) {
        if (StringUtils.isEmpty(date)) {
            date =  DateUtils.getFormatedDate(getYesterdayDate());
        }

        return date;
    }

    public static String getDefaultMonthIfEmpty(String month) {
        if (StringUtils.isEmpty(month)) {
            month =  DateUtils.getFormatedDate(getYesterdayDate(),"yyyy-MM");
        }

        return month;
    }

    public static String getDateFromTime(String time) {
        String date = time;
        if (StringUtils.isEmpty(time) || date.length() < 10) {
            date = DateUtils.getFormatedDate(getYesterdayDate());
        } else if (date.length() > 10) {
            date = date.substring(0, 10);
        }

        return date;
    }

    public static Date getYesterdayDate() {
        return DateUtils.addDays(new Date(), -1);
    }

    public static String currentDate() {
        Date currentDate = new Date();

        return getFormatedDate(currentDate);
    }

    public static String getFormatedDate(Date date, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }

    public static Date parseDate(String date, String format) {
        try {
            return parseDate(date,new String[] {format});
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Date parseDate(String s) {
        return parseDate(s,"yyyy-MM-dd");
    }

    public static String getFormatedDate(Date date) {
        return getFormatedDate(date, "yyyy-MM-dd");
    }
    
    
    /**
     * 获取 fromDay 前后interval的日期
     * @param fromDay
     * @return
     */
    public static Date getDate(String fromDay,int interval) {
        return DateUtils.addDays(parseDate(fromDay), interval);
    }

    /**
     * 计算两个日期之间相差的天数，不足1天的时间将舍去
     *
     * @param d1 被减数
     * @param d2 减数
     * @return d1-d2 时间相差的天数
     */
    public static int minus(Date d1, Date d2) {
        return (int) Math.floor((d1.getTime() - d2.getTime()) / 1000 / 60 / 60 / 24);
    }
    
    /**
     * 返回日期的最后一天
     * @param date
     * @param format
     * @return
     */
    public static String getLastDay(Date date,String format){
    	String endDate = "";
    	if(null!=date){
    		Calendar c = Calendar.getInstance();
    		c.setTime(date);
    		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
    		return getFormatedDate(c.getTime(), format);
    	}
    	return endDate;
    }
    /**
     * 返回日期的最后一天
     * @param date
     * @param format
     * @return
     */
    public static String getLastDay(String date,String format){
    	String endDate = "";
    	Date dateVar = parseDate(date,format);
    	if(null!=date){
    		Calendar c = Calendar.getInstance();
    		c.setTime(dateVar);
    		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
    		return getFormatedDate(c.getTime(), format);
    	}
    	return endDate;
    }
    
    /**
	 * 获取结束日期前一个月的日期字符串
	 * @param end
	 * @return
	 */
	public static String getEndDate2BeforDateStr(String end,String format){
		Date endDate =  com.howbuy.uaa.utils.DateUtils.parseDate(end, format);
		Date endDateBefor = DateUtils.addMonths(endDate, -1);
		String date2BeforStr = com.howbuy.uaa.utils.DateUtils.getFormatedDate(endDateBefor, format);
		String date2Befor = com.howbuy.uaa.utils.DateUtils.getLastDay(date2BeforStr,format);
		return date2Befor;
	}
	
	public static List<String> getDataX24(Calendar now){
		List<String> datax = new ArrayList<String>();
		Calendar nowVar = Calendar.getInstance();
		nowVar.setTime(now.getTime());
		nowVar.set(Calendar.MILLISECOND, 0);
		nowVar.set(Calendar.SECOND, 0);
		nowVar.set(Calendar.MINUTE, 5);
		nowVar.set(Calendar.HOUR_OF_DAY, 0);
		while(true){
			String date = DateUtils.getFormatedDate(nowVar.getTime(), DateUtils.FORMAT_YYYYMMDD_HHMMSS2);
			datax.add(date);
			int minute = nowVar.get(Calendar.MINUTE);
			nowVar.set(Calendar.MINUTE,minute +5);
			/*int hour = nowVar.get(Calendar.HOUR_OF_DAY);
			if(hour==23 && nowVar.get(Calendar.MINUTE)==55){
				date = DateUtils.getFormatedDate(nowVar.getTime(), DateUtils.FORMAT_YYYYMMDD_HHMMSS2);
				datax.add(date);
				break;
			}*/
			if(datax.size()==288){
				break;
			}
			
		}
		return datax;
	}
	
	
	/**
	 * 获取最近的五分钟整点时刻
	 * @return
	 */
	public static String getCurrent5Min(Calendar now) {
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
}
