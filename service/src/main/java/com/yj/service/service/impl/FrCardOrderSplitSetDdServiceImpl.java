package com.yj.service.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.util.DateUtil;
import com.yj.common.util.StringUtils;
import com.yj.common.util.UUIDUtils;
import com.yj.dal.dao.FrCardTypeSplitSetDdMapper;
import com.yj.dal.model.FrCardOrderSplitSetDd;
import com.yj.dal.dao.FrCardOrderSplitSetDdMapper;
import com.yj.dal.model.FrCardTypeSplitSetDd;
import com.yj.service.service.IFrCardOrderSplitSetDdService;
import com.yj.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 所有会员卡分期付款订单，相关分期付款设置 详细 服务实现类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-13
 */
@Service
public class FrCardOrderSplitSetDdServiceImpl extends BaseServiceImpl<FrCardOrderSplitSetDdMapper, FrCardOrderSplitSetDd> implements IFrCardOrderSplitSetDdService {

    @Resource
    private FrCardTypeSplitSetDdMapper frCardTypeSplitSetDdMapper;

    /**
     * 初始化会员卡分期详情
     * @param cardTypeSplitSetId    FrCardTypeSplitSet 的主键ID
     * @param cardOrderSplitSetId  FrCardOrderSplitSet 的主键ID
     * @return
     * @throws YJException
     */
    public List<FrCardOrderSplitSetDd> initFrCardOrderSplitSetDd(String cardTypeSplitSetId,String cardOrderSplitSetId)throws YJException {
        List<FrCardTypeSplitSetDd> frCardTypeSplitSetDds = frCardTypeSplitSetDdMapper.selectList(new EntityWrapper<FrCardTypeSplitSetDd>().where("split_set_id={0}",cardTypeSplitSetId));
        List<FrCardOrderSplitSetDd> list = new ArrayList<>();
        Date data = new Date();
        if(frCardTypeSplitSetDds != null  && frCardTypeSplitSetDds.size()>0){
            for(FrCardTypeSplitSetDd frCardTypeSplitSetDd : frCardTypeSplitSetDds){
                FrCardOrderSplitSetDd frCardOrderSplitSetDd = new FrCardOrderSplitSetDd();
                frCardOrderSplitSetDd.setId(UUIDUtils.generateGUID());
                frCardOrderSplitSetDd.setSplitSetId(cardOrderSplitSetId);
                frCardOrderSplitSetDd.setSplitNum(frCardTypeSplitSetDd.getSplitNum());
                frCardOrderSplitSetDd.setSplitOrder(frCardTypeSplitSetDd.getSplitOrder());
                frCardOrderSplitSetDd.setSplitDate(DateUtil.getAddForDays(data,frCardTypeSplitSetDd.getSplitDate()));
                list.add(frCardOrderSplitSetDd);
            }
        }
        return list;
    }



}
