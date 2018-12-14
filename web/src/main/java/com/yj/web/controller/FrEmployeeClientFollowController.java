package com.yj.web.controller;

import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.common.util.CookieUtils;
import com.yj.common.util.DateUtils;
import com.yj.common.util.PageUtil;
import com.yj.common.util.StringUtils;
import com.yj.dal.model.FrEmployeeClientFollow;
import com.yj.service.service.IFrEmployeeClientFollowService;
import com.yj.service.service.IFrFollowPicService;
import com.yj.service.service.IPersonlRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * <p>
 * 现有会员跟进记录 前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-30
 */
@RestController
@RequestMapping("/frEmployeeClientFollow")
public class FrEmployeeClientFollowController {

    @Autowired
    IFrEmployeeClientFollowService service;

    @Autowired
    IFrFollowPicService  iFrFollowPicService;

    @Autowired
    FileController fileController;

    @Autowired
    IPersonlRoleService iPersonlRoleService;

    private static final  String  FOLLOW_TYPE_1 = "1";    // 微信/短信等文字跟进(如果选择微信短信跟进，跟进图片必填)
    private static final  String  FOLLOW_TYPE_0 = "0";    // 电话跟进

    /**
     * 获取客户跟进信息
     * @param pageUtil
     * @param request
     * @return
     * @throws YJException
     */
    @GetMapping("/getEmployeeClientList")
    public JsonResult getEmployeeClientList(PageUtil<FrEmployeeClientFollow> pageUtil, HttpServletRequest request)throws YJException {
        String code = CookieUtils.getCookieValue(request, "code", true);
        FrEmployeeClientFollow frEmployeeClientFollow = pageUtil.getCondition();
        if(frEmployeeClientFollow == null){
           frEmployeeClientFollow = new FrEmployeeClientFollow();
           if(StringUtils.isEmpty(pageUtil.getClientId())){
              return  JsonResult.parameterError();
           }
           frEmployeeClientFollow.setClientId(pageUtil.getClientId());
           pageUtil.setCondition(frEmployeeClientFollow);
        }
        frEmployeeClientFollow.setCustomerCode(code);
        frEmployeeClientFollow.setUsing(true);
        return JsonResult.success(service.queryEmployeeClientList(pageUtil));
    }

    /**
     * 获取客户跟进图片
     * @param followdId
     * @return
     * @throws YJException
     */
    @GetMapping("/getPricList")
    public JsonResult getPricList(@RequestParam String followdId) throws YJException{
        if(StringUtils.isEmpty(followdId)){
            return  JsonResult.parameterError();
        }
        return JsonResult.success(iFrFollowPicService.queryPricList(followdId));
    }


    /**
     * 审核或者修改信息
     * @param frEmployeeClientFollow
     * @param request
     * @return
     * @throws YJException
     */
    @PostMapping("/toSaveSubmit")
    public JsonResult toSaveSubmit(@RequestBody FrEmployeeClientFollow frEmployeeClientFollow,HttpServletRequest request)throws YJException{
        if(frEmployeeClientFollow == null || StringUtils.isEmpty(frEmployeeClientFollow.getId())){
           return  JsonResult.parameterError();
        }
        if(StringUtils.isEmpty(frEmployeeClientFollow.getHandleAdvice())){
            return  JsonResult.failMessage("跟进调整请勿放空");
        }
        Date date = new Date();
        frEmployeeClientFollow.setType(1);
        frEmployeeClientFollow.setUsing(true);
        frEmployeeClientFollow.setFollowAdjustTime(date);
        if(frEmployeeClientFollow.getCheckTime() == null){
            frEmployeeClientFollow.setCheckTime(date);
        }
        boolean toUpdate = service.toInsertOrUpdate(frEmployeeClientFollow);
        if(toUpdate){
            return JsonResult.successMessage("审核成功");
        }
        return JsonResult.failMessage("审核失败");
    }

    /**
     * @Description: 添加跟进记录
     * @Author: zhangxb
     * @Date: 2018/9/6 10:25
     */
    @PostMapping("/toAddUpdateLoad")
    public JsonResult toAddUpdateLoad(@RequestParam("file") MultipartFile[] files,
                             @RequestParam("childPath")String childPath, @RequestParam("createTime") String createTime,
                             @RequestParam("followType") String followType, @RequestParam("followContent") String followContent,
                             @RequestParam("nextFollowTime") String nextFollowTime, @RequestParam("planVisitTime") String planVisitTime,
                             @RequestParam("planPurchaseTime") String planPurchaseTime,@RequestParam("clientId") String clientId, HttpServletRequest request) throws YJException {
       if(StringUtils.isEmpty(followType) || StringUtils.isEmpty(followContent) || StringUtils.isEmpty(nextFollowTime)
               || StringUtils.isEmpty(nextFollowTime)){
           JsonResult.failMessage("请填写完整");
       }
       if(StringUtils.isEmpty(clientId)){
           JsonResult.failMessage("cId参数异常");
       }
       if(FOLLOW_TYPE_1.equals(followType) && files.length < 4){
           JsonResult.failMessage("微信/文字跟进请上传4张图片");
       }
        String code = CookieUtils.getCookieValue(request, "code", true);
        String personalId = CookieUtils.getCookieValue(request, "id", true);
        String shopId = CookieUtils.getCookieValue(request, "shopid", true);

        FrEmployeeClientFollow frEmployeeClientFollow = new FrEmployeeClientFollow();
        frEmployeeClientFollow.setCustomerCode(code);
        frEmployeeClientFollow.setClientId(clientId);
        frEmployeeClientFollow.setPersonalId(personalId);
        frEmployeeClientFollow.setShopId(shopId);
        frEmployeeClientFollow.setFollowType(followType);
        frEmployeeClientFollow.setFollowContent(followContent);
        frEmployeeClientFollow.setNextFollowTime(DateUtils.getDataforString(nextFollowTime,"yyyy-mm-dd"));
        frEmployeeClientFollow.setFollowTime(new Date());
        if(!StringUtils.isEmpty(createTime)){
           frEmployeeClientFollow.setPlanVisitTime(DateUtils.getDataforString(createTime,"yyyy-mm-dd"));
        }
        if(!StringUtils.isEmpty(planVisitTime)){
            frEmployeeClientFollow.setPlanVisitTime(DateUtils.getDataforString(planVisitTime,"yyyy-mm-dd"));
        }
        if(!StringUtils.isEmpty(planPurchaseTime)){
            frEmployeeClientFollow.setPlanPurchaseTime(DateUtils.getDataforString(planPurchaseTime,"yyyy-mm-dd"));
        }
        StringBuffer imagePath = new StringBuffer(CookieUtils.getCookieValue(request, "url", true)) ;
        imagePath.append(CookieUtils.getCookieValue(request, "imgPath", true));
        List<String> imagesList = fileController.getImgUrlList(files,childPath);
        Map<String,String> map = iPersonlRoleService.getParentIdByPersonnelInfoId(personalId,code);
        if(map != null){
            frEmployeeClientFollow.setRoleInfoId(map.get("roleInfoId"));
            frEmployeeClientFollow.setSupperManagmentId(map.get("parentId"));
        }
        boolean toUpdate = service.toInertAndUpdatImage(frEmployeeClientFollow,imagesList,imagePath.toString());
        if(toUpdate){
           return JsonResult.successMessage("添加成功");
        }
        return JsonResult.failMessage("添加失败");
    }

}

