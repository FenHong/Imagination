package com.springboot.demo;

import com.springboot.pojo.WoResultCode;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class MisConstant {
	@SuppressWarnings("unused")
	private final static Logger LOG = LogManager.getLogger(MisConstant.class);
	
	public final static String APP_JSON = "application/json";
	
	public final static String SESSION_USER = "woUser";
	
	public final static String SESSION_STUDENT = "woStudent";
	
	public final static WoResultCode ERR_LOGIN = new WoResultCode (102, "用户名或者密码不正确！");
	
	public final static WoResultCode ERR_LOGIN_NOT = new WoResultCode (104, "请登录！");
	

}
