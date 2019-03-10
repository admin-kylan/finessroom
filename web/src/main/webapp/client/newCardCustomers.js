//关闭小弹窗
function settingOut_a() {
    $('.maskLayerAlert_box_alert').hide();
}

//关闭小弹窗
function settingOut_a2() {
    $('.maskLayerAlert_box_alert2').hide();
}

var newCardCustomers = new Vue({
    el: '#newCardCustomers',
    data: {
        loadName: '续卡操作',
        LiIndex: 0,                  // 0，新建现有客户，1 新购，2，续卡 , 3,新购项目课程 4，新购套餐疗程
        accountsMeth: '',            //要调用的结账方法
        randomNumber: '',            //随机生成，防止点击过快
        salespersonList: [],         //销售顾问列表
        salespersonListTwo: [],      //获取服务顾问列表
        paymentMethodTops: 0,        //切换支付方式
        shipCard: {},                 //存放其他页面传过来的数据
        clientId: '',                 //会员ID
        code: $.cookie("code"),       //客户代码
        cardId: '',                   //所选择的会员卡卡种ID
        cardMap: {                    //设置的会员卡参数
            shopId: '',               //销售店铺
            personnelId: '',          //员工Id
            cardNumId: '',            //会员卡号规则ID
            cardNo: '',               //会员卡号
            agreementId: '',         //编号协议ID
            agreementNo: '',         //协议编号
            cardTypeId: '',          //卡类型ID
            totalPrice: 0,           //总价
            buyRightsNum: 0,        //购买权益
            giveRightsNum: 0,       //赠送权益
            bindTime: '',           //开卡日期
            externalNo: '',         //外部卡号
            note: '',                //备注
            allotSetType: '0',     //业绩分配设置
            orderAllotSetList: [], //业绩分配
            orderAllotSetSav: {},  //业绩分配
            isFlage: 0,             //是否直接开卡
            orderNo: '',            //订单编号
            salesPrice: 0,          //售价备用切换
            invoiceStatus: 0,       //是否购置发票
            depositTime: 0,          //定金支付方式的补余期限
        },
        clientUserName: '',    //客户姓名
        clientPhone: '', //客户手机
        clientSet: '',   //客户性别
        clientURL: '',   //客户性别
        personnelName: '',  //系统当前操作人信息
        creatTime: getNowTime(true),  //创建时间
        catcherClientId: '',      //承接的客户Id
        catcherClientCardId: '', //承接的客户会员卡ID
        givePhone: '',            //承接客户的手机
        giveName: '',             //承接客户的姓名
        optionClientList: [],    //获取选择的会员信息
        optionClientCardList: [],  //获取选择的会员信息
        maeketUserList: [],     //销售人员列表
        maeketUserListTwo: [],     //获取服务人员列表
        payModelMoney: 0,        //支付总金额
        payMentMoney: {           //小计金额
            price: 0,              //实际应付金额
            totalPrice: 0,        //小计 = 实际应付金额 + 票卷差价（默认差价0）
            discountNum: 0,       //抵扣金额
            discount: 0,           //优惠折扣
            needPrice: 0,          //计算后的应付金额
            noPrice: 0,            //未付金额
            retChange: 0,          //找零
        },
        payModel: {              //支付方式
            L1: parseFloat(0).toFixed(2),   //支付宝
            L2: parseFloat(0).toFixed(2),   //刷卡
            L3: parseFloat(0).toFixed(2),   //微信
            L4: parseFloat(0).toFixed(2),   //现金
            L5: parseFloat(0).toFixed(2),   //转账
            L6: parseFloat(0).toFixed(2),   //花呗
            L7: parseFloat(0).toFixed(2),   //其他
        },
        payModelCount: parseFloat(0).toFixed(0),  //统计支付金额共用
        ticketPrice: {
            ticketPriceList: [           //选中的会员卡票卷信息    -----------先写死后期动态
                {"tickNo": 201807246565, "tickName": "抵用券", "tickNum": 10, "differ": 5},
                {"tickNo": 201807246158, "tickName": "充值券", "tickNum": 100, "differ": 10},
                {"tickNo": 201807246158, "tickName": "充值券", "tickNum": 50, "differ": 15},
                {"tickNo": 201807246158, "tickName": "充值券", "tickNum": 100, "differ": 20},
                {"tickNo": 201807246158, "tickName": "抵用券", "tickNum": 50, "differ": 15},
                {"tickNo": 201807246158, "tickName": "抵用券", "tickNum": 100, "differ": 20},
                {"tickNo": 201807246158, "tickName": "抵用券", "tickNum": 50, "differ": 15},
                {"tickNo": 201807246158, "tickName": "抵用券", "tickNum": 100, "differ": 20},
            ],
            tickMark: '',                 //票劵标记
            ticketPriceListOk: [],        //选中的要使用的票卷信息
            storage0rderPrice: 0,       //票卷选中的会员卡的有效储值金额
            cardNo: '未选择会员卡',       //票卷选中的会员卡号  ------未选择会员卡
            cardId: '',                    //票卷选中的会员卡号Id
            cardUserName: '--',           //票卷选中的会员卡客户名称
            differencePrice: 0,           //票卷差价
            totalPrice: 0,                 //选中的票卷金额
            storagePirc: 0,                //要抵扣的储值卡金额
            userPass: '',                  //票卷客户授权凭证
        },       //票卷部分
        discount: {                                 //折扣优惠
            num: 0,                   //打几折
            changeNum: 0,            //整单去零
            fullType: 0,             //满减优惠类型（0、一次；1、可重复）
            discountPrice: 0,       //总折扣/优惠金额
            discountFullOne: 0,     //重复满
            discountReduceOne: 0,   //重复减
            discountFullTwo: 0,     //单次满
            discountReduceTwo: 0,   //单次减
        },
        parameterList: {
            splitType: [
                {"vue": 1, "lav": "比例"},
                {"vue": 2, "lav": "金额"},
            ],
        },
//      小部分共用参数--end
        loadData1: {
            authorizingPass: '',//授权凭证
            splitNum: 0, //分期期数
            shopList: [],//销售门店列表
            orderList: [],//新购订单列表
            cardsList: [],//指定门店下的卡种列表
            salesPrice: 0, //卡售价
            splitSet: {             //分期信息
                cardTypeSplitSetList: [],//分期付款类型
                cardTypeSplitSetDD: [],//分期详情
                cardTypeSplitSetIdList: [], //选中的分期付款
                cardTypeSplitSetIdListT: [], //待选中
                cardTypeSplitSetIdT: '',  //备用切换
                cardTypeSplitSetId: '', //分期付款
            },
            paremt: {
                consumeType: 1,     //消费方式，
                payType: 1,          //支付方式
                depositTime: '',     //定金补余期限
                invoiceStatus: '',   //是否购置发票
                authorizingUserId: '', //授权人信息
                displaceCompany: '',    //置换单位
                displaceWay: '',        //置换方式
            },
            depositTime: '',  //其他补余期限
            orderAllotSetListOne: [],  //业绩分配添加的时候
            orderAllotSetList: [],  //业绩分配正式
            allotNumOne: 0,
            allotNumTwo: 0,
            orderAllotSetSav: {  //业绩分配
                allotType: '',  //业绩分配类型（1、分配百分百；2、分配金额）
                allotNum: 0,   //分配数额（百分百或者实际金额，根据分配类型判断）
                saleAllotType: 0, //销售业绩分配类型（0、无业绩分配；1、按比例分配；2、按金额分配）
            },
            orderAllotSet: {  //业绩分配
                allotType: '',  //业绩分配类型（1、分配百分百；2、分配金额）
                allotNum: 0,   //分配数额（百分百或者实际金额，根据分配类型判断）
                saleAllotType: 0, //销售业绩分配类型（0、无业绩分配；1、按比例分配；2、按金额分配）
                personnelId: '',  //业绩分配员工
                saleAllotNum: '',
            },
        },
        loadData2: {
            cardDatail: {}, //会员卡订单信息
            clitenUser: {}, //获取客户信息，
            cardFlagList: [], //卡系列
            cardNameList: [], //卡名称
            careFlag: '', //卡系列
            cardName: '', //卡名称
            salesPrice: 0, //售价
            serviceLife: '',//使用期限
            haveRightsNum: 0,//购买权益
            buyRightsNum: 0,//总权益
            giveRightsNum: 0,//赠送权益
            qtZdqy: true,//判断赠送权益是否合法
            minPrice: false, //判断购卡金额是否合法
            addQtZdqy: 0, //最多赠送权益
            type: '',//卡类别
            salesPrice: 0,  //续卡价格
            salespersonId: '',  //销售顾问ID
            continueAdd: {
                cardOpening: 0,          //开卡方式（0，直接延续 ； 1，另行开卡）
                replacementCard: 0,      //是否更换新卡(0、否；1、是)
            },
            addCardData: {
                minPrice: 0,         //前台可修改最低销售价格
                qtZdqy: 0,           //判断赠送权益是否合法
            },
        },
        loadDatat0: {
            userURL: '',
        },
        temp:null,
        ticketTable:{
            list: [],
            currPage: 1,
            pageSize: 5,
            totalContentInfo: 0,
            totalPage: 0,
            totalCount:0,
        },
        ticketSetTable: {
            list: [],
            currPage: 1,
            pageSize: 10,
            totalContentInfo: 0,
            totalPage: 0,
        },
        cardUserTable: {
            list: [],
            currPage: 1,
            pageSize: 5,
            totalContentInfo: 0,
            totalPage: 0,
        },
        zrCardUserTable: {
            list: [],
            currPage: 1,
            pageSize: 5,
            totalContentInfo: 0,
            totalPage: 0,
        },
        customerTicketPointTable: {
            list: [],
            currPage: 1,
            pageSize: 10,
            totalContentInfo: 0,
            totalPage: 0,
        }, customerTicketTable: {
            list: [],
            currPage: 1,
            pageSize: 10,
            totalContentInfo: 0,
            totalPage: 0,
        },
		ticketEquityDetail:[],
		ticketSetSelectIds:[],
		ticketSetCard:[],   //赠送选择会员卡号
		obtains:["购卡赠送","友情赠送","推荐赠送","消费推荐"],
		ticketSetList:[], // 存储赠送票卷
		customerPhone:'', 
		ticketSelectList:[],
        cardFlagList:[],
        numAll:'',
    },
    filters: {
        formatDate: function (time, type, typeT) {
            if (!time) {
                return '';
            }
            var _time = timeFormatDate(time, type, typeT);
            return _time;
        },
        toManey: function (val) {
            if (!val || 'undefined' == typeof (val)) {
                return parseFloat(0).toFixed(2);
                ;
            }
            return parseFloat(val).toFixed(2);
        },
        toMoneySet: function (val, ss, obj) {
            var that = this;
            var splitNum = val;
            if (obj) {
                var splitType = obj.splitType;
                // 分期类型（1、比例；2、金额）
                if (splitType == 2) {
                    return parseFloat(val).toFixed(2);
                }
                if (splitType == 1) {
                    var splitPrice = obj.splitPrice;
                    if (splitPrice && splitNum) {
                        var numMoney = (parseFloat(splitNum) / 100) * splitPrice;
                        return parseFloat(numMoney).toFixed(2);
                    }
                }
            }
            return val;
        },
    },
    created: function () {
        console.log($.cookie("name"))
        this.personnelName = $.cookie("name")
        console.log(this.personnelName)
        this.loadData2.clitenUser.consultantId = $.cookie("id")
        this.loadData2.clitenUser.fwhjName = $.cookie("name")
        this.cardMap.shopId = $.cookie('shopid')
        var that = this;
        var timestamp = Date.parse(new Date())
        that.loadData2.clitenUser.buildDate = that.formatDate(timestamp)
        that.loadData2.clitenUser.applyTime = that.formatDate(timestamp)
        that.shipCard = window.parent["shipCard"];
        if (that.shipCard) {
            that.LiIndex = that.shipCard.LiIndex;
        }
        var methodsName = 'loadL' + that.LiIndex;
        that[methodsName]();
    },
    methods: {
    	 init: function (t) {
         	this.queryTicketList();
         },
    	   queryTicketList: function () {

    		   const that = this;
//               var    params = {"tel": this.tel,'code':$.cookie("code")};
               var    params = {"tel": '13645945925','code':'003'};
                //    console.log( "params---- >  "+JSON.stringify(params) );
               //显示加载中
               Loading.prototype.show();
               that.$nextTick(function () {
                   const url = $.stringFormat('{0}/ticket/getTicketList', 'http://www.4006337366.com:8080/');
                   $.get(url, params, function (res) {
                       if (res.code === '200') {
                           that.customerTicketPointTable = res.data;
                           that.ticketPageInit();
                           //console.log("-------"+that.customerTicketPointTable.list);
                           new myPagination({
                               id: 'ticketsetPagination',
                               curPage: that.customerTicketTable.currPage, //初始页码
                               pageTotal: that.customerTicketTable.totalPage, //总页数
                               pageAmount: that.customerTicketTable.pageSize,  //每页多少条
                               dataTotal: that.customerTicketTable.totalCount, //总共多少条数据
                               showPageTotalFlag: true, //是否显示数据统计
                               showSkipInputFlag: true, //是否支持跳转
                               getPage: function (page) {
                               	  console.log("------getPage-----");
                                   //获取当前页数
                                   console.log(page)
                                   var currPage = page + "";
                                   that.ticketPageNext(page);
                                //   that.ticketSetPageNext(0,page);
                                  // that.queryServiceRecordList({"cid": cid, "currPage": currPage});
                               }
                           })
                       } else {
                           $.alert(jsonData['data']['msg'])
                       }
                       //隐藏加载中
                       Loading.prototype.hide();
                   })

               })

           },
           ticketPageInit:function(){
           	// console.log("-----------ticketPageInit------------");
           	const that = this;
           	  for(var i = 0 ; i<  5 ; i++){
                 	if("undefined"==that.customerTicketPointTable.list[i]) break;
                 	  that.customerTicketTable.list[i]=that.customerTicketPointTable.list[i] ;
           		  
                 }
           	  Vue.set(this.customerTicketTable,0,that.customerTicketTable);
           	 	//计算总页数
         	  	var    totalPage = that.customerTicketPointTable.list.length/5;
                 if( totalPage> parseInt(totalPage)){
                 	totalPage =  parseInt(totalPage)+1;
                 }else{
                 	totalPage = parseInt(totalPage);
                 }
                 that.customerTicketTable.pageSize =  that.customerTicketTable.list.length;//单页数
                 that.customerTicketTable.totalCount = that.customerTicketPointTable.list.length; //总记录数
                 that.customerTicketTable.totalPage = totalPage; //总页数
           },// 票卷下一页
           ticketPageNext:function(page){
           	const that = this;
           	//  console.log("ticketSetPageNext index>"+index+"  page>>>"+page);
           	  var pagesize = page*5 
           	  var i =  (page-1)*5;
           	  var j = 0 ;
           	  that.customerTicketTable.list=[];
           	  //装载数据。 每页限制5条；
           	  for(i; i< pagesize ; i++){
                   	if(undefined==that.customerTicketPointTable.list[i])break;
                   	that.customerTicketTable.list[j]=that.customerTicketPointTable.list[i];
                   	
                   	j++;
                   }
           	  Vue.set(this.customerTicketTable,0,that.customerTicketTable);
           	  	//计算总页数
           		var    totalPage = that.customerTicketPointTable.list.length/5;
                   if( totalPage> parseInt(totalPage)){
                   	totalPage =  parseInt(totalPage)+1;
                   }else{
                   	totalPage = parseInt(totalPage);
                   }
                   that.customerTicketTable.pageSize =  that.customerTicketTable.list.length;//单页数
                   that.customerTicketTable.totalCount = that.customerTicketPointTable.list.length; //总记录数
                   that.customerTicketTable.totalPage = totalPage; //总页数
                   
           },
           //赠送票
           ticketSetPageNext:function(index,page){
           	const that = this;
           	//  console.log("ticketSetPageNext index>"+index+"  page>>>"+page);
           	  var pagesize = page*5 
           	  var i =  (page-1)*5;
           	  var j = 0 ;
           	  that.ticketTable.list=[];
           	  //装载数据。 每页限制5条；
           	  for(i; i< pagesize ; i++){
                   	if(undefined==that.ticketSetTable.list[index].TicketList[i])break;
                   	that.ticketTable.list[j]=that.ticketSetTable.list[index].TicketList[i] ;
                   	
                   	j++;
                   }
           	  	//计算总页数
           	  	var    totalPage = that.ticketSetTable.list[index].TicketList.length/5;
                  
                   if( totalPage> parseInt(totalPage)){
                   	totalPage =  parseInt(totalPage)+1;
                   }else{
                   	totalPage = parseInt(totalPage);
                   }
                  
                  
                   that.ticketTable.pageSize =j;//单页数
                   that.ticketTable.totalCount =that.ticketSetTable.list[index].TicketList.length; //总记录数
                   that.ticketTable.totalPage = totalPage; //总页数
                   //暂时 采用这个方法：需要修改
                   Vue.set(this.ticketTable,page,that.ticketTable);
                   
                   //初始化表格数据， 下拉框、文本框
             			var trList = $("#titcketSetTable tbody").children("tr");
   				   var tdArr = trList.eq(i).find("td");
   				   	 for (var i=0;i<trList.length;i++) {
   				   		 var tdArr = trList.eq(i).find("td");
   	    	        
   	   	    	        tdArr.eq(12).find('input').val("");
   	   	    	        tdArr.eq(3).find('select').find("option").first().attr("selected", true);
   	   	    	     console.log("params>>"+tdArr.eq(3).find('select').find("option").first());
   	   	    	        
   	    	     }
                   
                   
           },
    	//赠送票卷提交
    	ticketSet:function(){
    	//	const that = this;
    		
    		console.log( "ticketSetCard >  "+JSON.stringify(this.ticketSetCard) );
    		var  memberCardId="";
    		var shopId="";
    		// 有选择会员卡才赋值
    		if(this.ticketSetCard.length >0){
    			memberCardId =this.ticketSetCard[0]["id"];
    			shopId = this.ticketSetCard[0]["shopId"] ;
    		}
    		if(this.ticketSetSelectIds.length ==0){
    			alert("请选择要赠送的票卷");
    			return ;
    		}
    		
    		
    		this.refreshTicketSetList();
    		
    		console.log("------------ticketSet------------");
    		/*
    		var trList = $("#titcketSetTable tbody").children("tr");
    		var i = 0 ;
    		for(var key in this.ticketSetSelectIds){
    			console.log("input>>"+key+"="+this.ticketSetSelectIds[key]); 
	       		 for (var i=0;i<trList.length;i++) {
	    	        var tdArr = trList.eq(i).find("td");
	    	        if(this.ticketSetSelectIds[key] ==tdArr.eq(0).find('input').val() ){

	   	    	  this.ticketSetList.push({ "ID":this.ticketSetSelectIds[key],
	    		 							"TicketSendWhy":tdArr.eq(3).find('select').val(),
	    		 							"Num":tdArr.eq(12).find('input').val()});
	   	    	     						break;
	    	        }
	   	    	        
	    	     }
	       		 i++;
	    	  }*/
    	
    		
    		var ticketSetParam ={
    				'customerCode':this.customerCode,
    				'memberCardId':memberCardId,
    				'usePeople':'柯宁翔',  //客户姓名 需要动态获取
    				'contactTel':'13645945925',  //客户手机号  需要动态获取
    				'operateName':'处理人1',    //操作人
    				'operateID':'13000000000',  //操作人手机号
    				'shopId':shopId,
    				'serviceName':'服务1',  	// 服务人员姓名
    				'serviceTel':'13000000001', //服务人员手机
    				'ticketSetList':this.ticketSetList,
    				'operateType':'0',
    				'consumeState':'6',
    				'givingID':''
    		};
    		
    		console.log("ticketSetParam>>"+JSON.stringify(ticketSetParam)); 
    		//提交至票卷服务
    		 var url = $.stringFormat('{0}/ticket/postGiveTicketList', 'http://www.4006337366.com:8080/');
	      	   axios.post(url, ticketSetParam).then(function (res) {0
	                 let resData = eval(res);
	                 if (resData['data']['code'] === '200') {
	                     alert("赠送成功！")
	                     location.reload();
	                 } else {
	                     alert(resData['data']['msg']);
	                 }
	             }).catch(function (error) {
	                     alert(error);
	                 }); 
	      	   
      	   
    		
    	},
    	refreshTicketSetList:function(){
    		console.log("-------------------------------refreshTicketSetList"); 

    		//获取当前页赠送数据
    		this.storageTicketSetList();
    		//清除已被取消选择的数据
    		this.removeNoInTicketSetList();
    		
    		console.log("-------------------------------refreshTicketSetList"); 

    	},
    	//加载赠送票卷数据
        showTicketGift:function (){
        	const that = this;
        	console.log("----showTicketGift-----");
        	//console.log("clientPhone>>>>"+window.parent.document.getElementById("clientPhone").innerText);
        	$('#ticketGift').show();
        	
        	  params = {'shopid': '0', 'customerCode':$.cookie("code")};
        	   //显示加载中
            Loading.prototype.show();
            that.$nextTick(function () {
                const url = $.stringFormat('{0}/ticket/getTicketSet', 'http://www.4006337366.com:8080/');
                $.get(url, params, function (res) {
                    if (res.code === '200') {
                        that.ticketSetTable = res.data;
                        //只存储5条记录 
                      
                        that.ticketSetPageInit();
                        
                        //分页 
                        new myPagination({
                            id: 'pagination',
                            curPage: that.ticketTable.currPage, //初始页码
                            pageTotal: that.ticketTable.totalPage, //总页数
                            pageAmount: that.ticketTable.pageSize,  //每页多少条
                            dataTotal: that.ticketTable.totalCount, //总共多少条数据
                            showPageTotalFlag: true, //是否显示数据统计
                            showSkipInputFlag: true, //是否支持跳转
                            getPage: function (page) {
                            	  console.log("------getPage-----");
                                //获取当前页数
                                console.log(page)
                                var currPage = page + "";
                                that.ticketSetPageNext(0,page);
                               // that.queryServiceRecordList({"cid": cid, "currPage": currPage});
                            }
                        })
                        
                        
                    } else {
                        $.alert(jsonData['data']['msg'])
                    }
                    //隐藏加载中
                    Loading.prototype.hide();
                })

            });
            
            
           // that.getCardUserList();
            
        }, //分页初始化 默认取第一一个ticketData   赠送票卷
        ticketSetPageInit:function(){
        	const that = this;
        	  for(var i = 0 ; i<  5 ; i++){
              	if("undefined"==that.ticketSetTable.list[0].TicketList[i]) break;
              	that.ticketTable.list[i]=that.ticketSetTable.list[0].TicketList[i] ;
              }
        	 
        	 	//计算总页数
      	  	var    totalPage = that.ticketSetTable.list[0].TicketList.length/5;
             
              if( totalPage> parseInt(totalPage)){
              	totalPage =  parseInt(totalPage)+1;
              }else{
              	totalPage = parseInt(totalPage);
              }
        	  
              that.ticketTable.pageSize =  that.ticketTable.list.length;//单页数
              that.ticketTable.totalCount = that.ticketSetTable.list[0].TicketList.length; //总记录数
              that.ticketTable.totalPage = totalPage; //总页数
        	
        },
        //根据ticketData_id 更新分页数据   赠送票卷
        ticketSetPageNextById:function(ticketData_id){
        	this.refreshTicketSetList();// 刷新存储的赠送数据
        	
        	const that = this;
        	
		        //	 console.log("ticketData_id >"+ticketData_id);
		        //	 console.log("************"+  $("#titcketSetTable tbody").children("tr").eq(0).find("td").eq(0).find('input').val() );
		        	 var i=0;
		        	 for(i ; i<that.ticketSetTable.list.length ; i++){
		        		if(ticketData_id ==  that.ticketSetTable.list[i].id){
		        			break;
		        		}
		        	 }
		        	 console.log("ticketSetPageNextById index>>"+  i );
		        	 that.ticketSetPageNext(i,1);
		     	
		        	 var trList = $("#titcketSetTable tbody").children("tr");
		      	  
		        	// this.ticketSetSelectIds=[];
		        	 var obtains_tmp = this.obtains;
		        	 this.obtains=[];
		        	 for(var key in obtains_tmp){
		        		 Vue.set( this.obtains,key,obtains_tmp[key]);
		        	 }
		        	
		        	 
		        	   new myPagination({
		                   id: 'pagination',
		                   curPage: that.ticketTable.currPage, //初始页码
		                   pageTotal: that.ticketTable.totalPage, //总页数
		                   pageAmount: that.ticketTable.pageSize,  //每页多少条
		                   dataTotal: that.ticketTable.totalCount, //总共多少条数据
		                   showPageTotalFlag: true, //是否显示数据统计
		                   showSkipInputFlag: true, //是否支持跳转
		                   getPage: function (page) {
		                   	  console.log("------getPage-----");
		                       //获取当前页数
		                       console.log(page)
		                       var currPage = page + "";
		                       that.ticketSetPageNext(i,page);
		                      // that.queryServiceRecordList({"cid": cid, "currPage": currPage});
		                   }
		               })
		        	   that.$nextTick(function () {
		        		   	this.refreshTicketSetTable(); //刷新表格、
		        	   	});
        },
        //赠送票卷 下一页
        ticketSetPageNext:function(index,page){
        	const that = this;
        	//  console.log("ticketSetPageNext index>"+index+"  page>>>"+page);
        	  var pagesize = page*5 
        	  var i =  (page-1)*5;
        	  var j = 0 ;
        	  that.ticketTable.list=[];
        	  //装载数据。 每页限制5条；
        	  for(i; i< pagesize ; i++){
                	if(undefined==that.ticketSetTable.list[index].TicketList[i])break;
                	that.ticketTable.list[j]=that.ticketSetTable.list[index].TicketList[i] ;
                	
                	j++;
                }
        	  	//计算总页数
        	  	var    totalPage = that.ticketSetTable.list[index].TicketList.length/5;
               
                if( totalPage> parseInt(totalPage)){
                	totalPage =  parseInt(totalPage)+1;
                }else{
                	totalPage = parseInt(totalPage);
                }
               
               
                that.ticketTable.pageSize =j;//单页数
                that.ticketTable.totalCount =that.ticketSetTable.list[index].TicketList.length; //总记录数
                that.ticketTable.totalPage = totalPage; //总页数
                //暂时 采用这个方法：需要修改
                Vue.set(this.ticketTable,page,that.ticketTable);
                
                //初始化表格数据， 下拉框、文本框
          			var trList = $("#titcketSetTable tbody").children("tr");
				   var tdArr = trList.eq(i).find("td");
				   	 for (var i=0;i<trList.length;i++) {
				   		 var tdArr = trList.eq(i).find("td");
	    	        
	   	    	        tdArr.eq(12).find('input').val("");
	   	    	        tdArr.eq(3).find('select').find("option").first().attr("selected", true);
	   	    	     console.log("params>>"+tdArr.eq(3).find('select').find("option").first());
	   	    	        
	    	     }
                
                
        },
        //   刷新表格数据显示
    	refreshTicketSetTable(){
    		
    		console.log("------------refreshTicketSetTable-----------");
    		var trList = $("#titcketSetTable tbody").children("tr");
    		for(var key in this.ticketSetList){
	       		 for (var i=0;i<trList.length;i++) {
	       		  var tdArr = trList.eq(i).find("td");
	       		console.log(this.ticketSetList[key].ID+"-----"+tdArr.eq(0).find('input').val() );
	       			 if(this.ticketSetList[key].ID == tdArr.eq(0).find('input').val() ){
	       				tdArr.eq(3).find('select').val(this.ticketSetList[key].TicketSendWhy);
	       				tdArr.eq(12).find('input').val(this.ticketSetList[key].Num);
	       			 }
	       		 }
	       	}
    		
    	},
    	setNumAll:function(){
    		console.log("numAll>>>"+this.numAll);
    		
    		var trList = $("#titcketSetTable tbody").children("tr");
    		for(var key in this.ticketSetList){
	       		 for (var i=0;i<trList.length;i++) {
	       		  var tdArr = trList.eq(i).find("td");
	       		console.log(this.ticketSetList[key].ID+"-----"+tdArr.eq(0).find('input').val() );
	       			 if(this.ticketSetList[key].ID == tdArr.eq(0).find('input').val() ){
	       				tdArr.eq(12).find('input').val(this.numAll);
	       			 }
	       		 }
	       	}
    	},
		//查看权益
		viewQY:function(id){
			const that = this;
			$('#qyModal').modal('show');
			
			  const url = $.stringFormat('{0}/ticket/getSelectTicketEquity', 'http://www.4006337366.com:8080/');
              $.get(url, {"id":id}, function (res) {
            	  if (res.code === '200') {
                      that.ticketEquityDetail = res.data;
                    } else {
                      alert(jsonData['data']['msg'])
                  }
              });
              
             
			
		},
		 //查看权益typeset转换
    	typesetFormater:function(typeset){
    		if(typeset==1){
    			return "项目";
    		}else if(typeset==2){
    			return "产品";
    		}else if (typeset==0){
    			return "代金券";
    		}
    	},
		// 存储赠送票卷信息
    	storageTicketSetList:function(){
    		console.log("-------------------------------存储赠送票卷信息"); 
    		var trList = $("#titcketSetTable tbody").children("tr");
    		var i = 0 ;
    		for(var key in this.ticketSetSelectIds){
	       		 for (var i=0;i<trList.length;i++) {
	       			 // 获取获取类型、赠送数量
	    	        var tdArr = trList.eq(i).find("td");
	    	        console.log("------/////-------------------------"+this.ticketSetSelectIds[key]  +"----"+tdArr.eq(0).find('input').val() );
	    	      //  console.log("tdArr------->>"+JSON.stringify(tdArr)); 
	    	        if(this.ticketSetSelectIds[key] ==tdArr.eq(0).find('input').val() ){
	    	        	//当前对象已经存在 删除掉  ， 重新保存
	    	        	var index = this.queryTicketSetList(this.ticketSetSelectIds[key]);
	    	        	if(index>=0){
	    	        		console.log("当前对象已经存在 删除掉  ，ticketSetSelectIds>>"+this.ticketSetSelectIds[key]+" input>>"+tdArr.eq(0).find('input').val());
 	    	        		this.ticketSetList.splice(index,1);
	    	        	}
	    	        	
	  	   	    	   this.ticketSetList.push({ "ID":this.ticketSetSelectIds[key],
	  	    		 							"TicketSendWhy":tdArr.eq(3).find('select').val(),
	  	    		 							"Num":tdArr.eq(12).find('input').val()});
	   	    	     	break;
	    	        }
	   	    	        
	    	     }
	       		 i++;
	    	  }
    		console.log("storageTicketSetList------->>"+JSON.stringify(this.ticketSetList)); 
    		console.log("-------------------------------存储赠送票卷信息"); 

    	},
    	/**
    	 * 判断已经存储的是否在选择内， 在删除
    	 */
    	removeNoInTicketSetList:function(){
    		console.log("删除之前 >>"+JSON.stringify(this.ticketSetList)); 
    		for(var i in this.ticketSetList){
    			var isin=false; //默认不存在
    			for(var key in this.ticketSetSelectIds){
    				//判断已经存储的是否在选择内， 不在删除
    				
    				if( this.ticketSetList[i].ID==this.ticketSetSelectIds[key]){
    					isin = true;
    				}
    			}
    			if(!isin){
    				console.log("-----removeNoInTicketSetList----"+this.ticketSetList[i].ID);
    				this.ticketSetList.splice(i,1);
    			}
    		}
    		console.log("删除之后 >>"+JSON.stringify(this.ticketSetList)); 
    	},//查询id 是否存在 赠送TicketSetList
    	queryTicketSetList:function(id){
    		console.log('--------------queryTicketSetList-------------------------');
			console.log(JSON.stringify(this.ticketSetList));

    		for(var i in this.ticketSetList){
				console.log(this.ticketSetList[i].ID+"==********=="+id);

    			if(this.ticketSetList[i].ID==id){
    				return i;
    			}
    			
    		}
    		return -1;
    		console.log('--------------queryTicketSetList-------------------------');

    	},
       
        //新增现有客户
        loadL0: function () {
            var that = this;
            setTimeout(function () {
                jeDate('.jeinput', {
                    format: "YYYY-MM-DD hh:mm:ss",
                    minDate: getNowTime(),
                    donefun: function (obj) {
                        that.cardMap.bindTime = obj.val;
                    }
                });
            }, 50);
            that.loadName = '新建会员';
            $('#giveRightsNum').removeAttr("readonly");
            //获取销售门店列表
            that.getMarketShopList();
            //生成订单编号;
            that.getOrderNo();
            //获取销售顾问
            that.getMarketShopPeople($.cookie("shopid"), that, 1);
            //获取服务顾问
            that.getMarketShopPeople($.cookie("shopid"), that, 2);
        },
        //会员卡新购
        loadL1: function () {
            var that = this;
            that.getInit();
            that.initLoad1();
            that.getOrderNo();
            that.getCardUser();
            that.getMarketShopPeople($.cookie("shopid"), that, 1);
        },
        //会员卡续卡
        loadL2: function () {
            var that = this;
            setTimeout(function () {
                jeDate('.jeinput', {
                    format: "YYYY-MM-DD hh:mm:ss",
                    minDate: getNowTime(),
                    donefun: function (obj) {
                        that.cardMap.bindTime = obj.val;
                    }
                });
            }, 50);
            that.getInit();
            that.initLoad1();
            //获取销售门店列表
            that.getMarketShopList();
            //生成订单编号;
            that.getOrderNo();
            //获取销售顾问
            that.getSalespersonList();
            //获取服务顾问
            that.getSalespersonList(2, 'salespersonListTwo');
            //开卡时间---另行设置
            that.chackBindTime();
            //获取用户信息
            that.getClientUser();
            if (that.cardId) {
                // 获取会员卡订单信息，及卡种信息
                that.getCardInfo();
            }
            ;
        },
        //新购项目课程
        loadL3: function () {
            var that = this;
            that.getInit();
            that.getMarketShopPeople($.cookie("shopid"), that, 1);
        },
        //新购项套餐疗程
        loadL4: function () {
            var that = this;
            that.getInit();
            that.getMarketShopPeople($.cookie("shopid"), that, 1);
        },
        //新购初始化用户信息
        getInit: function () {
            var that = this;
            that.clientUserName = window.parent.document.getElementById("clientUserName").innerText;    //客户姓名
            that.clientPhone = window.parent.document.getElementById("clientPhone").innerText; //客户手机
            that.clientSet = window.parent.document.getElementById("clientSet").innerText;   //客户性别
            that.clientURL = window.parent.document.getElementById("clientURL").src;   //客户性别
        },
        //验证凭证信息封装---------员工
        getPassParemt: function (methName, passName, personnelId) {
            var that = this;
            var pass = that.loadData1[passName];
            var personnel = personnelId;
            if (!pass) {
                return $.alert("请输入凭证信息");
            }
            if (!personnel) {
                personnel = $.cookie("id");
            }
            var data = {
                PassWord: pass,
                id: personnel,
                customerCode: that.code,
                ShopId: $.cookie("shopid"),
            }
            var url = $.stringFormat('{0}/personnelInfo/getVerification', 'http://www.4006337366.com:8080/');
            that.getPassIsFlag(methName, url, data, that);
        },
        //验证凭证信息封装----------------客户
        getPassUseParemt: function (methName, id, isFlag) {
            var that = this;
            //验证客户授权凭证
            var pass = that.ticketPrice.userPass;
            var cardId = that.ticketPrice.cardId;
            if (isFlag) {
                pass = $("#" + id).val();
                cardId = that.cardId;
            }
            if (!pass) {
                return alert("客户授权凭证不能为空")
            }
            var data = {
                usePasswd: pass,
                CustomerCode: that.code,
                cardId: cardId,
            }
            var url = $.stringFormat('{0}/frCardLimit/getVerification', 'http://www.4006337366.com:8080/');
            that.getPassIsFlag(methName, url, data, that);
        },
        getPassIsFlag: function (methName, url, data, that) {
            //避免多次点击
            if (that.randomNumber) {
                return alert("请勿太快重复点击");
            }
            //生成字符串
            that.randomNumber = Math.random().toString(36).substr(2);
            $.ajax({
                type: 'POST',
                url: url,
                data: JSON.stringify(data),
                dataType: "json",
                contentType: "application/json;charset=utf-8",
                success: function (res) {
                    if (res.code == '200') {
                        if ("验证成功" == res.data) {
                            that.randomNumber = '';
                            that[methName]();
                        }
                        return;
                    } else {
                        alert(res.msg)
                    }
                    that.randomNumber = '';
                }
            })
        },
        //判断值封装
        getParemtDate: function (obj, def) {
            var par = obj;
            if (!par || par == '' || 'undefined' == typeof (par)) {
                par = def;
            }
            return par;
        },
        //初始化新购参数
        initLoad1: function () {
            var that = this;
            var obj = that.shipCard;
            if (!obj) {
                return;
            }
            if (!that.code) {
                that.code = obj.code;
            }
            that.clientId = obj.clientId;
            that.cardId = obj.cardId;
            that.cardMap.shopId = obj.shopId;          //销售店铺
            that.cardMap.personnelId = obj.personnelId;    //员工Id
            that.cardMap.cardNumId = obj.cardNumId;      //会员卡号规则ID
            that.cardMap.cardNo = obj.cardNo;         //会员卡号
            that.cardMap.agreementId = obj.agreementId;    //编号协议ID
            that.cardMap.agreementNo = obj.agreementNo;    //协议编号
            that.cardMap.cardTypeId = obj.cardTypeId;     //卡类型ID
            that.cardMap.totalPrice = obj.totalPrice;     //总价
            that.cardMap.buyRightsNum = obj.buyRightsNum;     //购买权益
            that.cardMap.giveRightsNum = obj.giveRightsNum;    //赠送权益
            that.cardMap.bindTime = obj.bindTime;       //开卡日期
            that.cardMap.externalNo = obj.externalNo;     //外部卡号
            that.cardMap.note = obj.note;            //备注
            that.cardMap.allotSetType = obj.allotSetType;     //业绩分配设置
            that.cardMap.orderAllotSetList = obj.orderAllotSetList;     //业绩分配设置
            that.cardMap.orderAllotSetSav = obj.orderAllotSetSav;     //业绩分配设置
            that.cardMap.isFlage = obj.isFlage;
            that.cardMap.salesPrice = obj.salesPrice;
            that.payMentMoney.price = obj.totalPrice;
            that.loadData2.continueAdd.cardOpening = obj.cardOpening;
            that.loadData2.continueAdd.replacementCard = obj.replacementCard;
            that.checkDiscounts();
        },
        //随机生成订单编号
        getOrderNo: function () {
            var that = this;
            var url = $.stringFormat("{0}/frCardOrderInfo/getOrderNo", 'http://www.4006337366.com:8080/');
            $.get(url, {},
                function (res) {
                    if (res.code == '200') {
                        that.cardMap.orderNo = res.data;
                    } else {
                        alert(res.msg)
                    }
                }
            )
        },
        //随机生成会员卡号
        addCardNumMet: function (label_Id) {
            var that = this;
            if (that.LiIndex == 2) {
                var replacementCard = that.loadData2.continueAdd.replacementCard //是否更换新卡(0、否；1、是);cardNum
                if (replacementCard == 0) {
                    return;
                }
            }
            var url = $.stringFormat('{0}/frCardNum/addCardNums', 'http://www.4006337366.com:8080/');
            $.get(url, {
                    code: that.code,
                },
                function (res) {
                    if (res.code == '200') {
                        $("#" + label_Id).val(res.data.cardNo);
                        that.cardMap.cardNumId = res.data.cardNumId;
                        that.cardMap.cardNo = res.data.cardNo;
                        that.checkCardNum(label_Id);
                    } else {
                        alert(res.msg);
                    }
                })
        },
        //检索外部卡号
        checkExternalCard: function (strId) {
            var externalCard = $("#" + strId).val();
             if (externalCard.length > 32) {
                that.cardMap.externalNo = '请填写外部卡号';
                return $.alert("外部卡号过长");
            } else {
                $("#" + strId).next().html("")
            }
        },
        //随机生成协议号
        addCardAgreement: function (id) {
            var that = this;
            var url = $.stringFormat('{0}/frAgreement/addCardAgreement', 'http://www.4006337366.com:8080/');
            $.get(url, {
                    code: that.code,
                },
                function (res) {
                    if (res.code == '200') {
                        $("#" + id).val(res.data.agreement);
                        that.loadData1.cardInfoMap.agreementId = res.data.agreementId;
                        that.loadData1.cardInfoMap.agreementNo = res.data.agreement;
                        that.checkCardAgreement(id);
                    } else {
                        alert(res.msg);
                    }
                }
            )
        },
        //初始化支付金额---小计金额---票劵
        initPayModel: function () {
            var that = this;
            that.payModel.L1 = parseFloat(0).toFixed(2);
            that.payModel.L2 = parseFloat(0).toFixed(2);
            that.payModel.L3 = parseFloat(0).toFixed(2);
            that.payModel.L4 = parseFloat(0).toFixed(2);
            that.payModel.L5 = parseFloat(0).toFixed(2);
            that.payModel.L6 = parseFloat(0).toFixed(2);
            that.payModel.L7 = parseFloat(0).toFixed(2);
            that.payMentMoney.price = 0;
            that.payMentMoney.totalPrice = 0;
            that.payMentMoney.discountNum = 0;
            that.payMentMoney.needPrice = 0;
            that.payMentMoney.noPrice = 0;
            that.payMentMoney.discount = 0;
            that.payMentMoney.retChange = 0;
            that.payModelMoney = 0;
            that.initTicketPrice();
            that.initDiscountPrice();
        },
        //初始化优惠折扣部分
        initDiscountPrice: function () {
            var that = this;
            that.discount.num = 0;
            that.discount.changeNum = 0;
            that.discount.fullType = 0;
            // that.discount.discountFull = 0;
            // that.discount.discountReduce = 0;
            that.discount.discountPrice = 0;
            that.discount.discountFullOne = 0;
            that.discount.discountReduceOne = 0;
            that.discount.discountFullTwo = 0;
            that.discount.discountReduceTwo = 0;
        },
        //初始化票劵信息
        initTicketPrice: function () {
            var that = this;
            // that.ticketPrice.ticketPriceList = [];
            that.ticketPrice.ticketPriceListOk = [];
            that.ticketPrice.storage0rderPrice = 0;
            that.ticketPrice.cardNo = '未选择会员卡';
            that.ticketPrice.cardId = '';
            that.ticketPrice.cardUserName = '--';
            that.ticketPrice.differencePrice = 0;
            that.ticketPrice.totalPrice = 0;
            that.ticketPrice.storagePirc = 0;
            that.ticketPrice.userPass = '';
            that.initGiveDatail();
        },
        //初始化承接人信息
        initGiveDatail: function () {
            var that = this;
            that.catcherClientId = '';
            that.catcherClientCardId = '';   //承接的客户会员卡ID
            that.givePhone = '';         //承接客户的手机
            that.giveName = '';           //承接客户的姓名
            that.optionClientList = [];  //获取选择的会员信息
            that.optionClientCardList = []; //获取选择的会员信息
        },
        //优惠折扣检索
        checkDiscounts: function (t, str) {
            var that = this;
            var reg = /^[0-9]*$/;
            var regt = /^[0-9]+\.?[0-9]*?$/;
            var num = that.discount[str];
            if (1 == t) {
                if (num < 1 || num >= 10) {
                    num = 0;
                    $.alert("请输入打几折:1-9");
                }
                if (!reg.test(num)) {
                    num = 0;
                    $.alert("请输入正确的格式：1-9 数字");
                }
            }
            if (2 == t) {
                if (!regt.test(num)) {
                    num = 0;
                }
            }
            that.discount[str] = parseFloat(num);
            that.chageDiscount();
        },
        //重复满减切换
        switchType: function () {
            var that = this;
            that.discount.discountFullOne = 0;
            that.discount.discountReduceOne = 0;
            that.discount.discountFullTwo = 0;
            that.discount.discountReduceTwo = 0;
            that.chageDiscount();
        },
        switchFullReduction: function (val, str, valT) {
            var that = this;
            var regt = /^[0-9]+\.?[0-9]*?$/;
            var num = val;
            if (!regt.test(num)) {
                num = parseFloat(0).toFixed(2);
            }
            var objStr = parseFloat(num).toFixed(2);
            var fullType = that.discount.fullType;
            if (valT != fullType) {
                that.discount.fullType = valT;
                if (valT == 1) {
                    that.discount.discountFullTwo = 0;
                    that.discount.discountReduceTwo = 0;
                }
                if (valT == 0) {
                    that.discount.discountFullOne = 0;
                    that.discount.discountReduceOne = 0;
                }
            }
            that.discount[str] = objStr;
            that.chageDiscount();
        },
        //计算折扣优惠
        chageDiscount: function () {
            var that = this;
            that.payMentMoney.discount = 0;
            //金额
            var price = that.payMentMoney.price;
            //应付金额大于0 的基础上在开始计算
            if (price > 0) {
                //打几折
                var num = 0;
                if (that.discount.num) {
                    var disNum = that.getParemtDate(that.discount.num, 0);
                    num = (1 - (disNum / 10)) * price;
                }
                //整单去零
                var changeN = parseFloat(that.getParemtDate(that.discount.changeNum, 0));
                //满减类型
                var t = that.discount.fullType;
                // 满减折扣
                var fullMoney = 0;
                if (t == 1) {  //重复
                    //满
                    var Zmoney = that.discount.discountFullOne;
                    //减
                    var Zdismoney = that.discount.discountReduceOne;
                    if (Zmoney && Zdismoney && price >= Zmoney) {
                        var numT = parseInt(price / Zmoney);
                        if (numT > 0) {
                            fullMoney = numT * Zdismoney;
                        }
                    }
                }
                if (t == 0) {  //单次
                    //满
                    var Omoney = that.discount.discountFullTwo;
                    // 减
                    var Odismoney = that.discount.discountReduceTwo;
                    if (Omoney && Odismoney) {
                        if (price >= Omoney) {
                            fullMoney = Odismoney;
                        }
                    }
                }
                that.payMentMoney.discount = parseFloat(num) + parseFloat(changeN) + parseFloat(fullMoney);
            }
            that.isCommonMoney();
        },
        // 计算票卷抵扣
        ticketDeductiont: function () {
            var that = this;
            var allTotal = 0;
            var diff = 0;
            var len = that.ticketPrice.ticketPriceListOk;
            if (len.length > 0) {
                for (var i = 0; i < len.length; i++) {
                    allTotal = parseFloat(allTotal) + parseFloat(that.getParemtDate(len[i].tickNum, 0));
                    diff = parseFloat(diff) + parseFloat(that.getParemtDate(len[i].differ, 0));
                }
            }
            that.ticketPrice.totalPrice = allTotal;
            that.ticketPrice.differencePrice = diff;

            var storage0rderPrice = that.getParemtDate(that.ticketPrice.storage0rderPrice, 0);
            var storagePirc = that.getParemtDate(that.ticketPrice.storagePirc, 0);
            if (storagePirc > storage0rderPrice) {
                that.ticketPrice.storagePirc = parseFloat(0).toFixed(2);
                return $.alert("卡中储值金额不足");
            }
            that.payMentMoney.discountNum = parseFloat(storagePirc) + parseFloat(that.getParemtDate(that.ticketPrice.totalPrice, 0));
            that.isCommonMoney();
        },
        //计算票卷中的储值金额
        changeTicket: function () {
            var that = this;
            if (!that.ticketPrice.cardId) {
                that.ticketPrice.ticketPriceListOk = [];
                that.ticketPrice.storagePirc = parseFloat(0).toFixed(2);
                return $.alert("请先选择使用票劵的会员卡信息")
            }
            if (!that.ticketPrice.cardNo || that.ticketPrice.cardNo == '未选择会员卡') {
                that.ticketPrice.ticketPriceListOk = [];
                that.ticketPrice.storagePirc = parseFloat(0).toFixed(2);
                return $.alert("请先选择使用票劵的会员卡信息")
            }
            that.ticketDeductiont();
        },
        //判断支付金额是否符合 -------- 公共（改新购的太麻烦了，后期再修改）
        isCommonMoney: function (val, str) {
            var that = this;
            var regt = /^[0-9]+\.?[0-9]*?$/;
            var num = val;
            if (!regt.test(num)) {
                num = parseFloat(0).toFixed(2);
            }
            var objStr = parseFloat(num).toFixed(2);
            that.payModel[str] = objStr;
            that.getCommonMoney();
        },
        //计算小计 -------- 公共
        getCommonMoney: function () {
            var that = this;
            var L1 = parseFloat(that.getParemtDate(that.payModel.L1, 0));
            var L2 = parseFloat(that.getParemtDate(that.payModel.L2, 0));
            var L3 = parseFloat(that.getParemtDate(that.payModel.L3, 0));
            var L4 = parseFloat(that.getParemtDate(that.payModel.L4, 0));
            var L5 = parseFloat(that.getParemtDate(that.payModel.L5, 0));
            var L6 = parseFloat(that.getParemtDate(that.payModel.L6, 0));
            var allMoney = L1 + L2 + L3 + L4 + L5 + L6;
            that.payModelMoney = allMoney;
            var price = that.getParemtDate(that.payMentMoney.price, 0);
            var diff = that.getParemtDate(that.ticketPrice.differencePrice, 0)
            //小计 = 价格+票卷差价
            var totalPrice = parseFloat(price) + parseFloat(diff);
            that.payMentMoney.totalPrice = totalPrice;
            //优惠折扣
            var discount = that.getParemtDate(that.payMentMoney.discount, 0);
            //抵扣金额
            var discountNum = that.getParemtDate(that.payMentMoney.discountNum, 0);
            // 应付金额 =  小计-抵扣金额-优惠折扣
            that.payMentMoney.needPrice = parseFloat(totalPrice) - parseFloat(discountNum) - parseFloat(discount);
            that.payMentMoney.noPrice = 0;
            that.payMentMoney.retChange = 0;
            // 计算金额
            var money = parseFloat(that.getParemtDate(that.payMentMoney.needPrice, 0)) - parseFloat(allMoney);
            if (money > 0) {
                //未付金额
                that.payMentMoney.noPrice = parseFloat(money).toFixed(2);
            }
            if (money < 0) {
                money = Math.abs(money)
                that.payMentMoney.retChange = parseFloat(money).toFixed(2);
            }
        },
        //支付方式切换
        section4BoxTop: function (t) {
            //切换时-关闭弹窗--初始化数据
            $('.maskLayerAlert_box_alert2').hide();
            $('.maskLayerAlert_box_alert').hide();
            var that = this;
            $('#minPrice').removeAttr("disabled");
            that.loadData1.paremt.displaceCompany = '';
            that.loadData1.paremt.displaceWay = '';
            that.loadData1.paremt.authorizingUserId = '';
            that.loadData1.authorizingPass = '';
            that.payMentMoney.price = that.cardMap.totalPrice;
            that.loadData1.salesPrice = that.cardMap.salesPrice;
            $('.section4_box_top label input[name=quan]').eq(t).addClass('on').siblings().removeClass('on');
            $('.section4_box_botton fieldset').eq(t).show().siblings().hide();
            if (t == 4) {
                $('#minPrice').attr("disabled", "disabled");
                if (!that.loadData1.splitSet.cardTypeSplitSetId) {
                    that.getCardTypeSplit();
                }
                if (that.loadData1.splitSet.cardTypeSplitSetIdList.length > 0) {
                    that.payMentMoney.price = that.loadData1.splitSet.cardTypeSplitSetIdList[0].firstPrice;
                    that.loadData1.salesPrice = that.loadData1.splitSet.cardTypeSplitSetIdList[0].totalPrice;
                }
            }
            that.loadData2.salesPrice = that.loadData1.salesPrice;
            that.chageDiscount();
        },
        //获取该卡类型的分期付款信息
        getCardTypeSplit: function () {
            var that = this;
            $('.maskLayerAlert_box_alert2').show();
            var cardTypeId = that.cardMap.cardTypeId;
            if (!cardTypeId) {
                return;
            }
            if (that.loadData1.splitSet.cardTypeSplitSetList.length > 0) {
                return;
            }
            var url = $.stringFormat("{0}/frCardTypeSplitSet/getCardTypeSplitList", 'http://www.4006337366.com:8080/');
            $.get(url, {
                cardTypeId: cardTypeId,
                code: that.code,
            }, function (res) {
                if (res.code == '200') {
                    that.loadData1.splitSet.cardTypeSplitSetList = res.data;
                } else {
                    alert(res.msg)
                }
            });
        },
        //获取分期详情小窗
        getCardTypeSplitDD: function (id, num) {
            var that = this;
            that.loadData1.splitNum = num;
            if (!id) {
                id = that.loadData1.splitSet.cardTypeSplitSetId;
            }
            $('.maskLayerAlert_box_alert').show();
            var url = $.stringFormat("{0}/frCardTypeSplitSetDd/getCardTypeSplitDd", 'http://www.4006337366.com:8080/');
            $.get(url, {
                splitSetId: id,
            }, function (res) {
                if (res.code == '200') {
                    that.loadData1.splitSet.cardTypeSplitSetDD = res.data;
                } else {
                    alert(res.msg)
                }
            });
        },
        //待确认选中
        choice: function (val) {
            var that = this;
            that.loadData1.splitSet.cardTypeSplitSetIdListT = [];
            that.loadData1.splitSet.cardTypeSplitSetIdListT.push(val);
        },
        //选中了
        choiceSplit: function () {
            var that = this;
            that.loadData1.splitSet.cardTypeSplitSetIdList = that.loadData1.splitSet.cardTypeSplitSetIdListT;
            that.loadData1.splitSet.cardTypeSplitSetId = that.loadData1.splitSet.cardTypeSplitSetIdT;
            $('.maskLayerAlert_box_alert').hide();
            $('.maskLayerAlert_box_alert2').hide();
            that.payMentMoney.price = that.loadData1.splitSet.cardTypeSplitSetIdList[0].firstPrice;
            that.loadData1.salesPrice = that.loadData1.splitSet.cardTypeSplitSetIdList[0].totalPrice;
            that.loadData2.salesPrice = that.loadData1.salesPrice;
            that.checkDiscounts();
        },
        //关闭窗口
        closeCustomer: function () {
            var that = this;
            var newPopup = window.parent.document.getElementById("newPopup");
            $(newPopup).hide();
        },
        //根据卡种ID重新获取数据
        getCardUser: function () {
            var that = this;
            that.loadData2.cardName = '';
            that.loadData2.salesPrice = 0;
            that.loadData2.serviceLife = 0;
            that.loadData2.haveRightsNum = 0;
            that.cardMap.buyRightsNum = 0;
            if (!that.cardMap.cardTypeId) {
                return;
            }
            var url = $.stringFormat('{0}/frCardType/getFrCardTypeDetails', 'http://www.4006337366.com:8080/');
            $.get(url, {
                    id: that.cardMap.cardTypeId,
                },
                function (res) {
                    if (res.code == '200') {
                        that.payMentMoney.price = res.data.salesPrice
                        res.data.serviceLife=res.data.serviceLife.replace(",","")
                        that.getCardTypeInfo(res.data);
                    } else {
                        alert(res.msg)
                    }
                }
            )
        },
        // 会员消费/普通消费/员工领用/协议单位切换
        paymentMethodTop: function (t, val) {
            $('.XFFS-list li').eq(t).addClass('on').siblings().removeClass('on');
            this.paymentMethodTops = t;
            this.loadData1.paremt.consumeType = val;
        },
        //根据手机号获取会员信息
        getClientList: function (mark) {
            var that = this;
            if (!that.givePhone) {
                return;
            }
            that.ticketPrice.tickMark = mark;
            var isFlag = isPoneAvailable(that.givePhone);
            if (!isFlag) {
                that.givePhone = '';
                return $.alert("请输入正确的手机号");
            }
            var url = $.stringFormat("{0}/frClient/getClientList", 'http://www.4006337366.com:8080/');
            $.get(url, {
                    mobile: that.givePhone,
                    CustomerCode: $.cookie("code"),
                },
                function (res) {
                    if (res.code == '200') {
                        that.optionClientList = res.data;
                        console.log(that.optionClientList.length);
                        if (that.optionClientList.length > 0) {
                            $("#clientList").show();
                        }
                    } else {
                        alert(res.msg)
                    }
                }
            )
        },
        //根据选中的客户信息，获取会员卡信息
        catcherClientList: function (obj) {
            var that = this;
            var id = obj.id;
            if (!id) {
                return;
            }
            $("#clientList").hide();
            that.catcherClientId = id;
            that.giveName = obj.clientName;
            if (that.ticketPrice.tickMark) {
                that.ticketPrice.cardNo = '此会员的会员卡信息未选择';
                that.ticketPrice.cardUserName = obj.clientName;
            }
            var url = $.stringFormat('{0}/frCard/queryByFrCardListByStatus', 'http://www.4006337366.com:8080/');
            $.post(url, {
                    page: -1,
                    rows: -1,
                    clientId: id,
                    code: that.code,
                },
                function (res) {
                    if (res.code == '200') {
                        that.optionClientCardList = res.data;
                        if (that.optionClientCardList.length > 0) {
                            $("#clientCardList").show();
                        }
                    } else {
                        alert(res.msg)
                    }
                }
            )
            $("#clientList").hide();
        },
        //获取选中的会员卡信息
        catcheCardId: function (obj) {
            var that = this;
            if (!obj) {
                return;
            }

            that.catcherClientCardId = obj.id;
            that.giveName = obj.clientName;
            that.givePhone = obj.mobile;
            if (that.ticketPrice.tickMark) {
                that.ticketPrice.cardId = obj.id;
                that.ticketPrice.cardNo = obj.cardNo;
                //获取此会员卡的储值信息
                that.ticketPrice.storage0rderPrice = that.getParemtDate(obj.orderPrice, 0);
            }
            $("#clientList").hide();
            $("#clientCardList").hide();
        },
        //结账--前先验证数据是否合法
        settlement: function () {
            var that = this;
            if (!that.cardMap.shopId) {
                return $.alert("销售门店信息未获取");
            }
            if (that.LiIndex == 0) {
                that.cardMap.personnelId = that.loadData2.clitenUser.consultantId;

                var options = $("#consultantSelect option:selected");
                that.loadData2.clitenUser.consultantName=options.text().replace(/\s/g, "");
                console.log(that.cardMap.consultantName)
                if(! that.loadData2.clitenUser.fwhjName){
                    return $.alert("服务顾问未选择");
                }
            }
            if (!that.cardMap.personnelId) {
                return $.alert("销售顾问未选择");
            }

            if (!that.cardMap.cardNo) {
                return $.alert("会员卡号未获取");
            }
            if (that.LiIndex == 1) {
                if (!that.cardMap.agreementNo) {
                    return $.alert("协议编号未获取");
                }
            }
            if (that.cardMap.isFlage == 1) {
                if (!that.cardMap.bindTime) {
                    // return $.alert("开卡，请设置开卡日期");
                }
            }
            // if (!that.cardMap.externalNo) {
            //     return $.alert("外部卡号未设置");
            // }
            that.uploadAvatar()
            if (that.cardMap.allotSetType == 1) {
                // return $.alert("业绩分配信息设置有误");
                that.loadData1.orderAllotSetSav = that.cardMap.orderAllotSetSav;
                that.loadData1.orderAllotSetList = that.cardMap.orderAllotSetList;

                if (!that.loadData1.orderAllotSetSav.allotType) {
                    that.loadData1.orderAllotSetSav = that.cardMap.orderAllotSetSav;
                    if (!that.loadData1.orderAllotSetSav.allotType) {
                        return $.alert("销售价格分配比例/金额类型需选择")
                    }
                }
                if (!that.loadData1.orderAllotSetSav.allotNum) {
                    return $.alert("销售价格分配比例/金额需填写")
                }
                if (!that.loadData1.orderAllotSetSav.saleAllotType) {
                    return $.alert("业绩分配类型有误，请重新设置")
                }
                if (that.loadData1.orderAllotSetList.length <= 0) {
                    return $.alert("设置的业绩比例信息有问题，请重新设置")
                }
            }
            if (that.payMentMoney.discountNum > 0) {
                if (!that.catcherClientCardId) {
                    return $.alert("未获取抵扣客户的会员卡Id");
                }
            }
            //--------------根据付款类型验证数据
            var num = that.getParemtDate(that.loadData1.paremt.payType, 1);  //默认是全款
            if (num == 1 || num == 5) {
                // 总价（实际应付金额）
                var price = that.getParemtDate(that.payMentMoney.price, 0);
                // 小计 = 实际应付金额 + 票卷差价（默认差价0）
                var totalPrice = that.getParemtDate(that.payMentMoney.totalPrice, 0);
                //抵扣金额
                var discountNum = that.getParemtDate(that.payMentMoney.discountNum, 0);
                //优惠折扣
                var discount = that.getParemtDate(that.payMentMoney.discount, 0);
                //计算后的应付金额
                var needPrice = that.getParemtDate(that.payMentMoney.needPrice, 0);
                //未付金额
                var noPrice = that.getParemtDate(that.payMentMoney.noPrice, 0);
                //找零
                var retChange = that.getParemtDate(that.payMentMoney.retChange, 0);
                //实际支付的金额
                var payModelMoney = that.getParemtDate(that.payModelMoney, 0);
                var str = "全款支付，请全额支付";
                if (num == 5) {
                    str = "请全款支付首付金额";
                    if (!that.loadData1.splitSet.cardTypeSplitSetId) {
                        return $.alert("分期类型未选择");
                    }
                }
                if (payModelMoney < needPrice) {
                    return $.alert(str);
                }
            }
            if (num == 2) {
                if (!that.loadData1.paremt.depositTime) {
                    return $.alert("定金，请选择补余期限");
                }
                that.cardMap.depositTime = that.loadData1.paremt.depositTime;

                if (that.loadData1.paremt.depositTime == '其他') {
                    if (!that.loadData1.depositTime) {
                        return $.alert("其他，请填写补余期限");
                    }
                    that.cardMap.depositTime = that.loadData1.depositTime;
                }
                if (!that.payModelMoney || that.payModelMoney <= 0) {
                    return $.alert("定金，请输入定金金额");
                }
            }
            //默认新购的结算方法名称
            that.accountsMeth = 'toSettlement';
            if (that.LiIndex == 2) {
                //续卡结算方法名称
                that.accountsMeth = 'toContinue';
            }
            if (that.LiIndex == 0) {
                //新建现有会员
                that.accountsMeth = 'saveCustomer';
                if (!that.loadData2.clitenUser.clientName) {
                    return $.alert("请填写会员名称");
                }
                if (!that.loadData2.clitenUser.sex && that.loadData2.clitenUser.sex != 0) {
                    return $.alert("请选择性别");
                }
                if (!that.loadData2.clitenUser.mobile) {
                    return $.alert("手机号码需填写");
                }
                if (!that.loadData2.clitenUser.consultantId) {
                    return $.alert("销售顾问需选择");
                }
            }
            if (num == 3 || num == 4) {
                var str = '请先选择赠送人';
                if (num == 4) {
                    //置换但是否需要验证？
                    if (!that.loadData1.paremt.displaceCompany) {
                        return $.alert("请先填写置换单位");
                    }
                    if (!that.loadData1.paremt.displaceWay) {
                        return $.alert("请先填写置换方式");
                    }
                    str = '请先选择审核人';
                }
                if (!that.loadData1.paremt.authorizingUserId) {
                    return $.alert(str);
                }
                if (!that.loadData1.authorizingPass) {
                    return $.alert("请输入凭证信息");
                }
                var autUserId = that.loadData1.paremt.authorizingUserId;
                return that.getPassParemt("toSettlementConfirm", "authorizingPass", autUserId);
            }
            that.commonConfirm(that.accountsMeth, '');

        },
        //提交--数据验证通过后--跳转提示框
        toSettlementConfirm: function () {
            var that = this;
            that.commonConfirm(that.accountsMeth, '');
        },
        //提交订单信息----新购
        toSettlement: function () {
            var that = this;
            var type = that.discount.fullType;
            var discountFull = 0;
            var discountReduce = 0
            if (type == 0) {
                discountFull = that.discount.discountFullTwo;
                discountReduce = that.discount.discountReduceTwo;
            }
            if (type == 1) {
                discountFull = that.discount.discountFullOne;
                discountReduce = that.discount.discountReduceOne;
            }
            if (that.randomNumber) {
                return alert("请勿太快重复点击");
            }
            //生成字符串
            that.randomNumber = Math.random().toString(36).substr(2);
            // 支付
            var payModel = that.payModel;
            // 协议号规则
            var agreementMap = {
                agreementId: that.cardMap.agreementId,         //编号协议ID
                agreementNo: that.cardMap.agreementNo,         //协议编号
            }
            // 分期
            var orderSplitId = that.loadData1.splitSet.cardTypeSplitSetId;
            //业绩分配
            var orderAllotSetList = that.loadData1.orderAllotSetList;
            //业绩分配
            var orderAllotSet = that.loadData1.orderAllotSetSav;
            var haveRightsNum = parseFloat(that.getParemtDate(that.cardMap.buyRightsNum, 0)) + parseFloat(that.getParemtDate(that.cardMap.giveRightsNum, 0))
            var cardMap = {
                // id:that.cardId,           //会员Id
                cardNo: that.cardMap.cardNo,           //会员卡号
                clientId: that.clientId,               //客户ID
                CustomerCode: that.code,               //客户代码
                shopId: that.cardMap.shopId,           //店铺ID
                cardTypeId: that.cardMap.cardTypeId, //卡类型ID
                bindTime: that.cardMap.bindTime,      //开卡日期
                cardNumId: that.cardMap.cardNumId,    //会员卡号规则ID
                haveRightsNum: haveRightsNum,           //拥有的总权益
                externalNo: that.cardMap.externalNo,  //外部卡号
                note: that.cardMap.note,               //备注
                // originalId:'',                       //卡前设置ID
                // type:that.cardMap.bindTime,          //卡类型
                // status:'',                           //会员卡状态，已开卡未开卡
                // invalidTime:that.cardMap.cardTypeId, //失效时间
            };
            var cardInfoMap = {
                cardId: that.cardId, //会员卡ID
                cardTypeId: that.cardMap.cardTypeId,//会员卡类型ID
                orderNo: that.cardMap.orderNo,//订单编号
                clientId: that.clientId,//客户ID
                payType: that.loadData1.paremt.payType,//付款方式
                consumeType: that.loadData1.paremt.consumeType,//消费方式
                totalPrice: that.payMentMoney.price,//总价
                discount: that.discount.num,//优惠折扣
                changeNum: that.discount.changeNum,//整单去零
                fullType: that.discount.fullType,//满减优惠类型
                discountFull: discountFull,//满
                discountReduce: discountReduce,//减
                discountPrice: that.payMentMoney.discount,//折扣/优惠
                discountNum: that.payMentMoney.discountNum,//抵扣金额
                needPrice: that.payMentMoney.needPrice,//应付金额
                noPrice: that.payMentMoney.noPrice,//未付金额
                retChange: that.payMentMoney.retChange,//找零
                shopId: $.cookie("shopid"),//基础表门店ID
                personnelId: that.cardMap.personnelId,//人员（员工）ID
                type: '',//订单类型（1、新购；2、续卡;3、转卡；4、卡升级）
                CustomerCode: that.code,//客户代码
                buyRightsNum: that.cardMap.buyRightsNum,//购买权益
                giveRightsNum: that.cardMap.giveRightsNum,//赠送权益
                allotSetType: that.cardMap.allotSetType,//业绩分配设置（0、无业绩分配；1、有业绩分配）
                depositTime: that.cardMap.depositTime,//补余期限
                invoiceStatus: that.cardMap.invoiceStatus,//是否购置发票（0：否，1：是）
                authorizingUserId: that.loadData1.paremt.authorizingUserId,//授权人ID
                displaceCompany: that.loadData1.paremt.displaceCompany,//置换单位
                displaceWay: that.loadData1.paremt.displaceWay,//置换方式
                giveClientCardId: that.catcherClientCardId,//抵扣人的会员卡ID
                // orderState:'',//订单状态
                // flag:'',//备用
                // cardSetId:'',//关联的会员卡设置Id
            }
            var data = {
                agreementMap: JSON.stringify(agreementMap),
                payModel: JSON.stringify(payModel),
                cardMap: JSON.stringify(cardMap),
                cardInfoMap: JSON.stringify(cardInfoMap),
                orderSplitId: orderSplitId,
                orderAllotSetList: JSON.stringify(orderAllotSetList),
                orderAllotSet: JSON.stringify(orderAllotSet),
            }
            var url = $.stringFormat('{0}/frCard/addCardDataList', 'http://www.4006337366.com:8080/');
            $.ajax({
                type: 'POST',
                url: url,
                data: JSON.stringify(data),
                dataType: "json",
                contentType: "application/json;charset=utf-8",
                success: function (res) {
                    if (res.code == '200') {
                        //给父页面返回信息
                        that.initPayModel();
                        that.toChildSub(true, "提交成功", that.LiIndex);
                    } else {
                        alert(res.msg)
                    }
                    that.randomNumber = '';
                }
            });
        },
        //第一个是确认要调用的方法名称，第二个是取消要调用的方法名称
        commonConfirm: function (methName, methNameTwo) {
            var that = this;
            $.confirm({
                title: '确认',
                content: '请确认是否提交此数据?',
                icon: 'glyphicon glyphicon-question-sign',
                buttons: {
                    ok: {
                        text: '确认',
                        btnClass: 'btn-primary',
                        action: function () {

                            return that[methName]();
                        }
                    },
                    cancel: {
                        text: '取消',
                        btnClass: 'btn-primary',
                        action: function () {
                            if (methNameTwo) {
                                return that[methNameTwo]();
                            }
                        }
                    }
                }
            })

        },
        //提交成功后给父页传值
        toChildSub: function (isFlag, mess, type) {
            var that = this;
            var methone = 'toSubChild';
            that.closeCustomer();
            return window.parent[methone](isFlag, mess, type);
        },
        //初始化绑定时间
        chackBindTime: function () {
            console.log()
            var that = this;
            var num = that.cardMap.isFlage;
            that.cardMap.bindTime = '';
            $('#bindTime').attr("disabled", "disabled");
            if (num == 1) {
                $('#bindTime').removeAttr("disabled");
            }
        },
        //续卡--客户信息初始化
        getClientUser: function () {
            var that = this;
            var url = $.stringFormat("{0}/frClient/getClient", 'http://www.4006337366.com:8080/');
            $.get(url, {
                    id: that.clientId,
                },
                function (res) {
                    if (res.code == '200') {
                        that.loadData2.clitenUser = res.data;
                        console.log(that.loadData2.clitenUser);
                    } else {
                        alert(res.msg)
                    }
                }
            );
        },
        //续卡--初始会员卡信息
        getCardInfo: function () {
            var that = this;
            var url = $.stringFormat("{0}/frCardOrderInfo/queryCardAndType", 'http://www.4006337366.com:8080/');
            $.get(url, {
                    cardId: that.cardId,
                    code: that.code,
                    clientId: that.clientId,
                },
                function (res) {
                    if (res.code == '200') {
                        that.loadData2.cardDatail = res.data;
                        return that.continueInfo(that.loadData2.cardDatail);
                    } else {
                        $.alert(res.msg)
                    }
                }
            );
        },
        //获取销售门店列表
        getMarketShopList: function () {
            var that = this;
            var shopId = that.cardMap.shopId;
            var url = $.stringFormat('{0}/shop/getMarketShopList', 'http://www.4006337366.com:8080/');
            $.get(url, {
                    type: 0,//（0为获取上次添加卡类型时间，在这边无意义）
                },
                function (res) {
                    if (res.code == '200') {
                        that.loadData1.shopList = res.data.data;
                    } else {
                        alert(res.msg);
                    }
                }
            )
        },
        //选择会员卡弹窗
        products: function () {
            var that = this;
            //赋值当前门店名称
            var shopId = that.cardMap.shopId;
            if (!shopId) {
                return;
            }
            that.loadData1.cardsList = [];
            that.loadData2.cardFlagList = [];
            //根据门店获取门店下的所有卡种
            var url = $.stringFormat('{0}/frCardType/getByShopIdAllList', 'http://www.4006337366.com:8080/');
            $.get(url, {
                    shopId: shopId,//门店id
                    type: 0,//卡种类型
                    CustomerCode: that.code,
                },
                function (res) {
                    if (res.code == '200') {
                        that.loadData1.cardsList = res.data;
                        var temp=[]
                        var cardF = [];
                        if (that.loadData1.cardsList && that.loadData1.cardsList.length > 0) {
                            for (var i = 0; i < that.loadData1.cardsList.length; i++) {
                                var obj = that.loadData1.cardsList[i];
                                // console.log(obj)
                                var f = obj.cardFlag;
                                if (cardF.indexOf(f) == -1) {
                                    cardF.push(f);
                                    var data = {
                                        cardFlag: f,
                                        cardTypeId: obj.id
                                    }

                                    temp.push(data);

                                }
                            }
                        }
                        that.cardFlagList=temp
                        console.log(that.cardFlagList)
                        //切换门店卡种信息更新
                        var obj = {};
                        that.checkCardType(obj);
                    } else {
                        $.alert(res.msg)
                    }
                }
            )
            that.getMarketShopPeople(that.cardMap.shopId, that, 1);
            that.getMarketShopPeople(that.cardMap.shopId, that, 2);
        },
        //选中卡系列
        checkCardType: function (obj) {
            var that = this;
            that.cardMap.cardTypeId = that.getParemtDate(obj.id, '');
            that.loadData2.careFlag = that.getParemtDate(obj.cardFlag, '');
            that.loadData1.paremt.payType = 1;
            that.section4BoxTop(0);
            that.getCardUser();
        },
        //检索会员卡号是否符合规则
        checkCardNum: function (id) {
            var cardNum = $("#" + id).val();
            var myreg = /^[0-9]*$/;
            if (!myreg.test(cardNum)) {
                $("#" + id).val('');
                return $.alert("会员卡号不符合");
            }
            var url = $.stringFormat('{0}/frCardNum/checkCardNum', 'http://www.4006337366.com:8080/');
            $.get(url, {
                    cardNum: cardNum,
                },
                function (res) {
                    if (res.code == '200') {
                        $("#spa1").html("会员卡号符合规则").css("color", "green").css("margin-left", "5px");
                    } else {
                        $("#" + id).val('');
                        return $.alert("会员卡号不符合");
                    }
                }
            )
        },
        //判断是否低于买卡最低价
        checkMinPrice: function () {
            var that = this;
            var minPrice1 = that.payMentMoney.price;//填写的值
            var minPrice = that.loadData2.addCardData.minPrice;
            that.loadData2.minPrice = false;
            if (minPrice1 != '') {
                if (minPrice1 < minPrice) {
                    that.payMentMoney.price = parseFloat(minPrice).toFixed(0);
                    $.alert("价格低于最低销售金额");
                } else {
                    $("#minPrice1").html("");
                    that.loadData2.minPrice = true;
                }
            }
            that.cardMap.totalPrice = that.payMentMoney.price;
            that.checkDiscounts();
        },
        //关闭弹窗
        colseX: function (id) {
            $("#" + id).hide();
        },
        //初始化续卡会员卡信息
        continueInfo: function (obj) {
            var that = this;
            console.log("获取续卡订单会员卡信息===========");
            console.log(obj);
            if (!obj.bId) {
                alert("此会员卡的卡种已经禁用了，请先操作转卡");
                return that.closeCustomer();
            }
            if (obj.orderState != 3 && obj.orderState != 4 && obj.orderState != 5) {
                alert("此会员卡未结款，不能操作续卡");
                return that.closeCustomer();
            }
            obj.salesPrice = obj.bsalesPrice;
            obj.totalNum = obj.btotalNum;
            that.getCardTypeInfo(obj);
        },
        //根据获取的catdType初始化信息
        getCardTypeInfo: function (cardTypeD) {
            var that = this;
            console.log("获取续卡订单会员卡信息==========1");
            if (cardTypeD) {
                //赋值卡种名
                $(".section3_box_botton_rigth strong").eq(0).html(cardTypeD.cardTypeName);
                that.loadData1.salesPrice = cardTypeD.salesPrice; //售价
                var give = that.getParemtDate(that.cardMap.giveRightsNum, 0);
                var totalNum = that.getParemtDate(cardTypeD.totalNum, 0)
                $(".section3_box_botton_rigth strong").eq(2).html(parseFloat(give) + parseFloat(totalNum));
                $(".section3_box_botton_rigth strong").eq(4).html(cardTypeD.serviceLife);
                $(".section3_box_botton_rigth strong").eq(5).html(cardTypeD.totalNum);
                $(".section3_box_botton_rigth strong").eq(6).html(give);
                that.loadData2.cardName = cardTypeD.cardTypeName;
                that.loadData2.salesPrice = cardTypeD.salesPrice;
                that.loadData2.serviceLife = cardTypeD.serviceLife;
                that.loadData2.haveRightsNum = parseFloat(give) + parseFloat(totalNum);
                that.cardMap.buyRightsNum = totalNum;
                that.cardMap.giveRightsNum = give;
                that.cardMap.salesPrice = cardTypeD.salesPrice;
                if (cardTypeD.qtXxjgUpdate) {//判断是否可修改实际销售价格
                    that.loadData2.addCardData.minPrice = cardTypeD.qtZdxxjg;//前台可修改最低销售价格
                } else {
                    that.loadData2.addCardData.minPrice = cardTypeD.salesPrice;
                }
                if (cardTypeD.qtZsqyUpdate) {//判断 前台是否可修改赠送权益
                    that.loadData2.addQtZdqy = cardTypeD.qtZdqy;//前台赠送权益
                }
                $('#minPrice').removeAttr("disabled"); //默认是可修改的
                if (that.LiIndex == 2) {  //续卡操作这是续卡价格信息
                    that.cardMap.personnelId = cardTypeD.personnelId;
                    var price = that.getParemtDate(cardTypeD.renewCommonPrice, 0);
                    if (price <= 0) {
                        price = that.getParemtDate(cardTypeD.CrenewCommonPrice, 0);
                    }
                    if ('普通会员' == cardTypeD.levelName) {
                        price = that.getParemtDate(cardTypeD.renewLevel1Price, 0);
                        if (price <= 0) {
                            price = that.getParemtDate(cardTypeD.CrenewLevel1Price, 0);
                        }
                    }
                    if ('银卡会员' == cardTypeD.levelName) {
                        price = that.getParemtDate(cardTypeD.renewLevel2Price, 0);
                        if (price <= 0) {
                            price = that.getParemtDate(cardTypeD.CrenewLevel2Price, 0);
                        }
                    }
                    if ('金卡会员' == cardTypeD.levelName) {
                        price = that.getParemtDate(cardTypeD.renewLevel3Price, 0);
                        if (price <= 0) {
                            price = that.getParemtDate(cardTypeD.CrenewLevel3Price, 0);
                        }
                    }
                    if ('钻石会员' == cardTypeD.levelName) {
                        price = that.getParemtDate(cardTypeD.renewLevel4Price, 0);
                        if (price <= 0) {
                            price = that.getParemtDate(cardTypeD.CrenewLevel4Price, 0);
                        }
                    }
                    that.payMentMoney.price = parseFloat(price).toFixed(2);
                    that.cardMap.totalPrice = that.payMentMoney.price
                    console.log("续卡价格=====================");
                    console.log(that.cardMap.salesPrice);
                    //未设置续卡价格，开放修改
                    if (price < that.loadData2.addCardData.minPrice) {
                        that.loadData2.addCardData.minPrice = price;//前台可修改最低销售价格
                    }
                    if (!cardTypeD.qtCanRenew && that.payMentMoney.price > 0) {  //判断是否可修改续卡价格？ fals 不可吸怪
                        $("#minPrice").attr("disabled", "disabled");
                    }
                    //默认另行开卡
                    $('#bindTime').removeAttr("disabled");
                    $("#wu").attr("disabled", "disabled");
                    that.cardMap.isFlage = 1;
                    //开卡时间
                    var cardBindTime = cardTypeD.cardBindTime;
                    //卡失效时间
                    var cardInvalidTime = cardTypeD.cardInvalidTime;
                    //有效期限
                    var serviceLife = that.loadData2.serviceLife;
                    if (!cardBindTime && that.loadData2.continueAdd.cardOpening == 0) {   //开卡方式（0，直接延续 ； 1，另行开卡）
                        alert("直接延续只支持已开卡的会员卡，请重新选择另行开卡");
                        return that.closeCustomer();
                    }
                    if (that.loadData2.continueAdd.cardOpening == 0) {
                        $("#you").attr("disabled", "disabled");
                        $("#bindTime").attr("disabled", "disabled");
                        if (!cardInvalidTime) {
                            return $.alert("卡失效时间获取失败,请先填写其他");
                        }
                        var nums = 0;
                        var interval = '';
                        if (serviceLife) {
                            var spli = serviceLife.split(",");
                            if (spli) {
                                nums = spli[0];
                                interval = spli[1];
                            }
                        }
                        if (nums <= 0) {
                            alert("此卡种未设置有效期限，请重新选择其他卡种");
                            return that.closeCustomer();
                        }
                        var dateS = new Date(cardInvalidTime.replace(/-/g, "/"));
                        var d = DateAdd(interval, parseInt(nums), dateS);
                        that.cardMap.bindTime = formatDate(d);
                    }
                    that.cardMap.cardNo = cardTypeD.cardNo;
                    that.cardMap.externalNo = cardTypeD.externalNo;
                    that.loadData2.careFlag = cardTypeD.cardFlag;
                    that.cardMap.shopId = cardTypeD.shopId
                    $("#externalCard").attr("disabled", "disabled");
                    var replacementCard = that.loadData2.continueAdd.replacementCard //是否更换新卡(0、否；1、是);cardNum
                    $("#cardNum").attr("disabled", "disabled");
                    if (replacementCard == 1) {
                        that.addCardNumMet('cardNum');
                    }
                }
                that.chageDiscount();
            }
        },
        //=========================业绩分配
        initAllotSet: function () {
            var that = this;
            that.loadData1.orderAllotSetListOne = [];
            that.loadData1.orderAllotSetList = [];
            that.loadData1.allotNumOne = 0;
            that.loadData1.allotNumTwo = 0;
            that.loadData1.orderAllotSetSav.allotType = '';
            that.loadData1.orderAllotSetSav.allotNum = 0;
            that.loadData1.orderAllotSetSav.saleAllotType = 0;
            that.loadData1.orderAllotSet.personnelId = '';
            that.loadData1.orderAllotSet.saleAllotNum = '';
            that.loadData1.orderAllotSet.allotType = '';
            that.loadData1.orderAllotSet.allotNumOne = 0;
            that.loadData1.orderAllotSet.allotNumTwo = 0;
            that.loadData1.orderAllotSet.saleAllotType = 0;
        },
        addOrderAllotSetOne: function () {
            var that = this;
            if (!that.loadData1.orderAllotSet.personnelId) {
                return alert("请选择销售人员");
            }
            if (!that.loadData1.orderAllotSet.saleAllotNum) {
                return alert("请填写业分配比例/金额");
            }
            var val = {
                personnelId: that.loadData1.orderAllotSet.personnelId,
                saleAllotNum: that.loadData1.orderAllotSet.saleAllotNum,
            }
            that.loadData1.orderAllotSetListOne.push(val)
        },
        //保存并关闭弹窗
        addOrderAllotSet: function () {
            //点击保存的时候，设置订单里的业绩分配为1 表示已设置
            var that = this;
            var allotType = that.loadData1.orderAllotSet.allotType;
            var allotNum = 0;
            if (!allotType) {
                return $.alert("销售价格分配比例/金额未选择")
            }
            if (allotType == 1) {
                if (!that.loadData1.orderAllotSet.allotNumOne) {
                    return $.alert("销售业绩比例需填写")
                }
                allotNum = that.loadData1.orderAllotSet.allotNumOne;
            }
            if (allotType == 2) {
                if (!that.loadData1.orderAllotSet.allotNumTwo) {
                    return $.alert("销售业绩金额需填写")
                }
                allotNum = that.loadData1.orderAllotSet.allotNumTwo;
            }
            var saleAllotType = that.loadData1.orderAllotSet.saleAllotType;
            if (!saleAllotType) {
                return $.alert("销售人员销售业绩分配比例/金额需选择");
            }
            if (that.loadData1.orderAllotSetListOne.length <= 0) {
                return $.alert("业绩分配销售人员未设置");
            }
            that.loadData1.orderAllotSetSav.allotType = allotType;
            that.loadData1.orderAllotSetSav.allotNum = allotNum;
            that.loadData1.orderAllotSetSav.saleAllotType = saleAllotType;
            that.loadData1.orderAllotSetList = [];
            if (that.loadData1.orderAllotSetListOne.length > 0) {
                for (var i = 0; i < that.loadData1.orderAllotSetListOne.length; i++) {
                    var val = {
                        personnelId: that.loadData1.orderAllotSetListOne[i].personnelId,
                        saleAllotNum: that.loadData1.orderAllotSetListOne[i].saleAllotNum,
                    }
                    that.loadData1.orderAllotSetList.push(val);
                }
            }
            that.cardMap.allotSetType = 1;
            that.newCustomers_maskLayer_X();
        },
        //打开弹窗
        performances: function () {
            var that = this;
            $('#performance').show();
            var allotType = that.loadData1.orderAllotSetSav.allotType;
            var allotNum = that.loadData1.orderAllotSetSav.allotNum;
            if (allotType == 1) {
                that.loadData1.orderAllotSet.allotNumOne = allotNum;
            }
            if (allotType == 2) {
                that.loadData1.orderAllotSet.allotNumTwo = allotNum;
            }
            var saleAllotType = that.loadData1.orderAllotSetSav.saleAllotType;
            that.loadData1.orderAllotSet.allotType = allotType;
            that.loadData1.orderAllotSet.allotNum = allotNum;
            that.loadData1.orderAllotSet.saleAllotType = saleAllotType;

            if (that.loadData1.orderAllotSetList.length > 0) {
                for (var i = 0; i < that.loadData1.orderAllotSetList.length; i++) {
                    var val = {
                        personnelId: that.loadData1.orderAllotSetList[i].personnelId,
                        saleAllotNum: that.loadData1.orderAllotSetList[i].saleAllotNum,
                    }
                    that.loadData1.orderAllotSetListOne.push(val);
                }
            }
        },
        // 关闭弹窗
        newCustomers_maskLayer_X: function () {
            var that = this;
            $('.newCustomers_maskLayer').hide();
            var num = that.loadData1.orderAllotSetListOne.length;
            that.loadData1.orderAllotSetListOne.length = 0;
            if (num > 0) {
                for (var i = 0; i < num; i++) {
                    $('.orderAllotSet').eq(i).remove();
                }
            }
            that.loadData1.orderAllotSet.personnelId = '';
            that.loadData1.orderAllotSet.saleAllotNum = '';
            that.loadData1.orderAllotSet.allotType = '';
            that.loadData1.orderAllotSet.allotNumOne = 0;
            that.loadData1.orderAllotSet.allotNumTwo = 0;
            that.loadData1.orderAllotSet.saleAllotType = 0;
        },
        //提交订单信息----续卡
        toContinue: function () {
            var that = this;
            if (!that.cardId) {
                return $.alert("会员卡ID获取有误");
            }
            var type = that.discount.fullType;
            var discountFull = 0;
            var discountReduce = 0
            if (type == 0) {
                discountFull = that.discount.discountFullTwo;
                discountReduce = that.discount.discountReduceTwo;
            }
            if (type == 1) {
                discountFull = that.discount.discountFullOne;
                discountReduce = that.discount.discountReduceOne;
            }
            if (that.randomNumber) {
                return alert("请勿太快重复点击");
            }
            //生成字符串
            that.randomNumber = Math.random().toString(36).substr(2);
            // 支付
            var payModel = that.payModel;
            var continueMap = that.loadData2.continueAdd;
            // 分期
            var orderSplitId = that.loadData1.splitSet.cardTypeSplitSetId;
            //业绩分配
            var orderAllotSetList = that.loadData1.orderAllotSetList;
            //业绩分配
            var orderAllotSet = that.loadData1.orderAllotSetSav;
            var cardMap = {
                id: that.cardId,           //会员Id
                cardNo: that.cardMap.cardNo,           //会员卡号
                clientId: that.clientId,               //客户ID
                CustomerCode: that.code,               //客户代码
                shopId: that.cardMap.shopId,           //店铺ID
                cardNumId: that.cardMap.cardNumId,    //会员卡号规则ID
                externalNo: that.cardMap.externalNo,  //外部卡号
                bindTime: that.cardMap.bindTime,      //开卡时间
                note: that.cardMap.note,               //备注
            };
            var cardInfoMap = {
                cardId: that.cardId, //会员卡ID
                cardTypeId: that.cardMap.cardTypeId,//会员卡类型ID
                orderNo: that.cardMap.orderNo,//订单编号
                clientId: that.clientId,//客户ID
                payType: that.loadData1.paremt.payType,//付款方式
                consumeType: that.loadData1.paremt.consumeType,//消费方式
                totalPrice: that.payMentMoney.price,//总价
                discount: that.discount.num,//优惠折扣
                changeNum: that.discount.changeNum,//整单去零
                fullType: that.discount.fullType,//满减优惠类型
                discountFull: discountFull,//满
                discountReduce: discountReduce,//减
                discountPrice: that.payMentMoney.discount,//折扣/优惠
                discountNum: that.payMentMoney.discountNum,//抵扣金额
                needPrice: that.payMentMoney.needPrice,//应付金额
                noPrice: that.payMentMoney.noPrice,//未付金额
                retChange: that.payMentMoney.retChange,//找零
                shopId: $.cookie("shopid"),//基础表门店ID
                personnelId: that.cardMap.personnelId,//人员（员工）ID
                type: 2,//订单类型（1、新购；2、续卡;3、转卡；4、卡升级）
                CustomerCode: that.code,//客户代码
                buyRightsNum: that.cardMap.buyRightsNum,//购买权益
                giveRightsNum: that.cardMap.giveRightsNum,//赠送权益
                allotSetType: that.cardMap.allotSetType,//业绩分配设置（0、无业绩分配；1、有业绩分配）
                depositTime: that.cardMap.depositTime,//补余期限
                invoiceStatus: that.cardMap.invoiceStatus,//是否购置发票（0：否，1：是）
                authorizingUserId: that.loadData1.paremt.authorizingUserId,//授权人ID
                displaceCompany: that.loadData1.paremt.displaceCompany,//置换单位
                displaceWay: that.loadData1.paremt.displaceWay,//置换方式
                giveClientCardId: that.catcherClientCardId,//抵扣人的会员卡ID
            }
            var data = {
                continueMap: JSON.stringify(continueMap),
                payModel: JSON.stringify(payModel),
                cardMap: JSON.stringify(cardMap),
                cardInfoMap: JSON.stringify(cardInfoMap),
                orderSplitId: orderSplitId,
                orderAllotSetList: JSON.stringify(orderAllotSetList),
                orderAllotSet: JSON.stringify(orderAllotSet),
            }
            var url = $.stringFormat('{0}/frCardSupplyRecord/addContinueList', 'http://www.4006337366.com:8080/');
            $.ajax({
                type: 'POST',
                url: url,
                data: JSON.stringify(data),
                dataType: "json",
                contentType: "application/json;charset=utf-8",
                success: function (res) {
                    if (res.code == '200') {
                        that.initPayModel();
                        //给父页面返回信息
                        that.toChildSub(true, "提交成功", that.LiIndex);
                    } else {
                        alert(res.msg)
                    }
                    that.randomNumber = '';
                }
            });
        },
        //选择图片弹窗
        pictureSelected: function () {
            var that = this;
            $('#file-fr').click();
        },
        //显示图片
        pictureShow: function (e) {
            console.log(e)
            var that = this;
            var file = e.srcElement.files.item(0);
            var num = 'price1'
            // 看支持不支持FileReader
            if (!file || !window.FileReader) return;

            if (/^image/.test(file.type)) {
                // 创建一个reader
                var reader = new FileReader();
                // 将图片将转成 base64 格式
                reader.readAsDataURL(file);
                // 读取成功后的回调
                reader.onloadend = function () {
                    $("#" + num).attr('src', this.result);
                    // that.loadDatat0.userURL = this.result;
                }
            }
        },
        //根据手机号获取会员信息
        getClientList: function (mark) {
            var that = this;
            if (!that.givePhone) {
                return;
            }
            that.ticketPrice.tickMark = mark;
            var isFlag = isPoneAvailable(that.givePhone);
            if (!isFlag) {
                that.givePhone = '';
                return $.alert("请输入正确的手机号");
            }
            var url = $.stringFormat("{0}/frClient/getClientList", 'http://www.4006337366.com:8080/');
            $.get(url, {
                    mobile: that.givePhone,
                    CustomerCode: $.cookie("code"),
                },
                function (res) {
                    if (res.code == '200') {
                        console.log("获取客户信息==========================================");
                        console.log(res);
                        that.optionClientList = res.data;
                        if (that.optionClientList.length > 0) {
                            $("#clientList").show();
                        }
                    } else {
                        $.alert(res.msg)
                    }
                }
            )
        },
        // 上传图片
        uploadAvatar: function () {
            const that = this;
            var isSuccess = false;
            var f = that.$refs.avatar;
            console.log(f)
            //判断是否有图片
            if (!f) {
                return $.alert("请选择图片");
            }
            //判断图片大小是否超过5MB
            if (f.length > 5242880) {
                return $.alert("图片大小不得大于5MB");
            }
            Loading.prototype.show();
            var param = new FormData();
            param.append('file', f.files[0]); //通过append向form对象添加数据
            param.append('childPath', 'avatar/'); //通过append向form对象添加数据
            const url = $.stringFormat('{0}/file/upload', 'http://www.4006337366.com:8080/');
            console.log(param)
            $.ajax({
                type: 'POST',
                url: url,
                data: param,
                dataType: "json",
                processData: false,
                contentType: false,
                cache: false,
                success: function (res) {
                    if (res.code == '200') {
                        that.loadDatat0.userURL = res.data.imgUrl;
                        console.log(that.loadDatat0.userURL)
                        return $.alert(res.data.msg);
                    } else {
                        $.alert(res.msg)
                    }
                    Loading.prototype.hide();
                    that.randomNumberAdd = '';
                }
            });
            return isSuccess;
        },
        //检索数据
        getUserDatat: function (num, str, t) {
            var that = this;
            var mess = '';
            var isFlag = false;
            //检索手机
            if (t == 1) {
                isFlag = isPoneAvailable(num);
                mess = "请输入正确的手机号";
            }
            //检索电话号码
            if (t == 2) {
                isFlag = isTelAvailable(num);
                mess = "请输入正确的电话";
            }
            //检索电话号码
            if (t == 3) {
                var isFlag = checkNum(num);
                mess = "只能输入纯数字";
            }
            //检索电话号码
            if (t == 3) {
                var isFlag = isTelAvailable(num);
                mess = "请输入正确的身份证号";
            }
            

            if (!isFlag) {
                that.loadData2.clitenUser[str] = '';
                return $.alert(mess);
            } else {
                if(str=='referenceTel'){
                    var url = $.stringFormat('{0}/frClient/getNameByPhone', 'http://www.4006337366.com:8080/');
                    console.log(num)
                    $.get(url, {"phone": num}, function (res) {

                        that.loadData2.clitenUser.referenceName=res.data;
                        $("#name").val(that.loadData2.clitenUser.referenceName)
                    })
                }else {

                var url = $.stringFormat('{0}/frClient/getByPhone', 'http://www.4006337366.com:8080/');
                console.log(num)
                $.get(url, {"phone": num}, function (res) {
                    if (res.data != false) {

                        that.loadData2.clitenUser = res.data
                        $("#consultant").html(that.loadData2.clitenUser.consultantName)
                        $("#fwhj").html(that.loadData2.clitenUser.fwhjName)
                        if(that.loadData2.clitenUser.shopName!=null ||that.loadData2.clitenUser.shopName!=''){
                            $("#shopName").html(that.loadData2.clitenUser.shopName)
                        }
                        that.loadData2.clitenUser.birthday=that.formatDate(that.loadData2.clitenUser.birthday)
                        that.loadData2.clitenUser.applyTime=that.formatDate(that.loadData2.clitenUser.applyTime)
                        that.loadData2.clitenUser.buildDate=that.formatDate(that.loadData2.clitenUser.buildDate)
                        console.log(that.loadData2.clitenUser.buildDate)
                        that.temp=num;
                    } else if(res.data == false){
                        var timestamp = Date.parse(new Date())
                        console.log(res.data)
                        console.log(timestamp)
                        console.log(that.formatDate(timestamp))
                        that.loadData2.clitenUser.buildDate = that.formatDate(timestamp)
                        console.log(that.loadData2.clitenUser.buildDate)

                    }

                })
                }
            }


        },
        // 查询销售顾问列表
        getSalespersonList: function (type, str) {
            var that = this;
            if (!type) {
                type = 1;
            }
            if (!str) {
                str = 'salespersonList';
            }
            const url = $.stringFormat('{0}/personnelInfo/getServicePersonnel', 'http://www.4006337366.com:8080/');
            var data = {
                customerCode: that.code,
                userType: type,
            }
            $.get(url, data, function (res) {
                if (res.code == '200') {
                    that[str] = res.data;
                } else {
                    $.alert(res.msg);
                }
            });
        },
        //获取销售人员列表
        getMarketShopPeople: function (shop_Id, that, type) {
            //默认获取销售人员列表
            var str = 'maeketUserList';
            if (!type) {
                type = 1;
            }
            if (type == 2) {
                str = 'maeketUserListTwo';
            }
            var url = $.stringFormat('{0}/personnelInfo/getPsersonnelListByShopId', 'http://www.4006337366.com:8080/');
            $.get(url, {
                    shopId: shop_Id,//门店id
                    CustomerCode: that.code,
                    UserType: type,
                },
                function (res) {
                    if (res.code == '200') {
                        that[str] = res.data;
                    } else {
                        $.alert(res.msg);
                    }
                }
            )
        },
        //业绩分配的删除
        deleteFamily: function (t) {
            var that = this;
            if (!t && t != 0) {
                return;
            }
            if (that.loadData1.orderAllotSetListOne == null || that.loadData1.orderAllotSetListOne.length <= 0) {
                return;
            }
            var list = that.loadData1.orderAllotSetListOne;
            that.loadData1.orderAllotSetListOne = [];
            for (var i = 0; i < list.length; i++) {
                if (t != i) {
                    that.loadData1.orderAllotSetListOne.push(list[i]);
                }
            }
        },
        //新建现有会员--- 保存
        saveCustomer: function () {
            var that = this;
            // that.uploadAvatar()
            var type = that.discount.fullType;
            var discountFull = 0;
            var discountReduce = 0
            if (type == 0) {
                discountFull = that.discount.discountFullTwo;
                discountReduce = that.discount.discountReduceTwo;
            }
            if (type == 1) {
                discountFull = that.discount.discountFullOne;
                discountReduce = that.discount.discountReduceOne;
            }
            if (that.randomNumber) {
                return alert("请勿太快重复点击");
            }
            //生成字符串
            that.randomNumber = Math.random().toString(36).substr(2);
            // 支付
            var payModel = that.payModel;
            var client = that.loadData2.clitenUser;
            // 分期
            var orderSplitId = that.loadData1.splitSet.cardTypeSplitSetId;
            //业绩分配
            var orderAllotSetList = that.loadData1.orderAllotSetList;
            //业绩分配
            var orderAllotSet = that.loadData1.orderAllotSetSav;
            //获取上传的头像信息
            var price1 = that.loadDatat0.userURL
            console.log(price1)
            var cardMap = {
                id: that.cardId,           //会员Id
                cardNo: that.cardMap.cardNo,           //会员卡号
                clientId: that.clientId,               //客户ID
                CustomerCode: that.code,               //客户代码
                shopId: that.cardMap.shopId,           //店铺ID
                cardNumId: that.cardMap.cardNumId,    //会员卡号规则ID
                externalNo: that.cardMap.externalNo,  //外部卡号
                bindTime: that.cardMap.bindTime,      //开卡时间
                cardTypeId: that.cardMap.cardTypeId,  //选中的会员卡类型
                note: that.cardMap.note,               //备注
            };
            var cardInfoMap = {
                cardId: that.cardId, //会员卡ID
                cardTypeId: that.cardMap.cardTypeId,//会员卡类型ID
                orderNo: that.cardMap.orderNo,//订单编号
                clientId: that.clientId,//客户ID
                payType: that.loadData1.paremt.payType,//付款方式
                consumeType: that.loadData1.paremt.consumeType,//消费方式
                totalPrice: that.payMentMoney.price,//总价
                discount: that.discount.num,//优惠折扣
                changeNum: that.discount.changeNum,//整单去零
                fullType: that.discount.fullType,//满减优惠类型
                discountFull: discountFull,//满
                discountReduce: discountReduce,//减
                discountPrice: that.payMentMoney.discount,//折扣/优惠
                discountNum: that.payMentMoney.discountNum,//抵扣金额
                needPrice: that.payMentMoney.needPrice,//应付金额
                noPrice: that.payMentMoney.noPrice,//未付金额
                retChange: that.payMentMoney.retChange,//找零
                shopId: '',//基础表门店ID
                personnelId: that.cardMap.personnelId,//人员（员工）ID
                type: 2,//订单类型（1、新购；2、续卡;3、转卡；4、卡升级）
                CustomerCode: that.code,//客户代码
                buyRightsNum: that.cardMap.buyRightsNum,//购买权益
                giveRightsNum: that.cardMap.giveRightsNum,//赠送权益
                allotSetType: that.cardMap.allotSetType,//业绩分配设置（0、无业绩分配；1、有业绩分配）
                depositTime: that.cardMap.depositTime,//补余期限
                invoiceStatus: that.cardMap.invoiceStatus,//是否购置发票（0：否，1：是）
                authorizingUserId: that.loadData1.paremt.authorizingUserId,//授权人ID
                displaceCompany: that.loadData1.paremt.displaceCompany,//置换单位
                displaceWay: that.loadData1.paremt.displaceWay,//置换方式
                giveClientCardId: that.catcherClientCardId,//抵扣人的会员卡ID
            }
            var data = {
                client: JSON.stringify(client),
                payModel: JSON.stringify(payModel),
                cardMap: JSON.stringify(cardMap),
                cardInfoMap: JSON.stringify(cardInfoMap),
                orderSplitId: orderSplitId,
                orderAllotSetList: JSON.stringify(orderAllotSetList),
                orderAllotSet: JSON.stringify(orderAllotSet),
                shopId: $.cookie("shopid"),
                userURL: price1,
                temp:that.loadData2.clitenUser,
            }
            var url = $.stringFormat('{0}/frCard/addSaveCustomer', 'http://www.4006337366.com:8080/');
            console.log(that.loadData2.clitenUser)
           $.ajax({
                type: 'POST',
                url: url,
                data: JSON.stringify(data),
                dataType: "json",
                contentType: "application/json;charset=utf-8",
                success: function (res) {
                    if (res.code == '200') {
                        $.alert("添加成功");
                        console.log(res.data)
                        that.initPayModel();
                    } else {
                        $.alert(res.msg)
                    }
                    that.randomNumber = '';
                    location.href='customerInformation.html?id='+res.data;
                }
            });
        },
        //检索数据
        checkNum: function (num, str, t) {
            var that = this;
            var mess = '';
            var isFlag = false;
            //检索纯数字
            if (t == 3) {
                var isFlag = checkNum(num);
                mess = "只能输入纯数字";
            }
            if (!isFlag) {
                that.loadData1.orderAllotSet[str] = '';
                return $.alert(mess);
            }
        },
//毫秒转为日期
        formatDate: function (time, type, typeT) {
            if (!time) {
                return '';
            }
            var _time = timeFormatDate(time, type, typeT);
            return _time;
        },
    }

});