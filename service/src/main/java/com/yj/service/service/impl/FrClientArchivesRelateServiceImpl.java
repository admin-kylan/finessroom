package com.yj.service.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.result.JsonResult;
import com.yj.common.util.CookieUtils;
import com.yj.common.util.UUIDUtils;
import com.yj.dal.dao.FrClientArchivesMapper;
import com.yj.dal.dao.FrClientArchivesRelatePicMapper;
import com.yj.dal.dao.FrClientArchivesTypeMapper;
import com.yj.dal.dto.ArchivesDTO;
import com.yj.dal.model.FrClientArchives;
import com.yj.dal.model.FrClientArchivesRelate;
import com.yj.dal.dao.FrClientArchivesRelateMapper;
import com.yj.dal.model.FrClientArchivesRelatePic;
import com.yj.dal.model.FrClientArchivesType;
import com.yj.service.service.IFrClientArchivesRelateService;
import com.yj.service.base.BaseServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 客户档案（皮肤、头发、塑性抗衰 档案）  内容保存 服务实现类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-13
 */
@Service
public class FrClientArchivesRelateServiceImpl extends BaseServiceImpl<FrClientArchivesRelateMapper, FrClientArchivesRelate> implements IFrClientArchivesRelateService {

    @Autowired
    FrClientArchivesTypeMapper frClientArchivesTypeMapper;

    @Autowired
    FrClientArchivesMapper frClientArchivesMapper;

