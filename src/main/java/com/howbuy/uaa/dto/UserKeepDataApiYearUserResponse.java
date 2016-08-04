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
/**
 * @author qiankun.li
 * 
 */
public class UserKeepDataApiYearUserResponse extends UserKeepDataApiBase {

	/*********** 数据切片对象（总）begin ************/
	/**
	 * 当年开户总人数
	 */
	private Long dnkh_zrs;

	/**
	 * 当年开户当年交易总人数
	 */
	private Long dnkhdnjy_rs_total;
	/**
	 * 当年开户当年交易总金额
	 */
	private BigDecimal dnkhdnjy_amt_total;

	/*********** 数据切片对象（总）end ************/

	/*********** 数据切片对象（有存量） begin ************/

	/**
	 * 当年开户总人数
	 */
	private Long cl_dnkh_zrs;
	/**
	 * 当年开户当年交易总人数
	 */
	private Long cl_dnkhdnjy_rs_total;

	/**
	 * 当年开户当年交易总金额
	 */
	private BigDecimal cl_dnkhdnjy_amt_total;
	/*********** 数据切片对象（有存量） end ************/

	/*********** 数据切片对象（无存量） begin ************/

	/**
	 * 当年开户总人数
	 */
	private Long wcl_dnkh_zrs;

	/**
	 * 当年开户当年交易总人数
	 */
	private Long wcl_dnkhdnjy_rs_total;

	/**
	 * 当年开户当年交易总金额
	 */
	private BigDecimal wcl_dnkhdnjy_amt_total;

	/*********** 数据切片对象（无存量） end ************/

	/*********** "当年开户当年交易人数（总）begin ************/

	/**
	 * 当年开户当年仅交易1次总人数
	 */
	private Long dnkhdnjy_rs_1;

	/**
	 * 当年开户当年仅交易2次总人数
	 */
	private Long dnkhdnjy_rs_2;

	/**
	 * 当年开户当年仅交易3次总人数
	 */
	private Long dnkhdnjy_rs_3;

	/**
	 * 当年开户当年仅交易4次总人数
	 */
	private Long dnkhdnjy_rs_4;

	/**
	 * 当年开户当年仅交易5次总人数
	 */
	private Long dnkhdnjy_rs_5;

	/**
	 * 当年开户当年仅交易5次以上总人数
	 */
	private Long dnkhdnjy_rs_gt5;
	/*********** "当年开户当年交易人数（总）end ************/

	/*********** "当年开户当年交易人数（有存量）begin ************/
	/**
	 * 当年开户当年仅交易1次总人数
	 */
	private Long cl_dnkhdnjy_rs_1;

	/**
	 * 当年开户当年仅交易2次总人数
	 */
	private Long cl_dnkhdnjy_rs_2;

	/**
	 * 当年开户当年仅交易3次总人数
	 */
	private Long cl_dnkhdnjy_rs_3;

	/**
	 * 当年开户当年仅交易4次总人数
	 */
	private Long cl_dnkhdnjy_rs_4;

	/**
	 * 当年开户当年仅交易5次总人数
	 */
	private Long cl_dnkhdnjy_rs_5;

	/**
	 * 当年开户当年仅交易5次以上总人数
	 */
	private Long cl_dnkhdnjy_rs_gt5;

	/*********** "当年开户当年交易人数（有存量）end ************/

	/*********** "当年开户当年交易人数（无存量）begin ************/

	/**
	 * 当年开户当年仅交易0次总人数 -自定义字段（当年开户总人数-当年开户当年交易人数）
	 */
	private Long wcl_dnkhdnjy_rs_0;
	/**
	 * 当年开户当年仅交易1次总人数
	 */
	private Long wcl_dnkhdnjy_rs_1;

	/**
	 * 当年开户当年仅交易2次总人数
	 */
	private Long wcl_dnkhdnjy_rs_2;
	/**
	 * 当年开户当年仅交易3次总人数
	 */
	private Long wcl_dnkhdnjy_rs_3;
	/**
	 * 当年开户当年仅交易4次总人数
	 */
	private Long wcl_dnkhdnjy_rs_4;

	/**
	 * 当年开户当年仅交易5次总人数
	 */
	private Long wcl_dnkhdnjy_rs_5;

	/**
	 * 当年开户当年仅交易5次以上总人数
	 */
	private Long wcl_dnkhdnjy_rs_gt5;

