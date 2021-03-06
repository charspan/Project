package com.inxedu.os.edu.entity.website;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @description 推荐课程
 * @author www.inxedu.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WebsiteCourseDetail implements Serializable {
	private static final long serialVersionUID = 7475674095165175841L;

	private int id;// 主键
	private int recommendId;// 分类id
	private int courseId;// 课程id
	private int orderNum;// 排序值

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRecommendId() {
		return recommendId;
	}

	public void setRecommendId(int recommendId) {
		this.recommendId = recommendId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

}
