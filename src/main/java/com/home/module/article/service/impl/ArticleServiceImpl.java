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
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {
	
	@Autowired
	private ISysOssService sysOssService;

	@Override
	public IPage<Article> getArticlerList(Integer page, Integer pageSize, Integer isPrivate, String userId) {
		IPage<Article> result = baseMapper.selectPage(new Page<Article>(page, pageSize),
				new QueryWrapper<Article>().eq("is_private", isPrivate)
										   .eq(StringUtils.isNotBlank(userId), "create_user", userId).orderByDesc("read_num"));
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
	@Transactional
	public void saveArticle(Article article) {
		Article articleDB = baseMapper.selectOne(new QueryWrapper<Article>().eq("title", article.getTitle())
																			.eq("content", article.getContent()));
		if (articleDB != null) {
			throw new ServiceException(ServiceEnum.BUSINESS_FAIL.getCode(), "请勿重复提交");
		}
		article.setCreateTime(LocalDateTime.now());
		baseMapper.insert(article);
	}

}
