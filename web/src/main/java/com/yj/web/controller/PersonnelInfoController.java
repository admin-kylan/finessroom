package com.yj.web.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.result.JsonResult;
import com.yj.common.util.CookieUtils;
import com.yj.dal.model.PersonnelInfo;
import com.yj.service.service.IPersonnelInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 人员信息表 前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-10-09
 */
@RestController
@RequestMapping("/personnelInfo")
public class PersonnelInfoController {

    @Autowired
    IPersonnelInfoService service;

    /**
     * @Description: 根据客户代码查询销售顾问(员工列表)
     * @Author: 欧俊俊
     * @Date: 2018-10-09 10:37
     */
    @GetMapping("/getSalespersonList")
    public JsonResult getSalespersonList(HttpServletRequest request) throws YJException {
        String code = CookieUtils.getCookieValue(request, "code", true);
        if (StringUtils.isEmpty(code)) {
            throw new YJException(YJExceptionEnum.CUSTOMERCODE_NOT_FOUND);
        }
        EntityWrapper entityWrapper = new EntityWrapper<>();
        entityWrapper.setSqlSelect("id,RelName").where("CustomerCode = {0}", code);
        return JsonResult.success(service.selectList(entityWrapper));
    }

    /**
     * 获取销售人员列表
     *
     * @param shopId 商店id
     * @return
     * @throws YJException
     */
    @GetMapping("/getMarketUserList")
    public JsonResult getMarketUserList(HttpServletRequest request, String shopId) throws YJException {
        String code = CookieUtils.getCookieValue(request, "code", true);
        return JsonResult.success(service.getMarketUserList(shopId, code));
    }

    @GetMapping("/findAll")
    public JsonResult findAll() {
        List<PersonnelInfo> personnelInfos = service.selectList(null);
        return JsonResult.success(personnelInfos);
    }

    /**
     * 验证凭证密码
     *
     * @param personnelInfo
     * @param request
     * @return
     * @throws YJException
     */
    @PostMapping("getVerification")
    public JsonResult getVerification(@RequestBody PersonnelInfo personnelInfo, HttpServletRequest request) throws YJException {
        if (StringUtils.isEmpty(personnelInfo.getPassWord())) {
            return JsonResult.parameterError();
        }
        String code = personnelInfo.getCustomerCode();
        if (StringUtils.isEmpty(code)) {
            code = CookieUtils.getCookieValue(request, "code", true);
        }
        String shopid = personnelInfo.getShopId();
        if (StringUtils.isEmpty(shopid)) {
            shopid = CookieUtils.getCookieValue(request, "shopid", true);
        }
        String id = personnelInfo.getId();
        if (StringUtils.isEmpty(id)) {
            id = CookieUtils.getCookieValue(request, "id", true);
        }
        EntityWrapper entityWrapper = new EntityWrapper<PersonnelInfo>();
        entityWrapper.where("CustomerCode = {0}", code).and("ID={0}", id)
                .and("PassWord={0}", personnelInfo.getPassWord())
                .and("ShopId={0}", shopid).and("Status={0}", 0).and("IsDel={0}", 0);
        PersonnelInfo personnelInfo1 = service.selectOne(entityWrapper);
        if (personnelInfo1 != null && !StringUtils.isEmpty(personnelInfo1.getId())) {
            return JsonResult.success("验证成功");
        }
        return JsonResult.failMessage("验证失败");
    }

    @GetMapping("/getServicePersonnel")
    public JsonResult getServicePersonnel(Integer userType) throws YJException{
        List<PersonnelInfo> personnelInfos = service.getServicePersonnel(userType);
        return JsonResult.success(personnelInfos);
    }
    /**
     * 获取角色为未分配的人员
     */
    @GetMapping("/getUnallocatedPersonnel")
    public JsonResult getUnallocatedPersonnel(String rid) throws YJException{
        List<PersonnelInfo> personnelInfos = service.getUnallocatedPersonnel(rid);
        return JsonResult.success(personnelInfos);
    }
}

