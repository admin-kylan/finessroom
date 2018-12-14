package com.yj.service.service;

import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.dal.model.FrClientWorkRelate;
import com.yj.service.base.BaseService;

import java.util.List;

/**
 * <p>
 * 客户工作行业 内容保存 服务类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-08
 */
public interface IFrClientWorkRelateService extends BaseService<FrClientWorkRelate> {

    JsonResult saveWorks(String[] frClientWorkTypeIds, String cid, String workHistory, String workSituation) throws YJException;

    List<FrClientWorkRelate> getWorks();
}
