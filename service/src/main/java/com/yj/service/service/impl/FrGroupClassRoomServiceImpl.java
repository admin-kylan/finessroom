package com.yj.service.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.yj.common.exception.YJException;
import com.yj.common.util.PageUtils;
import com.yj.common.util.Query;
import com.yj.dal.dao.FrGroupClassRoomSeatMapper;
import com.yj.dal.dto.FrGroupClassRoomDTO;
import com.yj.dal.dto.FrGroupClassRoomSeatDTO;
import com.yj.dal.model.FrGroupClassRoom;
import com.yj.dal.dao.FrGroupClassRoomMapper;
import com.yj.dal.model.FrGroupClassRoomSeat;
import com.yj.dal.model.FrGroupCourse;
import com.yj.service.service.IFrGroupClassRoomSeatService;
import com.yj.service.service.IFrGroupClassRoomService;
import com.yj.service.base.BaseServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-12-08
 */
@Service
public class FrGroupClassRoomServiceImpl extends BaseServiceImpl<FrGroupClassRoomMapper, FrGroupClassRoom> implements IFrGroupClassRoomService {

    @Autowired
    private FrGroupClassRoomSeatMapper frGroupClassRoomSeatMapper;

    @Override
    public Object queryPage(Map<String, Object> params) throws YJException {
        String sdaduimId = String.valueOf(params.get("sdaduimId"));

        Page page = this.selectPage(new Query<FrGroupClassRoom>(params).getPage(),
                new EntityWrapper<FrGroupClassRoom>()
                        .where("is_using = 1 and sdaduim_id={0}",sdaduimId)
                        .orderBy("create_time desc")
        );
        List<FrGroupClassRoom> list = page.getRecords();
        List<FrGroupClassRoomDTO> dtos = new ArrayList<>();
        list.stream().forEach((data) ->{
            FrGroupClassRoomDTO dto = new FrGroupClassRoomDTO();
            FrGroupClassRoomSeatDTO  seatDto = new FrGroupClassRoomSeatDTO();
            BeanUtils.copyProperties(data,dto);

            FrGroupClassRoomSeat change = new FrGroupClassRoomSeat();
            change.setClassRoomId(data.getId());
            FrGroupClassRoomSeat setInfo = frGroupClassRoomSeatMapper.selectOne(change);
            BeanUtils.copyProperties(setInfo,seatDto);
            seatDto.setSeatNumArr(JSON.parseObject(setInfo.getSeatNum(), int[][].class));
            dto.setFrGroupClassRoomSeat(seatDto);

            dtos.add(dto);
        });

        page.setRecords(dtos);

        return new PageUtils(page);
    }
}
