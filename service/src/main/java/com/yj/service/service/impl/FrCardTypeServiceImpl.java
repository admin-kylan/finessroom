package com.yj.service.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.result.JsonResult;
import com.yj.common.util.NumberUtilsTwo;
import com.yj.common.util.StringUtils;
import com.yj.common.util.UUIDUtils;
import com.yj.dal.dao.FrCardMapper;
import com.yj.dal.dao.FrShopCardTypeRelateMapper;
import com.yj.dal.model.*;
import com.yj.dal.dao.FrCardTypeMapper;
import com.yj.service.service.IFrCardTypeService;
import com.yj.service.base.BaseServiceImpl;
import com.yj.service.service.IShopService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 * 卡类型表 服务实现类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-10
 */
@Service
public class FrCardTypeServiceImpl extends BaseServiceImpl<FrCardTypeMapper, FrCardType> implements IFrCardTypeService {

    @Resource
    private FrCardTypeMapper frCardTypeMapper;

    @Resource
    private FrShopCardTypeRelateMapper frShopCardTypeRelateMapper;

    @Resource
    private FrCardMapper frCardMapper;

    @Resource
    private IShopService iShopService;

    /**
     * 加入卡系列、卡种
     *
     * @param ty       0为卡系列，1为卡种
     * @param frCardType
     * @param ids        关联门店id
     * @param cardType
     * @return
     */
    @Override
    @Transactional
    public JsonResult addFrCardType(FrCardType frCardType, String[] ids, Integer ty, String cardType) {
        frCardType.setCreateTime(new Date());
        if ("2".equals(cardType)) {
            frCardType.setUsing(false);
        } else if ("1".equals(cardType)) {
            frCardType.setUsing(true);
        }
        if (ty == 1) {
            //添加UUID
            frCardType.setId(UUIDUtils.generateGUID());
        }
        if (ids != null && ids.length != 0) {
            //添加关联门店
            FrShopCardTypeRelate sctyRelate = new FrShopCardTypeRelate();
            for (int i = 0; i < ids.length; i++) {
                //0为卡系列，1为卡种
                //添加卡种需要添加销售门店
                if (ty == 0) {
                    //添加UUID
                    frCardType.setId(UUIDUtils.generateGUID());
                    frCardType.setpId("0");
                    //添加卡类型表归属门店
                    frCardType.setShopId(ids[i]);

                } else {
                    //卡系列id
                    String cardTypeId = frCardType.getId();
                    sctyRelate.setName(frCardType.getCardTypeName());
                    sctyRelate.setCardTypeId(cardTypeId);
                    sctyRelate.setCreateTime(new Date());
                    sctyRelate.setTypeSetState(frCardType.getTypeSetState());
                    sctyRelate.setCreateUserId(frCardType.getCreateUserId());
                    sctyRelate.setCreateUserName(frCardType.getCreateUserName());
                    sctyRelate.setId(UUIDUtils.generateGUID());
                    sctyRelate.setCustomerCode(frCardType.getCustomerCode());
                    sctyRelate.setShopId(ids[i]);
                    //插入卡系列或卡种关联门店信息
                    Integer c = frShopCardTypeRelateMapper.insert(sctyRelate);
                    if (c < 1) {
                        return JsonResult.failMessage("添加卡种与门店关系失败");
                    }
                }
                if (ty == 0) {
                    boolean code = this.insert(frCardType);
                    if (!code) {
                        return JsonResult.failMessage("添加失败");
                    }
                }

            }
        }
        //添加卡种
        if (ty == 1) {
            boolean code2 = this.insert(frCardType);
            if (!code2) {
                return JsonResult.failMessage("添加失败");
            }
        }
        return JsonResult.success();
    }


