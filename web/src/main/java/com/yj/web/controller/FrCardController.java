package com.yj.web.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.result.JsonResult;
import com.yj.common.util.CommonUtils;
import com.yj.common.util.CookieUtils;
import com.yj.common.util.PageUtil;
import com.yj.dal.model.*;
import com.yj.service.service.IFrCardOrderAllotSetService;
import com.yj.service.service.IFrCardOrderPayModeService;
import com.yj.service.service.IFrCardService;
import com.yj.service.service.IPersonnelInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 会员卡表 前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-04
 */
@RestController
@RequestMapping("/frCard")
public class FrCardController {

    @Autowired
    IFrCardService service;

    @Autowired
    IPersonnelInfoService iPersonnelInfoService;

    @Autowired
    IFrCardOrderPayModeService iFrCardOrderPayModeService;

    @Autowired
    IFrCardOrderAllotSetService iFrCardOrderAllotSetService;

    /**
     * @Description: 获取作废会员号列表
     * @Author: 欧俊俊
     * @Date: 2018/9/7 10:01
     */
    @GetMapping("/getInvalidCardNoList")
    public JsonResult invalidList(@RequestParam Map<String, Object> params, HttpServletRequest request) throws YJException {
        String code = CookieUtils.getCookieValue(request, "code", true);
        params.put("code",code);
        return JsonResult.success(service.queryPage(params));
    }


    /**
     * @Description: 增加作废会员号
     * @Author: 欧俊俊
     * @Date: 2018/9/10 18:14
     */
    @PostMapping("/postAddInvalidCardNo")
    public JsonResult addInvalidCardNo(@RequestBody FrCard frCard,HttpServletRequest request) throws YJException {
        String code = CookieUtils.getCookieValue(request, "code", true);
        if (StringUtils.isEmpty(code)) {
            throw new YJException(YJExceptionEnum.CUSTOMERCODE_NOT_FOUND);
        }
        boolean update = service.addInvalidCardNo(frCard,code);
        if(update){
            return JsonResult.successMessage("作废成功");
        }
        return JsonResult.failMessage("作废失败");
    }


    /**
     * @Description: 删除作废会员号
     * @Author: 欧俊俊
     * @Date: 2018/9/10 18:14
     */
    @PostMapping("/postDelInvalidCardNo")
    @Transactional
    public JsonResult delInvalidCardNo(@RequestBody FrCard frCard,HttpServletRequest request) throws YJException {
        String code = CookieUtils.getCookieValue(request, "code", true);
        if (StringUtils.isEmpty(code)) {
            throw new YJException(YJExceptionEnum.CUSTOMERCODE_NOT_FOUND);
        }
        frCard.setUsing(true);
        boolean update = service.updateById(frCard);
        if(update){
            return JsonResult.successMessage("删除成功");
        }
        return JsonResult.failMessage("删除失败");
    }

    // 加载会员卡权益
    @GetMapping("/getMemberCard")
    public JsonResult getMemberCard( @RequestParam String id) {
        return service.getMemberCard(id);
    }

    /**
     * @Author Sinyu
     * @Description 添加会员卡权益
     * @Param
     * @return
     **/
    @PostMapping("/addMemberCard")
    public JsonResult addMemberCard(@RequestBody FrCardType cardType) {
        //组装数据
        cardType.setUpdateTime(new Date());
        return service.addMemberCardType(cardType);
    }

    /**
     * 获取指定客户的会员卡信息（新）
     * @param pageUtil 分页类
     * @return
     */
    @GetMapping("/queryUserCardList")
    public JsonResult queryUserCardList(PageUtil<FrCard> pageUtil,HttpServletRequest request) throws YJException{
        this.toVerificationPage(pageUtil,request);
        return service.queryUserCardList(pageUtil);
    }


