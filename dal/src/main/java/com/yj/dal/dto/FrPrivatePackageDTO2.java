package com.yj.dal.dto;

import com.yj.dal.model.FrPrivatePackage;
import com.yj.dal.model.FrPrivatePackageRelation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by linzhq on 2018/11/28.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FrPrivatePackageDTO2 extends FrPrivatePackage {

	private List<PackageCanUsingItemDTO> canUsingItem ;
}
