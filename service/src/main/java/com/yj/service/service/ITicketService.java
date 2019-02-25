package com.yj.service.service;

import com.yj.common.exception.YJException;
import com.yj.common.util.PageUtils;
import com.yj.dal.param.PostDelTicketParam;

public interface ITicketService {
	
	/**
	 * 接口1：客户管理会员票券列表--Get
	 */
	public 		PageUtils 	getTicketList() throws YJException ;
	/**
	 * 接口2：客户管理赠送票券列表--Get
	 */
	public   	PageUtils  	getTicketSet() throws YJException ;
	/**
	 * 接口4：批量删除用户票券--POST
	 */
	public String PostDelTicket(PostDelTicketParam postDelTicketParam) throws YJException ;
}
