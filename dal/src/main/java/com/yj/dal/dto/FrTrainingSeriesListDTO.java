package com.yj.dal.dto;

import java.util.ArrayList;
import java.util.List;

import com.yj.dal.model.FrTrainingSeries;

public class FrTrainingSeriesListDTO {

	private List<FrTrainingSeries> seriesList = new ArrayList<>();
	
	private List<FrTrainingSeries> subSeriesList = new ArrayList<>();
	
	private List<FrTrainingClassDTO> classDtoList  = new ArrayList<>();

	public List<FrTrainingSeries> getSeriesList() {
		return seriesList;
	}

	public void setSeriesList(List<FrTrainingSeries> seriesList) {
		this.seriesList = seriesList;
	}

	public List<FrTrainingClassDTO> getClassDtoList() {
		return classDtoList;
	}

	public void setClassDtoList(List<FrTrainingClassDTO> classDtoList) {
		this.classDtoList = classDtoList;
	}

	public List<FrTrainingSeries> getSubSeriesList() {
		return subSeriesList;
	}

	public void setSubSeriesList(List<FrTrainingSeries> subSeriesList) {
		this.subSeriesList = subSeriesList;
	}
	

	
}
