package com.springboot.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.springboot.po.Student;

@Repository
public interface StudentRepository extends
		PagingAndSortingRepository<Student, String> {
	// findAllBy表示查询一个集合
	Page<Student> findAllByStudentNameLike(String studentName, Pageable p);

	// 通過ID查找
	Student findById(int id);

	// 删除
	void deleteById(int id);

	// 通过ID获取一个学生
	Student getById(int id);

	Student findByStudentNo(String name);// 通过学生账号查找学生信息

}
