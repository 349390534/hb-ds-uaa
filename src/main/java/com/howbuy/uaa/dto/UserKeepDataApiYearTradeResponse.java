/**
 * 
 */
package com.howbuy.uaa.dto;

import java.math.BigDecimal;

/**
 * @author qiankun.li
 *
 */
/**
 * @author qiankun.li
 * 
 */
public class UserKeepDataApiYearTradeResponse extends UserKeepDataApiBase {

	/*********** 数据切片对象（总）begin ************/
	/**
	 * 当年新增交易总人数
	 */
	private Long dnxzjy_rs_total;

	/**
	 * 当年新增交易客户总购买金额
	 */
	private BigDecimal dnxzjy_amt_total;
	/*********** 数据切片对象（总）end ************/

	/*********** 数据切片对象（有存量） begin ************/
	/**
	 * 当年新增交易总人数
	 */
	private Long cl_dnxzjy_rs_total;
	/**
	 * 当年新增交易客户总购买金额
	 */
	private BigDecimal cl_dnxzjy_amt_total;

	/*********** 数据切片对象（有存量） end ************/

	/*********** 数据切片对象（无存量） begin ************/
	/**
	 * 当年新增交易总人数
	 */
	private Long wcl_dnxzjy_rs_total;

	/**
	 * 当年新增交易客户总购买金额
	 */
	private BigDecimal wcl_dnxzjy_amt_total;

	/*********** 数据切片对象（无存量） end ************/

	/*********** "当年新增交易人数（总）begin ************/

	/**
	 * 当年新增交易客户中仅交易1次总人数
	 */
	private Long dnxzjy_rs_1;

	/**
	 * 当年新增交易客户中仅交易2次总人数
	 */
	private Long dnxzjy_rs_2;

	/**
	 * 当年新增交易客户中仅交易3次总人数
	 */
	private Long dnxzjy_rs_3;

	/**
	 * 当年新增交易客户中仅交易4次总人数
	 */
	private Long dnxzjy_rs_4;

	/**
	 * 当年新增交易客户中仅交易5次总人数
	 */
	private Long dnxzjy_rs_5;

	/**
	 * 当年新增交易客户中交易5次以上的总人数
	 */
	private Long dnxzjy_rs_gt5;
	/*********** 当年新增交易人数（总）end ************/

	/*********** 当年新增交易人数（有存量）begin ************/
	/**
	 * 当年新增交易客户中仅交易1次总人数
	 */
	private Long cl_dnxzjy_rs_1;

	/**
	 * 当年新增交易客户中仅交易2次总人数
	 */
	private Long cl_dnxzjy_rs_2;

	/**
	 * 当年新增交易客户中仅交易3次总人数
	 */
	private Long cl_dnxzjy_rs_3;

	/**
	 * 当年新增交易客户中仅交易4次总人数
	 */
	private Long cl_dnxzjy_rs_4;

	/**
	 * 当年新增交易客户中仅交易5次总人数
	 */
	private Long cl_dnxzjy_rs_5;

	/**
	 * 当年开户当年仅交易5次以上总人数
	 */
	private Long cl_dnxzjy_rs_gt5;

	/*********** 当年新增交易人数（有存量）end ************/

	/*********** 当年新增交易人数（无存量）begin ************/
	
	/**
	 * 当年新增交易客户中仅交易1次总人数
	 */
	private Long wcl_dnxzjy_rs_1;

	/**
	 * 当年新增交易客户中仅交易2次总人数
	 */
	private Long wcl_dnxzjy_rs_2;
	/**
	 * 当年新增交易客户中仅交易3次总人数
	 */
	private Long wcl_dnxzjy_rs_3;
	/**
	 * 当年新增交易客户中仅交易4次总人数
	 */
	private Long wcl_dnxzjy_rs_4;

	/**
	 * 当年新增交易客户中仅交易5次总人数
	 */
	private Long wcl_dnxzjy_rs_5;

	/**
	 * 当年新增交易客户中交易5次以上的总人数
	 */
	private Long wcl_dnxzjy_rs_gt5;

	/*********** 当年新增交易人数（无存量）end ************/

