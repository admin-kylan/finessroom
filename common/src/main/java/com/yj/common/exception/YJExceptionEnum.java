package com.yj.common.exception;

/**
 * 〈一句话功能简述〉<br>
 * 〈异常枚举〉
 *
 * @author 欧俊俊
 * @create 2018/8/31
 */
public enum  YJExceptionEnum implements ServiceExceptionEnum  {
    /**
     * 其他
     */
    INVLIDE_DATE_STRING(400, "输入日期格式不对"),

    /**
     * 其他
     */
    WRITE_ERROR(500, "渲染界面错误"),

    /**
     * 文件上传
     */
    FILE_READING_ERROR(400, "FILE_READING_ERROR!"),
    FILE_NOT_FOUND(400, "文件未找到"),

    /**
     * 错误的请求
     */
    REQUEST_NULL(400, "请求有错误"),
    SERVER_ERROR(500, "服务器异常"),
    PARAM_ERROR(500, "请求参数异常"),
    CUSTOMERCODE_NOT_FOUND(500, "客户代码为空"),
    CLIENTID_NOT_FOUND(500,"客户ID不能为空"),
    PERSONNEL_ID_NOT_NULL(500, "员工ID不能为空"),
    PERSONNEL_ID_NOT_FOUND(500, "查无此工作ID"),
    FRCARD_NOT_EXISTED(500, "会员号信息有误"),
    PARENT_CARD_ID_EXISTED(500, "主卡会员卡信息获取有误"),
    PARENT_CARD_NUM_EXISTED(500, "主卡会员卡剩余权益不足，请重新设置"),
    OBJECT_NOT_FOUND(500, "对象未找到"),
    OBJECT_TYPE_STATUS(500, "已审核通过的数据，不能冲销"),
    ORDER_TYPE_STATUS(500, "冲销数据请勿重复操作"),
    STORAGE_PRICE_CARD_ID(500, "储值抵扣的会员卡信息未获取"),
    CLIENT_NUM_EXISTED(500, "此信息查询到多个客户，请重新获取设置"),
    FRCARD_STATUS_EXISTED(500, "冻结、过期、历史卡禁止任何操作"),
    CARD_TYPE_EXISTED(500, "此卡种信息已停止销售"),
    CARD_TYPE_TOTAL_EXISTED(500, "此卡种信息设置有误，请更换卡种"),
    NUMBER_ERROR(500, "开始数必须小于结束数"),
    OBJECT_EXISTED(500, "对象已经存在"),
    CARD_NUM_ID_EXISTED(500, "会员卡号不符合规则协议"),
    PROTOCOLNO_NOT_CONFORMITY(500, "此协议号不符合，不可用"),
    VIPCARDNO_NOT_CONFORMITY(500, "此会员号不符合，不可用"),
    PAY_TYPE_NOT_EXISTED(500, "支付类型未设置"),
    PAY_PRICE_EXISTED(500, "支付金额有误"),
    CARD_INFO_EXISTED(500, "会员卡订单信息获取有误"),
    BLACE_PRICE_EXISTED(500,"退款金额有变动，请重新核对后操作"),

    PROTOCOLNO_NOT_EXISTED(500, "协议号不存在"),
    PROTOCOLNO_ABOLISHED(500, "协议号已作废"),
    VIPCARDNO_NOT_EXISTED(500, "会员号不存在"),
    VIPCARDNO_ABOLISHED(500, "会员号已作废"),
    VIPCARDNO_ISNOTBLANK(500, "会员号不能为空"),
    KEYWORD_EXISTED(500, "关键字已存在"),
    KEYWORD_ISNOTBLANK(500, "关键字不能为空"),
    SELECTONE_ERROR(500, "查询单个对象出现错误"),
    NUMBER_EXISTED(500, "编号已存在"),
    INVAILD_SYMBOL(500,"包含非法字符");

    YJExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;

    private String message;

    @Override
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
