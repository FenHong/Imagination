package com.springboot.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.springboot.po.SelectSubject;


@Repository
public interface SelectSubjectRepository extends
		PagingAndSortingRepository<SelectSubject, String> {
	// 分页查询
	Page<SelectSubject> findAllByStudentStudentName(String name,
			Pageable pageInput);

	// 删除选题
	void deleteById(Integer id);

	// 通过ID查找选题
	SelectSubject findById(Integer id);

	// 通过老师名分页查询
	Page<SelectSubject> findAllBySubjectTeacherTeacherName(String name,
			Pageable pageInput);

	// 通过学生名查询
	SelectSubject findByStudentStudentName(String name);

	// 查询一个集合
	List<SelectSubject> findBySubjectTeacherTeacherName(String teacherName);

}
