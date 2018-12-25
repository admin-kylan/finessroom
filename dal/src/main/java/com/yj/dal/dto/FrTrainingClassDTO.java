package com.yj.dal.dto;

import java.util.Date;

import com.yj.dal.model.FrTrainingSeries;

public class FrTrainingClassDTO {

	
	
	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private String id;
		private Integer type;
		private Integer time;
		private String seriesName;
		private String strength;
		private Integer count;
		private String remark;
	    private String image;
		private String actionPrinceple;
	    private String name;
	    private String diff;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public Integer getType() {
			return type;
		}
		public void setType(Integer type) {
			this.type = type;
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
		public String getImage() {
			return image;
		}
		public void setImage(String image) {
			this.image = image;
		}
		public String getActionPrinceple() {
			return actionPrinceple;
		}
		public void setActionPrinceple(String actionPrinceple) {
			this.actionPrinceple = actionPrinceple;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDiff() {
			return diff;
		}
		public void setDiff(String diff) {
			this.diff = diff;
		}
		public String getSeriesName() {
			return seriesName;
		}
		public void setSeriesName(String seriesName) {
			this.seriesName = seriesName;
		}
	    
}
