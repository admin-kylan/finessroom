package com.yj.dal.dao;

import com.yj.dal.model.FrClientArchivesRelate;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 客户档案（皮肤、头发、塑性抗衰 档案）  内容保存 Mapper 接口
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-13
 */
public interface FrClientArchivesRelateMapper extends BaseMapper<FrClientArchivesRelate> {

    void updateText(@Param("type") String type, @Param("typeId") String typeId, @Param("text") String text);

    List<FrClientArchivesRelate> getRelate(@Param("date")Date date,@Param("cid") String cid,@Param("type")Integer type);
}
