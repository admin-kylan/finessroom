package com.yj.dal.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.yj.dal.model.FrEducation;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 教学表，团体教学，私人教学 Mapper 接口
 * </p>
 *
 * @author MP自动生成
 * @since 2019-01-05
 */
public interface FrEducationMapper extends BaseMapper<FrEducation> {


    List<Map<String, Object>> findEducationPublicListCoach(@Param("shopId") String shopId, @Param("executeDate") String executeDate, @Param("sdaduimId") String sdaduimId, @Param("CustomerCode") String CustomerCode, @Param("teachType")Integer teachType);


    List<Map<String, Object>> findEducationPublicListRoom(@Param("shopId") String shopId, @Param("sdaduimId") String sdaduimId, @Param("executeDate") String executeDate, @Param("CustomerCode") String CustomerCode, @Param("teachType")Integer teachType);


    List<Map<String, Object>> findEducationPublicListDate(@Param("shopId") String shopId, @Param("sdaduimId") String sdaduimId, @Param("executeBeginDate") String executeBeginDate,@Param("executeEndDate") String executeEndDate, @Param("CustomerCode") String CustomerCode, @Param("teachType")Integer teachType);
}
