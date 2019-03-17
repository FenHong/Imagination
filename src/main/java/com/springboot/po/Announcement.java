package com.springboot.po;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "announcement")
public class Announcement {
	/**
	 * Id自增
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/**
	 * 通知编号
	 */
	@Column(name = "announcement_no")
	private String announcementNo;
	/**
	 * 公告名
	 */
	@Column(name = "announcement_name")
	private String announcementName;
	/**
	 * 通知内容
	 */
	@Column(name = "information")
	private String information;
	/**
	 * 通知时间
	 */
	@Column(name = "date")
	private Date date;
	/**
	 * 通知与管理员是多对一关系
	 */
	@ManyToOne
	@JoinColumn(name = "manager_no")
	private Manager manager;

	public Announcement() {

	}

	// set和get方法
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAnnouncementNo() {
		return announcementNo;
	}

	public void setAnnouncementNo(String announcementNo) {
		this.announcementNo = announcementNo;
	}

	public String getAnnouncementName() {
		return announcementName;
	}

	public void setAnnouncementName(String announcementName) {
		this.announcementName = announcementName;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	// tostring方法
	@Override
	public String toString() {
		return "Announcement [id=" + id + ", announcementNo=" + announcementNo
				+ ", announcementName=" + announcementName + ", information="
				+ information + ", date=" + date + ", manager=" + manager + "]";
	}

}
