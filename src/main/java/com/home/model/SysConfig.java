package com.home.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("sys_config")
public class SysConfig implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId
	private String dictId;
	/**
	 * key
	 */
	private String paramKey;

	/**
	 * value
	 */
	private String paramValue;

	/**
	 * 状态 0：隐藏 1：显示
	 */
	private Integer status;

	/**
	 * 备注
	 */
	private String remark;

	public String getDictId() {
		return dictId;
	}

	public void setDictId(String dictId) {
		this.dictId = dictId;
	}

	public String getParamKey() {
		return paramKey;
	}

	public void setParamKey(String paramKey) {
		this.paramKey = paramKey;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
