package com.yj.web.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.common.util.CommonUtils;
import com.yj.dal.model.FrClientPic;
import com.yj.service.service.IFrCardLimitService;
import com.yj.service.service.IFrCardService;
import com.yj.service.service.IFrClientPicService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 客户图片表 前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-21
 */
@RestController
@RequestMapping("/frClientPic")
public class FrClientPicController {

    @Resource
    private IFrClientPicService service;

    @Resource
    private IFrCardService iFrCardService;

    /**
     * 获取客户设置图片信息及会员权益部分统计数据
     * @param CustomerCode
     * @param clientId
     * @param request
     * @return
     */
    @GetMapping("/getClietnPriceList")
    public JsonResult getClietnPriceList(@RequestParam("CustomerCode") String CustomerCode,
                                         @RequestParam("clientId") String clientId, HttpServletRequest request)throws YJException{
        List<FrClientPic> list = service.selectList(new EntityWrapper<FrClientPic>().where("client_id={0}",clientId)
                .and("pic_type={0}", CommonUtils.PIC_TYPT_2).and("pic_state={0}",1));
        Map<String,Object> map = iFrCardService.getNumCard(clientId,CustomerCode,31,false);
        if(map == null ){
            map = new HashMap<>();
        }
        map.put("pricList",list);
        return JsonResult.success(map);
    }

}

