package com.springboot.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.springboot.po.Manager;
import com.springboot.po.ManagerFile;
import com.springboot.pojo.WoPage;

public class ManagerFileDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;// ID

	private String managerFileNo;// 文件编号

	private String managerFileName;// 文件名

	private String filePath;// 文件存储路径

	private Manager manager;// 文件发布人

	// get和set方法
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getManagerFileNo() {
		return managerFileNo;
	}

	public void setManagerFileNo(String managerFileNo) {
		this.managerFileNo = managerFileNo;
	}

	public String getManagerFileName() {
		return managerFileName;
	}

	public void setManagerFileName(String managerFileName) {
		this.managerFileName = managerFileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	// 构造方法
	public ManagerFileDto() {

	}

	public ManagerFileDto(ManagerFile managerfile) {
		this.id = managerfile.getId();
		this.managerFileNo = managerfile.getManagerFileNo();
		this.managerFileName = managerfile.getManagerFileName();
		this.filePath = managerfile.getFilePath();
		this.manager = managerfile.getManager();
	}

	// 将po列表数据转化为Dto列表数据
	public static List<ManagerFileDto> getList(List<ManagerFile> managerfiles) {
		List<ManagerFileDto> managerfileDto = new ArrayList<ManagerFileDto>();
		for (ManagerFile managerfile : managerfiles) {
			ManagerFileDto r = new ManagerFileDto(managerfile);
			managerfileDto.add(r);
		}
		return managerfileDto;
	}

	// 将po分页数据转化为Dto分页数据
	public static WoPage<ManagerFileDto> getPageData(List<ManagerFile> pos,
			Long total) {
		WoPage<ManagerFileDto> puDto = new WoPage<ManagerFileDto>(getList(pos),
				total);
		return puDto;
	}

	// 创建po对象
	public ManagerFile createPO() {
		ManagerFile managerfile = new ManagerFile();
		managerfile.setId(this.id);
		managerfile.setManagerFileNo(this.managerFileNo);
		managerfile.setManagerFileName(this.managerFileName);
		managerfile.setFilePath(this.filePath);
		managerfile.setManager(this.manager);
		return managerfile;

	}

}
