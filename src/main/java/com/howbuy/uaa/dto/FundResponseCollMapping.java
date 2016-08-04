package com.howbuy.uaa.dto;

import java.math.BigDecimal;

/**
 * @author qiankun.li
 * 
 */
public class FundResponseCollMapping {
	// private String fundName;//基金类型名称
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
	private long zkh;// 总开户数
	private long sczbk;// 首次总绑卡数
	private long sczyk;// 首次总验卡数
	private long sczjq;// 首次总鉴权数
	private long drkhdrzbk;// 当日开户当日总绑卡数
	private long drkhdrzyk;// 当日开户当日总验卡数
	private long drkhdrzjq;// 当日开户当日总鉴权数
	private long xzjyzrs;// 新增交易总人数
	private BigDecimal xzjyzje;// 新增交易总金额
	private long drkhdrjyzbs;// 当日开户当日交易总笔数
	private BigDecimal drkhdrjyzje;// 当日开户当日交易总金额
	private long xdzbs;// 下单总笔数
	private BigDecimal xdzje;// 下单总金额
	private long qrjycjzbs;// 确认交易成交总笔数
	private BigDecimal qrjycjzje;// 确认交易成交总金额
	private long drkhdrjyzrs;// 当日开户当日交易总人数
	private long xdzrs;// 下单总人数
	private long qrjycjzrs;// 确认交易成交总人数

	/**
	 * 当日支付人数
	 */
	private long zzfrs;
	/**
	 * 当日支付笔数
	 */
	private long zzfbs;
	/**
	 * 当日支付金额
	 */
	private BigDecimal zzfje;

	// 人均
	private BigDecimal rjxdzje = new BigDecimal(0);// 人均下单总金额
	private BigDecimal rjcjzje = new BigDecimal(0);// 人均成交总金额
	private BigDecimal rjzzfje = new BigDecimal(0);// 人均支付总金额

	// 率
	private BigDecimal drkhzbkl = new BigDecimal(0);// 当日开户总绑卡率
	private BigDecimal drkhzykl = new BigDecimal(0);// 当日开户总验卡率
	private BigDecimal drkhzjql = new BigDecimal(0);// 当日开户总鉴权率
	private BigDecimal drkhzjyl = new BigDecimal(0);// 当日开户总交易率
	private BigDecimal xdzzhl = new BigDecimal(0);// 下单总转化率
	private BigDecimal cjzzhl = new BigDecimal(0);// 成交总转化率

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

	public long getZkh() {
		return zkh;
	}

	public void setZkh(long zkh) {
		this.zkh = zkh;
	}

	public long getSczbk() {
		return sczbk;
	}

	public void setSczbk(long sczbk) {
		this.sczbk = sczbk;
	}

	public long getSczyk() {
		return sczyk;
	}

	public void setSczyk(long sczyk) {
		this.sczyk = sczyk;
	}

	public long getSczjq() {
		return sczjq;
	}

	public void setSczjq(long sczjq) {
		this.sczjq = sczjq;
	}

	public long getDrkhdrzbk() {
		return drkhdrzbk;
	}

	public void setDrkhdrzbk(long drkhdrzbk) {
		this.drkhdrzbk = drkhdrzbk;
	}

	public long getDrkhdrzyk() {
		return drkhdrzyk;
	}

	public void setDrkhdrzyk(long drkhdrzyk) {
		this.drkhdrzyk = drkhdrzyk;
	}

	public long getDrkhdrzjq() {
		return drkhdrzjq;
	}

	public void setDrkhdrzjq(long drkhdrzjq) {
		this.drkhdrzjq = drkhdrzjq;
	}

	public long getXzjyzrs() {
		return xzjyzrs;
	}

	public void setXzjyzrs(long xzjyzrs) {
		this.xzjyzrs = xzjyzrs;
	}

	public BigDecimal getXzjyzje() {
		return xzjyzje;
	}

	public void setXzjyzje(BigDecimal xzjyzje) {
		this.xzjyzje = xzjyzje;
	}

	public long getDrkhdrjyzbs() {
		return drkhdrjyzbs;
	}

