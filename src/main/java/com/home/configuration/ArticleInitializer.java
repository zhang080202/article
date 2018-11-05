package com.home.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.home.common.utils.OssUtil;

@Component
public class ArticleInitializer implements CommandLineRunner {
	
	@Autowired
	private ArticleConfiguration config;
	
	@Override
	public void run(String... args) throws Exception {
		initParams();
	}
	
	/**
	 * 初始化阿里云OSS配置参数
	 */
	private void initParams() {
		OssUtil.endpoint = config.getEndpoint();
		OssUtil.accessKeyId = config.getAccessKeyId();
		OssUtil.accessKeySecret = config.getAccessKeySecret();
		OssUtil.bucketName = config.getBucketName();
	}

}