    /**
     * 通用门店添加卡系列或卡种
     *
     * @param frCardType
     * @param ids
     * @param type
     * @param cardType
     * @return
     */
    @Override
    @Transactional
    public JsonResult addCurrentFrCardType(FrCardType frCardType, String[] ids, Integer type, String cardType) {
        frCardType.setCreateTime(new Date());
        if ("2".equals(cardType)) {
            frCardType.setUsing(false);
        } else if ("1".equals(cardType)) {
            frCardType.setUsing(true);
        }
        if (type == 0) {
            frCardType.setpId("0");
        }

        //添加UUID
        frCardType.setId(UUIDUtils.generateGUID());
        if (ids != null && ids.length != 0) {
            //添加关联门店
            FrShopCardTypeRelate sctyRelate = new FrShopCardTypeRelate();
            for (int i = 0; i < ids.length; i++) {
                //0为卡系列，1为卡种
                //添加卡种需要添加销售门店
                if (type == 0) {
                    //添加UUID
                    frCardType.setId(UUIDUtils.generateGUID());
                    frCardType.setpId("0");
                    //添加卡类型表归属门店
                    frCardType.setShopId(ids[i]);

                } else {
                    //卡系列id
                    String cardTypeId = frCardType.getId();
                    sctyRelate.setName(frCardType.getCardTypeName());
                    sctyRelate.setCardTypeId(cardTypeId);
                    sctyRelate.setCreateTime(new Date());
                    sctyRelate.setTypeSetState(frCardType.getTypeSetState());
                    sctyRelate.setCreateUserId(frCardType.getCreateUserId());
                    sctyRelate.setCreateUserName(frCardType.getCreateUserName());
                    sctyRelate.setId(UUIDUtils.generateGUID());
                    sctyRelate.setCustomerCode(frCardType.getCustomerCode());
                    sctyRelate.setShopId(ids[i]);
                    //插入卡系列或卡种关联门店信息
                    Integer c = frShopCardTypeRelateMapper.insert(sctyRelate);
                    if (c < 1) {
                        return JsonResult.failMessage("添加卡种与门店关系失败");
                    }
                }
                if (type == 0) {
                    Integer code = frCardTypeMapper.insert(frCardType);
                    if (code < 1) {
                        return JsonResult.failMessage("添加失败");
                    }
                }

            }
        }
        if (type != 0) {
            boolean code = this.insert(frCardType);
            if (!code) {
                return JsonResult.failMessage("添加失败");
            }
        }


        //添加卡系列
        if (type == 0 && ids.length < 1) {
            boolean code2 = this.insert(frCardType);
            if (!code2) {
                return JsonResult.failMessage("添加失败");
            }
        }
        return JsonResult.success();
    }

    /**
     * 修改卡系列
     *
     * @param frCardType
     * @return
     */
    @Override
    public JsonResult updateFrCardType(FrCardType frCardType) {
        frCardType.setUpdateTime(new Date());
        //修改该ID表信息
        Integer code = frCardTypeMapper.updateById(frCardType);
        if (code < 1) {
            return JsonResult.failMessage("修改失败");
        }
        return JsonResult.success();
    }

    /**
     * 修改卡种
     *
     * @param frCardType
     * @return
     */
    @Override
    public JsonResult updateFrCard(FrCardType frCardType) {
        frCardType.setUpdateTime(new Date());
        //修改该ID表信息
        Integer code = frCardTypeMapper.updateById(frCardType);
        if (code < 1) {
            return JsonResult.failMessage("修改失败");
        }
        FrShopCardTypeRelate f = new FrShopCardTypeRelate();
        f.setUpdateTime(new Date());
        f.setName(frCardType.getCardTypeName());
        //修改卡种与销售门店关系
        Integer cc = frShopCardTypeRelateMapper.update(
                f,
                new EntityWrapper<FrShopCardTypeRelate>()
                        .where("card_type_id = {0} ", frCardType.getId())
        );
        if (cc < 1) {
            return JsonResult.failMessage("修改关系失败");
        }

        return JsonResult.success();
    }


