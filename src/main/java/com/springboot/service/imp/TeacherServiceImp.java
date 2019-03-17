package com.springboot.service.imp;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import com.springboot.dto.TeacherDto;
import com.springboot.po.Teacher;
import com.springboot.po.User;
import com.springboot.pojo.WoPage;
import com.springboot.repository.TeacherRepository;
import com.springboot.repository.UserRepository;
import com.springboot.service.TeacherService;

@Service
@Transactional
public class TeacherServiceImp implements TeacherService {

	@Resource
	private TeacherRepository teacherdao;

	@Resource
	private UserRepository userdao;

	// 創建老師
	public void createTeacher(TeacherDto teacherdto) {
		Teacher teacher = teacherdto.createPO();
		User user = new User();
		user.setId(teacher.getId());
		user.setAccount(teacher.getTeacherNo());
		user.setPassWord(teacher.getTeacherNo());
		user.setRole("2");
		teacher.setUser(userdao.save(user));
		teacherdao.save(teacher);

	}

	// 分頁查詢
	public WoPage<TeacherDto> getList(String name, Long page, Long size) {
		// 从dao获取数据
		Pageable pageInput = PageRequest.of(page.intValue() - 1,
				size.intValue(), Direction.DESC, "id");
		Page<Teacher> pageData = null;
		if (StringUtils.isEmpty(name)) {
			pageData = teacherdao.findAll(pageInput);
		} else {
			pageData = teacherdao.findAllByTeacherNameLike("%" + name + "%",
					pageInput);
		}
		// 将Student转化为StudentDto
		WoPage<TeacherDto> pr = TeacherDto.getPageData(pageData.getContent(),
				pageData.getTotalElements());
		return pr;
	}

	// 刪除
	public void delete(String[] ids) {

		Integer id1;
		for (String id : ids) {
			id1 = Integer.parseInt(id);
			Teacher s = teacherdao.findById(id1);
			User user = userdao.findByAccount(s.getTeacherNo());
			System.out.print(user.getAccount());
			teacherdao.deleteById(id1);
			userdao.deleteByAccount(user.getAccount());
		}
	}

	// 更新
	public void update(TeacherDto dto) {
		// 从数据库查询PO
		Teacher s = teacherdao.findById(dto.getId());
		// 设置简单属性
		s.setTeacherName(dto.getTeacherName());
		s.setJobTitle(dto.getJobTitle());
		s.setAge(dto.getAge());
		s.setSex(dto.getSex());
		teacherdao.save(s);
	}

	// 通過ID查找
	public TeacherDto getById(Integer id) {
		Teacher s = teacherdao.findById(id);
		return new TeacherDto(s);
	}

	// 通过账号查找老师
	public TeacherDto findTeacherInfo(String name) {
		Teacher teacher = teacherdao.findByTeacherNo(name);
		TeacherDto teacherDto = new TeacherDto(teacher);
		return teacherDto;
	}

	// 老师更新自己信息
	public void updateteacher(TeacherDto dto) {
		// 从数据库查询PO
		Teacher s = teacherdao.findById(dto.getId());
		// 设置简单属性
		s.setTel(dto.getTel());
		s.setAge(dto.getAge());
		s.setEmail(dto.getEmail());
		teacherdao.save(s);

	}

}
