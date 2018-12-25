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

        temp: null,
        temp2: null,
        temp3: null,
        ids: [],
        ActionInfo: [],
        PlanInfo: [],
        ContentInfo: [],
        SetMealInfo: [],
        SetMealContentInfo: [],
    },
    crated: function () {
    },
    filters: {},
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

        //查询训练计划
        findTrainingPlan: function () {
            var that = this;
            var url = $.stringFormat('{0}/frTrainingPlan/getCourse', $.cookie('url'));
            $.get(url, {"type": "1"}, function (res) {
                that.PlanInfo = res.data;

            });

        },
        //点击训练计划
        getContentInfo: function (id) {
            var that = this;
            var url = $.stringFormat('{0}/frTraningClass/getPlan', $.cookie('url'));
            $.get(url, {"id": id, "type": 1}, function (res) {
                that.ContentInfo = res.data;
                console.log(that.ContentInfo)
            });

        },
        //查询训练套餐
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
        //添加课程弹窗
        addClass: function () {
            var that = this;
            if (that.temp3 != 1) {
                that.ActionInfo = [];
                that.temp = null;
            }
            $('.box').show();
        },
        //课程详情弹窗
        course: function (list, index) {
            var that = this;

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
            that.temp3 == null;
        },
        //导入训练计划
        addPlan: function () {
            var that = this;
            var url = $.stringFormat('{0}/frTrainingPlan/getPlanById', $.cookie('url'));
            if (that.ids == '' || that.ids == [] || that.ids == null || typeof(that.ids) == 'undefined') {
                alert("未选择训练计划");
                return;
            }
            var ids = JSON.stringify(that.ids).replace("[", "").replace("]", "").replace(/\"/g, "");
            $.get(url, {"ids": ids}, function (res) {
                console.log(that.ActionInfo.length)
                if (that.ActionInfo.length != 0) {
                    that.ActionInfo.push.apply(that.ActionInfo,res.data);
                    $('.box1').hide();
                } else {
                    that.ActionInfo = res.data;
                    if (res.code === "200") {
                        $('.box1').hide();
                        that.temp3 = 1;
                    }
                }
            })
        },
        //添加
        add: function () {
            var that = this;
            console.log(that.ActionInfo)
            that.param.cid = $.cookie("cid");
            $.each(that.ActionInfo, function (i, n) {
                that.param.classIds.push(n.id)
            })
            console.log(that.param)
            var url = $.stringFormat('{0}/frTrainingPlan/saveProject', $.cookie('url'));
            axios.post(url, that.param).then(function (res) {
                let resData = eval(res);
                if (resData['data']['code'] === '200') {
                    alert("添加成功")
                } else {
                    $.alert(resData['data']['msg']);
                }
            })
                .catch(function (error) {
                    $.alert(error);
                });

        },
        //修改
        update: function (list,index) {
            var that = this;
            var update = $('.updateTr').eq(index).find("input")
            var updateParam = {
                project: update[0].value,
                trainDate: update[1].value,
                trainStartDate: update[2].value,
                trainEndDate: update[3].value,
                cid: $.cookie("cid"),
                classIds: [],
                id:update[9].value,
                actualStartDate: update[5].value,
                actualEndDate: update[6].value,
                actualDate: update[4].value,
                memberFeel: update[7].value,
                coachSummary: update[8].value,
            }
            console.log(list)
            $.each(list, function (i, n) {
                updateParam.classIds.push(n.id)
            })
            console.log(updateParam)
            var url = $.stringFormat('{0}/frTrainingPlan/updateProject', $.cookie('url'));
            axios.post(url, updateParam).then(function (res) {
                let resData = eval(res);
                if (resData['data']['code'] === '200') {
                    alert("添加成功")
                } else {
                    $.alert(resData['data']['msg']);
                }
            })
                .catch(function (error) {
                    $.alert(error);
                });

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

