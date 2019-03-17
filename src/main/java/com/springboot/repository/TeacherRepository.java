package com.springboot.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.springboot.po.Teacher;

public interface TeacherRepository extends
		PagingAndSortingRepository<Teacher, String> {
	// findAllBy表示查询一个集合
	Page<Teacher> findAllByTeacherNameLike(String teacherName, Pageable p);

	// 通过ID查找
	Teacher findById(int id);

	// 删除
	void deleteById(int id);

	// 获取老师
	Teacher getById(int id);

	Teacher findByTeacherNo(String name);// 通过老师账号查找老师信息

}
