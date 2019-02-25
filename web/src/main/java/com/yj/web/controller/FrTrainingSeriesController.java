package com.yj.web.controller;


import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.yj.dal.model.FrAction;
import com.yj.dal.model.FrActionSeries;
import com.yj.service.service.IFrActionSeriesService;
import com.yj.service.service.IFrActionService;
import com.yj.common.util.UUIDUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.dal.dao.FrPrivateCourceMapper;
import com.yj.dal.dao.FrTraningClassMapper;
import com.yj.dal.dto.FrTrainingClassDTO;
import com.yj.dal.dto.FrTrainingSeriesListDTO;
import com.yj.dal.model.FrTrainingSeries;
import com.yj.dal.model.FrTraningClass;
import com.yj.service.service.IFrTrainingSeriesService;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-15
 */
@RestController
@RequestMapping("/frTrainingSeries")
public class FrTrainingSeriesController {

    @Autowired
    private IFrActionSeriesService frActionSeriesService;

    @Autowired
    IFrTrainingSeriesService iFrTrainingSeriesService;

    /**
     * 功能：训练计划和训练 系列  列表
     *
     * <p>
     * 创建日期：2018年11月15日下午9:03:34
     * </p>
     *
     * @param request
     * @param parentId 如果为训练计划指向训练系列id 否则为0
     * @param type     类型   1|单节训练课程   2|训练套餐
     * @return
     * @throws YJException
     */
    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public JsonResult list(HttpServletRequest request, @RequestParam(value = "parentId", required = false, defaultValue = "") String parentId, Integer type, Integer ownType,String sdaduimId) throws YJException {
        EntityWrapper<FrTrainingSeries> wrapper = new EntityWrapper<>();
        if (StringUtils.isNotBlank(parentId)) {
            wrapper.setSqlSelect(" * ").where("is_using = 1 and type = {0} and parent_id = {1} and own_type={2} and sdaduim_id={3}", type, parentId, ownType,sdaduimId);
        } else {
            wrapper.setSqlSelect(" * ").where("is_using = 1 and type = {0} and parent_id is null and own_type={1} and sdaduim_id={2}", type, ownType,sdaduimId);

        }
        List<FrTrainingSeries> frTrainingSeriess = iFrTrainingSeriesService.selectList(wrapper);
        return JsonResult.success(frTrainingSeriess);
    }

    /**
     * 功能： 更新或者新增
     *
     * <p>
     * 创建日期：2018年11月15日下午9:10:29
     * </p>
     *
     * @param frTrainingSeries
     * @return
     * @throws YJException
     */

    @RequestMapping(value = "/addOrUpdate", method = {RequestMethod.GET, RequestMethod.POST})
    public JsonResult addOrUpdate(@RequestBody FrTrainingSeries frTrainingSeries) throws YJException {
        if (StringUtils.isNotBlank(frTrainingSeries.getId())) {
            Wrapper<FrTrainingSeries> entityWrapper = new EntityWrapper<>();
            entityWrapper.where("id = {0}", frTrainingSeries.getId());
            boolean update = iFrTrainingSeriesService.update(frTrainingSeries, entityWrapper);
            if (update) {
                return JsonResult.success(frTrainingSeries);
            } else {
                return JsonResult.fail(frTrainingSeries);
            }
        } else {
            //新增
            frTrainingSeries.setId(UUIDUtils.generateGUID());
            boolean insertOrUpdateAllColumn = iFrTrainingSeriesService.insertOrUpdateAllColumn(frTrainingSeries);
            if (insertOrUpdateAllColumn) {
                return JsonResult.success(frTrainingSeries);
            } else {
                return JsonResult.fail(frTrainingSeries);
            }

        }
    }

    @Autowired
    IFrActionService frActionService;

