package com.home.module.banner.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.home.model.Banner;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2018-11-01
 */
@Mapper
public interface BannerMapper extends BaseMapper<Banner> {

	List<Map<String, Object>> getBannerList();

}
