package com.home.module.praise.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.home.model.UserPraise;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2018-11-29
 */
public interface IUserPraiseService extends IService<UserPraise> {

	void cancelPraise(String articleId, String userId);

}
