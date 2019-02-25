package com.yj.service.service.impl;

import com.yj.dal.dao.ConsumeAccountInfoMapper;
import com.yj.dal.dao.ConsumeAccountOrderMapper;
import com.yj.dal.dao.MoneyReportMapper;
import com.yj.dal.model.ConsumeAccountInfo;
import com.yj.dal.model.ConsumeAccountOrder;
import com.yj.dal.model.MoneyReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * className FrConsumeMoneyOrderServiceImpl
 * by Kylan
 * 消费金额订单服务
 *
 * @author Kylan
 * @date 2019-02-23 19:03
 */
@Service
public class FrConsumeMoneyOrderServiceImpl {

    @Autowired
    private MoneyReportMapper moneyReportMapper;

    @Autowired
    private ConsumeAccountInfoMapper consumeAccountInfoMapper;

    @Autowired
    private ConsumeAccountOrderMapper consumeAccountOrderMapper;


    /**
     * 统一账单表
     * @param moneyReport
     */
    public void saveMoneyReport(MoneyReport moneyReport){
        moneyReportMapper.insert(moneyReport);
    }


    /**
     * 消费账单明细
     * @param consumeAccountInfo
     */
    public void saveConsumeAccountInfo(ConsumeAccountInfo consumeAccountInfo){
        consumeAccountInfoMapper.insert(consumeAccountInfo);
    }

    /**
     * 消费账单
     * @param consumeAccountOrder
     */
    public void saveConsumeAccountOrder(ConsumeAccountOrder consumeAccountOrder){
        consumeAccountOrderMapper.insert(consumeAccountOrder);
    }

    /**
     * 更新结束时间
     * @param tableId
     * @param date
     */
    public void updateEndTime(String tableId, Date date){
        ConsumeAccountInfo consumeAccountInfo = new ConsumeAccountInfo();
        consumeAccountInfo.setTableId(tableId);
        consumeAccountInfo = consumeAccountInfoMapper.selectOne(consumeAccountInfo);
        consumeAccountInfo.setEndTime(date);
        consumeAccountInfoMapper.updateAllColumnById(consumeAccountInfo);

    }


    /**
     * 生成随机订单号（判断是否已存在）
     *
     * @return
     */
    public String getOrderIdByTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String newDate = sdf.format(new Date());
        String result = "";
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            result += random.nextInt(10);
        }
        return newDate + result;
    }


}
