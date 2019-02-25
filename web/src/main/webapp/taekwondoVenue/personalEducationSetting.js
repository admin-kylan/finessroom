new Vue({
    el: '#wrapper',
    data: {
        //训练课程设置 添加系列的type  0 动作设置 1 单节训练 2 训练套餐
        addSeriesType: 0,

        //动作设置页面中 动作系列的选中下标
        actionSettingIndex: 0,
        //单节训练课程设置页面中 动作系列的选中下标
        actionEduSettingIndex: 0,
        //动作设置---动作系列
        actions: [
            {
                actionList: []
            }
        ],
        //动作设置---新增动作的对象
        actionAddData: {},
        //动作设置---移动动作选中的系列的id
        changeActionSelect: '',
        //动作设置---移动动作选中的id
        changeActionIds: [],
        //动作设置 --- 修改动作名称的对象
        actionSettingData: {},


        addActionSeriesName: '',


        //单节训练课程设置页面中 动作系列的item
        simpleLinkSettingAction: [],
        //单节训练课程设置页面中 动作系列的item的子项
        simpleLinkSettingActionSub: [],
        //单节训练课程设置页面中 动作系列的item的子项选中项
        simpleLinkSettingActionSubSelectId: '',
        //单节训练课程设置页面中 动作系列的item的子项对应的表格数据
        simpleLinkSettingActionSubTable: [],
        //单节训练课程设置页面中 动作系列的item的子项的添加名称
        addTrainName: '',
        //单节训练课程设置页面中 动作系列的item的id
        updateItemName: '',
        updateTrainName: '',
        //单节训练课程设置页面中 修改训练计划的item的id
        addTrainParentId: '',
        //单节训练课程设置页面中 动作系列的item的类型区分 单节训练和套餐
        addTrainType: 1,
        //单节训练课程设置页面中 添加动作类型的对话框的动作系列的index
        trainActionIndex: 0,
        //单节训练课程设置页面中 添加动作的动作ids
        trainAddActionIds: [],
        //单节训练课程设置页面中 有修改的动作 (时间 次数)
        trainActionChanges: [],


        //训练套餐页面 ---- 动作系列的选中下标
        mealSettingIndex: 0,
        //训练套餐页面---- 动作系列的item
        mealLinkSettingAction: [],
        //训练套餐页面 动作系列的item的子项
        mealLinkSettingActionSub: [],
        //训练套餐页面 动作系列的item的子项选中项
        mealLinkSettingActionSubSelectId: '',
        //训练套餐页面 动作系列的item的子项对应的表格数据
        mealLinkSettingActionSubTable: [],
        //训练套餐页面 有修改的动作 (时间 次数)
        mealActionChanges: [],


        isAuth: false,
        isAll: false,
        storeData: [
            {
                value: 1,
                text: '厦门市',
                selected: false,
                children: [
                    {
                        value: 10000,
                        text: '软件园店',
                        selected: false,
                    },
                    {
                        value: 10001,
                        text: '集美店',
                        selected: false,
                    },
                    {
                        value: 10002,
                        text: '思明店',
                        selected: false,
                    }
                ]
            },
            {
                value: 2,
                text: '泉州市',
                selected: false,
                children: [
                    {
                        value: 20000,
                        text: '丰泽区',
                        selected: false,
                    },
                    {
                        value: 20001,
                        text: '鲤城区',
                        selected: false,
                    },
                    {
                        value: 20002,
                        text: '东海区',
                        selected: false,
                    }
                ]
            }
        ],
        courseSeriess: [
            {
                text: '单车系类',
                href: '',
                tags: ['3'],
                nodes: [
                    {
                        text: '+新增课程',
                        href: '',
                        tags: ['0'],
                    },
                    {
                        text: '动感单车',
                        href: '',
                        tags: ['0']
                    },
                    {
                        text: '丛林穿越',
                        href: '',
                        tags: ['0']
                    }
                ]
            },
            {
                text: '单车系类',
                href: '',
                tags: ['3'],
                nodes: [
                    {
                        text: '+新增课程',
                        href: '',
                        tags: ['0'],
                    },
                    {
                        text: '动感单车',
                        href: '',
                        tags: ['0']
                    },
                    {
                        text: '丛林穿越',
                        href: '',
                        tags: ['0']
                    }
                ]
            }
        ],
        addPrivate: false,//新增私教课程
        addPrivateCourse: false,
        deleteStr: '<span class="delete-node">删除</span>',
        editor: null,
        //私教课程列表
        privateCource: {
            list: [],
            pageNo: 1,
            pageSize: 3,

        },
        //私教课程表单数据
        privateCourceData: {
            dayAndMounth: 2,
            courseName: "",
            remainCourceNum: 0,
            classSchedulingType: '',
            assignTeacherType: ''
        },
        //私有教模块选中默认课程弹窗信息
        privateSingleCourse: {
            seriesList: [],
            subSeriesList: [],
            showIndex: 0,
            totalCount: 0,
            items: [],     //弹窗右边数据
            itemIndex: -1,    //trainingSeriesIds
        },
        // 整个界面通用界面url
        imgUrl: null,
        //对应图片文件的file对象
        imgFile: null,
        packageCource: {
            list: [],
            pageNo: 1,
            pageSize: 3,

        },
        packageData: {
            validType: 1,
        },
        shopInfos: [],
        packagePrivateCourse: {
            list: [],
            pageNo: 1,
            pageSize: 3,
        },
        selectRelation: {
            shopItem: {},
            courseObj: [],
            courseIds: [],
        },
        canUsingItem: [],
        listViewCanUsingItem: [],
        listViewCanUsingItemCount: 0,
        listViewCanUsingItemPackageId: 0,
        treeNode: {},

        settingInfo: {},
        settingSdaduimList: [],
        isok: false,
        //课程ID
        courceIds: [],
        modalTitle: "",
        placeholderTxt: "",

        shopInfo: [],
        shopId: '',
        temp: 0,
        tempInfo:[],
        sdaduimId:'491e0714fd3aad50',
        tempImg : '',
    },
    mounted: function () {
        this.initSelectableTree();
        this.initPrivateCource({curPage: 1, limit: 10,sdaduimId:this.sdaduimId});
        this.initServerClassRoomSetting();
        /*jeDate("#datepicker",{
            format:"YYYY-MM-DD",
            isTime:false,
            minDate:"2014-09-19 00:00:00"
        })*/
        //     jeDate("#datepicker");
        /*  $("#datepicker").jeDate({
              isinitVal: true,
              festival: true,
              ishmsVal: false,
              format: "YYYY年MM月DD日",
              zIndex: 3000
          });*/
        /*  var datepickerOptions = {
              dateCell:"#datepicker", //目标元素。由于jedate.js封装了一个轻量级的选择器，因此dateCell还允许你传入class、tag这种方式 '#id .class'
              format:"YYYY-MM-DD hh:mm:ss", //日期格式
              minDate:"1900-01-01 00:00:00", //最小日期
              maxDate:"2099-12-31 23:59:59", //最大日期
              isinitVal:false, //是否初始化时间
              isTime:true, //是否开启时间选择
              isClear:true, //是否显示清空
              festival:false, //是否显示节日
              zIndex:999,  //弹出层的层级高度
              marks:null, //给日期做标注
            //  choosefun:function(val) {},  //选中日期后的回调
              clearfun:function(val) {},   //清除日期后的回调
             // okfun:function(val) {}       //点击确定后的回调
          };
          jeDate(datepickerOptions);*/
        /* $(function () {
             $("#datepicker").jeDate({
                 isinitVal: true,
                 festival: true,
                 ishmsVal: false,
                 format: "YYYY年MM月DD日",
                 zIndex: 3000
             });
         });*/
    },
    methods: {
        cancelPrivateCourse: function () {
            this.privateCourceData = {
                dayAndMounth: 2,
                courseName: "",
                remainCourceNum: 0,
                classSchedulingType: '',
                assignTeacherType: ''
            };
            this.privateCourceData.dayAndMounth = 2;
            this.privateCourceData.remainCourceNum = 0;
            this.editor.txt.html("");
            this.imgUrl = null
            this.addPrivateCourse = false;
        },
        getsettingSdaduimList: function () {
            let url = $.stringFormat("{0}/frPrivatePackageRelation/sdaduimList", $.cookie('url')), that = this;
            if (that.settingSdaduimList.length > 0) {
                $("#appointModal").modal('show')
                return;
            }
            // Loading.prototype.show();
            axios.get(url, {params: {}}).then(function (res) {
                let resData = eval(res);
                if (res.data.code != 500) {
                    that.settingSdaduimList = res.data.data;
                    $("#appointModal").modal('show')
                } else {
                    $.alert("操作失败，服务器异常");
                }
                Loading.prototype.hide();

            }).catch(function (error) {
                //隐藏加载中
                console.info(error)
                Loading.prototype.hide();
                $.alert(error)
            });
        },
        saveSettingInfo: function () {
            let url = $.stringFormat("{0}/frSettingInfo/update/private/setting", $.cookie('url')), that = this;
            // Loading.prototype.show();
            let cks = [], cgs = [];
            $('.settingckCls:checked').each(function () {
                cks.push(this.value)
            });
            $('.xfzdcg:checked').each(function () {
                cgs.push(this.value);
            });
            console.info(cks);
            console.info(cgs);
            this.settingInfo.privateHYXFBMK = cks.join(",");
            this.settingInfo.privateHYJZZZCS = cgs.join(",");
            delete this.settingInfo.privateHYXFBMKCK;
            delete this.settingInfo.privateHYJZZZCSCK;
            this.settingInfo.sdaduimId = this.sdaduimId;
            axios.get(url, {params: this.settingInfo}).then(function (res) {
                let resData = eval(res);
                if (res.data.code != 500) {
                    $.alert("操作成功");
                } else {
                    $.alert("操作失败，服务器异常");
                }
                Loading.prototype.hide();

            }).catch(function (error) {
                //隐藏加载中
                console.info(error)
                Loading.prototype.hide();
                $.alert(error)
            });
        },
        getPrivateSetting: function () {
            let url = $.stringFormat("{0}/frSettingInfo/get/private/setting", $.cookie('url')), that = this;
            // Loading.prototype.show();
            this.settingSdaduimList = [];
            axios.get(url, {params: {"sdaduimId": this.sdaduimId}})
                .then(function (res) {
                    if (res.data.code == 200) {
                        that.settingInfo = res.data.data;
                        if (that.settingInfo.privateHYXFBMK && that.settingInfo.privateHYXFBMK != null && that.settingInfo.privateHYXFBMK.length > 0)
                            that.settingInfo.privateHYXFBMKCK = that.settingInfo.privateHYXFBMK.split(",")
                        else {
                            that.settingInfo.privateHYXFBMKCK = [];
                        }
                        if (that.settingInfo.privateHYJZZZCS && that.settingInfo.privateHYJZZZCS != null && that.settingInfo.privateHYJZZZCS.length > 0)
                            that.settingInfo.privateHYJZZZCSCK = that.settingInfo.privateHYJZZZCS.split(",")
                        else {
                            that.settingInfo.privateHYJZZZCSCK = [];
                        }
                    } else {
                        $.alert("服务器异常，获取数据失败")
                    }
                    Loading.prototype.hide();
                })
                .catch(function (error) {
                    console.info(error)
                    Loading.prototype.hide();
                    $.alert("服务器异常，获取数据失败")
                });
        },
        deletePackage: function (id) {
            let url = $.stringFormat("{0}/frPrivatePackage/delete", $.cookie('url')), that = this;
            $.confirm({
                title: '确认',
                content: '确认删除?',
                buttons: {
                    ok: {
                        text: '确认',
                        btnClass: 'btn-primary',
                        action: function () {
                            axios.get(url, {params: {id: id}})
                                .then(function (res) {
                                    if (res.data.code == 200) {
                                        that.getPackageList({
                                            curPage: that.packageCource.pageNo,
                                            limit: that.packageCource.pageSize
                                        });
                                    } else {
                                        $.alert("服务器异常，获取数据失败")
                                    }
                                })
                                .catch(function (error) {
                                    console.info(error)
                                    Loading.prototype.hide();
                                    $.alert("服务器异常，获取数据失败")
                                });
                        }
                    },
                    cancel: {
                        text: '取消',
                        btnClass: 'btn-primary'
                    }
                }
            });
        },
        singleDeleteUsingItem: function (item, index) {
            console.info(item)
            let url = $.stringFormat("{0}/frPrivatePackageRelation/deleteRelation", $.cookie('url')), that = this;
            // Loading.prototype.show();
            axios.get(url, {params: {pakageId: that.listViewCanUsingItemPackageId, courceId: item.courseId}})
                .then(function (res) {
                    if (res.data.code == 200) {
                        that.listViewCanUsingItem.splice(index, 1);
                        that.listViewCanUsingItemCount = that.listViewCanUsingItem.length;
                    } else {
                        $.alert("服务器异常，获取数据失败")
                    }
                    Loading.prototype.hide();
                })
                .catch(function (error) {
                    console.info(error)
                    Loading.prototype.hide();
                    $.alert("服务器异常，获取数据失败")
                });
        },

        cancelPackage: function () {
            let that = this;
            that.addPrivate = false;
            that.selectRelation = {
                shopItem: {},
                courseObj: [],
                courseIds: [],
            },
                that.canUsingItem = [];
            that.packageData = {
                validType: 1,
            }
            $('.canUsingCls').each(function () {
                if (this.checked) {
                    $(this).attr("checked", false);
                }
            })
            try {
                $('#area_tree').treeview('uncheckNode', [that.treeNode, {silent: true}]);
            } catch (e) {
                console.info(e)
            }

        },
        updatePackage: function (id, type) {
            this.getInfo(id, type);
        },
        getInfo: function (id, type) {
            let url = $.stringFormat("{0}/frPrivatePackage/getById", $.cookie('url')), that = this;
            // Loading.prototype.show();
            axios.get(url, {params: {id: id}})
                .then(function (res) {
                    console.info(res.data.data)
                    if (res.data.code == 200) {
                        if (type == 1) {
                            that.listViewCanUsingItem = res.data.data.canUsingItem;
                            that.listViewCanUsingItemCount = that.listViewCanUsingItem.length;
                            $('#viewModal').modal('show');
                        } else {
                            that.packageData = res.data.data;
                            that.canUsingItem = res.data.data.canUsingItem;

                            that.addPrivate = true;
                        }
                    } else {
                        $.alert("服务器异常，获取数据失败")
                    }
                    Loading.prototype.hide();
                })
                .catch(function (error) {
                    console.info(error)
                    Loading.prototype.hide();
                    $.alert("服务器异常，获取数据失败")
                });
        },
        deleteUsingItem: function (index) {
            this.canUsingItem.splice(index, 1);
        },
        saveCAS: function () {
            if (!this.selectRelation.shopItem.id || this.selectRelation.shopItem.id.length == 0) {
                $.alert("请选择门店");
                return;
            }
            if (this.selectRelation.courseObj.length == 0) {
                $.alert("请选择课程");
                return;
            }
            console.log(this.selectRelation)
            let item = {}, index = -1;
            for (let i = 0; i < this.selectRelation.courseObj.length; i++) {
                index = this.getCourseObjIndex(this.canUsingItem, this.selectRelation.courseObj[i].courseId);
                if (index == -1) {
                    item = {};
                    item.courseId = this.selectRelation.courseObj[i].courseId;
                    item.courseName = this.selectRelation.courseObj[i].courseName;
                    item.shopName = this.selectRelation.shopItem.name;
                    item.sdaduimName = this.selectRelation.shopItem.pName;
                    item.sdaduimId = this.selectRelation.shopItem.id;
                    item.classCount = "";
                    item.limitCount = "";
                    item.shopId = this.selectRelation.shopItem.shopId
                    this.canUsingItem.push(item);
                }
            }
            console.info(this.canUsingItem);
            $('#kcFilterModel').modal('hide');
        },
        isCheck: function (id) {
            let course = {};
            course.id = id;
            let index = this.getCourseObjIndex(this.selectRelation.courseObj, id);

            return index == -1 ? false : true;
        },
        selectCourCK: function (e, cource) {
            console.log(cource)
            let checked = e.target.checked,
                index = this.getCourseObjIndex(this.selectRelation.courseObj, cource.id);
            if (checked) {
                if (index == -1) {
                    let vo = {};
                    vo.courseName = cource.name;
                    vo.courseId = cource.id;

                    this.selectRelation.courseObj.push(vo)
                }
            } else {
                if (index != -1) {
                    this.selectRelation.courseObj.splice(index, 1);
                }
            }

        },
        getCourseObjIndex: function (objs, id) {
            for (let i = 0; i < objs.length; i++) {
                if (id == objs[i].courseId) {
                    return i;
                }
            }
            return -1;
        },
        savePackage: function () {
            var that = this;
            this.packageData.canUsingItem = this.canUsingItem;
            var errMsg = "";
            if (!this.isNoEmpty(this.packageData.name) || this.packageData.name == "") {
                $.alert('套餐名称必填');
                return;
            }
            ;
            if (!this.isNoEmpty(this.packageData.count) || this.packageData.count == "") {
                $.alert('总数量必填');
                return;
            }
            ;
            if (!this.isNoEmpty(this.packageData.marketPrice) || this.packageData.marketPrice == "") {
                $.alert('市场价必填');
                return;
            }
            ;
            if (!this.isNoEmpty(this.packageData.memberPrice) || this.packageData.memberPrice == "") {
                $.alert('会员价必填');
                return;
            }
            ;
            if (!this.isNoEmpty(this.packageData.validValue) || this.packageData.validValue == "") {
                $.alert('有效期必填');
                return;
            }
            ;
            if (!this.isNoEmpty(this.packageData.classCount)) {
                $.alert('课时计算数必填');
                return;
            }
            ;

            if (!this.isNoEmpty(this.packageData.isShowDesk)) {
                $.alert('前台是否显示必填');
                return;
            }
            ;
            if (!this.isNoEmpty(this.packageData.isAccountSpending)) {
                $.alert('是否可以挂账消费必填');
                return;
            }
            ;
            if (that.packageData.classCount != null && that.packageData.classCount != '') {
                that.packageData.classCountDesc = "按照" + that.packageData.classCount + "节计算单价，以实际课时数量计费，封顶" + that.packageData.classCount + "节"
            }
            this.packageData.sdaduimId = this.sdaduimId;
            var url = $.stringFormat("{0}/frPrivatePackage/addOrUpdate", $.cookie('url'))
            axios.post(url, this.packageData).then(function (res) {
                var resData = eval(res);
                if (res.data.code != 500) {
                    that.addPrivate = false;
                    that.selectRelation = {
                        shopItem: {},
                        courseObj: [],
                        courseIds: [],
                    },
                        that.canUsingItem = [];
                    that.packageData = {
                        validType: 1,
                    }
                    that.getPackageList({curPage: that.packageCource.pageNo, limit: that.packageCource.pageSize});
                    $('.canUsingCls:checked').each(function () {
                        $(this).attr("checked", false);
                    });
                    try {
                        $('#area_tree').treeview('uncheckNode', [that.treeNode, {silent: true}]);
                    } catch (e) {
                        //console.info(e)
                    }
                }
                Loading.prototype.hide();
                $.alert(resData['data']['msg']);
            })
                .catch(function (error) {
                    //隐藏加载中
                    console.info(error)
                    Loading.prototype.hide();
                    $.alert(error)
                });
            console.info(this.packageData)
        },
        isNoEmpty: function (value) {
            if (!isNaN(value))
                return true;

            return value && value.length != 0
        },
        getPackageList: function (params) {
            let that = this;
            that.temp = 1;
            var options = $("#shopSelect option:selected");
            if (options.val() != null && options.val() != '') {
                params.shopId = options.val();
            }
            params.sdaduimId = this.sdaduimId;
            let url = $.stringFormat("{0}/frPrivatePackage/list", $.cookie('url'));
            // Loading.prototype.show();
            axios.get(url, {params: params})
                .then(function (res) {
                    if (res.data.code == 200) {
                        that.packageCource.list = res.data.data.list;
                        that.packageCource.pageNo = res.data.data.currPage;
                        that.packageCource.pageSize = res.data.data.pageSize;
                        that.packageCource.totalCount = res.data.data.totalCount;
                        new myPagination({
                            id: 'pagination1',
                            curPage: res.data.data.currPage, //初始页码
                            pageTotal: res.data.data.totalPage, //总页数
                            pageAmount: res.data.data.pageSize,  //每页多少条
                            dataTotal: res.data.data.totalCount, //总共多少条数据
                            showPageTotalFlag: true, //是否显示数据统计
                            showSkipInputFlag: true, //是否支持跳转
                            getPage: function (page) {
                                //获取当前页数
                                that.getPackageList({page: page, limit: res.data.data.pageSize});
                            }
                        })
                    } else {
                        $.alert("服务器异常，获取数据失败")
                    }
                    Loading.prototype.hide();
                })
                .catch(function (error) {
                    console.info(error)
                    Loading.prototype.hide();
                    $.alert("服务器异常，获取数据失败")
                });

            url = $.stringFormat("{0}/frPrivatePackageRelation/shopList", $.cookie('url'));
            axios.get(url, {params: params})
                .then(function (res) {
                    if (res.data.code == 200) {
                        that.areaTree(res.data.data);
                    } else {
                        $.alert("服务器异常，获取数据失败")
                    }
                    Loading.prototype.hide();
                })
                .catch(function (error) {
                    console.info(error)
                    Loading.prototype.hide();
                    $.alert("服务器异常，获取数据失败")
                });

            url = $.stringFormat("{0}/frPrivateCource/list", $.cookie('url'));
            axios.get(url, {params: params})
                .then(function (res) {

                    if (res.data.code == 200) {
                        // $.each(res.data.data.list, function (i, n) {
                        //     that.packagePrivateCourse.list.push(n)
                        // })
                        that.packagePrivateCourse.list = res.data.data.list;

                        that.packagePrivateCourse.pageNo = res.data.data.currPage;
                        that.packagePrivateCourse.pageSize = res.data.data.pageSize;
                        that.packagePrivateCourse.totalCount = res.data.data.totalCount;
                        new myPagination({
                            id: 'pagination2',
                            curPage: res.data.data.currPage, //初始页码
                            pageTotal: res.data.data.totalPage, //总页数
                            pageAmount: res.data.data.pageSize,  //每页多少条
                            dataTotal: res.data.data.totalCount, //总共多少条数据
                            showPageTotalFlag: true, //是否显示数据统计
                            showSkipInputFlag: true, //是否支持跳转
                            getPage: function (page) {
                                //获取当前页数
                                that.initPrivateCource({
                                    page: page,
                                    limit: res.data.data.pageSize,
                                    sdaduimId: this.sdaduimId
                                }, 1);
                            }
                        })
                    } else {
                        $.alert("服务器异常，获取数据失败")
                    }
                    Loading.prototype.hide();
                })
                .catch(function (error) {
                    console.info(error)
                    Loading.prototype.hide();
                    $.alert("服务器异常，获取数据失败")
                });
        },
        selectDefault: function () {
            let item = this.privateSingleCourse.subSeriesList[this.privateSingleCourse.itemIndex];
            if (item) {
                this.privateCourceData.courseName = item.name;
                this.privateCourceData.trainingSeriesIds = item.id;
                $('#defaultCurriculumPlanModal').modal('hide');
                $("#privatemrkcjh").val(this.privateCourceData.courseName);
            } else {
                $.alert("请选中训练课程");
                return;
            }
        },
        getPrivateList: function (id, index) {
            let url = $.stringFormat("{0}/frPrivateCource/getActionsByseriesId", $.cookie('url')), that = this;
            // Loading.prototype.show();
            axios.get(url, {params: {type: 1, traningSeriesId: id}})
                .then(function (res) {
                    let resData = eval(res);
                    if (resData['data']['code'] === '200') {
                        that.privateSingleCourse.items = resData['data']['data'];
                    } else {
                        $.alert(resData['data']['msg']);
                    }
                    that.privateSingleCourse.itemIndex = index;
                    Loading.prototype.hide();
                })
                .catch(function (error) {
                    Loading.prototype.hide();
                    $.alert(error);
                });
        },
        findChild: function (parantId, index) {
            let url = $.stringFormat("{0}/frTrainingSeries/list", $.cookie('url')), that = this;

            axios.get(url, {params: {type: 1, parentId: parantId, ownType: 1, sdaduimId: this.sdaduimId}})
                .then(function (res) {
                    let resData = eval(res);
                    if (resData['data']['code'] === '200') {
                        that.privateSingleCourse.subSeriesList = resData['data']['data'];
                    } else {
                        $.alert(resData['data']['msg']);
                    }
                    Loading.prototype.hide();
                    that.privateSingleCourse.showIndex = index;
                })
                .catch(function (error) {
                    Loading.prototype.hide();
                    $.alert(error);
                });
        },
        deleteAction: function (id) {
            let that = this;
            $.confirm({
                title: '确认',
                content: '确认删除?',
                buttons: {
                    ok: {
                        text: '确认',
                        btnClass: 'btn-primary',
                        action: function () {
                            const url = $.stringFormat("{0}/frPrivateCource/deleteAction", $.cookie('url'));
                            axios.get(url, {params: {id: id}})
                                .then(function (res) {
                                    let resData = eval(res);
                                    if (res.data.code != 500) {
                                        that.initPrivateCource({
                                            curPage: that.privateCourceData.pageNo,
                                            limit: that.privateCourceData.pageSize,
                                            sdaduimId: this.sdaduimId
                                        })
                                    }
                                    $.alert(resData['data']['msg']);
                                }).catch(function (error) {
                                //隐藏加载中
                                // Loading.prototype.hide();
                                $.alert(error)
                            });
                            $('#deleteActionModal').modal('hide');
                        }
                    },
                    cancel: {
                        text: '取消',
                        btnClass: 'btn-primary'
                    }
                }
            });
        },
        //头像url拼接
        avatarUrl: function (avatarLink) {
            if (avatarLink === null || avatarLink === '' || typeof avatarLink === 'undefined') {
                return 'img/addImg.png';  //默认图片
            }
            return $.stringFormat("{0}/{1}/" + avatarLink, $.cookie('url'), $.cookie('imgPath'));
        },
        updateCourse: function (obj) {
            this.tempImg = 2;
            if (obj.validTimeType != 1) {
                obj.dayAndMounth = obj.validTimeType;
            }
            if (this.isNoEmpty(obj.classSchedulingType)) {
                if (obj.classSchedulingType == 1)
                    obj.classSchedulingBL = obj.classScheduling
                else
                    obj.classSchedulingJY = obj.classScheduling
            }
            if (this.isNoEmpty(obj.assignTeacherType)) {
                if (obj.assignTeacherType == 1)
                    obj.assignTeacherBL = obj.assignTeacher
                else
                    obj.assignTeacherJY = obj.assignTeacher
            }
            if (this.isNoEmpty(obj.privateImage)) {
                this.imgUrl = $.stringFormat('{0}{1}{2}', $.cookie('url'), $.cookie('imgPath'), obj.privateImage);
            }
            if (obj.validTimeType != 1) {
                obj.validTimeType1 = 2;
                obj.dayAndMounth = obj.validTimeType;
            } else {
                obj.validTimeType1 = 1;
                obj.dayAndMounth = 2;
            }

            this.privateCourceData = obj;
            this.addPrivateCourse = !this.addPrivateCourse;
            if (this.addPrivateCourse && !this.editor) this.initEdit();
            this.editor.txt.html(obj.classInfo);
        },
        saveOrUpdatePrivateAction: function () {
            console.info(this.privateCourceData)
            console.info(this.editor.txt.html())

//          if(!this.isNoEmpty(this.privateCourceData.trainingSeriesIds) || this.privateCourceData.trainingSeriesIds==""){
//            errMsg += "默认课程计划必填<br/>"
//          }
//          if(this.editor.txt.text()==""){
//              errMsg += "课程详情必填<br/>"
//          }

            //拼接数据
            this.privateCourceData.classInfo = this.editor.txt.html();
            this.privateCourceData.classScheduling = this.privateCourceData.classSchedulingType == 1 ?
                this.privateCourceData.classSchedulingBL : this.privateCourceData.classSchedulingJY;
            this.privateCourceData.assignTeacher = this.privateCourceData.assignTeacherType == 1 ?
                this.privateCourceData.assignTeacherBL : this.privateCourceData.assignTeacherJY;
            if (this.privateCourceData.validTimeType1 == 2) {//不是与卡同期
                this.privateCourceData.validTimeType = this.privateCourceData.dayAndMounth;
            } else {
                this.privateCourceData.validTimeType = 1;
            }
            delete this.privateCourceData.dayAndMounth;
            delete this.privateCourceData.classSchedulingBL;
            delete this.privateCourceData.classSchedulingJY;
            delete this.privateCourceData.assignTeacherJY;
            delete this.privateCourceData.assignTeacherBL;
            delete this.privateCourceData.validTimeType1;
            this.privateCourceData.sdaduimId = this.sdaduimId
            let url = $.stringFormat("{0}/frPrivateCource/addOrUpdate", $.cookie('url')), that = this;
            axios.post(url, this.privateCourceData).then(function (res) {
                let resData = eval(res);
                if (res.data.code != 500) {
                    that.privateCourceData = {
                        dayAndMounth: 2,
                        courseName: "",
                        remainCourceNum: 0,
                        classSchedulingType: ''
                    };
                    that.privateCourceData.dayAndMounth = 2;
                    that.initPrivateCource({
                        curPage: that.privateCourceData.pageNo,
                        limit: that.privateCourceData.pageSize,
                        sdaduimId: that.sdaduimId
                    });
                    that.editor.txt.html("");
                    that.imgUrl = null
                }
                that.addPrivateCourse = false;
                $.alert(resData['data']['msg']);
            })
                .catch(function (error) {
                    //隐藏加载中
                    console.info(error)
                    Loading.prototype.hide();
                    $.alert(error)
                });

        },
        biliInput: function (obj, type) {
            obj.classSchedulingType = type + "";
        },
        biliInput1: function (obj, type) {
            obj.assignTeacherType = type;
        },
        /**
         * 上传图片
         */
        uploadAvatar: function () {
            var that = this;
            var url = $.stringFormat('{0}/file/upload', $.cookie('url'));
            //判断是否有图片
            if (that.imgFile == null || that.imgFile == 'null' || that.imgFile == '') {
                $.alert("请选择图片");
            }
            //判断图片大小是否超过5MB
            if (that.imgFile.size > 1048576) {
                $.alert("图片大小不得大于1MB");
            }
            // Loading.prototype.show();
            var param = new FormData();
            param.append('file', that.imgFile); //通过append向form对象添加数据
            param.append('childPath', 'avatar/'); //通过append向form对象添加数据
            var config = {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            };
            //上传图片
            axios.post(url, param, config).then(function (response) {

                var jsonData = eval(response);
                console.log(response)
                console.log(response.data.code)
                Loading.prototype.hide();
                $.alert(jsonData['data']['data']['msg'])
                that.privateCourceData.privateImage = jsonData['data']['data']['imgUrl'];
                that.imgUrl = $.stringFormat('{0}{1}{2}', $.cookie('url'), $.cookie('imgPath'), jsonData['data']['data']['imgUrl']);
                if (response.data.code == 200 || response.data.code == '200') {
                    that.imgFile = null;//清空条件
                }

            })
        },
        savePrivateClass: function () {
            var that = this;
            var errMsg = "";
            if (!this.isNoEmpty(this.privateCourceData.name) || this.privateCourceData.name == "") {
                errMsg += "私教名称必填<br/>"
            }
            if (!this.isNoEmpty(this.privateCourceData.time) || this.privateCourceData.time == "") {
                errMsg += "私教时长必填<br/>"
            }
            if (!this.isNoEmpty(this.privateCourceData.marketPrice) || this.privateCourceData.marketPrice == "") {
                errMsg += "市场价必填<br/>"
            }
            if (!this.isNoEmpty(this.privateCourceData.memberPrice) || this.privateCourceData.memberPrice == "") {
                errMsg += "会员价必填<br/>"
            }
//          if(!this.isNoEmpty(this.privateCourceData.promotionPrice) || this.privateCourceData.promotionPrice==""){
//              errMsg += "促销价必填<br/>"
//          }
            if (!this.isNoEmpty(this.privateCourceData.validTimeType1) || this.privateCourceData.validTimeType1 == "") {
                errMsg += "有效期必填<br/>"
            }
            if (!this.isNoEmpty(this.privateCourceData.isAccountSpending)) {
                errMsg += "是否挂载消费必填<br/>"
            }
            if (!this.isNoEmpty(this.privateCourceData.isShowDesk)) {
                errMsg += "是否前台显示必填<br/>"
            }
            if (errMsg.length != 0) {
                $.alert(errMsg);
                return;
            }
            console.info("课程详情:" + this.editor.txt.text());
            var url = $.stringFormat('{0}/file/upload', $.cookie('url'));
            //判断是否有图片
            if (this.tempImg == 1) {
                if (that.imgFile == null || that.imgFile == 'null' || that.imgFile == '') {
                    $.alert("请选择图片");
                }
                //判断图片大小是否超过5MB
                if (that.imgFile.size > 1048576) {
                    $.alert("图片大小不得大于1MB");
                }
                // Loading.prototype.show();
                var param = new FormData();
                param.append('file', that.imgFile); //通过append向form对象添加数据
                param.append('childPath', 'avatar/'); //通过append向form对象添加数据
                var config = {
                    headers: {
                        'Content-Type': 'multipart/form-data'
                    }
                };
                //上传图片
                axios.post(url, param, config).then(function (response) {
                    var jsonData = eval(response);
                    Loading.prototype.hide();
                    that.privateCourceData.privateImage = jsonData['data']['data']['imgUrl'];
                    that.imgUrl = $.stringFormat('{0}{1}{2}', $.cookie('url'), $.cookie('imgPath'), jsonData['data']['data']['imgUrl']);
                    if (response.data.code == 200 || response.data.code == '200') {
                        that.imgFile = null;//清空条件
                        $("#privateFile").val('')
                        that.saveOrUpdatePrivateAction();
                    }

                })


            } else if (this.tempImg == 2) {
                if (that.imgFile == null || that.imgFile == 'null' || that.imgFile == '') {
                    that.imgUrl = $("#addImg")[0].src;
                    that.saveOrUpdatePrivateAction();
                }else {
                    // Loading.prototype.show();
                    var param = new FormData();
                    param.append('file', that.imgFile); //通过append向form对象添加数据
                    param.append('childPath', 'avatar/'); //通过append向form对象添加数据
                    var config = {
                        headers: {
                            'Content-Type': 'multipart/form-data'
                        }
                    };
                    //上传图片
                    axios.post(url, param, config).then(function (response) {

                        var jsonData = eval(response);
                        console.log(response)
                        console.log(response.data.code)
                        Loading.prototype.hide();
                        that.privateCourceData.privateImage = jsonData['data']['data']['imgUrl'];
                        that.imgUrl = $.stringFormat('{0}{1}{2}', $.cookie('url'), $.cookie('imgPath'), jsonData['data']['data']['imgUrl']);
                        if (response.data.code == 200 || response.data.code == '200') {
                            that.imgFile = null;//清空条件
                            $("#privateFile").val('')
                            that.saveOrUpdatePrivateAction();
                        }

                    })

                }


            }


        },
        /**
         * 选择完成后显示图片
         * @param e
         */
        pictureShowVersion: function (e) {
            let that = this;
            let file = e.srcElement.files.item(0);
            // debugger
            // 看支持不支持FileReader
            if (!file || !window.FileReader) return;
            that.imgFile = file
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
        initPrivateCource: function (params, flag) {
            let that = this;
            let url = $.stringFormat("{0}/frPrivateCource/list", $.cookie('url'));
            // Loading.prototype.show();
            axios.get(url, {params: params})
                .then(function (res) {
                    if (res.data.code == 200) {
                        if (flag == 1) {
                            that.packagePrivateCourse.list = res.data.data.list;
                            that.packagePrivateCourse.pageNo = res.data.data.currPage;
                            that.packagePrivateCourse.pageSize = res.data.data.pageSize;
                            that.packagePrivateCourse.totalCount = res.data.data.totalCount;
                            new myPagination({
                                id: 'pagination2',
                                curPage: res.data.data.currPage, //初始页码
                                pageTotal: res.data.data.totalPage, //总页数
                                pageAmount: res.data.data.pageSize,  //每页多少条
                                dataTotal: res.data.data.totalCount, //总共多少条数据
                                showPageTotalFlag: true, //是否显示数据统计
                                showSkipInputFlag: true, //是否支持跳转
                                getPage: function (page) {
                                    //获取当前页数
                                    that.initPrivateCource({
                                        page: page, limit: res.data.data.pageSize,
                                        sdaduimId: that.sdaduimId
                                    }, 1);
                                }
                            })
                        } else {
                            that.privateCource.list = res.data.data.list;
                            that.privateCource.pageNo = res.data.data.currPage;
                            that.privateCource.pageSize = res.data.data.pageSize;
                            that.privateCource.totalCount = res.data.data.totalCount;
                            new myPagination({
                                id: 'pagination',
                                curPage: res.data.data.currPage, //初始页码
                                pageTotal: res.data.data.totalPage, //总页数
                                pageAmount: res.data.data.pageSize,  //每页多少条
                                dataTotal: res.data.data.totalCount, //总共多少条数据
                                showPageTotalFlag: true, //是否显示数据统计
                                showSkipInputFlag: true, //是否支持跳转
                                getPage: function (page) {
                                    //获取当前页数
                                    that.initPrivateCource({
                                        page: page, limit: res.data.data.pageSize,
                                        sdaduimId: that.sdaduimId
                                    });
                                }
                            })
                        }
                    } else {
                        $.alert("服务器异常，获取数据失败")
                    }
                    Loading.prototype.hide();
                })
                .catch(function (error) {
                    console.info(error)
                    Loading.prototype.hide();
                    $.alert("服务器异常，获取数据失败")
                });
        },
        actionSettingMain: function () {
            this.actionSettingIndex = 0;
            let that = this;

            // let url = "http://localhost:8080/frActionSeries/list";
            let url = $.stringFormat("{0}/frActionSeries/list?ownType=1&sdaduimId=" + this.sdaduimId, $.cookie('url'));
            // Loading.prototype.show();
            axios.get(url)
                .then(function (res) {
                    let resData = eval(res);
                    if (resData['data']['code'] === '200') {
                        that.actions = resData['data']['data']['seriesList'];
                        that.actions[0].actionList = resData['data']['data']['actionList'];
                    } else {
                        $.alert(resData['data']['msg']);
                    }
                    Loading.prototype.hide();
                })
                .catch(function (error) {
                    Loading.prototype.hide();
                    $.alert(error);
                });
        },

        //动作设置页面 动作系列的点击事件
        actionClick: function (index) {


            let seriesId = this.actions[index].id;
            // $(event.target).addClass('active').siblings().removeClass('active'); 不可用  事件会传递

            let that = this;
            that.isok = false;

            let url = $.stringFormat("{0}/frActionSeries/actionList", $.cookie('url'));
            let data = {
                seriesId: seriesId,
            };
            axios.get(url, {params: data})
                .then(function (res) {
                    let resData = eval(res);
                    if (resData['data']['code'] === '200') {

                        that.actions[index].actionList = resData['data']['data'];
                        that.actionSettingIndex = index;
                        that.changeActionIds = []
                    } else {
                        $.alert(resData['data']['msg']);
                    }
                })
                .catch(function (error) {
                    $.alert(error);
                });


        },

        //动作设置-----动作系列  -----增加动作
        ActionSettingAddAction: function (index) {
            if (!this.actionAddData.name || this.actionAddData.name == '') {
                $.alert('名称不能为空');
                return;
            }
            if (!this.actionAddData.diff || this.actionAddData.diff == '') {
                $.alert('难度不能为空');
                return;
            }

            /*   if(!this.actionAddData.actionPrinceple || this.actionAddData.actionPrinceple ==''){
                 $.alert('动作效果原理不能为空');
                 return;
               }*/
            if (!this.actionAddData.image || this.actionAddData.image == '') {
                $.alert('动作图片不能为空');
                return;
            }


            let that = this;
            $("#addActionImg" + index).attr('src', 'img/addImg.png');

            let url = $.stringFormat("{0}/frAction/addOrUpdate", $.cookie('url'));
            let data = this.actionAddData;
            data.seriesId = that.actions[that.actionSettingIndex].id;
            //  // Loading.prototype.show();
            // ajax提交

            data.ownType = 1;
            axios.post(url, data)
                .then(function (res) {
                    let resData = eval(res);
                    if (res.data.code != 200)
                        return;
                    that.actionAddData = {};
                    let newVar = resData['data']['data'];
                    if (newVar.id) {
                        that.actions[that.actionSettingIndex].actionList.push(newVar)
                    }
                    $.alert(resData['data']['msg']);
                    console.log($("#ActionFile").val())
                    $("#ActionFile").val('')
                })
                .catch(function (error) {
                    //隐藏加载中
                    // Loading.prototype.hide();
                    $.alert(error)
                });


        },

        changeParent: function () {
            console.info(this.changeActionSelect)
            console.info(this.changeActionIds)
            if (!this.changeActionSelect) {
                $.alert('请选择对应的动作系列')
                return;
            }
            if (this.changeActionIds.length == 0) {
                $.alert('请选择对应的动作')
                return;
            }
            if (this.actions[this.actionSettingIndex].id == this.changeActionSelect) {
                $.alert('已经在对应的系列中 无需再次移动')
                return;
            }


            let that = this;


            let url = $.stringFormat("{0}/frAction/batchFrAction", $.cookie('url'));
            let data = {
                actionIds: this.changeActionIds.join(","),
                seriesId: this.changeActionSelect,
            }
            //  // Loading.prototype.show();
            // ajax提交
            axios.get(url, {params: data})
                .then(function (res) {
                    let resData = eval(res);

                    //删除已选中的id
                    for (var i = 0; i < that.actions[that.actionSettingIndex].actionList.length; i++) {
                        let newVar = that.actions[that.actionSettingIndex].actionList[i];
                        if (that.changeActionIds.indexOf(newVar.id) > -1) {
                            that.actions[that.actionSettingIndex].actionList.splice(i, 1);
                            i--;
                        }
                    }

                    that.actions.splice(0, 0);

                })
                .catch(function (error) {
                    //隐藏加载中
                    // Loading.prototype.hide();
                    $.alert(error)
                });

        },

        //删除动作
        delAction: function (index, actionType) {
            let that = this;
            let url = $.stringFormat("{0}/frAction/addOrUpdate", $.cookie('url'));
            actionType.isUsing = 0;
            $.confirm({
                title: '确认',
                content: '确认删除?',
                buttons: {
                    ok: {
                        text: '确认',
                        btnClass: 'btn-primary',
                        action: function () {
                            // ajax提交
                            axios.post(url, actionType)
                                .then(function (res) {
                                    let resData = eval(res);
                                    that.actions[that.actionSettingIndex].actionList.splice(index, 1);
                                    that.actions.splice(0, 0);
                                }).catch(function (error) {
                                //隐藏加载中
                                // Loading.prototype.hide();
                                $.alert(error)
                            });
                        }
                    },
                    cancel: {
                        text: '取消',
                        btnClass: 'btn-primary'
                    }
                }
            });
        },

        //修改动作
        modifyActionConfirm: function () {


            let length = this.actions[this.actionSettingIndex].actionList.length;
            let number = -1;
            for (let i = 0; i < length; i++) {
                if (this.actionSettingData.id == this.actions[this.actionSettingIndex].actionList[i].id) {
                    number = i;
                    break;

                }
            }

            console.info("=================num=" + number)
            if (number == -1) {
                $('#modifyActionModal').modal('hide')
                $.alert('发生未知错误')
                return;
            }
            let that = this;


            let url = $.stringFormat("{0}/frAction/addOrUpdate", $.cookie('url'));
            let data = this.actionSettingData;

            if (!data.name || data.name == '') {
                $.alert('动作名称不能为空');
                return;
            }
            ;
            if (!data.diff || data.diff == '') {
                $.alert('动作难度不能为空');
                return;
            }
            ;
            data.ownType = 1;
            axios.post(url, data)
                .then(function (res) {
                    let resData = eval(res);
                    that.actionAddData = {};
                    let newVar = resData['data']['data'];
                    if (newVar.id) {
                        that.actions[that.actionSettingIndex].actionList.splice(number, 1, newVar)
                    }
                    that.actionSettingData = {};
                    $('#modifyActionModal').modal('hide')
                    $("#ActionFlie2").val('')

                })
                .catch(function (error) {
                    //隐藏加载中
                    // Loading.prototype.hide();
                    $.alert(error)
                });

        },


        //显示图片
        /**
         *
         * @param e
         * @param index  用addActionImg+index 来确认需要修改的img
         * @param object 记录url对像
         */
        pictureShow: function (e, index, object) {
            var that = this;
            var file = e.srcElement.files.item(0);

            // 看支持不支持FileReader
            if (!file || !window.FileReader) return;
            if (/^image/.test(file.type)) {
                // 创建一个reader
                var reader = new FileReader();
                // 将图片将转成 base64 格式
                reader.readAsDataURL(file);
                // 读取成功后的回调
                reader.onloadend = function () {
                    $("#addActionImg" + index).attr('src', this.result);


                    let url = $.stringFormat("{0}/file/upload", $.cookie('url'));
                    var param = new FormData();

                    param.append('file', file); //通过append向form对象添加数据
                    param.append('childPath', 'action/'); //通过append向form对象添加数据
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
                                object.image = $.stringFormat('{0}{1}{2}', $.cookie('url'), $.cookie('imgPath'), res.data.imgUrl);
                                console.info(res)
                                console.info(that.actionAddData)
                            } else {
                                alert(res.msg)
                            }


                        }
                    });
                }
            }
        },


        //单节训练课程设置页面 动作系列的点击事件
        actionEduClick: function (index, parentId, type) {
            console.info("===============actionEduClick")
            // $(event.target).addClass('active').siblings().removeClass('active'); 不可用  事件会传递

            let that = this;
            let url = $.stringFormat("{0}/frTrainingSeries/list", $.cookie('url'));
            let data = {
                type: type,
                parentId: parentId,
                ownType: 1, sdaduimId: this.sdaduimId
            };

            axios.get(url, {params: data})
                .then(function (res) {
                    let resData = eval(res);
                    if (resData['data']['code'] === '200') {
                        if (type == 1) {
                            that.simpleLinkSettingActionSub = resData['data']['data'];
                            that.actionEduSettingIndex = index;
                        } else if (type == 2) {
                            that.mealLinkSettingActionSub = resData['data']['data'];
                            that.mealSettingIndex = index
                        }


                    } else {
                        $.alert(resData['data']['msg']);
                    }
                })
                .catch(function (error) {
                    $.alert(error);
                });


        },

        addActionSeries: function () {
            let that = this;

            if (!this.addActionSeriesName || this.addActionSeriesName == '') {
                $.alert('名称不能为空');
                return;
            }

            let jsonData = {
                name: String(this.addActionSeriesName),
                isUsing: 1,
                type: this.addSeriesType,

            };
            let url = '';
            if (this.addSeriesType == 0) {
                url = $.stringFormat("{0}/frActionSeries/addFrActionSeries", $.cookie('url'));
            } else {
                url = $.stringFormat("{0}/frTrainingSeries/addOrUpdate", $.cookie('url'));
            }
            jsonData.ownType = 1;
            jsonData.sdaduimId=that.sdaduimId
            //  // Loading.prototype.show();
            // ajax提交
            axios.post(url, jsonData)
                .then(function (res) {
                    let resData = eval(res);
                    if (that.addSeriesType == 0) {
                        that.actions.push(resData['data']['data']);
                    } else if (that.addSeriesType == 1) {
                        that.simpleLinkSettingAction.push(resData['data']['data']);
                    } else if (that.addSeriesType == 2) {
                        that.mealLinkSettingAction.push(resData['data']['data'])
                    }
                    that.addActionSeriesName = '';
                    $('#addActionModal').modal('hide');
                    //$.alert(resData['data']['msg']);

                })
                .catch(function (error) {
                    //隐藏加载中
                    // Loading.prototype.hide();
                    $.alert(error)
                });
        },

        cancelActionSeries: function () {
            this.addActionSeriesName = '';
        },

        //单节训练课程设置 主要请求
        simpleLinkSettingMain: function (type) {
            if (type == 1) {
                this.actionEduSettingIndex = 0;
            } else {
                this.mealSettingIndex = 0
            }
            let that = this;

            // let url = "http://localhost:8080/frTrainingSeries/list";
            let url = $.stringFormat("{0}/frTrainingSeries/seriesAndActionList", $.cookie('url'));
            let data = {
                type: type,
                ownType: 1,
                sdaduimId: this.sdaduimId
            };

            axios.get(url, {params: data})
                .then(function (res) {
                    let resData = eval(res);
                    if (resData['data']['code'] === '200') {
                        if (type == 1) {
                            that.simpleLinkSettingAction = resData['data']['data']['seriesList'];
                            that.simpleLinkSettingActionSub = resData['data']['data']['subSeriesList'];
                            // that.simpleLinkSettingActionSubTable = resData['data']['data']['classDtoList'];
                        } else if (type == 2) {
                            that.mealLinkSettingAction = resData['data']['data']['seriesList'];
                            that.mealLinkSettingActionSub = resData['data']['data']['subSeriesList'];
                        }
                    } else {
                        $.alert(resData['data']['msg']);
                    }
                })
                .catch(function (error) {
                    $.alert(error);
                });

        },


        addTrain: function (parentId, type) {
            this.addTrainParentId = parentId;
            this.addTrainType = type;
            $('#addTrainModal').modal('show');
        },
        updateItem: function (itemId, type) {
            this.updateItemId = itemId;
            this.updateType = type;
            $('#updateItemModal').modal('show');
        },
        updateTrain: function (id, type) {
            //this.
            this.updateTrainId = id,
                this.updateTrainType = type,
                $('#updateTrainModal').modal('show');
        },
        deleteItem: function (index, id, type) {
            var that = this;
            var url = $.stringFormat("{0}/frTrainingSeries/list", $.cookie('url'));
            var data = {
                type: type,
                parentId: id,
                ownType: 1, sdaduimId: this.sdaduimId
            };
            this.deleteItemId = id,
                this.deletItemType = type,


                axios.get(url, {params: data})
                    .then(function (res) {
                        let resData = eval(res);
                        if (resData['data']['code'] === '200') {
                            if (type == 1) {
                                that.simpleLinkSettingActionSub = resData['data']['data'];
                                that.actionEduSettingIndex = index;
                            }


                        } else {
                            $.alert(resData['data']['msg']);
                        }
                    })
                    .catch(function (error) {
                        $.alert(error);
                    });


            $('#deleteItemModal').modal('show');
        },

        deleteTrain: function (id, type) {
            this.deleteTrainId = id,
                this.deletTrainType = type,
                $('#deleteTrainModal').modal('show');
        },

        addTrainConfirm: function () {
            if (!this.addTrainName || this.addTrainName == '') {
                $.alert('名称不能为空');
                return;
            }


            let that = this;

            let url = $.stringFormat("{0}/frTrainingSeries/addOrUpdate", $.cookie('url'));

            let jsonData = {
                name: String(this.addTrainName),
                isUsing: 1,
                type: this.addTrainType,
                parentId: this.addTrainParentId,
                ownType: 1
            };
            //  // Loading.prototype.show();
            // ajax提交
            axios.post(url, jsonData)
                .then(function (res) {
                    let resData = eval(res);
                    if (that.addTrainType == 1) {
                        that.simpleLinkSettingActionSub.push(resData['data']['data']);
                    } else if (that.addTrainType == 2) {
                        that.mealLinkSettingActionSub.push(resData['data']['data']);
                    }
                    $('#addTrainModal').modal('hide');
                    that.addTrainName = '';
                    that.addTrainType = '';
                    that.addTrainParentId = '';


                })
                .catch(function (error) {
                    //隐藏加载中
                    // Loading.prototype.hide();
                    $.alert(error)
                });

        },
        deleteItemConfirm: function () {
            let that = this;
            if (this.deletItemType == 3) {
                var url = $.stringFormat("{0}/frTrainingSeries/updateAction", $.cookie('url'));
                var jsonData = {
                    id: this.deleteItemId,
                    isUsing: 0,
                };
            } else {
                var url = $.stringFormat("{0}/frTrainingSeries/addOrUpdate", $.cookie('url'));
                var jsonData = {
                    id: this.deleteItemId,
                    isUsing: 0,
                    type: this.deletItemType,
                    ownType: 1,
                };
            }

            //  // Loading.prototype.show();
            // ajax提交
            axios.post(url, jsonData)
                .then(function (res) {
                    let resData = eval(res);
                    // console.log(that.simpleLinkSettingActionSub);
                    if (that.deletItemType == 0) {
                        that.actions.push(resData['data']['data']);
                    } else if (that.deletItemType == 1) {
                        for (var i in that.simpleLinkSettingAction) {
                            if (that.simpleLinkSettingAction[i].id == resData.data.data.id) {
                                that.simpleLinkSettingAction.splice(i, 1);
                                that.simpleLinkSettingActionSub = [];
                                // that.actionEduSettingIndex = 0;
                                if (that.simpleLinkSettingAction.length > 0) {
                                    that.actionEduClick(0, that.simpleLinkSettingAction[0].id, 1);
                                }
                                // that.actionEduClick(0,this.deleteItemId,1);
                            }
                        }
                        //console.log(that.simpleLinkSettingAction);
                    } else if (that.deletItemType == 2) {

                        for (var i in that.mealLinkSettingAction) {
                            if (that.mealLinkSettingAction[i].id == resData.data.data.id) {
                                that.mealLinkSettingAction.splice(i, 1);
                                that.mealLinkSettingActionSub = [];
                                // that.actionEduSettingIndex = 0;
                                if (that.mealLinkSettingAction.length > 0) {
                                    that.actionEduClick(0, that.mealLinkSettingAction[0].id, 2);
                                }
                                // that.actionEduClick(0,this.deleteItemId,1);
                            }
                        }


                    } else if (that.deletItemType == 3) {
                        for (var i in that.actions) {
                            if (that.actions[i].id == resData.data.data.id) {
                                that.actions.splice(i, 1);
                                $('#deleteItemModal').modal('hide');
                                return false;
                            }
                        }
                    }
                    //    that.updateItemName ='';
                    $('#deleteItemModal').modal('hide');
                    //$.alert(resData['data']['msg']);

                })
                .catch(function (error) {
                    //隐藏加载中
                    // Loading.prototype.hide();
                    $.alert(error)
                });


        },

        deleteTrainConfirm: function () {
            let that = this;

            let url = $.stringFormat("{0}/frTrainingSeries/addOrUpdate", $.cookie('url'));

            let jsonData = {
                id: this.deleteTrainId,
                isUsing: 0,
                type: this.deletTrainType,
                ownType: 1
            };
            //  // Loading.prototype.show();
            // ajax提交
            axios.post(url, jsonData)
                .then(function (res) {
                    let resData = eval(res);
                    if (that.deletTrainType == 0) {
                        that.actions.push(resData['data']['data']);
                    } else if (that.deletTrainType == 1) {
                        for (var i in that.simpleLinkSettingActionSub) {
                            if (that.simpleLinkSettingActionSub[i].id == resData.data.data.id) {
                                that.simpleLinkSettingActionSub.splice(i, 1);
                                if (that.simpleLinkSettingActionSub.length > 0) {
                                    that.subTrainClick(that.simpleLinkSettingActionSub[0], 1);
                                }
                            }
                        }
                        console.log(that.simpleLinkSettingAction);
                    } else if (that.deletTrainType == 2) {

                        for (var i in that.mealLinkSettingActionSub) {
                            if (that.mealLinkSettingActionSub[i].id == resData.data.data.id) {
                                that.mealLinkSettingActionSub.splice(i, 1);
                                if (that.simpleLinkSettingActionSub.length > 0) {
                                    that.subTrainClick(that.simpleLinkSettingActionSub[0], 2);
                                }
                            }
                        }

                    }
                    //    that.updateItemName ='';
                    $('#deleteTrainModal').modal('hide');
                    //$.alert(resData['data']['msg']);

                })
                .catch(function (error) {
                    //隐藏加载中
                    // Loading.prototype.hide();
                    $.alert(error)
                });
        },

        updateItemConfirm: function () {

            if (!this.updateItemName || this.updateItemName == '') {
                $.alert('名称不能为空');
                return;
            }
            var that = this;
            if (that.updateType == 3) {
                var url = $.stringFormat("{0}/frTrainingSeries/updateAction", $.cookie('url'));
                var jsonData = {
                    id: this.updateItemId,
                    name: String(this.updateItemName),
                };
            } else {
                var url = $.stringFormat("{0}/frTrainingSeries/addOrUpdate", $.cookie('url'));
                var jsonData = {
                    id: this.updateItemId,
                    name: String(this.updateItemName),
                    isUsing: 1,
                    type: this.updateType,
                    ownType: 1
                };
            }

            //  // Loading.prototype.show();
            // ajax提交
            axios.post(url, jsonData)
                .then(function (res) {
                    let resData = eval(res);
                    if (that.updateType == 0) {
                        that.actions.push(resData['data']['data']);
                    } else if (that.updateType == 1) {
                        for (var i in that.simpleLinkSettingAction) {
                            if (that.simpleLinkSettingAction[i].id == resData.data.data.id) {
                                that.simpleLinkSettingAction[i].name = resData.data.data.name
                            }
                        }
                    } else if (that.updateType == 2) {
                        for (var i in that.mealLinkSettingAction) {
                            if (that.mealLinkSettingAction[i].id == resData.data.data.id) {
                                that.mealLinkSettingAction[i].name = resData.data.data.name
                            }
                        }
                        //       that.mealLinkSettingAction.push(resData['data']['data'])
                    } else if (that.updateType == 3) {
                        for (var i in that.actions) {
                            if (that.actions[i].id == resData.data.data.id) {
                                that.actions[i].name = resData.data.data.name
                            }
                        }
                        //       that.mealLinkSettingAction.push(resData['data']['data'])
                    }
                    that.updateItemName = '';
                    $('#updateItemModal').modal('hide');
                    //$.alert(resData['data']['msg']);

                })
                .catch(function (error) {
                    //隐藏加载中
                    // Loading.prototype.hide();
                    $.alert(error)
                });

        },
        updateTrainConfirm: function () {

            if (!this.updateTrainName || this.updateTrainName == '') {
                $.alert('名称不能为空');
                return;
            }
            let that = this;

            let url = $.stringFormat("{0}/frTrainingSeries/addOrUpdate", $.cookie('url'));

            let jsonData = {
                id: this.updateTrainId,
                name: String(this.updateTrainName),
                isUsing: 1,
                type: this.updateTrainType,
                ownType: 1
            };
            //  // Loading.prototype.show();
            // ajax提交
            axios.post(url, jsonData)
                .then(function (res) {
                    let resData = eval(res);
                    if (that.updateTrainType == 0) {
                        that.actions.push(resData['data']['data']);
                    } else if (that.updateTrainType == 1) {
                        //   console.log(that.simpleLinkSettingActionSub);
                        for (var i in that.simpleLinkSettingActionSub) {
                            if (that.simpleLinkSettingActionSub[i].id == resData.data.data.id) {
                                that.simpleLinkSettingActionSub[i].name = resData.data.data.name
                            }
                        }
                        console.log(that.simpleLinkSettingActionSub);
                    } else if (that.updateTrainType == 2) {

                        for (var i in that.mealLinkSettingActionSub) {
                            if (that.mealLinkSettingActionSub[i].id == resData.data.data.id) {
                                that.mealLinkSettingActionSub[i].name = resData.data.data.name
                            }
                        }
                    }
                    that.updateTrainName = '';
                    $('#updateTrainModal').modal('hide');
                    //$.alert(resData['data']['msg']);

                })
                .catch(function (error) {
                    //隐藏加载中
                    // Loading.prototype.hide();
                    $.alert(error)
                });

        },

        cancelTrainAdd: function () {
            this.addTrainName = '';
            this.addTrainType = '';
            this.addTrainParentId = '';

        },

        cancelItemUpate: function () {
            // this.addTrainName ='';
            // this.addTrainType ='';
            // this.addTrainParentId ='';

        },
        canceldeleteItem: function () {


        },
        canceldeleteTrain: function () {

        },
        cancelupdateTrain: function () {
            this.updateTrainId = '';
            this.updateTrainName = '';
            // this.addTrainName ='';
            // this.addTrainType ='';
            // this.addTrainParentId ='';
        },
        /**
         * 左侧列表的子项
         * @param train
         * @param type
         */
        subTrainClick: function (train, type) {
            //点击制空


            if (type == 1) {
                this.simpleLinkSettingActionSubSelectId = train.id;
                this.trainActionChanges = [];
            }
            if (type == 2) {
                this.mealLinkSettingActionSubSelectId = train.id;
                this.mealActionChanges = [];
            }
            let that = this;

            let url = $.stringFormat("{0}/frTraningClass/list", $.cookie('url'));

            let jsonData = {
                traningSeriesId: train.id,
                type: type,
                ownType: 1
            };
            //  // Loading.prototype.show();

            axios.get(url, {params: jsonData})
                .then(function (res) {
                    let resData = eval(res);
                    if (resData['data']['code'] == 200) {
                        if (type == 1) {
                            that.simpleLinkSettingActionSubTable = (resData['data']['data']);
                        } else if (type == 2) {
                            that.mealLinkSettingActionSubTable = (resData['data']['data']);


                        }
                    }


                })
                .catch(function (error) {
                    //隐藏加载中
                    // Loading.prototype.hide();
                    $.alert(error)
                });


        },

        /**
         * 选择动作
         * @param index
         */
        choiceAction: function (index) {
            this.trainAddActionIds = [];
            this.isok = false;
            this.trainActionIndex = index;
            if (!this.actions[index].actionList) {
                this.actionClick(index)
            }
        },

        trainAddAction: function (type) {

            console.log(this.actions);
            let that = this;
            if (that.trainAddActionIds.length < 1) {
                $.alert('未选中动作')
                return
            }
            let url = $.stringFormat("{0}/frTraningClass/batchAddFrTraningClass", $.cookie('url'));
            if (type == 1) {
                var jsonData = {
                    traningSeriesId: this.simpleLinkSettingActionSubSelectId,
                    actionIds: that.trainAddActionIds.join(','),
                    type: type,
                    ownType: 1
                };
            } else if (type == 2) {
                var jsonData = {
                    traningSeriesId: this.mealLinkSettingActionSubSelectId,
                    actionIds: that.trainAddActionIds.join(','),
                    type: type,
                    ownType: 1
                };
            }

            //  // Loading.prototype.show();
            //  jsonData.ownType = 1;
            axios.post(url, jsonData)
                .then(function (res) {
                    let resData = eval(res);
                    if (resData['data']['code'] == 200) {
                        //   $('#addActionTypeModal').modal('hide');

                        if (type == 1) {
                            $('#addActionTypeOneModal').modal('hide');
                            if (resData['data']['data'].length > 0) {
                                for (var i in resData['data']['data']) {
                                    that.simpleLinkSettingActionSubTable.push(resData['data']['data'][i]);
                                }
                                //that.subTrainClick(this.simpleLinkSettingActionSubSelectId,1);
                                console.log(jsonData);

                                let url = $.stringFormat("{0}/frTraningClass/list", $.cookie('url'));

                                let jsonData2 = {
                                    traningSeriesId: jsonData.traningSeriesId,
                                    type: type,
                                    ownType: 1
                                };
                                //  // Loading.prototype.show();

                                axios.get(url, {params: jsonData2})
                                    .then(function (res) {
                                        let resData = eval(res);
                                        if (resData['data']['code'] == 200) {
                                            if (type == 1) {
                                                that.simpleLinkSettingActionSubTable = (resData['data']['data']);
                                            } else if (type == 2) {
                                                that.mealLinkSettingActionSubTable = (resData['data']['data']);
                                            }
                                        }


                                    })
                                    .catch(function (error) {
                                        //隐藏加载中
                                        // Loading.prototype.hide();
                                        $.alert(error)
                                    });

                            }

                        } else {
                            $("#addActionTypeModal").modal('hide')
                            if (resData['data']['data'].length > 0) {
                                for (var i in resData['data']['data']) {
                                    that.mealLinkSettingActionSubTable.push(resData['data']['data'][i]);
                                }

                                console.log(jsonData);

                                let url = $.stringFormat("{0}/frTraningClass/list", $.cookie('url'));

                                let jsonData2 = {
                                    traningSeriesId: jsonData.traningSeriesId,
                                    type: type,
                                    ownType: 1
                                };
                                //  // Loading.prototype.show();

                                axios.get(url, {params: jsonData2})
                                    .then(function (res) {
                                        let resData = eval(res);
                                        if (resData['data']['code'] == 200) {
                                            if (type == 1) {
                                                that.simpleLinkSettingActionSubTable = (resData['data']['data']);
                                            } else if (type == 2) {
                                                that.mealLinkSettingActionSubTable = (resData['data']['data']);
                                            }
                                        }


                                    })
                                    .catch(function (error) {
                                        //隐藏加载中
                                        // Loading.prototype.hide();
                                        $.alert(error)
                                    });
                            }
                        }
                    }


                })
                .catch(function (error) {
                    //隐藏加载中
                    // Loading.prototype.hide();
                    $.alert(error)
                });


        },
        delEduAction: function (index, action, type) {
            let that = this;

            let url = $.stringFormat("{0}/frTraningClass/addOrUpdate", $.cookie('url'));

            let jsonData = {
                id: action.id,
                isUsing: 0,
            };
            jsonData.ownType = 1;

            $.confirm({
                title: '确认',
                content: '确认删除?',
                buttons: {
                    ok: {
                        text: '确认',
                        btnClass: 'btn-primary',
                        action: function () {
                            axios.get(url, {params: jsonData})
                                .then(function (res) {
                                    let resData = eval(res);
                                    if (resData['data']['code'] == 200) {
                                        if (type == 1) {
                                            that.simpleLinkSettingActionSubTable.splice(index, 1);
                                        } else if (type == 2) {
                                            that.mealLinkSettingActionSubTable.splice(index, 1)
                                        }
                                    }


                                })
                                .catch(function (error) {
                                    //隐藏加载中
                                    // Loading.prototype.hide();
                                    $.alert(error)
                                });
                        }
                    },
                    cancel: {
                        text: '取消',
                        btnClass: 'btn-primary'
                    }
                }
            });
        },
        datePickerInit: function (domId, format, isTime) {
            var datePickerOptions = {
                dateCell: "#" + domId, //目标元素。由于jedate.js封装了一个轻量级的选择器，因此dateCell还允许你传入class、tag这种方式 '#id .class'
                format: format, //日期格式
                //minDate:"1900-01-01 00:00:00", //最小日期
                //maxDate:"2099-12-31 23:59:59", //最大日期
                isinitVal: false, //是否初始化时间
                isTime: isTime, //是否开启时间选择
                isClear: true, //是否显示清空
                festival: false, //是否显示节日
                zIndex: 999,  //弹出层的层级高度
                marks: null, //给日期做标注
                choosefun: function (val) {
                },  //选中日期后的回调
                clearfun: function (val) {
                },   //清除日期后的回调
                okfun: function (val) {
                }       //点击确定后的回调
            };
            jeDate(datePickerOptions);
        },
        /*   $("#datepicker").jeDate({
               isinitVal: true,
               festival: true,
               ishmsVal: false,
                format: "YYYY年MM月DD日",
               zIndex: 3000
           });*/



        /**
         * 批量保存动作设置
         * @param type
         */
        saveAction: function (type) {
            /*   if (type == 1 && this.trainActionChanges.length > 1) {

                 return;
               }
               if (type == 2 && this.mealActionChanges.length >1){
                   return;
               }
       */
            let that = this;

            let url = $.stringFormat("{0}/frTraningClass/batchUpdateFrTraningClass", $.cookie('url'));

            let list = this.mealActionChanges || this.trainActionChanges;
            let count = list.length;
            for (let i = 0; i < count; i++) {
                list[i].isUsing = 1;
                list[i].type = type;
                list[i].ownType = 1;
            }

            let jsonData = type == 1 ? this.trainActionChanges : this.mealActionChanges;
            //  // Loading.prototype.show();
            // ajax提交
            axios.post(url, jsonData)
                .then(function (res) {
                    let resData = eval(res);
                    if (resData.data.code == "200") {
                        $.alert('保存成功');
                        return;
                    } else {
                        $.alert('保存失败');
                        return;
                    }
                })
                .catch(function (error) {
                    //隐藏加载中
                    // Loading.prototype.hide();
                    $.alert(error)
                });


        },


        changeData: function (data, type) {
            if (type == 1) {
                if (this.trainActionChanges.indexOf(data) < 0) {
                    this.trainActionChanges.push(data);
                    console.info(this.trainActionChanges)
                }
            }
            if (type == 2) {
                if (this.mealActionChanges.indexOf(data) < 0) {
                    this.mealActionChanges.push(data);
                    console.info(this.mealActionChanges)
                }
            }
        },
        viewModal: function (id, type) {
            this.listViewCanUsingItemPackageId = id;
            this.getInfo(id, type);
        },
