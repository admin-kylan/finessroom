package com.yj.service.service.impl;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.result.JsonResult;
import com.yj.common.util.CookieUtils;
import com.yj.common.util.HttpServletUtils;
import com.yj.common.util.UUIDUtils;
import com.yj.dal.dao.*;
import com.yj.dal.dto.CoachDTO;
import com.yj.dal.model.*;
import com.yj.service.base.BaseServiceImpl;
import com.yj.service.service.IFrPersonalDetailsService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 基础档案 服务实现类
 */
@Service
public class FrPersonalDetailsServiceImpl extends BaseServiceImpl<FrPersonalDetailsMapper, FrClient> implements IFrPersonalDetailsService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    FrClientMapper frClientMapper;

    @Autowired
    FrClientPersonnelRelateMapper frClientPersonnelRelateMapper;

    @Autowired
    PersonnelInfoMapper personnelInfoMapper;

    @Autowired
    RoleInfoMapper roleInfoMapper;

    @Autowired
    FrClientPicMapper frClientPicMapper;

    /**
     * 获得基础档案信息
     *
     * @param id
     * @return
     */
    @Override
    public JsonResult getPersonalDetails(String id) throws YJException {
        //校验参数
        if (id == null) {
            throw new YJException(YJExceptionEnum.PARAM_ERROR);
        }
        //获取客户代码
        String custmerCode = HttpServletUtils.getCustmerCode();
        if (StringUtils.isEmpty(custmerCode)) {
            throw new YJException(YJExceptionEnum.CUSTOMERCODE_NOT_FOUND);
        }
        //查询客户信息
        FrClient client = selectOne(
                new EntityWrapper<FrClient>().where("id={0}", id).where("CustomerCode={0}", custmerCode));
        if (client != null) {
            //查询客户头像
            FrClientPic frClientPic = frClientPicMapper.getClientImg(id);
            log.info("客户图片[{}]", frClientPic);
            if (frClientPic != null) {
                client.setClientImg(frClientPic.getPicLink());
            }
            List<FrClientPersonnelRelate> relateList =
                    frClientPersonnelRelateMapper.selectList(
                            new EntityWrapper<FrClientPersonnelRelate>().where("client_id={0}", client.getId()));
            List<CoachDTO> coachDTOList = new ArrayList<>();
            if (relateList != null) {
                for (int i = 0; i < relateList.size(); i++) {
                    RoleInfo roleInfo = roleInfoMapper.selectById(relateList.get(i).getRoleId());
                    PersonnelInfo personnelInfo = personnelInfoMapper.selectById(relateList.get(i).getPersonalId());
                    CoachDTO coachDTO = new CoachDTO();
                    coachDTO.setFirstName(roleInfo.getFirstName());
                    coachDTO.setRolRoleName(roleInfo.getRolRoleName());
                    coachDTO.setPhone(personnelInfo.getMobile());
                    coachDTO.setRoleId(roleInfo.getId());
                    coachDTO.setPersonalId(personnelInfo.getId());
                    coachDTOList.add(coachDTO);
                }
            }
            client.setCoachs(coachDTOList);
            log.info("客户[{}]", client);
            return JsonResult.success(client);
        } else {
            return JsonResult.failMessage("查询失败");
        }

    }

    /**
     * 修改基础档案
     *
     * @param frClient
     * @param request
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public JsonResult updatePersonalDetails(FrClient frClient, HttpServletRequest request) throws YJException {

        //校验参数
        if (frClient.getClientName() == null || frClient.getMobile() == null || frClient.getSex() == null || frClient.getConsultantId() == null) {
            throw new YJException(YJExceptionEnum.PARAM_ERROR);
        }
        Integer update = frClientMapper.updateById(frClient);
        log.info("修改基础档案[{}]", SqlHelper.retBool(update));
        //获取教练与人员ID
        List<CoachDTO> coachs = frClient.getCoachs();
        if (coachs != null) {
            //获取门店Id
            String shopid = CookieUtils.getCookieValue(request, "shopid", true);
            //保存前先把该客户关联的关系删除
            frClientPersonnelRelateMapper.delete(
                    new EntityWrapper<FrClientPersonnelRelate>().where("client_id={0}", frClient.getId()));

            for (CoachDTO coachDTO : coachs) {
                FrClientPersonnelRelate frClientPersonnelRelate = new FrClientPersonnelRelate();
                frClientPersonnelRelate.setId(UUIDUtils.generateGUID());//主键ID
                frClientPersonnelRelate.setClientId(frClient.getId());//客户ID
                frClientPersonnelRelate.setRoleId(coachDTO.getRoleId());//角色ID
                frClientPersonnelRelate.setPersonalId(coachDTO.getPersonalId());//人员ID
                frClientPersonnelRelate.setShopId(shopid);//门店ID
                frClientPersonnelRelate.setCustomerCode(frClient.getCustomerCode());//客户代码
                frClientPersonnelRelate.setUsing(true);//启用
                frClientPersonnelRelateMapper.insert(frClientPersonnelRelate);
            }
            return JsonResult.successMessage("修改成功");
        } else {
            return JsonResult.failMessage("修改失败");
        }
    }
}