    /**
     * 根据会员卡信息获取单条数据 (新)
     * @param request
     * @param cardId 用户会员卡id
     * @return
     */
    @GetMapping("queryByCardIdInformation")
    public JsonResult queryByCardIdInformation(HttpServletRequest request,@RequestParam("cardId") String cardId,@RequestParam("code")String code)throws YJException{
        if(StringUtils.isEmpty(cardId)){
            throw  new YJException(YJExceptionEnum.VIPCARDNO_NOT_EXISTED);
        }
        if(StringUtils.isEmpty(code)){
            code = CookieUtils.getCookieValue(request, "code", true);
        }
        if(StringUtils.isEmpty(code)){
            throw  new YJException(YJExceptionEnum.CUSTOMERCODE_NOT_FOUND);
        }
        return service.queryUserCardOne(code,cardId);
    }


    /**
     * 保存会员卡失效时间
     * @param request
     * @return
     */
    @PostMapping("saveInvalidTime")
    public JsonResult saveInvalidTime(HttpServletRequest request,FrCard frCard)throws YJException{
        if(frCard == null){
            return JsonResult.failMessage("参数错误");
        }
        if(StringUtils.isEmpty(frCard.getCustomerCode())){
            String code = CookieUtils.getCookieValue(request, "code", true);
            if(StringUtils.isEmpty(code)){
                throw new YJException(YJExceptionEnum.CUSTOMERCODE_NOT_FOUND);
            }
            frCard.setCustomerCode(code);
        }
        if(StringUtils.isEmpty(frCard.getUpdateUserId())){
            String id = CookieUtils.getCookieValue(request, "id", true);
            if(StringUtils.isEmpty(id)){
                throw new YJException(YJExceptionEnum.PERSONNEL_ID_NOT_NULL);
            }
            frCard.setUpdateUserId(id);
        }
        if(StringUtils.isEmpty(frCard.getFlag())){
            return JsonResult.failMessage("设置失效原因需填写");
        }
        PersonnelInfo personnelInfo = iPersonnelInfoService.selectOne(
                new EntityWrapper<PersonnelInfo>().where("CustomerCode = {0}",frCard.getCustomerCode())
                        .and("ID={0}",frCard.getUpdateUserId()));
        if(personnelInfo == null || StringUtils.isEmpty(personnelInfo.getId())){
            throw new YJException(YJExceptionEnum.PERSONNEL_ID_NOT_FOUND);
        }
        return service.saveInvalidTime(frCard);
    }


    @PostMapping("queryByFrCardList")
    public JsonResult queryByFrCardList(PageUtil<FrCard> pageUtil,HttpServletRequest request)throws YJException{
        this.toVerificationPage(pageUtil,request);
        FrCard frCard  = new FrCard();
        frCard.setCustomerCode(pageUtil.getCode());
        frCard.setClientId(pageUtil.getClientId());
        frCard.setUsing(true);
        //随机定义的一个变量
        frCard.setStatus(100);
        pageUtil.setCondition(frCard);
        return service.queryUserCardList(pageUtil);
    }


    @PostMapping("queryByFrCardListByStatus")
    public JsonResult queryByFrCardListByStatus(PageUtil<FrCard> pageUtil,HttpServletRequest request)throws YJException{
        this.toVerificationPage(pageUtil,request);
        FrCard frCard  = new FrCard();
        frCard.setCustomerCode(pageUtil.getCode());
        frCard.setClientId(pageUtil.getClientId());
        frCard.setUsing(true);
        //随机定义的一个变量
        frCard.setStatus(CommonUtils.CARD_STATUS_0);
        pageUtil.setCondition(frCard);
        return service.queryUserCardList(pageUtil);
    }


    /**
     * 判断客户代码是否为空，若为空尝试cookie获取
     * @param code
     * @param request
     * @throws YJException
     */
    public String getFlageParemt(String code,HttpServletRequest request)throws YJException{
        if(StringUtils.isEmpty(code)){
            code = CookieUtils.getCookieValue(request, "code", true);
        }
        if(StringUtils.isEmpty(code)){
            throw  new YJException(YJExceptionEnum.CUSTOMERCODE_NOT_FOUND);
        }
        return code;
    }

