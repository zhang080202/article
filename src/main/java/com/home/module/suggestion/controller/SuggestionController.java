package com.home.module.suggestion.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.home.common.exception.ServiceException;
import com.home.common.utils.ValidatorUtil;
import com.home.model.ResponseBean;
import com.home.model.Suggestion;
import com.home.module.suggestion.service.ISuggestionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2018-11-01
 */
@RestController
@RequestMapping("/suggestion")
@Api(tags = "用户意见反馈")
public class SuggestionController {
	
	private static Logger logger = LoggerFactory.getLogger(SuggestionController.class);
	
	@Autowired
	private ISuggestionService suggestionService;
	
	@GetMapping
	@ApiOperation("分页查询意见反馈列表")
	public ResponseBean list(@RequestParam(name = "page", defaultValue = "1") Integer page,
							 @RequestParam(name = "size", defaultValue = "10") Integer size) {
		IPage<Suggestion> result = suggestionService.list(page, size);
		return ResponseBean.succ(result);
	}
	
	@PostMapping
	@ApiOperation("保存意見反饋")
	public ResponseBean store(@RequestBody Suggestion suggestion) {
		try {
			ValidatorUtil.validatorModel(suggestion);
		} catch (ServiceException e) {
			e.printStackTrace();
			logger.error("保存文章数据错误  " + e);
			return ResponseBean.fail(e.getMessage());
		}
		suggestionService.store(suggestion);
		return ResponseBean.succ();
	}

}
