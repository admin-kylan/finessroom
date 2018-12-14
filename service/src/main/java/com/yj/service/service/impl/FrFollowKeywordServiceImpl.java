package com.yj.service.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.util.UUIDUtils;
import com.yj.dal.dao.FrFollowKeywordMapper;
import com.yj.dal.model.FrFollowKeyword;
import com.yj.dal.param.KeywordParam;
import com.yj.service.service.IFrFollowKeywordService;
import com.yj.service.base.BaseServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * <p>
 * 跟进记录关键字表 服务实现类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-04
 */
@Service
public class FrFollowKeywordServiceImpl extends BaseServiceImpl<FrFollowKeywordMapper, FrFollowKeyword> implements IFrFollowKeywordService {

    @Override
    @Transactional
    public Boolean addFollowKeyword(KeywordParam param) throws YJException {
        String keyword = param.getKeyword();
        int successCount = 0;
        //判断关键字是否包含顿号
        int i = StringUtils.indexOf(keyword, "、");
        if(i != -1){
            //分组
            String[] keywords = StringUtils.split(keyword,"、");
            if (keywords.length<=0) {
                throw new YJException(YJExceptionEnum.KEYWORD_ISNOTBLANK);
            }
            for (int j = 0; j < keywords.length ; j++) {
                //查询关键字是否已存在;
                EntityWrapper<FrFollowKeyword> entityWrapper = new EntityWrapper<>();
                entityWrapper.where("is_using = {0}",1).and("content = {0}",keywords[j]);
                Integer count = baseMapper.selectCount(entityWrapper);
                if(count==0){
                    Date date = new Date();
                    FrFollowKeyword frFollowKeyword = new FrFollowKeyword();
                    frFollowKeyword.setId(UUIDUtils.generateGUID());
                    frFollowKeyword.setContent(keywords[j]);
                    successCount = successCount + baseMapper.insert(frFollowKeyword);
                }
            }
        }else {
            //插入单个关键字
            if(StringUtils.isBlank(keyword)){
                throw new YJException(YJExceptionEnum.KEYWORD_ISNOTBLANK);
            }
            //查询关键字是否已存在;
            EntityWrapper<FrFollowKeyword> entityWrapper = new EntityWrapper<>();
            entityWrapper.where("is_using = {0}",1).and("content = {0}",keyword);
            int count = baseMapper.selectCount(entityWrapper);
            if(count > 0 ){
                throw new YJException(YJExceptionEnum.KEYWORD_EXISTED);
            }
            Date date = new Date();
            FrFollowKeyword frFollowKeyword = new FrFollowKeyword();
            frFollowKeyword.setId(UUIDUtils.generateGUID());
            frFollowKeyword.setContent(keyword);
            successCount = successCount + baseMapper.insert(frFollowKeyword);
        }
        if (successCount>0) {
            return true;
        }
        return false;
    }
}
