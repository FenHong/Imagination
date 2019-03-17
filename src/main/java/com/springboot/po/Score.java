package com.springboot.po;

import javax.persistence.*;

@Entity
@Table(name = "score")
public class Score {
	/**
	 * Id自增
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/**
	 * 分数
	 */
	@Column(name = "grade")
	private float grade;
	/**
	 * 评语
	 */
	@Column(name = "comment")
	private String comment;
	/**
	 * 成绩与选题是一对一关系
	 */
	@OneToOne
	@JoinColumn(name = "subject_no")
	private SelectSubject selectsubject;

	public Score() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public float getGrade() {
		return grade;
	}

	public void setGrade(float grade) {
		this.grade = grade;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public SelectSubject getSelectsubject() {
		return selectsubject;
	}

	public void setSelectsubject(SelectSubject selectsubject) {
		this.selectsubject = selectsubject;
	}

	@Override
	public String toString() {
		return "Score [id=" + id + ", grade=" + grade + ", comment=" + comment
				+ ", selectsubject=" + selectsubject + "]";
	}

}
