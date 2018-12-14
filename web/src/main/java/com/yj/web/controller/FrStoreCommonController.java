package com.yj.web.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.result.JsonResult;
import com.yj.common.util.CookieUtils;
import com.yj.common.util.DateUtils;
import com.yj.dal.model.FrStoreCommon;
import com.yj.dal.param.OperaterParam;
import com.yj.service.service.IFrStoreCommonService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 门店通用设置表 前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-04
 */
@RestController
@RequestMapping("/frStoreCommon")
public class FrStoreCommonController {

    @Autowired
    IFrStoreCommonService service;

    /**
     * @Description: 根据客户代码读取通用门店设置
     * @Author: 欧俊俊
     * @Date: 2018/9/5 14:09
     */
    @GetMapping("/getSet")
    public JsonResult getSet() throws YJException {
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String code = CookieUtils.getCookieValue(req, "code",true);
        if(StringUtils.isEmpty(code)){
            throw new YJException(YJExceptionEnum.CUSTOMERCODE_NOT_FOUND);
        }
        EntityWrapper ew = new EntityWrapper<>();
        ew.where("CustomerCode = {0}",code).and("is_using = 1");
        Page<FrStoreCommon> page = service.selectPage(new Page<>(1, 1), ew);
        List<FrStoreCommon> records = page.getRecords();
        if(records.size()>0){
            //处理等级数据
            FrStoreCommon frStoreCommon = records.get(0);
            if (frStoreCommon.getQzLevel() != null) {
                //“.”和“|”都是转义字符，必须得加"\\";
                String[] strings = frStoreCommon.getQzLevel().split("\\|");
                frStoreCommon.setQzLevels(strings);
                frStoreCommon.setQzLevel(null);
            }
            //处理储值金额可用设置
            if(frStoreCommon.getCzjeUse()!=null){
                String[] czjeUseStr = frStoreCommon.getCzjeUse().split(",");
                frStoreCommon.setCzjeUses(czjeUseStr);
                frStoreCommon.setCzjeUse(null);
            }
            return JsonResult.success(frStoreCommon);
        }else {
            return JsonResult.failMessage("读取失败");
        }
    }


    /**
     * @Description: 修改通用门店设置
     * @Author: 欧俊俊
     * @Date: 2018/9/5 14:26
     */
    @PostMapping("/postUpdateSet")
    @Transactional
    public JsonResult updateOne(@RequestBody FrStoreCommon frStoreCommon ) {
        String[] czjeUses = frStoreCommon.getCzjeUses();
        if (czjeUses != null) {
            //处理储值金额可用设置
            String czjeUse = "";
            for (int i = 0; i < czjeUses.length ; i++) {
                if(czjeUse.equals("")){
                    czjeUse = czjeUses[i];
                }else {
                    czjeUse = czjeUse +","+ czjeUses[i];
                }
            }
            frStoreCommon.setCzjeUse(czjeUse);
        }
        boolean b = service.updateById(frStoreCommon);
        if(b){
            return JsonResult.successMessage("修改成功");
        }
        return JsonResult.failMessage("修改失败");
    }

    /**
     * @Description: 查询客户等级
     * @Author: 欧俊俊
     * @Date: 2018-10-08 10:42
     */
    @GetMapping("/getQzLevel")
    public JsonResult getQzLevel(HttpServletRequest request) {
        String code = CookieUtils.getCookieValue(request, "code", true);
        EntityWrapper ew = new EntityWrapper<>();
        ew.where("CustomerCode = {0}",code).and("is_using = 1");
        FrStoreCommon frStoreCommon = service.selectOne(ew);
        if (frStoreCommon.getQzLevel() != null) {
            //“.”和“|”都是转义字符，必须得加"\\";
            String[] strings = frStoreCommon.getQzLevel().split("\\|");
            return JsonResult.success(strings);
        }
        return JsonResult.failMessage("查询结果不存在");
    }



}

