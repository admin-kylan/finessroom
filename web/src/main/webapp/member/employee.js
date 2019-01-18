var right = new Vue({
    el: '#right',
    data: {
        special: false,
        SetStoreItems: [], //门店
        Storelist: [], //门店信息
        pzfsArr: [],
        // zkXffsArr: [],

        cardTypeSet: {
            pzfs: '',
            pid: '',
            cardTypeName: '',
            serviceLife: '',
            swipingInterval: '',
            swipingTime: '',
            usedNum: 0,
            originalPrice: '',
            salesPrice: '',
            totalNum: '',
            stopNum: '',
            stopDays: '',
            totalDays: '',
            outPrice: '',
            qtXxjg_update: 0,
            qtXxqy_update: 0,
            qtZsqy_update: 0,
            qtLrzp_update: 1,
            qtZdxxjg: '',
            qtZdqy: '',
            canTransfer: 0,
            canChangeType: 0,
            transferFee: 0,
            renewCommonPrice: '',
            renewLevel1Price: '',
            renewLevel2Price: '',
            renewLevel3Price: '',
            renewLevel4Price: '',
            qtCanRenew: 1,
            zkNum: '',
            zkCyfs: 0,
            zkTkqy: 1,
            zkXffs: '',
            zkClfs: 0,
            zkSjjg: '',
            zkJcszsm: '',
            totalTime: '',
            cardAmount: '',
            flag: '',
            type: '',
            CustomerCode: '',
            isUsing: 1,
            setting: false,
            changeTransferFee: 0,

        },
//      分期付款
        fQList:[],
        splitSetDd:[],//分期详情 Map
        oldsplitSetDd:[],
        splitNum:2,//分期数
        firstPrice:500,//首付金额
        splitType:0,//分期类型（1、比例；2、金额）
        splitContent:'',//分期付款内容
        splitPrice:0,//分期总额
//      单个修改
        oneSplitSetDd:[],//分期详情 Map
		onesplitSetDd:[],
        oldonesplitSetDd:[],
        onesplitNum:2,//分期数onesplitNum
        onefirstPrice:500,//首付金额
        onesplitType:0,//分期类型（1、比例；2、金额）
        onesplitContent:'',//分期付款内容
        onesplitPrice:0,//分期总额
        enOrder:['一','二','三','四','五','六'],
        fqxqData:[],//分期详情
        oneSplit:{},
        lastpt:0,//最后平摊的钱或者比例
        //会员卡类型-门店-场馆-项目关系表（消费门店会员卡权益设置）
        shopCardConsume: {
            list: [],

        },

        //		shop:{
        //			shopName:"",
        //			CustomerCode:"";
        //		},
        checkboxs: [], //门店全选数组
        sdaduimList: [], //门店使用的场馆
        shopList: [], //门店 场馆这边用
        sdaduimArrList: [], //场馆全选数组
        sdaduimItems: [], //门店场馆 项目设置需用
        categoryItemlist: [], //项目
        //shopSdaduimIId: [], //提交到后台的 门店和场馆id
        sdaduimLists: [],
        cardConsumeSetDTO: [], //设置  fr_shop_ctype_consume_p....
        // 测试获取项目  时间设置
        setTimeList: {
            list: [],
        },
        //
        Pladdsets: {
            useLimit: 0,
            useDayStar: null,
            useDayEnd: null,
            useSelect: false,
            useTimeStar: null,
            useTimeEnd: null,
            consumeId: null,
        },
        //项目id
        Consumeids: null,
        cardExplainInfo: null,
        tempInfo: [],
    },
    mounted: function () {
//		this.init();
        this.getMemberCard();
        this.cardExplain();
        $('.special').hide();
    },
    watch:{
    	splitNum:function(val){
    		if(val>6){
    			val=6
    			this.splitNum=val
    			$.alert("最多只能分6期");
    		}
    		this.initSplitSetDd();
    	},
        onesplitNum:function(val){
            if(val>6){
                val=6
                this.onesplitNum=val
                $.alert("最多只能分6期");
            }
            this.initOneSplitSetDd();
        },
    	splitType:function(){
    		this.initSplitSetDd();
    	},
    	onesplitType:function(){
    		this.initOneSplitSetDd();
    	},
    	oneSplitSetDd:{
    		handler(newName, oldName){
    			var _len=this.oneSplit.splitNum;
    			var oldsplitSetDd=this.oldonesplitSetDd;
    			if(oldsplitSetDd.length!=_len){
    				this.oldsplitSetDd=JSON.parse(JSON.stringify(newName));
    				return ;
    			}
    			var _type=0;//检测变化项 2 钱变了  1时间变了
    			var _index=0;
    			for(var i=0;i<_len;i++){
    				var item = newName[i]
    				var item1 = oldsplitSetDd[i]
//  				if(item.splitDate!=item1.splitDate){
//  					_index=i;
//  					_type=1;
//  					break;
//  				}
    				if(item.splitNum!=item1.splitNum){
    					_index=i;
    					_type=2;
    					break;
    				}
    			}
    			if(_type) this.changeOneSplitSetDd(_index);
    			else this.oldonesplitSetDd=JSON.parse(JSON.stringify(newName));
		      //要干的事情
		    },
		    deep: true
    	},
    	splitSetDd:{
    		handler(newName, oldName){
    			var _len=this.splitNum;
    			var oldsplitSetDd=this.oldsplitSetDd;
    			if(oldsplitSetDd.length!=_len){
    				this.oldsplitSetDd=JSON.parse(JSON.stringify(newName));
    				return ;
    			}
    			var _type=0;//检测变化项 2 钱变了  1时间变了
    			var _index=0;
    			for(var i=0;i<_len;i++){
    				var item = newName[i]
    				var item1 = oldsplitSetDd[i]
//  				if(item.splitDate!=item1.splitDate){
//  					_index=i;
//  					_type=1;
//  					break;
//  				}
    				if(item.splitNum!=item1.splitNum){
    					_index=i;
    					_type=2;
    					break;
    				}
    			}
    			if(_type) this.changeSplitSetDd(_index);
    			else this.oldsplitSetDd=JSON.parse(JSON.stringify(newName));
		      //要干的事情
		    },
		    deep: true
    	},
    	onefirstPrice:function(){
    		this.initOneSplitSetDd();
    	},
    	firstPrice:function(){
    		this.initSplitSetDd();
    	}
    },
    methods: {
        //设置小弹窗
        setting_a:function(id) {
    console.log(id);
    $('.maskLayerAlert_box_alert').show();
    var data={
        splitSetId: id
    }
    var url = $.stringFormat("{0}/frCardTypeSplitSetDd/getBySplitSetId", $.cookie('url'));
    $.ajax({
        type: "get",
        url: url,
        data: data,
        success: function (res) {
            if(res.code==200){
                right.fqxqData=res.data;
            }
        }
    });
//		fqxqData
},
    	initOneSplitSetDd:function(){
    		var val=this.onesplitNum
    		this.oneSplitSetDd.length=0;
			var salesPrice=this.oneSplit.totalPrice;
			var splitPrice=salesPrice - this.onefirstPrice;
			this.onesplitPrice=splitPrice;
			if(this.onesplitType==1){
				splitPrice=100;
			}
			var splitNum=(splitPrice/val).toFixed(2);
			var lastSplitNum=(splitPrice-splitNum*(val-1)).toFixed(2);//最后一个钱可能分不清楚
			for(var i=1;i<=val;i++){
				if(i==val){
					splitNum = lastSplitNum
				}
				this.oneSplitSetDd.push({
					splitNum:splitNum,//该分期需付（金额/比例）
					splitDate:30,//分期天数
					splitOrder:i,//第几期
				})
			}
    	},
    	initSplitSetDd:function(){
    		var val=this.splitNum
    		this.splitSetDd.length=0;
			var salesPrice=this.cardTypeSet.salesPrice||2000;
			var splitPrice=salesPrice - this.firstPrice;
			this.splitPrice=splitPrice;
			if(this.splitType==1){
				splitPrice=100;
			}
			var splitNum=(splitPrice/val).toFixed(2);
			var lastSplitNum=(splitPrice-splitNum*(val-1)).toFixed(2);//最后一个钱可能分不清楚
			for(var i=1;i<=val;i++){
				if(i==val){
					splitNum = lastSplitNum
				}
				this.splitSetDd.push({
					splitNum:splitNum,//该分期需付（金额/比例）
					splitDate:30,//分期天数
					splitOrder:i,//第几期
				})
			}
    	},
		getFqList:function(){
    		var vm=this;
            var data={
                cardTypeId:vm.cardTypeSet.id,
                code:vm.cardTypeSet.customerCode
            }
            var url = $.stringFormat("{0}/frCardTypeSplitSet/list", $.cookie('url'));
            $.ajax({
                type: "get",
                url: url,
                data: data,
                success: function (res) {
                    if(res.code==200){
                        vm.fQList=res.data
                    }
                }
            });
		},
    	modify:function(id){
    		var vm=this;
    		$('.maskLayerAlert_box_alert_big').show();
    		var data={
				id:id || 1
			}
			var url = $.stringFormat("{0}/frCardTypeSplitSet/get", $.cookie('url'));
	        $.ajax({
	            type: "get",
	            url: url,
	            data: data,
	            success: function (res) {
	            	if(res.code==200){        		
	            		vm.oneSplit=res.data[0];
	            		vm.onesplitNum=res.data[0].splitNum
				        vm.onefirstPrice=res.data[0].firstPrice
				        vm.onesplitType=res.data[0].splitType
				        vm.onesplitContent=res.data[0].splitContent
				        vm.onesplitPrice=res.data[0].splitPrice
	            		vm.oneSplitSetDd=res.data[1].splitSetDd;
	            		console.log(vm.oneSplitSetDd)
	            	}
	            }
	        });
    	},
    	deleteSplit:function(id,idx){
    		var vm=this;
    		var r=confirm("你确定要删除这条分期记录？")
		  	if (r==true)
		    {
		    	var data={
		    		id:id
		    	}
		    	var url = $.stringFormat("{0}/frCardTypeSplitSet/delete", $.cookie('url'));
		        $.ajax({
		            type: "get",
		            url: url,
		            data: data,
		            success: function (res) {
		            	if(res.code==200){
		            		vm.fQList.splice(idx,1);
						}
		            	alert(res.msg)
		            }
		        });
		    }
    	},
    	changeOneSplitSetDd:function(){
    		var splitPrice = this.splitPrice;//分期的钱
    		var splitSetDd = this.oneSplitSetDd;
    		if(splitSetDd[idx].splitNum.indexOf('.')!==-1 && (!splitSetDd[idx].splitNum.split('.')[1] || splitSetDd[idx].splitNum.split('.')[1]==0)) return ;//说明用户删除到点  先跳过
    		var qs=this.splitNum;//总期数
    		var ptsj=30;//理论上还钱周期30天
    		var zts=qs*ptsj;
    		var ptdq=this.lastpt;//理论上平摊的钱
    		var _type=this.splitType;//钱还是比例
    		var str='还款金额大于分期金额';
    		var chazhiBZ=1;//差值标准
			if(_type==1){
				splitPrice=100;
				str='最多只能100%'
				chazhiBZ=0.05;
			}
    		if(ptdq==0 || ptdq==Infinity){
    			ptdq=(splitPrice/qs).toFixed(2);
    		}
			var gbqs=0;//已经改变的期数
			var sdxgqs=0;//手动修改的钱数总和
			var types=[];//改变的下标值
			for(var i=0;i<qs;i++){//找出被修改的值
				var item = splitSetDd[i];
				var chazhi = Math.abs(item.splitNum*1-ptdq);
				if(chazhi>chazhiBZ){
					types.push(i);
					gbqs++
					sdxgqs += (item.splitNum*1);
				}
			}
			if(gbqs==0){
				types.push(idx)
				gbqs++
				sdxgqs += (splitSetDd[idx].splitNum*1);
			}
			if(splitPrice<sdxgqs){//数据还原
				$.extend(this.splitSetDd,this.oldsplitSetDd);
				$.alert(str);
				return ;
			}
			var pjje=((splitPrice-sdxgqs)/(qs-gbqs)).toFixed(2)//剩余平均天数
			this.lastpt=pjje;//最后被平摊的钱
			var ggsl=qs-gbqs;//更改数量  为了找出最后一个
			var lastje=(splitPrice-(sdxgqs + pjje*(qs-gbqs-1))).toFixed(2);//最后一次的天数  因为使用了向下取整
			for(var i=0;i<qs;i++){
				var item = splitSetDd[i];
				if(types.indexOf(i)===-1){
					ggsl--
					if(ggsl==0){//说明是最后一个
						item.splitNum=lastje;
					}else{
						item.splitNum=pjje;
					}
				}
			}
			this.splitSetDd=splitSetDd;
			this.oldsplitSetDd=JSON.parse(JSON.stringify(splitSetDd));
    	},
    	changeSplitSetDd:function(idx){
    		var splitPrice = this.splitPrice;//分期的钱
    		var splitSetDd = this.splitSetDd;
    		if(splitSetDd[idx].splitNum.indexOf('.')!==-1 && (!splitSetDd[idx].splitNum.split('.')[1] || splitSetDd[idx].splitNum.split('.')[1]==0)) return ;//说明用户删除到点  先跳过
    		var qs=this.splitNum;//总期数
    		var ptsj=30;//理论上还钱周期30天
    		var zts=qs*ptsj;
    		var ptdq=this.lastpt;//理论上平摊的钱
    		var _type=this.splitType;//钱还是比例
    		var str='还款金额大于分期金额';
    		var chazhiBZ=1;//差值标准
			if(_type==1){
				splitPrice=100;
				str='最多只能100%'
				chazhiBZ=0.05;
//				var gbqs=0;//已经改变的期数
//				var sdxgqs=0;//手动修改期数
//				var types=[];//改变的下标值
//				for(var i=0;i<qs;i++){//找出被修改的值
//					var item = splitSetDd[i];
//					var chazhi = Math.abs(item.splitNum*1-ptdq);
//					if(chazhi>1){
//						types.push(i);
//						gbqs++
//						sdxgqs += (item.splitNum*1);
//					}
//				}
//				if(splitPrice<sdxgqs){//数据还原
//					$.extend(this.splitSetDd,this.oldsplitSetDd);
//					$.alert("还款金额大于分期金额");
//					return ;
//				}
//				var pjje=((splitPrice-sdxgqs)/(qs-gbqs)).toFixed(2)//剩余平均天数
//				this.lastpt=pjje;//最后被平摊的钱
//				var ggsl=qs-gbqs;//更改数量  为了找出最后一个
//				var lastje=(splitPrice-(sdxgqs + pjje*(qs-gbqs-1))).toFixed(2);//最后一次的天数  因为使用了向下取整
//				for(var i=0;i<qs;i++){
//					var item = splitSetDd[i];
//					if(types.indexOf(i)===-1){
//						ggsl--
//						if(ggsl==0){//说明是最后一个
//							item.splitNum=lastje;
//						}else{
//							item.splitNum=pjje;
//						}
//					}
//				}
//				this.splitSetDd=splitSetDd;
//				this.oldsplitSetDd=JSON.parse(JSON.stringify(splitSetDd));
			}
//			else{
	
	    		if(ptdq==0 || ptdq==Infinity){
	    			ptdq=(splitPrice/qs).toFixed(2);
	    		}
				var gbqs=0;//已经改变的期数
				var sdxgqs=0;//手动修改的钱数总和
				var types=[];//改变的下标值
				for(var i=0;i<qs;i++){//找出被修改的值
					var item = splitSetDd[i];
					var chazhi = Math.abs(item.splitNum*1-ptdq);
					if(chazhi>chazhiBZ){
						types.push(i);
						gbqs++
						sdxgqs += (item.splitNum*1);
					}
				}
				if(gbqs==0){
					types.push(idx)
					gbqs++
					sdxgqs += (splitSetDd[idx].splitNum*1);
				}
				if(splitPrice<sdxgqs){//数据还原
					$.extend(this.splitSetDd,this.oldsplitSetDd);
					$.alert(str);
					return ;
				}
				var pjje=((splitPrice-sdxgqs)/(qs-gbqs)).toFixed(2)//剩余平均天数
				this.lastpt=pjje;//最后被平摊的钱
				var ggsl=qs-gbqs;//更改数量  为了找出最后一个
				var lastje=(splitPrice-(sdxgqs + pjje*(qs-gbqs-1))).toFixed(2);//最后一次的天数  因为使用了向下取整
				for(var i=0;i<qs;i++){
					var item = splitSetDd[i];
					if(types.indexOf(i)===-1){
						ggsl--
						if(ggsl==0){//说明是最后一个
							item.splitNum=lastje;
						}else{
							item.splitNum=pjje;
						}
					}
				}
				this.splitSetDd=splitSetDd;
				this.oldsplitSetDd=JSON.parse(JSON.stringify(splitSetDd));
//			}
    		
    	},
    	addFq:function(){
    		var vm=this;
    		var data={
				cardTypeId:vm.cardTypeSet.id,
				firstPrice:vm.firstPrice,
				splitNum:vm.splitNum,
				splitType:vm.splitType,
				splitContent:vm.splitContent,
				splitPrice:vm.splitPrice,
				totalPrice:vm.cardTypeSet.salesPrice||2000,
				splitSetDd:vm.splitSetDd
//				splitSetDd:JSON.stringify(vm.splitSetDd)
			}
			var url = $.stringFormat("{0}/frCardTypeSplitSet/insert", $.cookie('url'));
	        $.ajax({
	            type: "post",
	            url: url,
	            data: JSON.stringify(data),
                dataType: 'json',
                contentType: "application/json;charset=utf-8",
	            success: function (res) {
	            	alert(res.msg)
					if(res.code==200){
                        vm.getFqList();
					}
	            }
	        });
    	},
    	modifyFq:function(){
    		var vm=this;
			var url = $.stringFormat("{0}/frCardTypeSplitSet/update", $.cookie('url'));
			var data={
				id:vm.oneSplit.id,
				cardTypeId:vm.oneSplit.cardTypeId,
				firstPrice:vm.onefirstPrice,
				splitNum:vm.onesplitNum,
				splitType:vm.onesplitType,
				splitContent:vm.onesplitContent,
				splitPrice:vm.onesplitPrice,
				totalPrice:vm.oneSplit.totalPrice,
				splitSetDd:vm.oneSplitSetDd
			}
	        $.ajax({
	            type: "post",
	            url: url,
	            data: JSON.stringify(data),
                dataType: 'json',
                contentType: "application/json;charset=utf-8",
	            success: function (res) {
	            	alert(res.msg)
	            	if(res.code==200){
                        $('.maskLayerAlert_box_alert_big').hide();
                        vm.getFqList();
	            	}
	            }
	        });
    	},
        //设置项目时间   上午下午
        setsetTime: function () {
            $('.setting').eq(parseInt($(this).attr('id')) - 1).prop("checked", true);
            $('.mask').hide();


            var that = this;
            var data = $("#setTimeform").serialize()
            var url = $.stringFormat("{0}/frShopCtypeConsumePladdset/setsetTimeList", $.cookie('url'));
            $.ajax({
                type: "post",
                url: url,
                data: data,
                success: function (res) {
                    if (res.code == 200) {
                        alert("保存成功")
                        $("#setTimeform")[0].reset();//清空表单
                        that.selectConsume(that.Consumeids);
                    } else {

                        alert(res.msg);
                    }
                }
            });
        },

        /**添加  设置卡使用时间**/
        dsada: function (e) {
            var that = this;
            if (that.Pladdsets.useDayStar === null && that.Pladdsets.useDayEnd === null && that.Pladdsets.useTimeStar === null && that.Pladdsets.useTimeEnd === null) {
                $.alert("输入框不能为空");
                return;
            }
            //写入数据库 //that.Pladdsets

            var Pladdset = {
                useLimit: that.Pladdsets.useLimit,
                useDayStar: that.Pladdsets.useDayStar,
                useDayEnd: that.Pladdsets.useDayEnd,
                useSelect: that.Pladdsets.useSelect == false ? 0 : 1,
                useTimeStar: that.Pladdsets.useTimeStar,
                useTimeEnd: that.Pladdsets.useTimeEnd,
                consumeId: that.Consumeids,
            }
            //that.Pladdsets.consumeId:that.Consumeids;

            var url = $.stringFormat("{0}/frShopCtypeConsumePladdset/setConsumePladdset", $.cookie('url'));
            // ajax提交
            axios.post(url, Pladdset)
                .then(function (res) {
                    if (res.code == 200) {
                        alert("新增成功")

                        that.selectConsume(that.Consumeids);
                    } else {

                        alert(res.msg);
                    }

                })
                .catch(function (error) {
                    $.alert(error)
                });
            startDate();
            table_hide();
        },
        /**修改确认**/
        queren: function (e) {
            $(e).parent().html('<a href="#" onclick="xiugai(this)">修改</a>');
            table_hide()
        },

        //点击弹窗 设置  传进项目id
        ClickingPopup: function (t) {
            var that = this;
            $('.mask').show();
            that.selectConsume(t);
        },

        //查询项目的设置
        selectConsume: function (Consumeid) {
            $("#setTimeform")[0].reset()//清空
            var that = this;
            that.Consumeids = Consumeid
            var url = $.stringFormat("{0}/frCategoryItem/selectConsume", $.cookie('url'));
            $.ajax({
                type: "post",
                url: url,
                data: {
                    consumeid: Consumeid
                },
                async: true,
                success: function (res) {
                    if (res.code == 200) {
                        //						获取项目的设置
                        var data = res.data;
                        that.cardConsumeSetDTO = data;
                        console.log(that.cardConsumeSetDTO)
                        //赋值时间设置
                        var dcar = data.cardConsumePlsetDTO;
                        if (dcar) {
                            for (i in dcar) {
                                var zouyi = dcar[i].startTimeAm + ' 至 ' + dcar[i].startTimePm;
                                var zouyi2 = dcar[i].endTimeAm + ' 至 ' + dcar[i].endTimePm;
                                $('.jddate' + dcar[i].useDays).val(zouyi)
                                $('.jddates' + dcar[i].useDays).val(zouyi2);
                                $('.jddatess' + dcar[i].useDays).attr('checked', dcar[i].useSelect);

                                $('.setid' + dcar[i].useDays).val(dcar[i].id);
                            }
                        }

                        setTimeout(function () {
                            table_hide();

                        }, 50)
                    } else {

                        alert(res.msg);
                    }
                }
            });

        },

        /****/

        //全选/项目 操作
        categoryItemCheckAll: function () {
            var that = this;
            if ($("#xmAll").prop("checked")) {
                for (var i = 0; i < $("#items ul li  .xm").length; i++) {
                    $("#items ul li .xm").prop("checked", true);
                }
            } else {
                $(".xmAll").prop("checked", false);
                for (var i = 0; i < $("#items ul li  .xm").length; i++) {
                    $("#items ul li .xm").prop("checked", false);
                }
            }

        },
        //全选/卡种金额 操作
        jeItemCheckAll: function () {
            var that = this;
            if ($("#jeAll").prop("checked")) {
                for (var i = 0; i < $("#items ul li  .kje").length; i++) {
                    $("#items ul li .kje").prop("checked", true);
                }

            } else {
                $(".jeAll").prop("checked", false);
                for (var i = 0; i < $("#items ul li  .kje").length; i++) {
                    $("#items ul li .kje").prop("checked", false);
                }
            }

        },
        //全选/免费通用  操作
        setCheckAll: function () {
            var that = this;
            if ($("#rdio1").prop("checked")) {
                for (var i = 0; i < $("#items ul li  .radio1").length; i++) {
                    $("#items ul li .radio1").prop("checked", true);
                }
            }
        },
        //按价格 赋值
        setModePrice: function () {
            var Price = $("#setPrice").val();
            for (var i = 0; i < $("#items ul li  .mPrice").length; i++) {
                $(".mPrice").val(Price);
            }
        },
        /**********************************/
        //按折扣 赋值
        setModeWay: function () {
            var discount = $("#modeWay").val();
            for (var i = 0; i < $("#items ul li  .discount").length; i++) {
                $(".discount").val(discount);
            }
        },
        //按折扣价 赋值
        setmWay: function () {
            var mWay = $("#mWay").val();
            for (var i = 0; i < $("#items ul li  .mWay").length; i++) {
                $(".mWay").val(mWay);
            }
        },
        /**********************************/

        /**********************************/
        //按次 赋值
        setModeNum: function () {
            var modeNum = $("#ModeNum").val();
            for (var i = 0; i < $("#items ul li  .modeNum").length; i++) {
                $(".modeNum").val(modeNum);
            }
        },
        //按小时 赋值
        setModeTime: function () {
            var modeTime = $("#ModeTime").val();
            for (var i = 0; i < $("#items ul li  .modeTime").length; i++) {
                $(".modeTime").val(modeTime);
            }
        },
        /**********************************/

        //全选/按价格  操作
        setusageModeCheckAll: function () {
            var that = this;
            if ($("#rdio2").prop("checked")) {
                for (var i = 0; i < $("#items ul li  .radio2").length; i++) {
                    $("#items ul li .radio2").prop("checked", true);
                }
            }
        },

        //全选/按折扣  操作
        setmodeWayCheckAll: function () {
            var that = this;
            if ($("#rdio3").prop("checked")) {
                for (var i = 0; i < $("#items ul li  .radio3").length; i++) {
                    $("#items ul li .radio3").prop("checked", true);
                }
            }
            //折扣未赋值
        },
        //全选/按扣 次数  操作
        setmodePriceCheckAll: function () {
            var that = this;
            if ($("#rdio4").prop("checked")) {
                for (var i = 0; i < $("#items ul li  .radio4").length; i++) {
                    $("#items ul li .radio4").prop("checked", true);
                }
            }
            //次数未赋值
        },

        //赋值   门店id
        CheckStoreid: function (dt) {
            var that = this;
            for (isd in dt) {
                for (var i = 0; i < $('.keshiyong .Scheck').length; i++) {
                    if (isd == $('.keshiyong .Scheck').eq(i).val()) {
                        $('.xiangMu .Storeid').eq(i).html($('.keshiyong .Scheck').eq(i).next().text())
                        //						console.log(i);
                        //						console.log($('.xiangMu .Storeid').length)
                    }
                }
            }

        },
        //隐藏 场馆和 项目
        hidesdaduimItem: function () {
            $(".sdaduim_hide").hide();
            $(".item_hide").hide();

        },

        //场馆全选事件
        sdaduimCheckAll: function (obj) {
            var that = this;
            //场馆全选
            if ($("#sdaduimCheck").prop("checked")) {
                $(".Scheck").prop("checked", true);
                for (var i = 0; i < $('#kysdaduim li input').length; i++) {
                    that.sdaduimArrList.push($('#kysdaduim li input').eq(i).val())
                    //					console.log($('#kysdaduim li input').eq(i).val())
                }
                //显示项目
                $(".item_hide").show()

                that.getSdaduimList(); //点击搜索 项目
            } else {
                $(".Scheck").prop("checked", false);
                that.sdaduimArrList = [];
                that.sdaduimItems = [];
                that.categoryItemlist = [];
                that.shopCardConsume.list = [];
            }
        },

        //场馆单选事件
        sdaduimCheckClick: function (sdid, shopId) {
            //显示项目
            $(".item_hide").show()
            var that = this;

            //			console.log("选中的sdid,shopId==" + sdid + "++++++" + shopId)

            var that = this;
            var arr1 = []
            for (var i = 0; i < $('#kysdaduim li input').length; i++) {
                if ($('#kysdaduim li input').eq(i).prop("checked")) {
                    arr1.push($('#kysdaduim li input').eq(i).val())
                }
            }
            that.sdaduimArrList = arr1

            if (!that.sdaduimArrList.length) {
                //显示项目
                $(".item_hide").hide()
                console.log('取消选中');
                that.sdaduimItems = [];
                that.categoryItemlist = [];
                that.shopCardConsume.list = [];
            } else {
                //that.shopSdaduimIId.push(sdid + "," + shopId);
                that.getSdaduimList(); //点击搜索 项目
            }

        },

        //场馆点击搜索 项目
        getSdaduimList: function () {

            var that = this;
            var ids = new Array();
            ids = that.sdaduimArrList;

            that.sdaduimItems = []
            that.categoryItemlist = []
            var url = $.stringFormat("{0}/frCategoryItem/getCategoryItem", $.cookie('url'));
            $.ajax({
                type: "post",
                url: url,
                data: {
                    ssids: ids
                },
                async: true,
                success: function (res) {
                    if (res.code == 200) {
                        var data = res.data;
                        that.sdaduimItems = data;

                        that.shopCardConsume.list = data; //赋值

                        //						var dadad={}
                        //						for(isd in data) {
                        //							for(var i = 0; i < $('.keshiyong .Scheck').length; i++) {
                        //								if(isd == $('.keshiyong .Scheck').eq(i).val()) {
                        //									dadad[$('.keshiyong .Scheck').eq(i).next().text()]=data[isd];
                        //								}
                        //							}
                        //						}
                        //
                        //						that.shopCardConsume.list =dadad;
                        setTimeout(function () {
                            that.CheckStoreid(data)
                        })

                    } else {
                        alert(1);
                        alert(res.msg);
                    }
                }
            });

        },

        //门店全选事件
        checkboxAll: function (obj) {
            var that = this;
            //全选
            if ($("#checkboxAll").prop("checked")) {
                $(".checks").prop("checked", true);
                for (var i = 0; i < $('#keshiyongmendian li input').length; i++) {
                    that.checkboxs.push($('#keshiyongmendian li input').eq(i).val())
                }
                //显示场馆
                $(".sdaduim_hide").show();
                that.getShopSdaduimList(); //搜索
            } else {
                $(".checks").prop("checked", false);
                that.checkboxs = [];
                that.sdaduimList = [];
                that.shopList = [];
                that.shopCardConsume.list = [];
            }

        },
        //门店单选事件
        checkboxClick: function (idx) {
            var that = this;
            //显示场馆
            $(".sdaduim_hide").show();

            var arr = []
            for (var i = 0; i < $('#keshiyongmendian li input').length; i++) {
                if ($('#keshiyongmendian li input').eq(i).prop("checked")) {
                    arr.push($('#keshiyongmendian li input').eq(i).val())
                }
            }
            that.checkboxs = arr
            that.sdaduimList = [];
            that.shopList = [];
            that.shopCardConsume.list = [];
            if (!that.checkboxs.length) {
                //取消选中
                $(".sdaduim_hide").hide();
                //				console.log('取消选中');
            } else {

                that.getShopSdaduimList(); //搜索
            }
        },

        //门店点击搜索场馆
        getShopSdaduimList: function () {
            var that = this;
            var ids = new Array();
            ids = that.checkboxs;
            var url = $.stringFormat("{0}/shop/ShopSdaduimList", $.cookie('url'));
            $.ajax({
                type: "post",
                url: url,
                data: {
                    sids: ids
                },
                async: true,
                success: function (res) {
                    if (res.code == 200) {
                        if (res.data != null) {
                            //循环 取出门店及关联项目
                            for (var k = 0; k < res.data.length; k++) {
                                that.shopList.push(res.data[k]);
                            }

                        }
                    } else {
                        alert(res.msg);
                    }

                }
            });

        },
        //获取卡权益、门店
        getMemberCard: function () {
            var that = this;
            //隐藏场馆 项目
            that.hidesdaduimItem();
            //var id = "72ed7aa546b0a9cf"; // window.parent.getParam();
            var id = window.parent.getParam();

            var url = $.stringFormat("{0}/frCard/getMemberCard", $.cookie('url'));
            $.ajax({
                "type": "get",
                "url": url,
                "async": false,
                "data": "id=" + id,
                "success": function (res) {
                    if (res.code == 200) {
                        that.tempInfo = res.data.store;
                        that.setCardType(res.data.cardType); //拆分卡属性
                        //console.log(res.data.cardType)
                        that.cardTypeSet = res.data.cardType; //卡基础设置
                        if(that.cardTypeSet.zkXffs!=0){
                            that.cardTypeSet.zkSjjg="";
                        }
                        if(!that.cardTypeSet.canChangeType){
                            that.cardTypeSet.transferFee="";
                        }
                        if(!that.cardTypeSet.canTransfer){
                            that.cardTypeSet.changeTransferFee="";
                        }
                        if(!that.cardTypeSet.qtZsqyUpdate){
                            that.cardTypeSet.qtZdqy="";
                        }
                        if(!that.cardTypeSet.qtXxjgUpdate){
                            that.cardTypeSet.qtZdxxjg="";
                        }
                        if (that.cardTypeSet.type == 1) {
                            that.cardTypeSet.type = "时间卡"
                        } else if (that.cardTypeSet.type == 2) {
                            that.cardTypeSet.type = "小时卡"
                        } else if (that.cardTypeSet.type == 3) {
                            that.cardTypeSet.type = "次卡"
                        } else if (that.cardTypeSet.type == 4) {
                            that.cardTypeSet.type = "储值卡"
                        } else if (that.cardTypeSet.type == 5) {
                            that.cardTypeSet.type = "充值卡"
                        } else if (that.cardTypeSet.type == 6) {
                            that.cardTypeSet.type = "折扣卡"
                        } else if (that.cardTypeSet.type == 7) {
                            that.cardTypeSet.type = "员工卡"
                        }
                        //that.shopCardConsume.cardTypeId=res.data.cardType.id;/** * 卡类型ID */
                        that.init();
                        ///****门店******
                        var _store = [];
                        for (var i in res.data.store) {
                            if (!!res.data.store[i]) _store.push(res.data.store[i]);
                        }
                        if (_store.length) {
                            that.SetStoreItems = _store;
                            for (var i = 0; i < _store.length; i++) {
                                if (that.Storelist.length > 0) {
                                    for (var j = 0; j < that.Storelist.length; j++) {
                                        if (!_store[i]) break;
                                        if (that.Storelist[j].cityId != _store[i].cityId) {
                                            that.Storelist.push(_store[i]);
                                            break;
                                        }
                                    }
                                } else {
                                    if (!_store[i]) continue;
                                    that.Storelist.push(_store[i]);
                                }
                            }
                        }
                    } else {
                        alert(res.msg);
                    }
                }
            });
        },

        //保存会员卡的所有权益
        addMemberCard: function () {
            //获取会员卡的所有权益
            var that = this;
            if (that.cardTypeSet.type == "时间卡") {
                that.cardTypeSet.type = 1
            } else if (that.cardTypeSet.type == "小时卡") {
                that.cardTypeSet.type = 2
            } else if (that.cardTypeSet.type == "次卡") {
                that.cardTypeSet.type = 3
            } else if (that.cardTypeSet.type == "储值卡") {
                that.cardTypeSet.type = 4
            } else if (that.cardTypeSet.type == "充值卡") {
                that.cardTypeSet.type = 5
            } else if (that.cardTypeSet.type == "折扣卡") {
                that.cardTypeSet.type = 6
            } else if (that.cardTypeSet.type == "员工卡") {
                that.cardTypeSet.type = 7
            }

            that.setCardTypePzfs(); //处理凭证方式
            var url = $.stringFormat("{0}/frCard/addMemberCard", $.cookie('url'));
            console.log(that.cardTypeSet)
            if(that.cardTypeSet.zkXffs!=0){
                that.cardTypeSet.zkSjjg="";
            }
            if(!that.cardTypeSet.canChangeType){
                that.cardTypeSet.transferFee="";
            }
            if(!that.cardTypeSet.canTransfer){
                that.cardTypeSet.changeTransferFee="";
            }
            var data = JSON.stringify(that.cardTypeSet)
            $.ajax({
                url: url,
                data: data,
                type: 'POST',
                dataType: 'json',
                contentType: "application/json;charset=utf-8",
                success: function (res) {
                    if (res.code == "200") {
                        var flag = that.saveItems();
                        console.log(flag)
                        if (that.cardConsumeSetDTO.length > 0) {
                            flag = that.saveConsumeSet();
                        }
                        console.log(flag)
                        $.alert(res.msg)
                        alert("添加成功")
                        location.reload();
                    } else {
                        alert("网络繁忙")
                    }
                }
            })
        },

        //保存
        saveItems: function () {
            var that = this;
            var url = $.stringFormat("{0}/frShopCtypeConsume/addShopCtypeConsume/", $.cookie('url'));
            if (that.shopCardConsume.list.length < 1 || that.shopCardConsume.list == []) {
                return;
            }
            axios.post(url + that.cardTypeSet.id, that.shopCardConsume.list)
                .then(function (res) {
                    if (res.code != '200') {
                        return false;
                    } else {
                        return true;
                    }
                })
                .catch(function (error) {
                    $.alert(error)
                });
        },
        //保存项目设置
        saveConsumeSet: function () {
            var that = this;
            var url = $.stringFormat("{0}/frCategoryItem/setConsume", $.cookie('url'));
            var consume = JSON.stringify(that.cardConsumeSetDTO)
            console.log(consume)
            if (consume == null || consume == '' || consume == [] || consume.size() < 1) {
                return;
            }
            $.ajax({
                url: url,
                data: consume,
                type: 'POST',
                dataType: 'json',
                contentType: "application/json;charset=utf-8",
                success: function (res) {
                    if (res.code == "200") {
                        // alert("添加成功")
                        // location.reload();
                        return true;
                    } else {
                        return false;
                    }
                }
            })
        },

        //处理凭证方式
        setCardTypePzfs: function () {
            var that = this;
            var pzfs = "";
            // var zkXffs = "";
            for (var i = 0; i < that.pzfsArr.length; i++) {
                if (i == that.pzfsArr.length - 1) {
                    pzfs += that.pzfsArr[i];
                } else {
                    pzfs += that.pzfsArr[i] + ",";
                }
            }
            that.cardTypeSet.pzfs = pzfs;
            // //子卡消费方式
            // for (var i = 0; i < that.zkXffsArr.length; i++) {
            //     if (i == that.zkXffsArr.length - 1) {
            //         zkXffs += that.zkXffsArr[i];
            //     } else {
            //         zkXffs += that.zkXffsArr[i] + ",";
            //     }
            // }
            // that.cardTypeSet.zkXffs = zkXffs;
            //组合属性
            that.cardTypeSet.serviceLife = $("#service").val() + "," + $("#life").val();
            that.cardTypeSet.swipingInterval = $("#swiping").val() + "," + $("#interval").val();
            that.cardTypeSet.swipingTime = $("#swiping1").val() + "," + $("#timess").val();
        },
        //拆分卡属性
        setCardType: function (data) {
            var that = this;
            if (data.serviceLife != null) {
                var arr = data.serviceLife.split(',');
                //使用期限
                $("#service").val(arr[0]);
                $("#life").val(arr[1]);
            }
            //刷卡间隔：
            if (data.swipingInterval != null) {
                arr = data.swipingInterval.split(',');
                $("#swiping").val(arr[0]);
                $("#interval").val(arr[1]);
            }
            //刷卡次数
            if (data.swipingInterval != null) {
                arr = data.swipingTime.split(',');
                $("#swiping1").val(arr[0]);
                $("#timess").val(arr[1]);
            }
            //人数
            //$("#used_num").val(data.usedNum);
            //验证方式
            if (data.pzfs != null) {
                var checkBoxArray = data.pzfs.split(',')
                that.pzfsArr = checkBoxArray;
                //				for(var i = 0; i < checkBoxArray.length; i++) {
                //					$("input[name=pzfs]").each(function() {
                //						if($(this).val() == checkBoxArray[i]) {
                //							$(this).attr("checked", "checked");
                //						}
                //					});
                //				}
            }
            //
            // if (data.zkXffs != null) {
            //     var zkXffsArray = data.zkXffs.split(',')
            //     that.zkXffsArr = zkXffsArray;
            // }

        },

        //初始化加载
        init: function () {
//			var type = 1; // window.parent.getType();
            if (!this.cardTypeSet.type) alert('未获取到卡类型')
            switch (parseInt(this.cardTypeSet.type)) {
                //时间卡(年卡）
                case 1:
                    // $('#years').html('一年卡');
                    $('#time').html('时间卡');
                    $('.basis .common2 p').html('（填写使用期限，如果期限已到，该卡权益失效）');
                    $('.basis .total').html('总时长：').show();
                    $('.basis .total_input').attr('placeholder', '总时长(天)').show();
                    $('.basis .common').show();
                    break;
                //小时卡
                case 2:
                    // $('#years').html('10小时卡');
                    $('#time').html('时间卡');
                    $('.basis .common2 p').html('（填写使用期限，如果期限已到，该卡权益失效）');
                    $('.basis .total').html('总时长：').show();
                    $('.basis .total_input').attr('placeholder', '总时长(小时)').show();
                    $('.basis .common').show();
                    break;
                //次卡
                case 3:
                    // $('#years').html('一次卡');
                    $('#time').html('次卡');
                    $('.basis .common2 p').html('（填写使用期限，如果期限已到，该卡权益失效）');
                    $('.basis .total').html('总次数：').show();
                    $('.basis .total_input').attr('placeholder', '扣卡次数').show();
                    $('.basis .common').show();
                    break;
                //储值卡
                case 4:
                case 6:
                    // $('#years').html('储值卡');
                    $('#time').html('储值卡');
                    $('.basis .common2 p').html('（填写使用期限，如果期限已到，该卡权益失效）');
                    $('.basis .total').hide();
                    $('.basis .total_input').hide();
                    $('.basis .common').show();
                    break;
                //充值卡
                case 5:
                    // $('#years').html('10元充值卡');
                    $('#time').html('充值卡');
                    $('.basis .common2 p').html('（填写使用期限，如果期限已到，该卡权益失效）');
                    $('.basis .total').html('卡金额：').show();
                    $('.basis .total_input').attr('placeholder', '充值卡金额(元)').show();
                    $('.basis .common').show();
                    break;
                case 7: //员工卡
                    // $('#years').html('经理卡');
                    $('#time').html('员工卡');
                    $('.basis .common2 p').html('（员工卡使用期限不能修改）');
                    $('.basis .total').hide();
                    $('.basis .total_input').hide();
                    $('.basis .common').hide();
                    $('.basis .common').eq(1).show()
                    $('.basis .common').eq(3).show()
                    break;

            }
        },
        //特殊续卡价格
        special_checkbox: function (e) {
            if (this.special) {
                $('.special').hide();
                // this.special = false;
            } else {
                $('.special').show();
                // this.special = true;
            }
        },
        // 显示弹窗
        settingShow: function () {
            if (this.setting) {
                $('.mask').hide();
                this.setting = false;
            } else {
                $('.mask').show();
                this.setting = true;
            }
        },
        //卡权益总结说明
        cardExplain: function () {
            var that = this;
            that.cardExplainInfo = "卡类别：" + that.cardTypeSet.cardTypeName + "，卡名称：" + that.cardTypeSet.type + "，可使用门店："
            $.each(that.SetStoreItems, function (i, n) {

                if (i + 1 != that.SetStoreItems.length) {
                    that.cardExplainInfo += n.shopName
                    that.cardExplainInfo += "、"
                } else {
                    that.cardExplainInfo += n.shopName
                }
            })

            $.each(that.shopList, function (i, n) {

                $.each(n.sdaduimArrayList, function (j, k) {
                    that.cardExplainInfo += (n.shopName + '-' + k.name + "、")

                })

            })
            // if(){
            //
            // }
            that.cardExplainInfo += "，可使用场馆："
            $.each(that.tempInfo, function (i, n) {
                if (n != null && n != '') {
                    $.each(n.sdaduimArrayList, function (j, k) {
                        that.cardExplainInfo += (n.shopName + '-' + k.name + "、")
                    })
                }


            })
            that.cardExplainInfo = that.cardExplainInfo.substring(0, that.cardExplainInfo.lastIndexOf('、'));
            that.cardExplainInfo += "，使用项目："
            var temp = 1;
            $.each(that.tempInfo, function (i, n) {
                if (n != null && n != '') {

                    $.each(n.sdaduimArrayList, function (j, k) {

                        $.each(k.categoryItemlist, function (o, p) {
                            that.cardExplainInfo += "（" + (temp++) + "）、" + p.itemName;
                            if (p.kzje == true) {
                                that.cardExplainInfo += "，扣卡消费"
                            }
                            if (p.usageMode == 0) {
                                that.cardExplainInfo += "，免费通用"
                            } else if (p.usageMode == 1) {
                                if (p.modePrice != null) {
                                    that.cardExplainInfo += "，按" + p.modePrice + "元/次"
                                }

                            } else if (p.usageMode == 2) {
                                if (p.modeDiscount != null) {
                                    if (p.modeWay == 0) {
                                        that.cardExplainInfo += "，按市场价" + p.modeDiscount + "折消费"
                                    } else if (p.modeWay == 1) {
                                        that.cardExplainInfo += "，按促销价" + p.modeDiscount + "折消费"
                                    } else if (p.modeWay == 2) {
                                        that.cardExplainInfo += "，按会员价" + p.modeDiscount + "折消费"
                                    }
                                }

                            } else if (p.usageMode == 3) {
                                if (p.modeTime != null && p.modeNum != null) {
                                    that.cardExplainInfo += "，扣" + p.modeTime + "次每" + p.modeNum
                                }

                            }

                            if (!p.plSet) {
                                that.cardExplainInfo += "，无使用限制"
                            }
                            that.cardExplainInfo += ";"
                        })
                    })
                }


            })

            console.log(that.tempInfo)
            // 	 ，使用项目：（1）、增肌，扣卡消费，无使用限制；（2）、体态调整，免费通用，无使用限制；"
        }
    },
    updated: function () {
        // dsada();
        startDate();
    },


})


//var mask = new Vue({
//	el: '#masks',
//	data: {
//	 ctypeConsumePladdset: [], //设置  fr_shop_ctype_consume_pladdset
//
//	},
//	methods: {
//
//
//	}
//})