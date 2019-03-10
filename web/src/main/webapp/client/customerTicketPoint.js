var customerTicket = new Vue({
	 el:'#customerTicketPoint',
	 data: {
		 id:'',
		 customerCode:'003',
		 tel:'',
		 clientId: '2869fa11516833fc',           //会员ID
		 people:'柯宁翔',
		 tel:'13645945925',
		 cardId:'',
		 clientAuth:'',
		 status:'',
		 clientName:'',
		 mobile:'',
		 servicePerson:'',
		 processDate:'',
		 serviceOpinion:'',
		 ticketDataId:'0',
		 ticketSelectList:[],
		 ticketSetCard:[],   //赠送选择会员卡号
		 obtainWays:[],
		 ticketSelectListData:[],
		 ticketSetSelectList:[], 
		 ticketSetSelectIds:[],
		 ticketSetSelectObtains:[],
		 ticketSetSelectAmounts:[],
		 ticketEquityDetail:[],
		 ticketSetList:[], // 存储赠送票卷
		 obtains:["购卡赠送","友情赠送","推荐赠送","消费推荐"],
		 numAll:'',
		 customerTicketPointTable: {
	            list: [],
	            currPage: 1,
	            pageSize: 10,
	            totalContentInfo: 0,
	            totalPage: 0,
	        },
	        customerTicketTable: {
	            list: [],
	            currPage: 1,
	            pageSize: 10,
	            totalContentInfo: 0,
	            totalPage: 0,
	        },cardUserTable: {
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
	        ticketSetTable: {
	            list: [],
	            currPage: 1,
	            pageSize: 10,
	            totalContentInfo: 0,
	            totalPage: 0,
	        },
	        ticketTable:{
	            list: [],
	            currPage: 1,
	            pageSize: 5,
	            totalContentInfo: 0,
	            totalPage: 0,
	            totalCount:0,
 	        },ticket:{
 	        	ticketname:'',
 	        	startno:'',
 	        	startdate:'',
 	        	enddate:'',
 	        	
 	        },
 	       parameterList: {
 	    	  status: [               //卡状态
 	                {"vue": 0, "lav": "正常"},
 	                {"vue": 1, "lav": "停卡"},
 	                {"vue": 2, "lav": "冻结"},
 	                {"vue": 3, "lav": "已过期"},
 	                {"vue": 4, "lav": "未开卡"},
 	                {"vue": 5, "lav": "待补余"},
 	                {"vue": 6, "lav": "历史"}
 	            ]
 	       },
 	       
		  
	 },
	methods : {
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
		init : function() {
			const that = this;
			 that.queryTicketList();
			 console.log("code------>"+ $.cookie("code"));
			 console.log("shopid------>"+ $.cookie("shopid"));
			 console.log("cid------>"+ $.cookie("cid"));
			 this.getUserInfo();
			 //console.log("clientPhone>>"+$('#clientPhone', window.parent.document).val());
			 
		},
	    queryTicketList: function (params) {

            const that = this;
                params = {"tel": this.tel,'code':$.cookie("code")};
                console.log( "params---- >  "+JSON.stringify(params) );
            //显示加载中
            Loading.prototype.show();
            that.$nextTick(function () {
                const url = $.stringFormat('{0}/ticket/getTicketList', 'http://www.4006337366.com:8080/');
                $.get(url, params, function (res) {
                    if (res.code === '200') {
                        that.customerTicketPointTable = res.data;
                        that.ticketPageInit();
                       // console.log("customerTicketTable length------->"+that.customerTicketTable.list.length);
                      //  console.log( "customerTicketTable ---- >  "+JSON.stringify(that.customerTicketTable) );
                        
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
        	console.log("-----------ticketPageInit------------");
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
        
        //加载赠送票卷数据
        showTicketGift:function (){
        	const that = this;
        	console.log("----showTicketGift-----");
        	$('#ticketGift').show();
        	
//                 params = {'shopid': $.cookie('shopid'), 'customerCode':$.cookie("code")};
                 params = {'shopid': '0', 'customerCode':$.cookie("code")};
                 console.log( "params---- >  "+JSON.stringify(params) );
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
            
            
            that.getCardUserList();
            
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
        	
        },//根据ticketData_id 更新分页数据   赠送票卷
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
        },//赠送票卷 下一页
        ticketSetPageNext:function(index,page){
        	this.refreshTicketSetList();// 刷新存储的赠送数据
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
        //进入转让页面
        ticketTransfer :function(){
    		const that = this;
    		
    		if(this.ticketSelectList.length ==0){
    			 alert("请选择要转让的票卷！");
    			 return;
    			 
    		}
    		
    		that.ticketSelectListData=[];
    		$('#performance').show();

    		for(var key in that.ticketSelectList){
    			for(var k in that.customerTicketPointTable.list){
    				if(that.ticketSelectList[key] ==that.customerTicketPointTable.list[k].id){
    					that.ticketSelectListData.push(that.customerTicketPointTable.list[k]); 
    				}
    			}
    		}

    	},
    	//删除票卷
    	delTicket:function (){
    		const that = this;
    		var params;
    		console.log(that.ticketSelectList);
    		for(var key in that.ticketSelectList){
    			for(var k in that.customerTicketPointTable.list){
    				if(that.ticketSelectList[key] ==that.customerTicketPointTable.list[k].id){
    					that.ticketSelectListData.push(that.customerTicketPointTable.list[k]); 
    					params = {"id":that.customerTicketPointTable.list[k].id,
    							"customerCode":"003",
    							"ticketNum":that.customerTicketPointTable.list[k].startno,
    							"ticketName":that.customerTicketPointTable.list[k].ticketname}
    				}
    			}
    		}
    		console.log("params>>"+params);
              var url = $.stringFormat('{0}/ticket/postDelTicket', 'http://www.4006337366.com:8080/');
         	   axios.post(url, params).then(function (res) {
                    let resData = eval(res);
                    if (resData['data']['code'] === '200') {
                        alert("更新成功！")
                        location.reload();
                    } else {
                        $.alert(resData['data']['msg']);
                    }
                }).catch(function (error) {
                        $.alert(error);
                    });
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
    		/*
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
	      	   */
      	   
    		
    	},//   刷新表格数据显示
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
    		
    	}
    	,
    	refreshTicketSetList:function(){
    		console.log("-------------------------------refreshTicketSetList"); 

    		//获取当前页赠送数据
    		this.storageTicketSetList();
    		//清除已被取消选择的数据
    		this.removeNoInTicketSetList();
    		
    		console.log("-------------------------------refreshTicketSetList"); 

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
    	//赠送票卷 指定会员卡 只能选一个
    	ticketSetCardSelect : function(craditem){
    		//console.log("ticketSetCard>>>"+JSON.stringify(craditem));
    		this.ticketSetCard=[];
    		this.ticketSetCard.push(craditem);
    		console.log("ticketSetCard>>>"+JSON.stringify(this.ticketSetCard));
    	},

    	   //获取会员卡列表
        getCardUserList: function (params) {
            var that = this;
            //显示加载中
            Loading.prototype.show();

        
            if (typeof(params) == 'undefined') {
                params = {"clientId": that.clientId,'code':that.customerCode,"rows":10};
            }
                 that.$nextTick(function () {
                const url = $.stringFormat('{0}/frCard/queryUserCardList', 'http://www.4006337366.com:8080/');
                $.get(url, params, function (res) {
                    if (res.code === '200') {
                         that.cardUserTable = res.data.list;
                        new myPagination({
                            id: 'pagination1',
                            curPage: that.cardUserTable.currPage, //初始页码
                            pageTotal: that.cardUserTable.totalPage, //总页数
                            pageAmount: that.cardUserTable.pageSize,  //每页多少条
                            dataTotal: that.cardUserTable.totalCount, //总共多少条数据
                            showPageTotalFlag: true, //是否显示数据统计
                            showSkipInputFlag: true, //是否支持跳转
                            getPage: function (page) {
                                //获取当前页数
                                console.log(page)
                                var currPage = page + "";
                                params = {"currPage": currPage,"clientId": that.clientId,'code':that.code,"rows":10};
                                console.log("params--------"+ JSON.stringify(params));
                                console.log(page);
                                that.getCardUserList(params);
                            }
                        })
                    } else {
                        //$.alert(jsonData['data']['msg'])
                    }
                    //隐藏加载中
                    Loading.prototype.hide();
                })
                Vue.set(this.ticketTable,1,that.ticketTable);

            })
            
            
        },
        //转让卡
    	openCardUser:function(){
    	
    		
    		$('#xzhyModal').modal('show');
    		//console.log("clientName>>"+this.clientName);
    		//console.log("mobile>>>>"+this.mobile);
    		this.getZRCardUserList();
        },//加载转让会员卡列表
        getZRCardUserList :function(params){
        	 var that = this;
             //显示加载中
             Loading.prototype.show();

         
             if (typeof(params) == 'undefined') {
                 params = {"mobile": that.mobile,"clientName": that.clientName,'code':that.code,"rows":5};
             }
                  that.$nextTick(function () {
                 const url = $.stringFormat('{0}/frCard/queryUserCardList', 'http://www.4006337366.com:8080/');
                 $.get(url, params, function (res) {
                     if (res.code === '200') {
                          that.zrCardUserTable = res.data.list;
                         new myPagination({
                             id: 'pagination2',
                             curPage: that.zrCardUserTable.currPage, //初始页码
                             pageTotal: that.zrCardUserTable.totalPage, //总页数
                             pageAmount: that.zrCardUserTable.pageSize,  //每页多少条
                             dataTotal: that.zrCardUserTable.totalCount, //总共多少条数据
                             showPageTotalFlag: true, //是否显示数据统计
                             showSkipInputFlag: true, //是否支持跳转
                             getPage: function (page) {
                                 //获取当前页数
                                 console.log(page)
                                 var currPage = page + "";
                                 that.getZRCardUserList({"mobile": that.mobile,"clientName": that.clientName,'code':that.code,"rows":5});
                             }
                         })
                     } else {
                         //$.alert(jsonData['data']['msg'])
                     }
                     //隐藏加载中
                     Loading.prototype.hide();
                 })

             })
        	},
        	//转让 会员卡选择
        	cardSelect:function(cardItem){
        		this.cardId=cardItem.id;
        		this.clientName=cardItem.clientName;
        		$('#xzhyModal').modal('hide');
        		
        	},
        	// 转让提交
        	zrSubmit:function(){
        		var data = new Array();
        		//console.log("ticketSelectListData--------"+JSON.stringify( this.ticketSelectListData));
        		for(var key in this.ticketSelectListData){
        			var ticket = this.ticketSelectListData[key];
        			console.log("ticket--->>>"+JSON.stringify( ticket) );
        			console.log("ticketsetid--------"+ticket["id"]);

        			 data[key] = {'ID':ticket["id"],'TicketName':ticket["ticketname"]};
        			
        		}
        		
        		var params={"memberCardId":this.cardId,
						"people":this.clientName,
						"tel":this.mobile,
						"data":  data,
						"clientId":this.clientId
						};
			console.log("params--->>>"+JSON.stringify( params) );
        		
        		 var url = $.stringFormat('{0}/ticket/postTransfer', 'http://www.4006337366.com:8080/');
	          	   axios.post(url, params).then(function (res) {0
	                     let resData = eval(res);
	                     if (resData['data']['code'] === '200') {
	                         alert("转让成功！")
	                         location.reload();
	                     } else {
	                         alert(resData['data']['msg']);
	                     }
	                 	}).catch(function (error) {
	                         alert(error);
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
			 /**
        	 *  睡眠函数
        	 *  @param numberMillis -- 要睡眠的毫秒数
        	 */
        	sleep :function (numberMillis) {
        	    var now = new Date();
        	    var exitTime = now.getTime() + numberMillis;
        	    while (true) {
        	        now = new Date();
        	        if (now.getTime() > exitTime)
        	            return;
        	    }
        	},getUserInfo:function(){
        		 var that = this; 
        		const url = $.stringFormat('{0}/frClient/getClient', 'http://www.4006337366.com:8080/');
                 $.get(url, {"id":$.cookie("cid")}, function (res) {
                	  let resData = eval(res);
                	//	console.log("getUserInfo res--->>>"+JSON.stringify( res) );
                	 // console.log("mobile --->>>"+ resData['data']['mobile']);
                	  this.tel = resData['data']['mobile'];
                 })
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
        	}
    	
	}
	

});