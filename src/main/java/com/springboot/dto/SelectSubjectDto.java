package com.springboot.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.springboot.po.Score;
import com.springboot.po.SelectSubject;
import com.springboot.po.Student;
import com.springboot.po.Subject;
import com.springboot.pojo.WoPage;

public class SelectSubjectDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;// id

	private Subject subject;// 课题

	private Student student;// 选题学生

	private Score score;// 分数

	// get和set方法

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Score getScore() {
		return score;
	}

	public void setScore(Score score) {
		this.score = score;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	// 构造方法
	public SelectSubjectDto() {

	}

	public SelectSubjectDto(SelectSubject selectsubject) {
		this.id = selectsubject.getId();
		this.subject = selectsubject.getSubject();
		this.score = selectsubject.getScore();
		this.student = selectsubject.getStudent();
	}

	// 将po列表数据转化为Dto列表数据
	public static List<SelectSubjectDto> getList(
			List<SelectSubject> selectsubjects) {
		List<SelectSubjectDto> selectsubjectDto = new ArrayList<SelectSubjectDto>();
		for (SelectSubject selectsubject : selectsubjects) {
			SelectSubjectDto r = new SelectSubjectDto(selectsubject);
			selectsubjectDto.add(r);
		}
		return selectsubjectDto;
	}

	// 将po分页数据转化为Dto分页数据
	public static WoPage<SelectSubjectDto> getPageData(List<SelectSubject> pos,
			Long total) {
		WoPage<SelectSubjectDto> puDto = new WoPage<SelectSubjectDto>(
				getList(pos), total);
		return puDto;
	}

	// 创建po对象
	public SelectSubject createPO() {
		SelectSubject selectsubject = new SelectSubject();
		selectsubject.setId(this.id);
		selectsubject.setScore(this.score);
		selectsubject.setSubject(this.subject);
		selectsubject.setStudent(this.student);
		return selectsubject;
	}

}
