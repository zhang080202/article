package com.home.module.banner.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.home.model.ResponseBean;
import com.home.module.banner.service.IBannerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 首页轮播图管理
 * @author zhangyf
 *
 */
@RestController
@RequestMapping("/banner")
@Api(tags = "首页轮播图管理")
public class BannerController {
	
	@Autowired
	private IBannerService bannerService;
	
	@GetMapping("/v1/getBannerList")
	@ApiOperation("获取轮播图信息列表")
	public ResponseBean getBannerList() {
		List<Map<String, Object>> params = null;
		try {
			params = bannerService.getBannerList();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseBean.fail();
		}
		return ResponseBean.succ(params);
	}
}