	/*********** 当年新增交易金额（总）begin ************/
	/**
	 * 当年新增交易客户仅交易1次总金额
	 */
	private BigDecimal dnxzjy_amt_1;

	/**
	 * 当年新增交易客户仅交易2次总金额
	 */
	private BigDecimal dnxzjy_amt_2;

	/**
	 * 当年新增交易客户仅交易3次总金额
	 */
	private BigDecimal dnxzjy_amt_3;

	/**
	 * 当年新增交易客户仅交易4次总金额
	 */
	private BigDecimal dnxzjy_amt_4;

	/**
	 * 当年新增交易客户仅交易5次总金额
	 */
	private BigDecimal dnxzjy_amt_5;

	/**
	 * 当年新增交易客户交易5次以上总金额
	 */
	private BigDecimal dnxzjy_amt_gt5;

	/*********** 当年开户当年交易金额（总）end ************/

	/*********** 当年新增交易金额（有存量）begin ************/

	/**
	 * 当年新增交易客户仅交易1次总金额
	 */
	private BigDecimal cl_dnxzjy_amt_1;

	/**
	 * 当年新增交易客户仅交易2次总金额
	 */
	private BigDecimal cl_dnxzjy_amt_2;

	/**
	 * 当年新增交易客户仅交易3次总金额
	 */
	private BigDecimal cl_dnxzjy_amt_3;

	/**
	 * 当年新增交易客户仅交易4次总金额
	 */
	private BigDecimal cl_dnxzjy_amt_4;

	/**
	 * 当年新增交易客户仅交易5次总金额
	 */
	private BigDecimal cl_dnxzjy_amt_5;

	/**
	 * 当年开户当年交易5次以上总金额
	 */
	private BigDecimal cl_dnxzjy_amt_gt5;

	/*********** 当年新增交易金额（有存量）end ************/

	/*********** 当年新增交易金额（无存量）begin ************/
	/**
	 * 当年新增交易客户仅交易1次总金额
	 */
	private BigDecimal wcl_dnxzjy_amt_1;

	/**
	 * 当年新增交易客户仅交易2次总金额
	 */
	private BigDecimal wcl_dnxzjy_amt_2;

	/**
	 * 当年新增交易客户仅交易3次总金额
	 */
	private BigDecimal wcl_dnxzjy_amt_3;

	/**
	 * 当年新增交易客户仅交易4次总金额
	 */
	private BigDecimal wcl_dnxzjy_amt_4;

	/**
	 * 当年新增交易客户仅交易5次总金额
	 */
	private BigDecimal wcl_dnxzjy_amt_5;

	/**
	 * 当年新增交易客户交易5次以上总金额
	 */
	private BigDecimal wcl_dnxzjy_amt_gt5;

	/*********** 当年新增交易金额（无存量）end ************/

	/*********** 当年新增交易人数占比begin ************/
	/**
	 * 当年新增交易客户仅交易1次的用户占比
	 */
	private BigDecimal dnxzjy_rate_1;

	/**
	 * 当年新增交易客户仅交易2次的用户占比
	 */
	private BigDecimal dnxzjy_rate_2;

	/**
	 * 当年新增交易客户仅交易3次的用户占比
	 */
	private BigDecimal dnxzjy_rate_3;

	/**
	 * 当年新增交易客户仅交易4次的用户占比
	 */
	private BigDecimal dnxzjy_rate_4;

	/**
	 * 当年新增交易客户仅交易5次的用户占比
	 */
	private BigDecimal dnxzjy_rate_5;

	/**
	 * 当年新增交易客户仅交易5次以上的用户占比
	 */
	private BigDecimal dnxzjy_rate_gt5;
	/*********** 当年新增交易人数占比end ************/

	/*********** 当年新增交易人数占比(有存量）begin ************/
	/**
	 * 当年新增交易客户仅交易1次的用户占比
	 */
	private BigDecimal cl_dnxzjy_rate_1;

	/**
	 * 当年新增交易客户仅交易2次的用户占比
	 */
	private BigDecimal cl_dnxzjy_rate_2;

	/**
	 * 当年新增交易客户仅交易3次的用户占比
	 */
	private BigDecimal cl_dnxzjy_rate_3;

	/**
	 * 当年新增交易客户仅交易4次的用户占比
	 */
	private BigDecimal cl_dnxzjy_rate_4;

