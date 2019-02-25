package com.yj.dal.model;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.yj.dal.dto.CoachDTO;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 客户表
 * </p>
 *
 * @author MP自动生成
 * @since 2018-10-08
 */
@TableName("fr_client")
public class FrClient extends Model<FrClient> {

    private static final long serialVersionUID = 1L;

    /**
     * 客户姓名
     */
    @TableField("client_name")
    private String clientName;
    /**
     * 英文名
     */
    @TableField("english_name")
    private String englishName;
    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 电话号码
     */
    private String tel;
    /**
     * 性别（0：男 1：女）
     */
    private Integer sex;
    /**
     * 身高 CM
     */
    private Integer stature;
    /**
     * 体重 KG
     */
    private Double weight;
    /**
     * 毕业学校
     */
    private String school;
    /**
     * 所学专业
     */
    private String major;
    /**
     * 生日
     */
    @JSONField(format = "yyyy-MM-dd")
    private Date birthday;
    /**
     * 特长
     */
    private String speciality;
    /**
     * 会员等级ID
     */
    @TableField("level_id")
    private String levelId;
    /**
     * 婚姻状况（0：未婚 1：已婚）
     */
    @TableField("marital_status")
    private Boolean maritalStatus;
    /**
     * 证件类型（0：身份证 1：港澳居民来往内地通行证 2：台湾居民来往大陆通行证 3：军人证 4：护照）默认0
     */
    @TableField("id_type")
    private Integer idType;
    /**
     * 证件号码
     */
    @TableField("id_no")
    private String idNo;
    /**
     * 证件地址
     */
    @TableField("id_address")
    private String idAddress;
    /**
     * 家庭住址
     */
    @TableField("home_add")
    private String homeAdd;
    /**
     * 电子邮件
     */
    private String email;
    /**
     * 籍贯
     */
    @TableField("native_place")
    private String nativePlace;
    /**
     * 公司名称
     */
    @TableField("company_name")
    private String companyName;
    /**
     * 职位名称
     */
    @TableField("job_title")
    private String jobTitle;
    /**
     * 公司地址
     */
    @TableField("company_address")
    private String companyAddress;
    /**
     * 经营范围
     */
    @TableField("business_scope")
    private String businessScope;
    /**
     * 车牌号码
     */
    @TableField("car_num")
    private String carNum;
    /**
     * 注意问题
     */
    @TableField("attention_problem")
    private String attentionProblem;
    /**
     * 公司电话
     */
    @TableField("company_tel")
    private String companyTel;
    /**
     * 大约收入
     */
    private Double income;
    /**
     * 车辆品牌
     */
    @TableField("car_brand")
    private String carBrand;
    /**
     * 备注
     */
    private String note;
    /**
     * 推荐人姓名
     */
    @TableField("reference_name")
    private String referenceName;
    /**
     * 推荐人号码
     */
    @TableField("reference_tel")
    private String referenceTel;
    /**
     * 销售顾问姓名
     */
    @TableField("consultant_name")
    private String consultantName;
    /**
     * 销售顾问电话
     */
    @TableField("consultant_tel")
    private String consultantTel;
    /**
     * 服务会籍姓名
     */
    @TableField("fwhj_name")
    private String fwhjName;
    /**
     * 服务会籍电话
     */
    @TableField("fwhj_tel")
    private String fwhjTel;
    /**
     * 保护天数
     */
    @TableField("protect_day")
    private Integer protectDay;
    /**
     * 客户来源
     */
    @TableField("source_id")
    private String sourceId;
    /**
     * 客户来源
     */
    @TableField("customer_source")
    private String customerSource;
    /**
     * 客户入会申请时间
     */
    @TableField("apply_time")
    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    private Date applyTime;
    /**
     * 备用
     */
    private String flag;
    /**
     * 类型(备用)
     */
    private Integer type;
    /**
     * 是否启用(0：不启用 1：启用）默认1
     */
    @TableField("is_using")
    private Boolean isUsing;
    /**
     * 创建人时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    private Date updateTime;
    /**
     * 创建人名称
     */
    @TableField(value = "create_user_name", fill = FieldFill.INSERT)
    private String createUserName;
    /**
     * 更新人名称
     */
    @TableField(value = "update_user_name", fill = FieldFill.INSERT_UPDATE)
    private String updateUserName;
    /**
     * 客户代码
     */
    @TableField(fill = FieldFill.INSERT)
    private String CustomerCode;
    /**
     * 创建人ID
     */
    @TableField(value = "create_user_id", fill = FieldFill.INSERT)
    private String createUserId;
    /**
     * 更新人ID
     */
    @TableField(value = "update_user_id", fill = FieldFill.INSERT_UPDATE)
    private String updateUserId;
    /**
     * 状态[0.正常 1.未付款]
     */
    private Boolean status;
    /**
     * 微信
     */
    private String wechat;
    /**
     * qq
     */
    private String qq;
    /**
     * 客户等级
     */
    @TableField("vip_level")
    private Integer vipLevel;
    /**
     * 购买意向
     */
    @TableField("purchase_will")
    private String purchaseWill;
    /**
     * 意向价格
     */
    @TableField("willing_price")
    private Double willingPrice;
    /**
     * 建档日期
     */
    @TableField("build_date")
    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    private Date buildDate;
    /**
     * 关注问题
     */
    @TableField("caution_question")
    private String cautionQuestion;
    /**
     * 意向卡类别
     */
    @TableField("willing_card_type")
    private String willingCardType;
    /**
     * 意向卡名称
     */
    @TableField("willing_card_name")
    private String willingCardName;
    /**
     * 销售顾问id
     */
    @TableField("consultant_id")
    private String consultantId;
    /**
     * 预售状态
     */
    @TableField("presale_status")
    private String presaleStatus;

