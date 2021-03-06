package com.yj.service.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.common.util.CookieUtils;
import com.yj.common.util.UUIDUtils;
import com.yj.dal.model.FrSetGym;
import com.yj.dal.dao.FrSetGymMapper;
import com.yj.dal.model.Shop;
import com.yj.service.service.IFrSetGymService;
import com.yj.service.base.BaseServiceImpl;
import com.yj.service.service.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 健身馆设置保存 服务实现类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-12-28
 */
@Service
public class FrSetGymServiceImpl extends BaseServiceImpl<FrSetGymMapper, FrSetGym> implements IFrSetGymService {

    @Autowired
    IShopService shopService;

    @Override
    public List<Map<String, Object>> getShop(String code, String modelId) throws YJException {
        if (code == null || code == "") {
            HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            code = CookieUtils.getCookieValue(req, "code", true);
        }
        List<Map<String, Object>> list = shopService.selectMaps(
                new EntityWrapper<Shop>().setSqlSelect("ID", "shopName").where("CustomerCode={0} and Status={1}", code, 0)
        );
        for (Map<String, Object> map : list) {
            if (map != null) {
                List<FrSetGym> frSetGyms = baseMapper.selectList(
                        new EntityWrapper<FrSetGym>().where("is_using=1 and shop_id={0} and is_currency=0 and model_id={1}", map.get("ID"), modelId)
                );
                map.put("frSetGyms", frSetGyms);
            }
        }
        return list;
    }

    @Override
    public List<FrSetGym> getChainStore(String modelId) {
        List<FrSetGym> frSetGyms = baseMapper.selectList(
                new EntityWrapper<FrSetGym>().where("is_using=1 and is_currency=1 and model_id={0}", modelId)
        );
        return frSetGyms;
    }

    @Override
    public Map<String, Object> getTime(String modelId) {
        return baseMapper.getTime(modelId);
    }

    @Override
    public List<Map<String, Object>> getCityShop(String code) throws YJException {
        if (code == null || code == "") {
            HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            code = CookieUtils.getCookieValue(req, "code", true);
        }
        List<Map<String, Object>> city = baseMapper.getCity(code);
        for (Map<String, Object> map : city) {
            List<Map<String, Object>> shopList = shopService.selectMaps(
                    new EntityWrapper<Shop>().setSqlSelect("ID", "ShopName")
                            .where("CustomerCode={0} and CityId={1}", code, map.get("CityId"))
            );
            for (Map<String, Object> map2 : shopList) {
                map2.put("selected", false);
            }
            map.put("shopList", shopList);
            map.put("selected", false);
        }

        return city;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JsonResult saveProject(Map<String, Object> map) throws YJException {
        FrSetGym frSetGym = new FrSetGym();
        frSetGym.setProjectName((String) map.get("projectName"));
        frSetGym.setMarketPrice((Double.parseDouble(map.get("marketPrice").toString())));
        frSetGym.setPromotionPrice((Double.parseDouble(map.get("promotionPrice").toString())));
        frSetGym.setMemberPrice((Double.parseDouble(map.get("memberPrice").toString())));
        frSetGym.setRemarks((String) map.get("remarks"));
        frSetGym.setCurrency((Boolean) map.get("isCurrency"));
        frSetGym.setDeposit((Double.parseDouble(map.get("deposit").toString())));
        frSetGym.setProjectName((String) map.get("projectName"));
        frSetGym.setModelId((String) map.get("modelId"));
        frSetGym.setCompany((String) map.get("company"));
        if ("d93c9fc97f0759bb".equals(frSetGym.getModelId())) {
            frSetGym.setIsTime((String) map.get("isTime"));
            frSetGym.setOvertime((String) map.get("overtime"));


        }
        boolean b = false;
        List<Map<String, String>> list = (List<Map<String, String>>) map.get("shop");
        if (list.size() > 0) {
            for (Map shop : list) {
                frSetGym.setId(UUIDUtils.generateGUID());
                frSetGym.setShopId((String) shop.get("shopId"));
                frSetGym.setShopName((String) shop.get("shopName"));
                b = insert(frSetGym);
                if (!b) {
                    return JsonResult.fail();
                }
            }
        } else {
            frSetGym.setId(UUIDUtils.generateGUID());
            frSetGym.setCurrency(true);
            b = insert(frSetGym);
            if (!b) {
                return JsonResult.fail();
            }
        }


        return JsonResult.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JsonResult updateProject(FrSetGym frSetGym) throws YJException {
        frSetGym.setUpdateTime(null);
        frSetGym.setUpdateUserName(null);
        frSetGym.setUpdateUserId(null);
        boolean b = updateById(frSetGym);
        if (b) {
            return JsonResult.success();
        }
        return JsonResult.fail();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JsonResult delProject(String id) throws YJException {
        if (id != null && id != "") {
            FrSetGym frSetGym = selectOne(
                    new EntityWrapper<FrSetGym>().where("is_using=1 and id={0}", id)
            );
            boolean b = false;
            if (frSetGym != null) {
                frSetGym.setUsing(false);
                frSetGym.setUpdateTime(null);
                frSetGym.setUpdateUserName(null);
                frSetGym.setUpdateUserId(null);
                b = updateById(frSetGym);
            }
            if (b) {
                return JsonResult.success(frSetGym.getId());
            }
        }
        return JsonResult.fail();
    }


}
