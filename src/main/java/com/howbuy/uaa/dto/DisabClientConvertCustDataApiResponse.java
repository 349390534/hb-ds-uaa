/**
 * 
 */
package com.howbuy.uaa.dto;

/**
 * @author qiankun.li
 * 
 */
public class DisabClientConvertCustDataApiResponse {

	/**
	 * 日期
	 */
	private String statdt;
	/**
	 * 一手客户成交人数
	 */
	private long trade_cnt1;
	/**
	 * 一手客户当月分配数
	 */
	private long curr_assign_cnt1;
	/**
	 * 一手客户当月成交数
	 */
	private long curr_trade_cnt1;
	/**
	 * 二手潜在客户成交人数
	 */
	private long trade_cnt2;
	/**
	 * 二手潜在客户当月分配数
	 */
	private long curr_assign_cnt2;
	/**
	 * 二手潜在客户当月成交数
	 */
	private long curr_trade_cnt2;
	/**
	 * 二手成交客户成交人数
	 */
	private long trade_cnt3;
	/**
	 * 二手成交客户当月分配数
	 */
	private long curr_assign_cnt3;
	/**
	 * 二手成交客户当月成交数
	 */
	private long curr_trade_cnt3;

	public String getStatdt() {
		return statdt;
	}

	public void setStatdt(String statdt) {
		this.statdt = statdt;
	}

	public long getTrade_cnt1() {
		return trade_cnt1;
	}

	public void setTrade_cnt1(long trade_cnt1) {
		this.trade_cnt1 = trade_cnt1;
	}

	public long getCurr_assign_cnt1() {
		return curr_assign_cnt1;
	}

	public void setCurr_assign_cnt1(long curr_assign_cnt1) {
		this.curr_assign_cnt1 = curr_assign_cnt1;
	}

	public long getCurr_trade_cnt1() {
		return curr_trade_cnt1;
	}

	public void setCurr_trade_cnt1(long curr_trade_cnt1) {
		this.curr_trade_cnt1 = curr_trade_cnt1;
	}

	public long getTrade_cnt2() {
		return trade_cnt2;
	}

	public void setTrade_cnt2(long trade_cnt2) {
		this.trade_cnt2 = trade_cnt2;
	}

	public long getCurr_assign_cnt2() {
		return curr_assign_cnt2;
	}

	public void setCurr_assign_cnt2(long curr_assign_cnt2) {
		this.curr_assign_cnt2 = curr_assign_cnt2;
	}

	public long getCurr_trade_cnt2() {
		return curr_trade_cnt2;
	}

	public void setCurr_trade_cnt2(long curr_trade_cnt2) {
		this.curr_trade_cnt2 = curr_trade_cnt2;
	}

	public long getTrade_cnt3() {
		return trade_cnt3;
	}

	public void setTrade_cnt3(long trade_cnt3) {
		this.trade_cnt3 = trade_cnt3;
	}

	public long getCurr_assign_cnt3() {
		return curr_assign_cnt3;
	}

	public void setCurr_assign_cnt3(long curr_assign_cnt3) {
		this.curr_assign_cnt3 = curr_assign_cnt3;
	}

	public long getCurr_trade_cnt3() {
		return curr_trade_cnt3;
	}

	public void setCurr_trade_cnt3(long curr_trade_cnt3) {
		this.curr_trade_cnt3 = curr_trade_cnt3;
	}

}
