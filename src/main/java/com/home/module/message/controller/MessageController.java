package com.home.module.message.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.home.model.Message;
import com.home.model.ResponseBean;
import com.home.module.message.service.IMessageService;

/**
 * 留言
  * <p>Title: MessageController</p>  
  * <p>Description: </p>  
  * @author zhangyf 
  * @date 2018年11月30日
 */
@RestController
@RequestMapping("/message")
public class MessageController {
	
	@Autowired
	private IMessageService messageService;
	
	@PostMapping("/v1/saveMessage")
	public ResponseBean saveMessage(@RequestBody Message msg) {
		messageService.saveMessage(msg);
		return ResponseBean.succ();
	}
}
