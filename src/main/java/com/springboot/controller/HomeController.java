package com.springboot.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.springboot.demo.MisConstant;
import com.springboot.demo.MisException;
import com.springboot.dto.UserDto;
import com.springboot.service.UserService;

@Controller
@SessionAttributes(MisConstant.SESSION_USER)
public class HomeController {
	@Resource
	private UserService userservice;
	/**
	 * 跳转到登录页面
	 * @param error
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String goLogin(String error, Map<String, Object> map) {
		
		return "login";
	}
	
	/**
	 * 登录验证跳转，分别跳转到管理员、老师、学生主页
	 * @param username
	 * @param password
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(String username, String password, Map<String, Object> map) {
		String returninfo="";//定义返回值变量
		
		try {
			UserDto u = userservice.authenticate(username, password);
			if(u.getRole().equals("1")){
				map.put(MisConstant.SESSION_USER, u);
				returninfo="manager/manager_index";//跳转到管理员主页
			}
			if (u.getRole().equals("2")){
				map.put(MisConstant.SESSION_USER, u);
				returninfo="teacher/teacher_index";//跳转到老师主页
			}
			if (u.getRole().equals("3")){
				map.put(MisConstant.SESSION_USER, u);
				returninfo="student/student_index";//跳转到学生主页
			}
			return returninfo;
		} catch (Exception e) {
			if (e instanceof MisException) {
				map.put("error", e.getMessage());
			} else {
				map.put("error", "认证失败！");
			}
			return "login";
		}
		
	}
	/**
	 * 注销登录功能
	 */
	@RequestMapping(value = "/logout")
	public String logout(Map<String, Object> map, SessionStatus sessionStatus) {
		map.remove(MisConstant.SESSION_USER);
		System.out.print("ok");
		sessionStatus.setComplete();
		return "login";
	}
}
