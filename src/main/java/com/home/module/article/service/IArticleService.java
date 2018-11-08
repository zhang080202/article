package com.home.module.article.service;

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

	IPage<Article> getArticlerList(Integer page, Integer pageSize);

	Article getArticlerById(String articleId);

	void saveArticle(Article article);

}
