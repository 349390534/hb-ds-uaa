package com.howbuy.uaa.quartz.dto;


public class AnaLysisObj {
	
	private String cookie;
	
	private long recTime;
	
	private String srcUrl;
	
	private String destUrl;

	public String getSrcUrl() {
		return srcUrl;
	}

	public void setSrcUrl(String srcUrl) {
		this.srcUrl = srcUrl;
	}
	
	public long getRecTime() {
		return recTime;
	}

	public void setRecTime(long recTime) {
		this.recTime = recTime;
	}

	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}
	
	public String getDestUrl() {
		return destUrl;
	}

	public void setDestUrl(String destUrl) {
		this.destUrl = destUrl;
	}

}
