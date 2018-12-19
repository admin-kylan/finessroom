package com.yj.service.service.impl;

import com.yj.dal.dao.FrCustomerCourseProjectMapper;
import com.yj.dal.model.PersonnelInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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


    /**
     * 根据场馆ID查询 并且 userType 是动态的，选择教练还是助教
     * @param userType
     * @param
     * @param code
     * @return
     */
    public List<PersonnelInfo> getTechnicianBySdaduimId(String userType, String businessTypeId, String code){
        return frCustomerCourseProjectMapper.getTechnicianBySdaduimId(userType, businessTypeId, code);
    }
}
