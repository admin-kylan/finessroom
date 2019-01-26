package com.yj.service.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yj.common.result.JsonResult;
import com.yj.common.util.BeanUtil;
import com.yj.common.util.StringUtils;
import com.yj.common.util.UUIDUtils;
import com.yj.dal.dao.*;
import com.yj.dal.model.*;
import com.yj.service.service.IImportExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ImportExcelServiceImpl implements IImportExcelService {

    @Autowired
    FrClientMapper frClientMapper;

    @Autowired
    ShopMapper shopMapper;

    @Autowired
    PersonnelInfoMapper personnelInfoMapper;

    @Autowired
    FrClientLatencePersonalMapper frClientLatencePersonalMapper;

    @Autowired
    FrLevelMapper frLevelMapper;

    @Autowired
    FrClientPersonalMapper frClientPersonalMapper;


    @Override
    public JsonResult clientUpload(List<Map<String, Object>> list) {
        if (list == null && list.size() > 0) {
            return JsonResult.successMessage("Excel数据为空");
        }
        for (int i = 0; i < list.size(); i++ ){
            {
                Map<String, Object> map = list.get(i);
                FrClient frClient = BeanUtil.toBean(FrClient.class,map);
                int a = frClientMapper.selectByMobile(frClient.getMobile());
                int number = i + 1;
                if(a != 0){
                    return JsonResult.failMessage("第"+number+"条数据已存在，请检查之后再重新操作");
                }
            }
        }
        for (int i = 0; i < list.size(); i++ ){
            {
                Map<String, Object> map = list.get(i);
                //根据门店名称查出门店表对应id
                String shopName = (String) map.get("shopName");
                Shop shop = shopMapper.selectbyShopName(shopName);
                //根据员工姓名查询员工表获取员工id（销售员）
                String salespersonName = (String)map.get("salespersonName");
                PersonnelInfo personnelInfo =  personnelInfoMapper.selectBySalespersonName(salespersonName);
                //根据会员等级查询会员表获取会员id
                String levelName = (String)map.get("level");
                //根据卡号查询会卡信息 绑定到fr_card
                String cardNo = (String) map.get("cardNo");

                Map<String,Object> clientMap = new HashMap<>();
                clientMap.put("clientName",map.get("clientName"));
                clientMap.put("sex",map.get("sex"));
                clientMap.put("birthday",map.get("birthday"));
                clientMap.put("mobile",map.get("mobile"));
                clientMap.put("consultantName",map.get("consultantName"));
                clientMap.put("fwhjName",map.get("fwhjName"));
                clientMap.put("createTime",map.get("createTime"));
                clientMap.put("createUserName",map.get("createUserName"));
                clientMap.put("note",map.get("note"));

                FrClient frClient = BeanUtil.toBean(FrClient.class,clientMap);

                if(StringUtils.isNotEmpty(levelName)){
                    FrLevel frLevel = frLevelMapper.selectByLevelName(levelName);
                    //根据卡号查询会卡信息 绑定到fr_card
                    frClient.setLevelId(frLevel.getId());
                }

                //添加fr_client 客户表（）
                frClientMapper.insert(frClient);

                //添加到fr_client_personal 现有客户关系表 （员工id 、门店id）
                FrClientPersonal frClientPersonal = new FrClientPersonal();
                frClientPersonal.setId(UUIDUtils.generateGUID());
                frClientPersonal.setClientId(frClient.getId());
                frClientPersonal.setShopId(shop.getId());
                frClientPersonal.setPersonalId(personnelInfo.getId());
                frClientPersonalMapper.insert(frClientPersonal);

            }
        }

        return null;
    }

    @Override
    public JsonResult prospectiveClient(List<Map<String, Object>> list) {
        if (list == null && list.size() > 0) {
            return JsonResult.successMessage("Excel数据为空");
        }
        for(int i= 0; i < list.size(); i++){
            Map<String, Object> map = list.get(i);
            FrClient frClient = BeanUtil.toBean(FrClient.class,map);
            int a = frClientMapper.selectByMobile(frClient.getMobile());
            int number = i + 1;
            if(a != 0){
                return JsonResult.failMessage("第"+number+"条数据已存在，请检查之后再重新操作");
            }
        }

        for (int i = 0; i < list.size(); i++) {
            //map转实体类
            Map<String, Object> map = list.get(i);
            FrClient frClient = BeanUtil.toBean(FrClient.class,map);
            frClient.setId(UUIDUtils.generateGUID());
            frClient.setUpdateTime(new Date());
            frClient.setUsing(true);
            //根据门店名称查出门店表对应id
            String shopName = (String) map.get("shopName");
            Shop shop = shopMapper.selectbyShopName(shopName);
            //根据员工姓名查询员工表获取员工id
            String salespersonName = (String)map.get("salespersonName");
            PersonnelInfo personnelInfo =  personnelInfoMapper.selectBySalespersonName(salespersonName);

            //设置潜在关系客户表
            FrClientLatencePersonal frClientLatencePersonal = new FrClientLatencePersonal();
            frClientLatencePersonal.setId(UUIDUtils.generateGUID());
            frClientLatencePersonal.setClientId(frClient.getId());
            frClientLatencePersonal.setShopId(shop.getId());
            frClientLatencePersonal.setPersonalId(personnelInfo.getId());

            frClientMapper.insert(frClient);
            frClientLatencePersonalMapper.insert(frClientLatencePersonal);
        }
        return JsonResult.successMessage("导入成功");

    }


}