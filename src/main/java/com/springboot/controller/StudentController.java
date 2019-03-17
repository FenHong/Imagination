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
import com.springboot.dto.UserDto;
import com.springboot.pojo.WoPage;
import com.springboot.pojo.WoResultCode;
import com.springboot.service.SelectSubjectService;
import com.springboot.service.StudentService;
import com.springboot.service.UserService;

@Controller
@SessionAttributes(MisConstant.SESSION_USER)
public class StudentController {
	@Resource
	private StudentService studentservice;
	@Resource
	private UserService userservice;
	@Resource 
	private SelectSubjectService selectsubjectservice;
	
//管理员功能模块
	/**
	 * 跳转到管理员学生主页
	 */
	@RequestMapping("manager_studentinfo")
	public String toManagerStudentIndex(){
		return "manager/student/manager_studentinfo";
	
	}
	/**
	 * 跳转到学生创建页面
	 */
	@RequestMapping("toaddstudent")
	public String toCreateStudent(){
		return "manager/student/student_create";
	}
	/**
	 * 创建学生
	 * @param studentDto
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/createstudent",method=RequestMethod.POST)
	public WoResultCode createStudent(StudentDto studentDto){
		
		String message=null;
		if(studentDto.isEmpty(studentDto)){
			message="请输入完整的用户信息";
		}
		else{
			message="创建学生[" + studentDto.getStudentName() + "]成功！";
		studentservice.createStudent(studentDto);
		}
		return WoResultCode.getSuccessCode().setMsg(message);
	}
	/**
	 * 跳转到学生查询页面
	 */
	@RequestMapping("toquerystudent")
	public String toQueryStudent(){
		return "manager/student/student_query";
	}
	/**
	 * 查询学生
	 */
	@RequestMapping("student_query")
	public String list (String name, @RequestParam(defaultValue="1") Long page, Map<String, Object>map) {
		WoPage<StudentDto> students = studentservice.getList(name, page, WoPage.SIZE);
		students.setPage(page);
		map.put("students", students);
		return "manager/student/student_list";
	}
	/**
	 * 加载修改页面
	 */
	@RequestMapping(value="/tostudentupdate", method=RequestMethod.GET)
	public String loadUpdateForm (String id, Map<String, Object>map) {
		int id2=Integer.parseInt(id);
		StudentDto s = studentservice.getById (id2);
		map.put("student", s);
		return "manager/student/student_update";
	}
	/**
	 * 修改学生
	 * @param s
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/studentupdate", method=RequestMethod.POST)
	public WoResultCode update (StudentDto s, Map<String, Object>map) {
		System.out.println(s.getStudentName());
		studentservice.update (s);
		return WoResultCode.getSuccessCode().setMsg("修改学生[" + s.getStudentName() + "]成功！");
	}
	/**
	 * 删除学生
	 * @param id
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/studentdelete", method=RequestMethod.POST)
	public WoResultCode delete (String[] id, Map<String, Object> map) {

		studentservice.delete(id);
		System.out.print("ok");
		return WoResultCode.getSuccessCode().setMsg("删除学生成功！");
	}
	
	//学生功能模块
	/**
	 * 留在学生主页
	 */
	@RequestMapping("student")
	public String toStudentIndex(){
		return "student/student_index";
	}
	/**
	 * 跳转到学生信息主页
	 */
	@RequestMapping("studentinfo")
	public String findStudentInfo(Map<String, Object> map){
		UserDto u=(UserDto) map.get(MisConstant.SESSION_USER);
		System.out.print(u.getAccount());
		StudentDto studentDto=studentservice.findStudentInfo(u.getAccount());
		map.put("student", studentDto);
		
		return "student/studentinfo";
	}
	/**
	 * 跳转到修改学生密码页面
	 */
	@RequestMapping("/toUpdateStudentPassword")
	public String toupdatestudentpassword(Map<String, UserDto> map){
		UserDto u=(UserDto) map.get(MisConstant.SESSION_USER);
		u=userservice.findUser(u.getAccount());
		map.put("user", u);
		System.out.print(u.getAccount());
		return "student/updatePassword";
	}
	/**
	 * 修改密码功能
	 */
	
	@RequestMapping("/updatestudentpassword")
	public String udaptestudentpassword(UserDto userDto, Map<String, Object>map){
		userservice.updatePassword(userDto);
		System.out.print("success");
		return "/student/student_index";
	}
	/**
	 * 学生查询老师信息
	 */
    @RequestMapping("student_teacher")
	public String findTeacher(Map<String ,Object>map){
    	UserDto userDto=(UserDto) map.get(MisConstant.SESSION_USER);
		StudentDto studentDto=studentservice.findStudentInfo(userDto.getAccount());
		SelectSubjectDto selectsubjectDto=selectsubjectservice.findTeacher(studentDto);
		map.put("student_teacher", selectsubjectDto);
		return "student/teacher/teacher_info";
	}
}
