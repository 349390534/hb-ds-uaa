/**
 * 
 */
package com.howbuy.uaa.dto;

import java.util.List;

/**
 * @author qiankun.li
 *
 */
/**
 * @author qiankun.li
 * 
 */
public class UserKeepApiResponse<T> {

	private String json;

	private String status;

	private List<T> list;

	private Pagination pagination;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

}
