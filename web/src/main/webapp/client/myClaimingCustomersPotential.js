var myClaimingCustomersPotential = new Vue({
    el: '#myClaimingCustomersPotential',
    data: {
        //我的潜在客户筛选
        customerFilter: {
            //性别
            sex: '',
            //销售顾问
            consultantId: '',
            //服务会稽
            fwhjNameId: '',
            //教练id
            personalId: '',
            //日期类型
            dateType: '0',
            //开始时间
            startTime: null,
            //结束时间
            endTime: null,
            //搜索关键字
            keyword: null,
            //门店ID
            shopId: '',
            //手动跟进次数
            manualCount: null,
            //自动跟进次数
            automaticCount: null,
            //多少天无人跟进
            noFollow: null,
            //当前日期
            newDate: null
        },
        //可认领客户列表
        customerTable: {
            list: [],
            currPage: 1,
            pageSize: 10,
            totalCount: 0,
            totalPage: 0,
        },
        //服务会稽
        ServiceInfo: [],
        //门店
        ShopInfo: [],
        //教练
        CoachInfo: [],
        //销售
        ConsultantInfo: [],
        ClaimingInfo:[],
        pageTemp:'',
    },
    crated: function () {
    },
    watch: {
        'customerFilter.consultantId': 'buildFilterDate',
        'customerFilter.fwhjNameId': 'buildFilterDate',
        'customerFilter.shopId': 'buildFilterDate',
        'customerFilter.personalId': 'buildFilterDate',
    },
    methods: {
        init: function (t) {
            const that = this;
            that.queryService();
            that.queryClaimingList();
            that.queryClaiming();
            that.queryConsultant();
            that.queryCoach();
            that.queryShop();
        },
        mounted: function () {


        },
        /**
         * 查询可认领客户
         */
        queryClaiming: function () {
            const that = this;
            const url = $.stringFormat('{0}/frIndustry/getClientCount', $.cookie('url'));
            //潜在 clientType:1
            $.get(url, {"clientType": 1}, function (res) {
                that.ClaimingInfo = res.data;
            })
        },
        /**
         * 查询服务会稽
         */
        queryService: function () {
            const that = this;
            const url = $.stringFormat('{0}/personnelInfo/getServicePersonnel', $.cookie('url'));
            $.get(url, {"userType": 2}, function (res) {
                that.ServiceInfo = res.data;
            })
        },
        /**
         * 查询销售顾问
         */
        queryConsultant: function () {
            const that = this;
            const url = $.stringFormat('{0}/personnelInfo/getServicePersonnel', $.cookie('url'));
            $.get(url, {"userType": 1}, function (res) {
                that.ConsultantInfo = res.data;
            })
        },
        /**
         * 查询教练
         */
        queryCoach: function () {
            const that = this;
            const url = $.stringFormat('{0}/personlRole/getCoach', $.cookie('url'));
            $.get(url, function (res) {
                that.CoachInfo = res.data;
            })
        },
        /**
         * 查询门店
         */
        queryShop: function () {
            const that = this;
            const url = $.stringFormat('{0}/shop/getShopAll', $.cookie('url'));
            $.get(url, function (res) {
                that.ShopInfo = res.data;
            })
        },
        /**
         * 分页查询可认领客户列表
         */
        queryClaimingList: function (params) {
            const that = this;
            //显示加载中
            Loading.prototype.show();
            that.$nextTick(function () {
                try {
                    const url = $.stringFormat('{0}/frClient/getCollarList', $.cookie('url'));
                    axios.get(url, {params: params})
                        .then(function (res) {
                            let jsonData = eval(res);
                            if (jsonData['data']['code'] === '200') {
                                that.customerTable = jsonData['data']['data'];
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
         * 筛选-服务会稽
         */
        serviceSelect: function () {
            const that = this;
            let fwhjNameId = that.customerFilter.fwhjNameId;
            that.queryClaimingList({
                fwhjNameId: fwhjNameId,
            });
        },
        /**
         * 筛选-销售顾问
         */
        consultantSelect: function () {
            const that = this;
            let consultantId = that.customerFilter.consultantId;
            that.queryClaimingList({
                consultantId: consultantId,
            });
        },
        /**
         * 筛选-教练
         */
        coachSelect: function () {
            const that = this;
            let personalId = that.customerFilter.personalId;
            that.queryClaimingList({
                personalId: personalId,
            });
        },
        /**
         * 筛选-教练
         */
        shopSelect: function () {
            const that = this;
            let shopId = that.customerFilter.shopId;
            that.queryClaimingList({
                shopId: shopId,
            });
        },
        /**
         * 搜索手动跟进次数
         */
        searchManualCount: function () {
            const that = this;
            that.queryClaimingList({
                manualCount: that.customerFilter.manualCount,
            });
        },
        /**
         * 搜索自动跟进次数
         */
        searchAutomaticCount: function () {
            const that = this;
            that.queryClaimingList({
                automaticCount: that.customerFilter.automaticCount,
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
                that.queryClaimingList(jsonObj);
            } else {
                jsonObj.page = eval(page.page);
                jsonObj.limit = eval(page.limit);
                that.queryClaimingList(jsonObj);
            }
        },
        //认领
        claiming: function () {
            let that = this;
            var list = [];
            $("input[name='checkAll']:checked").each(function () {
                list.push($(this).val());
            });
            list = JSON.stringify(list).replace("[", "").replace("]", "").replace(/\"/g, "");
            const url = $.stringFormat('{0}/frClientPersonnelRelate/claiming', $.cookie('url'));
            $.get(url, {"cids": list, "type": "1"}, function (res) {
                if (res.code == '200') {
                    alert('认领成功')
                    location.reload();
                } else {
                    alert('认领失败')
                }
            })
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
            that.queryClaimingList({
                dateType: that.customerFilter.dateType,
                startTime: that.startTime,
                endTime: that.endTime,
            })
        },
        //全选,取消
        queryAll: function () {
            if ($("#pcheckbox").is(":checked") == true) {
                $("#myClaimingCustomersPotential").find('input').prop("checked", true);//全选
            } else {
                $("#myClaimingCustomersPotential").find('input').prop("checked", false);//取消
            }
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
            var url = $.stringFormat('{0}/excel/claimingPotential', $.cookie('url'));
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
    donefun: function (obj) {
        myClaimingCustomersPotential.customerFilter.startTime = obj.val;
        //重载数据
        myClaimingCustomersPotential.buildFilterDate();
    }
});


//初始化:结束日期
jeDate('.endDate', {
    format: "YYYY-MM-DD",
    donefun: function (obj) {
        myClaimingCustomersPotential.customerFilter.endTime = obj.val;
        //重载数据
        myClaimingCustomersPotential.buildFilterDate();
    }
});

