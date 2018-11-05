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
 * @since 2018-11-01
 */
@TableName("article")
public class Article implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId
	private String articleId;

	private Integer articleType;

	private String content;

	private String title;

	/**
	 * 是否审核 0 未审核 1 已审核
	 */
	private Integer status;

	private String createUser;

	private LocalDateTime createTime;

	private String image;
	
	private Long readNum;
	
	@TableField(exist = false)
	private String accessImage;
	
	public String getAccessImage() {
		return accessImage;
	}

	public void setAccessImage(String accessImage) {
		this.accessImage = accessImage;
	}

	public Long getReadNum() {
		return readNum;
	}

	public void setReadNum(Long readNum) {
		this.readNum = readNum;
	}

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public Integer getArticleType() {
		return articleType;
	}

	public void setArticleType(Integer articleType) {
		this.articleType = articleType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
