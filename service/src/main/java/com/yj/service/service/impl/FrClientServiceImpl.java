package com.yj.service.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.baomidou.mybatisplus.plugins.Page;
import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.result.JsonResult;
import com.yj.common.util.*;
import com.yj.dal.dao.FrCardMapper;
import com.yj.dal.dao.FrLatenceFollowMapper;
import com.yj.dal.dao.ShopMapper;
import com.yj.dal.dto.*;
import com.yj.dal.dao.FrClientMapper;
import com.yj.dal.model.*;
import com.yj.dal.param.*;
import com.yj.service.service.*;
import com.yj.service.base.BaseServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 客户表 服务实现类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-12
 */
@Service
public class FrClientServiceImpl extends BaseServiceImpl<FrClientMapper, FrClient> implements IFrClientService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ShopMapper shopMapper;
    @Autowired
    IFrClientPersonalService clientPersonalService;
    @Autowired
    IFrClientLatencePersonalService clientLatencePersonalService;
    @Autowired
    IFrClientPicService clientPicService;
    @Autowired
    IPersonnelInfoService personnelInfoService;
    @Autowired
    FrLatenceFollowMapper frLatenceFollowMapper;
    @Autowired
    IFrCardService frCardService;
    @Autowired
    IFrClientPersonnelRelateService frClientPersonnelRelateService;
    @Autowired
    FrCardMapper frCardMapper;
    @Resource
    private IFrClientPersonalService iFrClientPersonalService;
    @Resource
    private IFrClientLatencePersonalService iFrClientLatencePersonalService;

    @Resource
    private IFrStoreSingleService iFrStoreSingleService;


    @Override
    public PageUtils selectExistenceList(ExistenceFilterParam conditions) throws YJException {
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //获取当前登录用户的id
        String code = CookieUtils.getCookieValue(req, "code", true);
        String shopId = CookieUtils.getCookieValue(req, "shopid", true);
        conditions.setCustomerCode(code);
        conditions.setShopId(shopId);
        Map<String, Object> map = new HashMap<>();
        map.put("page", conditions.getPage());
        map.put("limit", conditions.getLimit());
        Page page = new Query<FrClient>(map).getPage();
        List<ExistenceClientDTO> list = baseMapper.selectExistenceList(page, conditions);
        page.setRecords(list);
        return new PageUtils(page);
    }

    @Override
    public PageUtils selectPotentialList(PotentialFilterParam conditions) throws YJException {
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //获取当前登录用户的id
        String code = CookieUtils.getCookieValue(req, "code", true);
        String shopId = CookieUtils.getCookieValue(req, "shopid", true);
        conditions.setCustomerCode(code);
        conditions.setShopId(shopId);
        Map<String, Object> map = new HashMap<>();
        map.put("page", conditions.getPage());
        map.put("limit", conditions.getLimit());
        Page page = new Query<FrClient>(map).getPage();
        List<PotentialClientDTO> list = baseMapper.selectPotentialList(page, conditions);
        page.setRecords(list);
        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JsonResult addPotentialCustomer(AddPotentialParam potentialParam) throws YJException {
        FrClient base = potentialParam.getBase();
        //校验参数
        if (base.getClientName() == null || base.getMobile() == null || base.getSex() == null || potentialParam.getSalespersonId() == null) {
            throw new YJException(YJExceptionEnum.PARAM_ERROR);
        }
        //获取客户代码
        String custmerCode = HttpServletUtils.getCustmerCode();
        if (StringUtils.isEmpty(custmerCode)) {
            throw new YJException(YJExceptionEnum.CUSTOMERCODE_NOT_FOUND);
        }
        //查询手机号码是否已经被注册
        EntityWrapper entityWrapper = new EntityWrapper<>();
        entityWrapper.where("is_using = 1");
        entityWrapper.and("CustomerCode = {0}", custmerCode);
        entityWrapper.and("mobile = {0}", base.getMobile());
        FrClient existedClient = selectOne(entityWrapper);
        if (existedClient != null) {
            //查询该客户所在的门店
            Shop shop = shopMapper.selectShopByClientId(existedClient.getId());
            if (shop != null) {
                log.info("该手机号已在" + shop.getCityName() + shop.getShopName() + "录入");
                return JsonResult.failMessage("该手机号已在" + shop.getCityName() + shop.getShopName() + "录入");
            }
            log.info("该手机号已录入");
            return JsonResult.failMessage("该手机号已录入");
        }
        base.setId(UUIDUtils.generateGUID());
        //根据人员id查询人员
        PersonnelInfo personnelInfo = personnelInfoService.selectById(potentialParam.getSalespersonId());
        if (personnelInfo != null) {
            base.setConsultantName(personnelInfo.getRelName());
        }
        log.info("销售顾问[{}]", personnelInfo);
        base.setConsultantId(potentialParam.getSalespersonId());
        //检查建档日期
        if (base.getBuildDate() == null) {
            base.setBuildDate(new Date());
        }
        Integer insert = baseMapper.insert(base);
        boolean b = SqlHelper.retBool(insert);
        log.info("写入潜在客户[{}]", b);
        if (b) {
            //插入潜在客户关系表
            FrClientLatencePersonal relation = new FrClientLatencePersonal();
            relation.setClientId(base.getId());
            relation.setId(UUIDUtils.generateGUID());
            relation.setShopId(potentialParam.getShopId());
            relation.setPersonalId(potentialParam.getSalespersonId());
            boolean insert2 = clientLatencePersonalService.insert(relation);
            log.info("写入潜在客户关系[{}]", insert2);
            //插入用户头像
            if (potentialParam.getPicLink() != null) {
                FrClientPic pic = new FrClientPic();
                pic.setId(UUIDUtils.generateGUID());
                pic.setClientId(base.getId());
                pic.setPicLink(potentialParam.getPicLink());
                pic.setPicState(true);
                pic.setPicType(1);
                boolean insert1 = clientPicService.insert(pic);
                log.info("写入客户头像[{}]", insert1);
            }
            return JsonResult.successMessage("保存成功");
        }
        return JsonResult.failMessage("保存失败");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JsonResult delPotentialCustomer(String id) {
        //查找客户是否存在
        FrClient frClient = selectById(id);
        if (frClient != null) {
            frClient.setUsing(false);
            boolean b = updateById(frClient);
            if (b) {
                return JsonResult.successMessage("删除成功");
            }
        }
        return JsonResult.failMessage("删除失败");
    }

    @Override
    public ExistenceCountDTO selectExistenceStat(String code, String shopId) {
        EntityWrapper ew = new EntityWrapper<>();
        ew.where("CustomerCode = {0}", code);
        if (StringUtils.isNotEmpty(shopId)) {
            ew.and("shop_id = {0}", shopId);
        }
        //查询会员总数
        Integer count = clientPersonalService.selectCount(ew);
        //查询近一周生日会员数
        Integer birthdayCount = baseMapper.selectBirthdayCount(7, code, shopId);
        ew.and("DateDiff(wk, create_time, GetDate()) = 0");
        //查询本周新增会员数
        Integer addCount = clientPersonalService.selectCount(ew);
        ExistenceCountDTO dto = new ExistenceCountDTO();
        dto.setCount(count);
        dto.setBirthdayCount(birthdayCount);
        dto.setAddCount(addCount);
        return dto;
    }


    @Override
    public JsonResult getClient(String id) {
        Map<String, Object> client = baseMapper.getClient(id);
        return JsonResult.success(client);
    }

    @Override
    public JsonResult getClientList(String CustomerCode, String mobile) {
        List<FrClient> clients = baseMapper.selectList(new EntityWrapper<FrClient>().where("is_using={0}", 1)
                .and("mobile={0}", mobile).and("CustomerCode={0}", CustomerCode));
        return JsonResult.success(clients);
    }

    @Override
    public JsonResult addCustomer(AddCustomerParam customerParam) throws YJException {
        FrClient base = customerParam.getBase();
        //校验参数
        if (base.getClientName() == null || base.getMobile() == null || base.getSex() == null || customerParam.getSalespersonId() == null) {
            throw new YJException(YJExceptionEnum.PARAM_ERROR);
        }
        //获取客户代码
        String custmerCode = HttpServletUtils.getCustmerCode();
        if (StringUtils.isEmpty(custmerCode)) {
            throw new YJException(YJExceptionEnum.CUSTOMERCODE_NOT_FOUND);
        }
        //查询手机号码是否已经被注册
        EntityWrapper entityWrapper = new EntityWrapper<>();
        entityWrapper.where("is_using = 1");
        entityWrapper.and("CustomerCode = {0}", custmerCode);
        entityWrapper.and("mobile = {0}", base.getMobile());
        FrClient existedClient = selectOne(entityWrapper);
        if (existedClient != null) {
            //查询该客户所在的门店
            Shop shop = shopMapper.selectShopByClientId(existedClient.getId());
            if (shop != null) {
                log.info("该手机号已在" + shop.getCityName() + shop.getShopName() + "录入");
                return JsonResult.failMessage("该手机号已在" + shop.getCityName() + shop.getShopName() + "录入");
            }
            log.info("该手机号已录入");
            return JsonResult.failMessage("该手机号已录入");
        }
        base.setId(UUIDUtils.generateGUID());
        //根据人员id查询人员
        PersonnelInfo personnelInfo = personnelInfoService.selectById(customerParam.getSalespersonId());
        if (personnelInfo != null) {
            base.setConsultantName(personnelInfo.getRelName());
        }
        log.info("销售顾问[{}]", personnelInfo);
        base.setConsultantId(customerParam.getSalespersonId());
        //检查建档日期
        if (base.getBuildDate() == null) {
            base.setBuildDate(new Date());
        }
        Integer insert = baseMapper.insert(base);
        boolean b = SqlHelper.retBool(insert);
        log.info("写入现有客户[{}]", b);
        if (b) {
            //插入现有客户关系表
            FrClientPersonal relation = new FrClientPersonal();
            relation.setClientId(base.getId());
            relation.setId(UUIDUtils.generateGUID());
            relation.setShopId(customerParam.getShopId());
            relation.setPersonalId(customerParam.getSalespersonId());
            boolean insert2 = clientPersonalService.insert(relation);
            log.info("写入现有客户关系[{}]", insert2);
            //插入用户头像
            if (customerParam.getPicLink() != null) {
                FrClientPic pic = new FrClientPic();
                pic.setId(UUIDUtils.generateGUID());
                pic.setClientId(base.getId());
                pic.setPicLink(customerParam.getPicLink());
                pic.setPicState(true);
                pic.setPicType(1);
                boolean insert1 = clientPicService.insert(pic);
                log.info("写入客户头像[{}]", insert1);
            }
            return JsonResult.successMessage("保存成功");
        }
        return JsonResult.failMessage("保存失败");
    }

    /**
     * 我的潜在客户
     *
     * @param params
     * @return
     * @throws YJException
     */
    @Override
    public PageUtils selectmyPotentialList(MyPotentialFilterParam params) throws YJException {
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //获取当前登录用户的id
        String customerCode = CookieUtils.getCookieValue(req, "code", true);
        String shopId = CookieUtils.getCookieValue(req, "shopid", true);
        String personalId = CookieUtils.getCookieValue(req, "id", true);
        params.setPersonalId(personalId);
        params.setCustomerCode(customerCode);
        params.setShopId(shopId);
        Map<String, Object> map = new HashMap<>();
        map.put("page", params.getPage());
        map.put("limit", params.getLimit());
        Page page = new Query<FrClient>(map).getPage();
        List<MyPotentialClientDTO> list = baseMapper.findPotential(page, params);

        page.setRecords(list);
        return new PageUtils(page);
    }

    /**
     * 潜在未被认领
     *
     * @param params
     * @return
     * @throws YJException
     */
    @Override
    public PageUtils selectCollarList(CollarClientParam params) throws YJException {
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //获取当前登录用户的id
        String customerCode = CookieUtils.getCookieValue(req, "code", true);
        params.setCustomerCode(customerCode);
        Map<String, Object> map = new HashMap<>();
        map.put("page", params.getPage());
        map.put("limit", params.getLimit());
        Page page = new Query<FrClient>(map).getPage();
        List<CollarClientDTO> list = baseMapper.getNoCollerClient(page, params);
        //无人跟进天数
        setNoFollowDay(list);
        page.setRecords(list);
        return new PageUtils(page);
    }

    /**
     * 我的现有客户
     *
     * @param params
     * @return
     * @throws YJException
     */
    @Override
    public PageUtils selectMyExistenceList(MyExistenceFilterParam params) throws YJException {
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //获取当前登录用户的id
        String customerCode = CookieUtils.getCookieValue(req, "code", true);
        String shopId = CookieUtils.getCookieValue(req, "shopid", true);
        String personalId = CookieUtils.getCookieValue(req, "id", true);
        params.setPersonalId(personalId);
        params.setCustomerCode(customerCode);
        params.setShopId(shopId);
        Map<String, Object> map = new HashMap<>();
        map.put("page", params.getPage());
        map.put("limit", params.getLimit());
        Page page = new Query<FrClient>(map).getPage();
        List<MyExistenceClientDTO> list = baseMapper.findMyExistenceList(page, params);
        for (MyExistenceClientDTO myExistenceClientDTO : list) {
            List<Map<String, String>> coach = frClientPersonnelRelateService.getService(myExistenceClientDTO.getId(), "3");
            myExistenceClientDTO.setCoachList(coach);
        }

        page.setRecords(list);
        return new PageUtils(page);
    }

    /**
     * 现有未被认领
     *
     * @param params
     * @return
     * @throws YJException
     */
    @Override
    public PageUtils selectExistingList(CollarClientParam params) throws YJException {
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //获取当前登录用户的id
        String customerCode = CookieUtils.getCookieValue(req, "code", true);
        params.setCustomerCode(customerCode);
        Map<String, Object> map = new HashMap<>();
        map.put("page", params.getPage());
        map.put("limit", params.getLimit());
        Page page = new Query<FrClient>(map).getPage();
        List<CollarClientDTO> list = baseMapper.getNoExistingClient(page, params);
        //无人跟进天数
        setNoFollowDay(list);
        page.setRecords(list);
        return new PageUtils(page);
    }

    private void setNoFollowDay(List<CollarClientDTO> list) {
        for (CollarClientDTO collarClientDTO : list) {
            if (collarClientDTO.getFollowTime() != null) {
                Date followTime = collarClientDTO.getFollowTime();
                Date nowTime = DateUtils.getNowTime2();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Integer noFollow = null;
                try {
                    Date endDate = simpleDateFormat.parse(simpleDateFormat.format(followTime));
                    Date beginDate = simpleDateFormat.parse(simpleDateFormat.format(nowTime));
                    noFollow = (int) ((beginDate.getTime() - endDate.getTime()) / (24 * 60 * 60 * 1000));
                    collarClientDTO.setOnFollow(noFollow);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 客户分配
     *
     * @param params
     * @return
     * @throws YJException
     */
    @Override
    public PageUtils selectClientAllot(CollarClientParam params) throws YJException {
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //获取当前登录用户的id
        String customerCode = CookieUtils.getCookieValue(req, "code", true);
        String personalId = CookieUtils.getCookieValue(req, "id", true);
        String shopId = CookieUtils.getCookieValue(req, "shopid", true);
        params.setCustomerCode(customerCode);
        params.setPersonalId(personalId);
        params.setShopId(shopId);
        Map<String, Object> map = new HashMap<>();
        map.put("page", params.getPage());
        map.put("limit", params.getLimit());
        Page page = new Query<FrClient>(map).getPage();
        List<CollarClientDTO> list = baseMapper.getClientAllot(page, params);
        page.setRecords(list);
        return new PageUtils(page);
    }

    /**
     * 客户信息分析
     *
     * @param params
     * @return
     * @throws YJException
     */
    @Override
    public PageUtils selectClientInformation(ClientInformationParam params) throws YJException {
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //获取当前登录用户的id
        String customerCode = CookieUtils.getCookieValue(req, "code", true);
        String personalId = CookieUtils.getCookieValue(req, "id", true);
        String shopId = CookieUtils.getCookieValue(req, "shopid", true);
        params.setCustomerCode(customerCode);
        params.setPersonalId(personalId);
        params.setShopId(shopId);
        Map<String, Object> map = new HashMap<>();
        map.put("page", params.getPage());
        map.put("limit", params.getLimit());
        Page page = new Query<FrClient>(map).getPage();
        List<ClientInformationDTO> list = baseMapper.getClientInformation(page, params);
        for (ClientInformationDTO clientInformationDTO : list) {
            String cid = clientInformationDTO.getId();
            //客户信息
            Map clientInformation = baseMapper.getClientEssentialInformation(cid);
            clientInformationDTO.setClientInformation(clientInformation);
            //购卡信息
            List<FrCard> cardInformation = frCardService.getCardInformation(cid);
            clientInformationDTO.setCardInformation(cardInformation);
            //服务人员信息
            List<Map<String, String>> service = frClientPersonnelRelateService.getService(cid, "2");
            clientInformationDTO.setServicePersonal(service);
        }
        page.setRecords(list);
        return new PageUtils(page);
    }

    @Override
    public PageUtils existenceListBG(HttpServletRequest request,ExistenceFilterParam params) throws YJException {
        String code = CookieUtils.getCookieValue(request, "code", true);
        FrCard frCard = new FrCard();
        frCard.setCustomerCode(code);
        Map<String, Object> map = new HashMap<>();
        map.put("page", params.getPage());
        map.put("limit", params.getLimit());
        Page page = new Query<FrCard>(map).getPage();
        List<ClientUploadDTO> list = frCardMapper.queryUserCardInfoListBG(page, frCard);
        page.setRecords(list);
        return new PageUtils(page);
    }

    @Override
    public PageUtils potentialListBG(HttpServletRequest request, PotentialFilterParam params) throws YJException {
        //获取当前登录用户的id
        String code = CookieUtils.getCookieValue(request, "code", true);
        String shopId = CookieUtils.getCookieValue(request, "shopid", true);
        params.setCustomerCode(code);
        params.setShopId(shopId);
        Map<String, Object> map = new HashMap<>();
        map.put("page", params.getPage());
        map.put("limit", params.getLimit());
        Page page = new Query<FrClient>(map).getPage();
        List<ProspectiveClientDTO> list = baseMapper.selectPotentialListBG(page, params);
        page.setRecords(list);
        return new PageUtils(page);
    }


    /**
     * 增加一条现有客户数据
     *
     * @param frClient
     * @param isFlag   若客户不是现有是否需要插入现有客户数据 true 是
     * @return
     * @throws YJException
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public FrClient addClientPersonal(FrClient frClient, boolean isFlag, String shopId) throws YJException {
        if (frClient == null) {
            throw new YJException(YJExceptionEnum.REQUEST_NULL);
        }
        if (StringUtils.isEmpty(frClient.getClientName()) || StringUtils.isEmpty(frClient.getMobile()) || StringUtils.isEmpty(frClient.getCustomerCode())) {
            //客户姓名，客户手机号必须提供
            throw new YJException(YJExceptionEnum.PARAM_ERROR);
        }
        //默认客户是存在的不需要插入数据；
        String clientId = frClient.getId();
        FrClient frClient1 = baseMapper.selectById(clientId);
        boolean clientFlag = false;
        if (frClient1 == null) {
            clientFlag = true;
            //尝试查找客户信息
            List<FrClient> frClients = baseMapper.selectList(new EntityWrapper<FrClient>()
                    .where("mobile={0}", frClient.getMobile()).and("client_name={0}", frClient.getClientName())
                    .and("CustomerCode={0}", frClient.getCustomerCode()).and("is_using ={0}", 1));
            if (frClients != null && frClients.size() > 0) {
                if (frClients.size() > 1) {
                    throw new YJException(YJExceptionEnum.CLIENT_NUM_EXISTED);
                }
                frClient1 = frClients.get(0);
                clientFlag = false;
            }
        }
        //如果客户不存在
        if (clientFlag) {
            // 不插入现有客户的情况下，直接返回；
            if (!isFlag) {
                return frClient1;
            }
            //查询设置的保护天数
            FrStoreSingle frStoreSingle = iFrStoreSingleService.selectOne(
                    new EntityWrapper<FrStoreSingle>().where("CustomerCode={0}", frClient.getCustomerCode()).and("is_using={0}", 1)
                            .orderBy("update_time DESC"));
            Integer protectDay = 1;
            if (frStoreSingle != null) {
                Integer hours = frStoreSingle.getXyGjHour();
                if (hours != null && hours > 0) {
                    hours = hours / 24;
                    if (hours > 0) {
                        protectDay = hours;
                    }
                }
            }
            Date date = new Date();
            //初始化新的客户数据准备插入
            frClient1 = frClient;
            frClient1.setId(UUIDUtils.generateGUID());
            frClient1.setStatus(false);
            frClient1.setApplyTime(date);
            frClient1.setProtectDay(protectDay);
            baseMapper.insert(frClient1);
        }
        FrClientPersonal frClientPersonal = null;
        FrClientLatencePersonal frClientLatencePersonal = null;
        //如果客户是存在的，判断是否已存在现有客户
        if (!clientFlag) {
            //是否现有客户
            frClientPersonal = iFrClientPersonalService.selectOne(new EntityWrapper<FrClientPersonal>()
                    .where("client_id={0}", frClient1.getId()).and("CustomerCode={0}", frClient1.getCustomerCode()));
        }
        //不是现有客户，客户存在，需要生产一条现有客户数据
        if (frClientPersonal == null && frClient1 != null && isFlag) {
            //确认客户是否潜在客户
            frClientLatencePersonal = iFrClientLatencePersonalService.selectOne(new EntityWrapper<FrClientLatencePersonal>()
                    .where("client_id={0}", frClient1.getId()).and("CustomerCode={0}", frClient1.getCustomerCode())
                    .and("is_using={0}", 1));
            //如果是潜在客户，有客户转让潜在客户，需要操作更新潜在客户表，isUsing false 潜在客户与现有客户不能同时存在
            if (frClientLatencePersonal != null) {
                FrClientLatencePersonal frClientLatencePersonal1 = new FrClientLatencePersonal();
                frClientLatencePersonal1.setUsing(false);
                iFrClientLatencePersonalService.update(frClientLatencePersonal1, new EntityWrapper<FrClientLatencePersonal>()
                        .where("id={0}", frClientLatencePersonal.getId()).and("is_using={0}", 1)
                        .and("client_id={0}", frClientLatencePersonal.getId()).and("CustomerCode={0}", frClientLatencePersonal.getCustomerCode()));
            }
            frClientPersonal = new FrClientPersonal();
            frClientPersonal.setClientId(frClient1.getId());
            frClientPersonal.setCustomerCode(frClient1.getCustomerCode());
            frClientPersonal.setId(UUIDUtils.generateGUID());
            frClientPersonal.setUsing(true);
            frClientPersonal.setShopId(shopId);
            frClientPersonal.setProtectDay(frClient1.getProtectDay());
            iFrClientPersonalService.insert(frClientPersonal);
        }
        //之前已经存在的现有客户，状态是不启用的更新为启用
        if (frClientPersonal.getUsing() != null && !frClientPersonal.getUsing()) {
            FrClientPersonal frClientPersonal1 = new FrClientPersonal();
            frClientPersonal1.setUsing(true);
            //若之前的前有客户状态是不启用，更新客户状态
            iFrClientPersonalService.update(frClientPersonal1, new EntityWrapper<FrClientPersonal>()
                    .where("client_id={0}", frClient1.getId()).and("CustomerCode={0}", frClient1.getCustomerCode()));
        }
        return frClient1;
    }


    /**
     * 判断是否已经是现有客户了
     *
     * @param frClient
     * @return
     */
    @Override
    public List<FrClient> queryByClient(FrClient frClient) throws YJException {
        if (frClient == null) {
            throw new YJException(YJExceptionEnum.REQUEST_NULL);
        }
        if (StringUtils.isEmpty(frClient.getCustomerCode()) || StringUtils.isEmpty(frClient.getMobile())
                || StringUtils.isEmpty(frClient.getClientName())) {
            throw new YJException(YJExceptionEnum.PARAM_ERROR);
        }
        List<FrClient> frClientList = baseMapper.queryByClient(frClient);
        return frClientList;
    }

    @Override
    public JsonResult getPersonalDetails(String id) {
        String shopId = null;
        String shopName = null;
        FrClientLatencePersonal frClientLatencePersonal = clientLatencePersonalService.selectOne(
                new EntityWrapper<FrClientLatencePersonal>().where("is_using=1 and client_id={0}", id)
        );

        if (frClientLatencePersonal == null) {
            FrClientPersonal frClientPersonal = clientPersonalService.selectOne(
                    new EntityWrapper<FrClientPersonal>().where("is_using=1 and client_id={0}", id)
            );
            shopId = frClientPersonal.getShopId();
        } else {
            shopId = frClientLatencePersonal.getShopId();
        }
        if (shopId != null) {
            Shop shop = shopMapper.selectById(shopId);
            shopName = shop.getShopName();
        }
        Map<String, Object> map = selectMap(
                new EntityWrapper<FrClient>()
                        .setSqlSelect("client_name clientName,sex,vip_level vipLevel,mobile," +
                                "willing_card_type willingCardType,willing_card_name willingCardName,consultant_name consultantName,reference_name referenceName" +
                                ",reference_tel referenceTel,build_date buildDate,qq,wechat")
                        .where("is_using=1 and id={0}", id)
        );
        if (shopName != null) {
            map.put("shopName", shopName);
        }

        return JsonResult.success(map);
    }

    @Override
    public JsonResult getPotentialClientList() {
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //获取当前登录用户的id
        String customerCode = CookieUtils.getCookieValue(req, "code", true);

        String shopId = CookieUtils.getCookieValue(req, "shopid", true);
        List<Map<String, Object>> potentialClientList = baseMapper.selectPotentialClientList(customerCode, shopId);

        return JsonResult.success(potentialClientList);
    }

    @Override
    public JsonResult getEmployeeClientList() {
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //获取当前登录用户的id
        String customerCode = CookieUtils.getCookieValue(req, "code", true);

        String shopId = CookieUtils.getCookieValue(req, "shopid", true);
        List<Map<String, Object>> employeeClientList = baseMapper.selectEmployeeClientList(shopId, customerCode);

        return JsonResult.success(employeeClientList);
    }


}
