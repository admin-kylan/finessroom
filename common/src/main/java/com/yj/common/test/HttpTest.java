package com.yj.common.test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yj.common.util.HttpUtils;
import com.yj.common.util.PublicUtils;

public class HttpTest {
	
	final static  String key = "3d8a29c49c36cc2f"; 
	final static String TEL = "13645945925";  //13645945925 
	final static String CUSTOMERCODE = "003"; //CustomerCode
	final static String SHOPID="0";  //ShopId
	final static  String  TEL_SIGN = PublicUtils.cryptMD5_Double("3d8a29c49c36cc2f"+TEL+"3d8a29c49c36cc2f" );  //
	 //crypt(crypt("3d8a29c49c36cc2f"+TEL+"3d8a29c49c36cc2f"));
	final static  String CUSTOMERCODE_SIGN = PublicUtils.cryptMD5_Double("3d8a29c49c36cc2f"+CUSTOMERCODE+"3d8a29c49c36cc2f");
			// crypt(crypt("3d8a29c49c36cc2f"+CUSTOMERCODE+"3d8a29c49c36cc2f"));
	final static  String CUSTOMERCODE_TEL_SGIN = PublicUtils.cryptMD5_Double("3d8a29c49c36cc2f"+CUSTOMERCODE+"&"+TEL+"3d8a29c49c36cc2f");
	static String uri   ;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String pinfix_url = "https://txc.4006337366.com/index.php/Api/";
	
//		String tel = "13645945925";  //13645945925 
//		String customerCode = "003"; //CustomerCode
//		String shopId="0";  //ShopId
//		String  tel_sign = crypt(crypt("3d8a29c49c36cc2f"+tel+"3d8a29c49c36cc2f"));
//		String  customerCode_sign = crypt(crypt("3d8a29c49c36cc2f"+customerCode+"3d8a29c49c36cc2f"));
//		
//		
//		HashMap<String ,String> mapParams = new HashMap<String ,String>();
//		
//		System.out.println("tel: "+tel);
//		System.out.println("customerCode: "+customerCode);
//		System.out.println("tel_sign: "+tel_sign);
//		System.out.println("customerCode_sign: "+customerCode_sign);
//		/**
//		 * 接口1：客户管理会员票券列表--Get
//		 */
//		mapParams.put("Sign", tel_sign);
//		mapParams.put("Tel", tel);
//		mapParams.put("CustomerCode", customerCode);
//		System.out.println(mapParams);
//		String uri ="/TicketSale/GetTicketList";
//		//System.out.println("----------------接口1：客户管理会员票券列表--Get----------------------------");
//		System.out.println(HttpUtils.doGet(getUrl(uri),  mapParams));	
//		/**
//		 * 接口2：客户管理赠送票券列表--Get
//		 */
//		mapParams = new HashMap<String ,String>();
//		mapParams.put("Sign", customerCode_sign);
//		mapParams.put("CustomerCode", customerCode);
//		mapParams.put("ShopId", shopId);
//		uri ="/TicketSale/GetTicketSet";
////		System.out.println("----------------接口2：客户管理赠送票券列表--Get----------------------------");
////		System.out.println(HttpUtils.doGet(getUrl(uri), mapParams));
////		//{"Code":"1","Msg":"Success","TicketData":[{"id":"5c65619276afa791","shopid":"865f76b124329a6b","seriesname":"跆拳道短期券","row_number":"1"}]}
//
//		
//		
//		//  12f4eef7cf5619ac 会员卡号
//		/**
//		 * 接口3： 票券赠送接口--Post
//		 */
//		List TicketSetList = new ArrayList();
//		
//		HashMap<String ,String>	TicketSet  = new HashMap<String ,String>();
//		TicketSet.put("ID", "5c65619276afa791");
//		TicketSet.put("TicketSendWhy", "1111");
//		TicketSet.put("Num", "1");
//		TicketSetList.add(TicketSet);
//		
//		HashMap<String ,Object>	dataParams = new HashMap<String ,Object>();
//		dataParams.put("CustomerCode", customerCode);
//		dataParams.put("MemberCardId", "12f4eef7cf5619ac");
//		dataParams.put("UsePeople", "003");
//		dataParams.put("ContactTel", tel);
//		dataParams.put("OperateName", "1234");
//		dataParams.put("OperateID", "1234");
//		dataParams.put("OperateType", "0");
//		dataParams.put("ConsumeState", "0");
//		dataParams.put("GivingID", "");
//		dataParams.put("ShopId", shopId);
//		dataParams.put("ServiceName", "1234");
//		dataParams.put("ServiceTel", "13645945925");
//		dataParams.put("TicketSetList", TicketSetList);
//		
//		System.out.println("---------------票券赠送接口--Post----------------------------begin");
//		System.out.println("dataParams：|"+JSONObject.toJSONString(dataParams)+"|");
//		mapParams = new HashMap<String ,String>();
//	//	3d8a29c49c36cc2f
//		String sign = crypt(crypt("3d8a29c49c36cc2f"+customerCode+"&"+tel+"3d8a29c49c36cc2f"));
//		System.out.println("sign："+sign);
//		mapParams.put("Sign", sign);
//		mapParams.put("data", JSONObject.toJSONString(dataParams));
//		uri = "/TicketSale/PostGiveTicketList";
//		System.out.println(HttpUtils.doPost(getUrl(uri), mapParams));
//		System.out.println("---------------票券赠送接口--Post----------------------------end");
//		
//		
//		/**
//		 * 接口5：查看票券使用权益--Get
//		 */
//		uri = "/TicketList/GetSelectTicketEquity";
//		String id ="5c65619276afa791";
//		String  id_sign = crypt(crypt("3d8a29c49c36cc2f"+id+"3d8a29c49c36cc2f"));
//		mapParams = new HashMap<String ,String>();
//		mapParams.put("ID", id);
//		mapParams.put("Sign", id_sign);
//		System.out.println("---------------- 接口5：查看票券使用权益--Get---------------------------");
////		System.out.println(HttpUtils.doGet(getUrl(uri), mapParams));
//		
		
