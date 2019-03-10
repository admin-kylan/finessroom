

const lcapp = new Vue({
    el: '#leadCustomerEntry',
    data: {
        //客户资料
        customerData:{
            //用户头像url 例：/avatar/aaa.jpg
            picLink:null,
            shopId:'',
            salespersonId:'',
            //基础参数
            base:{
                clientName:null,
                englishName:null,
                mobile:null,
                tel:null,
                sex:null,
                stature:null,
                weight:null,
                school:null,
                major:null,
                birthday:null,
                speciality:null,
                levelId:null,
                maritalStatus:'',
                idType:0,
                idNo:null,
                idAddress:null,
                homeAdd:null,
                email:null,
                nativePlace:null,
                companyName:null,
                jobTitle:null,
                companyAddress:null,
                businessScope:null,
                carNum:null,
                attentionProblem:null,
                companyTel:null,
                income:null,
                carBrand:null,
                note:null,
                referenceName:null,
                referenceTel:null,
                consultantName:null,
                consultantTel:null,
                fwhjName:null,
                fwhjTel:null,
                protectDay:null,
                customerSource:null,
                applyTime:null,
                flag:null,
                type:null,
                isUsing:null,
                createTime:null,
                updateTime:null,
                createUserName:null,
                updateUserName:null,
                CustomerCode:null,
                createUserId:null,
                updateUserId:null,
                status:null,
                wechat:null,
                qq:null,
                vipLevel:'',
                purchaseWill:'',
                willingPrice:null,
                buildDate:(new Date()).format('yyyy-MM-dd'),
                cautionQuestion:null,
                willingCardType:'',
                willingCardName:'',
            },
        },
        //门店列表
        shopList:[],
        //卡类别列表
        cardTypeList:[],
        //卡名称列表
        cardNameList:[],
        //销售顾问列表
        salespersonList:[],
        //渠道列表
        resourceList:[],
        //哪个渠道被选中
        resourceIndex:'',
        //头像图片链接
        imgUrl:null,
    },
    computed:{
        wpCheckNum:{
            get:function () {
                const that = this;
                return that.customerData.base.willingPrice;
            },
            set:function (newValue) {
                const that = this;
                that.customerData.base.willingPrice = newValue.replace(/[^\d]/g,'');
            }
            
        }

    },
    watch: {

    },
    methods:{
        /**
         * 查询门店列表
         */
        queryShopList:function () {
            const that  = this;
            const url = $.stringFormat('{0}/shop/getShopListNoTree','http://www.4006337366.com:8080/');
            axios.get(url)
            .then(function (res) {
                let jsonData = eval(res);
                if(jsonData['data']['code']==='200'){
                    that.shopList = jsonData['data']['data'];
                    that.customerData.shopId=$.cookie("shopid")
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
         * 查询卡类别列表
         */
        queryCardTypeList:function () {
            const that  = this;
            const url = $.stringFormat('{0}/frCardType/getCardTypeListByCode','http://www.4006337366.com:8080/');
            axios.get(url)
                .then(function (res) {
                    let jsonData = eval(res);
                    if(jsonData['data']['code']==='200'){
                        that.cardTypeList = jsonData['data']['data'];
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
         * 查询卡名称列表
         */
        queryCardNameList:function () {
            const that  = this;
            var cardId = that.customerData.base.willingCardType;
            console.log(cardId)
            const url = $.stringFormat('{0}/frCardType/getCardNameListByCode','http://www.4006337366.com:8080/');
            $.get(url,{"cardId":cardId},function (res) {
                if (res.code=='200'){
                    that.cardNameList=res.data;
                }else {
                    $.alert(jsonData['data']['msg'])
                }
            })

        },
        /**
         * 查询销售顾问列表
         */
        querySalespersonList:function () {
            const that  = this;
            const url = $.stringFormat('{0}/personnelInfo/getSalespersonList','http://www.4006337366.com:8080/');
            axios.get(url)
                .then(function (res) {
                    let jsonData = eval(res);
                    if(jsonData['data']['code']==='200'){
                        that.salespersonList = jsonData['data']['data'];
                       that.customerData.salespersonId=$.cookie("id")
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
         * 查询手动来源渠道列表
         */
        queryResourceList:function () {
            const that  = this;
            const url = $.stringFormat('{0}/frClientSource/getListByIsAuto?isAuto=0','http://www.4006337366.com:8080/');
            axios.get(url)
                .then(function (res) {
                    let jsonData = eval(res);
                    if(jsonData['data']['code']==='200'){
                        that.resourceList = jsonData['data']['data'];
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
         * 弹出选择图片
         */
        pictureSelected:function () {
            $('#file-fr').click();
        },
        /**
         * 选择完成后显示图片
         * @param e
         */
        pictureShow:function (e) {
            let that = this;
            let file = e.srcElement.files.item(0);
            // 看支持不支持FileReader
            if (!file || !window.FileReader) return;
            if (/^image/.test(file.type)) {
                // 创建一个reader
                let reader = new FileReader();
                // 将图片将转成 base64 格式
                reader.readAsDataURL(file);
                // 读取成功后的回调
                reader.onloadend = function () {
                    that.imgUrl =  this.result;
                }
            }
        },
        getUserData:function (referenceTel) {
            var that = this;
            var mess = '';
            var isFlag = false;
            //检索手机
            isFlag = isPoneAvailable(referenceTel);
            mess = "请输入正确的手机号";
            if (!isFlag) {
                // that.loadData2.clitenUser[str] = '';
                return $.alert(mess);
            } else {
                var url = $.stringFormat('{0}/frClient/getNameByPhone', 'http://www.4006337366.com:8080/');
                console.log(referenceTel)
                $.get(url, {"phone": referenceTel}, function (res) {
                         console.log(res.data)
                    that.customerData.base.referenceName=res.data
                    $("#name").val( that.customerData.base.referenceName)
                })

            }
        },    getUserByMobile:function (mobile) {
            var that = this;
            var mess = '';
            var isFlag = false;
            //检索手机
            isFlag = isPoneAvailable(mobile);
            mess = "请输入正确的手机号";
            if (!isFlag) {
                // that.loadData2.clitenUser[str] = '';
                return $.alert(mess);
            } else {
                var url = $.stringFormat('{0}/frClient/getByPhone', 'http://www.4006337366.com:8080/');
                console.log(mobile)
                $.get(url, {"phone": mobile}, function (res) {
                    console.log(res.data)
                    if(res.data!=false && res.data!="false"){
                        that.customerData.base=res.data
                    }

                })

            }
        },
        /**
         * 上传图片
         */
        uploadAvatar:function () {
            const that = this;
            const url = $.stringFormat('{0}/file/upload','http://www.4006337366.com:8080/');
            let isSuccess = false;
            let f = that.$refs.avatar;
            //判断是否有图片
            if(f.files.length<1){
                $.alert("请选择图片");
            }
            let file = f.files[0];
            //判断图片大小是否超过5MB
            if(file.size>5242880){
                $.alert("图片大小不得大于5MB");
            }
            Loading.prototype.show();
            let param = new FormData();
            param.append('file', f.files[0]);//通过append向form对象添加数据
            param.append('childPath', 'avatar/');//通过append向form对象添加数据
            let config = {
                headers: { 'Content-Type': 'multipart/form-data' }
            };
            //上传图片
            axios.post(url, param, config)
            .then(response => {
                let jsonData = eval(response);
                Loading.prototype.hide();
                $.alert(jsonData['data']['data']['msg'])
                that.customerData.picLink = jsonData['data']['data']['imgUrl'];
                console.log(that.customerData.picLink)
                that.imgUrl = jsonData['data']['data']['imgUrl'];
                if(jsonData['data']['code']==='200'){
                    return true;
                }
            })
            return isSuccess;
        },
        /**
         * 保存
         */
        saveCustomer:function () {

            const that = this;
            const url = $.stringFormat('{0}/frClient/postAddPotentialCustomer','http://www.4006337366.com:8080/');
            //组装数据
            if(that.resourceIndex>-1&&that.resourceIndex!==''){
                let resource = that.resourceList[that.resourceIndex];
                that.customerData.base.customerSource = $.stringFormat('{0}:{1}',resource.sourceName,resource.sourceText);
            }
            //校验数据
            if(that.customerData.base.clientName===null){
                $.alert('请输入姓名');
                return;
            }
            if(that.customerData.base.sex===null){
                $.alert('请选择性别');
                return;
            }
            if(that.customerData.base.mobile===null){
                $.alert('请输入手机号码');
                return;
            }
            if(!isPoneAvailable(that.customerData.base.mobile)){
                $.alert('请输入正确的手机号码');
                return;
            }
            if(!isPoneAvailable(that.customerData.base.mobile)){
                $.alert('请输入正确的手机号码');
                return;
            }
            if(that.customerData.base.stature!==null&&!checkNum(that.customerData.base.stature)){
                $.alert('请输入正确的身高');
                return;
            }
            if(that.customerData.base.weight!==null&&!checkNum(that.customerData.base.weight)){
                $.alert('请输入正确的体重');
                return;
            }

            // let reg = /^\s*data:([a-z]+\/[a-z0-9-+.]+(;[a-z-]+=[a-z0-9-]+)?)?(;base64)?,([a-z0-9!$&',()*+;=\-._~:@\/?%\s]*?)\s*$/i;
            // if(reg.test(that.imgUrl)){
            //     //选择了图片但是没有上传
            //     if(that.uploadAvatar()){
            //
            //     }
            // }
            var options = $("#cardType option:selected");
            that.customerData.base.willingCardType= options.text();
            console.log( that.customerData.base.willingCardType)
            //提交
            axios.post(url,that.customerData)
            .then(response => {
                let jsonData = eval(response);
                if(jsonData['data']['code']==='200'){
                    $.confirm({
                        title: '提示',
                        content: jsonData['data']['msg'],
                        buttons: {
                            somethingElse: {
                                text: '确定',
                                keys: ['enter', 'shift'],
                                action: function(){
                                    window.location.href="potentialCustomers.html";
                                }
                            }
                        }
                    });
                }else {
                    $.alert(jsonData['data']['msg'])
                }
            })
            .catch(error => {
                $.alert("请求发生错误！")
                console.log(error);
            });
        },


    }
});


lcapp.queryShopList();
lcapp.queryCardTypeList();
lcapp.querySalespersonList();
lcapp.queryResourceList();


// 初始化加载中插件
Loading.prototype.init();

//初始化:生日
jeDate('.birthday', {
    format: "YYYY-MM-DD",
    donefun: function(obj){
        lcapp.customerData.base.birthday = obj.val;
    }
});
//初始化:建档日期
jeDate('.buildDate', {
    format: "YYYY-MM-DD",
    donefun: function(obj){
        lcapp.customerData.base.buildDate = obj.val;
    }
});


// 对Date的扩展，将 Date 转化为指定格式的String 
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
// 例子： 
// (new Date()).format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
// (new Date()).format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
Date.prototype.format = function(fmt) { //author: meizz 
	var o = {
		"M+": this.getMonth() + 1, //月份 
		"d+": this.getDate(), //日 
		"h+": this.getHours(), //小时 
		"m+": this.getMinutes(), //分 
		"s+": this.getSeconds(), //秒 
		"q+": Math.floor((this.getMonth() + 3) / 3), //季度 
		"S": this.getMilliseconds() //毫秒 
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	for (var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
}
