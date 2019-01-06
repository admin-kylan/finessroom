package com.yj.service.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.sun.org.apache.xerces.internal.xs.ShortList;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.result.JsonResult;
import com.yj.common.util.StringUtils;
import com.yj.dal.dao.*;
import com.yj.dal.dto.ShopItemDTO;
import com.yj.dal.dto.ShopListDTO;
import com.yj.dal.model.*;
import com.yj.common.exception.YJException;
import com.yj.service.service.IFrStoreService;
import com.yj.service.service.IShopService;
import com.yj.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 * 门店表 服务实现类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-25
 */
@Service
public class ShopServiceImpl extends BaseServiceImpl<ShopMapper, Shop> implements IShopService {

    @Autowired
    IFrStoreService frStoreService;

	@Resource
    private ShopMapper shopMapper;

    @Resource
    private FrCardTypeMapper frCardTypeMapper;

    @Resource
    private FrShopCardTypeRelateMapper frShopCardTypeRelateMapper;

    @Resource
    private FrShopSdaduimRelateMapper frShopSdaduimRelateMapper;//门店关联的场馆
    @Resource
    private SdaduimMapper sdaduimMapper;//场馆


    /**
     * 获取所有门店及其下卡系列与卡种
     *
     * @return
     */
    @Override
    public JsonResult getFrCardType(String code) {
        List<Shop> list = shopMapper.getFrCardType(code);
        for(Shop shop:list){
            //该门店下的所有卡系列
            List<FrCardType> list1 = shop.getFrCardTypeList();
            if(list1 != null && list1.size() == 0){
                shop.setFlag("1");
            }
            for(FrCardType frCardType : list1){
                //查询该卡系列下的所以卡种
                List<FrCardType> list2 = frCardTypeMapper.selectList(
                        new EntityWrapper<FrCardType>()
                                .where("p_id = {0}",frCardType.getId())
                                .and("is_using = 1")
                                .and("CustomerCode = {0}",code)
                );
                frCardType.setFrCardTypeList(list2);
            }
        }
        return JsonResult.success(list);
    }


    /**
     * 获取门店列表
     *
     * @return
     */
    @Override
    public JsonResult getStoreList(Integer type,String code) {

        List<Shop> list = baseMapper.selectList(
                new EntityWrapper<Shop>().where("CustomerCode = {0}",code).orderBy("CityId")
        );
        //城市id数组
        List id = new ArrayList();

        for(Shop shop : list){
            boolean b = true;
            if(id.size()<1){
                id.add(shop.getCityId());
            }
            if(id.size()>0){
                for(int i=0;i<id.size();i++){
                    if(shop.getCityId().equals(id.get(i))){
                        b = false;
                    }
                }
                if(b){
                    id.add(shop.getCityId());
                }
            }
        }
        Map map = new HashMap();
        List list2 = new ArrayList();
        for(int i=0;i<id.size();i++){
            List<Shop> list1 = baseMapper.selectList(
                    new EntityWrapper<Shop>().where("CityId={0}", id.get(i)).and("CustomerCode = {0}",code)
            );
            list2.add(list1);
        }
        map.put("list",list2);
        //最后一次修改卡的时间
        List li = new ArrayList();
        if(type == 0){//添加的操作时间
            li = frCardTypeMapper.selectList(
                    new EntityWrapper().where("p_id= '0'")
                            .and("CustomerCode = {0}",code).orderBy("create_time DESC")
            );
        }else {
            li = frShopCardTypeRelateMapper.selectPage(
                    new Page<FrCardType>(0,1),
                    new EntityWrapper().and("CustomerCode = {0}",code)
                            .orderBy("update_time DESC")
            );
        }

        if( li != null && li.size()>0){
            map.put("time",li.get(0));//最后一次修改卡的时间
        }else {
            map.put("time","");//最后一次修改卡的时间
        }
        return JsonResult.success(map);
    }



    /**
     * 根据客户代码查询所在门店列表(单个门店设置)
     *
     * @param customerCode
     */
    @Override
    public JsonResult<List<ShopListDTO>> selectShopList(String customerCode) {
        EntityWrapper entityWrapper = new EntityWrapper<>();
        entityWrapper.setSqlSelect("CityName");
        entityWrapper.where("CustomerCode = {0}",customerCode).and("Status = 0");
        entityWrapper.groupBy("CityName");
        //查询城市分组baseMapper.selectCityList(customerCode);
        List<Shop> cityList = baseMapper.selectList(entityWrapper);
        List<ShopListDTO> list = new ArrayList<>();
        for (int i = 0; i < cityList.size() ; i++) {
            Shop shop = cityList.get(i);
            ShopListDTO dto = new ShopListDTO();
            dto.setCityName(shop.getCityName());
            //关联查询fr_store
            dto.setList(baseMapper.selectShopItemList(customerCode,shop.getCityName()));
            list.add(dto);
        }
        return JsonResult.success(list);


    }

