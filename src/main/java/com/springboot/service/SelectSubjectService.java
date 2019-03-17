package com.springboot.service;

import java.util.List;

import com.springboot.dto.SelectSubjectDto;
import com.springboot.dto.StudentDto;
import com.springboot.dto.TeacherDto;
import com.springboot.pojo.WoPage;

public interface SelectSubjectService {
	WoPage<SelectSubjectDto> getList(String name, Long page, Long size);// 分页查询学生选课信息

	void delete(String id);// 删除选题

	WoPage<SelectSubjectDto> getList1(String name, Long page, Long size);// 分页查询老师课题选取信息

	WoPage<SelectSubjectDto> getList2(Long page, Long size);// 管理员分页查询老师课题选取信息

	List<SelectSubjectDto> findstudents(TeacherDto teacherDto);// 查询老师学生信息

	SelectSubjectDto findTeacher(StudentDto studentDto);// 查询学生老师信息

	SelectSubjectDto getById(int id);// 按ID查找选题信息

}
