package com.yj.service.service;

import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.dal.dto.NumRoleDTO;
import com.yj.dal.model.FrCardNum;
import com.yj.service.base.BaseService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 会员卡号规则表 服务类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-27
 */
public interface IFrCardNumService extends BaseService<FrCardNum> {

    List<NumRoleDTO> selectFrCardNumRuleList(String code);

    boolean addRole(FrCardNum frCardNum) throws YJException;

    String checkCardNum(String cardNum,String code);

    JsonResult addCardNum(String code);

    /**
     * 生成协议编号
     * @param code
     * @return
     */
    Map<String,String> getCardNum(String code);
}
