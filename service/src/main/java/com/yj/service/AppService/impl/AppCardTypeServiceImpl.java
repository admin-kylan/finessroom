package com.yj.service.AppService.impl;

import com.alibaba.fastjson.JSONObject;
import com.yj.common.result.JsonResult;
import com.yj.dal.dao.FrCardTypeMapper;
import com.yj.dal.dao.ShopMapper;
import com.yj.dal.model.FrCardType;
import com.yj.dal.model.Shop;
import com.yj.service.AppService.IAppCardTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class AppCardTypeServiceImpl implements IAppCardTypeService{

    @Resource
    private ShopMapper shopMapper;
    @Resource
    private FrCardTypeMapper frCardTypeMapper;

    @Override
    public JsonResult getGeneralStoreList(String shopId,String cityId,String customerCode,String cardTypeId,String cardTypeName) {
        List<JSONObject> list = new ArrayList<JSONObject>();//全部
        List<JSONObject> cityList = new ArrayList<JSONObject>();//城市 用以判断存储
        List<Shop> shopList = shopMapper.selectShopListByCity(shopId,cityId,customerCode);//门店
        if(shopList.size() == 0){
            return JsonResult.appSuccessMessage("无门店信息！");
        }
        if(shopId != null && shopId != ""){
            Shop s = shopList.get(0);
            JSONObject cityJson = getJson(s.getCityId(),"-1",s.getCityName(),0,null,null);//城市
            list.add(cityJson);//城市
            list.add(getJson(s.getId(),s.getCityId(),s.getShopName(),1,null,null));//门店
            List<FrCardType> cardTypeList = frCardTypeMapper.selectCardTypetByIdAndName(s.getId(),cardTypeId,cardTypeName,"0",null);
            if(cardTypeList != null && cardTypeList.size() != 0){
                Integer cTypeNum = frCardTypeMapper.selectCardTypetCount(s.getId(),cardTypeId,cardTypeName,"-1",null);//查询门店下属，是会员卡类型下属卡种的  数量
                if(cTypeNum != null && cTypeNum > 0 ){
                    list.add(getJson(s.getId()+"-1",s.getId(),"所有会员卡",2,null,null));//固定所有会员卡
                }
                for(FrCardType ct : cardTypeList){
                    list.add(getJson(ct.getId(),s.getId(),ct.getCardTypeName(),2,null,null));//会员卡类型
                    List<FrCardType> cTypeList = frCardTypeMapper.selectCardTypetByIdAndName(s.getId(),cardTypeId,cardTypeName,ct.getId(),null);
                    if(cTypeList != null && cTypeList.size() != 0){
                        list.add(getJson(ct.getId()+"-1",ct.getId(),"选择全部",3,null,null));//固定所有会员卡
                        for(FrCardType ctt : cTypeList){
                            list.add(getJson(ctt.getId(),ctt.getpId(),ctt.getCardTypeName(),3,ctt.getOriginalPrice(),ctt.getSalesPrice()));//会员卡类型下属卡种
                        }
                    }
                }
            }
        }else{
            for(Shop s :shopList){
                if(cityId != null && cityId != ""){
                    if(cityList.size() == 0){
                        JSONObject cityJson =getJson(s.getCityId(),"-1",s.getCityName(),0,null,null);
                        list.add(cityJson);//城市
                        cityList.add(cityJson);//城市
                    }
                }else{
                    if(cityList.size() == 0){
                        JSONObject cityJson =getJson(s.getCityId(),"-1",s.getCityName(),0,null,null);
                        list.add(cityJson);//城市
                        cityList.add(cityJson);//城市
                    }else{
                        int sign = 1;
                        for(JSONObject cj :cityList){
                            if(cj.get("ID").equals(s.getCityId())){
                                sign = 2;//城市数据已有
                                break;
                            }
                        }
                        if(sign == 1){
                            JSONObject cityJson =getJson(s.getCityId(),"-1",s.getCityName(),0,null,null);
                            list.add(cityJson);//城市
                            cityList.add(cityJson);//城市
                        }
                    }
                }
                list.add(getJson(s.getId(),s.getCityId(),s.getShopName(),1,null,null));//门店
                List<FrCardType> cardTypeList = frCardTypeMapper.selectCardTypetByIdAndName(s.getId(),cardTypeId,cardTypeName,"0",null);
                if(cardTypeList != null && cardTypeList.size() != 0){
                    Integer cTypeNum = frCardTypeMapper.selectCardTypetCount(s.getId(),cardTypeId,cardTypeName,"-1",null);//查询门店下属，是会员卡类型下属卡种的  数量
                    if(cTypeNum != null && cTypeNum > 0 ){
                        list.add(getJson(s.getId()+"-1",s.getId(),"所有会员卡",2,null,null));//固定所有会员卡
                    }
                    for(FrCardType ct : cardTypeList){
                        list.add(getJson(ct.getId(),s.getId(),ct.getCardTypeName(),2,null,null));//会员卡类型
                        List<FrCardType> cTypeList = frCardTypeMapper.selectCardTypetByIdAndName(s.getId(),cardTypeId,cardTypeName,ct.getId(),null);
                        if(cTypeList != null && cTypeList.size() != 0){
                            list.add(getJson(ct.getId()+"-1",ct.getId(),"选择全部",3,null,null));//固定所有会员卡
                            for(FrCardType ctt : cTypeList){
                                list.add(getJson(ctt.getId(),ctt.getpId(),ctt.getCardTypeName(),3,ctt.getOriginalPrice(),ctt.getSalesPrice()));//会员卡类型下属卡种
                            }
                        }
                    }
                }
            }
        }
        return JsonResult.appSuccess(list);
    }

    /**
     * 组装成json
     * @param id                    id
     * @param parentId              归属ID
     * @param navName               名称
     * @param type                  类型（0代表城市 1代表门店 2卡类型 3会员卡种）
     * @return
     */
    private JSONObject getJson(String id,String parentId,String navName,Integer type,Double originalPrice,Double salesPrice){
        JSONObject json  = new JSONObject();
        json.put("ID",id);
        json.put("ParentId",parentId);
        json.put("NavName",navName);
        if(type == 0 || type == 1){
            json.put("IsCheckbox",false);
        }else if(type == 2){//固定所有会员卡
            if((parentId+"-1").equals(id)){
                json.put("IsCheckbox",true);
            }else{
                json.put("IsCheckbox",false);
            }
        }else{
            json.put("IsCheckbox",true);
        }

        json.put("Type",type);
        if(type == 0 || type == 1){
            json.put("IsChoiceAll",false);
        }else if(type == 2){//固定所有会员卡
            if((parentId+"-1").equals(id)){
                json.put("IsChoiceAll",true);
            }else{
                json.put("IsChoiceAll",false);
            }
        }else{
            json.put("IsChoiceAll",true);
            if((parentId+"-1").equals(id)){
                json.put("IsChoiceAll",true);
            }else{
                json.put("IsChoiceAll",false);
            }
        }
        if(originalPrice != null){
            json.put("MarketPrice",originalPrice);
        }else{
            json.put("MarketPrice",0);
        }
        if(salesPrice != null){
            json.put("SalePrice",salesPrice);
        }else{
            json.put("SalePrice",0);
        }
        return json;
    }
}
