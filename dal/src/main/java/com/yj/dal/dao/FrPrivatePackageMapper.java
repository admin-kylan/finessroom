package com.yj.dal.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.yj.dal.model.FrPrivateCource;
import com.yj.dal.model.FrPrivatePackage;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-18
 */
public interface FrPrivatePackageMapper extends BaseMapper<FrPrivatePackage> {

    List<FrPrivatePackage> findCource(Page page, @Param("shopId") String shopId,@Param("sdaduimId") String sdaduimId);
}
