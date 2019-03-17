package com.springboot.service;

import com.springboot.dto.StudentDto;
import com.springboot.dto.SubjectDto;
import com.springboot.dto.TeacherDto;
import com.springboot.pojo.WoPage;

public interface SubjectService {

	void createSubject(TeacherDto teacherDto, SubjectDto subjectDto);// 老师申请课题

	WoPage<SubjectDto> getList(String name, Long page, Long size);// 分页查询老师课题信息

	SubjectDto getById(Integer id);// 通过ID查找课题

	void auditingSubject(SubjectDto subjectDto);// 审核课题

	void updateSubject(SubjectDto subjectDto);// 修改课题

	void delete(String id);// 删除课题

	void release(String id);// 发布课题

	void selectSubject(SubjectDto subjectDto, StudentDto studentDto);// 学生选题

}
