package com.springboot.service;

import com.springboot.dto.StudentDto;
import com.springboot.pojo.WoPage;

public interface StudentService {
	void createStudent(StudentDto dto);// 创建学生

	WoPage<StudentDto> getList(String name, Long page, Long size);// 分页查询学生信息

	void delete(String[] ids);// 删除学生

	void update(StudentDto dto);// 更新学生

	StudentDto getById(Integer id);// 通过id查找学生

	StudentDto findStudentInfo(String name);// 通过学生账号查找用户

}
