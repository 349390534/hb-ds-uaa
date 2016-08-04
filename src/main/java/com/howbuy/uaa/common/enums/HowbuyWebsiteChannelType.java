package com.howbuy.uaa.common.enums;

/**
 * @author qiankun.li 好买网站渠道类型
 */
public enum HowbuyWebsiteChannelType {
	/**
	 * 直接访问
	 */
	CHANNEL_ZJFW("直接访问", "1"), 
	/**
	 * 其他渠道
	 */
	CHANNEL_OTHER("其他渠道", "2"),
	/**
	 * 搜索引擎
	 */
	CHANNEL_SSYQ("搜索引擎", "3"), 
	/**
	 * 推广渠道
	 */
	CHANNEL_TGQD("推广渠道", "4"),
	/**
	 * 渠道汇总数据
	 */
	CHANNEL_COL("渠道汇总", "5"),
	
	;
	private HowbuyWebsiteChannelType(String channelName, String channelType) {
		this.channelName = channelName;
		this.channelType = channelType;
	}

	/**
	 * 渠道名称
	 */
	private String channelName;
	/**
	 * 渠道类型
	 */
	private String channelType;

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getChannelType() {
		return channelType;
	}

	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}

	public static HowbuyWebsiteChannelType findByType(String type) {
		HowbuyWebsiteChannelType[] channelTypes = HowbuyWebsiteChannelType.class
				.getEnumConstants();
		for (HowbuyWebsiteChannelType channelType : channelTypes) {
			if (channelType.getChannelType().equals(type)) {
				return channelType;
			}
		}
		return null;
	}

}
