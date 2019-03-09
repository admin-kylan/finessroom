package com.yj.web.controller;

import com.yj.common.result.JsonResult;
import com.yj.service.service.impl.FrCustomerConsumeHistoryServiceImpl;
import com.yj.service.service.impl.TicketSaleEducationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * className TicketSaleEducationController
 * by Kylan
 *
 * @author Kylan
 * @date 2019-02-27 20:57
 */
@RestController
@RequestMapping("/eduTicket")
public class TicketSaleEducationController {

    @Autowired
    private TicketSaleEducationServiceImpl ticketSaleEducationService;

    @Autowired
    private FrCustomerConsumeHistoryServiceImpl frCustomerConsumeHistoryService;

    /**
     * 查询记录
     * @param request
     * @return
     */
    @GetMapping("findPostTicketList")
    public JsonResult findCoachList(HttpServletRequest request){
        String code = request.getParameter("code");
        String mobile = request.getParameter("mobile");
        String shopId = request.getParameter("shopId");
        String sdaduimId = request.getParameter("sdaduimId");
        String type = request.getParameter("type");
        String price = request.getParameter("price");
        Map<String, String> map = new HashMap();
        map.put("Sign", ticketSaleEducationService.getSignPostTicketList(code, shopId, sdaduimId, mobile));
        map.put("Tel", mobile);
        map.put("CustomerCode", code);
        map.put("ShopId", shopId);
        map.put("SdaduimId", sdaduimId);
        map.put("Type", type);
        map.put("Price", price);
        return JsonResult.success(ticketSaleEducationService.findPostTicketList(map));
    }

    /**
     * 积分兑换记录
     * @param request
     * @return
     */
    @GetMapping("findGetTicketCoumerList")
    public JsonResult findGetTicketCoumerList(HttpServletRequest request){
        String code = request.getParameter("code");
        String num = request.getParameter("num"); //每页大小
        String mobile = request.getParameter("mobile");

        String type = request.getParameter("type");
        String pageIndex = request.getParameter("pageIndex");
        String startTime = request.getParameter("beginDate");
        String endTime = request.getParameter("endDate");
        Map<String, Object> map = new HashMap();
        map.put("Sign", ticketSaleEducationService.getSignGetTicketCoumerList(mobile));
        map.put("Num", Integer.parseInt(num));
        map.put("Tel", mobile);
        map.put("CustomerCode", code);
        map.put("type", type);
        map.put("PageIndex", Integer.parseInt(pageIndex));
        map.put("StartTime", startTime);
        map.put("EndTime", endTime);
        return JsonResult.success(ticketSaleEducationService.findGetTicketCoumerList(map));
    }


    /**
     * 会员消费记录
     * @param request
     * @return
     */
    @GetMapping("findConsumeList")
    public JsonResult findConsumeList(HttpServletRequest request){
        String code = request.getParameter("code");
        String clientId = request.getParameter("clientId");
        String beginDate = request.getParameter("beginDate");
        String endDate = request.getParameter("endDate");
        String pageSize = request.getParameter("pageSize"); //每页大小
        String pageIndex = request.getParameter("pageIndex");
        return JsonResult.success(frCustomerConsumeHistoryService.findConsumeList(clientId, beginDate, endDate, pageIndex, pageSize, code));
    }



}
