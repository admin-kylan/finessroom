/**
 * by Kylan
 * @author
 * @date 2019-01-15
 */
Vue.component('edu-pay-bill-children', {//模版挂载的标签名
    template: '#edu-pay-bill-id',//id对应子组件的ID
    data() {
        return {
            index: 0,
            toDisabled: false,
            isShowBill: false,
            eduItem: {},// 教学json
            clientInfo: {},//用户json
            param: {},
            createDate: timeFormatDate(new Date(), true),
            orderNo: '',
            invoiceStatus: '', //购置税发票
            totalPrice: 0, //存储的总额
            memberInfo: {
                isShow: false,
                num: '18650808999', //卡号
                ticketList: [//会员票据列表
                    {num: '1', name: '1', interest: '22', differ: 20, use: false},
                    {num: '2', name: '1', interest: '1', "differ": 20, use: false},
                    {num: '3', name: '1', interest: '43', "differ": 20, use: false},
                    {num: '4', name: '1', interest: '21', "differ": 20, use: false}
                ],
                optionClientList:[],
                optionClientCardList:[],
                isShowCardStatus: false,
                clientName: '',
                cardInfo: {
                    storageOrderPrice: 0, //票卷选中的会员卡的有效储值金额
                    cardNo: '未选择会员卡', //票卷选中的会员卡号  ------未选择会员卡
                    clientId: '', //会员用户Id
                    cardId: '', //票卷选中的会员卡号Id
                    cardUserName: '--',//票卷选中的会员卡客户名称
                    differencePrice: 0, //票卷差价
                    totalPrice: 0, //选中的票卷金额
                    selectTotalPrice: 0, //选中的金额
                    userPass: '', //输入用户明码
                    userPassStatus: false, //用户密码验证
                    inputMoney: '', //输入要抵扣的储存金额
                },
            },
            generalInfo:{
                num: '', //电话号码
                ticketList: [//普通票据列表
                    {num: '1', name: '1', interest: '22', "differ": 20, use: false},
                    {num: '2', name: '1', interest: '1', "differ": 20, use: false},
                    {num: '3', name: '1', interest: '43', "differ": 20, use: false},
                    {num: '4', name: '1', interest: '21', "differ": 20, use: false}
                ],
                totalPrice: 0,//选中的票卷金额
                selectTotalPrice: 0, //选中的金额
                differencePrice: 0, //票卷差价
                inputMoney: '', //输入要抵扣的储存金额
                isSuccess: false, //输入成功
            },
            payType: {// 支付方式
                p1: '',
                p2: '',
                p3: '',
                p4: '',
                p5: '',
                p6: '',
                p7: '',
                payStatus: '0',// 付款方式
            },
            rebate: {//优惠折扣
                currentTotalMoney: 0, //当前总金额
                offer: '', //优惠折扣
                integer: '', //整单去零
                type: '', //整单打折 重复单/次 1/0 重复/单次
                repeat: {//重复
                    full: '',//满
                    less: '',//减
                },
                single: {//单次
                    full: '',//满
                    less: '',//减
                }
            },
            payMoney: {
                totalPrice: 0,        //小计 = 实际应付金额 + 票卷差价（默认差价0）
                discount: 0,           //折扣优惠
                discountMoney: 0,       //抵扣金额
                price: 0,          //应付金额
                noPrice: 0,            //未付金额
                retChange: 0,          //找零
            },
        }
    },
    filters: {
        formatDate: function (time, type, typeT) {
            if (!time) {
                return '';
            }
            var _time = timeFormatDate(time, type, typeT);
            return _time;
        },
        toDateHHmm(val){
            let date = new Date(val);
            let h = date.getHours();
            h = h < 10 ? ('0' + h) : h;
            let m = date.getMinutes();
            m = m < 10 ? ('0' + m) : m;
            let _time = "";
            _time += ' '+h + ':' + m;
            return _time;
        },
        toDateyyyyMMddHHmm(val){
            if(!val){
                return "";
            }
            return timeFormatDate(val, true)
        },
        toDateyyyyMMdd(val){
            if(!val){
                return "";
            }
            return timeFormatDate(val)
        },
        toMoney(val){
            if (!val || 'undefined' == typeof(val)) {
                return '0.00';
            }
            return parseFloat(val).toFixed(2);
        },
        cardStatusFilter(val){
            //会员卡状态[0:正常，1:停卡，2:冻结，3:已过期，4:未开卡，5:待补余，6:历史]
            let str = "";
            if(val == 0){
                str = "正常"
            }
            if(val == 1){
                str = "停卡"
            }
            if(val == 2){
                str = "冻结"
            }
            if(val == 3){
                str = "已过期"
            }
            if(val == 4){
                str = "未开卡"
            }
            if(val == 5){
                str = "待补余"
            }
            if(val == 6){
                str = "历史"
            }
            return str;
        },
        reserveStatusFilter(val){
            let str = "";
            if(val == 0){
                str = "已取消"
            }
            if(val == 1){
                str = "已预约"
            }
            if(val == 2){
                str = "待确认"
            }
            return str;
        },
        remarksFilter(val){
            if(!val){
                return "无";
            }
            return val;
        },

    },
    computed: {},
    watch: {
        'index'(val){
            //如果导航栏改变，则触发选择的票券
            if (val === 0) {
                $.each(this.generalInfo.ticketList, (i, d) => {
                    d.use = false;
                });
            }
            if (val === 1) {
                $.each(this.memberInfo.ticketList, (i, d) => {
                    d.use = false;
                });
            }
        },
        'memberInfo.cardInfo.inputMoney'(val, oldVal){ //监控输入的储存金额

            if (parseFloat(val) > parseFloat(this.memberInfo.cardInfo.storageOrderPrice)) {
                this.memberInfo.cardInfo.inputMoney = oldVal;
                return false;
            }
            if(this.memberInfo.cardInfo.userPassStatus === true){
                //折扣减
                this.payMoney.discountMoney = parseFloat(val?val:0) + this.memberInfo.cardInfo.selectTotalPrice;
            }
        },
        'generalInfo.inputMoney'(val, oldVal){ //输入使用权益
            if (parseFloat(val) > parseFloat(this.generalInfo.totalPrice)) {
                this.generalInfo.inputMoney = oldVal;
                return false;
            }
            this.payMoney.discountMoney = this.generalInfo.totalPrice +
                parseFloat(val ? val : 0);
        },
        'memberInfo.ticketList': {//计算数组票据列表的变化
            handler: function (value, oldVal) {
                console.log(33)
                this.memberInfo.cardInfo.totalPrice = 0;
                this.memberInfo.cardInfo.differencePrice = 0;
                $.each(value, (i, d) => {
                    if (d.use === true) {
                        //
                        this.memberInfo.cardInfo.totalPrice += parseFloat(d.interest);
                        this.memberInfo.cardInfo.differencePrice += parseFloat(d.differ);
                    }
                });
                //差价添加进小计
                this.payMoney.totalPrice = this.totalPrice?this.totalPrice:0 + this.memberInfo.cardInfo.differencePrice;
                this.memberInfo.cardInfo.selectTotalPrice = this.memberInfo.cardInfo.totalPrice;
                if (this.memberInfo.cardInfo.userPassStatus == true && parseFloat(this.memberInfo.cardInfo.inputMoney) > 0) {
                    this.payMoney.discountMoney = parseFloat(this.memberInfo.cardInfo.inputMoney) + this.memberInfo.cardInfo.selectTotalPrice;
                } else {
                    this.payMoney.discountMoney = this.memberInfo.cardInfo.selectTotalPrice;
                }


            },
            deep: true,
        },
        'generalInfo.ticketList': {//计算数组票据列表的变化
            handler: function (value, oldVal) {
                this.generalInfo.totalPrice = 0;
                this.generalInfo.differencePrice = 0;
                $.each(value, (i, d) => {
                    if (d.use === true) {
                        //
                        this.generalInfo.totalPrice += parseFloat(d.interest);
                        this.generalInfo.differencePrice += parseFloat(d.differ);
                    }
                });

                //差价添加进小计
                this.payMoney.totalPrice = this.totalPrice?this.totalPrice:0 + this.generalInfo.differencePrice;
                this.generalInfo.selectTotalPrice = this.generalInfo.totalPrice;

                //再加上使用权益
                this.payMoney.discountMoney = this.generalInfo.totalPrice +
                    parseFloat(this.generalInfo.inputMoney ? this.generalInfo.inputMoney : 0);
            },
            deep: true,
        },
        'rebate': {
            handler(rebate){
                let currentTotalMoney = this.payMoney.totalPrice; //当前折扣后的钱
                let offerMoney = 0; //当前优惠的钱
                //先计算打折
                let offer = rebate.offer;
                let integer = rebate.integer;
                let full = 0, less = 0, type = rebate.type;
                //优惠折扣
                if (offer && offer !== '0') {
                    offerMoney = (currentTotalMoney * (1 - (parseFloat(offer) / 10))).toFixed(2);
                    currentTotalMoney = currentTotalMoney - offerMoney; //折扣后剩下
                }
                //整单去零
                if (integer && parseInt(integer) > 0) {
                    offerMoney = parseFloat(offerMoney) + parseInt(integer);
                    currentTotalMoney = currentTotalMoney - parseInt(integer);
                }
                if (type === "1") {//重复
                    this.rebate.single.full = '';
                    this.rebate.single.less = '';
                    full = this.rebate.repeat.full;
                    less = this.rebate.repeat.less;
                    full = full ? full : 0;
                    less = less ? less : 0;
                    full = parseFloat(full);
                    less = parseFloat(less);
                    if (full > 0 && full <= currentTotalMoney) {
                        let rate = parseInt(currentTotalMoney / full);
                        offerMoney = parseFloat(offerMoney) + parseFloat((rate * less).toFixed(2));
                    }
                }
                if (type === "0") {//单次
                    this.rebate.repeat.full = '';
                    this.rebate.repeat.less = '';
                    full = this.rebate.single.full;
                    less = this.rebate.single.less;
                    full = full ? full : 0;
                    less = less ? less : 0;
                    full = parseFloat(full);
                    less = parseFloat(less);
                    if (full > 0 && full <= currentTotalMoney) {
                        offerMoney = (parseFloat(offerMoney) + less).toFixed(2);
                    }
                }
                this.payMoney.discount = offerMoney;
            },
            deep: true,
        },
        'payMoney.totalPrice'(val){
            //计算应付金额
            let price = (parseFloat(val) -
                parseFloat(this.payMoney.discount) -
                parseFloat(this.payMoney.discountMoney)).toFixed(2);
            this.payMoney.price = price <= 0 ? 0 : price;
        },
        'payMoney.discount'(val){
            //计算应付金额
            let price = (parseFloat(this.payMoney.totalPrice) -
                parseFloat(val) -
                parseFloat(this.payMoney.discountMoney)).toFixed(2);
            this.payMoney.price = price <= 0 ? 0 : price;
        },
        'payMoney.discountMoney'(val){
            //计算应付金额
            let price = (parseFloat(this.payMoney.totalPrice) -
                parseFloat(this.payMoney.discount) -
                parseFloat(val)).toFixed(2);
            this.payMoney.price = price <= 0 ? 0 : price;
        },
        'payType': {
            handler: function (value, oldVal) {
                let p1 = value.p1 ? value.p1 : 0,
                    p2 = value.p2 ? value.p2 : 0,
                    p3 = value.p3 ? value.p3 : 0,
                    p4 = value.p4 ? value.p4 : 0,
                    p5 = value.p5 ? value.p5 : 0,
                    p6 = value.p6 ? value.p6 : 0,
                    p7 = value.p7 ? value.p7 : 0;
                let money = parseFloat(p1) +
                    parseFloat(p2) +
                    parseFloat(p3) +
                    parseFloat(p4) +
                    parseFloat(p5) +
                    parseFloat(p6) +
                    parseFloat(p7);
                let end = (parseFloat(this.payMoney.price) - money).toFixed(2);
                if (end < 0) {
                    this.payMoney.noPrice = 0;
                    this.payMoney.retChange = 0 - end;
                } else {
                    this.payMoney.noPrice = end;
                    this.payMoney.retChange = 0;
                }
                if (money > 0) { //有付款表明已经付款

                    this.payType.payStatus = '1';
                } else {
                    this.payType.payStatus = '0';
                }


            },
            deep: true,
        },
        'payMoney.price'(val){
            let price = parseFloat(val);
            let value = this.payType;
            let p1 = value.p1 ? value.p1 : 0,
                p2 = value.p2 ? value.p2 : 0,
                p3 = value.p3 ? value.p3 : 0,
                p4 = value.p4 ? value.p4 : 0,
                p5 = value.p5 ? value.p5 : 0,
                p6 = value.p6 ? value.p6 : 0,
                p7 = value.p7 ? value.p7 : 0;
            let money = parseFloat(p1) +
                parseFloat(p2) +
                parseFloat(p3) +
                parseFloat(p4) +
                parseFloat(p5) +
                parseFloat(p6) +
                parseFloat(p7);
            let end = (price - money).toFixed(2);
            if (end < 0) {
                this.payMoney.noPrice = 0;
                this.payMoney.retChange = 0 - end;
            } else {
                this.payMoney.noPrice = end;
                this.payMoney.retChange = 0;
            }
        },
    },
    created() {
        Event.$off(EDU_CONSTANT.listenerChooseClientInfo);
        Event.$on(EDU_CONSTANT.listenerChooseClientInfo, (status, clientInfo, eduItem)=>{
            this.isShowBill = status;
            this.clientInfo = clientInfo;
            this.eduItem = eduItem;
            this.payMoney.totalPrice = this.eduItem.courseMemberPrice ? this.eduItem.courseMemberPrice : 0;
            this.payMoney.oldTotalPrice = this.payMoney.totalPrice;
            // //当前总金额
            this.rebate.currentTotalMoney = this.payMoney.totalPrice;
            //courseMemberPrice
        })
      //  this.loadInit();
    },
    mounted() {

    },
    updated: function () {

    },
    methods: {
        loadInit(){
            //测试用的

            Event.$on("inputEduItem", (val)=>{
                this.eduItem = val;
                console.log(this.eduItem)
                let param = {
                    searchInput: '18650808999',
                    shopId: $.cookie('shopid'),
                    configStartClass: true,
                    code: $.cookie('code')
                };
                //查询卡号
                axiosGetParams(EDUCATION_URL.findMemberCardByInput, param, (res) => {
                    this.clientInfo = res[0];
                    console.log(this.clientInfo)
                    this.totalPrice = this.eduItem.courseMemberPrice;
                    //测试结束
                    this.payMoney.totalPrice = this.totalPrice ? this.totalPrice : 0;
                    //当前总金额
                    this.rebate.currentTotalMoney = this.payMoney.totalPrice;

                });
            })

        },
        //输入查询
        searchByTelAndNo(){

            //判断手机号码是否有输入
            if (!this.memberInfo.num) {
                return false;
            }
            //15345982222
            let param = {
                mobile: this.memberInfo.num,
                CustomerCode: $.cookie('code')
            };
            axiosGetParams(COURSE_URL.getClientListByMobile, param, (response) => {
                this.memberInfo.optionClientList = response;//导入值
                this.memberInfo.isShow = true; //显示弹出框
            });
        },
        //选择客户，查询会员卡
        catcherClientList(item){

            let param = {
                page: -1,
                rows: -1,
                clientId: item.id,
                CustomerCode: $.cookie('code'),
            };
            //设置会员卡名称
            this.memberInfo.cardInfo.cardUserName = item.clientName;
            jqueryPostParams(COURSE_URL.getCardListByMobile, param, (res) =>{
                this.memberInfo.optionClientCardList = res;
                //显示选择后的弹窗
                this.memberInfo.isShowCardStatus = true;
            });

        },
        //选择会员卡操作
        catcherCardId(item){

            this.memberInfo.cardInfo.cardId = item.id;
            this.memberInfo.cardInfo.cardNo = item.cardNo;
            this.memberInfo.cardInfo.clientId = item.clientId;
            this.memberInfo.cardInfo.storageOrderPrice = item.orderPrice ? item.orderPrice : 0;
            //隐藏
            this.memberInfo.isShowCardStatus = false;
            this.memberInfo.isShow = false; //显示弹出框
        },
        //验证用户密码
        verifyPassword(){
            if(!this.memberInfo.cardInfo.cardId){
                $.alert("请选择会员卡");
                return false;
            }
            this.memberInfo.cardInfo.userPassStatus = false;
            let param = {
                usePasswd: this.memberInfo.cardInfo.userPass,
                CustomerCode: $.cookie('code'),
                cardId: this.memberInfo.cardInfo.cardId,
            };

            ajaxPostJsonParams(COURSE_URL.verifyCard, JSON.stringify(param), (response) => {
                this.memberInfo.cardInfo.userPassStatus = true;

                if (this.memberInfo.cardInfo.userPassStatus == true && parseFloat(this.memberInfo.cardInfo.inputMoney) > 0) {
                    this.payMoney.discountMoney = parseFloat(this.memberInfo.cardInfo.inputMoney) + this.memberInfo.cardInfo.selectTotalPrice;
                } else {
                    this.payMoney.discountMoney = this.memberInfo.cardInfo.selectTotalPrice;
                }
                alert('验证成功');

            },()=>{
                $.alert('验证失败')
            })
        },
        payBill(){

            if(this.toDisabled !== false){
                return false;
            }
            //判断钱是否付款完成；
            if(this.payMoney.noPrice != 0){
                $.alert("钱未付清")
                return false;
            }
            this.toDisabled = true;
            let param = {};
            //--------------------
            let cardOrderPayModeList = []; //订单表
            for (let key in this.payType) {
                let cardOrderPayMode = {},
                    model = '',
                    price = 0,
                    type = '0';

                if (key === 'p1' && parseFloat(this.payType[key]) > 0) {
                    model = 1;
                    price = parseFloat(this.payType[key]).toFixed(2)
                }
                if (key === 'p2' && parseFloat(this.payType[key]) > 0) {
                    model = 2
                    price = parseFloat(this.payType[key]).toFixed(2)
                }
                if (key === 'p3' && parseFloat(this.payType[key]) > 0) {
                    model = 3;
                    price = parseFloat(this.payType[key]).toFixed(2)
                }
                if (key === 'p4' && parseFloat(this.payType[key]) > 0) {
                    model = 4;
                    price = parseFloat(this.payType[key]).toFixed(2)
                }
                if (key === 'p5' && parseFloat(this.payType[key]) > 0) {
                    model = 5;
                    price = parseFloat(this.payType[key]).toFixed(2)
                }
                if (key === 'p6' && parseFloat(this.payType[key]) > 0) {
                    model = 6;
                    price = parseFloat(this.payType[key]).toFixed(2)
                }
                if (parseFloat(price) > 0) {
                    cardOrderPayMode = {
                        orderId: '', //订单ID
                        orderType: 1, //订单类型（1、新购；2、续卡；3、转卡；4、补卡（暂无）；5、储值；6、补余；7、退卡, 8、停卡 ，9、转让）
                        payMode: model, // 支付方式（1、支付宝；2、刷卡；3、微信；4、现金；5、转账；6、花呗；7、其他）
                        payPrice: parseFloat(price), //支付金额
                        type: type, //支付状态（0：支出 1：收入）（备用）
                    }
                    cardOrderPayModeList.push(cardOrderPayMode);
                }
            }

            //会员卡订单交易明细
            let fr_card_order_datail_list = [];
            if (this.memberInfo.cardInfo.userPassStatus == true && parseFloat(this.memberInfo.cardInfo.inputMoney) > 0) {
                let memberChuzhi = {};
                let orderAmt = parseFloat(this.memberInfo.cardInfo.storageOrderPrice) - parseFloat(this.memberInfo.cardInfo.inputMoney);
                memberChuzhi.orderDetail = {
                    cardId: this.memberInfo.cardInfo.cardId, //会员卡ID
                    clientId: this.memberInfo.cardInfo.clientId, //客户ID
                    cardType: '1', //卡种类型（1：时间卡 2：小时卡 3：次卡 4：储值卡 5：充值卡 6：折扣卡）
                    orderStatus: '0', //支付状态（0：支出 1：收入）
                    orderPrice: -parseFloat(this.memberInfo.cardInfo.inputMoney),//金额（注意正负 支出就是  -0.00）
                    orderRightsNum: 0, //权益 注意正负
                    shopId: headVue.shopId, //基础表门店ID
                    personnelId: $.cookie('id'), //操作员工id
                    flag: '储值', //消费内容
                    type: 2, //支付类型（1:权益 2:储值 3:其他消费）
                    CustomerCode: $.cookie('code'),
                    status: 0,//财务审核 （0，待审核1，已审核，2，审核不通过）
                    auditStatus: 0,//相关复核 （0，待审核1，已审核，2，审核不通过）
                    orderType: 1,//订单类型（1、新购；2、续卡；3、转卡；4、补卡（暂无）；5、储值；6、补余；7、退卡, 8、停卡，9，转让，10，卡升级【若有其他的，类型数字请往后添加】）
                    orderAmt: orderAmt > 0 ? orderAmt:0, //剩余权益\剩余储值金额
                    createUserId: $.cookie('id'),
                    createUserName: $.cookie('name'),
                    createTime: new Date(),
                };
                memberChuzhi.orderdetailPrice = {
                    cardId: this.memberInfo.cardInfo.cardId, //会员卡ID
                    clientId: this.memberInfo.cardInfo.clientId, //客户ID
                    cardType: '1', //卡种类型（1：时间卡 2：小时卡 3：次卡 4：储值卡 5：充值卡 6：折扣卡）
                    orderStatus: '0', //支付状态（0：支出 1：收入）
                    orderPrice: parseFloat(this.memberInfo.cardInfo.inputMoney),//金额（注意正负 支出就是  -0.00）
                    orderRightsNum: 0, //权益 注意正负
                    shopId: headVue.shopId, //基础表门店ID
                    personnelId: $.cookie('id'), //操作员工id
                    flag: '储值', //消费内容
                    type: 2, //支付类型（1:权益 2:储值 3:其他消费）
                    CustomerCode: $.cookie('code'),
                    status: 0,//财务审核 （0，待审核1，已审核，2，审核不通过）
                    auditStatus: 0,//相关复核 （0，待审核1，已审核，2，审核不通过）
                    orderType: 1,//订单类型（1、新购；2、续卡；3、转卡；4、补卡（暂无）；5、储值；6、补余；7、退卡, 8、停卡，9，转让，10，卡升级【若有其他的，类型数字请往后添加】）
                    orderId: '', //订单ID
                    createUserId: $.cookie('id'),
                    createUserName: $.cookie('name'),
                    createTime: new Date(),
                };
                fr_card_order_datail_list.push(memberChuzhi)
            }
            //扣除权益 param
            let buyEduDetail = {
                cardId: this.clientInfo.cardId, //会员卡ID
                clientId: this.clientInfo.clientId, //客户ID
                cardType: '1', //卡种类型（1：时间卡 2：小时卡 3：次卡 4：储值卡 5：充值卡 6：折扣卡）
                orderStatus: '0', //支付状态（0：支出 1：收入）
                orderPrice: 0,//金额（注意正负 支出就是  -0.00）
                orderRightsNum: -this.eduItem.classSalesNum, //权益 注意正负
                shopId: this.eduItem.shopId, //基础表门店ID
                personnelId: $.cookie('id'), //操作员工id
                flag: '权益', //消费内容
                type: 1, //支付类型（1:权益 2:储值 3:其他消费）
                CustomerCode: $.cookie('code'),
                status: 0,//财务审核 （0，待审核1，已审核，2，审核不通过）
                auditStatus: 0,//相关复核 （0，待审核1，已审核，2，审核不通过）
                orderType: 1,//订单类型（1、新购；2、续卡；3、转卡；4、补卡（暂无）；5、储值；6、补余；7、退卡, 8、停卡，9，转让，10，卡升级【若有其他的，类型数字请往后添加】）
                orderAmt: this.eduItem.amtNum, //剩余权益\剩余储值金额
                createUserId: $.cookie('id'),
                createUserName: $.cookie('name'),
                createTime: new Date(),
            }


            param.cardOrderPayModeDate = cardOrderPayModeList;
            param.cardOrderDetailList = fr_card_order_datail_list;
            // param.eduItem = this.eduItem;
            // param.clientInfo = this.clientInfo;
            param.buyEduDetail = buyEduDetail;
            this.isShowBill = false;
            //付款的反馈
            Event.$emit(EDU_CONSTANT.listenerChooseClientInfoBack, param)
            this.toDisabled = false;
        }
    },
});