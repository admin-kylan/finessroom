package com.yj.service.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.result.JsonResult;
import com.yj.common.util.HttpServletUtils;
import com.yj.dal.dao.FrAgreementDiscardedMapper;
import com.yj.dal.dao.FrCardAgreementMapper;
import com.yj.dal.dto.NumRoleDTO;
import com.yj.dal.model.FrAgreement;
import com.yj.dal.dao.FrAgreementMapper;
import com.yj.dal.model.FrAgreementDiscarded;
import com.yj.dal.model.FrCardAgreement;
import com.yj.service.service.IFrAgreementService;
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
 * 协议号规则表 服务实现类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-27
 */
@Service
public class FrAgreementServiceImpl extends BaseServiceImpl<FrAgreementMapper, FrAgreement> implements IFrAgreementService {

    @Resource
    private FrCardAgreementMapper frCardAgreementMapper;

    @Resource
    private FrAgreementDiscardedMapper frAgreementDiscardedMapper;

    @Override
    public List<NumRoleDTO> selectAgreementRuleList(String code) {
        List<NumRoleDTO> numRoleDTOS = baseMapper.selectRoleList(code);
        return numRoleDTOS;
    }

    @Override
    @Transactional
    public boolean addRole(FrAgreement frAgreement) throws YJException {
        //获取客户代码
        String custmerCode = HttpServletUtils.getCustmerCode();
        if(StringUtils.isEmpty(custmerCode)){
            throw new YJException(YJExceptionEnum.CUSTOMERCODE_NOT_FOUND);
        }
        frAgreement.setCustomerCode(custmerCode);
        frAgreement.setHeadNum(frAgreement.getLimitNum());
        frAgreement.setMiddleNum(frAgreement.getLimitNum());
        frAgreement.setTailNum(frAgreement.getLimitNum());
        Integer result = baseMapper.insert(frAgreement);
        return SqlHelper.retBool(result);
    }

    /**
     * 判断协议号是否符合规则
     * @param agreement
     * @return
     */
    @Override
    public String checkCardAgreement(String agreement,String code) {
        //获取协议号规则表
        List<FrAgreement> list = baseMapper.selectList(
                new EntityWrapper<FrAgreement>().where("CustomerCode = {0}",code)
        );
        if(list.size() < 1){
            return "1";
        }
        String id="";
        for(FrAgreement frAgreement:list){//判断是否属于开始于结束编号的范围
            String n = "";
            if(!(Long.valueOf(agreement)>=Long.valueOf(frAgreement.getStartNo())&&
                    Long.valueOf(agreement)<=Long.valueOf(frAgreement.getEndNo())) ){
                n = "0";
            }
            if(frAgreement.getUseHeadNum()){//是否去除头
                if(agreement.startsWith(frAgreement.getHeadNum())){//判断是否以去除的数字开头
                    n = "0";
                }
            }
            if(frAgreement.getUseLimitNum()){//是否去除尾部
                if(agreement.endsWith(frAgreement.getLimitNum())){//判断是否以去除的数字结尾
                    n = "0";
                }
            }
            if(frAgreement.getUseMiddleNum()){//是否出去中间数
                int a = agreement.indexOf(frAgreement.getMiddleNum());
                if(a != -1){//判断是否包含去除的数字
                    n = "0";
                }
            }
            //判断是否已存在
            //获取协议表数据
            List<FrCardAgreement> list1 = frCardAgreementMapper.selectList(
                    new EntityWrapper<FrCardAgreement>()
                            .where("is_using = {0}",1)
                            .and("CustomerCode = {0}",code)
            );
            for(FrCardAgreement frCardAgreement:list1){
                if(agreement.equals(frCardAgreement.getAgreementNo())){
                    n = "0";
                }
            }
            //判断是否废弃
            List<FrAgreementDiscarded> list2 = frAgreementDiscardedMapper.selectList(
                    new EntityWrapper<FrAgreementDiscarded>()
                            .where("is_using = {0}",1)
                            .and("CustomerCode = {0}",code)
            );
            for(FrAgreementDiscarded fs:list2){
                if(agreement.equals(fs.getProtocolNo())){
                    n = "0";
                }
            }

            if("".equals(n)){
                id = frAgreement.getId();
                break;
            }

        }
        return id;

    }