//					默认课程计划
        defaultCurriculumPlanModal: function () {
            let url = $.stringFormat("{0}/frTrainingSeries/seriesAndActionList", $.cookie('url')), that = this;
            if (that.privateSingleCourse.seriesList.length == 0) {
                axios.get(url, {params: {type: 1, ownType: 1, sdaduimId: this.sdaduimId}})
                    .then(function (res) {
                        let resData = eval(res);
                        if (resData['data']['code'] === '200') {
                            that.privateSingleCourse.seriesList = resData['data']['data'].seriesList;
                            that.privateSingleCourse.subSeriesList = resData['data']['data'].subSeriesList;
                        } else {
                            $.alert(resData['data']['msg']);
                        }
                    })
                    .catch(function (error) {
                        $.alert(error);
                    });
            }

            if (that.privateCourceData.trainingSeriesIds) {
                that.getPrivateList(that.privateCourceData.trainingSeriesIds, that.privateSingleCourse.itemIndex)
            }
            $('#defaultCurriculumPlanModal').modal('show');
        },
        //新增私教课程
        addKcc: function () {
            this.tempImg = 1;
            this.addPrivateCourse = !this.addPrivateCourse;
            if (this.addPrivateCourse && !this.editor) this.initEdit();
            this.privateCourceData = {
                dayAndMounth: 2,
                courseName: "",
                remainCourceNum: 0,
                classSchedulingType: '',
                assignTeacherType: ''
            };
            this.imgUrl = null;
            this.privateCourceData.dayAndMounth = 2;
            this.privateCourceData.remainCourceNum = 0;
            this.editor.txt.html("");
        },
        initEdit: function () {
            var E = window.wangEditor
            !this.editor && (this.editor = new E('#eidt-area'));
            this.editor.customConfig.uploadImgShowBase64 = true
            this.editor.create()
        },
        //新增私教套餐
        addKc: function () {
            this.selectRelation = {
                shopItem: {},
                courseObj: [],
                courseIds: [],
            },
                this.canUsingItem = [];
            this.packageData = {
                validType: 1,
            }
            $('.canUsingCls').each(function () {
                if (this.checked) {
                    $(this).attr("checked", false);
                }
            })
            try {
                $('#area_tree').treeview('uncheckNode', [that.treeNode, {silent: true}]);
            } catch (e) {
                //  console.info(e)
            }

            this.addPrivate = !this.addPrivate;

        },
        kcFilterModel: function () {
            this.courceIds = [];
            this.isok = false;
            $('#kcFilterModel').modal('show');
            // this.areaTree();
        },
        appointModal: function () {
            this.getsettingSdaduimList();
        },
        addActionTypeOneModal: function () {
            if (!this.simpleLinkSettingActionSubSelectId) {
                $.alert('尚未选择训练计划 不能添加')
                return;
            }
            this.isok = false;
            this.trainAddActionIds = [];
            $("#addActionTypeOneModal").modal('show')
        },
