package com.yj.service.base;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈Service基类〉
 *
 * @author 欧俊俊
 * @create 2018/8/31
 */
public interface BaseService<T> {
    boolean insert(T var1);

    boolean insertAllColumn(T var1);

    boolean insertBatch(List<T> var1);

    boolean insertBatch(List<T> var1, int var2);

    boolean insertOrUpdateBatch(List<T> var1);

    boolean insertOrUpdateBatch(List<T> var1, int var2);

    boolean insertOrUpdateAllColumnBatch(List<T> var1);

    boolean insertOrUpdateAllColumnBatch(List<T> var1, int var2);

    boolean deleteById(Serializable var1);

    boolean deleteByMap(Map<String, Object> var1);

    boolean delete(Wrapper<T> var1);

    boolean deleteBatchIds(Collection<? extends Serializable> var1);

    boolean updateById(T var1);

    boolean updateAllColumnById(T var1);

    boolean update(T var1, Wrapper<T> var2);

    boolean updateBatchById(List<T> var1);

    boolean updateBatchById(List<T> var1, int var2);

    boolean updateAllColumnBatchById(List<T> var1);

    boolean updateAllColumnBatchById(List<T> var1, int var2);

    boolean insertOrUpdate(T var1);

    boolean insertOrUpdateAllColumn(T var1);

    T selectById(Serializable var1);

    List<T> selectBatchIds(Collection<? extends Serializable> var1);

    List<T> selectByMap(Map<String, Object> var1);

    T selectOne(Wrapper<T> var1);

    Map<String, Object> selectMap(Wrapper<T> var1);

    Object selectObj(Wrapper<T> var1);

    int selectCount(Wrapper<T> var1);

    List<T> selectList(Wrapper<T> var1);

    Page<T> selectPage(Page<T> var1);

    List<Map<String, Object>> selectMaps(Wrapper<T> var1);

    List<Object> selectObjs(Wrapper<T> var1);

    Page<Map<String, Object>> selectMapsPage(Page var1, Wrapper<T> var2);

    Page<T> selectPage(Page<T> var1, Wrapper<T> var2);

}
