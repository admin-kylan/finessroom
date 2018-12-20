package com.yj.service.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.util.StringUtils;
import com.yj.common.util.UUIDUtils;
import com.yj.dal.dto.CategoryItemDTO;
import com.yj.dal.model.FrCategoryItem;
import com.yj.dal.model.FrShopCtypeConsume;
import com.yj.dal.dao.FrShopCtypeConsumeMapper;
import com.yj.dal.model.Sdaduim;
import com.yj.service.service.IFrCategoryItemService;
import com.yj.service.service.IFrShopCtypeConsumeService;
import com.yj.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 会员卡类型-门店-场馆-项目关系表（消费门店会员卡权益设置） 服务实现类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-29
 */
@Service
public class FrShopCtypeConsumeServiceImpl extends BaseServiceImpl<FrShopCtypeConsumeMapper, FrShopCtypeConsume> implements IFrShopCtypeConsumeService {

    @Autowired
    IFrCategoryItemService iFrCategoryItemService;

    @Override
    @Transactional
    public Boolean addShopCtypeConsume(Map<String, Object> map, String cardTypeId)  {
        Integer successCount  = 0;
        for(String key:map.keySet()){
            //门店id
            String shopId = key;
            //场馆集合
            map.get(key).toString();
            List<Sdaduim> list = JSONObject.parseArray(map.get(key).toString(), Sdaduim.class);

            for (int i = 0; i < list.size() ; i++) {
                Sdaduim sdaduim = list.get(i);
                String sdaduimId = sdaduim.getId();
                //场馆下的项目列表
                List<CategoryItemDTO> categoryItemlist = sdaduim.getCategoryItemlist();
                for (int j = 0; j < categoryItemlist.size() ; j++) {
                    CategoryItemDTO dto = categoryItemlist.get(j);

                    FrCategoryItem frCategoryItem = new FrCategoryItem();
                    frCategoryItem.setUsing(dto.getChecked());
                    frCategoryItem.setCreateTime(dto.getCreateTime());
                    EntityWrapper entityWrapper = new EntityWrapper<>();
                    entityWrapper.where("id = {0}",dto.getId());
                    iFrCategoryItemService.update(frCategoryItem,entityWrapper);

                    FrShopCtypeConsume frShopCtypeConsume = new FrShopCtypeConsume();
                    frShopCtypeConsume.setCardTypeId(cardTypeId);
                    frShopCtypeConsume.setShopId(shopId);
                    frShopCtypeConsume.setSdaduimId(sdaduimId);
                    frShopCtypeConsume.setCustomerCode(dto.getCustomerCode());
                    frShopCtypeConsume.setItemId(dto.getId());
                    frShopCtypeConsume.setKzje(dto.getKzje());
                    frShopCtypeConsume.setModeDiscount(dto.getModeDiscount());
                    frShopCtypeConsume.setModePrice(dto.getModePrice());
                    frShopCtypeConsume.setModeWay(dto.getModeWay());
                    frShopCtypeConsume.setUsageMode(dto.getUsageMode());
                    frShopCtypeConsume.setPlRight(dto.getPlRight());
                    frShopCtypeConsume.setPlLeft(dto.getPlLeft());
                    frShopCtypeConsume.setPlTime(dto.getPlTime());
                    frShopCtypeConsume.setSdaduimId(sdaduimId);
                    // 加入
                    frShopCtypeConsume.setModeTime(dto.getModeTime());
                    frShopCtypeConsume.setModeNum(dto.getModeNum());

                    if(dto.getSccId()==null){
                        //插入
                        frShopCtypeConsume.setId(UUIDUtils.generateGUID());
                        successCount = baseMapper.insert(frShopCtypeConsume);
                    }else {
                        //更新
                        frShopCtypeConsume.setId(dto.getSccId());
                        successCount = baseMapper.updateById(frShopCtypeConsume);
                    }
                }

            }
        }
        return SqlHelper.retBool(successCount);
    }


    @Override
    public List<FrShopCtypeConsume> queryByConsumeId(String cardTypeId, String code) throws YJException {
        if(StringUtils.isEmpty(cardTypeId) || StringUtils.isEmpty(code)){
            throw new YJException(YJExceptionEnum.PARAM_ERROR);
        }
        FrShopCtypeConsume frShopCtypeConsume = new FrShopCtypeConsume();
        frShopCtypeConsume.setCardTypeId(cardTypeId);
        frShopCtypeConsume.setCustomerCode(code);
        return baseMapper.queryByConsumeId(frShopCtypeConsume);
    }
}
