package com.home.module.dict.controller;


import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.home.model.ResponseBean;
import com.home.module.dict.service.ISysConfigService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 *  字典表相关接口
  * <p>Title: SysConfigController</p>  
  * <p>Description: </p>  
  * @author zhangyf 
  * @date 2018年11月8日
 */
@RestController
@RequestMapping("/dict")
@Api(tags = "字典表相关接口")
public class SysConfigController {
	
	private static Logger logger = LoggerFactory.getLogger(SysConfigController.class);
	
	@Autowired
	private ISysConfigService sysConfigService;
	
	@GetMapping("/v1/getDictByKey/{key}")
	@ApiOperation("根据KEY 获取字典表")
	public ResponseBean getDictByKey(@PathVariable("key") String key) {
		Map<String, Object> result = null;
		try {
			result = sysConfigService.getDictByKey(key);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("根据KEY 获取字典表 接口异常 Caused by " + e);
			return ResponseBean.fail("根据KEY 获取字典表 接口异常 Caused by " + e);
		}
		return ResponseBean.succ(result);
	}
}
