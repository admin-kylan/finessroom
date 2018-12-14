package com.yj.service.service.impl;

import com.yj.common.result.JsonResult;
import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.dal.dao.FrCardTypeMapper;
import com.yj.dal.dao.FrShopCardTypeRelateMapper;
import com.yj.dal.dto.ShopListDTO;import com.yj.dal.dao.FrShopSdaduimRelateMapper;import com.yj.dal.dao.FrStoreMapper;
import com.yj.dal.model.FrStore;
import com.yj.service.service.IFrStoreService;
import com.yj.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 * 门店表 服务实现类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-04
 */
@Service
public class FrStoreServiceImpl extends BaseServiceImpl<FrStoreMapper, FrStore> implements IFrStoreService {

    @Resource
    private FrStoreMapper frStoreMapper;

    @Resource
    private FrCardTypeMapper frCardTypeMapper;

    @Resource
   private FrShopSdaduimRelateMapper  shopSdaduimRelateMapper;//

    @Override
    public List<ShopListDTO> selectStoreList() {
        List<ShopListDTO> listDTOS = new ArrayList<>();

        List<FrStore> list = baseMapper.selectListByGroupBy();
        for (int i = 0; i <list.size() ; i++) {
            ShopListDTO dto = new ShopListDTO();
            FrStore frStore = list.get(i);
            //dto.setCityId(frStore.getCityId());
            //dto.setCityName(frStore.getCityName());
            ////查询出这个城市下的所有门店
            //EntityWrapper<FrStore> ew = new EntityWrapper<>();
            //ew.where("is_using = {0}",1).and("city_name = {0}",frStore.getCityName());
            //List<FrStore> frStores1 = baseMapper.selectList(ew);
            //dto.setList(frStores1);
            listDTOS.add(dto);
        }
        return listDTOS;
    }




    @Override
    public Boolean updateList(List<ShopListDTO> dtos) throws YJException {
        int size = dtos.size();
        if (size<1){
            throw new YJException(YJExceptionEnum.PARAM_ERROR);
        }
        Date now = new Date();
        Integer successCount = 0;
        //for (int i = 0; i < size ; i++) {
        //    ShopListDTO dto = dtos.get(i);
            //List<FrStore> list = dto.getList();
            //for (int j = 0; j < list.size() ; j++) {
            //    FrStore frStore = list.get(j);
            //    frStore.setUpdateTime(now);
            //    successCount = successCount + baseMapper.updateById(frStore);
            //}
        //}
        if (successCount>0){
            return true;
        }
        return false;
    }

    //根据门店搜索可以使用 的场馆
    @Override
    public List getStoreCategoryList(Integer[] sid) {



        return null;
    }


}
