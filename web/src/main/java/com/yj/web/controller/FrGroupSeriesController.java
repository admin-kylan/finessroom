package com.yj.web.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yj.common.result.JsonResult;
import com.yj.common.util.CookieUtils;
import com.yj.common.util.StringUtils;
import com.yj.common.util.UUIDUtils;
import com.yj.dal.dto.FrGroupSeriesDTO;
import com.yj.dal.model.FrGroupCourse;
import com.yj.dal.model.FrGroupSeries;
import com.yj.dal.model.FrGroupSeriesShopRelation;
import com.yj.service.service.IFrGroupCourseService;
import com.yj.service.service.IFrGroupSeriesService;
import com.yj.service.service.IFrGroupSeriesShopRelationService;
import com.yj.service.service.impl.FrGroupSeriesServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-12-06
 */
@RestController
@RequestMapping("/frGroupSeries")
public class FrGroupSeriesController {

    @Autowired
    private IFrGroupSeriesService iFrGroupSeriesService;
    @Autowired
    private IFrGroupSeriesShopRelationService iFrGroupSeriesShopRelationService;
    @Autowired
    private IFrGroupCourseService iFrGroupCourseService;
    @Autowired
    private IFrGroupSeriesShopRelationService frGroupSeriesShopRelationService;


    @GetMapping("/tree")
    public JsonResult getAllSeries(@RequestParam Map<String, Object> params, HttpServletRequest request) {
        String code = CookieUtils.getCookieValue(request, "code", true);
        String shopId = String.valueOf(params.get("shopId"));
        String sdaduimId = String.valueOf(params.get("sdaduimId"));
        List<FrGroupSeries> parent = new ArrayList<>();
        if (shopId != "null") {
            List<FrGroupSeriesShopRelation> frGroupSeriesShopRelations = frGroupSeriesShopRelationService.selectList(
                    new EntityWrapper<FrGroupSeriesShopRelation>().where("is_using=1 and shop_id={0}", shopId)
            );
            for (FrGroupSeriesShopRelation frGroupSeriesShopRelation : frGroupSeriesShopRelations) {
                FrGroupSeries frGroupSeries = iFrGroupSeriesService.selectOne(
                        new EntityWrapper<FrGroupSeries>()
                                .where(" customer_code={0} and is_using=1 and id={1} and sdaduim_id={2}", code, frGroupSeriesShopRelation.getSeriesId(),sdaduimId).orderBy("create_time")
                );
                parent.add(frGroupSeries);
            }

        } else {
            parent = iFrGroupSeriesService.selectList(
                    new EntityWrapper<FrGroupSeries>()
                            .where("(parent_id is null or parent_id='0') and customer_code={0} and is_using=1 and sdaduim_id={1}", code,sdaduimId).orderBy("create_time"));
        }
        List<Map<String, Object>> nodes = new ArrayList<>();

        parent.stream().forEach((pNode) -> {
            Map<String, Object> node = new HashMap<>();
            node.put("text", pNode.getName());
            node.put("isCatalog", 1);
            node.put("id", pNode.getId());
            List<Map<String, Object>> childern = new ArrayList<>();

            Map<String, Object> addNode = new HashMap<>();
            addNode.put("text", "新增课程");
            addNode.put("id", "");
            childern.add(addNode);

            List<FrGroupCourse> childernList = iFrGroupCourseService.selectList(new EntityWrapper<FrGroupCourse>().where("series_id={0} and is_using=1", pNode.getId()).orderBy("create_time"));

            childernList.stream().forEach((child) -> {
                Map<String, Object> childNode = new HashMap<>();
                childNode.put("text", child.getName());
                childNode.put("id", child.getId());
                childern.add(childNode);
            });
            node.put("nodes", childern);
            nodes.add(node);
        });

        return JsonResult.success(nodes);
    }

    @GetMapping("/info")
    public JsonResult getInfoById(String id) {
        FrGroupSeriesDTO dto = new FrGroupSeriesDTO();
        FrGroupSeries series = iFrGroupSeriesService.selectById(id);

        if (series.getIsChain() == 0) {
            List<FrGroupSeriesShopRelation> relations = iFrGroupSeriesShopRelationService.selectList(new EntityWrapper<FrGroupSeriesShopRelation>().where("series_id={0}", series.getId()));
            List<String> shopIds = new ArrayList<>();
            relations.stream().forEach((relation) -> {
                shopIds.add(relation.getShopId());
            });
            dto.setShopIds(String.join(",", shopIds));
        }
        BeanUtils.copyProperties(series, dto);

        return JsonResult.success(dto);
    }


    @PostMapping("/saveOrUpdate")
    public JsonResult saveOrUpdate(@RequestBody FrGroupSeriesDTO dto, HttpServletRequest request) {
        String code = CookieUtils.getCookieValue(request, "code", true);
        FrGroupSeries value = new FrGroupSeries();
        BeanUtils.copyProperties(dto, value);

        if (StringUtils.isNotEmpty(dto.getId())) {
            value.setIsUsing(1);
            value.setCustomerCode(code);
            value.setUpdateTime(new Date());
            iFrGroupSeriesService.update(value, new EntityWrapper<FrGroupSeries>().where("id = {0}", value.getId()));
            iFrGroupSeriesShopRelationService.delete(new EntityWrapper<FrGroupSeriesShopRelation>().where("series_id={0}", value.getId()));

        } else {
            value.setId(UUIDUtils.generateGUID());
            value.setCreateTime(new Date());
            value.setIsUsing(1);
            value.setCustomerCode(code);
            value.setSdaduimId(dto.getSdaduimId());
            iFrGroupSeriesService.insert(value);

        }
        if (value.getIsChain() != null && value.getIsChain() == 0) {//保存关系
            Arrays.stream(dto.getShopIds().split(",")).forEach((shopId) -> {
                FrGroupSeriesShopRelation relation = new FrGroupSeriesShopRelation();
                relation.setId(UUIDUtils.generateGUID());
                relation.setCreateTime(new Date());
                relation.setCustomerCode(code);
                relation.setIsUsing(1);
                relation.setSeriesId(value.getId());
                relation.setShopId(shopId);

                iFrGroupSeriesShopRelationService.insert(relation);
            });
        }

        return JsonResult.successMessage("操作成功");

    }
}

