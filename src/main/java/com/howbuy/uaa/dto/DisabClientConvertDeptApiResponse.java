/**
 * 
 */
package com.howbuy.uaa.dto;

import java.util.List;

/**
 * @author qiankun.li
 * 
 */
public class DisabClientConvertDeptApiResponse {

	private String status;

	private List<DisabClientConvertDeptDataApiResponse> list;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<DisabClientConvertDeptDataApiResponse> getList() {
		return list;
	}

	public void setList(List<DisabClientConvertDeptDataApiResponse> list) {
		this.list = list;
	}

}
