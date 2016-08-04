/**
 * 
 */
package com.howbuy.uaa.dto;

import java.math.BigDecimal;


/**
 * @author qiankun.li
 * 
 */
public class DisabClientConvertDeptDataApiResponse {

	/**
	 * 日期
	 */
	private String statdt;
	/**
	 * 零售业务部成交数
	 */
	private long trade_cnt_ls;
	/**
	 * 零售业务部分配未成交数
	 */
	private long no_trade_cnt_ls;
	/**
	 * 零售业务部成交比
	 */
	private float trade_pct_ls;
	/**
	 * 公募挖掘团队成交数
	 */
	private long trade_cnt_wj;
	/**
	 * 公募挖掘团队分配未成交数
	 */
	private long no_trade_cnt_wj;
	/**
	 * 公募挖掘团队成交比
	 */
	private float trade_pct_wj;
	/**
	 * 公募挖掘团队储蓄罐未分配人数
	 */
	private long cxgwfp_cnt_wj;
	
	/**
	 * 公募挖掘团队成交比-含储蓄罐未分配
	 */
	private float no_trade_cnt_wj_ls;

	public String getStatdt() {
		return statdt;
	}

	public void setStatdt(String statdt) {
		this.statdt = statdt;
	}

	public long getTrade_cnt_ls() {
		return trade_cnt_ls;
	}

	public void setTrade_cnt_ls(long trade_cnt_ls) {
		this.trade_cnt_ls = trade_cnt_ls;
	}

	public long getNo_trade_cnt_ls() {
		return no_trade_cnt_ls;
	}

	public void setNo_trade_cnt_ls(long no_trade_cnt_ls) {
		this.no_trade_cnt_ls = no_trade_cnt_ls;
	}

	public float getTrade_pct_ls() {
		return trade_pct_ls;
	}

	public void setTrade_pct_ls(float trade_pct_ls) {
		this.trade_pct_ls = trade_pct_ls;
	}

	public long getTrade_cnt_wj() {
		return trade_cnt_wj;
	}

	public void setTrade_cnt_wj(long trade_cnt_wj) {
		this.trade_cnt_wj = trade_cnt_wj;
	}

	public long getNo_trade_cnt_wj() {
		return no_trade_cnt_wj;
	}

	public void setNo_trade_cnt_wj(long no_trade_cnt_wj) {
		this.no_trade_cnt_wj = no_trade_cnt_wj;
	}

	public float getTrade_pct_wj() {
		return trade_pct_wj;
	}

	public void setTrade_pct_wj(float trade_pct_wj) {
		this.trade_pct_wj = trade_pct_wj;
	}

	public long getCxgwfp_cnt_wj() {
		return cxgwfp_cnt_wj;
	}

	public void setCxgwfp_cnt_wj(long cxgwfp_cnt_wj) {
		this.cxgwfp_cnt_wj = cxgwfp_cnt_wj;
	}

	public float getNo_trade_cnt_wj_ls() {
		BigDecimal a = new BigDecimal(this.trade_cnt_wj).multiply(new BigDecimal(100));
		BigDecimal b = new BigDecimal(this.trade_cnt_wj + this.no_trade_cnt_wj + this.cxgwfp_cnt_wj);
		no_trade_cnt_wj_ls= a.divide(b,8, BigDecimal.ROUND_HALF_DOWN).floatValue();
		return no_trade_cnt_wj_ls;
	}

	public void setNo_trade_cnt_wj_ls(float no_trade_cnt_wj_ls) {
		this.no_trade_cnt_wj_ls = no_trade_cnt_wj_ls;
	}

}
