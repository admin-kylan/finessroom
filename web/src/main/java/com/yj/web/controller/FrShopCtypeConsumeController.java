package com.yj.web.controller;


import com.yj.common.result.JsonResult;
import com.yj.dal.model.FrShopCtypeConsume;
import com.yj.dal.model.Sdaduim;
import com.yj.service.service.IFrShopCtypeConsumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 会员卡类型-门店-场馆-项目关系表（消费门店会员卡权益设置） 前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-29
 */
@RestController
@RequestMapping("/frShopCtypeConsume")
public class FrShopCtypeConsumeController {

    @Autowired
    private IFrShopCtypeConsumeService  shopCtypeConsumeService;


    /**
     * @Description: /提交   会员卡类型-门店-场馆-项目关系表
     */
    @PostMapping("/addShopCtypeConsume/{cardTypeId}")
    public JsonResult addShopCtypeConsume(@RequestBody Map<String ,Object> map,@PathVariable("cardTypeId") String cardTypeId) throws JSONException {
        Boolean is = shopCtypeConsumeService.addShopCtypeConsume(map,cardTypeId);
        if (is) {
            return JsonResult.successMessage("保存成功");
        }
        return JsonResult.failMessage("保存失败");
//        return   s.addShopCtypeConsume();
    }














}

