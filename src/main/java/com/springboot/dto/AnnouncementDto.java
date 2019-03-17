package com.springboot.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.springboot.po.Announcement;

import com.springboot.po.Manager;
import com.springboot.pojo.WoPage;

public class AnnouncementDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;// id

	private String announcementNo;// 通知编号

	private String announcementName;// 通知题目

	private String information;// 通知内容

	private Date date;// 通知时间

	private Manager manager;// 通知人

	// set 和get方法

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAnnouncementNo() {
		return announcementNo;
	}

	public void setAnnouncementNo(String announcementNo) {
		this.announcementNo = announcementNo;
	}

	public String getAnnouncementName() {
		return announcementName;
	}

	public void setAnnouncementName(String announcementName) {
		this.announcementName = announcementName;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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
	public AnnouncementDto() {

	}

	public AnnouncementDto(Announcement announcement) {
		this.announcementNo = announcement.getAnnouncementNo();
		this.id = announcement.getId();
		this.announcementName = announcement.getAnnouncementName();
		this.information = announcement.getInformation();
		this.date = announcement.getDate();
		this.manager = announcement.getManager();
	}

	// 将po列表数据转化为Dto列表数据
	public static List<AnnouncementDto> getList(List<Announcement> announcements) {
		List<AnnouncementDto> announcementDto = new ArrayList<AnnouncementDto>();
		for (Announcement announcement : announcements) {
			AnnouncementDto r = new AnnouncementDto(announcement);
			announcementDto.add(r);
		}
		return announcementDto;
	}

	// 将po分页数据转化为Dto分页数据
	public static WoPage<AnnouncementDto> getPageData(List<Announcement> pos,
			Long total) {
		WoPage<AnnouncementDto> puDto = new WoPage<AnnouncementDto>(
				getList(pos), total);
		return puDto;
	}

	// 创建PO对象
	public Announcement createPO() {
		Announcement announcement = new Announcement();
		announcement.setId(this.id);
		announcement.setAnnouncementNo(this.announcementNo);
		announcement.setAnnouncementName(this.announcementName);
		announcement.setInformation(this.information);
		announcement.setDate(new Date());
		announcement.setManager(this.manager);
		return announcement;
	}
	//判断对象是否为空
	public boolean isEmpty(AnnouncementDto announcementDto){
		if(announcementDto.getAnnouncementNo()==""||announcementDto.getAnnouncementName()==""
				||announcementDto.getInformation()==""){
			return true;
		}else{
			return false;
		}
		
	}

}
