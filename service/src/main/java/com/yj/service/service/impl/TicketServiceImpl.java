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
import com.yj.dal.param.GetIsBindingPeopleParam;
import com.yj.dal.param.PostConsumeTicketParam11;
import com.yj.dal.param.PostConsumeTicketParam8;
import com.yj.dal.param.PostDelTicketParam;
import com.yj.dal.param.PostGiveTicketListParam;
import com.yj.dal.param.PostTicketListParam;
import com.yj.dal.param.PostTransferParam;
import com.yj.dal.param.PostUseConsumeTicketParam;
import com.yj.service.service.ITicketService;
@Service
public class TicketServiceImpl implements ITicketService{

	
	
//	final   String TEL = "13645945925";  //13645945925 
//	final   String CUSTOMERCODE = "003"; //CustomerCode
//	final   String SHOPID="0";  //ShopId
//	final   String  TEL_SIGN = PublicUtils.cryptMD5_Double("3d8a29c49c36cc2f".concat(TEL).concat("3d8a29c49c36cc2f") );  //
//	final   String CUSTOMERCODE_SIGN = PublicUtils.cryptMD5_Double("3d8a29c49c36cc2f".concat(CUSTOMERCODE).concat("3d8a29c49c36cc2f"));
//	final   String CUSTOMERCODE_TEL_SGIN = PublicUtils.cryptMD5_Double("3d8a29c49c36cc2f".concat(CUSTOMERCODE).concat("&").concat(TEL).concat("3d8a29c49c36cc2f"));
	  String uri   ;
	  final   String key = "3d8a29c49c36cc2f"; 
	/**
	 * 接口1：客户管理会员票券列表--Get
	 */
	@Override
	public PageUtils getTicketList(String tel,String  code) throws YJException {
		System.out.println("----------------接口1：客户管理会员票券列表--Get----------------------------");
		uri ="/TicketSale/GetTicketList";
		String  tel_sign = PublicUtils.cryptMD5_Double(key.concat(tel).concat(key) );   
		HashMap<String ,String> mapParams = new HashMap<String ,String>();
		Page page =  new Page();
		
		// TODO Auto-generated method stub
		mapParams.put("Sign", tel_sign);
		mapParams.put("Tel", tel);
		mapParams.put("CustomerCode", code);
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
	public   PageUtils  getTicketSet(String shopid,String customerCode) throws YJException {
		Page page =  new Page();
		String customerCode_sign = PublicUtils.cryptMD5_Double(key.concat(customerCode).concat(key));
		HashMap<String ,String> mapParams = new HashMap<String ,String>();
		mapParams = new HashMap<String ,String>();
		mapParams.put("Sign", customerCode_sign);
		mapParams.put("CustomerCode", customerCode);
		mapParams.put("ShopId", shopid);
		uri ="/TicketSale/GetTicketSet";
		System.out.println("----------------接口2：客户管理赠送票券列表--Get----------------------------");
		System.out.println("mapParams------"+ mapParams);
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
		String  sign = PublicUtils.cryptMD5_Double(key+params.toString()+key);
		mapParams = new HashMap<String ,String>();
		mapParams.put("Sign", sign);
		mapParams.put("data", params.toString());
		System.out.println("---------------- 接口4：批量删除用户票券--POST---------------------------");
		JSONObject jsonoObj =  JSONObject.parseObject(HttpUtils.doPost(getFullUrl(uri), mapParams));
		jsonoObj.get("Code");
		System.out.println(">>>>>>>>>>>>"+jsonoObj);
		return jsonoObj.get("Code").toString();
	}
	
	/**
	 * 接口3：票券赠送接口--Post
	 */
	public Map postGiveTicketList(PostGiveTicketListParam postGiveTicketListParam) {
		List TicketSetList = new ArrayList();
		HashMap<String ,String> mapParams = new HashMap<String ,String>();
		
//		HashMap<String ,String>	TicketSet  = new HashMap<String ,String>();
//		TicketSet.put("ID", "5c418c8899d06735");
//		TicketSet.put("TicketSendWhy", "购卡赠送");
//		TicketSet.put("Num", "1");
//		TicketSetList.add(TicketSet);
		
		
		
		HashMap<String ,Object>	dataParams = new HashMap<String ,Object>();
		dataParams.put("CustomerCode", postGiveTicketListParam.getCustomerCode());
		dataParams.put("MemberCardId",  postGiveTicketListParam.getMemberCardId());
		dataParams.put("UsePeople", postGiveTicketListParam.getUsePeople());
		dataParams.put("ContactTel", postGiveTicketListParam.getContactTel());
		dataParams.put("OperateName", postGiveTicketListParam.getOperateName());
		dataParams.put("OperateID", postGiveTicketListParam.getOperateID());
		dataParams.put("OperateType", postGiveTicketListParam.getOperateType());// 固定
		dataParams.put("ConsumeState", postGiveTicketListParam.getConsumeState()); // 固定
		dataParams.put("GivingID", postGiveTicketListParam.getGivingID());
		dataParams.put("ShopId", postGiveTicketListParam.getShopId());
		dataParams.put("ServiceName", postGiveTicketListParam.getServiceName());
		dataParams.put("ServiceTel", postGiveTicketListParam.getServiceTel());
		dataParams.put("TicketSetList", JSONArray.parseArray(postGiveTicketListParam.getTicketSetList(), Map.class) );
		
		System.out.println("---------------票券赠送接口--Post----------------------------begin");
		System.out.println("dataParams：|"+JSONObject.toJSONString(dataParams)+"|");
		mapParams = new HashMap<String ,String>();
		mapParams.put("Sign", PublicUtils.cryptMD5_Double(key.concat(postGiveTicketListParam.getCustomerCode()).concat("&").concat(postGiveTicketListParam.getContactTel()).concat(key)));
		mapParams.put("data", JSONObject.toJSONString(dataParams));
		uri = "/TicketSale/PostGiveTicketList";
		System.out.println("mapParams--->"+mapParams);
		String resStr = HttpUtils.doPost(getFullUrl(uri), mapParams);
		System.out.println("resStr--->"+resStr);
		System.out.println("---------------票券赠送接口--Post----------------------------end"); 
		// {"Code":"1","Msg":"赠送成功"}
		return JSONObject.parseObject(resStr,Map.class);
	}

	/**
	 * 接口5：查看票券使用权益--Get
	 */
	public Map getSelectTicketEquity(String ticketsetid){
		HashMap<String ,String> mapParams = new HashMap<String ,String>();
		System.out.println("id>>"+ticketsetid);
		uri = "/TicketList/GetSelectTicketEquity";
		String  id_sign = PublicUtils.cryptMD5_Double(key+ticketsetid+key);
		mapParams = new HashMap<String ,String>();
		mapParams.put("ID", ticketsetid);
		mapParams.put("Sign", id_sign);
		System.out.println("---------------- 接口5：查看票券使用权益--Get---------------------------");
		String resultStr  = HttpUtils.doGet(getFullUrl(uri), mapParams);
		System.out.println("resultStr>>"+resultStr);
		JSONObject jsonObj =JSONObject.parseObject(resultStr);
		return	JSONObject.parseObject(resultStr, Map.class);
		
 	}
	
	/**
	 * 接口6：判断该票券是否绑定使用人--Get
	 * Sign	String	验签
		CustomerCode	String	客户代码
		ShopId	String	门店ID
		SdaduimId	String	场馆ID
		TicketNum	string	票券编号
		
		Sign=md5(md5(3d8a29c49c36cc2f+CustomerCode&TicketNum+3d8a29c49c36cc2f)) 小写

	 */
	public Map getIsBindingPeople(GetIsBindingPeopleParam  getIsBindingPeopleParam){
		String  sign = PublicUtils.cryptMD5_Double(key.concat(getIsBindingPeopleParam.getCustomerCode().concat("&")
				.concat(getIsBindingPeopleParam.getTicketNum())).concat(key));
		
		HashMap<String ,String> mapParams = new HashMap<String ,String>();
		mapParams.put("Sign", sign);
		mapParams.put("CustomerCode", getIsBindingPeopleParam.getCustomerCode());
		mapParams.put("ShopId", getIsBindingPeopleParam.getShopId());
		mapParams.put("SdaduimId", getIsBindingPeopleParam.getSdaduimId());
		mapParams.put("TicketNum", getIsBindingPeopleParam.getTicketNum());
 
		uri = "/TicketSale/GetIsBindingPeople";
		System.out.println("mapParams>>>"+mapParams);
		String resultStr  = HttpUtils.doGet(getFullUrl(uri), mapParams);
		return JSONObject.parseObject(resultStr,  Map.class);
		
	}
	
	/**
	 * 接口7：根据票券号查询该场馆可以使用的票券--Get
		服务方法：/TicketSale/PostUseConsumeTicket

	 */
	public Map postUseConsumeTicket(PostUseConsumeTicketParam  postUseConsumeTicketParam){
		
		String  sign = PublicUtils.cryptMD5_Double(key.concat(postUseConsumeTicketParam.getCustomerCode().concat("&")
				.concat(postUseConsumeTicketParam.getTicketNum())).concat(key));
		
		HashMap<String ,String> mapParams = new HashMap<String ,String>();
		mapParams.put("Sign", sign);
		mapParams.put("CustomerCode", postUseConsumeTicketParam.getCustomerCode());
		mapParams.put("ShopId", postUseConsumeTicketParam.getShopId());
		mapParams.put("SdaduimId", postUseConsumeTicketParam.getSdaduimId());
		mapParams.put("Tel", postUseConsumeTicketParam.getTel());
		mapParams.put("UsePeople", postUseConsumeTicketParam.getUsePeople());
		mapParams.put("TicketNum", postUseConsumeTicketParam.getTicketNum());
		
		uri = "/TicketSale/PostUseConsumeTicket";
		System.out.println("mapParams>>>"+mapParams);
		String resultStr  = HttpUtils.doGet(getFullUrl(uri), mapParams);
		return JSONObject.parseObject(resultStr,  Map.class);
		
	}
	
	/**
	 * 接口8：获取该模块消费可以抵扣的票券-Post
		服务方法：/TicketSale/PostConsumeTicket

	 */
	public Map postConsumeTicket(PostConsumeTicketParam8  postConsumeTicketParam){
		String  sign = PublicUtils.cryptMD5_Double(key.concat(postConsumeTicketParam.getCustomerCode().concat("&")
				.concat(postConsumeTicketParam.getShopId())).concat("&").concat(postConsumeTicketParam.getSdaduimId()).concat(key));
		
		HashMap<String ,String> mapParams = new HashMap<String ,String>();
		mapParams.put("Sign", sign);
		mapParams.put("CustomerCode", postConsumeTicketParam.getCustomerCode());
		mapParams.put("ShopId", postConsumeTicketParam.getShopId());
		mapParams.put("SdaduimId", postConsumeTicketParam.getSdaduimId());
		mapParams.put("ProjectId", postConsumeTicketParam.getProjectId());
		mapParams.put("TotalPrice", postConsumeTicketParam.getTotalPrice());
		mapParams.put("ContactTel", postConsumeTicketParam.getContactTel());
		mapParams.put("UsePeople", postConsumeTicketParam.getUsePeople());
		mapParams.put("TicketNum", postConsumeTicketParam.getTicketNum());
		mapParams.put("CreatePeople", postConsumeTicketParam.getCreatePeople());
		mapParams.put("OrderID", postConsumeTicketParam.getOrderID());
		mapParams.put("TableName", postConsumeTicketParam.getTableName());
		mapParams.put("HandNumber", postConsumeTicketParam.getHandNumber());
		mapParams.put("IfOut", postConsumeTicketParam.getIfOut().toString());
		mapParams.put("WaiterId", postConsumeTicketParam.getWaiterId());
		mapParams.put("OpeningId", postConsumeTicketParam.getOpeningId());

		
		uri = "/TicketSale/PostConsumeTicket";
		System.out.println("mapParams>>>"+mapParams);
		String resultStr  = HttpUtils.doPost(getFullUrl(uri), mapParams);
		return JSONObject.parseObject(resultStr,  Map.class);
		
	}
	
	/**
	 * 接口9：出场-Post
		服务方法：/TicketSale/PostOut
	
	 */
	
	public Map postOut(String id){
		String  sign = PublicUtils.cryptMD5_Double(key.concat(id).concat(key));
		HashMap<String ,String> mapParams = new HashMap<String ,String>();
		mapParams.put("Sign", sign);
		mapParams.put("ID",id);
		
		uri = "/TicketSale/PostOut";
		System.out.println("mapParams>>>"+mapParams);
		String resultStr  = HttpUtils.doPost(getFullUrl(uri), mapParams);
		return JSONObject.parseObject(resultStr,  Map.class);
	}
	
	
	
	/**
	 * 接口10：获取该模块消费可以抵扣的票券-Post
	 * 
	 * Type	String	消费类型 0-会员消费 1-普通消费 
现有客户传0，潜在客户传1
Price	String	消费金额  点击转让获取小计后在获取数据

	 */
	public Map postTicketList(PostTicketListParam postTicketListParam) {
		String  sign = PublicUtils.cryptMD5_Double(key.concat(postTicketListParam.getCustomerCode().concat("&")
					.concat(postTicketListParam.getShopId())).concat("&").concat(postTicketListParam.getSdaduimId())
				.concat("&").concat(postTicketListParam.getTel()).concat(key));
		HashMap<String ,String> mapParams = new HashMap<String ,String>();
		mapParams.put("Sign", sign);
		mapParams.put("CustomerCode", postTicketListParam.getCustomerCode());
		mapParams.put("ShopId", postTicketListParam.getShopId());
		mapParams.put("SdaduimId", postTicketListParam.getSdaduimId());
		mapParams.put("Tel", postTicketListParam.getTel());
		mapParams.put("Type", postTicketListParam.getType());
		mapParams.put("Price", postTicketListParam.getPrice());
		uri = "/TicketSale/PostTicketList";
		System.out.println("mapParams>>>"+mapParams);
		String resultStr  = HttpUtils.doPost(getFullUrl(uri), mapParams);
		return JSONObject.parseObject(resultStr,  Map.class);
		
	}
	
	
	/**
	 * 接口11：会员使用票券抵扣接口--Post
	 */
	
	public Map postConsumeTicket(PostConsumeTicketParam11 postConsumeTicketParam11){
		String  sign = PublicUtils.cryptMD5_Double(key.concat(postConsumeTicketParam11.getCustomerCode().concat("&")
				.concat(postConsumeTicketParam11.getShopId())).concat("&").concat(postConsumeTicketParam11.getSdaduimId()).concat(key));
		
		HashMap<String ,Object> mapParams = new HashMap<String ,Object>();
		mapParams.put("Sign", sign);
		mapParams.put("CustomerCode", postConsumeTicketParam11.getCustomerCode());
		mapParams.put("ShopId", postConsumeTicketParam11.getShopId());
		mapParams.put("SdaduimId", postConsumeTicketParam11.getSdaduimId());
		mapParams.put("TicketList",  JSONArray.parseArray(postConsumeTicketParam11.getTicketList(), Map.class)  ); //前端传json字符串
		mapParams.put("PayWay", postConsumeTicketParam11.getProductID());
		mapParams.put("TotalPrice", postConsumeTicketParam11.getTotalPrice());
		mapParams.put("ProductID", postConsumeTicketParam11.getProductID());
		mapParams.put("OrderID", postConsumeTicketParam11.getOrderID());
		mapParams.put("TableName", postConsumeTicketParam11.getTableName());
		mapParams.put("CreatePeople", postConsumeTicketParam11.getCreatePeople());
		
		uri = "/TicketSale/PostConsumeTicket";
		System.out.println("mapParams>>>"+mapParams);
		String resultStr  = HttpUtils.doPost(getFullUrl(uri), mapParams);
		return JSONObject.parseObject(resultStr,  Map.class);
	}
	/**
	 * 接口12：批量转让用户票券--POST
	 */
	public Map PostTransfer(PostTransferParam postTransferParam) {
		System.out.println("---------------- 接口12：批量转让用户票券---------------------------");
//		String sign_str = "3d8a29c49c36cc2f"+postTransferParam.getTel()+"3d8a29c49c36cc2f";
//		System.out.println("加密前："+sign_str);
		String  id_sign = PublicUtils.cryptMD5_Double(key+postTransferParam.getTel()+key);
		System.out.println("加密后："+id_sign);

		HashMap<String ,String> mapParams = new HashMap<String ,String>();
		mapParams.put("Sign", id_sign);
//		mapParams.put("data",  JSONObject.toJSONString(JSONArray.parseArray(postTransferParam.getData(), Map.class)));
		mapParams.put("data",  JSONArray.parseArray(postTransferParam.getData()).toJSONString());

		mapParams.put("People",postTransferParam.getPeople());
		mapParams.put("Tel",postTransferParam.getTel());
		mapParams.put("MemberCardId",postTransferParam.getMemberCardId());
		mapParams.put("client_id",postTransferParam.getClientId());
		uri = "/TicketSale/PostTransfer";
		System.out.println("mapParams>>>"+mapParams);
		String resultStr  = HttpUtils.doPost(getFullUrl(uri), mapParams);
		System.out.println("resultStr>>>"+resultStr);
		JSONObject jsonObj =JSONObject.parseObject(resultStr);
		System.out.println("---------------- 接口12：批量转让用户票券---------------------------");
		return JSONObject.parseObject(resultStr,  Map.class);
			
		
	}
	

	
	
}
