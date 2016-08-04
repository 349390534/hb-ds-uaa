package com.howbuy.uaa.dto;

/**
 * @author qiankun.li 好买H5活动数据查询汇总数据
 */
public class H5ActivityCollDataMapping {

	private String statdt;// 日期
	private String channel;// 渠道编号
	private String channelName;// 渠道名称
	private String channelType;// 渠道类型
	/**
	 * 总PV
	 */
	private Long pv;
	/**
	 * 总UV
	 */
	private Long uv;
	/**
	 * 总开户人数
	 */
	private Long openaccNum;

	/**
	 * 领红包首页UV
	 */
	private Long hongbaoIndexUv;

	/**
	 * H5开户首页UV
	 */
	private Long h5OpenAccIndexPageUv;

	/**
	 * 身份验证页UV
	 */
	private Long authPageUv;

	/**
	 * H5开户结果页UV
	 */
	private Long h5OpenAccResultPageUv;

	private Long enter;

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

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getChannelType() {
		return channelType;
	}

	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}

	public Long getOpenaccNum() {
		return openaccNum;
	}

	public void setOpenaccNum(Long openaccNum) {
		this.openaccNum = openaccNum;
	}

	public Long getHongbaoIndexUv() {
		return hongbaoIndexUv;
	}

	public void setHongbaoIndexUv(Long hongbaoIndexUv) {
		this.hongbaoIndexUv = hongbaoIndexUv;
	}

	public Long getH5OpenAccIndexPageUv() {
		return h5OpenAccIndexPageUv;
	}

	public void setH5OpenAccIndexPageUv(Long h5OpenAccIndexPageUv) {
		this.h5OpenAccIndexPageUv = h5OpenAccIndexPageUv;
	}

	public Long getAuthPageUv() {
		return authPageUv;
	}

	public void setAuthPageUv(Long authPageUv) {
		this.authPageUv = authPageUv;
	}

	public Long getH5OpenAccResultPageUv() {
		return h5OpenAccResultPageUv;
	}

	public void setH5OpenAccResultPageUv(Long h5OpenAccResultPageUv) {
		this.h5OpenAccResultPageUv = h5OpenAccResultPageUv;
	}

	public Long getEnter() {
		return enter;
	}

	public void setEnter(Long enter) {
		this.enter = enter;
	}

}
