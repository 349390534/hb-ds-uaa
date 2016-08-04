package com.howbuy.uaa.utils;

public class PermissionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4937778163737091319L;

	private String message;
	
	public PermissionException(String message){
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
