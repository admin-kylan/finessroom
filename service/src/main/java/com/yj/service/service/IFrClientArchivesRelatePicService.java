package com.yj.service.service;

import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.dal.model.FrClientArchivesRelatePic;
import com.yj.service.base.BaseService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 客户档案（皮肤、头发、塑性抗衰 档案）  内容保存图片 服务类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-13
 */
public interface IFrClientArchivesRelatePicService extends BaseService<FrClientArchivesRelatePic> {

    JsonResult delPic(String[] pids, String filePath) throws YJException;

    JsonResult savePic(List<String> imagesList, HttpServletRequest request, Integer type, Date date, String cid, Integer imgType)throws YJException;
}