	/**
	 * 当年新增交易客户仅交易5次的用户占比
	 */
	private BigDecimal cl_dnxzjy_rate_5;

	/**
	 * 当年新增交易客户仅交易5次以上的用户占比
	 */
	private BigDecimal cl_dnxzjy_rate_gt5;

	/*********** 当年新增交易人数占比(有存量）end ************/

	/*********** 当年新增交易人数占比(无存量）begin ************/
	/**
	 * 当年新增交易客户仅交易1次的用户占比
	 */
	private BigDecimal wcl_dnxzjy_rate_1;

	/**
	 * 当年新增交易客户仅交易2次的用户占比
	 */
	private BigDecimal wcl_dnxzjy_rate_2;

	/**
	 * 当年新增交易客户仅交易3次的用户占比
	 */
	private BigDecimal wcl_dnxzjy_rate_3;

	/**
	 * 当年新增交易客户仅交易4次的用户占比
	 */
	private BigDecimal wcl_dnxzjy_rate_4;

	/**
	 * 当年新增交易客户仅交易5次的用户占比
	 */
	private BigDecimal wcl_dnxzjy_rate_5;

	/**
	 * 当年新增交易客户仅交易5次以上的用户占比
	 */
	private BigDecimal wcl_dnxzjy_rate_gt5;

	/*********** 当年新增交易人数占比(无存量）end ************/
	
	
	/*********** 自定义字段 ************/
	
	/**
	 *复购人数 
	 */
	private  Long fgrs;
	/**
	 * 复购人数-存量
	 */
	private  Long fgrs_cl;
	/**
	 * 复购人数-无存量
	 */
	private  Long fgrs_wcl;
	
	/**
	 * 复购金额
	 */
	private BigDecimal fgje;
	/**
	 * 复购金额-存量
	 */
	private BigDecimal fgje_cl;
	/**
	 * 复购金额-无存量
	 */
	private BigDecimal fgje_wcl;
	
	
	public Long getDnxzjy_rs_total() {
		return dnxzjy_rs_total;
	}

	public void setDnxzjy_rs_total(Long dnxzjy_rs_total) {
		this.dnxzjy_rs_total = dnxzjy_rs_total;
	}

	public BigDecimal getDnxzjy_amt_total() {
		return dnxzjy_amt_total;
	}

	public void setDnxzjy_amt_total(BigDecimal dnxzjy_amt_total) {
		this.dnxzjy_amt_total = dnxzjy_amt_total;
	}

	public Long getCl_dnxzjy_rs_total() {
		return cl_dnxzjy_rs_total;
	}

	public void setCl_dnxzjy_rs_total(Long cl_dnxzjy_rs_total) {
		this.cl_dnxzjy_rs_total = cl_dnxzjy_rs_total;
	}

	public BigDecimal getCl_dnxzjy_amt_total() {
		return cl_dnxzjy_amt_total;
	}

	public void setCl_dnxzjy_amt_total(BigDecimal cl_dnxzjy_amt_total) {
		this.cl_dnxzjy_amt_total = cl_dnxzjy_amt_total;
	}

	public Long getWcl_dnxzjy_rs_total() {
		return wcl_dnxzjy_rs_total;
	}

	public void setWcl_dnxzjy_rs_total(Long wcl_dnxzjy_rs_total) {
		this.wcl_dnxzjy_rs_total = wcl_dnxzjy_rs_total;
	}

	public BigDecimal getWcl_dnxzjy_amt_total() {
		return wcl_dnxzjy_amt_total;
	}

	public void setWcl_dnxzjy_amt_total(BigDecimal wcl_dnxzjy_amt_total) {
		this.wcl_dnxzjy_amt_total = wcl_dnxzjy_amt_total;
	}

	public Long getDnxzjy_rs_1() {
		return dnxzjy_rs_1;
	}

	public void setDnxzjy_rs_1(Long dnxzjy_rs_1) {
		this.dnxzjy_rs_1 = dnxzjy_rs_1;
	}

	public Long getDnxzjy_rs_2() {
		return dnxzjy_rs_2;
	}

