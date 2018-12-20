package com.yj.web.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.common.util.CookieUtils;
import com.yj.common.util.StringUtils;
import com.yj.dal.dto.FrGroupClassRoomDTO;
import com.yj.dal.model.FrGroupClassRoom;
import com.yj.dal.model.FrGroupClassRoomSeat;
import com.yj.service.service.IFrGroupClassRoomSeatService;
import com.yj.service.service.IFrGroupClassRoomService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-12-08
 */
@RestController
@RequestMapping("/frGroupClassRoom")
public class FrGroupClassRoomController {

    @Autowired
    private IFrGroupClassRoomService iFrGroupClassRoomService;
    @Autowired
    private IFrGroupClassRoomSeatService iFrGroupClassRoomSeatService;

    @GetMapping("/delete")
    public JsonResult delete(String id){
        FrGroupClassRoom room = new FrGroupClassRoom();
        room.setId(id);
        room.setIsUsing(0);
        room.setUpdateTime(new Date());
        iFrGroupClassRoomService.update(room,new EntityWrapper<FrGroupClassRoom>().where("id={0}",id));

        FrGroupClassRoomSeat seat = new FrGroupClassRoomSeat();
        seat.setUpdateTime(new Date());
        seat.setIsUsing(0);
        seat.setClassRoomId(id);
        iFrGroupClassRoomSeatService.update(seat,new EntityWrapper<FrGroupClassRoomSeat>().where("class_room_id={0}",id));

        return JsonResult.successMessage("操作成功");
    }

    @PostMapping("/saveOrUpdate")
    public JsonResult saveOrUpdate(@RequestBody FrGroupClassRoomDTO dto, HttpServletRequest request){
        String code = CookieUtils.getCookieValue(request, "code", true);
        FrGroupClassRoom room = new FrGroupClassRoom();
        FrGroupClassRoomSeat seat = new FrGroupClassRoomSeat();
        BeanUtils.copyProperties(dto,room);
        BeanUtils.copyProperties(dto.getFrGroupClassRoomSeat(),seat);
        seat.setSeatNum(JSON.toJSONString(dto.getFrGroupClassRoomSeat().getSeatNumArr()));

        room.setIsUsing(1);
        seat.setIsUsing(1);
        room.setCustomerCode(code);
        seat.setCustomerCode(code);

        if(StringUtils.isNotEmpty(dto.getId())){
            room.setUpdateTime(new Date());
            iFrGroupClassRoomService.update(room,new EntityWrapper<FrGroupClassRoom>().where("id={0}",room.getId()));
            seat.setUpdateTime(new Date());
            iFrGroupClassRoomSeatService.update(seat,new EntityWrapper<FrGroupClassRoomSeat>().where("class_room_id={0}",room.getId()));
        }else{

            room.setCreateTime(new Date());
            iFrGroupClassRoomService.insert(room);
            //保存作为信息
            seat.setClassRoomId(room.getId());
            seat.setCreateTime(new Date());
            iFrGroupClassRoomSeatService.insert(seat);
        }

        return JsonResult.successMessage("操作成功");
    }

    @RequestMapping(value="/list",method={RequestMethod.GET,RequestMethod.POST})
    public JsonResult list(@RequestParam Map<String, Object> params, HttpServletRequest request) throws YJException {
        String code = CookieUtils.getCookieValue(request, "code", true);
        params.put("code",code);

        return JsonResult.success(iFrGroupClassRoomService.queryPage(params));
    }

}

