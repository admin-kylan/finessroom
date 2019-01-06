package com.yj.web.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.result.JsonResult;
import com.yj.common.util.CommonUtils;
import com.yj.common.util.CookieUtils;
import com.yj.common.util.StringUtils;
import com.yj.dal.model.FrCardLimit;
import com.yj.dal.model.FrClient;
import com.yj.dal.model.PersonnelInfo;
import com.yj.service.service.IFrCardLimitService;
import com.yj.service.service.IFrClientService;
import com.yj.service.service.IPersonnelInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.jar.JarEntry;

/**
 * <p>
 * 会员卡使用限定 前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-10-31
 */
@RestController
@RequestMapping("/frCardLimit")
public class FrCardLimitController {
    @Resource
    private IFrCardLimitService limitService;

    @Resource
    private IPersonnelInfoService iPersonnelInfoService;

    @Resource
    private IFrClientService iFrClientService;

    /**
     * 添加使用限定
     * @param frCardLimit
     * @param request
     * @return
     */
    @PostMapping("addCardLimit")
    public JsonResult addCardLimit(FrCardLimit frCardLimit, HttpServletRequest request)throws YJException{
        if(StringUtils.isEmpty(frCardLimit.getUseName()) || StringUtils.isEmpty(frCardLimit.getUsePhone())
                || StringUtils.isEmpty(frCardLimit.getUsePasswd())
                || StringUtils.isEmpty(frCardLimit.getClientId())){
            return JsonResult.failMessage("用户名、用户手机、密码、用户ID获取异常");
        }
        if(CommonUtils.CARD_LIMIT_2 == frCardLimit.getType()){
            if(StringUtils.isEmpty(frCardLimit.getCardId())){
                return JsonResult.failMessage("会员卡ID获取异常");
            }
        }
        String code = frCardLimit.getCustomerCode();
        if(StringUtils.isEmpty(code)){
            code = CookieUtils.getCookieValue(request,"code",true);
            if(StringUtils.isEmpty(code)){
                throw  new YJException(YJExceptionEnum.CUSTOMERCODE_NOT_FOUND);
            }
        }
        String personnelId = frCardLimit.getUpdateUserId();
        this.getFlageParemt(code,personnelId,request);
        frCardLimit.setCustomerCode(code);
        frCardLimit.setUpdateUserId(personnelId);
        return limitService.addCardLimit(frCardLimit,code);
    }

    /**
     * 获取指定会员卡的使用限定列表
     * @param request
     * @param cardId
     * @return
     */
    @GetMapping("getCardLimitList")
    public JsonResult getCardLimitList(HttpServletRequest request,@RequestParam("cardId") String cardId,@RequestParam("code")String code)throws YJException{
        if(org.apache.commons.lang3.StringUtils.isEmpty(cardId)){
            throw  new YJException(YJExceptionEnum.VIPCARDNO_NOT_EXISTED);
        }
        if(org.apache.commons.lang3.StringUtils.isEmpty(code)){
            code = CookieUtils.getCookieValue(request, "code", true);
        }
        if(org.apache.commons.lang3.StringUtils.isEmpty(code)){
            throw  new YJException(YJExceptionEnum.CUSTOMERCODE_NOT_FOUND);
        }
        return limitService.getCardLimitList(code,cardId);
    }

    /**
     * 删除指定使用限定列表
     * @param request
     * @param id
     * @return
     */
    @PostMapping("deleteCardLimit")
    public JsonResult deleteCardLimit(HttpServletRequest request,@RequestParam("id") String id,
                                      @RequestParam("code")String code,@RequestParam("clientId")String clientId,
                                      @RequestParam("personnelId") String personnelId)throws  YJException{
        if(StringUtils.isEmpty(id)){
            throw new YJException(YJExceptionEnum.PARAM_ERROR);
        }
        this.getFlageParemt(code,personnelId,request);
        return limitService.deleteCardLimit(code,id,clientId);
    }

