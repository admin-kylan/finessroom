package com.yj.service.service;

import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.common.util.PageUtils;
import com.yj.dal.dto.ExistenceCountDTO;
import com.yj.dal.model.FrClient;
import com.yj.dal.param.*;
import com.yj.service.base.BaseService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 客户表 服务类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-12
 */
public interface IFrClientService extends BaseService<FrClient> {

    PageUtils selectExistenceList(ExistenceFilterParam conditions) throws YJException;

    ExistenceCountDTO selectExistenceStat(String code, String shopId);

    PageUtils selectPotentialList(PotentialFilterParam params) throws YJException;

    JsonResult addPotentialCustomer(AddPotentialParam potentialParam) throws YJException;

    JsonResult delPotentialCustomer(String id);

    JsonResult getClient(String id);

    JsonResult getClientList(String CustomerCode,String mobile);

    JsonResult addCustomer(AddCustomerParam customerParam) throws YJException;

    PageUtils selectmyPotentialList(MyPotentialFilterParam params)throws YJException;

    PageUtils selectCollarList(CollarClientParam params)throws YJException;

    /**
     * 增加一条现有客户数据
     * @param frClient
     * @param isFlag  若客户不是现有是否需要插入现有客户数据 true 是
     * @return
     * @throws YJException
     */
    FrClient addClientPersonal(FrClient frClient,boolean isFlag,String shopId)throws YJException;


    PageUtils selectMyExistenceList(MyExistenceFilterParam params)throws YJException;

    PageUtils selectExistingList(CollarClientParam params)throws YJException;

    PageUtils selectClientAllot(CollarClientParam params)throws YJException;

    PageUtils selectClientInformation(ClientInformationParam params)throws YJException;

    List<FrClient> queryByClient(FrClient frClient)throws YJException;

    JsonResult getPersonalDetails(String id);

    JsonResult getPotentialClientList();

    JsonResult getEmployeeClientList();


    PageUtils existenceListBG(HttpServletRequest request,ExistenceFilterParam params) throws YJException;


    PageUtils potentialListBG(HttpServletRequest request,PotentialFilterParam params) throws YJException;

}
