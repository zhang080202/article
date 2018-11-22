package com.home.module.article.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.home.common.exception.ServiceException;
import com.home.common.utils.ValidatorUtil;
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

	private static Logger logger = LoggerFactory.getLogger(ArticleController.class);

	@Autowired
	private IArticleService articleService;

	@GetMapping("/v1/getArticlerList/{page}/{pageSize}")
	@ApiOperation("获取文章列表信息")
	public ResponseBean getArticlerList(@PathVariable("page") Integer page,
			@PathVariable("pageSize") Integer pageSize) {
		IPage<Article> result = null;
		try {
			result = articleService.getArticlerList(page, pageSize, false, null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取文章列表信息接口异常 Caused by " + e);
			return ResponseBean.fail();
		}
		return ResponseBean.succ(result);
	}

	@GetMapping("/v1/getArticlerListByUser/{page}/{pageSize}/{isPrivate}/{userId}")
	@ApiOperation("获取用户的文章列表信息")
	public ResponseBean getArticlerListByUser(@PathVariable("page") Integer page,
			@PathVariable("pageSize") Integer pageSize, @PathVariable("isPrivate") Boolean isPrivate,
			@PathVariable("userId") String userId) {
		IPage<Article> result = null;
		try {
			result = articleService.getArticlerList(page, pageSize, isPrivate, userId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取文章列表信息接口异常 Caused by " + e);
			return ResponseBean.fail();
		}
		return ResponseBean.succ(result);
	}

	@GetMapping("/v1/getArticlerListByUser")
	@ApiOperation("获取用户的文章列表信息")
	public ResponseBean getArticlerListByUser(@RequestParam("page") Integer page,
			@RequestParam("pageSize") Integer pageSize, @RequestParam("isPrivate") Boolean isPrivate,
			@RequestParam("userId") String userId, @RequestParam(value = "type", required = false) Integer type,
			@RequestParam(value = "status", required = false) Integer status, @RequestParam(value = "isDesc", required = false) Boolean isDesc) {
		IPage<Article> result = null;
		try {
			result = articleService.getArticlerList(page, pageSize, isPrivate, userId, type, status, isDesc);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取文章列表信息接口异常 Caused by " + e);
			return ResponseBean.fail();
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
			return ResponseBean.fail();
		}
		return ResponseBean.succ(article);
	}

	@GetMapping("/v1/getArticleStatus/{articleId}")
	@ApiOperation("根据ID获取文章状态")
	public ResponseBean getArticleStatus(@PathVariable("articleId") String articleId) {
		Article article = null;
		try {
			article = articleService.getArticlerById(articleId);
			article.setContent(null);
			article.setTitle(null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("根据ID获取文章状态 接口异常 Caused by " + e);
			return ResponseBean.fail();
		}
		return ResponseBean.succ(article);
	}

	@PostMapping("/v1/saveArticle")
	@ApiOperation("保存文章")
	public ResponseBean saveArticle(@RequestBody Article article) {
		try {
			ValidatorUtil.validatorModel(article);
			articleService.saveArticle(article);
		} catch (ServiceException e) {
			e.printStackTrace();
			logger.error("保存文章数据错误  + " + e.toString());
			return ResponseBean.fail(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("保存文章接口异常  + " + e.toString());
			return ResponseBean.fail();
		}
		return ResponseBean.succ();
	}

	@GetMapping("/v1/submitCheck/{articleId}")
	@ApiOperation("文章提交审核")
	public ResponseBean submitCheck(@PathVariable("articleId") String articleId) {
		try {
			articleService.submitCheck(articleId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("文章提交审核接口异常 : " + e);
			return ResponseBean.fail(e.getMessage());
		}
		return ResponseBean.succ();
	}

	@PutMapping("/v1/repealCheck/{articleId}")
	@ApiOperation("文章撤销审核")
	public ResponseBean repealCheck(@PathVariable("articleId") String articleId) {
		try {
			articleService.repealCheck(articleId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("文章撤销审核接口异常 : " + e);
			return ResponseBean.fail(e.getMessage());
		}
		return ResponseBean.succ();
	}

	@DeleteMapping("/v1/deleteArticle/{articleId}/{userId}")
	@ApiOperation("删除文章")
	public ResponseBean deleteArticle(@PathVariable("articleId") String articleId,
			@PathVariable("userId") String userId) {
		try {
			articleService.deleteArticle(articleId, userId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除文章接口异常 : " + e);
			return ResponseBean.fail(e.getMessage());
		}
		return ResponseBean.succ();
	}

	@PutMapping("/v1/setIsPrivate/{articleId}/{isPrivate}")
	@ApiOperation("设置文章是否公开")
	public ResponseBean setIsPrivate(@PathVariable("articleId") String articleId,
			@PathVariable("isPrivate") Boolean isPrivate) {
		try {
			articleService.setIsPrivate(articleId, isPrivate);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("设置文章是否公开接口异常 : " + e);
			return ResponseBean.fail(e.getMessage());
		}
		return ResponseBean.succ();
	}

	@GetMapping("/v1/getArticleCount")
	@ApiOperation("查询文章数量")
	public ResponseBean getArticleCount(@RequestParam("userId") String userId, @RequestParam(value = "type", required = false) Integer type,
			@RequestParam(value = "status", required = false) Integer status) {
		Map<String, Object> params = null;
		try {
			params = articleService.getArticleCount(userId, type, status);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询文章数量接口异常 : " + e);
			return ResponseBean.fail(e.getMessage());
		}
		return ResponseBean.succ(params);
	}

}
