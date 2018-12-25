var right = new Vue({
	el: '#right',
	data: {
		special: true,
		SetStoreItems: [], //门店
		Storelist: [], //门店信息
		pzfsArr: [],
		zkXffsArr: [],

		cardTypeSet: {
			pzfs: '',
			pid: '',
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
			changeTransferFee:0,
		},
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
	},

	methods: {
           //设置项目时间   上午下午
		setsetTime: function() {
		$('.setting').eq(parseInt($(this).attr('id')) - 1).prop("checked", true);
		$('.mask').hide();
			
			
			var that = this;
			var data = $("#setTimeform").serialize()
			var url = $.stringFormat("{0}/frShopCtypeConsumePladdset/setsetTimeList", $.cookie('url'));
			$.ajax({
				type: "post",
				url: url,
				data: data,
				success: function(res) {
					if(res.code == 200) {
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
		dsada: function(e) {
			var that = this;
			if(that.Pladdsets.useDayStar === null && that.Pladdsets.useDayEnd === null && that.Pladdsets.useTimeStar === null && that.Pladdsets.useTimeEnd === null) {
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
				.then(function(res) {
					if(res.code == 200) {
						alert("新增成功")

						that.selectConsume(that.Consumeids);
					} else {

						alert(res.msg);
					}

				})
				.catch(function(error) {
					$.alert(error)
				});
			startDate();
			table_hide();
		},
		/**修改确认**/
		queren: function(e) {
			$(e).parent().html('<a href="#" onclick="xiugai(this)">修改</a>');
			table_hide()
		},

		//点击弹窗 设置  传进项目id   
		ClickingPopup: function(t) {
			var that = this;
			$('.mask').show();
			that.selectConsume(t);
		},

		//查询项目的设置
		selectConsume: function(Consumeid) {
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
				success: function(res) {
					if(res.code == 200) {
						//						获取项目的设置
						var data = res.data;
						that.cardConsumeSetDTO = data;

						//赋值时间设置
						var dcar = data.cardConsumePlsetDTO;
						if(dcar) {
							for(i in dcar) {
								var zouyi = dcar[i].startTimeAm + ' 至 ' + dcar[i].startTimePm;
								var zouyi2 = dcar[i].endTimeAm + ' 至 ' + dcar[i].endTimePm;
								$('.jddate' + dcar[i].useDays).val(zouyi)
								$('.jddates' + dcar[i].useDays).val(zouyi2);
								$('.jddatess' + dcar[i].useDays).attr('checked', dcar[i].useSelect);

								$('.setid' + dcar[i].useDays).val(dcar[i].id);
							}
						}

						setTimeout(function() {
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
		categoryItemCheckAll: function() {
			var that = this;
			if($("#xmAll").prop("checked")) {
				for(var i = 0; i < $("#items ul li  .xm").length; i++) {
					$("#items ul li .xm").prop("checked", true);
				}
			} else {
				$(".xmAll").prop("checked", false);
				for(var i = 0; i < $("#items ul li  .xm").length; i++) {
					$("#items ul li .xm").prop("checked", false);
				}
			}

		},
		//全选/卡种金额 操作
		jeItemCheckAll: function() {
			var that = this;
			if($("#jeAll").prop("checked")) {
				for(var i = 0; i < $("#items ul li  .kje").length; i++) {
					$("#items ul li .kje").prop("checked", true);
				}

			} else {
				$(".jeAll").prop("checked", false);
				for(var i = 0; i < $("#items ul li  .kje").length; i++) {
					$("#items ul li .kje").prop("checked", false);
				}
			}

		},
		//全选/免费通用  操作
		setCheckAll: function() {
			var that = this;
			if($("#rdio1").prop("checked")) {
				for(var i = 0; i < $("#items ul li  .radio1").length; i++) {
					$("#items ul li .radio1").prop("checked", true);
				}
			}
		},
		//按价格 赋值
		setModePrice: function() {
			var Price = $("#setPrice").val();
			for(var i = 0; i < $("#items ul li  .mPrice").length; i++) {
				$(".mPrice").val(Price);
			}
		},
		/**********************************/
		//按折扣 赋值
		setModeWay: function() {
			var discount = $("#modeWay").val();
			for(var i = 0; i < $("#items ul li  .discount").length; i++) {
				$(".discount").val(discount);
			}
		},
		//按折扣价 赋值
		setmWay: function() {
			var mWay = $("#mWay").val();
			for(var i = 0; i < $("#items ul li  .mWay").length; i++) {
				$(".mWay").val(mWay);
			}
		},
		/**********************************/

		/**********************************/
		//按次 赋值
		setModeNum: function() {
			var modeNum = $("#ModeNum").val();
			for(var i = 0; i < $("#items ul li  .modeNum").length; i++) {
				$(".modeNum").val(modeNum);
			}
		},
		//按小时 赋值
		setModeTime: function() {
			var modeTime = $("#ModeTime").val();
			for(var i = 0; i < $("#items ul li  .modeTime").length; i++) {
				$(".modeTime").val(modeTime);
			}
		},
		/**********************************/

		//全选/按价格  操作
		setusageModeCheckAll: function() {
			var that = this;
			if($("#rdio2").prop("checked")) {
				for(var i = 0; i < $("#items ul li  .radio2").length; i++) {
					$("#items ul li .radio2").prop("checked", true);
				}
			}
		},

		//全选/按折扣  操作
		setmodeWayCheckAll: function() {
			var that = this;
			if($("#rdio3").prop("checked")) {
				for(var i = 0; i < $("#items ul li  .radio3").length; i++) {
					$("#items ul li .radio3").prop("checked", true);
				}
			}
			//折扣未赋值
		},
		//全选/按扣 次数  操作
		setmodePriceCheckAll: function() {
			var that = this;
			if($("#rdio4").prop("checked")) {
				for(var i = 0; i < $("#items ul li  .radio4").length; i++) {
					$("#items ul li .radio4").prop("checked", true);
				}
			}
			//次数未赋值
		},

		//赋值   门店id
		CheckStoreid: function(dt) {
			var that = this;
			for(isd in dt) {
				for(var i = 0; i < $('.keshiyong .Scheck').length; i++) {
					if(isd == $('.keshiyong .Scheck').eq(i).val()) {
						$('.xiangMu .Storeid').eq(i).html($('.keshiyong .Scheck').eq(i).next().text())
						//						console.log(i);
						//						console.log($('.xiangMu .Storeid').length)
					}
				}
			}

		},
		//隐藏 场馆和 项目
		hidesdaduimItem: function() {
			$(".sdaduim_hide").hide();
			$(".item_hide").hide();

		},

		//场馆全选事件
		sdaduimCheckAll: function(obj) {
			var that = this;
			//场馆全选
			if($("#sdaduimCheck").prop("checked")) {
				$(".Scheck").prop("checked", true);
				for(var i = 0; i < $('#kysdaduim li input').length; i++) {
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
		sdaduimCheckClick: function(sdid, shopId) {
			//显示项目
			$(".item_hide").show()
			var that = this;

			//			console.log("选中的sdid,shopId==" + sdid + "++++++" + shopId)

			var that = this;
			var arr1 = []
			for(var i = 0; i < $('#kysdaduim li input').length; i++) {
				if($('#kysdaduim li input').eq(i).prop("checked")) {
					arr1.push($('#kysdaduim li input').eq(i).val())
				}
			}
			that.sdaduimArrList = arr1

			if(!that.sdaduimArrList.length) {
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
		getSdaduimList: function() {

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
				success: function(res) {
					if(res.code == 200) {
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
						setTimeout(function() {
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
		checkboxAll: function(obj) {
			var that = this;
			//全选
			if($("#checkboxAll").prop("checked")) {
				$(".checks").prop("checked", true);
				for(var i = 0; i < $('#keshiyongmendian li input').length; i++) {
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
		checkboxClick: function(idx) {
			var that = this;
			//显示场馆
			$(".sdaduim_hide").show();

			var arr = []
			for(var i = 0; i < $('#keshiyongmendian li input').length; i++) {
				if($('#keshiyongmendian li input').eq(i).prop("checked")) {
					arr.push($('#keshiyongmendian li input').eq(i).val())
				}
			}
			that.checkboxs = arr
			that.sdaduimList = [];
			that.shopList = [];
			that.shopCardConsume.list = [];
			if(!that.checkboxs.length) {
				//取消选中
				$(".sdaduim_hide").hide();
				//				console.log('取消选中');
			} else {

				that.getShopSdaduimList(); //搜索
			}
		},

		//门店点击搜索场馆
		getShopSdaduimList: function() {
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
				success: function(res) {
					if(res.code == 200) {
						if(res.data != null) {
							//循环 取出门店及关联项目
							for(var k = 0; k < res.data.length; k++) {
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
		getMemberCard: function() {
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
				"success": function(res) {
					if(res.code == 200) {
						that.setCardType(res.data.cardType); //拆分卡属性
						//console.log(res.data.cardType)
						that.cardTypeSet = res.data.cardType; //卡基础设置
						//that.shopCardConsume.cardTypeId=res.data.cardType.id;/** * 卡类型ID */

						///****门店******
						if(res.data.store != null) {
							that.SetStoreItems = res.data.store;
							for(var i = 0; i < res.data.store.length; i++) {
								if(i > 0) {
									for(var j = 0; j < that.Storelist.length; j++) {
										if(that.Storelist[j].cityId != res.data.store[i].cityId) {
											that.Storelist.push(res.data.store[i]);
										}
									}
								} else {
									that.Storelist.push(res.data.store[i]);
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
		addMemberCard: function() {
			//获取会员卡的所有权益
			var that = this;

			that.setCardTypePzfs(); //处理凭证方式
			var url = $.stringFormat("{0}/frCard/addMemberCard", $.cookie('url'));
			axios.post(url, that.cardTypeSet)
				.then(function(res) {
					var resData = eval(res);
					that.saveItems();

					that.saveConsumeSet();
					$.alert(resData['data']['msg'])

				})
				.catch(function(error) {
					$.alert(error)
				});

		},

		//保存
		saveItems: function() {
			var that = this;
			var url = $.stringFormat("{0}/frShopCtypeConsume/addShopCtypeConsume/", $.cookie('url'));
			axios.post(url + that.cardTypeSet.id, that.shopCardConsume.list)
				.then(function(res) {
					var resData = eval(res);
					$.alert(resData['data']['msg'])

				})
				.catch(function(error) {
					$.alert(error)
				});
		},
		//保存项目设置
		saveConsumeSet: function() {

			var that = this;
			var url = $.stringFormat("{0}/frCategoryItem/setConsume", $.cookie('url'));
            var consume = JSON.stringify(that.cardConsumeSetDTO)
            $.ajax({
                url: url,
                data: consume,
                type: 'POST',
                dataType: 'json',
                contentType: "application/json;charset=utf-8",
                success: function (res) {
                    alert(res.msg)
                }
			// axios.post(url, that.cardConsumeSetDTO)
			// 	.then(function(res) {
			// 		var resData = eval(res);
			// 		$.alert(resData['data']['msg'])
            //
			// 	})
			// 	.catch(function(error) {
			// 		$.alert(error)
				});

		},

		//处理凭证方式
		setCardTypePzfs: function() {
			var that = this;
			var pzfs = "";
			var zkXffs = "";
			for(var i = 0; i < that.pzfsArr.length; i++) {
				if(i == that.pzfsArr.length - 1) {
					pzfs += that.pzfsArr[i];
				} else {
					pzfs += that.pzfsArr[i] + ",";
				}
			}
			that.cardTypeSet.pzfs = pzfs;
			//子卡消费方式
			for(var i = 0; i < that.zkXffsArr.length; i++) {
				if(i == that.zkXffsArr.length - 1) {
					zkXffs += that.zkXffsArr[i];
				} else {
					zkXffs += that.zkXffsArr[i] + ",";
				}
			}
			that.cardTypeSet.zkXffs = zkXffs;
			//组合属性
			that.cardTypeSet.serviceLife = $("#service").val() + "," + $("#life").val();
			that.cardTypeSet.swipingInterval = $("#swiping").val() + "," + $("#interval").val();
			that.cardTypeSet.swipingTime = $("#swiping1").val() + "," + $("#timess").val();
		},
		//拆分卡属性
		setCardType: function(data) {
			var that = this;
			if(data.serviceLife != null) {
				var arr = data.serviceLife.split(',');
				//使用期限
				$("#service").val(arr[0]);
				$("#life").val(arr[1]);
			}
			//刷卡间隔：
			if(data.swipingInterval != null) {
				arr = data.swipingInterval.split(',');
				$("#swiping").val(arr[0]);
				$("#interval").val(arr[1]);
			}
			//刷卡次数
			if(data.swipingInterval != null) {
				arr = data.swipingTime.split(',');
				$("#swiping1").val(arr[0]);
				$("#timess").val(arr[1]);
			}
			//人数
			//$("#used_num").val(data.usedNum);
			//验证方式
			if(data.pzfs != null) {
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

			if(data.zkXffs != null) {
				var zkXffsArray = data.zkXffs.split(',')
				that.zkXffsArr = zkXffsArray;
			}

		},

		//初始化加载
		init: function() {
			var type = 1; // window.parent.getType();
			switch(parseInt(type)) {
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
		special_checkbox: function(e) {
			if(this.special) {
				$('.special').show();
				this.special = false;
			} else {
				$('.special').hide();
				this.special = true;
			}
		},
		// 显示弹窗
		settingShow: function() {
			if(this.setting) {
				$('.mask').hide();
				this.setting = false;
			} else {
				$('.mask').show();
				this.setting = true;
			}
		}
	},
	updated: function() {
		// dsada();
		startDate();
	}

})

right.getMemberCard();

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