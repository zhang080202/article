package com.home.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 系统日志
 * </p>
 *
 * @author jobob
 * @since 2018-11-01
 */
@TableName("sys_log")
public class SysLog implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@TableId
	private String logId;
	/**
	 * 用户名
	 */
	private String userId;

	/**
	 * 用户操作
	 */
	private String operation;

	/**
	 * 请求方法
	 */
	private String method;

	/**
	 * 请求参数
	 */
	private String params;

	/**
	 * 执行时长(毫秒)
	 */
	private Long time;

	/**
	 * IP地址
	 */
	private String ip;

	/**
	 * 创建时间
	 */
	private LocalDateTime createDate;

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

}
