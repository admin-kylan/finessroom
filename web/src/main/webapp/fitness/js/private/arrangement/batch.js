/**
 * by Kylan
 * @author
 * @date 2019-01-23
 */
Vue.component('edu-batch-arrangement-children', {//模版挂载的标签名
    template: '#edu-batch-arrangement-id',//id对应子组件的ID
    data() {
        return {
            shopName: '',
            shopId: $.cookie('shopid'),
            roomList: [],
            selectRoomId: '',
            selectRoomName: '',
            selectRoomItem: {},
            coachList: [],
            selectCoachId: '',
            selectCoachItem: {},
            courseList: [],
            selectCourseId: '',
            selectCourseItem: {},//courseTime
            courseTime: 0, //课程时间
          //  executeDate: '', //上课时间
            executeBeginDate: '',
            executeEndDate: '',
            openReserveDate: '', //预约时间 最后确定的时间
            openReserveDateSelect: '', //定时预约时间
            //--
            selectOpenReserveStatus: '1', //预约状态
            reserveHoursBegin: '',//开始前几分钟预约
            //--
            configChangeEdu: '', //预约设置修改
            configStartClass: '1',//未经允许是否可以上课
            configSelectCoach: '1',//选择教练
            configConfirmEdu: '1', //是否需要确认
            configBeginClass: '', //入场
            //-- 课程结算
            isOnlinePay: false,//团会员扣费
            isMemberCardPay: false, //会员卡结算
            isCoursePay: false, //课程结算
            price: '', //每节课多少元
            isLimitReserveClass: '1', //限制约课
            //限制对象
            p1: false,
            p2: false,
            p3: false,
            p4: false,
            p5: false,
            p6: false,
            isSave: false,
            //周期设置
            w1:{
                status: false,
                begin: '',
                end: '',
            },
            w2:{
                status: false,
                begin: '',
                end: '',
            },
            w3:{
                status: false,
                begin: '',
                end: '',
            },
            w4:{
                status: false,
                begin: '',
                end: '',
            },
            w5:{
                status: false,
                begin: '',
                end: '',
            },
            w6:{
                status: false,
                begin: '',
                end: '',
            },
            w7:{
                status: false,
                begin: '',
                end: '',
            },
            //课程数组
            resultCourseList: [],
            selectCourseStatus: false,
            //私教
            moreTeach: '1',
            eduMemberNum: '1',

        }
    },
    filters: {
        toCalcNum(val){
            if(!val){
                return 0;
            }
            return val;
        },
        toMoney(val){
            if(!val){
                return '0.00';
            }
            return parseFloat(val).toFixed(2);
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
            return timeFormatDate(val, true)
        },
        toDateyyyyMMdd(val){
            return timeFormatDate(val)
        },
        toSex(val){
            if(val == '1'){
                return "女"
            }
            return '男'
        }
    },
    computed: {},
    watch: {
        selectCourseStatus(val){
            $.each(this.resultCourseList, (i, item)=>{
                item.groupList.status = val;
                item.privateList.status = val;
                item.status = val;
                $.each(this.resultCourseList, (i, d)=>{
                    $.each(d.groupList.courseList, (i, json)=>{
                        json.status = val
                    })
                    $.each(d.privateList.courseList, (i, json)=>{
                        json.status = val
                    })
                })
            })
        },
        selectCoachId(val){
            let a = this.coachList.find((item)=>{
                return item.coachId == val;
            });
            if(!a){
                a = {};
            }
            this.selectCoachItem = a;

        },
        selectCourseId(val){
            let a = this.courseList.find((item)=>{
                return item.courseId == val;
            });
            if(!a){
                a = {};
            }
            this.selectCourseItem = a;
            this.courseTime = this.selectCourseItem.courseTime? this.selectCourseItem.courseTime: 0;
        },
        selectRoomId(val){
            let a = this.roomList.find((item)=>{
                return item.roomId == val;
            });
            if(!a){
                a = {};
            }
            this.selectRoomItem = a;
        },
        //时间更改
        courseTime(){
            if(this.w1.begin){
                let t = '2019-01-01 ' + this.w1.begin + ":00"
                //courseTime
                let date = new Date(t);
                date.setMinutes(date.getMinutes() + parseInt(this.courseTime));
                this.w1.end = this.toDateHHmm(date);
            }
            if(this.w2.begin){
                let t = '2019-01-01 ' + this.w2.begin + ":00"
                //courseTime
                let date = new Date(t);
                date.setMinutes(date.getMinutes() + parseInt(this.courseTime));
                this.w2.end = this.toDateHHmm(date);
            }
            if(this.w3.begin){
                let t = '2019-01-01 ' + this.w3.begin + ":00"
                //courseTime
                let date = new Date(t);
                date.setMinutes(date.getMinutes() + parseInt(this.courseTime));
                this.w3.end = this.toDateHHmm(date);
            }
            if(this.w4.begin){
                let t = '2019-01-01 ' + this.w4.begin + ":00"
                //courseTime
                let date = new Date(t);
                date.setMinutes(date.getMinutes() + parseInt(this.courseTime));
                this.w4.end = this.toDateHHmm(date);
            }
            if(this.w5.begin){
                let t = '2019-01-01 ' + this.w5.begin + ":00"
                //courseTime
                let date = new Date(t);
                date.setMinutes(date.getMinutes() + parseInt(this.courseTime));
                this.w5.end = this.toDateHHmm(date);
            }
            if(this.w6.begin){
                let t = '2019-01-01 ' + this.w6.begin + ":00"
                //courseTime
                let date = new Date(t);
                date.setMinutes(date.getMinutes() + parseInt(this.courseTime));
                this.w6.end = this.toDateHHmm(date);
            }
            if(this.w7.begin){
                let t = '2019-01-01 ' + this.w7.begin + ":00"
                //courseTime
                let date = new Date(t);
                date.setMinutes(date.getMinutes() + parseInt(this.courseTime));
                this.w7.end = this.toDateHHmm(date);
            }
        },
        'w1.begin'(val){
            let t = '2019-01-01 ' + val + ":00"
            //courseTime
            let date = new Date(t);
            date.setMinutes(date.getMinutes() + parseInt(this.courseTime));
            this.w1.end = this.toDateHHmm(date);
        },
        'w2.begin'(val){
            let t = '2019-01-01 ' + val + ":00"
            //courseTime
            let date = new Date(t);
            date.setMinutes(date.getMinutes() + parseInt(this.courseTime));
            this.w2.end = this.toDateHHmm(date);
        },
        'w3.begin'(val){
            let t = '2019-01-01 ' + val + ":00"
            //courseTime
            let date = new Date(t);
            date.setMinutes(date.getMinutes() + parseInt(this.courseTime));
            this.w3.end = this.toDateHHmm(date);
        },
        'w4.begin'(val){
            let t = '2019-01-01 ' + val + ":00"
            //courseTime
            let date = new Date(t);
            date.setMinutes(date.getMinutes() + parseInt(this.courseTime));
            this.w4.end = this.toDateHHmm(date);
        },
        'w5.begin'(val){
            let t = '2019-01-01 ' + val + ":00"
            //courseTime
            let date = new Date(t);
            date.setMinutes(date.getMinutes() + parseInt(this.courseTime));
            this.w5.end = this.toDateHHmm(date);
        },
        'w6.begin'(val){
            let t = '2019-01-01 ' + val + ":00"
            //courseTime
            let date = new Date(t);
            date.setMinutes(date.getMinutes() + parseInt(this.courseTime));
            this.w6.end = this.toDateHHmm(date);
        },
        'w7.begin'(val){
            let t = '2019-01-01 ' + val + ":00"
            //courseTime
            let date = new Date(t);
            date.setMinutes(date.getMinutes() + parseInt(this.courseTime));
            this.w7.end = this.toDateHHmm(date);
        },

    },
    created() {
        this.load();
    },
    mounted() {

        let that = this;
        //
        jeDate('#batch-w1-begin-date',{
            // isinitVal: true,
            festival: true,
            format: 'hh:mm',
            donefun(obj, val){
                that.w1.begin = obj.val;
            }
        });
        //
        jeDate('#batch-w2-begin-date',{
            // isinitVal: true,
            festival: true,
            format: 'hh:mm',
            donefun(obj, val){
                that.w2.begin = obj.val;
            }
        });
        //
        jeDate('#batch-w3-begin-date',{
            // isinitVal: true,
            festival: true,
            format: 'hh:mm',
            donefun(obj, val){
                that.w3.begin = obj.val;
            }
        });
        //
        jeDate('#batch-w4-begin-date',{
            // isinitVal: true,
            festival: true,
            format: 'hh:mm',
            donefun(obj, val){
                that.w4.begin = obj.val;
            }
        });
        //
        jeDate('#batch-w5-begin-date',{
            // isinitVal: true,
            festival: true,
            format: 'hh:mm',
            donefun(obj, val){
                that.w5.begin = obj.val;
            }
        });
        //
        jeDate('#batch-w6-begin-date',{
            // isinitVal: true,
            festival: true,
            format: 'hh:mm',
            donefun(obj, val){
                that.w6.begin = obj.val;
            }
        });
        //
        jeDate('#batch-w7-begin-date',{
            // isinitVal: true,
            festival: true,
            format: 'hh:mm',
            donefun(obj, val){
                that.w7.begin = obj.val;
            }
        });
        //上课时间
        jeDate('#batch-execute-begin-date',{
            // isinitVal: true,
            festival: true,
            format: 'YYYY/MM/DD',
            donefun(obj, val){
                that.executeBeginDate = obj.val;
            }
        });
        //上课时间
        jeDate('#batch-execute-end-date',{
            // isinitVal: true,
            festival: true,
            format: 'YYYY/MM/DD',
            donefun(obj, val){
                that.executeEndDate = obj.val;
            }
        });
        //定时预约时间
        jeDate('#batch-execute-reserve-date',{
            //  isinitVal: true,
            festival: true,
            format: 'YYYY-MM-DD hh:mm:ss',
            donefun(obj, val){
                that.openReserveDateSelect = obj.val;
            }
        })
    },
    updated: function () {
    },
    methods: {
        LI(i,j){
            this.$emit('return-page', i, j)
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
        load(){
            let code = $.cookie('code'), shopId = $.cookie('shopid');
            //获取教练
            axiosGetParams(EDUCATION_URL.findCoachList, {code: code, shopId: shopId}, (res) => {
                this.coachList = res;
                if(this.coachList){
                    this.selectCoachId = this.coachList[0]['coachId']
                }
            });
            //获取课程 下拉框
            axiosGetParams(EDUCATION_URL.findCourseList, {CustomerCode: code, shopId: shopId, eduType: 1}, (res) => {
                this.courseList = res;
                if(this.courseList){
                    this.selectCourseId = this.courseList[0]['courseId']

                }
            });
            // //获得团教/私教的tree数据
            // axiosGetParams(EDUCATION_URL.findShopSdaduimCourseList, {CustomerCode: code, shopId: shopId, eduType: 0}, (res) => {
            //     // console.log(res)
            // });

            //获取门店
            axiosGetFetch("/shop/getShopAll",(res)=>{
                let a = res.find((item)=>{
                    return item.id == this.shopId;
                });
                this.shopName = a.shopName;
            });

            //获取场地
            axiosGetParams(EDUCATION_URL.getGroupRoomByShopId, {code: code, shopId: shopId}, (res)=>{
                this.roomList = res;
                if(this.roomList){
                    this.selectRoomId = this.roomList[0]['roomId']

                }
            });
            //查询门店下的私教团教的课程
            axiosGetParams(EDUCATION_URL.findAllCourseForShopSdaduim, {code: code, shopId: shopId}, (res)=>{
                let array = [];
                let obj = {};
                $.each(res, (i,d)=>{
                    let a = array.find((item)=>{
                        return item.shopId == d.shopId;
                    });
                    if(!a){
                        obj.shopId = d.shopId;
                        obj.shopName = d.shopName;
                        obj.status = false;
                        obj.groupList = {name: '健身团教', eduType: 0, courseList: [], status: false};
                        obj.privateList = {name: '健身私教', eduType: 1, courseList: [], status: false};
                        array.push(obj);
                        obj = {};
                    }
                });
                $.each(res, (i0,item)=>{
                    $.each(array, (i,d)=>{
                        item.status = false;
                        item.price = '';
                        if(item.eduType == 0){
                            d.groupList.courseList.push(item)
                        }
                        if(item.eduType == 1){
                            d.privateList.courseList.push(item)
                        }
                    });
                });
                this.resultCourseList = array;

            });

        },

        save(){
            //
            // this.LI(5);
            // Event.$emit(EDU_CONSTANT.listenerEducationWeekList)
            // return false;

            //判断是否选择场地
            // if(!this.selectRoomId){
            //     $.alert("请选择场地/教室")
            //     return false
            // }
            //判断是否有填写
            if(!this.selectCoachId){
                $.alert("请选择教练")
                return false
            }
            if(!this.selectCourseId){
                $.alert("请选择教练")
                return false
            }

            // if(!this.executeDate){
            //     $.alert("请输入上课时间")
            //     return false
            // }
            if(!this.executeBeginDate){
                $.alert("请输入课程开始时间")
                return false
            }
            if(!this.executeEndDate){
                $.alert("请输入课程结束时间")
                return false
            }
            if(!this.eduMemberNum){
                $.alert("单人私教单节课预约人数必须大于等于1个人")
                return false
            }
            //判断课程支持的人数
            if(this.moreTeach == '1'){
                if(this.eduMemberNum != '1'){
                    $.alert("单人私教单节课预约人数必须为1个人")
                    return false
                }

            }
            //判断必须要有选择星期几的周期
            if(this.w1.status == false && this.w2.status == false && this.w3.status == false &&
                this.w4.status == false && this.w5.status == false && this.w6.status == false &&
                this.w7.status == false){
                $.alert("至少选择一个课程周期")
                return false;
            }
            let dateSpan, iDays, date1, date2;
            date1 = Date.parse(this.executeBeginDate);
            date2 = Date.parse(this.executeEndDate);
            if(date1 - date2 > 0){
                $.alert("课程开始时间必须小于课程结束时间")
                return false;
            }
            dateSpan = date2 - date1;
            dateSpan = Math.abs(dateSpan);
            iDays = Math.floor(dateSpan /(24 * 3600 * 1000)) ;

            if(this.selectOpenReserveStatus == '2' && !this.openReserveDateSelect){
                $.alert('请输入定时开发预约时间');
                return false;
            }
            //防止重复
            if(this.isSave == true){
                return false;
            }
            this.isSave = true;

            //计算预约时间
            for(let i = 0; i < iDays + 1; i++){
                //date 当前的预约时间
                let date = new Date(this.executeBeginDate);
                date.setDate(date.getDate() + i);
                //定义一个开始时间
                let beginDate = '';
                let endDate = '';
                let executeDate = '';
                //判断当前时间是星期几
                let day = date.getDay();
                if(day == 0){
                    if(this.w7.status == false){
                        continue;
                    }
                    if(!this.w7.begin){
                        $.alert('请选择星期日的开始时间');
                        this.isSave = false;
                        return false;
                    }
                    executeDate = timeFormatDate(date);
                    beginDate = executeDate + " " + this.w7.begin + ":00";
                    endDate = executeDate + "" + this.w7.end + ":00";
                }
                if(day == 1){
                    if(this.w1.status == false){
                        continue;
                    }
                    if(!this.w1.begin){
                        $.alert('请选择星期一的开始时间');
                        this.isSave = false;
                        return false;
                    }
                    executeDate = timeFormatDate(date);
                    beginDate = executeDate + " " + this.w1.begin + ":00";
                    endDate = executeDate + "" + this.w1.end + ":00";
                }
                if(day == 2){
                    if(this.w2.status == false){
                        continue;
                    }
                    if(!this.w2.begin){
                        $.alert('请选择星期二的开始时间');
                        this.isSave = false;
                        return false;
                    }
                    executeDate = timeFormatDate(date);
                    beginDate = executeDate + " " + this.w2.begin + ":00";
                    endDate = executeDate + "" + this.w2.end + ":00";
                }
                if(day == 3){
                    if(this.w3.status == false){
                        continue;
                    }
                    if(!this.w3.begin){
                        $.alert('请选择星期三的开始时间');
                        this.isSave = false;
                        return false;
                    }
                    executeDate = timeFormatDate(date);
                    beginDate = executeDate + " " + this.w3.begin + ":00";
                    endDate = executeDate + "" + this.w3.end + ":00";
                }
                if(day == 4){
                    if(this.w4.status == false){
                        continue;
                    }
                    if(!this.w4.begin){
                        $.alert('请选择星期四的开始时间');
                        this.isSave = false;
                        return false;
                    }
                    executeDate = timeFormatDate(date);
                    beginDate = executeDate + " " + this.w4.begin + ":00";
                    endDate = executeDate + "" + this.w4.end + ":00";
                }
                if(day == 5){
                    if(this.w5.status == false){
                        continue;
                    }
                    if(!this.w5.begin){
                        $.alert('请选择星期五的开始时间');
                        this.isSave = false;
                        return false;
                    }
                    executeDate = timeFormatDate(date);
                    beginDate = executeDate + " " + this.w5.begin + ":00";
                    endDate = executeDate + "" + this.w5.end + ":00";
                }
                if(day == 6){
                    if(this.w6.status == false){
                        continue;
                    }
                    if(!this.w6.begin){
                        $.alert('请选择星期六的开始时间');
                        this.isSave = false;
                        return false;
                    }
                    executeDate = timeFormatDate(date);
                    beginDate = executeDate + " " + this.w6.begin + ":00";
                    endDate = executeDate + "" + this.w6.end + ":00";
                }



                //计算预约时间
                let param = {};
                let openReserveDate = new Date();
                if(this.selectOpenReserveStatus == '2'){
                    openReserveDate = new Date(this.openReserveDateSelect);
                }
                if(this.selectOpenReserveStatus == '3'){
                    let d = new Date(beginDate)
                    d.setHours(d.getHours() + parseInt(this.reserveHoursBegin ? this.reserveHoursBegin : 0));
                    openReserveDate = d;
                }
                //    console.log(this.selectCourseItem)

                let education = {
                    executeDatePlan: executeDate,
                    beginDatePlan: beginDate,
                    endDatePlan: endDate,
                    shopId: this.selectCourseItem.shopId,
                    shopName: this.selectCourseItem.shopName,
                    courseId: this.selectCourseId,
                    courseName: this.selectCourseItem.courseName,
                    type: 1,
                    coachId: this.selectCoachId,
                    coachName: this.selectCoachItem.coachName,
                    roomId: this.selectRoomItem.roomId,
                    roomName: this.selectRoomItem.roomName,
                    reserveTotalNum: this.eduMemberNum,
                    createTime: new Date(),
                    status: 0,
                    sdaduimId: this.selectRoomItem.sdaduimId,
                    sdaduimName: this.selectRoomItem.sdaduimName,
                    classSalesNum: this.selectCourseItem.classSalesNum,
                    createUserId: $.cookie('id'),
                    CustomerCode: $.cookie('code'),
                    createUserName: $.cookie('name'),
                    reserveType: this.moreTeach
                }
                //  console.log(education)

                let eduConfig = {
                    educationId: '',
                    reserveTime: timeFormatDate(openReserveDate, true, true),
                    settingTime: this.configChangeEdu?this.configChangeEdu:0,
                    isStartClass: this.configStartClass == "1" ? true : false,
                    isChooseCoach: this.configSelectCoach == "1" ? true : false,
                    isReserveConfirm: this.configConfirmEdu == "1" ? true : false,
                    admissionTime: this.configBeginClass?this.configBeginClass:0,
                    isOnlineReserve: this.isOnlinePay,
                    lessonPrice: parseFloat(this.price? this.price: 0),
                    isLimitReserve: this.isLimitReserveClass == "1" ? true : false,
                    isCardSettle: this.isMemberCardPay,
                    isCourseSettle: this.isCoursePay
                }
                // console.log(eduConfig)

                let eduReserveObject = null;
                if(eduConfig.isLimitReserve == true){
                    eduReserveObject =  {
                        educationConfigId: '',
                        isNewUser: this.p1,
                        isGeneralMember: this.p2,
                        isSilverMember: this.p3,
                        isGoldMember: this.p4,
                        isDiamondMember: this.p5,
                        isLatenceMember: this.p6,
                    }

                }
                //card
                let cardObject = null;
                if(eduConfig.isCardSettle == true){
                    cardObject = [];
                    $.each(this.resultCourseList, (i,d)=>{
                        $.each(d.groupList.courseList, (i,item)=>{
                            if(item.status == true){
                                let obj = {
                                    educationConfigId: '',
                                    shopId: this.selectCourseItem.shopId,
                                    sdadiumId: item.sdaduimId,
                                    projectId: item.courseId,
                                    price: item.price,
                                    shopName: item.shopName,
                                    sdadiumName: item.sdaduimName,
                                    projectName: item.courseName,
                                    educationType: item.eduType,
                                }
                                cardObject.push(obj)
                            }
                        })
                        $.each(d.privateList.courseList, (i,item)=>{
                            if(item.status == true){
                                let obj = {
                                    educationConfigId: '',
                                    shopId: this.selectCourseItem.shopId,
                                    sdadiumId: item.sdaduimId,
                                    projectId: item.courseId,
                                    price: item.price,
                                    shopName: item.shopName,
                                    sdadiumName: item.sdaduimName,
                                    projectName: item.courseName,
                                    educationType: item.eduType,
                                }
                                cardObject.push(obj)
                            }
                        })

                    });

                }

                //  console.log(eduMemberObject)
                param.education = education;
                param.eduConfig = eduConfig;
                param.cardObject = cardObject;
                param.eduReserveObject = eduReserveObject;
                axiosPostParams(EDUCATION_URL.saveEducation, param, (res)=>{
                    //重新刷新组建
                    if(i == iDays){
                        this.isSave = false;
                        this.LI(5);
                        Event.$emit(EDU_CONSTANT.listenerEducationMonthList)
                        $.alert("批量排课成功！！")
                    }

                },(res)=>{
                    this.isSave = false;
                    $.alert(res.msg)
                })

            }


        }
    },
});