package com.howbuy.uaa.dto;

import java.math.BigDecimal;

public class FundResponseMapping {
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
	private long drkh;// 当日开户人数
	private long drscbk;// 当日首次绑卡人数
	private long drscyk;// 当日首次验卡人数
	private long drscjq;// 当日首次鉴权人数
	private long drkhdrbk;// 当日开户当日绑卡人数
	private long drkhdryk;// 当日开户当日验卡人数
	private long drkhdrjq;// 当日开户当日鉴权人数
	private long drxzjyrs;// 当日新增交易人数
	private BigDecimal drxzjyje;// 当日新增交易金额
	private long drkhdrjybs;// 当日开户当日交易笔数
	private BigDecimal drkhdrjyje;// 当日开户当日交易金额
	private long drxdbs;// 单日下单笔数
	private BigDecimal drxdje;// 单日下单金额
	private long drqrjycjbs;// 当日确认交易的成交笔数
	private BigDecimal drqrjycjje;// 当日确认交易的成交金额
	private long drkhdrjyrs;// 当日开户当日交易人数
	private long drxdrs;// 当日下单人数
	private long drqrjycjrs;// 当日确认交易的成交人数

	/**
	 * 累计到当日的总客户数（开户）
	 */
	private long ljkhs;
	/**
	 * 累计到当日的总绑卡人数
	 */
	private long ljbks;
	/**
	 * 累计到当日的总验卡人数
	 */
	private long ljyks;
	/**
	 * 累计到当日的总鉴权人数
	 */
	private long ljjqs;
	/**
	 * 累计到当日的开户首次交易总人数
	 */
	private long ljscjys;
	/**
	 * 当前持仓份额不为0的人数
	 */
	private long cys;

	/**
	 * 当日支付人数
	 */
	private long drzfrs;
	/**
	 * 当日支付笔数
	 */
	private long drzfbs;
	/**
	 * 当日支付金额
	 */
	private BigDecimal drzfje;
	// 人均
	private BigDecimal rjxdje = new BigDecimal(0);// 人均下单金额
	private BigDecimal rjcjje = new BigDecimal(0);// 人均成交金额
	private BigDecimal rjzfje = new BigDecimal(0);// 人均支付金额

	// 率
	private BigDecimal drkhbkl = new BigDecimal(0);// 当日开户绑卡率
	private BigDecimal drkhykl = new BigDecimal(0);// 当日开户验卡率
	private BigDecimal drkhjql = new BigDecimal(0);// 当日开户鉴权率
	private BigDecimal drkhjyl = new BigDecimal(0);// 当日开户交易率
	private BigDecimal xdzhl = new BigDecimal(0);// 下单转化率
	private BigDecimal cjzhl = new BigDecimal(0);// 成交转化率

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

	public long getDrkh() {
		return drkh;
	}

	public void setDrkh(long drkh) {
		this.drkh = drkh;
	}

	public long getDrscbk() {
		return drscbk;
	}

	public void setDrscbk(long drscbk) {
		this.drscbk = drscbk;
	}

	public long getDrscyk() {
		return drscyk;
	}

	public void setDrscyk(long drscyk) {
		this.drscyk = drscyk;
	}

	public long getDrscjq() {
		return drscjq;
	}

	public void setDrscjq(long drscjq) {
		this.drscjq = drscjq;
	}

	public long getDrkhdrbk() {
		return drkhdrbk;
	}

	public void setDrkhdrbk(long drkhdrbk) {
		this.drkhdrbk = drkhdrbk;
	}

	public long getDrkhdryk() {
		return drkhdryk;
	}

	public void setDrkhdryk(long drkhdryk) {
		this.drkhdryk = drkhdryk;
	}

	public long getDrkhdrjq() {
		return drkhdrjq;
	}

	public void setDrkhdrjq(long drkhdrjq) {
		this.drkhdrjq = drkhdrjq;
	}

	public long getDrxzjyrs() {
		return drxzjyrs;
	}

	public void setDrxzjyrs(long drxzjyrs) {
		this.drxzjyrs = drxzjyrs;
	}

	public BigDecimal getDrxzjyje() {
		return drxzjyje;
	}

	public void setDrxzjyje(BigDecimal drxzjyje) {
		this.drxzjyje = drxzjyje;
	}

