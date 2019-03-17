package com.springboot.controller;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.demo.MisConstant;
import com.springboot.dto.ManagerDto;
import com.springboot.dto.ManagerFileDto;
import com.springboot.dto.StudentDto;
import com.springboot.dto.StudentFileDto;
import com.springboot.dto.UserDto;
import com.springboot.pojo.WoPage;

import com.springboot.service.FileService;
import com.springboot.service.ManagerService;
import com.springboot.service.StudentService;

@Controller
@SessionAttributes(MisConstant.SESSION_USER)
public class FileController {
	@Resource
	private ManagerService managerservice;
	@Resource
	private FileService fileservice;
	@Resource
	private StudentService studentservice;

	/**
	 * 跳转到管理员文件资料主页
	 */
	@RequestMapping("managerfile")
	public String toManagerFileIndex() {
		return "manager/folder/managerfile";

	}

	/**
	 * 跳转到学生文件资料主页
	 */
	@RequestMapping("studentfile")
	public String toStudentfileIndex() {
		return "student/folder/studentfile";

	}

	/**
	 * 跳转到老师文件资料主页
	 */
	@RequestMapping("teacherfile")
	public String toTeacherfileIndex() {
		return "teacher/folder/teacherfile";

	}

	/**
	 * 跳转到管理员上传文件页面
	 */
	@RequestMapping("touploadmanagerfile")
	public String toManagerUploadFile() {
		return "manager/folder/uploadmanagerfile";
	}

	/**
	 * 上传管理员文件
	 */
	@RequestMapping(value = "/uploadManagerFile", method = RequestMethod.POST)
	public String uploadManagerFile(
			@RequestParam("managerfile") MultipartFile managerfile,
			Map<String, Object> map) {
		UserDto userDto = (UserDto) map.get(MisConstant.SESSION_USER);
		ManagerDto managerDto = managerservice.findManagerInfo(userDto
				.getAccount());
		fileservice.uploadManagerFile(managerDto, managerfile);
		map.put("message", "文件上传成功");
		return "manager/folder/managerfile_uploadsuccess";
	}

	/**
	 * 跳转到管理员文件查询页面
	 */
	@RequestMapping("tofindmanagerfile")
	public String toManagerFileQuery() {
		return "manager/folder/file_query";
	}

	/**
	 * 查询管理员文件
	 */
	@RequestMapping("managerfile_query")
	public String list(String name,
			@RequestParam(defaultValue = "1") Long page, Map<String, Object> map) {
		WoPage<ManagerFileDto> managerfiles = fileservice.getList(name, page,
				WoPage.SIZE);
		managerfiles.setPage(page);
		String returnvalue = null;
		UserDto userDto = (UserDto) map.get(MisConstant.SESSION_USER);
		if (userDto.getRole().equals("1")) {
			returnvalue = "manager/folder/file_list";
		} else if (userDto.getRole().equals("3")) {
			returnvalue = "student/folder/file_list";
		} else {
			returnvalue = "teacher/folder/managerfile_list";
		}
		map.put("managerfiles", managerfiles);
		return returnvalue;
	}

	/**
	 * 跳转到学生上传文件页面
	 */
	@RequestMapping("touploadstudentfile")
	public String toStudentUploadFile() {
		return "student/folder/uploadstudentfile";
	}

	/**
	 * 上传学生文件
	 */
	@RequestMapping(value = "/uploadStudentFile", method = RequestMethod.POST)
	public String uploadStudentFile(
			@RequestParam("studentfile") MultipartFile studentfile,
			Map<String, Object> map) {
		UserDto userDto = (UserDto) map.get(MisConstant.SESSION_USER);
		StudentDto studentDto = studentservice.findStudentInfo(userDto
				.getAccount());
		fileservice.uploadStudentFile(studentDto, studentfile);
		map.put("message", "文件上传成功");
		return "student/folder/studentfile_uploadsuccess";
	}

	/**
	 * 跳转到学生查询管理员文件页面
	 */
	@RequestMapping("S_tofindmanagerfile")
	public String toSManagerFileQuery() {
		return "student/folder/file_query";
	}

	/**
	 * 下载管理员文件
	 * 
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("toManagerFileDownload")
	public void ManagerFileDownload(String id, Map<String, Object> map,
			HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		int id2 = Integer.parseInt(id);
		ManagerFileDto managerfileDto = fileservice.getById(id2);
		fileservice.downloadManagerFile(managerfileDto, request, response);

	}

	/**
	 * 跳转到老师查询管理员文件页面
	 */
	@RequestMapping("totfindmanagerfile")
	public String toTManagerFileQuery() {
		return "teacher/folder/managerfile_query";
	}

	/**
	 * 跳转到老师查询学生文件页面
	 */
	@RequestMapping("totfindstudentfile")
	public String toTStudentFileQuery() {
		return "teacher/folder/studentfile_query";
	}

	/**
	 * 老师查询学生文件
	 * 
	 * @param name
	 * @param page
	 * @param map
	 * @return
	 */
	@RequestMapping("studentfile_query")
	public String listStudentFile(String name,
			@RequestParam(defaultValue = "1") Long page, Map<String, Object> map) {
		WoPage<StudentFileDto> studentfiles = fileservice.getStudentFileList(
				name, page, WoPage.SIZE);
		studentfiles.setPage(page);

		map.put("studentfiles", studentfiles);
		return "teacher/folder/studentfile_list";
	}

	/**
	 * 老师下载学生文件
	 */
	@RequestMapping("toStudentFileDownload")
	public void StudentFileDownload(String id, Map<String, Object> map,
			HttpServletRequest request, HttpServletResponse response) {
		int id2 = Integer.parseInt(id);
		StudentFileDto studentfileDto = fileservice.findById(id2);
		fileservice.downloadStudentFile(studentfileDto, request, response);
	}

}
