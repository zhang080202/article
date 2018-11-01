package com.home.module.suggestion.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.home.model.Suggestion;
import com.home.module.suggestion.mapper.SuggestionMapper;
import com.home.module.suggestion.service.ISuggestionService;

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

}
