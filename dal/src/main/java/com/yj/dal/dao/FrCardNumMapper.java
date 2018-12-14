package com.yj.dal.dao;

import com.yj.dal.dto.NumRoleDTO;
import com.yj.dal.model.FrCardNum;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 会员卡号规则表 Mapper 接口
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-27
 */
public interface FrCardNumMapper extends BaseMapper<FrCardNum> {

    List<NumRoleDTO> selectRoleList(String code);
}
