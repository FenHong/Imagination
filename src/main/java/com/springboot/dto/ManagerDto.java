package com.springboot.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.springboot.po.Manager;
import com.springboot.po.User;
import com.springboot.pojo.WoPage;

public class ManagerDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;// id

	private String managerNo;// 管理员编号

	private String managerName;// 管理员姓名

	private String sex;// 性别

	private Integer age;// 年龄

	private User user;// 用户

	// get和set方法

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getManagerNo() {
		return managerNo;
	}

	public void setManagerNo(String managerNo) {
		this.managerNo = managerNo;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	// 构造方法
	public ManagerDto() {

	}

	public ManagerDto(Manager manager) {
		this.id = manager.getId();
		this.managerNo = manager.getManagerNo();
		this.managerName = manager.getManagerName();
		this.sex = manager.getSex();
		this.age = manager.getAge();
		this.user = manager.getUser();
	}

	// 将po列表数据转化为Dto列表数据
	public static List<ManagerDto> getList(List<Manager> managers) {
		List<ManagerDto> managerDto = new ArrayList<ManagerDto>();
		for (Manager manager : managers) {
			ManagerDto r = new ManagerDto(manager);
			managerDto.add(r);
		}
		return managerDto;

	}

	// 将po分页数据转化为Dto分页数据
	public static WoPage<ManagerDto> getPageData(List<Manager> pos, Long total) {
		WoPage<ManagerDto> puDto = new WoPage<ManagerDto>(getList(pos), total);
		return puDto;
	}

	// 创建po对象
	public Manager createPO() {
		Manager manager = new Manager();
		manager.setId(this.id);
		manager.setManagerNo(this.managerNo);
		manager.setManagerName(this.managerName);
		manager.setSex(this.sex);
		manager.setAge(this.age);
		this.setUser(this.user);
		return manager;
	}

}