	public void setDrkhdrjyzbs(long drkhdrjyzbs) {
		this.drkhdrjyzbs = drkhdrjyzbs;
	}

	public BigDecimal getDrkhdrjyzje() {
		return drkhdrjyzje;
	}

	public void setDrkhdrjyzje(BigDecimal drkhdrjyzje) {
		this.drkhdrjyzje = drkhdrjyzje;
	}

	public long getXdzbs() {
		return xdzbs;
	}

	public void setXdzbs(long xdzbs) {
		this.xdzbs = xdzbs;
	}

	public BigDecimal getXdzje() {
		return xdzje;
	}

	public void setXdzje(BigDecimal xdzje) {
		this.xdzje = xdzje;
	}

	public long getQrjycjzbs() {
		return qrjycjzbs;
	}

	public void setQrjycjzbs(long qrjycjzbs) {
		this.qrjycjzbs = qrjycjzbs;
	}

	public BigDecimal getQrjycjzje() {
		return qrjycjzje;
	}

	public void setQrjycjzje(BigDecimal qrjycjzje) {
		this.qrjycjzje = qrjycjzje;
	}

	public long getDrkhdrjyzrs() {
		return drkhdrjyzrs;
	}

	public void setDrkhdrjyzrs(long drkhdrjyzrs) {
		this.drkhdrjyzrs = drkhdrjyzrs;
	}

	public long getXdzrs() {
		return xdzrs;
	}

	public void setXdzrs(long xdzrs) {
		this.xdzrs = xdzrs;
	}

	public long getQrjycjzrs() {
		return qrjycjzrs;
	}

	public void setQrjycjzrs(long qrjycjzrs) {
		this.qrjycjzrs = qrjycjzrs;
	}

	public BigDecimal getRjxdzje() {
		return rjxdzje;
	}

	public void setRjxdzje(BigDecimal rjxdzje) {
		this.rjxdzje = rjxdzje;
	}

	public BigDecimal getRjcjzje() {
		return rjcjzje;
	}

	public void setRjcjzje(BigDecimal rjcjzje) {
		this.rjcjzje = rjcjzje;
	}

	public BigDecimal getDrkhzbkl() {
		return drkhzbkl;
	}

	public void setDrkhzbkl(BigDecimal drkhzbkl) {
		this.drkhzbkl = drkhzbkl;
	}

	public BigDecimal getDrkhzykl() {
		return drkhzykl;
	}

	public void setDrkhzykl(BigDecimal drkhzykl) {
		this.drkhzykl = drkhzykl;
	}

	public BigDecimal getDrkhzjql() {
		return drkhzjql;
	}

	public void setDrkhzjql(BigDecimal drkhzjql) {
		this.drkhzjql = drkhzjql;
	}

	public BigDecimal getDrkhzjyl() {
		return drkhzjyl;
	}

	public void setDrkhzjyl(BigDecimal drkhzjyl) {
		this.drkhzjyl = drkhzjyl;
	}

	public BigDecimal getXdzzhl() {
		return xdzzhl;
	}

	public void setXdzzhl(BigDecimal xdzzhl) {
		this.xdzzhl = xdzzhl;
	}

	public BigDecimal getCjzzhl() {
		return cjzzhl;
	}

	public void setCjzzhl(BigDecimal cjzzhl) {
		this.cjzzhl = cjzzhl;
	}

	public long getZzfrs() {
		return zzfrs;
	}

	public void setZzfrs(long zzfrs) {
		this.zzfrs = zzfrs;
	}

	public long getZzfbs() {
		return zzfbs;
	}

	public void setZzfbs(long zzfbs) {
		this.zzfbs = zzfbs;
	}

	public BigDecimal getZzfje() {
		return zzfje;
	}

	public void setZzfje(BigDecimal zzfje) {
		this.zzfje = zzfje;
	}

	public BigDecimal getRjzzfje() {
		return rjzzfje;
	}

	public void setRjzzfje(BigDecimal rjzzfje) {
		this.rjzzfje = rjzzfje;
	}

}
