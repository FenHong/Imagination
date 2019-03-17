package com.springboot.po;

import javax.persistence.*;

@Entity
@Table(name = "managerFile")
public class ManagerFile {
	/**
	 * Id自增
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/**
	 * 文件编号
	 */
	@Column(name = "manager_file_no")
	private String managerFileNo;
	/**
	 * 文件名
	 */
	@Column(name = "manager_file_name")
	private String managerFileName;
	/**
	 * 文件地址
	 */
	@Column(name = "file_path")
	private String filePath;
	/**
	 * 管理员文件与管理员是多对一关系
	 */
	@ManyToOne
	@JoinColumn(name = "manager_no")
	private Manager manager;

	public ManagerFile() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getManagerFileNo() {
		return managerFileNo;
	}

	public void setManagerFileNo(String managerFileNo) {
		this.managerFileNo = managerFileNo;
	}

	public String getManagerFileName() {
		return managerFileName;
	}

	public void setManagerFileName(String managerFileName) {
		this.managerFileName = managerFileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	@Override
	public String toString() {
		return "ManagerFile [id=" + id + ", managerFileNo=" + managerFileNo
				+ ", managerFileName=" + managerFileName + ", filePath="
				+ filePath + "]";
	}

}
