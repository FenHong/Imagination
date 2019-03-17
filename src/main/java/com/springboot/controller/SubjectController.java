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
import com.springboot.dto.StudentDto;
import com.springboot.dto.SubjectDto;
import com.springboot.dto.TeacherDto;
import com.springboot.dto.UserDto;
import com.springboot.po.SelectSubject;
import com.springboot.pojo.WoPage;
import com.springboot.pojo.WoResultCode;
import com.springboot.repository.SelectSubjectRepository;
import com.springboot.service.StudentService;
import com.springboot.service.SubjectService;
import com.springboot.service.TeacherService;

@Controller
@SessionAttributes(MisConstant.SESSION_USER)
public class SubjectController {
	@Resource
	private TeacherService teacherservice;
	@Resource
	private SubjectService subjectservice;
	@Resource
	private StudentService studentservice;
	@Resource 
	private SelectSubjectRepository selectsubjectdao;
	/**
	 * 跳转到管理员课题主页
	 */
	@RequestMapping("managersubjectinfo")
	public String toManagerSubjectIndex(){
		return "manager/subject/managersubject";
	}
	/**
	 * 跳转到学生课题主页
	 */
	@RequestMapping("studentsubject")
	public String toStudentSubjectIndex(){
		return "student/subject/studentsubject";
	}
	/**
	 * 跳转到老师课题主页
	 */
	@RequestMapping("teachersubject")
	public String toTeacherSubjectIndex(){
		return "teacher/subject/teachersubject";
	}
	/**
	 * 跳转到老师申请课题页面
	 */
	@RequestMapping("subject_apply")
	public String toTeacherSubjectApply(){
		return "teacher/subject/subject_apply";
	}
	/**
	 * 跳转到管理员查询老师课题页面
	 */
	@RequestMapping("subject_auditing")
	public String toQueryManagerSubject(){
		return "manager/subject/subject_query";
	}
	/**
	 * 管理员查询老师课题
	 */
	@RequestMapping("managersubject_query")
	public String getList(String name, @RequestParam(defaultValue="1") Long page, Map<String, Object>map){
		WoPage<SubjectDto> managersubjects = subjectservice.getList(name, page, WoPage.SIZE);
		managersubjects.setPage(page);
		map.put("managersubjects", managersubjects);
		return "manager/subject/subject_list";
	}
	/**
	 * 跳转到管理员审核课题页面
	 */
	@RequestMapping("tomanagersubjectupdate")
	public String toAuditingSubject(String id, Map<String, Object>map){
		int id2=Integer.parseInt(id);
		SubjectDto s = subjectservice.getById (id2);
		map.put("managersubject", s);
		return "manager/subject/auditingsubject";
		
	}
	/**
	 * 审核课题
	 * @param s
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/auditingsubject", method=RequestMethod.POST)
	public WoResultCode auditingSubject (SubjectDto s, Map<String, Object>map) {
		subjectservice.auditingSubject (s);
		return WoResultCode.getSuccessCode().setMsg("提交成功！");
	}
	
	/**
	 * 课题申请
	 */
	@ResponseBody
	@RequestMapping(value="createteachersubject",method=RequestMethod.POST)
	public WoResultCode TeacherSubjectApply(SubjectDto subjectDto,Map<String,Object> map){
		UserDto userDto=(UserDto) map.get(MisConstant.SESSION_USER);
		TeacherDto teacherDto=teacherservice.findTeacherInfo(userDto.getAccount());
		String message=null;
		if(subjectDto.isEmpty(subjectDto)){
			message="请输入完整的课题信息";
		}else{
			message="课题提交成功，待审核！";
			subjectservice.createSubject(teacherDto,subjectDto);
		}
		
		return WoResultCode.getSuccessCode().setMsg(message);
	}
	/**
	 * 跳转到老师查询自己课题页面
	 */
	@RequestMapping("teachersubjects")
	public String toQueryTeacherSubject(){
		return "teacher/subject/subject_query";
	}
	/**
	 * 老师查询自己课题
	 */
	@RequestMapping("teachersubject_query")
	public String list (String name, @RequestParam(defaultValue="1") Long page, Map<String, Object>map) {
		UserDto userDto=(UserDto) map.get(MisConstant.SESSION_USER);
		TeacherDto teacherDto=teacherservice.findTeacherInfo(userDto.getAccount());
		System.out.print(teacherDto.getTeacherName());
		WoPage<SubjectDto> teachersubjects = subjectservice.getList(teacherDto.getTeacherName(), page, WoPage.SIZE);
		teachersubjects.setPage(page);
		map.put("teachersubjects", teachersubjects);
		return "teacher/subject/subject_list";

	}
	/**
	 * 跳转到老师修改自己课题信息页面
	 */
	@RequestMapping("toteachersubjectupdate")
	public String toUpdateTeacherSubject(String id, Map<String, Object>map){
		int id2=Integer.parseInt(id);
		SubjectDto s = subjectservice.getById (id2);
		map.put("teachersubject", s);
		return "teacher/subject/subject_update";
	}
	/**
	 * 老师修改自己课题信息
	 */
	@ResponseBody
	@RequestMapping(value="/teachersubjectupdate", method=RequestMethod.POST)
	public WoResultCode updateSubject (SubjectDto s, Map<String, Object>map) {
		subjectservice.updateSubject (s);
		return WoResultCode.getSuccessCode().setMsg("修改课题成功！");
	}
	/**
	 * 删除老师课题
	 */
	@ResponseBody
	@RequestMapping(value="/teachersubjectdelete", method=RequestMethod.POST)
	public WoResultCode deletesubject(String id, Map<String, Object> map) {
		subjectservice.delete(id);
		System.out.print("ok");
		return WoResultCode.getSuccessCode().setMsg("删除课题成功！");
	}
	/**
	 * 发布课题
	 * @param id
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/teachersubjectrelease", method=RequestMethod.POST)
	public WoResultCode releasesubject(String id, Map<String, Object> map) {
		subjectservice.release(id);
		System.out.print("ok");
		return WoResultCode.getSuccessCode().setMsg("发布课题成功！");
	}
	/**
	 * 跳转到学生查询老师课题页面
	 */
	@RequestMapping("allsubjects")
	public String toQueryStudentSubject(){
		return "student/subject/subject_query";
	}
	/**
	 * 学生查询老师课题
	 */
	@RequestMapping("studentsubject_query")
	public String getList1(String name, @RequestParam(defaultValue="1") Long page, Map<String, Object>map){
		WoPage<SubjectDto> studentsubjects = subjectservice.getList(name, page, WoPage.SIZE);
		studentsubjects.setPage(page);
		map.put("studentsubjects", studentsubjects);
		return "student/subject/subject_list";
	}
	/**
	 * 跳转到学生查看老师课题详细信息页
	 */
	@RequestMapping("tostudentsubjectselect")
	public String toSelectSubject(String id, Map<String, Object>map){
		int id2=Integer.parseInt(id);
		SubjectDto s = subjectservice.getById (id2);
		map.put("studentsubject", s);
		return "student/subject/selectsubject";
		
	}
	/**
	 * 选取课题
	 */
	@ResponseBody
	@RequestMapping(value="/select_subject", method=RequestMethod.POST)
	public WoResultCode selectSubject (SubjectDto s, Map<String, Object>map) {
		UserDto userDto=(UserDto) map.get(MisConstant.SESSION_USER);
		StudentDto studentDto=studentservice.findStudentInfo(userDto.getAccount());
		SelectSubject selectsubject=selectsubjectdao.findByStudentStudentName(studentDto.getStudentName());
		String message=null;
		if(selectsubject==null){
		subjectservice.selectSubject (s,studentDto);
		message="选取成功";
		}else{
			message="只能选取一门课题";
		}
		return WoResultCode.getSuccessCode().setMsg(message);
	}
	
	
}
