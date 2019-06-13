package com.home.module.suggestion.service.impl;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.home.common.enums.SuggestStatusEnum;
import com.home.model.Suggestion;
import com.home.model.UserModel;
import com.home.module.suggestion.mapper.SuggestionMapper;
import com.home.module.suggestion.service.ISuggestionService;
import com.home.module.user.service.IUserService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2018-11-01
 */
@Service
public class SuggestionServiceImpl extends ServiceImpl<SuggestionMapper, Suggestion> implements ISuggestionService {
	
	@Autowired
	private IUserService userService;

	@Override
	public IPage<Suggestion> list(Integer page, Integer size) {
		Page<Suggestion> pg = new Page<>(page, size);
		IPage<Suggestion> result = baseMapper.selectPage(pg, new QueryWrapper<Suggestion>().orderByDesc("create_time"));
		List<Suggestion> collect = result.getRecords()
										 .stream()
										 .map((x) -> {
											 String value = SuggestStatusEnum.getValue(x.getStatus());
											 UserModel user = userService.getById(x.getUserId());
											  
											 x.setStatusValue(value);
											 x.setUserName(user.getName());
											 return x;
										 })
										 .collect(Collectors.toList());
		
		result.setRecords(collect);
		return result;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void store(Suggestion suggestion) {
		suggestion.setStatus(0);
		suggestion.setCreateTime(LocalDateTime.now());
		
		baseMapper.insert(suggestion);
	}

}
