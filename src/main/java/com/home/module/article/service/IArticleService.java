package com.home.module.article.service;

import java.util.Map;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.home.model.Article;

/**
 * 
  * <p>Title: IArticleService</p>  
  * <p>Description: </p>  
  * @author zhangyf 
  * @date 2018年11月5日
 */
public interface IArticleService extends IService<Article> {

	IPage<Article> getArticlerList(Integer page, Integer pageSize, Boolean isPrivate, String userId);
	
	IPage<Article> getArticlerList(Integer page, Integer pageSize, Boolean isPrivate, String userId, Integer type, Integer status,
			Boolean isDesc);

	Article getArticlerById(String articleId);

	String saveArticle(Article article);

	String submitCheck(String articleId);

	String deleteArticle(String articleId, String userId);

	String repealCheck(String articleId);

	String setIsPrivate(String articleId, Boolean isPrivate);

	Map<String, Object> getArticleCount(String userId, Integer type, Integer status);

	void praiseArticle(Integer praiseNum, String articleId, String userId);

	IPage<Map<String, Object>> getArticlerListAll(Integer page, Integer pageSize, Map<String, Object> parse);

}