    /**
     * 会员密码
     */
    @TableField("client_pass")
    private String clientPass;

    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /*用户头像*/
    @TableField(exist = false)
    private String clientImg;

    /**
     * 客户标识
     */
    private String CustomerMark;

    @TableField(exist = false)
    private FrClientPic clientPic;
    @TableField(exist = false)
    private List<CoachDTO> coachs;

    public String getWillingCardName() {
        return willingCardName;
    }

    public void setWillingCardName(String willingCardName) {
        this.willingCardName = willingCardName;
    }

    public String getClientImg() {
        return clientImg;
    }

    public void setClientImg(String clientImg) {
        this.clientImg = clientImg;
    }

    public List<CoachDTO> getCoachs() {
        return coachs;
    }

    public void setCoachs(List<CoachDTO> coachs) {
        this.coachs = coachs;
    }

    public String getPresaleStatus() {
        return presaleStatus;
    }

    public void setPresaleStatus(String presaleStatus) {
        this.presaleStatus = presaleStatus;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getConsultantId() {
        return consultantId;
    }

    public void setConsultantId(String consultantId) {
        this.consultantId = consultantId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getStature() {
        return stature;
    }

    public void setStature(Integer stature) {
        this.stature = stature;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    public Boolean getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(Boolean maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Integer getIdType() {
        return idType;
    }

    public void setIdType(Integer idType) {
        this.idType = idType;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(String idAddress) {
        this.idAddress = idAddress;
    }

    public String getHomeAdd() {
        return homeAdd;
    }

    public void setHomeAdd(String homeAdd) {
        this.homeAdd = homeAdd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getBusinessScope() {
        return businessScope;
    }

    public void setBusinessScope(String businessScope) {
        this.businessScope = businessScope;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public String getAttentionProblem() {
        return attentionProblem;
    }

    public void setAttentionProblem(String attentionProblem) {
        this.attentionProblem = attentionProblem;
    }

    public String getCompanyTel() {
        return companyTel;
    }

    public void setCompanyTel(String companyTel) {
        this.companyTel = companyTel;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getReferenceName() {
        return referenceName;
    }

    public void setReferenceName(String referenceName) {
        this.referenceName = referenceName;
    }

    public String getReferenceTel() {
        return referenceTel;
    }

    public void setReferenceTel(String referenceTel) {
        this.referenceTel = referenceTel;
    }

    public String getConsultantName() {
        return consultantName;
    }

    public void setConsultantName(String consultantName) {
        this.consultantName = consultantName;
    }

    public String getConsultantTel() {
        return consultantTel;
    }

    public void setConsultantTel(String consultantTel) {
        this.consultantTel = consultantTel;
    }

    public String getFwhjName() {
        return fwhjName;
    }

    public void setFwhjName(String fwhjName) {
        this.fwhjName = fwhjName;
    }

    public String getFwhjTel() {
        return fwhjTel;
    }

    public void setFwhjTel(String fwhjTel) {
        this.fwhjTel = fwhjTel;
    }

    public Integer getProtectDay() {
        return protectDay;
    }

    public void setProtectDay(Integer protectDay) {
        this.protectDay = protectDay;
    }

    public String getCustomerSource() {
        return customerSource;
    }

    public void setCustomerSource(String customerSource) {
        this.customerSource = customerSource;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Boolean getUsing() {
        return isUsing;
    }

    public void setUsing(Boolean isUsing) {
        this.isUsing = isUsing;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public String getCustomerCode() {
        return CustomerCode;
    }

    public void setCustomerCode(String CustomerCode) {
        this.CustomerCode = CustomerCode;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public Integer getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(Integer vipLevel) {
        this.vipLevel = vipLevel;
    }

    public String getPurchaseWill() {
        return purchaseWill;
    }

    public void setPurchaseWill(String purchaseWill) {
        this.purchaseWill = purchaseWill;
    }

    public Double getWillingPrice() {
        return willingPrice;
    }

    public void setWillingPrice(Double willingPrice) {
        this.willingPrice = willingPrice;
    }

    public Date getBuildDate() {
        return buildDate;
    }

    public void setBuildDate(Date buildDate) {
        this.buildDate = buildDate;
    }

    public String getCautionQuestion() {
        return cautionQuestion;
    }

    public void setCautionQuestion(String cautionQuestion) {
        this.cautionQuestion = cautionQuestion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWillingCardType() {
        return willingCardType;
    }

    public void setWillingCardType(String willingCardType) {
        this.willingCardType = willingCardType;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }


    public FrClientPic getClientPic() {
        return clientPic;
    }

    public void setClientPic(FrClientPic clientPic) {
        this.clientPic = clientPic;
    }

    public String getCustomerMark() {
        return CustomerMark;
    }

    public void setCustomerMark(String customerMark) {
        CustomerMark = customerMark;
    }

    public String getClientPass() {
        return clientPass;
    }

    public void setClientPass(String clientPass) {
        this.clientPass = clientPass;
    }

    @Override
    public String toString() {
        return "FrClient{" +
                "clientName='" + clientName + '\'' +
                ", englishName='" + englishName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", tel='" + tel + '\'' +
                ", sex=" + sex +
                ", stature=" + stature +
                ", weight=" + weight +
                ", school='" + school + '\'' +
                ", major='" + major + '\'' +
                ", birthday=" + birthday +
                ", speciality='" + speciality + '\'' +
                ", levelId='" + levelId + '\'' +
                ", maritalStatus=" + maritalStatus +
                ", idType=" + idType +
                ", idNo='" + idNo + '\'' +
                ", idAddress='" + idAddress + '\'' +
                ", homeAdd='" + homeAdd + '\'' +
                ", email='" + email + '\'' +
                ", nativePlace='" + nativePlace + '\'' +
                ", companyName='" + companyName + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", companyAddress='" + companyAddress + '\'' +
                ", businessScope='" + businessScope + '\'' +
                ", carNum='" + carNum + '\'' +
                ", attentionProblem='" + attentionProblem + '\'' +
                ", companyTel='" + companyTel + '\'' +
                ", income=" + income +
                ", carBrand='" + carBrand + '\'' +
                ", note='" + note + '\'' +
                ", referenceName='" + referenceName + '\'' +
                ", referenceTel='" + referenceTel + '\'' +
                ", consultantName='" + consultantName + '\'' +
                ", consultantTel='" + consultantTel + '\'' +
                ", fwhjName='" + fwhjName + '\'' +
                ", fwhjTel='" + fwhjTel + '\'' +
                ", protectDay=" + protectDay +
                ", sourceId='" + sourceId + '\'' +
                ", customerSource='" + customerSource + '\'' +
                ", applyTime=" + applyTime +
                ", flag='" + flag + '\'' +
                ", type=" + type +
                ", isUsing=" + isUsing +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", createUserName='" + createUserName + '\'' +
                ", updateUserName='" + updateUserName + '\'' +
                ", CustomerCode='" + CustomerCode + '\'' +
                ", createUserId='" + createUserId + '\'' +
                ", updateUserId='" + updateUserId + '\'' +
                ", status=" + status +
                ", wechat='" + wechat + '\'' +
                ", qq='" + qq + '\'' +
                ", vipLevel=" + vipLevel +
                ", purchaseWill='" + purchaseWill + '\'' +
                ", willingPrice=" + willingPrice +
                ", buildDate=" + buildDate +
                ", cautionQuestion='" + cautionQuestion + '\'' +
                ", willingCardType='" + willingCardType + '\'' +
                ", consultantId='" + consultantId + '\'' +
                ", presaleStatus='" + presaleStatus + '\'' +
                ", id='" + id + '\'' +
                ", clientImg='" + clientImg + '\'' +
                ", clientPic=" + clientPic +
                ", CustomerMark=" + CustomerMark +
                ", coachs=" + coachs +
                '}';
    }
}
