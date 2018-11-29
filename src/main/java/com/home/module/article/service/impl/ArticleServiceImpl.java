package com.home.module.article.service.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.home.common.annotation.opLog;
import com.home.common.enums.ServiceEnum;
import com.home.common.exception.ServiceException;
import com.home.model.Article;
import com.home.model.SysOss;
import com.home.model.UserModel;
import com.home.model.UserPraise;
import com.home.module.article.mapper.ArticleMapper;
import com.home.module.article.service.IArticleService;
import com.home.module.oss.service.ISysOssService;
import com.home.module.praise.service.IUserPraiseService;
import com.home.module.user.service.IUserService;

/**
 * 
 * <p>
 * Title: ArticleServiceImpl
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author zhangyf
 * @date 2018年11月5日
 */
@Service
@Transactional
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

	private static Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);

	@Autowired
	private ISysOssService sysOssService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IUserPraiseService userPraiseService;

	@Override
	public IPage<Article> getArticlerList(Integer page, Integer pageSize, Boolean isPrivate, String userId) {
		return this.getArticlerList(page, pageSize, isPrivate, userId, -1, -1, false);
	}

	@Override
	@Transactional
	public Article getArticlerById(String articleId) {
		Article article = baseMapper.selectById(articleId);
		if (StringUtils.isNotBlank(article.getImage())) {
			SysOss oss = sysOssService.getById(article.getImage());
			article.setAccessImage(oss.getOssUrl());
		}
		UserModel user = userService.queryUser(article.getCreateUser());
		article.setUsername(user.getName());
		//增加一次阅读次数
		article.setReadNum(article.getReadNum() + 1);
		baseMapper.updateById(article);
		return article;
	}

	@Override
	@opLog("保存文章")
	public String saveArticle(Article article) {
//		Article articleDB = baseMapper.selectOne(new QueryWrapper<Article>().eq("title", article.getTitle())
//																			.eq("flag", 0)
//																			.eq("content", article.getContent()));

		if (StringUtils.isNotBlank(article.getArticleId())) {
			// 修改文章
			Article articleDB = baseMapper.selectById(article.getArticleId());
			articleDB.setUpdateTime(LocalDateTime.now());
			articleDB.setStatus(0);
			articleDB.setContent(article.getContent());
			articleDB.setIsPrivate(true);
			articleDB.setArticleType(article.getArticleType());
			articleDB.setTitle(article.getTitle());
			articleDB.setImage(article.getImage());
			logger.info("修改文章 -----------> " + article.toString());
			baseMapper.updateById(articleDB);
		} else {
			// 新增文章
			article.setCreateTime(LocalDateTime.now());
			article.setStatus(0);
			logger.info("新增文章 -----------> " + article.toString());
			baseMapper.insert(article);
		}
//		if (articleDB != null) {
//			throw new ServiceException(ServiceEnum.BUSINESS_FAIL.getCode(), "请勿重复提交");
//		}

		return article.getCreateUser();
	}

	@Override
	@opLog("提交审核")
	public String submitCheck(String articleId) {
		Article article = baseMapper.selectById(articleId);

		if (article == null) {
			throw new ServiceException(ServiceEnum.BUSINESS_FAIL.getCode(), "该文章不存在");
		}
		Integer status = article.getStatus();

		if (status != 0 || status != 3) {
			if (status == 1) {
				throw new ServiceException(ServiceEnum.BUSINESS_FAIL.getCode(), "该文章审核中，请勿重复提交");
			}

			if (status == 2) {
				throw new ServiceException(ServiceEnum.BUSINESS_FAIL.getCode(), "该文章审核已通过，请勿重复提交");
			}
		}

		article.setStatus(1);
//		article.setPassTime(LocalDateTime.now());
		baseMapper.updateById(article);

		return article.getCreateUser();
	}

	@Override
	@opLog("删除文章")
	public String deleteArticle(String articleId, String userId) {
		// 检查是否越过权限
		Article article = baseMapper.selectById(articleId);
		if (!userId.equals(article.getCreateUser())) {
			throw new ServiceException(ServiceEnum.BUSINESS_FAIL.getCode(), "您没有权限操作此文章，若有疑问请联系管理员");
		}
		// 逻辑删除
		article.setFlag(1);
		baseMapper.updateById(article);
		return userId;
	}

	@Override
	@opLog("文章撤销审核")
	public String repealCheck(String articleId) {
		Article article = baseMapper.selectById(articleId);

		if (article == null) {
			throw new ServiceException(ServiceEnum.BUSINESS_FAIL.getCode(), "该文章不存在");
		}
		article.setStatus(0);
		baseMapper.updateById(article);
		return article.getCreateUser();
	}

	@Override
	@opLog("设置文章是否公开")
	public String setIsPrivate(String articleId, Boolean isPrivate) {
		Article article = baseMapper.selectById(articleId);

		if (article == null) {
			throw new ServiceException(ServiceEnum.BUSINESS_FAIL.getCode(), "该文章不存在");
		}
		article.setIsPrivate(isPrivate);
		baseMapper.updateById(article);
		return article.getCreateUser();
	}

	@Override
	public Map<String, Object> getArticleCount(String userId, Integer type, Integer status) {
		Integer privateNum = baseMapper.selectCount(new QueryWrapper<Article>().eq("is_private", true)
				.eq(StringUtils.isNotBlank(userId), "create_user", userId).eq("flag", 0)
				.eq(type != -1, "article_type", type).eq(status != -1, "status", status));
		Integer openNum = baseMapper.selectCount(new QueryWrapper<Article>().eq("is_private", false)
				.eq(StringUtils.isNotBlank(userId), "create_user", userId).eq("status", 2).eq("flag", 0)
				.eq(type != -1, "article_type", type).eq(status != -1, "status", status));
		Map<String, Object> result = new HashMap<>();
		result.put("privateNum", privateNum);
		result.put("openNum", openNum);
		return result;
	}

	@Override
	public IPage<Article> getArticlerList(Integer page, Integer pageSize, Boolean isPrivate, String userId,
			Integer type, Integer status, Boolean isDesc) {
		IPage<Article> result = baseMapper.selectPage(new Page<Article>(page, pageSize),
				new QueryWrapper<Article>().eq("is_private", isPrivate)
						.eq(StringUtils.isNotBlank(userId), "create_user", userId).eq(!isPrivate, "status", 2)
						.eq("flag", 0).eq(type != -1, "article_type", type).eq(status != -1, "status", status)
						.orderByDesc(isDesc, "create_time"));
		// 获取图片url 并授权
		List<Article> list = result.getRecords();
//		OssUtil ossUtil = new OssUtil();
		for (Article article : list) {
			if (StringUtils.isNotBlank(article.getImage())) {
				SysOss oss = sysOssService.getById(article.getImage());
//				String authAccess = ossUtil.authAccess(oss.getOssUrl());
				// 列表查询不返回文章内容
				article.setContent(null);
				article.setAccessImage(oss.getOssUrl());
			}
		}
		return result;
	}
	
	/**
	 * 点赞
	 */
	@Override
	@Transactional
	public void praiseArticle(Integer praiseNum, String articleId, String userId) {
		Article article = baseMapper.selectById(articleId);
		if (praiseNum != article.getPraiseNum()) {
			if (praiseNum > article.getPraiseNum()) {
				UserPraise praise = new UserPraise();
				praise.setArticleId(article.getArticleId());
				praise.setUserId(userId);
				praise.setCreateTime(LocalDateTime.now());
				userPraiseService.save(praise);
			} else if (praiseNum < article.getPraiseNum()) {
				userPraiseService.cancelPraise(articleId, userId);
			}
			article.setPraiseNum(praiseNum);
			baseMapper.updateById(article);
		}
	}

}
