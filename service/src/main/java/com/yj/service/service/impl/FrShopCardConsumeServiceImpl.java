package com.yj.service.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.util.StringUtils;
import com.yj.common.util.UUIDUtils;
import com.yj.dal.dao.FrShopCardConsumePlsetMapper;
import com.yj.dal.model.*;
import com.yj.dal.dao.FrShopCardConsumeMapper;
import com.yj.service.service.*;
import com.yj.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 会员卡类型-门店-场馆-项目关系表（消费门店会员卡权益设置） 服务实现类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-12-19
 */
@Service
public class FrShopCardConsumeServiceImpl extends BaseServiceImpl<FrShopCardConsumeMapper, FrShopCardConsume> implements IFrShopCardConsumeService {

    @Resource
    private IFrShopCardConsumePlsetService iFrShopCardConsumePlsetService;
    @Resource
    private IFrShopCardConsumePladdsetService iFrShopCardConsumePladdsetService;

    @Resource
    private IFrShopCtypeConsumeService iFrShopCtypeConsumeService;




    /**
     * 根据卡种信息初始化，会员卡的使用及权益范围
     * @param cardTypeId
     * @param cardId
     * @return
     * @throws YJException
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean queryByCardTypeId(String cardTypeId, String cardId,String code) throws YJException {
        if(StringUtils.isEmpty(cardTypeId) || StringUtils.isEmpty(cardId) || StringUtils.isEmpty(code)){
            throw  new  YJException(YJExceptionEnum.PARAM_ERROR);
        }
        List<FrShopCtypeConsume> frShopCtypeConsumeList = iFrShopCtypeConsumeService.queryByConsumeId(cardTypeId,code);
        List<FrShopCardConsume> frShopCardConsumeList = new ArrayList<>();
        List<FrShopCardConsumePladdset> frShopCardConsumePladdsetList = new ArrayList<>();
        List<FrShopCardConsumePlset> frShopCardConsumePlsetList = new ArrayList<>();
        this.getCardConsumeList(frShopCtypeConsumeList,frShopCardConsumeList,frShopCardConsumePladdsetList,frShopCardConsumePlsetList,cardId);
        if(frShopCardConsumeList != null && frShopCardConsumeList.size()>0){
            for(FrShopCardConsume frShopCardConsume : frShopCardConsumeList){
                baseMapper.insert(frShopCardConsume);
            }
        }
        if(frShopCardConsumePladdsetList != null && frShopCardConsumePladdsetList.size()>0){
            for(FrShopCardConsumePladdset frShopCardConsumePladdset : frShopCardConsumePladdsetList){
                iFrShopCardConsumePladdsetService.insert(frShopCardConsumePladdset);
            }
        }
        if(frShopCardConsumePlsetList != null && frShopCardConsumePlsetList.size()>0){
            for(FrShopCardConsumePlset frShopCardConsumePlset : frShopCardConsumePlsetList){
               iFrShopCardConsumePlsetService.insert(frShopCardConsumePlset);
            }
        }
        return true;
    }

    /**
     * 初始化会员卡类型-门店-场馆-项目关系表
     * @param frShopCtypeConsumeList
     * @return
     */
    public void getCardConsumeList( List<FrShopCtypeConsume> frShopCtypeConsumeList,
                                                        List<FrShopCardConsume> frShopCardConsumeList,
                                                        List<FrShopCardConsumePladdset> frShopCardConsumePladdsetList,
                                                        List<FrShopCardConsumePlset> frShopCardConsumePlsetList,
                                                        String cardId){
        if(frShopCtypeConsumeList != null  && frShopCtypeConsumeList.size() >0){
            for(FrShopCtypeConsume frShopCtypeConsume : frShopCtypeConsumeList){
                FrShopCardConsume frShopCardConsume = this.getShopCardConsumeInit(frShopCtypeConsume,cardId);
                if(frShopCardConsume != null){
                    frShopCardConsumeList.add(frShopCardConsume);
                    List<FrShopCtypeConsumePladdset> frShopCtypeConsumePladdsets = frShopCtypeConsume.getFrShopCtypeConsumePladdsetList();
                    if( frShopCtypeConsumePladdsets  != null && frShopCtypeConsumePladdsets.size() >0){
                        for(FrShopCtypeConsumePladdset frShopCtypeConsumePladdset:frShopCtypeConsumePladdsets){
                            FrShopCardConsumePladdset frShopCardConsumePladdset = iFrShopCardConsumePladdsetService.getCardConsumePladdsetInfo(frShopCtypeConsumePladdset,frShopCardConsume.getId());
                            if(frShopCardConsumePladdset != null){
                                frShopCardConsumePladdsetList.add(frShopCardConsumePladdset);
                            }
                        }
                    }
                    List<FrShopCtypeConsumePlset> frShopCtypeConsumePlsetList = frShopCtypeConsume.getFrShopCtypeConsumePlsetList();
                    if(frShopCtypeConsumePlsetList != null && frShopCtypeConsumePlsetList.size()>0){
                        for(FrShopCtypeConsumePlset frShopCtypeConsumePlset: frShopCtypeConsumePlsetList){
                            FrShopCardConsumePlset frShopCardConsumePlset = iFrShopCardConsumePlsetService.getCardConsumePlsetInfo(frShopCtypeConsumePlset,frShopCardConsume.getId());
                            if(frShopCardConsumePlset != null){
                                frShopCardConsumePlsetList.add(frShopCardConsumePlset);
                            }
                        }
                    }
                }
            }
        }
    }

    public  FrShopCardConsume getShopCardConsumeInit(FrShopCtypeConsume frShopCtypeConsume,String cardId){
        FrShopCardConsume frShopCardConsume = null;
        if(frShopCtypeConsume != null){
            frShopCardConsume = new FrShopCardConsume();
            frShopCardConsume.setId(UUIDUtils.generateGUID());
            frShopCardConsume.setCardId(cardId);
            frShopCardConsume.setShopId(frShopCtypeConsume.getShopId());
            frShopCardConsume.setSdaduimId(frShopCtypeConsume.getSdaduimId());
            frShopCardConsume.setItemId(frShopCtypeConsume.getItemId());
            frShopCardConsume.setTypeSetState(frShopCtypeConsume.getTypeSetState());
            frShopCardConsume.setKzje(frShopCtypeConsume.getKzje());
            frShopCardConsume.setUsageMode(frShopCtypeConsume.getUsageMode());
            frShopCardConsume.setModePrice(frShopCtypeConsume.getModePrice());
            frShopCardConsume.setModeWay(frShopCtypeConsume.getModeWay());
            frShopCardConsume.setModeDiscount(frShopCtypeConsume.getModeDiscount());
            frShopCardConsume.setPlSet(frShopCtypeConsume.getPlSet());
            frShopCardConsume.setPlLeft(frShopCtypeConsume.getPlLeft());
            frShopCardConsume.setPlTime(frShopCtypeConsume.getPlTime());
            frShopCardConsume.setPlRight(frShopCtypeConsume.getPlRight());
            frShopCardConsume.setCustomerCode(frShopCtypeConsume.getCustomerCode());
            frShopCardConsume.setModeTime(frShopCtypeConsume.getModeTime());
            frShopCardConsume.setModeNum(frShopCtypeConsume.getModeNum());
        }
        return frShopCardConsume;
    }
}
