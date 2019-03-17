package com.springboot.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.springboot.po.Teacher;
import com.springboot.po.User;
import com.springboot.pojo.WoPage;

public class TeacherDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;// id

	private String teacherNo;// 老师工号

	private String teacherName;// 老师姓名

	private String sex;// 性别

	private Integer age;// 年龄

	private String jobTitle;// 职称

	private String tel;// 电话

	private String email;// 邮箱

	private User user;// 用户

	// get和set方法

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTeacherNo() {
		return teacherNo;
	}

	public void setTeacherNo(String teacherNo) {
		this.teacherNo = teacherNo;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
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

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
	public TeacherDto() {

	}

	public TeacherDto(Teacher teacher) {
		this.id = teacher.getId();
		this.teacherNo = teacher.getTeacherNo();
		this.teacherName = teacher.getTeacherName();
		this.sex = teacher.getSex();
		this.age = teacher.getAge();
		this.email = teacher.getEmail();
		this.jobTitle = teacher.getJobTitle();
		this.tel = teacher.getTel();
		this.user = teacher.getUser();
	}

	// 将po列表数据转化为Dto列表数据
	public static List<TeacherDto> getList(List<Teacher> teachers) {
		List<TeacherDto> teacherDto = new ArrayList<TeacherDto>();
		for (Teacher teacher : teachers) {
			TeacherDto r = new TeacherDto(teacher);
			teacherDto.add(r);
		}
		return teacherDto;
	}

	// 将po分页数据转化为Dto分页数据
	public static WoPage<TeacherDto> getPageData(List<Teacher> pos, Long total) {
		WoPage<TeacherDto> puDto = new WoPage<TeacherDto>(getList(pos), total);
		return puDto;
	}

	// 创建po对象
	public Teacher createPO() {
		Teacher teacher = new Teacher();
		teacher.setId(this.id);
		teacher.setTeacherNo(this.teacherNo);
		teacher.setTeacherName(this.teacherName);
		teacher.setSex(this.sex);
		teacher.setAge(this.age);
		teacher.setEmail(this.email);
		teacher.setJobTitle(this.jobTitle);
		teacher.setTel(this.tel);
		teacher.setUser(this.user);
		return teacher;
	}
	//判断对象是否为空
	public boolean isEmpty(TeacherDto teacherDto){
		if (teacherDto.getEmail()==""
				||teacherDto.getJobTitle()==""||teacherDto.getSex()==""||
				teacherDto.getTeacherName()==""||teacherDto.getTeacherNo()==""
				||teacherDto.getTel()==""||teacherDto.getAge()==null
				){
			return true;
		}else{
			return false;
		}
		
	}

}
