package com.home.module.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.home.model.UserModel;

@Mapper
public interface UserMapper extends BaseMapper<UserModel> {

}
