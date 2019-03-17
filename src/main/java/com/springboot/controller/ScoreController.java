package com.springboot.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.springboot.demo.MisConstant;
import com.springboot.dto.ScoreDto;
import com.springboot.dto.SelectSubjectDto;
import com.springboot.dto.StudentDto;
import com.springboot.dto.UserDto;
import com.springboot.pojo.WoPage;
import com.springboot.pojo.WoResultCode;
import com.springboot.service.ScoreService;
import com.springboot.service.SelectSubjectService;
import com.springboot.service.StudentService;

@Controller
@SessionAttributes(MisConstant.SESSION_USER)
public class ScoreController {
	@Resource
	private SelectSubjectService selectsubjectservice;
	@Resource
	private ScoreService scoreservice;
	@Resource
	private StudentService studentservice;
	/**
	 * 跳转到老师评分页面
	 */
	@RequestMapping("tostudentscorecreate")
	public String toScoreMark(String id, Map<String, Object>map){
		int id2=Integer.parseInt(id);
		SelectSubjectDto selectsubjectDto=selectsubjectservice.getById(id2);
		System.out.print(selectsubjectDto.getSubject());
		map.put("selectsubject", selectsubjectDto);
		return "teacher/subject/score_mark";
	}
	/**
	 * 老师评分
	 */
	@ResponseBody
	@RequestMapping(value="/createstudentscore",method=RequestMethod.POST)
	public WoResultCode createStudentscore(Integer id,float grade,String comment){
		SelectSubjectDto dto=selectsubjectservice.getById(id);
		ScoreDto scoreDto=scoreservice.getBySubjectNo(dto.getStudent().getStudentNo());
		String message=null;
		if (scoreDto.getSelectsubject().getStudent().getStudentName().isEmpty()){
			scoreservice.createStudentScore(dto,grade,comment);
			message="提交成功";
		}else{
			message="成绩已经存在，如需修改，请到修改页面修改成绩！";
		}
		
		return WoResultCode.getSuccessCode().setMsg(message);
	}
	/**
	 * 跳转到老师查询学生成绩页面
	 * @return
	 */
	@RequestMapping("subject_mark")
	public String toQueryStudentScore(){
		return"teacher/subject/score_query";
	}
	/**
	 * 查询成绩列表信息
	 */
	@RequestMapping("score_query")
	public String list (@RequestParam(defaultValue="1") Long page, Map<String, Object>map) {
		WoPage<ScoreDto> scores = scoreservice.getList( page, WoPage.SIZE);
		scores.setPage(page);
		map.put("scores", scores);
		return "teacher/subject/score_list";
	}
	/**
	 * 跳转到修改成绩页面
	 */
	@RequestMapping(value="/toscoreupdate", method=RequestMethod.GET)
	public String toUpdateScore(String id, Map<String, Object>map){
		int id2=Integer.parseInt(id);
		ScoreDto scoreDto=scoreservice.getById(id2);
		map.put("score", scoreDto);
		return "teacher/subject/score_update";
	}
	/**
	 * 修改成绩
	 */
	@ResponseBody
	@RequestMapping(value="/scoreupdate", method=RequestMethod.POST)
	public WoResultCode update (ScoreDto s, Map<String, Object>map) {
		scoreservice.update (s);
		return WoResultCode.getSuccessCode().setMsg("修改学生成功！");
	}
	/**
	 * 学生查询自己成绩
	 */
	@RequestMapping("studentgrade")
	public String studentQueryGrade(Map<String,Object>map){
		UserDto u=(UserDto) map.get(MisConstant.SESSION_USER);
		System.out.print(u.getAccount());
		StudentDto studentDto=studentservice.findStudentInfo(u.getAccount());
		ScoreDto scoreDto=scoreservice.findstudentgrade(studentDto.getStudentName());
		System.out.print(scoreDto.getComment());
		map.put("grade", scoreDto);
		return "student/subject/scoreinfo";
		
	}

}
