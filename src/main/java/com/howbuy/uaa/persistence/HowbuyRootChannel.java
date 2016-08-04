/**
 * 
 */
package com.howbuy.uaa.persistence;

/**
 * @author qiankun.li howbuy根渠道
 */
public class HowbuyRootChannel {

	private Long id;
	private String channelName;
	private String channelValue;
	/**
	 * 1:直接访问，2：搜索引擎，3：推广渠道，4：其他渠道
	 */
	private String channelType;
	private String channelCode;
	/**
	 * 是否有效:1是，0否
	 */
	private int isValidate;
	private int channelOrder;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public String getChannelValue() {
		return channelValue;
	}
	public void setChannelValue(String channelValue) {
		this.channelValue = channelValue;
	}
	public String getChannelType() {
		return channelType;
	}
	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}
	public String getChannelCode() {
		return channelCode;
	}
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
	public int getIsValidate() {
		return isValidate;
	}
	public void setIsValidate(int isValidate) {
		this.isValidate = isValidate;
	}
	public int getChannelOrder() {
		return channelOrder;
	}
	public void setChannelOrder(int channelOrder) {
		this.channelOrder = channelOrder;
	}

}
