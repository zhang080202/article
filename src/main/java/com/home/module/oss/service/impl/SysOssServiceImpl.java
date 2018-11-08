package com.home.module.oss.service.impl;

import java.time.LocalDateTime;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.home.model.SysOss;
import com.home.module.banner.service.IBannerService;
import com.home.module.oss.mapper.SysOssMapper;
import com.home.module.oss.service.ISysOssService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2018-11-01
 */
@Service
@Transactional
public class SysOssServiceImpl extends ServiceImpl<SysOssMapper, SysOss> implements ISysOssService {
	
	@Autowired
	private IBannerService bannerService;

	@Override
	public void saveSysOss(String url, String flag) {
		//保存sysoss表
		SysOss oss = new SysOss();
		oss.setOssUrl(url);
		oss.setCreateTime(LocalDateTime.now());
		this.save(oss);
		if (StringUtils.isNotBlank(flag)) {
			//根据ID 将数据存入banner表中
			bannerService.insertBanner(oss.getOssId());
		}
	}

}
