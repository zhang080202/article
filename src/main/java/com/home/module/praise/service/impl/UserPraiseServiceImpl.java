package com.home.module.praise.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.home.model.UserPraise;
import com.home.module.praise.mapper.UserPraiseMapper;
import com.home.module.praise.service.IUserPraiseService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2018-11-29
 */
@Service
public class UserPraiseServiceImpl extends ServiceImpl<UserPraiseMapper, UserPraise> implements IUserPraiseService {

	@Override
	@Transactional
	public void cancelPraise(String articleId, String userId) {
		baseMapper.delete(new QueryWrapper<UserPraise>().eq("user_id", userId)
														.eq("article_id", articleId));
	}

}
