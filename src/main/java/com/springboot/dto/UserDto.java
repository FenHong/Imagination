package com.springboot.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.springboot.po.User;
import com.springboot.pojo.WoPage;

public class UserDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;// id

	private String account;// 账号

	private String passWord;// 密码

	private String role;// 角色

	// get和set方法

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	// 构造方法
	public UserDto() {

	}

	public UserDto(User user) {
		this.id = user.getId();
		this.account = user.getAccount();
		this.passWord = user.getPassWord();
		this.role = user.getRole();
	}

	// 将po列表数据转化为Dto列表数据
	public static List<UserDto> getList(List<User> users) {
		List<UserDto> userDto = new ArrayList<UserDto>();
		for (User user : users) {
			UserDto r = new UserDto(user);
			userDto.add(r);
		}
		return userDto;
	}

	// 将po分页数据转化为Dto分页数据
	public static WoPage<UserDto> getPageData(List<User> pos, Long total) {
		WoPage<UserDto> puDto = new WoPage<UserDto>(getList(pos), total);
		return puDto;
	}

	// 创建po对象
	public User createPO() {
		User user = new User();
		user.setId(this.id);
		user.setAccount(this.account);
		user.setPassWord(this.passWord);
		user.setRole(this.role);
		return user;
	}
}
