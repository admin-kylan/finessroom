package com.yj.service.service;

import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.dal.dto.NumRoleDTO;
import com.yj.dal.model.FrAgreement;
import com.yj.dal.model.FrCardAgreement;
import com.yj.service.base.BaseService;

import java.util.List;

/**
 * <p>
 * 协议号规则表 服务类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-27
 */
public interface IFrAgreementService extends BaseService<FrAgreement> {

    List<NumRoleDTO> selectAgreementRuleList(String code);

    boolean addRole(FrAgreement frAgreement) throws YJException;

    String checkCardAgreement(String agreement,String code);

    JsonResult addCardAgreement(String code);

    /**
     * 生成协议编号
     * @param code
     * @return
     */
    FrCardAgreement getAgreement(String code);
}