    /**
     * 随机生成协议号
     * @return
     */
    @Override
    public JsonResult addCardAgreement(String code) {
        //获取协议号规则表
        List<FrAgreement> list = baseMapper.selectList(
                new EntityWrapper<FrAgreement>().where("CustomerCode = {0}",code)
        );
        //一直生成符合第一条的协议号，不过还要判断是否已存在
        if(list.size()>0){
            FrAgreement frAgreement = list.get(0);
            Long startNo = Long.valueOf(frAgreement.getStartNo());//开始编码
            Long endNo = Long.valueOf(frAgreement.getEndNo());//结束编号
            Random random = new Random();
            Long temp=endNo-startNo;
            Long n;
            while (true){
                int ce =0;
                 n = nextLong(random,temp)+startNo;
                if(frAgreement.getUseHeadNum()){//是否去除头
                    if(String.valueOf(n).startsWith(frAgreement.getHeadNum())){//判断是否以去除的数字开头
                        ce = 1;
                    }
                }
                if(frAgreement.getUseLimitNum()){//是否去除尾部
                    if(String.valueOf(n).endsWith(frAgreement.getLimitNum())){//判断是否以去除的数字结尾
                        ce = 1;
                    }
                }
                if(frAgreement.getUseMiddleNum()){//是否出去中间数
                    int a = String.valueOf(n).indexOf(frAgreement.getMiddleNum());
                    if(a != -1){//判断是否包含去除的数字
                        ce = 1;
                    }
                }
                //判断是否已存在
                //获取协议表数据
                List<FrCardAgreement> list1 = frCardAgreementMapper.selectList(
                  new EntityWrapper<FrCardAgreement>()
                          .where("is_using = {0}",1)
                        .and("CustomerCode = {0}",code)
                );
                for(FrCardAgreement frCardAgreement:list1){
                    if(String.valueOf(n).equals(frCardAgreement.getAgreementNo())){
                        ce = 1;
                    }
                }
                //判断是否废弃
                List<FrAgreementDiscarded> list2 = frAgreementDiscardedMapper.selectList(
                        new EntityWrapper<FrAgreementDiscarded>()
                                .where("is_using = {0}",1)
                                .and("CustomerCode = {0}",code)
                );
                for(FrAgreementDiscarded fs:list2){
                    if(String.valueOf(n).equals(fs.getProtocolNo())){
                        ce = 1;
                    }
                }
                if(ce == 0){
                    break;
                }
            }
            Map<String,Object> map = new HashMap<>();
            map.put("agreementId",frAgreement.getId());
            map.put("agreement",n);
            return JsonResult.success(map);

        }else {
            return JsonResult.failMessage("暂无协议号规则，请创建协议号规则");
        }

    }


    /**
     * 生成协议规则
     * @param code
     * @return
     */
    @Override
    public FrCardAgreement getAgreement(String code){
        //生成协议编号
        FrCardAgreement frCardAgreement = null;
        //生成规则
        JsonResult rds = this.addCardAgreement(code);
        Object obj = rds.getData();
        if(obj != null){
            //初始化规则信息
            frCardAgreement = new FrCardAgreement();
            Map<String,Object> map  = (Map)obj;
            String agreement = this.getParemtToMap(map,"agreement");
            String agreementId = this.getParemtToMap(map,"agreementId");
            frCardAgreement.setAgreementId(agreementId);
            frCardAgreement.setAgreementNo(agreement);
        }
        return  frCardAgreement;
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
    public long nextLong(Random rng, long n) {
        // error checking and 2^x checking removed for simplicity.
        long bits, val;
        do {
            bits = (rng.nextLong() << 1) >>> 1;
            val = bits % n;
        } while (bits-val+(n-1) < 0L);
        return val;
    }
}
