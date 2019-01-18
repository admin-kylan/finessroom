package com.yj.web.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.result.JsonResult;
import com.yj.common.util.CookieUtils;
import com.yj.common.util.StringUtils;
import com.yj.dal.model.FrCardTypeSplitSet;
import com.yj.service.service.IFrCardTypeSplitSetService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 会员卡分期付款设置 前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-13
 */
@RestController
@RequestMapping("/frCardTypeSplitSet")
public class FrCardTypeSplitSetController {

    @Resource
    private IFrCardTypeSplitSetService service;

    /**
     * 查询列表（不分页）
     * @param cardTypeId
     * @param code
     * @return
     * @throws YJException
     */
    @GetMapping("/list")
    public JsonResult getCardTypeSplitList(@RequestParam("cardTypeId")String cardTypeId, @RequestParam("code") String code)throws YJException{

        if(StringUtils.isEmpty(cardTypeId) || StringUtils.isEmpty(code)){
            throw new YJException(YJExceptionEnum.PARAM_ERROR);
        }
        List<FrCardTypeSplitSet> list = service.selectList(new EntityWrapper<FrCardTypeSplitSet>()
                .where("CustomerCode={0}",code).and("card_type_id={0}",cardTypeId));
//        and("is_using={0}",1).
        return JsonResult.success(list);
    }
    /**
     * 新增会员卡分期及详情
     * @param request
     * @param map
     * @return
     * @throws YJException
     */
    @PostMapping("/insert")
    public JsonResult insert(HttpServletRequest request,@RequestBody Map<String, Object> map)throws YJException{
//        JSONObject jsonObject = JSONObject.parseObject(params);
//        Map<String,Object> map = (Map) jsonObject;
        FrCardTypeSplitSet frCardTypeSplitSet = new FrCardTypeSplitSet();
        //从cookie里获取
        String code = CookieUtils.getCookieValue(request,"code",true);
        if(StringUtils.isEmpty(code)){
            throw new YJException(YJExceptionEnum.CUSTOMERCODE_NOT_FOUND);
        }
        frCardTypeSplitSet.setCustomerCode(code);
        //设置创建者信息
        String userId = CookieUtils.getCookieValue(request, "id", true);
        String userName = CookieUtils.getCookieValue(request,"name",true);
        frCardTypeSplitSet.setCreateUserId(userId);
        frCardTypeSplitSet.setUpdateUserId(userId);
        frCardTypeSplitSet.setCreateUserName(userName);
        frCardTypeSplitSet.setUpdateUserName(userName);
        return service.insert(map,frCardTypeSplitSet);
    }

    /**
     * 查询分期信息详情
     * @return
     */
    @GetMapping("/get")
    public JsonResult get(String id)throws YJException{
        return service.get(id);
    }

    /**
     * 根据id更新修改分期信息
     * @param request
     * @param map
     * @return
     * @throws YJException
     */
    @PostMapping("/update")
    public JsonResult update(HttpServletRequest request,@RequestBody Map<String, Object> map) throws YJException{
        return service.update(request,map);
    }

    /**
     * 根据id删除分期信息
     * @param id
     * @return
     * @throws YJException
     */
    @GetMapping("/delete")
    public JsonResult delete(String id) throws YJException{
        return service.delete(id);
    }

    /**
     * 是否启用
     * @return
     */
    @PostMapping("/isUsing")
    public JsonResult isUsing(@RequestBody FrCardTypeSplitSet frCardTypeSplitSet)throws YJException{
        return service.isUsing(frCardTypeSplitSet);
    }

}