    /**
     * 添加会员卡信息
     * @param map
     * @param request
     * @return
     * @throws YJException
     */
    @PostMapping("addCardDataList")
    public JsonResult addCardDataList(@RequestBody Map<String,String> map,HttpServletRequest request)throws YJException{
        if(map == null ){
            throw  new YJException(YJExceptionEnum.OBJECT_NOT_FOUND);
        }
        //接收json格式的字符串
        // 协议号规则
        String agreementMap = map.get("agreementMap");
        // 会员卡
        String cardMap = map.get("cardMap");
        // 会员卡订单
        String cardInfoMap = map.get("cardInfoMap");
        // 支付
        String payModel = map.get("payModel");
        // 分期
        String orderSplitId = map.get("orderSplitId");
        //业绩分配
        String orderAllotSetList = map.get("orderAllotSetList");
        //业绩分配
        String orderAllotSet = map.get("orderAllotSet");

        if(StringUtils.isEmpty(agreementMap) || StringUtils.isEmpty(cardMap) || StringUtils.isEmpty(cardInfoMap) || StringUtils.isEmpty(payModel)){
            throw new  YJException(YJExceptionEnum.PARAM_ERROR);
        }
        //业绩分配
        List<FrCardOrderAllotSet> frCardOrderAllotSetList = iFrCardOrderAllotSetService.initFrCardOrderAllotSetData(orderAllotSetList,orderAllotSet,1);
        //会员卡规则
        FrCardAgreement frCardAgreement =  JSONObject.parseObject(agreementMap,FrCardAgreement.class);
        //会员卡
        FrCard frCard  = JSONObject.parseObject(cardMap,FrCard.class);
        //会员卡订单
        FrCardOrderInfo frCardOrderInfo  = JSONObject.parseObject(cardInfoMap,FrCardOrderInfo.class);
        //初始化支付信息
        Map<String,Object> payM = JSONObject.parseObject(payModel,Map.class);
        List<FrCardOrderPayMode>  frCardOrderPayModes = iFrCardOrderPayModeService.getPayModeList(payM,"L",1);
        if(frCard == null){
            throw new  YJException(YJExceptionEnum.OBJECT_NOT_FOUND);
        }
        if(StringUtils.isEmpty(frCard.getCustomerCode())){
            String code = CookieUtils.getCookieValue(request,"code",true);
            frCard.setCustomerCode(code);
        }
        Map<String,String> mapS = new HashMap<>();
        mapS.put("orderSplitId",orderSplitId);
        mapS.put("messFlag","新购会员卡订单");
        Map<String,Integer> mapI = new HashMap<>();
        mapI.put("infoType",CommonUtils.CARD_ORDRE_INFO_TYPE_1);
        return  service.toAddFrCard(frCardAgreement,frCard,frCardOrderInfo,frCardOrderPayModes,frCardOrderAllotSetList,mapS,mapI);
    }

    @GetMapping("getCardInformation")
    public JsonResult getCardInformation(String cid)throws YJException{
        return JsonResult.success(service.getCardInformation(cid));
    }

