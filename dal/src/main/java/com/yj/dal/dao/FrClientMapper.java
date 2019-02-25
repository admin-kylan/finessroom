package com.yj.dal.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.yj.dal.dto.*;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.yj.dal.model.FrClient;
import com.yj.dal.param.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 客户表 Mapper 接口
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-12
 */
public interface FrClientMapper extends BaseMapper<FrClient> {

    List<ExistenceClientDTO> selectExistenceList(Page<FrClient> page, ExistenceFilterParam param);

    Integer selectBirthdayCount(@Param("dayNum") Integer dayNum, @Param("code") String code, @Param("shopId") String shopId);

    List<PotentialClientDTO> selectPotentialList(Page page, PotentialFilterParam conditions);

    Map<String,Object> getClient(@Param("id") String id);

    void updateClient(FrClient frClient);

    FrClient getById(@Param("id") String id, @Param("customerCode") String customerCode);

    Integer getFollowCount(String cid);

    List<MyPotentialClientDTO> findPotential(Page page, MyPotentialFilterParam params);

    List<CollarClientDTO> getNoCollerClient(Page page, CollarClientParam params);

    List<MyExistenceClientDTO> findMyExistenceList(Page page, MyExistenceFilterParam params);

    List<CollarClientDTO> getNoExistingClient(Page page, CollarClientParam params);

    List<CollarClientDTO> getClientAllot(Page page, CollarClientParam params);

    List<ClientInformationDTO> getClientInformation(Page page, ClientInformationParam params);

    Map getClientEssentialInformation(String cid);

    List<FrClient> queryByClient(FrClient frClient);

    FrClient selectByMobile(String mobile);

    List<Map<String, Object>> selectPotentialClientList(@Param("customerCode")String customerCode, @Param("shopId")String shopId);

    List<Map<String, Object>> selectEmployeeClientList(@Param("shopId")String shopId,@Param("customerCode") String customerCode);
}
