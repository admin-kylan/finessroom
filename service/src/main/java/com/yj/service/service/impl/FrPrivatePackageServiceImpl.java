package com.yj.service.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.util.PageUtils;
import com.yj.common.util.Query;
import com.yj.dal.dao.SdaduimMapper;
import com.yj.dal.dao.ShopMapper;
import com.yj.dal.dto.FrPrivatePackageDTO2;
import com.yj.dal.dto.PackageCanUsingItemDTO;
import com.yj.dal.model.FrPrivateCource;
import com.yj.dal.model.FrPrivatePackage;
import com.yj.dal.dao.FrPrivatePackageMapper;
import com.yj.dal.model.FrPrivatePackageRelation;
import com.yj.dal.model.Sdaduim;
import com.yj.service.service.IFrPrivateCourceService;
import com.yj.service.service.IFrPrivatePackageRelationService;
import com.yj.service.service.IFrPrivatePackageService;
import com.yj.service.base.BaseServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-18
 */
@Service
public class FrPrivatePackageServiceImpl extends BaseServiceImpl<FrPrivatePackageMapper, FrPrivatePackage> implements IFrPrivatePackageService {

    @Autowired
    IFrPrivatePackageRelationService iFrPrivatePackageRelationService;
    @Autowired
    IFrPrivateCourceService iFrPrivateCourceService;
    @Resource
    ShopMapper shopMapper;
    @Resource
    SdaduimMapper sdaduimMapper;

    public PageUtils queryPage(Map<String, Object> params) throws YJException{

        String code = String.valueOf(params.get("code"));
        if (StringUtils.isEmpty(code)) {
            throw new YJException(YJExceptionEnum.CUSTOMERCODE_NOT_FOUND);
        }
        String queryProperty = " valid_value AS validValue,member_price AS memberPrice, count, class_count_desc AS classCountDesc, is_show_desk AS isShowDesk, id, update_time AS updateTime, valid_type AS validType, customer_code AS customerCode, market_price AS marketPrice, is_using AS isUsing, create_user AS createUser, name, remain_count_notice AS remainCountNotice, update_user AS updateUser, promtion_price AS promtionPrice, is_account_spending AS isAccountSpending, create_time AS createTime, class_count AS classCount";
        Page page = this.selectPage(
                new Query<FrPrivatePackage>(params).getPage(),
                new EntityWrapper<FrPrivatePackage>()
                        .where("is_using = 1 ")
                        .orderBy("create_time desc")
                        .setSqlSelect(queryProperty)
        );

        return new PageUtils(page);
    }

    @Override
    public Object findProjectInfo(Map<String, Object> params){
        FrPrivatePackage info = this.selectById(String.valueOf(params.get("id")));
        Map<String,Object> Fparams = new HashMap<>();
        String queryProperty = " id,shop_id as shopId,sdaduim_id as sdaduimId,package_id as packageId,cource_id as courceId,cource_count as courceCount,day_limit_count as dayLimitCount";
        List<FrPrivatePackageRelation> canUsingItemRelation = iFrPrivatePackageRelationService.selectList(new EntityWrapper<FrPrivatePackageRelation>().setSqlSelect(queryProperty).where("package_id={0}" , info.getId()));
        List<PackageCanUsingItemDTO> canUsingItem = new ArrayList<>();

        if(canUsingItemRelation != null && canUsingItemRelation.size() > 0 ){
            canUsingItemRelation.stream().forEach((relation)->{
                PackageCanUsingItemDTO item = new PackageCanUsingItemDTO();
                item.setClassCount(relation.getCourceCount());
                item.setCourseId(relation.getCourceId());
                item.setCourseName(iFrPrivateCourceService.selectById(relation.getCourceId()).getName());
                item.setLimitCount(relation.getDayLimitCount());
                item.setSdaduimId(relation.getSdaduimId());
                Sdaduim sda = sdaduimMapper.selectById(relation.getSdaduimId());
                item.setShopName(shopMapper.selectById(sda.getShopId()).getShopName());
                item.setSdaduimName(sda.getName());
                canUsingItem.add(item);
            });
        }

        FrPrivatePackageDTO2 dto2 = new FrPrivatePackageDTO2();
        BeanUtils.copyProperties(info,dto2);
        dto2.setCanUsingItem(canUsingItem);

        return dto2;
    }
}