	/*********** "当年开户当年交易人数（无存量）end ************/

	/*********** "当年开户当年交易金额（总）begin ************/

	/**
	 * 当年开户当年仅交易1次总金额
	 */
	private BigDecimal dnkhdnjy_amt_1;

	/**
	 * 当年开户当年仅交易2次总金额
	 */
	private BigDecimal dnkhdnjy_amt_2;

	/**
	 * 当年开户当年仅交易3次总金额
	 */
	private BigDecimal dnkhdnjy_amt_3;

	/**
	 * 当年开户当年仅交易4次总金额
	 */
	private BigDecimal dnkhdnjy_amt_4;

	/**
	 * 当年开户当年仅交易5次总金额
	 */
	private BigDecimal dnkhdnjy_amt_5;

	/**
	 * 当年开户当年交易5次以上总金额
	 */
	private BigDecimal dnkhdnjy_amt_gt5;

	/*********** "当年开户当年交易金额（总）end ************/

	/*********** "当年开户当年交易金额（有存量）begin ************/

	/**
	 * 当年开户当年仅交易1次总金额
	 */
	private BigDecimal cl_dnkhdnjy_amt_1;

	/**
	 * 当年开户当年仅交易2次总金额
	 */
	private BigDecimal cl_dnkhdnjy_amt_2;

	/**
	 * 当年开户当年仅交易3次总金额
	 */
	private BigDecimal cl_dnkhdnjy_amt_3;

	/**
	 * 当年开户当年仅交易4次总金额
	 */
	private BigDecimal cl_dnkhdnjy_amt_4;

	/**
	 * 当年开户当年仅交易5次总金额
	 */
	private BigDecimal cl_dnkhdnjy_amt_5;

	/**
	 * 当年开户当年交易5次以上总金额
	 */
	private BigDecimal cl_dnkhdnjy_amt_gt5;

	/*********** "当年开户当年交易金额（有存量）end ************/

	/*********** "当年开户当年交易金额（无存量）begin ************/

	/**
	 * 当年开户当年仅交易1次总金额
	 */
	private BigDecimal wcl_dnkhdnjy_amt_1;

	/**
	 * 当年开户当年仅交易2次总金额
	 */
	private BigDecimal wcl_dnkhdnjy_amt_2;

	/**
	 * 当年开户当年仅交易3次总金额
	 */
	private BigDecimal wcl_dnkhdnjy_amt_3;

	/**
	 * 当年开户当年仅交易4次总金额
	 */
	private BigDecimal wcl_dnkhdnjy_amt_4;

	/**
	 * 当年开户当年仅交易5次总金额
	 */
	private BigDecimal wcl_dnkhdnjy_amt_5;

	/**
	 * 当年开户当年交易5次以上总金额
	 */
	private BigDecimal wcl_dnkhdnjy_amt_gt5;

	/*********** "当年开户当年交易金额（无存量）end ************/

	/*********** "当年开户当年交易人数占比begin ************/

	/**
	 * 当年开户当年仅交易1次的用户占比
	 */
	private BigDecimal dnkhdnjy_rate_1;

	/**
	 * 当年开户当年仅交易2次的用户占比
	 */
	private BigDecimal dnkhdnjy_rate_2;

	/**
	 * 当年开户当年仅交易3次的用户占比
	 */
	private BigDecimal dnkhdnjy_rate_3;

	/**
	 * 当年开户当年仅交易4次的用户占比
	 */
	private BigDecimal dnkhdnjy_rate_4;

	/**
	 * 当年开户当年仅交易5次的用户占比
	 */
	private BigDecimal dnkhdnjy_rate_5;

	/**
	 * 当年开户当年仅交易5次以上的用户占比
	 */
	private BigDecimal dnkhdnjy_rate_gt5;

	/*********** "当年开户当年交易人数占比end ************/

	/*********** "当年开户当年交易人数占比(有存量）begin ************/

	/**
	 * 当年开户当年仅交易1次的用户占比
	 */
	private BigDecimal cl_dnkhdnjy_rate_1;

	/**
	 * 当年开户当年仅交易2次的用户占比
	 */
	private BigDecimal cl_dnkhdnjy_rate_2;

	/**
	 * 当年开户当年仅交易3次的用户占比
	 */
	private BigDecimal cl_dnkhdnjy_rate_3;

