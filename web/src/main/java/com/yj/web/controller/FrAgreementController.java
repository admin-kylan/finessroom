package com.yj.web.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.result.JsonResult;
import com.yj.common.util.CookieUtils;
import com.yj.common.util.UUIDUtils;
import com.yj.dal.dto.NumRoleDTO;
import com.yj.dal.model.FrAgreement;
import com.yj.service.service.IFrAgreementService;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 协议号规则表 前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-27
 */
@RestController
@RequestMapping("/frAgreement")
public class FrAgreementController {
    @Autowired
    IFrAgreementService service;
    
    /**
     * @Description: 获取协议号规则列表
     * @Author: 欧俊俊
     * @Date: 2018-09-27 18:55
     */
    @GetMapping("/getList")
    public JsonResult getList(HttpServletRequest request) {
        String code = CookieUtils.getCookieValue(request, "code",true);
        List<NumRoleDTO> frAgreements = service.selectAgreementRuleList(code);
        return JsonResult.success(frAgreements);
    }

    /**
     * @Description: 增加协议号规则
     * @Author: 欧俊俊
     * @Date: 2018-09-27 18:55
     */
    @PostMapping("/postAdd")
    public JsonResult add(@RequestBody FrAgreement frAgreement) throws YJException {
        frAgreement.setId(UUIDUtils.generateGUID());
        boolean insert = service.addRole(frAgreement);
        if (insert){
            return JsonResult.successMessage("增加成功");
        }
        return JsonResult.failMessage("增加失败");
    }

    /**
     * 判断协议号是否符合规则
     * @param agreement 协议号
     * @return
     */
    @GetMapping("checkCardAgreement")
    public JsonResult checkCardAgreement(HttpServletRequest request,@RequestParam("agreement") String agreement,String code){
        if(StringUtils.isEmpty(agreement)){
            return JsonResult.failMessage("协议号不能为空");
        }
        if(StringUtils.isEmpty(code)){
            code = CookieUtils.getCookieValue(request, "code", true);
            if(StringUtils.isEmpty(code)){
                return JsonResult.failMessage("客户代码获取异常");
            }
        }
        String id = service.checkCardAgreement(agreement,code);
        if("".equals(id)){
            return JsonResult.failMessage("协议号不可用");
        }
        if("1".equals(id)){
            return JsonResult.failMessage("暂无协议号规则，请创建协议号规则");
        }
        return JsonResult.success();
    }

    /**
     * 随机生成协议号
     * @param request
     * @return
     */
    @GetMapping("addCardAgreement")
    public JsonResult addCardAgreement(HttpServletRequest request,String code)throws YJException{
        if(StringUtils.isEmpty(code)){
            code = CookieUtils.getCookieValue(request, "code",true);
            if(StringUtils.isEmpty(code)){
                throw new YJException(YJExceptionEnum.CUSTOMERCODE_NOT_FOUND);
            }
        }
        return service.addCardAgreement(code);
    }

}

