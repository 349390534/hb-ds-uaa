package com.howbuy.uaa.dto;

import java.util.List;

public class CustomerResponse {
	private int pageIndex;
	private int pageSize;
	private int pageCount;
	private List<CustomerResponseMapping> list;
	private List<CustomerResponseCollMapping> coll;
	
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public List<CustomerResponseMapping> getList() {
		return list;
	}
	public void setList(List<CustomerResponseMapping> list) {
		this.list = list;
	}
	public List<CustomerResponseCollMapping> getColl() {
		return coll;
	}
	public void setColl(List<CustomerResponseCollMapping> coll) {
		this.coll = coll;
	}
	
}
