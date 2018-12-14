var customerPhysicalFitnessTest = new Vue({
    el: '#customerPhysicalFitnessTest',
    data: {
        PhysicalInfoOne: {},
        PhysicalInfoTwo: {},
        PersonnelInfo: {},
        SaveDateInfo: {},
        imagesList: {},
        imageFiles: [],
        imgNum: null,
        delImg: [],

    },
    crated: function () {
    },
    methods: {
        init: function (t) {
            const that = this;
            var clientName = $.cookie("clientName")
            $("#title").html(clientName + "的体侧数据")
            that.getSaveDate();
            $("#add").click(function () {
                that.getData(null)
            })

        },
        mounted: function () {


        },
        getData: function (date) {
            const that = this;
            that.getPersonnel();
            for (var i = 0; i < 3; i++) {
                var num = 'test'+(i+1);
                $("#" + num).attr('src', '../img/addImg.png')
            }
            if (date != null) {
                that.getPhysical(date);

            } else {
                var physicalInfoOne = that.PhysicalInfoOne;
                var physicalInfoTwo = that.PhysicalInfoTwo;

                for (let key in physicalInfoOne) {
                    physicalInfoOne[key] = ''
                }
                for (let key in physicalInfoTwo) {
                    physicalInfoTwo[key] = ''
                }
                document.getElementById("pass").value = '';
            }

        },
        //获得日期
        getSaveDate: function () {
            var cid = $.cookie("cid");
            const that = this;
            const url = $.stringFormat('{0}/frClientPhysicalTest/getSaveDate', $.cookie('url'));
            $.get(url, {"cid": cid}, function (res) {
                that.SaveDateInfo = res.data;
                console.log(res.data)
                $.each(res.data, function (i, n) {
                    n.saveDate = timeFormatDate(n.saveDate, true, true);
                })
            })

        },
        getPic: function () {
            var that = this;
            var url = $.stringFormat('{0}/frClientPhysicalTestPic/getPic', $.cookie('url'));
            $.get(url, {"tid": that.PhysicalInfoOne.id}, function (res) {
                if (res.data.length != 0) {
                    for (var i = 0; i < res.data.length; i++) {
                        console.log(res.data[i].picLink)
                        var num = 'test' + (i + 1);
                        $("#" + num).attr('src', res.data[i].picLink)
                        $("#" + num).attr('value', res.data[i].id)
                    }
                }
            })

        },
        //查询体能测试
        getPhysical: function (date) {
            const that = this;
            const url = $.stringFormat('{0}/frClientPhysicalTest/getPhysical', $.cookie('url'));
            var time = getNowTime(true);
            var param = new FormData();
            var cid = $.cookie("cid");
            param.append("date", date);
            param.append("cid", cid)
            $.ajax({
                type: 'POST',
                url: url,
                data: param,
                dataType: "json",
                processData: false,
                contentType: false,
                cache: false,
                success: function (res) {
                    if (res.code == '200') {
                        that.PhysicalInfoOne = res.data;
                        var PhysicalInfoOne = that.PhysicalInfoOne
                        $.each(res.data, function (i, n) {
                            if (PhysicalInfoOne[i].result == false) {
                                that.PhysicalInfoOne = n;
                                that.PhysicalInfoOne.buildDate = timeFormatDate(that.PhysicalInfoOne.buildDate, true, true);
                                that.PhysicalInfoOne.evaluationDate = timeFormatDate(that.PhysicalInfoOne.evaluationDate, true, true);
                            } else {
                                that.PhysicalInfoTwo = n;
                            }
                        })
                       that.getPic();
                        console.log(that.PhysicalInfoOne)
                    } else {

                    }
                }
            })
        },
        //保存体能测试
        savePhysical: function () {
            const that = this;
            var url = $.stringFormat('{0}/personnelInfo/getVerification', $.cookie('url'));
            var pass = $("#pass").val();
            if (pass == null || pass == '') {
                alert("请填写会员确认")
                return;
            }
            if (jQuery("#select1  option:selected").val() == null || jQuery("#select1  option:selected").val() == '') {
                alert("请选择服务教练")
                return;
            }
            var data = {
                PassWord: pass,
                id: $.cookie("id"),
                customerCode: $.cookie("code"),
                ShopId: $.cookie("shopid"),
            }
            $.ajax({
                type: 'POST',
                url: url,
                data: JSON.stringify(data),
                dataType: "json",
                contentType: "application/json;charset=utf-8",
                success: function (res) {
                    if (res.code == '200') {
                        const url = $.stringFormat('{0}/frClientPhysicalTest/savePhysical', $.cookie('url'));
                        var PhysicalInfo = [];
                        that.PhysicalInfoOne.personalId = jQuery("#select1  option:selected").val();
                        that.PhysicalInfoTwo.personalId = jQuery("#select1  option:selected").val();
                        that.PhysicalInfoOne.clientId = $.cookie("cid");
                        that.PhysicalInfoTwo.clientId = $.cookie("cid");
                        that.PhysicalInfoOne.result = false;
                        that.PhysicalInfoTwo.result = true;
                        console.log(that.PhysicalInfoOne.personalId)
                        var pass = $("#pass").val();
                        console.log(pass)
                        PhysicalInfo.push(that.PhysicalInfoOne)
                        PhysicalInfo.push(that.PhysicalInfoTwo)
                        $.ajax({
                            type: 'POST',
                            url: url,
                            data: JSON.stringify(PhysicalInfo),
                            dataType: "json",
                            contentType: "application/json;charset=utf-8",
                            success: function (res) {
                                console.log(res.data)
                                if (res.code == '200') {
                                    alert("保存成功")
                                    var imgList = that.imagesList;
                                    console.log(imgList)
                                    var leng = Object.keys(imgList);
                                    console.log(leng)
                                    var isFlage = true;
                                    if (leng.length > 0) {
                                        isFlage = that.toImagesIsFlage(leng, isFlage);
                                    }
                                    const imgUrl = $.stringFormat('{0}/frClientPhysicalTestPic/savePic', $.cookie('url'));
                                    var param = new FormData();
                                    console.log(that.imageFiles)
                                    for (var i = 0; i < that.imageFiles.length; i++) {
                                        param.append('file', that.imageFiles[i]); //通过append向form对象添加数据

                                    }
                                    var cid = $.cookie("cid");
                                    param.append('childPath', 'avatar/'); //通过append向form对象添加数据
                                    param.append('tid', res.data);
                                    param.append('cid', cid);
                                    $.ajax({
                                        type: 'POST',
                                        url: imgUrl,
                                        data: param,
                                        dataType: "json",
                                        processData: false,
                                        contentType: false,
                                        cache: false
                                    })
                                    console.log(that.delImg)
                                    const delImgUrl = $.stringFormat('{0}/frClientPhysicalTestPic/delPic', $.cookie('url'));
                                    var list = JSON.stringify(that.delImg).replace("[", "").replace("]", "").replace(/\"/g, "");
                                    if (that.delImg != '' || that.delImg != null || that.delImg != []) {
                                        $.get(delImgUrl, {"pids": list}, function (res) {

                                        })
                                    }
                                } else {
                                    alert(res.msg)
                                }
                            }
                        })


                    } else {
                        alert("会员确认验证失败")
                    }
                }
            })
        },
        //获得所有角色为教练的人员
        getPersonnel: function () {
            const that = this;
            const url = $.stringFormat('{0}/personlRole/getCoach', $.cookie('url'));
            $.get(url, function (res) {
                that.PersonnelInfo = res.data;
                console.log(that.PersonnelInfo)

            })

        },
        //选择图片弹窗
        pictureSelected: function (num) {
            var that = this;
            that.imgNum = num;
            $('.ImgModal-wrap').show();
            // console.log(num);
        },
        // 点击上传图片
        picModalFile: function (){
            $('#file-fr').click();
            console.log('click');
        },
        closeImgModal: function(){
            $('.ImgModal-wrap').hide();
        },
        //显示图片
        pictureShow: function (e) {
            var that = this;
            console.log(111)
            var file = e.srcElement.files.item(0);
            var num = that.imgNum;
            if (typeof $("#" + num).attr("value")) {
                that.delImg.push($("#" + num).attr("value"));
                console.log($("#" + num).attr("value"))
            }

            // 看支持不支持FileReader
            if (!file || !window.FileReader) return;
            if (/^image/.test(file.type)) {
                // 创建一个reader
                var reader = new FileReader();
                // 将图片将转成 base64 格式
                reader.readAsDataURL(file);
                // 读取成功后的回调
                reader.onloadend = function () {
                    $("#" + num).attr('src', this.result);
                    $("#ImgModal-ImgUrl").attr('src', this.result);
                    that.imagesList[num] = file;
                    console.log(that.imagesList)
                }
            }
        },
        //判断图片大小
        toImagesIsFlage: function (images, isFlage) {
            var that = this;
            if (!images) {
                return
            }
            var num = that.imgNum;
            for (var i = 0; i < images.length; i++) {
                var iKey = images[i];
                var obj = that.imagesList[iKey];
                if (obj) {
                    var file = obj;
                    if (file.size > 5242880) {
                        $.alert("图片大小不得大于5MB");
                        isFlage = false;
                        ary.length = 0
                        break;
                    }
                    that.imageFiles.push(file);
                }
            }
            return isFlage;
        },
        //图片下载
        picDownload:function () {
            var that = this;
          var alink=document.createElement("a")
            var num=that.imgNum;
          console.log(that.imgNum)
            alink.href=$("#"+num).attr("src");
            console.log($("#"+num).attr("src"))
            alink.download='img';
            alink.click();
        },
    }
});
$('body').on('click','img.img-responsive',function () {
    $("#ImgModal-ImgUrl").attr("src",$(this).attr('src'));
})