    /**
     * 修改所在门店设置
     *
     * @param list
     * @return
     * @throws YJException
     */
    @Override
    @Transactional
    public Boolean updateWhereShop(List<ShopListDTO> list) throws YJException {
        int size = list.size();
        if (size<1){
            throw new YJException(YJExceptionEnum.PARAM_ERROR);
        }
        Date now = new Date();
        Integer successCount = 0;
        for (int i = 0; i < size ; i++) {
            ShopListDTO shopListDTO =  list.get(i);
            List<ShopItemDTO> shopItemDTOS = shopListDTO.getList();
            for (int j = 0; j < shopItemDTOS.size() ; j++) {
                ShopItemDTO shopItemDTO = shopItemDTOS.get(j);
                FrStore frStore = new FrStore();
                frStore.setSingleStatus(shopItemDTO.getSingleStatus());

                EntityWrapper ew = new EntityWrapper<>();
                ew.where("shop_id = {0}",shopItemDTO.getId());

                boolean update = frStoreService.update(frStore, ew);
                if(update){
                    successCount++;
                }
            }
        }
        if(successCount>0){
            return true;
        }
        return false;
    }

    /*
     * @Author Sinyu
     * @Description  搜索门店关联的场馆
      * @param sids 门店id
     * @return
     **/
    @Override
    public JsonResult getShopSdaduimList(String[] sids) {
        List<Shop> shopList = new ArrayList<>();
        for (int i = 0; i < sids.length; i++) {
            //查场馆
//            List<Sdaduim> sdaduimArrayList = new ArrayList<>();
//                sdaduimArrayList = sdaduimMapper.selectList(new EntityWrapper<Sdaduim>().
//                        where("ShopId = {0}", sids[i]));
//                list.add(sdaduimArrayList);
           // System.out.println("sdaduimArrayListsdaduimArrayListsdaduimArrayList="+sdaduimArrayList);
            System.out.println(sids[i]);

            Shop shop = shopMapper.selectById(sids[i]);
            shop.setSdaduimArrayList(sdaduimMapper.selectList(new EntityWrapper<Sdaduim>().
                    where("ShopId = {0}", sids[i])));
            shopList.add(shop);
            System.out.println("shop="+shop);
        }
       // Map<Object,Object> map=new HashMap();

        System.out.println("shopList="+shopList);
       // System.out.println("list="+list);
//        map.put("shopList",shopList);//门店list
//        map.put("sdaduimList", list);
        return JsonResult.success(shopList);
    }


    /**
     * 获取所有门店（不排序）
     * @return
     */
    @Override
    public JsonResult getShopList(String code) {
        List<Shop> list = baseMapper.selectList(
                new EntityWrapper<Shop>().where("CustomerCode={0}",code).orderBy("CityId")
        );
        return JsonResult.success(list);
    }

    /**
     * 获取前台不显示列表
     * @return
     */
    @Override
    public JsonResult getIsUsingIsTrue(String code) {
        //先获取所有门店及卡系列
        List<Shop> list = shopMapper.getAllFrCardType(code);
        for(Shop shop:list){
            //该门店下的所有卡系列
            List<FrCardType> list1 = shop.getFrCardTypeList();
            if(list1 != null && list1.size() == 0){
                shop.setFlag("1");
            }
            for(FrCardType frCardType : list1){
                //查询该卡系列下的所以卡种
                List<FrCardType> list2 = frCardTypeMapper.selectList(
                        new EntityWrapper<FrCardType>()
                                .where("p_id = {0}",frCardType.getId())
                                .and("is_using = 0")
                );
                if(list2 == null || list2.size()<1){
                    //flag=1为不显示
                    frCardType.setFlag("1");
                    if(frCardType.getUsing() == false){
                        frCardType.setFlag("");
                    }
                }
                frCardType.setFrCardTypeList(list2);
            }
        }
        return JsonResult.success(list);
    }

    /**
     * 获取指定门店下的所有卡类型卡种
     * @param code 客户代码
     * @param shopId    门店id
     * @return
     */
    @Override
    public JsonResult getByShopIdList(String code, String shopId,Integer type) {
        //获取该门店下的所有卡种（不包括卡类型）
        Shop shop = shopMapper.getByShopIdList(code,shopId,type);
           if(shop != null){
               //该门店下的所有卡种
               List<FrCardType> list = shop.getFrCardTypeList();
               FrCardType fr = new FrCardType();
               for(FrCardType frCardType : list){
                   //查询该卡种的卡类型名字，赋值于flag供前端显示
                   fr.setId(frCardType.getpId());
                   FrCardType frCardType1 = frCardTypeMapper.selectOne(fr);
                   if(frCardType1!=null){
                       frCardType.setFlag(frCardType1.getCardTypeName());
                   }
               }
           }
        return JsonResult.success(shop);
    }

}
