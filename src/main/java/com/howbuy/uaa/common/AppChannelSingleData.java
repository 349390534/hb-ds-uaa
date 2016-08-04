/**
 * 
 */
package com.howbuy.uaa.common;

/**
 * @author qiankun.li
 * 
 */
public class AppChannelSingleData {

	private static AppChannelSingleData singleData = new AppChannelSingleData();

	private AppChannelSingleData() {
	}

	public static AppChannelSingleData getSingleData() {
		return singleData;
	}

	private String channelData;

	public String getChannelData() {
		return channelData;
	}

	public void setChannelData(String channelData) {
		this.channelData = channelData;
	}

}