	/**
	 * 当年开户当年仅交易4次的用户占比
	 */
	private BigDecimal cl_dnkhdnjy_rate_4;

	/**
	 * 当年开户当年仅交易5次的用户占比
	 */
	private BigDecimal cl_dnkhdnjy_rate_5;

	/**
	 * 当年开户当年仅交易5次以上的用户占比
	 */
	private BigDecimal cl_dnkhdnjy_rate_gt5;

	/*********** "当年开户当年交易人数占比(有存量）end ************/

	/*********** 当年开户当年交易人数占比(无存量）begin ************/

	/**
	 * 当年开户当年仅交易1次的用户占比
	 */
	private BigDecimal wcl_dnkhdnjy_rate_1;

	/**
	 * 当年开户当年仅交易2次的用户占比
	 */
	private BigDecimal wcl_dnkhdnjy_rate_2;

	/**
	 * 当年开户当年仅交易3次的用户占比
	 */
	private BigDecimal wcl_dnkhdnjy_rate_3;

	/**
	 * 当年开户当年仅交易4次的用户占比
	 */
	private BigDecimal wcl_dnkhdnjy_rate_4;

	/**
	 * 当年开户当年仅交易5次的用户占比
	 */
	private BigDecimal wcl_dnkhdnjy_rate_5;

	/**
	 * 当年开户当年仅交易5次以上的用户占比
	 */
	private BigDecimal wcl_dnkhdnjy_rate_gt5;

	/*********** 当年开户当年交易人数占比(无存量）end ************/
	
	
	
	
	/*********** 自定义字段 begin************/
	
	/**
	 * 复购人数
	 */
	private Long fgrs;
	/**
	 * 复购人数 存量
	 */
	private Long fgrs_cl;
	/**
	 * 复购人数无存量
	 */
	private Long fgrs_wcl;
	
	
	/*********** 自定义字段 end************/
	
	

	public Long getDnkh_zrs() {
		return dnkh_zrs;
	}

	public void setDnkh_zrs(Long dnkh_zrs) {
		this.dnkh_zrs = dnkh_zrs;
	}

	public Long getDnkhdnjy_rs_total() {
		return dnkhdnjy_rs_total;
	}

	public void setDnkhdnjy_rs_total(Long dnkhdnjy_rs_total) {
		this.dnkhdnjy_rs_total = dnkhdnjy_rs_total;
	}

	public BigDecimal getDnkhdnjy_amt_total() {
		return dnkhdnjy_amt_total;
	}

	public void setDnkhdnjy_amt_total(BigDecimal dnkhdnjy_amt_total) {
		this.dnkhdnjy_amt_total = dnkhdnjy_amt_total;
	}

	public Long getCl_dnkh_zrs() {
		return cl_dnkh_zrs;
	}

	public void setCl_dnkh_zrs(Long cl_dnkh_zrs) {
		this.cl_dnkh_zrs = cl_dnkh_zrs;
	}

	public Long getCl_dnkhdnjy_rs_total() {
		return cl_dnkhdnjy_rs_total;
	}

	public void setCl_dnkhdnjy_rs_total(Long cl_dnkhdnjy_rs_total) {
		this.cl_dnkhdnjy_rs_total = cl_dnkhdnjy_rs_total;
	}

	public BigDecimal getCl_dnkhdnjy_amt_total() {
		return cl_dnkhdnjy_amt_total;
	}

	public void setCl_dnkhdnjy_amt_total(BigDecimal cl_dnkhdnjy_amt_total) {
		this.cl_dnkhdnjy_amt_total = cl_dnkhdnjy_amt_total;
	}

	public Long getWcl_dnkh_zrs() {
		return wcl_dnkh_zrs;
	}

	public void setWcl_dnkh_zrs(Long wcl_dnkh_zrs) {
		this.wcl_dnkh_zrs = wcl_dnkh_zrs;
	}

	public Long getWcl_dnkhdnjy_rs_total() {
		return wcl_dnkhdnjy_rs_total;
	}

	public void setWcl_dnkhdnjy_rs_total(Long wcl_dnkhdnjy_rs_total) {
		this.wcl_dnkhdnjy_rs_total = wcl_dnkhdnjy_rs_total;
	}

	public BigDecimal getWcl_dnkhdnjy_amt_total() {
		return wcl_dnkhdnjy_amt_total;
	}

