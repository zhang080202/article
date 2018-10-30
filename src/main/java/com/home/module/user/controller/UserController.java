package com.home.module.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.home.common.utils.ValidatorUtil;
import com.home.model.ResponseBean;
import com.home.model.UserModel;
import com.home.module.user.service.IUserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService userService;

	@GetMapping("/v1/queryAllUser")
	public UserModel queryUser() {
		return userService.queryUser().get(0);
	}
	
	@GetMapping("/v1/queryUserById")
	public ResponseBean queryUser(@RequestParam("userId") String userId) {
		UserModel userModel = userService.queryUser(userId);
		return ResponseBean.succ(userModel);
	}

	@GetMapping("/v1/userLogin")
	public ResponseBean userLogin(@RequestParam("code") String code, @RequestParam("userInfo") String userInfo) {
		UserModel user = userService.userLogin(code, userInfo);

		return ResponseBean.succ(user);
	}
	
	@PostMapping("/v1/saveUserInfo")
	public ResponseBean saveUserInfo(@RequestBody UserModel user) {
		if (!ValidatorUtil.isMobile(user.getPhone())) {
			return ResponseBean.fail("请输入正确的手机号码");
		}
		userService.saveOrUpdate(user);
		return ResponseBean.succ("操作成功");
	}
}