    /**
     * 删除卡系列
     *
     * @param id 卡系列id
     * @return
     */
    @Override
    @Transactional
    public JsonResult deleteFrCardType(String id, String shopId) {
        try {
            //获取该系列下是否有卡种，以及卡种下是否有卡，并提示删除着
            //先查询底下卡种
            List<FrCardType> list = this.selectList(
                    new EntityWrapper<FrCardType>().where("p_id = {0}", id)
            );
            if (list != null && list.size() > 0) {
                for (FrCardType frCardType : list) {
                    //查询该卡种下是否有会员卡
                    List list1 = frCardMapper.selectList(
                            new EntityWrapper<FrCard>()
                                    .where("card_type_id = {0}", frCardType.getId())
                                    .and("is_using = 1")
                    );
                    if (list1 != null && list1.size() > 0) {
                        return JsonResult.failMessage("该卡系列的卡种下已有会员卡正在使用，不可删除！");
                    }
                }
                //删除卡种与门店关系
                for (FrCardType frCardType : list) {
                    //删除卡种与门店关系表
                    int c1 = frShopCardTypeRelateMapper.delete(
                            new EntityWrapper<FrShopCardTypeRelate>()
                                    .where("card_type_id = {0}", frCardType.getId())
                    );
                    //删除卡种（与卡系列关系）
                    int c2 = frCardTypeMapper.deleteById(frCardType.getId());
                    if (c1 < 1 || c2 < 1) {
                        return JsonResult.failMessage("删除失败");
                    }
                }
                //删除卡系列
                int c3 = frCardTypeMapper.deleteById(list.get(0).getpId());
                if (c3 < 1) {
                    return JsonResult.failMessage("删除失败");
                }
            } else {
                //下无卡种与会员卡则直接删除卡系列
                //先删除卡系列与门店关系表
                Integer code = frCardTypeMapper.delete(
                        new EntityWrapper<FrCardType>()
                                .where("id = {0}", id)
//                                .and("shop_id = {0}", shopId)
                );
                if (code < 1) {
                    return JsonResult.failMessage("删除错误！");
                }
            }
        } catch (Exception e) {
            return JsonResult.failMessage("出现异常");
        }
        return JsonResult.success();
    }

    /**
     * 删除卡种
     *
     * @param id
     * @return
     */
    @Override
    @Transactional
    public JsonResult deleteCardType(String id) {
        try {
            //查询该卡种下是否有会员卡
            List list = frCardMapper.selectList(
                    new EntityWrapper<FrCard>()
                            .where("card_type_id = {0}", id)
                            .and("is_using=1")
            );
            if (list != null && list.size() > 0) {
                return JsonResult.failMessage("该卡种下已有会员卡正在使用，不可删除！");
            } else {
                //无会员卡情况
                //判断是否是通用门店员工卡卡种，是则没有关系门店
                FrCardType frCardType = frCardTypeMapper.selectById(id);
                if (frCardType.getType() != 7) {
                    //删除该卡种与门店关系
                    Integer a = frShopCardTypeRelateMapper.delete(
                            new EntityWrapper<FrShopCardTypeRelate>()
                                    .where("card_type_id = {0}", id)
                    );
                    if (a < 1) {
                        return JsonResult.failMessage("删除错误！");
                    }
                }
                //下无会员卡则直接删除卡种
                boolean b = this.delete(
                        new EntityWrapper<FrCardType>().where("id = {0}", id)
                );
                if (!b) {
                    return JsonResult.failMessage("删除错误！");
                }


            }
        } catch (Exception e) {
            return JsonResult.failMessage("出现异常");
        }

        return JsonResult.success();
    }

