package com.howbuy.uaa.dto;

import java.math.BigDecimal;

public class CustomerResponseCollMapping {
	private String childname;// 下级名称
	private String discode; // 机构代码
	private String disname; // 机构名称
	private String tradechan; // 平台代码
	private String channame; // 平台名称
	private String hzlxcode; // 合作类型代码
	private String hzlx; // 合作类型
	private String outletcode; // 网点代码
	private String outletname; // 网点名称
	private String fundtype; // 基金类型
	private String fundtypename; // 基金类型名称
	private long ljzkhs;// 总开户数
	private long ljzyks;// 总验卡人数
	private long ljzjqs;// 总鉴权人数
	private long ljscjyzs;// 首次交易总人数
	private long zcys;// 总持有人数
	private long ljzbks;// 总绑卡人数
	// 率
	private BigDecimal ljzykl = new BigDecimal(0);// 总验卡率
	private BigDecimal ljzjql = new BigDecimal(0);// 总鉴权率
	private BigDecimal ljzjyl = new BigDecimal(0);// 总交易率
	private BigDecimal ljzjqjyl = new BigDecimal(0);// 总鉴权交易率
	public String getChildname() {
		return childname;
	}
	public void setChildname(String childname) {
		this.childname = childname;
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
	public long getLjzkhs() {
		return ljzkhs;
	}
	public void setLjzkhs(long ljzkhs) {
		this.ljzkhs = ljzkhs;
	}
	public long getLjzyks() {
		return ljzyks;
	}
	public void setLjzyks(long ljzyks) {
		this.ljzyks = ljzyks;
	}
	public long getLjzjqs() {
		return ljzjqs;
	}
	public void setLjzjqs(long ljzjqs) {
		this.ljzjqs = ljzjqs;
	}
	public long getLjscjyzs() {
		return ljscjyzs;
	}
	public void setLjscjyzs(long ljscjyzs) {
		this.ljscjyzs = ljscjyzs;
	}
	public long getZcys() {
		return zcys;
	}
	public void setZcys(long zcys) {
		this.zcys = zcys;
	}
	public long getLjzbks() {
		return ljzbks;
	}
	public void setLjzbks(long ljzbks) {
		this.ljzbks = ljzbks;
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
