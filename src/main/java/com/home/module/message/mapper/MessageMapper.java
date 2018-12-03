package com.home.module.message.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.home.model.Message;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2018-11-30
 */
@Mapper
public interface MessageMapper extends BaseMapper<Message> {
	
	public List<Map<String, Object>> queryMessageByArticle(Page page, @Param("params") Map<String, Object> params);
}
