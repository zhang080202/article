package com.home.module.message.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.home.common.exception.ServiceException;
import com.home.model.Message;
import com.home.model.UserModel;
import com.home.module.message.mapper.MessageMapper;
import com.home.module.message.service.IMessageService;
import com.home.module.user.service.IUserService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2018-11-30
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements IMessageService {
	
	@Autowired
	private IUserService userService;
	
	@Override
	@Transactional
	public void saveMessage(Message msg) {
		int count = userService.count(new QueryWrapper<UserModel>().eq("msg_flag", 0)
													   			   .eq("user_id", msg.getUserId()));
		if (count == 0) 
			throw new ServiceException("抱歉，您已被禁言");
		
		msg.setCreateTime(LocalDateTime.now());
		baseMapper.insert(msg);
	}

	@Override
	public IPage<Message> queryMessageList(String articleId) {
		Page<Message> page = new Page<>(0, 10);
		return baseMapper.selectPage(page, new QueryWrapper<Message>().eq("article", articleId)
																	  .orderByDesc("order"));
	}

}
