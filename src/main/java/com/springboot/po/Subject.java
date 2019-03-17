package com.springboot.po;

import javax.persistence.*;

@Entity
@Table(name = "subject")
public class Subject {
	/**
	 * Id自增
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * 课题编号
	 */
	@Column(name = "subject_no")
	private String subjectNo;
	/**
	 * 课题名称
	 */
	@Column(name = "subject_name")
	private String subjectName;
	/**
	 * 课题描述
	 */
	@Column(name = "subject_info")
	private String subjectInfo;
	/*
	 * 课题类型
	 */
	@Column(name = "subject_type")
	private String subjectType;
	/**
	 * 课题审核状态
	 */
	@Column(name = "subject_astatus")
	private String subjectAStatus;
	/**
	 * 课题选取状态
	 */
	@Column(name = "subject_sstatus")
	private String subjectSStatus;
	/**
	 * 课题与老师是多对一关系
	 */
	@ManyToOne
	@JoinColumn(name = "teacher_no")
	private Teacher teacher;
	/**
	 * 课题与选题是一对一关系
	 */
	@OneToOne(mappedBy = "subject")
	private SelectSubject selectsubject;

	public Subject() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSubjectNo() {
		return subjectNo;
	}

	public void setSubjectNo(String subjectNo) {
		this.subjectNo = subjectNo;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getSubjectInfo() {
		return subjectInfo;
	}

	public void setSubjectInfo(String subjectInfo) {
		this.subjectInfo = subjectInfo;
	}

	public String getSubjectType() {
		return subjectType;
	}

	public void setSubjectType(String subjectType) {
		this.subjectType = subjectType;
	}

	public String getSubjectAStatus() {
		return subjectAStatus;
	}

	public void setSubjectAStatus(String subjectAStatus) {
		this.subjectAStatus = subjectAStatus;
	}

	public String getSubjectSStatus() {
		return subjectSStatus;
	}

	public void setSubjectSStatus(String subjectSStatus) {
		this.subjectSStatus = subjectSStatus;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public SelectSubject getSelectsubject() {
		return selectsubject;
	}

	public void setSelectsubject(SelectSubject selectsubject) {
		this.selectsubject = selectsubject;
	}

	@Override
	public String toString() {
		return "Subject [id=" + id + ", subjectNo=" + subjectNo
				+ ", subjectName=" + subjectName + ", subjectInfo="
				+ subjectInfo + ", subjectType=" + subjectType
				+ ", subjectAStatus=" + subjectAStatus + ", subjectSStatus="
				+ subjectSStatus + "]";
	}

}
