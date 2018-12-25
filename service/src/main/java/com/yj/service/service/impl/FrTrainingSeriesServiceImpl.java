package com.yj.service.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yj.common.exception.YJException;
import com.yj.dal.model.FrTrainingSeries;
import com.yj.dal.dao.FrTrainingSeriesMapper;
import com.yj.service.service.IFrTrainingSeriesService;
import com.yj.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-15
 */
@Service
public class FrTrainingSeriesServiceImpl extends BaseServiceImpl<FrTrainingSeriesMapper, FrTrainingSeries> implements IFrTrainingSeriesService {


    @Override
    public List<FrTrainingSeries> getCourse(String type) throws YJException {
        List<FrTrainingSeries> frTrainingSeries = baseMapper.selectList(
                new EntityWrapper<FrTrainingSeries>().where("is_using = 1 and own_type=1 and type={0} and parent_id is null", type)
        );
        for (FrTrainingSeries frTrainingSery : frTrainingSeries) {
            List<FrTrainingSeries> frTrainingSeries1 = baseMapper.selectList(
                    new EntityWrapper<FrTrainingSeries>()
                            .where("is_using = 1 and own_type=1 and type={0} and parent_id={1}", type, frTrainingSery.getId())
            );
            frTrainingSery.setFrTrainingSeries(frTrainingSeries1);
        }

        return frTrainingSeries;
    }
}
