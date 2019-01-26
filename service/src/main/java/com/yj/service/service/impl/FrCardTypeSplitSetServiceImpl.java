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

import javax.servlet.http.HttpServletRequest;
import java.util.*;

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
    public JsonResult insert(Map<String, Object> map,FrCardTypeSplitSet frCardTypeSplitSet) throws YJException{
        frCardTypeSplitSet.setId(UUIDUtils.generateGUID());
        frCardTypeSplitSet.setCreateTime(new Date());
        frCardTypeSplitSet.setUpdateTime(new Date());
        frCardTypeSplitSet.setUsing(true);
        frCardTypeSplitSet.setCardTypeId((String) map.get("cardTypeId"));
        frCardTypeSplitSet.setFirstPrice(Double.parseDouble(map.get("firstPrice").toString()));
        frCardTypeSplitSet.setSplitNum(Integer.parseInt(String.valueOf(map.get("splitNum"))));
        frCardTypeSplitSet.setSplitType(Integer.parseInt(map.get("splitType").toString()));
        frCardTypeSplitSet.setSplitContent((String) map.get("splitContent"));
        frCardTypeSplitSet.setSplitPrice(Double.parseDouble(map.get("splitPrice").toString()));
        frCardTypeSplitSet.setTotalPrice(Double.parseDouble(map.get("totalPrice").toString()));
        FrCardTypeSplitSetDd frCardTypeSplitSetDd = new FrCardTypeSplitSetDd();
        List<Map<String,String>> list = (List<Map<String,String>>)map.get("splitSetDd");
        if(list.size() > 0){
            for(Map splitSetDd : list){
                frCardTypeSplitSetDd.setId(UUIDUtils.generateGUID());
                frCardTypeSplitSetDd.setSplitSetId(frCardTypeSplitSet.getId());
                frCardTypeSplitSetDd.setSplitNum(Double.parseDouble(splitSetDd.get("splitNum").toString()));
                frCardTypeSplitSetDd.setSplitDate(Integer.parseInt(splitSetDd.get("splitDate").toString()));
                frCardTypeSplitSetDd.setSplitOrder(Integer.parseInt(splitSetDd.get("splitOrder").toString()));
                System.out.print(frCardTypeSplitSetDd);
                frCardTypeSplitSetDdMapper.insert(frCardTypeSplitSetDd);
            }
        }
        frCardTypeSplitSetMapper.insert(frCardTypeSplitSet);
        return JsonResult.successMessage("添加成功");
    }

    @Override
    public JsonResult update(HttpServletRequest request, Map<String, Object> map)throws YJException {
        String id = (String) map.get("id");
        this.delete(id);
        FrCardTypeSplitSet frCardTypeSplitSet = new FrCardTypeSplitSet();
        //从cookie里获取
        String code = CookieUtils.getCookieValue(request,"code",true);
        if(StringUtils.isEmpty(code)){
            throw new YJException(YJExceptionEnum.CUSTOMERCODE_NOT_FOUND);
        }
        frCardTypeSplitSet.setCustomerCode(code);
        //设置创建者信息
        String userId = CookieUtils.getCookieValue(request, "id", true);
        String userName = CookieUtils.getCookieValue(request,"name",true);
        frCardTypeSplitSet.setCreateUserId(userId);
        frCardTypeSplitSet.setUpdateUserId(userId);
        frCardTypeSplitSet.setCreateUserName(userName);
        frCardTypeSplitSet.setUpdateUserName(userName);
        this.insert(map,frCardTypeSplitSet);
        return  JsonResult.successMessage("修改成功");
    }

    @Override
    public JsonResult delete(String id) throws YJException {
        if(id == null){
            throw new YJException(YJExceptionEnum.PARAM_ERROR);
        }
        FrCardTypeSplitSetDd frCardTypeSplitSetDd = new FrCardTypeSplitSetDd();
        frCardTypeSplitSetDd.setSplitSetId(id);
        frCardTypeSplitSetDdMapper.delete((new EntityWrapper<FrCardTypeSplitSetDd>().where("split_set_id ={0}",frCardTypeSplitSetDd.getSplitSetId())));
        boolean flag =  deleteById(id);
        if (flag) {
            return JsonResult.successMessage("删除成功");
        }
        return JsonResult.fail();
    }

    @Override
    public JsonResult get(String id) throws YJException {
        FrCardTypeSplitSet frCardTypeSplitSet = selectOne(new EntityWrapper<FrCardTypeSplitSet>().where("id={0}",id));
        List<Map<String,Object>> splitSetDd  =  frCardTypeSplitSetDdMapper.selectMaps((new EntityWrapper<FrCardTypeSplitSetDd>().where("split_set_id ={0}",id)));
        Map<String,Object> map = new HashMap<>();
        map.put("splitSetDd",splitSetDd);
        List list = new ArrayList();
        list.add(frCardTypeSplitSet);
        list.add(map);
        return JsonResult.success(list);
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
