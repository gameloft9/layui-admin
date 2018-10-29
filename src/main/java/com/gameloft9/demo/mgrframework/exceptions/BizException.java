package com.gameloft9.demo.mgrframework.exceptions;

import com.gameloft9.demo.mgrframework.beans.error.IErrCode;
import lombok.Data;

/**
 * 业务异常封装
 * @author leiYao 2017-11-10
 * */
@Data
public class BizException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String errorCode;

	public BizException() {
	}

	/**
	 * 构造函数
	 * */
	public BizException(String code,String message) {
		super(message);
		this.errorCode = code;
	}

	/**
	 * 构造函数
	 * */
	public BizException(IErrCode e) {
		super(e.getDesc());
		this.errorCode = e.getCode();
	}

	public BizException(Throwable cause) {
		super(cause);
	}

	public BizException(String message, Throwable cause) {
		super(message, cause);
	}

	public BizException(String message) {
		super(message);
	}

}
