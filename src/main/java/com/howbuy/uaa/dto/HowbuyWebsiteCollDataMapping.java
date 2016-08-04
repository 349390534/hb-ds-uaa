package com.howbuy.uaa.dto;

import java.math.BigDecimal;

import com.howbuy.uaa.common.contants.UaaContants;

/**
 * @author qiankun.li 好买网站查询汇总数据
 */
public class HowbuyWebsiteCollDataMapping {

	private String statdt;// 日期
	private String channel;// 渠道编号
	private String channelName;// 渠道名称
	private String channelType;// 渠道类型
	private Long enter;// 进入次数
	private Long pv;// PV
	private Long uv;// UV
	private Long validuv;// 有效UV
	private Long gmuv;// 公募基金档案页UV
	private Long simuuv;// 高端详情页UV

	private Long drkh;// 开户人数
	private Long drbk;// 当日绑卡人数
	
	private BigDecimal xdzhl = new BigDecimal(0);// 下单转化率

	private Long drxdcjrs;// 成交人数

	private Long drxdcjbs;// 成交笔数

	private BigDecimal drxdcjje;// 成交金额
	private BigDecimal cjzhl = new BigDecimal(0);// 成交转化率

	private Long persons;// 交易人数
	private Long bills;// 交易笔数
	private BigDecimal amt;// 交易金额

	public String getStatdt() {
		return statdt;
	}

	public void setStatdt(String statdt) {
		this.statdt = statdt;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public Long getEnter() {
		return enter;
	}

	public void setEnter(Long enter) {
		this.enter = enter;
	}

	public Long getPv() {
		return pv;
	}

	public void setPv(Long pv) {
		this.pv = pv;
	}

	public Long getUv() {
		return uv;
	}

	public void setUv(Long uv) {
		this.uv = uv;
	}

	public Long getValiduv() {
		return validuv;
	}

	public void setValiduv(Long validuv) {
		this.validuv = validuv;
	}

	public Long getGmuv() {
		return gmuv;
	}

	public void setGmuv(Long gmuv) {
		this.gmuv = gmuv;
	}

	public Long getDrkh() {
		return drkh;
	}

	public void setDrkh(Long drkh) {
		this.drkh = drkh;
	}

	public Long getDrbk() {
		return drbk;
	}

	public void setDrbk(Long drbk) {
		this.drbk = drbk;
	}

	public BigDecimal getXdzhl() {

		if(uv==null||uv==0||persons==null||persons==0){
			return xdzhl;
		}
		BigDecimal p = new BigDecimal(persons);
		BigDecimal u = new BigDecimal(uv);
		this.xdzhl = p.divide(u,UaaContants.SCALE_LV, BigDecimal.ROUND_HALF_UP);
		return xdzhl;
	}

	public void setXdzhl(BigDecimal xdzhl) {
		this.xdzhl = xdzhl;
	}

	public Long getDrxdcjrs() {
		return drxdcjrs;
	}

	public void setDrxdcjrs(Long drxdcjrs) {
		this.drxdcjrs = drxdcjrs;
	}

	public Long getDrxdcjbs() {
		return drxdcjbs;
	}

	public void setDrxdcjbs(Long drxdcjbs) {
		this.drxdcjbs = drxdcjbs;
	}

	public BigDecimal getDrxdcjje() {
		return drxdcjje;
	}

	public void setDrxdcjje(BigDecimal drxdcjje) {
		this.drxdcjje = drxdcjje;
	}

	public BigDecimal getCjzhl() {

		if(null==drxdcjrs||uv==null||drxdcjrs==0||uv==0){
			return cjzhl;
		}
		BigDecimal dr = new BigDecimal(drxdcjrs);
		BigDecimal u = new BigDecimal(uv);
		this.cjzhl =dr.divide(u,UaaContants.SCALE_LV, BigDecimal.ROUND_HALF_UP);
		return cjzhl;
	}

	public void setCjzhl(BigDecimal cjzhl) {
		this.cjzhl = cjzhl;
	}

	public String getChannelType() {
		return channelType;
	}

	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public Long getSimuuv() {
		return simuuv;
	}

	public void setSimuuv(Long simuuv) {
		this.simuuv = simuuv;
	}

	public Long getPersons() {
		return persons;
	}

	public void setPersons(Long persons) {
		this.persons = persons;
	}

	public Long getBills() {
		return bills;
	}

	public void setBills(Long bills) {
		this.bills = bills;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

}
