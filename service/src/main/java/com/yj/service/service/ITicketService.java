package com.yj.service.service;

import java.util.List;
import java.util.Map;

import com.yj.common.exception.YJException;
import com.yj.common.util.PageUtils;
import com.yj.dal.param.GetIsBindingPeopleParam;
import com.yj.dal.param.PostConsumeTicketParam11;
import com.yj.dal.param.PostConsumeTicketParam8;
import com.yj.dal.param.PostDelTicketParam;
import com.yj.dal.param.PostGiveTicketListParam;
import com.yj.dal.param.PostTicketListParam;
import com.yj.dal.param.PostTransferParam;
import com.yj.dal.param.PostUseConsumeTicketParam;

public interface ITicketService {

	/**
	 * 接口1：客户管理会员票券列表--Get
	 */
	public PageUtils getTicketList(String tel, String code) throws YJException;

	/**
	 * 接口2：客户管理赠送票券列表--Get
	 */
	public PageUtils getTicketSet(String shopid, String customerCode) throws YJException;

	/**
	 * 接口3：票券赠送接口--Post
	 * 
	 * @param postGiveTicketListParam
	 * @return
	 */
	public Map postGiveTicketList(PostGiveTicketListParam postGiveTicketListParam);

	/**
	 * 接口4：批量删除用户票券--POST
	 */
	public String PostDelTicket(PostDelTicketParam postDelTicketParam) throws YJException;

	/**
	 * 接口5：查看票券使用权益--Get
	 */
	public Map getSelectTicketEquity(String id);

	
	/**
	 * 接口6：判断该票券是否绑定使用人--Get
	 * Sign	String	验签
		CustomerCode	String	客户代码
		ShopId	String	门店ID
		SdaduimId	String	场馆ID
		TicketNum	string	票券编号
		
		Sign=md5(md5(3d8a29c49c36cc2f+CustomerCode&TicketNum+3d8a29c49c36cc2f)) 小写

	 */
	public Map getIsBindingPeople(GetIsBindingPeopleParam  getIsBindingPeopleParam);
	
	
	/**
	 * 接口7：根据票券号查询该场馆可以使用的票券--Get
		服务方法：/TicketSale/PostUseConsumeTicket

	 */
	public Map postUseConsumeTicket(PostUseConsumeTicketParam  postUseConsumeTicketParam);
	
	/**
	 * 接口8：获取该模块消费可以抵扣的票券-Post
		服务方法：/TicketSale/PostConsumeTicket

	 */
	public Map postConsumeTicket(PostConsumeTicketParam8  postConsumeTicketParam);
	/**
	 * 接口9：出场-Post
		服务方法：/TicketSale/PostOut
	
	 */
	
	public Map postOut(String id);
	
	/**
	 * 接口10：获取该模块消费可以抵扣的票券-Post
	 * 
	 * Type String 消费类型 0-会员消费 1-普通消费 现有客户传0，潜在客户传1 Price String 消费金额
	 * 点击转让获取小计后在获取数据
	 * 
	 */
	public Map postTicketList(PostTicketListParam postTicketListParam);

	/**
	 * 接口11：会员使用票券抵扣接口--Post
	 */
	
	public Map postConsumeTicket(PostConsumeTicketParam11 postConsumeTicketParam11);
	/**
	 * 接口12：批量转让用户票券--POST
	 */
	public Map PostTransfer(PostTransferParam postTransferParam);

}
