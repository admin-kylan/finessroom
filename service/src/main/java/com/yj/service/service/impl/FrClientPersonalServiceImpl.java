package com.yj.service.service.impl;

import com.yj.common.result.JsonResult;
import com.yj.common.util.CookieUtils;
import com.yj.common.util.UUIDUtils;
import com.yj.dal.model.FrClientPersonal;
import com.yj.dal.dao.FrClientPersonalMapper;
import com.yj.dal.model.FrEmployeeClientFollow;
import com.yj.dal.model.FrFollowPic;
import com.yj.dal.model.FrLatenceFollowPic;
import com.yj.service.service.IFrClientPersonalService;
import com.yj.service.base.BaseServiceImpl;
import com.yj.service.service.IFrFollowPicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 现有客户关系表 服务实现类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-30
 */
@Service
public class FrClientPersonalServiceImpl extends BaseServiceImpl<FrClientPersonalMapper, FrClientPersonal> implements IFrClientPersonalService {

}
