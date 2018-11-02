package com.home.module.article.mapper;

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
