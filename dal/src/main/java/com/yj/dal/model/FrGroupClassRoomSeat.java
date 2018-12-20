package com.yj.dal.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
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
 * @since 2018-12-08
 */
@TableName("fr_group_class_room_seat")
public class FrGroupClassRoomSeat extends Model<FrGroupClassRoomSeat> {

    private static final long serialVersionUID = 1L;

    @TableField("create_user")
    private String createUser;
    @TableField("customer_code")
    private String customerCode;
    @TableField("cnt_row")
    private Integer cntRow;
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    @TableField("is_using")
    private Integer isUsing;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @TableField("seat_num")
    private String seatNum;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    @TableField("class_room_id")
    private String classRoomId;
    @TableField("update_user")
    private String updateUser;
    @TableField("cnt_col")
    private Double cntCol;


    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public Integer getCntRow() {
        return cntRow;
    }

    public void setCntRow(Integer cntRow) {
        this.cntRow = cntRow;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getIsUsing() {
        return isUsing;
    }

    public void setIsUsing(Integer isUsing) {
        this.isUsing = isUsing;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(String seatNum) {
        this.seatNum = seatNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getClassRoomId() {
        return classRoomId;
    }

    public void setClassRoomId(String classRoomId) {
        this.classRoomId = classRoomId;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Double getCntCol() {
        return cntCol;
    }

    public void setCntCol(Double cntCol) {
        this.cntCol = cntCol;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "FrGroupClassRoomSeat{" +
        ", createUser=" + createUser +
        ", customerCode=" + customerCode +
        ", cntRow=" + cntRow +
        ", id=" + id +
        ", isUsing=" + isUsing +
        ", updateTime=" + updateTime +
        ", seatNum=" + seatNum +
        ", createTime=" + createTime +
        ", classRoomId=" + classRoomId +
        ", updateUser=" + updateUser +
        ", cntCol=" + cntCol +
        "}";
    }
}
