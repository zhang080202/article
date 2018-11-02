package com.home.module.banner.service.impl;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.home.model.Banner;
import com.home.module.banner.mapper.BannerMapper;
import com.home.module.banner.service.IBannerService;

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
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements IBannerService {
	
	@Autowired
	private BannerMapper bannerMapper;
	/**
	 * 根据上传OSS 文件ID 存入banner表中
	 */
	@Override
	public void insertBanner(String ossId) {
		Banner banner = new Banner();
		banner.setUrl(ossId);
		banner.setCreateTime(LocalDateTime.now());
		banner.setRemark("通过swagger保存");
		banner.setCreateUser("default");
		this.save(banner);
	}

	@Override
	public List<Map<String, Object>> getBannerList() {
		return bannerMapper.getBannerList();
	}
	
	public static void main(String[] args) {
		Long time = 1541145912599L;
		Assert.notNull(time, "time is null");
        DateTimeFormatter ftf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String format = ftf.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(time),ZoneId.systemDefault()));
        System.out.println(format);
	}

}
