package com.springboot.po;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "student")
public class Student {
	/**
	 * Id自增
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/**
	 * 学生学号
	 */
	@Column(name = "student_no")
	private String studentNo;
	/**
	 * 学生姓名
	 */
	@Column(name = "student_name")
	private String studentName;
	/**
	 * 学生性别
	 */
	@Column(name = "sex")
	private String sex;
	/**
	 * 年龄
	 */
	@Column(name = "age")
	private Integer age;
	/**
	 * 学院
	 */
	@Column(name = "college")
	private String college;
	/**
	 * 专业
	 */
	@Column(name = "major")
	private String major;
	/**
	 * 班级
	 */
	@Column(name = "class")
	private String clazz;
	/**
	 * 学生与用户是一对一关系
	 */
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	/**
	 * 学生与文件是一对多关系
	 */
	@OneToMany(mappedBy = "student")
	private List<StudentFile> studentfiles;
	/**
	 * 学生与选题是一对一关系
	 */
	@OneToOne(mappedBy = "student")
	private SelectSubject selectsubject;


	public Student() {

	}

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

	public List<StudentFile> getStudentfiles() {
		return studentfiles;
	}

	public void setStudentfiles(List<StudentFile> studentfiles) {
		this.studentfiles = studentfiles;
	}

	public SelectSubject getSelectsubject() {
		return selectsubject;
	}

	public void setSelectsubject(SelectSubject selectsubject) {
		this.selectsubject = selectsubject;
	}
	

	@Override
	public String toString() {
		return "Student [id=" + id + ", studentNo=" + studentNo
				+ ", studentName=" + studentName + ", sex=" + sex + ", age="
				+ age + ", college=" + college + ", major=" + major
				+ ", clazz=" + clazz + ", user=" + user + ", studentfiles="
				+ studentfiles + ", selectsubject=" + selectsubject + "]";
	}

}
