//跟进图片窗口-----弹窗
$(document).on('click','.tableList tbody td:nth-child(4)', function () {
    colseSee();
    var followUpId =  $(this).parent().attr("value");
    if(!followUpId){
        return;
    }
    var url = $.stringFormat('{0}/frEmployeeClientFollow/getPricList',$.cookie('url'));
    $.get(url,{
            followdId: followUpId,
           },
        function(res){
            if(res.code=='200'){
                var  pricList = res.data;
                var imgs = $('.imgWrap img');
                if( pricList && pricList.length >0){
                   for(var i = 0; i < pricList.length; i++){
                       $(imgs[i]).attr("src",pricList[i].followMarkPic);
                   }
                }
             }else {
               alert(res.msg)
             }
            $('.box1').css('display','block');
         }
    );
});
//关闭初始化跟进图片-----弹窗
$(document).on('click','.box1 .close', function () {
    colseSee();
});
function  colseSee() {
    $('.box1').css('display','none');
    var imgs = $('.imgWrap img');
    if( imgs && imgs.length >0){
        for(var i = 0; i < imgs.length; i++){
            $(imgs[i]).attr("src",'../img/u2871.png');
        }
    }
}
//审核--------弹窗
$(document).on('click','table tbody td:nth-child(13)', function () {
    var supperManagmentId = $(this).parent().attr("suId");
    if("-1" == supperManagmentId){
        return;
    }
    var roleInfoId = $(this).parent().attr("roId");
    var pId = $(this).parent().attr("pId");
    var nId = $.cookie("id");
    if(nId == pId){
        alert("抱歉请勿审核自己的数据")
        return;
    }
    var url = $.stringFormat('{0}/roleInfo/getParentIdList',$.cookie('url'));
    $.get(url,{
        roleInfoId:roleInfoId,
        personalId:nId
    },function(res){
        if(res.code=='200'){
            console.log(res);
            var followUpId =  $(this).parent().attr("value");
            var _type = $(this).parent().attr("type");
            if( _type == 1 ){
                customerFollowUpRecord.supperTitle = '修改';
            }
            customerFollowUpRecord.supperManagmentName = $.cookie('name');
            customerFollowUpRecord.follwAdivce.supperManagmentId = $.cookie('id');
            customerFollowUpRecord.follwAdivce.id = followUpId;
            customerFollowUpRecord.follwAdivce.checkTime = getNowTime(true);
            $('.box2').css('display','block');
        }else {
            alert(res.msg)
        }
    })
});
// 审核关闭-------------弹窗
$(document).on('click','.box2 .close', function () {
    closeBox2();
});
$(document).on('click','.box2 .toclose', function () {
    closeBox2();
});
function closeBox2(){
    $('.box2').css('display','none');
    customerFollowUpRecord.supperTitle = '审核';
    customerFollowUpRecord.supperManagmentName='无';
    customerFollowUpRecord.follwAdivce.supperManagmentId='';
    customerFollowUpRecord.follwAdivce.id='';
    customerFollowUpRecord.follwAdivce.handleAdvice='';
};
$(document).on('click','#followKeyword .panel_state_content',function(){
    $("#followKeyword").hide();
    var keyId =  $(this).attr("value");
    var t = $(this).find("span").text();
    customerFollowUpRecord.followContentT = t;
    customerFollowUpRecord.followUpRecordAdd.followContent = keyId;
});

