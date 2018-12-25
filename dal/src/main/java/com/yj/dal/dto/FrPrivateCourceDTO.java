package com.yj.dal.dto;

import com.yj.dal.model.FrPrivateCource;

/**
 * Created by linzhq on 2018/11/28.
 */
public class FrPrivateCourceDTO extends FrPrivateCource {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String trainingSeriesIds;

    public String getTrainingSeriesIds() {
        return trainingSeriesIds;
    }

    public void setTrainingSeriesIds(String trainingSeriesIds) {
        this.trainingSeriesIds = trainingSeriesIds;
    }

    private String courseName;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
