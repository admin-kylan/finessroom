/**
 * by Kylan
 * @author
 * @date 2019-01-27
 */
Vue.component('edu-class-setting-children', {//模版挂载的标签名
    template: '#edu-class-setting-id',//id对应子组件的ID
    data() {
        return {
            settingStatus: false,
            eduItem: {},
            updateClassStatus: false,
            //----更新
            coachList: [],
            selectCoachId: '',
            selectCoachItem: {},
            courseList: [],
            selectCourseId: '',
            selectCourseItem: {},
            executeDate: '', //上课时间
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
            //课程数组
            resultCourseList: [],
            selectCourseStatus: false,
            //eduConfig
            eduConfig: {},
            eduReserveObjectConfig: {}
        }
    },
    filters: {
        toDateyyyyMMdd(val){
            return timeFormatDate(val)
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
    },
    computed: {},
    watch: {
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
        },
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
    },
    created() {
        Event.$off(EDU_CONSTANT.listenerClassSettingInfo)
        Event.$on(EDU_CONSTANT.listenerClassSettingInfo, (item)=>{
            this.settingStatus = true;
            //获取数据
            //课程
            axiosGetParams(EDUCATION_URL.findEducationById, {eduId: item.eduId}, (res) => {
                this.eduItem = res;
                this.load();
            });
        });

        let code = $.cookie('code'), shopId = $.cookie('shopid');
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
    mounted() {
        let that = this;
        this.$nextTick(()=>{
            //上课时间
            jeDate('#setting-execute-date',{
                // isinitVal: true,
                festival: true,
                format: 'YYYY/MM/DD',
                donefun(obj, val){
                    that.executeDate = obj.val;
                }
            });
            //上课时间
            jeDate('#setting-execute-begin-date',{
                // isinitVal: true,
                festival: true,
                format: 'hh:mm',
                donefun(obj, val){
                    that.executeBeginDate = obj.val;
                }
            });
            //上课时间
            jeDate('#setting-execute-end-date',{
                // isinitVal: true,
                festival: true,
                format: 'hh:mm',
                donefun(obj, val){
                    that.executeEndDate = obj.val;
                }
            });
            //定时预约时间
            jeDate('#setting-execute-reserve-date',{
                //  isinitVal: true,
                festival: true,
                format: 'YYYY-MM-DD hh:mm:ss',
                donefun(obj, val){
                    that.openReserveDateSelect = obj.val;
                }
            })
        })
    },
    updated: function () {
    },
    methods: {
        LI(index, index2){
            //this.$parent.LI(index, index2)
            this.$emit('return-page', index, index2);
            setTimeout(()=>{
                if(this.eduItem.id){
                    Event.$emit(EDU_CONSTANT.listenerEduItem, this.eduItem.id, this.eduItem)
                }
            },51)
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
        showUpdateClass(){
            //获取当前课程的信息
            axiosGetParams(EDUCATION_URL.findEduCationInfoById, {eduId: this.eduItem.id}, (map) => {
                this.updateClassStatus = true;
                let education = map.education;
                let eduConfig = map.eduConfig;
                let eduReserveObject = map.eduReserveObject;
                let eduCardObjects = map.eduCardObjects;
                this.eduConfig = eduConfig;
                this.eduReserveObjectConfig = eduReserveObject;
                //赋值
                this.selectCoachId = education.coachId;
                this.selectCourseId = education.courseId;
                this.executeDate = timeFormatDate(education.executeDatePlan)
                this.executeBeginDate = this.toDateHHmm(education.beginDatePlan)
                this.executeEndDate = this.toDateHHmm(education.endDatePlan)
                this.openReserveDateSelect = timeFormatDate(eduConfig.reserveTime, true, true);
                this.selectOpenReserveStatus = '2'
                this.configChangeEdu = eduConfig.settingTime;
                this.configStartClass = eduConfig.startClass?'1':'0';
                this.configSelectCoach = eduConfig.chooseCoach?'1':'0';
                this.configConfirmEdu = eduConfig.reserveConfirm?'1':'0';
                this.configBeginClass = eduConfig.admissionTime;
                this.isOnlinePay = eduConfig.onlineReserve;
                this.isMemberCardPay = eduConfig.cardSettle;
                this.isCoursePay = eduConfig.courseSettle;
                this.price = eduConfig.lessonPrice;
                this.isLimitReserveClass = eduConfig.limitReserve?'1':'0';
                if(eduReserveObject){
                    this.p1 = eduReserveObject.newUser;
                    this.p2 = eduReserveObject.generalMember;
                    this.p3 = eduReserveObject.silverMember;
                    this.p4 = eduReserveObject.goldMember;
                    this.p5 = eduReserveObject.diamondMember;
                    this.p6 = eduReserveObject.latenceMember;
                }
                if(eduCardObjects){
                    //resultCourseList
                    $.each(eduCardObjects, (i, item)=>{
                        $.each(this.resultCourseList, (i, d)=>{
                            $.each(d.groupList.courseList, (i, obj)=>{
                                if (item.projectId == obj.courseId) {
                                    obj.status = true;
                                }
                            })
                            $.each(d.privateList.courseList, (i, obj)=>{
                                if (item.projectId == obj.courseId) {
                                    obj.status = true;
                                }
                            })
                        })
                    })
                }

            })
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
            axiosGetParams(EDUCATION_URL.findCourseList, {CustomerCode: code, shopId: shopId, eduType: 0}, (res) => {
                this.courseList = res;
                if(this.courseList){
                    this.selectCourseId = this.courseList[0]['courseId']

                }
            });
            // //获得团教/私教的tree数据
            // axiosGetParams(EDUCATION_URL.findShopSdaduimCourseList, {CustomerCode: code, shopId: shopId, eduType: 0}, (res) => {
            //    // console.log(res)
            // });
        },
        save(){

            //判断是否有填写
            if(!this.selectCoachId){
                $.alert("请选择教练")
                return false
            }
            if(!this.selectCourseId){
                $.alert("请选择教练")
                return false
            }
            if(!this.executeDate){
                $.alert("请输入上课时间")
                return false
            }
            if(!this.executeBeginDate){
                $.alert("请输入上课开始时间")
                return false
            }
            if(!this.executeEndDate){
                $.alert("请输入上课结束时间")
                return false
            }
            if(this.selectOpenReserveStatus == '2' && !this.openReserveDateSelect){
                $.alert('请输入定时开发预约时间');
                return false;
            }
            if(this.isSave == true){
                return false;
            }
            this.isSave = true;

            //计算预约时间
            let param = {};
            let openReserveDate = new Date();
            if(this.selectOpenReserveStatus == '2'){
                openReserveDate = new Date(this.openReserveDateSelect);
            }
            if(this.selectOpenReserveStatus == '3'){
                let d = new Date(this.executeDate + " " + this.executeBeginDate)
                d.setHours(d.getHours() + parseInt(this.reserveHoursBegin ? this.reserveHoursBegin : 0));
                openReserveDate = d;
            }
            //    console.log(this.selectCourseItem)

            let education = {
                id: this.eduItem.id,
                executeDatePlan: this.executeDate,
                beginDatePlan: this.executeDate + this.executeBeginDate + ":00",
                endDatePlan: this.executeDate + this.executeEndDate + ":00",
                shopId: this.eduItem.shopId,
                shopName: this.eduItem.shopName,
                courseId: this.selectCourseId,
                courseName: this.selectCourseItem.courseName,
                type: 0,
                coachId: this.selectCoachId,
                coachName: this.selectCoachItem.coachName,
                roomId: this.eduItem.roomId,
                roomName: this.eduItem.roomName,
                reserveTotalNum: this.selectCourseItem.reserveTotalNum,
                createTime: new Date(),
                status: 0,
                sdaduimId: this.eduItem.sdaduimId,
                sdaduimName: this.eduItem.sdaduimName,
                classSalesNum: this.selectCourseItem.classSalesNum,
                createUserId: $.cookie('id'),
                CustomerCode: $.cookie('code'),
                createUserName: $.cookie('name'),
            }
            //  console.log(education)

            let eduConfig = {
                id: this.eduConfig.id,
                educationId: education.id,
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
                   // id: this.eduReserveObjectConfig.id,
                    educationConfigId: eduConfig.id,
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
                                educationConfigId: eduConfig.id,
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
                                educationConfigId: eduConfig.id,
                                shopId: this.selectCourseItem.shopId,
                                sdadiumId: item.sdaduimId,
                                projectId: item.courseId,
                                price: item.price,
                                shopName: item.shopName,
                                sdadiumName: item.sdaduimName,
                                projectName: item.courseName,
                                educationType: item.eduType,
                            }
                           // cardObject.push(obj)
                        }
                    })

                });

            }

            //  console.log(eduMemberObject)
            param.education = education;
            param.eduConfig = eduConfig;
            param.cardObject = cardObject;
            param.eduReserveObject = eduReserveObject;
            axiosPostParams(EDUCATION_URL.updateEducationInfo, param, (res)=>{
                //重新刷新组建
                Event.$emit(EDU_CONSTANT.listenerEducationMonthList)
                this.updateClassStatus = false;
                this.isSave = false;
            },(res)=>{
                this.isSave = false;
                $.alert(res.msg)
            })

        }
    },
});