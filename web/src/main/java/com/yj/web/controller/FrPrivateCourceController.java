package com.yj.web.controller;


import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.common.util.CookieUtils;
import com.yj.common.util.StringUtils;
import com.yj.common.util.UUIDUtils;
import com.yj.dal.dao.FrPrivateCourceMapper;
import com.yj.dal.dao.FrPrivateCourceRelationMapper;
import com.yj.dal.dto.FrPrivateCourceDTO;
import com.yj.dal.dto.FrTrainingClassDTO;
import com.yj.dal.model.FrPrivateCource;
import com.yj.dal.model.FrPrivateCourceRelation;
import com.yj.service.service.IFrPrivateCourceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-14
 */
@RestController
@RequestMapping("/frPrivateCource")
public class FrPrivateCourceController {


    @Autowired
    IFrPrivateCourceService iFrPrivateCourceService;


    @Autowired
    FrPrivateCourceMapper frPrivateCourceMapper;

    @Autowired
    FrPrivateCourceRelationMapper frPrivateCourceRelationMapper;

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
    @RequestMapping(value = "/list")
    public JsonResult invalidList(@RequestParam Map<String, Object> params, HttpServletRequest request) throws YJException {
        String code = CookieUtils.getCookieValue(request, "code", true);
        params.put("code", code);
        return JsonResult.success(iFrPrivateCourceService.queryPage(params));
    }

    /**
     * 功能： 私人课程绑定训练课程计划
     *
     * <p>
     * 创建日期：2018年11月15日下午9:10:29
     * </p>
     *
     * @param vo
     * @return
     * @throws YJException
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/addOrUpdate")
    public JsonResult addOrUpdate(@RequestBody FrPrivateCourceDTO vo) throws YJException {
        String trainingSeriesIds = vo.getTrainingSeriesIds();
        FrPrivateCource frPrivateCource = new FrPrivateCource();
        BeanUtils.copyProperties(vo, frPrivateCource);
        if (StringUtils.isNotBlank(vo.getId())) {
            Integer updateById = frPrivateCourceMapper.updateById(frPrivateCource);
            if (updateById > 0) {
                //解除原先绑定关系
                List<FrPrivateCourceRelation> oldRelations = frPrivateCourceRelationMapper.getRelationByCourceId(vo.getId());
                for (FrPrivateCourceRelation frPrivateCourceRelation : oldRelations) {
                    frPrivateCourceRelation.setIsUsing(0);
                    frPrivateCourceRelation.setUpdateTime(new Date());
                    frPrivateCourceRelationMapper.updateById(frPrivateCourceRelation);
                }
                //重新绑定关系
                String[] seriesIds = null;
                if (trainingSeriesIds != null) {
                    seriesIds = trainingSeriesIds.split(",");

                    for (int i = 0; i < seriesIds.length; i++) {
                        FrPrivateCourceRelation relation = new FrPrivateCourceRelation();
                        relation.setId(UUIDUtils.generateGUID());
                        relation.setCreateTime(new Date());
                        relation.setPrivateCourceId(vo.getId());
                        relation.setTrainingSeriesId(seriesIds[i]);
                        relation.setUpdateTime(new Date());
                        relation.setIsUsing(1);
                        frPrivateCourceRelationMapper.insert(relation);
                    }

                }
                return JsonResult.successMessage("操作成功");
            } else {
                return JsonResult.failMessage("操作失败");
            }
        } else {
            //新增
            vo.setId(UUIDUtils.generateGUID());
            vo.setCreateTime(new Date());
            vo.setUpdateTime(new Date());
            vo.setIsUsing(1);
            BeanUtils.copyProperties(vo, frPrivateCource);
            Integer insertOrUpdateAllColumn = frPrivateCourceMapper.insertAllColumn(frPrivateCource);
            if (insertOrUpdateAllColumn > 0) {

                //重新绑定关系
                String[] seriesIds = null;
                if (trainingSeriesIds != null) {
                    seriesIds = trainingSeriesIds.split(",");
                    for (int i = 0; i < seriesIds.length; i++) {
                        FrPrivateCourceRelation relation = new FrPrivateCourceRelation();
                        relation.setId(UUIDUtils.generateGUID());
                        relation.setCreateTime(new Date());
                        relation.setPrivateCourceId(frPrivateCource.getId());
                        relation.setTrainingSeriesId(seriesIds[i]);
                        relation.setUpdateTime(new Date());
                        frPrivateCourceRelationMapper.insert(relation);
                    }
                }
                return JsonResult.successMessage("操作成功");
            } else {
                return JsonResult.failMessage("操作失败");
            }

        }
    }


    /**
     * @param parentId
     * @return
     * @throws YJException
     * @TODO: 获取训练计划列表
     * @author hujz
     * @date 2018年11月19日 下午10:28:19
     */
    @RequestMapping(value = "/getActionsByseriesId", method = {RequestMethod.GET, RequestMethod.POST})
    public JsonResult list(String traningSeriesId, Integer type) throws YJException {
        // 展示系列课程 以及每个课程对应 的动作
        List<FrTrainingClassDTO> actions = frPrivateCourceMapper.getActionsByseriesId(traningSeriesId, type);
        return JsonResult.success(actions);
    }

    @GetMapping("deleteAction")
    public JsonResult deletePrivateCource(String id) {
        FrPrivateCource cource = new FrPrivateCource();
        cource.setIsUsing(0);
        cource.setId(id);
        if (StringUtils.isEmpty(id))
            return JsonResult.failMessage("参数异常,接口调用失败");

        else {
            return JsonResult.success(frPrivateCourceMapper.updateById(cource));
        }

    }
}

