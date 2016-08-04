/**
 * 
 */
package com.howbuy.uaa.dto;

import java.util.List;

/**
 * @author qiankun.li
 * 
 */
public class DisabClientConvertCustApiResponse {

	private String status;

	private List<DisabClientConvertCustDataApiResponse> list;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<DisabClientConvertCustDataApiResponse> getList() {
		return list;
	}

	public void setList(List<DisabClientConvertCustDataApiResponse> list) {
		this.list = list;
	}

}