		/**
		 * 接口1：客户管理会员票券列表--Get
		 */
		//getTicketList();
		/**
		 * 接口2：客户管理赠送票券列表--Get
		 */
		//getTicketSet();
		
//		 PostDelTicket();
		/**
		 * 接口3： 票券赠送接口--Post
		 */
	  // postGiveTicketList();
	   
	   /**
		 * 接口5：查看票券使用权益--Get
		 */
//	   getSelectTicketEquity();
		
//		String sql = "[{ \"ID\":5c418c8876696472,\"TicketSendWhy\":消费推荐,\"Num\":1}]";
//		System.out.println(  sql.replaceAll("\\\\", ""));

	   
	   PostTicketListParam postTicketListParam = new PostTicketListParam();
	   postTicketListParam.setCustomerCode("003");
	   postTicketListParam.setShopId("0");
	   postTicketListParam.setSdaduimId("11d10a835160cfe3");//1124fdf37cee9191  10c05bec9a74681f
	   postTicketListParam.setPrice("100");
	   postTicketListParam.setTel("13645945925");
	   postTicketListParam.setType("0");;
	   postTicketList(postTicketListParam);

	}

	
	
	public static void postTicketList(PostTicketListParam postTicketListParam) {
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
		String resultStr  = HttpUtils.doPost(getUrl(uri), mapParams);
		System.out.println(resultStr);
	 ///	return JSONObject.parseObject(resultStr,  Map.class);
		
	}
	
	
	/**
	 * 接口1：客户管理会员票券列表--Get
	 */
	public static  void getTicketList(){
		
		HashMap<String ,String> mapParams = new HashMap<String ,String>();
//		System.out.println("tel: "+TEL);
//		System.out.println("customerCode: "+CUSTOMERCODE);
//		System.out.println("tel_sign: "+TEL_SIGN);
//		System.out.println("customerCode_sign: "+CUSTOMERCODE_SIGN);
		/**
		 * 接口1：客户管理会员票券列表--Get
		 */
		mapParams.put("Sign", TEL_SIGN);
		mapParams.put("Tel", TEL);
		mapParams.put("CustomerCode", CUSTOMERCODE);
		System.out.println(mapParams);
		uri ="/TicketSale/GetTicketList";
		System.out.println("----------------接口1：客户管理会员票券列表--Get----------------------------");
//		System.out.println(HttpUtils.doGet(getUrl(uri),  mapParams));	
		String resultStr = HttpUtils.doGet(getUrl(uri),  mapParams);
		System.out.println(resultStr);
		JSONObject jsonoObj =  JSONObject.parseObject(resultStr);
		JSONArray jsonArr =	jsonoObj.getJSONArray("TicketList");
		for (Object object : jsonArr) {
			System.out.println(">"+JSONObject.parseObject(object.toString()));
		}
		
		List<Map> list = JSONObject.parseArray(jsonArr.toJSONString(), Map.class);
		System.out.println("list>>"+list);
		
		
	}
	
	/**
	 * 接口2：客户管理赠送票券列表--Get
	 */
	
	public static void getTicketSet(){
		HashMap<String ,String> mapParams = new HashMap<String ,String>();
		mapParams = new HashMap<String ,String>();
		mapParams.put("Sign", CUSTOMERCODE_SIGN);
		mapParams.put("CustomerCode", CUSTOMERCODE);
		mapParams.put("ShopId", SHOPID);
		uri ="/TicketSale/GetTicketSet";
		System.out.println("----------------接口2：客户管理赠送票券列表--Get----------------------------");
	//	System.out.println(HttpUtils.doGet(getUrl(uri), mapParams));
		//{"Code":"1","Msg":"Success","TicketData":[{"id":"5c65619276afa791","shopid":"865f76b124329a6b","seriesname":"跆拳道短期券","row_number":"1"}]}
		
		String resultStr  =HttpUtils.doGet(getUrl(uri), mapParams);
		System.out.println(resultStr);
		JSONObject jsonoObj =  JSONObject.parseObject(resultStr);
		
 		JSONArray jsonArr =	jsonoObj.getJSONArray("TicketData");
 		List ticketDatas  = new ArrayList();
 		HashMap ticketData ;
 		for (Object object : jsonArr) {
 			if(object!=null) {
 				JSONObject ticketJson =JSONObject.parseObject(object.toString());
// 				ticket = new HashMap();
// 				ticket.put("id", value);
 				System.out.println("----------------------------");
 				System.out.println(">>>"+ticketJson.getString("id"));
 				System.out.println(">>>"+ticketJson.getString("shopid"));
 				System.out.println(">>>"+ticketJson.getString("seriesname"));
 				jsonoObj.getJSONArray("TicketList");
 				System.out.println(">>>"+ticketJson);
 				
 			}
 		
		}
//		List<Map> list = JSONObject.parseArray(jsonArr.toJSONString(), Map.class);
//		System.out.println("list>>"+list);

	}
	
	
	
	
	/**
	 * 接口3： 票券赠送接口--Post
	 */
	
	public static void postGiveTicketList(){
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
		dataParams.put("ConsumeState", "0");
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
		System.out.println("mapParams>>>"+mapParams);
		uri = "/TicketSale/PostGiveTicketList";
		//System.out.println(HttpUtils.doPost(getUrl(uri), mapParams));
		System.out.println("---------------票券赠送接口--Post----------------------------end"); 
		// {"Code":"1","Msg":"赠送成功"}

	}
	
	
	/**
	 * 接口4：批量删除用户票券--POST
	 */
	public static void PostDelTicket(){
		HashMap<String ,String> mapParams = new HashMap<String ,String>();

		uri = "/TicketSale/PostDelTicket";
		String id ="5c418c8876696472";
		
		HashMap params = new HashMap<String ,String>();
		params.put("ID", "");
		params.put("CustomerCode", "5c418c8876696472");
		params.put("TicketNum", "5c418c8876696472");
		params.put("TicketName", "5c418c8876696472");
		
		String  id_sign = crypt(crypt("3d8a29c49c36cc2f"+"3d8a29c49c36cc2f"));
		mapParams = new HashMap<String ,String>();
		mapParams.put("Sign", id_sign);
		mapParams.put("data", params.toString());
		System.out.println("---------------- 接口4：批量删除用户票券--POST---------------------------");
		System.out.println(HttpUtils.doPost(getUrl(uri), mapParams));
		
		//{"Code":"1","Msg":"删除成功！"}

		
	}
	
	
	/**
	 * 接口5：查看票券使用权益--Get
	 */
	public static void getSelectTicketEquity(){
		HashMap<String ,String> mapParams = new HashMap<String ,String>();

		uri = "/TicketList/GetSelectTicketEquity";
		String id ="5c418c88a3560119";
		String  id_sign = crypt(crypt("3d8a29c49c36cc2f"+id+"3d8a29c49c36cc2f"));
		mapParams = new HashMap<String ,String>();
		mapParams.put("ID", id);
		mapParams.put("Sign", id_sign);
		System.out.println("---------------- 接口5：查看票券使用权益--Get---------------------------");
		System.out.println(HttpUtils.doGet(getUrl(uri), mapParams));
		
		//JSONObject jsonoObj =  JSONObject.parseObject(HttpUtils.doGet(getUrl(uri), mapParams));
		//jsonoObj.getJSONArray(data)
		
	}
	
	

	
	public static String getUrl(String uri ){
		String pinfix_url = "https://txc.4006337366.com/index.php/Api";
		System.out.println("URL : "+pinfix_url.concat(uri));

		return pinfix_url.concat(uri);
	}
	
	
	
	
	
	public static String crypt(String str) {
		if (str == null || str.length() == 0) {
			throw new IllegalArgumentException("String to encript cannot be null or zero length");
		}
		StringBuffer hexString = new StringBuffer();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte[] hash = md.digest();
			for (int i = 0; i < hash.length; i++) {
				if ((0xff & hash[i]) < 0x10) {
					hexString.append("0" + Integer.toHexString((0xFF & hash[i])));
				} else {
					hexString.append(Integer.toHexString(0xFF & hash[i]));
				}
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return hexString.toString();
	}

 
}
