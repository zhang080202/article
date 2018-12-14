package com.home.module.article.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.home.model.Article;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2018-11-01
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
	
	public List<Map<String, Object>> getArticlerListAll(Page page, @Param("params") Map<String, Object> params);
}