//					套餐新增
        addActionTypeModal: function () {
            if (!this.mealLinkSettingActionSubSelectId) {
                $.alert('尚未选择训练计划 不能添加')
                return;
            }
            this.isok = false;
            this.trainAddActionIds = [];
            $("#addActionTypeModal").modal('show');
        },
        modifyAction: function (index, actionType) {


            this.actionSettingData = JSON.parse(JSON.stringify(actionType));

            $('#modifyActionModal').modal('show')
        },
//					新增动作
        addAction: function (type) {
            this.addSeriesType = type;
            console.info(type);
            if (type == 0) {
                this.modalTitle = "操作动作系列";
                this.placeholderTxt = this.modalTitle + "(限制15字符)";
            } else if (type == 1) {
                this.modalTitle = "操作训练课程系列";
                this.placeholderTxt = this.modalTitle + "(限制15字符)";
            } else {
                this.modalTitle = "操作训练课程系列";
                this.placeholderTxt = this.modalTitle + "(限制15字符)";
            }
            $('#addActionModal').modal('show');
        },
//					座位设置
        seatSetting: function () {
            $("#settingModal").modal('show')
        },
        areaTree: function (data) {
            var vm = this;
//						修改插件527-529行
            $('#area_tree').treeview({
                data: data,
                isShowModiyf: false,//是否显示修改
                selectedColor: "#2d9bd2",//选择时的颜色
                selectedBackColor: "#f0f5f7",//选中时的背景颜色
                expandIcon: "glyphicon glyphicon-triangle-right",//收起时的图标
                collapseIcon: "glyphicon glyphicon-triangle-bottom",//展开时的图标
                showBorder: false,//是否显示边框
                state: {
                    expanded: false,
                },
                backColor: "#FFFFFF",
                onNodeSelected: function (event, node) {
                    //			            	在点击选中节点加删除字眼,不要可删除
                    //							(deleteMode && $("#course_tree").treeview("editNode", [node.nodeId, { text: node.text.indexOf(vm.deleteStr)===-1?node.text+=vm.deleteStr:node.text}])) || $("#course_tree").treeview("editNode", [node.nodeId, { text: node.text.replace(vm.deleteStr,'')}]);
                    console.log('节点选中')
                    // console.log(node.sdaduimId)
                    if (typeof node.sdaduimId != 'undefined') {
                        vm.initPrivateCource({
                            curPage: vm.privateCourceData.pageNo,
                            limit: vm.privateCourceData.pageSize,
                            sId: node.sdaduimId,
                            sdaduimId: vm.sdaduimId
                        }, 1);

                    }

                    this.courceIds = [];
                    let parent = $('#area_tree').treeview('getParent', node.nodeId);
                    if (!parent) return;
                    let treeNode = {};
                    vm.treeNode = node;
                    treeNode.id = node.sdaduimId;
                    treeNode.name = node.text;
                    treeNode.pName = parent.text;
                    treeNode.shopId = parent.shopId
                    vm.selectRelation.shopItem = treeNode;

                },
                onNodeUnselected: function (event, node) {
                    console.log('节点未选中')
                }
            });
        },
