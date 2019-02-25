package com.yj.dal.dao;

import com.yj.dal.model.Sdaduim;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-26
 */
public interface SdaduimMapper extends BaseMapper<Sdaduim> {

    /**
     * 根据场馆名称查询
     * @param sdadiumName
     * @return
     */
    Sdaduim selectBySdadiumName(String sdadiumName);
}
