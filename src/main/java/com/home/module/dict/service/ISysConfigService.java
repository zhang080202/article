package com.home.module.dict.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.home.model.SysConfig;

/**
 * <p>
 * 系统配置信息表 服务类
 * </p>
 *
 * @author jobob
 * @since 2018-11-08
 */
public interface ISysConfigService extends IService<SysConfig> {

	Map<String, Object> getDictByKey(String key);

}