	public void setDnxzjy_rs_2(Long dnxzjy_rs_2) {
		this.dnxzjy_rs_2 = dnxzjy_rs_2;
	}

	public Long getDnxzjy_rs_3() {
		return dnxzjy_rs_3;
	}

	public void setDnxzjy_rs_3(Long dnxzjy_rs_3) {
		this.dnxzjy_rs_3 = dnxzjy_rs_3;
	}

	public Long getDnxzjy_rs_4() {
		return dnxzjy_rs_4;
	}

	public void setDnxzjy_rs_4(Long dnxzjy_rs_4) {
		this.dnxzjy_rs_4 = dnxzjy_rs_4;
	}

	public Long getDnxzjy_rs_5() {
		return dnxzjy_rs_5;
	}

	public void setDnxzjy_rs_5(Long dnxzjy_rs_5) {
		this.dnxzjy_rs_5 = dnxzjy_rs_5;
	}

	public Long getDnxzjy_rs_gt5() {
		return dnxzjy_rs_gt5;
	}

	public void setDnxzjy_rs_gt5(Long dnxzjy_rs_gt5) {
		this.dnxzjy_rs_gt5 = dnxzjy_rs_gt5;
	}

	public Long getCl_dnxzjy_rs_1() {
		return cl_dnxzjy_rs_1;
	}

	public void setCl_dnxzjy_rs_1(Long cl_dnxzjy_rs_1) {
		this.cl_dnxzjy_rs_1 = cl_dnxzjy_rs_1;
	}

	public Long getCl_dnxzjy_rs_2() {
		return cl_dnxzjy_rs_2;
	}

	public void setCl_dnxzjy_rs_2(Long cl_dnxzjy_rs_2) {
		this.cl_dnxzjy_rs_2 = cl_dnxzjy_rs_2;
	}

	public Long getCl_dnxzjy_rs_3() {
		return cl_dnxzjy_rs_3;
	}

	public void setCl_dnxzjy_rs_3(Long cl_dnxzjy_rs_3) {
		this.cl_dnxzjy_rs_3 = cl_dnxzjy_rs_3;
	}

	public Long getCl_dnxzjy_rs_4() {
		return cl_dnxzjy_rs_4;
	}

	public void setCl_dnxzjy_rs_4(Long cl_dnxzjy_rs_4) {
		this.cl_dnxzjy_rs_4 = cl_dnxzjy_rs_4;
	}

	public Long getCl_dnxzjy_rs_5() {
		return cl_dnxzjy_rs_5;
	}

	public void setCl_dnxzjy_rs_5(Long cl_dnxzjy_rs_5) {
		this.cl_dnxzjy_rs_5 = cl_dnxzjy_rs_5;
	}

	public Long getCl_dnxzjy_rs_gt5() {
		return cl_dnxzjy_rs_gt5;
	}

	public void setCl_dnxzjy_rs_gt5(Long cl_dnxzjy_rs_gt5) {
		this.cl_dnxzjy_rs_gt5 = cl_dnxzjy_rs_gt5;
	}

	public Long getWcl_dnxzjy_rs_1() {
		return wcl_dnxzjy_rs_1;
	}

	public void setWcl_dnxzjy_rs_1(Long wcl_dnxzjy_rs_1) {
		this.wcl_dnxzjy_rs_1 = wcl_dnxzjy_rs_1;
	}

	public Long getWcl_dnxzjy_rs_2() {
		return wcl_dnxzjy_rs_2;
	}

	public void setWcl_dnxzjy_rs_2(Long wcl_dnxzjy_rs_2) {
		this.wcl_dnxzjy_rs_2 = wcl_dnxzjy_rs_2;
	}

	public Long getWcl_dnxzjy_rs_3() {
		return wcl_dnxzjy_rs_3;
	}

	public void setWcl_dnxzjy_rs_3(Long wcl_dnxzjy_rs_3) {
		this.wcl_dnxzjy_rs_3 = wcl_dnxzjy_rs_3;
	}

	public Long getWcl_dnxzjy_rs_4() {
		return wcl_dnxzjy_rs_4;
	}

	public void setWcl_dnxzjy_rs_4(Long wcl_dnxzjy_rs_4) {
		this.wcl_dnxzjy_rs_4 = wcl_dnxzjy_rs_4;
	}