    @PostMapping("/updateAction")
    public JsonResult updateAction(@RequestBody Map<String, Object> map) {
        FrActionSeries frActionSeries = new FrActionSeries();
        boolean b = false;
        if (map.get("id").toString() != null) {
            frActionSeries = frActionSeriesService.selectOne(
                    new EntityWrapper<FrActionSeries>().where("is_using=1 and id={0}", (String) map.get("id"))
            );
        }
        if (frActionSeries != null) {
            if (map.get("isUsing")!= null) {
                frActionSeries.setIsUsing(Integer.valueOf(map.get("isUsing").toString()));
                List<FrAction> frActions = frActionService.selectList(
                        new EntityWrapper<FrAction>().where("is_using=1 and series_id={0}", (String) map.get("id"))
                );
                if(frActions.size()<1){
                    for (FrAction frAction : frActions) {
                        frAction.setIsUsing(0);
                        b = frActionService.updateById(frAction);
                    }
                }
                b = frActionSeriesService.updateById(frActionSeries);
            }
            if (map.get("name")!= null) {
                frActionSeries.setName((String) map.get("name"));
                b = frActionSeriesService.updateById(frActionSeries);
            }
        }
        if (b) {
            return JsonResult.success(frActionSeries);
        }

        return JsonResult.fail();
    }

    @Autowired
    FrPrivateCourceMapper frPrivateCourceMapper;


    @RequestMapping(value = "/seriesAndActionList", method = {RequestMethod.GET, RequestMethod.POST})
    public JsonResult list(HttpServletRequest request, Integer type, Integer ownType,String sdaduimId) throws YJException {

        FrTrainingSeriesListDTO dto = new FrTrainingSeriesListDTO();

        EntityWrapper<FrTrainingSeries> wrapper = new EntityWrapper<>();
        wrapper.setSqlSelect(" * ").where("is_using = 1 and type = {0} and parent_id is null and own_type={1} and sdaduim_id={2}", type, ownType,sdaduimId);
        // 一级系列列表
        List<FrTrainingSeries> frTrainingSeriess = iFrTrainingSeriesService.selectList(wrapper);
        if (null != frTrainingSeriess && !frTrainingSeriess.isEmpty()) {
            dto.setSeriesList(frTrainingSeriess);
            //一级系列索引第一个对象
            FrTrainingSeries frTrainingSeries = frTrainingSeriess.get(0);

            EntityWrapper<FrTrainingSeries> wrapper2 = new EntityWrapper<>();
            wrapper2.setSqlSelect(" * ").where("is_using = 1 and type = {0} and parent_id = {1} and sdaduim_id={2}", type, frTrainingSeries.getId(),sdaduimId);
            // 二级系列列表
            List<FrTrainingSeries> seriess2 = iFrTrainingSeriesService.selectList(wrapper2);
            if (null != seriess2 && !seriess2.isEmpty()) {
                dto.setSubSeriesList(seriess2);
                //二级系列索引第一个对象
                FrTrainingSeries series = seriess2.get(0);
                //二级系列索引第一个对象 对应的动作集合
                List<FrTrainingClassDTO> classDtoList = frPrivateCourceMapper.getActionsByseriesId(series.getId(), type);
                dto.setClassDtoList(classDtoList);
            }

            // 二级系列动作

        }
        return JsonResult.success(dto);
    }

    @RequestMapping(value = "/getActionList", method = {RequestMethod.GET, RequestMethod.POST})
    public JsonResult getActionList(HttpServletRequest request, Integer type, Integer ownType,String sdaduimId) throws YJException {
        EntityWrapper<FrTrainingSeries> wrapper = new EntityWrapper<>();
        wrapper.setSqlSelect(" * ").where("is_using = 1 and type = {0} and parent_id is null and own_type={1} and sdaduim_id={2}", type, ownType,sdaduimId);
        // 一级系列列表
        List<Map<String, Object>> maps = iFrTrainingSeriesService.selectMaps(wrapper);
        for (Map<String, Object> map : maps) {
            if (null != map && !map.isEmpty()) {

                //一级系列索引第一个对
                EntityWrapper<FrTrainingSeries> wrapper2 = new EntityWrapper<>();
                wrapper2.setSqlSelect(" * ").where("is_using = 1 and type = {0} and parent_id = {1} and sdaduim_id={2}", type, (String) map.get("id"),sdaduimId);
                // 二级系列列表
                List<Map<String, Object>> maps1 = iFrTrainingSeriesService.selectMaps(wrapper2);
                for (Map<String, Object> stringObjectMap : maps1) {
                    if (null != stringObjectMap && !stringObjectMap.isEmpty()) {
                        //二级系列索引第一个对象 对应的动作集合
                        List<FrTrainingClassDTO> classDtoList = frPrivateCourceMapper.getActionsByseriesId2((String) stringObjectMap.get("id"), type);
                        stringObjectMap.put("actionList", classDtoList);
                    }
                }
                map.put("childrenList", maps1);
            }
        }


        return JsonResult.success(maps);
    }

}

