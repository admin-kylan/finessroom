package com.yj.common.util;

public class PageUtil<T> {
    private String page="1";//页数
    private String rows="15";//显示条数
    private Integer pages;
    private Integer offset;
    private T condition;
    private Integer userId;
    private Integer type;
    private Integer thirdId;
    private String title;
    private String phone;
    private String clientId;
    private String code;
    private String clientName ;  //用户姓名
    private String  mobile; //用户手机号
    //与code字段一样，之后请尽量使用CustomerCode 字段，与大部分表的字段名称对应
    private String CustomerCode;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getRows() {
        return rows;
    }

    public void setRows(String rows) {
        this.rows = rows;
    }

//    public Integer getPages(int counts){
//        pages = counts / rows;
//        if (counts % rows != 0) {
//            pages++;
//        }
//        if (page > pages) {
//            page = pages;
//        }
//        if (page < 1) {
//            page = 1;
//        }
//        offset = (page - 1) * rows;
//        return pages;
//    }

    public Integer getOffset(){
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public T getCondition() {
        return condition;
    }

    public void setCondition(T condition) {
        this.condition = condition;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCustomerCode() {
        return CustomerCode;
    }

    public void setCustomerCode(String customerCode) {
        CustomerCode = customerCode;
    }

    @Override
    public String toString() {
        return "PageUtil{" +
                "page='" + page + '\'' +
                ", rows='" + rows + '\'' +
                ", pages=" + pages +
                ", offset=" + offset +
                ", condition=" + condition +
                ", userId=" + userId +
                ", type=" + type +
                ", thirdId=" + thirdId +
                ", title='" + title + '\'' +
                ", phone='" + phone + '\'' +
                ", clientId='" + clientId + '\'' +
                ", code='" + code + '\'' +
                ", CustomerCode='" + CustomerCode + '\'' +
                '}';
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getThirdId() {
        return thirdId;
    }

    public void setThirdId(Integer thirdId) {
        this.thirdId = thirdId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
    
    
}
