package com.springboot.po;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
	/**
	 * Id自增
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/**
	 * 用户账户
	 */
	@Column(name = "account")
	private String account;
	/**
	 * 用户密码
	 */
	@Column(name = "password")
	private String passWord;
	/**
	 * 用户角色 1,管理员2，老师，3，学生
	 */
	@Column(name = "role", length = 1)
	private String role;
	/**
	 * 用户与管理员是一对一关系
	 */
	@OneToOne(mappedBy = "user")
	private Manager manager;
	/**
	 * 用户与老师是一对一关系
	 */
	@OneToOne(mappedBy = "user")
	private Teacher teacher;
	/**
	 * 用户与学生是一对一关系
	 */
	@OneToOne(mappedBy = "user")
	private Student student;

	

	public User() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}



	@Override
	public String toString() {
		return "User [id=" + id + ", account=" + account + ", passWord="
				+ passWord + ", role=" + role + "]";
	}

}