    /**
     * 获取通用门店卡系列及卡种
     *
     * @return
     */
    @Override
    public JsonResult getGeneralStoreList(String code) {
        //获取所有卡系列
        List<FrCardType> list = frCardTypeMapper.selectList(
                new EntityWrapper<FrCardType>()
                        .where("p_id = '0'")
                        .and("type_set_state = 1")
                        .and("is_using = 1")
                        .and("CustomerCode = {0}", code).orderBy("create_time")
        );
        for (FrCardType frCardType : list) {
            //查询该卡系列下的所以卡种
            List<FrCardType> list1 = frCardTypeMapper.selectList(
                    new EntityWrapper<FrCardType>()
                            .where("p_id = {0}", frCardType.getId())
                            .and("is_using = 1").orderBy("create_time")
            );
            frCardType.setFrCardTypeList(list1);
        }
        return JsonResult.success(list);
    }

    /**
     * 通用门店修改卡系列与卡种
     *
     * @param frCardType
     * @return
     */
    @Override
    public JsonResult generalStoreUpdate(FrCardType frCardType) {
        frCardType.setUpdateTime(new Date());
        Integer code = frCardTypeMapper.updateById(frCardType);
        if (code < 1) {
            return JsonResult.failMessage("修改失败");
        }
        return JsonResult.success();
    }

    /**
     * 查询卡系列、卡种详情
     *
     * @param id
     * @return
     */
    @Override
    public JsonResult getFrCardTypeDetails(String id) {
        FrCardType frCardType = frCardTypeMapper.selectById(id);
        return JsonResult.success(frCardType);
    }

    /**
     * 查询卡种操作的最后一次的时间
     *
     * @param type
     * @return
     */
    @Override
    public JsonResult selectFrCardTypeTime(Integer type, String code) {
        String co;
        if (type == 0) {//添加操作为0，修改操作为1
            co = "create_time DESC";
        } else {
            co = "update_time DESC";
        }

        List<FrCardType> list = frCardTypeMapper.selectPage(
                new Page<FrCardType>(0, 1),
                new EntityWrapper().where("p_id != '0'").and("CustomerCode = {0}", code).orderBy(co)
        );
        FrCardType frCardType = new FrCardType();
        if (list != null && list.size() > 0) {
            frCardType = list.get(0);
        }
        return JsonResult.success(frCardType);
    }

    /**
     * 根据pid获取卡系列
     *
     * @param pId
     * @return
     */
    @Override
    public JsonResult getFrCardTypeByPid(String pId, String storeId) {
        FrCardType frCardType = new FrCardType();
        frCardType.setId(pId);
        if (!StringUtils.isEmpty(storeId)) {
            frCardType.setShopId(storeId);
        }
        FrCardType frCardType1 = frCardTypeMapper.selectOne(frCardType);
        return JsonResult.success(frCardType1);
    }

    /**
     * 前台不显示设置（修改卡系列或卡种的is_using状态）
     *
     * @param ids
     * @param show
     * @return
     */
    @Override
    public JsonResult updateIsUsing(String[] ids, FrCardType frCardType, Boolean show) {
        frCardType.setUpdateTime(new Date());
        frCardType.setUsing(show);
        try {
            for (int i = 0; i < ids.length; i++) {
                //查询不显示的是卡种还是卡系列，如是卡系列，它底下所有卡种都变不显示，如是卡种则就卡种不显示
                FrCardType frCardType1 = frCardTypeMapper.selectById(ids[i]);
                if ("0".equals(frCardType1.getpId())) {
                    //如果是卡系列，，则判断是否拥有卡种
                    List lis = frCardTypeMapper.selectList(
                            new EntityWrapper<FrCardType>()
                                    .where("p_id = {0}", frCardType1.getId())
                    );
                    if (lis != null && lis.size() > 0) {
                        //修改卡系列底下卡种状态
                        int c = frCardTypeMapper.update(
                                frCardType,
                                new EntityWrapper<FrCardType>().where("p_id={0}", ids[i])
                        );
                        if (c < 1) {
                            return JsonResult.failMessage("网络繁忙，请稍后再试");
                        }
                    }
                }
                int code = frCardTypeMapper.update(
                        frCardType,
                        new EntityWrapper<FrCardType>().where("id = {0}", ids[i])
                );
                if (code < 1) {
                    return JsonResult.failMessage("网络繁忙，请稍后再试");
                }

            }

        } catch (Exception e) {
            return JsonResult.failMessage("出现异常");
        }
        return JsonResult.success();
    }

