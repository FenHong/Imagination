package com.springboot.service.imp;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.demo.FileUtil;
import com.springboot.dto.ManagerDto;
import com.springboot.dto.ManagerFileDto;
import com.springboot.dto.StudentDto;
import com.springboot.dto.StudentFileDto;
import com.springboot.po.Manager;
import com.springboot.po.ManagerFile;
import com.springboot.po.Student;
import com.springboot.po.StudentFile;
import com.springboot.pojo.WoPage;
import com.springboot.repository.ManagerFileRepository;
import com.springboot.repository.StudentFileRepository;
import com.springboot.service.FileService;

@Service
@Transactional
public class FileServiceImp implements FileService {
	@Resource
	private ManagerFileRepository managerfiledao;
	@Resource
	private StudentFileRepository studentfiledao;

	// 上传管理员文件
	public void uploadManagerFile(ManagerDto managerDto, MultipartFile filename) {
		if (!filename.isEmpty()) {
			// 获取文件名称,包含后缀
			String fileName = filename.getOriginalFilename();

			// 存放在这个路径下：该路径是该工程目录下的static文件下：
			String path = "D:/Java/developer_software/J_workspace/GraduationDesignMS/src/main/resources/static/managerfiles/";

			try {
				// 该方法是对文件写入的封装
				FileUtil.fileupload(filename.getBytes(), path, fileName);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Manager manager = managerDto.createPO();
			ManagerFile managerfile = new ManagerFile();
			managerfile.setManagerFileNo("1");
			managerfile.setManagerFileName(fileName);
			managerfile.setFilePath("http://localhost:8080/managerfiles/"
					+ fileName);
			managerfile.setManager(manager);
			managerfiledao.save(managerfile);

		}
	}

	// 分页查询管理员文件
	public WoPage<ManagerFileDto> getList(String name, Long page, Long size) {
		// 从dao获取数据
		Pageable pageInput = PageRequest.of(page.intValue() - 1,
				size.intValue(), Direction.DESC, "id");
		Page<ManagerFile> pageData = null;
		if (StringUtils.isEmpty(name)) {
			pageData = managerfiledao.findAll(pageInput);
		} else {
			pageData = managerfiledao.findAllByManagerFileNameLike("%" + name
					+ "%", pageInput);
		}
		// 将Student转化为StudentDto
		WoPage<ManagerFileDto> pr = ManagerFileDto.getPageData(
				pageData.getContent(), pageData.getTotalElements());
		return pr;
	}

	// 上传学生文件
	public void uploadStudentFile(StudentDto studentDto, MultipartFile filename) {
		if (!filename.isEmpty()) {
			// 获取文件名称,包含后缀
			String fileName = filename.getOriginalFilename();

			// 存放在这个路径下：该路径是该工程目录下的static文件下：
			String path = "D:/Java/developer_software/J_workspace/GraduationDesignMS/src/main/resources/static/studentfiles/";

			try {
				// 该方法是对文件写入的封装
				FileUtil.fileupload(filename.getBytes(), path, fileName);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Student student = studentDto.createPO();
			StudentFile studentfile = new StudentFile();
			studentfile.setStudentFileNo("2");
			studentfile.setStudentFileName(fileName);
			studentfile.setFilePath("http://localhost:8080/studentfiles/"
					+ fileName);
			studentfile.setStudent(student);
			studentfiledao.save(studentfile);
		}
	}

	// 通过ID查找管理员文件
	public ManagerFileDto getById(Integer id) {
		ManagerFile managerfile = managerfiledao.findById(id);
		return new ManagerFileDto(managerfile);
	}

	// 管理员文件下载
	public void downloadManagerFile(ManagerFileDto managerfileDto,
			HttpServletRequest request, HttpServletResponse response) {
		String filename = managerfileDto.getManagerFileName();
		String realpath = "D:/Java/developer_software/J_workspace/GraduationDesignMS/src/main/resources/static/managerfiles/";
		if (managerfileDto.getManagerFileName() != null) {
			File file = new File(realpath, filename);

			if (file.exists()) {
				System.out.print(managerfileDto.getFilePath());
				try {
					filename = URLEncoder.encode(filename, "UTF-8");
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				response.setContentType("application/force-download");// 设置强制下载不打开
				response.addHeader("Content-Disposition",
						"attachment;fileName=" + filename);// 设置文件名
				byte[] buffer = new byte[1024];
				FileInputStream fis = null;
				BufferedInputStream bis = null;
				try {
					fis = new FileInputStream(file);
					bis = new BufferedInputStream(fis);
					OutputStream os = response.getOutputStream();
					int i = bis.read(buffer);
					while (i != -1) {
						os.write(buffer, 0, i);
						i = bis.read(buffer);
					}
					System.out.println("success");
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (bis != null) {
						try {
							bis.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					if (fis != null) {
						try {
							fis.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}

	}

	// 分页查询学生文件
	public WoPage<StudentFileDto> getStudentFileList(String name, Long page,
			Long size) {
		// 从dao获取数据
		Pageable pageInput = PageRequest.of(page.intValue() - 1,
				size.intValue(), Direction.DESC, "id");
		Page<StudentFile> pageData = null;
		if (StringUtils.isEmpty(name)) {
			pageData = studentfiledao.findAll(pageInput);
		} else {
			pageData = studentfiledao.findAllByStudentFileNameLike("%" + name
					+ "%", pageInput);
		}
		// 将Student转化为StudentDto
		WoPage<StudentFileDto> pr = StudentFileDto.getPageData(
				pageData.getContent(), pageData.getTotalElements());
		return pr;
	}

	// 通过ID查找学生文件
	public StudentFileDto findById(Integer id) {
		StudentFile studentfile = studentfiledao.findById(id);
		return new StudentFileDto(studentfile);
	}

	// 学生文件下载
	public void downloadStudentFile(StudentFileDto studentfileDto,
			HttpServletRequest request, HttpServletResponse response) {
		String filename = studentfileDto.getStudentFileName();
		String realpath = "D:/Java/developer_software/J_workspace/GraduationDesignMS/src/main/resources/static/studentfiles/";
		if (studentfileDto.getStudentFileName() != null) {
			File file = new File(realpath, filename);

			if (file.exists()) {
				System.out.print(studentfileDto.getFilePath());
				try {
					filename = URLEncoder.encode(filename, "UTF-8");
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				response.setContentType("application/force-download");// 设置强制下载不打开
				response.addHeader("Content-Disposition",
						"attachment;fileName=" + filename);// 设置文件名
				byte[] buffer = new byte[1024];
				FileInputStream fis = null;
				BufferedInputStream bis = null;
				try {
					fis = new FileInputStream(file);
					bis = new BufferedInputStream(fis);
					OutputStream os = response.getOutputStream();
					int i = bis.read(buffer);
					while (i != -1) {
						os.write(buffer, 0, i);
						i = bis.read(buffer);
					}
					System.out.println("success");
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (bis != null) {
						try {
							bis.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					if (fis != null) {
						try {
							fis.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}

	}

}
