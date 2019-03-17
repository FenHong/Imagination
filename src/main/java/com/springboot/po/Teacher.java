package com.springboot.po;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "teacher")
public class Teacher {
	/**
	 * Id自增
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/**
	 * 老师工号
	 */
	@Column(name = "teacher_no")
	private String teacherNo;
	/**
	 * 老师姓名
	 */
	@Column(name = "teacher_name")
	private String teacherName;
	/**
	 * 老师性别
	 */
	@Column(name = "sex")
	private String sex;
	/**
	 * 年龄
	 */
	@Column(name = "age")
	private Integer age;
	/**
	 * 职称
	 */
	@Column(name = "job_title")
	private String jobTitle;
	/**
	 * 联系电话
	 */
	@Column(name = "telephone")
	private String tel;
	/**
	 * 邮箱
	 */
	@Column(name = "email")
	private String email;
	/**
	 * 老师与用户是一对一关系
	 */
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	/**
	 * 老师与课题是一对多关系
	 */
	@OneToMany(mappedBy = "teacher")
	private List<Subject> subjects;

	public Teacher() {

	}

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

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}


	@Override
	public String toString() {
		return "Teacher [id=" + id + ", teacherNo=" + teacherNo
				+ ", teacherName=" + teacherName + ", sex=" + sex + ", age="
				+ age + ", jobTitle=" + jobTitle + ", tel=" + tel + ", email="
				+ email + ", user=" + user + ", subjects=" + subjects + "]";
	}

}
