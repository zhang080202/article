package com.home.module.user.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.home.common.utils.HttpUtil;
import com.home.configuration.ArticleConfiguration;
import com.home.model.UserModel;
import com.home.module.user.mapper.UserMapper;
import com.home.module.user.service.IUserService;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserModel> implements IUserService {

	@Autowired
	private ArticleConfiguration config;

	@Override
	public List<UserModel> queryUser() {
		return baseMapper.selectList(new QueryWrapper<UserModel>(null));
	}

	@Override
	public UserModel userLogin(String code, String userInfo) {
		String appid = config.getAppid();
		String secret = config.getSecret();
		// 获取微信 会话秘钥
		String str = HttpUtil.sendGet("https://api.weixin.qq.com/sns/jscode2session?appid=" + appid + "&secret="
				+ secret + "&js_code=" + code + "&grant_type=authorization_code");
		Map<String, Object> data = (Map<String, Object>) JSON.parse(str);
		String sessionKey = data.get("session_key").toString();
		String openId = data.get("openid").toString();

		// 查询数据库是否存在该用户
		UserModel user = baseMapper.selectById(openId);
		if (user == null) {
			// 第一次登陆
			// 解析用户信息
			Map<String, Object> info = (Map<String, Object>) JSON.parse(userInfo);

			user = new UserModel();
			user.setUserId(openId);
			user.setLoginNum(1);
			user.setLastDate(new Date());
			user.setName(info.get("nickName").toString());
			user.setCity(info.get("city").toString());
			user.setProvince(info.get("province").toString());
			user.setGender(Integer.parseInt(info.get("gender").toString()));
			user.setSessionKey(sessionKey);

			baseMapper.insert(user);
		} else {

			user.setLoginNum(user.getLoginNum() + 1);
			user.setLastDate(new Date());

			baseMapper.updateById(user);
		}
		user.setSessionKey(null);
		return user;
	}

	@Override
	public UserModel queryUser(String userId) {
		return baseMapper.selectById(userId);
	}

}
