package com.springboot.repository;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.springboot.po.Score;

@Repository
public interface ScoreRepository extends
		PagingAndSortingRepository<Score, String> {
	// 通过ID查找
	Score findById(int id2);

	Score findByselectsubjectStudentStudentName(String studentName);// 按学生名查找学生成绩

	Score findByselectsubjectStudentStudentNo(String studentNo);// 按学号查找学生成绩

}
