package com.yj.dal.dto;

public class FrTraningClassListDTO {

	private String id;
	private String image;
	
	private String name;
	
	private Integer time ;
	
	private String strength;
	
	private Integer count;
	private String remark;
	private String traning_time;
	private String seriesname;



	public String getTraning_time() {
		return traning_time;
	}

	public void setTraning_time(String traning_time) {
		this.traning_time = traning_time;
	}

	public String getSeriesname() {
		return seriesname;
	}

	public void setSeriesname(String seriesname) {
		this.seriesname = seriesname;
	}



	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public String getStrength() {
		return strength;
	}

	public void setStrength(String strength) {
		this.strength = strength;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