    /**
     * 前台获取不显示的通用门店
     *
     * @return
     */
    @Override
    public JsonResult getGeneralStoreNotList(String code) {
        //获取所有卡系列
        List<FrCardType> list = frCardTypeMapper.selectList(
                new EntityWrapper<FrCardType>()
                        .where("p_id = '0'")
                        .and("type_set_state = 1")
                        .and("CustomerCode = {0}", code)
        );
        for (FrCardType frCardType : list) {
            //查询该卡系列下的所以卡种
            List<FrCardType> list1 = frCardTypeMapper.selectList(
                    new EntityWrapper<FrCardType>()
                            .where("p_id = {0}", frCardType.getId())
                            .and("is_using = 0")
            );
            if (list1 == null || list1.size() < 1) {
                //flag=1为不显示
                frCardType.setFlag("1");
                if (frCardType.getUsing() == false) {
                    frCardType.setFlag("");
                }
            }
            frCardType.setFrCardTypeList(list1);
        }
        return JsonResult.success(list);
    }

    @Override
    public List<FrCardType> queryByShopIdList(String shopId, String CustomerCode, Integer type, Integer typeSetState) throws YJException {
        if (StringUtils.isEmpty(CustomerCode) || StringUtils.isEmpty(shopId) || type == null) {
            throw new YJException(YJExceptionEnum.REQUEST_NULL);
        }
        List<String> shopIdList = new ArrayList<>();
        shopIdList.add(shopId);
        Map<String, Object> map = new HashMap<>();
        map.put("CustomerCode", CustomerCode);
        map.put("shopIdList", shopIdList);
        if (typeSetState != null) {
            map.put("typeSetState", typeSetState);
        }
        if (type > 0) {
            map.put("type", type);
        }
        return baseMapper.queryByShopIdList(map);
    }


    @Override
    public FrCardType queryByCardId(String cardId, String CustomerCode) throws YJException {
        if (StringUtils.isEmpty(cardId) || StringUtils.isEmpty(CustomerCode)) {
            throw new YJException(YJExceptionEnum.REQUEST_NULL);
        }
        return baseMapper.queryByCardId(cardId, CustomerCode);
    }


