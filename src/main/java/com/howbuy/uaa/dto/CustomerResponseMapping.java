package com.howbuy.uaa.dto;

import java.math.BigDecimal;

public class CustomerResponseMapping {
	private String statdt;// 日期
	private String discode;// 机构代码
	private String disname;// 机构名称
	private String tradechan;// 平台代码
	private String channame;// 平台名称
	private String hzlxcode;// 合作类型代码
	private String hzlx;// 合作类型
	private String outletcode;// 网点代码
	private String outletname;// 网点名称
	private String fundtype;// 基金类型
	private String fundtypename;
	private long ljkhs;// 总开户数
	private long ljyks;// 总验卡人数
	private long ljjqs;// 总鉴权人数
	private long ljscjys;// 首次交易总人数
	private long cys;// 总持有人数
	private long ljbks;// 总绑卡人数

	// 率
	private BigDecimal ljykl = new BigDecimal(0);// 总验卡率
	private BigDecimal ljjql = new BigDecimal(0);// 总鉴权率
	private BigDecimal ljjyl = new BigDecimal(0);// 总交易率
	private BigDecimal ljjqjyl = new BigDecimal(0);// 总鉴权交易率
	
	private BigDecimal ljzykl = new BigDecimal(0);// 总验卡率
	private BigDecimal ljzjql = new BigDecimal(0);// 总鉴权率
	private BigDecimal ljzjyl = new BigDecimal(0);// 总交易率
	private BigDecimal ljzjqjyl = new BigDecimal(0);// 总鉴权交易率

	public String getStatdt() {
		return statdt;
	}

	public void setStatdt(String statdt) {
		this.statdt = statdt;
	}

	public String getDiscode() {
		return discode;
	}

	public void setDiscode(String discode) {
		this.discode = discode;
	}

	public String getDisname() {
		return disname;
	}

	public void setDisname(String disname) {
		this.disname = disname;
	}

	public String getTradechan() {
		return tradechan;
	}

	public void setTradechan(String tradechan) {
		this.tradechan = tradechan;
	}

	public String getChanname() {
		return channame;
	}

	public void setChanname(String channame) {
		this.channame = channame;
	}

	public String getHzlxcode() {
		return hzlxcode;
	}

	public void setHzlxcode(String hzlxcode) {
		this.hzlxcode = hzlxcode;
	}

	public String getHzlx() {
		return hzlx;
	}

	public void setHzlx(String hzlx) {
		this.hzlx = hzlx;
	}

	public String getOutletcode() {
		return outletcode;
	}

	public void setOutletcode(String outletcode) {
		this.outletcode = outletcode;
	}

	public String getOutletname() {
		return outletname;
	}

	public void setOutletname(String outletname) {
		this.outletname = outletname;
	}

	public String getFundtype() {
		return fundtype;
	}

	public void setFundtype(String fundtype) {
		this.fundtype = fundtype;
	}

	public String getFundtypename() {
		return fundtypename;
	}

	public void setFundtypename(String fundtypename) {
		this.fundtypename = fundtypename;
	}

	public long getLjkhs() {
		return ljkhs;
	}

	public void setLjkhs(long ljkhs) {
		this.ljkhs = ljkhs;
	}

	public long getLjyks() {
		return ljyks;
	}

	public void setLjyks(long ljyks) {
		this.ljyks = ljyks;
	}

	public long getLjjqs() {
		return ljjqs;
	}

	public void setLjjqs(long ljjqs) {
		this.ljjqs = ljjqs;
	}

	public long getLjscjys() {
		return ljscjys;
	}

	public void setLjscjys(long ljscjys) {
		this.ljscjys = ljscjys;
	}

	public long getCys() {
		return cys;
	}

	public void setCys(long cys) {
		this.cys = cys;
	}

	public long getLjbks() {
		return ljbks;
	}

	public void setLjbks(long ljbks) {
		this.ljbks = ljbks;
	}

	public BigDecimal getLjykl() {
		return ljykl;
	}

	public void setLjykl(BigDecimal ljykl) {
		this.ljykl = ljykl;
	}

	public BigDecimal getLjjql() {
		return ljjql;
	}

	public void setLjjql(BigDecimal ljjql) {
		this.ljjql = ljjql;
	}

	public BigDecimal getLjjyl() {
		return ljjyl;
	}

	public void setLjjyl(BigDecimal ljjyl) {
		this.ljjyl = ljjyl;
	}

	public BigDecimal getLjjqjyl() {
		return ljjqjyl;
	}

	public void setLjjqjyl(BigDecimal ljjqjyl) {
		this.ljjqjyl = ljjqjyl;
	}

	public BigDecimal getLjzykl() {
		return ljzykl;
	}

	public void setLjzykl(BigDecimal ljzykl) {
		this.ljzykl = ljzykl;
	}

	public BigDecimal getLjzjql() {
		return ljzjql;
	}

	public void setLjzjql(BigDecimal ljzjql) {
		this.ljzjql = ljzjql;
	}

	public BigDecimal getLjzjyl() {
		return ljzjyl;
	}

	public void setLjzjyl(BigDecimal ljzjyl) {
		this.ljzjyl = ljzjyl;
	}

	public BigDecimal getLjzjqjyl() {
		return ljzjqjyl;
	}

	public void setLjzjqjyl(BigDecimal ljzjqjyl) {
		this.ljzjqjyl = ljzjqjyl;
	}
	
}
