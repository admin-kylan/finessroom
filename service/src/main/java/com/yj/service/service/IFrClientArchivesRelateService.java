package com.yj.service.service;

import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.dal.dto.ArchivesDTO;
import com.yj.dal.model.FrClientArchivesRelate;
import com.yj.service.base.BaseService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 客户档案（皮肤、头发、塑性抗衰 档案）  内容保存 服务类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-13
 */
public interface IFrClientArchivesRelateService extends BaseService<FrClientArchivesRelate> {

    JsonResult updateRelate(String[] relateIds, String text, Integer type)throws YJException;

    JsonResult saveRelate(String[] typeIds, String text, String cid,Integer type,Date date,HttpServletRequest request)throws YJException;

    ArchivesDTO getRelate(Date date,String cid,Integer type)throws YJException;
}
