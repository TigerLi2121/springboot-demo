package com.mm.common.exception;

import lombok.Getter;

/**
 * 自定义异常
 */
@Getter
public class MMException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String msg;
	private int code = 500;

	public MMException(String msg) {
		super(msg);
		this.msg = msg;
	}

	public MMException(String msg, Throwable e) {
		super(msg, e);
		this.msg = msg;
	}

	public MMException(String msg, int code) {
		super(msg);
		this.msg = msg;
		this.code = code;
	}

	public MMException(String msg, int code, Throwable e) {
		super(msg, e);
		this.msg = msg;
		this.code = code;
	}
}
