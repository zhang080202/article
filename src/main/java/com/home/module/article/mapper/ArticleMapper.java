package com.home.module.article.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
	
}
