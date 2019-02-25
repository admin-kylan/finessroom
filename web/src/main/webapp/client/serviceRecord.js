var serviceRecord = new Vue({
	 el:'#serviceRecord',
	 data: {
		 id:'',
		 status:'',
		 servicePerson:'',
		 processDate:'',
		 serviceOpinion:'',
		 departmentList:[],
		 personnelInfoList:[],
		 selectPersonnel:'',
		 serviceRecordTable: {
	            list: [],
	            currPage: 1,
	            pageSize: 10,
	            totalContentInfo: 0,
	            totalPage: 0,
	        },
		 serviceRecord:{
			 id:'',
			 type:'',
			 context:'',
			 replyTime:'',
			 processMethod:'',
			 opinion:'',
			 servicePerson1:'',
			 processDate1:'',
			 serviceOpinion1:'',
			 servicePerson2:'',
			 processDate2:'',
			 serviceOpinion2:'',
			 authorizer:'',
			 authorizeDate:'',
			 customerId:'',
			 status:'',
			 
		 },
		 serviceProcess1:{
			 servicePerson1:'',
			 processDate1:'',
			 serviceOpinion1:'',
			 status:'',
			 id:'',
		 },
		 serviceProcess2:{
			 servicePerson2:'',
			 processDate2:'',
			 serviceOpinion2:'',
			 status:'',
			 id:'',
		 },
		 authorProcess:{
			 authorizer:'',
			 authorizeDate:'',
			 authorizeResult:'',
			 status:'',
			 id:'',
		 },
		 typeSelect:100 ,
		 typeArr:[
			 {
			 	value:100,
			 	name:'全部'
			 },
			 
			 {
			 	value:0,
			 	name:'未处理'
			 },
			 {
				 	value:1,
				 	name:'服务1'
			},
			 {
			 	value:2,
			 	name:'服务2'
			 },
			 
			 {
			 	value:3,
			 	name:'授权'
			 },
			 
			 {
			 	value:4,
			 	name:'已完成'
			 }
		 ]
		  
	 },
	mounted:function(){
		this.init();
		jeDate('#fwTime',{
	        format: "YYYY-MM-DD hh:mm:ss",
	        isTime: false,
	        minDate: "2014-09-19 00:00:00",
	        donefun: function (obj) {
	
	        }
	
	    });
		jeDate('#dateInp',{
	        format: "YYYY-MM-DD hh:mm:ss",
	        isTime: false,
	        minDate: "2014-09-19 00:00:00",
	        donefun: function (obj) {
	
	        }
	
	    });

		
	},
	methods : {
		changetype:function(item){
			this.typeSelect=item.value;
			 console.log(">>>>"+this.typeSelect);
			 this.queryServiceRecordList();
		},
		init : function() {
			const that = this;
			 that.queryServiceRecordList();
			 that.loadDepartment();
		},
		  //分页初始化
        getPageInfo: function (res,that,page) {
            that.count = res.data.totalCount;
            new myPagination({
                id: 'pagination',
                curPage:res.data.currPage, //初始页码
                pageTotal: res.data.totalPage, //总页数
                pageAmount: res.data.pageSize,  //每页多少条
                dataTotal: res.data.totalCount, //总共多少条数据
                showPageTotalFlag:true, //是否显示数据统计
                showSkipInputFlag:true, //是否支持跳转
                getPage: function (page) {
                    //获取当前页数
                    that.getFollowUpRecordList({
                        page: page,
                        rows: res.data.pageSize,
                        id: that.clientId
                    });
                }
            })
        },
		addServiceRecord : function() {
			   var that = this;
			   that.serviceRecord.customerId=$.cookie("cid");
			   that.serviceRecord.replyTime=$("#dateInp").val();
			   if(that.serviceRecord.type==''){
				   alert("请选择服务类型！");
				   return;
			   }
			   if(that.serviceRecord.context==''){
				   alert("请填写服务内容！");
				   return;
			   }
			   if(that.serviceRecord.replyTime==''){
				   alert("请选择答复时间！");
				   return;
			   }
			   if(that.serviceRecord.processMethod==''){
				   alert("请选择处理方式！");
				   return;
			   }
			   if(that.serviceRecord.opinion==''){
				   alert("请填写处理意见！");
				   return;
			   }
			   
			   var url = $.stringFormat('{0}/serviceRecord/addServiceRecord', $.cookie('url'));
	            axios.post(url, that.serviceRecord).then(function (res) {
	                let resData = eval(res);
	                if (resData['data']['code'] === '200') {
	                    alert("添加成功")
	                    location.reload();
	                } else {
	                    $.alert(resData['data']['msg']);
	                }
	            }).catch(function (error) {
	                    $.alert(error);
	                });
			
			

		},
	    queryServiceRecordList: function (params) {
            const that = this;
            var cid = $.cookie("cid");
            console.log("cid>>>"+cid);
            console.log(">>>>"+this.typeSelect);
            if (typeof(params) == 'undefined') {
                params = {"cid": cid,'status':this.typeSelect};
            }
           // params.push({'status':this.typeSelect});
            console.log("params>>>>"+params);
            //显示加载中
            Loading.prototype.show();
            that.$nextTick(function () {
                const url = $.stringFormat('{0}/serviceRecord/queryServiceRecordList', $.cookie('url'));
                $.get(url, params, function (res) {
                    if (res.code === '200') {
                        that.serviceRecordTable = res.data;

                        new myPagination({
                            id: 'pagination',
                            curPage: that.serviceRecordTable.currPage, //初始页码
                            pageTotal: that.serviceRecordTable.totalPage, //总页数
                            pageAmount: that.serviceRecordTable.pageSize,  //每页多少条
                            dataTotal: that.serviceRecordTable.totalCount, //总共多少条数据
                            showPageTotalFlag: true, //是否显示数据统计
                            showSkipInputFlag: true, //是否支持跳转
                            getPage: function (page) {
                                //获取当前页数
                                console.log(page)
                                var currPage = page + "";
                                that.queryServiceRecordList({"cid": cid, "currPage": currPage});
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
        //服务处理
        serviceprocess :function(status,id){
        	 $('.selectCZR').val("");
        	if(status>1){
        		console.log("status："+status+" return");
        		return;
        	}
        	
        	const that = this;
        	// that.loadDepartment();
        	that.id = id ;
        	that.status=status;
        	 $('#submitModal').modal('show');
        },
        //服务处理提交 1、2 
        addServiceprocess:function(){
            const that = this;
            that.processDate = $("#fwTime").val();
            if(that.servicePerson==''){
				   alert("请选择服务人员！");
				   return;
			   }
            if(that.processDate==''){
				   alert("请选择处理时间！");
				   return;
			   }
            if(that.serviceOpinion==''){
				   alert("请填写意见/内容！");
				   return;
			   }
            
            var serviceProcessRecord={};
            if(that.status=='0'){
            	//服务处理1
            	that.serviceProcess1.servicePerson1=that.servicePerson;
            	that.serviceProcess1.processDate1=that.processDate;
            	that.serviceProcess1.serviceOpinion1=that.serviceOpinion;
            	that.serviceProcess1.status=1;
            	that.serviceProcess1.id=that.id;
            	serviceProcessRecord = that.serviceProcess1;
            }else if (that.status=='1'){
            	//服务处理2
            	that.serviceProcess2.servicePerson2=that.servicePerson;
            	that.serviceProcess2.processDate2=that.processDate;
            	that.serviceProcess2.serviceOpinion2=that.serviceOpinion;
            	that.serviceProcess2.status=2;
            	that.serviceProcess2.id=that.id;
            	serviceProcessRecord = that.serviceProcess2;
            	
            }

         
            var url = $.stringFormat('{0}/serviceRecord/serviceProcess', $.cookie('url'));
            axios.post(url, serviceProcessRecord).then(function (res) {
                let resData = eval(res);
                if (resData['data']['code'] === '200') {
                    alert("服务处理成功！");
                    location.reload();
                } else {
                    $.alert(resData['data']['msg']);
                }
            }).catch(function (error) {
                    $.alert(error);
                });
          
            
        }, //授权
        authprocess :function(status,id){
        	const that = this;
        	
        	if(status=='0' ||status=='4' ||status=='3'  ){
        		console.log("status："+status+" return");
        		return;
        	}
       	 $('.selectCZR').val("");
        	that.id = id ;
        	that.status=status;
        	$('#submitAutoModal').modal('show');
        },
        addAuthProcess:function (){
        	const that = this;
        	
        	if(that.authorProcess.authorizer==''){
				   alert("请选择授权人员！");
				   return;
			   }
	         if(that.authorProcess.authorizeResult==''){
					   alert("请填写授权结果！");
				   return;
			   }
        	
        	that.authorProcess.id = that.id;
        	that.authorProcess.status = 3;
        	
            var url = $.stringFormat('{0}/serviceRecord/serviceProcess', $.cookie('url'));
        	   axios.post(url, that.authorProcess).then(function (res) {
                   let resData = eval(res);
                   if (resData['data']['code'] === '200') {
                       alert("授权处理成功!");
                       location.reload();
                   } else {
                       $.alert(resData['data']['msg']);
                   }
               }).catch(function (error) {
                       $.alert(error);
                   });
        	
        	
        },
        successprocess :function(status,id){
        	if(status=='0' ||status=='4'  ){
        		console.log("status："+status+" return");
        		return;
        	}
        	console.log("successprocess status:>>"+status);
        	console.log("successprocess id:>>"+id);
        	var a=confirm("是否确认完成！");  
            if(a){  
                    //alert("继续下一题");  
             	var successProcessRecord={"status":4,"id":id};
             	 var url = $.stringFormat('{0}/serviceRecord/serviceProcess', $.cookie('url'));
          	   axios.post(url, successProcessRecord).then(function (res) {
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
                 }
             
 
        	

        },
        convertType:function(type){
               switch(type) {
                  case '0':
                	  return "投诉";
                  case '1':
                	  return "建议";
                  case '2':
                	  return "合作";
                  case '3':
                	  return "采购";
                  case '4':
                	  return "租赁";
                  case '5':
                	  return "生日";
                  case '6':
                	  return "违规";
                  case '7':
                	  return "回归";
              }
        },
        convertProcessMethod:function(processMethod){
            switch(processMethod) {
               case '0':
             	  return "电话";
               case '1':
             	  return "邮件";
               case '2':
             	  return "书面";
               case '3':
             	  return "赔偿";
               case '4':
             	  return "延期";
               case '5':
             	  return "补偿";
             
           }
     },convertStatus:function(status){
    	 //0 未处理、1 服务人员1已处理、2服务人员2已处理 、3 授权处理、4授权已处理、5 已完成
    	    switch(status) {
            case '0':
          	  return "未处理";
            case '1':
          	  return "服务人员1已处理";
            case '2':
          	  return "服务人员2已处理";
            case '3':
          	  return "授权处理";
            case '4':
          	  return "已完成";
          
        }
     } , //获取部门信息
     loadDepartment:function(){
    	 const that = this;
       	 var url = $.stringFormat('{0}/department/queryDepartmentList', $.cookie('url'));
    	   axios.get(url, null).then(function (res) {
               let resData = eval(res);
               if (resData['data']['code'] === '200') {
            	   that.departmentList = resData['data']['data'];
            	   console.log("json>"+JSON.stringify( that.departmentList ));
            	   
                   Vue.set(this.departmentList,0,that.departmentList);

              }
           }).catch(function (error) {
                 //  $.alert(error);
               });
     },
     loadPersonnelInfo:function(deptName){
    	 var id ;
    	 console.log("deptName>>>"+deptName);
    	 for(var key in this.departmentList){
    		 if(this.departmentList[key].departName==deptName){
    			 id = this.departmentList[key].id  ;
    			 break;
    		 }
    			 
    	 }
    	 console.log("id>>>"+id);
     	 const that = this;
       	 var url = $.stringFormat('{0}/personnelInfo/getMarketUserList?shopId='+id, $.cookie('url'));
    	   axios.get(url, null).then(function (res) {
               let resData = eval(res);
               //if (resData['data']['code'] === '200') {
            	   that.personnelInfoList = resData['data']['data'];
            	   console.log("personnelInfoList>>>"+JSON.stringify( that.personnelInfoList ));
            	   
                   Vue.set(this.personnelInfoList,0,that.personnelInfoList);

             // }
           }).catch(function (error) {
                   $.alert(error);
               });
     },
     departmentSelect:function (id){
 		console.log("departmentSelect>>>"+id);
 		 const that = this;
       	 var url = $.stringFormat('{0}/personnelInfo/getMarketUserList?shopId='+id, $.cookie('url'));
    	   axios.get(url, null).then(function (res) {
               let resData = eval(res);
            	   that.personnelInfoList = resData['data']['data']['data'] ;
                   Vue.set(this.personnelInfoList,0,that.personnelInfoList);

           }).catch(function (error) {
                   //$.alert(error);
               }); 
 	},//毫秒转为日期
     formatDate: function (time, type, typeT) {
            if (!time) {
                return '';
            }
            var _time = timeFormatDate(time, type, typeT);
            return _time;
        }
	},
	
	

});