package com.yj.dal.param;

public class PostConsumeTicketParam11 {

	
 
	private String customerCode	;	//客户代码
	private String  shopId	 ;	//门店ID
	private String  sdaduimId	; 	//场馆ID
	private String ticketList	; 	//可以使用的票券字符串 Json 形式
	private String payWay	 ;	//消费类型 0-会员消费 1-普通消费
	private String totalPrice	; 	//消费金额
	private String productID	; 	//消耗所需要的ID，多个ID 用,隔开
	private String orderID	 	;//订单ID
	private String tableName	; 	//所关联的数据库表
	private String createPeople	 ;	//创建人
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
	public String getTicketList() {
		return ticketList;
	}
	public void setTicketList(String ticketList) {
		this.ticketList = ticketList;
	}
	public String getPayWay() {
		return payWay;
	}
	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
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
	public String getCreatePeople() {
		return createPeople;
	}
	public void setCreatePeople(String createPeople) {
		this.createPeople = createPeople;
	}
	
	

}
