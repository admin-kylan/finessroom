package com.yj.dal.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author MP自动生成
 * @since 2019-02-18
 */
@TableName("ServiceRecord")
public class ServiceRecord   {

    private static final long serialVersionUID = 1L;

    /**
     * 服务类型  0投诉 1建议 2合作 3采购 4租赁 5生日 6 违规 7回归 
     */
    private String type;
    /**
     * 服务内容
     */
    private String context;
    /**
     * 答复时间
     */
    private Date replyTime;
    /**
     * 处理方式：   0电话、1邮件、2书目、3赔偿、4延期、5补偿
     */
    private String processMethod;
    /**
     * 意见
     */
    private String opinion;
    /**
     * 服务人员
     */
    private String servicePerson1;
    /**
     * 处理日期
     */
    private Date processDate1;
    /**
     * 处理日期
     */
    private String serviceOpinion1;
    /**
     * 服务人员2
     */
    private String servicePerson2;
    /**
     * 处理日期
     */
    private Date processDate2;
    /**
     * 意见
     */
    private String serviceOpinion2;
    /**
     * 被授权人
     */
    private String authorizer;
    /**
     * 日期
     */
    private Date authorizeDate;
    /**
     * 授权结果
     */
    private String authorizeResult;
    
    private String status;
    
    private String customerId;
    
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }

    public String getProcessMethod() {
        return processMethod;
    }

    public void setProcessMethod(String processMethod) {
        this.processMethod = processMethod;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getServicePerson1() {
        return servicePerson1;
    }

    public void setServicePerson1(String servicePerson1) {
        this.servicePerson1 = servicePerson1;
    }

    public Date getProcessDate1() {
        return processDate1;
    }

    public void setProcessDate1(Date processDate1) {
        this.processDate1 = processDate1;
    }

    public String getServiceOpinion1() {
        return serviceOpinion1;
    }

    public void setServiceOpinion1(String serviceOpinion1) {
        this.serviceOpinion1 = serviceOpinion1;
    }

    public String getServicePerson2() {
        return servicePerson2;
    }

    public void setServicePerson2(String servicePerson2) {
        this.servicePerson2 = servicePerson2;
    }

    public Date getProcessDate2() {
        return processDate2;
    }

    public void setProcessDate2(Date processDate2) {
        this.processDate2 = processDate2;
    }

    public String getServiceOpinion2() {
        return serviceOpinion2;
    }

    public void setServiceOpinion2(String serviceOpinion2) {
        this.serviceOpinion2 = serviceOpinion2;
    }

    public String getAuthorizer() {
        return authorizer;
    }

    public void setAuthorizer(String authorizer) {
        this.authorizer = authorizer;
    }

    public Date getAuthorizeDate() {
        return authorizeDate;
    }

    public void setAuthorizeDate(Date authorizeDate) {
        this.authorizeDate = authorizeDate;
    }

    public String getAuthorizeResult() {
        return authorizeResult;
    }

    public void setAuthorizeResult(String authorizeResult) {
        this.authorizeResult = authorizeResult;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

   

    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	@Override
    public String toString() {
        return "ServiceRecord{" +
        ", type=" + type +
        ", context=" + context +
        ", replyTime=" + replyTime +
        ", processMethod=" + processMethod +
        ", opinion=" + opinion +
        ", servicePerson1=" + servicePerson1 +
        ", processDate1=" + processDate1 +
        ", serviceOpinion1=" + serviceOpinion1 +
        ", servicePerson2=" + servicePerson2 +
        ", processDate2=" + processDate2 +
        ", serviceOpinion2=" + serviceOpinion2 +
        ", authorizer=" + authorizer +
        ", authorizeDate=" + authorizeDate +
        ", authorizeResult=" + authorizeResult +
        ", id=" + id +
        "}";
    }
}
