package com.springboot.demo;


import com.springboot.dto.UserDto;

import java.util.Map;

public class MisUtil {
	
	/**
	 * @param map
	 * @return
	 */
	public static UserDto getCurrentUser(Map<String, Object> map) {
		UserDto u = (UserDto) map.get(MisConstant.SESSION_USER);
		if (u == null) {
			throw new MisException(MisConstant.ERR_LOGIN_NOT);
		}
		return u;
	}
}
