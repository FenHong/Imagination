package com.springboot.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.springboot.po.Announcement;

@Repository
public interface AnnouncementRepository extends
		PagingAndSortingRepository<Announcement, String> {
	// 分页查询数据
	Page<Announcement> findAllByAnnouncementNameLike(String announcementName,
			Pageable p);
	//查询通知信息
	Announcement findById(Integer id);

}