    /**
     * 新建现有客户
     * @param map
     * @param request
     * @return
     * @throws YJException
     */
    @PostMapping("addSaveCustomer")
    public JsonResult addSaveCustomer(@RequestBody Map<String,String> map,HttpServletRequest request)throws YJException{
        if(map == null ){
            throw  new YJException(YJExceptionEnum.OBJECT_NOT_FOUND);
        }
        //会员信息
        String client = map.get("client");
        // 会员卡
        String cardMap = map.get("cardMap");
        // 会员卡订单
        String cardInfoMap = map.get("cardInfoMap");
        // 支付
        String payModel = map.get("payModel");
        // 分期
        String orderSplitId = map.get("orderSplitId");
        //业绩分配
        String orderAllotSetList = map.get("orderAllotSetList");
        //业绩分配
        String orderAllotSet = map.get("orderAllotSet");
        //客户头像
        String userURL = map.get("userURL");
        //当前操作的门店id
        String shopId = map.get("shopId");
        if(StringUtils.isEmpty(shopId)){
            shopId = CookieUtils.getCookieValue(request,"shopid",true);
            if(StringUtils.isEmpty(shopId)){
               return  JsonResult.failMessage("未获取当前操作的门店id");
            }
        }

        if(StringUtils.isEmpty(client) || StringUtils.isEmpty(cardMap) || StringUtils.isEmpty(cardInfoMap) || StringUtils.isEmpty(payModel)){
            throw new  YJException(YJExceptionEnum.PARAM_ERROR);
        }
        //客户信息
        FrClient frClient =  JSONObject.parseObject(client,FrClient.class);
        //会员卡
        FrCard frCard  = JSONObject.parseObject(cardMap,FrCard.class);
        //业绩分配
        List<FrCardOrderAllotSet> frCardOrderAllotSetList = iFrCardOrderAllotSetService.initFrCardOrderAllotSetData(orderAllotSetList,orderAllotSet,1);
        //会员卡订单
        FrCardOrderInfo frCardOrderInfo  = JSONObject.parseObject(cardInfoMap,FrCardOrderInfo.class);
        //初始化支付信息
        Map<String,Object> payM = JSONObject.parseObject(payModel,Map.class);
        List<FrCardOrderPayMode> frCardOrderPayModes = iFrCardOrderPayModeService.getPayModeList(payM,"L", CommonUtils.PAY_MODE_ORDER_TYPE_2);
        if(frCard == null){
            throw new  YJException(YJExceptionEnum.OBJECT_NOT_FOUND);
        }
        if(StringUtils.isEmpty(frCard.getCustomerCode())){
            String code = CookieUtils.getCookieValue(request,"code",true);
            frCard.setCustomerCode(code);
        }
        Map<String,String> mapS = new HashMap<>();
        mapS.put("orderSplitId",orderSplitId);
        mapS.put("messFlag","新建现有客户");
        mapS.put("shopId",shopId);
        //设置头像路径
        if(!StringUtils.isEmpty(userURL)){
            StringBuffer imagePath = new StringBuffer(CookieUtils.getCookieValue(request, "url", true)) ;
            imagePath.append(CookieUtils.getCookieValue(request, "imgPath", true));
            imagePath.append(userURL);
            //客户头像路径
            mapS.put("userURL",imagePath.toString());
        }
        Map<String,Integer> mapI = new HashMap<>();
        mapI.put("infoType",CommonUtils.CARD_ORDRE_INFO_TYPE_1);
        return  service.addSaveCustomer(frClient,frCard,frCardOrderInfo,frCardOrderPayModes,frCardOrderAllotSetList,mapS,mapI);
    }


    /**
     * 根据用户获取所有会员卡信息，App
     * @param clientId
     * @param code
     * @param request
     * @return
     */
    @GetMapping("/getClientCardList")
    public JsonResult getClientCardList(@RequestParam("clientId")String clientId,@RequestParam("code")String code,HttpServletRequest request)throws YJException{
        return JsonResult.success(service.getClientCardList(clientId,code));
    }


    /**
     * 验证数据
     * @param pageUtil
     * @param request
     * @throws YJException
     */
    public void toVerificationPage(PageUtil<FrCard> pageUtil,HttpServletRequest request)throws YJException{
        if(StringUtils.isEmpty(pageUtil.getClientId())){
            throw new YJException(YJExceptionEnum.CLIENTID_NOT_FOUND);
        }
        String code = pageUtil.getCode();
        if(StringUtils.isEmpty(code)){
            code = CookieUtils.getCookieValue(request, "code", true);
        }
        if(StringUtils.isEmpty(code)){
            throw new YJException(YJExceptionEnum.CUSTOMERCODE_NOT_FOUND);
        }
        pageUtil.setCode(code);
    }

//    /**
//     * 测定时任务是否失效
//     * @throws YJException
//     */
//    @GetMapping("/testTime")
//    public void testTime()throws YJException{
//          service.updateCardTime();
//    }
}

