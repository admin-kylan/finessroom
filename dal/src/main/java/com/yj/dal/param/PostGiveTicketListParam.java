package com.yj.dal.param;

public class PostGiveTicketListParam {
	private String customerCode;  //客户代码
	private String memberCardId;  //会员卡ID
	private String usePeople;   //使用人
	private String contactTel;  //使用人电话
	private String operateName; //操作人姓名 
	private String operateID; //操作人姓名
	private String shopId;   //门店ID
	private String serviceName; //服务人员姓名
	private String serviceTel;  //服务人员手机 
	private String ticketSetList; // 票券列表
	
	/**
	 * 贡献成长操作类型 0正常奖励发放 1将自己的礼物赠送于他人  2 手动  3 自动 
	 * 默认传0 
	 */
	private String 	operateType;
	/**
	 * 消费状态 0普通消费 1 会员消费 3员工领用 2 挂账 4挂单 5协议单位 6 会所赠送
	 * 默认6
	 */
	private String  consumeState;
	private String 	givingID;//传空
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getMemberCardId() {
		return memberCardId;
	}
	public void setMemberCardId(String memberCardId) {
		this.memberCardId = memberCardId;
	}
	public String getUsePeople() {
		return usePeople;
	}
	public void setUsePeople(String usePeople) {
		this.usePeople = usePeople;
	}
	public String getContactTel() {
		return contactTel;
	}
	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}
	public String getOperateName() {
		return operateName;
	}
	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}
	public String getOperateID() {
		return operateID;
	}
	public void setOperateID(String operateID) {
		this.operateID = operateID;
	}
 
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getServiceTel() {
		return serviceTel;
	}
	public void setServiceTel(String serviceTel) {
		this.serviceTel = serviceTel;
	}
	public String getTicketSetList() {
		return ticketSetList;
	}
	public void setTicketSetList(String ticketSetList) {
		this.ticketSetList = ticketSetList;
	}
	public String getOperateType() {
		return operateType;
	}
	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
	public String getConsumeState() {
		return consumeState;
	}
	public void setConsumeState(String consumeState) {
		this.consumeState = consumeState;
	}
	public String getGivingID() {
		return givingID;
	}
	public void setGivingID(String givingID) {
		this.givingID = givingID;
	} //默认传空
	
	
	 
}
