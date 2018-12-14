package com.yj.dal.dao;

import com.yj.dal.dto.CategoryItemDTO;
import com.yj.dal.model.FrCategoryItem;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 场馆项目表 Mapper 接口
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-27
 */
public interface FrCategoryItemMapper extends BaseMapper<FrCategoryItem> {

    List<CategoryItemDTO> selectItemsBySdaduimId(@Param("sdaduimId") String sdaduimId);
}
