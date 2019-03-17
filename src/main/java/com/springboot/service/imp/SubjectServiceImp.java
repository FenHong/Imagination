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
import com.springboot.dto.SubjectDto;
import com.springboot.dto.TeacherDto;
import com.springboot.po.SelectSubject;
import com.springboot.po.Student;
import com.springboot.po.Subject;
import com.springboot.po.Teacher;
import com.springboot.pojo.WoPage;
import com.springboot.repository.SelectSubjectRepository;
import com.springboot.repository.SubjectRepository;
import com.springboot.service.SubjectService;

@Service
@Transactional
public class SubjectServiceImp implements SubjectService {
	@Resource
	private SubjectRepository subjectdao;
	@Resource
	private SelectSubjectRepository selectsubjectdao;

	// 老师申请课题
	public void createSubject(TeacherDto teacherDto, SubjectDto subjectDto) {
		Subject subject = subjectDto.createPO();
		Teacher teacher = teacherDto.createPO();
		subject.setSubjectAStatus("待审核");
		subject.setTeacher(teacher);
		subjectdao.save(subject);

	}

	// 分页查询老师课题信息
	public WoPage<SubjectDto> getList(String name, Long page, Long size) {
		Pageable pageInput = PageRequest.of(page.intValue() - 1,
				size.intValue(), Direction.DESC, "id");
		Page<Subject> pageData = null;
		if (StringUtils.isEmpty(name)) {
			pageData = subjectdao.findAll(pageInput);
		} else {
			pageData = subjectdao.findAllByTeacherTeacherName(name, pageInput);
		}
		// 将Student转化为StudentDto
		WoPage<SubjectDto> pr = SubjectDto.getPageData(pageData.getContent(),
				pageData.getTotalElements());
		return pr;
	}

	// 通过ID查找课题信息
	public SubjectDto getById(Integer id) {
		Subject subject = subjectdao.findById(id);
		return new SubjectDto(subject);
	}

	// 审核课题
	public void auditingSubject(SubjectDto subjectDto) {
		Subject subject = subjectdao.findById(subjectDto.getId());
		subject.setSubjectAStatus(subjectDto.getSubjectAStatus());
		subjectdao.save(subject);

	}

	// 修改课题信息
	public void updateSubject(SubjectDto subjectDto) {
		Subject subject = subjectdao.findById(subjectDto.getId());
		subject.setSubjectName(subjectDto.getSubjectName());
		subject.setSubjectInfo(subjectDto.getSubjectInfo());
		subject.setSubjectType(subjectDto.getSubjectType());
		subjectdao.save(subject);

	}

	// 删除课题
	public void delete(String id) {
		Integer id1 = Integer.parseInt(id);
		subjectdao.deleteById(id1);

	}

	// 发布课题
	public void release(String id) {
		Integer id1 = Integer.parseInt(id);
		Subject subject = subjectdao.findById(id1);
		subject.setSubjectSStatus("待选取");
		subjectdao.save(subject);

	}

	// 学生选课题
	public void selectSubject(SubjectDto subjectDto, StudentDto studentDto) {
		Student student = studentDto.createPO();
		Subject subject = subjectdao.findById(subjectDto.getId());
		SelectSubject selectSubject = new SelectSubject();
		subject.setSubjectSStatus("已选取");
		subjectdao.save(subject);
		selectSubject.setSubject(subject);
		selectSubject.setStudent(student);
		selectsubjectdao.save(selectSubject);

	}

}
