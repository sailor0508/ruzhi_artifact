package com.ruzhi.demo.common;

import com.ruzhi.demo.exceptions.NoNullFieldStringStyle;
import org.apache.commons.lang.builder.ToStringBuilder;
import com.ruzhi.demo.common.Constants.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class ResponseResult<T> implements Serializable {


	private static final long serialVersionUID = -3519851122646071103L;

	private boolean success = false;
	private ErrorCode errorCode;
	private T result;

	private int code;
	private String msg;

	public ResponseResult() {
		this.success = false;
	}

	public ResponseResult(boolean success) {
		this.success = success;
	}

	public ResponseResult(boolean success, String msg) {
		this.success = success;
		this.msg = msg;
	}

	public ResponseResult(boolean success, String msg, T result) {
		this.success = success;
		this.msg = msg;
		this.result = result;
	}

	public ResponseResult(boolean success, int code, String msg, T result) {
		this.success = success;
		this.code = code;
		this.msg = msg;
		this.result = result;
	}
	/**
	 * 异常信息
	 */
	private List<String> errMsg = new ArrayList<String>();
	public int getCode() {
		return code;
	}


	public void setCode(int code) {
		this.code = code;
	}


	public boolean isSuccess() {
		return success;
	}


	public void setSuccess(boolean success) {
		this.success = success;
	}


	public ErrorCode getErrorCode() {
		return errorCode;
	}


	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}


	public T getResult() {
		return result;
	}


	public void setResult(T result) {
		this.result = result;
	}


	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}


	public List<String> getErrMsg() {
		return errMsg;
	}


	public void setErrMsg(List<String> errMsg) {
		this.errMsg = errMsg;
	}


	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, new NoNullFieldStringStyle());
	}
}
