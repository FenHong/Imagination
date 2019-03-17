package com.springboot.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.springboot.po.Subject;

@Repository
public interface SubjectRepository extends
		PagingAndSortingRepository<Subject, String> {
	// findAllBy表示查询一个集合
	Page<Subject> findAllByTeacherTeacherName(String teacherName, Pageable p);

	// 通过ID查找
	Subject findById(Integer id);

	// 删除
	void deleteById(Integer id);

}
