package com.springboot.po;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "manager")
public class Manager {
	/**
	 * Id自增
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/**
	 * 管理员工号
	 */
	@Column(name = "manager_no")
	private String managerNo;
	/**
	 * 管理员姓名
	 */
	@Column(name = "manager_name")
	private String managerName;
	/**
	 * 管理员性别
	 */
	@Column(name = "sex")
	private String sex;
	/**
	 * 年龄
	 */
	@Column(name = "age")
	private Integer age;
	/**
	 * 管理员与通知是一对多关系
	 */
	@OneToMany(mappedBy = "manager")
	private List<Announcement> announcements;
	/**
	 * 管理员与用户是一对一关系
	 */
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	/**
	 * 管理员与管理员文件是一对多关系
	 */
	@OneToMany(mappedBy = "manager")
	private List<ManagerFile> managerfiles;

	public Manager() {

	}

	// set和get方法
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getManagerNo() {
		return managerNo;
	}

	public void setManagerNo(String managerNo) {
		this.managerNo = managerNo;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
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

	public List<Announcement> getAnnouncements() {
		return announcements;
	}

	public void setAnnouncements(List<Announcement> announcements) {
		this.announcements = announcements;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<ManagerFile> getManagerfiles() {
		return managerfiles;
	}

	public void setManagerfiles(List<ManagerFile> managerfiles) {
		this.managerfiles = managerfiles;
	}

	@Override
	public String toString() {
		return "Manager [id=" + id + ", managerNo=" + managerNo
				+ ", managerName=" + managerName + ", sex=" + sex + ", age="
				+ age + "]";
	}

}
