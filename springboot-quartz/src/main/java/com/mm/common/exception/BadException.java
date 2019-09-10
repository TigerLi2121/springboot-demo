package com.mm.common.exception;

/**
 * 自定义异常
 *
 */
public class BadException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
    private String msg;
    private int code = 0;
    
    public BadException(String msg) {
		super(msg);
		this.msg = msg;
	}
	
	public BadException(String msg, Throwable e) {
		super(msg, e);
		this.msg = msg;
	}
	
	public BadException(String msg, int code) {
		super(msg);
		this.msg = msg;
		this.code = code;
	}
	
	public BadException(String msg, int code, Throwable e) {
		super(msg, e);
		this.msg = msg;
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	
}