	public Long getWcl_dnxzjy_rs_5() {
		return wcl_dnxzjy_rs_5;
	}

	public void setWcl_dnxzjy_rs_5(Long wcl_dnxzjy_rs_5) {
		this.wcl_dnxzjy_rs_5 = wcl_dnxzjy_rs_5;
	}

	public Long getWcl_dnxzjy_rs_gt5() {
		return wcl_dnxzjy_rs_gt5;
	}

	public void setWcl_dnxzjy_rs_gt5(Long wcl_dnxzjy_rs_gt5) {
		this.wcl_dnxzjy_rs_gt5 = wcl_dnxzjy_rs_gt5;
	}

	public BigDecimal getDnxzjy_amt_1() {
		return dnxzjy_amt_1;
	}

	public void setDnxzjy_amt_1(BigDecimal dnxzjy_amt_1) {
		this.dnxzjy_amt_1 = dnxzjy_amt_1;
	}

	public BigDecimal getDnxzjy_amt_2() {
		return dnxzjy_amt_2;
	}

	public void setDnxzjy_amt_2(BigDecimal dnxzjy_amt_2) {
		this.dnxzjy_amt_2 = dnxzjy_amt_2;
	}

	public BigDecimal getDnxzjy_amt_3() {
		return dnxzjy_amt_3;
	}

	public void setDnxzjy_amt_3(BigDecimal dnxzjy_amt_3) {
		this.dnxzjy_amt_3 = dnxzjy_amt_3;
	}

	public BigDecimal getDnxzjy_amt_4() {
		return dnxzjy_amt_4;
	}

	public void setDnxzjy_amt_4(BigDecimal dnxzjy_amt_4) {
		this.dnxzjy_amt_4 = dnxzjy_amt_4;
	}

	public BigDecimal getDnxzjy_amt_5() {
		return dnxzjy_amt_5;
	}

	public void setDnxzjy_amt_5(BigDecimal dnxzjy_amt_5) {
		this.dnxzjy_amt_5 = dnxzjy_amt_5;
	}

	public BigDecimal getDnxzjy_amt_gt5() {
		return dnxzjy_amt_gt5;
	}

	public void setDnxzjy_amt_gt5(BigDecimal dnxzjy_amt_gt5) {
		this.dnxzjy_amt_gt5 = dnxzjy_amt_gt5;
	}

	public BigDecimal getCl_dnxzjy_amt_1() {
		return cl_dnxzjy_amt_1;
	}

	public void setCl_dnxzjy_amt_1(BigDecimal cl_dnxzjy_amt_1) {
		this.cl_dnxzjy_amt_1 = cl_dnxzjy_amt_1;
	}

	public BigDecimal getCl_dnxzjy_amt_2() {
		return cl_dnxzjy_amt_2;
	}

	public void setCl_dnxzjy_amt_2(BigDecimal cl_dnxzjy_amt_2) {
		this.cl_dnxzjy_amt_2 = cl_dnxzjy_amt_2;
	}

	public BigDecimal getCl_dnxzjy_amt_3() {
		return cl_dnxzjy_amt_3;
	}

	public void setCl_dnxzjy_amt_3(BigDecimal cl_dnxzjy_amt_3) {
		this.cl_dnxzjy_amt_3 = cl_dnxzjy_amt_3;
	}

	public BigDecimal getCl_dnxzjy_amt_4() {
		return cl_dnxzjy_amt_4;
	}

	public void setCl_dnxzjy_amt_4(BigDecimal cl_dnxzjy_amt_4) {
		this.cl_dnxzjy_amt_4 = cl_dnxzjy_amt_4;
	}

	public BigDecimal getCl_dnxzjy_amt_5() {
		return cl_dnxzjy_amt_5;
	}

	public void setCl_dnxzjy_amt_5(BigDecimal cl_dnxzjy_amt_5) {
		this.cl_dnxzjy_amt_5 = cl_dnxzjy_amt_5;
	}

	public BigDecimal getCl_dnxzjy_amt_gt5() {
		return cl_dnxzjy_amt_gt5;
	}

	public void setCl_dnxzjy_amt_gt5(BigDecimal cl_dnxzjy_amt_gt5) {
		this.cl_dnxzjy_amt_gt5 = cl_dnxzjy_amt_gt5;
	}

