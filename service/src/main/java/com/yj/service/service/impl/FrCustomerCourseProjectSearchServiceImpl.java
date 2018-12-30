package com.yj.service.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yj.common.util.UUIDUtils;
import com.yj.dal.dao.*;
import com.yj.dal.model.*;
import com.yj.service.service.IFrCardOrderAllotSetService;
import com.yj.service.service.IFrCardOrderDatailService;
import com.yj.service.service.IFrCardOrderPayModeService;
import com.yj.service.service.IFrCardOrderPriceDatailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * by Kylan
 * 操作记录查询
 *
 * @author YongL
 * @date 2018/12/18
 */
@Service
public class FrCustomerCourseProjectSearchServiceImpl {

    @Autowired
    private FrCustomerCourseProjectMapper frCustomerCourseProjectMapper;

    @Autowired
    private ShopMapper shopMapper;
    @Autowired
    private SdaduimMapper sdaduimMapper;

//    @Autowired
//    private AddConsumeProjectMapper addConsumeProjectMapper;

    @Autowired
    private AddProjectConsumeMapper addProjectConsumeMapper;

    @Resource
    private IFrCardOrderPayModeService iFrCardOrderPayModeService;
    @Resource
    private FrCardAgreementMapper frCardAgreementMapper;

    @Resource
    private IFrCardOrderAllotSetService iFrCardOrderAllotSetService;
    @Resource
    private IFrCardOrderDatailService iFrCardOrderDatailService;
    @Resource
    private IFrCardOrderPriceDatailService iFrCardOrderPriceDatailService;
    @Resource
    private ProjectOrderMapper projectOrderMapper;
    @Resource
    private SysConsumeOrderMapper sysConsumeOrderMapper;
    @Resource
    private AddProjectMapper addProjectMapper;
    @Resource
    private FrClientPersonnelRelateMapper frClientPersonnelRelateMapper;
    @Resource
    private PersonnelInfoMapper personnelInfoMapper;




}

