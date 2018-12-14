package com.yj.service.service;

import com.yj.common.exception.YJException;
import com.yj.dal.model.FrFollowKeyword;
import com.yj.dal.param.KeywordParam;
import com.yj.service.base.BaseService;

/**
 * <p>
 * 跟进记录关键字表 服务类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-04
 */
public interface IFrFollowKeywordService extends BaseService<FrFollowKeyword> {

    Boolean addFollowKeyword(KeywordParam param) throws YJException;
}
