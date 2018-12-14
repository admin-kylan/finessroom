package com.yj.dal.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 跟进表的图片
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-01
 */
@TableName("fr_follow_pic")
public class FrFollowPic extends Model<FrFollowPic> {

    private static final long serialVersionUID = 1L;

    /**
     * 现有用户跟进表id
     */
    @TableField("employee_client_follow_id")
    private String employeeClientFollowId;
    /**
     * 跟进记录图片
     */
    @TableField("follow_mark_pic")
    private String followMarkPic;
    @TableId(value = "id", type = IdType.UUID)
    private String id;


    public String getEmployeeClientFollowId() {
        return employeeClientFollowId;
    }

    public void setEmployeeClientFollowId(String employeeClientFollowId) {
        this.employeeClientFollowId = employeeClientFollowId;
    }

    public String getFollowMarkPic() {
        return followMarkPic;
    }

    public void setFollowMarkPic(String followMarkPic) {
        this.followMarkPic = followMarkPic;
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
        return "FrFollowPic{" +
        ", employeeClientFollowId=" + employeeClientFollowId +
        ", followMarkPic=" + followMarkPic +
        ", id=" + id +
        "}";
    }
}