//					树形图
        initSelectableTree: function () {
            var vm = this;
//						修改插件527-529行
            $('#course_tree').treeview({
                data: vm.courseSeriess,
                isShowModiyf: true,
                selectedColor: "#2d9bd2",//选择时的颜色
                selectedBackColor: "#f0f5f7",//选中时的背景颜色
                expandIcon: "glyphicon glyphicon-triangle-right",//收起时的图标
                collapseIcon: "glyphicon glyphicon-triangle-bottom",//展开时的图标
                showBorder: false,//是否显示边框
                backColor: "#FFFFFF",
                onNodeSelected: function (event, node) {
                    //			            	在点击选中节点加删除字眼,不要可删除
                    //							(deleteMode && $("#course_tree").treeview("editNode", [node.nodeId, { text: node.text.indexOf(vm.deleteStr)===-1?node.text+=vm.deleteStr:node.text}])) || $("#course_tree").treeview("editNode", [node.nodeId, { text: node.text.replace(vm.deleteStr,'')}]);
                    console.log('节点选中')
                },
                onNodeUnselected: function (event, node) {
                    console.log('节点未选中')
                }
            });
        },
        chooseAll: function (type) {
            console.log(this.isok)
            // if( !this.isok)
            //  changeActionIds=true;
            if (!this.isok) {
                if (type == 0) {
                    this.actions[this.actionSettingIndex].actionList.forEach(function (item) {
                        this.changeActionIds.push(item.id);
                    }, this);
                } else if (type == 1) {
                    this.actions[this.trainActionIndex].actionList.forEach(function (item) {
                        this.trainAddActionIds.push(item.id);
                    }, this);

                } else if (type == 2) {
                    console.log(this.packagePrivateCourse.list);
                    this.selectRelation.courseObj = [];
                    this.courceIds = [];
                    //课程ID
                    this.packagePrivateCourse.list.forEach(function (item) {
                        let vo = {};
                        vo.courseName = item.name;
                        vo.courseId = item.id;
                        this.selectRelation.courseObj.push(vo);
                        this.courceIds.push(item.id);
                    }, this);
                }
            } else {
                if (type == 0) {
                    this.changeActionIds = [];
                } else if (type == 1) {
                    this.trainAddActionIds = [];
                } else if (type == 2) {
                    this.courceIds = [];
                    this.selectRelation.courseObj = [];
                }

            }

            //this.changeActionIds.join(","),

        },
        deleteActionConfirm: function () {

        },
        canceldeleteAction: function () {

        },
        shopClick: function () {
            var that = this;
            var options = $("#shopSelect option:selected");
            console.log()
            if (that.temp == 0) {
                if (options.val() == '' || options.val() == null) {
                    that.initPrivateCource({
                        curPage: that.privateCourceData.pageNo,
                        limit: that.privateCourceData.pageSize,
                        sdaduimId: this.sdaduimId
                    });
                } else {
                    that.initPrivateCource({
                        curPage: that.privateCourceData.pageNo,
                        limit: that.privateCourceData.pageSize,
                        shopId: options.val(),
                        sdaduimId: this.sdaduimId
                    });
                }
            } else {
                if (options.val() == '' || options.val() == null) {
                    that.getPackageList({
                        curPage: that.privateCourceData.pageNo,
                        limit: that.privateCourceData.pageSize,
                        sdaduimId: this.sdaduimId
                    });
                } else {
                    that.getPackageList({
                        curPage: that.privateCourceData.pageNo,
                        limit: that.privateCourceData.pageSize,
                        shopId: options.val(),
                        sdaduimId: this.sdaduimId
                    });
                }
            }

        },
        temp: function () {
            var that = this;
            that.temp = 0;

        },
        /**
         * 获取门店场馆信息
         */
        initServerClassRoomSetting: function () {
            let url = $.stringFormat("{0}/frPrivatePackageRelation/shopList", $.cookie('url')), that = this;
            axios.get(url, {params: {}})
                .then(function (res) {
                    if (res.data.code == 200) {
                        let item = res.data.data;
                        that.shopInfo = [];
                        item.forEach(function (shop) {
                            that.shopInfo.push({id: shop.shopId, name: shop.text});
                        });
                    } else {
                        $.alert("服务器异常，获取数据失败")
                    }
                    // Loading.prototype.hide();
                }).catch(function (error) {
                console.info(error)
                Loading.prototype.hide();
                $.alert("服务器异常，获取数据失败")
            });
        },
    },
    watch: {
        // 'shopId': 'shopClick',
        isAuth: function (val) {
            if (val) {
                for (var i in this.storeData) {
                    this.storeData[i].selected = false;
                    var children = this.storeData[i].children;
                    if (children && children.length) {
                        for (var j in children) {
                            this.storeData[i].children[j].selected = false;
                        }
                    }
                }
                this.isAll = false;
            }
        },
        isAll: function (val) {
            if (val) {
                for (var i in this.storeData) {
                    this.storeData[i].selected = true;
                    var children = this.storeData[i].children;
                    if (children && children.length) {
                        for (var j in children) {
                            this.storeData[i].children[j].selected = true;
                        }
                    }
                }
            } else {
                for (var i in this.storeData) {
                    this.storeData[i].selected = false;
                    var children = this.storeData[i].children;
                    if (children && children.length) {
                        for (var j in children) {
                            this.storeData[i].children[j].selected = false;
                        }
                    }
                }
            }
        },
        mealLinkSettingActionSubTable: function () {
            //  alert("tetx");

            this.$nextTick(function () {
                /!*现在数据已经渲染完毕*!/
                console.log(this.mealLinkSettingActionSubTable);
                let that = this;
                //   console.log(that.mealLinkSettingActionSubTable);
                //     var mealLinkSettingActionSubTable_=that.mealLinkSettingActionSubTabl;
                if (that.mealLinkSettingActionSubTable != null && that.mealLinkSettingActionSubTable.length > 0) {
                    for (var i in that.mealLinkSettingActionSubTable) {
                        jeDate("#pick" + i, {
                            format: "YYYY-MM-DD hh:mm:ss",
                            isTime: false,
                            minDate: "2014-09-19 00:00:00",
                            donefun: function (obj) {


                                var str = obj.elem.id;
                                str = str.substr(str.length - 1, 1);
                                that.mealLinkSettingActionSubTable[str].traning_time = obj.val;
                                that.changeData(that.mealLinkSettingActionSubTable[str], 2);

                                //this    而this指向的都是当前实例
                                console.log(obj.elem);     //得到当前输入框的ID
                                console.log(obj.val);      //得到日期生成的值，如：2017-06-16

                                //得到日期时间对象，range为false
                                console.log(obj.date);    //这里是对象
                                obj.date = {YYYY: 2017, MM: 8, DD: 18, hh: 10, mm: 25, ss: 10}

                                //开启区域选择，range为字符串 时才会返回一个日期时间数组对象。
                                console.log(obj.date);    //这里是数组
                                obj.date = [
                                    {YYYY: 2017, MM: 8, DD: 18, hh: 10, mm: 25, ss: 10},
                                    {YYYY: 2019, MM: 11, DD: 25, hh: 15, mm: 30, ss: 25}
                                ]
                            }

                        });
                    }
                }

            })
        }
    }
})
//			修改
$treeNode = $('#course_tree');
$treeNode.on('click', '.modify-tree', function (event) {
    //禁止选中节点
    event.stopPropagation();
    event.stopImmediatePropagation();
    $('#modifyModal').modal('show');
})