    @Autowired
    FrClientArchivesRelatePicMapper frClientArchivesRelatePicMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JsonResult updateRelate(String[] relateIds, String text, Integer type) throws YJException {
        //校验参数
        if (relateIds == null || type == null || text == null) {
            throw new YJException(YJExceptionEnum.PARAM_ERROR);
        }
        for (String relateId : relateIds) {
            FrClientArchivesRelate frClientArchivesRelate = selectOne(
                    new EntityWrapper<FrClientArchivesRelate>().where("id={0}", relateId));
            if (frClientArchivesRelate.getUsing() == true) {
                frClientArchivesRelate.setUsing(false);
            } else if (frClientArchivesRelate.getUsing() == false) {
                frClientArchivesRelate.setUsing(true);
            }
        }
        JSONObject jsonObject = JSON.parseObject(text);
        if (type == 1) {
            for (int i = 21; i <= 28; i++) {
                if (jsonObject.getString(String.valueOf(i)) != null) {
                    baseMapper.updateText(String.valueOf(type), String.valueOf(i), jsonObject.getString(String.valueOf(i)));
                }
            }
        } else if (type == 2) {
            for (int i = 11; i <= 17; i++) {
                if (jsonObject.getString(String.valueOf(i)) != null) {
                    baseMapper.updateText(String.valueOf(type), String.valueOf(i), jsonObject.getString(String.valueOf(i)));
                }
            }
        } else if (type == 3) {
            for (int i = 9; i <= 14; i++) {
                if (jsonObject.getString(String.valueOf(i)) != null) {
                    baseMapper.updateText(String.valueOf(type), String.valueOf(i), jsonObject.getString(String.valueOf(i)));
                }
            }
        }
        return JsonResult.successMessage("修改成功");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JsonResult saveRelate(String[] typeIds, String text, String cid, Integer type, Date date, HttpServletRequest request) throws YJException {
        //校验参数
        if (typeIds.length <= 0 || cid == null || StringUtils.isEmpty(text) || type == null || date == null) {
            throw new YJException(YJExceptionEnum.PARAM_ERROR);
        }
        String code = CookieUtils.getCookieValue(request, "code", true);
        String name = CookieUtils.getCookieValue(request, "name", true);
        String id = CookieUtils.getCookieValue(request, "id", true);
        //根据时间查询关系表
        FrClientArchives frClientArchives = frClientArchivesMapper.findByTime(date, cid, type);
        if (frClientArchives == null) {
            frClientArchives = new FrClientArchives();
            frClientArchives.setId(UUIDUtils.generateGUID());
            frClientArchives.setSaveDate(date);
            frClientArchives.setClientId(cid);
            frClientArchives.setType(type);
            frClientArchives.setCreateUserName(name);
            frClientArchives.setCreateUserId(id);
            frClientArchives.setCustomerCode(code);
            frClientArchives.setCreateTime(new Date());
            frClientArchivesMapper.insert(frClientArchives);
        }
        List<FrClientArchivesType> frClientArchivesTypes = new ArrayList<>();
        for (String typeId : typeIds) {
            FrClientArchivesType frClientArchivesType = frClientArchivesTypeMapper.selectById(typeId);
            if (frClientArchivesType.getUsing() == true) {
                frClientArchivesTypes.add(frClientArchivesType);
            }
        }
        baseMapper.delete(new EntityWrapper<FrClientArchivesRelate>().where("archives_id={0}", frClientArchives.getId()));

        if (frClientArchivesTypes != null) {
            FrClientArchivesRelate frClientArchivesRelate = new FrClientArchivesRelate();
            //添加文本内容
            JSONObject jsonObject = JSON.parseObject(text);
            if (type == 1) {
                for (int i = 21; i <= 28; i++) {
                    frClientArchivesRelate.setId(UUIDUtils.generateGUID());//主键ID
                    frClientArchivesRelate.setType(i);//子分类ID
                    frClientArchivesRelate.setArchivesId(frClientArchives.getId());//关系表ID
                    frClientArchivesRelate.setArchivesTypeContent(jsonObject.getString(String.valueOf(i)));//分类内容
                    baseMapper.insert(frClientArchivesRelate);
                }
            } else if (type == 2) {
                for (int i = 11; i <= 17; i++) {
                    frClientArchivesRelate.setId(UUIDUtils.generateGUID());//主键ID
                    frClientArchivesRelate.setType(i);//子分类ID
                    frClientArchivesRelate.setArchivesId(frClientArchives.getId());//关系表ID
                    frClientArchivesRelate.setArchivesTypeContent(jsonObject.getString(String.valueOf(i)));//分类内容
                    baseMapper.insert(frClientArchivesRelate);
                }
            } else if (type == 3) {
                for (int i = 9; i <= 14; i++) {
                    frClientArchivesRelate.setId(UUIDUtils.generateGUID());//主键ID
                    frClientArchivesRelate.setType(i);//子分类ID
                    frClientArchivesRelate.setArchivesId(frClientArchives.getId());//关系表ID
                    frClientArchivesRelate.setArchivesTypeContent(jsonObject.getString(String.valueOf(i)));//分类内容
                    baseMapper.insert(frClientArchivesRelate);
                }
            }
            //添加分类内容
            for (FrClientArchivesType frClientArchivesType : frClientArchivesTypes) {
                frClientArchivesRelate.setId(UUIDUtils.generateGUID());//主键ID
                frClientArchivesRelate.setType(frClientArchivesType.getArchivesType());//子分类ID
                frClientArchivesRelate.setArchivesTypeId(frClientArchivesType.getId());//父分类ID
                frClientArchivesRelate.setArchivesTypeContent(frClientArchivesType.getArchivesName());//分类内容
                frClientArchivesRelate.setArchivesId(frClientArchives.getId());//关系表ID
                baseMapper.insert(frClientArchivesRelate);
            }
//            //保存图片路径
//            StringBuffer imagePath = new StringBuffer(CookieUtils.getCookieValue(request, "url", true));
//            imagePath.append(CookieUtils.getCookieValue(request, "imgPath", true));
//            FrClientArchivesRelatePic frClientArchivesRelatePic = new FrClientArchivesRelatePic();
//            frClientArchivesRelatePic.setPicType(1);
//            frClientArchivesRelatePic.setPicState(true);
//            frClientArchivesRelatePic.setCreateTime(new Date());
//            for (String imgUrl : imagesList) {
//                frClientArchivesRelatePic.setId(UUIDUtils.generateGUID());
//                frClientArchivesRelatePic.setPicLink(imagePath.append(imgUrl).toString());
//                frClientArchivesRelatePic.setPicType(type);
//                frClientArchivesRelatePic.setArchivesId(frClientArchives.getId());
//                frClientArchivesRelatePic.setCreateTime(new Date());
//                frClientArchivesRelatePicMapper.insert(frClientArchivesRelatePic);
//            }

            return JsonResult.successMessage("保存成功");
        } else {
            return JsonResult.failMessage("保存失败");
        }
    }

    @Override
    public ArchivesDTO getRelate(Date date, String cid, Integer type) throws YJException {
        //校验参数
        if (date == null || cid == null || type == null) {
            throw new YJException(YJExceptionEnum.PARAM_ERROR);
        }
        ArchivesDTO archivesDTO = new ArchivesDTO();
        List<FrClientArchivesRelate> frClientArchivesRelates = baseMapper.getRelate(date, cid, type);
        List<FrClientArchivesRelatePic> frClientArchivesRelatePics = frClientArchivesRelatePicMapper.getRelatePic(date, cid, type);
        archivesDTO.setFrClientArchivesRelatePics(frClientArchivesRelatePics);
        archivesDTO.setFrClientArchivesRelates(frClientArchivesRelates);
        return archivesDTO;
    }
}
