package com.yj.common.util;

import org.springframework.stereotype.Service;

/**
 * 共用常规量
 */
public  interface CommonUtils {

    static final Integer ORDER_TYPE_0 = 0;  //待付款
    static final Integer ORDER_TYPE_1 = 1;  //已付款
    static final Integer ORDER_TYPE_2 = 2;  //已冲销
    static final Integer ORDER_TYPE_3 = 3;  //已退款
    static final Integer ORDER_TYPE_4 = 4;  //已转让
    static final Integer ORDER_TYPE_5 = 5;  //审核通过退款
    static final Integer ORDER_TYPE_6 = 6;  //复核通过退款
    static final Integer ORDER_TYPE_7 = 7;  //审核通过转让
    static final Integer ORDER_TYPE_8 = 8;  //复核通过转让

//  审核状态 （0，待审核1，已审核，2，审核不通过）
    static final Integer ORDER_STATUS_0 = 0;  //待审核
    static final Integer ORDER_STATUS_1 = 1;  //已审核
    static final Integer ORDER_STATUS_2 = 2;  //审核不通过
//  复核状态 （0，待审核1，已审核，2，审核不通过）
    static final Integer AUDIT_ORDER_STATUS_0 = 0;  //待审核
    static final Integer AUDIT_ORDER_STATUS_1 = 1;  //已审核
    static final Integer AUDIT_ORDER_STATUS_2 = 2;  //审核不通过
//   新购消费方式
    static final Integer CONSUME_TYPE_1 = 1;  //会员消费
    static final Integer CONSUME_TYPE_2 = 2;  //普通消费
    static final Integer CONSUME_TYPE_3 = 3;  //员工领用
    static final Integer CONSUME_TYPE_4 = 4;  //协议单位
//  订单表付款方式
    static final Integer ORDER_INFO_PAY_TYPE_1 = 1;  //全款
    static final Integer ORDER_INFO_PAY_TYPE_2 = 2;  //定金(可能有补余记录)
    static final Integer ORDER_INFO_PAY_TYPE_3 = 3;  //赠送
    static final Integer ORDER_INFO_PAY_TYPE_4 = 4;  //置换
    static final Integer ORDER_INFO_PAY_TYPE_5 = 5;  //分期付款(可能有补余记录)
//    补余订单类型
    static final Integer COMPLEMENT_TYPE_1 = 1;  //定金补余
    static final Integer COMPLEMENT_TYPE_2 = 2;  //分期补余
//    订单分期设置详情参数
    static final Integer ORDER_SPLIT_SET_DD_0= 0; //未还款
    static final Integer ORDER_SPLIT_SET_DD_1= 1; //已还款
//  会员卡所有涉及支付的订单类型参数        -----------payModel  cardOrderPrice，cardOrderDatail
    static final Integer PAY_MODE_ORDER_TYPE_1 = 1; //新购
    static final Integer PAY_MODE_ORDER_TYPE_2 = 2; //续卡
    static final Integer PAY_MODE_ORDER_TYPE_3 = 3; //转卡
    static final Integer PAY_MODE_ORDER_TYPE_4 = 4; //补卡
    static final Integer PAY_MODE_ORDER_TYPE_5 = 5; //储值
    static final Integer PAY_MODE_ORDER_TYPE_6 = 6; //补余
    static final Integer PAY_MODE_ORDER_TYPE_7 = 7; //退卡
    static final Integer PAY_MODE_ORDER_TYPE_8 = 8; //停卡
    static final Integer PAY_MODE_ORDER_TYPE_9 = 9; //转让
    static final Integer PAY_MODE_ORDER_TYPE_10 = 10; //卡升级
    static final Integer PAY_MODE_ORDER_TYPE_11 = 11; //子卡分割权益

//    支付方式
    static final Integer PAY_MODE_COUNT= 8; // 注意0：是没有值的，支付方式的统计+1 ，7 种支付方式 数量为8
    static final Integer PAY_MODE_1= 1; //支付宝
    static final Integer PAY_MODE_2= 2; //刷卡
    static final Integer PAY_MODE_3= 3; //微信
    static final Integer PAY_MODE_4= 4; //现金
    static final Integer PAY_MODE_5= 5; //转账
    static final Integer PAY_MODE_6= 6; //花呗
    static final Integer PAY_MODE_7= 7; //其他

