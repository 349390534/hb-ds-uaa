/**
 * 
 */
package com.howbuy.uaa.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author qiankun.li
 * 
 */
public class UserKeepDataApiBase {
	/*********** 维度begin ************/
	/**
	 * 日期
	 */
	protected String stat_dt;

	/**
	 * 开户机构
	 */
	protected String reg_dis_name;
	/**
	 * 开户平台
	 */
	protected String reg_chan_name;

	/**
	 * 合作类型
	 */
	protected String reg_hzlx;

	/**
	 * 开户网点
	 */
	protected String reg_outlet_name;

	/**
	 * 开户机构代码
	 */
	protected String reg_dis_code;

	/**
	 * 开户平台代码
	 */
	protected String reg_trade_chan;

	/**
	 * 合作类型代码
	 */
	protected String reg_hzlx_code;

	/**
	 * 开户网点代码
	 */
	protected String reg_outlet_code;
	/*********** 维度end ************/

	/*********** 存量begin ************/

	/**
	 * 存量人数
	 */
	protected Long clrs;

	/**
	 * 存量人数占比
	 */
	protected BigDecimal clrs_rate;

	/**
	 * 存量金额
	 */
	protected BigDecimal clje;

	/*********** 存量end ************/

	/**
	 * 创建时间
	 */
	protected Date cre_dtm;

	/**
	 * 更新时间
	 */
	protected Date up_dtm;

	/*********** 存量end ************/
	public String getStat_dt() {
		return stat_dt;
	}

	public void setStat_dt(String stat_dt) {
		this.stat_dt = stat_dt;
	}

	public String getReg_dis_name() {
		return reg_dis_name;
	}

	public void setReg_dis_name(String reg_dis_name) {
		this.reg_dis_name = reg_dis_name;
	}

	public String getReg_chan_name() {
		return reg_chan_name;
	}

	public void setReg_chan_name(String reg_chan_name) {
		this.reg_chan_name = reg_chan_name;
	}

	public String getReg_hzlx() {
		return reg_hzlx;
	}

	public void setReg_hzlx(String reg_hzlx) {
		this.reg_hzlx = reg_hzlx;
	}

	public String getReg_outlet_name() {
		return reg_outlet_name;
	}

	public void setReg_outlet_name(String reg_outlet_name) {
		this.reg_outlet_name = reg_outlet_name;
	}

	public String getReg_dis_code() {
		return reg_dis_code;
	}

	public void setReg_dis_code(String reg_dis_code) {
		this.reg_dis_code = reg_dis_code;
	}

	public String getReg_trade_chan() {
		return reg_trade_chan;
	}

	public void setReg_trade_chan(String reg_trade_chan) {
		this.reg_trade_chan = reg_trade_chan;
	}

	public String getReg_hzlx_code() {
		return reg_hzlx_code;
	}

	public void setReg_hzlx_code(String reg_hzlx_code) {
		this.reg_hzlx_code = reg_hzlx_code;
	}

	public String getReg_outlet_code() {
		return reg_outlet_code;
	}

	public void setReg_outlet_code(String reg_outlet_code) {
		this.reg_outlet_code = reg_outlet_code;
	}

	public Long getClrs() {
		return clrs;
	}

	public void setClrs(Long clrs) {
		this.clrs = clrs;
	}

	public BigDecimal getClrs_rate() {
		return clrs_rate;
	}

	public void setClrs_rate(BigDecimal clrs_rate) {
		this.clrs_rate = clrs_rate;
	}

	public BigDecimal getClje() {
		return clje;
	}

	public void setClje(BigDecimal clje) {
		this.clje = clje;
	}

	public Date getCre_dtm() {
		return cre_dtm;
	}

	public void setCre_dtm(Date cre_dtm) {
		this.cre_dtm = cre_dtm;
	}

	public Date getUp_dtm() {
		return up_dtm;
	}

	public void setUp_dtm(Date up_dtm) {
		this.up_dtm = up_dtm;
	}

}
