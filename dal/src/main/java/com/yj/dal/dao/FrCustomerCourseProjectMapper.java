package com.yj.dal.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.yj.dal.model.PersonnelInfo;
import com.yj.dal.model.ProjectOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * className FrCustomerCourseProjectMapper
 * by Kylan
 * 课程/项目/产品
 *
 * @author Kylan
 * @date 2018/12/18 下午9:23
 */
public interface FrCustomerCourseProjectMapper extends BaseMapper<ProjectOrder> {


    List<PersonnelInfo> getTechnicianBySdaduimId(@Param("userType") String userType, @Param("businessTypeId") String businessTypeId, @Param("code") String code);

    List<Map<String, Object>> getCourseList(@Param("shopId") String shopId,@Param("sdaduimId") String sdaduimId,@Param("code") String code);
    List<Map<String, Object>> getCourseList1(@Param("shopId") String shopId,@Param("sdaduimId") String sdaduimId,@Param("code") String code);

    List<Map<String, Object>> getCoursePackageCourseId(@Param("shopId") String shopId,@Param("sdaduimId") String sdaduimId,
                                                       @Param("code") String code,@Param("courseId") String courseId);
}
