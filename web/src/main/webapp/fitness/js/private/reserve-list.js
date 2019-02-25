Vue.component('edu-work-bench-children', {//模版挂载的标签名
    template: '#edu-work-bench-id',//id对应子组件的ID
    props: [],
    data() {
        return {
            searchInput: '', //输入的值
            coachList: [], //教练数组
            selectCoachId: '', //选择教练
            sdaduimList: [], //场馆数组
            selectSdaduimId: '', //选择场馆
            beginDate: '', //开始时间
            endDate: '', //结束时间
            resultListOneToOne: [], //结果数组
            resultListOneToMany: [], //结果数组
            showBeginDate: '',//
            showEndDate: '',
            dateStatus: 4, //选择时间段的限制
            eduCount: 0, //总共几节课
            eduClientCount: 0, //上课人数
            hoveResultList: [], //鼠标移动出现的数据
            toConfirmResultList: [], //待确认会员数组
            toConfirmList: [], //待确认会员数组 页面遍历显示
            toConfirmCheckbox: [], //选择 id
            isCheckedAll: false,
            reserveType: 1, //一对一， 2一对多
        }
    },
    filters: {
        toNull(val){
            if(!val){
                return '无';
            }
            return val;
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
                return '无';
            }
           return timeFormatDate(val, true)
        },
        toDateyyyyMMdd(val){
            return timeFormatDate(val);
        },
        reserveStatusFilter1(val, item){

            let str = "未预约";

            let infoClientId = item.infoClientId;//没有预约用户
            let eduStatus = item.status;
            //开课状态 1/0/2/3 已开始，未开始，已结束, 已取消
            //预约状态 1/已预约 0/已取消 2/待确认

            if((eduStatus == 0) && val == 1){
                str = "已确定"
            }
            if((eduStatus == 0) && val == 2){
                str = "已预约"
            }
            if((eduStatus == 0) && val == 0){
                str = "已拒绝"
            }

            if((val == null || val == '' || val == undefined ) && eduStatus == 0){
                str = "未预约"
            }
            if(eduStatus == 2 || eduStatus == 1){
                str = "已结束"
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
    watch: {
        //限制的改变
        dateStatus(val){
            switch(val){
                case 1:
                    this.beginDate = getYearStartDate();
                    this.endDate = getYearEndDate();
                    break;
                case 2:
                    this.beginDate = formatDate(new Date());
                    this.endDate = formatDate(new Date());
                    break;
                case 3:
                    this.beginDate = getWeekStartDate();
                    this.endDate = getWeekEndDate();
                    break;
                case 4:
                    this.beginDate = getMonthStartDate();
                    this.endDate = getMonthEndDate();
                    break;
                case 5:
                    this.beginDate = getQuarterStartDate();
                    this.endDate = getQuarterEndDate();
                    break;
                case 6:
                    this.beginDate = getYearStartDate();
                    this.endDate = getYearEndDate();
                    break;
            }
        },
        reserveType(){
            this.save();
        }
    },
    created() {
        //默认的开始时间是当前一个月
        this.beginDate = getMonthStartDate();
        this.endDate = getMonthEndDate();
        //搜索
        this.save();
        Event.$on(EDU_CONSTANT.listenerListItemList, ()=>{
            this.save();
        })
    },
    mounted() {
        let that = this;
        let code = $.cookie('code'), shopId = $.cookie('shopid');
        //获取教练
        axiosGetParams(EDUCATION_URL.findCoachList, {code: code, shopId: shopId}, (res) => {
            this.coachList = res;
        });
        //获取场馆
        axiosGetParams(EDUCATION_URL.findSdaduimList, {code: code, shopId: shopId}, (res) => {
            this.sdaduimList = res;
        });
        this.$nextTick(()=> {
            $('#appointmentWorkbench').show().siblings().hide();
            //上课时间
            jeDate('#work-bench-begin-date',{
               // isinitVal: true,
                festival: true,
                format: 'YYYY-MM-DD',
                donefun(obj, val){
                    that.beginDate = obj.val;
                }
            });
            //上课时间
            jeDate('#work-bench-end-date',{
               // isinitVal: true,
                festival: true,
                format: 'YYYY-MM-DD',
                donefun(obj, val){
                    that.endDate = obj.val;
                }
            });
        })
    },
    updated: function () {
        this.$nextTick(()=> {
            this.ajaxInitFunc();
        })
    },
    methods: {
        LI(i,j){
            this.$emit('return-page', i, j)
        },
        ajaxInitFunc(){
            // $('.toBlock').unbind("click")
            // $('.tabBox').unbind("mouseenter")
            // $('.toBlock').unbind("mouseleave")

            $('.toBlock').hover(function () {
                // 滑动滚动条
                $('.tabBox').css({
                    'display': 'block',
                    'top': $('.contains').scrollTop() + $(this).offset().top - 25
                });
            } );

            $('.tabBox').mouseenter(function () {
                $('.tabBox').css({
                    'display': 'block'
                });
            })
            $('.tabBox').mouseleave(function () {
                $('.tabBox').css({
                    'display': 'none'
                });
            })

        },
        //选择框
        checkedOne(clientId){
            let idIndex = this.toConfirmCheckbox.indexOf(clientId)
            if (idIndex >= 0) {
                // 如果已经包含了该id, 则去除(单选按钮由选中变为非选中状态)
                this.toConfirmCheckbox.splice(idIndex, 1)
            } else {
                // 选中该checkbox
                this.toConfirmCheckbox.push(clientId)
            }
        },
        checkedAll(){
            this.isCheckedAll = !this.isCheckedAll;
            if (this.isCheckedAll) {
                // 全选时
                this.toConfirmCheckbox = [];
                this.toConfirmList.forEach((item) => {
                    this.toConfirmCheckbox.push(item.id);
                })
            } else {
                this.toConfirmCheckbox = []
            }
        },
        //鼠标划过，出现的数据
        selectToConfirmList(eduId, item){
          //  this.hoverEduId = eduId;

            if(item.status != 0){
                return false;
            }
            let a = this.toConfirmResultList.find((item)=>{
                return item.eduId == eduId;
            });
            this.toConfirmList = [];
            if(a){
                this.toConfirmList = a['result']
            }
        },
        //搜索
        save() {
            //判断时间是否存在
            if(!this.beginDate){
                $.alert("请选择开始时间")
                return false;
            }
            if(!this.endDate){
                $.alert("请选择结束时间")
                return false;
            }
            let param = {
                code: $.cookie('code'),
                shopId: $.cookie('shopid'),
                coachId: this.selectCoachId,
                sdaduimId: this.selectSdaduimId,
                beginDate: this.beginDate.trim() + " 00:00:00",
                endDate: this.endDate.trim() + " 23:59:59",
                eduType: 1, //私教
                searchInput: this.searchInput,
                reserveType: this.reserveType,//一对一 一对多
            };
            //初始化
            this.eduClientCount = 0;
            this.eduCount = 0;
            this.toConfirmResultList = [];
            //获取数组
            axiosGetParams(EDUCATION_URL.findEducationList, param, (res) => {

                if(this.reserveType == 1){
                    this.resultListOneToOne = res;
                }else{
                    this.resultListOneToMany = res;
                    //现实结果 循环遍历得到数据
                    $.each(res, (i, d) =>{
                        //eduClientCount
                        //eduCount
                        if(d.status == 2){
                            this.eduClientCount += d.eduCurrentCount;
                        }
                        this.eduCount = i++;
                        //设置100毫秒后执行查询
                        if(this.reserveType == 2){ // 一对多
                            setTimeout((eduId)=>{
                                let param1 = {
                                    eduId: eduId,
                                    reserveStatus: 2, //待确认
                                    searchInput: this.searchInput
                                };
                                axiosGetParams(EDUCATION_URL.findToConfirmList, param1, (res) => {
                                    //
                                    let obj = {};
                                    obj.eduId = eduId;
                                    obj.result = res;
                                    this.toConfirmResultList.push(obj);
                                })
                            },100, d.id)
                        }

                    })
                }


                //点击搜索，执行查询前，时间填入
                let db = new Date(this.beginDate);
                let de = new Date(this.endDate);
                this.showBeginDate = db.getFullYear() + "年" + (db.getMonth() + 1) + "月" + db.getDate() + "日";
                this.showEndDate = de.getFullYear() + "年" + (de.getMonth() + 1) + "月" + de.getDate() + "日";

                //测试用的
             //   Event.$emit("inputEduItem", this.resultList[0])
            });

        },
        changeStatusAll(status){
            let num = this.toConfirmCheckbox.length;
            let ind = '';
            this.toConfirmCheckbox.forEach((clientInfoId, index) =>{
                if(num == (index + 1)){
                    ind = 'true';
                }
                this.changeStatus(clientInfoId, status, ind)
            })
        },
        //确定 //取消
        changeStatus(id, status, index){
            let param = {
                clientInfoId: id,
                status: 0
            }
            if(status == 1){
                param.status = 1
            }
            axiosPostParams(EDUCATION_URL.changeEduClientStatus, param, (res) => {
                //修改成功后，修改状态
                if(index == 'true'){
                    this.save();
                    if(this.reserveType == 2){
                        $('.tabBox').css({
                            'display': 'none'
                        });
                    }

                }

            })
        },
        changeEduId(eduId, eduItem){
            Event.$emit(EDU_CONSTANT.listenerEduItem, eduId, eduItem)
        },
        //添加预约，添加预约限制
        addReserve(eduId, eduItem){
           // console.log(eduId)
          //  console.log(eduItem)
            //判断当前预约是否可以在pc端预约
            if(eduItem.configOnlineReserve == true){
                $.alert("该团教课程只能在app上预约")
                return false;
            }
            //判断当前是否可以预约，时间判断
            if((new Date().getTime() - eduItem.configReserveTime) < 0){
                let date = new Date(eduItem.configReserveTime);
                $.alert("该课程还未开放预约,请在" + timeFormatDate(date, true) + "后预约。");
                return false;
            }
            if((eduItem.eduCurrentCount + eduItem.eduToConfirmCount) == eduItem.reserveTotalNum){
                $.alert("该课程会员人数已约满")
                return false;
            }
            Event.$emit(EDU_CONSTANT.listenerEduItem, eduId, eduItem)

            reservation();
        },
        //开课
        startClass(eduId, eduItem){
            Event.$emit(EDU_CONSTANT.listenerEduItem, eduId, eduItem);
            startClass();
        }
    },
});
