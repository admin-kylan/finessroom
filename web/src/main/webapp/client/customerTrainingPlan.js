var customerTrainingPlan = new Vue({
    el: '#customerTrainingPlan',
    data: {
        //训练计划列表
        customerTable: {
            list: [],
            currPage: 1,
            pageSize: 10,
            totalContentInfo: 0,
            totalPage: 0,
        },
        param: {
            project: '',
            trainDate: '',
            trainStartDate: '',
            trainEndDate: '',
            improvementPlan: '',
            cid: '',
            classIds: [],
        },
        //控制弹窗临时变量
        temp: null,
        temp2: null,
        temp3: null,
        //导入训练计划ID
        ids: [],
        ActionInfo: [],
        PlanInfo: [],
        ContentInfo: [],
        SetMealInfo: [],
        SetMealContentInfo: [],
        tempInfo: [],
        tempInfo2:[],
    },
    crated: function () {
    },
    methods: {
        init: function (t) {
            const that = this;
            that.queryTrainingPlanList();
            that.findTrainingPlan();
            that.findSetMeal();
        },
        mounted: function () {
        },
        /**
         * 分页查询可认领客户列表
         */
        queryTrainingPlanList: function (params) {
            const that = this;
            var cid = $.cookie("cid");
            if (typeof(params) == 'undefined') {
                params = {"cid": cid};
            }
            //显示加载中
            Loading.prototype.show();
            that.$nextTick(function () {
                const url = $.stringFormat('{0}/frTrainingPlan/getTrainingPlanList', $.cookie('url'));
                $.get(url, params, function (res) {
                    if (res.code === '200') {
                        that.customerTable = res.data;

                        new myPagination({
                            id: 'pagination',
                            curPage: that.customerTable.currPage, //初始页码
                            pageTotal: that.customerTable.totalPage, //总页数
                            pageAmount: that.customerTable.pageSize,  //每页多少条
                            dataTotal: that.customerTable.totalCount, //总共多少条数据
                            showPageTotalFlag: true, //是否显示数据统计
                            showSkipInputFlag: true, //是否支持跳转
                            getPage: function (page) {
                                //获取当前页数
                                console.log(page)
                                var currPage = page + "";
                                that.queryTrainingPlanList({"cid": cid, "currPage": currPage});
                            }
                        })
                    } else {
                        $.alert(jsonData['data']['msg'])
                    }
                    //隐藏加载中
                    Loading.prototype.hide();
                })

            })

        },
        //课程详情中 点击导入
        open: function (index) {
            var that = this;
            console.log(index)
            //取消选中
            that.ids = [];
            that.ContentInfo = []

            $(":checkbox").prop('checked', false)
            if (index == 1) {
                $('.box1').show();
            } else {
                $('.box2').show();
            }
        },
        //查询训练计划
        findTrainingPlan: function () {
            var that = this;
            var url = $.stringFormat('{0}/frTrainingPlan/getCourse', $.cookie('url'));
            $.get(url, {"type": "1"}, function (res) {
                that.PlanInfo = res.data;

            });

        },
        //添加训练计划中 点击训练计划
        getContentInfo: function (id) {
            var that = this;
            var url = $.stringFormat('{0}/frTraningClass/getPlan', $.cookie('url'));
            $.get(url, {"id": id, "type": 1}, function (res) {
                that.ContentInfo = res.data;
            });

        },
        //添加训练计划中  查询训练套餐
        findSetMeal: function () {
            var that = this;
            var url = $.stringFormat('{0}/frTrainingPlan/getCourse', $.cookie('url'));
            $.get(url, {"type": "2"}, function (res) {
                that.SetMealInfo = res.data;

            });

        },
        //点击训练套餐
        getSetMealContentInfo: function (id) {
            var that = this;
            var url = $.stringFormat('{0}/frTraningClass/getPlan', $.cookie('url'));
            $.get(url, {"id": id, "type": 2}, function (res) {
                that.SetMealContentInfo = res.data;
                console.log(that.SetMealContentInfo)
            });

        },
        //添加课程 弹窗
        addClass: function () {
            var that = this;
            that.ids == []
            if (that.temp3 != 1) {
                that.ActionInfo = [];
                that.temp = null;
            }

            $('.box').show();
        },
        //课程详情 弹窗
        course: function (list, index) {
            var that = this;
            that.ids == []
            if (that.temp == 2 && that.temp2 == index) {
            } else {
                that.ActionInfo = list;
            }
            if (that.temp == null || that.temp == 1) {
                that.ActionInfo = list;
            }

            that.temp2 = index;
            that.temp = 2;
            $('.box').show();
            console.log(that.ActionInfo)
            that.temp3 == null;
        },
        //导入 type=1 训练计划 type=2 训练计划套餐
        addPlan: function (type) {
            var that = this;
            var url = $.stringFormat('{0}/frTrainingPlan/getPlanById', $.cookie('url'));
            if (that.ids == '' || that.ids == [] || that.ids == null || typeof(that.ids) == 'undefined') {
                if (type == 1) {
                    alert("未选择训练计划");
                } else {
                    alert("未选择训练计划套餐");
                }
                return;
            }
            var ids = JSON.stringify(that.ids).replace("[", "").replace("]", "").replace(/\"/g, "");
            $.get(url, {"ids": ids}, function (res) {
                if (res.code === "200") {
                    if (that.ActionInfo.length != 0) {
                        that.ActionInfo.push.apply(that.ActionInfo, res.data);
                    } else {
                        that.ActionInfo = res.data;

                    }
                    console.log(that.ActionInfo)
                    that.temp3 = 1;
                    if (type == 1) {
                        $('.box1').hide();
                    } else {
                        $('.box2').hide();
                    }
                }

            })
        },

        //添加训练计划
        add: function () {
            var that = this;
            if (that.param.project == '') {
                alert("私教名称不能为空")
                return;
            } else if (that.param.improvementPlan == '') {
                alert("改进方案不能为空")
                return;
            } else if (that.param.trainDate == '') {
                alert("训练日期不能为空")
                return;
            } else if (that.param.trainStartDate == '') {
                alert("训练开始时间不能为空")
                return;
            } else if (that.param.trainEndDate == '') {
                alert("训练结束时间不能为空")
                return;
            }
            console.log(that.param)
            that.param.cid = $.cookie("cid");
            $.each(that.ActionInfo, function (i, n) {
                that.param.classIds.push(n.id)
            })
            var url = $.stringFormat('{0}/frTrainingPlan/saveProject', $.cookie('url'));
            axios.post(url, that.param).then(function (res) {
                let resData = eval(res);
                if (resData['data']['code'] === '200') {
                    alert("添加成功")
                    location.reload();
                } else {
                    $.alert(resData['data']['msg']);
                }
            })
                .catch(function (error) {
                    $.alert(error);
                });

        },
        //修改训练计划
        update: function (list, index) {
            var that = this;
            var update = $('.updateTr').eq(index).find("input")

            if (list == '' || list == null) {
                list = that.ActionInfo;
            }
            console.log(list)
            var updateParam = {
                project: update[0].value,
                trainDate: update[1].value,
                trainStartDate: update[2].value,
                trainEndDate: update[3].value,
                cid: $.cookie("cid"),
                actionInfo: list,
                actualStartDate: update[5].value,
                actualEndDate: update[6].value,
                actualDate: update[4].value,
                memberFeel: update[7].value,
                coachSummary: update[8].value,
                fid: update[9].value,
            }
            console.log(updateParam)
            var url = $.stringFormat('{0}/frTrainingPlan/updateProject', $.cookie('url'));
            axios.post(url, updateParam).then(function (res) {
                let resData = eval(res);
                if (resData['data']['code'] === '200') {
                    alert("修改成功")
                    location.reload();
                } else {
                    $.alert(resData['data']['msg']);
                }
            })
                .catch(function (error) {
                    $.alert(error);
                });

        },
        //训练计划删除
        del: function (id) {
            var that = this;
            console.log(id)
            var flag = confirm("确定删除吗?");
            if (flag) {
                var url = $.stringFormat('{0}/frTrainingPlan/delPlan', $.cookie('url'));
                $.get(url, {"id": id}, function (res) {
                    if (res.code == "200") {
                        alert("删除成功")
                        location.reload();
                    } else {
                        alert("删除失败")
                    }
                })
            }
        },
        //动作修改
        updateAction: function (index) {
            var that = this;
            var update = $('.action').eq(index).find("input")
            var param = {
                name: update[1].value,
                diff: update[4].value,
                image: update[3].value,
                time: update[5].value,
                strength: update[6].value,
                count: update[7].value,
                remark: update[8].value,
                sname: update[2].value,
                id: update[0].value,

            }
            $.each(that.customerTable.list, function (i, n) {
                $.each(n.frPlanClasses, function (j, k) {
                    if (k.id == param.id) {
                        that.customerTable.list[i].frPlanClasses[j] = param;
                    }
                })
            })
            var url = $.stringFormat('{0}/frPlanClass/updateAction', $.cookie('url'));
            axios.post(url, param).then(function (res) {
                let resData = eval(res);
                if (resData['data']['code'] === '200') {
                    alert("修改成功")
                } else {
                    $.alert(resData['data']['msg']);
                }
            })
                .catch(function (error) {
                    $.alert(error);
                });
        },
        //动作删除
        delAction: function (index) {
            var that = this;
            var flag = confirm("确定删除吗?");
            if (flag) {
                var tid = $('.action').eq(index).find("input")[9].value
                var id = $('.action').eq(index).find("input")[0].value
                var url = $.stringFormat('{0}/frPlanClass/delAction', $.cookie('url'));
                $.each(that.ActionInfo, function (i, n) {
                    if (n.id = id) {
                        that.ActionInfo.splice(i, 1)
                        return false;
                    }
                })
                if (tid != "" && tid != null) {
                    $.get(url, {"id": id, "tid": tid}, function (res) {
                        console.log(id)
                        if (res.code == "200") {
                            alert("删除成功")

                        } else {
                            alert("删除失败")
                        }
                    });
                } else {
                    alert("删除成功")
                }

            } else {
                return;
            }

        },
        //全选
        checkAll: function (type, id, index,event) {
            var that = this;
            if (type == 1) {
                var thisChecked = $("#checkPlan").prop('checked');
                $("#planBody input[type='checkbox']").each(function () {
                    if (thisChecked) {
                        $(this).prop("checked", thisChecked);
                        that.ids.push($(this).val())
                    } else {
                        that.ids = [];
                        $(this).prop("checked", thisChecked);
                    }

                })
            } else if (type == 2) {
                var thisChecked = $("#checkSetMeal").prop('checked');
                $("#setMealBody input[type='checkbox']").each(function () {
                    if (thisChecked) {
                        $(this).prop("checked", thisChecked);
                        that.ids.push($(this).val())
                    } else {
                        that.ids = [];
                        $(this).prop("checked", thisChecked);
                    }

                })
            } else if (type == 3) {
                var thisChecked = $(".planParent").eq(index).prop('checked');

                if (thisChecked) {
                    var url = $.stringFormat('{0}/frTraningClass/getPlan', $.cookie('url'));
                    $.get(url, {"id": id, "type": 1}, function (res) {
                        that.tempInfo = res.data;
                        $(that.tempInfo).each(function (i, n) {
                            that.ids.push(n.id)
                        })
                    });
                } else {
                    $(that.tempInfo).each(function (i, n) {
                        $(that.ids).each(function (j, k) {
                            if (n.id == k) {
                                that.ids.splice(j, 1)
                                j--;
                            }
                        })
                    })
                }
            } else if (type == 4) {
                var thisChecked = $(".setMealParent").eq(index).prop('checked');
                if (thisChecked) {
                    var url = $.stringFormat('{0}/frTraningClass/getPlan', $.cookie('url'));
                    $.get(url, {"id": id, "type": 2}, function (res) {
                        that.tempInfo = res.data;
                        $(that.tempInfo).each(function (i, n) {
                            that.ids.push(n.id)
                        })
                    });
                } else {
                    $(that.tempInfo).each(function (i, n) {
                        $(that.ids).each(function (j, k) {
                            if (n.id == k) {
                                that.ids.splice(j, 1)
                                j--;
                            }
                        })
                    })
                }
            } else if (type == 5) {
                var thisChecked = $(".checkboxParent").eq(index).prop('checked');
                var  el=$(event.currentTarget).parent().next();
                $(el).find("input").each(function () {
                    if (thisChecked) {
                        $(this).prop("checked", thisChecked);
                    } else {
                        $(this).prop("checked", thisChecked);
                    }

                })
               $(id).each(function (i,n) {
                   if (thisChecked) {
                       var url = $.stringFormat('{0}/frTraningClass/getPlan', $.cookie('url'));
                       $.get(url, {"id": n.id, "type": 1}, function (res) {
                           that.tempInfo = res.data;
                           $(that.tempInfo).each(function (j, k) {
                               that.ids.push(k.id)

                           })
                           if (that.tempInfo.length != 0) {
                               that.tempInfo2.push.apply(that.tempInfo2, res.data);
                           } else {
                               that.tempInfo2 = res.data;
                           }
                       });
                   } else {
                        console.log(that.tempInfo2)
                       $(that.tempInfo2).each(function (q, w) {
                           $(that.ids).each(function (j, k) {
                               if (w.id == k) {
                                   that.ids.splice(j, 1)
                                   j--;
                               }
                           })
                       })

                   }
               })

            } else if (type == 6) {

            }

        },
        //毫秒转为时分秒
        formatSeconds: function (time) {
            if (time == null || time == '') {
                return '';
            }
            var date = new Date(time);
            var h = date.getHours();
            h = h < 10 ? ('0' + h) : h;
            var m = date.getMinutes();
            m = m < 10 ? ('0' + m) : m;
            var _time = h + ':' + m;
            return _time;
        },
        //毫秒转为日期
        formatDate: function (time, type, typeT) {
            if (!time) {
                return '';
            }
            var _time = timeFormatDate(time, type, typeT);
            return _time;
        },
    }
});

