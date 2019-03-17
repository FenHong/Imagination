package com.springboot.po;

import javax.persistence.*;

@Entity
@Table(name = "studentFile")
public class StudentFile {
	/**
	 * Id自增
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/**
	 * 文件编号
	 */
	@Column(name = "student_file_no")
	private String studentFileNo;
	/**
	 * 文件名
	 */
	@Column(name = "student_file_name")
	private String studentFileName;
	/**
	 * 文件地址
	 */
	@Column(name = "file_path")
	private String filePath;
	/**
	 * 学生文件与学生是多对一关系
	 */
	@ManyToOne
	@JoinColumn(name = "student_no")
	private Student student;

	public StudentFile() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStudentFileNo() {
		return studentFileNo;
	}

	public void setStudentFileNo(String studentFileNo) {
		this.studentFileNo = studentFileNo;
	}

	public String getStudentFileName() {
		return studentFileName;
	}

	public void setStudentFileName(String studentFileName) {
		this.studentFileName = studentFileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return "StudentFile [id=" + id + ", studentFileNo=" + studentFileNo
				+ ", studentFileName=" + studentFileName + ", filePath="
				+ filePath + ", student=" + student + "]";
	}

}
