Vue.component('edu-work-bench-children', {//模版挂载的标签名
    template: '#edu-work-bench-id',//id对应子组件的ID
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
    },
    created() {
        //默认的开始时间是当前一个月
        this.beginDate = getMonthStartDate();
        this.endDate = getMonthEndDate();
        //搜索
        this.save();
    },
    mounted() {
        let code = $.cookie('code'), shopId = $.cookie('shopid');
        //获取教练
        axiosGetParams(EDUCATION_URL.findCoachList, {code: code, shopId: shopId}, (res) => {
            this.coachList = res;
        });
        //获取教练
        axiosGetParams(EDUCATION_URL.findSdaduimList, {code: code, shopId: shopId}, (res) => {
            this.sdaduimList = res;
        });
        this.$nextTick(()=> {
            $('#appointmentWorkbench').show().siblings().hide();
        })
    },
    updated: function () {
        this.$nextTick(()=> {
            this.ajaxInitFunc();
        })
    },
    methods: {
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
        selectToConfirmList(eduId){
          //  this.hoverEduId = eduId;

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
                beginDate: this.beginDate,
                endDate: this.endDate,
                eduType: 1, //私教
                searchInput: this.searchInput,
                reserveType: this.reserveType,//一对一
            };
            //初始化
            this.eduClientCount = 0;
            this.eduCount = 0;
            this.toConfirmResultList = [];
            //获取数据
            axiosGetParams(EDUCATION_URL.findEducationList, param, (res) => {
                if(this.reserveType == 1){
                    this.resultListOneToOne = res;
                }else{
                    this.resultListOneToMany = res;
                }

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
                //点击搜索，执行查询前，时间填入
                let db = new Date(this.beginDate);
                let de = new Date(this.endDate);
                this.showBeginDate = db.getFullYear() + "年" + (db.getMonth() + 1) + "月" + db.getDate() + "日";
                this.showEndDate = de.getFullYear() + "年" + (de.getMonth() + 1) + "月" + de.getDate() + "日";
            });

        },
        changeStatusAll(status){
            this.toConfirmCheckbox.forEach((clientInfoId) =>{
                this.changeStatus(clientInfoId, status, "")
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
                this.save();
                $('.tabBox').css({
                    'display': 'none'
                });
            })
        },
        changeEduId(eduId, eduItem){
            this.$emit("listener-bench-bessage", eduId, eduItem)
        },
        //私教一对一预约/私教一对多预约切换函数
        btnWrapSpanFl: function (t) {
            if(t == 0){
                this.reserveType = 1;
                if(this.resultListOneToOne.length == 0){
                    this.save();
                }
            }

            if(t == 1){
                this.reserveType = 2;
                if(this.resultListOneToMany.length == 0){
                    this.save();
                }
            }

            $('.btnWrap span.fl').eq(t).addClass('on').siblings().removeClass('on');
            $('.tab .tabt').eq(t).addClass('on').siblings().removeClass('on');
        },
        // //课程消费明细表/个人消费明细表切换函数
        // tabSpanSpans: function (t) {
        //     this.tabSpanspan = t;
        //     $('.tabSpan span').eq(t).addClass('on').siblings().removeClass('on');
        //     $('.select').eq(t).addClass('on').siblings().removeClass('on');
        // },
    },
});