var customerFollowUpRecord = new Vue({
    el:'#customerFollowUpRecord',
    data:{
        followUpRecordList:[],//跟进记录列表
        clientId:'',//客户Id
        supperManagmentName: '无',  //上级主管
        supperTitle: '审核',
        follwAdivce:{
            id: '',
            supperManagmentId: '',
            checkTime:'',//审核时间为当前日期
            handleAdvice:'' //跟进调整
        },
        randomNumber:'', //随机生成
        randomNumberAdd:'',//随机生成
        followTypeList:[
            {"vue":0,"lab":"电话跟进"},
            {"vue":1,"lab":"微信/短信等文字跟进"}
        ], //跟进类型参数
        followUpRecordAdd:{
            createTime: getNowTime(true),
            followType:'',
            followContent:'',
            nextFollowTime:'',
            planVisitTime:'',
            planPurchaseTime:''
        },
        followContentT:'',
        priceList:'',
        imagesList:{},
        imageFiles:[],
        imgNum:1, //第几张图片
        followKeywordList:[] //关键字列表
    },
    filters: {
        formatDate: function (time,type) {
            if(!time){
                return '';
            }
            var _time = timeFormatDate(time,type);
            return _time;
        }
     },
    created: function () {
        var that = this;
        var id = document.URL.split("=")[1];
        if(!id){
            id=$.cookie("cid");
        }
        that.clientId = id;
        that.getFollowUpRecordList(1,10,that.clientId);
    },
    methods: {
        init: function(){

        },
        //获取客户的跟进列表
        getFollowUpRecordList:function (page,limit,id) {
            var that = this;
            var url = $.stringFormat('{0}/frEmployeeClientFollow/getEmployeeClientList',$.cookie('url'));
            Loading.prototype.show();
            if(!limit||!id){
                id = page.id;
                limit = page.rows;
                page = page.page;
            }
            $.get(url,{
                    page:page,
                    clientId:id,
                    rows:limit,
                },
                function (res) {
                    if(res.code=='200'){
                        console.log(res);
                        that.followUpRecordList =res.data.list;
                        Loading.prototype.hide();
                        that.getPageInfo(res,that,page);
                    }else {
                        alert(res.msg)
                    }
                }
            )
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
        //保存审核记录
        toSaveSubmit:function () {
            var that = this;
            if(!that.follwAdivce.id){
                alert("id未获取，请刷新后再次操作")
            }
            if(!that.follwAdivce.supperManagmentId){
                alert("上级主管未获取，请刷新后再次操作")
            }
            if(!that.follwAdivce.checkTime){
                that.follwAdivce.checkTime = getNowTime(true);
            }
            if(that.randomNumber){
                closeBox2();
                return alert("请太快重复点击");
            }
            //生成字符串
            that.randomNumber = Math.random().toString(36).substr(2);
            var data = JSON.stringify(that.follwAdivce);
            var url = $.stringFormat('{0}/frEmployeeClientFollow/toSaveSubmit',$.cookie('url'));
            $.ajax({
                type: 'POST',
                url: url,
                data: data,
                dataType: "json",
                contentType: "application/json;charset=utf-8",
                success: function(res){
                    closeBox2();
                    if(res.code=='200'){
                        alert("审核成功");
                        that.getFollowUpRecordList(1,10,that.clientId);
                    }else {
                        alert(res.msg)
                    }
                    that.randomNumber = '';
                }
            });
        },
        //选择图片弹窗
        pictureSelected:function(num){
            var that = this;
            that.imgNum = num;
            $('#file-fr').click();
        },
        //显示图片
        pictureShow: function (e) {
            var that = this;
            var file = e.srcElement.files.item(0);
            var num = 'price'+that.imgNum;
            // 看支持不支持FileReader
            if (!file || !window.FileReader) return;
            if (/^image/.test(file.type)) {
                // 创建一个reader
                var reader = new FileReader();
                // 将图片将转成 base64 格式
                reader.readAsDataURL(file);
                // 读取成功后的回调
                reader.onloadend = function () {
                    $("#"+num).attr('src',this.result);
                    that.imagesList[num] = file;
                }
            }
        },
        //添加跟进数据
        addAndUpdateLoad: function () {
           var that = this;
           var followUpRecordAdd = that.followUpRecordAdd;
           var imgList = that.imagesList;
            console.log(imgList)
           var leng = Object.keys(imgList);
            console.log(leng)
           var isFlage = true;
           var followOne = followUpRecordAdd.followType;
           var followTypeOne = true;
            if(followOne >= 0){
                followTypeOne = false;
            }
            if(followTypeOne){
                alert("请选择跟进类型")
                return;
            }
           if(leng.length < 4){
               if(followUpRecordAdd.followType == 1){
                   alert("微信/文字跟进请上传4张图片")
                   return;
               }
           }
           if(!followUpRecordAdd.followContent){
               alert("请填写跟进内容")
               return;
           }
           if(!followUpRecordAdd.nextFollowTime){
               alert("下次跟进时间需选择")
               return;
           }
            Loading.prototype.show();
            if(that.randomNumberAdd){
                return alert("请太快重复点击");
            }
            that.randomNumberAdd = Math.random().toString(36).substr(2);
            if(leng.length > 0){
                isFlage = that.toImagesIsFlage(leng,isFlage);
            }
            if(isFlage){
                var param = new FormData();
                for(var i =0;i<that.imageFiles.length;i++){
                    param.append('file', that.imageFiles[i]); //通过append向form对象添加数据
                }
                param.append('childPath', 'avatar/'); //通过append向form对象添加数据
                param.append('createTime',followUpRecordAdd.createTime);
                param.append('followType',followUpRecordAdd.followType);
                param.append('followContent',followUpRecordAdd.followContent);
                param.append('nextFollowTime',followUpRecordAdd.nextFollowTime);
                param.append('planVisitTime',followUpRecordAdd.planVisitTime);
                param.append('planPurchaseTime',followUpRecordAdd.planPurchaseTime);
                param.append('clientId',$.cookie('cid'));
                var url = $.stringFormat('{0}/frEmployeeClientFollow/toAddUpdateLoad',$.cookie('url'));
                $.ajax({
                    type: 'POST',
                    url: url,
                    data: param,
                    dataType: "json",
                    processData: false,
                    contentType: false,
                    cache:false,
                    success: function(res){
                        if(res.code=='200'){
                            that.followUpRecordAdd.followType='';
                            that.followUpRecordAdd.nextFollowTime='';
                            that.followUpRecordAdd.planVisitTime='';
                            that.followUpRecordAdd.planPurchaseTime='';
                            that.followUpRecordAdd.followContent='';
                            that.followContentT = '';
                            for(var i = 0;i<leng.length;i++){
                                $("#"+leng[i]).attr("src",'../img/u2871.png');
                            }
                            alert("添加成功");
                            that.getFollowUpRecordList(1,10,that.clientId);
                        }else {
                            alert(res.msg)
                        }
                        Loading.prototype.hide();
                        that.randomNumberAdd = '';
                    }
                });
            }
        },
        //判断图片大小
        toImagesIsFlage: function (images,isFlage) {
            var that = this;
            if(!images){
                return
            }
             for(var i = 0;i<images.length;i++){
                var iKey = images[i];
                var obj = that.imagesList[iKey];
                if(obj){
                    var file = obj;
                    if (file.size > 5242880) {
                        $.alert("图片大小不得大于5MB");
                        isFlage = false;
                        that.imageFiles =[];
                        break;
                    }
                    that.imageFiles.push(file);
                }
             }
             return isFlage;
        },
        //获取关键字列表
        getFollwKeyLis: function () {
            var that = this;
            var url = $.stringFormat('{0}/frFollowKeyword/getKeywordList',$.cookie('url'));
            $.get(url,
                function (res) {
                    if(res.code=='200'){
                        that.followKeywordList = res.data;
                       $('#followKeyword').show();
                    }else {
                        alert(res.msg)
                    }
                }
            )
        }
    }
});