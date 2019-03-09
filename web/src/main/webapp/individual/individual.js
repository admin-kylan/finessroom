const app = new Vue({
    el: '#app',
    data: {
        startTime:'',
        endTime:'',
        isStatic:'',
        updateBy:'',
        updateTime:'',
        //所在门店
        whereShop:{
            list:[],
        },
        //单个门店设置
        storeSingleSet:{
            createTime: "",
            createUser: "",
            qzGjHour: 24,
            qzGjStatus: 1,
            showSqr: 1,
            showXsr: 1,
            updateTime: "",
            updateUser: "",
            xyGjHour: 24,
            xyGjStatus: 1
        },
        //客户保护天数设置
        protectionDaysSet:{
            list:[],
        },
        //客户认领设置
        claimSet:{
            list:[],
        },
        //现有客户跟进管理
        xyFollowSet:{
            list:[],
        },
        //潜在客户跟进管理
        qzFollowSet:{
            list:[],
        },
        //关键字列表
        keywordList:{
            list:[],
            keyword:'',
        },
        //通用门店设置
        commonSet:{
            xyShowMobile:0,
            showCardNum:0,
            memberTransfer:0,
            xsUpdate:0,
            redDisabled:15,
            redBirthday:3,
            openUpdate:0,
            stopUpdate:0,
            cztk:0,
            invoiceUpdate:0,
            zrhb:0,
            qzShowMobile:0,
            qzXsgwUpdate:0,
            qzLevel:'',
            qzLevels:[],
            tjyInputTime:0,
            showMobile:1,
            updateTime:'2018-09-05 14:42:11',
            updateUser:'隔壁老王',
            CustomerCode:null,
            czjeUses:[-1],
        },
        //作废协议号设置
        invalidProtocolSet:{
            list:[],//列表数据
            currPage:1,//当前页
            pageSize:10,//每页条数
            totalCount:0,//总条数
            totalPage:0,//总页数
            invalidProtocolNo:null
        },
        //作废会员号设置
        invalidVIPCardSet:{
            list:[],//列表数据
            currPage:1,//当前页
            pageSize:10,//每页条数
            totalCount:0,//总条数
            totalPage:0,//总页数
            invalidVIPCardNo:null
        },
        //协议号管理
        protocolNumMgt:{
            list:[],
            batchNum:null,
        startNo:null,
        endNo:null,
        //协议号是否开启排除数字
        useLimitNum:false,
        limitNum:4,
        // 头部
        useHeadNum:false,
        // 中间
        useMiddleNum:false,
        // 尾部
        useTailNum:false,
    },
        //会员号管理
        VIPCardMgt:{
            list:[],
            batchNum:null,
            startNo:null,
            endNo:null,
            //会员号是否开启排除数字
            useLimitNum:false,
            limitNum:4,
            // 头部
            useHeadNum:false,
            // 中间
            useMiddleNum:false,
            // 尾部
            useTailNum:false
        }
        ,Span:false,
        existence:{
            list:[],
            currPage:1,
            pageSize:10,
            totalCount:0,
            totalPage:0,
        },
        existenceType:0
    },
    computed:{
        //协议号管理计算公式：结束编号 = 开始编号 + 数量 - 去除数字n的数量 - 1
        protocolNumMgtEndNoResult:{
            get(){
                const that = this;
                let endNo = null;
                if(that.protocolNumMgt.startNo!==null&&that.protocolNumMgt.batchNum!==null){
                    // 组装数据(是否需要排除某个数字的input复选框)
                    let position = {
                        useHeadNum:that.protocolNumMgt.useHeadNum,
                        useMiddleNum: that.protocolNumMgt.useMiddleNum,
                        useTailNum: that.protocolNumMgt.useTailNum,
                        useLimitNum: that.protocolNumMgt.useLimitNum,
                    };
                    //计算结算编号
                    endNo = genByBenginAndNum(Number(this.protocolNumMgt.startNo),Number(this.protocolNumMgt.batchNum),Number(this.protocolNumMgt.limitNum),position).end;              // 如果结算编号超过12位数字，默认999999999999
                    if(endNo.toString().length>12){
                        endNo = 999999999999;
                    }
                }
                //赋值结束编号
                that.protocolNumMgt.endNo = endNo;
                return endNo;
            },
            set(value){
            }
        },
        //会员号管理计算公式：结束编号 = 开始编号 + 数量 - 1
        VIPCardMgtEndNoResult:{
            get(){
                const that = this;
                let endNo = null;
                if(that.VIPCardMgt.startNo!==null&&that.VIPCardMgt.batchNum!==null){
                    // 组装数据(是否需要排除某个数字的input复选框)
                    let position = {
                        useHeadNum:that.VIPCardMgt.useHeadNum,
                        useMiddleNum: that.VIPCardMgt.useMiddleNum,
                        useTailNum: that.VIPCardMgt.useTailNum,
                        useLimitNum: that.VIPCardMgt.useLimitNum,
                    };
                    //计算结算编号
                    endNo = genByBenginAndNum(Number(this.VIPCardMgt.startNo),Number(this.VIPCardMgt.batchNum),Number(this.VIPCardMgt.limitNum),position).end;
                    // 如果结算编号超过12位数字，默认999999999999
                    if(endNo.toString().length>12){
                        endNo = 999999999999;
                    }
                }
                //赋值结束编号
                that.VIPCardMgt.endNo = endNo;
                return endNo;
            },
            set(value){
            }
        },
    },
    watch: {
        // //监听协议号规则是否排除某个数字
        // 'protocolNumMgt.useLimitNum':function () {
        //     var that = this;
        //     if(!that.protocolNumMgt.useLimitNum){
        //         that.protocolNumMgt.useHeadNum = false;
        //         that.protocolNumMgt.useMiddleNum = false;
        //         that.protocolNumMgt.useTailNum = false;
        //     }
        // },
        // //监听会员卡号规则是否排除某个数字
        // 'VIPCardMgt.useLimitNum':function () {
        //     var that = this;
        //     if(!that.VIPCardMgt.useLimitNum){
        //         that.VIPCardMgt.useHeadNum = false;
        //         that.VIPCardMgt.useMiddleNum = false;
        //         that.VIPCardMgt.useTailNum = false;
        //     }
        // },

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
    	/**
         * @param Intger(传0为导出数据,传1为下载模板)
         * @returns {*}
         */ 
    	clientUpload(type){
    		var that=this;
    		var url
    		if(that.existenceType==0){
            	url = $.stringFormat("{0}/excel/client_upload",$.cookie('url'));
            }else{
            	url = $.stringFormat("{0}/excel/prospective_client",$.cookie('url'));
            }
    		axios.get(url,{params:{
    			Intger:type,
    			page:that.existence.currPage,
                limit:that.existence.pageSize
    		}}).then(function(res){
    			console.log(res)
    		})
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
    	statusJudge:function (status) {
            var res = {};
            switch(status) {
                case 0:
                    res.class = 'tonormal';
                    res.text = '正常';
                    res.operateClass = '';
                    res.operate = '打印';
                    return res;
                default:
                    res.class = 'tored';
                    res.text = '未付款';
                    res.operateClass = 'tored';
                    res.operate = '去付款';
                    return res;
            }
        },
        changeType:function(idx){
        	if(this.existenceType!=idx){
        		this.existenceType=idx;
        		this.getExistenceList(1);
        	}
        },
    	getExistenceList:function(page){
    		var that=this;
            Loading.prototype.show();
            var url,id;
            if(that.existenceType==0){
            	url = $.stringFormat("{0}/frClient/getExistenceList",$.cookie('url'));
            	id='pagination'
            }else{
            	url = $.stringFormat("{0}/frClient/getPotentialListBG",$.cookie('url'));
            	id='pagination1'
            }
    		axios.get(url,{params:{
                	page:page || that.existence.currPage,
                	limit:that.existence.pageSize
                }}).then(function(res){
                //隐藏加载中
                Loading.prototype.hide();
            	var jsonData=res.data;
            	if(jsonData.code==='200'){
                    that.existence = jsonData.data;
	            	new myPagination({
	                    id: id,
	                    curPage:jsonData.data.currPage, //初始页码
	                    pageTotal: jsonData.data.totalPage, //总页数
	                    pageAmount: jsonData.data.pageSize,  //每页多少条
	                    dataTotal: jsonData.data.totalCount, //总共多少条数据
	                    showPageTotalFlag:true, //是否显示数据统计
	                    showSkipInputFlag:true, //是否支持跳转
	                    getPage: function (page) {
	                        //获取当前页数
	                        that.getExistenceList(page);
	                    }
	                })
            	}
            }).catch(function (error) {
                //隐藏加载中
                Loading.prototype.hide();
                $.alert(error)
            });
    	},
        //阿拉伯数字转中文数字
        toChinese:function (i) {
            return toZhDigit(i);
        },
        //增加报废协议号
        addInvalidProtocolNo:function () {
            //TODO 作废协议号
            // var that = this;
            // var url = $.stringFormat("{0}/frAgreement/updateInvalid",$.cookie('url'));
            // if(this.invalidProtocolSet.invalidProtocolNo==null){
            //     $.alert("输入框不能为空");
            //     return;
            // }
            // axios({
            //     method: 'post',
            //     url: url,
            //     params:{
            //         protocolNo:this.invalidProtocolSet.invalidProtocolNo,
            //         type:2
            //     }
            // })
            // .then(function (res) {
            //     var resData = eval(res);
            //     if(resData['data']['code']=='200'){
            //         initAgreementList();
            //     }else {
            //         $.alert(resData['data']['msg'])
            //     }
            // })
            // .catch(function (error) {
            //     $.alert(error)
            // });
        },
        //删除报废协议号
        deleteProtocolNo:function (value,index) {
            //TODO 删除作废协议号
            // var that = this;
            // var url = $.stringFormat("{0}/frAgreement/updateInvalid",$.cookie('url'));
            // $.confirm({
            //     title: '确认',
            //     content: '确认删除?',
            //     buttons: {
            //         ok: {
            //             text: '确认',
            //             btnClass: 'btn-primary',
            //             action: function() {
            //                 axios.post(url,{
            //                     id:value,
            //                     type:1
            //                 })
            //                     .then(function (res) {
            //                         var resData = eval(res);
            //                         if(resData['data']['code']=='200'){
            //                             that.invalidProtocolSet.list.splice(index, 1)
            //                             that.invalidProtocolSet.totalCount--;
            //                             var oldTotalPage = that.invalidProtocolSet.totalPage;
            //                             that.invalidProtocolSet.totalPage = Math.ceil(that.invalidProtocolSet.totalCount / 10);
            //                             if(that.invalidProtocolSet.totalPage<oldTotalPage){
            //                                 initAgreementList({page:that.invalidProtocolSet.totalPage})
            //                             }else {
            //                                 initAgreementList({page:that.invalidProtocolSet.currPage})
            //                             }
            //                         }else {
            //                             $.alert(resData['msg']);
            //                         }
            //                     })
            //                     .catch(function (error) {
            //                         $.alert(error)
            //                     });
            //             }
            //         },
            //         cancel: {
            //             text: '取消',
            //             btnClass: 'btn-primary'
            //         }
            //     }
            // });
        },
        //作废会员号
        addInvalidVIPCardNo:function () {
            let that = this;
            let url = $.stringFormat("{0}/frCard/postAddInvalidCardNo",$.cookie('url'));
            if(this.invalidVIPCardSet.invalidVIPCardNo===null){
                $.alert("输入框不能为空");
                return;
            }
            axios({
                method: 'post',
                url: url,
                data:{
                    cardNo:this.invalidVIPCardSet.invalidVIPCardNo,
                }
            })
            .then(function (res) {
                let resData = eval(res);
                if(resData['data']['code']==='200'){
                    $.alert(resData['data']['msg'])
                    initInvalidCardNoList();
                }else {
                    $.alert(resData['data']['msg'])
                }
            })
            .catch(function (error) {
                $.alert(error)
            });
        },
        //删除作废会员号
        deleteInvalidVIPCard:function(value,index){
            let that = this;
            let url = $.stringFormat("{0}/frCard/postDelInvalidCardNo",$.cookie('url'));
            $.confirm({
                title: '确认',
                content: '确认删除?',
                buttons: {
                    ok: {
                        text: '确认',
                        btnClass: 'btn-primary',
                        action: function() {
                            axios.post(url,{id:value})
                            .then(function (res) {
                                let resData = eval(res);
                                if(resData['data']['code']==='200'){
                                    //删除后重新计算总页数
                                    that.invalidVIPCardSet.list.splice(index, 1);
                                    that.invalidVIPCardSet.totalCount--;
                                    let oldTotalPage = that.invalidVIPCardSet.totalPage;
                                    that.invalidVIPCardSet.totalPage = Math.ceil(that.invalidVIPCardSet.totalCount / 10);
                                    // 如果当前总页数<没删除之前的总页数 直接查看最后一页
                                    if(that.invalidVIPCardSet.totalPage<oldTotalPage){
                                        initInvalidCardNoList({page:that.invalidVIPCardSet.totalPage})
                                    }else {
                                        initInvalidCardNoList({page:that.invalidVIPCardSet.currPage})
                                    }
                                }else {
                                    $.alert(resData['msg']);
                                }
                            })
                            .catch(function (error) {
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
        },

        // 增加协议号规则
        addProtocols:function () {
            let that = this;
            let url = $.stringFormat("{0}/frAgreement/postAdd",$.cookie('url'));
            //检查参数
            if(this.protocolNumMgt.batchNum===null||this.protocolNumMgt.startNo===null||this.protocolNumMgt.endNo===null){
                $.alert("请输入范围!");
                return;
            }
            if(this.protocolNumMgt.position!==null){
                if(this.protocolNumMgt.limitNum===null){
                    $.alert("请输入要去除的数字");
                    return;
                }
            }
            if(this.protocolNumMgt.startNo.toString().length<9||this.protocolNumMgt.startNo.toString().length>12){
                $.alert("范围在9到12个数字之间");
                return;
            }
            // 组装数据(是否需要排除复选框)
            let position = {
                useHeadNum:that.protocolNumMgt.useHeadNum,
                useMiddleNum: that.protocolNumMgt.useMiddleNum,
                useTailNum: that.protocolNumMgt.useTailNum,
                useLimitNum: that.protocolNumMgt.useLimitNum,
            };
            //检查排除的数字和开始编号是否冲突
            new checkBegin(Number(that.protocolNumMgt.startNo),Number(that.protocolNumMgt.limitNum),position);
            // 组装规则对象
            let jsonData = {
                batchNum : Number(that.protocolNumMgt.batchNum),
                startNo : String(that.protocolNumMgt.startNo),
                endNo : String(that.protocolNumMgt.endNo),
                useHeadNum:that.protocolNumMgt.useHeadNum,
                useMiddleNum: that.protocolNumMgt.useMiddleNum,
                useTailNum: that.protocolNumMgt.useTailNum,
                useLimitNum: that.protocolNumMgt.useLimitNum,
                limitNum : that.protocolNumMgt.limitNum,
            };
            console.log(jsonData);
            //显示加载中
            Loading.prototype.show();
            // ajax提交
            axios.post(url, jsonData)
            .then(function (res) {
                let resData = eval(res);
                $.alert(resData['data']['msg']);
                //隐藏加载中
                Loading.prototype.hide();
                initProtocolGroupList();
            })
            .catch(function (error) {
                //隐藏加载中
                Loading.prototype.hide();
                $.alert(error)
            });
        },
        // 增加会员号规则
        addVIPCard:function () {
            let that = this;
            let url = $.stringFormat("{0}/frCardNum/postAdd",$.cookie('url'));
            //检查参数
            if(this.VIPCardMgt.batchNum===null||this.VIPCardMgt.startNo===null||this.VIPCardMgt.endNo===null){
                $.alert("请输入范围");
                return;
            }
            if(this.VIPCardMgt.position!==null&&this.VIPCardMgt.notInNum===null){
                $.alert("请输入要去除的数字");
                return;
            }
            if(this.VIPCardMgt.startNo.toString().length<9||this.VIPCardMgt.startNo.toString().length>12){
                $.alert("范围在9到12个数字之间");
                return;
            }
            // 组装数据(是否需要排除复选框)
            let position = {
                useHeadNum:that.VIPCardMgt.useHeadNum,
                useMiddleNum: that.VIPCardMgt.useMiddleNum,
                useTailNum: that.VIPCardMgt.useTailNum,
                useLimitNum: that.VIPCardMgt.useLimitNum,
            };
            //检查排除的数字和开始编号是否冲突
            new checkBegin(Number(this.VIPCardMgt.startNo),Number(this.VIPCardMgt.limitNum),position);
            // 组装规则对象
            let jsonData = {
                batchNum : Number(that.VIPCardMgt.batchNum),
                startNo : String(that.VIPCardMgt.startNo),
                endNo : String(that.VIPCardMgt.endNo),
                useHeadNum:that.VIPCardMgt.useHeadNum,
                useMiddleNum: that.VIPCardMgt.useMiddleNum,
                useTailNum: that.VIPCardMgt.useTailNum,
                useLimitNum: that.VIPCardMgt.useLimitNum,
                limitNum : that.VIPCardMgt.limitNum,
            };
            //显示加载中
            Loading.prototype.show();
            // ajax提交
            axios.post(url, jsonData)
            .then(function (res) {
                let resData = eval(res);
                $.alert(resData['data']['msg']);
                //隐藏加载中
                Loading.prototype.hide();
                initCardGroupList();
            })
            .catch(function (error) {
                //隐藏加载中
                Loading.prototype.hide();
                $.alert(error)
            });
        },
        //保存通用设置
        saveCommonSet:function () {
            let that = this;
            let url = $.stringFormat("{0}/frStoreCommon/postUpdateSet",$.cookie('url'));
            axios.post(url, that.commonSet)
            .then(function (res) {
                let resData = eval(res);
                $.alert(resData['data']['msg']);
                initStoreCommon();
            })
            .catch(function (error) {
                $.alert(error)
            });
        },
        //保存所在门店设置
        updateWhereStore:function () {
            let that = this;
            let url = $.stringFormat("{0}/shop/postUpdateWhereShop",$.cookie('url'));
            axios.post(url, that.whereShop.list)
            .then(function (res) {
                let resData = eval(res);
                initShopList();
            })
            .catch(function (error) {
                $.alert(error)
            });
        },
        //修改单个门店设置
        updateStoreSingleSet:function () {
            let that = this;
            let url = $.stringFormat("{0}/frStoreSingle/postUpdateSet",$.cookie('url'));
            let data = that.storeSingleSet;
            data.newParam = 'operaterId';
            data.newParam = 'operaterName';
            data.operaterId = $.cookie('id');
            data.operaterName = $.cookie('name');
            axios.post(url, data)
            .then(function (res) {
                let resData = eval(res);
                $.alert(resData['data']['msg']);
                initStoreSingleSet();
            })
            .catch(function (error) {
                $.alert(error)
            });
        },
        //修改客户保护天数设置
        updateProtectionDaysSet:function () {
            let that = this;
            let url = $.stringFormat("{0}/frIndustry/postUpdateSet",$.cookie('url'));
            axios.post(url, this.protectionDaysSet.list)
            .then(function (res) {
                let resData = eval(res);
            })
            .catch(function (error) {
                $.alert(error)
            });
        },
        //修改客户认领设置
        updateClaimSet:function () {
            let that = this;
            let url = $.stringFormat("{0}/frIndustry/postUpdateSet",$.cookie('url'));
            console.log(this.claimSet.list)
            axios.post(url, this.claimSet.list)
            .then(function (res) {
                let resData = eval(res);
            })
            .catch(function (error) {
                $.alert(error)
            });
        },
        //修改客户跟进设置
        updateFollowSet:function () {
            let that = this;
            let url = $.stringFormat("{0}/frIndustry/postUpdateSet",$.cookie('url'));
            //现有客户跟进
            axios.post(url, this.xyFollowSet.list)
            .then(function (res) {
                let resData = eval(res);
            })
            .catch(function (error) {
                $.alert(error)
            });
            //潜在客户跟进
            axios.post(url, this.qzFollowSet.list)
            .then(function (res) {
                let resData = eval(res);
            })
            .catch(function (error) {
                $.alert(error)
            });
        },
        //删除关键字
        deleteKeyword:function (value,index) {
            let that = this;
            let url = $.stringFormat("{0}/frFollowKeyword/postDelKeyWord",$.cookie('url'));
            $.confirm({
                title: '确认',
                content: '确认删除?',
                buttons: {
                    ok: {
                        text: '确认',
                        btnClass: 'btn-primary',
                        action: function() {
                            axios.post(url,{
                                id:value,
                            })
                            .then(function (res) {
                                let resData = eval(res);
                                initKeywordList();
                                $.alert(resData['data']['msg']);
                            })
                            .catch(function (error) {
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

        },
        //增加关键字
        addKeyword:function () {
            let that = this;
            let url = $.stringFormat("{0}/frFollowKeyword/postAddKeyWord",$.cookie('url'));
            if(this.keywordList.keyword===''||this.keywordList.keyword===null){
                $.alert('请输入关键字');
                return;
            }
            if(this.keywordList.keyword.length>20){
                $.alert('请输入20个字符内的关键字');
                return;
            }
            axios.post(url,{
                keyword:that.keywordList.keyword,
                operaterId:$.cookie('id'),
                operaterName:$.cookie('name'),
                customerCode:$.cookie('code'),
            })
            .then(function (res) {
                let resData = eval(res);
                initKeywordList();
                $.alert(resData['data']['msg']);
            })
            .catch(function (error) {
                $.alert(error)
            });
        },
        SpanMouseenter($event,index){
            $event.currentTarget.className="on";
        },
        SpanMouseleave($event,index){
            $event.currentTarget.className="";
        }
    }
});





initShopList();
initStoreSingleSet();
initProtectionDaysSet();
initClaimSetList();
initXyFollowSetList();
initQzFollowSetList();
initKeywordList();

initStoreCommon();
// initAgreementList();
initInvalidCardNoList();
initCardGroupList();
initProtocolGroupList();
Loading.prototype.init();
//创建分页实例
$('.cardPageBox').Paging({
    align:'left',
    hasSize:false,
    pageSize:10,
    marginTop:10,
    returnPageId:function (id) {
        app.$data.pageBoxId = id;
    }
})



/**
 * 初始化数据:门店通用设置
 */
function initStoreCommon() {
    let that = this;
    let url = $.stringFormat("{0}/frStoreCommon/getSet",$.cookie('url'));
    axios.get(url)
    .then(function (res) {
        let resData = eval(res);
        if(resData['data']['code'] === '200'){
            app.$data.commonSet = resData['data']['data'];
        }else {
            $.alert(resData['data']['msg']);
        }
    })
    .catch(function (error) {
        $.alert(error);
    });
}
/**
 * 初始化数据:作废协议号列表
 */
function initAgreementList(params) {
    let that = app.$data;
    let url = $.stringFormat("{0}/frAgreement/invalidList",$.cookie('url'));
    axios.get(url, {params: params})
    .then(function (res) {
        let resData = eval(res);
        if(resData['data']['code'] === '200'){
            app.$data.invalidProtocolSet = resData['data']['data'];

        }else {
            $.alert(resData['data']['msg']);
        }
    })
    .catch(function (error) {
        $.alert(error);
    });
}
/**
 * 初始化数据:获取作废会员卡号getInvalidCardNoList
 */
function initInvalidCardNoList(params) {
    let that = app.$data;
    let url = $.stringFormat("{0}/frCard/getInvalidCardNoList",$.cookie('url'));
    axios.get(url, {params: params})
    .then(function (res) {
        let resData = eval(res);
        if(resData['data']['code'] === '200'){
            app.$data.invalidVIPCardSet = resData['data']['data'];
            $.newPaging(that.invalidVIPCardSet.totalPage,that.pageBoxId,function (index,size) {
                initInvalidCardNoList({page:index,limit:size});
            })
        }else {
            $.alert(resData['data']['msg']);
        }
    })
    .catch(function (error) {
        $.alert(error);
    });
}

/**
 * 初始化数据:查询协议号组
 */
function initProtocolGroupList() {
    let that = this;
    let url = $.stringFormat("{0}/frAgreement/getList",$.cookie('url'));
    axios.get(url)
    .then(function (res) {
        let resData = eval(res);
        if(resData['data']['code'] === '200'){
            app.$data.protocolNumMgt.list = resData['data']['data'];
        }else {
            $.alert(resData['data']['msg']);
        }
    })
    .catch(function (error) {
        $.alert(error);
    });
}

/**
 * 初始化数据:查询会员号组
 */
function initCardGroupList() {
    let that = this;
    let url = $.stringFormat("{0}/frCardNum/getList",$.cookie('url'));
    axios.get(url)
    .then(function (res) {
        let resData = eval(res);
        if(resData['data']['code'] === '200'){
            app.$data.VIPCardMgt.list = resData['data']['data'];
        }else {
            $.alert(resData['data']['msg']);
        }
    })
    .catch(function (error) {
        $.alert(error);
    });
}

/**
 * 初始化数据:门店列表
 */
function initShopList() {
    let that = this;
    let url = $.stringFormat("{0}/shop/getShopList",$.cookie('url'));
    axios.get(url)
    .then(function (res) {
        let resData = eval(res);
        if(resData['data']['code'] === '200'){
            app.$data.whereShop.list = resData['data']['data'];
        }else {
            $.alert(resData['data']['msg']);
        }
    })
    .catch(function (error) {
        $.alert(error);
    });
}

/**
 * 初始化数据:获取单个门店设置
 */
function initStoreSingleSet() {
    let that = this;
    let url = $.stringFormat("{0}/frStoreSingle/getSingleShopSet",$.cookie('url'));
    axios.get(url)
    .then(function (res) {
        let resData = eval(res);
        if(resData['data']['code'] === '200'){
            app.$data.storeSingleSet = resData['data']['data'];
        }else {
            $.alert(resData['data']['msg']);
        }
    })
    .catch(function (error) {
        $.alert(error);
    });
}
/**
 * 初始化数据:客户认领保护天数列表
 */
function initProtectionDaysSet() {
    let that = this;
    let url = $.stringFormat("{0}/frIndustry/getProtectDaysList",$.cookie('url'));
    axios.get(url,{params:{customerCode:$.cookie('code')}})
    .then(function (res) {
        let resData = eval(res);
        if(resData['data']['code'] === '200'){
            app.$data.protectionDaysSet.list = resData['data']['data'];
        }else {
            $.alert(resData['data']['msg']);
        }
    })
    .catch(function (error) {
        $.alert(error);
    });
}
/**
 * 初始化数据:客户认领设置列表
 */
function initClaimSetList() {
    let that = this;
    let url = $.stringFormat("{0}/frIndustry/getClaimSetList",$.cookie('url'));
    axios.get(url,{params:{customerCode:$.cookie('code')}})
    .then(function (res) {
        let resData = eval(res);
        if(resData['data']['code'] === '200'){
            app.$data.claimSet.list = resData['data']['data'];
        }else {
            $.alert(resData['data']['msg']);
        }
    })
    .catch(function (error) {
        $.alert(error);
    });
}
/**
 * 初始化数据:现有客户跟进管理设置
 */
function initXyFollowSetList() {
    let that = this;
    let url = $.stringFormat("{0}/frIndustry/getFollowList",$.cookie('url'));
    axios.get(url,{params:{
        customerType:0,
    }})
    .then(function (res) {
        var resData = eval(res);
        if(resData['data']['code'] === '200'){
            app.$data.xyFollowSet.list = resData['data']['data'];
        }else {
            $.alert(resData['data']['msg']);
        }
    })
    .catch(function (error) {
        $.alert(error);
    });
}
/**
 * 初始化数据:潜在客户跟进管理设置
 */
function initQzFollowSetList() {
    let that = this;
    let url = $.stringFormat("{0}/frIndustry/getFollowList",$.cookie('url'));
    axios.get(url,{params:{
        customerType:1,
        customerCode:$.cookie('code'),
    }})
    .then(function (res) {
        let resData = eval(res);
        if(resData['data']['code'] === '200'){
            app.$data.qzFollowSet.list = resData['data']['data'];
        }else {
            $.alert(resData['data']['msg']);
        }
    })
    .catch(function (error) {
        $.alert(error);
    });
}
/**
 * 初始化数据:关键字列表
 */
function initKeywordList() {
    let that = this;
    let url = $.stringFormat('{0}/frFollowKeyword/getKeywordList',$.cookie('url'));
    axios.get(url,{params:{customerCode:$.cookie('code')}})
    .then(function (res) {
        let resData = eval(res);
        if(resData['data']['code'] === '200'){
            app.$data.keywordList.list = resData['data']['data'];
        }else {
            $.alert(resData['data']['msg']);
        }
    })
    .catch(function (error) {
        $.alert(error);
    });
}

class checkBegin {
    /**
     * 检查开始编号是否需要跳过jumpNum
     * @param begin
     * @param jumpNum
     * @param position
     */
    constructor(begin,jumpNum,position) {
        let that = this;
        if(position.useLimitNum){
            let isReturn = false;
            //检查数组是否包含某个值
            that.beginNoIn = position.useHeadNum;
            that.middleNoIn = position.useMiddleNum;
            that.endNoIn = position.useTailNum;
            if(that.beginNoIn){
                //取开始编号的最大位的数字
                if (begin.toString().substring(0,1)===jumpNum.toString()){
                    $.alert("首号不能包含数字"+jumpNum);
                    if(app.VIPCardMgt.useHeadNum){
                        app.VIPCardMgt.useHeadNum = false;
                    }
                    if(app.protocolNumMgt.useHeadNum){
                        app.protocolNumMgt.useHeadNum = false;
                    }
                    that.beginNoIn = false;
                    isReturn = true;
                }
            }
            if(that.endNoIn){
                //取开始编号的个位数
                if (begin%10===jumpNum){
                    $.alert("尾号不能包含数字"+jumpNum);
                    if(app.VIPCardMgt.useTailNum){
                        app.VIPCardMgt.useTailNum = false;
                    }
                    if(app.protocolNumMgt.useTailNum){
                        app.protocolNumMgt.useTailNum = false;
                    }
                    that.endNoIn = false;
                    isReturn = true;
                }
            }
            if(that.middleNoIn){
                //开始编号的开头和结尾都被过滤以后,要判断中间是否有jumpNum这个数,直接使用indexOf
                let beginMaxLength = begin.toString().length;
                if(begin.toString().substring(1,beginMaxLength-1).indexOf(jumpNum.toString())!==-1){
                    $.alert("中间不能包含数字"+jumpNum);
                    if(app.VIPCardMgt.useMiddleNum){
                        app.VIPCardMgt.useMiddleNum = false;
                    }
                    if(app.protocolNumMgt.useMiddleNum){
                        app.protocolNumMgt.useMiddleNum = false;
                    }
                    that.middleNoIn = false;
                    isReturn = true;
                }
            }
            if(isReturn){
                return;
            }
        }else {
            that.beginNoIn = false;
            that.middleNoIn = false;
            that.endNoIn = false;
        }
    }
}

/**
 * 计算结束编号 ：去除 开头 中间 结束  数字n 的数量
 */
function genByBenginAndNum(begin,num,jumpNum,position) {
    let result = {
        jumpCount:0,
        end:0,
        available:false,
    };
    let genCount = 0;
    let end = null;
    let checkBeginO = new checkBegin(begin,jumpNum,position);

    let beginNoIn = checkBeginO.beginNoIn;
    let middleNoIn = checkBeginO.middleNoIn;
    let endNoIn = checkBeginO.endNoIn;
    //i的位数
    let iLength = 0;
    //跳过数字的位数
    let jumpNumLength =jumpNum.toString().length;
    let isPrint = false;
    //只要有一个条件满足isTrue==true则跳过
    let isTrue = false;
    let maxIndex = 0;
    //跳过的数量
    let jumpCount = 0;
    console.log('跳到这一步')
    console.info('position = ',position);
    console.info('checkBeginO = ',checkBeginO);


    for (let i = begin; i < begin+num ; i++) {

        //下一个i值，重置数据
        isPrint = true;
        isTrue = false;
        iLength = i.toString().length;
        //检查是否需要去除数字(是：进入 否：跳出并放进list)
        if(beginNoIn||middleNoIn||endNoIn){
            //遍历位数(例：i = 14567 从1，4，5，6，7遍历是否满足某个数字)
            for (let j = 0; j < iLength ; j++) {

                maxIndex = j+jumpNumLength;
                if(maxIndex<=iLength){

                    if (i.toString().substring(j,j+jumpNumLength)===jumpNum.toString()) {
                        if (beginNoIn){
                            //检查是否是开头
                            if (maxIndex === 1){
                                isPrint = false;
                                isTrue = true;
                                continue;
                            }
                        }
                        if (middleNoIn){
                            //检查是否是中间
                            if (maxIndex!==1&&maxIndex!==iLength){
                                isPrint = false;
                                isTrue = true;
                                continue;
                            }
                        }
                        if (endNoIn){
                            //检查是否是尾号
                            if (maxIndex === iLength){
                                isPrint = false;
                                isTrue = true;
                            }
                        }
                    }
                }
            }
        }
        if (isPrint&&!isTrue) {
            genCount++;
            // console.log('生成数 = '+i);
            end = i;
        }
        //每跳过一次数字，num+1
        if(isTrue){
            // console.log('跳过数 = '+i);
            jumpCount++;
            num++;


        }
    }
    result = {
        jumpCount:Number(jumpCount),
        end:Number(end)
    }
    console.log('跳过的数量为'+result.jumpCount);
    console.log('生成的数量为'+genCount);
    console.log('结束编号为'+result.end);
    return result;
}









