/**
 * 
 */
package com.howbuy.uaa.dto;

import java.util.List;

/**
 * @author qiankun.li
 * 
 */
public class DisabClientConvertCustDetailApiResponse {

	private String status;

	private List<DisabClientConvertCustDetailDataApiResponse> list;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<DisabClientConvertCustDetailDataApiResponse> getList() {
		return list;
	}

	public void setList(List<DisabClientConvertCustDetailDataApiResponse> list) {
		this.list = list;
	}

}