	public void setWcl_dnkhdnjy_amt_total(BigDecimal wcl_dnkhdnjy_amt_total) {
		this.wcl_dnkhdnjy_amt_total = wcl_dnkhdnjy_amt_total;
	}

	public Long getDnkhdnjy_rs_1() {
		return dnkhdnjy_rs_1;
	}

	public void setDnkhdnjy_rs_1(Long dnkhdnjy_rs_1) {
		this.dnkhdnjy_rs_1 = dnkhdnjy_rs_1;
	}

	public Long getDnkhdnjy_rs_2() {
		return dnkhdnjy_rs_2;
	}

	public void setDnkhdnjy_rs_2(Long dnkhdnjy_rs_2) {
		this.dnkhdnjy_rs_2 = dnkhdnjy_rs_2;
	}

	public Long getDnkhdnjy_rs_4() {
		return dnkhdnjy_rs_4;
	}

	public void setDnkhdnjy_rs_4(Long dnkhdnjy_rs_4) {
		this.dnkhdnjy_rs_4 = dnkhdnjy_rs_4;
	}

	public Long getDnkhdnjy_rs_5() {
		return dnkhdnjy_rs_5;
	}

	public void setDnkhdnjy_rs_5(Long dnkhdnjy_rs_5) {
		this.dnkhdnjy_rs_5 = dnkhdnjy_rs_5;
	}

	public Long getDnkhdnjy_rs_gt5() {
		return dnkhdnjy_rs_gt5;
	}

	public void setDnkhdnjy_rs_gt5(Long dnkhdnjy_rs_gt5) {
		this.dnkhdnjy_rs_gt5 = dnkhdnjy_rs_gt5;
	}

	public Long getCl_dnkhdnjy_rs_1() {
		return cl_dnkhdnjy_rs_1;
	}

	public void setCl_dnkhdnjy_rs_1(Long cl_dnkhdnjy_rs_1) {
		this.cl_dnkhdnjy_rs_1 = cl_dnkhdnjy_rs_1;
	}

	public Long getCl_dnkhdnjy_rs_2() {
		return cl_dnkhdnjy_rs_2;
	}

	public void setCl_dnkhdnjy_rs_2(Long cl_dnkhdnjy_rs_2) {
		this.cl_dnkhdnjy_rs_2 = cl_dnkhdnjy_rs_2;
	}

	public Long getCl_dnkhdnjy_rs_3() {
		return cl_dnkhdnjy_rs_3;
	}

	public void setCl_dnkhdnjy_rs_3(Long cl_dnkhdnjy_rs_3) {
		this.cl_dnkhdnjy_rs_3 = cl_dnkhdnjy_rs_3;
	}

	public Long getCl_dnkhdnjy_rs_4() {
		return cl_dnkhdnjy_rs_4;
	}

	public void setCl_dnkhdnjy_rs_4(Long cl_dnkhdnjy_rs_4) {
		this.cl_dnkhdnjy_rs_4 = cl_dnkhdnjy_rs_4;
	}

	public Long getCl_dnkhdnjy_rs_5() {
		return cl_dnkhdnjy_rs_5;
	}

	public void setCl_dnkhdnjy_rs_5(Long cl_dnkhdnjy_rs_5) {
		this.cl_dnkhdnjy_rs_5 = cl_dnkhdnjy_rs_5;
	}

	public Long getCl_dnkhdnjy_rs_gt5() {
		return cl_dnkhdnjy_rs_gt5;
	}

	public void setCl_dnkhdnjy_rs_gt5(Long cl_dnkhdnjy_rs_gt5) {
		this.cl_dnkhdnjy_rs_gt5 = cl_dnkhdnjy_rs_gt5;
	}

	public Long getWcl_dnkhdnjy_rs_0() {
		Long dnkhAll = wcl_dnkh_zrs;
		if(null!=wcl_dnkhdnjy_rs_1)
			dnkhAll-=wcl_dnkhdnjy_rs_1;
		if(null!=wcl_dnkhdnjy_rs_2)
			dnkhAll-=wcl_dnkhdnjy_rs_2;
		if(null!=wcl_dnkhdnjy_rs_3)
			dnkhAll-=wcl_dnkhdnjy_rs_3;
		if(null!=wcl_dnkhdnjy_rs_4)
			dnkhAll-=wcl_dnkhdnjy_rs_4;
		if(null!=wcl_dnkhdnjy_rs_5)
			dnkhAll-=wcl_dnkhdnjy_rs_5;
		if(null!=wcl_dnkhdnjy_rs_gt5)
			dnkhAll-=wcl_dnkhdnjy_rs_gt5;
		wcl_dnkhdnjy_rs_0=dnkhAll;
		return wcl_dnkhdnjy_rs_0;
	}

