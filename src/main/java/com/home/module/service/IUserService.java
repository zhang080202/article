package com.home.module.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.home.model.UserModel;

public interface IUserService extends IService<UserModel> {

	public List<UserModel> queryUser();

	public UserModel userLogin(String code, String userInfo);
}
