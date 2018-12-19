package com.yj.dal.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.yj.dal.model.PersonnelInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * className FrCustomerCourseProjectMapper
 * by Kylan
 * 课程/项目/产品
 *
 * @author Kylan
 * @date 2018/12/18 下午9:23
 */
public interface FrCustomerCourseProjectMapper extends BaseMapper<PersonnelInfo> {


    List<PersonnelInfo> getTechnicianBySdaduimId(@Param("userType") String userType, @Param("businessTypeId") String businessTypeId, @Param("code") String code);
}
