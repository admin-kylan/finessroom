package com.yj.service.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.yj.common.exception.YJException;
import com.yj.common.util.HttpUtils;
import com.yj.common.util.PageUtils;
import com.yj.common.util.PublicUtils;
import com.yj.dal.param.PostDelTicketParam;
import com.yj.service.service.ITicketService;
@Service
public class TicketServiceImpl implements ITicketService{

	
	
	final   String TEL = "13645945925";  //13645945925 
	final   String CUSTOMERCODE = "003"; //CustomerCode
	final   String SHOPID="0";  //ShopId
	final   String  TEL_SIGN = PublicUtils.cryptMD5_Double("3d8a29c49c36cc2f".concat(TEL).concat("3d8a29c49c36cc2f") );  //
	final   String CUSTOMERCODE_SIGN = PublicUtils.cryptMD5_Double("3d8a29c49c36cc2f".concat(CUSTOMERCODE).concat("3d8a29c49c36cc2f"));
	final   String CUSTOMERCODE_TEL_SGIN = PublicUtils.cryptMD5_Double("3d8a29c49c36cc2f".concat(CUSTOMERCODE).concat("&").concat(TEL).concat("3d8a29c49c36cc2f"));
	  String uri   ;
	
	/**
	 * 接口1：客户管理会员票券列表--Get
	 */
	@Override
	public PageUtils getTicketList() throws YJException {
		System.out.println("----------------接口1：客户管理会员票券列表--Get----------------------------");
		uri ="/TicketSale/GetTicketList";
		
		HashMap<String ,String> mapParams = new HashMap<String ,String>();
		Page page =  new Page();
		
		// TODO Auto-generated method stub
		mapParams.put("Sign", TEL_SIGN);
		mapParams.put("Tel", TEL);
		mapParams.put("CustomerCode", CUSTOMERCODE);
		System.out.println("mapParams>>"+mapParams);
		
		String resultStr = HttpUtils.doGet(getFullUrl(uri),  mapParams);
		System.out.println(resultStr);
		JSONObject jsonoObj =  JSONObject.parseObject(resultStr);
  		JSONArray jsonArr =	jsonoObj.getJSONArray("TicketList");
		page.setRecords(JSONObject.parseArray(jsonArr.toJSONString(), Map.class));
		 return new PageUtils(page);
	}
	
	
	/**
	 * 接口2：客户管理赠送票券列表--Get
	 */
	public   PageUtils  getTicketSet() throws YJException {
		Page page =  new Page();
		HashMap<String ,String> mapParams = new HashMap<String ,String>();
		mapParams = new HashMap<String ,String>();
		mapParams.put("Sign", CUSTOMERCODE_SIGN);
		mapParams.put("CustomerCode", CUSTOMERCODE);
		mapParams.put("ShopId", SHOPID);
		uri ="/TicketSale/GetTicketSet";
		System.out.println("----------------接口2：客户管理赠送票券列表--Get----------------------------");
		System.out.println(HttpUtils.doGet(getFullUrl(uri), mapParams));
		String resultStr = HttpUtils.doGet(getFullUrl(uri), mapParams);
		JSONObject jsonoObj =  JSONObject.parseObject(resultStr);
		JSONArray jsonArr =	jsonoObj.getJSONArray("TicketData");
		//List<Map> list = JSONObject.parseArray(jsonArr.toJSONString(), Map.class);
		page.setRecords(JSONObject.parseArray(jsonArr.toJSONString(), Map.class));
		 return new PageUtils(page);
		
	}
	
	
	
	public static String getFullUrl(String uri ){
		String pinfix_url = "https://txc.4006337366.com/index.php/Api";
		System.out.println("URL : "+pinfix_url.concat(uri));

		return pinfix_url.concat(uri);
	}


	@Override
	public String PostDelTicket(PostDelTicketParam postDelTicketParam) throws YJException {
		// TODO Auto-generated method stub
		HashMap<String ,String> mapParams = new HashMap<String ,String>();

		uri = "/TicketSale/PostDelTicket";
		
		HashMap<String,String> params = new HashMap<String ,String>();
		params.put("ID", postDelTicketParam.getId());
		params.put("CustomerCode", postDelTicketParam.getCustomerCode());
		params.put("TicketNum", postDelTicketParam.getTicketNum());
		params.put("TicketName", postDelTicketParam.getTicketName());
		System.out.println("params>>>>"+params);
		String  sign = PublicUtils.cryptMD5_Double("3d8a29c49c36cc2f"+params.toString()+"3d8a29c49c36cc2f");
		mapParams = new HashMap<String ,String>();
		mapParams.put("Sign", sign);
		mapParams.put("data", params.toString());
		System.out.println("---------------- 接口4：批量删除用户票券--POST---------------------------");
		JSONObject jsonoObj =  JSONObject.parseObject(HttpUtils.doPost(getFullUrl(uri), mapParams));
		jsonoObj.get("Code");
		System.out.println(">>>>>>>>>>>>"+jsonoObj);
		return jsonoObj.get("Code").toString();
	}
	
	public void postGiveTicketList() {
		List TicketSetList = new ArrayList();
		HashMap<String ,String> mapParams = new HashMap<String ,String>();
		
		HashMap<String ,String>	TicketSet  = new HashMap<String ,String>();
		TicketSet.put("ID", "5c418c8899d06735");
		TicketSet.put("TicketSendWhy", "购卡赠送");
		TicketSet.put("Num", "1");
		TicketSetList.add(TicketSet);
		
		HashMap<String ,Object>	dataParams = new HashMap<String ,Object>();
		dataParams.put("CustomerCode", CUSTOMERCODE);
		dataParams.put("MemberCardId", "12f4eef7cf5619ac");
		dataParams.put("UsePeople", "003");
		dataParams.put("ContactTel", TEL);
		dataParams.put("OperateName", "1234");
		dataParams.put("OperateID", "1234");
		dataParams.put("OperateType", "0");
		dataParams.put("ConsumeState", "6"); // 固定
		dataParams.put("GivingID", "");
		dataParams.put("ShopId", SHOPID);
		dataParams.put("ServiceName", "1234");
		dataParams.put("ServiceTel", "13645945925");
		dataParams.put("TicketSetList", TicketSetList);
		
		System.out.println("---------------票券赠送接口--Post----------------------------begin");
		System.out.println("dataParams：|"+JSONObject.toJSONString(dataParams)+"|");
		mapParams = new HashMap<String ,String>();
		mapParams.put("Sign", CUSTOMERCODE_TEL_SGIN);
		mapParams.put("data", JSONObject.toJSONString(dataParams));
		uri = "/TicketSale/PostGiveTicketList";
		System.out.println(HttpUtils.doPost(getFullUrl(uri), mapParams));
		System.out.println("---------------票券赠送接口--Post----------------------------end"); 
		// {"Code":"1","Msg":"赠送成功"}
	}

}
