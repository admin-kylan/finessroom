package com.yj.service.service;

import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.dal.model.FrClientPhysicalTestPic;
import com.yj.service.base.BaseService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 体能测试图片保存 服务类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-12-10
 */
public interface IFrClientPhysicalTestPicService extends BaseService<FrClientPhysicalTestPic> {

    JsonResult savePic(List<String> imagesList, HttpServletRequest request, String cid, String tid)throws YJException;

    JsonResult delPic(String[] pids, String filePath)throws YJException;
}
