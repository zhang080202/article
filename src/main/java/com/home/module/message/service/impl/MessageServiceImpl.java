package com.home.module.message.service.impl;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.home.common.exception.ServiceException;
import com.home.common.utils.CommonUtil;
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
		
		if (StringUtils.isBlank(msg.getMsgContent())) 
			throw new ServiceException("请输入留言内容");
		
		
		msg.setCreateTime(LocalDateTime.now());
		baseMapper.insert(msg);
	}

	@Override
	public IPage<Map<String, Object>> queryMessageList(String articleId) {
		Page<Map<String, Object>> page = new Page<>(0, 10);
		Map<String, Object> params = new HashMap<>();
		params.put("articleId", articleId);
		List<Map<String, Object>> list = baseMapper.queryMessageByArticle(page, params);
		for (Map<String, Object> map : list) {
			Timestamp time = (Timestamp) map.get("createTime");
		    map.put("createTime", CommonUtil.timestamp2LocalDateTime(time));
		}
		page.setRecords(list);
		return page;
	}

}
