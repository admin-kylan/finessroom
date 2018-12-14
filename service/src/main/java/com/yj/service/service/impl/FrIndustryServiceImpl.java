package com.yj.service.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yj.common.util.CookieUtils;
import com.yj.dal.dao.FrIndustryMapper;
import com.yj.dal.dao.RoleInfoMapper;
import com.yj.dal.model.FrIndustry;
import com.yj.service.service.IFrIndustryService;
import com.yj.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 行业表 服务实现类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-04
 */
@Service
public class FrIndustryServiceImpl extends BaseServiceImpl<FrIndustryMapper, FrIndustry> implements IFrIndustryService {

    @Autowired
    RoleInfoMapper roleInfoMapper;

    @Autowired
    IFrIndustryService frIndustryService;

    @Override
    public List<FrIndustry> selectProtectionDaysList(String customerCode) {
        return baseMapper.selectProtectionDaysList(customerCode);
    }

    @Override
    public List<FrIndustry> selectClaimSetList(String customerCode) {
        return baseMapper.selectClaimSetList(customerCode);
    }

    @Override
    public List<FrIndustry> selectFollowList(Integer customerType, String customerCode) {
        return baseMapper.selectFollowList(customerType,customerCode);
    }

    @Override
    @Transactional
    public Boolean updateIndustrySetList(List<FrIndustry> frIndustries) {
        int successCount = 0;
        FrIndustry frIndustry = new FrIndustry();
        for (int i = 0; i < frIndustries.size() ; i++) {
            frIndustry = frIndustries.get(i);
            successCount = successCount + baseMapper.updateByEntity(frIndustry);
        }
        if(successCount>0){
            return true;
        }
        return false;
    }

    @Override
    public FrIndustry getClientCount(String clientType) {
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //获取当前登录用户的id
        String pid = CookieUtils.getCookieValue(req, "id", true);
        //查询当前操作人角色
        String fistName = roleInfoMapper.getFistName(pid);
        //查询该角色默认保护天数
        FrIndustry frIndustry = frIndustryService.selectOne(
                new EntityWrapper<FrIndustry>().where("role={0}", fistName).and("is_using=1")
                        .and("customer_type={0}",clientType)
        );
        return frIndustry;
    }


}
