var customerTicket = new Vue({
	 el:'#customerTicketPoint',
	 data: {
		 id:'',
		 status:'',
		 servicePerson:'',
		 processDate:'',
		 serviceOpinion:'',
		 ticketSelectList:[],
		 obtainWays:[],
		 ticketSelectListDate:[],
		 ticketSetSelectList:[], 
		 customerTicketPointTable: {
	            list: [],
	            currPage: 1,
	            pageSize: 10,
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
 	        	
 	        } 
		  
	 },
	methods : {
		init : function() {
			const that = this;
			 that.queryTicketList();
		},
	    queryTicketList: function (params) {
            console.log("cidqueryTicketList>>>");

            const that = this;
            var cid = $.cookie("cid");
            console.log("cid>>>"+cid);
            if (typeof(params) == 'undefined') {
                params = {"cid": cid};
            }
            //显示加载中
            Loading.prototype.show();
            that.$nextTick(function () {
                const url = $.stringFormat('{0}/ticket/getTicketList', $.cookie('url'));
                $.get(url, params, function (res) {
                    if (res.code === '200') {
                        that.customerTicketPointTable = res.data;
                        console.log("-------"+that.customerTicketPointTable.list);
                    } else {
                        $.alert(jsonData['data']['msg'])
                    }
                    //隐藏加载中
                    Loading.prototype.hide();
                })

            })

        },
        showTicketGift:function (){
        	const that = this;
        	console.log("----showTicketGift-----");
        	$('#ticketGift').show();
        	
        	 var cid = $.cookie("cid");
             console.log("cid>>>"+cid);
             if (typeof(params) == 'undefined') {
                 params = {"cid": cid};
             }
        	   //显示加载中
            Loading.prototype.show();
            that.$nextTick(function () {
                const url = $.stringFormat('{0}/ticket/getTicketSet', $.cookie('url'));
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

            })
        }, //分页初始化 默认取第一一个ticketData   赠送票卷
        ticketSetPageInit:function(){
        	const that = this;
        	  for(var i = 0 ; i<  5 ; i++){
              	if("undefined"==that.ticketSetTable.list[0].TicketList[i]) break;
              	that.ticketTable.list[i]=that.ticketSetTable.list[0].TicketList[i] ;
              }
              console.log("that.ticketTable.list>"+that.ticketTable.list.length);
              console.log("json>"+JSON.stringify(that.ticketTable.list));
              
              that.ticketTable.pageSize =that.ticketTable.list.length;//单页数
              that.ticketTable.totalCount =that.ticketSetTable.list[0].TicketList.length; //总记录数
              that.ticketTable.totalPage = that.ticketSetTable.list[0].TicketList.length/5; //总页数
        	
        },//根据ticketData_id 更新分页数据   赠送票卷
        ticketSetPageNextById:function(ticketData_id){
        	const that = this;
        	 console.log("ticketData_id >"+ticketData_id);
        	 var i=0;
        	 for(i ; i<that.ticketSetTable.list.length ; i++){
        		if(ticketData_id ==  that.ticketSetTable.list[i].id){
        			break;
        		}
        		// console.log("id>>"+  that.ticketSetTable.list[i].id );
        	 }
        	 console.log("ticketSetPageNextById index>>"+  i );
        	 that.ticketSetPageNext(i,1);
        },//赠送票卷 下一页
        ticketSetPageNext:function(index,page){
        	const that = this;
        	  console.log("ticketSetPageNext index>"+index+"  page>>>"+page);
        	  var pagesize = page*5 
        	  var i =  (page-1)*5;
        	  var j = 0 ;
        	  that.ticketTable.list=[];
        	  for(i; i< pagesize ; i++){
                	if(undefined==that.ticketSetTable.list[index].TicketList[i])break;
                	that.ticketTable.list[j]=that.ticketSetTable.list[index].TicketList[i] ;
                	j++;
                }
                console.log("that.ticketTable.list>"+that.ticketTable.list.length);
                console.log("json>"+JSON.stringify(that.ticketTable.list));
                
                that.ticketTable.pageSize =j+1;//单页数
                that.ticketTable.totalCount =that.ticketSetTable.list[index].TicketList.length; //总记录数
                that.ticketTable.totalPage = that.ticketSetTable.list[index].TicketList.length/5; //总页数
                //暂时 采用这个方法：需要修改
                Vue.set(this.ticketTable,page,that.ticketTable);
        },
        ticketTransfer :function(){
    		const that = this;
    		that.ticketSelectListDate=[];
    		$('#performance').show();
    		console.log(that.ticketSelectList);
    		for(var key in that.ticketSelectList){
    			for(var k in that.customerTicketPointTable.list){
    				if(that.ticketSelectList[key] ==that.customerTicketPointTable.list[k].id){
    					that.ticketSelectListDate.push(that.customerTicketPointTable.list[k]); 
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
    					that.ticketSelectListDate.push(that.customerTicketPointTable.list[k]); 
    					params = {"id":that.customerTicketPointTable.list[k].id,
    							"customerCode":"003",
    							"ticketNum":that.customerTicketPointTable.list[k].startno,
    							"ticketName":that.customerTicketPointTable.list[k].ticketname}
    				}
    			}
    		}
    		console.log("params>>"+params);
              var url = $.stringFormat('{0}/ticket/postDelTicket', $.cookie('url'));
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
    	},ticketSet:function(){
    	//	const that = this;
    		console.log("------------ticketSet------------");
    		console.log(this.ticketSetSelectList);
    		var trList = $("#titcketSetTable").children("tr");
////    		console.log("html>>>>>"+trList.eq(2).html());
////    		console.log("html>>>>>"+trList.eq(2));
    		for(var key in this.ticketSetSelectList){
    			console.log("input>>"+key+"="+this.ticketSetSelectList[key]); 
//    			var tdArr = trList.eq(this.ticketSetSelectList[key]).find("td");
////    			console.log("select>>"+tdArr.eq(0).find('select').val()); 
////    			console.log("select>>"+tdArr.eq(12).find('input').val()); 
//    			console.log("input>>"+tdArr.eq(12).html()); 
//    	        console.log("input>>"+tdArr.eq(12).find('input').val());
//    	        console.log("select>>"+tdArr.eq(3).html()); 
//    	        console.log("select>>"+tdArr.eq(3).find('select').val());
//    			
    			var  i = this.ticketSetSelectList[key]*1;
    			console.log("i"+i);
    	        var tdArr = trList.eq(i).find("td");
    	        console.log("input>>"+tdArr.eq(12).html()); 
    	        console.log("input>>"+tdArr.eq(12).find('input').val());
    	        console.log("select>>"+tdArr.eq(3).html()); 
    	        console.log("select>>"+tdArr.eq(3).find('select').val());
    			
    		}
    		
    		var trList = $("#titcketSetTable tbody").children("tr");
    		 console.log("length>>>>>"+trList.length); 
    	  
    		 for (var i=0;i<trList.length;i++) {
    	    	console.log(i);
    	        var tdArr = trList.eq(i).find("td");
    	        console.log("input>>"+tdArr.eq(12).html()); 
    	        console.log("input>>"+tdArr.eq(12).find('input').val());
    	        console.log("select>>"+tdArr.eq(3).html()); 
    	        console.log("select>>"+tdArr.eq(3).find('select').val());

    	    }
    		
    		
    	}
	}
	

});