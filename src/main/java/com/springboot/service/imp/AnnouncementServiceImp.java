package com.springboot.service.imp;

import javax.annotation.Resource;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.springboot.dto.AnnouncementDto;
import com.springboot.dto.ManagerDto;
import com.springboot.po.Announcement;
import com.springboot.po.Manager;
import com.springboot.pojo.WoPage;
import com.springboot.repository.AnnouncementRepository;
import com.springboot.repository.ManagerRepository;
import com.springboot.service.AnnouncementService;

@Service
@Transactional
public class AnnouncementServiceImp implements AnnouncementService {
	@Resource
	private AnnouncementRepository announcementdao;
	@Resource
	private ManagerRepository managerdao;

	// 創建通知
	public void createAnnouncement(AnnouncementDto dto, ManagerDto managerDto) {
		Announcement announcement = dto.createPO();
		Manager manager = managerDto.createPO();
		announcement.setManager(managerdao.save(manager));
		announcementdao.save(announcement);

	}
   //分頁獲取通知
	public WoPage<AnnouncementDto> getList(String name, Long page, Long size) {
		// 从dao获取数据
		Pageable pageInput = PageRequest.of(page.intValue() - 1,
				size.intValue(), Direction.DESC, "id");
		Page<Announcement> pageData = null;
		if (StringUtils.isEmpty(name)) {
			pageData = announcementdao.findAll(pageInput);
		} else {
			pageData = announcementdao.findAllByAnnouncementNameLike("%" + name
					+ "%", pageInput);
		}
		// 将Student转化为StudentDto
		WoPage<AnnouncementDto> pr = AnnouncementDto.getPageData(
				pageData.getContent(), pageData.getTotalElements());
		return pr;
	}
	//通过ID从后台获取一条通知信息
	public AnnouncementDto getById(int id2) {
		Announcement announcement=announcementdao.findById(id2);
		return new AnnouncementDto(announcement);
	}

}
