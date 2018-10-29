package com.home.module.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.home.model.ResponseBean;
import com.home.model.UserModel;
import com.home.module.service.IUserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService userService;

	@GetMapping("/v1/queryAllUser")
	public UserModel queryUser() {
		return userService.queryUser().get(0);
	}

	@GetMapping("/v1/queryUserByCode")
	public ResponseBean queryUserByCode(@RequestParam("code") String code, @RequestParam("userInfo") String userInfo) {
		UserModel user = userService.userLogin(code, userInfo);

		return ResponseBean.succ(user);
	}
}
