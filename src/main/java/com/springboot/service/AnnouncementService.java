package com.springboot.service;

import com.springboot.dto.AnnouncementDto;
import com.springboot.dto.ManagerDto;
import com.springboot.pojo.WoPage;

public interface AnnouncementService {
	void createAnnouncement(AnnouncementDto dto, ManagerDto managerDto);// 创建通知

	WoPage<AnnouncementDto> getList(String name, Long page, Long size);// 分页查询通知信息

	AnnouncementDto getById(int id2);//通过ID获取一条通知信息

}
