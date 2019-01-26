package com.yj.dal.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.yj.dal.model.FrEducation;
import com.yj.dal.model.FrEducationCardObject;
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
    List<Map<String, Object>> findEducationOneToManyList(@Param("shopId") String shopId, @Param("CustomerCode") String CustomerCode,
                                                        @Param("coachId") String coachId, @Param("sdaduimId") String sdaduimId,
                                                        @Param("beginDate") String beginDate, @Param("endDate") String endDate,
                                                        @Param("eduType") String eduType);
    Map<String, Object> findEducationById(@Param("eduId") String eduId);

    Map<String, Object> getGroupRoomSeatById(@Param("roomId") String roomId);

    List<Map<String, Object>> getClientByMobileName(@Param("CustomerCode") String CustomerCode,@Param("mobile") String mobile);

    Map<String, Object> getClientMemberType(@Param("clientId") String clientId);

    List<Map<String, Object>> findCourseTrainingPlan(@Param("courseId") String courseId);

    List<Map<String, Object>> findMemberCardByInput(@Param("searchInput") String searchInput, @Param("shopId") String shopId, @Param("CustomerCode") String CustomerCode, @Param("isSearchMobile") String isSearchMobile);

    List<Map<String, Object>> findMemberCardReserveByInput(@Param("searchInput") String searchInput, @Param("shopId") String shopId, @Param("CustomerCode") String CustomerCode, @Param("isSearchMobile") String isSearchMobile);


    Map<String, Object> findCardOrderDetailFirst(@Param("cardId") String cardId);

    List<Map<String, Object>> findEducationCoachList(@Param("shopId") String shopId, @Param("executeDate") String executeDate, @Param("sdaduimId") String sdaduimId, @Param("CustomerCode") String CustomerCode, @Param("teachType")Integer teachType);

    List<Map<String, Object>> findEducationRoomList(@Param("shopId") String shopId, @Param("executeDate") String executeDate, @Param("sdaduimId") String sdaduimId, @Param("CustomerCode") String CustomerCode, @Param("teachType")Integer teachType);

    List<Map<String, Object>> findCoursePublicList(@Param("shopId") String shopId, @Param("CustomerCode") String CustomerCode);

    List<Map<String, Object>> findCoursePrivateList(@Param("shopId") String shopId, @Param("CustomerCode") String CustomerCode);

    List<Map<String, Object>> findShopSdaduimPublicCourseList(@Param("shopId") String shopId, @Param("CustomerCode") String CustomerCode);

    List<Map<String, Object>> findShopSdaduimPrivateCourseList(@Param("shopId") String shopId, @Param("CustomerCode") String CustomerCode);

    List<Map<String, Object>> searchSettlement(@Param("shopId") String shopId, @Param("searchInput") String searchInput,
                                               @Param("beginDate") String beginDate, @Param("endDate") String endDate,
                                               @Param("code") String code, @Param("eduType")Integer eduType);


    List<Map<String, String>> findConsumeConditionCardType();

    List<Map<String, String>> findConsumeConditionCardName();

    List<Map<String, String>> findEduIdForEndClass();

    void updateEduStatusToEndClass(@Param("eduId")String eduId);

    List<Map<String, String>> findAllCourseForShopSdaduim(@Param("shopId")String shopId, @Param("code")String code);



    List<Map<String, Object>> findConsumeClass(@Param("beginDate")String beginDate, @Param("endDate")String endDate, @Param("shopId")String shopId,
                                               @Param("searchCourse")String searchCourse, @Param("searchCoach")String searchCoach, @Param("code")String code,
                                               @Param("eduType")Integer eduType);

    List<Map<String, Object>> findConsumeMember(@Param("beginDate")String beginDate, @Param("endDate")String endDate, @Param("shopId")String shopId,
                                                @Param("searchCourse")String searchCourse, @Param("searchCoach")String searchCoach, @Param("code")String code,
                                                @Param("cardType")String cardType, @Param("cardName")String cardName,@Param("eduType")Integer eduType);

    List<Map<String, Object>> findMemberFreezeList(@Param("shopId") String shopId, @Param("code") String code,
                                                   @Param("beginDate") String beginDate, @Param("endDate") String endDate,
                                                   @Param("searchInput") String searchInput);

    Map<String, Object> findManageCount(@Param("shopId") String shopId,
                                                   @Param("beginDate") String beginDate, @Param("endDate") String endDate);

    List<Map<String, Object>> getGroupRoomByShopId(@Param("shopId") String shopId, @Param("code") String code);

    List<Map<String, String>> findEducationToCopy( @Param("beginDate") String beginDate,
                                                @Param("endDate")String endDate, @Param("coachId") String coachId,
                                                @Param("courseId") String courseId,  @Param("eduType") String eduType,
                                                   @Param("shopId") String shopId);

    List<Map<String, Object>> findEduListByMonthWeek( @Param("shopId") String shopId, @Param("beginDate") String beginDate,
                                                @Param("endDate")String endDate, @Param("coachId") String coachId,
                                                @Param("code") String code,  @Param("eduType") String eduType,
                                                  @Param("roomId") String roomId);
    Map<String, String> findEducationToCopyByEduId(@Param("eduId") String eduId);

    Map<String, String> findSettingCourse(@Param("eduType") String eduType, @Param("code") String code,@Param("key") String key);

}
