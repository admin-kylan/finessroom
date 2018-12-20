
// 票券抵扣弹窗
function btn_de() {
    $('.btn_de').show();
}
//年卡详情查看
function carddetails(id) {

}
function  newCustomers_maskLayer_X(){
    $('.newCustomers_maskLayer').hide();
}
//关闭小弹窗
function settingOut_a() {
    $('.maskLayerAlert_box_alert').hide();
}
//关闭小弹窗
function settingOut_a2() {
    $('.maskLayerAlert_box_alert2').hide();
}

// 转卡左侧树状图事件
$('body').on('click', '.NavTree-first li strong', function () {
    if ($(this).parent('li').hasClass('active')) {
        $(this).parent('li').removeClass('active');
    } else {
        $(this).parent('li').addClass('active');
    }
    if ($(this).parent('li').parent('ul').hasClass('NavTree-third')) {
        $('.NavTree-third li').removeClass('active');
        $(this).parent('li').addClass('active');
    }
})

var customerMembershipCard = new Vue({
    el: '#customerMembershipCard',
    data: {
//      共用参数---start
        LiIndex: 0,
        clientId:'',           //会员ID
        code:'',               //客户代码
        cardId:'',             //所选择的会员卡卡种ID
        shopId:'',             //选中的会员卡所在店铺ID
        cardNo:'',             //选中的会员卡号
        cardNumId:'',         //生成会员卡号的规则ID
        status:'',             //选中的会员状态
        catcherClientId:'',   //承接的客户Id
        catcherClientCardId:'',   //承接的客户会员卡ID
        givePhone:'',          //承接客户的手机
        giveName:'',           //承接客户的姓名
        optionClientList:[],  //获取选择的会员信息
        optionClientCardList:[],  //获取选择的会员信息
        clientUserName: window.parent.document.getElementById("clientUserName").innerText,    //客户姓名
        clientPhone: window.parent.document.getElementById("clientPhone").innerText, //客户手机
        clientSet: window.parent.document.getElementById("clientSet").innerText,   //客户性别
        personnelName:$.cookie("name"),  //系统当前操作人信息
        randomNumber:'',      //随机生成，防止点击过快
        paginationId:'pagination0',      //分页标签ID
        paymentMethodTops:0,             //切换支付方式
        transferCardBoxTop: 0,           //停卡、冻结切换
        creatTime:getNowTime(true),  //创建时间
        methName:'',                  //验证完之后要执行的方法名称
        methPass:'',                  //要验证的密码的标签id
        perId:'',                     //要验证的员工ID  不填写，默认是cookie获取
//      共用参数---end
//      小部分共用参数--start
        PaginationName:'',     //存放调用分页的方法名称
        optionCardLis:[],      //选择会员卡列表
        maeketUserList:[],     //销售人员列表
        payModelParemt:'',       //存放金额
        payModelMoney:0,        //支付总金额
        payMentMoney:{           //小计金额
            price:0,              //实际应付金额
            totalPrice:0,        //小计 = 实际应付金额 + 票卷差价（默认差价0）
            discountNum:0,       //抵扣金额
            discount:0,           //优惠折扣
            needPrice:0,          //计算后的应付金额
            noPrice:0,            //未付金额
            retChange:0,          //找零
        },
        payModel:{              //支付方式
            L1:parseFloat(0).toFixed(2),   //支付宝
            L2:parseFloat(0).toFixed(2),   //刷卡
            L3:parseFloat(0).toFixed(2),   //微信
            L4:parseFloat(0).toFixed(2),   //现金
            L5:parseFloat(0).toFixed(2),   //转账
            L6:parseFloat(0).toFixed(2),   //花呗
            L7:parseFloat(0).toFixed(2),   //其他
        },
        payModelCount:parseFloat(0).toFixed(0),  //统计支付金额共用
        ticketPrice:{
            ticketPriceList:[           //选中的会员卡票卷信息    -----------先写死后期动态
                {"tickNo":201807246565,"tickName":"抵用券","tickNum":10,"differ":5},
                {"tickNo":201807246158,"tickName":"充值券","tickNum":100,"differ":10},
                {"tickNo":201807246158,"tickName":"充值券","tickNum":50,"differ":15},
                {"tickNo":201807246158,"tickName":"充值券","tickNum":100,"differ":20},
                {"tickNo":201807246158,"tickName":"抵用券","tickNum":50,"differ":15},
                {"tickNo":201807246158,"tickName":"抵用券","tickNum":100,"differ":20},
                {"tickNo":201807246158,"tickName":"抵用券","tickNum":50,"differ":15},
                {"tickNo":201807246158,"tickName":"抵用券","tickNum":100,"differ":20},
            ],
            tickMark:'',                 //票劵标记
            ticketPriceListOk:[],        //选中的要使用的票卷信息
            storage0rderPrice:0,         //票卷选中的会员卡的有效储值金额
            cardNo:'未选择会员卡',       //票卷选中的会员卡号  ------未选择会员卡
            cardId:'',                    //票卷选中的会员卡号Id
            cardUserName:'--',           //票卷选中的会员卡客户名称
            differencePrice:0,           //票卷差价
            totalPrice:0,                 //选中的票卷金额
            storagePirc:0,                //要抵扣的储值卡金额
            userPass:'',                  //票卷客户授权凭证
        },       //票卷部分
        discount:{                                 //折扣优惠
            num:0,                   //打几折
            changeNum:0,            //整单去零
            fullType:0,             //满减优惠类型（0、一次；1、可重复）
            // discountFull:0,        //满
            // discountReduce:0,      //减
            discountPrice:0,       //总折扣/优惠金额
            discountFullOne:0,     //重复满
            discountReduceOne:0,   //重复减
            discountFullTwo:0,     //单次满
            discountReduceTwo:0,   //单次减
        },
//      小部分共用参数--end
//      后期可动态从字典表获取赋值参数--start
        parameterList:{
            payModeList:[               //支付方式参数
                {"vue":1,"lav":"支付宝"},
                {"vue":2,"lav":"刷卡"},
                {"vue":3,"lav":"微信"},
                {"vue":4,"lav":"现金"},
                {"vue":5,"lav":"转账"},
                {"vue":6,"lav":"花呗"},
                {"vue":7,"lav":"其他"}
            ],
            status:[               //卡状态
                {"vue":0,"lav":"正常"},
                {"vue":1,"lav":"停卡"},
                {"vue":2,"lav":"冻结"},
                {"vue":3,"lav":"已过期"},
                {"vue":4,"lav":"未开卡"},
                {"vue":5,"lav":"待补余"},
                {"vue":6,"lav":"历史"}
            ],
            type:[               //卡类型
                {"vue":1,"lav":"时间卡"},
                {"vue":2,"lav":"小时卡"},
                {"vue":3,"lav":"次卡"},
                {"vue":4,"lav":"储值卡"},
                {"vue":5,"lav":"充值卡"},
                {"vue":6,"lav":"折扣卡"},
            ],
            payType:[          //付款方式
                {"vue":1,"lav":"全款"},
                {"vue":2,"lav":"定金"},
                {"vue":3,"lav":"赠送"},
                {"vue":4,"lav":"置换"},
                {"vue":5,"lav":"分期付款"},
            ],
            consumeType:[        //消费方式
                {"vue":1,"lav":"会员消费"},
                {"vue":2,"lav":"普通消费"},
                {"vue":3,"lav":"员工领用"},
                {"vue":4,"lav":"协议单位"},
            ],
            orderState:[       //订单状态
                {"vue":1,"lav":"代付款"},
                {"vue":2,"lav":"已付款"},
            ],
            fullType:[        //满减优惠类型
                {"vue":0,"lav":"一次"},
                {"vue":1,"lav":"可重复"},
            ],
            auditStatus:[     //审核状态
                {"vue":0,"lav":"待审核"},
                {"vue":1,"lav":"已审核"},
                {"vue":2,"lav":"审核不通过"},
            ],
            saleAllotType:[          //销售业绩分配类型
                {"vue":0,"lav":"无业绩分配"},
                {"vue":1,"lav":"按比例分配"},
                {"vue":2,"lav":"按金额分配"},
            ],
            zkCyfs:[           //持有方式参数
                {"vue":0,"lav":"不限人员使用"},
                {"vue":1,"lav":"指定人员持有"}],
            zkXffs:[           //消费方式参数
                {"vue":0,"lav":"随主卡同步附属使用"},
                {"vue":1,"lav":"子卡与主卡分割额度使用"},
                {"vue":2,"lav":"子卡与主卡享有同等权益"}],
            cardHandle:[  // 卡处理参数
                {"vue":0,"lav":"子卡存在"},
                {"vue":1,"lav":"独立分享"}
            ],
            shareBenefit:[  // 分享权益参数
                {"vue":0,"lav":"主卡共享"},
                {"vue":1,"lav":"随主卡"}
            ],
            continueOrderStat:[  //续卡操作状态
                {"vue":0,"lav":"待付款"},
                {"vue":1,"lav":"冲销"},
                {"vue":2,"lav":"已冲销"}
            ],
            splitType:[
                {"vue":1,"lav":"比例"},
                {"vue":2,"lav":"金额"},
            ],
            saleStatus:[  //人员状态
                {"vue":0,"lav":"正常"},
                {"vue":1,"lav":"离职"},
            ],
            allotSetType:[  //人员状态
                {"vue":0,"lav":"否"},
                {"vue":1,"lav":"是"},
            ],
            stopStatus:[  //停卡状态
                {"vue":0,"lav":"正常"},
                {"vue":1,"lav":"停卡"},
                {"vue":2,"lav":"停卡终止"},
                {"vue":3,"lav":"停卡终止"},
                {"vue":4,"lav":"停卡终止"},
            ],
            stopStatusT:[  //停卡状态
                {"vue":1,"lav":"冻结"},
                {"vue":2,"lav":"已解冻"},
                {"vue":3,"lav":"已解冻"},
                {"vue":4,"lav":"已解冻"},
            ],
            storageStatus:[  // 储值状态
                {"vue":1,"lav":"已储值","cla":"toblue"},
                {"vue":2,"lav":"已冲销","cla":"togreen"},
                {"vue":3,"lav":"已退款","cla":"toyin"},
                {"vue":4,"lav":"已转让","cla":"toyin"},
                {"vue":5,"lav":"已退款","cla":"toyin"},
                {"vue":6,"lav":"已退款","cla":"toyin"},
                {"vue":7,"lav":"已转让","cla":"toyin"},
                {"vue":8,"lav":"已转让","cla":"toyin"},
            ],
            sexList:[
                {"vue":0,"lav":"男"},
                {"vue":1,"lav":"女"},
            ],
        },
//      后期可动态从字典表获取赋值参数--end
        loadData0:{
            cardList:[],            //会员卡列表
            count:'',        //会员卡数量
            cardFlag:'',     //卡系列
            cardTypeName:'', //卡系列名称
            status:'',        //卡状态
            agreementNo:'',   //协议编号
            b_needPrice:'',   //购卡金额
            balance:0,         //剩余金额（动态计算）
            storageValue:0,   //储值金额（等储值表出来后关联）
            b_buyRightsNum:0, //购买卡权益
            b_giveRightsNum:0,//赠送卡权益
            availableNum:0,   //可用权益
            alreadyUsedNum:0, //已使用权益=购买+赠送-剩余权益
            haveRightsNum:0,  //剩余权益
            bindTime:'',        //开卡日期
            invalidTime:'',     //失效日期
            flag:'',             //失效原因
            b_status:'',        //财务审核
            b_auditStatus:'',  //相关复核
            limitList:[],       //使用限定列表
            userdNum:1,         //使用本卡限定人数
            userName:'',        //使用者姓名
            userPhone:'',       //使用者联系方式
            userPasswd:'',      //使用密码
            note:'',             //备注
        },
        loadData1:{
            shopList:[],//销售门店列表
            cardsList:[],//指定门店下的卡种列表
            orderList:[],//新购订单列表
            qtZdqy:true,//判断赠送权益是否合法
            minPrice:false, //判断购卡金额是否合法
            addQtZdqy:0, //最多赠送权益
            addCardData:{
                minPrice:0,         //前台可修改最低销售价格
                qtZdqy:0,           //判断赠送权益是否合法
            },
            cardInfoMap:{  //新购的会员卡订单信息
                totalPrice:0,       //购卡金额
                salesPrice:0,       //售价
                shopId:'',          //销售门店
                personnelId:'',     //员工Id
                buyRightsNum:0,     //购买权益
                giveRightsNum:0,    //赠送权益
                isFlage:'', //是否直接开卡 0，否，1是
                agreementId:'',//编号协议ID
                agreementNo:'',  //协议编号
                cardTypeId:'',     //卡类型ID
                serviceLife:'',      //时限
                cardFlag:'',          //卡系列
                cardTypeName:'',     //卡名称
                type:'',            //卡类型
                haveRightsNum:0,  //拥有的总权益
                bindTime:'',       //开卡日期
                externalNo:'',     //外部卡号
                cardNumId:'',      //会员卡号规则ID
                cardNo:'',         //会员卡号
                note:'',            //备注
                allotSetType:0,     //业绩分配设置
                orderAllotSetList:[],  //业绩分配正式
                orderAllotSetSav:{},  //业绩分配
            },
            orderAllotSetListOne:[],  //业绩分配添加的时候
            orderAllotSetList:[],  //业绩分配正式
            allotNumOne:0,
            allotNumTwo:0,
            orderAllotSetSav:{  //业绩分配
                allotType:'',  //业绩分配类型（1、分配百分百；2、分配金额）
                allotNum:0,   //分配数额（百分百或者实际金额，根据分配类型判断）
                saleAllotType:0, //销售业绩分配类型（0、无业绩分配；1、按比例分配；2、按金额分配）
            },
            orderAllotSet:{  //业绩分配
                allotType:'',  //业绩分配类型（1、分配百分百；2、分配金额）
                allotNum:0,   //分配数额（百分百或者实际金额，根据分配类型判断）
                saleAllotType:0, //销售业绩分配类型（0、无业绩分配；1、按比例分配；2、按金额分配）
                personnelId:'',  //业绩分配员工
                saleAllotNum:'',
            },
        },
        loadData2:{
            continuedCardList:[],  //续卡列表
            cardOpening:0,          //开卡方式（0，直接延续 ； 1，另行开卡）
            replacementCard:0,      //是否更换新卡(0、否；1、是)
        },
        loadData3:{
            context:'选择要转卡的类型卡',
            shopId:'',//要转卡的门店ID
            Lindex:0, //切换转卡，卡升级
            shopList:[],
            cardFlag:'',//卡系列
            cardName:'', //卡名称
            originalPrice:0, //原价
            salesPrice:0, //售价
            totalNum:0, //购买权益
            type:0,     //卡类型
            supplyList:[],   //补卡或者转卡信息
            transferFee:parseFloat(0).toFixed(2), //手续费用于切话备用
            cardTypeId:'', //要转的类型卡的ID
            cardSupply3:{
                cardNo:'',//卡号
                transferFee:parseFloat(0).toFixed(2), //手续费
                personnelId:'',//授权员工
                price:0,    //存放计算后的剩余金额
            },
            canChangeType:'',  //能否转卡
        },
        loadData4:{
            imgNum:1,    //第1张图片
            imagesList:{},
            imageFiles:[],
            status:'',    //会员卡状态
            stopNum:0,  //可操作停卡次数
            stopDays:0, //每次最多停几天
            totalDays:0, //停卡总天数
            outPrice:0,  //超出时间每天费用
            realStopNum:0,//已使用次数
            realTotalNum:0,//已经停卡天数（根据实际的）
            stopType1:[], //停卡列表
            stopType2:[], //冻结列表
            personnelName:$.cookie("name"), //操作人员
            estInvalidTime:'',//预计失效时间
            terminationId:'', // 要终止停卡的数据Id
            addStroData:{
                startTime:'', //开始停卡时间
                estEndTime:'', //预计停止结束时间
                estStopTime:'', //预计停卡时长
                personnelId:'', //操作人员
                invalidTime:'', //新失效时间
                flag:'',         //原因
                totalPrice:0,    //收费
            },
            termination:{
                stopTime:0, //实际停卡时长
                endTime:'',  //实际停卡时间，
                invalidTime:'', //新失效时间
                flag:'',         //原因
                totalPrice:0,    //收费
                personnelId:$.cookie("id"), //操作人员
            }
        },
        loadData5:{
            carDsupplyRecordList:[],//补卡记录列表
            SupplyMoney:0, //补卡历史金额
            addSupply1:{
                externalNo:'',//外部卡号
                supplyRemarks:'',//备注
                oriCardId:'',   //会员卡Id
                oriCardNo:'',   // 原会员卡号
                newCardNo:'',   //新会员卡号
                payPrice:parseFloat(0).toFixed(),
                personnelId:'',  //销售人员
            }
        },
        loadData6:{
            storageCardList:[], //获取储值列表
            orderPrice:0,   //当前储值金额
            givePrice:0,  //赠送金额
            personnelName:$.cookie("name"),//操作人
            refundStorageId:'',//要退款的订单id 用于切换
            transfersStorageId:'',//要转让的订单id
            clientUserName:'',//提款人姓名
            transfersMoney:0, //转让的总储值金额
            addRefund:{  //退款信息
                totalPrice:0, //需退款金额
                refundNote:'',//退款原因
                refundUserId:'',//退款授权人Id
            },
            transfersStorage:{  //转让储值金额
                giveName:'',//转让人姓名
                givePhone:'',//转让人手机
                giveUserId:'',//转让人客户id
                giveCardId:'',//转让人的会员卡ID
            },
        },
        loadData7:{
            zkNum:0,     //可添加子卡数
            zkCyfs:0, //持有方式
            zkXffs:0, //消费方式
            alreadyNum:0, //已经存在卡数
            childCardList:[],//子卡列表
            cardNo:'',     //子卡的会员卡号
            cardHandle:0,  //卡处理
            shareBenefit:0,//分享权益
            outCardNo:'',  //外部卡号
            userName:'',   //持卡人姓名
            mobile:'',      //持卡人手机
            saleManId:'',   //销售人员
            parentCardId:'',//选中的会员卡号
            shareNum:0,  //分享的权益
        },
        loadData8:{
            complementCardList:[],//补余列表
            complementPrice:0,   //已经付款金额
            noPrice:0,            //欠款金额
            orderSplitList:[] ,  //还款选择
            num:0,                 //共几期
            depositTime:0,        //补余期限，
            nextTime:'',          //下次补余期限
            depositMon:0,         //补余金额，
            orderSetIdList:[],    //分期补余存放ID
            orderSetIdListTwo:[], // 存放分期数据
            addComplement:{
                splitSetId:'',  //要补余的新购订单ID
                totalPrice:0,   //卡销售金额
                shopId:'',      //业绩归属门店
                payRemarks:'',  //支付备注
                type:0,          // 类型(1、定金补余；2、分期补余)
                personnelId:'', //销售人员ID
                allotSetType:0, //是否设置业绩分配
            },
        },
        loadData9:{
            transferCardList:[], //获取转让列表
            totalPrice:0, //转让总手续费
            transferFee:0, //此类型卡的转卡手续费
            createdName:$.cookie('name'), //操作人员
            addTranferCar:{
                totalPrice:0, //转让费
                personnelId:'',// 授权人
                flag:'',//备注
            },
        },
        loadData10:{
            SupplyMoney:parseFloat(0).toFixed(2), //退卡手续费
            blacekCardList:[], //退卡列表
            saleStatus:'',  //销售状态
            saleName:'',   //销售人员姓名
            createdName:$.cookie('name'), //操作人员
            addBlackCard:{
                cardId:'',//会员卡id
                clientId:'', //会员Id
                needPrice:parseFloat(0).toFixed(2), //退卡金额
                procedPrcie:parseFloat(0).toFixed(2), // 退卡手续费
                totalPrice:parseFloat(0).toFixed(2),  //退款总金额
                personnelId:'',//销售人员
                allotSetType:0,//是否扣除当月业绩
                cardFlag:'',//卡系列
                cardTypeName:'', //卡名称
                accountNumber:'',//退款账号
                refundType:'', //退款类型
            },
        },
    },
    filters: {
        formatDate: function (time,type,typeT) {
            if(!time){
                return '';
            }
            var _time = timeFormatDate(time,type,typeT);
            return _time;
        },
        toManey:function (val) {
            if(!val || 'undefined'== typeof(val)){
                return parseFloat(0).toFixed(2);;
            }
            return parseFloat(val).toFixed(2);
        },
        toMoneySet:function (val,ss,obj) {
            var that =this;
            var splitNum = val;
            if(obj){
                var splitType = obj.splitType;
                // 分期类型（1、比例；2、金额）
                if(splitType == 2){
                    return parseFloat(val).toFixed(2);
                }
                if(splitType == 1){
                    var splitPrice = obj.splitPrice;
                    if(splitPrice && splitNum){
                        var numMoney = (parseFloat(splitNum)/100)*splitPrice;
                        return parseFloat(numMoney).toFixed(2);
                    }
                }
            }
            return val;
        },
        toType:function(val){
            var str = '';
            if(val = 1){str = '时间卡';}
            if(val = 2){str = '小时卡';}
            if(val = 3){str = '次卡';}
            if(val = 4){str = '储值卡';}
            if(val = 5){str = '充值卡';}
            if(val = 6){str = '折扣卡';}
            return str;
        }
    },
    created:function () {
        var that = this;
        var id = document.URL.split("=")[1];
        if(!id){
            id = $.cookie("cid");
        }
        that.getCardUserList(1,10,id);
        that.clientId = id;
    },
    methods: {
        // 初始函数
        init: function (t) {
        },
        //会员卡子页面切换
        LI: function (t) {
            var that = this;
            if (!that.code) {
                that.code = $.cookie("code");
            }
            //切换页面初始化部分共用参数
            that.cardId = '';
            that.shopId = '';
            that.payModelParemt = '';
            that.payModelMoney = 0;
            that.catcherClientId = '';
            that.catcherClientCardId = '';
            that.givePhone = '';
            that.giveName = '';
            that.optionClientList = [];
            that.optionClientCardList = [];
            that.methName = '';
            that.methPass = '';
            that.LiIndex = t;
            //初始化样式显示
            $('.MembershipCard-list li').eq(t).addClass('active').siblings().removeClass('active');
            $('#MembershipCardDetails').hide();
            $('#MembershipCardlist').show();
            var paginationId = that.paginationId;
            $("#" + paginationId).html('');
            that.paginationId = 'pagination' + that.LiIndex;
            //动态跳转对应的方法 Load方法
            var methodsName = 'Load' + that.LiIndex;
            //初始化支付参数
            that.initPayModel();
            that[methodsName]();
        },
        //会员卡列表
        Load0: function () {
            //获取会员卡信息列表----根据clientId
            var that = this;
            that.getCardUserList(1, 10, that.clientId);
        },
        //新购
        Load1: function () {
            var that = this;
            // 初始化: 初始化绑定时间
            setTimeout(function () {
                jeDate('.jeinput', {
                    format: "YYYY-MM-DD hh:mm:ss",
                    minDate: getNowTime(),
                    donefun: function (obj) {
                       that.loadData1.cardInfoMap.bindTime = obj.val;
                    }
                });
            }, 50)
            $('#NewPurchase').show().siblings().hide();
            that.initLoad1();
            that.getMarketShopList();
            that.getCardOrederList(1, 10, that.clientId);
        },
        //续卡
        Load2: function () {
            var that = this;
            that.initGetOptionsCard();
        },
        // 转卡
        Load3: function () {
            var that = this;
            that.loadData3.Lindex = 0;
            that.loadData3.type = 0 ;
            that.getCardSupplyList(that.loadData3.Lindex);
            that.initGetOptionsCard();
            that.getMarketShopList();
            that.getMarketShopPeople($.cookie("shopid"),that);
        },
        // 停卡/冻结
        Load4: function () {
            var that = this;
            that.initGetOptionsCard();
        },
        // 补卡
        Load5: function () {
            var that = this;
            that.initGetOptionsCard();
            that.toCarDsupplyRecordList();
        },
        // 储值
        Load6: function () {
            var that = this;
            that.initGetOptionsCard();
        },
        // 子卡
        Load7: function () {
            var that = this;
            that.initGetOptionsCard();
        },
        // 补余
        Load8: function () {
            var that = this;
            that.initAllotSet();
            if (that.loadData1.shopList == null || that.loadData1.shopList.length <= 0) {
                that.getMarketShopList();
            }
            that.initGetOptionsCard();
        },
        // 转让
        Load9: function () {
            var that = this;
            that.initGetOptionsCard();
            that.getMarketShopPeople($.cookie("shopid"), that);
        },
        // 退卡
        Load10: function () {
            var that = this;
            that.initGetOptionsCard();
            that.getBlackCardList();
        },
// ======================================
        //初始化选择会员卡
        initGetOptionsCard: function () {
            var that = this;
            if (that.optionCardLis != null && that.optionCardLis.length <= 0) {
                    that.getOptionsCardList();
            }
        },
        //初始化支付金额---小计金额---票劵
        initPayModel: function () {
            var that = this;
            that.payModel.L1 = parseFloat(0).toFixed(2);
            that.payModel.L2 = parseFloat(0).toFixed(2);
            that.payModel.L3 = parseFloat(0).toFixed(2);
            that.payModel.L4 = parseFloat(0).toFixed(2);
            that.payModel.L5 = parseFloat(0).toFixed(2);
            that.payModel.L6 = parseFloat(0).toFixed(2);
            that.payModel.L7 = parseFloat(0).toFixed(2);
            that.payMentMoney.price = 0;
            that.payMentMoney.totalPrice = 0;
            that.payMentMoney.discountNum = 0;
            that.payMentMoney.needPrice = 0;
            that.payMentMoney.noPrice = 0;
            that.payMentMoney.discount = 0;
            that.payMentMoney.retChange = 0;
            that.payModelMoney = 0;
            that.initTicketPrice();
            that.initDiscountPrice();
        },
        //初始化优惠折扣部分
        initDiscountPrice:function(){
            var that = this;
            that.discount.num = 0;
            that.discount.changeNum = 0;
            that.discount.fullType = 0;
            // that.discount.discountFull = 0;
            // that.discount.discountReduce = 0;
            that.discount.discountPrice = 0;
            that.discount.discountFullOne = 0;
            that.discount.discountReduceOne = 0;
            that.discount.discountFullTwo = 0;
            that.discount.discountReduceTwo = 0;
        },
        //初始化票劵信息
        initTicketPrice: function () {
            var that = this;
            // that.ticketPrice.ticketPriceList = [];
            that.ticketPrice.ticketPriceListOk = [];
            that.ticketPrice.storage0rderPrice = 0;
            that.ticketPrice.cardNo = '未选择会员卡';
            that.ticketPrice.cardId = '';
            that.ticketPrice.cardUserName = '--';
            that.ticketPrice.differencePrice = 0;
            that.ticketPrice.totalPrice = 0;
            that.ticketPrice.storagePirc = 0;
            that.ticketPrice.userPass = '';
            that.initGiveDatail();
        },
        //初始化承接人信息
        initGiveDatail:function(){
            var that = this;
            that.catcherClientId = '';
            that.catcherClientCardId = '';   //承接的客户会员卡ID
            that.givePhone = '';         //承接客户的手机
            that.giveName = '';           //承接客户的姓名
            that.optionClientList = [];  //获取选择的会员信息
            that.optionClientCardList = []; //获取选择的会员信息
        },
        //优惠折扣检索
        checkDiscounts: function (t, str) {
            var that = this;
            var reg = /^[0-9]*$/;
            var regt = /^[0-9]+\.?[0-9]*?$/;
            var num = that.discount[str];
            if (1 == t) {
                if (num < 1 || num >= 10) {
                    num = 0;
                    $.alert("请输入打几折:1-9");
                }
                if (!reg.test(num)) {
                    num = 0 ;
                    $.alert("请输入正确的格式：1-9 数字");
                }
            }
            if (2 == t) {
                if (!regt.test(num)) {
                    num = 0;
                }
            }
            that.discount[str] = parseFloat(num);
            that.chageDiscount();
        },
        //重复满减切换
        switchType:function(){
            var that = this;
            that.discount.discountFullOne = 0;
            that.discount.discountReduceOne = 0;
            that.discount.discountFullTwo = 0;
            that.discount.discountReduceTwo = 0;
            that.chageDiscount();
        },
        switchFullReduction: function (val, str,valT) {
            var that = this;
            var regt = /^[0-9]+\.?[0-9]*?$/;
            var num = val;
            if (!regt.test(num)) {
                num = parseFloat(0).toFixed(2);
            }
            var objStr = parseFloat(num).toFixed(2);
            var fullType = that.discount.fullType;
            if(valT != fullType){
                that.discount.fullType = valT;
                if(valT == 1){
                    that.discount.discountFullTwo = 0;
                    that.discount.discountReduceTwo = 0;
                }
                if(valT == 0){
                    that.discount.discountFullOne = 0;
                    that.discount.discountReduceOne = 0;
                }
            }
            that.discount[str] = objStr;
            that.chageDiscount();
        },
        //计算折扣优惠
        chageDiscount:function(){
            var that = this;
            that.payMentMoney.discount = 0;
            //金额
            var price = that.payMentMoney.price;
            //应付金额大于0 的基础上在开始计算
            if(price > 0){
                //打几折
                var num = 0;
                if(that.discount.num){
                    var disNum = that.getParemtDate(that.discount.num,0);
                    num =(1-(disNum/10)) * price;
                }
                //整单去零
                var changeN = parseFloat(that.getParemtDate(that.discount.changeNum,0));
                //满减类型
                var t = that.discount.fullType;
                // 满减折扣
                var fullMoney = 0;
                if (t == 1) {  //重复
                    //满
                    var Zmoney = that.discount.discountFullOne;
                    //减
                    var Zdismoney = that.discount.discountReduceOne;
                    if (Zmoney && Zdismoney && price >= Zmoney) {
                        var numT = parseInt(price / Zmoney);
                        if (numT > 0 ) {
                            fullMoney = numT * Zdismoney;
                        }
                    }
                }
                if (t == 0) {  //单次
                    //满
                    var Omoney = that.discount.discountFullTwo;
                    // 减
                    var Odismoney = that.discount.discountReduceTwo;
                    if (Omoney && Odismoney) {
                        if (price >= Omoney) {
                            fullMoney = Odismoney;
                        }
                    }
                }
                that.payMentMoney.discount = parseFloat(num)+ parseFloat(changeN)+parseFloat(fullMoney);
            }
            that.isCommonMoney();
        },
        // 计算票卷抵扣
        ticketDeductiont: function () {
            var that = this;
            var allTotal = 0;
            var diff = 0;
            var len = that.ticketPrice.ticketPriceListOk;
            if(len.length > 0 ){
                for(var i = 0 ; i<len.length ;i++){
                    allTotal = parseFloat(allTotal) + parseFloat(that.getParemtDate(len[i].tickNum,0));
                    diff =  parseFloat(diff) + parseFloat(that.getParemtDate(len[i].differ,0));
                }
            }
            that.ticketPrice.totalPrice = allTotal;
            that.ticketPrice.differencePrice = diff;

            var storage0rderPrice = that.getParemtDate(that.ticketPrice.storage0rderPrice,0);
            var storagePirc = that.getParemtDate(that.ticketPrice.storagePirc,0);
            if(storagePirc > storage0rderPrice){
                that.ticketPrice.storagePirc = parseFloat(0).toFixed(2);
                return $.alert("卡中储值金额不足");
            }
            that.payMentMoney.discountNum = parseFloat(storagePirc) + parseFloat(that.getParemtDate(that.ticketPrice.totalPrice,0));
            that.isCommonMoney();
        },
        //计算票卷中的储值金额
        changeTicket: function () {
           var that = this;
           if(!that.ticketPrice.cardId ){
               that.ticketPrice.ticketPriceListOk = [];
               that.ticketPrice.storagePirc = parseFloat(0).toFixed(2);
               return $.alert("请先选择使用票劵的会员卡信息")
           }
            if(!that.ticketPrice.cardNo || that.ticketPrice.cardNo == '未选择会员卡'){
                that.ticketPrice.ticketPriceListOk = [];
                that.ticketPrice.storagePirc = parseFloat(0).toFixed(2);
                return $.alert("请先选择使用票劵的会员卡信息")
            }
           that.ticketDeductiont();
        },
        //判断支付金额是否符合 -------- 公共（改新购的太麻烦了，后期再修改）
        isCommonMoney: function (val, str) {
            var that = this;
            var regt = /^[0-9]+\.?[0-9]*?$/;
            var num = val;
            if (!regt.test(num)) {
                num = parseFloat(0).toFixed(2);
            }
            var objStr = parseFloat(num).toFixed(2);
            that.payModel[str] = objStr;
            that.getCommonMoney();
        },
        //计算小计 -------- 公共
        getCommonMoney: function () {
            var that = this;
            var L1 = parseFloat(that.getParemtDate(that.payModel.L1,0));
            var L2 = parseFloat(that.getParemtDate(that.payModel.L2,0));
            var L3 = parseFloat(that.getParemtDate(that.payModel.L3,0));
            var L4 = parseFloat(that.getParemtDate(that.payModel.L4,0));
            var L5 = parseFloat(that.getParemtDate(that.payModel.L5,0));
            var L6 = parseFloat(that.getParemtDate(that.payModel.L6,0));
            var allMoney = L1 + L2 + L3 + L4 + L5 + L6;
            that.payModelMoney = allMoney;
            var price = that.getParemtDate(that.payMentMoney.price,0);
            var diff = that.getParemtDate(that.ticketPrice.differencePrice,0)
            //小计 = 价格+票卷差价
            var totalPrice = parseFloat(price) + parseFloat(diff);
            that.payMentMoney.totalPrice = totalPrice;
            //优惠折扣
            var discount =  that.getParemtDate(that.payMentMoney.discount,0);
            //抵扣金额
            var discountNum = that.getParemtDate(that.payMentMoney.discountNum,0);
            // 应付金额 =  小计-抵扣金额-优惠折扣
            that.payMentMoney.needPrice = parseFloat(totalPrice) - parseFloat(discountNum) - parseFloat(discount);
            that.payMentMoney.noPrice = 0;
            that.payMentMoney.retChange = 0;
            // 计算金额
            var money = parseFloat(that.getParemtDate(that.payMentMoney.needPrice,0)) - parseFloat(allMoney);
            if (money > 0) {
                //未付金额
                that.payMentMoney.noPrice = parseFloat(money).toFixed(2);
            }
            if (money < 0) {
                money = Math.abs(money)
                that.payMentMoney.retChange = parseFloat(money).toFixed(2);
            }
        },
        // 分页封装方法----调用分页前请设置PaginationName调用它的方法名称
        getPaginationPage: function (data) {
            Loading.prototype.hide();
            var that = this
            var methodsName = that.PaginationName;
            if (!methodsName || !data) {
                return;
            }
            var totalPage = data.totalPage; //总页数
            var paginationId = that.paginationId;
            $("#" + paginationId).html('');
            //总页数大于1再初始化
            if (totalPage >= 1) {
                new myPagination({
                    id: paginationId,
                    curPage: data.currPage, //初始页码
                    pageTotal: totalPage, //总页数
                    pageAmount: data.pageSize,  //每页多少条
                    dataTotal: data.totalCount, //总共多少条数据
                    showPageTotalFlag: true, //是否显示数据统计
                    showSkipInputFlag: true, //是否支持跳转
                    getPage: function (page) {
                        if (!methodsName) {
                            methodsName = that.PaginationName;
                        }
                        //获取当前页数
                        that[methodsName](page, data.pageSize, that.clientId)
                    }
                })
            }
        },
        //判断值封装
        getParemtDate: function (obj, def) {
            var par = obj;
            if (!par || par == '' || 'undefined' == typeof(par)) {
                par = def;
            }
            return par;
        },
        //验证凭证信息封装---------员工
        getPassParemt: function (methName, id,personnelId) {
            var that = this;
            var pass = $("#" + id).val();
            if (!pass) {
                return $.alert("请输入凭证信息");
            }
            var perId = personnelId;
            if(!perId || perId == ''){
                perId = $.cookie("id")
            }
            var data = {
                PassWord: pass,
                id: perId,
                customerCode: that.code,
                ShopId: $.cookie("shopid"),
            }
            var url = $.stringFormat('{0}/personnelInfo/getVerification', $.cookie('url'));
            that.getPassIsFlag(methName, url, data, that);
        },
        //验证凭证信息封装----------------客户
        getPassUseParemt: function (methName, id,isFlag) {
            var that = this;
            //验证客户授权凭证
            var pass = that.ticketPrice.userPass;
            var cardId = that.ticketPrice.cardId;
            if(isFlag){
               pass = $("#" + id).val();
               cardId = that.cardId;
            }
            if (!pass) {
                return $.alert("客户授权凭证不能为空")
            }
            var data = {
                usePasswd: pass,
                CustomerCode: that.code,
                cardId: cardId,
            }
            var url = $.stringFormat('{0}/frCardLimit/getVerification', $.cookie('url'));
            that.getPassIsFlag(methName, url, data, that);
        },
        getPassIsFlag: function (methName, url, data, that) {
            //避免多次点击
            if (that.randomNumber) {
                return $.alert("请勿太快重复点击");
            }
            //生成字符串
            that.randomNumber = Math.random().toString(36).substr(2);
            $.ajax({
                type: 'POST',
                url: url,
                data: JSON.stringify(data),
                dataType: "json",
                contentType: "application/json;charset=utf-8",
                success: function (res) {
                    if (res.code == '200') {
                        if ("验证成功" == res.data) {
                            that.randomNumber = '';
                            that[methName]();
                        }
                        return;
                    } else {
                        $.alert(res.msg)
                    }
                    that.randomNumber = '';
                }
            })
        },
        //查看
        toSee: function (note, mess) {
            var that = this;
            if( that.LiIndex == 3 ){
                if( 'cardNo' == note){
                    note = that.cardNo;
                }
            }
            if (!note) {  return ; }
            var m = mess + note;
            return $.alert(m);
        },
        //封装选择会员卡
        getMembershipCard: function (id) {
            var that = this;
            var cardId = $("#" + id).find("option:selected").attr("value");
            if (!cardId) {
                return true
            }
            that.cardId = cardId;
            var shopId = $("#" + id).find("option:selected").attr("data");
            that.shopId = shopId;
            var cardNo = $("#" + id).find("option:selected").attr("cardNo");
            that.cardNo = cardNo;
            var status = $("#" + id).find("option:selected").attr("status");
            that.status = status;
            return false;
        },
        //关闭弹窗
        colseX: function (id) {
            $("#" + id).hide();
        },
        //根据手机号获取会员信息
        getClientList: function (mark) {
            var that = this;
            if (!that.givePhone) {
                return;
            }
            that.ticketPrice.tickMark = mark;
            var isFlag = isPoneAvailable(that.givePhone);
            if (!isFlag) {
                that.givePhone = '';
                return $.alert("请输入正确的手机号");
            }
            var url = $.stringFormat("{0}/frClient/getClientList", $.cookie('url'));
            $.get(url, {
                    mobile: that.givePhone,
                    CustomerCode: $.cookie("code"),
                },
                function (res) {
                    if (res.code == '200') {
                        console.log("获取客户信息==========================================");
                        console.log(res);
                        that.optionClientList = res.data;
                        if(that.optionClientList.length > 0){
                            $("#clientList").show();
                        }
                    } else {
                        $.alert(res.msg)
                    }
                }
            )
        },
        //根据选中的客户信息，获取会员卡信息
        catcherClientList: function (obj) {
            var that = this;
            var id = obj.id;
            if (!id) {
                return;
            }
            $("#clientList").hide();
            that.catcherClientId = id;
            that.giveName = obj.clientName;
            if(that.ticketPrice.tickMark){
                that.ticketPrice.cardNo = '此会员的会员卡信息未选择';
                that.ticketPrice.cardUserName = obj.clientName;
            }
            var url = $.stringFormat('{0}/frCard/queryByFrCardList', $.cookie('url'));
            $.post(url, {
                    clientId: that.catcherClientId,
                    CustomerCode: that.code,
                    isUsing: true
                },
                function (res) {
                    if (res.code == '200') {
                        console.log("获取客户会员卡信息==========================================");
                        console.log(res);
                        that.optionClientCardList = res.data;
                        if(that.optionClientCardList.length > 0){
                            $("#clientCardList").show();
                        }
                    } else {
                        $.alert(res.msg)
                    }
                }
            )
            $("#clientList").hide();
        },
        //获取选中的会员卡信息
        catcheCardId: function (obj) {
            var that = this;
            if (!obj) {
                return;
            }

            that.catcherClientCardId = obj.id;
            that.giveName = obj.clientName;
            that.givePhone = obj.mobile;
            if(that.ticketPrice.tickMark){
                that.ticketPrice.cardId = obj.id;
                that.ticketPrice.cardNo = obj.cardNo;
                //获取此会员卡的储值信息
                that.ticketPrice.storage0rderPrice = that.getParemtDate(obj.orderPrice,0);
            }
            $("#clientList").hide();
            $("#clientCardList").hide();
        },
        //票券抵扣鼠标移出事件
        btn_deMouseleave: function () {
            $('.btn_de').hide();
        },
        //票券抵扣鼠标移入事件
        btn_deMouseenter: function () {
            $('.btn_de').show();
        },
        //获取销售门店列表
        getMarketShopList: function () {
            var that = this;
            var url = $.stringFormat('{0}/shop/getMarketShopList', $.cookie('url'));
            $.get(url, {
                    type: 0,//（0为获取上次添加卡类型时间，在这边无意义）
                },
                function (res) {
                    if (res.code == '200') {
                        that.loadData1.shopList = res.data.data;
                        that.loadData3.shopList = res.data.data;
                    } else {
                        $.alert(res.msg);
                    }
                }
            )
        },
        //获取销售人员列表
        getMarketUser: function (id) {
            var that = this;
            var shopId = $("#" + id).val();
            that.loadData1.cardInfoMap.shopId = shopId;
            that.getMarketShopPeople(shopId, that);
        },
        //获取销售人员列表
        getMarketShopPeople: function (shop_Id, that) {
            var url = $.stringFormat('{0}/personnelInfo/getMarketUserList', $.cookie('url'));
            $.get(url, {
                    shopId: shop_Id,//门店id
                },
                function (res) {
                    if (res.code == '200') {
                        that.maeketUserList = res.data.data;
                    } else {
                        $.alert(res.msg);
                    }
                }
            )
        },
        //获取会员卡列表
        getCardUserList: function (page, limit, id) {
            var that = this;
            //显示加载中
            Loading.prototype.show();
            if (!limit || !id) {
                id = page.id;
                limit = page.rows;
                page = page.page;
            }
            var url = $.stringFormat('{0}/frCard/queryUserCardList', $.cookie('url'));
            $.get(url, {
                    page: page,
                    rows: limit,
                    clientId: id,
                    code: that.code,
                },
                function (res) {
                    if (res.code == '200') {
                        var cardList = res.data.list;
                        if (cardList) {
                            that.loadData0.cardList = cardList.list;
                            that.loadData0.count = cardList.totalCount;
                            that.PaginationName = "getCardUserList";
                            that.getPaginationPage(cardList);
                        }
                        that.loadData0.beOverdue = that.getParemtDate(res.data.beOverdue, 0);
                    } else {
                        $.alert(res.msg)
                    }
                    Loading.prototype.hide();
                }
            )
        },
        //渲染会员卡信息
        carddetails: function (obj) {
            var that = this;
            var dataOne = obj
            var cardStatus = obj.status;
            //历史卡不允许再设置
            if(6 == cardStatus){
                return ;
            }
            that.cardId = obj.id;
            $('#MembershipCardlist').hide();
            that.loadData0.cardFlag = dataOne.cardFlag;
            that.loadData0.cardTypeName = dataOne.cardTypeName;
            that.loadData0.status = dataOne.status;
            that.loadData0.agreementNo = dataOne.agreementNo;
            that.loadData0.b_needPrice = dataOne.b_needPrice;
            that.loadData0.balance = (dataOne.b_needPrice / (dataOne.b_buyRightsNum + dataOne.b_giveRightsNum)) * dataOne.haveRightsNum;
            that.loadData0.availableNum = dataOne.b_buyRightsNum + dataOne.b_giveRightsNum;
            // that.loadData0.storageValue = obj.storageValue; 储值金额（等储值表出来后关联）
            that.loadData0.b_buyRightsNum = dataOne.b_buyRightsNum;
            that.loadData0.b_giveRightsNum = dataOne.b_giveRightsNum;
            that.loadData0.alreadyUsedNum = dataOne.b_buyRightsNum + dataOne.b_giveRightsNum - dataOne.haveRightsNum;
            that.loadData0.haveRightsNum = dataOne.haveRightsNum;
            that.loadData0.bindTime = dataOne.bindTime;
            if (dataOne.bindTime) {
                that.loadData0.invalidTime = timeFormatDate(obj.invalidTime);
            }
            if (!dataOne.bindTime) {
                $("#loadInvali").attr("disabled", "disabled");
                $("#loadFlag").attr("disabled", "disabled");
            }
            that.loadData0.b_status = dataOne.b_status;
            that.loadData0.b_auditStatus = dataOne.b_auditStatus;
            that.loadData0.flag = dataOne.flag;
            $('#MembershipCardDetails').show();
            that.getCardLimitList();
        },
        //获取会员卡使用限定列表
        getCardLimitList: function () {
            var that = this;
            var url = $.stringFormat('{0}/frCardLimit/getCardLimitList', $.cookie('url'));
            $.get(url, {
                    cardId: that.cardId,
                    code: that.code
                },
                function (res) {
                    if (res.code == '200') {
                        that.loadData0.limitList = res.data
                    } else {
                        $.alert(res.msg)
                    }
                }
            )
        },
        //保存会员卡信息失效日期
        Load0Save: function () {
            var that = this;
            if (!that.loadData0.bindTime) {
                return $.alert("客户未开卡")
            }
            if (!that.loadData0.invalidTime) {
                return $.alert("失效时间不能为空")
            }
            if (!that.loadData0.flag) {
                return $.alert("设置失效原因需填写")
            }
            if (that.randomNumber) {
                return $.alert("请勿太快重复点击");
            }
            that.randomNumber = Math.random().toString(36).substr(2);
            var url = $.stringFormat('{0}/frCard/saveInvalidTime', $.cookie('url'));
            $.post(url, {
                    invalidTime: that.loadData0.invalidTime,//过期时间
                    id: that.cardId,//会员卡id
                    flag: that.loadData0.flag,//原因
                    CustomerCode: that.code,
                    updateUserId: $.cookie("id"),
                },
                function (res) {
                    if (res.code == '200') {
                        $.alert("保存成功");
                        that.loadData0.flag = '';
                    } else {
                        $.alert(res.msg)
                    }
                    that.randomNumber = '';
                }
            )
        },
        //删除使用限定
        deleteCardLimit: function (id) {
            var that = this;
            if(that.loadData0.status == 6){
                return $.alert("历史卡不能操作");
            }
            if (that.randomNumber) {
                return $.alert("请勿太快重复点击");
            }
            that.randomNumber = Math.random().toString(36).substr(2);
            if (!id) {
                return;
            }
            var url = $.stringFormat('{0}/frCardLimit/deleteCardLimit', $.cookie('url'));
            $.post(url, {
                    id: id,
                    code: that.code,
                    personnelId: $.cookie("id"),
                },
                function (res) {
                    if (res.code == '200') {
                        $.alert("删除成功");
                        that.getCardLimitList();
                    } else {
                        $.alert(res.msg)
                    }
                    that.randomNumber = '';
                }
            )
        },
        //添加使用限定
        addCardLimit: function () {
            var that = this;
            if (!that.loadData0.limitList) {
                return;
            }
            if(that.loadData0.status == 6){
                return $.alert("历史卡不能操作");
            }
            if (that.loadData0.limitList.length >= that.loadData0.userdNum) {
                return $.alert("限定使用人数已满")
            }
            if (that.randomNumber) {
                return $.alert("请勿太快重复点击");
            }
            that.randomNumber = Math.random().toString(36).substr(2);
            if (!that.loadData0.userName) {
                return  $.alert("使用者姓名不能为空");
            }
            if (!that.loadData0.userPhone) {
                return  $.alert("使用者联系方式不能为空");
            }
            if (!that.loadData0.userPasswd) {
                return  $.alert("使用密码不能为空");
            }
            var url = $.stringFormat('{0}/frCardLimit/addCardLimit', $.cookie('url'));
            $.post(url, {
                    cardId: that.cardId,
                    CustomerCode: that.code,
                    useName: that.loadData0.userName,
                    usePhone: that.loadData0.userPhone,
                    usePasswd: that.loadData0.userPasswd,
                    note: that.loadData0.note,
                    updateUserId: $.cookie("id"),
                },
                function (res) {
                    if (res.code == '200') {
                        $.alert("添加成功")
                        that.loadData0.userName = '';
                        that.loadData0.userPhone = '';
                        that.loadData0.userPasswd = '';
                        that.loadData0.note = '';
                        that.getCardLimitList();
                    } else {
                        $.alert(res.msg)
                    }
                    that.randomNumber = '';
                }
            )
        },
        //获取会员卡信息
        getOptionsCardList: function () {
            var that = this;
            var url = $.stringFormat('{0}/frCard/queryByFrCardList', $.cookie('url'));
            $.post(url, {
                    clientId: that.clientId,
                    CustomerCode: that.code,
                    isUsing: true
                },
                function (res) {
                    if (res.code == '200') {
                        that.optionCardLis = res.data;
                    } else {
                        $.alert(res.msg)
                    }
                }
            )
        },
        //获取子卡列表
        getChildCardList: function () {
            var that = this;
            that.loadData7.zkXffs = 0;
            var isFlag = that.getMembershipCard('childCard');
            if (isFlag) {
                return;
            }
            that.loadData7.parentCardId = that.cardId;
            that.maeketUserList = [];
            var url = $.stringFormat('{0}/frChildCard/getChildCardList', $.cookie('url'));
            $.get(url, {
                    cardId: that.cardId,
                    code: that.code,
                },
                function (res) {
                    if (res.code == '200') {
                        console.log(res);
                        that.loadData7.childCardList = res.data.list;
                        that.loadData7.zkNum = 0;
                        that.loadData7.zkCyfs = '0';
                        that.loadData7.zkXffs = '0';
                        that.loadData7.alreadyNum = that.loadData7.childCardList.length;
                        var OneData = res.data.frSet;
                        if (OneData) {
                            that.loadData7.zkNum = OneData.zkNum;
                            that.loadData7.zkCyfs = OneData.zkCyfs;
                            that.loadData7.zkXffs = OneData.zkXffs;
                            that.loadData7.cardHandle = OneData.zkClfs;
                        }
                        that.getMarketShopPeople(that.shopId, that);
                    } else {
                        $.alert(res.msg);
                    }
                }
            )
        },
        //添加子卡
        saveChildCard: function () {
            var that = this;
            var aNum = that.loadData7.alreadyNum
            var nNum = that.loadData7.zkNum;
            if (aNum >= nNum) {
                return  $.alert("子卡数量满额");
            }
            if (!that.cardId) {
                return  $.alert("请先选择会员卡");
            }
            that.loadData7.cardNo = $("#cardNo").val();
            if (!that.loadData7.cardNo) {
                return $.alert("请填写或者生成会员卡号");

            }
            var cardHandleType = true;
            if (that.loadData7.cardHandle >= 0) {
                cardHandleType = false;
            }
            if (cardHandleType) {
                return $.alert("请选择分享权益");
            }
            if (that.loadData7.zkXffs == 1) {
                if (!that.loadData7.shareNum) {
                    return  $.alert("请设置独立分享的卡权益");
                }
            }
            that.loadData7.userName = that.giveName;
            if (!that.loadData7.userName) {
                return  $.alert("请填写持卡人姓名");
            }
            that.loadData7.mobile = that.givePhone;
            if (!that.loadData7.mobile) {
                return  $.alert("请填写持卡人手机号");
            }
            if (!that.loadData7.saleManId) {
                return $.alert("请选择销售人员");
            }
            //避免多次点击
            if (that.randomNumber) {
                return $.alert("请勿太快重复点击");
            }
            //生成字符串
            that.randomNumber = Math.random().toString(36).substr(2);
            var param = {
                cardNo: that.loadData7.cardNo,
                cardHandle: that.loadData7.cardHandle,
                shareBenefit: that.loadData7.shareBenefit,
                userName: that.loadData7.userName,
                mobile: that.loadData7.mobile,
                saleManId: that.loadData7.saleManId,
                outCardNo: that.loadData7.outCardNo,
                shopId: that.shopId,
                parentCardId: that.loadData7.parentCardId,
                handlePersonalId: $.cookie('id'),
                shareNum: that.loadData7.shareNum,
            }
            var url = $.stringFormat('{0}/frChildCard/insertOrUpdateChildCard', $.cookie('url'));
            $.post(url, param, function (res) {
                if (res.code == '200') {
                    $.alert("添加成功");
                    that.getChildCardList();
                    that.getOptionsCardList();
                    //清空数据
                    $("#cardNo").val('');
                    that.loadData7.userName = '';
                    that.loadData7.outCardNo = '';
                    that.loadData7.mobile = '';
                    that.loadData7.shareNum = 0;
                } else {
                    $.alert(res.msg)
                }
                that.randomNumber = '';
            })
        },
        //获取补卡列表
        toCarDsupplyRecordList: function () {
            var that = this;
            var clientId = that.clientId;
            that.getMembershipCard('cardSupply1');
            that.loadData5.SupplyMoney = 0;
            that.maeketUserList = [];
            var url = $.stringFormat('{0}/frCardSupplyRecord/getCarDsupplyRecordList', $.cookie('url'));
            $.get(url, {
                CustomerCode: that.code,
                clientId: clientId,
                type : 1
            }, function (res) {
                if (res.code == '200') {
                    that.loadData5.carDsupplyRecordList = res.data.list;
                    var money = res.data.SupplyMoney;
                    if (money) {
                        that.loadData5.SupplyMoney = money;
                    }
                } else {
                    $.alert(res.msg)
                }
                that.getMarketShopPeople(that.shopId, that);
            });
        },
        // 添加补卡前需先选择会员卡
        addCardSupplyCard: function () {
            var that = this;
            var isFlag = that.getMembershipCard('cardSupply1');
            if (isFlag) {
                return;
            }
            that.loadData5.addSupply1.oriCardNo = that.cardNo;
            that.loadData5.addSupply1.oriCardId = that.cardId;
            that.getMarketShopPeople(that.shopId, that);
        },
        //随机生成协议号
        addCardAgreement: function (id) {
            var that = this;
            var url = $.stringFormat('{0}/frAgreement/addCardAgreement', $.cookie('url'));
            $.get(url, {
                    code: that.code,
                },
                function (res) {
                    if (res.code == '200') {
                        $("#"+id).val(res.data.agreement);
                        that.loadData1.cardInfoMap.agreementId = res.data.agreementId;
                        that.loadData1.cardInfoMap.agreementNo = res.data.agreement;
                        that.checkCardAgreement(id);
                    } else {
                        $.alert(res.msg);
                    }
                }
            )
        },
        //--------------------------------------------新购
        //加载新购订单列表
        getCardOrederList: function (page, limit, id) {
            var that = this;
            if (!limit || !id) {
                id = page.id;
                limit = page.rows;
                page = page.page;
            }
            var url = $.stringFormat('{0}/frCardOrderInfo/getCardOrederList', $.cookie('url'));
            $.get(url, {
                    page: page,
                    clientId: id,
                    CustomerCode: that.code,
                    rows: limit,
                },
                function (res) {
                    if (res.code == '200') {
                        that.count = res.data.totalCount;
                        that.loadData1.orderList = res.data.list;
                        that.PaginationName = "getCardOrederList";
                        that.getPaginationPage(res);
                    } else {
                        $.alert(res.msg)
                    }
                }
            )
        },
        //判断是否超过赠送权益
        checkQtZdqy: function () {
            var that = this;
            var qtZdqy = that.loadData1.cardInfoMap.giveRightsNum;
            var qtZdqyMax = that.loadData1.addQtZdqy;
            that.loadData1.qtZdqy = true;
            that.loadData1.cardInfoMap.haveRightsNum = that.loadData1.cardInfoMap.buyRightsNum;
            if (qtZdqy && qtZdqy != '') {
                if (qtZdqy > qtZdqyMax) {
                    $("#qtZdqy1").html("赠送权益大于最高赠送权益").css("color", "red").css("margin-left", "5px");
                    that.loadData1.qtZdqy = false;
                } else {
                    $("#qtZdqy1").html("");
                    that.loadData1.cardInfoMap.haveRightsNum += parseInt(qtZdqy);
                }
            }
        },
        //结账前先验证数据
        toOffers: function () {
            var that = this;
            //销售人员
            var share = $("#maeketUser option:selected").text();
            var shareId = $("#maeketUser option:selected").attr("value");
            var agreement = $("#agreement").val();
            that.loadData1.cardInfoMap.personnelId = shareId;
            that.loadData1.cardInfoMap.agreementNo = agreement;
            if (!that.loadData1.cardInfoMap.personnelId) {
                return $.alert("请选择销售人员");
            }
            if (!that.loadData1.cardInfoMap.agreementNo) {
                return $.alert("协议编号不能为空");
            }
            if (!that.loadData1.cardInfoMap.salesPrice) {
                return $.alert("该会员卡未设置售价，请选择其他卡种");
            }
            if (!that.loadData1.qtZdqy) {
                return $.alert("赠送权益超额，请重新设置");
            }
            if (!that.loadData1.minPrice) {
                return $.alert("请重新填写购卡金额");
            }
            if (!that.loadData1.cardInfoMap.cardTypeName) {
                return  $.alert("请先选择要购买的会员卡信息");
            }
            if (!that.loadData1.cardInfoMap.buyRightsNum) {
                return $.alert("该会员卡未设置卡权益，请选择其他卡种");
            }
            if (!that.loadData1.cardInfoMap.serviceLife) {
               return  $.alert("该会员卡未设置有效时长，请选择其他卡种");
            }
            that.loadData1.cardInfoMap.cardNo = $("#cardNum").val();
            if (! that.loadData1.cardInfoMap.cardNo) {
                return  $.alert("会员卡号不能为空");
            }
            if (!that.loadData1.cardInfoMap.externalNo) {
                return $.alert("请输入外部卡号");
            }
            if (!that.loadData1.cardInfoMap.isFlage) {
                return  $.alert("是否设置自动开卡,需选择");
            }
            if (that.loadData1.cardInfoMap.isFlage == 1) {
                if (!that.loadData1.cardInfoMap.bindTime) {
                    return $.alert("请设置开卡时间");
                }
            }
            that.getAllotSetTypeIsFlag(1);
        },
        //验证业绩分配是否有值
        getAllotSetTypeIsFlag: function (t) {
            var that = this;
            if (that.loadData1.cardInfoMap.allotSetType) {
                if (!that.loadData1.orderAllotSetSav.allotType) {
                    return $.alert("销售价格分配比例/金额类型需选择")
                }
                if (!that.loadData1.orderAllotSetSav.allotNum) {
                    return $.alert("销售价格分配比例/金额需填写")
                }
                if (!that.loadData1.orderAllotSetSav.saleAllotType) {
                    return $.alert("业绩分配类型有误，请重新设置")
                }
                if (that.loadData1.orderAllotSetList.length <= 0) {
                    return $.alert("设置的业绩比例信息有问题，请重新设置")
                }
                that.loadData1.cardInfoMap.orderAllotSetList = that.loadData1.orderAllotSetList;
            }
            that.loadData1.cardInfoMap.orderAllotSetSav = that.loadData1.orderAllotSetSav;
            if (t == 1) {
                that.newPopup();
            }
            if (t == 8) {
                var isFlag = true;
                if(that.payMentMoney.discountNum > 0 ){
                    if(!that.ticketPrice.cardId){
                        return $.alert("未获取到抵扣的会员卡信息");
                    }
                    if(!that.ticketPrice.userPass){
                        return $.alert("请输入要抵扣的会员卡的凭证");
                    }
                    isFlag = false;
                    that.getPassUseParemt('toCheckMoenyOK', '',false);
                }
                if(isFlag){
                    that.toCheckMoenyOK();
                }
            }
        },
        //随机生成订单编号
        getOrderNo: function () {
            var that = this;
            var url = $.stringFormat("{0}/frCardOrderInfo/getOrderNo", $.cookie('url'));
            $.get(url, {},
                function (res) {
                    if (res.code == '200') {
                        that.loadData1.cardInfoMap.orderNo = res.data;
                    } else {
                        $.alert(res.msg)
                    }
                }
            )
        },
        //初始化会员卡数据
        initLoad1: function () {
            var that = this;
            that.loadData1.cardInfoMap.totalPrice = 0;       //购卡金额
            that.loadData1.cardInfoMap.giveRightsNum = 0;       //赠送权益
            that.loadData1.cardInfoMap.isFlage = '';       //是否直接开卡 0，否，1是
            that.loadData1.cardInfoMap.agreementId = '';       //编号协议ID
            that.loadData1.cardInfoMap.agreementNo = '';       //协议编号
            that.loadData1.cardInfoMap.bindTime = 0;       //开卡日期
            that.loadData1.cardInfoMap.externalNo = 0;       //外部卡号
            that.loadData1.cardInfoMap.cardNumId = 0;       //会员卡号规则ID
            that.loadData1.cardInfoMap.cardNo = 0;       //会员卡号
            if(that.loadData1.cardInfoMap.allotSetType ==1 ){
                $.confirm({
                    title: '确认',
                    content: '是否需要重置业绩分配信息?',
                    icon: 'glyphicon glyphicon-question-sign',
                    buttons: {
                        ok: {
                            text: '是',
                            btnClass: 'btn-primary',
                            action: function() {
                                that.initAllotSet();
                            }
                        },
                        cancel: {
                            text: '否',
                            btnClass: 'btn-primary',
                        }
                    }
                })
            }
            return ;
        },
        //初始化业绩分配 切换窗口使用
        initAllotSet: function () {
            var that = this;
            that.loadData1.orderAllotSetListOne = [];
            that.loadData1.orderAllotSetList = [];
            that.loadData1.allotNumOne = 0;
            that.loadData1.allotNumTwo = 0;
            that.loadData1.cardInfoMap.allotSetType = 0;
            that.loadData1.orderAllotSetSav.allotType = '';
            that.loadData1.orderAllotSetSav.allotNum = 0;
            that.loadData1.orderAllotSetSav.saleAllotType = 0;
            that.loadData1.orderAllotSet.personnelId = '';
            that.loadData1.orderAllotSet.saleAllotNum = '';
            that.loadData1.orderAllotSet.allotType = '';
            that.loadData1.orderAllotSet.allotNumOne = 0;
            that.loadData1.orderAllotSet.allotNumTwo = 0;
            that.loadData1.orderAllotSet.saleAllotType = 0;
        },
        addOrderAllotSetOne: function () {
            var that = this;
            if (!that.loadData1.orderAllotSet.personnelId) {
                return $.alert("请选择销售人员");
            }
            if (!that.loadData1.orderAllotSet.saleAllotNum) {
                return $.alert("请填写业分配比例/金额");
            }
            var val = {
                personnelId: that.loadData1.orderAllotSet.personnelId,
                saleAllotNum: that.loadData1.orderAllotSet.saleAllotNum,
            }
            that.loadData1.orderAllotSetListOne.push(val)
        },
        //保存并关闭弹窗
        addOrderAllotSet: function () {
            //点击保存的时候，设置订单里的业绩分配为1 表示已设置
            var that = this;
            var allotType = that.loadData1.orderAllotSet.allotType;
            var allotNum = 0;
            if (!allotType) {
                return $.alert("销售价格分配比例/金额未选择")
            }
            if (allotType == 1) {
                if (!that.loadData1.orderAllotSet.allotNumOne) {
                    return $.alert("销售业绩比例需填写")
                }
                allotNum = that.loadData1.orderAllotSet.allotNumOne;
            }
            if (allotType == 2) {
                if (!that.loadData1.orderAllotSet.allotNumTwo) {
                    return $.alert("销售业绩金额需填写")
                }
                allotNum = that.loadData1.orderAllotSet.allotNumTwo;
            }
            var saleAllotType = that.loadData1.orderAllotSet.saleAllotType;
            if (!saleAllotType) {
                return $.alert("销售人员销售业绩分配比例/金额需选择");
            }
            if (that.loadData1.orderAllotSetListOne.length <= 0) {
                return $.alert("业绩分配销售人员未设置");
            }
            that.loadData1.orderAllotSetSav.allotType = allotType;
            that.loadData1.orderAllotSetSav.allotNum = allotNum;
            that.loadData1.orderAllotSetSav.saleAllotType = saleAllotType;
            that.loadData1.orderAllotSetList = [];
            if (that.loadData1.orderAllotSetListOne.length > 0) {
                for (var i = 0; i < that.loadData1.orderAllotSetListOne.length; i++) {
                    var val = {
                        personnelId: that.loadData1.orderAllotSetListOne[i].personnelId,
                        saleAllotNum: that.loadData1.orderAllotSetListOne[i].saleAllotNum,
                    }
                    that.loadData1.orderAllotSetList.push(val);
                }
            }
            that.loadData1.cardInfoMap.allotSetType = 1;
            that.newCustomers_maskLayer_X();
        },
        // 关闭弹窗
        newCustomers_maskLayer_X: function () {
            var that = this;
            $('.newCustomers_maskLayer').hide();
            var num = that.loadData1.orderAllotSetListOne.length;
            that.loadData1.orderAllotSetListOne.length = 0;
            if (num > 0) {
                for (var i = 0; i < num; i++) {
                    $('.orderAllotSet').eq(i).remove();
                }
            }
            that.loadData1.orderAllotSet.personnelId = '';
            that.loadData1.orderAllotSet.saleAllotNum = '';
            that.loadData1.orderAllotSet.allotType = '';
            that.loadData1.orderAllotSet.allotNumOne = 0;
            that.loadData1.orderAllotSet.allotNumTwo = 0;
            that.loadData1.orderAllotSet.saleAllotType = 0;
        },
        //打开弹窗
        performances: function () {
            var that = this;
            $('#performance').show();
            var allotType = that.loadData1.orderAllotSetSav.allotType;
            var allotNum = that.loadData1.orderAllotSetSav.allotNum;
            if (allotType == 1) {
                that.loadData1.orderAllotSet.allotNumOne = allotNum;
            }
            if (allotType == 2) {
                that.loadData1.orderAllotSet.allotNumTwo = allotNum;
            }
            var saleAllotType = that.loadData1.orderAllotSetSav.saleAllotType;
            that.loadData1.orderAllotSet.allotType = allotType;
            that.loadData1.orderAllotSet.allotNum = allotNum;
            that.loadData1.orderAllotSet.saleAllotType = saleAllotType;

            if (that.loadData1.orderAllotSetList.length > 0) {
                for (var i = 0; i < that.loadData1.orderAllotSetList.length; i++) {
                    var val = {
                        personnelId: that.loadData1.orderAllotSetList[i].personnelId,
                        saleAllotNum: that.loadData1.orderAllotSetList[i].saleAllotNum,
                    }
                    that.loadData1.orderAllotSetListOne.push(val);
                }
            }
        },
        //添加补卡信息
        addSupply1Ok: function () {
            var that = this;
            if (!that.clientId) {
                return $.alert("客户信息未获取，请刷新页面后再次操作");
            }
            that.loadData5.addSupply1.oriCardId = that.cardId;
            if (!that.loadData5.addSupply1.oriCardId) {
                return $.alert("请先选择要补卡的会员卡信息");
            }
            if (!that.loadData5.addSupply1.externalNo) {
                return $.alert("外部卡号未填写");
            }
            that.loadData5.addSupply1.newCardNo = $("#cardSupply").val();
            if (!that.loadData5.addSupply1.newCardNo) {
                return $.alert("请先随机生成新会员卡号");
            }
            if (!that.loadData5.addSupply1.personnelId) {
                return $.alert("请先选择销售人员ID");
            }
            //不确定是否需要验证补卡金额
            if (that.randomNumber) {
                return $.alert("请勿太快重复点击");
            }
            that.loadData5.addSupply1.payPrice = that.payModelMoney;
            //生成字符串
            that.randomNumber = Math.random().toString(36).substr(2);
            var addSupply1 = that.loadData5.addSupply1;
            addSupply1.CustomerCode = that.code;
            addSupply1.clientId = that.clientId;
            var payModel = that.payModel;
            var data = {
                supply1: JSON.stringify(addSupply1),
                payModel: JSON.stringify(payModel)
            };
            var url = $.stringFormat('{0}/frCardSupplyRecord/addSupply1Ok', $.cookie('url'));
            $.ajax({
                type: 'POST',
                url: url,
                data: JSON.stringify(data),
                dataType: "json",
                contentType: "application/json;charset=utf-8",
                success: function (res) {
                    if (res.code == '200') {
                        that.getOptionsCardList();
                        that.toCarDsupplyRecordList();
                        $("#cardSupply").val('');
                        that.loadData5.addSupply1.externalNo = '';
                        that.loadData5.addSupply1.newCardNo = '';
                        that.loadData5.addSupply1.supplyRemarks = '';
                        that.loadData5.addSupply1.oriCardId = '';
                        that.loadData5.addSupply1.oriCardNo = '';
                        that.loadData5.addSupply1.personnelId = '';
                        that.loadData5.addSupply1.payPrice = parseFloat(0).toFixed();
                        that.initPayModel();
                    } else {
                        $.alert(res.msg)
                    }
                    that.randomNumber = '';
                }
            });

        },
        //初始化绑定时间
        chackBindTime: function () {
            var that = this;
            var num = that.loadData1.cardInfoMap.isFlage;
            that.loadData1.cardInfoMap.bindTime = '';
            $('#bindTime').attr("disabled", "disabled");
            if (num == 1) {
                $('#bindTime').removeAttr("disabled");
            }
        },
        //获取退卡列表
        getBlackCardList: function () {
            var that = this;
            var clientId = that.clientId;
            var code = that.code;
            var url = $.stringFormat('{0}/frCardOrderBack/getBlackCardList', $.cookie('url'));
            $.get(url, {
                clientId: clientId,
                CustomerCode: code,
            }, function (res) {
                if (res.code == '200') {
                    that.loadData10.blacekCardList = res.data.list;
                    var money = res.data.SupplyMoney;
                    if (money) {
                        that.loadData10.SupplyMoney = money;
                    }
                } else {
                    $.alert(res.msg)
                }
            });
        },
        //添加退卡信息
        getBlackCardOne: function () {
            var that = this;
            var isFlag = that.getMembershipCard('blackCard');
            if (isFlag) {
                return;
            }
            var careType = that.status;
            //6 历史卡， 3 过期卡
            if (careType == 6 || careType == 3) {
                return;
            }
            var url = $.stringFormat('{0}/frCardOrderInfo/getBlaceCardData', $.cookie('url'));
            $.get(url, {
                cardId: that.cardId,
                code: that.code,
                clientId: that.clientId,
            }, function (res) {
                if (res.code == '200') {
                    console.log("获取退卡信息============================");
                    console.log(res);
                    that.loadData10.saleName = res.data.saleName;
                    that.loadData10.saleStatus = res.data.saleStatus;
                     var allPrice = that.getParemtDate(res.data.allPrice,0);
                    that.loadData10.addBlackCard.needPrice = parseFloat(allPrice).toFixed(2);
                    that.loadData10.addBlackCard.totalPrice = that.loadData10.addBlackCard.needPrice;
                    that.loadData10.addBlackCard.personnelId = res.data.personnelId;
                    that.loadData10.addBlackCard.cardFlag = res.data.cardFlag;
                    that.loadData10.addBlackCard.cardTypeName = res.data.cardName;
                } else {
                    $.alert(res.msg)
                }
            })
        },
        calculationMoney: function () {
            var that = this;
            var needPrice = that.loadData10.addBlackCard.needPrice;
            var procedPrcie = that.loadData10.addBlackCard.procedPrcie;
            if (!procedPrcie || procedPrcie == '' || 'undefined' == typeof(procedPrcie)) {
                procedPrcie = 0;
            }
            var allMoney = parseFloat(needPrice) - parseFloat(procedPrcie);
            that.loadData10.addBlackCard.totalPrice = parseFloat(allMoney).toFixed(2);
        },
        //验证退款凭证
        blackOk: function () {
            var that = this;
            that.loadData10.addBlackCard.cardId = that.cardId;
            that.loadData10.addBlackCard.clientId = that.clientId;
            if (!that.loadData10.addBlackCard.cardId) {
                return $.alert("会员卡信息获取错误，请刷新后重新操作")
            }
            if (!that.loadData10.addBlackCard.clientId) {
                return $.alert("会员信息获取有误，请刷新后重新操作")
            }
            var allotSetType = that.loadData10.addBlackCard.allotSetType;
            if (!allotSetType && allotSetType != 0) {
                return $.alert("请选择是否扣除当月业绩")
            }
            var refundTypeR = $("#refundType").find("option:selected").attr("value");
            that.loadData10.addBlackCard.refundType = refundTypeR;
            if (that.loadData10.addBlackCard.refundType != 4) {
                if (!that.loadData10.addBlackCard.accountNumber) {
                    return $.alert("非现金退款，请填写退款账号")
                }
            }
            that.getPassParemt('toBlackSubimt', 'blackPassId','');
        },
        //退卡保存
        toBlackSubimt: function () {
            var that = this;
            if (that.randomNumber) {
                return $.alert("请勿太快重复点击");
            }
            //生成字符串
            that.randomNumber = Math.random().toString(36).substr(2);
            var data = that.loadData10.addBlackCard;
            data.CustomerCode = $.cookie("code");
            var url = $.stringFormat('{0}/frCardOrderBack/toBlackSubimt', $.cookie('url'));
            $.ajax({
                type: 'POST',
                url: url,
                data: JSON.stringify(data),
                dataType: "json",
                contentType: "application/json;charset=utf-8",
                success: function (res) {
                    if (res.code == '200') {
                        that.getOptionsCardList();
                        that.getBlackCardList();
                        that.loadData10.addBlackCard.cardId = '';
                        that.loadData10.addBlackCard.needPrice = parseFloat(0).toFixed(2);
                        that.loadData10.addBlackCard.procedPrcie = parseFloat(0).toFixed(2);
                        that.loadData10.addBlackCard.totalPrice = parseFloat(0).toFixed(2);
                        that.loadData10.addBlackCard.allotSetType = 0;
                        that.loadData10.addBlackCard.cardFlag = '';
                        that.loadData10.addBlackCard.cardTypeName = '';
                        that.loadData10.addBlackCard.accountNumber = '';
                        that.loadData10.addBlackCard.refundType = '';
                    } else {
                        $.alert(res.msg);
                    }
                    that.randomNumber = '';
                }
            })
        },
        // 停卡、冻结获取
        getCardStopList: function () {
            var that = this;
            var isFlag = that.getMembershipCard('cardStop');
            if (isFlag) {
                return;
            }
            that.loadData4.addStroData.status = that.status;
            var type = that.transferCardBoxTop + 1;
            var data = {
                cardId: that.cardId,
                clientId: that.clientId,
                CustomerCode: that.code,
                type: type,
            }
            var url = $.stringFormat('{0}/frCardOrderStop/getStopCardListByType', $.cookie('url'));
            $.get(url, data, function (res) {
                if (res.code == '200') {
                    var frCard = res.data.frCard;
                    if (frCard != null) {
                        that.loadData4.stopNum = frCard.stopNum;
                        that.loadData4.stopDays = frCard.stopDays;
                        that.loadData4.totalDays = frCard.totalDays;
                        that.loadData4.outPrice = frCard.outPrice;
                        that.loadData4.addStroData.invalidTime = frCard.invalidTime;
                        that.loadData4.addStroData.status = frCard.status;
                    }
                    that.loadData4.estInvalidTime = that.loadData4.addStroData.invalidTime;
                    //已使用次数
                    that.loadData4.realStopNum = res.data.realStopNum;
                    //已经使用时间
                    that.loadData4.realTotalNum = res.data.realTotalNum;
                    var methName = "stopType2"
                    if (that.transferCardBoxTop == 0) {
                        methName = "stopType1"
                    }
                    that.loadData4[methName] = res.data.list;
                    if (type == 1) {
                        that.initGetStartTime();
                    }
                } else {
                    $.alert(res.msg)
                }
            })
        },
        //选择图片弹窗
        pictureSelected: function (num) {
            var that = this;
            that.loadData4.imgNum = num;
            $('#file-fr').click();
        },
        //显示图片
        pictureShow: function (e) {
            var that = this;
            var file = e.srcElement.files.item(0);
            that.loadData4.imagesList = {};
            var num = 'price' + that.loadData4.imgNum;
            // 看支持不支持FileReader
            if (!file || !window.FileReader) return;
            if (/^image/.test(file.type)) {
                // 创建一个reader
                var reader = new FileReader();
                // 将图片将转成 base64 格式
                reader.readAsDataURL(file);
                // 读取成功后的回调
                reader.onloadend = function () {
                    $("#" + num).attr('src', this.result);
                    that.loadData4.imagesList[num] = file;
                }
            }
        },
        //初始化停卡开始时间（时间联动）
        initGetStartTime: function () {
            var that = this;
            if (!that.cardId) {
                return $.alert("请先选择要停卡的会员卡信息");
            }
            var nowTime = getNowTime();
            var start = {
                format: "YYYY-MM-DD",
                minDate: nowTime,
                donefun: function (obj) {
                    that.loadData4.addStroData.startTime = obj.val;
                    that.initGetEstEndTime();
                    that.computingTime();
                }
            };
            // 初始化: 开始时间
            jeDate('.startTime', start);
        },
        //初始化停卡结束时间（时间联动）
        initGetEstEndTime: function () {
            var that = this;
            var nowTime = that.loadData4.addStroData.startTime;
            var end = {
                trigger: false,
                format: "YYYY-MM-DD",
                minDate: nowTime,
                maxDate: function () {
                    return getNewData(nowTime, that.loadData4.stopDays, false)
                },
                donefun: function (obj) {
                    that.loadData4.addStroData.estEndTime = obj.val;
                    that.computingTime();
                }
            }
            jeDate('.estEndTime', end);
        },
        //计算预计停卡时间 及收费
        computingTime: function () {
            var that = this;
            var s1 = that.loadData4.addStroData.startTime;
            var s2 = that.loadData4.addStroData.estEndTime;
            var time = getTwoBetweenDates(s1, s2);
            if (time < 0) {
                time = 0;
            }
            that.loadData4.addStroData.estStopTime = time;
            //计算前准备
            //已经停卡的次数
            var realStopNum = that.getParemtDate(that.loadData4.realStopNum, 0);
            //可停卡次数
            var stopNum = that.loadData4.stopNum;
            if (realStopNum >= stopNum) {
                return $.alert("停卡次数已满");
            }
            var invalidTime = that.loadData4.addStroData.invalidTime;
            that.loadData4.estInvalidTime = getNewData(invalidTime, time, true);
            //每次最多停卡几天
            var stopDays = that.loadData4.addStroData.stopDays;
            //超出的费用
            var outPrice = that.getParemtDate(that.loadData4.outPrice, 0);
            //停卡总时长
            var totalDays = that.getParemtDate(that.loadData4.totalDays, 0);
            //实际停卡总时长
            var realTotalNum = that.getParemtDate(that.loadData4.realTotalNum, 0);
            //收费金额
            var price = 0;
            var todayNum = totalDays - realTotalNum;
            if (time > todayNum) {
                price = (time - todayNum) * outPrice;
            }
            that.loadData4.addStroData.totalPrice = price;
            that.payMentMoney.price = price;
            that.getCommonMoney();
        },
        //验证停卡信息
        stopSubtime: function () {
            var that = this;
            if (!that.cardId) {
                return $.alert("要停卡的会员卡信息未选择")
            }
            //已经停卡的次数
            var realStopNum = that.getParemtDate(that.loadData4.realStopNum, 0);
            //可停卡次数
            var stopNum = that.loadData4.stopNum;
            if (realStopNum >= stopNum) {
                return $.alert("停卡次数已满");
            }
            if (!that.loadData4.status && that.loadData4.status != 0) {
                return $.alert("会员卡状态设置有误，请联系管理员")
            }
            if (that.loadData4.status == 2) {
                return $.alert("该卡已经冻结，请先解冻")
            }
            if (that.loadData4.status == 3) {
                return $.alert("该卡已过期，无需停卡")
            }
            if (that.loadData4.status == 4) {
                return $.alert("停卡仅限已开卡的会员卡")
            }
            if (that.loadData4.status == 6) {
                return $.alert("历史卡无法操作")
            }
            if (!that.loadData4.addStroData.invalidTime) {
                return $.alert("停卡仅限已开卡的会员卡")
            }
            //验证参数
            if (!that.loadData4.addStroData.startTime) {
                return $.alert("开始停卡时间未选择");
            }
            if (!that.loadData4.addStroData.estEndTime) {
                return $.alert("预计停止结束时间未选择");
            }
            if (that.loadData4.addStroData.estStopTime < 0) {
                return $.alert("停卡时长设置有误，请重新设置");
            }
            if (that.loadData4.addStroData.totalPrice > 0) {
                var payMoney = that.payModelMoney;
                if (that.loadData4.addStroData.totalPrice > payMoney) {
                    return $.alert("支付金额不足");
                }
            }
            var isFlage = true;
            var imgList = that.loadData4.imagesList;
            var leng = Object.keys(imgList);
            if (leng.length > 0) {
                isFlage = that.toImagesIsFlage(leng, isFlage);
            }
            if (!isFlage) {
                that.loadData4.imagesList = {};
                that.loadData4.imageFiles = [];
                return $.alert("图片大小不得大于5MB")
            }
            that.getPassUseParemt('toStopSubtimeOk', 'stopUserVoucher',true);
        },
        //验证通过后，添加停卡信息
        toStopSubtimeOk: function () {
            var that = this;
            that.loadData4.addStroData.invalidTime = that.loadData4.estInvalidTime;
            that.loadData4.addStroData.personnelId = $.cookie("id");
            var data = that.loadData4.addStroData;
            data.cardId = that.cardId;
            data.CustomerCode = that.code;
            data.clientId = that.clientId;
            //图片和数据一起上传
            var param = new FormData();
            for (var i = 0; i < that.loadData4.imageFiles.length; i++) {
                param.append('file', that.loadData4.imageFiles[i]); //通过append向form对象添加数据
            }
            param.append('childPath', 'avatar/'); //通过append向form对象添加数据
            param.append('frStop', JSON.stringify(data));
            param.append('payModel',JSON.stringify(that.payModel));
            var url = $.stringFormat('{0}/frCardOrderStop/toAddUpdateLoad', $.cookie('url'));
            $.ajax({
                type: 'POST',
                url: url,
                data: param,
                dataType: "json",
                processData: false,
                contentType: false,
                cache: false,
                success: function (res) {
                    if (res.code == '200') {
                        that.loadData4.addStroData.startTime = '';
                        that.loadData4.addStroData.estEndTime = '';
                        that.loadData4.addStroData.estStopTime = '';
                        that.loadData4.addStroData.flag = '';
                        that.loadData4.addStroData.totalPrice = 0;
                        that.loadData4.imagesList = {};
                        that.loadData4.imageFiles = [];
                        that.payModelMoney = 0;
                        $("#stopUserVoucher").val('');
                        that.initPayModel();
                        that.getCardStopList();
                    } else {
                        $.alert(res.msg)
                    }
                    Loading.prototype.hide();
                    that.randomNumber = '';
                }
            });

        },
        //判断图片大小
        toImagesIsFlage: function (images, isFlage) {
            var that = this;
            if (!images) {
                return
            }
            for (var i = 0; i < images.length; i++) {
                var iKey = images[i];
                var obj = that.loadData4.imagesList[iKey];
                if (obj) {
                    var file = obj;
                    if (file.size > 5242880) {
                        isFlage = false;
                        break;
                    }
                    that.loadData4.imageFiles.push(file);
                }
            }
            return isFlage;
        },
        //添加冻结或者解冻信息
        toStop2SubtimeOk: function (t) {
            var that = this;
            var status = that.loadData4.addStroData.status;
            if (!status && status != 0) {
                return $.alert("会员卡状态设置有误，请联系管理员")
            }
            if (status == 2) {
                if (t == 1) {
                    return $.alert("已经冻结的卡无需冻结");
                }
            }
            if (status == 3) {
                return $.alert("该卡已过期")
            }
            if (status == 4) {
                return $.alert("不能操作未开卡的会员卡")
            }
            if (status == 6) {
                return $.alert("不能操作历史卡")
            }
            if (!that.loadData4.addStroData.flag) {
                return $.alert("原因须填写");
            }
            var param = {
                cardId: that.cardId,
                CustomerCode: that.code,
                clientId: that.clientId,
                personnelId: $.cookie("id"),
                flag: that.loadData4.addStroData.flag,
                stopStatus: t,
            }
            var url = $.stringFormat('{0}/frCardOrderStop/toAddStopType', $.cookie('url'));
            $.ajax({
                type: 'POST',
                url: url,
                data: JSON.stringify(param),
                dataType: "json",
                contentType: "application/json;charset=utf-8",
                success: function (res) {
                    if (res.code == '200') {
                        that.loadData4.addStroData.flag = '';
                        that.getCardStopList();
                        that.initGetOptionsCard();
                    } else {
                        $.alert(res.msg)
                    }
                    Loading.prototype.hide();
                    that.randomNumberAdd = '';
                }
            });
        },
        //停卡冲销
        delStop: function (priceStatus, id) {
            var that = this;
            if (priceStatus == 2) {
                return;
            }
            var id = id;
            if (!id) {
                return $.alert("获取的数据有误");
            }
            if (that.randomNumber) {
                return $.alert("请勿太快重复点击");
            }
            //生成字符串
            that.randomNumber = Math.random().toString(36).substr(2);
            var url = $.stringFormat('{0}/frCardOrderStop/getUpdateStop', $.cookie('url'));
            $.get(url, {
                id: id,
                CustomerCode: that.code,
                cardId: that.cardId,
                clientId: that.clientId,
            }, function (res) {
                if (res.code == '200') {
                    that.getOptionsCardList();
                    that.getCardStopList();
                } else {
                    $.alert(res.msg);
                }
                that.randomNumber = '';
            })
        },
        //停卡终止
        termination: function (obj) {
            var that = this;
            var stopStatus = obj.stopStatus;
            if (stopStatus != 1) {
                return $.alert("不是停卡状态，无需终止");
            }
            var getT = getNowTime(); //获取终止时间
            var startTime = timeFormatDate(obj.startTime);  //获取开始停卡时间
            var time = getTwoBetweenDates(startTime, getT);    //获取实际停卡时长 = 现在时间- 开始时间
            if (time < 0) {
                time = 0;
            }
            var invalidTime = obj.invalidTime;               //获取失效时间
            var estStopTime = obj.estStopTime;               //获取预计停卡时长
            var timeDifference = time - estStopTime;
            that.loadData4.termination.stopTime = time;
            that.loadData4.termination.endTime = getT;
            that.loadData4.termination.totalPrice = obj.totalPrice;
            that.loadData4.termination.invalidTime = getNewData(invalidTime, timeDifference, true);
            that.loadData4.terminationId = obj.id;
            $('#termination').show();
        },
        //===============================================================麻烦的储值处理
        //获取储值列表
        getStorageCardList: function () {
            var that = this;
            var isFlag = that.getMembershipCard('storageCard');
            if (isFlag) {  return;  }
            var url = $.stringFormat('{0}/frCardOrderStorage/getStorageCardList', $.cookie('url'));
            $.get(url, {
                CustomerCode: that.code,
                cardId: that.cardId,
                clientId: that.clientId,
            }, function (res) {
                if (res.code == '200') {
                    console.log(res);
                    var orderPrice = res.data.orderPrice;
                    that.loadData6.orderPrice = that.getParemtDate(orderPrice, 0);
                    that.loadData6.storageCardList = res.data.list;
                } else {
                    $.alert(res.msg);
                }
            })
        },
        //添加储值信息
        storageSubVin:function(){
            var that = this;
            that.methName = 'storageSubmit';
            that.methPass = 'storagePass';
            that.payModelSub();
        },
        //验证支付信息是否
        payModelSub: function () {
            var that = this;
            if (!that.cardId) {
                return $.alert("请先选择要操作的会员卡");
            }
            if (!that.clientId) {
                return $.alert("会员ID获取有误，请刷新页面后重新操作");
            }
            if (that.payMentMoney.needPrice < 0) {
                return $.alert("应付款金额不能小于0");
            }
            if (!that.payMentMoney.price && that.payMentMoney.price == 0) {
                return $.alert("金额未设置");
            }
            var payMoney = that.getParemtDate(that.payModelMoney,0);
            if (that.payMentMoney.needPrice > payMoney) {
                return $.alert("支付金额不足");
            }
            var isFlag = true;
            if(that.payMentMoney.discountNum > 0 ){
                if(!that.ticketPrice.cardId){
                    return $.alert("未获取到抵扣的会员卡信息");
                }
                if(!that.ticketPrice.userPass){
                    return $.alert("请输入要抵扣的会员卡的凭证");
                }
                isFlag = false;
                that.getPassUseParemt('getStoragePassOk', '',false);
            }
            if(isFlag){
                that.getPassParemt(that.methName, that.methPass,that.perId);
            }
        },
        //验证完客户信息，继续执行验证员工信息
        getStoragePassOk:function(){
            var that = this;
            var methName =  that.methName;
            var methPass = that.methPass;
            if(!methName || !methPass ){
                return $.alert("未获取到方法名,及标签ID");
            }
            that.randomNumber = '';
            that.getPassParemt(methName, methPass,'');
        },
        //添加储值信息
        storageSubmit: function () {
            var that = this;
            var storage = {
                givePrice: that.getParemtDate(that.loadData6.givePrice,0),           //赠送金额
                storePrice: that.getParemtDate(that.payMentMoney.price,0),           // 储值金额
                storagePrice:that.getParemtDate(that.ticketPrice.storagePirc,0),    //储值抵扣
                ticketPrice: that.getParemtDate(that.ticketPrice.totalPrice,0),     //票卷抵扣
                totalPrice: that.getParemtDate(that.payMentMoney.needPrice,0),      //实际付款金额
                storageCardId: that.ticketPrice.cardId,                             //抵扣的会员卡id
                cardId: that.cardId,
                clientId: that.clientId,
                shopId: $.cookie("shopid"),
                personnelId: $.cookie("id"),
                CustomerCode: $.cookie("code"),
            }
            var data = {
                storage: JSON.stringify(storage),
                payModel: JSON.stringify(that.payModel)
            };
            var url = $.stringFormat('{0}/frCardOrderStorage/addStorageCard', $.cookie('url'));
            $.ajax({
                type: 'POST',
                url: url,
                data: JSON.stringify(data),
                dataType: "json",
                contentType: "application/json;charset=utf-8",
                success: function (res) {
                    if (res.code == '200') {
                        that.getStorageCardList();
                        that.loadData6.givePrice = 0;
                        $("#storagePass").val('');
                        //初始化数据
                        that.initPayModel();
                    } else {
                        $.alert(res.msg)
                    }
                    that.randomNumber = '';
                }
            });
        },
        //储值冲销
        delStorage: function (id, storageStatus) {
            var that = this;
            if (storageStatus == 2) {
                return;
            }
            var id = id;
            if (!id) {
                return $.alert("获取的数据有误");
            }
            if (that.randomNumber) {
                return $.alert("请勿太快重复点击");
            }
            //生成字符串
            that.randomNumber = Math.random().toString(36).substr(2);
            var url = $.stringFormat('{0}/frCardOrderStorage/toDelStorageCard', $.cookie('url'))
            $.get(url, {
                id: id,
                storageStatus: storageStatus,
                CustomerCode: that.code,
                cardId: that.cardId,
                clientId: that.clientId,
            }, function (res) {
                if (res.code == '200') {
                    that.initTicketPrice();
                    that.getStorageCardList();
                } else {
                    $.alert(res.msg);
                }
                that.randomNumber = '';
            })

        },
        //储值>退款
        Refund: function (totalPrice, id, surplusPrice) {
            var that = this;
            if (!id) {
                return;
            }
            var orderPrice = that.getParemtDate(that.loadData6.orderPrice, 0);
            var surplusPrice = that.getParemtDate(surplusPrice, 0);
            var tota = that.getParemtDate(totalPrice, 0);
            if (tota <= 0) {
                return $.alert("实际支付为小于0，无需退款");
            }
            if (surplusPrice > orderPrice) {
                return $.alert("剩余金额不足以支付退款");
            }
            that.loadData6.addRefund.totalPrice = tota;
            var clientUserName = window.parent.document.getElementById("clientUserName").innerText;
            that.loadData6.clientUserName = clientUserName;
            that.loadData6.refundStorageId = id;
            //退款授权，只能是当前门店人员才能授权
            that.shopId = $.cookie("shopid");
            that.getMarketShopPeople(that.shopId, that);
            $('#Refund').show();
        },
        //储值退款
        refundStorage: function () {
            var that = this;
            if (!that.loadData6.refundStorageId) {
                return $.alert("要退款的数据Id未获取成功");
            }
            if (!that.loadData6.addRefund.refundNote) {
                return $.alert("退款原因需填写");
            }
            if (!that.loadData6.addRefund.refundUserId) {
                return $.alert("退款需授权");
            }
            that.getPassParemt('toRefundStorage', 'refundStorage','');
        },
        //储值退款添加
        toRefundStorage: function () {
            var that = this;
            var data = that.loadData6.addRefund;
            data.id = that.loadData6.refundStorageId;
            data.CustomerCode = that.code;
            data.cardId = that.cardId;
            data.clientId = that.clientId;
            data.shopId = $.cookie("shopid");
            data.personnelId = $.cookie("id");
            var url = $.stringFormat('{0}/frCardOrderStorage/toRefundSubimt', $.cookie('url'));
            $.ajax({
                type: 'POST',
                url: url,
                data: JSON.stringify(data),
                dataType: "json",
                contentType: "application/json;charset=utf-8",
                success: function (res) {
                    if (res.code == '200') {
                        that.getStorageCardList();
                        that.loadData6.refundStorageId = '';
                        that.loadData6.addRefund.refundNote = '';
                        that.loadData6.addRefund.refundUserId = '';
                        that.initTicketPrice();
                        $("#refundStorage").val('');
                        $('.newCustomers_maskLayer').hide();
                    } else {
                        $.alert(res.msg);
                    }
                    that.randomNumber = '';
                }
            })
        },
        //储值>转让
        transfersStorage: function (id, surplusPrice) {
            var that = this;
            that.initGiveDatail();
            if (!id) {
                return;
            }
            // 剩余储值金额
            var orderPrice = that.getParemtDate(that.loadData6.orderPrice, 0);
            // 储值总金额
            var surplusPrice = that.getParemtDate(surplusPrice, 0);
            if (surplusPrice > orderPrice) {
                return $.alert("剩余储值金额，不足以转让");
            }
            that.loadData6.transfersMoney = surplusPrice;
            that.loadData6.transfersStorageId = id;
            $('#TransferOperation').show();
        },
        //储值转让
        toGiveUserStor: function () {
            var that = this;
            var transfersMoney = that.getParemtDate(that.loadData6.transfersMoney, 0);
            var orderPrice = that.getParemtDate(that.loadData6.orderPrice, 0);
            if (orderPrice < transfersMoney) {
                return $.alert("剩余储值金额不足");
            }
            if (!that.loadData6.transfersStorageId) {
                return $.alert("要转让的订单id未获取");
            }
            if (!that.catcherClientId) {
                return $.alert("只能转让现有客户，请重新选择");
            }
            if (!that.catcherClientCardId) {
                return $.alert("需选择转让客户的会员卡");
            }
            if (!that.givePhone) {
                return $.alert("转让人手机号未填写");
            }
            if (!that.giveName) {
                return $.alert("转让人姓名未填写");
            }
            // that.loadData6.transfersStorage.giveStorageId = that.loadData6.transfersStorageId;
            that.getPassUseParemt('toGiveUserStorOK', 'giveUserStor',true);
        },
        //储值转让
        toGiveUserStorOK: function () {
            var that = this;
            that.loadData6.transfersStorage.givePhone = that.givePhone;
            that.loadData6.transfersStorage.giveName = that.giveName;
            that.loadData6.transfersStorage.giveUserId = that.catcherClientId;
            that.loadData6.transfersStorage.giveCardId = that.catcherClientCardId;
            var data = that.loadData6.transfersStorage;
            data.id = that.loadData6.transfersStorageId;
            data.CustomerCode = that.code;
            data.cardId = that.cardId;
            data.clientId = that.clientId;
            data.shopId = $.cookie("shopid");
            data.personnelId = $.cookie("id");
            var url = $.stringFormat('{0}/frCardOrderStorage/toTransfersStorage', $.cookie('url'));
            $.ajax({
                type: 'POST',
                url: url,
                data: JSON.stringify(data),
                dataType: "json",
                contentType: "application/json;charset=utf-8",
                success: function (res) {
                    if (res.code == '200') {
                        that.getStorageCardList();
                        that.giveName = '';
                        that.givePhone = '';
                        that.catcherClientId = '';
                        that.catcherClientCardId = '';
                        that.initTicketPrice();
                        $("#giveUserStor").val('');
                        $('.newCustomers_maskLayer').hide();
                    } else {
                        $.alert(res.msg);
                    }
                    that.randomNumber = '';
                }
            })
        },
        //验证停卡终止信息
        toStopTermination: function () {
            var that = this;
            if (!that.loadData4.terminationId) {
                return $.alert("数据获取错误，请刷新后再次操作");
            }
            if (!that.loadData4.termination.personnelId) {
                return $.alert("操作人员ID获取错误，请刷新后再次操作");
            }
            if (!that.loadData4.termination.flag) {
                return $.alert("终止原因未获取");
            }
            that.getPassUseParemt('toStopTerminationOK', 'stopTermination',true);
        },
        // 验证通过后添加终止信息
        toStopTerminationOK: function () {
            var that = this;
            var data = that.loadData4.termination;
            data.clientId = that.clientId;
            data.cardId = that.cardId;
            // data.shopId = $.cookie("shopid");
            data.stopUserId = $.cookie("id");
            data.CustomerCode = that.code;
            data.id = that.loadData4.terminationId;

            var url = $.stringFormat('{0}/frCardOrderStop/toStopTerminationOK', $.cookie('url'));
            $.ajax({
                type: 'POST',
                url: url,
                data: JSON.stringify(data),
                dataType: "json",
                contentType: "application/json;charset=utf-8",
                success: function (res) {
                    if (res.code == '200') {
                        that.loadData6.refundStorageId = '';
                        that.loadData6.addRefund.refundNote = '';
                        that.loadData6.addRefund.refundUserId = '';
                        $("#refundStorage").val('');
                        $('.newCustomers_maskLayer').hide();
                        that.initGetOptionsCard();
                        that.getCardStopList();
                    } else {
                        $.alert(res.msg);
                    }
                    that.randomNumber = '';
                }
            })
        },
        //获取转让数据----根据会员卡
        getTransferCardList: function () {
            var that = this;
            var isFlag = that.getMembershipCard('cardtTransfer');
            if (isFlag) {
                return;
            }
            var url = $.stringFormat('{0}/frCardOrderTransfer/getTransferList', $.cookie('url'));
            $.get(url, {
                cardId: that.cardId,
                CustomerCode: that.code,
            }, function (res) {
                if (res.code == '200') {
                    console.log("转让卡================");
                    console.log(res);
                    that.loadData9.transferCardList = res.data.list;
                    that.loadData9.totalPrice = that.getParemtDate(res.data.totalPrice, 0);
                    that.loadData9.addTranferCar.totalPrice = that.getParemtDate(res.data.transferFee, 0);
                    that.loadData9.transferFee = that.getParemtDate(res.data.transferFee, 0);
                    //当转让金额大于0的时候，禁止设置
                    $('#tranferCardPrice').removeAttr("disabled");
                    if (that.loadData9.addTranferCar.totalPrice > 0) {
                        $('#tranferCardPrice').attr("disabled", "disabled");
                    }
                } else {
                    $.alert(res.msg)
                }
            })
        },
        //检索手机号
        isPoneAvailable: function () {
            var that = this;
            var isFlag = isPoneAvailable(that.loadData0.userPhone);
            if (!isFlag) {
                that.loadData0.userPhone = '';
            }
        },
        //检索会员卡号是否符合规则
        checkCardNum: function (id) {
            var cardNum = $("#" + id).val();
            var myreg = /^[0-9]*$/;
            if (!myreg.test(cardNum)) {
                $("#spa1").html("输入有误,请输入数字").css("color", "red").css("margin-left", "5px");
                return;
            }
            var url = $.stringFormat('{0}/frCardNum/checkCardNum', $.cookie('url'));
            $.get(url, {
                    cardNum: cardNum,
                },
                function (res) {
                    if (res.code == '200') {
                        $("#spa1").html("会员卡号符合规则").css("color", "green").css("margin-left", "5px");
                    } else {
                        $("#spa1").html(res.msg).css("color", "red").css("margin-left", "5px");
                    }
                }
            )
        },
        //检索协议号是否符合规则
        checkCardAgreement: function (id) {
            var agreement = $("#"+id).val();
            var myreg = /^[0-9]*$/;
            if (!myreg.test(agreement)) {
                $("#spa").html("输入有误，请输入数字").css("color", "red").css("margin-left", "5px");
                return;
            }
            var url = $.stringFormat('{0}/frAgreement/checkCardAgreement', $.cookie('url'));
            $.get(url, {
                    agreement: agreement,
                },
                function (res) {
                    if (res.code == '200') {
                        $("#spa").html("协议号符合规则").css("color", "green").css("margin-left", "5px");
                    } else {
                        $("#spa").html(res.msg).css("color", "red").css("margin-left", "5px");
                    }
                }
            )
        },
        //获取补余列表
        getComplementCardList: function () {
            var that = this;
            var isFlag = that.getMembershipCard('complementCard');
            that.loadData8.complementPrice = 0;
            that.loadData8.noPrice = 0;
            that.loadData8.orderSplitList = [];
            that.loadData8.num = 0;
            that.loadData8.depositTime = 0;
            that.loadData8.nextTime = '';
            that.loadData8.depositMon = 0;
            that.loadData8.orderSetIdList = [];
            that.loadData8.orderSetIdListTwo = [];
            that.loadData8.addComplement.totalPrice = 0;
            that.initPayModel();
            if (isFlag) {
                return;
            }
            that.maeketUserList = [];
            that.loadData8.addComplement.shopId = that.shopId;
            that.loadData8.nextTime = '';
            var clientId = that.clientId;
            var id = $.cookie('id');
            var url = $.stringFormat('{0}/frCardOrderComplement/getComplementList', $.cookie('url'));
            $.get(url, {
                clientId: clientId,
                CustomerCode: that.code,
                cardId: that.cardId
            }, function (res) {
                console.log("获取补余列表=============================");
                console.log(res);
                if (res.code == '200') {
                    that.loadData8.orderSplitList = [];
                    that.loadData8.orderSetIdList = [];
                    that.loadData8.complementCardList = res.data.list;
                    var orderSetList = res.data.frOrderSetList[0];
                    var orderMap = res.data.orderMap;
                    var payType = 0;
                    if (orderMap) {
                        that.loadData8.addComplement.shopId = orderMap.shopId;
                        payType = orderMap.payType;
                        that.loadData8.addComplement.splitSetId = orderMap.id;

                        //已付款金额
                        var sum = parseFloat(that.getParemtDate(orderMap.payPrice,0));
                        if (payType == 5) {  //支付方式是分期，选择分期的购卡价格
                            that.loadData8.addComplement.totalPrice = orderSetList.totalPrice;
                            that.loadData8.num = res.data.frOrderSetList.length;
                            that.loadData8.orderSplitList = res.data.frOrderSetList;
                            that.loadData8.addComplement.type = 2;
                            that.loadData8.depositTime = 0;
                            that.loadData8.nextTime =  timeFormatDate(orderSetList.splitDate);
                            //分期的按照购买金额计算
                            sum = parseFloat(that.getParemtDate(orderMap.totalPrice,0));
                        }
                        if (payType == 2) {  //定金支付的话，选择应付金额
                            that.loadData8.addComplement.type = 1;
                            that.loadData8.addComplement.totalPrice = orderMap.needPrice;
                            that.loadData8.num = 1;
                            that.loadData8.depositTime = orderMap.depositTime;
                            //下次补余期限 = 下单时间+补余时间
                            var createTime = timeFormatDate(orderMap.createTime);
                            var num = that.getParemtDate(orderMap.depositTime,0);
                            that.loadData8.nextTime =  getNewData(createTime,num,true);
                        }
                        //补余金额
                        var comPrice = that.getParemtDate(orderMap.complementPrice,0);
                        if (comPrice) {
                            sum += parseFloat(comPrice);
                        }
                        that.loadData8.complementPrice = parseFloat(sum).toFixed(2);
                        var noPric = 0;
                        var allMoney = that.loadData8.addComplement.totalPrice;
                        if (allMoney) {
                            noPric = parseFloat(allMoney) - that.loadData8.complementPrice;
                            that.loadData8.noPrice = parseFloat(noPric).toFixed(2);
                        }
                        if (payType == 2) {
                            var data = {
                                b_id: 'false',
                                splitOrder: 1,
                                splitNum: that.loadData8.noPrice,
                            }
                            that.loadData8.orderSplitList.push(data);
                        }
                        that.getMarketShopPeople(that.shopId, that);
                    }
                } else {
                    $.alert(res.msg)
                }
            })
        },
        // 支付补余前验证信息
        toCheckMoeny: function () {
            var that = this;
            if(!that.loadData8.addComplement.splitSetId){
                return $.alert("未获取到要补余的订单ID,操作失败")
            }
            if (!that.loadData8.addComplement.type) {
                return $.alert("请重新核对，该会员需要补余的信息");
            }
            if (!that.loadData8.depositMon) {
                return $.alert("请先选择需要补余的金额");
            }
            var tickP = that.getParemtDate(that.ticketPrice.totalPrice,0);
            var storageP =  that.getParemtDate(that.ticketPrice.storagePirc,0);
            var disP = that.getParemtDate(that.payMentMoney.discountNum,0);
            if(disP > (parseFloat(tickP) + parseFloat(storageP))){
                return $.alert("抵扣金额计算有误");
            }
            var payMoney = that.getParemtDate(that.payModelMoney,0);
            if (that.payMentMoney.needPrice > payMoney) {
                return $.alert("支付金额不足");
            }
            if (!that.loadData8.addComplement.shopId) {
                return $.alert("请先选择业绩归属门店")
            }
            that.getAllotSetTypeIsFlag(8);
        },
        toCheckMoenyOK: function () {
            var that = this;
            if (that.randomNumber) {
                return $.alert("请勿太快重复点击");
            }
            //生成字符串
            that.randomNumber = Math.random().toString(36).substr(2);
            var orderAllotSetList = that.loadData1.orderAllotSetList;
            var orderAllotSet = that.loadData1.orderAllotSetSav;
            var orderSetIdList = that.loadData8.orderSetIdList;
            that.loadData8.addComplement.allotSetType = that.loadData1.cardInfoMap.allotSetType;
            var payModel = that.payModel;
            var complement = that.loadData8.addComplement;
            complement.personnelId = $.cookie("id");
            complement.clientId = that.clientId;
            complement.cardId = that.cardId;
            complement.CustomerCode = that.code;
            complement.totalPrice = that.payMentMoney.price;           // 总价
            complement.ticketPrice = that.ticketPrice.totalPrice;    // 票卷抵扣金额
            complement.needPrice = that.payMentMoney.needPrice;      // 应付金额
            complement.noPrice = that.loadData8.noPrice;              // 总剩余欠款金额
            complement.retChange = that.payMentMoney.retChange;      // 找零
            complement.storagePrice = that.ticketPrice.storagePirc;   //储值抵扣
            complement.storageCardId = that.ticketPrice.cardId  ;  //抵扣的会员卡id
            console.log(complement);
            var data = {
                complement: JSON.stringify(complement),
                payModel: JSON.stringify(payModel),
                orderAllotSetList: JSON.stringify(orderAllotSetList),
                orderAllotSet: JSON.stringify(orderAllotSet),
                orderSetIdList: JSON.stringify(orderSetIdList),
            };
            var url = $.stringFormat('{0}/frCardOrderComplement/addComplementList', $.cookie('url'));
            $.ajax({
                type: 'POST',
                url: url,
                data: JSON.stringify(data),
                dataType: "json",
                contentType: "application/json;charset=utf-8",
                success: function (res) {
                    if (res.code == '200') {
                        that.initAllotSet();
                        that.getComplementCardList();
                        that.initPayModel();
                        that.loadData8.orderSplitList = [];
                    } else {
                        $.alert(res.msg)
                    }
                    that.randomNumber = '';
                }
            });
        },
        //计算补余金额(新)
        checkMoeny: function () {
            var that = this;
            var setList  =  that.loadData8.orderSetIdListTwo;
            var sum = parseFloat(0);
            if(setList  && setList.length > 0){
                that.loadData8.orderSetIdList = [];
                for(var i=0 ; i< setList.length ; i++){
                    var obj = setList[i];
                    var splitNum = 0;
                    if(obj){
                        var splitType = obj.splitType;
                        splitNum  = that.getParemtDate(obj.splitNum,0);
                        // 分期类型（1、比例；2、金额）
                        if(splitType == 1){
                            var splitPrice = obj.splitPrice;
                            if(splitPrice && splitNum){
                                var numMoney = (parseFloat(splitNum)/100)*splitPrice;
                                splitNum = numMoney;
                            }
                        }
                        var b_id = obj.b_id;
                        that.loadData8.orderSetIdList.push(b_id);
                    }
                    sum = parseFloat(sum) + parseFloat(splitNum);
                }
            }
            that.loadData8.depositMon = sum;
            that.payMentMoney.price = sum;
            that.isCommonMoney();
        },
        //补余冲销
        toDelComplement: function (id, type) {
            var that = this;
            var complementId = id;
            var orderState = type;
            //状态不是已付款状态禁止冲销
            if (orderState != '1') {
                return;
            }
            if (complementId) {
                if (that.randomNumber) {
                    return $.alert("请勿太快重复点击");
                }
                //生成字符串
                that.randomNumber = Math.random().toString(36).substr(2);
                var url = $.stringFormat('{0}/frCardOrderComplement/toUpdateOrderStart', $.cookie('url'));
                $.get(url, {
                    id: complementId,
                    personnelId: $.cookie("id"),
                    clientId: that.clientId,
                    cardId: that.cardId,
                    CustomerCode: that.code,
                }, function (res) {
                    if (res.code == '200') {
                        that.getComplementCardList();
                    } else {
                        $.alert(res.msg)
                    }
                    that.randomNumber = '';
                })
            }
        },
        //验证转让信息
        transferSub: function () {
            var that = this;
            if (!that.givePhone) {
                return $.alert("承接人手机未设置");
            }
            if (!that.giveName) {
                return $.alert("承接人姓名未设置");
            }
            if (!that.loadData9.addTranferCar.personnelId) {
                return $.alert("授权人需选择");
            }
            if(!that.cardId){
                return $.alert("请先选择要转让的会员卡");
            }
            //获取总金额  --- 后期改成动态获取
            var payModelPrice = that.getParemtDate(that.payModelMoney,0);
            var totalPrice = that.getParemtDate(that.loadData9.addTranferCar.totalPrice, 0);
            if (payModelPrice < totalPrice) {
                return $.alert("支付金额不足转让手续费");
            }
            that.getPassParemt('toTransferSub', 'transferUserPass','');
        },
        //添加转让卡信息
        toTransferSub: function () {
            var that = this;
            var tranfer = that.loadData9.addTranferCar;
            tranfer.catcherClientName = that.giveName;         //承接客户的姓名
            tranfer.catcherClientPhone = that.givePhone;       //承接客户的手机
            tranfer.catcherClientId = that.catcherClientId;   //承接的客户Id
            tranfer.transferClientId = that.clientId;          //会员ID
            tranfer.transferUserName = that.clientUserName;   //客户姓名
            tranfer.transferUserPhone = that.clientPhone;     //客户手机
            tranfer.transferPrice = that.payModelMoney;       //实际支付金额
            tranfer.CustomerCode = that.code;                   //客户代码
            tranfer.shopId = $.cookie("shopid");
            tranfer.cardId = that.cardId;
            var payModel = that.payModel;
            if (that.randomNumber) {
                return $.alert("请勿太快重复点击");
            }
            //生成字符串
            that.randomNumber = Math.random().toString(36).substr(2);
            var data = {
                orderTransfer: JSON.stringify(tranfer),
                payModel: JSON.stringify(payModel),
            }
            var url = $.stringFormat('{0}/frCardOrderTransfer/addTransferSub', $.cookie('url'));
            $.ajax({
                type: 'POST',
                url: url,
                data: JSON.stringify(data),
                dataType: "json",
                contentType: "application/json;charset=utf-8",
                success: function (res) {
                    if (res.code == '200') {
                       that.getOptionsCardList();
                       that.initPayModel();
                       that.initGiveDatail();
                       that.cardId = '';
                       that.loadData9.addTranferCar.flag = '';
                    } else {
                        $.alert(res.msg);
                    }
                    that.randomNumber = '';
                }
            })
        },
        //转让卡冲销
        delTransfer: function (id, transferStatus) {
            var that = this;
            if (transferStatus == 2) {
                return;
            }
            var id = id;
            if (!id) {
                return $.alert("获取的数据有误");
            }
            if (that.randomNumber) {
                return $.alert("请勿太快重复点击");
            }
            //生成字符串
            that.randomNumber = Math.random().toString(36).substr(2);
            var url = $.stringFormat('{0}/frCardOrderTransfer/toDelTransferCard', $.cookie('url'))
            var data={
                id: id,
                transferStatus: transferStatus,
                CustomerCode: that.code,
                cardId: that.cardId,
                clientId: that.clientId,
                shopId:$.cookie("shopid"),
                personnelId:$.cookie("id"),
            }
            console.log(data);
            $.ajax({
                type: 'POST',
                url: url,
                data:JSON.stringify(data),
                dataType: "json",
                contentType: "application/json;charset=utf-8",
                success: function (res) {
                    if (res.code == '200') {
                        that.getOptionsCardList();
                        that.initPayModel();
                        that.getTransferCardList();
                    } else {
                        $.alert(res.msg);
                    }
                    that.randomNumber = '';
                }
            })
        },
        //随机生成会员卡号
        addCardNumMet: function (label_Id) {
            var that = this;
            var url = $.stringFormat('{0}/frCardNum/addCardNums', $.cookie('url'));
            $.get(url, {
                    code: that.code,
                },
                function (res) {
                    if (res.code == '200') {
                        $("#" + label_Id).val(res.data.cardNo);
                        that.loadData1.cardInfoMap.cardNumId = res.data.cardNumId;
                        that.cardNumId = res.data.cardNumId;
                        that.loadData1.cardInfoMap.cardNo = res.data.cardNo;
                        that.loadData3.cardSupply3.cardNo = res.data.cardNo;
                        that.checkCardNum(label_Id);
                    } else {
                        $.alert(res.msg);
                    }
                })
        },
        //检索外部卡号
        checkExternalCard: function (strId) {
            var externalCard = $("#" + strId).val();
            if (!externalCard) {
                $("#" + strId).next().html('请填写外部卡号');
            } else if (externalCard.length > 32) {
                $("#" + strId).next().html("外部卡号过长").css("color", "red").css("margin-left", "5px");
            } else {
                $("#" + strId).next().html("")
            }
        },
        //选择会员卡弹窗
        products: function () {
            var that = this;
            //显示会员弹窗
            if(that.LiIndex != 3){
                $('#product').show();
            }
            //赋值当前门店名称
            var shop = $("#marketShop option:selected").text();
            var shopId = $("#marketShop option:selected").attr("value");
            $("#currentStore").html(shop);
            var type = $("#cardUser").val();
            that.loadData1.cardsList = [];
            //根据门店获取门店下的所有卡种
            var url = $.stringFormat('{0}/frCardType/getByShopIdList', $.cookie('url'));
            $.get(url, {
                    shopId: shopId,//门店id
                    type: type,//卡种类型
                    CustomerCode: that.code,
                },
                function (res) {
                    if (res.code == '200') {
                        that.loadData1.cardsList = res.data;
                    } else {
                        $.alert(res.msg)
                    }
                }
            )
        },
        //判断是否低于买卡最低价
        checkMinPrice: function () {
            var that = this;
            var minPrice1 = that.loadData1.cardInfoMap.totalPrice;//填写的值
            var minPrice = that.loadData1.addCardData.minPrice;
            that.loadData1.minPrice = false;
            if (minPrice1 != '') {
                if (minPrice1 < minPrice) {
                    $("#minPrice1").html("价格低于最低销售金额").css("color", "red").css("margin-left", "5px");
                } else {
                    $("#minPrice1").html("");
                    that.loadData1.minPrice = true;
                }
            }

        },
        //====把参数传入父级页面
        newPopup:function(){
           var that = this;
           var newPopup = window.parent.document.getElementById("newPopup");
           $(newPopup).show();
            //要传出去的参数
            var data = that.loadData1.cardInfoMap;
            data.LiIndex = that.LiIndex;
            data.clientId = that.clientId;
            data.code = that.code;
            data.cardId = that.cardId;
            data.cardOpening = that.loadData2.cardOpening;
            data.replacementCard = that.loadData2.replacementCard;
            console.log(data);
            window.parent["shipCard"] = data;
           $(newPopup).find("iframe").attr("src", 'newCardCustomers.html');
        },
        //根据弹窗返回的数据处理
        toChildSub:function(){
            var that = this;
            var mess = $("#mess").val();
            var isFlag = $("#toFlag").val();
            //如果失败了
            $.alert(mess);
            that.LI(that.LiIndex);
            return  that.getOptionsCardList();
        },
        //赋值卡种
        assignment: function (id, flag) {
            var that = this;
            that.loadData1.cardInfoMap.cardTypeId = id;
            $('.newCustomers_maskLayer').hide();
            var url = $.stringFormat('{0}/frCardType/getFrCardTypeDetails', $.cookie('url'));
            $.get(url, {
                    id: id
                },
                function (res) {
                    if (res.code == '200') {
                        //赋值
                        var cardTypeD = res.data;
                        that.loadData1.cardInfoMap.cardTypeName = cardTypeD.cardTypeName;
                        that.loadData1.cardInfoMap.cardFlag = flag;
                        that.loadData1.cardInfoMap.serviceLife = cardTypeD.serviceLife;//有效时长
                        that.loadData1.cardInfoMap.buyRightsNum = cardTypeD.totalNum;//购买权益
                        that.loadData1.cardInfoMap.salesPrice = cardTypeD.salesPrice; //售价
                        that.loadData1.cardInfoMap.haveRightsNum = cardTypeD.totalNum;
                        that.loadData1.cardInfoMap.type = cardTypeD.type;
                        if (cardTypeD.qtXxjgUpdate) {//判断是否有修改
                            that.loadData1.addCardData.minPrice = cardTypeD.qtZdxxjg;//前台可修改最低销售价格
                        } else {
                            that.loadData1.addCardData.minPrice = cardTypeD.salesPrice;
                        }
                        if (cardTypeD.qtZsqyUpdate) {//判断 前台是否可修改赠送权益
                            that.loadData1.addQtZdqy = cardTypeD.qtZdqy;//前台赠送权益
                        }
                    } else {
                        $.alert(res.msg)
                    }
                }
            )
        },
        //====================================续卡
        // 获取续卡列表
        toContinueCardList: function () {
            var that = this;
            that.getContinueCardList(1, 10, that.clientId);
        },
        //获取续卡列表
        getContinueCardList: function (page, limit, id) {
            var that = this;
            var isFlag = that.getMembershipCard('continueCard');
            if (isFlag) { return; }
            that.loadData2.cardOpening = 0;
            that.loadData2.replacementCard = 0;
            var url = $.stringFormat('{0}/frCardSupplyRecord/getContinueCardList', $.cookie('url'));
            Loading.prototype.show();
            if (!limit || !id) {
                id = page.id;
                limit = page.rows;
                page = page.page;
            }
            var _data = {
                pages: page,
                rows: limit,
                cardId: that.cardId,
                clientId: that.clientId,
                CustomerCode: that.code,
            }
            $.get(url, _data, function (res) {
                if (res.code == '200') {
                    console.log("获取续卡订单===============================");
                    console.log(res);
                    var connion = res.data;
                    that.loadData2.continuedCardList = connion.data.list;
                    that.PaginationName = "getContinueCardList";
                    that.getPaginationPage(connion);
                } else {
                    $.alert(res.msg)
                }
            })
        },
        toSubContinueCard:function(){
            var that = this;
            if(!that.cardId){
                return $.alert("请先选择要续卡的会员卡信息");
            }
            var orderState = $("#continueCard").find("option:selected").attr("orderState");
            // 订单状态（1、待付款；2、已付款；3、 已结款 ; 4、审核通过已结款；5、复核通过已结款；）
            if( orderState >0 ){
                return $.alert("此会员卡有未结款，不能操作续卡");
            }
            that.newPopup();
        },
        // 添加补卡前需先选择会员卡
        addCardSupply2Card: function () {
            var that = this;
            var isFlag = that.getMembershipCard('cardSupply2');
            if (isFlag) {
                return;
            }
            that.loadData5.addSupply1.oriCardNo = that.cardNo;
            that.loadData5.addSupply1.oriCardId = that.cardId;
            that.getMarketShopPeople(that.shopId, that);
        },
        getProducts:function(id,index){
            var that = this;
            //赋值当前门店名称
            var shopId = id;
            if(!shopId){ return ;}
            that.loadData3.shopId = id;
            var shopList = that.loadData3.shopList;
            //根据门店获取门店下的所有卡种
            var type = that.loadData3.type;
            var url = $.stringFormat('{0}/frCardType/getByShopIdList', $.cookie('url'));
            $.get(url, {
                    shopId: shopId,//门店id
                    type: type,//卡种类型
                    CustomerCode: that.code,
                },
                function (res) {
                    if (res.code == '200') {
                        shopList[index].cardsList = res.data;
                        var cardFlagList = [];
                        var cardF = '';
                        if(shopList[index].cardsList && shopList[index].cardsList.length >0){
                            for( var i = 0 ; i < shopList[index].cardsList.length ; i++){
                                var obj = shopList[index].cardsList[i];
                                var f = obj.cardFlag;
                                if(cardF.search(f) == -1){
                                    cardF = cardF+','+f;
                                    var data = {
                                        cardFlag :f,
                                        cardTypeId:obj.id
                                    }
                                    cardFlagList.push(data);
                                }
                            }
                        }
                        shopList[index].cardFlagList = cardFlagList;
                        that.loadData3.shopList = shopList;
                        that.$forceUpdate();
                    } else {
                        $.alert(res.msg)
                    }
                }
            )
        },
        //选中卡系列
        checkCardType:function(obj){
            var that = this;
            if(!obj){
                return ;
            }
            console.log(obj);
            that.loadData3.cardFlag = obj.cardFlag;
            that.loadData3.cardTypeName = obj.cardTypeName;
            that.loadData3.originalPrice = that.getParemtDate(obj.originalPrice,0);
            that.loadData3.salesPrice = that.getParemtDate(obj.salesPrice,0);
            that.loadData3.type = obj.type;
            that.loadData3.totalNum = that.getParemtDate(obj.totalNum,0);
            that.loadData3.cardTypeId = obj.id;
            that.getTransferFee();
        },
        // <!-- 转卡/停卡/冻结 切换-->
        transferCardBoxTops: function (t) {
            var that = this;
            if(that.LiIndex == 4){
                $('.transferCard_box_top a').eq(t).addClass('on').siblings().removeClass('on');
                that.transferCardBoxTop = t;
                that.loadData4.addStroData.flag = '';
                that.loadData4.imagesList = {};
                that.loadData4.imageFiles = [];
                that.getCardStopList();
            }
            if(that.LiIndex == 3){
                $('.transferCard-type-list li').eq(t).addClass('active').siblings().removeClass('active');
                that.transferCardBoxTop = t;
                that.getMarketShopList();
                that.loadData3.Lindex = t;
                if( 0 == that.loadData3.Lindex){
                   that.loadData3.context = '选择要转卡的类型卡';
                   return that.Load3();
                }
                that.loadData3.context = '选择要升级的类型卡';
                that.getCardTypeList();
                that.getCardSupplyList(that.loadData3.Lindex);
            }
        },
        //卡升级
        getCardTypeList:function(){
            var that = this;
            if(!that.cardId){ return $.alert("请先选择要升级的会员"); }
            var type = $("#cardSupply2").find("option:selected").attr("type");
            var shopName = $("#cardSupply2").find("option:selected").attr("shopName");
            var shopList = {
                shopName:shopName,
                id:that.shopId,
            };
            console.log(shopList);
            //根据门店获取门店下的所有卡种
            var url = $.stringFormat('{0}/frCardType/getByShopIdList', $.cookie('url'));
            $.get(url, {
                    shopId: that.shopId,//门店id
                    type: type,//卡种类型
                    CustomerCode: that.code,
                },
                function (res) {
                    if (res.code == '200') {
                        console.log("卡升级================================");
                        console.log(res);
                        shopList.cardsList = res.data;
                        var cardFlagList = [];
                        var cardF = '';
                        if(shopList.cardsList && shopList.cardsList.length >0){
                            for( var i = 0 ; i < shopList.cardsList.length ; i++){
                                var obj = shopList.cardsList;
                                var f = obj.cardFlag;
                                if(cardF.search(f) == -1){
                                    cardF = cardF+','+f;
                                    var data = {
                                        cardFlag :f,
                                        cardTypeId:obj.id
                                    }
                                    cardFlagList.push(data);
                                }
                            }
                        }
                        shopList.cardFlagList = cardFlagList;
                        that.loadData3.shopList = [];
                        that.loadData3.shopList.push(shopList);
                        that.$forceUpdate();
                    } else {
                        $.alert(res.msg)
                    }
                });
        },
        // 转卡/卡升级信息
        toCardSupply2: function () {
            var that = this;
            var isFlag = that.getMembershipCard('cardSupply2');
            if (isFlag) { return;  }
            if (that.status == 6) {
                return $.alert("不能操作历史卡")
            }
            var url = $.stringFormat("{0}/frCardOrderInfo/queryCardAndType", $.cookie('url'));
            $.get(url, {
                    cardId: that.cardId,
                    code:that.code,
                    clientId:that.clientId,
                },
                function (res) {
                    if (res.code == '200') {
                        console.log("转卡信息=====================");
                        console.log(res);
                        that.toCardSupplyInfo(res.data);
                        that.$forceUpdate();
                    } else {
                        $.alert(res.msg)
                    }
                }
            );
        },
        //获取会员卡订单信息计算转卡差价
        toCardSupplyInfo:function(obj){
            var that = this;
            that.loadData3.canChangeType = that.getParemtDate(obj.bcanChangeType,'');
            if(that.loadData3.Lindex != 1){
                if(that.loadData3.canChangeType == ''){
                    return $.alert("此卡种未设置是否能转卡");
                }
                if(!that.loadData3.canChangeType){
                    return $.alert("此卡种已设置不能转卡");
                }
            }
            var transferFee = that.getParemtDate(obj.btransferFee,0);
            if(transferFee > 0){
                //如果设置了转卡手续费，禁止修改
                $("#transferFee").attr("disabled", "disabled");
            }
            //获取剩余权益
            var orderRightsNum = $("#cardSupply2").find("option:selected").attr("orderRightsNum");
            //购买权益
            var buyRightsNum = that.getParemtDate(obj.buyRightsNum,0);
            //赠送权益
            var giveRightsNum = that.getParemtDate(obj.giveRightsNum,0);
            //应付金额（实际购买金额 = 计算后的售价）
            var needPrice = that.getParemtDate(obj.needPrice,0);
            // 剩余金额 = 购卡金额/(购买权益+赠送权益)  * 剩余权益
            var price = 0 ;
            //剩余权益大于0才有必要计算
            if(orderRightsNum > 0){
                price  = (parseFloat(needPrice)/(parseFloat(buyRightsNum)+parseFloat(giveRightsNum)))* parseFloat(orderRightsNum);
            }
            that.loadData3.cardSupply3.price = price;
            that.loadData3.transferFee = transferFee;
            that.loadData3.cardSupply3.transferFee = that.loadData3.transferFee;
            that.getTransferFee();
        },
        //计算金额
        getTransferFee:function(){
            var that = this;
            //手续费
            var transferFeeTwo = that.getParemtDate(that.loadData3.cardSupply3.transferFee,0);
            var regt = /^[0-9]+\.?[0-9]*?$/;
            if (!regt.test(transferFeeTwo)) {
                transferFeeTwo = that.getParemtDate(that.loadData3.transferFee,0);
            }
            var transferFee = parseFloat(transferFeeTwo).toFixed(2);
            that.loadData3.cardSupply3.transferFee = transferFee;
            //剩余金额
            var price = that.getParemtDate(that.loadData3.cardSupply3.price,0);
            //要转卡/升级的卡类型的售价
            var salesPrice = that.getParemtDate(that.loadData3.salesPrice,0);
            //计算差价 A转B  B卡的购卡金额 - A的剩余金额；
            var allMoney = parseFloat(salesPrice) - parseFloat(price) + parseFloat(transferFee);
            that.payMentMoney.price = parseFloat(allMoney);
            that.getCommonMoney();
        },
        // 转卡/卡升级结算
        addSupply34Ok: function () {
            var that = this;
            //验证下 其他数据
            that.loadData3.cardSupply3.cardNo = $("#cardSupplyId").val();
            if(!that.loadData3.cardSupply3.cardNo){
                return $.alert("卡号需填写");
            }
            if(!that.loadData3.cardSupply3.personnelId){
                return $.alert("授权员工信息未获取");
            }
            if(!that.loadData3.cardTypeId){
                return $.alert("要转卡的卡类型未选择");
            }
            if(that.loadData3.Lindex != 1){
                if(that.loadData3.canChangeType == ''){
                    return $.alert("此卡种未设置是否能转卡");
                }
                if(!that.loadData3.canChangeType){
                    return $.alert("此卡种已设置不能转卡");
                }
            }
            that.methName = 'supply34OK';
            that.methPass = 'storagePass';
            that.getPassUseParemt('supply34OK', 'supplyClientPass',true);
        },
        // 转卡/卡升级结算
        supply34OK:function(){
            var that = this;
            that.methName = 'supply34OKTwo';
            that.methPass = 'supplyPersonnelPass';
            that.perId = that.loadData3.cardSupply3.personnelId;
            that.payModelSub();
        },
        // 转卡/卡升级结算
        supply34OKTwo:function(){
            var that = this;
            //避免多次点击
            if (that.randomNumber) {
                return $.alert("请勿太快重复点击");
            }
            //生成字符串
            that.randomNumber = Math.random().toString(36).substr(2);
            var LindType = that.loadData3.Lindex;
            //默认是转卡操作，
            var type = 3;
            if(LindType == 1){
                type = 4;
            }
            var transferCard = {
                oriCardId: that.cardId,           //原先会员卡ID
                oriCardNo: that.cardNo,           //原先会员卡号
                newCardNo: that.loadData3.cardSupply3.cardNo, //新会员卡号
                newCardNumId: that.cardNumId,         //新会员卡的规则ID
                payPrice:that.getParemtDate(that.payMentMoney.needPrice),  //应付金额（差价）
                type:type,                           //类型（3、转卡；4、卡升级；）
                clientId: that.clientId,
                shopId: $.cookie("shopid"),
                personnelId: that.loadData3.cardSupply3.personnelId,
                CustomerCode: $.cookie("code"),
                cardName:that.loadData3.cardFlag,
                cardFlag:that.loadData3.cardTypeName
            }
            var data = {
                transferCard: JSON.stringify(transferCard),
                payModel: JSON.stringify(that.payModel),
                payMentMoney: JSON.stringify(that.payMentMoney),
                newCardTypeId:that.loadData3.cardTypeId,
                shopId:that.loadData3.shopId,
            };
            var url = $.stringFormat('{0}/frCardSupplyRecord/addTransferCard', $.cookie('url'));
            $.ajax({
                type: 'POST',
                url: url,
                data: JSON.stringify(data),
                dataType: "json",
                contentType: "application/json;charset=utf-8",
                success: function (res) {
                    if (res.code == '200') {
                        that.getCardSupplyList(that.loadData3.Lindex);
                        that.loadData3.cardSupply3.personnelId ='';
                        $("#supplyClientPass").val();
                        $("#supplyPersonnelPass").val();
                        //初始化数据
                        that.initPayModel();
                    } else {
                        $.alert(res.msg)
                    }
                    that.randomNumber = '';
                }
            });
        },
        getCardSupplyList:function(t){
            var that = this;
            if (!that.clientId) {return ;}
            var type = 3;
            if(t == 1){
                type = 4;
            }
            var url = $.stringFormat("{0}/frCardSupplyRecord/getCarDsupplyRecordList", $.cookie('url'));
            $.get(url, {
                CustomerCode: that.code,
                clientId: that.clientId,
                type:type
            }, function (res) {
                if (res.code == '200') {
                    console.log(res);
                    that.loadData3.supplyList = res.data.list;
                } else {
                    $.alert(res.msg)
                }
            });

        },
        getReturnStorage:function(){
            var that = this;
            if(!that.cardId){
               return $.alert("请选择要操作的会员卡信息");
            }
            //查询可退的总储值金额
            var url = $.stringFormat('{0}/frCardOrderStorage/getAllBlackStorage', $.cookie('url'));
            $.get(url, {
                    cardId: that.cardId,
                    CustomerCode:that.code,
                    clientId:that.clientId,
                },
                function (res) {
                    if (res.code == '200') {
                        console.log("获取可退的储值金额=====================");
                        console.log(res);
                        that.loadData6.addRefund.totalPrice = res.data;
                        var clientUserName = window.parent.document.getElementById("clientUserName").innerText;
                        that.loadData6.clientUserName = clientUserName;
                        that.loadData6.refundStorageId = 'all';
                        //退款授权，只能是当前门店人员才能授权
                        that.shopId = $.cookie("shopid");
                        that.getMarketShopPeople(that.shopId, that);
                        $('#Refund').show();
                    } else {
                        $.alert(res.msg)
                    }
                }
            );
        },
        //第一个是确认要调用的方法名称，第二个是取消要调用的方法名称
        commonConfirm:function(methName, methNameTwo,mess){
            var that = this;
            $.confirm({
                title: '确认',
                content: mess,
                icon: 'glyphicon glyphicon-question-sign',
                buttons: {
                    ok: {
                        text: '确认',
                        btnClass: 'btn-primary',
                        action: function() {
                            return that[methName]();
                        }
                    },
                    cancel: {
                        text: '取消',
                        btnClass: 'btn-primary',
                        action: function() {
                            if(methNameTwo){
                                return that[methNameTwo]();
                            }
                        }
                    }
                }
            })
        },
        //业绩分配的删除
        deleteFamily:function(t){
            var that = this;
            if(!t && t != 0){
                return ;
            }
            if(that.loadData1.orderAllotSetListOne == null || that.loadData1.orderAllotSetListOne.length <=0){
                return ;
            }
            var list =  that.loadData1.orderAllotSetListOne;
            that.loadData1.orderAllotSetListOne = [];
            for(var i = 0; i< list.length ;i++){
                if(t != i){
                    that.loadData1.orderAllotSetListOne.push(list[i]);
                }
            }
        },

    },
});