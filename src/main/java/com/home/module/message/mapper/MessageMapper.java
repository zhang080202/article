package com.home.module.message.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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

}
