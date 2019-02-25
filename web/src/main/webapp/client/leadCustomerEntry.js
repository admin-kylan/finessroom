

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
                buildDate:null,
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
            const url = $.stringFormat('{0}/shop/getShopListNoTree',$.cookie('url'));
            axios.get(url)
            .then(function (res) {
                let jsonData = eval(res);
                if(jsonData['data']['code']==='200'){
                    that.shopList = jsonData['data']['data'];
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
            const url = $.stringFormat('{0}/frCardType/getCardTypeListByCode',$.cookie('url'));
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
            const url = $.stringFormat('{0}/frCardType/getCardNameListByCode',$.cookie('url'));
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
            const url = $.stringFormat('{0}/personnelInfo/getSalespersonList',$.cookie('url'));
            axios.get(url)
                .then(function (res) {
                    let jsonData = eval(res);
                    if(jsonData['data']['code']==='200'){
                        that.salespersonList = jsonData['data']['data'];
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
            const url = $.stringFormat('{0}/frClientSource/getListByIsAuto?isAuto=0',$.cookie('url'));
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
        /**
         * 上传图片
         */
        uploadAvatar:function () {
            const that = this;
            const url = $.stringFormat('{0}/file/upload',$.cookie('url'));
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
                that.imgUrl = $.stringFormat('{0}{1}{2}',$.cookie('url'),$.cookie('imgPath'),jsonData['data']['data']['imgUrl']);
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
            const url = $.stringFormat('{0}/frClient/postAddPotentialCustomer',$.cookie('url'));
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



