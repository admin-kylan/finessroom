package com.yj.service.service.impl;

import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.result.JsonResult;
import com.yj.common.util.CookieUtils;
import com.yj.common.util.UUIDUtils;
import com.yj.dal.model.FrClientArchivesRelatePic;
import com.yj.dal.model.FrClientPhysicalTestPic;
import com.yj.dal.dao.FrClientPhysicalTestPicMapper;
import com.yj.service.service.IFrClientPhysicalTestPicService;
import com.yj.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 体能测试图片保存 服务实现类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-12-10
 */
@Service
public class FrClientPhysicalTestPicServiceImpl extends BaseServiceImpl<FrClientPhysicalTestPicMapper, FrClientPhysicalTestPic> implements IFrClientPhysicalTestPicService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JsonResult savePic(List<String> imagesList, HttpServletRequest request, String cid ,String tid) throws YJException {
        StringBuffer imagePath = new StringBuffer(CookieUtils.getCookieValue(request, "url", true));
        imagePath.append(CookieUtils.getCookieValue(request, "imgPath", true));
        String path = imagePath.toString();
        FrClientPhysicalTestPic frClientPhysicalTestPic = new FrClientPhysicalTestPic();
        for (String imgUrl : imagesList) {
            frClientPhysicalTestPic.setId(UUIDUtils.generateGUID());
            frClientPhysicalTestPic.setPicLink(path + imgUrl);
            frClientPhysicalTestPic.setFrClientPhysicalTestId(tid);
            baseMapper.insert(frClientPhysicalTestPic);
        }
        return JsonResult.successMessage("保存图片成功");

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JsonResult delPic(String[] pids, String filePath) throws YJException {
        //校验参数
        if (pids == null) {
            throw new YJException(YJExceptionEnum.PARAM_ERROR);
        }
        for (String pid : pids) {
            FrClientPhysicalTestPic frClientPhysicalTestPic = selectById(pid);
            if (frClientPhysicalTestPic != null) {
                String path = frClientPhysicalTestPic.getPicLink();
                path = "/" + path.substring(path.indexOf("avatar"), path.length());
                File file = new File(filePath + path);
                file.delete();
                baseMapper.deleteById(frClientPhysicalTestPic);

            }
        }
        return JsonResult.success();
    }
}
