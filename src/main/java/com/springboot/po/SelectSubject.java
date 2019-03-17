package com.springboot.po;

import javax.persistence.*;

@Entity
@Table(name = "selectSuject")
public class SelectSubject {
	/**
	 * Id自增
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/**
	 * 选题与课题是一对一关系
	 */
	@OneToOne
	@JoinColumn(name = "subject_no")
	private Subject subject;

	/**
	 * 选题与学生是一对一关系
	 */
	@OneToOne
	@JoinColumn(name = "student_no")
	private Student student;
	/**
	 * 选题与成绩是一对一关系
	 */
	@OneToOne(mappedBy = "selectsubject")
	private Score score;

	public SelectSubject() {

	}

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

	@Override
	public String toString() {
		return "SelectSubject [id=" + id + ", subject=" + subject
				+ ", student=" + student + ", score=" + score + "]";
	}

}
