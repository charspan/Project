package com.inxedu.os.edu.entity.teacher;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 讲师实体
 * 
 * @author www.inxedu.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Teacher implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 838162101564081713L;
	private int id;// 主键自增
	private String name;// 讲师名称
	private String education;// 讲师资历
	private String career;// 讲师简介
	private int isStar;// 头衔 1高级讲师2首席讲师
	private String picPath;// 头像
	private int status;// 状态:0正常1删除
	private java.util.Date createTime;// 创建时间
	private java.util.Date updateTime;// 更新时间
	private int subjectId;// 专业ID
	private int sort;// 排序

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getCareer() {
		return career;
	}

	public void setCareer(String career) {
		this.career = career;
	}

	public int getIsStar() {
		return isStar;
	}

	public void setIsStar(int isStar) {
		this.isStar = isStar;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public java.util.Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

}
