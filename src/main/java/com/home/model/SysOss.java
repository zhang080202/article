package com.home.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2018-11-01
 */
@TableName("sys_oss")
public class SysOss implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId
	private String ossId;

	private String ossUrl;

	private LocalDateTime createTime;

	public String getOssId() {
		return ossId;
	}

	public void setOssId(String ossId) {
		this.ossId = ossId;
	}

	public String getOssUrl() {
		return ossUrl;
	}

	public void setOssUrl(String ossUrl) {
		this.ossUrl = ossUrl;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

}
