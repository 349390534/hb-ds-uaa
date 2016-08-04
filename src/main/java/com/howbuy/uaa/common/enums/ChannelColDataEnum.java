package com.howbuy.uaa.common.enums;

/**
 * @author qiankun.li 渠道汇总数据枚举
 */
public enum ChannelColDataEnum {
	/**
	 * 搜索归总
	 */
	CHANNEL_COL_SSYQ("搜索归总", "9999999997"),
	/**
	 * 推广归总
	 */
	CHANNEL_COL_TGQD("推广归总", "9999999996"),
	/**
	 * 其他归总
	 */
	CHANNEL_COL_OTHER("其他归总", "9999999995"),
	/**
	 * 直接归总
	 */
	CHANNEL_COL_ZJ("直接归总", "9999999994"),
	/**
	 * 所有渠道归总
	 */
	CHANNEL_COL_ALL("所有渠道归总", "9999999993");

	private ChannelColDataEnum(String channelName, String channel) {
		this.channelName = channelName;
		this.channel = channel;
	}

	/**
	 * 渠道名称
	 */
	private String channelName;
	/**
	 * 渠道
	 */
	private String channel;

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public static ChannelColDataEnum findByType(String channel) {
		ChannelColDataEnum[] channelTypes = ChannelColDataEnum.class
				.getEnumConstants();
		for (ChannelColDataEnum channelType : channelTypes) {
			if (channelType.getChannel().equals(channel)) {
				return channelType;
			}
		}
		return null;
	}

}
