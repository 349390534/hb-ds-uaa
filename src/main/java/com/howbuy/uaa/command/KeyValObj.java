package com.howbuy.uaa.command;

import java.util.List;


/**
 * 通用 键值对 用于json数据转换
 * @author yichao.song
 *
 */
public class KeyValObj {
	
	private String key;
	
	private String val;
	
	private String para;
	
	private String id;
	
	private List<KeyValObj> childs;

	public List<KeyValObj> getChilds() {
		return childs;
	}

	public void setChilds(List<KeyValObj> childs) {
		this.childs = childs;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	public String getPara() {
		return para;
	}

	public void setPara(String para) {
		this.para = para;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
