package com.yj.dal.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 体能测试图片保存
 * </p>
 *
 * @author MP自动生成
 * @since 2018-12-10
 */
@TableName("fr_client_physical_test_pic")
public class FrClientPhysicalTestPic extends Model<FrClientPhysicalTestPic> {

    private static final long serialVersionUID = 1L;

    /**
     * 体能测试id
     */
    @TableField("fr_client_physical_test_id")
    private String frClientPhysicalTestId;
    /**
     * 体能测试图片
     */
    @TableField("pic_link")
    private String picLink;
    @TableId(value = "id", type = IdType.UUID)
    private String id;


    public String getFrClientPhysicalTestId() {
        return frClientPhysicalTestId;
    }

    public void setFrClientPhysicalTestId(String frClientPhysicalTestId) {
        this.frClientPhysicalTestId = frClientPhysicalTestId;
    }

    public String getPicLink() {
        return picLink;
    }

    public void setPicLink(String picLink) {
        this.picLink = picLink;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "FrClientPhysicalTestPic{" +
        ", frClientPhysicalTestId=" + frClientPhysicalTestId +
        ", picLink=" + picLink +
        ", id=" + id +
        "}";
    }
}
