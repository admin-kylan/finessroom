package com.yj.dal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PackageCanUsingItemDTO {
    private String courseId;
    private String courseName;
    private String shopName;
    private String sdaduimName;
    private String sdaduimId;
    private String shopId;
    private Integer classCount;
    private Integer limitCount;
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getSdaduimName() {
		return sdaduimName;
	}
	public void setSdaduimName(String sdaduimName) {
		this.sdaduimName = sdaduimName;
	}
	public String getSdaduimId() {
		return sdaduimId;
	}
	public void setSdaduimId(String sdaduimId) {
		this.sdaduimId = sdaduimId;
	}
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public Integer getClassCount() {
		return classCount;
	}
	public void setClassCount(Integer classCount) {
		this.classCount = classCount;
	}
	public Integer getLimitCount() {
		return limitCount;
	}
	public void setLimitCount(Integer limitCount) {
		this.limitCount = limitCount;
	}
    
    
    
}
