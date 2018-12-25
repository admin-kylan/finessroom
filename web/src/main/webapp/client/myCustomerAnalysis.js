var myCustomerAnalysis = new Vue({
    el: '#myCustomerAnalysis',
    data: {
        parameterList:{
            status:[               //卡状态
                {"vue":0,"lav":"正常"},
                {"vue":1,"lav":"停卡"},
                {"vue":2,"lav":"冻结"},
                {"vue":3,"lav":"已过期"},
                {"vue":4,"lav":"未开卡"},
                {"vue":5,"lav":"待补余"},
                {"vue":6,"lav":"历史"}
            ],
            auditStatus:[     //审核状态
                {"vue":0,"lav":"待审核"},
                {"vue":1,"lav":"已审核"},
                {"vue":2,"lav":"审核不通过"},
            ],
        },
        //客户信息分析筛选
        customerFilter: {
            //性别
            sex: '',
            //了解渠道
            sourceId: '',
            //从事行业
            industryId: '',
            //交通工具
            vehicleId: '',
            //累计金额
            beginMoney: '',
            //累计金额
            endMoney: '',
            //购卡数量筛选
            cardCount: '',
            //搜索关键字
            keyword: null,
        },
        //客户信息分析客户列表
        customerTable: {
            list: [],
            currPage: 1,
            pageSize: 10,
            totalCount: 0,
            totalPage: 0,
        },
        //从事行业
        IndustryInfo: [],
        //交通工具
        VehicleInfo: [],
        //了解渠道
        SourceInfo: [],
        LiIndex: 0,
        tabSpanspan: 0,
        WeekMonth: 0,
        ServicePersonnelInfo:[],
        CardInfo:[],
        ClientInfo:[],
    },
    crated: function () {
    },
    watch: {
        'customerFilter.sourceId': 'buildFilterDate',
        'customerFilter.industryId': 'buildFilterDate',
        'customerFilter.vehicleId': 'buildFilterDate',
        'customerFilter.sex': 'buildFilterDate',
    },
    methods: {
        init: function (t) {
            const that = this;


            that.queryResourceList();
            that.queryVehicle();
            that.queryIndustry();
            that.queryClientList();
        },
        mounted: function () {


        },
        /**
         * 查询了解渠道
         */
        /**
         * 查询来源渠道列表
         */
        queryResourceList:function () {
            const that  = this;
            const url = $.stringFormat('{0}/frClientSource/getListByIsAuto',$.cookie('url'));
            axios.get(url)
                .then(function (res) {
                    let jsonData = eval(res);
                    if(jsonData['data']['code']==='200'){
                        that.SourceInfo = jsonData['data']['data'];
                    }else {
                        $.alert(jsonData['data']['msg'])
                    }
                })
                .catch(function (error) {
                    $.alert("请求发生错误！")
                    console.log(error);
                });
        },
        /**
         * 查询交通工具
         */
        queryVehicle: function () {
            const that = this;
            const url = $.stringFormat('{0}/frClientLifeType/getVehicle',$.cookie('url'));
            axios.get(url)
                .then(function (res) {
                    let jsonData = eval(res);
                    if(jsonData['data']['code']==='200'){
                        that.VehicleInfo = jsonData['data']['data'];
                    }else {
                        $.alert(jsonData['data']['msg'])
                    }
                })
                .catch(function (error) {
                    $.alert("请求发生错误！")
                    console.log(error);
                });
        },
        /**
         * 查询从事行业
         */
        queryIndustry: function () {
            const that = this;
            const url = $.stringFormat('{0}/frClientWorkType/getIndustry',$.cookie('url'));
            axios.get(url)
                .then(function (res) {
                    let jsonData = eval(res);
                    if(jsonData['data']['code']==='200'){
                        that.IndustryInfo = jsonData['data']['data'];
                    }else {
                        $.alert(jsonData['data']['msg'])
                    }
                })
                .catch(function (error) {
                    $.alert("请求发生错误！")
                    console.log(error);
                });
        },
        /**
         * 分页查询客户分配列表
         */
        queryClientList: function (params) {
            const that = this;
            //显示加载中
            Loading.prototype.show();
            that.$nextTick(function () {
                try {
                    const url = $.stringFormat('{0}/frClient/getClientInformation', $.cookie('url'));
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
         * 筛选-性别
         */
        sexSelect: function () {
            const that = this;
            let sex = that.customerFilter.sex;
            that.queryClientList({
                sex: sex,
            });
        },
        /**
         * 筛选-了解渠道
         */
        sourceSelect: function () {
            const that = this;
            let sourceId = that.customerFilter.sourceId;
            that.queryClientList({
                sourceId: sourceId,
            });
        },
        /**
         * 筛选-交通工具
         */
        vehicleSelect: function () {
            const that = this;
            let vehicleId = that.customerFilter.vehicleId;
            that.queryClientList({
                vehicleId: vehicleId,
            });
        },
        /**
         * 筛选-从事行业
         */
        industrySelect: function () {
            const that = this;
            let industryId = that.customerFilter.industryId;
            that.queryClientList({
                industryId: industryId,
            });
        },
        /**
         * 搜索累计金额
         */
        searchMoneyCount: function () {
            const that = this;
            that.queryClientList({
                beginMoney: that.customerFilter.beginMoney,
                endMoney: that.customerFilter.endMoney,
            });
        },
        /**
         * 搜索购卡数量
         */
        searchCardCount: function () {
            const that = this;
            that.queryClientList({
                cardCount: that.customerFilter.cardCount,
            });
        },
        /**
         * 组装筛选数据直接查询
         */
        buildFilterDate: function (page) {
            let that = this;
            let jsonObj = {};
            for (let item in that.customerFilter) {
                let jsonData = eval(that.customerFilter);
                jsonObj[item] = jsonData[item];
            }
            if (typeof(eval(page.page)) == "undefined") {
                that.queryClientList(jsonObj);
            } else {
                jsonObj.page = eval(page.page);
                jsonObj.limit = eval(page.limit);
                that.queryClientList(jsonObj);
            }
        },
        //查看门店  移入
        // lookShop:function(obj){
        //     $('.tabBox0').css({
        //         'display': 'block',
        //         'top': $(this).offset().top + $('.tableWrap').scrollTop() - 135
        //     });
        //     console.log(obj);
        // },
        // //移出
        // outShop:function(obj){
        //     $('.tabBox0').css({
        //         'display': 'none'
        //     });
        // },
        //查看客户信息  移入
        lookClient:function(obj){
            const that =  event.currentTarget;
            var thatT = this;
            $('.tabBox1').css({
                'display': 'block',
                'top': $(that).offset().top + $('.tableWrap').scrollTop() - 135
            });
            thatT.ClientInfo = obj;
            thatT.ClientInfo.birthday=timeFormatDate(thatT.ClientInfo.birthday);
        },
        //移出
        outClient:function(obj){
            $('.tabBox1').css({
                'display': 'none'
            });
        },
        //查看服务人员  移入
        lookService:function(obj,event){
            const that =  event.currentTarget;
            var thatT = this;
            $('.tabBox2').css({
                'display': 'block',
                'top': $(that).offset().top + $('.tableWrap').scrollTop() - 135
            });
            thatT.ServicePersonnelInfo = obj;
        },
        //移出
        outService:function(){
            $('.tabBox2').css({
                'display': 'none'
            });
        },
        //查看购卡信息  移入
        lookCard:function(obj){
            console.log(obj)
            const that =  event.currentTarget;
            var thatT = this;
            $('.tabBox3').css({
                'display': 'block',
                'top': $(that).offset().top + $('.tableWrap').scrollTop() - 135
            });
            thatT.CardInfo = obj;
            $.each(thatT.CardInfo, function (i, n) {
                n.createTime = timeFormatDate(n.createTime);
            })
        },
        //移出
        outCard:function(){
            $('.tabBox3').css({
                'display': 'none'
            });
        },

    }
});

Loading.prototype.init();
