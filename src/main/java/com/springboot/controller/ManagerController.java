package com.springboot.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.springboot.demo.MisConstant;
import com.springboot.dto.ManagerDto;
import com.springboot.dto.UserDto;
import com.springboot.service.ManagerService;
import com.springboot.service.UserService;

@Controller
@SessionAttributes(MisConstant.SESSION_USER)
public class ManagerController {
	@Resource
	private ManagerService managerservice;
	@Resource
	private UserService userservice;
	/**
	 * 查看个人信息
	 */
	@RequestMapping("/findmanagerinfo")
	public String findManagerInfo(Map<String, Object> map){
		UserDto u=(UserDto) map.get(MisConstant.SESSION_USER);
		ManagerDto managerDto=managerservice.findManagerInfo(u.getAccount());
		map.put("manager", managerDto);
		System.out.print(managerDto.getManagerName());
		return "manager/managerinfo";
	}
	/**
	 * 跳转到修改个人信息页面
	 */
	@RequestMapping("/toupdatemanagerinfo")
	public String toUpdateManagerInfo(Map<String, Object> map){
		UserDto u=(UserDto) map.get(MisConstant.SESSION_USER);
		ManagerDto managerDto=managerservice.findManagerInfo(u.getAccount());
		map.put("managerinfo", managerDto);
	    System.out.print(managerDto.getManagerName());
		return "manager/updatemanagerinfo";
	}
	/**
	 * 修改个人信息
	 */
	@RequestMapping("/updatemanagerinfo")
	public String updateManagerInfo(ManagerDto managerDto,Map<String, ManagerDto> map){
		System.out.println("ok");
		
		System.out.print(managerDto.getManagerNo());
		managerservice.updateManagerInfo(managerDto);
		return "manager/manager";
	}
	/**
	 * 停留在管理员主页
	 */
	@RequestMapping("manager")
	public String toManagerIndex(){
		return "manager/manager_index";
	}
	/**
	 * 跳转到管理员信息页面
	 */
	@RequestMapping("managerinfo")
	public String toManager(){
		return "/manager/manager";
	}
	/**
	 * 跳转到修改管理员密码页面
	 */
	@RequestMapping("/toUpdateManagerPassword")
	public String toupdatemanagerpassword(Map<String, UserDto> map){
		UserDto u=(UserDto) map.get(MisConstant.SESSION_USER);
		u=userservice.findUser(u.getAccount());
		map.put("user", u);
		System.out.print(u.getAccount());
		return "manager/updatePassword";
	}
	/**
	 * 修改密码功能
	 */
	
	@RequestMapping("/updatemanagerpassword")
	public String udaptestudentpassword(UserDto userDto, Map<String, Object>map){
		userservice.updatePassword(userDto);
		System.out.print("success");
		return "/manager/manager";
	}
	

}
