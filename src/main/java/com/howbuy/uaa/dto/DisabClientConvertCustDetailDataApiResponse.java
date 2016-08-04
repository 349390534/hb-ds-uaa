/**
 * 
 */
package com.howbuy.uaa.dto;

/**
 * @author qiankun.li
 * 
 */
public class DisabClientConvertCustDetailDataApiResponse {

	/**
	 * 投顾姓名
	 */
	private String consname;
	/**
	 * 一手客户-分配客户数
	 */
	private long assign_cnt1;
	/**
	 * 一手客户-成交客户数
	 */
	private long trade_cnt1;
	/**
	 * 一手客户-成交比
	 */
	private float trade_pct1;
	/**
	 * 二手潜在客户-分配客户数
	 */
	private long assign_cnt2;
	/**
	 * 二手潜在客户-成交客户数
	 */
	private long trade_cnt2;
	/**
	 * 二手潜在客户-成交比
	 */
	private float trade_pct2;
	/**
	 * 二手成交客户-分配客户数
	 */
	private long assign_cnt3;
	/**
	 * 二手成交客户-成交客户数
	 */
	private long trade_cnt3;
	/**
	 * 二手成交客户-成交比
	 */
	private float trade_pct3;

	public String getConsname() {
		return consname;
	}

	public void setConsname(String consname) {
		this.consname = consname;
	}

	public long getAssign_cnt1() {
		return assign_cnt1;
	}

	public void setAssign_cnt1(long assign_cnt1) {
		this.assign_cnt1 = assign_cnt1;
	}

	public long getTrade_cnt1() {
		return trade_cnt1;
	}

	public void setTrade_cnt1(long trade_cnt1) {
		this.trade_cnt1 = trade_cnt1;
	}

	public float getTrade_pct1() {
		return trade_pct1;
	}

	public void setTrade_pct1(float trade_pct1) {
		this.trade_pct1 = trade_pct1;
	}

	public long getAssign_cnt2() {
		return assign_cnt2;
	}

	public void setAssign_cnt2(long assign_cnt2) {
		this.assign_cnt2 = assign_cnt2;
	}

	public long getTrade_cnt2() {
		return trade_cnt2;
	}

	public void setTrade_cnt2(long trade_cnt2) {
		this.trade_cnt2 = trade_cnt2;
	}

	public float getTrade_pct2() {
		return trade_pct2;
	}

	public void setTrade_pct2(float trade_pct2) {
		this.trade_pct2 = trade_pct2;
	}

	public long getAssign_cnt3() {
		return assign_cnt3;
	}

	public void setAssign_cnt3(long assign_cnt3) {
		this.assign_cnt3 = assign_cnt3;
	}

	public long getTrade_cnt3() {
		return trade_cnt3;
	}

	public void setTrade_cnt3(long trade_cnt3) {
		this.trade_cnt3 = trade_cnt3;
	}

	public float getTrade_pct3() {
		return trade_pct3;
	}

	public void setTrade_pct3(float trade_pct3) {
		this.trade_pct3 = trade_pct3;
	}

}
