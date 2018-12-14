
const pcapp = new Vue({
    el: '#pcapp',
    data: {
        //潜在客户筛选
        customerFilter:{
            //客户等级
            customerLevel:'',
            //购买意向
            purchaseWill:'',
            //来源id
            sourceId:'',
            //预售状态
            presaleStatus:'',
            //是否有推荐人
            isReferrer:'',
            //日期类型
            dateType:0,
            //开始时间
            startTime:null,
            //结束时间
            endTime:null,
            //销售顾问/教练
            consultantName:null,
            //操作人
            updateUserName:null,
            //搜索关键字
            keyword:null,
        },
        //潜在客户列表
        customerTable:{
            list:[],
            currPage:1,
            pageSize:10,
            totalCount:0,
            totalPage:0,
        },
        //客户等级列表
        levelList:[],
        //客户来源类型列表
        sourceList:[],
    },
    computed:{


    },
    watch: {
        // 'customerFilter.customerLevel':'levelSelect',
        // 'customerFilter.purchaseWill':'purchaseWillSelect',
        // 'customerFilter.sourceId':'sourceIdSelect',
        // 'customerFilter.isReferrer':'isReferrerSelect',
        // 'customerFilter.presaleStatus':'presaleStatusSelect',
        // 'customerFilter.dateType':'resetFilterDate',
        'customerFilter.customerLevel':'buildFilterDate',
        'customerFilter.purchaseWill':'buildFilterDate',
        'customerFilter.sourceId':'buildFilterDate',
        'customerFilter.isReferrer':'buildFilterDate',
        'customerFilter.presaleStatus':'buildFilterDate',
        'customerFilter.dateType':'buildFilterDate',
    },
    methods:{
        /**
         * 查询等级
         */
        queryLevel:function () {
            const that = this;
            const url = $.stringFormat('{0}/frStoreCommon/getQzLevel',$.cookie('url'));
            axios.get(url)
            .then(function (res) {
                let jsonData = eval(res);
                if(jsonData['data']['code']==='200'){
                    that.levelList = jsonData['data']['data'];
                }else {
                    $.alert(jsonData['data']['msg'])
                }
            })
            .catch(function (error) {
                $.alert(error)
            });
        },
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
                        that.sourceList = jsonData['data']['data'];
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
         * 头像url拼接
         * @param avatarLink
         * @returns {*}
         */
        avatarUrl:function (avatarLink) {
            if(avatarLink === null||avatarLink ===''||typeof avatarLink ==='undefined'){
                return '../img/addImg.png';
            }
            return $.stringFormat("{0}/{1}/"+avatarLink, $.cookie('url'),$.cookie('imgPath'));
        },
        /**
         * 分页查询潜在客户列表
         */
        queryPotentialList:function (params) {
            const that  = this;
            //显示加载中
            Loading.prototype.show();
            that.$nextTick(function () {
                try {
                    const url = $.stringFormat('{0}/frClient/getPotentialList',$.cookie('url'));
                    axios.get(url,{params: params})
                        .then(function (res) {
                            let jsonData = eval(res);
                            if(jsonData['data']['code']==='200'){
                                that.customerTable = jsonData['data']['data'];
                                new myPagination({
                                    id: 'pagination',
                                    curPage:that.customerTable.currPage, //初始页码
                                    pageTotal: that.customerTable.totalPage, //总页数
                                    pageAmount: that.customerTable.pageSize,  //每页多少条
                                    dataTotal: that.customerTable.totalCount, //总共多少条数据
                                    showPageTotalFlag:true, //是否显示数据统计
                                    showSkipInputFlag:true, //是否支持跳转
                                    getPage: function (page) {
                                        //获取当前页数
                                        that.buildFilterDate({page:page,limit:that.customerTable.pageSize});
                                    }
                                })
                            }else {
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
                catch (ex){
                    console.log(ex);
                    $.alert(ex.message);
                }

            })
        },
        /**
         * 保留2位小数
         * @param arr
         * @param digit
         * @returns {*}
         */
        unitNormalization:function (arr,digit) {
            /*单位规整*/
            if((typeof arr)==='undefined'){
                return;
            }
            digit=digit||0;
            let realVal = Number(arr).toFixed(digit);
            return realVal;
        },
        /**
         * 筛选-选择等级
         */
        levelSelect:function(){
            const that = this;
            let levelId = that.customerFilter.customerLevel;
            that.queryPotentialList({
                customerLevel:levelId,
            });
        },
        /**
         * 筛选-选择购买意向
         */
        purchaseWillSelect:function(){
            const that = this;
            that.queryPotentialList({
                purchaseWill:that.customerFilter.purchaseWill,
            });
        },
        /**
         * 筛选-选择来源
         */
        sourceIdSelect:function(){
            const that = this;
            that.queryPotentialList({
                sourceId:that.customerFilter.sourceId,
            });
        },
        /**
         * 筛选-选择是否有推荐人
         */
        isReferrerSelect:function(){
            const that = this;
            that.queryPotentialList({
                isReferrer:Number(that.customerFilter.isReferrer),
            });
        },
        /**
         * 筛选-选择预售状态
         */
        presaleStatusSelect:function(){
            const that = this;
            that.queryPotentialList({
                presaleStatus:that.customerFilter.presaleStatus,
            });
        },
        /**
         * 搜索销售顾问/教练
         */
        searchForConsultantName:function () {
            const that = this;
            that.queryPotentialList({
                consultantName:that.customerFilter.consultantName,
            });
        },
        /**
         * 搜索操作人
         */
        searchForUpdateUserName:function () {
            const that = this;
            that.queryPotentialList({
                updateUserName:that.customerFilter.updateUserName,
            });
        },
        /**
         * 搜索关键字
         */
        searchForKeyword:function () {
            const that = this;
            that.queryPotentialList({
                keyword:that.customerFilter.keyword,
            });
        },
        /**
         * 重置筛选数据
         */
        resetFilterDate:function () {
            let that = this;
            that.customerFilter.startTime = null;
            that.customerFilter.endTime = null;
        },
        /**
         * 组装筛选数据直接查询
         */
        buildFilterDate:function (page) {
            let that = this;
            let jsonObj = {};
            for(let item in that.customerFilter){
                let jsonData = eval(that.customerFilter);
                jsonObj[item] = jsonData[item];
            }
            if (typeof(eval(page.page)) == "undefined") {
                that.queryPotentialList(jsonObj);
            } else {
                jsonObj.page=eval(page.page);
                jsonObj.limit= eval(page.limit);
                console.log(jsonObj)
                that.queryPotentialList(jsonObj);
            }
        },
        /**
         * 删除客户
         */
        delPotentialCustomers:function (id,index) {
            let that = this;
            $.confirm({
                title: '确认',
                content: '确认删除?',
                buttons: {
                    ok: {
                        text: '确认',
                        btnClass: 'btn-primary',
                        action: function() {
                            const url = $.stringFormat('{0}/frClient/postDelPotentialCustomer',$.cookie('url'));
                            let param = new FormData();
                            param.append('id', id);
                            axios.post(url,param)
                                .then(function (res) {
                                    let resData = eval(res);
                                    //在vue数据对象中删除那条记录
                                    that.customerTable.list.splice(index, 1);
                                    // 重新计算总页数
                                    that.customerTable.totalCount--;
                                    let oldTotalPage = that.customerTable.totalPage;
                                    that.customerTable.totalPage = Math.ceil(that.customerTable.totalCount / 10);
                                    // 如果当前总页数<没删除之前的总页数 直接查看最后一页
                                    if(that.customerTable.totalPage<oldTotalPage){
                                        that.queryPotentialList({page:that.customerTable.totalPage})
                                    }else {
                                        that.queryPotentialList({page:that.customerTable.currPage})
                                    }
                                    $.alert(resData['data']['msg']);
                                })
                                .catch(function (error) {
                                    console.log(error);
                                    $.alert(error)
                                });
                        }
                    },
                    cancel: {
                        text: '取消',
                        btnClass: 'btn-primary'
                    }
                }
            });
        }
    }
});


// 初始化加载中插件
Loading.prototype.init();
pcapp.queryLevel();
pcapp.queryResourceList();
pcapp.queryPotentialList();


//初始化:开始日期
jeDate('.startDate', {
    format: "YYYY-MM-DD",
    donefun: function(obj){
        pcapp.customerFilter.startTime = obj.val;
        //重载数据
        pcapp.buildFilterDate();
    }
});


//初始化:结束日期
jeDate('.endDate', {
    format: "YYYY-MM-DD",
    donefun: function(obj){
        pcapp.customerFilter.endTime = obj.val;
        //重载数据
        pcapp.buildFilterDate();
        // const that = pcapp.$data.customerFilter;
        // pcapp.queryPotentialList({
        //     dateType:that.dateType,
        //     startTime:that.startTime,
        //     endTime:that.endTime,
        // })


    }
});





