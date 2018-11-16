package com.home.module.article.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
import com.home.common.utils.OssUtil;
import com.home.model.Article;
import com.home.model.SysOss;
import com.home.module.article.mapper.ArticleMapper;
import com.home.module.article.service.IArticleService;
import com.home.module.oss.service.ISysOssService;

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
	
	@Autowired
	private ISysOssService sysOssService;

	@Override
	public IPage<Article> getArticlerList(Integer page, Integer pageSize, Integer isPrivate, String userId) {
		IPage<Article> result = baseMapper.selectPage(new Page<Article>(page, pageSize),
				new QueryWrapper<Article>().eq("is_private", isPrivate)
										   .eq(StringUtils.isNotBlank(userId), "create_user", userId)
										   .eq("flag", 0)
										   .orderByDesc("read_num"));
		//获取图片url 并授权
		List<Article> list = result.getRecords();
		OssUtil ossUtil = new OssUtil();
		for (Article article : list) {
			if (StringUtils.isNotBlank(article.getImage())) {
				SysOss oss = sysOssService.getById(article.getImage());
				String authAccess = ossUtil.authAccess(oss.getOssUrl());
				//列表查询不返回文章内容
				article.setContent(null);
				article.setAccessImage(authAccess);
			}
		}
		return result;
	}

	@Override
	public Article getArticlerById(String articleId) {
		return baseMapper.selectById(articleId);
	}

	@Override
	@opLog("保存文章")
	public String saveArticle(Article article) {
		Article articleDB = baseMapper.selectOne(new QueryWrapper<Article>().eq("title", article.getTitle())
																			.eq("flag", 0)
																			.eq("content", article.getContent()));
		if (articleDB != null) {
			throw new ServiceException(ServiceEnum.BUSINESS_FAIL.getCode(), "请勿重复提交");
		}
		article.setCreateTime(LocalDateTime.now());
		article.setStatus(0);
		this.saveOrUpdate(article);
		
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
		article.setPassTime(LocalDateTime.now());
		baseMapper.updateById(article);
		
		return article.getCreateUser();
	}

	@Override
	@opLog("删除文章")
	public String deleteArticle(String articleId, String userId) {
		//检查是否越过权限
		Article article = baseMapper.selectById(articleId);
		if (!userId.equals(article.getCreateUser())) {
			throw new ServiceException(ServiceEnum.BUSINESS_FAIL.getCode(), "您没有权限操作此文章，若有疑问请联系管理员");
		}
		//逻辑删除
		article.setFlag(1);
		baseMapper.updateById(article);
		return userId;
	}

}
