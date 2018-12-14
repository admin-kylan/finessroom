package com.yj.dal.dao;

import com.yj.dal.model.FrClientArchives;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 客户档案（皮肤、头发、塑性抗衰 档案） 区分 Mapper 接口
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-13
 */
public interface FrClientArchivesMapper extends BaseMapper<FrClientArchives> {

    FrClientArchives findByTime(@Param("date") Date date,@Param("cid") String cid ,@Param("type")Integer type);

    List<FrClientArchives> findByClient(@Param("cid")String cid,@Param("type") Integer type);
}
