package com.inxedu.os.edu.entity.website;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 推荐课程DTO
 * 
 * @author www.inxedu.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WebsiteCourseDetailDTO extends WebsiteCourseDetail implements Serializable {
	private static final long serialVersionUID = 7475674095165175841L;
	private String recommendName;// 推荐名称
	private String courseName;// 课程名称
	private int isavaliable;// 课程是否下架

	public String getRecommendName() {
		return recommendName;
	}

	public void setRecommendName(String recommendName) {
		this.recommendName = recommendName;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getIsavaliable() {
		return isavaliable;
	}

	public void setIsavaliable(int isavaliable) {
		this.isavaliable = isavaliable;
	}

}
