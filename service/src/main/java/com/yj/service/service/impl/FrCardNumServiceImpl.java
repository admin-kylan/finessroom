package com.yj.service.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.result.JsonResult;
import com.yj.common.util.HttpServletUtils;
import com.yj.dal.dao.FrCardDiscardedMapper;
import com.yj.dal.dao.FrCardMapper;
import com.yj.dal.dto.NumRoleDTO;
import com.yj.dal.model.*;
import com.yj.dal.dao.FrCardNumMapper;
import com.yj.service.service.IFrCardNumService;
import com.yj.service.base.BaseServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * <p>
 * 会员卡号规则表 服务实现类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-27
 */
@Service
public class FrCardNumServiceImpl extends BaseServiceImpl<FrCardNumMapper, FrCardNum> implements IFrCardNumService {

    @Resource
    private FrCardMapper frCardMapper;

    @Resource
    private FrCardDiscardedMapper frCardDiscardedMapper;

    @Override
    public List<NumRoleDTO> selectFrCardNumRuleList(String code) {
        return baseMapper.selectRoleList(code);
    }

    @Override
    @Transactional
    public boolean addRole(FrCardNum frCardNum) throws YJException {
        //获取客户代码
        String custmerCode = HttpServletUtils.getCustmerCode();
        if(StringUtils.isEmpty(custmerCode)){
            throw new YJException(YJExceptionEnum.CUSTOMERCODE_NOT_FOUND);
        }
        frCardNum.setCustomerCode(custmerCode);
        frCardNum.setHeadNum(frCardNum.getLimitNum());
        frCardNum.setMiddleNum(frCardNum.getLimitNum());
        frCardNum.setTailNum(frCardNum.getLimitNum());
        Integer result = baseMapper.insert(frCardNum);
        return SqlHelper.retBool(result);
    }

    /**
     * 判断会员卡号是否符合规则
     * @param cardNum
     * @param code
     * @return
     */
    @Override
    public String checkCardNum(String cardNum, String code) {
        //获取会员卡号规则表
        List<FrCardNum> list = baseMapper.selectList(
                new EntityWrapper<FrCardNum>().where("CustomerCode = {0}",code)
        );
        if(list.size() < 1){
            return "1";
        }
        String id="";
        for(FrCardNum frCardNum:list){//判断是否属于开始于结束编号的范围
            String n = "";
            if(frCardNum == null || StringUtils.isEmpty(frCardNum.getStartNo())){
                break;
            }
            if(!(Integer.valueOf(cardNum)>=Integer.valueOf(frCardNum.getStartNo())&&
                    Integer.valueOf(cardNum)<=Integer.valueOf(frCardNum.getEndNo())) ){
                n = "0";
            }
            if(frCardNum.getUseHeadNum()){//是否去除头
                if(cardNum.startsWith(frCardNum.getHeadNum())){//判断是否以去除的数字开头
                    n = "0";
                }
            }
            if(frCardNum.getUseLimitNum()){//是否去除尾部
                if(cardNum.endsWith(frCardNum.getLimitNum())){//判断是否以去除的数字结尾
                    n = "0";
                }
            }
            if(frCardNum.getUseMiddleNum()){//是否出去中间数
                int a = cardNum.indexOf(frCardNum.getMiddleNum());
                if(a != -1){//判断是否包含去除的数字
                    n = "0";
                }
            }
            //判断是否已存在
            //获取协议表数据
            List<FrCard> list1 = frCardMapper.selectList(
                    new EntityWrapper<FrCard>()
                            .where("CustomerCode = {0}",code)
            );
            for(FrCard frCard:list1){
                if(cardNum.equals(frCard.getCardNo())){
                    n = "0";
                }
            }

            //判断是否废弃
            List<FrCardDiscarded> list2 = frCardDiscardedMapper.selectList(
                    new EntityWrapper<FrCardDiscarded>()
                            .where("is_using = {0}",1)
                            .and("CustomerCode = {0}",code)
            );
            for(FrCardDiscarded fs:list2){
                if(String.valueOf(n).equals(fs.getCardNo())){
                    n = "0";
                }
            }
            
            if("".equals(n)){
                id = frCardNum.getId();
                break;
            }
        }
        return id;
    }

    /**
     * 随机生成会员卡号
     * @param code
     * @return
     */
    @Override
    public JsonResult addCardNum(String code) {
        //获取会员卡号规则表
        List<FrCardNum> list = baseMapper.selectList(
                new EntityWrapper<FrCardNum>().where("CustomerCode = {0}",code)
        );
        //一直生成符合第一条的会员卡号，不过还要判断是否已存在
        if(list.size()>0){
            FrCardNum frCardNum = list.get(0);
            int startNo = Integer.valueOf(frCardNum.getStartNo());//开始编码
            int endNo = Integer.valueOf(frCardNum.getEndNo());//结束编号
            Random random = new Random();
            Integer n;
            while (true){
                int ce =0;
                n = random.nextInt(endNo-startNo)+startNo;
                if(frCardNum.getUseHeadNum()){//是否去除头
                    if(String.valueOf(n).startsWith(frCardNum.getHeadNum())){//判断是否以去除的数字开头
                        ce = 1;
                    }
                }
                if(frCardNum.getUseLimitNum()){//是否去除尾部
                    if(String.valueOf(n).endsWith(frCardNum.getLimitNum())){//判断是否以去除的数字结尾
                        ce = 1;
                    }
                }
                if(frCardNum.getUseMiddleNum()){//是否出去中间数
                    int a = String.valueOf(n).indexOf(frCardNum.getMiddleNum());
                    if(a != -1){//判断是否包含去除的数字
                        ce = 1;
                    }
                }
                //判断是否已存在
                //获取协议表数据
                List<FrCard> list1 = frCardMapper.selectList(
                        new EntityWrapper<FrCard>()
                                .where("CustomerCode = {0}",code)
                );
                for(FrCard frCard:list1){
                    if(String.valueOf(n).equals(frCard.getCardNo())){
                        ce = 1;
                    }
                }

                //判断是否废弃
                List<FrCardDiscarded> list2 = frCardDiscardedMapper.selectList(
                        new EntityWrapper<FrCardDiscarded>()
                                .where("is_using = {0}",1)
                                .and("CustomerCode = {0}",code)
                );
                for(FrCardDiscarded fs:list2){
                    if(String.valueOf(n).equals(fs.getCardNo())){
                        ce = 1;
                    }
                }

                if(ce == 0){
                    break;
                }
            }
            Map<String,Object> map = new HashMap<>();
            map.put("cardNumId",frCardNum.getId());
            map.put("cardNo",n);
            return JsonResult.success(map);
        }else {
            return JsonResult.failMessage("暂无会员卡号规则，请创建会员卡号规则");
        }
    }

    @Override
    public Map<String,String> getCardNum(String code) {
        //生成协议编号
        Map<String,String> frCardNum = null;
        //生成规则
        JsonResult rds = this.addCardNum(code);
        Object obj = rds.getData();
        if(obj != null){
            //初始化规则信息
            frCardNum = new HashMap<>();
            Map<String,Object> map  = (Map)obj;
            String cardNumId = this.getParemtToMap(map,"cardNumId");
            String cardNo = this.getParemtToMap(map,"cardNo");
            frCardNum.put("cardNumId",cardNumId);
            frCardNum.put("cardNo",cardNo);
        }
        return  frCardNum;
    }

    public  String  getParemtToMap(Map<String,Object> map,String key){
        String par = "";
        if(map != null){
            Object str = map.get(key);
            if(str != null){
                par = str.toString();
            }
        }
        return par;
    }
}
