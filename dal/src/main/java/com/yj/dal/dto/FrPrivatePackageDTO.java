package com.yj.dal.dto;

import java.util.List;

import com.yj.dal.model.FrPrivatePackage;
import com.yj.dal.model.FrPrivatePackageRelation;

/**
 * Created by linzhq on 2018/11/28.
 */
public class FrPrivatePackageDTO extends FrPrivatePackage {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private List<FrPrivatePackageRelation> relationList;

	public List<FrPrivatePackageRelation> getRelationList() {
		return relationList;
	}

	public void setRelationList(List<FrPrivatePackageRelation> relationList) {
		this.relationList = relationList;
	}

    
}
