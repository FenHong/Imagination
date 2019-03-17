package com.springboot.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.springboot.po.User;

@Repository
public interface UserRepository extends
		PagingAndSortingRepository<User, String> {

	User findByAccount(String account);// 通过账户ID查找用户

	void deleteByAccount(String account);// 通过账户删除用户

}
