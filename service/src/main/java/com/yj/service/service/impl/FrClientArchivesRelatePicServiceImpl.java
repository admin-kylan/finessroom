package com.yj.service.service.impl;

import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.result.JsonResult;
import com.yj.common.util.CookieUtils;
import com.yj.common.util.UUIDUtils;
import com.yj.dal.dao.FrClientArchivesMapper;
import com.yj.dal.model.FrClientArchives;
import com.yj.dal.model.FrClientArchivesRelatePic;
import com.yj.dal.dao.FrClientArchivesRelatePicMapper;
import com.yj.service.service.IFrClientArchivesRelatePicService;
import com.yj.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 客户档案（皮肤、头发、塑性抗衰 档案）  内容保存图片 服务实现类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-13
 */
@Service
public class FrClientArchivesRelatePicServiceImpl extends BaseServiceImpl<FrClientArchivesRelatePicMapper, FrClientArchivesRelatePic> implements IFrClientArchivesRelatePicService {


    @Autowired
    FrClientArchivesMapper frClientArchivesMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JsonResult delPic(String[] pids, String filePath) throws YJException {
        //校验参数
        if (pids == null) {
            throw new YJException(YJExceptionEnum.PARAM_ERROR);
        }
        for (String pid : pids) {
            FrClientArchivesRelatePic frClientArchivesRelatePic = selectById(pid);
            if (frClientArchivesRelatePic != null) {
                String path = frClientArchivesRelatePic.getPicLink();
                path = "/" + path.substring(path.indexOf("avatar"), path.length());
                File file = new File(filePath + path);
                file.delete();
                baseMapper.deleteById(frClientArchivesRelatePic);

            }
        }
        return JsonResult.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JsonResult savePic(List<String> imagesList, HttpServletRequest request, Integer type, Date date, String cid, Integer imgType) throws YJException {

        FrClientArchives frClientArchives = frClientArchivesMapper.findByTime(date, cid, type);
        if (frClientArchives != null) {
            //保存图片路径
            StringBuffer imagePath = new StringBuffer(CookieUtils.getCookieValue(request, "url", true));
            imagePath.append(CookieUtils.getCookieValue(request, "imgPath", true));
            String path = imagePath.toString();
            FrClientArchivesRelatePic frClientArchivesRelatePic = new FrClientArchivesRelatePic();
            frClientArchivesRelatePic.setPicType(type);
            frClientArchivesRelatePic.setCreateTime(new Date());
            for (String imgUrl : imagesList) {
                    frClientArchivesRelatePic.setPicType(imgType);
                frClientArchivesRelatePic.setId(UUIDUtils.generateGUID());
                frClientArchivesRelatePic.setPicLink(path + imgUrl);
                frClientArchivesRelatePic.setArchivesId(frClientArchives.getId());
                baseMapper.insert(frClientArchivesRelatePic);
            }
            return JsonResult.successMessage("保存图片成功");
        }
        return JsonResult.failMessage("图片保存失败");
    }
}
