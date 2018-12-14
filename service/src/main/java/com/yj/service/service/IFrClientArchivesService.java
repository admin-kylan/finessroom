package com.yj.service.service;

import com.yj.common.exception.YJException;
import com.yj.dal.model.FrClientArchives;
import com.yj.service.base.BaseService;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 客户档案（皮肤、头发、塑性抗衰 档案） 区分 服务类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-13
 */
public interface IFrClientArchivesService extends BaseService<FrClientArchives> {

    List<FrClientArchives> getArchives(String cid, Integer type) throws YJException;
}
