package com.home.module.message.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.home.model.Message;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2018-11-30
 */
public interface IMessageService extends IService<Message> {

	void saveMessage(Message msg);

}
