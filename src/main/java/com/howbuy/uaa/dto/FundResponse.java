package com.howbuy.uaa.dto;

import java.util.List;

public class FundResponse {
	private int pageIndex;
	private int pageSize;
	private int pageCount;
//	private List<FundResponseMapping> list;
	private List<FundResponseMapping> list;
	private List<FundResponseCollMapping> coll;
	
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
	public List<FundResponseMapping> getList() {
		return list;
	}
	public void setList(List<FundResponseMapping> list) {
		this.list = list;
	}
	public List<FundResponseCollMapping> getColl() {
		return coll;
	}
	public void setColl(List<FundResponseCollMapping> coll) {
		this.coll = coll;
	}
	
}