	public BigDecimal getWcl_dnxzjy_amt_1() {
		return wcl_dnxzjy_amt_1;
	}

	public void setWcl_dnxzjy_amt_1(BigDecimal wcl_dnxzjy_amt_1) {
		this.wcl_dnxzjy_amt_1 = wcl_dnxzjy_amt_1;
	}

	public BigDecimal getWcl_dnxzjy_amt_2() {
		return wcl_dnxzjy_amt_2;
	}

	public void setWcl_dnxzjy_amt_2(BigDecimal wcl_dnxzjy_amt_2) {
		this.wcl_dnxzjy_amt_2 = wcl_dnxzjy_amt_2;
	}

	public BigDecimal getWcl_dnxzjy_amt_3() {
		return wcl_dnxzjy_amt_3;
	}

	public void setWcl_dnxzjy_amt_3(BigDecimal wcl_dnxzjy_amt_3) {
		this.wcl_dnxzjy_amt_3 = wcl_dnxzjy_amt_3;
	}

	public BigDecimal getWcl_dnxzjy_amt_4() {
		return wcl_dnxzjy_amt_4;
	}

	public void setWcl_dnxzjy_amt_4(BigDecimal wcl_dnxzjy_amt_4) {
		this.wcl_dnxzjy_amt_4 = wcl_dnxzjy_amt_4;
	}

	public BigDecimal getWcl_dnxzjy_amt_5() {
		return wcl_dnxzjy_amt_5;
	}

	public void setWcl_dnxzjy_amt_5(BigDecimal wcl_dnxzjy_amt_5) {
		this.wcl_dnxzjy_amt_5 = wcl_dnxzjy_amt_5;
	}

	public BigDecimal getWcl_dnxzjy_amt_gt5() {
		return wcl_dnxzjy_amt_gt5;
	}

	public void setWcl_dnxzjy_amt_gt5(BigDecimal wcl_dnxzjy_amt_gt5) {
		this.wcl_dnxzjy_amt_gt5 = wcl_dnxzjy_amt_gt5;
	}

	public BigDecimal getDnxzjy_rate_1() {
		return dnxzjy_rate_1;
	}

	public void setDnxzjy_rate_1(BigDecimal dnxzjy_rate_1) {
		this.dnxzjy_rate_1 = dnxzjy_rate_1;
	}

	public BigDecimal getDnxzjy_rate_2() {
		return dnxzjy_rate_2;
	}

	public void setDnxzjy_rate_2(BigDecimal dnxzjy_rate_2) {
		this.dnxzjy_rate_2 = dnxzjy_rate_2;
	}

	public BigDecimal getDnxzjy_rate_3() {
		return dnxzjy_rate_3;
	}

	public void setDnxzjy_rate_3(BigDecimal dnxzjy_rate_3) {
		this.dnxzjy_rate_3 = dnxzjy_rate_3;
	}

	public BigDecimal getDnxzjy_rate_4() {
		return dnxzjy_rate_4;
	}

	public void setDnxzjy_rate_4(BigDecimal dnxzjy_rate_4) {
		this.dnxzjy_rate_4 = dnxzjy_rate_4;
	}

	public BigDecimal getDnxzjy_rate_5() {
		return dnxzjy_rate_5;
	}

	public void setDnxzjy_rate_5(BigDecimal dnxzjy_rate_5) {
		this.dnxzjy_rate_5 = dnxzjy_rate_5;
	}

	public BigDecimal getDnxzjy_rate_gt5() {
		return dnxzjy_rate_gt5;
	}

	public void setDnxzjy_rate_gt5(BigDecimal dnxzjy_rate_gt5) {
		this.dnxzjy_rate_gt5 = dnxzjy_rate_gt5;
	}

	public BigDecimal getCl_dnxzjy_rate_1() {
		return cl_dnxzjy_rate_1;
	}

	public void setCl_dnxzjy_rate_1(BigDecimal cl_dnxzjy_rate_1) {
		this.cl_dnxzjy_rate_1 = cl_dnxzjy_rate_1;
	}

