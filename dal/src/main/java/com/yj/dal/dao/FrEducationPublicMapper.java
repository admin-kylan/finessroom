package com.yj.dal.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.yj.dal.model.FrEducation;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 教学表，团体教学，私人教学 Mapper 接口
 * </p>
 *
 * @author MP自动生成
 * @since 2019-01-05
 */
public interface FrEducationPublicMapper extends BaseMapper<FrEducation> {


    List<Map<String, Object>> findCoachList(@Param("shopId") String shopId, @Param("CustomerCode") String CustomerCode);

    List<Map<String, Object>> findSdaduimList(@Param("shopId") String shopId, @Param("CustomerCode") String CustomerCode);

    List<Map<String, Object>> findEducationList(@Param("shopId") String shopId, @Param("CustomerCode") String CustomerCode,
                                                @Param("coachId") String coachId, @Param("sdaduimId") String sdaduimId,
                                                @Param("beginDate") String beginDate, @Param("endDate") String endDate,
                                                @Param("eduType") String eduType);
    List<Map<String, Object>> findEducationOneToOneList(@Param("shopId") String shopId, @Param("CustomerCode") String CustomerCode,
                                                @Param("coachId") String coachId, @Param("sdaduimId") String sdaduimId,
                                                @Param("beginDate") String beginDate, @Param("endDate") String endDate,
                                                @Param("eduType") String eduType);
    Map<String, Object> findEducationById(@Param("eduId") String eduId);

    Map<String, Object> getGroupRoomSeatById(@Param("roomId") String roomId);

    List<Map<String, Object>> getClientByMobileName(@Param("CustomerCode") String CustomerCode,@Param("mobile") String mobile);

    Map<String, Object> getClientMemberType(@Param("clientId") String clientId);
}
