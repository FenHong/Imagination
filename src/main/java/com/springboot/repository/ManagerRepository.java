package com.springboot.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.springboot.po.Manager;

@Repository
public interface ManagerRepository extends
		PagingAndSortingRepository<Manager, String> {

	Manager findByManagerNo(String name);// 通过管理员账号查找管理员信息

}
