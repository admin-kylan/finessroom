package com.yj.service.service.impl;

import com.yj.common.result.JsonResult;
import com.yj.dal.dao.*;
import com.yj.dal.model.FrCardAgreement;
import com.yj.dal.model.PersonnelInfo;
import com.yj.service.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * by Kylan
 *
 * @author YongL
 * @date 2018/12/18
 */
@Service
public class FrCustomerCourseProjectServiceImpl {

    @Autowired
    private FrCustomerCourseProjectMapper frCustomerCourseProjectMapper;

    @Resource
    private FrShopCardTypeRelateMapper shopCardTypeRelateMapper;
    @Resource
    private FrCardTypeMapper frCardTypeMapper; //卡类型关联表
    @Resource
    private PersonnelInfoMapper personnelInfoMapper;
    @Resource
    private IFrAgreementService iFrAgreementService;
    @Resource
    private IFrCardNumService iFrCardNumService;
    @Resource
    private IFrCardOrderInfoService iFrCardOrderInfoService;
    @Resource
    private IFrCardOrderPayModeService iFrCardOrderPayModeService;
    @Resource
    private FrCardAgreementMapper frCardAgreementMapper;
    @Resource
    private IFrCardOriginalSetService iFrCardOriginalSetService;
    @Resource
    private IFrCardOrderSplitSetService iFrCardOrderSplitSetService;
    @Resource
    private IFrCardOrderSplitSetDdService iFrCardOrderSplitSetDdService;
    @Resource
    private IFrCardOrderAllotSetService iFrCardOrderAllotSetService;
    @Resource
    private IFrCardOrderDatailService iFrCardOrderDatailService;
    @Resource
    private IFrCardOrderPriceDatailService iFrCardOrderPriceDatailService;
    @Resource
    private IFrClientService iFrClientService;
    @Resource
    private IFrLevelService iFrLevelService;
    @Resource
    private IFrClientPicService iFrClientPicService;
    @Resource
    private IFrShopCardConsumeService iFrShopCardConsumeService;
    @Resource
    private IFrCardOrderStopService iFrCardOrderStopService;

    /**
     * 根据场馆ID查询 并且 userType 是动态的，选择教练还是助教
     *
     * @param userType
     * @param
     * @param code
     * @return
     */
    public List<PersonnelInfo> getTechnicianBySdaduimId(String userType, String businessTypeId, String code) {
        return frCustomerCourseProjectMapper.getTechnicianBySdaduimId(userType, businessTypeId, code);
    }


    public void addSaveCustomer(Map map) {
        FrCardAgreement frCardAgreement = (FrCardAgreement) map.get("frCardAgreement");


    }
}
