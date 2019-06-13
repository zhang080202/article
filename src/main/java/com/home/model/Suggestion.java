package com.home.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2018-11-01
 */
@TableName("suggestion")
public class Suggestion implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId
	private String suggestionId;

	/**
	 * 用户ID
	 */
	private String userId;

	/**
	 * 标题
	 */
	private String title;

	/**
	 * 内容
	 */
	@NotBlank(message = "内容不能为空")
	private String content;

	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createTime;

	/**
	 * 状态
	 */
	private Integer status;

	@TableField(exist = false)
	private String statusValue;

	@TableField(exist = false)
	private String userName;

	public String getStatusValue() {
		return statusValue;
	}

	public void setStatusValue(String statusValue) {
		this.statusValue = statusValue;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSuggestionId() {
		return suggestionId;
	}

	public void setSuggestionId(String suggestionId) {
		this.suggestionId = suggestionId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
