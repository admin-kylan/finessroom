package com.yj.service.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.result.JsonResult;
import com.yj.common.util.HttpServletUtils;
import com.yj.common.util.UUIDUtils;
import com.yj.dal.model.FrClientFamily;
import com.yj.dal.dao.FrClientFamilyMapper;
import com.yj.service.service.IFrClientFamilyService;
import com.yj.service.base.BaseServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 客户家庭亲子关系 服务实现类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-10
 */
@Service
public class FrClientFamilyServiceImpl extends BaseServiceImpl<FrClientFamilyMapper, FrClientFamily> implements IFrClientFamilyService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<FrClientFamily> getFamily(String clientId) throws YJException {
        //获取客户代码
        String customerCode = HttpServletUtils.getCustmerCode();
        if (StringUtils.isEmpty(customerCode)) {
            throw new YJException(YJExceptionEnum.CUSTOMERCODE_NOT_FOUND);
        }
        List<FrClientFamily> frClientFamilies = selectList(
                new EntityWrapper<FrClientFamily>().where("is_using={0} and client_id={1}", 1, clientId).where("CustomerCode={0}", customerCode)
        );
        return frClientFamilies;
    }

    @Override
    public JsonResult updateFamily(FrClientFamily frClientFamily, String cid) throws YJException {
        //校验参数
        if (frClientFamily.getTel() == null || frClientFamily.getHomeAdd() == null || frClientFamily.getWorkUnit() == null
                || frClientFamily.getName() == null || frClientFamily.getPosition() == null || frClientFamily.getRelations() == null) {
            throw new YJException(YJExceptionEnum.PARAM_ERROR);
        }
        if (frClientFamily.getId() == null) {
            //添加
            frClientFamily.setId(UUIDUtils.generateGUID());
            frClientFamily.setClientId(cid);
            boolean insert = insert(frClientFamily);
            log.info("修改家庭关系[{}]", insert);
            if (insert) {
                return JsonResult.successMessage("保存成功");
            } else {
                return JsonResult.failMessage("保存失败");
            }
        } else {
            boolean b = updateById(frClientFamily);
            log.info("修改家庭关系[{}]", b);
            if (b) {
                return JsonResult.successMessage("修改成功");
            } else {

                return JsonResult.failMessage("修改成功");
            }
        }

    }

    @Override
    public JsonResult delFamily(String id) {
        //查找客户是否存在
        FrClientFamily frClientFamily = selectById(id);
        if (frClientFamily != null) {
            frClientFamily.setUsing(false);
            boolean b = updateById(frClientFamily);
            if (b) {
                return JsonResult.successMessage("删除成功");
            }
        }
        return JsonResult.failMessage("删除失败");
    }


}