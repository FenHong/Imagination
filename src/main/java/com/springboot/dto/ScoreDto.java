package com.springboot.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.springboot.po.Score;
import com.springboot.po.SelectSubject;
import com.springboot.pojo.WoPage;

public class ScoreDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;// ID

	private float grade;// 成绩分数

	private String comment;// 评语

	private SelectSubject selectsubject;// 选课

	// get和set方法

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public float getGrade() {
		return grade;
	}

	public void setGrade(float grade) {
		this.grade = grade;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public SelectSubject getSelectsubject() {
		return selectsubject;
	}

	public void setSelectsubject(SelectSubject selectsubject) {
		this.selectsubject = selectsubject;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	// 构造方法
	public ScoreDto() {

	}

	public ScoreDto(Score score) {
		this.id = score.getId();
		this.grade = score.getGrade();
		this.comment = score.getComment();
		this.selectsubject = score.getSelectsubject();
	}

	// 将po列表数据转化为Dto列表数据
	public static List<ScoreDto> getList(List<Score> scores) {
		List<ScoreDto> scoreDto = new ArrayList<ScoreDto>();
		for (Score score : scores) {
			ScoreDto r = new ScoreDto(score);
			scoreDto.add(r);
		}
		return scoreDto;
	}

	// 将po分页数据转化为Dto分页数据
	public static WoPage<ScoreDto> getPageData(List<Score> pos, Long total) {
		WoPage<ScoreDto> puDto = new WoPage<ScoreDto>(getList(pos), total);
		return puDto;
	}

	// 创建po对象
	public Score createPO() {
		Score score = new Score();
		score.setId(this.id);
		score.setGrade(this.grade);
		score.setComment(this.comment);
		score.setSelectsubject(this.selectsubject);
		return score;
	}

}
