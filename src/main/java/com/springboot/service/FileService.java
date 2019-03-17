package com.springboot.service;

import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.dto.ManagerDto;
import com.springboot.dto.ManagerFileDto;
import com.springboot.dto.StudentDto;
import com.springboot.dto.StudentFileDto;
import com.springboot.pojo.WoPage;

public interface FileService {
	void uploadManagerFile(ManagerDto managerDto, MultipartFile filename);// 上传管理员文件

	WoPage<ManagerFileDto> getList(String name, Long page, Long size);// 分页查询学生信息

	void uploadStudentFile(StudentDto studentDto, MultipartFile filename);// 上传学生文件

	ManagerFileDto getById(Integer id);// 通过ID查找管理员文件

	void downloadManagerFile(ManagerFileDto managerfileDto,
			HttpServletRequest request, HttpServletResponse response);// 管理员文件下载

	WoPage<StudentFileDto> getStudentFileList(String name, Long page, Long size);

	StudentFileDto findById(Integer id);// 通过ID查找学生文件

	void downloadStudentFile(StudentFileDto studentfileDto,
			HttpServletRequest request, HttpServletResponse response);// 学生文件下载

}
