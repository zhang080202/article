package com.home.module.oss.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectResult;
import com.home.configuration.ArticleConfiguration;
import com.home.model.ResponseBean;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 阿里云OSS对象服务接口
 * @author zhangyf
 *
 */
@RestController
@RequestMapping("/oss")
@Api(tags = "阿里云OSS对象储存服务接口")
public class SysOssController {
	
	private static Logger logger = LoggerFactory.getLogger(SysOssController.class);
	
	@Autowired
	private ArticleConfiguration config;
	
	@PostMapping("/v1/uploadFile")
	@ApiOperation("文件上传接口")
	public ResponseBean uploadFile(@RequestParam("file") MultipartFile file) {
		String endpoint = config.getEndpoint();
		String accessKeyId = config.getAccessKeyId();
		String accessKeySecret = config.getAccessKeySecret();
		String bucketName = config.getBucketName();
		String filename = file.getOriginalFilename();//.substring(0, file.getOriginalFilename().lastIndexOf("."));
		
		// 创建OSSClient实例。
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		//上传文件
		String url = "";
		try {
			ossClient.putObject(bucketName, filename, file.getInputStream());
			url = "https://" + bucketName + "." + endpoint + "/" + filename;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("阿里云OSS 文件上传失败 Caused by " + e);
			return ResponseBean.fail("阿里云OSS 文件上传失败 Caused by " + e);
		} finally {
			// 关闭OSSClient。
			ossClient.shutdown();
		}
		return ResponseBean.succ(url);
	}

}
