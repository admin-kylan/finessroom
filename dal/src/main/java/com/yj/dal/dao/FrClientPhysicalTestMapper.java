package com.yj.dal.dao;

import com.yj.dal.model.FrClientPhysicalTest;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 体能测试 内容保存 Mapper 接口
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-16
 */
public interface FrClientPhysicalTestMapper extends BaseMapper<FrClientPhysicalTest> {

    FrClientPhysicalTest findOne(@Param("cid") String cid, @Param("date") Date date);

    List<Map<String,Object>> getTrainClass();
}
