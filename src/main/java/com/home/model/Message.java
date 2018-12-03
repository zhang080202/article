package com.home.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2018-11-30
 */
@TableName("message")
public class Message implements Serializable {

	private static final long	serialVersionUID	= 1L;

	@TableId
	private String				msgId;

	private String				articleId;

	private String				userId;

	private String				msgContent;

	/**
	 * 排序 默认降序排列
	 */
	private Integer				msgOrder;

	private LocalDateTime		createTime;

	@TableField(exist = false)
	private String				username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public Integer getMsgOrder() {
		return msgOrder;
	}

	public void setMsgOrder(Integer msgOrder) {
		this.msgOrder = msgOrder;
	}

}
