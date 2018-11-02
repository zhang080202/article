package com.home.module.banner.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.home.model.Banner;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2018-11-01
 */
public interface IBannerService extends IService<Banner> {

	void insertBanner(String ossId);

	List<Map<String, Object>> getBannerList();

}
