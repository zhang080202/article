package com.home.module.article.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.home.model.Article;
import com.home.model.ResponseBean;
import com.home.module.article.service.IArticleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 文章管理相关接口
 * <p>
 * Title: ArticleController
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author zhangyf
 * @date 2018年11月5日
 */
@RestController
@RequestMapping("/article")
@Api(tags = "文章管理相关接口")
public class ArticleController {

	private static Logger	logger	= LoggerFactory.getLogger(ArticleController.class);

	@Autowired
	private IArticleService	articleService;

	@GetMapping("/v1/getArticlerList/{page}/{pageSize}")
	@ApiOperation("获取文章列表信息")
	public ResponseBean getArticlerList(@PathVariable("page") Integer page,
			@PathVariable("pageSize") Integer pageSize) {
		IPage<Article> result = null;
		try {
			result = articleService.getArticlerList(page, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取文章列表信息接口异常 Caused by " + e);
			return ResponseBean.fail("获取文章列表信息接口异常 Caused by " + e);
		}
		return ResponseBean.succ(result);
	}

	@GetMapping("/v1/getArticlerById/{articleId}")
	@ApiOperation("根据ID获取文章信息")
	public ResponseBean getArticlerById(@PathVariable("articleId") String articleId) {
		Article article = null;
		try {
			article = articleService.getArticlerById(articleId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("根据ID获取文章信息 接口异常 Caused by " + e);
			return ResponseBean.fail("根据ID获取文章信息 接口异常 Caused by " + e);
		}
		return ResponseBean.succ(article);
	}

}
