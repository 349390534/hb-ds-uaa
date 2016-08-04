/**
 * 
 */
package com.howbuy.uaa.common.execption;

/**
 * @author qiankun.li
 *
 */
public class UaaRunTimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4057780921179162945L;

	
	/**
	 * 
	 */
	public UaaRunTimeException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public UaaRunTimeException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public UaaRunTimeException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public UaaRunTimeException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}
