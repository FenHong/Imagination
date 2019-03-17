package com.springboot.service;

import com.springboot.dto.ManagerDto;

public interface ManagerService {
	ManagerDto findManagerInfo(String name);// 通过管理员账号查找用户

	void updateManagerInfo(ManagerDto managerDto);// 更新管理员信息

}