	public BigDecimal getCl_dnxzjy_rate_2() {
		return cl_dnxzjy_rate_2;
	}

	public void setCl_dnxzjy_rate_2(BigDecimal cl_dnxzjy_rate_2) {
		this.cl_dnxzjy_rate_2 = cl_dnxzjy_rate_2;
	}

	public BigDecimal getCl_dnxzjy_rate_3() {
		return cl_dnxzjy_rate_3;
	}

	public void setCl_dnxzjy_rate_3(BigDecimal cl_dnxzjy_rate_3) {
		this.cl_dnxzjy_rate_3 = cl_dnxzjy_rate_3;
	}

	public BigDecimal getCl_dnxzjy_rate_4() {
		return cl_dnxzjy_rate_4;
	}

	public void setCl_dnxzjy_rate_4(BigDecimal cl_dnxzjy_rate_4) {
		this.cl_dnxzjy_rate_4 = cl_dnxzjy_rate_4;
	}

	public BigDecimal getCl_dnxzjy_rate_5() {
		return cl_dnxzjy_rate_5;
	}

	public void setCl_dnxzjy_rate_5(BigDecimal cl_dnxzjy_rate_5) {
		this.cl_dnxzjy_rate_5 = cl_dnxzjy_rate_5;
	}

	public BigDecimal getCl_dnxzjy_rate_gt5() {
		return cl_dnxzjy_rate_gt5;
	}

	public void setCl_dnxzjy_rate_gt5(BigDecimal cl_dnxzjy_rate_gt5) {
		this.cl_dnxzjy_rate_gt5 = cl_dnxzjy_rate_gt5;
	}

	public BigDecimal getWcl_dnxzjy_rate_1() {
		return wcl_dnxzjy_rate_1;
	}

	public void setWcl_dnxzjy_rate_1(BigDecimal wcl_dnxzjy_rate_1) {
		this.wcl_dnxzjy_rate_1 = wcl_dnxzjy_rate_1;
	}

	public BigDecimal getWcl_dnxzjy_rate_2() {
		return wcl_dnxzjy_rate_2;
	}

	public void setWcl_dnxzjy_rate_2(BigDecimal wcl_dnxzjy_rate_2) {
		this.wcl_dnxzjy_rate_2 = wcl_dnxzjy_rate_2;
	}

	public BigDecimal getWcl_dnxzjy_rate_3() {
		return wcl_dnxzjy_rate_3;
	}

	public void setWcl_dnxzjy_rate_3(BigDecimal wcl_dnxzjy_rate_3) {
		this.wcl_dnxzjy_rate_3 = wcl_dnxzjy_rate_3;
	}

	public BigDecimal getWcl_dnxzjy_rate_4() {
		return wcl_dnxzjy_rate_4;
	}

	public void setWcl_dnxzjy_rate_4(BigDecimal wcl_dnxzjy_rate_4) {
		this.wcl_dnxzjy_rate_4 = wcl_dnxzjy_rate_4;
	}

	public BigDecimal getWcl_dnxzjy_rate_5() {
		return wcl_dnxzjy_rate_5;
	}

	public void setWcl_dnxzjy_rate_5(BigDecimal wcl_dnxzjy_rate_5) {
		this.wcl_dnxzjy_rate_5 = wcl_dnxzjy_rate_5;
	}

	public BigDecimal getWcl_dnxzjy_rate_gt5() {
		return wcl_dnxzjy_rate_gt5;
	}

	public void setWcl_dnxzjy_rate_gt5(BigDecimal wcl_dnxzjy_rate_gt5) {
		this.wcl_dnxzjy_rate_gt5 = wcl_dnxzjy_rate_gt5;
	}

	public Long getFgrs() {
		Long fgrsAll = 0l;
		if(null!=dnxzjy_rs_2)
			fgrsAll+=dnxzjy_rs_2;
		if(null!=dnxzjy_rs_3)
			fgrsAll+=dnxzjy_rs_3;
		if(null!=dnxzjy_rs_4)
			fgrsAll+=dnxzjy_rs_4;
		if(null!=dnxzjy_rs_5)
			fgrsAll+=dnxzjy_rs_5;
		if(null!=dnxzjy_rs_gt5)
			fgrsAll+=dnxzjy_rs_gt5;
		fgrs=fgrsAll;
		return fgrs;
	}

