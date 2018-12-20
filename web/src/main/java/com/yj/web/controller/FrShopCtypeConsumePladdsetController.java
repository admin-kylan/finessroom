package com.yj.web.controller;


import com.yj.common.result.JsonResult;
import com.yj.common.util.CookieUtils;
import com.yj.common.util.UUIDUtils;
import com.yj.dal.dao.FrShopCtypeConsumePlsetMapper;
import com.yj.dal.model.*;
import com.yj.service.service.IFrShopCardConsumePladdsetService;
import com.yj.service.service.IFrShopCardConsumePlsetService;
import com.yj.service.service.IFrShopCtypeConsumePladdsetService;
import com.yj.service.service.IFrShopCtypeConsumePlsetService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-10-09
 */
@RestController
@RequestMapping("/frShopCtypeConsumePladdset")
public class FrShopCtypeConsumePladdsetController {
  @Resource
   private   IFrShopCtypeConsumePladdsetService ctypeConsumePladdsetService;
    @Resource
    private IFrShopCtypeConsumePlsetService    ctypeConsumePlsetService;
    @Resource
    private IFrShopCardConsumePlsetService cardConsumePlsetService;

    @Resource
    private IFrShopCardConsumePladdsetService cardConsumePladdsetService;

    /**
     * 设置卡使用时间
     *
     * @param
     * @return
     */
    @PostMapping("/setConsumePladdset")
    public JsonResult FrShopCtypeConsumePladdset(@RequestBody FrShopCtypeConsumePladdset Pladdset, HttpServletRequest request) {
      //  String code = CookieUtils.getCookieValue(request, "code", true);
        FrShopCardConsumePladdset Cardpladdset=new FrShopCardConsumePladdset();
        Cardpladdset.setUseLimit(Pladdset.getUseLimit());
        Cardpladdset.setUseDayStar(Pladdset.getUseDayStar());
        Cardpladdset.setUseDayEnd(Pladdset.getUseDayEnd());
        Cardpladdset.setUseSelect(Pladdset.getUseSelect());
        Cardpladdset.setUseTimeStar(Pladdset.getUseTimeStar());
        Cardpladdset.setUseTimeEnd(Pladdset.getUseTimeEnd());
        Cardpladdset.setCardConsumeId(Pladdset.getConsumeId());
        Cardpladdset.setId(UUIDUtils.generateUUID());
      Pladdset.setId(UUIDUtils.generateGUID());
        Boolean rows=false;
        rows = cardConsumePladdsetService.insert(Cardpladdset);
        rows = ctypeConsumePladdsetService.insert(Pladdset);

        if (rows){
            return JsonResult.successMessage("增加成功");
        }
        return JsonResult.failMessage("增加失败");
    }

    /**
     * 设置卡使用时间  上午 下午
     *
     * @param
     * @return
     */
    @PostMapping("/setsetTimeList")
    public JsonResult setTimeList(  FrShopCtypeConsumePlset setTimeList , HttpServletRequest request) {
        //  String code = CookieUtils.getCookieValue(request, "code", true);
        List<FrShopCtypeConsumePlset> list=setTimeList.getSetTimeList();
          String[] str=null;String[] str2=null;
        Boolean rows=false;
        for (int i = 0; i <list.size() ; i++) {
            FrShopCtypeConsumePlset plset=new FrShopCtypeConsumePlset();
            str = list.get(i).getAM().split("至");
            str2 = list.get(i).getPM().split("至");
            if (str.length<=1 ){
                continue;
            }  if (str2.length<=1){
                continue;
            }
                plset.setUseDays(list.get(i).getUseDays());
                plset.setConsumeId(list.get(i).getConsumeId());

                plset.setStartTimeAm(str[0]);
                plset.setEndTimeAm(str[1]);

                plset.setStartTimePm(str2[0]);
                plset.setEndTimePm(str2[1]);
             plset.setUseSelect(list.get(i).getUseSelect());
//            FrShopCardConsumePlset Cardplset= new FrShopCardConsumePlset();
//            Cardplset.setUseDays(list.get(i).getUseDays());
//            Cardplset.setCardConsumeId(list.get(i).getConsumeId());
//            Cardplset.setStartTimeAm(str[0]);
//            Cardplset.setEndTimeAm(str[1]);
//            Cardplset.setStartTimePm(str2[0]);
//            Cardplset.setEndTimePm(str2[1]);
//            Cardplset.setUseSelect(list.get(i).getUseSelect());
            if (list.get(i).getId()==null) {
                plset.setId(UUIDUtils.generateGUID());
             //   Cardplset.setId(UUIDUtils.generateGUID());
                rows = ctypeConsumePlsetService.insert(plset);
             //   rows =   cardConsumePlsetService.insert(Cardplset);
            }else {
                plset.setId(list.get(i).getId());
            //    Cardplset.setId(list.get(i).getId());
                rows = ctypeConsumePlsetService.updateById(plset);
             //   rows =  cardConsumePlsetService.updateById(Cardplset);
            }
        }
        if (rows){
            return JsonResult.successMessage("增加成功");
        }
        return JsonResult.failMessage("失败");

    }










}

