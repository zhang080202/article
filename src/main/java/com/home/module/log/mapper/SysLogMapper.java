package com.home.module.log.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.home.model.SysLog;

/**
 * <p>
 * 系统日志 Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2018-11-01
 */
@Mapper
public interface SysLogMapper extends BaseMapper<SysLog> {

}
