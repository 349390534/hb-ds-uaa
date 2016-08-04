/**
 * 
 */
package com.howbuy.uaa.dto;

import java.util.List;

/**
 * @author qiankun.li
 * 
 */
public class PenetrateApiResponse {

	private String status;

	private List<PenetrateApiDataResponse> list;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<PenetrateApiDataResponse> getList() {
		return list;
	}

	public void setList(List<PenetrateApiDataResponse> list) {
		this.list = list;
	}

}
