package com.yj.dal.param;

public class PostConsumeTicketParam8 {

	private String customerCode; // 客户代码
	private String shopId; // 门店ID
	private String sdaduimId; // 场馆ID
	private String projectId; // 项目ID
	private String totalPrice; // 消费金额
	private String contactTel; // 手机号码
	private String usePeople; // 使用人
	private String ticketNum; // 票券编号
	private String createPeople; // 创建者
	private String orderID; // 订单ID
	private String tableName; // 所关联的表
	private String handNumber; // 手牌号 无则传空
	private Integer ifOut;// 是否出场 0-否 1-出场 没有涉及进出场 传1
	private String waiterId; // String 服务人员ID
	private String openingId; // String 开场ID 无 则传空

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getSdaduimId() {
		return sdaduimId;
	}

	public void setSdaduimId(String sdaduimId) {
		this.sdaduimId = sdaduimId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getContactTel() {
		return contactTel;
	}

	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}

	public String getUsePeople() {
		return usePeople;
	}

	public void setUsePeople(String usePeople) {
		this.usePeople = usePeople;
	}

	public String getTicketNum() {
		return ticketNum;
	}

	public void setTicketNum(String ticketNum) {
		this.ticketNum = ticketNum;
	}

	public String getCreatePeople() {
		return createPeople;
	}

	public void setCreatePeople(String createPeople) {
		this.createPeople = createPeople;
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getHandNumber() {
		return handNumber;
	}

	public void setHandNumber(String handNumber) {
		this.handNumber = handNumber;
	}

	public Integer getIfOut() {
		return ifOut;
	}

	public void setIfOut(Integer ifOut) {
		this.ifOut = ifOut;
	}

	public String getWaiterId() {
		return waiterId;
	}

	public void setWaiterId(String waiterId) {
		this.waiterId = waiterId;
	}

	public String getOpeningId() {
		return openingId;
	}

	public void setOpeningId(String openingId) {
		this.openingId = openingId;
	}

}
