package com.yj.web.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sun.org.apache.xpath.internal.operations.Bool;
import com.yj.common.result.JsonResult;
import com.yj.common.util.CookieUtils;
import com.yj.common.util.StringUtils;
import com.yj.common.util.UUIDUtils;
import com.yj.dal.model.FrSettingInfo;
import com.yj.service.service.IFrSettingInfoService;
import com.yj.service.service.impl.FrSettingInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-12-03
 */
@RestController
@RequestMapping("/frSettingInfo")
public class FrSettingInfoController {

    @Autowired
    IFrSettingInfoService iFrSettingInfoService;

    @RequestMapping("/get/private/setting")
    public JsonResult getPrivateSetting(@RequestParam Map<String, Object> params, HttpServletRequest request) {
        String code = CookieUtils.getCookieValue(request, "code", true);
        Object type = params.get("type");
        String sdaduimId = (String) params.get("sdaduimId");
        int typeInt = 1;
        if (type != null) {
            typeInt = Integer.valueOf(String.valueOf(type));
        }
        List<FrSettingInfo> settingList = iFrSettingInfoService.selectList(new EntityWrapper<FrSettingInfo>().where("type={0} and customer_code={1} and sdaduim_id={2}", typeInt, code, sdaduimId));
        Boolean isNull = false;
        if (settingList.size() == 0 && sdaduimId != null) {
            isNull = true;
            settingList = iFrSettingInfoService.selectList(new EntityWrapper<FrSettingInfo>().where("type={0} and (customer_code is null or customer_code='')", typeInt));
        }
        Map<String, Object> result = new HashMap<>();

        settingList.stream().forEach((setting) -> {
            result.put(setting.getKey1(), setting.getValue());
        });

        if (isNull) {//插入记录
            settingList.stream().forEach((setting) -> {
                setting.setId(UUIDUtils.generateGUID());
                setting.setCustomerCode(code);
                setting.setCreateTime(new Date());
                setting.setSdaduimId(sdaduimId);
                iFrSettingInfoService.insert(setting);
            });
        }

        return JsonResult.success(result);
    }

    @RequestMapping("/update/private/setting")
    public JsonResult updatePrivateSetting(@RequestParam Map<String, Object> params, HttpServletRequest request) {
        String code = CookieUtils.getCookieValue(request, "code", true);
        String sdaduimId = (String) params.get("sdaduimId");
        params.entrySet().stream().forEach((value) -> {
            FrSettingInfo setting = new FrSettingInfo();
            if (StringUtils.isNotEmpty(String.valueOf(value.getValue()))) {
                setting.setValue(String.valueOf(value.getValue()));
            } else {
                setting.setValue("");
            }
            iFrSettingInfoService.update(setting, new EntityWrapper<FrSettingInfo>().where("key1={0} and customer_code={1} and sdaduim_id={2}", value.getKey(), code, sdaduimId));
        });

        return JsonResult.success("操作成功");
    }
}

