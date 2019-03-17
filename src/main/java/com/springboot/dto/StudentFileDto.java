package com.springboot.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.springboot.po.Student;
import com.springboot.po.StudentFile;
import com.springboot.pojo.WoPage;

public class StudentFileDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;// id

	private String studentFileNo;// 文件编号

	private String studentFileName;// 文件名

	private String filePath;// 文件存储路径

	private Student student;// 文件上传人

	// set和get方法

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	// 构造方法
	public StudentFileDto() {

	}

	public StudentFileDto(StudentFile studentfile) {
		this.id = studentfile.getId();
		this.studentFileNo = studentfile.getStudentFileNo();
		this.studentFileName = studentfile.getStudentFileName();
		this.student = studentfile.getStudent();
		this.filePath = studentfile.getFilePath();
	}

	// 将po列表数据转化为Dto列表数据
	public static List<StudentFileDto> getList(List<StudentFile> studentfiles) {
		List<StudentFileDto> studentfileDto = new ArrayList<StudentFileDto>();
		for (StudentFile studentfile : studentfiles) {
			StudentFileDto r = new StudentFileDto(studentfile);
			studentfileDto.add(r);
		}
		return studentfileDto;
	}

	// 将po分页数据转化为Dto分页数据
	public static WoPage<StudentFileDto> getPageData(List<StudentFile> pos,
			Long total) {
		WoPage<StudentFileDto> puDto = new WoPage<StudentFileDto>(getList(pos),
				total);
		return puDto;
	}

	// 创建po对象
	public StudentFile createPO() {
		StudentFile studentfile = new StudentFile();
		studentfile.setId(this.id);
		studentfile.setStudentFileNo(this.studentFileNo);
		studentfile.setStudentFileName(this.studentFileName);
		studentfile.setFilePath(this.filePath);
		studentfile.setStudent(this.student);
		return studentfile;
	}

}