	public Long getWcl_dnkhdnjy_rs_1() {
		return wcl_dnkhdnjy_rs_1;
	}

	public void setWcl_dnkhdnjy_rs_1(Long wcl_dnkhdnjy_rs_1) {
		this.wcl_dnkhdnjy_rs_1 = wcl_dnkhdnjy_rs_1;
	}

	public Long getWcl_dnkhdnjy_rs_3() {
		return wcl_dnkhdnjy_rs_3;
	}

	public void setWcl_dnkhdnjy_rs_3(Long wcl_dnkhdnjy_rs_3) {
		this.wcl_dnkhdnjy_rs_3 = wcl_dnkhdnjy_rs_3;
	}

	public Long getWcl_dnkhdnjy_rs_5() {
		return wcl_dnkhdnjy_rs_5;
	}

	public void setWcl_dnkhdnjy_rs_5(Long wcl_dnkhdnjy_rs_5) {
		this.wcl_dnkhdnjy_rs_5 = wcl_dnkhdnjy_rs_5;
	}

	public Long getWcl_dnkhdnjy_rs_gt5() {
		return wcl_dnkhdnjy_rs_gt5;
	}

	public void setWcl_dnkhdnjy_rs_gt5(Long wcl_dnkhdnjy_rs_gt5) {
		this.wcl_dnkhdnjy_rs_gt5 = wcl_dnkhdnjy_rs_gt5;
	}

	public BigDecimal getDnkhdnjy_amt_1() {
		return dnkhdnjy_amt_1;
	}

	public void setDnkhdnjy_amt_1(BigDecimal dnkhdnjy_amt_1) {
		this.dnkhdnjy_amt_1 = dnkhdnjy_amt_1;
	}

	public BigDecimal getDnkhdnjy_amt_2() {
		return dnkhdnjy_amt_2;
	}

	public void setDnkhdnjy_amt_2(BigDecimal dnkhdnjy_amt_2) {
		this.dnkhdnjy_amt_2 = dnkhdnjy_amt_2;
	}

	public BigDecimal getDnkhdnjy_amt_3() {
		return dnkhdnjy_amt_3;
	}

	public void setDnkhdnjy_amt_3(BigDecimal dnkhdnjy_amt_3) {
		this.dnkhdnjy_amt_3 = dnkhdnjy_amt_3;
	}

	public BigDecimal getDnkhdnjy_amt_4() {
		return dnkhdnjy_amt_4;
	}

	public void setDnkhdnjy_amt_4(BigDecimal dnkhdnjy_amt_4) {
		this.dnkhdnjy_amt_4 = dnkhdnjy_amt_4;
	}

	public BigDecimal getDnkhdnjy_amt_5() {
		return dnkhdnjy_amt_5;
	}

	public void setDnkhdnjy_amt_5(BigDecimal dnkhdnjy_amt_5) {
		this.dnkhdnjy_amt_5 = dnkhdnjy_amt_5;
	}

	public BigDecimal getDnkhdnjy_amt_gt5() {
		return dnkhdnjy_amt_gt5;
	}

	public void setDnkhdnjy_amt_gt5(BigDecimal dnkhdnjy_amt_gt5) {
		this.dnkhdnjy_amt_gt5 = dnkhdnjy_amt_gt5;
	}

	public BigDecimal getCl_dnkhdnjy_amt_1() {
		return cl_dnkhdnjy_amt_1;
	}

	public void setCl_dnkhdnjy_amt_1(BigDecimal cl_dnkhdnjy_amt_1) {
		this.cl_dnkhdnjy_amt_1 = cl_dnkhdnjy_amt_1;
	}

	public BigDecimal getCl_dnkhdnjy_amt_2() {
		return cl_dnkhdnjy_amt_2;
	}

	public void setCl_dnkhdnjy_amt_2(BigDecimal cl_dnkhdnjy_amt_2) {
		this.cl_dnkhdnjy_amt_2 = cl_dnkhdnjy_amt_2;
	}

	public BigDecimal getCl_dnkhdnjy_amt_3() {
		return cl_dnkhdnjy_amt_3;
	}

