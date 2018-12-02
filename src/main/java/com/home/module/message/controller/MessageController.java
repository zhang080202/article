package com.home.module.message.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.home.common.exception.ServiceException;
import com.home.model.Message;
import com.home.model.ResponseBean;
import com.home.module.message.service.IMessageService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 留言
  * <p>Title: MessageController</p>  
  * <p>Description: </p>  
  * @author zhangyf 
  * @date 2018年11月30日
 */
@RestController
@RequestMapping("/message")
@Api(tags = "留言功能相关接口")
public class MessageController {
	
	private static Logger logger = LoggerFactory.getLogger(MessageController.class);
	
	@Autowired
	private IMessageService messageService;
	
	@PostMapping("/v1/saveMessage")
	@ApiOperation("保存留言")
	public ResponseBean saveMessage(@RequestBody Message msg) {
		try {
			messageService.saveMessage(msg);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return ResponseBean.fail(e.getMessage());
		} catch (Exception e) {
			logger.error("保存留言 接口异常 Caused by " + e);
			e.printStackTrace();
			return ResponseBean.fail();
		}
		return ResponseBean.succ();
	}
	
	@GetMapping("/v1/list/{articleId}")
	@ApiOperation("根据文章ID获取留言列表")
	public ResponseBean list(@PathVariable("articleId") String articleId) {
		IPage<Message> page = null;
		try {
			page = messageService.queryMessageList(articleId);
		} catch (Exception e) {
			logger.error("根据文章ID获取留言列表 接口异常 Caused by " + e);
			e.printStackTrace();
			return ResponseBean.fail();
		}
		return ResponseBean.succ(page);
	}
}
