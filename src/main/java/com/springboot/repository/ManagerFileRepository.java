package com.springboot.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.springboot.po.ManagerFile;

@Repository
public interface ManagerFileRepository extends
		PagingAndSortingRepository<ManagerFile, String> {
	// 分页查询
	Page<ManagerFile> findAllByManagerFileNameLike(String name, Pageable p);

	// 通过ID查找管理员文件
	ManagerFile findById(Integer id);

}
