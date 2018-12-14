package com.yj.web.controller;


import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.result.JsonResult;
import com.yj.common.util.CookieUtils;
import com.yj.dal.dto.NumRoleDTO;
import com.yj.dal.model.FrCardNum;
import com.yj.service.service.IFrCardNumService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 会员卡号规则表 前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-27
 */
@RestController
@RequestMapping("/frCardNum")
public class FrCardNumController {
    @Autowired
    IFrCardNumService service;

    /**
     * @Description: 获取会员卡号规则列表
     * @Author: 欧俊俊
     * @Date: 2018-09-27 18:55
     */
    @GetMapping("/getList")
    public JsonResult getList(HttpServletRequest request) {
        String code = CookieUtils.getCookieValue(request, "code",true);
        List<NumRoleDTO> frAgreements = service.selectFrCardNumRuleList(code);
        return JsonResult.success(frAgreements);
    }

    /**
     * @Description: 增加会员卡号规则
     * @Author: 欧俊俊
     * @Date: 2018-09-27 18:55
     */
    @PostMapping("/postAdd")
    public JsonResult add(@RequestBody FrCardNum frCardNum) throws YJException {
        boolean insert = service.addRole(frCardNum);
        if (insert){
            return JsonResult.successMessage("增加成功");
        }
        return JsonResult.failMessage("增加失败");
    }

    /**
     * 判断会员卡号是否符合规则
     * @param cardNum 会员卡号
     * @return
     */
    @GetMapping("checkCardNum")
    public JsonResult checkCardNum(HttpServletRequest request,String cardNum){
        if(StringUtils.isEmpty(cardNum)){
            return JsonResult.failMessage("会员卡号不能为空");
        }
        String code = CookieUtils.getCookieValue(request, "code",true);
        String id = service.checkCardNum(cardNum,code);
        if("".equals(id)){
            return JsonResult.failMessage("会员卡号不可用");
        }
        if("1".equals(id)){
            return JsonResult.failMessage("暂无会员卡号规则，请创建会员卡号规则");
        }
        return JsonResult.success();
    }

    /**
     * 随机生成会员卡号
     * @param request
     * @return
     */
    @GetMapping("addCardNums")
    public JsonResult addCardNums(HttpServletRequest request,@RequestParam("code")String code)throws YJException{
        if(StringUtils.isEmpty(code)){
            code = CookieUtils.getCookieValue(request, "code",true);
            if(StringUtils.isEmpty(code)){
                throw new YJException(YJExceptionEnum.CUSTOMERCODE_NOT_FOUND);
            }
        }
        return service.addCardNum(code);
    }

}