	public void setCl_dnkhdnjy_amt_3(BigDecimal cl_dnkhdnjy_amt_3) {
		this.cl_dnkhdnjy_amt_3 = cl_dnkhdnjy_amt_3;
	}

	public BigDecimal getCl_dnkhdnjy_amt_4() {
		return cl_dnkhdnjy_amt_4;
	}

	public void setCl_dnkhdnjy_amt_4(BigDecimal cl_dnkhdnjy_amt_4) {
		this.cl_dnkhdnjy_amt_4 = cl_dnkhdnjy_amt_4;
	}

	public BigDecimal getCl_dnkhdnjy_amt_5() {
		return cl_dnkhdnjy_amt_5;
	}

	public void setCl_dnkhdnjy_amt_5(BigDecimal cl_dnkhdnjy_amt_5) {
		this.cl_dnkhdnjy_amt_5 = cl_dnkhdnjy_amt_5;
	}

	public BigDecimal getCl_dnkhdnjy_amt_gt5() {
		return cl_dnkhdnjy_amt_gt5;
	}

	public void setCl_dnkhdnjy_amt_gt5(BigDecimal cl_dnkhdnjy_amt_gt5) {
		this.cl_dnkhdnjy_amt_gt5 = cl_dnkhdnjy_amt_gt5;
	}

	public BigDecimal getWcl_dnkhdnjy_amt_1() {
		return wcl_dnkhdnjy_amt_1;
	}

	public void setWcl_dnkhdnjy_amt_1(BigDecimal wcl_dnkhdnjy_amt_1) {
		this.wcl_dnkhdnjy_amt_1 = wcl_dnkhdnjy_amt_1;
	}

	public BigDecimal getWcl_dnkhdnjy_amt_2() {
		return wcl_dnkhdnjy_amt_2;
	}

	public void setWcl_dnkhdnjy_amt_2(BigDecimal wcl_dnkhdnjy_amt_2) {
		this.wcl_dnkhdnjy_amt_2 = wcl_dnkhdnjy_amt_2;
	}

	public BigDecimal getWcl_dnkhdnjy_amt_3() {
		return wcl_dnkhdnjy_amt_3;
	}

	public void setWcl_dnkhdnjy_amt_3(BigDecimal wcl_dnkhdnjy_amt_3) {
		this.wcl_dnkhdnjy_amt_3 = wcl_dnkhdnjy_amt_3;
	}

	public BigDecimal getWcl_dnkhdnjy_amt_4() {
		return wcl_dnkhdnjy_amt_4;
	}

	public void setWcl_dnkhdnjy_amt_4(BigDecimal wcl_dnkhdnjy_amt_4) {
		this.wcl_dnkhdnjy_amt_4 = wcl_dnkhdnjy_amt_4;
	}

	public BigDecimal getWcl_dnkhdnjy_amt_5() {
		return wcl_dnkhdnjy_amt_5;
	}

	public void setWcl_dnkhdnjy_amt_5(BigDecimal wcl_dnkhdnjy_amt_5) {
		this.wcl_dnkhdnjy_amt_5 = wcl_dnkhdnjy_amt_5;
	}

	public BigDecimal getWcl_dnkhdnjy_amt_gt5() {
		return wcl_dnkhdnjy_amt_gt5;
	}

	public void setWcl_dnkhdnjy_amt_gt5(BigDecimal wcl_dnkhdnjy_amt_gt5) {
		this.wcl_dnkhdnjy_amt_gt5 = wcl_dnkhdnjy_amt_gt5;
	}

	public BigDecimal getDnkhdnjy_rate_1() {
		return dnkhdnjy_rate_1;
	}

	public void setDnkhdnjy_rate_1(BigDecimal dnkhdnjy_rate_1) {
		this.dnkhdnjy_rate_1 = dnkhdnjy_rate_1;
	}

	public BigDecimal getDnkhdnjy_rate_2() {
		return dnkhdnjy_rate_2;
	}

	public void setDnkhdnjy_rate_2(BigDecimal dnkhdnjy_rate_2) {
		this.dnkhdnjy_rate_2 = dnkhdnjy_rate_2;
	}

	public BigDecimal getDnkhdnjy_rate_3() {
		return dnkhdnjy_rate_3;
	}

