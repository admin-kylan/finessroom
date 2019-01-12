const ncapp = new Vue({
    el: '#newCustomers',
    data: {
        //新增客户的信息
        customerData: {
            picLink: null,//用户头像url
            shopId:'',//归属门店id
            salespersonId:'',//销售顾问id
            //基础参数
            base:{
                clientName:null,
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
            },
        },
        //门店列表
        shopList:[],
        //头像路径
        imgUrl: null,
        //销售顾问列表
        salespersonList: [],
        //门店及门店下的卡系列、卡种
        storeList:[],
        //默认选择的门店
        firstStoreId:'',
        //选中的门店id
        shopSelect:'',
        //选中的卡系列
        storeSelected:[],
    },
    methods: {
        /**
         * 查询门店列表及包含卡种
         */
        queryStoreList:function(){
            var that = this;
            var url = $.stringFormat("{0}/shop/getFrCardTypeList",$.cookie('url'));
            $.get(url, {
                },
                function(res) {
                    if(res.code == 200) {
                        that.storeList = res.data.data;
                        that.firstStoreId = that.storeList[0].id
                        $("#Not").hide();
                    } else {
                        //alert(res.msg);
                        //$('#tip').text(res.msg);//异常处理
                    }
                }
            );
        },
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
         * 弹出选择图片
         */
        pictureSelected: function () {
            $('#file-fr').click();
        },
        /**
         * 选择完成后显示图片
         * @param e
         */
        pictureShow: function (e) {
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
                    that.imgUrl = this.result;
                }
            }
        },
        /**
         * 上传图片
         */
        uploadAvatar: function () {
            const that = this;
            const url = $.stringFormat('{0}/file/upload', $.cookie('url'));
            let isSuccess = false;
            let f = that.$refs.avatar;
            //判断是否有图片
            if (f.files.length < 1) {
                $.alert("请选择图片");
            }
            let file = f.files[0];
            //判断图片大小是否超过5MB
            if (file.size > 5242880) {
                $.alert("图片大小不得大于5MB");
            }
            Loading.prototype.show();
            let param = new FormData();
            param.append('file', f.files[0]); //通过append向form对象添加数据
            param.append('childPath', 'avatar/'); //通过append向form对象添加数据
            let config = {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            };
            //上传图片
            axios.post(url, param, config)
                .then(response => {
                    let jsonData = eval(response);
                    Loading.prototype.hide();
                    $.alert(jsonData['data']['data']['msg'])
                    that.customerData.picLink = jsonData['data']['data']['imgUrl'];
                    that.imgUrl = $.stringFormat('{0}{1}{2}', $.cookie('url'), $.cookie('imgPath'), jsonData['data']['data']['imgUrl']);
                    if (jsonData['data']['code'] === '200') {
                        return true;
                    }
                })
            return isSuccess;
        },
        /**
         * 查询销售顾问列表
         */
        querySalespersonList: function () {
            console.log($.cookie('url'))
            const that = this;
            const url = $.stringFormat('{0}/personnelInfo/getSalespersonList', $.cookie('url'));
            axios.get(url)
                .then(function (res) {
                    let jsonData = eval(res);
                    if (jsonData['data']['code'] === '200') {
                        that.salespersonList = jsonData['data']['data'];
                    } else {
                        $.alert(jsonData['data']['msg'])
                    }
                })
                .catch(function (error) {
                    $.alert("请求发生错误！")
                    console.log(error);
                });
        },
        shopSelected:function(){
            console.log("lalal")
            var that = this;
                var opt =  $('#shopSelect').val();
                let storeList = that.storeList;
                for(let i = 1;i<storeList.length;i++){
                    if(storeList[i].id == opt){
                        that.storeSelected = storeList[i].frCardTypeList
                        break;
                    }
                }
        },
        /**
         * 保存
         */
        saveCustomer:function () {
            const that = this;
            console.log(that.customerData)
            const url = $.stringFormat('{0}/frClient/postAddCustomer',$.cookie('url'));
            //组装数据
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
            if(that.customerData.salespersonId==null){
                $.alert('请选择销售顾问');
                return;
            }

            // let reg = /^\s*data:([a-z]+\/[a-z0-9-+.]+(;[a-z-]+=[a-z0-9-]+)?)?(;base64)?,([a-z0-9!$&',()*+;=\-._~:@\/?%\s]*?)\s*$/i;
            // if(reg.test(that.imgUrl)){
            //     //选择了图片但是没有上传
            //     if(that.uploadAvatar()){
            //
            //     }
            // }
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
                                window.location.href="existingCustomer.html";
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
        //手机号码校验
        checkPhone:function (phone) {

            console.log(phone);
        },

    }
})

ncapp.querySalespersonList();
ncapp.queryShopList();
ncapp.queryStoreList();