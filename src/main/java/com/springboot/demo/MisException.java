package com.springboot.demo;

import com.springboot.pojo.WoResultCode;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Pintuer系统管理异常类
 * 
 *
 */
public class MisException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3290668438850456772L;

	@SuppressWarnings("unused")
	private final static Logger LOG = LogManager.getLogger(MisException.class);
	
	private WoResultCode code = WoResultCode.getUnknownCode();

	public WoResultCode getCode() {
		return code;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Throwable#getMessage()
	 */
	public String getMessage() {
		return code.getMsg();
	}

	/**
	 * 
	 */
	public MisException() {
		super();
	}

	/**
	 * @param code
	 */
	public MisException(WoResultCode code, Object... args) {
		super();
		this.code = code;
		this.code.setMsgArgs(args);
	}

	/**
	 * @param code
	 */
	public MisException(Throwable cause, WoResultCode code, Object... args) {
		super(cause);
		this.code = code;
		this.code.setMsgArgs(args);
	}
}
