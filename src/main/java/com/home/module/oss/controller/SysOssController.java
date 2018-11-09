package com.home.module.oss.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.home.common.utils.OssUtil;
import com.home.model.ResponseBean;
import com.home.model.SysOss;
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
		SysOss oss = null;
		try {
			String url = new OssUtil().uploadFile(file);
			oss = sysOssService.saveSysOss(url, null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("阿里云OSS 文件上传失败 Caused by " + e);
			return ResponseBean.fail("阿里云OSS 文件上传失败 Caused by " + e);
		}
		return ResponseBean.succ(oss);
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
	
	@DeleteMapping("/v1/deleteImage/{ossId}")
	@ApiOperation("删除图片")
	public ResponseBean deleteImage(@PathVariable("ossId") String ossId) {
		try {
			SysOss oss = sysOssService.getById(ossId);
			new OssUtil().deleteImage(oss.getOssUrl());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("阿里云OSS 文件删除失败 Caused by " + e);
			return ResponseBean.fail("阿里云OSS 文件删除失败 Caused by " + e);
		}
		return ResponseBean.succ();
	}

}
