package com.home.module.suggestion.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.home.model.Suggestion;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2018-11-01
 */
public interface ISuggestionService extends IService<Suggestion> {

	IPage<Suggestion> list(Integer page, Integer size);

	void store(Suggestion suggestion);

}