    @Override
    public List<Map<String, Object>> getCardTypeByShopIdList(String code) throws YJException {
        if (StringUtils.isEmpty(code)) {
            throw new YJException(YJExceptionEnum.REQUEST_NULL);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("CustomerCode", code);
        List<Map<String, Object>> listMap = new ArrayList<>();
        List<Shop> shopList = iShopService.selectList(new EntityWrapper<Shop>().where("CustomerCode={0}", code));
        List<Map<String, Object>> list = baseMapper.getCardTypeByShopIdList(map);
        List<Map<String, String>> cityL = new ArrayList<>();
        boolean isFlag = false;
        //初始化城市
        StringBuffer cityLT = new StringBuffer();
        if (shopList != null && shopList.size() > 0) {
            if (shopList != null && shopList.size() > 0) {
                for (int i = 0; i < shopList.size(); i++) {
                    isFlag = cityLT.toString().indexOf(shopList.get(i).getCityId()) != -1;
                    if (!isFlag) {
                        cityLT.append(shopList.get(i).getCityId());
                        Map<String, String> map1 = new HashMap<>();
                        map1.put("cityId", shopList.get(i).getCityId());
                        map1.put("cityName", shopList.get(i).getCityName());
                        cityL.add(map1);
                        //0代表城市
                        this.getMapInfo(listMap, shopList.get(i).getId(), "-1", shopList.get(i).getCityName(), false, 0, false, 0.0, 0.0);
                    }
                }
            }
        }
        List<Shop> disShop = new ArrayList<>();
        //初始化门店
        StringBuffer shops = new StringBuffer();
        if (shopList != null && shopList.size() > 0) {
            for (int i = 0; i < shopList.size(); i++) {
                isFlag = shops.toString().indexOf(shopList.get(i).getId()) != -1;
                if (!isFlag) {
                    shops.append(shopList.get(i).getId());
                    disShop.add(shopList.get(i));
                    String shopName = shopList.get(i).getShopName();
                    String id = shopList.get(i).getId();
                    String cityId = shopList.get(i).getCityId();
                    // 1代表门店
                    this.getMapInfo(listMap, id, cityId, shopName, false, 1, false, 0.0, 0.0);
                    // 所有会员卡
                    this.getMapInfo(listMap, UUIDUtils.generateGUID(), id, "所有会员卡", true, 2, true, 0.0, 0.0);
                }
            }
        }
        StringBuffer cardFlagL = new StringBuffer();
        //初始化卡系列
        if (disShop != null && disShop.size() > 0) {
            for (int i = 0; i < disShop.size(); i++) {
                if (list != null && list.size() > 0) {
                    for (Map<String, Object> map1 : list) {
                        isFlag = cardFlagL.toString().indexOf(StringUtils.getStringObject("cardFlagId", map1)) != -1;
                        if (!isFlag) {
                            String cityName = StringUtils.getStringObject("CityName", map1);
                            String shopName = StringUtils.getStringObject("ShopName", map1);
                            String id = StringUtils.getStringObject("id", map1);
                            String cardFlag = StringUtils.getStringObject("cardFlag", map1);
                            String cardFlagId = StringUtils.getStringObject("cardFlagId", map1);

                            cardFlagL.append(cardFlagId);
                            //  2代表系列
                            this.getMapInfo(listMap, cardFlagId, id, cardFlag, false, 2, false, 0.0, 0.0);
                            this.getMapInfo(listMap, "Fal-1", cardFlagId, "选择全部", true, 3, true, 0.0, 0.0);
                            for (Map<String, Object> map2 : list) {
                                if (cardFlagId.equals(StringUtils.getStringObject("cardFlagId", map2))) {
                                    Double originalPrice = NumberUtilsTwo.getDouNum("originalPrice", map2);
                                    Double salesPrice = NumberUtilsTwo.getDouNum("salesPrice", map2);
                                    String cardTypeId = StringUtils.getStringObject("cardTypeId", map2);
                                    String cardTypeName = StringUtils.getStringObject("cardTypeName", map2);
                                    //  3代表具体产品
                                    this.getMapInfo(listMap, cardTypeId, cardFlagId, cardTypeName, true, 3, false, originalPrice, salesPrice);
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println(listMap);
        return listMap;
    }

    /**
     * 初始化参数格式
     *
     * @param list
     * @param id
     * @param ParentId
     * @param NavName
     * @param IsCheckbox
     * @param Type
     * @param IsChoiceAll
     * @param MarketPrice
     * @param SalePrice
     */
    public void getMapInfo(List<Map<String, Object>> list, String id, String ParentId,
                           String NavName, boolean IsCheckbox, Integer Type, boolean IsChoiceAll,
                           Double MarketPrice, Double SalePrice) {
        Map<String, Object> map1 = new HashMap<>();
        map1.put("ID", id);
        map1.put("ParentId", ParentId);
        map1.put("NavName", NavName);
        map1.put("IsCheckbox", IsCheckbox);
        map1.put("Type", Type);
        map1.put("IsChoiceAll", IsChoiceAll);
        map1.put("MarketPrice", MarketPrice);
        map1.put("SalePrice", SalePrice);
        if (list != null) {
            list.add(map1);
        }
    }

}
