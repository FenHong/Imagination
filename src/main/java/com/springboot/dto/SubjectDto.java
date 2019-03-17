package com.springboot.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.springboot.po.Subject;
import com.springboot.po.Teacher;
import com.springboot.pojo.WoPage;

public class SubjectDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;// ID

	private String subjectNo;// 课题编号

	private String subjectName;// 课题名

	private String subjectInfo;// 课题信息

	private String subjectType;// 课题类型

	private String subjectAStatus;// 课题审核状态

	private String subjectSStatus;// 课题选取状态

	private Teacher teacher;// 课题老师

	// get 和set方法

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	// 构造方法
	public SubjectDto() {

	}

	public SubjectDto(Subject subject) {
		this.id = subject.getId();
		this.subjectNo = subject.getSubjectNo();
		this.subjectName = subject.getSubjectName();
		this.subjectInfo = subject.getSubjectInfo();
		this.subjectAStatus = subject.getSubjectAStatus();
		this.subjectSStatus = subject.getSubjectSStatus();
		this.subjectType = subject.getSubjectType();
		this.teacher = subject.getTeacher();
	}

	// 将po列表数据转化为Dto列表数据
	public static List<SubjectDto> getList(List<Subject> subjects) {
		List<SubjectDto> subjectDto = new ArrayList<SubjectDto>();
		for (Subject subject : subjects) {
			SubjectDto r = new SubjectDto(subject);
			subjectDto.add(r);
		}
		return subjectDto;
	}

	// 将po分页数据转化为Dto分页数据
	public static WoPage<SubjectDto> getPageData(List<Subject> pos, Long total) {
		WoPage<SubjectDto> puDto = new WoPage<SubjectDto>(getList(pos), total);
		return puDto;
	}

	// 创建pod对象
	public Subject createPO() {
		Subject subject = new Subject();
		subject.setId(this.id);
		subject.setSubjectNo(this.subjectNo);
		subject.setSubjectName(this.subjectName);
		subject.setSubjectInfo(this.subjectInfo);
		subject.setSubjectAStatus(this.subjectAStatus);
		subject.setSubjectSStatus(this.subjectSStatus);
		subject.setSubjectType(this.subjectType);
		subject.setTeacher(this.teacher);
		return subject;
	}
   //判断对象是否为空
	public boolean isEmpty(SubjectDto subjectDto){
		if(subjectDto.getSubjectNo()==""||subjectDto.getSubjectName()==""
				||subjectDto.getSubjectType()==""||subjectDto.getSubjectInfo()=="")
		{
			return true;
		}else{
			return false;
		}
		
	}
}
