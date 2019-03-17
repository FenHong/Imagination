package com.springboot.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.springboot.demo.MisConstant;
import com.springboot.dto.AnnouncementDto;
import com.springboot.dto.ManagerDto;
import com.springboot.dto.UserDto;
import com.springboot.pojo.WoPage;
import com.springboot.pojo.WoResultCode;
import com.springboot.service.AnnouncementService;
import com.springboot.service.ManagerService;

@Controller
@SessionAttributes(MisConstant.SESSION_USER)
public class AnnouncementController {
	/**
	 * 消息通知接口
	 */
	@Resource
	private AnnouncementService announcementservice;
	/**
	 * 管理员接口
	 */
	@Resource
	private ManagerService managerservice;
	/**
	 * 跳转到管理员通知主页
	 */
	@RequestMapping("managerannouncement")
	public String toManagerAnnouncementIndex(){
		return "manager/announcement/managerannouncement";
	}
	/**
	 * 跳转到管理员发布通知页面
	 * 
	 */
	@RequestMapping("toaddannouncement")
	public String toCreateAnnouncement(){
		return "manager/announcement/announcement_create";
	}
	/**
	 * 创建通知
	 * 
	 */
	@ResponseBody
	@RequestMapping(value="/createannouncement",method=RequestMethod.POST)
	public WoResultCode createAnnouncement(AnnouncementDto announcementDto ,Map<String,Object> map){
		UserDto userDto=(UserDto) map.get(MisConstant.SESSION_USER);
		ManagerDto managerDto=managerservice.findManagerInfo(userDto.getAccount());
		String message=null;
		if (announcementDto.isEmpty(announcementDto)){
			message="请输入完整的通知信息";
		}else{
			
		message="创建通知成功！";
		announcementservice.createAnnouncement(announcementDto,managerDto);
		}
		return WoResultCode.getSuccessCode().setMsg(message);
	}
	
	/**
	 * 跳转到管理员通知查询页
	 * 
	 */
	@RequestMapping("announcement_list")
	public String toQueryAnnouncement(){
		return "manager/announcement/managerannouncement_query";
	}
	/**
	 * 管理员查询通知
	 */
	@RequestMapping("managerannouncement_query")
	public String ManagerAnnouncementlist (String name, @RequestParam(defaultValue="1") Long page, Map<String, Object>map) {
		WoPage<AnnouncementDto> announcements = announcementservice.getList(name, page, WoPage.SIZE);
		announcements.setPage(page);
		map.put("announcements", announcements);
		return "manager/announcement/announcement_list";
	}
	/**
	 * 跳转到学生通知主页
	 */
	@RequestMapping("studentannouncement")
	public String toStudentAnnouncementIndex(){
		return "student/announcement/studentannouncement";
	}
	/**
	 * 跳转到学生通知查询页
	 * 
	 */
	@RequestMapping("studentannouncement_list")
	public String toQueryStudentAnnouncement(){
		return "student/announcement/studentannouncement_query";
	}
	/**
	 * 查询通知
	 */
	@RequestMapping("studentannouncement_query")
	public String StudentAnnouncementlist (String name, @RequestParam(defaultValue="1") Long page, Map<String, Object>map) {
		WoPage<AnnouncementDto> announcements = announcementservice.getList(name, page, WoPage.SIZE);
		announcements.setPage(page);
		map.put("announcements", announcements);
		return "student/announcement/announcement_list";
	}
	/**
	 * 跳转到老师通知主页
	 */
	@RequestMapping("teacherannouncement")
	public String toTeacherAnnouncementIndex(){
		return "teacher/announcement/teacherannouncement";
	}
	/**
	 * 跳转到老师通知查询页
	 * 
	 */
	@RequestMapping("teacherannouncement_list")
	public String toQueryTeacherAnnouncement(){
		return "teacher/announcement/teacherannouncement_query";
	}
	/**
	 * 查询通知
	 */
	@RequestMapping("teacherannouncement_query")
	public String TeacherAnnouncementlist (String name, @RequestParam(defaultValue="1") Long page, Map<String, Object>map) {
		WoPage<AnnouncementDto> announcements = announcementservice.getList(name, page, WoPage.SIZE);
		announcements.setPage(page);
		map.put("announcements", announcements);
		return "teacher/announcement/announcement_list";
	}
	/**
	 * 查看通知公告详情
	 */
	@RequestMapping("toannouncementinformation")
	public String toQueryAnnouncemntInformation(String id, Map<String, Object>map){
		int id2=Integer.parseInt(id);
		String returnvalue=null;
		AnnouncementDto s=announcementservice.getById(id2);
		UserDto userDto = (UserDto) map.get(MisConstant.SESSION_USER);
		if (userDto.getRole().equals("1")){
			returnvalue="manager/announcement/announcement_info";
		}else if(userDto.getRole().equals("2")){
			returnvalue="teacher/announcement/announcement_info";
		}else if(userDto.getRole().equals("3")){
			returnvalue="student/announcement/announcement_info";
		}
		map.put("announcement", s);
		
		return returnvalue;
	}

}
