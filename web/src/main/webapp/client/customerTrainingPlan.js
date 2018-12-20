var customerTrainingPlan = new Vue({
    el: '#customerTrainingPlan',
    data: {

        //训练计划列表
        customerTable: {
            list: [],
            currPage: 1,
            pageSize: 10,
            totalCount: 0,
            totalPage: 0,
        },
        ActionInfo:[],
    },
    crated: function () {
    },
    methods: {
        init: function (t) {
            const that = this;
            that.queryTrainingPlanList();

        },
        mounted: function () {


        },
        /**
         * 分页查询可认领客户列表
         */
        queryTrainingPlanList: function (params) {
            const that = this;
            var cid = $.cookie("cid");
            if (typeof(params)=='undefined'){
                params={"cid": cid};
            }
            //显示加载中
            Loading.prototype.show();
            that.$nextTick(function () {
                    const url = $.stringFormat('{0}/frTrainingPlan/getTrainingPlanList', $.cookie('url'));
                    $.get(url, params, function (res) {
                        if (res.code === '200') {
                            that.customerTable = res.data;
                            $.each(res.data.list, function (i, n) {
                                that.customerTable.list[i].trainDate = timeFormatDate(that.customerTable.list[i].trainDate);
                                that.customerTable.list[i].actualStartDate = timeFormatDate(that.customerTable.list[i].actualStartDate);
                                that.customerTable.list[i].actualEndDate = timeFormatDate(that.customerTable.list[i].actualEndDate);
                                that.customerTable.list[i].actualDate = timeFormatDate(that.customerTable.list[i].actualDate);
                                that.customerTable.list[i].trainEndDate = timeFormatDate(that.customerTable.list[i].trainEndDate);
                                that.customerTable.list[i].trainStartDate = timeFormatDate(that.customerTable.list[i].trainStartDate);
                            })
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
                                    var currPage=page+"";
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
        course:function (list) {
            var that=this;
                console.log(list)
                that.ActionInfo=list;

        } ,
    }
});

