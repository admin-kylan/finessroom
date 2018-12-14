package com.yj.service.service;

import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.dal.model.FrClientLifeRelate;
import com.yj.service.base.BaseService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 客户生活详情 内容保存 服务类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-02
 */
public interface IFrClientLifeRelateService extends BaseService<FrClientLifeRelate> {


    JsonResult saveDetails(String[] frClientLifeTypeIds, String cid, String source,String sid) throws YJException;

    List<FrClientLifeRelate> getDetails();
}