	public void setDnkhdnjy_rate_3(BigDecimal dnkhdnjy_rate_3) {
		this.dnkhdnjy_rate_3 = dnkhdnjy_rate_3;
	}

	public BigDecimal getDnkhdnjy_rate_4() {
		return dnkhdnjy_rate_4;
	}

	public void setDnkhdnjy_rate_4(BigDecimal dnkhdnjy_rate_4) {
		this.dnkhdnjy_rate_4 = dnkhdnjy_rate_4;
	}

	public BigDecimal getDnkhdnjy_rate_5() {
		return dnkhdnjy_rate_5;
	}

	public void setDnkhdnjy_rate_5(BigDecimal dnkhdnjy_rate_5) {
		this.dnkhdnjy_rate_5 = dnkhdnjy_rate_5;
	}

	public BigDecimal getDnkhdnjy_rate_gt5() {
		return dnkhdnjy_rate_gt5;
	}

	public void setDnkhdnjy_rate_gt5(BigDecimal dnkhdnjy_rate_gt5) {
		this.dnkhdnjy_rate_gt5 = dnkhdnjy_rate_gt5;
	}

	public BigDecimal getCl_dnkhdnjy_rate_1() {
		return cl_dnkhdnjy_rate_1;
	}

	public void setCl_dnkhdnjy_rate_1(BigDecimal cl_dnkhdnjy_rate_1) {
		this.cl_dnkhdnjy_rate_1 = cl_dnkhdnjy_rate_1;
	}

	public BigDecimal getCl_dnkhdnjy_rate_2() {
		return cl_dnkhdnjy_rate_2;
	}

	public void setCl_dnkhdnjy_rate_2(BigDecimal cl_dnkhdnjy_rate_2) {
		this.cl_dnkhdnjy_rate_2 = cl_dnkhdnjy_rate_2;
	}

	public BigDecimal getCl_dnkhdnjy_rate_3() {
		return cl_dnkhdnjy_rate_3;
	}

	public void setCl_dnkhdnjy_rate_3(BigDecimal cl_dnkhdnjy_rate_3) {
		this.cl_dnkhdnjy_rate_3 = cl_dnkhdnjy_rate_3;
	}

	public BigDecimal getCl_dnkhdnjy_rate_4() {
		return cl_dnkhdnjy_rate_4;
	}

	public void setCl_dnkhdnjy_rate_4(BigDecimal cl_dnkhdnjy_rate_4) {
		this.cl_dnkhdnjy_rate_4 = cl_dnkhdnjy_rate_4;
	}

	public BigDecimal getCl_dnkhdnjy_rate_5() {
		return cl_dnkhdnjy_rate_5;
	}

	public void setCl_dnkhdnjy_rate_5(BigDecimal cl_dnkhdnjy_rate_5) {
		this.cl_dnkhdnjy_rate_5 = cl_dnkhdnjy_rate_5;
	}

	public BigDecimal getCl_dnkhdnjy_rate_gt5() {
		return cl_dnkhdnjy_rate_gt5;
	}

	public void setCl_dnkhdnjy_rate_gt5(BigDecimal cl_dnkhdnjy_rate_gt5) {
		this.cl_dnkhdnjy_rate_gt5 = cl_dnkhdnjy_rate_gt5;
	}

	public BigDecimal getWcl_dnkhdnjy_rate_1() {
		return wcl_dnkhdnjy_rate_1;
	}

	public void setWcl_dnkhdnjy_rate_1(BigDecimal wcl_dnkhdnjy_rate_1) {
		this.wcl_dnkhdnjy_rate_1 = wcl_dnkhdnjy_rate_1;
	}

	public BigDecimal getWcl_dnkhdnjy_rate_2() {
		return wcl_dnkhdnjy_rate_2;
	}

	public void setWcl_dnkhdnjy_rate_2(BigDecimal wcl_dnkhdnjy_rate_2) {
		this.wcl_dnkhdnjy_rate_2 = wcl_dnkhdnjy_rate_2;
	}

	public BigDecimal getWcl_dnkhdnjy_rate_3() {
		return wcl_dnkhdnjy_rate_3;
	}

	public void setWcl_dnkhdnjy_rate_3(BigDecimal wcl_dnkhdnjy_rate_3) {
		this.wcl_dnkhdnjy_rate_3 = wcl_dnkhdnjy_rate_3;
	}

	public BigDecimal getWcl_dnkhdnjy_rate_4() {
		return wcl_dnkhdnjy_rate_4;
	}

