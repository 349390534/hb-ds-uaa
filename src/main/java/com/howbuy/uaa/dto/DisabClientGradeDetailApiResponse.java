/**
 * 
 */
package com.howbuy.uaa.dto;

import java.util.List;

/**
 * @author qiankun.li
 * 
 */
public class DisabClientGradeDetailApiResponse {
	private String status;

	private List<DisabClientGradeDetailDataApiResponse> list;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<DisabClientGradeDetailDataApiResponse> getList() {
		return list;
	}

	public void setList(List<DisabClientGradeDetailDataApiResponse> list) {
		this.list = list;
	}

}
