package com.home.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
	
	@NotNull(message = "文章标题不能为空")
	private Integer articleType;
	
	@NotBlank(message = "文章内容不能为空")
	private String content;
	
	@NotBlank(message = "文章标题不能为空")
	private String title;

	/**
	 * 是否审核 0 未审核 1 审核中 2 已审核 3 未通过
	 */
	private Integer status;

	private String createUser;

	private LocalDateTime createTime;
	
	private LocalDateTime updateTime;
	//
	private LocalDateTime passTime; //通过审核时间
	
	@NotBlank(message = "标题图片不能为空")
	private String image;
	
	private Long readNum;
	
	private Boolean isPrivate;
	/**
	 * 0 有效 1 无效
	 */
	private Integer flag;
	
	@TableField(exist = false)
	private String username;
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public LocalDateTime getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public LocalDateTime getPassTime() {
		return passTime;
	}

	public void setPassTime(LocalDateTime passTime) {
		this.passTime = passTime;
	}

	public Boolean getIsPrivate() {
		return isPrivate;
	}

	public void setIsPrivate(Boolean isPrivate) {
		this.isPrivate = isPrivate;
	}

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

	@Override
	public String toString() {
		return "Article [articleId=" + articleId + ", articleType=" + articleType + ", content=" + content + ", title="
				+ title + ", status=" + status + ", createUser=" + createUser + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", passTime=" + passTime + ", image=" + image + ", readNum=" + readNum
				+ ", isPrivate=" + isPrivate + ", flag=" + flag + ", accessImage=" + accessImage + "]";
	}
}
