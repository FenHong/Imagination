package com.springboot.service.imp;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.springboot.dto.StudentDto;
import com.springboot.po.Student;
import com.springboot.po.User;
import com.springboot.pojo.WoPage;
import com.springboot.repository.StudentRepository;
import com.springboot.repository.UserRepository;
import com.springboot.service.StudentService;

@Service
@Transactional
public class StudentServiceImp implements StudentService {
	@Resource
	private StudentRepository studentdao;
	@Resource
	private UserRepository userdao;

	// 創建學生
	public void createStudent(StudentDto studentdto) {
		Student student = studentdto.createPO();
		User user = new User();
		user.setId(student.getId());
		user.setAccount(student.getStudentNo());
		user.setPassWord(student.getStudentNo());
		user.setRole("3");
		student.setUser(userdao.save(user));
		studentdao.save(student);

	}

	// 分頁獲取數據
	public WoPage<StudentDto> getList(String name, Long page, Long size) {
		// 从dao获取数据
		Pageable pageInput = PageRequest.of(page.intValue() - 1,
				size.intValue(), Direction.DESC, "id");
		Page<Student> pageData = null;
		if (StringUtils.isEmpty(name)) {
			pageData = studentdao.findAll(pageInput);
		} else {
			pageData = studentdao.findAllByStudentNameLike("%" + name + "%",
					pageInput);
		}
		// 将Student转化为StudentDto
		WoPage<StudentDto> pr = StudentDto.getPageData(pageData.getContent(),
				pageData.getTotalElements());
		return pr;
	}

	// 删除学生
	public void delete(String[] ids) {

		Integer id1;
		for (String id : ids) {
			id1 = Integer.parseInt(id);
			Student s = studentdao.findById(id1);
			User user = userdao.findByAccount(s.getStudentNo());
			System.out.print(user.getAccount());
			studentdao.deleteById(id1);
			userdao.deleteByAccount(user.getAccount());
		}
	}
    //修改學生
	public void update(StudentDto dto) {
		// 从数据库查询PO
		Student s = studentdao.findById(dto.getId());
		// 设置简单属性
		s.setStudentName(dto.getStudentName());
		s.setCollege(dto.getCollege());
		s.setMajor(dto.getMajor());
		s.setClazz(dto.getClazz());
		studentdao.save(s);
	}

	// 通過ID查找
	public StudentDto getById(Integer id) {
		Student s = studentdao.findById(id);
		return new StudentDto(s);
	}

	// 通过账号查找学生
	public StudentDto findStudentInfo(String name) {
		Student student = studentdao.findByStudentNo(name);
		StudentDto studentDto = new StudentDto(student);
		return studentDto;
	}

}
