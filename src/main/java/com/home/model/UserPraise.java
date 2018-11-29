package com.home.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("user_praise")
public class UserPraise implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @TableId
    private String praiseId;

    /**
     * 用户ID 
     */
    private String userId;

    /**
     * 文章ID
     */
    private String articleId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

	public String getPraiseId() {
		return praiseId;
	}

	public void setPraiseId(String praiseId) {
		this.praiseId = praiseId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}
    
}
