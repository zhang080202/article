package com.home.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

@TableName("sys_user")
public class UserModel {

	@TableId
	private String			userId;

	private String			sessionKey;

	private String			name;

	private String			city;

	private String			province;

	private String			phone;

	private LocalDate		birthday;

	private Integer			gender;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime	lastDate;

	private Integer			loginNum;

	private String			remark;

	private Integer			msgFlag;	// 判断 该用户是否有留言权限 1 无权限 0 有权限

	private String			avatarUrl;	// 用户头像

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public Integer getMsgFlag() {
		return msgFlag;
	}

	public void setMsgFlag(Integer msgFlag) {
		this.msgFlag = msgFlag;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public LocalDateTime getLastDate() {
		return lastDate;
	}

	public void setLastDate(LocalDateTime lastDate) {
		this.lastDate = lastDate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	public Integer getLoginNum() {
		return loginNum;
	}

	public void setLoginNum(Integer loginNum) {
		this.loginNum = loginNum;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
