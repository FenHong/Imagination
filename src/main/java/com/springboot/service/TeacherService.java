package com.springboot.service;

import com.springboot.dto.TeacherDto;
import com.springboot.pojo.WoPage;

public interface TeacherService {
	void createTeacher(TeacherDto dto);// 创建老师

	WoPage<TeacherDto> getList(String name, Long page, Long size);// 分页查询老师信息

	void delete(String[] ids);// 删除laoshi

	void update(TeacherDto dto);// 更新老师

	TeacherDto getById(Integer id);// 通过id查找老师

	TeacherDto findTeacherInfo(String name);// 通过老师账号查找用户

	void updateteacher(TeacherDto dto);// 更新老师自己信息

}
