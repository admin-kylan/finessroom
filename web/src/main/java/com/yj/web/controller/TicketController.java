package com.yj.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.common.util.PageUtils;
import com.yj.dal.param.GetIsBindingPeopleParam;
import com.yj.dal.param.PostConsumeTicketParam11;
import com.yj.dal.param.PostConsumeTicketParam8;
import com.yj.dal.param.PostDelTicketParam;
import com.yj.dal.param.PostGiveTicketListParam;
import com.yj.dal.param.PostTicketListParam;
import com.yj.dal.param.PostTransferParam;
import com.yj.dal.param.PostUseConsumeTicketParam;
import com.yj.service.service.ITicketService;


@RestController
@RequestMapping("/ticket")
public class TicketController {
	@Autowired
	private ITicketService ticketService;
	@GetMapping("/getTicketList")
	    public JsonResult getTicketList(String tel ,String code) throws YJException {
		  PageUtils planList =  ticketService.getTicketList(tel,code);
	        if (planList != null) {
	            return JsonResult.success(planList);
	        } else {
	            return JsonResult.fail();
	        }
	    }
	
	@GetMapping("/getTicketSet")
    public JsonResult getTicketSet(String shopid,String customerCode) throws YJException {
	  PageUtils planList =  ticketService.getTicketSet(shopid,customerCode);
        if (planList != null) {
            return JsonResult.success(planList);
        } else {
            return JsonResult.fail();
        }
    }
	
	


	
	
	/**
	 * 接口3：票券赠送接口--Post
	 * @param postGiveTicketListParam
	 * @return
	 * @throws YJException
	 */
	@PostMapping("/postGiveTicketList")
	public JsonResult postGiveTicketList(@RequestBody  PostGiveTicketListParam postGiveTicketListParam)throws YJException{
		Map  resMap = ticketService.postGiveTicketList(postGiveTicketListParam);
		if(resMap.get("Code")==null || !"1".equals(resMap.get("Code")) ) {
			return JsonResult.fail();
		}else {
			 return JsonResult.success(resMap);
		}
	}
	
	
	/**
	 * 接口4：批量删除用户票券--POST
	 * @param postDelTicketParam
	 * @return
	 * @throws YJException
	 */
	@PostMapping("/postDelTicket")
	public JsonResult postDelTicket(@RequestBody PostDelTicketParam postDelTicketParam)  throws YJException{
		
		
		 	if ("1".equals(ticketService.PostDelTicket(postDelTicketParam))) {
	            return JsonResult.success();
	        } else {
	            return JsonResult.fail();
	        }
	}

	
	
	/**
	 * 接口5：查看票券使用权益--Get
	 */
	@GetMapping("/getSelectTicketEquity")
    public JsonResult getSelectTicketEquity(String id) throws YJException {
		Map  resMap =  ticketService.getSelectTicketEquity(id);

		if("1".equals(resMap.get("Code")) ) {
            return JsonResult.success(resMap.get("data"));
        } else {
            return JsonResult.fail();
        }
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
	@GetMapping("/getIsBindingPeople")
	public JsonResult getIsBindingPeople(GetIsBindingPeopleParam  getIsBindingPeopleParam)throws YJException{
		Map  resMap = ticketService.getIsBindingPeople(getIsBindingPeopleParam);
		System.out.println("postTicketList-------------->>>>"+resMap);
		if(resMap.get("Code")==null || !"1".equals(resMap.get("Code")) ) {
			return JsonResult.fail();
		}else {
			 return JsonResult.success(resMap);
		}
	}
	
	
	
	/**
	 * 接口7：根据票券号查询该场馆可以使用的票券--Get
		服务方法：/TicketSale/PostUseConsumeTicket

	 */
	@GetMapping("/postUseConsumeTicket")
	public JsonResult postUseConsumeTicket(PostUseConsumeTicketParam  postUseConsumeTicketParam)throws YJException{
		Map  resMap = ticketService.postUseConsumeTicket(postUseConsumeTicketParam);
		System.out.println("postTicketList-------------->>>>"+resMap);
		if(resMap.get("Code")==null || !"1".equals(resMap.get("Code")) ) {
			return JsonResult.fail();
		}else {
			 return JsonResult.success(resMap);
		}
	}
	
	
	/**
	 * 接口8：获取该模块消费可以抵扣的票券-Post
		服务方法：/TicketSale/PostConsumeTicket

	 */
	@GetMapping("/postConsumeTicket")
	public JsonResult postConsumeTicket(PostConsumeTicketParam8  postConsumeTicketParam)throws YJException{
		Map  resMap = ticketService.postConsumeTicket(postConsumeTicketParam);
		System.out.println("postTicketList-------------->>>>"+resMap);
		if(resMap.get("Code")==null || !"1".equals(resMap.get("Code")) ) {
			return JsonResult.fail();
		}else {
			 return JsonResult.success(resMap);
		}
	}
	
	/**
	 * 接口9：出场-Post
		服务方法：/TicketSale/PostOut
	
	 */
	@GetMapping("/postOut")
	public JsonResult postOut(String id)throws YJException{
		Map  resMap = ticketService.postOut(id);
		System.out.println("postTicketList-------------->>>>"+resMap);
		if(resMap.get("Code")==null || !"1".equals(resMap.get("Code")) ) {
			return JsonResult.fail();
		}else {
			 return JsonResult.success(resMap);
		}
	}
	
	
	
	
	
	
	
	
	
	/**
	 * 接口10：获取该模块消费可以抵扣的票券-Post
	 * 
	 * Type	String	消费类型 0-会员消费 1-普通消费 
现有客户传0，潜在客户传1
Price	String	消费金额  点击转让获取小计后在获取数据

	 */
	@GetMapping("/postTicketList")
	public JsonResult postTicketList(PostTicketListParam postTicketListParam) throws YJException{
		Map  resMap = ticketService.postTicketList(postTicketListParam);
		System.out.println("postTicketList-------------->>>>"+resMap);
		if(resMap.get("Code")==null || !"1".equals(resMap.get("Code")) ) {
			return JsonResult.fail();
		}else {
			 return JsonResult.success(resMap);
		}
	}
	
	
	
	/**
	 * 接口11：会员使用票券抵扣接口--Post
	 * @param postTransferParam
	 * @return
	 * @throws YJException
	 */
	@PostMapping("/postConsumeTicket")
	public JsonResult postConsumeTicket(@RequestBody PostConsumeTicketParam11 postConsumeTicketParam11)  throws YJException{
		Map  resMap = ticketService.postConsumeTicket(postConsumeTicketParam11);
		if(resMap.get("Code")==null || !"1".equals(resMap.get("Code")) ) {
			return JsonResult.fail();
		}else {
			 return JsonResult.success(resMap);
		}
	}
	
	
	/**
	 * 接口12：批量转让用户票券--POST
	 * @param postTransferParam
	 * @return
	 * @throws YJException
	 */
	@PostMapping("/postTransfer")
	public JsonResult postTransfer(@RequestBody PostTransferParam postTransferParam)  throws YJException{
		Map  resMap = ticketService.PostTransfer(postTransferParam);
		if(resMap.get("Code")==null || !"1".equals(resMap.get("Code")) ) {
			return JsonResult.fail();
		}else {
			 return JsonResult.success(resMap);
		}
	}
	
	
	
	
}
