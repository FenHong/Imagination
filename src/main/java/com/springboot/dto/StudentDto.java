package com.springboot.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.springboot.po.Student;
import com.springboot.po.User;
import com.springboot.pojo.WoPage;

public class StudentDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;// id

	private String studentNo;// 学号

	private String studentName;// 姓名

	private String sex;// 性别

	private Integer age;// 年龄

	private String college;// 学院

	private String major;// 专业

	private String clazz;// 班级

	private User user;// 用户

	// get和set方法

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
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

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
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
	public StudentDto() {

	}

	public StudentDto(Student student) {
		this.id = student.getId();
		this.studentNo = student.getStudentNo();
		this.studentName = student.getStudentName();
		this.age = student.getAge();
		this.sex = student.getSex();
		this.college = student.getCollege();
		this.major = student.getMajor();
		this.clazz = student.getClazz();
		this.user = student.getUser();
	}

	// 将po列表数据转化为Dto列表数据
	public static List<StudentDto> getList(List<Student> students) {
		List<StudentDto> studentDto = new ArrayList<StudentDto>();
		for (Student student : students) {
			StudentDto r = new StudentDto(student);
			studentDto.add(r);
		}
		return studentDto;
	}

	// 将po分页数据转化为Dto分页数据
	public static WoPage<StudentDto> getPageData(List<Student> pos, Long total) {
		WoPage<StudentDto> puDto = new WoPage<StudentDto>(getList(pos), total);
		return puDto;
	}

	// 创建po对象
	public Student createPO() {
		Student student = new Student();
		student.setId(this.id);
		student.setStudentNo(this.studentNo);
		student.setStudentName(this.studentName);
		student.setAge(this.age);
		student.setClazz(this.clazz);
		student.setCollege(this.college);
		student.setMajor(this.major);
		student.setSex(this.sex);
		student.setUser(this.user);
		return student;
	}
	//判断对象是否为空
	public boolean isEmpty(StudentDto studentDto){
		if(studentDto.getStudentNo()==""||studentDto.getStudentName()==""||
				studentDto.getAge()==null||studentDto.getClazz()==""||studentDto.getCollege()==""||studentDto.getSex()==""
				||studentDto.getMajor()==""){
			return true;
			}else{
		return false;
			}
		
	}

}
