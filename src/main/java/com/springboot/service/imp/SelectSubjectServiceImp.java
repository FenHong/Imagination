package com.springboot.service.imp;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.springboot.dto.SelectSubjectDto;
import com.springboot.dto.StudentDto;
import com.springboot.dto.TeacherDto;
import com.springboot.po.SelectSubject;
import com.springboot.po.Subject;
import com.springboot.pojo.WoPage;
import com.springboot.repository.SelectSubjectRepository;
import com.springboot.repository.SubjectRepository;
import com.springboot.service.SelectSubjectService;

@Service
@Transactional
public class SelectSubjectServiceImp implements SelectSubjectService {
	@Resource
	private SelectSubjectRepository selectsubjectdao;
	@Resource
	private SubjectRepository subjectdao;
    //分頁查詢
	public WoPage<SelectSubjectDto> getList(String name, Long page, Long size) {
		Pageable pageInput = PageRequest.of(page.intValue() - 1,
				size.intValue(), Direction.DESC, "id");
		Page<SelectSubject> pageData = null;
		if (StringUtils.isEmpty(name)) {
			pageData = null;
		} else {
			pageData = selectsubjectdao.findAllByStudentStudentName(name,
					pageInput);
		}
		// 将Student转化为StudentDto
		WoPage<SelectSubjectDto> pr = SelectSubjectDto.getPageData(
				pageData.getContent(), pageData.getTotalElements());
		return pr;

	}

	// 删除课题
	public void delete(String id) {
		Integer id1 = Integer.parseInt(id);
		SelectSubject selectsubject = selectsubjectdao.findById(id1);
		Subject subject = subjectdao.findById(selectsubject.getSubject()
				.getId());
		subject.setSubjectSStatus("待选取");// 修改课题选取状态
		subjectdao.save(subject);
		selectsubjectdao.deleteById(id1);

	}

	// 查询老师课题选取信息
	public WoPage<SelectSubjectDto> getList1(String name, Long page, Long size) {
		Pageable pageInput = PageRequest.of(page.intValue() - 1,
				size.intValue(), Direction.DESC, "id");
		Page<SelectSubject> pageData = null;
		if (StringUtils.isEmpty(name)) {
			pageData = null;
		} else {
			pageData = selectsubjectdao.findAllBySubjectTeacherTeacherName(
					name, pageInput);
		}
		// 将Student转化为StudentDto
		WoPage<SelectSubjectDto> pr = SelectSubjectDto.getPageData(
				pageData.getContent(), pageData.getTotalElements());
		return pr;
	}

	// 管理员查询学生选题情况
	public WoPage<SelectSubjectDto> getList2(Long page, Long size) {
		Pageable pageInput = PageRequest.of(page.intValue() - 1,
				size.intValue(), Direction.DESC, "id");
		Page<SelectSubject> pageData = selectsubjectdao.findAll(pageInput);

		// 将Student转化为StudentDto
		WoPage<SelectSubjectDto> pr = SelectSubjectDto.getPageData(
				pageData.getContent(), pageData.getTotalElements());
		return pr;
	}

	// 查询老师学生信息
	public List<SelectSubjectDto> findstudents(TeacherDto teacherDto) {
		List<SelectSubject> selectsubjects = selectsubjectdao
				.findBySubjectTeacherTeacherName(teacherDto.getTeacherName());
		List<SelectSubjectDto> selectsubjectDtos = new ArrayList<SelectSubjectDto>();
		for (SelectSubject selectsubject : selectsubjects) {
			SelectSubjectDto selectsubjectDto = new SelectSubjectDto(
					selectsubject);
			selectsubjectDtos.add(selectsubjectDto);

		}
		return selectsubjectDtos;
	}

	// 查询学生老师信息
	public SelectSubjectDto findTeacher(StudentDto studentDto) {
		SelectSubject selectsubject = selectsubjectdao
				.findByStudentStudentName(studentDto.getStudentName());

		return new SelectSubjectDto(selectsubject);
	}
    // 通過ID獲取
	public SelectSubjectDto getById(int id) {
		SelectSubject selectsubject = selectsubjectdao.findById(id);
		return new SelectSubjectDto(selectsubject);
	}

}
