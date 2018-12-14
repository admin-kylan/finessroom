package com.yj.dal.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 潜在客户跟进图片表
 * </p>
 *
 * @author MP自动生成
 * @since 2018-12-10
 */
@TableName("fr_latence_follow_pic")
public class FrLatenceFollowPic extends Model<FrLatenceFollowPic> {

    private static final long serialVersionUID = 1L;

    /**
     * 潜在客户跟进表id
     */
    @TableField("latence_follow_id")
    private String latenceFollowId;
    /**
     * 跟进记录图片
     */
    @TableField("follow_mark_pic")
    private String followMarkPic;
    @TableId(value = "id", type = IdType.UUID)
    private String id;


    public String getLatenceFollowId() {
        return latenceFollowId;
    }

    public void setLatenceFollowId(String latenceFollowId) {
        this.latenceFollowId = latenceFollowId;
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
        return "FrLatenceFollowPic{" +
        ", latenceFollowId=" + latenceFollowId +
        ", followMarkPic=" + followMarkPic +
        ", id=" + id +
        "}";
    }
}
