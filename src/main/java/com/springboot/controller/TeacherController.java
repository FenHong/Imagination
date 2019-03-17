package com.springboot.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;




import com.springboot.demo.MisConstant;
import com.springboot.dto.SelectSubjectDto;
import com.springboot.dto.TeacherDto;
import com.springboot.dto.UserDto;
import com.springboot.pojo.WoPage;
import com.springboot.pojo.WoResultCode;
import com.springboot.service.SelectSubjectService;
import com.springboot.service.TeacherService;
import com.springboot.service.UserService;

@Controller
@SessionAttributes(MisConstant.SESSION_USER)
public class TeacherController {
	@Resource
	private TeacherService teacherservice; 
	@Resource
	private UserService userservice;
	@Resource
	private SelectSubjectService selectsubjectservice;
	//管理员功能模块
	/**
	 * 跳转到管理员老师主页
	 */
	@RequestMapping("manager_teacherinfo")
	public String toManagerTeacherIndex(){
		return "manager/teacher/manager_teacherinfo";
	
	}
	/**
	 * 跳转到老师创建页面
	 */
	
	@RequestMapping("toaddteacher")
	public String toCreateTeacher(){
		return "manager/teacher/teacher_create";
	}
	/**
	 * 创建老师
	 * @param teacherDto
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/createteacher", method=RequestMethod.POST)
	public WoResultCode createTeacher(TeacherDto teacherDto){
		String message=null;
		if(teacherDto.isEmpty(teacherDto)){
			message="请输入完整的用户信息";
		}else{
			message="创建老师[" + teacherDto.getTeacherName() + "]成功！";
			teacherservice.createTeacher(teacherDto);
			
		}
		
		return WoResultCode.getSuccessCode().setMsg(message);
	}
	/**
	 * 跳转到老师查询页面
	 */
	@RequestMapping("toqueryteacher")
	public String toQueryTeacher(){
		return "manager/teacher/teacher_query";
	}
	/**
	 * 查询老师
	 */
	@RequestMapping("teacher_query")
	public String list (String name, @RequestParam(defaultValue="1") Long page, Map<String, Object>map) {
		WoPage<TeacherDto> teachers =teacherservice.getList(name, page, WoPage.SIZE);
		teachers.setPage(page);
		map.put("teachers", teachers);
		return "manager/teacher/teacher_list";
	}
	/**
	 * 加载修改页面
	 */
	@RequestMapping(value="/toteacherupdate", method=RequestMethod.GET)
	public String loadUpdateForm (String id, Map<String, Object>map) {
		int id2=Integer.parseInt(id);
		TeacherDto s = teacherservice.getById (id2);
		map.put("teacher", s);
		return "manager/teacher/teacher_update";
	}
	/**
	 * 修改老师
	 * @param s
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/teacherupdate", method=RequestMethod.POST)
	public WoResultCode update (TeacherDto s, Map<String, Object>map) {
		
		teacherservice.update (s);
		return WoResultCode.getSuccessCode().setMsg("修改老师[" + s.getTeacherName() + "]成功！");
	}
	/**
	 * 删除老师
	 * @param id
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/teacherdelete", method=RequestMethod.POST)
	public WoResultCode delete (String[] id, Map<String, Object> map) {
		teacherservice.delete(id);
		System.out.print("ok");
		return WoResultCode.getSuccessCode().setMsg("删除老师成功！");
	}
	//老师功能模块
	/**
	 * 留在老师主页
	 */
	@RequestMapping("teacher")
	public String toTeacherIndex(){
		return "teacher/teacher_index";
	}
	/**
	 * 跳转到老师信息主页
	 */
	@RequestMapping("teacherinfo")
	public String findTeacherInfo(Map<String, Object> map){
		UserDto u=(UserDto) map.get(MisConstant.SESSION_USER);
		System.out.print(u.getAccount());
		TeacherDto teacherDto=teacherservice.findTeacherInfo(u.getAccount());
		map.put("teacher", teacherDto);
		
		return "teacher/teacherinfo";
	}
	/**
	 * 跳转到修改个人信息页面
	 */
	@RequestMapping("/toupdateteacherinfo")
	public String toUpdateTeacherInfo(Map<String, Object> map){
		UserDto u=(UserDto) map.get(MisConstant.SESSION_USER);
		TeacherDto teacherDto=teacherservice.findTeacherInfo(u.getAccount());
		map.put("teacherinfo", teacherDto);
	    System.out.print(teacherDto.getTeacherName());
		return "teacher/updateteacherinfo";
	}
	/**
	 * 修改个人信息
	 */
	@RequestMapping("/updateteacherinfo")
	public String updateTeacherInfo(TeacherDto teacherDto,Map<String, TeacherDto> map){
		System.out.println("ok");
		
		System.out.print(teacherDto.getTeacherNo());
		teacherservice.updateteacher(teacherDto);
		return "teacher/teacher_index";
	}
	/**
	 * 跳转到修改老师密码页面
	 */
	@RequestMapping("/toUpdateTeacherPassword")
	public String toupdateteacherpassword(Map<String, UserDto> map){
		UserDto u=(UserDto) map.get(MisConstant.SESSION_USER);
		u=userservice.findUser(u.getAccount());
		map.put("user", u);
		System.out.print(u.getAccount());
		return "teacher/updatePassword";
	}
	/**
	 * 修改密码功能
	 */

	@RequestMapping("/updateTeacherpassword")
	public String udapteteacherpassword(UserDto userDto, Map<String, Object>map){
		userservice.updatePassword(userDto);
		System.out.print("success");
		return "/teacher/teacher_index";
	}
	/**
	 * 老师查询自己的学生信息
	 */
	@RequestMapping("teacher_students")
	public String findStudents(Map <String,Object>map,SelectSubjectDto selectsubjectDto){
		UserDto userDto=(UserDto) map.get(MisConstant.SESSION_USER);
		TeacherDto teacherDto=teacherservice.findTeacherInfo(userDto.getAccount());
		List<SelectSubjectDto> selectsubjects= selectsubjectservice.findstudents(teacherDto);
		map.put("teacher_students",selectsubjects);
		return "teacher/student/student_list";
		
	}

}