    //   业绩表设置参数
    static final Integer ORDER_ALLOT_TYPE_1= 1; //新购；
    static final Integer ORDER_ALLOT_TYPE_6= 6; //补余

    //  业绩分配表 订单类型（1、新购；6、补余）
    static final Integer ALLOT_SET_ORDER_TYPE_1= 1; //新购
    static final Integer ALLOT_SET_ORDER_TYPE_6= 6; //补余
//  补卡类型
    static final Integer CARD_SUPPLY_RECORD_TYPE_1 = 1; //补卡
    static final Integer CARD_SUPPLY_RECORD_TYPE_2 = 2; //续卡
    static final Integer CARD_SUPPLY_RECORD_TYPE_3 = 3; //转卡
    static final Integer CARD_SUPPLY_RECORD_TYPE_4 = 4; //卡升级
//    会员卡状态
    static final Integer CARD_STATUS_0 = 0; //正常
    static final Integer CARD_STATUS_1 = 1; //停卡
    static final Integer CARD_STATUS_2 = 2; //冻结
    static final Integer CARD_STATUS_3 = 3; //已过期
    static final Integer CARD_STATUS_4 = 4; //未开卡
    static final Integer CARD_STATUS_5 = 5; //待补余
    static final Integer CARD_STATUS_6 = 6; //历史
//    子卡状态
    static final Integer CHILD_CARD_STATUS_0 = 0; //禁用
    static final Integer CHILD_CARD_STATUS_1 = 1; //正常
    static final Integer CHILD_CARD_STATUS_2 = 2; //退卡
//   pay_mode  支付状态 默认收入
    static final boolean PAY_MODE_TYPE_FALSE = false; //0：支出
    static final boolean PAY_MODE_TYPE_TRUE = true; //1：收入
//    会员卡订单类型
    static final Integer CARD_ORDRE_INFO_TYPE_1 = 1 ; //1：新购
    static final Integer CARD_ORDRE_INFO_TYPE_2 = 2 ; //2：续卡
    static final Integer CARD_ORDRE_INFO_TYPE_3 = 3 ; //2：转卡
    static final Integer CARD_ORDRE_INFO_TYPE_4 = 4 ; //2：卡升级
//  会员卡订单状态  1、待付款；2、已付款；
    static final Integer CARD_ORDRE_STATE_1 = 1 ; //1：待付款
    static final Integer CARD_ORDRE_STATE_2 = 2 ; //2：已付款
    static final Integer CARD_ORDRE_STATE_3 = 3 ; //3：已结款
    static final Integer CARD_ORDRE_STATE_4 = 4 ; //4：审核通过已结款
    static final Integer CARD_ORDRE_STATE_5 = 5 ; //5：复核通过已结款
//  订单明细 支付类型（1:权益 2:储值 3:其他渠道）
    static final Integer ORDER_DATAIL_TYPE_1 = 1;  //权益
    static final Integer ORDER_DATAIL_TYPE_2 = 2;  //储值
    static final Integer ORDER_DATAIL_TYPE_3 = 3;  //其他消费
    //停卡状态
    static final Integer STOP_STATUS_0 =0; //0: 正常
    static final Integer STOP_STATUS_1 =1; //1：停卡/冻结
    static final Integer STOP_STATUS_2 =2; //2：未审核过---终止/解冻
    static final Integer STOP_STATUS_3 =3; //3：审核通过---终止/解冻
    static final Integer STOP_STATUS_4 =4; //4：复核通过---终止/解冻
//  停卡表类型参数
    static final Integer STOP_TYPE_1 = 1; //1：停卡
    static final Integer STOP_TYPE_2 = 2; //2、冻结

//    子卡消费方式
    static final String  ORIGINALSET_ZKXFFS_O = "0";  //0：随主卡同步附属使用
    static final String  ORIGINALSET_ZKXFFS_1 = "1";  //1：子卡与主卡分割额度使用
    static final String  ORIGINALSET_ZKXFFS_2 = "2";  // 2：子卡与主卡享有同等权益
    //是否更换新卡
    static final Integer REPLACEMENT_CARD_0 = 0; // 0、否
    static final Integer REPLACEMENT_CARD_1 = 1; // 1、是
    //续卡开发方式
    static final Integer CARD_OPENING_0 = 0; //0，直接延续
    static final Integer CARD_OPENING_1 = 1; //1，另行开卡
}
