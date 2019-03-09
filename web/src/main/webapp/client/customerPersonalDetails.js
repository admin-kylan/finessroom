//修复高度
function mainheight() {
    var mainheight = parseInt($('#customerPersonalDetails').height());
    $('#customerPersonalDetails .left').height(mainheight);
}

var customerPersonalDetails = new Vue({
    el: '#customerPersonalDetails',
    data: {
        LiIndex: 0,
        updatename: null,
        updatephone: null,
        upnameid: null,
        upphoneid: null,
        upnum: 0,
        addfamilyunm: 0,
        ClientInfo: {},
        UserLifeInfo: {},
        UserLifeContent: {},
        UserSource: {},
        SportInfo: {},
        Sportchecked: {},
        WorkInfo: {},
        Workchecked: {},
        MentalityInfo: {},
        Mentalitychecked: {},
        PsychologyInfo: {},
        psychologychecked: {},
        FamilyInfo: {},
        SkinInfo: {},
        Skinchecked: {},
        HairInfo: {},
        Hairchecked: {},
        PlasticityInfo: {},
        Plasticitychecked: {},
        time: {},
        imagesList: {},
        imageFiles: [],
        skinimg_leng: 0,
        imgNum: 1, //第几张图片
        type: 0,
        delImg: [],
        date: '',
        afterImg: [],
        imgSRC1: '',
        imgInfo1: [],
        imgInfo2: [],
        dateNow:"",
    },
    computed: {},
    watch: {},
    methods: {
        //会员卡子页面切换
        LI: function (t) {
            const that = this;
            this.LiIndex = t;
            $('#customerPersonalDetails .content .left li').eq(t).addClass('on').siblings().removeClass('on');
            if (t == 0) {
                that.getUserInfo();
            } else if (t == 1) {
                that.getDetails();
            } else if (t == 2) {
                that.getSports();
            } else if (t == 3) {
                that.getWorks();
            } else if (t == 4) {
                that.getMentality();
            } else if (t == 5) {
                that.getFamily();
            } else if (t == 6) {
                that.getPsychology();
            } else if (t == 7) {
                console.log(t)
                that.type = 1;
                that.Skinchecked.length = 0;
                that.getSaveDate();
                $(":checkbox").attr('checked', false)
                $("textarea").text('');
                $("p[name='pdate']").html("");

            } else if (t == 8) {
                that.type = 2;
                that.Hairchecked.length = 0;
                that.getSaveDate();
                $(":checkbox").attr('checked', false)
                $("textarea").text('');
                $("p[name='pdate']").html("");
            } else if (t == 9) {
                that.Plasticitychecked.length = 0;
                that.type = 3;
                that.getSaveDate();
                $(":checkbox").attr('checked', false)
                $("textarea").text('');
                $("p[name='pdate']").html("");
            }
            mainheight();
        },
        //新增日期
        addDate: function () {
            const that = this;
            for (var i = 0; i < 5; i++) {
                var num = that.imgNum;
                $("#" + num).attr('src', '../img/addImg.png')
            }
            that.date=formatDate(new Date());
            $(":checkbox").attr('checked', false)
            $("textarea").text('');
            $("p[name='pdate']").html("");
            that.Skinchecked.length = 0;
            that.Hairchecked.length = 0;
            that.Plasticitychecked.length = 0;
            $.each(that.time, function (i, n) {
                if (n.saveDate == formatDate(new Date())) {
                    alert("当天只能新增一次")
                }
            })
        },

        //获取用户
        getUserInfo: function () {
            const that = this;
            const url = $.stringFormat('{0}/frPersonalDetails/getPersonalDetails', $.cookie('url'));
            var clientId = $.cookie("cid")//取出值
            $.get(url, {"clientId": clientId}, function (res) {
                that.ClientInfo = res.data;
                console.log(that.ClientInfo.coachs)
            })

        },
        //获取生活详情分类
        getDetails: function () {
            const that = this;
            var cid = $.cookie("cid");
            const url2 = $.stringFormat('{0}/frClientLifeRelate/getDetails', $.cookie('url'));
            $.get(url2,{"clientId":cid}, function (res) {
                that.UserLifeContent = res.data;
            });
            const url1 = $.stringFormat('{0}/frClientLifeType/getDetails', $.cookie('url'));
            $.get(url1, function (res) {
                that.UserLifeInfo = res.data;
            });
            var interval = setInterval(function () {
                if (that.UserLifeInfo != null) {
                    clearInterval(interval);
                }
                var userinfo = that.UserLifeInfo
                var userlife = that.UserLifeContent
                for (i in userinfo) {
                    for (j in userlife) {
                        if (userinfo[i].id == userlife[j].lifeTypeId) {
                            $('#' + userlife[j].lifeTypeId).attr('checked', true)
                        }
                    }
                }
            }, 200)

            const url3 = $.stringFormat('{0}/frClientSource/getSource', $.cookie('url'));
            $.get(url3, {"cid": cid}, function (res) {
                that.UserSource = res.data.frClientSources;
                setTimeout(function () {
                    $('#' + res.data.sourceId).attr('checked', true)
                    if (res.data.customerSource != null && res.data.customerSource != '') {
                        var arr = res.data.customerSource.split(':');

                        $("input[name=" + res.data.sourceId + "]").val(arr[1])
                    }

                }, 200)

            });
        },
        //获取运动档案
        getSports: function () {
            const that = this;
            var cid = $.cookie("cid");
            const url = $.stringFormat('{0}/frClientMotionType/getSports', $.cookie('url'));
            $.get(url, function (res) {
                that.SportInfo = res.data;
            })
            const ur2 = $.stringFormat('{0}/frClientMotionRelate/getSports', $.cookie('url'));
            $.get(ur2,{"clientId":cid}, function (res) {
                that.Sportchecked = res.data;
            })
            var interval = setInterval(function () {
                if (that.SportInfo != null) {
                    clearInterval(interval);
                }
                var sportInfo = that.SportInfo
                var sportchecked = that.Sportchecked
                for (i in sportInfo) {
                    for (j in sportchecked) {
                        if (sportInfo[i].id == sportchecked[j].motionTypeId) {
                            $('#' + sportchecked[j].motionTypeId).attr('checked', true)
                        }
                        if (sportchecked[j].type == 9) {
                            $('#sportType').html(sportchecked[j].motionTypeContent)
                        }
                    }
                }
            }, 200)

        },
        //获得工作行业
        getWorks: function () {
            const that = this;
            var cid = $.cookie("cid");
            const url = $.stringFormat('{0}/frClientWorkType/getWorks', $.cookie('url'));
            $.get(url, function (res) {
                that.WorkInfo = res.data;
            })
            const ur2 = $.stringFormat('{0}/frClientWorkRelate/getWorks', $.cookie('url'));
            $.get(ur2,{"clientId":cid}, function (res) {
                that.Workchecked = res.data;
            })
            var interval = setInterval(function () {
                if (that.WorkInfo != null) {
                    clearInterval(interval);
                }
                var workInfo = that.WorkInfo
                var workchecked = that.Workchecked
                for (i in workInfo) {
                    for (j in workchecked) {
                        if (workInfo[i].id == workchecked[j].workTypeId) {
                            $('#' + workchecked[j].workTypeId).attr('checked', true)
                        }
                        if (workchecked[j].type == 3) {
                            $('#workSituation').html(workchecked[j].workTypeContent)
                        }
                        if (workchecked[j].type == 4) {
                            $('#workHistory').html(workchecked[j].workTypeContent)
                        }
                    }
                }
            }, 200)
        },
        //获得心理状况
        getMentality: function () {
            const that = this;
            var cid = $.cookie("cid");
            const url = $.stringFormat('{0}/frClientMentalityType/getMentality', $.cookie('url'));
            $.get(url, function (res) {

                that.MentalityInfo = res.data;
            })
            const ur2 = $.stringFormat('{0}/frClientMentalityRelate/getMentality', $.cookie('url'));
            $.get(ur2,{"clientId":cid},function (res) {
                that.Mentalitychecked = res.data;
            })

            var interval = setInterval(function () {
                if (that.MentalityInfo != null) {
                    clearInterval(interval);
                }
                var mentalityInfo = that.MentalityInfo
                var mentalitychecked = that.Mentalitychecked
                for (i in mentalityInfo) {
                    for (j in mentalitychecked) {
                        if (mentalityInfo[i].id == mentalitychecked[j].mentalityTypeId) {
                            $('#' + mentalitychecked[j].mentalityTypeId).attr('checked', true)
                        }
                        if (mentalitychecked[j].type == 1) {
                            $('#familyLife').html(mentalitychecked[j].mentalityTypeContent)
                        }
                        if (mentalitychecked[j].type == 4) {
                            $('#emotionalState').html(mentalitychecked[j].mentalityTypeContent)
                        }
                    }
                }
            }, 200)
        },
        //获得家庭关系
        getFamily: function () {
            var that = this;
            var cid = $.cookie("cid");
            var url = $.stringFormat('{0}/frClientFamily/getFamily', $.cookie('url'));
            $.get(url, {"clientId": cid}, function (res) {
                that.FamilyInfo = res.data;
            })
        },
        //获得日期
        getSaveDate: function () {
            var that = this;
            var url = $.stringFormat('{0}/frClientArchives/getArchives', $.cookie('url'));
            var cid = $.cookie("cid");
            var type = that.type;
            $.post(url, {"cid": cid, "type": type}, function (res) {
                that.time = res.data;
                $.each(res.data, function (i, n) {
                    n.saveDate = timeFormatDate(n.saveDate);
                })
            })

            var typeUrl = $.stringFormat('{0}/frClientArchivesType/getTypeAll', $.cookie('url'));
            $.get(typeUrl, {"type": type}, function (res) {
                that.date = formatDate(new Date());
                if (type == 1) {
                    that.SkinInfo = res.data;
                } else if (type == 2) {
                    that.HairInfo = res.data;
                } else if (type == 3) {
                    that.PlasticityInfo = res.data;
                }

            })

        },
        //点击日期
        getContent: function (date) {
            var that = this;
            for (var i = 0; i < 5; i++) {
                var num = 'price' + (i + 1);
                $("#" + num).attr('src', '../img/addImg.png')
            }

            var type = that.type;
            that.date = date;
            $("p[name='pdate']").html("日期:  " + date);
            if (date != null) {
                if (type == 1) {
                    that.getSkin(date);

                } else if (type == 2) {
                    that.getHair(date);

                } else if (type == 3) {
                    that.getPlasticity(date);

                }
            }
        },
        //获得皮肤档案
        getSkin: function (date) {
            const that = this;
            that.Skinchecked = {};
            $(":checkbox").attr('checked', false)
            var type = that.type;
            var cid = $.cookie("cid");
            that.dateNow=date;
            const url = $.stringFormat('{0}/frClientArchivesRelate/getRelate', $.cookie('url'));
            $.post(url, {"type": type, "date": date, "cid": cid}, function (res) {
                that.Skinchecked = res.data.frClientArchivesRelates;
                console.log(res.data.frClientArchivesRelatePics)
                if (res.data.frClientArchivesRelatePics.length != 0) {
                    // that.skinimg_leng = res.data.frClientArchivesRelatePics.length
                    // that.used_skinimg = res.data.frClientArchivesRelatePics
                    for (var i = 0; i < res.data.frClientArchivesRelatePics.length; i++) {
                        // $('.skin_img .delete_img').eq(i).attr('display', 'block')
                        console.log(res.data.frClientArchivesRelatePics[i].picLink)
                        var num = 'price' + (i + 1);
                        $("#" + num).attr('src', res.data.frClientArchivesRelatePics[i].picLink)
                        $("#" + num).attr('value', res.data.frClientArchivesRelatePics[i].id)
                    }
                }
            })

            var interval = setInterval(function () {
                if (that.SkinInfo != null) {
                    clearInterval(interval);
                }
                var skinInfo = that.SkinInfo
                var skinchecked = that.Skinchecked
                for (i in skinInfo) {
                    for (j in skinchecked) {
                        if (skinInfo[i].id == skinchecked[j].archivesTypeId) {
                            $('#' + skinchecked[j].archivesTypeId).attr('checked', true)
                        }
                        if (skinchecked[j].type == 21) {
                            $('#otherReasons').html(skinchecked[j].archivesTypeContent)
                        }
                        if (skinchecked[j].type == 22) {
                            $('#skinCare').html(skinchecked[j].archivesTypeContent)
                        }
                        if (skinchecked[j].type == 23) {
                            $('#skinCareProducts').html(skinchecked[j].archivesTypeContent)
                        }
                        if (skinchecked[j].type == 24) {
                            $('#otherInstructions').html(skinchecked[j].archivesTypeContent)
                        }
                        if (skinchecked[j].type == 25) {
                            $('#improveProblem').html(skinchecked[j].archivesTypeContent)
                        }
                        if (skinchecked[j].type == 26) {
                            $('#proposal').html(skinchecked[j].archivesTypeContent)
                        }
                        if (skinchecked[j].type == 27) {
                            $('#nursingAdvice').html(skinchecked[j].archivesTypeContent)
                        }
                        if (skinchecked[j].type == 28) {
                            $('#productProposal').html(skinchecked[j].archivesTypeContent)
                        }
                    }
                }
            }, 200)
        },
        //获得头发档案
        getHair: function (date) {
            const that = this;
            that.Hairchecked = {};
            $(":checkbox").attr('checked', false)
            var type = that.type;
            var cid = $.cookie("cid");
            that.dateNow=date;
            const url = $.stringFormat('{0}/frClientArchivesRelate/getRelate', $.cookie('url'));
            $.post(url, {"type": type, "date": date, "cid": cid}, function (res) {
                that.Hairchecked = res.data.frClientArchivesRelates;
                if (res.data.frClientArchivesRelatePics.length != 0) {
                    // that.skinimg_leng = res.data.frClientArchivesRelatePics.length
                    // that.used_skinimg = res.data.frClientArchivesRelatePics
                    for (var i = 0; i < res.data.frClientArchivesRelatePics.length; i++) {
                        // $('.hair_img .delete_img').eq(i).attr('display', 'block')
                        console.log(res.data.frClientArchivesRelatePics[i].picLink)
                        var num = 'hair' + (i + 1);
                        $("#" + num).attr('src', res.data.frClientArchivesRelatePics[i].picLink)
                        $("#" + num).attr('value', res.data.frClientArchivesRelatePics[i].id)
                    }
                }
            })

            var interval = setInterval(function () {
                if (that.HairInfo != null) {
                    clearInterval(interval);
                }
                var hairInfo = that.HairInfo
                var hairchecked = that.Hairchecked
                for (i in hairInfo) {
                    for (j in hairchecked) {
                        if (hairInfo[i].id == hairchecked[j].archivesTypeId) {
                            $('#' + hairchecked[j].archivesTypeId).attr('checked', true)
                        }
                        if (hairchecked[j].type == 11) {
                            $('#otherInstructions2').html(hairchecked[j].archivesTypeContent)
                        }
                        if (hairchecked[j].type == 12) {
                            $('#effect').html(hairchecked[j].archivesTypeContent)
                        }
                        if (hairchecked[j].type == 13) {
                            $('#color').html(hairchecked[j].archivesTypeContent)
                        }
                        if (hairchecked[j].type == 14) {
                            $('#hairstyle').html(hairchecked[j].archivesTypeContent)
                        }
                        if (hairchecked[j].type == 15) {
                            $('#proposal2').html(hairchecked[j].archivesTypeContent)
                        }
                        if (hairchecked[j].type == 16) {
                            $('#nursingAdvice2').html(hairchecked[j].archivesTypeContent)
                        }
                        if (hairchecked[j].type == 17) {
                            $('#productCollocation').html(hairchecked[j].archivesTypeContent)
                        }
                    }
                }
            }, 200)

        },
        //获得塑性抗衰
        getPlasticity: function (date) {
            const that = this;
            that.Plasticitychecked = {};
            $(":checkbox").attr('checked', false)
            var type = that.type;
            var cid = $.cookie("cid");
            that.dateNow=date;
            const url2 = $.stringFormat('{0}/frClientArchivesRelate/getRelate', $.cookie('url'));
            $.post(url2, {"type": type, "date": date, "cid": cid}, function (res) {
                that.Plasticitychecked = res.data.frClientArchivesRelates;
                if (res.data.frClientArchivesRelatePics.length != 0) {
                    for (var i = 0; i < res.data.frClientArchivesRelatePics.length; i++) {
                        console.log(res.data.frClientArchivesRelatePics[i].picLink)
                        if (res.data.frClientArchivesRelatePics[i].picType == 2) {
                            that.imgInfo2.push(res.data.frClientArchivesRelatePics[i])
                        } else if (res.data.frClientArchivesRelatePics[i].picType == 1) {
                            that.imgInfo1.push(res.data.frClientArchivesRelatePics[i])

                        }

                    }
                }
                console.log(that.imgInfo1)
                console.log(that.imgInfo2)
                for (var i = 0; i < that.imgInfo1.length; i++) {
                    var num = 'plasticity' + (i + 1);
                    $("#" + num).attr('src', that.imgInfo1[i].picLink)
                    $("#" + num).attr('value', that.imgInfo1[i].id)
                }
                for (var i = 0; i < that.imgInfo2.length; i++) {
                    var num = 'plasticity' + (i + 7);
                    $("#" + num).attr('src', that.imgInfo2[i].picLink)
                    $("#" + num).attr('value', that.imgInfo2[i].id)
                }

            })
            var interval = setInterval(function () {
                if (that.PlasticityInfo != null) {
                    clearInterval(interval);
                }
                var plasticityInfo = that.PlasticityInfo
                var plasticitychecked = that.Plasticitychecked
                for (i in plasticityInfo) {
                    for (j in plasticitychecked) {
                        if (plasticityInfo[i].id == plasticitychecked[j].archivesTypeId) {
                            $('#' + plasticitychecked[j].archivesTypeId).attr('checked', true)
                        }
                        if (plasticitychecked[j].type == 9) {
                            $('#hopeResult').html(plasticitychecked[j].archivesTypeContent)
                        }
                        if (plasticitychecked[j].type == 10) {
                            $('#useProduct').html(plasticitychecked[j].archivesTypeContent)
                        }
                        if (plasticitychecked[j].type == 11) {
                            $('#product').html(plasticitychecked[j].archivesTypeContent)
                        }
                        if (plasticitychecked[j].type == 12) {
                            $('#time').html(plasticitychecked[j].archivesTypeContent)
                        }
                        if (plasticitychecked[j].type == 13) {
                            $('#physicalTruth').html(plasticitychecked[j].archivesTypeContent)
                        }
                        if (plasticitychecked[j].type == 14) {
                            $('#taboo').html(plasticitychecked[j].archivesTypeContent)
                        }
                    }
                }
            }, 200)
        },

        //家庭亲子修改
        updateFamily: function (index) {
            var that = this
            var html = '<span class="toRed" onclick="customerPersonalDetails.familyConfirmation(' + index + ')">确认</span>'
            $('.clickupdate').eq(index).html(html)
            for (var i = 0; i <= 6; i++) {
                var text = $('.familytr').eq(index).children('td').eq(i).html()
                var input = '<input style="border:0;text-align:center;" value="' + text + '">'
                $('.familytr').eq(index).children('td').eq(i).html(input)
                if (i == 5) {
                    var select = '<select style="border: 0;">'
                    var option;
                    if (that.FamilyInfo[index].member == true) {
                        select += '<option checked>是</option><option>否</option>'
                    } else {
                        select += '<option checked>否</option><option>是</option>'
                    }
                    select += '</select>'
                    $('.familytr').eq(index).children('td').eq(i).html(select)
                }

            }
        },
        //亲子确定
        familyConfirmation: function (index) {
            var that = this
            var html = '<span class="toGreen" onclick="customerPersonalDetails.updateFamily(' + index + ')">修改</span>' +
                '<span class="dark" onclick="customerPersonalDetails.deleteFamily(' + index + ')">删除</span>';
            $('.clickupdate').eq(index).html(html)
            var sele = $('.familytr').eq(index).children('td').eq(5).children('select').val();
            for (var i = 0; i <= 6; i++) {
                var text = $('.familytr').eq(index).children('td').eq(i).children('input').val()
                switch (i) {
                    case i = 0:
                        that.FamilyInfo[index].relations = text;
                        $('.familytr').eq(index).children('td').eq(i).html(text);
                    case i = 1:
                        that.FamilyInfo[index].name = text;
                        $('.familytr').eq(index).children('td').eq(i).html(text);
                    case i = 2:
                        that.FamilyInfo[index].tel = text;
                        $('.familytr').eq(index).children('td').eq(i).html(text);
                    case i = 3:
                        that.FamilyInfo[index].workUnit = text;
                        $('.familytr').eq(index).children('td').eq(i).html(text);
                    case i = 4:
                        that.FamilyInfo[index].position = text;
                        $('.familytr').eq(index).children('td').eq(i).html(text);
                    case i = 5:
                        if (sele == '是') {
                            that.FamilyInfo[index].member = true
                            $('.familytr').eq(index).children('td').eq(i).html('是')
                        } else {
                            that.FamilyInfo[index].member = false
                            $('.familytr').eq(index).children('td').eq(i).html('否')
                        }
                    case i = 6:
                        that.FamilyInfo[index].homeAdd = text;
                        $('.familytr').eq(index).children('td').eq(i).html(text)
                }
            }
            const url = $.stringFormat('{0}/frClientFamily/updateFamily', $.cookie('url'));
            var family = JSON.stringify(that.FamilyInfo[index])
            var cid = $.cookie("cid");
            $.get(url, {"frClientFamilystr": family, "cid": cid}, function (res) {
            })
        },
        //亲子添加
        addfamily: function (event) {
            var that = this
            if (this.addfamilyunm == 0) {
                that.addfamilyunm = 1
                var el = event.currentTarget;
                var leng = this.FamilyInfo.length
                var html = '<tr class="familytr">'
                for (var i = 0; i <= 5; i++) {
                    html += '<td><input style="border:0;text-align:center;"></td>'
                    if (i == 4) {
                        html += '<td><select style="border: 0;">'
                        html += '<option checked>是</option><option>否</option>'
                        html += '</select></td>'
                    }
                }
                html += '<td><span class="toRed" onclick="customerPersonalDetails.addNew(' + leng + ')">确认</span></td>';
                $(el).parent().parent().before(html);
            } else {
                alert("请先完成上一个事件")
            }
        },
        //亲子确认新增
        addNew: function (index) {
            var that = this;
            var list = {relations: '', name: '', tel: '', workUnit: '', position: '', member: true, homeAdd: ''}
            var listthis = $('.familytr').eq(index)
            var sele = $('.familytr').eq(index).children('td').eq(5).children('select').val()
            for (var i = 0; i <= 6; i++) {
                var text = $('.familytr').eq(index).children('td').eq(i).children('input').val()
                switch (i) {
                    case i = 0:
                        list.relations = text
                    case i = 1:
                        list.name = text
                    case i = 2:
                        list.tel = text
                    case i = 3:
                        list.workUnit = text
                    case i = 4:
                        list.position = text
                    case i = 5:
                        if (sele == '是') {
                            list.member = true
                        } else {
                            list.member = false
                        }
                    case i = 6:
                        list.homeAdd = text
                }
            }
            if (list.homeAdd == "" || list.position == "" || list.workUnit == "" || list.tel == "" || list.name == "" || list.relations == "") {
                alert('填写内容不能为空')
            } else {
                that.FamilyInfo.push(list)
                listthis.remove()
                const url = $.stringFormat('{0}/frClientFamily/updateFamily', $.cookie('url'));
                var family = JSON.stringify(that.FamilyInfo[index])
                that.addfamilyunm = 0
                var cid = $.cookie("cid");
                $.get(url, {"frClientFamilystr": family, "cid": cid}, function (res) {
                })
            }

        },
        //亲子删除
        deleteFamily: function (id, index) {
            var del = confirm('确认删除吗?')
            if (del == true) {
                const url = $.stringFormat('{0}/frClientFamily/delFamily', $.cookie('url'));
                $.get(url, {"id": id}, function (res) {
                    $('.familytr').eq(index).remove()
                    alert(res.msg)
                })
            } else {
                return false
            }
        },
        //获得生理状况
        getPsychology: function () {
            const that = this;
            //获取类型
            var cid = $.cookie("cid");
            const url = $.stringFormat('{0}/frClientPhysiologyType/getPsychology', $.cookie('url'));
            $.get(url, function (res) {
                that.PsychologyInfo = res.data;
            })
            //获取内容
            const ur2 = $.stringFormat('{0}/frClientPhysiologyRelate/getPsychology', $.cookie('url'));
            $.get(ur2,{"clientId":cid}, function (res) {
                that.Psychologychecked = res.data;
            })

            var interval = setInterval(function () {
                if (that.PsychologyInfo != null) {
                    clearInterval(interval);
                }
                var psychologyInfo = that.PsychologyInfo
                var psychologychecked = that.Psychologychecked
                for (i in psychologyInfo) {
                    for (j in psychologychecked) {
                        if (psychologyInfo[i].id == psychologychecked[j].physiologyTypeId) {
                            $('#' + psychologychecked[j].physiologyTypeId).attr('checked', true)
                        }
                        if (psychologychecked[j].type == 5) {
                            $('#medicalHistory').html(psychologychecked[j].physiologyTypeContent)
                        }
                        if (psychologychecked[j].type == 6) {
                            $('#drug').html(psychologychecked[j].physiologyTypeContent)
                        }
                        if (psychologychecked[j].type == 7) {
                            $('#theHormone').html(psychologychecked[j].physiologyTypeContent)
                        }
                        if (psychologychecked[j].type == 8) {
                            $('#bloodPressure').html(psychologychecked[j].physiologyTypeContent)
                        }
                        if (psychologychecked[j].type == 9) {
                            $('#menstruation').html(psychologychecked[j].physiologyTypeContent)
                        }
                    }
                }
            }, 200)
        },
        // 聊天工具新增
        chatAdd: function (event) {
            var el = event.currentTarget;
            var html = '<tr>' +
                '<td><input type="text" value=""></td>' +
                '<td><input type="text" value=""></td>' +
                '<td colspan="2">' +
                '<span class="toRed" onclick="customerPersonalDetails.confirmNew(this)">确认</span>' +
                '</td>' +
                '</tr>';
            $('#chatAdd').attr('rowspan', parseInt($('#chatAdd').attr('rowspan')) + 1);
            $(el).parent().parent().before(html);
            //调整左边栏高度
            mainheight();
        },
        // 聊天工具确认新增
        confirmNew: function (event, index) {
            alert('确认成功');
            var html = '<span class="toGreen">修改</span>' +
                '<span class="dark">删除</span>';
            $(event).parent().html(html);
        },
        // 教练新增
        coachAdded: function (event, index) {
            const that = this;
            index;
            console.log(index)
            const rurl = $.stringFormat('{0}/roleInfo/findAll', $.cookie('url'));
            var el = event.currentTarget;
            $.get(rurl, function (res) {
                var html = '<tr class="' + "tr1" + '">' +
                    '<td class="' + "t1" + '"><select onchange="customerPersonalDetails.RoleChange(this,' + index + ')" name="" id="">'
                $.each(res.data, function (i, n) {
                    html += '<option value=' + n.id + '>' + n.firstName + '</option>'
                })
                html += '</select></td>';

                html += '<td class="' + "t2" + '"></td>' +
                    '<td class="' + "t3" + '"><select name="" id=""><option value="1"></option></select></td>' +
                    '<td >' +
                    '<span class="toRed" onclick="customerPersonalDetails.coachConfirmation(this,' + index + ')">确认</span>' +
                    '</td>' +
                    '</tr>';
                $('#coachAdded').attr('rowspan', parseInt($('#coachAdded').attr('rowspan')) + 1);
                $(el).parent().parent().before(html);
            })

            //调整左边栏高度
            mainheight();
        },
        //根据角色修改角色名称
        RoleChange: function (e, index) {
            const that = this;
            console.log(index)
            const url = $.stringFormat('{0}/roleInfo/getRoleName', $.cookie('url'));
            var firstName = $(e).find("option:selected").text();
            $.get(url, {"firstName": firstName}, function (res) {
                var roleName = JSON.stringify(res.data.firstName);
                var roleid = JSON.stringify(res.data.id);
                roleName = roleName.replace("\"", "").replace("\"", "");
                roleid = roleid.replace("\"", "").replace("\"", "");
                $(".t2").eq(index).html(roleName);
                that.updatename = roleName
                that.upnameid = roleid
            })
            //根据角色修改联系方式
            const url2 = $.stringFormat('{0}/personlRole/getPersonlByRole', $.cookie('url'));
            var rid = $(e).find("option:selected").val();
            $.get(url2, {"rid": rid}, function (res) {
                var html = '<select   name="" id="">';
                //拿到这个id,去匹配查出来的教练
                var dataiId = $('.t1').eq(index).attr('data-id');
                $.each(res.data, function (i, n) {
                    if (n.id == dataiId) {
                        html += '<option  value=' + n.id + ' selected>' + n.mobile + '</option>'
                        that.updatephone = n.mobile
                        that.upphoneid = n.id
                    } else {
                        html += '<option    value=' + n.id + '>' + n.mobile + '</option>'
                        that.updatephone = n.mobile
                        that.upphoneid = n.id
                    }
                })
                html += '</select>';
                $('.t3').eq(index).html(html);
            }, "json")

        },
        //教练修改
        coachUpdate: function (index) {
            const that = this;
            const url = $.stringFormat('{0}/roleInfo/findAll', $.cookie('url'));
            if (that.upnum == 0) {
                that.upnum = 1
                var html = '<span class="toRed" onclick="customerPersonalDetails.coachConfirmation(this,' + index + ')">确认</span>'
                $(".coach").eq(index).html(html);
                $.get(url, function (res) {
                    var html = '<select onchange="customerPersonalDetails.RoleChange(this,' + index + ')"  name="" id="">';
                    //拿到这个id,去匹配查出来的教练
                    var dataiId = $('.t1').eq(index).attr('data-id');
                    $.each(res.data, function (i, n) {

                        if (n.id == dataiId) {
                            html += '<option  value=' + n.id + ' selected>' + n.firstName + '</option>'
                            that.updatename = n.firstName
                            that.upnameid = n.id
                        } else {
                            html += '<option    value=' + n.id + '>' + n.firstName + '</option>'
                        }
                    })
                    html += '</select>';
                    $('.t1').eq(index).html(html);
                }, "json")
            } else {
                alert("已有内容正在修改,请先确定")
            }

        },

        // 教练确认
        coachConfirmation: function (event, index) {
            var that = this;
            console.log(that.ClientInfo.coachs)
            alert('确认成功');
            console.log($(".t1").eq(index).find("option:selected").text())
            that.upnum = 0
            this.updatename = $(".t1").eq(index).find("option:selected").text()
            // that.updatephone=$(".t3").eq(index).find("option:selected").text()
            if (typeof that.ClientInfo.coachs[index]) {
                //新增确认
                var client = new Object();
                client.firstName = this.updatename;
                client.rolRoleName = $(".t2").eq(index).text();
                client.phone = $(".t3").eq(index).find("option:selected").text();
                client.personalId = $(".t3").eq(index).find("option:selected").val();
                client.roleId = $(".t1").eq(index).find("option:selected").val();
                that.ClientInfo.coachs.push(client)
                $('.tr1').eq(index).remove();
            } else {
                //修改确认
                that.ClientInfo.coachs[index].firstName = this.updatename
                that.ClientInfo.coachs[index].rolRoleName = $(".t2").eq(index).text()
                that.ClientInfo.coachs[index].phone = $(".t3").eq(index).find("option:selected").text()
                var html = '<span class="toGreen" onclick="customerPersonalDetails.coachUpdate(' + index + ')">修改</span>' +
                    '<span class="dark">删除</span>';
                $('.t1').eq(index).html(this.updatename);
                $('.t3').eq(index).html($(".t3").eq(index).find("option:selected").text());
                console.log(html)
                $(event).parent().html(html);
                console.log($('.t1'))
            }

        },
        //基础档案保存
        save1: function () {
            const that = this;
            const url = $.stringFormat('{0}/frPersonalDetails/updatePersonalDetails', $.cookie('url'));
            var frClient = JSON.stringify(that.ClientInfo);
            $.ajax({
                type: 'POST',
                url: url,
                data: frClient,
                dataType: "json",
                contentType: "application/json;charset=utf-8",
                success: function (res) {
                    if (res.code == '200') {
                        alert("保存成功");
                    } else {
                        alert(res.msg)
                    }
                }
            });
        },
        //生活详情保存
        saveDetails: function () {
            const that = this;
            const url = $.stringFormat('{0}/frClientLifeRelate/saveDetails', $.cookie('url'));
            var list = [];
            $("input[name='character']:checked").each(function () {
                list.push($(this).val());
            });
            $("input[name='ECP']:checked").each(function () {
                list.push($(this).val());
            });
            $("input[name='FP']:checked").each(function () {
                list.push($(this).val());
            });
            $("input[name='vehicle']:checked").each(function () {
                list.push($(this).val());
            });
            $("input[name='read']:checked").each(function () {
                list.push($(this).val());
            });
            $("input[name='aroma']:checked").each(function () {
                list.push($(this).val());
            });
            $("input[name='shopping']:checked").each(function () {
                list.push($(this).val());
            });
            $("input[name='hobby']:checked").each(function () {
                list.push($(this).val());
            });
            var id = $.cookie("cid");
            list = JSON.stringify(list);
            list = list.replace("[", "").replace("]", "").replace(/\"/g, "");
            var sid = $("input[name='_radio']:checked").attr('id')
            var source = $("input[name='_radio']:checked").val() + ':' + $("input[name=" + sid + "]").val();
            $.get(url, {"frClientLifeTypeIds": list, "cid": id, "source": source, "sid": sid}, function (res) {
                alert(JSON.stringify(res.msg));
            });

        },
        //保存运动档案
        saveSports: function () {
            const that = this;
            const url = $.stringFormat('{0}/frClientMotionRelate/saveSports', $.cookie('url'));
            var list = [];
            $("input[name='shape']:checked").each(function () {
                list.push($(this).val());
            });
            $("input[name='sportsHobbies']:checked").each(function () {
                list.push($(this).val());
            });
            $("input[name='dance']:checked").each(function () {
                list.push($(this).val());
            });
            $("input[name='objective']:checked").each(function () {
                list.push($(this).val());
            });
            $("input[name='position']:checked").each(function () {
                list.push($(this).val());
            });
            $("input[name='know']:checked").each(function () {
                list.push($(this).val());
            });
            $("input[name='history']:checked").each(function () {
                list.push($(this).val());
            });
            $("input[name='time']:checked").each(function () {
                list.push($(this).val());
            });
            //客户ID
            var id = $.cookie("cid");
            list = JSON.stringify(list);
            list = list.replace("[", "").replace("]", "").replace(/\"/g, "");
            //希望运动的类型或项目
            var sportType = $("#sportType").val();
            $.get(url, {"frClientSportTypeIds": list, "cid": id, "sportType": sportType}, function (res) {
                alert(JSON.stringify(res.msg));
            });


        },
        //保存工作行业
        saveWork: function () {
            const that = this;
            const url = $.stringFormat('{0}/frClientWorkRelate/saveWorks', $.cookie('url'));
            var list = [];
            $("input[name='industry']:checked").each(function () {
                list.push($(this).val());
            });
            $("input[name='nature']:checked").each(function () {
                list.push($(this).val());
            });

            //客户ID
            var id = $.cookie("cid");
            list = JSON.stringify(list);
            list = list.replace("[", "").replace("]", "").replace(/\"/g, "");
            //工作情况
            var workSituation = $("#workSituation").val();
            //工作历史
            var workHistory = $("#workHistory").val();
            $.get(url, {
                "frClientWorkTypeIds": list,
                "cid": id,
                "workSituation": workSituation,
                "workHistory": workHistory
            }, function (res) {
                alert(JSON.stringify(res.msg));
            });
        },
        //保存心理状况
        saveMentality: function () {
            const that = this;
            const url = $.stringFormat('{0}/frClientMentalityRelate/saveMentality', $.cookie('url'));
            var list = [];
            $("input[name='sleepHabits']:checked").each(function () {
                list.push($(this).val());
            });
            $("input[name='sleepQuality']:checked").each(function () {
                list.push($(this).val());
            });

            //客户ID
            var id = $.cookie("cid");
            list = JSON.stringify(list);
            list = list.replace("[", "").replace("]", "").replace(/\"/g, "");
            //家庭生活情况
            var familyLife = $("#familyLife").val();
            //情绪状况
            var emotionalState = $("#emotionalState").val();
            $.get(url, {
                "frClientMentalityTypeIds": list,
                "cid": id,
                "familyLife": familyLife,
                "emotionalState": emotionalState
            }, function (res) {
                alert(JSON.stringify(res.msg));
            });
        },
        //保存生理状况
        savePsychology: function () {
            const that = this;
            const url = $.stringFormat('{0}/frClientPhysiologyRelate/savePsychology', $.cookie('url'));
            var list = [];
            $("input[name='medical']:checked").each(function () {
                list.push($(this).val());
            });
            $("input[name='size']:checked").each(function () {
                list.push($(this).val());
            });
            $("input[name='health']:checked").each(function () {
                list.push($(this).val());
            });
            $("input[name='body']:checked").each(function () {
                list.push($(this).val());
            });

            //客户ID
            var id = $.cookie("cid");
            list = JSON.stringify(list);
            list = list.replace("[", "").replace("]", "").replace(/\"/g, "");
            //以往病史
            var medicalHistory = $("#medicalHistory").val();
            //曾服药物
            var drug = $("#drug").val();
            //荷尔蒙情况
            var theHormone = $("#theHormone").val();
            //血压状况
            var bloodPressure = $("#bloodPressure").val();
            //月经状况
            var menstruation = $("#menstruation").val();
            $.get(url, {
                "frClientPsychologyTypeIds": list,
                "cid": id,
                "medicalHistory": medicalHistory,
                "drug": drug,
                "theHormone": theHormone,
                "bloodPressure": bloodPressure,
                "menstruation": menstruation
            }, function (res) {
                alert(JSON.stringify(res.msg));
            });

        },
        //保存皮肤档案
        saveSkin: function (date) {
            const that = this;
            var date = that.date;
            console.log(date)
            const url = $.stringFormat('{0}/frClientArchivesRelate/saveRelate', $.cookie('url'));
            var list = [];
            var imgList = that.imagesList;
            console.log(imgList)
            var leng = Object.keys(imgList);
            console.log(leng)
            var isFlage = true;
            if (leng.length > 0) {
                isFlage = that.toImagesIsFlage(leng, isFlage);
            }
            $("input[name='nursing']:checked").each(function () {
                list.push($(this).val());
            });
            $("input[name='skinType']:checked").each(function () {
                list.push($(this).val());
            });
            $("input[name='skinObservation']:checked").each(function () {
                list.push($(this).val());
            });
            $("input[name='forehead']:checked").each(function () {
                list.push($(this).val());
            });
            $("input[name='eye']:checked").each(function () {
                list.push($(this).val());
            });
            $("input[name='cheek']:checked").each(function () {
                list.push($(this).val());
            });
            $("input[name='nose']:checked").each(function () {
                list.push($(this).val());
            });
            $("input[name='lips']:checked").each(function () {
                list.push($(this).val());
            });
            $("input[name='neck']:checked").each(function () {
                list.push($(this).val());
            });
            $("input[name='chest']:checked").each(function () {
                list.push($(this).val());
            });
            $("input[name='waist']:checked").each(function () {
                list.push($(this).val());
            });
            $("input[name='causeOfPimples']:checked").each(function () {
                list.push($(this).val());
            });
            $("input[name='blackSpot']:checked").each(function () {
                list.push($(this).val());
            });
            $("input[name='causeOfWrinkles']:checked").each(function () {
                list.push($(this).val());
            });
            $("input[name='causeOfAnaphylaxis']:checked").each(function () {
                list.push($(this).val());
            });
            $("input[name='coarsePores']:checked").each(function () {
                list.push($(this).val());
            });
            $("input[name='vitamin']:checked").each(function () {
                list.push($(this).val());
            });
            $("input[name='anaphylaxis']:checked").each(function () {
                list.push($(this).val());
            });
            $("input[name='skinIntention']:checked").each(function () {
                list.push($(this).val());
            });
            $("input[name='physicalIntention']:checked").each(function () {
                list.push($(this).val());
            });
            //客户ID
            var cid = $.cookie("cid");
            list = JSON.stringify(list).replace("[", "").replace("]", "").replace(/\"/g, "");

            var text = {
                "21": $("#otherReasons").val(),
                "22": $("#skinCare").val(),
                "23": $("#skinCareProducts").val(),
                "24": $("#otherInstructions").val(),
                "25": $("#improveProblem").val(),
                "26": $("#proposal").val(),
                "27": $("#nursingAdvice").val(),
                "28": $("#productProposal").val()
            }
            text = JSON.stringify(text)
            $.post(url, {
                "typeIds": list,
                "cid": cid,
                "text": text,
                "type": 1,
                "date": date
            }, function (res) {
                alert(JSON.stringify(res.msg));
            });

            const imgUrl = $.stringFormat('{0}/frClientArchivesRelatePic/savePic', $.cookie('url'));
            var param = new FormData();
            console.log(that.imageFiles)
            for (var i = 0; i < that.imageFiles.length; i++) {
                param.append('file', that.imageFiles[i]); //通过append向form对象添加数据

            }
            param.append('childPath', 'avatar/'); //通过append向form对象添加数据
            param.append('type', 1);
            param.append('date', date);
            param.append('cid', cid);
            param.append('imgType', 1)
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
            const delImgUrl = $.stringFormat('{0}/frClientArchivesRelatePic/delPic', $.cookie('url'));
            var list = JSON.stringify(that.delImg).replace("[", "").replace("]", "").replace(/\"/g, "");
            if (that.delImg != '' || that.delImg != null || that.delImg != []) {
                $.get(delImgUrl, {"pids": list}, function (res) {

                })
            }

        },
        //保存头发档案
        saveHair: function (date) {
            const that = this;

            var date = that.date;
            const url = $.stringFormat('{0}/frClientArchivesRelate/saveRelate', $.cookie('url'));
            var list = [];
            $("input[name='hairColor']:checked").each(function () {
                list.push($(this).val());
            });
            $("input[name='colorReasons']:checked").each(function () {
                list.push($(this).val());
            });
            $("input[name='hairTy']:checked").each(function () {
                list.push($(this).val());
            });
            $("input[name='damage']:checked").each(function () {
                list.push($(this).val());
            });
            $("input[name='perm']:checked").each(function () {
                list.push($(this).val());
            });
            $("input[name='hairCare']:checked").each(function () {
                list.push($(this).val());
            });
            $("input[name='nursing2']:checked").each(function () {
                list.push($(this).val());
            });
            $("input[name='hairType']:checked").each(function () {
                list.push($(this).val());
            });
            $("input[name='shampoo']:checked").each(function () {
                list.push($(this).val());
            });
            //客户ID
            var cid = $.cookie("cid");
            list = JSON.stringify(list).replace("[", "").replace("]", "").replace(/\"/g, "");
            console.log(list)
            var text = {
                "11": $("#otherInstructions2").val(),
                "12": $("#effect").val(),
                "13": $("#color").val(),
                "14": $("#hairstyle").val(),
                "15": $("#proposal2").val(),
                "16": $("#nursingAdvice2").val(),
                "17": $("#productCollocation").val()
            }
            text = JSON.stringify(text)
            $.post(url, {
                "typeIds": list,
                "cid": cid,
                "text": text,
                "type": 2,
                "date": date
            }, function (res) {
                alert(JSON.stringify(res.msg));
            });
            var imgList = that.imagesList;
            console.log(imgList)
            var leng = Object.keys(imgList);
            console.log(leng)
            var isFlage = true;
            if (leng.length > 0) {
                isFlage = that.toImagesIsFlage(leng, isFlage);
            }
            const imgUrl = $.stringFormat('{0}/frClientArchivesRelatePic/savePic', $.cookie('url'));
            var param = new FormData();
            console.log(that.imageFiles)
            for (var i = 0; i < that.imageFiles.length; i++) {
                param.append('file', that.imageFiles[i]); //通过append向form对象添加数据

            }
            param.append('childPath', 'avatar/'); //通过append向form对象添加数据
            param.append('type', 2);
            param.append('date', date);
            param.append('cid', cid);
            param.append('imgType', 1)
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
            const delImgUrl = $.stringFormat('{0}/frClientArchivesRelatePic/delPic', $.cookie('url'));
            var list = JSON.stringify(that.delImg).replace("[", "").replace("]", "").replace(/\"/g, "");
            if (that.delImg != '' || that.delImg != null || that.delImg != []) {
                $.get(delImgUrl, {"pids": list}, function (res) {

                })
            }

        },
        //保存塑性抗衰
        savePlasticity: function () {
            const that = this;
            var date = that.date;
            const url = $.stringFormat('{0}/frClientArchivesRelate/saveRelate', $.cookie('url'));
            var list = [];
            $("input[name='face']:checked").each(function () {
                list.push($(this).val());
            });
            $("input[name='nose']:checked").each(function () {
                list.push($(this).val());
            });
            $("input[name='chest']:checked").each(function () {
                list.push($(this).val());
            });
            $("input[name='waist']:checked").each(function () {
                list.push($(this).val());
            });
            $("input[name='hand']:checked").each(function () {
                list.push($(this).val());
            });
            $("input[name='leg']:checked").each(function () {
                list.push($(this).val());
            });
            $("input[name='buttocks']:checked").each(function () {
                list.push($(this).val());
            });
            $("input[name='allergy']:checked").each(function () {
                list.push($(this).val());
            });
            //客户ID
            var cid = $.cookie("cid");
            list = JSON.stringify(list).replace("[", "").replace("]", "").replace(/\"/g, "");
            var text = {
                "9": $("#hopeResult").val(),
                "10": $("#useProduct").val(),
                "11": $("#product").val(),
                "12": $("#time").val(),
                "13": $("#physicalTruth").val(),
                "14": $("#taboo").val(),
            }
            text = JSON.stringify(text)
            $.post(url, {
                "typeIds": list,
                "cid": cid,
                "text": text,
                "type": 3,
                "date": date
            }, function (res) {
                alert(JSON.stringify(res.msg));
            });
            var imgList = that.imagesList;
            console.log(imgList)
            var leng = Object.keys(imgList);
            console.log(leng)
            var isFlage = true;
            if (leng.length > 0) {
                isFlage = that.toImagesIsFlage(leng, isFlage);
            }
            const imgUrl = $.stringFormat('{0}/frClientArchivesRelatePic/savePic', $.cookie('url'));
            var param = new FormData();
            console.log(that.imageFiles)
            for (var i = 0; i < that.imageFiles.length; i++) {
                param.append('file', that.imageFiles[i]); //通过append向form对象添加数据

            }
            param.append('childPath', 'avatar/'); //通过append向form对象添加数据
            param.append('type', 3);
            param.append('date', date);
            param.append('cid', cid);
            param.append('imgType', 1);
            $.ajax({
                type: 'POST',
                url: imgUrl,
                data: param,
                dataType: "json",
                processData: false,
                contentType: false,
                cache: false,
                success: function (res) {
                    if (res.code == '200') {
                        var param2 = new FormData();
                        console.log(that.afterImg)
                        if (that.afterImg.length > 0) {
                            for (var i = 0; i < that.afterImg.length; i++) {
                                param2.append('file', that.afterImg[i]); //通过append向form对象添加数据

                            }
                            param2.append('imgType', 2);
                            param2.append('childPath', 'avatar/'); //通过append向form对象添加数据
                            param2.append('type', 3);
                            param2.append('date', date);
                            param2.append('cid', cid);
                            console.log(param2)
                            $.ajax({
                                type: 'POST',
                                url: imgUrl,
                                data: param2,
                                dataType: "json",
                                processData: false,
                                contentType: false,
                                cache: false
                            })
                        }
                    }
                }
            })


            console.log(that.delImg)
            const delImgUrl = $.stringFormat('{0}/frClientArchivesRelatePic/delPic', $.cookie('url'));
            var list = JSON.stringify(that.delImg).replace("[", "").replace("]", "").replace(/\"/g, "");
            if (that.delImg != '' || that.delImg != null || that.delImg != []) {
                $.get(delImgUrl, {"pids": list}, function (res) {

                })
            }

        },
        //选择图片弹窗
        pictureSelected: function (num) {
            var that = this;
            that.imgNum = num;
            $('.ImgModal-wrap').show();
            // console.log(num);
        },
        // 点击上传图片
        picModalFile: function () {
            $('#file-fr').click();
            console.log('click');
        },
        closeImgModal: function () {
            $('.ImgModal-wrap').hide();
        },
        //显示图片
        pictureShow: function (e) {
            var that = this;
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
                    // $('.delete_img').eq(that.imgNum - 1).css('display', 'block')

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
                    if (images[i] == 'plasticity7' || images[i] == 'plasticity8' || images[i] == 'plasticity9' || images[i] == 'plasticity10' || images[i] == 'plasticity11' || images[i] == 'plasticity12') {
                        that.afterImg.push(file)
                        console.log(that.afterImg)
                    } else {
                        that.imageFiles.push(file);
                        console.log(that.imageFiles)
                    }

                }
            }
            return isFlage;
        },
        //图片下载
        picDownload: function () {
            var that = this;
            var alink = document.createElement("a")
            var num = that.imgNum;
            console.log(that.imgNum)
            alink.href = $("#" + num).attr("src");
            console.log($("#" + num).attr("src"))
            alink.download = 'img';
            alink.click();
        },
        // deleimg: function (num) {
        //     $('.img-responsive').eq(num).attr('src', '../img/addImg.png');
        //     // this.imagesList[num].splice()
        //     $('.delete_img').eq(num).css('display', 'none')
        // }
        //打印
        print: function (num) {
            var that = this;
            var headhtml = "<html><head><title></title>  <link rel=\"stylesheet\" href=\"../css/bootstrap.min.css\"/>\n" +
                "    <link rel=\"stylesheet\" href=\"../css/reset.css\"/>\n" +
                "    <link rel=\"stylesheet\" href=\"../css/base.css\"/>\n" +
                "    <link rel=\"stylesheet\" href=\"../css/skin/jedate.css\"/>\n" +
                "    <link rel=\"stylesheet\" href=\"../css/combobox.css\"/>\n" +
                "    <link rel=\"stylesheet\" href=\"../css/customerPersonalDetails.css\"/></head><body>";
            var foothtml = "</body>";

            // 获取div中的html内容
            // var newhtml = document.all.item(printpage).innerHTML;
            // 获取div中的html内容，jquery写法如下
            if (num == 1) {
                var newhtml = $(".skinPrint").html();
            } else if (num == 2) {
                var newhtml = $(".hairPrint").html();
            } else if (num == 3) {
                var newhtml = $(".plasticityPrint").html();
            }

            // 获取原来的窗口界面body的html内容，并保存起来
            var oldhtml = document.body.innerHTML;

            // 给窗口界面重新赋值，赋自己拼接起来的html内容
            document.body.innerHTML = headhtml + newhtml + foothtml;
            // 调用window.print方法打印新窗口
            window.print();
            // 将原来窗口body的html值回填展示
            document.body.innerHTML = oldhtml;
            // window.location.reload();
            if (num == 1) {
                window.location.reload();
                customerPersonalDetails.LI(7);
                console.log(11)
            } else if (num == 2) {
                window.location.reload();
                customerPersonalDetails.LI(8);
            } else if (num == 3) {
                window.location.reload();
                customerPersonalDetails.LI(9);
            }

            return false;
        },
        //导出Excel
        exportExcel: function (num) {
            var that = this;
            var type = that.type;
            var cid = $.cookie("cid");
            if (num == 1) {
                var url = $.stringFormat('{0}/excel/getSkin', $.cookie('url'));
                var data = JSON.stringify(that.Skinchecked)
                if (data === '{}' || data === '[]' || that.Skinchecked.length == 0) {
                    alert("未选中内容");
                } else {
                    var form = $("<form>");//定义一个form表单
                    form.attr("style", "display:none");
                    form.attr("target", "_break");
                    form.attr("method", "post");
                    form.attr("action", url);
                    var input1 = $("<input>");
                    input1.attr("type", "hidden");
                    input1.attr("name", "date");
                    input1.attr("value", that.dateNow);
                    var input2 = $("<input>");
                    input2.attr("type", "hidden");
                    input2.attr("name", "cid");
                    input2.attr("value", cid);
                    var input3 = $("<input>");
                    input3.attr("type", "hidden");
                    input3.attr("name", "type");
                    input3.attr("value",type);
                    $("body").append(form);//将表单放置在web中
                    form.append(input1);
                    form.append(input2);
                    form.append(input3);
                    form.submit();//表单提交
                }
            } else if (num == 2) {
                var url = $.stringFormat('{0}/excel/getHair', $.cookie('url'));
                var data = JSON.stringify(that.Hairchecked)
                if (data === '{}' || data === '[]' || that.Hairchecked.length == 0) {
                    alert("未选中内容");
                } else {
                    var form = $("<form>");//定义一个form表单
                    form.attr("style", "display:none");
                    form.attr("target", "_break");
                    form.attr("method", "post");
                    form.attr("action", url);
                    var input1 = $("<input>");
                    input1.attr("type", "hidden");
                    input1.attr("name", "date");
                    input1.attr("value", that.dateNow);
                    var input2 = $("<input>");
                    input2.attr("type", "hidden");
                    input2.attr("name", "cid");
                    input2.attr("value", cid);
                    var input3 = $("<input>");
                    input3.attr("type", "hidden");
                    input3.attr("name", "type");
                    input3.attr("value",type);
                    $("body").append(form);//将表单放置在web中
                    form.append(input1);
                    form.append(input2);
                    form.append(input3);
                    form.submit();//表单提交
                }
            } else if (num == 3) {
                var url = $.stringFormat('{0}/excel/getPlasticity', $.cookie('url'));
                var data = JSON.stringify(that.Plasticitychecked)
                if (data === '{}' || data === '[]' || that.Plasticitychecked.length == 0) {
                    alert("未选中内容");
                } else {
                    var form = $("<form>");//定义一个form表单
                    form.attr("style", "display:none");
                    form.attr("target", "_break");
                    form.attr("method", "post");
                    form.attr("action", url);
                    var input1 = $("<input>");
                    input1.attr("type", "hidden");
                    input1.attr("name", "date");
                    input1.attr("value", that.dateNow);
                    var input2 = $("<input>");
                    input2.attr("type", "hidden");
                    input2.attr("name", "cid");
                    input2.attr("value", cid);
                    var input3 = $("<input>");
                    input3.attr("type", "hidden");
                    input3.attr("name", "type");
                    input3.attr("value",type);
                    $("body").append(form);//将表单放置在web中
                    form.append(input1);
                    form.append(input2);
                    form.append(input3);
                    form.submit();//表单提交
                }
            }

        },
    },


    //页面加载完成后执行的函数
    mounted() {
        mainheight();
    }
});
$('body').on('click', 'img.img-responsive', function () {
    $("#ImgModal-ImgUrl").attr("src", $(this).attr('src'));
})
customerPersonalDetails.LI(0);