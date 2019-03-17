package com.springboot.service;

import com.springboot.dto.ScoreDto;
import com.springboot.dto.SelectSubjectDto;
import com.springboot.pojo.WoPage;

public interface ScoreService {
	void createStudentScore(SelectSubjectDto dto, float grade, String comment);// 创建课题分数

	WoPage<ScoreDto> getList(Long page, Long size);// 分页查询成绩信息

	ScoreDto getById(int id2);// 通过ID查找成绩

	void update(ScoreDto s);// 修改成绩信息

	ScoreDto findstudentgrade(String studentName);// 查询学生成绩

	ScoreDto getBySubjectNo(String studentNo);// 通过学生学号询成绩

}
