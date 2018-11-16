package com.home.model;

public class ResponseBean {

	private static final Integer	SUCC_CODE	= 200;

	private static final Integer	ERROR_CODE	= 500;
	
	private static final String 	ERROR_MSG   = "网络异常，请稍后再试";

	private String					msg;

	private Integer					code;

	private Object					data;

	public ResponseBean() {
		super();
	}

	public ResponseBean(String msg, Integer code, Object data) {
		super();
		this.msg = msg;
		this.code = code;
		this.data = data;
	}

	public static ResponseBean succ(String msg, Integer code, Object data) {
		return new ResponseBean(msg, code, data);
	}

	public static ResponseBean succ(String msg, Object data) {
		return ResponseBean.succ(msg, SUCC_CODE, data);
	}

	public static ResponseBean succ(String msg) {
		return ResponseBean.succ(msg, SUCC_CODE, null);
	}

	public static ResponseBean succ(Object data) {
		return ResponseBean.succ(null, SUCC_CODE, data);
	}

	public static ResponseBean succ() {
		return ResponseBean.succ(null, SUCC_CODE, null);
	}

	public static ResponseBean fail(String msg, Object data) {
		return ResponseBean.succ(msg, ERROR_CODE, data);
	}

	public static ResponseBean fail(String msg) {
		return ResponseBean.succ(msg, ERROR_CODE, null);
	}

	public static ResponseBean fail(Object data) {
		return ResponseBean.succ(null, ERROR_CODE, data);
	}

	public static ResponseBean fail() {
		return ResponseBean.succ(ERROR_MSG, ERROR_CODE, null);
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
