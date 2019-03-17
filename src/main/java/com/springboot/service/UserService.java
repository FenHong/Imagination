package com.springboot.service;

import com.springboot.dto.UserDto;

public interface UserService {

	UserDto authenticate(String username, String password);// 登录验证方法

	UserDto findUser(String username);// 通过账号查找用户

	void updatePassword(UserDto userDto);// 修改密码

}
