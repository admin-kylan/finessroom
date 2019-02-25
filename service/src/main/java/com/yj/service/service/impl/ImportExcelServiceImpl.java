package com.yj.service.service.impl;

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

    @Autowired
    FrCardMapper frCardMapper;

    @Autowired
    FrCardTypeMapper frCardTypeMapper;

    @Autowired
    FrCardOrderStopMapper frCardOrderStopMapper;

    @Autowired
    SdaduimMapper sdaduimMapper;

    @Autowired
    FrClientPersonnelRelateMapper frClientPersonnelRelateMapper;

    @Autowired
    AddProjectMapper addProjectMapper;

    @Autowired
    ProjectOrderMapper projectOrderMapper;

    @Autowired
    FrPrivateCourceMapper frPrivateCourceMapper;


    @Override
    public JsonResult clientUpload(List<Map<String, Object>> list) {
        if (list == null && list.size() > 0) {
            return JsonResult.successMessage("Excel数据为空");
        }
        for (int i = 0; i < list.size(); i++ ){
            {
                Map<String, Object> map = list.get(i);
                //查询用户是否重复
                Map<String,Object> clientMap = new HashMap<>();
                clientMap.put("mobile",map.get("mobile"));
                FrClient frClient = BeanUtil.toBean(FrClient.class,clientMap);
                FrClient fc = frClientMapper.selectByMobile(frClient.getMobile());
                int number = i + 1;
                if(fc != null){
                    return JsonResult.failMessage("第"+number+"条客户信息已存在，请检查之后再重新操作");
                }
                //查询门店是否存在
                String shopName = (String) map.get("shopName");
                Shop shop = shopMapper.selectbyShopName(shopName);
                if(shop == null ){
                    return JsonResult.failMessage("第"+number+"条数据对应门店信息不存在，请检查之后再重新操作");
                }
                //查询员工是否存在
                String salespersonName = (String)map.get("salespersonName");
                PersonnelInfo personnelInfo =  personnelInfoMapper.selectBySalespersonName(salespersonName);
                if(personnelInfo == null ){
                    return JsonResult.failMessage("第"+number+"条数据对应员工信息不存在，请检查之后再重新操作");
                }
                //根据会员卡名称跟门店id 查询卡类别表
                Map<String,Object> cardCardTypeMap = new HashMap();
                cardCardTypeMap.put("cardTypeName",map.get("cardTypeName"));
                cardCardTypeMap.put("shopId",shop.getId());
                String cardTypeName = (String)cardCardTypeMap.get("cardTypeName");
                FrCardType frCardType =  frCardTypeMapper.selectByCardTypeName(cardCardTypeMap);
                if(frCardType == null){
                    FrCardType frCardType1 =  frCardTypeMapper.selectByShopId(cardTypeName);
                    if(frCardType1 == null){
                        return JsonResult.failMessage("第"+number+"条数据对应会员卡信息不存在，请检查之后再重新操作");
                    }
                }
                //根据等级名称查询等级信息表
                String levelName = (String)map.get("level");
                if(StringUtils.isNotEmpty(levelName)){
                    FrLevel frLevel = frLevelMapper.selectByLevelName(levelName);
                    if(frLevel == null){
                        return JsonResult.failMessage("第"+number+"条数据对应会员卡等级信息不存在，请检查之后再重新操作");
                    }
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


                //添加到客户表
                //创建一个属于客户信息的map 吧数据放到对应的map 进行转换
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
                //map转实体类
                FrClient frClient = BeanUtil.toBean(FrClient.class,clientMap);
                //根据会员等级查询会员表获取会员id
                String levelName = (String)map.get("level");
                if(StringUtils.isNotEmpty(levelName)){
                    FrLevel frLevel = frLevelMapper.selectByLevelName(levelName);
                    frClient.setLevelId(frLevel.getId());
                }
                //添加fr_client 客户表（）
                frClient.setId(UUIDUtils.generateGUID());
                frClient.setUpdateTime(new Date());
                frClient.setUsing(true);
                frClientMapper.insert(frClient);


                //添加会员卡信息
                //创建一个属于会员卡的map 把数据放到对应的map 进行转换
                Map<String,Object> cardMap = new HashMap<>();
                cardMap.put("cardNo",map.get("cardNo"));
                cardMap.put("status",map.get("cardStatus"));
                cardMap.put("bindTime",map.get("bindTime"));
                cardMap.put("invalidTime",map.get("invalidTime"));
                cardMap.put("haveRightsNum",map.get("haveRightsNum"));
                //map转实体类
                FrCard frCard = BeanUtil.toBean(FrCard.class,cardMap);
                //根据会员卡名称跟门店id 查询卡类别表绑定
                Map<String,Object> cardCardTypeMap = new HashMap();
                cardCardTypeMap.put("cardTypeName",map.get("cardTypeName"));
                cardCardTypeMap.put("shopId",shop.getId());
                String cardTypeName = (String)cardCardTypeMap.get("cardTypeName");
                FrCardType frCardType =  frCardTypeMapper.selectByCardTypeName(cardCardTypeMap);
                FrCardType frCardType1 =  frCardTypeMapper.selectByShopId(cardTypeName);
                frCard.setId(UUIDUtils.generateGUID());
                frCard.setClientId(frClient.getId());
                frCard.setShopId(shop.getId());
                frCard.setUsing(true);
                if(frCardType != null){
                    frCard.setCardTypeId(frCardType.getId());
                }else{
                    frCard.setCardTypeId(frCardType1.getId());
                }
                frCardMapper.insert(frCard);


                //添加现有客户关系表 到fr_client_personal（员工id 、门店id）
                FrClientPersonal frClientPersonal = new FrClientPersonal();
                frClientPersonal.setId(UUIDUtils.generateGUID());
                frClientPersonal.setClientId(frClient.getId());
                frClientPersonal.setShopId(shop.getId());
                frClientPersonal.setPersonalId(personnelInfo.getId());
                frClientPersonalMapper.insert(frClientPersonal);

                //设置fr_card_order_info表   fr_card_order_complement



                //停卡/冻结表
                Map<String,Object> cardOrderStopMap = new HashMap();
                cardOrderStopMap.put("startTime",map.get("startTime"));
                cardOrderStopMap.put("estEndTime",map.get("estEndTime"));
                cardOrderStopMap.put("totalPrice",map.get("totalPrice"));
                cardOrderStopMap.put("flag",map.get("flag"));
                //map转实体
                FrCardOrderStop frCardOrderStop = BeanUtil.toBean(FrCardOrderStop.class,cardOrderStopMap);
                if(frCardOrderStop != null){
                   frCardOrderStop.setId(UUIDUtils.generateGUID());
                   frCardOrderStop.setCardId(frCard.getId());
                   frCardOrderStopMapper.insert(frCardOrderStop);
               }


            }
        }

        return JsonResult.successMessage("导入成功");
    }

    @Override
    public JsonResult prospectiveClient(List<Map<String, Object>> list) {
        if (list == null && list.size() > 0) {
            return JsonResult.successMessage("Excel数据为空");
        }
        for(int i= 0; i < list.size(); i++){
            Map<String, Object> map = list.get(i);
            //查询用户是否重复
            FrClient frClient = BeanUtil.toBean(FrClient.class,map);
            FrClient fc = frClientMapper.selectByMobile(frClient.getMobile());
            int number = i + 1;
            if(fc != null){
                return JsonResult.failMessage("第"+number+"条客户信息已存在，请检查之后再重新操作");
            }
            //查询门店是否存在
            String shopName = (String) map.get("shopName");
            Shop shop = shopMapper.selectbyShopName(shopName);
            if(shop == null ){
                return JsonResult.failMessage("第"+number+"条数据对应门店不存在，请检查之后再重新操作");
            }
            //查询员工是否存在
            String salespersonName = (String)map.get("salespersonName");
            PersonnelInfo personnelInfo =  personnelInfoMapper.selectBySalespersonName(salespersonName);
            if(personnelInfo == null ){
                return JsonResult.failMessage("第"+number+"条数据对应员工不存在，请检查之后再重新操作");
            }

        }

        for (int i = 0; i < list.size(); i++) {
            //map转实体类
            Map<String, Object> map = list.get(i);
            FrClient frClient = BeanUtil.toBean(FrClient.class,map);
            frClient.setId(UUIDUtils.generateGUID());
            frClient.setUpdateTime(new Date());
            frClient.setUsing(true);
            //根据员工姓名查询员工表获取员工id
            String salespersonName = (String)map.get("salespersonName");
            PersonnelInfo personnelInfo =  personnelInfoMapper.selectBySalespersonName(salespersonName);
            //根据门店名称查出门店表对应id
            String shopName = (String) map.get("shopName");
            Shop shop = shopMapper.selectbyShopName(shopName);
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

    @Override
    public JsonResult personalTrainer(List<Map<String, Object>> list) {
        if (list == null && list.size() > 0) {
            return JsonResult.successMessage("Excel数据为空");
        }
        for(int i= 0; i < list.size(); i++){
            Map<String, Object> map = list.get(i);
            //查询用户是否重复
            Map<String,Object> clientMap = new HashMap<>();
            clientMap.put("mobile",map.get("mobile"));
            FrClient frClient = BeanUtil.toBean(FrClient.class,clientMap);
            FrClient fc = frClientMapper.selectByMobile(frClient.getMobile());
            int number = i + 1;
            if(fc == null){
                return JsonResult.failMessage("第"+number+"条客户信息不已存在，请检查之后再重新操作");
            }
            //查询门店是否存在
            String shopName = (String) map.get("shopName");
            Shop shop = shopMapper.selectbyShopName(shopName);
            if(shop == null ){
                return JsonResult.failMessage("第"+number+"条数据对应门店不存在，请检查之后再重新操作");
            }
            //查询员工是否存在
            String salespersonName = (String)map.get("SaleName");
            PersonnelInfo personnelInfo =  personnelInfoMapper.selectBySalespersonName(salespersonName);
            if(personnelInfo == null ){
                return JsonResult.failMessage("第"+number+"条数据对应销售员不存在，请检查之后再重新操作");
            }
            //查询上课教练是否存在
            String theInstructor = (String)map.get("theInstructor");
            PersonnelInfo personnelInfo1 =  personnelInfoMapper.selectBySalespersonName(theInstructor);
            if(personnelInfo1 == null ){
                return JsonResult.failMessage("第"+number+"条数据对应上课教练不存在，请检查之后再重新操作");
            }
            //查询跟进教练是否存在
            String followUpInstructor = (String)map.get("followUpInstructor");
            PersonnelInfo personnelInfo2 =  personnelInfoMapper.selectBySalespersonName(followUpInstructor);
            if(personnelInfo2 == null ){
                return JsonResult.failMessage("第"+number+"条数据对应跟进教练不存在，请检查之后再重新操作");
            }
            //根据卡号查询会员卡信息是否存在
            String cardNo = (String)map.get("cardNo");
            FrCard frCard = frCardMapper.selectByCardNo(cardNo);
            if(frCard == null){
                return JsonResult.failMessage("第"+number+"条数据对应会员卡不存在，请检查之后再重新操作");
            }

            //根据场馆名查出场馆是否存在
            String sdadiumName = (String)map.get("SdadiumId");
            Sdaduim sdaduim = sdaduimMapper.selectBySdadiumName(sdadiumName);
            if(sdaduim == null){
                return JsonResult.failMessage("第"+number+"条数据对应场馆不存在，请检查之后再重新操作");
            }

            //根据项目名称查询项目是否存在
            String project = (String)map.get("project");
            FrPrivateCource frPrivateCource = frPrivateCourceMapper.selectByProject(project);
            if(frPrivateCource == null){
                return JsonResult.failMessage("第"+number+"条数据对应项目不存在，请检查之后再重新操作");
            }
        }
        for(int i= 0; i < list.size(); i++){
            Map<String, Object> map = list.get(i);

            //根据手机查询客户信息
            String mobile = (String)map.get("mobile");
            FrClient frClient = frClientMapper.selectByMobile(mobile);

            //根据销售员名称查出id
            String salespersonName = (String)map.get("SaleName");
            PersonnelInfo personnelInfo =  personnelInfoMapper.selectBySalespersonName(salespersonName);

            //查询上课教练是否存在
            String theInstructor = (String)map.get("theInstructor");
            PersonnelInfo personnelInfo1 =  personnelInfoMapper.selectBySalespersonName(theInstructor);

            //查询跟进教练是否存在
            String followUpInstructor = (String)map.get("followUpInstructor");
            PersonnelInfo personnelInfo2 =  personnelInfoMapper.selectBySalespersonName(followUpInstructor);

            //根据场馆名查出场馆id
            String sdadiumName = (String)map.get("SdadiumId");
            Sdaduim sdaduim = sdaduimMapper.selectBySdadiumName(sdadiumName);

            //查询门店是否存在
            String shopName = (String)map.get("shopName");
            Shop shop = shopMapper.selectbyShopName(shopName);

            //根据卡号查询会员卡信息
            String cardNo = (String)map.get("cardNo");
            FrCard frCard = frCardMapper.selectByCardNo(cardNo);

            //根据项目名称查询
            String project = (String)map.get("project");
            FrPrivateCource frPrivateCource = frPrivateCourceMapper.selectByProject(project);


            //设置添加ProjectOrder表
            Map<String,Object> projectOrderMap = new HashMap<>();
            projectOrderMap.put("BuyCount",map.get("BuyCount"));
            projectOrderMap.put("SendCount",map.get("SendCount"));
            projectOrderMap.put("AllCount",map.get("AllCount"));
            projectOrderMap.put("Price",map.get("Price"));
            projectOrderMap.put("PayCardPrice",map.get("PayCardPrice"));
            projectOrderMap.put("CashPrice",map.get("CashPrice"));
            projectOrderMap.put("WechatPrice",map.get("WechatPrice"));
            projectOrderMap.put("AlipayPrice",map.get("AlipayPrice"));
            ProjectOrder projectOrder = BeanUtil.toBean(ProjectOrder.class,projectOrderMap);
            projectOrder.setId(UUIDUtils.generateGUID());
            projectOrderMapper.insert(projectOrder);



            //设置添加AddProject表
            Map<String,Object> addProjectMap = new HashMap<>();
            addProjectMap.put("ContractNumber",map.get("ContractNumber"));
            addProjectMap.put("SaleType",map.get("SaleType"));
            addProjectMap.put("StartTime",map.get("StartTime"));
            addProjectMap.put("EndTime",map.get("EndTime"));
            addProjectMap.put("SaleName",map.get("SaleName"));
            AddProject addProject = BeanUtil.toBean(AddProject.class,addProjectMap);
            addProject.setId(UUIDUtils.generateGUID());
            addProject.setUseDay(new Date());
            addProject.setSaleId(personnelInfo.getId());
            addProject.setSdadiumId(sdaduim.getId());
            addProject.setShopId(shop.getId());
            addProject.setUseful(daysBetween(addProject.getEndTime(),addProject.getStartTime()));
            addProject.setProjectId(frPrivateCource.getId());
            addProject.setCourseId("1");
            addProject.setCourseSeriousId("1");
            addProject.setCardId(frCard.getId());
            addProjectMapper.insert(addProject);

            //跟进教练是放到员工与客户的关系表
            //跟进教练 设置fr_client_personnel_relate员工与客户的关系表
            FrClientPersonnelRelate frClientPersonnelRelate = new FrClientPersonnelRelate();
            frClientPersonnelRelate.setId(UUIDUtils.generateGUID());
            frClientPersonnelRelate.setShopId(shop.getId());
            frClientPersonnelRelate.setClientId(frClient.getId());
            frClientPersonnelRelate.setPersonalId(personnelInfo1.getId());
            frClientPersonnelRelateMapper.insert(frClientPersonnelRelate);
            //上课教练是放到课程表里面，排课的时候的教练
            //

        }
        return JsonResult.successMessage("导入成功");
    }

    /**
     * 计算两个日期之间相差的天数
     * @param date1
     * @param date2
     * @return
     */
    public static String daysBetween(Date date1,Date date2)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        long time1 = cal.getTimeInMillis();
        cal.setTime(date2);
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);
        return String.valueOf(between_days);
    }
}