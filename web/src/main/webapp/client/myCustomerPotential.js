var myCustomerPotential = new Vue({
    el: '#myCustomerPotential',
    data: {
        //我的潜在客户筛选
        customerFilter: {
            //客户等级
            customerLevel: '',
            //服务会稽
            fwhjNameId: '',
            //跟进人id
            followPerson: '',
            //剩余保护天数
            protectDay: '',
            //日期类型
            dateType: '0',
            //开始时间
            startTime: null,
            //结束时间
            endTime: null,
            //多少天无人跟进
            noFollow: null,
            //搜索关键字
            keyword: null,
            //客户类型
            clientType: null,
            //当前日期
            newDate: null,
        },

        //潜在客户列表
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
        //服务会稽
        ServicePersonnel: [],
    },
    crated: function () {
    },
    watch: {
        'customerFilter.customerLevel': 'buildFilterDate',
        'customerFilter.followPerson': 'buildFilterDate',
        'customerFilter.fwhjNameId': 'buildFilterDate',
    },
    methods: {
        init: function (t) {
            const that = this;
            that.queryLevel();
            that.queryPotentialList();
            that.queryFollow();
            that.queryServicePersonnel();
        },
        mounted: function () {


        },
        /**
         * 查询等级
         */
        queryLevel: function () {
            const that = this;
            const url = $.stringFormat('{0}/frStoreCommon/getQzLevel', $.cookie('url'));
            axios.get(url)
                .then(function (res) {
                    let jsonData = eval(res);
                    if (jsonData['data']['code'] === '200') {
                        that.levelList = jsonData['data']['data'];
                    } else {
                        $.alert(jsonData['data']['msg'])
                    }
                })
                .catch(function (error) {
                    $.alert(error)
                });
        },
        /**
         * 查询服务会稽
         */
        queryServicePersonnel: function () {
            const that = this;
            const url = $.stringFormat('{0}/personnelInfo/getServicePersonnel', $.cookie('url'));
            $.get(url, {"userType": 2}, function (res) {
                that.ServicePersonnel = res.data;
            })
        },
        /**
         * 查询跟进人
         */
        queryFollow: function () {
            const that = this;
            const url = $.stringFormat('{0}/personnelInfo/findAll', $.cookie('url'));
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
         * 分页查询我的潜在客户列表
         */
        queryPotentialList: function (params) {
            const that = this;
            //显示加载中
            Loading.prototype.show();
            that.$nextTick(function () {
                try {
                    const url = $.stringFormat('{0}/frClient/getMyPotentialList', $.cookie('url'));

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
         * 筛选-选择等级
         */
        levelSelect: function () {
            const that = this;
            let levelId = that.customerFilter.customerLevel;
            that.queryPotentialList({
                customerLevel: levelId,
            });
        },
        /**
         * 筛选-跟进人
         */
        followSelect: function () {
            const that = this;
            let followPersonId = that.customerFilter.followPerson;
            that.queryPotentialList({
                followPerson: followPersonId,
            });
        },
        /**
         * 筛选-服务会稽
         */
        serviceSelect: function () {
            const that = this;
            let fwhjNameId = that.customerFilter.fwhjNameId;
            that.queryPotentialList({
                fwhjNameId: fwhjNameId,
            });
        },
        /**
         * 搜索剩余保护天数
         */
        searchProtectDay: function () {
            const that = this;
            that.queryPotentialList({
                protectDay: that.customerFilter.protectDay,
            });
        },
        /**
         * 搜索多少天无人跟进
         */
        searchNoFollow: function () {
            const that = this;
            let timestamp = Date.parse(new Date()) / 1000;
            let date = jeDate.timeStampDate(timestamp.toString(), 'YYYY-MM-DD');
            that.queryPotentialList({
                noFollow: that.customerFilter.noFollow,
                startTime: date,
                endTime: date,
            });
        },
        /**
         * 重置筛选数据
         */
        resetFilterDate: function () {
            let that = this;
            that.customerFilter.startTime = null;
            that.customerFilter.endTime = null;
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
                if (jsonObj['noFollow'] != null) {
                    jsonObj['newDate'] = date;
                }
            }
            if (typeof(eval(page.page)) == "undefined") {
                that.queryPotentialList(jsonObj);
            } else {
                jsonObj.page = eval(page.page);
                jsonObj.limit = eval(page.limit);
                console.log(jsonObj)
                that.queryPotentialList(jsonObj);
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
            that.queryPotentialList({
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
            //今日待跟进
            if (type === 6) {
                let timestamp = Date.parse(new Date()) / 1000;
                let date = jeDate.timeStampDate(timestamp.toString(), 'YYYY-MM-DD');
                that.startTime = date;
                that.endTime = date;
            }
            //昨日跟进
            if (type === 7) {
                let timestamp = (Date.parse(new Date()) / 1000) - 24 * 60 * 60;
                let date = jeDate.timeStampDate(timestamp.toString(), 'YYYY-MM-DD');
                that.startTime = date;
                that.endTime = date;
            }
            console.log(that.customerFilter.clientType)

            //重载数据
            that.queryPotentialList({
                clientType: that.customerFilter.clientType,
                startTime: that.startTime,
                endTime: that.endTime,
            })
        },
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
            var url = $.stringFormat('{0}/excel/myPotential', $.cookie('url'));
            var data=JSON.stringify(that.customerTable.list)
            $.ajax({
                url : url,
                data : data,
                type : 'POST',
                dataType : 'json',
                contentType: "application/json;charset=utf-8",
                success : function(res) {
alert(res.msg)
                }
            })
        },
    }
});
Loading.prototype.init();

//
// //初始化:开始日期
// jeDate('.startDate', {
//     format: "YYYY-MM-DD",
//     donefun: function (obj) {
//         myCustomerPotential.startTime = obj.val;
//     }
// });
// //初始化:结束日期
// jeDate('.endDate', {
//     format: "YYYY-MM-DD",
//     donefun: function (obj) {
//         myCustomerPotential.endTime = obj.val;
//         const that = myCustomerPotential.$data;
//         //重载数据
//         myCustomerPotential.queryPotentialList({
//             dateType: that.customerFilter.dateType,
//             startTime: that.startTime,
//             endTime: that.endTime,
//         })
//     }
// });
//初始化:开始日期
jeDate('.startDate', {
    format: "YYYY-MM-DD",
    donefun: function (obj) {
        myCustomerPotential.customerFilter.startTime = obj.val;
        //重载数据
        myCustomerPotential.buildFilterDate();
    }
});


//初始化:结束日期
jeDate('.endDate', {
    format: "YYYY-MM-DD",
    donefun: function (obj) {
        myCustomerPotential.customerFilter.endTime = obj.val;
        //重载数据
        myCustomerPotential.buildFilterDate();
    }
});

