var myCustomer = new Vue({
    el: '#myCustomer',
    data: {
        //我的现有客户筛选
        customerFilter: {
            //客户等级
            levelId: '',
            //服务会稽
            fwhjNameId: '',
            //跟进人id
            followPerson: '',
            //会员卡多少天到期
            expireDay:'',
            //日期类型
            dateType: '1',
            //开始时间
            startTime: null,
            //结束时间
            endTime: null,
            //多少天无人跟进
            noFollow: '',
            //超过多少天无人跟进
            noFollowDay: '',
            //搜索关键字
            keyword: null,
            //客户类型
            clientType: null,
            //当前日期
            newDate:null,
        },

        //现有客户列表
        customerTable: {
            list: [],
            currPage: 1,
            pageSize: 10,
            totalCount: 0,
            totalPage: 0,
        },
        //客户等级列表
        levelList: [],
        //跟进人
        followPersonal: [],
        coachInfo:[],
        pageTemp:"",
    },
    crated: function () {
    },
    watch: {
        'customerFilter.levelId': 'buildFilterDate',
        'customerFilter.followPerson': 'buildFilterDate',
    },
    methods: {
        init: function (t) {
            const that = this;
            that.queryFollow();
            that.initlevels();
            that.queryExistingList();
        },
        mounted: function () {


        },
        /**
         * 获取所有会员等级
         */
        initlevels:function () {
            let that = this;
            let url = $.stringFormat('{0}/frLevel/getListForSelect','http://www.4006337366.com:8080/');
            axios.get(url).then(function (res) {
                let resData = eval(res);
                if(resData['data']['code'] === '200'){
                    that.levelList = resData['data']['data'];
                    console.log(that.levelList)
                }else {
                    $.alert(resData['data']['msg']);
                }
            })
                .catch(function (error) {
                    $.alert(error);
                });
        },
        /**
         * 查询跟进人
         */
        queryFollow: function () {
            const that = this;
            const url = $.stringFormat('{0}/personnelInfo/findAll', 'http://www.4006337366.com:8080/');
            axios.get(url)
                .then(function (res) {
                    let jsonData = eval(res);
                    if (jsonData['data']['code'] === '200') {
                        that.followPersonal = jsonData['data']['data'];
                    } else {
                        $.alert(jsonData['data']['msg'])
                    }
                })
                .catch(function (error) {
                    $.alert(error)
                });
        },
        /**
         * 分页查询我的现有客户列表
         */
        queryExistingList: function (params) {
            const that = this;
            //显示加载中
            Loading.prototype.show();
            that.$nextTick(function () {
                try {
                    const url = $.stringFormat('{0}/frClient/getMyExistenceList', 'http://www.4006337366.com:8080/');

                    axios.get(url, {params: params})
                        .then(function (res) {
                            let jsonData = eval(res);
                            if (jsonData['data']['code'] === '200') {
                                that.customerTable = jsonData['data']['data'];
                                console.log(that.customerTable)
                                new myPagination({
                                    id: 'pagination',
                                    curPage: that.customerTable.currPage, //初始页码
                                    pageTotal: that.customerTable.totalPage, //总页数
                                    pageAmount: that.customerTable.pageSize,  //每页多少条
                                    dataTotal: that.customerTable.totalCount, //总共多少条数据
                                    showPageTotalFlag: true, //是否显示数据统计
                                    showSkipInputFlag: true, //是否支持跳转
                                    getPage: function (page) {
                                        that.pageTemp=page
                                        //获取当前页数
                                        that.buildFilterDate({page: page, limit: that.customerTable.pageSize});
                                    }
                                })
                            } else {
                                $.alert(jsonData['data']['msg'])
                            }
                            //隐藏加载中
                            Loading.prototype.hide();
                        })
                        .catch(function (error) {
                            console.log(error);
                            $.alert(error)
                        });
                }
                catch (ex) {
                    console.log(ex);
                    $.alert(ex.message);
                }

            })
        },
        /**
         * 重置筛选数据
         */
        resetFilterDate: function () {
            let that = this;
            that.customerFilter.startTime = null;
            that.customerFilter.endTime = null;
            that.customerFilter.newDate = null;
        },
        /**
         * 组装筛选数据直接查询
         */
        buildFilterDate: function (page) {
            let that = this;
            let jsonObj = {};
            let timestamp = Date.parse(new Date()) / 1000;
            let date = jeDate.timeStampDate(timestamp.toString(), 'YYYY-MM-DD');
            for (let item in that.customerFilter) {
                let jsonData = eval(that.customerFilter);
                jsonObj[item] = jsonData[item];
                if (jsonObj['noFollow'] != '' ||jsonObj['expireDay']!=''||jsonObj['noFollowDay']!='' ) {
                    jsonObj['expireDay']=-jsonObj['expireDay'];
                    jsonObj['newDate'] = date;
                }else {
                    jsonObj['newDate'] =null;
                }
            }
            if (typeof(eval(page.page)) == "undefined") {
                that.queryExistingList(jsonObj);
            } else {
                jsonObj.page=eval(page.page);
                jsonObj.limit= eval(page.limit);
                console.log(jsonObj)
                that.queryExistingList(jsonObj);
            }
        },
        /**
         * 点击时间
         * @param type [1.今天 2.本周 3.本月4.本季度5.本年]
         */
        clickTime: function (type) {
            const that = this;
            //全部
            if (type === 0) {
                that.startTime = null;
                that.endTime = null;
            }
            //今天
            if (type === 1) {
                let timestamp = Date.parse(new Date()) / 1000;
                let date = jeDate.timeStampDate(timestamp.toString(), 'YYYY-MM-DD');
                that.startTime = date;
                that.endTime = date;
            }
            //本周
            if (type === 3) {
                that.startTime = getWeekStartDate();
                that.endTime = getWeekEndDate();
            }
            //本月
            if (type === 3) {
                that.startTime = getMonthStartDate();
                that.endTime = getMonthEndDate();
            }
            //本季度
            if (type === 4) {
                that.startTime = getQuarterStartDate();
                that.endTime = getQuarterEndDate();
            }
            //本年度
            if (type === 5) {
                that.startTime = getYearStartDate();
                that.endTime = getYearEndDate();
            }
            console.log(that.customerFilter.dateType)
            //重载数据
            that.queryExistingList({
                dateType: that.customerFilter.dateType,
                startTime: that.startTime,
                endTime: that.endTime,
            })
        },
        /**
         * 客户类型
         * type [1.今日到访 2.今日生日客户 3.本月未到访客户 4.已设置提醒用户 5.未设置提醒用户 6.7天内卡到期客户 7.今日待跟进 2.昨日跟进 ]
         */
        clientType: function (type) {
            const that = this;
            that.customerFilter.clientType = type;
            //今日到访
            if (type === 1) {
                let timestamp = Date.parse(new Date()) / 1000;
                let date = jeDate.timeStampDate(timestamp.toString(), 'YYYY-MM-DD');
                that.startTime = date;
                that.endTime = date;
            }
            //今日生日客户
            if (type === 2) {
                let timestamp = Date.parse(new Date()) / 1000;
                let date = jeDate.timeStampDate(timestamp.toString(), 'YYYY-MM-DD');
                that.startTime = date;
                that.endTime = date;
            }
            //本月未到访客户
            if (type === 3) {
                that.startTime = getMonthStartDate();
                that.endTime = getMonthEndDate();
            }
            //7天内卡到期
            if (type === 6) {
                let timestamp = Date.parse(new Date()) / 1000;
                let date = jeDate.timeStampDate(timestamp.toString(), 'YYYY-MM-DD');
                that.startTime = date;
            }
            //今日待跟进
            if (type === 7) {
                let timestamp = Date.parse(new Date()) / 1000;
                let date = jeDate.timeStampDate(timestamp.toString(), 'YYYY-MM-DD');
                that.startTime = date;
                that.endTime = date;
            }
            //昨日跟进
            if (type === 8) {
                let timestamp = (Date.parse(new Date()) / 1000) - 24 * 60 * 60;
                let date = jeDate.timeStampDate(timestamp.toString(), 'YYYY-MM-DD');
                that.startTime = date;
                that.endTime = date;
            }
            console.log(that.customerFilter.clientType)

            //重载数据
            that.queryExistingList({
                clientType: that.customerFilter.clientType,
                startTime: that.startTime,
                endTime: that.endTime,
            })
        },

        //查看教练  移入
        lookCoach:function(obj,event){
            const that =  event.currentTarget;
            var thatT = this;
            $('.tabBox2').css({
                'display': 'block',
                'top': $(that).offset().top + $('.tableWrap').scrollTop() + 56
            });
            thatT.coachInfo = obj;
            console.log(thatT.coachInfo)
        },
        //移出
        outCoach:function(){
            $('.tabBox2').css({
                'display': 'none'
            });
        },
        //打印
        print: function () {
            var headhtml = "<html><head><title></title></head><body>";
            var foothtml = "</body>";
            // 获取div中的html内容
            // var newhtml = document.all.item(printpage).innerHTML;
            // 获取div中的html内容，jquery写法如下
            var newhtml = $(".tabW").html();
            // 获取原来的窗口界面body的html内容，并保存起来
            var oldhtml = document.body.innerHTML;

            // 给窗口界面重新赋值，赋自己拼接起来的html内容
            document.body.innerHTML = headhtml + newhtml + foothtml;
            // 调用window.print方法打印新窗口
            window.print();

            // 将原来窗口body的html值回填展示
            document.body.innerHTML = oldhtml;
            window.location.reload();
            return false;
        },
        //导出Excel
        exportExcel: function () {
            var that = this;
            var url = $.stringFormat('{0}/excel/myCustomer', 'http://www.4006337366.com:8080/');
            var form = $("<form>");//定义一个form表单
            form.attr("style", "display:none");
            form.attr("target", "_break");
            form.attr("method", "post");
            form.attr("action", url);
            var input1 = $("<input>");
            input1.attr("type", "hidden");
            input1.attr("name", "page");
            if(that.pageTemp==""){
                that.pageTemp=1;
            }
            input1.attr("value", that.pageTemp);
            $("body").append(form);//将表单放置在web中
            form.append(input1);
            form.submit();//表单提交
        },
    }
});

Loading.prototype.init();
//初始化:开始日期
jeDate('.startDate', {
    format: "YYYY-MM-DD",
    donefun: function(obj){
        myCustomer.customerFilter.startTime = obj.val;
        //重载数据
        myCustomer.buildFilterDate();
    }
});


//初始化:结束日期
jeDate('.endDate', {
    format: "YYYY-MM-DD",
    donefun: function(obj){
        myCustomer.customerFilter.endTime = obj.val;
        //重载数据
        myCustomer.buildFilterDate();
    }
});
