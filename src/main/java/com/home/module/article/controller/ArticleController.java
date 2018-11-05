package com.home.module.article.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

	@Autowired
	private IArticleService articleService;

	@GetMapping("/v1/getArticlerList/{page}/{pageSize}")
	@ApiOperation("获取文章列表信息")
	public ResponseBean getArticlerList(@PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize) {
		IPage<Article> result = articleService.getArticlerList(page, pageSize);
		return ResponseBean.succ(result);
	}

}
