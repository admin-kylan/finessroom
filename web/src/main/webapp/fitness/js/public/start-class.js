/**
 * by Kylan
 * @author
 * @date 2019-01-12
 */
Vue.component('edu-start-class-children', {//模版挂载的标签名
    template: '#edu-start-class-id',//id对应子组件的ID
    data() {
        return {
            eduId: '',
            eduItem: {},
            courseItem: {}, //课程
            reserveClientInfo: {}, //保存预约的用户信息
            clientInfoList: [], //查询的列表
            eduConfigCardObject: [], //设置的会员卡配置
            clientInfo: {

            },//页面显示的用户信息，用于查询后的结果
            clientInfoCopy: {},
            eduItemMinute: 0,
            searchInput: '', //输入框的值  18650808999
            memberList: [],
            reserveNum: 0, //约课人数
            searchCardViewStatus: false,
            trainingPlanViewStatus: false,
            eduPlanList: [], //训练计划数组
            beginDate: '',//2019/01/18 15:08
            endDate: '',//2019/01/19 18:11
            searchInput2: '',

        }
    },
    filters: {
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
            if(!val){
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
        }

    },
    computed: {},
    watch: {},
    created() {
       // Event.$off(EDU_CONSTANT.listenerEduItem)
        Event.$on(EDU_CONSTANT.listenerEduItem, (eduId, eduItem)=>{

            //加载会员卡配置  eduConfigCardObject
            axiosGetParams(EDUCATION_URL.findSettCourseEdu, {eduId: eduId}, (res)=>{
                this.eduId = eduId;
                this.eduItem = eduItem;
                this.eduConfigCardObject = res;
                //计算分钟数
                //   let less = this.eduItem.endDatePlan - this.eduItem.beginDatePlan;
                //  this.eduItemMinute = parseInt(less/ 1000 / 60 / 60);
                //刷新列表
                this.fetchMemberList();
                this.load();
            },()=>{
                $.alert('拉取信息出错，请重试！！')
            })

        });


    },
    mounted() {
        let that = this;
        jeDate('#start-begin-date',{
            isinitVal: true,
            festival: true,
            format: 'YYYY/MM/DD hh:mm',
            donefun(obj, val){
                that.beginDate = obj.val;
            }
        })
        jeDate('#start-end-date',{
            isinitVal: true,
            festival: true,
            format: 'YYYY/MM/DD hh:mm',
            donefun(obj, val){
                that.endDate = obj.val;
            }
        })
        // this.$nextTick(()=>{
        //
        // })
    },
    updated: function () {

    },
    methods: {
        load(){
            //加载训练计划
            axiosGetParams(EDUCATION_URL.findShowTrainingPlan, {courseId: this.eduItem.courseId}, (res) => {
                this.eduPlanList = res
            },(res)=>{

            });
            //查询课程
            axiosGetParams(EDUCATION_URL.findCourseById, {courseId: this.eduItem.courseId}, (res) => {
                this.courseItem = res
            },(res)=>{

            });
        },
        searchFunc(){
            this.fetchMemberList();
        },
        fetchMemberList(){
            axiosGetParams(EDUCATION_URL.findMemberOrderListByEduId, {eduId: this.eduId, searchInput: this.searchInput2}, (res) => {
                this.memberList = res;
                this.reserveNum = this.memberList.length;
            });
        },
        //查看训练计划
        showTrainingPlan(){
            this.trainingPlanViewStatus = true;
        },
        //查看会员信息
        searchCard(){
            let param = {
                searchInput: this.searchInput,
                shopId: $.cookie('shopid'),
                configStartClass: this.eduItem.configStartClass,
                code: $.cookie('code')
            };
            //判断是否输入
            if(!this.searchInput){
                return false;
            }

            //查询卡号
            axiosGetParams(EDUCATION_URL.findMemberCardByInput, param, (res) => {
                this.searchCardViewStatus = true;
                this.clientInfoList = res;
            });
        },
        //冲销
        cancelMemberOrder(id, eduClassSalesNum){
            let param = {id: id,eduClassSalesNum: eduClassSalesNum};
            //冲销用的
            let buyEduDetail = {
                cardId: '', //会员卡ID
                clientId: '', //客户ID
                cardType: '1', //卡种类型（1：时间卡 2：小时卡 3：次卡 4：储值卡 5：充值卡 6：折扣卡）
                orderStatus: '1', //支付状态（0：支出 1：收入）
                orderPrice: 0,//金额（注意正负 支出就是  -0.00）
                orderRightsNum: 0, //权益 注意正负
                shopId: this.eduItem.shopId, //基础表门店ID
                personnelId: $.cookie('id'), //操作员工id
                flag: '权益', //消费内容
                type: 1, //支付类型（1:权益 2:储值 3:其他消费）
                CustomerCode: $.cookie('code'),
                status: 0,//财务审核 （0，待审核1，已审核，2，审核不通过）
                auditStatus: 0,//相关复核 （0，待审核1，已审核，2，审核不通过）
                orderType: 1,//订单类型（1、新购；2、续卡；3、转卡；4、补卡（暂无）；5、储值；6、补余；7、退卡, 8、停卡，9，转让，10，卡升级【若有其他的，类型数字请往后添加】）
                orderAmt: 0, //剩余权益\剩余储值金额
                createUserId: $.cookie('id'),
                createUserName: $.cookie('name'),
                createTime: new Date(),
            };
            param.buyEduDetail = buyEduDetail;
            let message = confirm("确认冲销该条记录？")
            if(message == true){
                axiosPostParams(EDUCATION_URL.cancelMemberOrder, param, ()=>{
                    this.fetchMemberList();
                    $.alert("冲销成功")
                },(res)=>{
                    $.alert(res.msg)
                })
            }

        },
        //付款
        payBill(val){
            if(!this.courseItem){
                $.alert("数据正在获取，请稍等")
                return false;
            }
            //判断人数是否够
            if(this.eduItem.reserveTotalNum == this.eduItem.eduCurrentCount){
                $.alert('预约失败，当前课程预约人数已满！');
            }
            //判断是否课程付款还是权益付款
            //判断是否选择 会员卡结算
            //configCardSettle configCourseSettle
            let param = {};
            let endAmt = parseInt(val.totalAmt?val.totalAmt:0);
            let classSalesNum = parseInt(this.eduItem.classSalesNum?this.eduItem.classSalesNum:0);
            let cardObject = {
             //   clientInfoId: val.clientId,
                classNum: classSalesNum,
                beginDateReal: new Date(),
                useUserName: val.clientName,
                totalTime: this.courseItem.time,
                createDate: new Date(),
                price: 0, //待定 消费金额
                cardType: val.cardType,
                cardName: val.cardTypeName,
                useBenefit: val.haveRightsNum,
                remainBenefit: val.totalAmt,
                createName: $.cookie('name'),
                saleUserName: val.salesName,
                billNumber: new Date().getTime() + "",
                educationId: this.eduId,
                status: 1,
                invalidDate: val.invalidDate,
                memberCardId: val.cardId,
                memberCardNo: val.cardNo,
            };
            //当前预约的用户
            let reserveClientInfo = {
                educationId: this.eduId,
                memberId: val.clientId,
                memberName: val.clientName,
                memberType: val.memberType,
                mobile: val.mobile,
                reserveStatus: 1,
                sex: val.sex,
            }
            this.reserveClientInfo = reserveClientInfo;
            console.log(cardObject)
            console.log(reserveClientInfo)

            //卡权益
            if(this.eduItem.configCardSettle == true && this.eduItem.configCourseSettle == false){
                this.payTypeOne(endAmt, classSalesNum, val, cardObject);
            }
            //课程
            if(this.eduItem.configCourseSettle == true && this.eduItem.configCardSettle == false){
                this.payTypeTwo(endAmt, classSalesNum, val, cardObject);
            }
            //如果会员结算和课程结算都没选择
            if(this.eduItem.configCourseSettle == false && this.eduItem.configCardSettle == false){
                let param1 = {
                    cardObject: cardObject,
                    reserveClientInfo: this.reserveClientInfo,
                };
                axiosPostParams(EDUCATION_URL.saveEduMemberOrder, param1, ()=>{
                    this.saveSuccess(val);
                },(res)=>{
                    $.alert(res.msg)
                })
            }
            //如果会员结算和课程结算都选择 判断哪个过期时间快
            if(this.eduItem.configCourseSettle == true && this.eduItem.configCardSettle == true){
                let courseList = [];
                param = {
                    courseId: this.eduItem.courseId,
                    clientId: val.clientId,
                    cardId: val.cardId,
                    code: $.cookie('code'),
                    num: classSalesNum,
                    shopId: $.cookie('shopid'),
                };
                axiosGetParams(EDUCATION_URL.findCourseByClientId, param, (res)=> {
                    courseList = res;
                    let projectOrder = courseList[0];
                    //1
                    //判断当前权益是否够
                    if(endAmt - classSalesNum < 0){
                        this.payTypeTwo(endAmt, classSalesNum, val, cardObject);
                        return false;
                    }

                    //2 是否课程
                    if (!projectOrder) {
                        this.payTypeOne(endAmt, classSalesNum, val, cardObject);
                        return false;
                    }

                    //3 课程数是否够
                    if(parseInt(projectOrder.orderAllCount) - classSalesNum < 0){
                        this.payTypeOne(endAmt, classSalesNum, val, cardObject);
                        return false;
                    }


                });
            }
        },
        payTypeOne(endAmt, classSalesNum, val, cardObject){

            //判断当前权益是否够
            if(endAmt - classSalesNum < 0){
                $.alert('预约失败，当前会员卡权益不够！')
                return false;
            }
            this.eduItem.amtNum = endAmt - classSalesNum;
            //去修改权益
            let param = {
                courseNum: classSalesNum, //数量
                eduId: this.eduId,
                cardId: val.cardId,
                clientId: val.clientId,
                amtNum: endAmt - classSalesNum,
                shopId: $.cookie('shopid'),
            };
            //判断当前课程是否被设置，如果没有，直接抛出不可预约；
            let courseObject = this.eduConfigCardObject.find((item)=>{
                return item.projectId == this.eduItem.courseId;
            });
            if(!courseObject){
                courseObject = this.eduConfigCardObject[0];
                if(!courseObject){
                    courseObject = this.eduConfigCardObject[1];
                    if(!courseObject){
                        //排课的时候，没有对该课程排课，就不可预约
                        $.alert("预约失败，不可预约该课程")
                        return false;
                    }
                    //排课的时候，没有对该课程排课，就不可预约
                    $.alert("预约失败，不可预约该课程")
                    return false;
                }
                //排课的时候，没有对该课程排课，就不可预约
                $.alert("预约失败，不可预约该课程")
                return false;
            }

            //可以预约
            //如果设置为0，则可不用付款
            if(parseFloat(courseObject.price) > 0){
                //生成付款页面
                this.eduItem.courseMemberPrice = parseFloat(courseObject.price);
                Event.$emit(EDU_CONSTANT.listenerChooseClientInfo, true, val, this.eduItem)
                Event.$off(EDU_CONSTANT.listenerChooseClientInfoBack)
                Event.$on(EDU_CONSTANT.listenerChooseClientInfoBack, (back)=>{
                    //该用户付款的钱
                    cardObject.price = parseFloat(courseObject.price);
                    cardObject.remainBenefit = endAmt - classSalesNum;
                    //计算剩余权益
                    back.cardObject = cardObject;
                    this.saveEduOrder(back, val);
                })
            }else{
                let back = {};
                cardObject.price = 0;
                back.cardObject = cardObject;
                this.saveEduOrder(back, val);
            }

        },
        payTypeTwo(endAmt, classSalesNum, val, cardObject){
            let courseList = [];
            let param = {
                courseId: this.eduItem.courseId,
                clientId: val.clientId,
                cardId: val.cardId,
                code: $.cookie('code'),
                num: classSalesNum,
                shopId: $.cookie('shopid'),
            }
            axiosGetParams(EDUCATION_URL.findCourseByClientId, param, (res)=>{
                courseList = res;
                let projectOrder = courseList[0];
                if(!projectOrder){
                    $.alert("预约失败，没有购买该课程")
                    return false;
                }
                //判断课程数是否够
                if(parseInt(projectOrder.orderAllCount) - classSalesNum < 0){
                    $.alert("预约失败，课程数不够")
                    return false;
                }
                //存在可以扣课程的数据
                let param1 = {
                    endAmt: parseInt(projectOrder.orderAllCount) - classSalesNum, //消费多少的课程
                    id: projectOrder['projectOrderId'],
                    cardObject: cardObject,
                    reserveClientInfo: this.reserveClientInfo
                };
                axiosPostParams(EDUCATION_URL.updateProjectOrder, param1, ()=>{
                    this.saveSuccess(val);
                },(res)=>{
                    $.alert(res.msg)
                })
            })
        },
        saveSuccess(val){
            $.alert("预约成功")
            this.clientInfo = val
            this.searchCardViewStatus = false;
            //重新刷新列表
            this.fetchMemberList();
        },
        //保存一个预约订单
        saveEduOrder(back, val){
            back.reserveClientInfo = this.reserveClientInfo;
            axiosPostParams(EDUCATION_URL.updateCardDetail, back, (res)=>{
                this.saveSuccess(val);
            },(res)=>{
                $.alert(res.msg)
            });
        },
        //开始课程
        beginEduFunc(){
            let that = this;

            if(!this.beginDate){
                $.alert('请输入开始时间')
                return false;
            }
            if(!this.endDate){
                $.alert('请输入结束时间')
                return false;
            }
            if(new Date(this.beginDate).getTime() - new Date(this.endDate).getTime() > 0){
                $.alert('开始时间勿大于结束时间');
                return false;
            }

            $.confirm({
                title: "确认",
                content: "确认开始教学课程",
                type: 'green',
                buttons:{
                    ok:{
                        text: '确认',
                        action(){

                            let classOrder = {
                                educationId: that.eduId,
                                totalTime: that.courseItem.time,
                                totalGain: '', //总收益，后台去计算
                                totalNum: '',//总人数， 后台计算
                                finishNum: '', //完成上课人数
                                undoneNum: '', //失约人数
                                createUser: $.cookie('name'),
                                createUserId: $.cookie('id'),
                                beginDateReal: new Date(that.beginDate),
                                endDateReal: new Date(that.endDate),
                            }


                            axiosPostParams(EDUCATION_URL.startEduClass,{classOrder: classOrder}, function (res) {
                               // $.alert('success')
                                window.location.reload();
                            })

                        }
                    },
                    cancel:{
                        text: '取消'
                    }
                }
            })
        }
    },
});