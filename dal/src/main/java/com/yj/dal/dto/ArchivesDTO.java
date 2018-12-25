package com.yj.dal.dto;

import com.yj.dal.model.FrClientArchives;
import com.yj.dal.model.FrClientArchivesRelate;
import com.yj.dal.model.FrClientArchivesRelatePic;

import java.util.List;

public class ArchivesDTO {
    private List<FrClientArchivesRelate> frClientArchivesRelates;
    private List<FrClientArchivesRelatePic> frClientArchivesRelatePics;

    public List<FrClientArchivesRelate> getFrClientArchivesRelates() {
        return frClientArchivesRelates;
    }

    public void setFrClientArchivesRelates(List<FrClientArchivesRelate> frClientArchivesRelates) {
        this.frClientArchivesRelates = frClientArchivesRelates;
    }

    public List<FrClientArchivesRelatePic> getFrClientArchivesRelatePics() {
        return frClientArchivesRelatePics;
    }

    public void setFrClientArchivesRelatePics(List<FrClientArchivesRelatePic> frClientArchivesRelatePics) {
        this.frClientArchivesRelatePics = frClientArchivesRelatePics;
    }
}
