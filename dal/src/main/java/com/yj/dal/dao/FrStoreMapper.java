package com.yj.dal.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.yj.dal.model.FrStore;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 门店表 Mapper 接口
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-12
 */
public interface FrStoreMapper extends BaseMapper<FrStore> {

    List<FrStore> selectListByGroupBy();

}
