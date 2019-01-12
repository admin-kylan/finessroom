package com.yj.service.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.result.JsonResult;
import com.yj.common.util.CookieUtils;
import com.yj.common.util.StringUtils;
import com.yj.common.util.UUIDUtils;
import com.yj.dal.dao.FrCardTypeSplitSetDdMapper;
import com.yj.dal.model.FrCardTypeSplitSet;
import com.yj.dal.dao.FrCardTypeSplitSetMapper;
import com.yj.dal.model.FrCardTypeSplitSetDd;
import com.yj.service.service.IFrCardTypeSplitSetService;
import com.yj.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 会员卡分期付款设置 服务实现类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-13
 */
@Service
public class FrCardTypeSplitSetServiceImpl extends BaseServiceImpl<FrCardTypeSplitSetMapper, FrCardTypeSplitSet> implements IFrCardTypeSplitSetService {

    @Autowired
    FrCardTypeSplitSetMapper frCardTypeSplitSetMapper;

    @Autowired
    FrCardTypeSplitSetDdMapper frCardTypeSplitSetDdMapper;


    @Override
    public JsonResult insert(Map<String, Object> map) throws YJException{
        FrCardTypeSplitSet frCardTypeSplitSet = new FrCardTypeSplitSet();
        frCardTypeSplitSet.setId(UUIDUtils.generateGUID());
        frCardTypeSplitSet.setCreateTime(new Date());
        frCardTypeSplitSet.setUpdateTime(new Date());
        frCardTypeSplitSet.setUsing(true);
        frCardTypeSplitSet.setCardTypeId((String) map.get("cardTypeId"));
        frCardTypeSplitSet.setFirstPrice(Double.parseDouble(map.get("firstPrice").toString()));
        frCardTypeSplitSet.setSplitNum(Integer.parseInt(map.get("splitNum ").toString()));
        frCardTypeSplitSet.setSplitType(Integer.parseInt(map.get("splitType ").toString()));
        frCardTypeSplitSet.setSplitContent((String) map.get("splitContent "));
        frCardTypeSplitSet.setSplitPrice(Double.parseDouble(map.get("splitPrice").toString()));
        frCardTypeSplitSet.setTotalPrice(Double.parseDouble(map.get("totalPrice ").toString()));

        FrCardTypeSplitSetDd frCardTypeSplitSetDd = new FrCardTypeSplitSetDd();
        List<Map<String,String>> list = (List<Map<String,String>>)map.get("splitSetDd");
        if(list.size() > 0){
            for(Map splitSetDd : list){
                frCardTypeSplitSetDd.setId(UUIDUtils.generateGUID());
                frCardTypeSplitSetDd.setSplitSetId(frCardTypeSplitSet.getId());
                frCardTypeSplitSetDd.setSplitNum(Double.parseDouble(splitSetDd.get("splitNum").toString()));
                frCardTypeSplitSetDd.setSplitDate(Integer.parseInt(splitSetDd.get("splitDate ").toString()));
                frCardTypeSplitSetDd.setSplitOrder(Integer.parseInt(splitSetDd.get("splitOrder ").toString()));
                frCardTypeSplitSetDdMapper.insert(frCardTypeSplitSetDd);
            }
        }
        frCardTypeSplitSetMapper.insert(frCardTypeSplitSet);
        return JsonResult.successMessage("添加成功");
    }

    @Override
    public JsonResult update(FrCardTypeSplitSet frCardTypeSplitSet)throws YJException {
        boolean flag = updateById(frCardTypeSplitSet);
        if(flag){
            return  JsonResult.successMessage("修改成功");
        }
        return JsonResult.fail();
    }


    @Override
    public JsonResult delete(String id) throws YJException {

//        if (id != null && id != "") {
//            FrCardTypeSplitSet frCardTypeSplitSet = selectOne(
//                    new EntityWrapper<FrCardTypeSplitSet>().where("is_using=1 and id={0}", id)
//            );
//            boolean flag = false;
//            if (frCardTypeSplitSet != null) {
//                frCardTypeSplitSet.setUsing(false);
//                flag = updateById(frCardTypeSplitSet);
//            }
        boolean flag =  deleteById(id);
        if (flag) {
            return JsonResult.successMessage("删除成功");
        }
        return JsonResult.fail();
    }

    @Override
    public FrCardTypeSplitSet get(String id) throws YJException {
        FrCardTypeSplitSet frCardTypeSplitSet = selectOne(
                new EntityWrapper<FrCardTypeSplitSet>().where("is_using=1 and id={0}",id));
        return frCardTypeSplitSet;
    }

    @Override
    public JsonResult isUsing(FrCardTypeSplitSet frCardTypeSplitSet) throws YJException {
        if (frCardTypeSplitSet.getId() != null && frCardTypeSplitSet.getId() != "") {
            boolean flag = updateById(frCardTypeSplitSet);
            if(flag){
                return  JsonResult.successMessage("修改成功");
            }
        }
        return JsonResult.fail();
    }
}
