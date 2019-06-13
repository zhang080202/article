package com.home.module.article.controller;

import java.util.HashMap;
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

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.home.common.exception.ServiceException;
import com.home.common.utils.ValidatorUtil;
import com.home.model.Article;
import com.home.model.ResponseBean;
import com.home.model.UserPraise;
import com.home.module.article.service.IArticleService;
import com.home.module.praise.service.IUserPraiseService;

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
	@Autowired
	private IUserPraiseService userPraiseService;
	
	@GetMapping("/v1/getArticlerListAll/{page}/{pageSize}/{params}")
	@ApiOperation("获取所有文章列表信息")
	public ResponseBean getArticlerListAll(@PathVariable("page") Integer page,
										   @PathVariable("pageSize") Integer pageSize, 
										   @PathVariable("params") String params) {
		IPage<Map<String,Object>> result = null;
		Map<String,Object> parse = (Map<String, Object>) JSON.parse(params);
		try {
			result = articleService.getArticlerListAll(page, pageSize, parse);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取所有文章列表信息接口异常 Caused by " + e);
			return ResponseBean.fail();
		}
		return ResponseBean.succ(result);
	}

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
											  @PathVariable("pageSize") Integer pageSize, 
											  @PathVariable("isPrivate") Boolean isPrivate,
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
			@RequestParam(value = "status", required = false) Integer status,
			@RequestParam(value = "isDesc", required = false) Boolean isDesc) {
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

	@GetMapping("/v1/getArticlerById/{articleId}/{userId}/{isOpen}")
	@ApiOperation("根据ID获取文章信息")
	public ResponseBean getArticlerById(@PathVariable("articleId") String articleId, @PathVariable("userId") String userId,
										@PathVariable("isOpen") Integer isOpen) {
		Article article = null;
		Map<String, Object> result = new HashMap<>();
		try {
			article = articleService.getArticlerById(articleId, isOpen);
			int count = userPraiseService.count(new QueryWrapper<UserPraise>().eq("article_id", articleId)
																  			  .eq("user_id", userId));
			result.put("detail", article);
			result.put("isPraise", count);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("根据ID获取文章信息 接口异常 Caused by " + e);
			return ResponseBean.fail();
		}
		return ResponseBean.succ(result);
	}

	@GetMapping("/v1/getArticleStatus/{articleId}")
	@ApiOperation("根据ID获取文章状态")
	public ResponseBean getArticleStatus(@PathVariable("articleId") String articleId) {
		Article article = null;
		try {
			//传入1表示为后台调用 非公开访问 
			article = articleService.getArticlerById(articleId, 0);
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
			logger.error("保存文章数据错误  " + e);
			return ResponseBean.fail(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("保存文章接口异常  " + e);
			return ResponseBean.fail();
		}
		return ResponseBean.succ();
	}
	
	@PostMapping("/v1/updateArticle")
	@ApiOperation("修改文章")
	public ResponseBean updateArticle(@RequestBody Article article) {
		try {
			ValidatorUtil.validatorModel(article);
			articleService.updateArticle(article);
		} catch (ServiceException e) {
			e.printStackTrace();
			logger.error("修改文章数据错误  " + e);
			return ResponseBean.fail(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("修改文章接口异常  " + e);
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
	
	@GetMapping("/v1/deleteArticle/{articleId}")
	@ApiOperation("删除文章")
	public ResponseBean deleteArticle(@PathVariable("articleId") String articleId) {
		try {
			articleService.removeById(articleId);
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
	public ResponseBean getArticleCount(@RequestParam("userId") String userId,
			@RequestParam(value = "type", required = false) Integer type,
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

	@GetMapping("/v1/praiseArticle/{praiseNum}/{articleId}/{userId}")
	@ApiOperation("点赞")
	public ResponseBean praiseArticle(@PathVariable("praiseNum") Integer praiseNum,
			@PathVariable("articleId") String articleId, @PathVariable("userId") String userId) {
		try {
			articleService.praiseArticle(praiseNum, articleId, userId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("点赞接口异常 : " + e);
			return ResponseBean.fail(e.getMessage());
		}
		return ResponseBean.succ();
	}

}
