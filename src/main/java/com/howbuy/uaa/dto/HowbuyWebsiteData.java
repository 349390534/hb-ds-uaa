package com.howbuy.uaa.dto;

import java.util.List;

/**
 * @author qiankun.li 好买网站数据查询对象
 */
public class HowbuyWebsiteData {
	private int pageIndex;
	private int pageSize;
	private int pageCount;

	/**
	 * 页码数
	 */
	private int pages;
	/**
	 * 集合数据
	 */
	private List<HowbuyWebsiteDataMapping> list;
	/**
	 * 汇总数据
	 */
	private List<HowbuyWebsiteCollDataMapping> coll;

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

	public List<HowbuyWebsiteDataMapping> getList() {
		return list;
	}

	public void setList(List<HowbuyWebsiteDataMapping> list) {
		this.list = list;
	}

	public List<HowbuyWebsiteCollDataMapping> getColl() {
		return coll;
	}

	public void setColl(List<HowbuyWebsiteCollDataMapping> coll) {
		this.coll = coll;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

}
