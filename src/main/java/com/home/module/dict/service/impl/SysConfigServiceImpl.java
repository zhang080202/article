package com.home.module.dict.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.home.model.SysConfig;
import com.home.module.dict.mapper.SysConfigMapper;
import com.home.module.dict.service.ISysConfigService;

/**
 * <p>
 * 系统配置信息表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2018-11-08
 */
@Service
public class SysConfigServiceImpl extends ServiceImpl<SysConfigMapper, SysConfig> implements ISysConfigService {

	@Override
	public Map<String, Object> getDictByKey(String key) {
		List<SysConfig> list = baseMapper.selectList(new QueryWrapper<SysConfig>().like("param_key", key));
		List<String> key1 = list.stream()
							    .map((x) -> x.getParamValue())
								.collect(Collectors.toList());
		List<String> value = list.stream()
								 .map((x) -> x.getRemark())
								 .collect(Collectors.toList());
		Map<String, Object> result = new HashMap<>();
		result.put("key", key1);
		result.put("value", value);
		result.put("result", list);
		return result;
	}

	@Override
	public SysConfig getDictByKeyAndValue(String key, Integer value) {
		return baseMapper.selectOne(new QueryWrapper<SysConfig>().like("param_key", key)
																 .eq("param_value", value));
	}

}
