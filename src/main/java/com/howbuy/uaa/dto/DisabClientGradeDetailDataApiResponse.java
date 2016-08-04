/**
 * 
 */
package com.howbuy.uaa.dto;

import java.math.BigDecimal;

/**
 * @author qiankun.li
 * 
 */
public class DisabClientGradeDetailDataApiResponse {

	/**
	 * 客户类型（1：只购买过货币基金；2：先买货币基金再买股的；3：先买股的）
	 */
	private int custtype;

	/**
	 * 部门代码
	 */
	private String outletcode;
	/**
	 * 客户人数
	 */
	private long total_cnt;
	/**
	 * 0分客户人数
	 */
	private long zero_cnt;
	/**
	 * 1分客户人数
	 */
	private long one_cnt;
	/**
	 * 2分客户人数
	 */
	private long two_cnt;
	/**
	 * 3分客户人数
	 */
	private long three_cnt;
	/**
	 * 4分客户人数
	 */
	private long four_cnt;
	/**
	 * 5分客户人数
	 */
	private long five_cnt;

	/**
	 * 投顾姓名
	 */
	private String consname;
	/**
	 * 客户人数
	 */
	private long ttl_cnt;
	/**
	 * 0分客户数
	 */
	private long cnt0;

	/**
	 * 1分客户数
	 */
	private long cnt1;

	/**
	 * 2分客户数
	 */
	private long cnt2;

	/**
	 * 3分客户数
	 */
	private long cnt3;
	/**
	 * 4分客户数
	 */
	private long cnt4;

	/**
	 * 5分客户数
	 */
	private long cnt5;
	/**
	 * 1+2+3分客户数
	 */
	private long cnt123;
	/**
	 * 1+2+3分客户占比
	 */
	private BigDecimal pct123;
	/**
	 * 4+5分客户数
	 */
	private long cnt45;
	/**
	 * 4+5分客户占比
	 */
	private BigDecimal pct45;

	public int getCusttype() {
		return custtype;
	}

	public void setCusttype(int custtype) {
		this.custtype = custtype;
	}

	public long getTotal_cnt() {
		return total_cnt;
	}

	public void setTotal_cnt(long total_cnt) {
		this.total_cnt = total_cnt;
	}

	public long getZero_cnt() {
		return zero_cnt;
	}

	public void setZero_cnt(long zero_cnt) {
		this.zero_cnt = zero_cnt;
	}

	public long getOne_cnt() {
		return one_cnt;
	}

	public void setOne_cnt(long one_cnt) {
		this.one_cnt = one_cnt;
	}

	public long getTwo_cnt() {
		return two_cnt;
	}

	public void setTwo_cnt(long two_cnt) {
		this.two_cnt = two_cnt;
	}

	public long getThree_cnt() {
		return three_cnt;
	}

	public void setThree_cnt(long three_cnt) {
		this.three_cnt = three_cnt;
	}

	public long getFour_cnt() {
		return four_cnt;
	}

	public void setFour_cnt(long four_cnt) {
		this.four_cnt = four_cnt;
	}

	public long getFive_cnt() {
		return five_cnt;
	}

	public void setFive_cnt(long five_cnt) {
		this.five_cnt = five_cnt;
	}

	public String getOutletcode() {
		return outletcode;
	}

	public void setOutletcode(String outletcode) {
		this.outletcode = outletcode;
	}

	public String getConsname() {
		return consname;
	}

	public void setConsname(String consname) {
		this.consname = consname;
	}

	public long getTtl_cnt() {
		return ttl_cnt;
	}

	public void setTtl_cnt(long ttl_cnt) {
		this.ttl_cnt = ttl_cnt;
	}

	public long getCnt0() {
		return cnt0;
	}

	public void setCnt0(long cnt0) {
		this.cnt0 = cnt0;
	}

	public long getCnt1() {
		return cnt1;
	}

	public void setCnt1(long cnt1) {
		this.cnt1 = cnt1;
	}

	public long getCnt2() {
		return cnt2;
	}

	public void setCnt2(long cnt2) {
		this.cnt2 = cnt2;
	}

	public long getCnt3() {
		return cnt3;
	}

	public void setCnt3(long cnt3) {
		this.cnt3 = cnt3;
	}

	public long getCnt4() {
		return cnt4;
	}

	public void setCnt4(long cnt4) {
		this.cnt4 = cnt4;
	}

	public long getCnt5() {
		return cnt5;
	}

	public void setCnt5(long cnt5) {
		this.cnt5 = cnt5;
	}

	public long getCnt123() {
		return cnt123;
	}

	public void setCnt123(long cnt123) {
		this.cnt123 = cnt123;
	}

	public long getCnt45() {
		return cnt45;
	}

	public void setCnt45(long cnt45) {
		this.cnt45 = cnt45;
	}

	public BigDecimal getPct123() {
		return pct123;
	}

	public void setPct123(BigDecimal pct123) {
		this.pct123 = pct123;
	}

	public BigDecimal getPct45() {
		return pct45;
	}

	public void setPct45(BigDecimal pct45) {
		this.pct45 = pct45;
	}

}