	public void setWcl_dnkhdnjy_rate_4(BigDecimal wcl_dnkhdnjy_rate_4) {
		this.wcl_dnkhdnjy_rate_4 = wcl_dnkhdnjy_rate_4;
	}

	public BigDecimal getWcl_dnkhdnjy_rate_5() {
		return wcl_dnkhdnjy_rate_5;
	}

	public void setWcl_dnkhdnjy_rate_5(BigDecimal wcl_dnkhdnjy_rate_5) {
		this.wcl_dnkhdnjy_rate_5 = wcl_dnkhdnjy_rate_5;
	}

	public BigDecimal getWcl_dnkhdnjy_rate_gt5() {
		return wcl_dnkhdnjy_rate_gt5;
	}

	public void setWcl_dnkhdnjy_rate_gt5(BigDecimal wcl_dnkhdnjy_rate_gt5) {
		this.wcl_dnkhdnjy_rate_gt5 = wcl_dnkhdnjy_rate_gt5;
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

	public Long getDnkhdnjy_rs_3() {
		return dnkhdnjy_rs_3;
	}

	public void setDnkhdnjy_rs_3(Long dnkhdnjy_rs_3) {
		this.dnkhdnjy_rs_3 = dnkhdnjy_rs_3;
	}

	public Long getWcl_dnkhdnjy_rs_2() {
		return wcl_dnkhdnjy_rs_2;
	}

	public void setWcl_dnkhdnjy_rs_2(Long wcl_dnkhdnjy_rs_2) {
		this.wcl_dnkhdnjy_rs_2 = wcl_dnkhdnjy_rs_2;
	}

	public Long getWcl_dnkhdnjy_rs_4() {
		return wcl_dnkhdnjy_rs_4;
	}

	public void setWcl_dnkhdnjy_rs_4(Long wcl_dnkhdnjy_rs_4) {
		this.wcl_dnkhdnjy_rs_4 = wcl_dnkhdnjy_rs_4;
	}

	public Long getFgrs() {
		Long fgrsAll = 0l;
		if(null!=dnkhdnjy_rs_2)
			fgrsAll+=dnkhdnjy_rs_2;
		if(null!=dnkhdnjy_rs_3)
			fgrsAll+=dnkhdnjy_rs_3;
		if(null!=dnkhdnjy_rs_4)
			fgrsAll+=dnkhdnjy_rs_4;
		if(null!=dnkhdnjy_rs_5)
			fgrsAll+=dnkhdnjy_rs_5;
		if(null!=dnkhdnjy_rs_gt5)
			fgrsAll+=dnkhdnjy_rs_gt5;
		fgrs = fgrsAll;	
		return fgrs;
	}

	public Long getFgrs_cl() {
		Long fgrsAll = 0l;
		if(null!=cl_dnkhdnjy_rs_2)
			fgrsAll+=cl_dnkhdnjy_rs_2;
		if(null!=cl_dnkhdnjy_rs_3)
			fgrsAll+=cl_dnkhdnjy_rs_3;
		if(null!=cl_dnkhdnjy_rs_4)
			fgrsAll+=cl_dnkhdnjy_rs_4;
		if(null!=cl_dnkhdnjy_rs_5)
			fgrsAll+=cl_dnkhdnjy_rs_5;
		if(null!=cl_dnkhdnjy_rs_gt5)
			fgrsAll+=cl_dnkhdnjy_rs_gt5;
		fgrs_cl = fgrsAll;	
		return fgrs_cl;
	}

	public Long getFgrs_wcl() {
		Long fgrsAll = 0l;
		if(null!=wcl_dnkhdnjy_rs_2)
			fgrsAll+=wcl_dnkhdnjy_rs_2;
		if(null!=wcl_dnkhdnjy_rs_3)
			fgrsAll+=wcl_dnkhdnjy_rs_3;
		if(null!=wcl_dnkhdnjy_rs_4)
			fgrsAll+=wcl_dnkhdnjy_rs_4;
		if(null!=wcl_dnkhdnjy_rs_5)
			fgrsAll+=wcl_dnkhdnjy_rs_5;
		if(null!=wcl_dnkhdnjy_rs_gt5)
			fgrsAll+=wcl_dnkhdnjy_rs_gt5;
		fgrs_wcl = fgrsAll;
		return fgrs_wcl;
	}
	
}