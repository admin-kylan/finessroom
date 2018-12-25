package com.yj.dal.dto;

import java.util.ArrayList;
import java.util.List;

import com.yj.dal.model.FrAction;
import com.yj.dal.model.FrActionSeries;

public class FrActionSeriesListDTO {

	
	private List<FrActionSeries> seriesList = new ArrayList<>();
	
	private List<FrAction> actionList = new ArrayList<>();

	public List<FrActionSeries> getSeriesList() {
		return seriesList;
	}

	public void setSeriesList(List<FrActionSeries> seriesList) {
		this.seriesList = seriesList;
	}

	public List<FrAction> getActionList() {
		return actionList;
	}

	public void setActionList(List<FrAction> actionList) {
		this.actionList = actionList;
	}
	
	
	
	
}
