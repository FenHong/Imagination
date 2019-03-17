package com.springboot.service.imp;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.springboot.dto.ManagerDto;
import com.springboot.po.Manager;
import com.springboot.repository.ManagerRepository;
import com.springboot.service.ManagerService;

@Service
public class ManagerServiceImp implements ManagerService {
	@Resource
	private ManagerRepository managerdao;

	/**
	 * 查找管理员信息
	 */
	public ManagerDto findManagerInfo(String name) {
		Manager manager = managerdao.findByManagerNo(name);
		ManagerDto managerDto = new ManagerDto(manager);
		return managerDto;
	}

	/**
	 * 更新管理员信息
	 */
	public void updateManagerInfo(ManagerDto managerDto) {
		Manager manager = managerdao.findByManagerNo(managerDto.getManagerNo());
		manager.setAge(managerDto.getAge());
		manager.setManagerName(managerDto.getManagerName());
		manager.setSex(managerDto.getSex());
		managerdao.save(manager);

	}

}
