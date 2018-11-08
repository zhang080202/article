package com.home.module.oss.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.home.common.utils.OssUtil;
import com.home.configuration.ArticleConfiguration;
import com.home.model.ResponseBean;
import com.home.module.oss.service.ISysOssService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 阿里云OSS对象服务接口
 * 
 * @author zhangyf
 *
 */
@RestController
@RequestMapping("/oss")
@Api(tags = "阿里云OSS对象储存服务接口")
public class SysOssController {

	private static Logger			logger	= LoggerFactory.getLogger(SysOssController.class);

	@Autowired
	private ISysOssService			sysOssService;

	@PostMapping("/v1/uploadFile")
	@ApiOperation("文件上传接口")
	public ResponseBean uploadFile(@RequestParam("file") MultipartFile file) {
		String url = "";
		try {
			url = new OssUtil().uploadFile(file);
			sysOssService.saveSysOss(url, null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("阿里云OSS 文件上传失败 Caused by " + e);
			return ResponseBean.fail("阿里云OSS 文件上传失败 Caused by " + e);
		}
		return ResponseBean.succ(url);
	}
	
	@PostMapping("/v1/uploadBanner")
	@ApiOperation("文件上传接口")
	public ResponseBean uploadBanner(@RequestParam("file") MultipartFile file) {
		String url = "";
		try {
			url = new OssUtil().uploadFile(file);
			sysOssService.saveSysOss(url, "banner");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("阿里云OSS 文件上传失败 Caused by " + e);
			return ResponseBean.fail("阿里云OSS 文件上传失败 Caused by " + e);
		}
		return ResponseBean.succ(url);
	}

}
