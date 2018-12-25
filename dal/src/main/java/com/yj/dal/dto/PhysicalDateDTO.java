package com.yj.dal.dto;

import java.util.Date;

public class PhysicalDateDTO {
    private Date saveDate;
    private String personalName;

    public Date getSaveDate() {
        return saveDate;
    }

    public void setSaveDate(Date saveDate) {
        this.saveDate = saveDate;
    }

    public String getPersonalName() {
        return personalName;
    }

    public void setPersonalName(String personalName) {
        this.personalName = personalName;
    }
}
