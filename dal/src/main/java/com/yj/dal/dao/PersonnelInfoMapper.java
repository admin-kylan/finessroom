package com.yj.dal.dao;

import com.yj.dal.model.PersonnelInfo;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 人员信息表 Mapper 接口
 * </p>
 *
 * @author MP自动生成
 * @since 2018-10-09
 */
public interface PersonnelInfoMapper extends BaseMapper<PersonnelInfo> {
    List<PersonnelInfo> getMarketUserList(@Param("shopId") String shopId,@Param("code") String code);

}
