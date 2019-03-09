/**
 * 筛查
 */
const ecapp = new Vue({
    el: '#c_rig2',
    data: {
        /*****筛选*****/
        //会员等级
        level:{
            selected: '',
            list:[],

        },
        //会员卡状态
        vipCardStatus:'',
        //销售顾问/教练Id
        salespersonId:'',
        //已购项目Id
        purchasedItemId:'',
        //保护天数
        protectDay:null,
        //日期类型
        dateType:'1',
        //开始时间
        startTime:'',
        //结束时间
        endTime:'',
        //搜索关键字
        keyword:'',
        /*****统计(会员总数/近一周生日会员数/本周新增会员数)*****/
        existenceStat:{
            count:0,
            birthdayCount:0,
            addCount:0,
        },
        //现有客户列表
        existence:{
            list:[],
            currPage:1,
            pageSize:10,
            totalCount:0,
            totalPage:0,
        },
        //分页BoxId
        pageBoxId:'',
    },

    computed:{

    },
    watch: {
        'level.selected':'levelSelect',
    },
    filters: {
        formatDate: function (time,type,typeT) {
            if(!time){
                return '';
            }
            var _time = timeFormatDate(time,type,typeT);
            return _time;
        },
    },
    methods:{
        init:function(val){
            var domain = window.location.host;
            $.cookie('url','http://'+domain+'/')
            console.log(111)
        },
        /**
         * 获取所有会员等级
         */
        initlevels:function () {
            let that = this;
            let url = $.stringFormat('{0}/frLevel/getListForSelect',$.cookie('url'));
            axios.get(url).then(function (res) {
                let resData = eval(res);
                if(resData['data']['code'] === '200'){
                    that.level.list = resData['data']['data'];
                }else {
                    $.alert(resData['data']['msg']);
                }
            })
            .catch(function (error) {
                $.alert(error);
            });
        },
        /**
         * 判断等级样式
         * @param level
         * @returns {*}
         */
        levelClass :function(level) {
            switch(level) {
                case '普通会员':
                    return 'togreen';
                case '银卡会员':
                    return 'toyen';
                case '金卡会员':
                    return 'toyellow';
                case '钻石会员':
                    return 'tored';
            }
        },
        //头像url拼接
        avatarUrl:function (avatarLink) {
            if(avatarLink === null||avatarLink ===''||typeof avatarLink ==='undefined'){
                return '../img/addImg.png';
            }
            return avatarLink
        },
        /**
         * 状态判断
         * @param status
         * @returns {{class: string, text: string}}
         */
        statusJudge:function (status) {
            var res = {};
            switch(status) {
                case 0:
                    res.class = 'tonormal';
                    res.text = '正常';
                    res.operateClass = '';
                    res.operate = '打印';
                    return res;
                case 1:
                    res.class = 'tored';
                    res.text = '未付款';
                    res.operateClass = 'tored';
                    res.operate = '去付款';
                    return res;
            }
        },
        /**
         * 根据保护天数搜索
         */
        searchByProtectDay:function () {
            var that  = this;
            that.initExistenceList({protectDay:that.protectDay});
            that.initFilter();

        },
        /**
         * 初始化筛选数据
         */
        initFilter:function () {
            var that = this;
            that.vipCardStatus='';
            that.startTime='';
            that.endTime='';
//          that.keyword='';
            that.protectDay=null;
            that.level.selected='';
        },
        /**
         * 点击搜索
         */
        searchByKeyword :function() {
            var that  = this;
            if(that.keyword == ''||that.keyword == null){
                that.initExistenceList({
                    page:that.existence.currPage,
                    limit:that.existence.pageSize,
                });
            }else {
                that.initExistenceList({
                    page:that.existence.currPage,
                    limit:that.existence.pageSize,
                    keyword:that.keyword,
                });
            }
            that.initFilter();
        },
        /**
         * 获取现有客户统计数据
         */
        initExistenceStat:function () {
            const that  = this;
            const url = $.stringFormat('{0}/frClient/getExistenceStat',$.cookie('url'));
            axios.get(url)
            .then(function (res) {
                let jsonData = eval(res);
                if(jsonData['data']['code']==='200'){
                    that.existenceStat = jsonData['data']['data'];
                }else {
                    $.alert(jsonData['data']['msg'])
                }
            })
            .catch(function (error) {
                console.log(error);
            });
        },
        /**
         * 获取现有客户列表数据
         */
        initExistenceList:function (params) {
            const that  = this;
            console.log("加载现有客户列表===================================================");
            //显示加载中
            Loading.prototype.show();
            that.$nextTick(function () {
                try {
                    const url = $.stringFormat('{0}/frClient/getExistenceList',$.cookie('url'));
                    axios.get(url,{params: params})
                        .then(function (res) {
                            console.log(res);
                            let jsonData = eval(res);
                            if(jsonData['data']['code']==='200'){
                                that.existence = jsonData['data']['data'];
                                new myPagination({
                                    id: 'pagination',
                                    curPage:that.existence.currPage, //初始页码
                                    pageTotal: that.existence.totalPage, //总页数
                                    pageAmount: that.existence.pageSize,  //每页多少条
                                    dataTotal: that.existence.totalCount, //总共多少条数据
                                    showPageTotalFlag:true, //是否显示数据统计
                                    showSkipInputFlag:true, //是否支持跳转
                                    getPage: function (page) {
                                        //获取当前页数
                                        that.initExistenceList({page:page,limit:that.existence.pageSize});
                                    }
                                })
                            }else {
                                $.alert(jsonData['data']['msg'])
                            }
                            //隐藏加载中
                            Loading.prototype.hide();
                        })
                        .catch(function (error) {
                            $.alert(error)
                        });
                }
                catch (ex){
                    $.alert(ex.message);
                }

            })

        },
        /**
         * 测试
         */
        test:function () {
            const url = $.stringFormat("{0}/PersonnelInfos/PostLoginSystem", $.cookie('liandengUrl'));
            let params = new URLSearchParams();
            params.append('Account', '13215026952');
            params.append('Password', 'e10adc3949ba59abbe56e057f20f883e');
            axios({
                method:'POST',
                url:url,
                data:params,
            }).then(function(res){
                let resData = eval(res);
                if (resData['data']['Code']===1){
                    $.alert(resData['data']['Msg']);
                    let userInfo = resData['data']['ReturnData'][0];
                    $.cookie('userId', userInfo.UserId, {expires:7, path: '/'});
                    $.cookie('userName', userInfo.UserName, {expires:7, path: '/'});
                    $.cookie('token', userInfo.Token, {expires:7, path: '/'});
                    $.cookie('isTourist', userInfo.IsTourist, {expires:7, path: '/'});
                    $.cookie('imageName', userInfo.ImageName, {expires:7, path: '/'});
                    $.cookie('imagePath', userInfo.ImagePath, {expires:7, path: '/'});
                }else {
                    $.alert(res['data']['Msg']);
                }
            }).catch(function (error) {
                $.alert(error);
                // console.log(error);
            });
        },
        /**
         * 筛选-选择等级
         */
        levelSelect:function(){
            const that = this;
            let levelId = that.level.selected;
                that.initExistenceList({
                limit:that.existence.pageSize,
                levelId:levelId,
            });
        },
        /**
         * 点击时间
         * @param type [1.今天 2.本月 3.今年]
         */
        clickTime:function (type) {
            const that = this;
            //今天
            if(type === 1){
                let timestamp = Date.parse(new Date()) / 1000;
                let date = jeDate.timeStampDate(timestamp.toString(),'YYYY-MM-DD');
                that.startTime = date;
                that.endTime = date;
            }
            //本月
            if(type === 2){
                that.startTime = getMonthStartDate();
                that.endTime = getMonthEndDate();
            }
            //今年
            if(type === 3){
                that.startTime = getYearStartDate();
                that.endTime = getYearEndDate();
            }
            //重载数据
            that.initExistenceList({
                dateType:that.dateType,
                startTime:that.startTime,
                endTime:that.endTime,
            })
        },
        tabalOperate:function (id,status) {
            if(status===0){
                $.alert("打印 : "+id);
            }
            if(status===1){
                $.alert("去结账 : "+id);
            }

            
        },
        // initAppText:function(){
        //     var code = "003";
        //     var data = {
        //         CustomerCode: "003",
        //     }
        //     var mess = '/frCardType/getCardTypeByShopIdList';
        //     var url = $.stringFormat('{0}'+mess,$.cookie('url'));
        //     console.log(url+'?CustomerCode='+code);
        //     console.log(data);
        //     $.get(url,data,function(res){
        //         if(res.code=='200'){
        //             console.log("测试App接口数据=================start");
        //             console.log(res);
        //             console.log("测试App接口数据=================end");
        //         }else {
        //             alert(res.msg)
        //         }
        //
        //     }) ;
        // }

    }
})

// 初始化加载中插件
Loading.prototype.init();
//初始化等级数据
ecapp.initlevels();
//初始化现有客户统计
ecapp.initExistenceStat();
//初始化现有客户列表
ecapp.initExistenceList({page:ecapp.$data.existence.currPage,limit:ecapp.$data.existence.pageSize});

// // 测试App接口
// ecapp.initAppText();


//初始化:开始日期
jeDate('.startDate', {
    format: "YYYY-MM-DD",
    donefun: function(obj){
        ecapp.startTime = obj.val;
    }
});
//初始化:结束日期
jeDate('.endDate', {
    format: "YYYY-MM-DD",
    donefun: function(obj){
        ecapp.endTime = obj.val;
        const that = ecapp.$data;
        //重载数据
        ecapp.initExistenceList({
            dateType:that.dateType,
            startTime:that.startTime,
            endTime:that.endTime,
        })
    }
});



