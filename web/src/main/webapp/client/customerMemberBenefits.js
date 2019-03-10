
var customerMemberBenefits = new Vue({
    el: '#customerMemberBenefits',
	data:{
        clientId:'',           //会员ID
        code:'',               //客户代码
        shopId:'',             //选中的会员卡所在店铺ID
		cardNum:0,             //会员卡数量
        projectNum:0,          //购买的产品数量
        priceList:[],          //获取图片列表
        userPasswd:'',         //设置通用使用密码
        note:'',                //使用限定备注
        limitList:[],          //设置的使用限定
        deleteId:'',           //存放要删除的通用限定的ID
	},
    methods:{
        init: function (t) {
            var that = this;
            var id = document.URL.split("=")[1];
            if(!id){
                id = $.cookie("cid");
            }
            that.clientId = id;
            that.shopId = $.cookie("shopid");  //获取当前操作门店的ID
            that.code = $.cookie("code");  //获取当前操作门店的ID
            that.getInfoDatail();
            that.getLimtClient();
        },
        //判断值封装
        getParemtDate: function (obj, def) {
            var par = obj;
            if (!par || par == '' || 'undefined' == typeof(par)) {
                par = def;
            }
            return par;
        },
        //获取图片及部分统计数据
        getInfoDatail:function(){
            var that = this;
            if (!that.clientId || !that.shopId || !that.code) {
                return ;
            }
            var url = $.stringFormat("{0}/frClientPic/getClietnPriceList", 'http://www.4006337366.com:8080/');
            $.get(url, {
                CustomerCode: that.code,
                clientId: that.clientId,
            }, function (res) {
                if (res.code == '200') {
                    var imgList = res.data.pricList;
                    that.priceList = [];
                    for(var i = 0 ;i < 5;i++){
                        var picLink = '../img/u2871.png';
                        if(imgList){
                            if(imgList[i]){
                                picLink = that.getParemtDate(imgList[i].picLink,'../img/u2871.png');
                            }
                        }
                        var imgs = {
                            picLink: picLink,
                        }
                        that.priceList.push(imgs);
                    }
                    that.cardNum = that.getParemtDate(res.data.cardAll, 0);
                } else {
                    $.alert(res.msg)
                }
            });
        },
        getLimtClient:function(){
            var that = this;
            if (!that.clientId || !that.shopId || !that.code) {
                return ;
            }
            that.limitList = [];
            var url = $.stringFormat('{0}/frCardLimit/getLimtTypeOne', 'http://www.4006337366.com:8080/');
            $.get(url, {
                CustomerCode: that.code,
                clientId: that.clientId,
            }, function (res) {
                if (res.code == '200') {
                    var limitList = res.data;
                    if(limitList){
                        that.limitList.push(limitList);
                    }
                } else {
                    $.alert(res.msg)
                }
            });
        },
        toAddLimtTypeOne:function(){
            var that = this;
            if (!that.userPasswd) {
                return  $.alert("使用密码不能为空");
            }
            if (that.randomNumber) {
                return $.alert("请勿太快重复点击");
            }
            that.commonConfirm("addLimtTypeOne","","是否确认提交此设置？")
        },
        //第一个是确认要调用的方法名称，第二个是取消要调用的方法名称
        commonConfirm:function(methName, methNameTwo,mess){
            var that = this;
            $.confirm({
                title: '确认',
                content: mess,
                icon: 'glyphicon glyphicon-question-sign',
                buttons: {
                    ok: {
                        text: '确认',
                        btnClass: 'btn-primary',
                        action: function() {
                            return that[methName]();
                        }
                    },
                    cancel: {
                        text: '取消',
                        btnClass: 'btn-primary',
                        action: function() {
                            if(methNameTwo){
                                return that[methNameTwo]();
                            }
                        }
                    }
                }
            })
        },
        //添加通用使用密码
        addLimtTypeOne:function () {
            var that = this;
            that.randomNumber = Math.random().toString(36).substr(2);
            var url = $.stringFormat('{0}/frCardLimit/addLimtTypeOne', 'http://www.4006337366.com:8080/');
            $.post(url, {
                    clientId: that.clientId,
                    CustomerCode: that.code,
                    usePasswd: that.userPasswd,
                    note: that.note,
                },
                function (res) {
                    if (res.code == '200') {
                        $.alert("添加成功")
                        that.userPasswd = '';
                        that.note  = '';
                        that.getLimtClient();
                    } else {
                        $.alert(res.msg)
                    }
                    that.randomNumber = '';
                }
            );
        },
        //确认删除通用限定
        toDeleteCardLimit:function(id){
            var that = this;
            if (!id) { return; }
            that.deleteId = id;
            that.commonConfirm("deleteCardLimit","revokeDelete","确认删除此通用限定？");
        },
        //清空要删除的ID
        revokeDelete:function(){
            var that = this;
            that.deleteId = '';
        },
        //删除使用限定
        deleteCardLimit: function () {
            var that = this;
            var id = that.deleteId;
            if (!id) { return; }
            if (that.randomNumber) {
                return $.alert("请勿太快重复点击");
            }
            that.randomNumber = Math.random().toString(36).substr(2);
            var url = $.stringFormat('{0}/frCardLimit/deleteCardLimit', 'http://www.4006337366.com:8080/');
            $.post(url, {
                    id: id,
                    code: that.code,
                    clientId: that.clientId,
                    personnelId: $.cookie("id"),
                },
                function (res) {
                    if (res.code == '200') {
                        $.alert("删除成功");
                        that.getLimtClient();
                    } else {
                        $.alert(res.msg)
                    }
                    that.randomNumber = '';
                }
            )
        },
	}

});