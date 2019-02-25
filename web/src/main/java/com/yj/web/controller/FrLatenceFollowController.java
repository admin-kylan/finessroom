package com.yj.web.controller;


import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.common.util.CookieUtils;
import com.yj.common.util.DateUtils;
import com.yj.common.util.PageUtil;
import com.yj.common.util.StringUtils;
import com.yj.dal.model.FrLatenceFollow;
import com.yj.service.service.IFrLatenceFollowPicService;
import com.yj.service.service.IFrLatenceFollowService;
import com.yj.service.service.IFrFollowPicService;
import com.yj.service.service.IPersonlRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * <p>
 * 潜在会员跟进记录 前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-20
 */
@RestController
@RequestMapping("/frLatenceFollow")
public class FrLatenceFollowController {
    @Autowired
    IFrLatenceFollowService service;

    @Autowired
    IFrLatenceFollowPicService frLatenceFollowPicService;

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
    @GetMapping("/getLatenceClientList")
    public JsonResult getLatenceClientList(PageUtil<FrLatenceFollow> pageUtil, HttpServletRequest request)throws YJException {
        String code = CookieUtils.getCookieValue(request, "code", true);
        FrLatenceFollow frLatenceFollow = pageUtil.getCondition();
        if(frLatenceFollow == null){
            frLatenceFollow = new FrLatenceFollow();
            if(StringUtils.isEmpty(pageUtil.getClientId())){
                return  JsonResult.parameterError();
            }
            frLatenceFollow.setClientId(pageUtil.getClientId());
            pageUtil.setCondition(frLatenceFollow);
        }
        frLatenceFollow.setCustomerCode(code);
        frLatenceFollow.setUsing(true);
        return JsonResult.success(service.queryLatenceClientList(pageUtil));
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
        return JsonResult.success(frLatenceFollowPicService.queryPricList(followdId));
    }


    /**
     * 审核或者修改信息
     * @param frLatenceFollow
     * @param request
     * @return
     * @throws YJException
     */
    @PostMapping("/toSaveSubmit")
    public JsonResult toSaveSubmit(@RequestBody FrLatenceFollow frLatenceFollow,HttpServletRequest request)throws YJException{
        if(frLatenceFollow == null || StringUtils.isEmpty(frLatenceFollow.getId())){
            return  JsonResult.parameterError();
        }
        if(StringUtils.isEmpty(frLatenceFollow.getHandleAdvice())){
            return  JsonResult.failMessage("跟进调整请勿放空");
        }
        Date date = new Date();
        frLatenceFollow.setType(1);
        frLatenceFollow.setUsing(true);
        frLatenceFollow.setFollowAdjustTime(date);
        if(frLatenceFollow.getCheckTime() == null){
            frLatenceFollow.setCheckTime(date);
        }
        boolean toUpdate = service.toInsertOrUpdate(frLatenceFollow);
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

        FrLatenceFollow frLatenceFollow = new FrLatenceFollow();
        frLatenceFollow.setCustomerCode(code);
        frLatenceFollow.setClientId(clientId);
        frLatenceFollow.setPersonalId(personalId);
        frLatenceFollow.setShopId(shopId);
        frLatenceFollow.setFollowType(followType);
        frLatenceFollow.setFollowContent(followContent);
        frLatenceFollow.setNextFollowTime(DateUtils.getDataforString(nextFollowTime,"yyyy-mm-dd"));
        frLatenceFollow.setFollowTime(new Date());
        if(!StringUtils.isEmpty(createTime)){
            frLatenceFollow.setPlanVisitTime(DateUtils.getDataforString(createTime,"yyyy-mm-dd"));
        }
        if(!StringUtils.isEmpty(planVisitTime)){
            frLatenceFollow.setPlanVisitTime(DateUtils.getDataforString(planVisitTime,"yyyy-mm-dd"));
        }
        if(!StringUtils.isEmpty(planPurchaseTime)){
            frLatenceFollow.setPlanPurchaseTime(DateUtils.getDataforString(planPurchaseTime,"yyyy-mm-dd"));
        }
        StringBuffer imagePath = new StringBuffer(CookieUtils.getCookieValue(request, "url", true)) ;
        imagePath.append(CookieUtils.getCookieValue(request, "imgPath", true));
        List<String> imagesList = fileController.getImgUrlList(files,childPath);
        Map<String,String> map = iPersonlRoleService.getParentIdByPersonnelInfoId(personalId,code);
        if(map != null){
            frLatenceFollow.setRoleInfoId(map.get("roleInfoId"));
            frLatenceFollow.setSupperManagmentId(map.get("parentId"));
        }
        boolean toUpdate = service.toInertAndUpdatImage(frLatenceFollow,imagesList,imagePath.toString());
        if(toUpdate){
            return JsonResult.successMessage("添加成功");
        }
        return JsonResult.failMessage("添加失败");
    }


    @Value("${fitness.uploadPath}")
    private String filePath;

    /**
     * 添加潜在跟进记录app
     * @param files
     * @param childPath
     * @param frLatenceFollow
     * @param request
     * @return
     * @throws YJException
     */
    @PostMapping("/addFollow")
    public JsonResult addFollow(@RequestParam("file") MultipartFile[] files,String childPath,@RequestBody FrLatenceFollow frLatenceFollow,
                               HttpServletRequest request) throws YJException {
        if (childPath == null || childPath == "") {
            childPath = "avatar/";
        }
        StringBuffer imagePath = new StringBuffer(CookieUtils.getCookieValue(request, "url", true));
        imagePath.append(CookieUtils.getCookieValue(request, "imgPath", true));
        List<String> imagesList = this.getImgUrlList(files, childPath);
        return service.addFollow(imagesList, request,frLatenceFollow);
    }
    //查询潜在跟进记录APP
    @GetMapping("/getFollow")
    public JsonResult getFollow(String id){
        return service.getFollow(id);
    }

    /**
     * 获取批量上传的文件路径
     *
     * @param files
     * @param childPath
     * @return
     * @throws YJException
     */
    public List<String> getImgUrlList(MultipartFile[] files, String childPath) throws YJException {
        Map<String, String> map = new HashMap<>();
        List<String> imgUrlList = new ArrayList<>();
        String imgURL;
        for (MultipartFile file : files) {
            fileController.toUpdateLoad(file, childPath, map);
            String message = map.get("msg");
            if ("true".equals(message)) {
                imgUrlList.add(map.get("imgUrl"));
            }
            map = new HashMap<>();
        }
        return imgUrlList;
    }
}

