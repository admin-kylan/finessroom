package com.yj.service.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.yj.common.util.HttpUtils;
import com.yj.common.util.PublicUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className TicketSaleEducationServiceImpl
 * by Kylan
 *
 * @author Kylan
 * @date 2019-02-27 16:49
 */
@Service
public class TicketSaleEducationServiceImpl {

    private static String TICKET_URL = "https://txc.4006337366.com/index.php/Api";

    private static String TICKET_LIST_NAME = "TicketList";

    private static String TICKET_MD5_NAME = "3d8a29c49c36cc2f";




    /**
     * sign
     * @param code
     * @param mobile
     * @return
     */
    public String getSignMobileCode(String mobile, String code){
        return PublicUtils.cryptMD5_Double(TICKET_MD5_NAME.concat(code).concat("&").concat(mobile).concat(TICKET_MD5_NAME));
    }

    /**
     * sign
     * @param mobile
     * @return
     */
    public String getSignMobile(String mobile){
        return PublicUtils.cryptMD5_Double(TICKET_MD5_NAME.concat(mobile).concat(TICKET_MD5_NAME) );
    }

    /**
     * sign
     * @param mobile
     * @return
     */
    public String getSignPostTicketList(String code, String shopId, String sdaduimId, String mobile){
        return PublicUtils.cryptMD5_Double(TICKET_MD5_NAME.concat(code).concat("&").concat(shopId).concat("&").concat(sdaduimId).concat("&").concat(mobile).concat(TICKET_MD5_NAME) );
    }

    /**
     * sign
     * @param
     * @return
     */
    public String getSignPostConsumeTicket(String code, String shopId, String sdaduimId){
        return PublicUtils.cryptMD5_Double(TICKET_MD5_NAME.concat(code).concat("&").concat(shopId).concat("&").concat(sdaduimId).concat(TICKET_MD5_NAME) );
    }

    /**
     * sign
     * @param
     * @return
     */
    public String getSignGetTicketCoumerList( String mobile){
        return PublicUtils.cryptMD5_Double(TICKET_MD5_NAME.concat(mobile).concat(TICKET_MD5_NAME) );
    }


    /**
     * 票券列表
     * @param map
     */
    public List findPostTicketList(Map<String, String> map){
        String resultStr = HttpUtils.doPost(TICKET_URL + "/TicketSale/PostTicketList",  map);
        return getList(resultStr);
    }

    /**
     * 使用票券接口
     * 会员使用票券抵扣接口
     * @param map
     */
    public void usePostConsumeTicket(Map map){
        HttpUtils.doPost(TICKET_URL + "/TicketSale/PostConsumeTicket",  map);
    }


    /**
     * 积分兑换记录
     * 会员使用票券抵扣接口
     * @param map
     */
    public Page findGetTicketCoumerList(Map map){
        Page page = new Page();
        String resultStr = HttpUtils.doGet(TICKET_URL + "/TicketSale/GetTicketCoumerList",  map);
        JSONObject jsonoObj =  JSONObject.parseObject(resultStr);
        Integer totalPage = jsonoObj.getInteger("TotalPage");
        JSONArray jsonArr =	jsonoObj.getJSONArray("Data");
        page.setRecords(JSONObject.parseArray(jsonArr.toJSONString(), Map.class));
        page.setTotal(totalPage);
        return page;
    }






    private List getList(String result){
        JSONObject jsonoObj =  JSONObject.parseObject(result);
        JSONArray jsonArr =	jsonoObj.getJSONArray(TICKET_LIST_NAME);
        return jsonArr;
    }
}
