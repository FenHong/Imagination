package com.springboot.service.imp;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.springboot.demo.MisConstant;
import com.springboot.demo.MisException;
import com.springboot.dto.UserDto;
import com.springboot.po.User;
import com.springboot.repository.UserRepository;
import com.springboot.service.UserService;

@Service
@Transactional
public class UserServiceImp implements UserService {
	@Resource
	private UserRepository userdao;

	// 登录验证
	public UserDto authenticate(String username, String password) {
		User user = userdao.findByAccount(username);// 从数据库查询数据
		// 对查询结果进行判断
		if (user == null) {
			throw new MisException(MisConstant.ERR_LOGIN);
		}
		if (!password.equals(user.getPassWord())) {
			throw new MisException(MisConstant.ERR_LOGIN);
		}
		// 构造Dto对象
		UserDto userDto = new UserDto(user);
		return userDto;

	}

	// 通过账号查找用户
	public UserDto findUser(String username) {
		User user = userdao.findByAccount(username);
		UserDto userDto = new UserDto(user);
		return userDto;
	}

	// 修改密码
	public void updatePassword(UserDto userDto) {
		User user = userdao.findByAccount(userDto.getAccount());
		user.setId(userDto.getId());
		user.setAccount(userDto.getAccount());
		user.setPassWord(userDto.getPassWord());

		userdao.save(user);

	}

}