	public Long getFgrs_cl() {
		Long fgrsAll = 0l;
		if(null!=cl_dnxzjy_rs_2)
			fgrsAll+=cl_dnxzjy_rs_2;
		if(null!=cl_dnxzjy_rs_3)
			fgrsAll+=cl_dnxzjy_rs_3;
		if(null!=cl_dnxzjy_rs_4)
			fgrsAll+=cl_dnxzjy_rs_4;
		if(null!=cl_dnxzjy_rs_5)
			fgrsAll+=cl_dnxzjy_rs_5;
		if(null!=cl_dnxzjy_rs_gt5)
			fgrsAll+=cl_dnxzjy_rs_gt5;
		fgrs_cl=fgrsAll;
		return fgrs_cl;
	}

	public Long getFgrs_wcl() {
		Long fgrsAll = 0l;
		if(null!=wcl_dnxzjy_rs_2)
			fgrsAll+=wcl_dnxzjy_rs_2;
		if(null!=wcl_dnxzjy_rs_3)
			fgrsAll+=wcl_dnxzjy_rs_3;
		if(null!=wcl_dnxzjy_rs_4)
			fgrsAll+=wcl_dnxzjy_rs_4;
		if(null!=wcl_dnxzjy_rs_5)
			fgrsAll+=wcl_dnxzjy_rs_5;
		if(null!=wcl_dnxzjy_rs_gt5)
			fgrsAll+=wcl_dnxzjy_rs_gt5;
		fgrs_wcl=fgrsAll;
		return fgrs_wcl;
	}

	public BigDecimal getFgje() {
		BigDecimal fgjeAll = new BigDecimal(0);
		if(null!=dnxzjy_amt_2)
			fgjeAll=fgjeAll.add(dnxzjy_amt_2);
		if(null!=dnxzjy_amt_3)
			fgjeAll=fgjeAll.add(dnxzjy_amt_3);
		if(null!=dnxzjy_amt_4)
			fgjeAll=fgjeAll.add(dnxzjy_amt_4);
		if(null!=dnxzjy_amt_5)
			fgjeAll=fgjeAll.add(dnxzjy_amt_5);
		if(null!=dnxzjy_amt_gt5)
			fgjeAll=fgjeAll.add(dnxzjy_amt_gt5);
		fgje=fgjeAll;
		return fgje;
	}

	public BigDecimal getFgje_cl() {
		BigDecimal fgjeAll = new BigDecimal(0);
		if(null!=cl_dnxzjy_amt_2)
			fgjeAll=fgjeAll.add(cl_dnxzjy_amt_2);
		if(null!=cl_dnxzjy_amt_3)
			fgjeAll=fgjeAll.add(cl_dnxzjy_amt_3);
		if(null!=cl_dnxzjy_amt_4)
			fgjeAll=fgjeAll.add(cl_dnxzjy_amt_4);
		if(null!=cl_dnxzjy_amt_5)
			fgjeAll=fgjeAll.add(cl_dnxzjy_amt_5);
		if(null!=cl_dnxzjy_amt_gt5)
			fgjeAll=fgjeAll.add(cl_dnxzjy_amt_gt5);
		fgje_cl=fgjeAll;
		return fgje_cl;
	}

	public BigDecimal getFgje_wcl() {
		BigDecimal fgjeAll = new BigDecimal(0);
		if(null!=wcl_dnxzjy_amt_2)
			fgjeAll=fgjeAll.add(wcl_dnxzjy_amt_2);
		if(null!=wcl_dnxzjy_amt_3)
			fgjeAll=fgjeAll.add(wcl_dnxzjy_amt_3);
		if(null!=wcl_dnxzjy_amt_4)
			fgjeAll=fgjeAll.add(wcl_dnxzjy_amt_4);
		if(null!=wcl_dnxzjy_amt_5)
			fgjeAll=fgjeAll.add(wcl_dnxzjy_amt_5);
		if(null!=wcl_dnxzjy_amt_gt5)
			fgjeAll=fgjeAll.add(wcl_dnxzjy_amt_gt5);
		fgje_wcl=fgjeAll;
		return fgje_wcl;
	}


}