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
@TableName("suggestion")
public class Suggestion implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId
	private String suggestionId;

	/**
	 * 标题
	 */
	private String title;

	/**
	 * 内容
	 */
	private String content;

	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;

	/**
	 * 状态
	 */
	private Integer status;

	/**
	 * 反馈图片
	 */
	private String image;

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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