	public long getDrkhdrjybs() {
		return drkhdrjybs;
	}

	public void setDrkhdrjybs(long drkhdrjybs) {
		this.drkhdrjybs = drkhdrjybs;
	}

	public BigDecimal getDrkhdrjyje() {
		return drkhdrjyje;
	}

	public void setDrkhdrjyje(BigDecimal drkhdrjyje) {
		this.drkhdrjyje = drkhdrjyje;
	}

	public long getDrxdbs() {
		return drxdbs;
	}

	public void setDrxdbs(long drxdbs) {
		this.drxdbs = drxdbs;
	}

	public BigDecimal getDrxdje() {
		return drxdje;
	}

	public void setDrxdje(BigDecimal drxdje) {
		this.drxdje = drxdje;
	}

	public long getDrqrjycjbs() {
		return drqrjycjbs;
	}

	public void setDrqrjycjbs(long drqrjycjbs) {
		this.drqrjycjbs = drqrjycjbs;
	}

	public BigDecimal getDrqrjycjje() {
		return drqrjycjje;
	}

	public void setDrqrjycjje(BigDecimal drqrjycjje) {
		this.drqrjycjje = drqrjycjje;
	}

	public long getDrkhdrjyrs() {
		return drkhdrjyrs;
	}

	public void setDrkhdrjyrs(long drkhdrjyrs) {
		this.drkhdrjyrs = drkhdrjyrs;
	}

	public long getDrxdrs() {
		return drxdrs;
	}

	public void setDrxdrs(long drxdrs) {
		this.drxdrs = drxdrs;
	}

	public long getDrqrjycjrs() {
		return drqrjycjrs;
	}

	public void setDrqrjycjrs(long drqrjycjrs) {
		this.drqrjycjrs = drqrjycjrs;
	}

	public BigDecimal getRjxdje() {
		return rjxdje;
	}

	public void setRjxdje(BigDecimal rjxdje) {
		this.rjxdje = rjxdje;
	}

	public BigDecimal getRjcjje() {
		return rjcjje;
	}

	public void setRjcjje(BigDecimal rjcjje) {
		this.rjcjje = rjcjje;
	}

	public BigDecimal getDrkhbkl() {
		return drkhbkl;
	}

	public void setDrkhbkl(BigDecimal drkhbkl) {
		this.drkhbkl = drkhbkl;
	}

	public BigDecimal getDrkhykl() {
		return drkhykl;
	}

	public void setDrkhykl(BigDecimal drkhykl) {
		this.drkhykl = drkhykl;
	}

	public BigDecimal getDrkhjql() {
		return drkhjql;
	}

	public void setDrkhjql(BigDecimal drkhjql) {
		this.drkhjql = drkhjql;
	}

	public BigDecimal getDrkhjyl() {
		return drkhjyl;
	}

	public void setDrkhjyl(BigDecimal drkhjyl) {
		this.drkhjyl = drkhjyl;
	}

	public BigDecimal getXdzhl() {
		return xdzhl;
	}

	public void setXdzhl(BigDecimal xdzhl) {
		this.xdzhl = xdzhl;
	}

	public BigDecimal getCjzhl() {
		return cjzhl;
	}

	public void setCjzhl(BigDecimal cjzhl) {
		this.cjzhl = cjzhl;
	}

	public long getLjkhs() {
		return ljkhs;
	}

	public void setLjkhs(long ljkhs) {
		this.ljkhs = ljkhs;
	}

	public long getLjbks() {
		return ljbks;
	}

	public void setLjbks(long ljbks) {
		this.ljbks = ljbks;
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

	public long getDrzfrs() {
		return drzfrs;
	}

	public void setDrzfrs(long drzfrs) {
		this.drzfrs = drzfrs;
	}

	public long getDrzfbs() {
		return drzfbs;
	}

	public void setDrzfbs(long drzfbs) {
		this.drzfbs = drzfbs;
	}

	public BigDecimal getDrzfje() {
		return drzfje;
	}

	public void setDrzfje(BigDecimal drzfje) {
		this.drzfje = drzfje;
	}

	public BigDecimal getRjzfje() {
		return rjzfje;
	}

	public void setRjzfje(BigDecimal rjzfje) {
		this.rjzfje = rjzfje;
	}

}
