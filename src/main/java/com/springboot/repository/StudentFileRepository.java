package com.springboot.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.springboot.po.StudentFile;

public interface StudentFileRepository extends
		PagingAndSortingRepository<StudentFile, String> {
	// 分页查询
	Page<StudentFile> findAllByStudentFileNameLike(String name, Pageable p);

	// 通过ID查找
	StudentFile findById(Integer id);
}
