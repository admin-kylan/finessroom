package com.yj.dal.dao;

import com.yj.dal.model.FrEducationCardObject;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 课程结算，会员卡设置表 Mapper 接口
 * </p>
 *
 * @author MP自动生成
 * @since 2019-01-05
 */
public interface FrEducationCardObjectMapper extends BaseMapper<FrEducationCardObject> {

    List<FrEducationCardObject> findSettCourseEdu(@Param("eduId") String eduId);

}
