package com.yj.dal.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.yj.dal.dto.NumRoleDTO;
import com.yj.dal.model.FrAgreement;

import java.util.List;

/**
 * <p>
 * 协议号表 Mapper 接口
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-04
 */
public interface FrAgreementMapper extends BaseMapper<FrAgreement> {

    List<NumRoleDTO> selectRoleList(String code);
}
