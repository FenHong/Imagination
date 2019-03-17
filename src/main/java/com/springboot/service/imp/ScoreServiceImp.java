package com.springboot.service.imp;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.springboot.dto.ScoreDto;
import com.springboot.dto.SelectSubjectDto;
import com.springboot.po.Score;
import com.springboot.po.SelectSubject;
import com.springboot.pojo.WoPage;
import com.springboot.repository.ScoreRepository;
import com.springboot.service.ScoreService;

@Service
@Transactional
public class ScoreServiceImp implements ScoreService {
	@Resource
	private ScoreRepository scoredao;

	// 创建成绩信息
	public void createStudentScore(SelectSubjectDto dto, float grade,
			String comment) {
		SelectSubject selectsubject = dto.createPO();
		ScoreDto scoreDto = new ScoreDto();
		scoreDto.setSelectsubject(selectsubject);
		scoreDto.setGrade(grade);
		scoreDto.setComment(comment);
		Score score = scoreDto.createPO();
		scoredao.save(score);

	}

	// 分页查询
	public WoPage<ScoreDto> getList(Long page, Long size) {
		// 从dao获取数据
		Pageable pageInput = PageRequest.of(page.intValue() - 1,
				size.intValue(), Direction.DESC, "id");
		Page<Score> pageData = scoredao.findAll(pageInput);

		// 将Student转化为StudentDto
		WoPage<ScoreDto> pr = ScoreDto.getPageData(pageData.getContent(),
				pageData.getTotalElements());
		return pr;
	}
    // 通過ID查找
	public ScoreDto getById(int id2) {
		Score score = scoredao.findById(id2);
		return new ScoreDto(score);
	}

	// 修改成绩信息
	public void update(ScoreDto s) {
		Score score = scoredao.findById(s.getId());
		score.setComment(s.getComment());
		score.setGrade(s.getGrade());
		scoredao.save(score);

	}

	// 查询学生成绩
	public ScoreDto findstudentgrade(String studentName) {
		Score score = scoredao
				.findByselectsubjectStudentStudentName(studentName);
		return new ScoreDto(score);
	}

	// 通过学生学号查询成绩
	public ScoreDto getBySubjectNo(String studentNo) {
		Score score = scoredao.findByselectsubjectStudentStudentNo(studentNo);
		return new ScoreDto(score);
	}

}
