package com.home.module.message.service.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.home.model.Message;
import com.home.module.message.mapper.MessageMapper;
import com.home.module.message.service.IMessageService;

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

	@Override
	@Transactional
	public void saveMessage(Message msg) {
		msg.setCreateTime(LocalDateTime.now());
		baseMapper.insert(msg);
	}

}
