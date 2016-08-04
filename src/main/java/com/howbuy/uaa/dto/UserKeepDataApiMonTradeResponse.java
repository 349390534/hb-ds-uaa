/**
 * 
 */
package com.howbuy.uaa.dto;


/**
 * @author qiankun.li
 * 
 */
public class UserKeepDataApiMonTradeResponse extends UserKeepDataApiMonBaseResponse {

	/**
	 * 当月首次交易总人数
	 */
	private Long dyxzjy_rs;

	public Long getDyxzjy_rs() {
		return dyxzjy_rs;
	}

	public void setDyxzjy_rs(Long dyxzjy_rs) {
		this.dyxzjy_rs = dyxzjy_rs;
	}

}