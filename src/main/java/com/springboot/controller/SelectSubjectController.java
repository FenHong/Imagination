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
import com.springboot.dto.SelectSubjectDto;
import com.springboot.dto.StudentDto;
import com.springboot.dto.TeacherDto;
import com.springboot.dto.UserDto;
import com.springboot.pojo.WoPage;
import com.springboot.pojo.WoResultCode;
import com.springboot.service.SelectSubjectService;
import com.springboot.service.StudentService;
import com.springboot.service.TeacherService;

@Controller
@SessionAttributes(MisConstant.SESSION_USER)
public class SelectSubjectController {
	@Resource
	private StudentService studentservice;
	@Resource
	private SelectSubjectService selectsubjectservice;
	@Resource
	private TeacherService teacherservice;
	/**
	 * 跳转到学生查询自己选题情况页面
	 */
	@RequestMapping("student_subject")
	public String toQueryStudentSelectSubject(){
		return"student/subject/selectsubject_query";
	}
	/**
	 * 显示学生查询选课结果
	 */
	@RequestMapping("studentselectsubject_query")
	public String list (String name, @RequestParam(defaultValue="1") Long page, Map<String, Object>map) {
		UserDto userDto=(UserDto) map.get(MisConstant.SESSION_USER);
		StudentDto studentDto=studentservice.findStudentInfo(userDto.getAccount());
		System.out.print(studentDto.getStudentName());
		WoPage<SelectSubjectDto> studentselectsubjects = selectsubjectservice.getList(studentDto.getStudentName(), page, WoPage.SIZE);
		studentselectsubjects.setPage(page);
		map.put("studentselectsubjects", studentselectsubjects);
		return "student/subject/selectsubject_list";

	}
	/**
	 * 退选课题
	 */
	@ResponseBody
	@RequestMapping(value="/studentselectsubjectdelete", method=RequestMethod.POST)
	public WoResultCode deleteSelectsubject(String id, Map<String, Object> map) {
		selectsubjectservice.delete(id);
		System.out.print("ok");
		return WoResultCode.getSuccessCode().setMsg("退选课题成功！");
	}
	/**
	 * 跳转到老师查询自己课题选取情况页面
	 */
	@RequestMapping("subject_selectinfo")
	public String toQueryTeacherSelectSubject(){
		return "teacher/subject/selectsubject_query";
	}
	/**
	 * 显示老师课题选取情况
	 */
	@RequestMapping("teacherselectsubject_query")
	public String list1 (String name, @RequestParam(defaultValue="1") Long page, Map<String, Object>map) {
		UserDto userDto=(UserDto) map.get(MisConstant.SESSION_USER);
		TeacherDto teacherDto=teacherservice.findTeacherInfo(userDto.getAccount());
		System.out.print(teacherDto.getTeacherName());
		WoPage<SelectSubjectDto> teacherselectsubjects = selectsubjectservice.getList1(teacherDto.getTeacherName(), page, WoPage.SIZE);
		teacherselectsubjects.setPage(page);
		map.put("teacherselectsubjects", teacherselectsubjects);
		return "teacher/subject/selectsubject_list";

	}
	/**
	 * 跳转到管理员查询选题情况页面
	 */
	@RequestMapping("subjectselectinfo")
	public String toQueryManagerSelectSubject(){
		return "manager/subject/selectsubject_query";
	}
	/**
	 * 显示管理员查询选题情况
	 */
	@RequestMapping("managerselectsubject_query")
	public String list1 ( @RequestParam(defaultValue="1") Long page, Map<String, Object>map) {
		
		
		WoPage<SelectSubjectDto> managerselectsubjects = selectsubjectservice.getList2( page, WoPage.SIZE);
		managerselectsubjects.setPage(page);
		map.put("managerselectsubjects", managerselectsubjects);
		return "manager/subject/selectsubject_list";

	}

}