    /**
     * 判断客户代码，操作id是否为空，若为空尝试cookie获取
     * @param code
     * @param personnelId
     * @param request
     * @throws YJException
     */
    public void getFlageParemt(String code,String personnelId,HttpServletRequest request)throws YJException{
        if(StringUtils.isEmpty(code)){
            code = CookieUtils.getCookieValue(request, "code", true);
        }
        if(StringUtils.isEmpty(code)){
            throw  new YJException(YJExceptionEnum.CUSTOMERCODE_NOT_FOUND);
        }
        if(StringUtils.isEmpty(personnelId)){
            personnelId = CookieUtils.getCookieValue(request, "id", true);
        }
        if(StringUtils.isEmpty(personnelId)){
            throw  new YJException(YJExceptionEnum.PERSONNEL_ID_NOT_FOUND);
        }
        PersonnelInfo personnelInfo = iPersonnelInfoService.selectOne(
                new EntityWrapper<PersonnelInfo>().where("CustomerCode = {0}",code)
                        .and("ID={0}",personnelId));
        if(personnelInfo == null || org.apache.commons.lang3.StringUtils.isEmpty(personnelInfo.getId())){
            throw new YJException(YJExceptionEnum.PERSONNEL_ID_NOT_FOUND);
        }
    }


    /**
     * 验证客户设置的会员卡密码
     * @param frCardLimit
     * @param request
     * @return
     * @throws YJException
     */
    @PostMapping("getVerification")
    public JsonResult getVerification(@RequestBody FrCardLimit frCardLimit ,HttpServletRequest request)throws YJException{
        if(StringUtils.isEmpty(frCardLimit.getUsePasswd()) || StringUtils.isEmpty(frCardLimit.getCardId())){
            return JsonResult.parameterError();
        }
        String code  = frCardLimit.getCustomerCode();
        if(StringUtils.isEmpty(code)){
            code = CookieUtils.getCookieValue(request, "code", true);
            if(StringUtils.isEmpty(code)){
                throw  new  YJException(YJExceptionEnum.CUSTOMERCODE_NOT_FOUND);
            }
        }
        FrCardLimit frCardLimit1 = limitService.selectOne(new EntityWrapper<FrCardLimit>().where("CustomerCode={0}",frCardLimit.getCustomerCode())
                                        .and("is_using={0}",1).and("card_id={0}",frCardLimit.getCardId())
                                        .and("use_passwd={0}",frCardLimit.getUsePasswd()).and("type={0}", CommonUtils.CARD_LIMIT_2));

        if(frCardLimit1 != null && !StringUtils.isEmpty(frCardLimit1.getId())){
            return JsonResult.success("验证成功");
        }
        return JsonResult.failMessage("验证失败");
    }


    /**
     * 添加通用使用限定
     * @param frCardLimit
     * @param request
     * @return
     */
    @PostMapping("addLimtTypeOne")
    public JsonResult addLimtTypeOne(FrCardLimit frCardLimit, HttpServletRequest request)throws YJException{
        if(StringUtils.isEmpty(frCardLimit.getClientId()) || StringUtils.isEmpty(frCardLimit.getUsePasswd())){
            return JsonResult.failMessage("客户ID，密码不能为空");
        }
        FrClient frClient = iFrClientService.selectById(frCardLimit.getClientId());
        if(frClient == null ){
            return JsonResult.failMessage("客户信息获取有误");
        }
        frCardLimit.setUseName(frClient.getClientName());
        frCardLimit.setUsePhone(frClient.getMobile());
        frCardLimit.setType(CommonUtils.CARD_LIMIT_1);
        return this.addCardLimit(frCardLimit,request);
    }

    @GetMapping("getLimtTypeOne")
    public JsonResult getLimtTypeOne(@RequestParam("CustomerCode") String CustomerCode,
                                         @RequestParam("clientId") String clientId, HttpServletRequest request)throws YJException{
        FrCardLimit frCardLimit1 = limitService.selectOne(new EntityWrapper<FrCardLimit>().where("is_using=1")
                .and("CustomerCode={0}",CustomerCode).and("client_id={0}",clientId)
                .and("type={0}",CommonUtils.CARD_LIMIT_1));
        return JsonResult.success(frCardLimit1);
    }

}

