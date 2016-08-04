package com.howbuy.uaa.dto;

import java.util.List;

/**
 * @author qiankun.li 好买H5活动数据查询对象
 */
public class H5ActivityData {
	
	private int pageIndex;
	private int pageSize;
	private int pageCount;

	/**
	 * 页码数
	 */
	private int pages;
	/**
	 * 明细数据
	 */
	private List<H5ActivityDataMapping> list;
	/**
	 * 汇总数据
	 */
	private List<H5ActivityCollDataMapping> coll;

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

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public List<H5ActivityDataMapping> getList() {
		return list;
	}

	public void setList(List<H5ActivityDataMapping> list) {
		this.list = list;
	}

	public List<H5ActivityCollDataMapping> getColl() {
		return coll;
	}

	public void setColl(List<H5ActivityCollDataMapping> coll) {
		this.coll = coll;
	}

}
