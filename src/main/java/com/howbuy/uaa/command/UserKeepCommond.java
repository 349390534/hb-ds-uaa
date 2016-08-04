/**
 * 
 */
package com.howbuy.uaa.command;

/**
 * @author qiankun.li
 * 
 */
public class UserKeepCommond extends FundStatisticsCommand {

	private String year;
	
	private String month;

	private Integer gid;

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Integer getGid() {
		return gid;
	}

	public void setGid(Integer gid) {
		this.gid = gid;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

}
