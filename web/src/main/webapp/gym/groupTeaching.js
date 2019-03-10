/**
 * Created by linzhq on 2018/12/7.
 */
$(function () {
    new Vue({
        el: '#wrapper',
        data: {
            privateSingleCourse: {
                seriesList: [],
                subSeriesList: [],
                showIndex: 0,
                totalCount: 0,
                items: [],     //弹窗右边数据
                itemIndex: -1,    //trainingSeriesIds
            },
            isAuth: false,
            isAll: false,
            storeData: [],//添加课程系列时门店信息
            courseSeriess: [//树节点信息

            ],
            editor: null,
            deleteStr: '<span class="delete-node">删除</span>',
            addPrivateCourse: false,
            seriesData: {
                name: '',
                parentId: '',
            },
            parenNode: {},
            groupCourseData: {},
            imgUrl: null,
            imgFile: null,
            groupCource: {
                list: [],
                pageNo: 1,
                pageSize: 10,

            },
            shopSdaduim: [],
            shopInfo: [],
            shopName: '',
            sdaduimInfo: [],
            sdaduimName: "",
            classRoomData: {
                FrGroupClassRoomSeat: {
                    cntRow: 6,
                    cntCol: 6,
                    seatNumArr: null
                }
            },
            seatNum: 1,
            roomList: {
                list: [],
                pageNo: 1,
                pageSize: 10,

            },
            old: {
                cntRow: 0,
                cntCol: 0,
                seatNumArr: []
            },
            settingInfo: {},
            //动作设置---动作系列
            actions: [
                {
                    actionList: []
                }
            ],
            //动作设置页面中 动作系列的选中下标
            actionSettingIndex: 0,
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

            //训练课程设置 添加系列的type  0 动作设置 1 单节训练 2 训练套餐
            addSeriesType: 0,
            isok: false,
            modalTitle: null,
            placeholderTxt: null,
            SeriesInfo: [],
            ClassInfo: {
                id:null,
                name:null,
            },
            sdaduimId:'fed61d300684ac6c',
        },
        mounted: function () {
            this.initSelectableTree();
            this.initServerData();//初始化加载服务器数据
            this.getGroupList({curPage: 1, limit: 10,sdaduimId:this.sdaduimId});
            this.initServerClassRoomSetting();
        },
        methods: {
            cancleSaveGroupCourse: function () {
                let that = this;
                that.editor.txt.html("");
                that.imgUrl = null;
                that.groupCourseData = {};
                $("#privatemrkcjh").val("");
                //刷新列表
                let selectNodes = $('#course_tree').treeview('getSelected');
                if (selectNodes[0].id != null)
                    that.getGroupList({curPage: 1, limit: 10, seriesId: selectNodes[0].id,sdaduimId:that.sdaduimId});
                that.addPrivateCourse = false;

            },
            //添加选中
            selectDefault: function () {
                //123123
                var that=this;
                if (that.ClassInfo.id!=null &&that.ClassInfo.id!='') {
                    this.groupCourseData.trainSeriesIds = that.ClassInfo.id
                    $('#defaultCurriculumPlanModal').modal('hide');
                    $("#privatemrkcjh").val(that.ClassInfo.name);
                } else {
                    $.alert("请选中训练课程");
                    return;
                }
            },
            //选择课程计划子节点点击
            getPrivateList: function (list, index) {
                var that = this;
                that.privateSingleCourse.items = list.actionList;
                that.ClassInfo = list;
                that.privateSingleCourse.itemIndex = index;
            },
            getPrivateSetting: function () {
                let url = $.stringFormat("{0}/frSettingInfo/get/private/setting", 'http://www.4006337366.com:8080/'), that = this;
                Loading.prototype.show();
                this.settingSdaduimList = [];
                axios.get(url, {params: {type: 2 ,sdaduimId:this.sdaduimId}})
                    .then(function (res) {
                        if (res.data.code == 200) {
                            that.settingInfo = res.data.data;
                            if (that.settingInfo.groupLJKFYYTIME && that.settingInfo.groupLJKFYYTIME != "") {
                                let arr = that.settingInfo.groupLJKFYYTIME.split(",");
                                $("#dateInp").val(arr[0]);
                                $("#XSInp").val(arr[1]);
                                $("#FZInp").val(arr[2]);
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
            saveSettingInfo: function () {
                let url = $.stringFormat("{0}/frSettingInfo/update/private/setting", 'http://www.4006337366.com:8080/'), that = this;
                Loading.prototype.show();
                that.settingInfo.groupLJKFYYTIME = $("#dateInp").val() + "," + $("#XSInp").val() + "," + $("#FZInp").val();
                this.settingInfo.sdaduimId=this.sdaduimId;
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
            inputFunc: function (e, type) {
                if (type == 1) {
                    if (e.target.value && e.target.value > 0 && e.target.value != this.old.cntRow) {
                        this.clearTxt();
                        this.classRoomData.FrGroupClassRoomSeat.seatNumArr = this.settingSeatInfo();
                        this.seatNum = 1;
                    } else {
                        this.classRoomData.FrGroupClassRoomSeat.cntRow = this.old.cntRow;
                    }
                } else {
                    if (e.target.value && e.target.value > 0 && e.target.value != this.old.cntCol) {
                        this.clearTxt();
                        this.classRoomData.FrGroupClassRoomSeat.seatNumArr = this.settingSeatInfo();
                        this.seatNum = 1;
                    } else {
                        this.classRoomData.FrGroupClassRoomSeat.cntCol = this.old.cntCol;
                    }
                }
            },
            deleteRoom: function (id) {
                let url = $.stringFormat("{0}/frGroupClassRoom/delete", 'http://www.4006337366.com:8080/'), that = this;
                Loading.prototype.show();
                $.confirm({
                    title: '确认',
                    content: '确认删除?',
                    buttons: {
                        ok: {
                            text: '确认',
                            btnClass: 'btn-primary',
                            action: function () {
                                //确认后的操作
                                axios.get(url, {params: {id: id}}).then(function (res) {
                                    console.info(res.data.data)
                                    if (res.data.code == 200) {
                                        that.resRoomData();
                                    } else {
                                        $.alert("服务器异常，获取数据失败")
                                    }
                                    Loading.prototype.hide();
                                }).catch(function (error) {
                                    console.info(error);
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
            updateInfo: function (room, flag) {
                console.info(room);
                if (room.frGroupClassRoomSeat) {
                    room = this.cloneObj(room);
                    this.classRoomData = room;
                    this.classRoomData.FrGroupClassRoomSeat = room.frGroupClassRoomSeat;
                    this.old.seatNumArr = room.frGroupClassRoomSeat.seatNumArr;
                    this.classRoomData.FrGroupClassRoomSeat.seatNumArr = this.deepcopy(this.old.seatNumArr);
                    delete this.classRoomData.frGroupClassRoomSeat;
                }
                this.clearTxt();
                this.seatNum = this.setTxt();
                if (flag == 1) {
                    this.old.cntCol = this.classRoomData.FrGroupClassRoomSeat.cntCol;
                    this.old.cntRow = this.classRoomData.FrGroupClassRoomSeat.cntRow;
                    $("#settingModal").modal('show');
                }
            },
            cloneObj: function (obj) {
                return JSON.parse(JSON.stringify(obj));
            },
            deepcopy: function (obj) {
                console.info(obj)
                let out = [];
                for (var i = 0; i < obj.length; i++) {
                    let inarr = []
                    for (var j = 0; j < obj[i].length; j++) {
                        inarr.push(obj[i][j]);
                    }
                    out.push(inarr);
                }
                console.info(out);
                return out;
            },
            setTxt: function () {
                let arr = this.classRoomData.FrGroupClassRoomSeat.seatNumArr, max = 0;
                for (var i = 0; i < arr.length; i++) {
                    for (j = 0; j < arr[i].length; j++) {
                        if (arr[i][j] != 0) {
                            $("#sel_" + i + "_" + j).text(arr[i][j]);
                            if (arr[i][j] > max) {
                                max = arr[i][j];
                            }
                        }
                    }
                }
                return max + 1;
            },
            chooseGW: function (e) {
                let that = this;
                this.sdaduimInfo.forEach(function (data) {
                    if (data.id == that.classRoomData.sdaduimId) {
                        that.sdaduimName = data.name;
                        that.getRoomList({curPage: 1, limit: 10, sdaduimId: data.id});
                        break;
                    }
                });
                that.resRoomData();
            },
            getRoomList: function (params) {
                let that = this;
                let url = $.stringFormat("{0}/frGroupClassRoom/list", 'http://www.4006337366.com:8080/');
                if (!params.sdaduimId) {
                    params.sdaduimId = that.classRoomData.sdaduimId;
                }
                axios.get(url, {params: params}).then(function (res) {
                    console.info(res.data.data)
                    if (res.data.code == 200) {
                        that.roomList.list = res.data.data.list;
                        that.roomList.pageNo = res.data.data.currPage;
                        that.roomList.pageSize = res.data.data.pageSize;
                        that.roomList.totalCount = res.data.data.totalCount;
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
                                that.getRoomList({page: page, limit: res.data.data.pageSize});
                            }
                        })
                    } else {
                        $.alert("服务器异常，获取数据失败")
                    }
                }).catch(function (error) {
                    console.info(error)
                    $.alert("服务器异常，获取数据失败")
                });
            },
            clearTxt: function () {
                $("#seatTable").find("tr").each(function () {
                    $(this).find("td").each(function () {
                        $(this).text("");
                    })
                });
            },
            /**
             *
             */
            saveClassRoom: function () {
                let errMsg = "";
                if (!this.isNoEmpty(this.classRoomData.shopId)) {
                    errMsg += "门店必填<br/>"
                }
                if (!this.isNoEmpty(this.classRoomData.sdaduimId)) {
                    errMsg += "场馆必填<br/>"
                }
                if (!this.isNoEmpty(this.classRoomData.name)) {
                    errMsg += "教室名称必填<br/>"
                }
                if (!this.isNoEmpty(this.classRoomData.containNum)) {
                    errMsg += "容纳人数必填<br/>"
                }
                if (!this.isNoEmpty(this.classRoomData.area)) {
                    errMsg += "面积必填<br/>"
                }
                if (!this.isNoEmpty(this.classRoomData.sdaduimUsing)) {
                    errMsg += "场馆用途必填<br/>"
                }
                if (this.classRoomData.isNeedSeat != 0 && !this.isNoEmpty(this.classRoomData.isNeedSeat)) {
                    errMsg += "座位是否必选必填<br/>"
                }
                // if (!this.isNoEmpty(this.classRoomData.remark)) {
                //     errMsg += "备注必填<br/>"
                // }
                if (errMsg.length != 0) {
                    $.alert(errMsg);
                    return;
                }
                console.info(this.classRoomData);
                let url = $.stringFormat("{0}/frGroupClassRoom/saveOrUpdate", 'http://www.4006337366.com:8080/'), that = this;
                axios.post(url, this.classRoomData).then(function (res) {
                    console.log(res)
                    if (res.data.code == 200) {
                        $.alert("保存成功");
                        that.resRoomData();
                    }
                    Loading.prototype.hide();

                }).catch(function (error) {
                    //隐藏加载中
                    console.info(error)
                    Loading.prototype.hide();
                    $.alert(error)
                });
            },
            saveClass:function(){
                var that = this;
                var errMsg = "";
                if (!this.isNoEmpty(this.groupCourseData.name)) {
                    errMsg += "课程名称必填<br/>"
                }
                if (!this.isNoEmpty(this.groupCourseData.time)) {
                    errMsg += "课程时长必填<br/>"
                }
                if (!this.isNoEmpty(this.groupCourseData.marketPrice)) {
                    errMsg += "市场价必填<br/>"
                }
                if (!this.isNoEmpty(this.groupCourseData.memberPrice)) {
                    errMsg += "会员价必填<br/>"
                }
                if (!this.isNoEmpty(this.groupCourseData.canReserveNum)) {
                    errMsg += "单节课可约人数必填<br/>"
                }
                if (!this.isNoEmpty(this.groupCourseData.leastClassNum)) {
                    errMsg += "最少上课人数必填<br/>"
                }
                if (!this.isNoEmpty(this.editor.txt.text())) {
                    errMsg += "课程详情必填<br/>"
                }
                if (!this.isNoEmpty(this.groupCourseData.classCount)) {
                    errMsg += "课时数必填<br/>"
                }
                if (errMsg.length != 0) {
                    $.alert(errMsg);
                    return;
                }

                var url = $.stringFormat('{0}/file/upload', 'http://www.4006337366.com:8080/');
                var isSuccess = false;
                //判断是否有图片
                if (that.imgFile == null || that.imgFile == 'null' || that.imgFile == '') {
                    $.alert("请选择图片");
                }
                //判断图片大小是否超过5MB
                if (that.imgFile.size > 1048576) {
                    $.alert("图片大小不得大于1MB");
                }
                Loading.prototype.show();
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
                    // $.alert(jsonData['data']['data']['msg'])
                    that.groupCourseData.imageUrl = jsonData['data']['data']['imgUrl'];
                    that.imgUrl = $.stringFormat('{0}{1}{2}', 'http://www.4006337366.com:8080/', $.cookie('imgPath'), jsonData['data']['data']['imgUrl']);
                    if (response.data.code == 200 || response.data.code == '200') {
                        that.imgFile = null;//清空条件
                        that.saveGroupCourse();
                        $("#groupFlie").val('')
                    }

                })
            },
            resRoomData: function () {
                let that = this;
                let curShopId = that.classRoomData.shopId,
                    curSdamin = that.classRoomData.sdaduimId;
                that.classRoomData = {
                    FrGroupClassRoomSeat: {
                        cntRow: 6,
                        cntCol: 6,
                        seatNumArr: null
                    }
                };
                that.classRoomData.shopId = curShopId;
                that.classRoomData.sdaduimId = curSdamin;
                that.seatNum = 1;
                that.classRoomData.FrGroupClassRoomSeat.cntRow = 6;
                that.classRoomData.FrGroupClassRoomSeat.cntCol = 6;
                that.classRoomData.FrGroupClassRoomSeat.seatNumArr = that.settingSeatInfo();
                that.clearTxt();
                that.getRoomList({curPage: that.roomList.pageNo, limit: 10, sdaduimId: that.classRoomData.sdaduimId});
            },
            /**
             * 作为选择逻辑
             * @param row
             * @param col
             */
            selectNum: function (row, col) {
                let num, isExit = false, currArr = this.classRoomData.FrGroupClassRoomSeat.seatNumArr;
                if (currArr[row][col] == 0) {//设置值才查找
                    for (let ii = 1; ii <= this.seatNum; ii++) {//loop one start
                        isExit = false; //重置条件
                        for (let i = 0; i < currArr.length; i++) {//loop two start

                            for (let j = 0; j < currArr[i].length; j++) {//loop three start

                                if (ii == currArr[i][j]) {//找到了
                                    isExit = true;
                                    break;
                                } else {
                                    isExit = false;
                                }

                            }//loop three end

                            if (isExit)//找到break 提高查找速度
                                break;
                        }//loop two end

                        if (!isExit) {//未找到
                            num = ii;
                            break;
                        }

                    }//loop one end

                }

                if (this.classRoomData.FrGroupClassRoomSeat.seatNumArr[row][col] == 0) {
                    if (num != undefined && num != this.seatNum) {
                        this.classRoomData.FrGroupClassRoomSeat.seatNumArr[row][col] = num;
                        $('#sel_' + row + '_' + col).text(num);
                    } else {
                        this.classRoomData.FrGroupClassRoomSeat.seatNumArr[row][col] = this.seatNum++;
                        $('#sel_' + row + '_' + col).text((this.seatNum - 1));
                    }
                } else {
                    this.classRoomData.FrGroupClassRoomSeat.seatNumArr[row][col] = 0;
                    $('#sel_' + row + '_' + col).text("");
                }
            },
            /**
             *更换门店
             * @param row
             * @param col
             */
            chooseShop: function (e) {
                console.log(this.shopInfo);
                for (var i in this.shopInfo) {
                    if (this.classRoomData.shopId == this.shopInfo[i].id) {
                        this.shopName = this.shopInfo[i].name;
                    }
                }
                console.info(e);
                this.getSdaduimItem(this.classRoomData.shopId);
            },
            /**
             * 获取门店场馆信息
             */
            initServerClassRoomSetting: function () {
                let url = $.stringFormat("{0}/frPrivatePackageRelation/shopList", 'http://www.4006337366.com:8080/'), that = this;
                // Loading.prototype.show();
                axios.get(url, {params: {}})
                    .then(function (res) {
                        console.info(res.data.data)
                        if (res.data.code == 200) {
                            let item = res.data.data;
                            that.shopInfo = [];
                            that.shopSdaduim = item;
                            item.forEach(function (shop) {
                                that.shopInfo.push({id: shop.shopId, name: shop.text});
                                shop.nodes.forEach(function (sdaduim) {
                                    that.sdaduimInfo.push({id: sdaduim.sdaduimId, name: sdaduim.text});
                                });
                            });
                            console.info(that.shopInfo)
                            if (that.shopInfo.length > 0) {
                                that.classRoomData.shopId = that.shopInfo[0].id;
                                that.shopName = that.shopInfo[0].name;
                                that.getSdaduimItem(that.classRoomData.shopId);
                            }
                        } else {
                            $.alert("服务器异常，获取数据失败")
                        }
                        // Loading.prototype.hide();
                    }).catch(function (error) {
                    console.info(error)
                    Loading.prototype.hide();
                    $.alert("服务器异常，获取数据失败")
                });
                this.classRoomData.FrGroupClassRoomSeat.seatNumArr = this.settingSeatInfo();
            },
            getSdaduimItem: function (shopId) {
                var that = this;
                that.sdaduimName = "";
                that.sdaduimInfo = [];
                that.classRoomData.sdaduimId = "";
                this.shopSdaduim.forEach(function (shop) {
                    if (shop.shopId == shopId) {
                        shop.nodes.forEach(function (sdaduim) {
                            that.sdaduimInfo.push({id: sdaduim.sdaduimId, name: sdaduim.text});
                        });
                    }
                });
                if (that.sdaduimInfo.length > 0) {
                    that.classRoomData.sdaduimId = that.sdaduimInfo[0].id;
                    that.sdaduimName = that.sdaduimInfo[0].name;
                    that.getRoomList({curPage: 1, limit: 10, sdaduimId: that.sdaduimInfo[0].id});
                } else {
                    that.classRoomData.sdaduimId = "";
                    that.getRoomList({curPage: 1, limit: 10, sdaduimId: "-1"});//加载不存在的列表也就是空列表
                }
            },
            /**
             * 课程删除或停课
             */
            changeStatus: function (id, flag) {
                let url = $.stringFormat("{0}/frGroupCourse/change/staus", 'http://www.4006337366.com:8080/'), that = this;
                $.confirm({
                    title: '确认',
                    content: flag == 2 ? "确认停课?" : flag == 3 ? "确认恢复课程？" : '确认删除?',
                    buttons: {
                        ok: {
                            text: '确认',
                            btnClass: 'btn-primary',
                            action: function () {
                                //确认后的操作
                                axios.get(url, {params: {id: id, flag: flag}})
                                    .then(function (res) {
                                        if (res.data.code == 200) {
                                            that.getGroupList({curPage: 1, limit: 10, seriesId: that.parenNode.id ,sdaduimId:that.sdaduimId});
                                            if (flag != 2 && flag != 3) {
                                                that.initSelectableTree();
                                            }
                                        } else {
                                            $.alert("服务器异常，获取数据失败")
                                        }
                                        $.alert(res['data']['msg']);
                                    }).catch(function (error) {
                                    console.info(error)
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
            updateCourse: function (obj) {
                if (this.isNoEmpty(obj.imageUrl)) {
                    this.imgUrl = $.stringFormat('{0}{1}{2}', 'http://www.4006337366.com:8080/', $.cookie('imgPath'), obj.imageUrl);
                }
                $("#privatemrkcjh").val(obj.trainSeriesName);
                this.groupCourseData = obj;
                this.addPrivateCourse = !this.addPrivateCourse;
                if (this.groupCourseData && !this.editor) this.initEdit();
                this.editor.txt.html(obj.info);
            },
            //头像url拼接
            avatarUrl: function (avatarLink) {
                if (avatarLink === null || avatarLink === '' || typeof avatarLink === 'undefined') {
                    return 'img/addImg.png';  //默认图片
                }
                return $.stringFormat("{0}/{1}/" + avatarLink, 'http://www.4006337366.com:8080/', $.cookie('imgPath'));
            },
            /**
             * 根据点击节点获取课程信息
             * @param params
             */
            getGroupList: function (params) {
                let that = this;
                let url = $.stringFormat("{0}/frGroupCourse/list", 'http://www.4006337366.com:8080/');
                // Loading.prototype.show();
                axios.get(url, {params: params})
                    .then(function (res) {
                        console.info(res.data.data)
                        if (res.data.code == 200) {
                            that.groupCource.list = res.data.data.list;
                            that.groupCource.pageNo = res.data.data.currPage;
                            that.groupCource.pageSize = res.data.data.pageSize;
                            that.groupCource.totalCount = res.data.data.totalCount;
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
                                    that.getGroupList({page: page, limit: res.data.data.pageSize,sdaduimId:that.sdaduimId});
                                }
                            })
                        } else {
                            $.alert("服务器异常，获取数据失败")
                        }
                        Loading.prototype.hide();
                    }).catch(function (error) {
                    console.info(error)
                    Loading.prototype.hide();
                    $.alert("服务器异常，获取数据失败")
                });
                Loading.prototype.hide();
            },
            /**
             * 保存课程闲情
             */
            saveGroupCourse: function () {
                var that=this;

                console.info(this.groupCourseData);
                this.groupCourseData.info = this.editor.txt.html();
                // let selectNodes = $('#course_tree').treeview('getSelected');
                this.groupCourseData.seriesId = this.parenNode.id;
                this.groupCourseData.sdaduimId = this.sdaduimId;
                console.info(this.groupCourseData)
                var url = $.stringFormat("{0}/frGroupCourse/saveOrUpdate", 'http://www.4006337366.com:8080/')
                axios.post(url, this.groupCourseData).then(function (res) {
                    var resData = eval(res);
                    if (res.data.code != 500) {
                        that.editor.txt.html("");
                        that.imgUrl = null;
                        that.groupCourseData = {};
                        //刷新列表
                        // let selectNodes = $('#course_tree').treeview('getSelected');
                        // if(selectNodes[0].id != null)
                        that.getGroupList({curPage: 1, limit: 10, seriesId: that.parenNode.id,sdaduimId:that.sdaduimId});
                        that.initSelectableTree();
                    }
                    Loading.prototype.hide();
                    that.addPrivateCourse = false;
                    $.alert(resData['data']['msg']);
                }).catch(function (error) {
                    //隐藏加载中
                    console.info(error)
                    Loading.prototype.hide();
                    $.alert(error)
                });

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
            /**
             * 上传图片
             */
            uploadAvatar: function () {
                var that = this;
                var url = $.stringFormat('{0}/file/upload', 'http://www.4006337366.com:8080/');
                var isSuccess = false;
                //判断是否有图片
                if (that.imgFile == null || that.imgFile == 'null' || that.imgFile == '') {
                    $.alert("请选择图片");
                }
                //判断图片大小是否超过5MB
                if (that.imgFile.size > 1048576) {
                    $.alert("图片大小不得大于1MB");
                }
                Loading.prototype.show();
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
                    that.groupCourseData.imageUrl = jsonData['data']['data']['imgUrl'];
                    that.imgUrl = $.stringFormat('{0}{1}{2}', 'http://www.4006337366.com:8080/', $.cookie('imgPath'), jsonData['data']['data']['imgUrl']);
                    if (response.data.code == 200 || response.data.code == '200') {
                        that.imgFile = null;//清空条件
                        // that.saveGroupCourse();
                    }

                })
            },
            /**
             * 保存课程系列下的子项
             */
            saveSeriesItem: function () {
                if (!this.isNoEmpty(this.seriesData.name)) {
                    $.alert("请填写课程名称")
                    return;
                }
                this.seriesData.sdaduimId=this.sdaduimId;
                this.seriesData.parentId = this.parenNode.id;
                let url = $.stringFormat("{0}/frGroupSeries/saveOrUpdate", 'http://www.4006337366.com:8080/'), that = this;
                //   Loading.prototype.show();
                $('#addCourseModel').modal('hide');
                axios.post(url, this.seriesData).then(function (res) {
                    let resData = eval(res);
                    if (res.data.code != 500) {
                        that.resetSeries();
                        that.initSelectableTree();
                        try {
                            $('#course_tree').treeview('unselectNode', ["", {silent: true}]);
                        } catch (e) {
                            //  Loading.prototype.hide();
                            // console.info(e);
                        }
                    }
                    // Loading.prototype.hide();
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
            /**
             * 获取系列信息
             */
            getSeriesInfo: function (id) {
                let url = $.stringFormat("{0}/frGroupSeries/info", 'http://www.4006337366.com:8080/'), that = this;
                Loading.prototype.show();
                axios.get(url, {params: {id: id}})
                    .then(function (res) {
                        if (res.data.code == 200) {
                            let value = res.data.data;
                            that.seriesData.name = value.name;
                            that.seriesData.parentId = value.parentId;
                            that.seriesData.id = value.id;
                            if (value.isChain == 0) {
                                let shopIds = value.shopIds.split(",");
                                that.storeData.forEach(function (area) {
                                    area.selected = false;
                                    if (area.children.length > 0) {
                                        area.children.forEach(function (shop) {
                                            shopIds.forEach(function (shopId) {
                                                if (shopId == shop.value)
                                                    shop.selected = true;
                                            })
                                        });
                                    }
                                });
                            } else {
                                that.isAuth = true;
                            }

                        } else {
                            $.alert("服务器异常，获取数据失败")
                        }
                        Loading.prototype.hide();
                    })
                    .catch(function (error) {
                        Loading.prototype.hide();
                        $.alert("服务器异常，获取数据失败")
                    });
            },
            /**
             * 保存课程系列
             */
            saveGropSeries: function () {
                let checked = this.justCheckShop(), isNoEmpty = this.isNoEmpty(this.seriesData.name), errMsg = "";
                if (checked != -1 && checked.length == 0)
                    errMsg += "请选择门店信息<br/>";
                if (!isNoEmpty)
                    errMsg += "请填写系列名称<br/>";
                if (errMsg.length > 0) {
                    $.alert(errMsg);
                    return;
                }
                let subData = this.seriesData;
                subData.isChain = checked == -1 ? 1 : 0;
                if (subData.isChain == 0) {
                    subData.shopIds = checked.join(",");
                }
                subData.parentId = 0;
                subData.sdaduimId=this.sdaduimId;
                let url = $.stringFormat("{0}/frGroupSeries/saveOrUpdate", 'http://www.4006337366.com:8080/'), that = this;
                axios.post(url, subData).then(function (res) {
                    let resData = eval(res);
                    if (res.data.code != 500) {
                        $('#modifyModal').modal('hide');
                        that.resetSeries();
                        that.initSelectableTree();
                    }
                    that.addPrivateCourse = false;
                    $.alert(resData['data']['msg']);
                })
                    .catch(function (error) {
                        //隐藏加载中
                        console.info(error)
                        $.alert(error)
                    });
            },
            /**
             * 重置课程系列弹窗
             */
            resetSeries: function () {
                this.storeData.forEach(function (area) {
                    area.selected = false;
                    if (area.children.length > 0) {
                        area.children.forEach(function (shop) {
                            shop.selected = false;
                        });
                    }
                });
                this.isAuth = false;
                this.isAll = false;
                this.seriesData = {
                    name: '',
                    parentId: '',
                };
            },
            /**
             * 判断字符串是否为空 去除数字
             * @param value
             * @returns {*}
             */
            isNoEmpty: function (value) {
                if (!isNaN(value) && value && value.length != 0)
                    return true;

                return value && value.length != 0
            },
            /**
             * 判断是否选中门店,-1|连锁 []|选中的
             */
            justCheckShop: function () {
                if (!this.isAuth) {
                    let that = this, shopIds = [];
                    that.storeData.forEach(function (data) {
                        if (data.children.length > 0) {
                            data.children.forEach(function (data1) {
                                if (data1.selected)
                                    shopIds.push(data1.value);
                            });
                        }
                    });
                    return shopIds;
                }

                return -1;//连锁店
            },
            /**
             * 点击市区选中所有的门店
             * @param e
             * @param obj
             */
            ckAll: function (e, obj) {
                let flag = obj.selected;
                if (obj.children.length > 0) {
                    for (let i = 0; i < obj.children.length; i++)
                        obj.children[i].selected = flag;
                }
            },
            /**
             * 初始化加载服务器数据
             */
            initServerData: function () {
                let url = $.stringFormat("{0}/frPrivatePackageRelation/shopSdaduim", 'http://www.4006337366.com:8080/'), that = this;
                // Loading.prototype.show();
                axios.get(url, {params: {}})
                    .then(function (res) {
                        if (res.data.code == 200) {
                            that.storeData = res.data.data;
                        } else {
                            $.alert("服务器异常，获取数据失败")
                        }
                        Loading.prototype.hide();
                    })
                    .catch(function (error) {
                        Loading.prototype.hide();
                        $.alert("服务器异常，获取数据失败")
                    });
            },
            /**
             * 添加课程系列
             */
            addCourseSeries: function () {
                this.seriesData.name = "";
                this.seriesData.parentId = "";

                console.log(this.seriesData);
                $("#modifyModal").modal("show");
            },
//					默认课程计划
            defaultCurriculumPlanModal: function () {
                //123123
                var that = this;
                var url = $.stringFormat("{0}/frTrainingSeries/getActionList", 'http://www.4006337366.com:8080/');
                var data = {
                    type: 1,
                    ownType: 2
                };
                axios.get(url, {params: data})
                    .then(function (res) {
                        var resData = eval(res);
                        if (resData['data']['code'] === '200') {
                            that.SeriesInfo = resData['data']['data'];
                        } else {
                            $.alert(resData['data']['msg']);
                        }
                    })
                    .catch(function (error) {
                        $.alert(error);
                    });
                // let url = $.stringFormat("{0}/frTrainingSeries/seriesAndActionList", 'http://www.4006337366.com:8080/'), that = this;
                // if (that.privateSingleCourse.seriesList.length == 0) {
                //     axios.get(url, {params: {type: 1, ownType: 2}})
                //         .then(function (res) {
                //             let resData = eval(res);
                //             if (resData['data']['code'] === '200') {
                //                 that.privateSingleCourse.seriesList = resData['data']['data'].seriesList;
                //                 that.privateSingleCourse.subSeriesList = resData['data']['data'].subSeriesList;
                //             } else {
                //                 $.alert(resData['data']['msg']);
                //             }
                //         })
                //         .catch(function (error) {
                //             $.alert(error);
                //         });
                // }

                // if (that.groupCourseData.trainSeriesIds) {
                //     that.getPrivateList(that.groupCourseData.trainSeriesIds, that.privateSingleCourse.itemIndex)
                // }
                $('#defaultCurriculumPlanModal').modal('show');
            },
            addKcc: function () {
                this.imgUrl = null;
                this.groupCourseData = {};
                $("#privatemrkcjh").val("");

                // let selectNodes = $('#course_tree').treeview('getSelected');
                // console.info(selectNodes)
                // if(selectNodes.length == 0 || (selectNodes.length != 0&&(selectNodes[0].isCatalog == 1 || selectNodes[0].id == ""))){
                //     $.alert("请选中课程系列下的课程");
                //     return;
                // }

                this.addPrivateCourse = !this.addPrivateCourse;
                if (this.addPrivateCourse && !this.editor) this.initEdit();
                this.editor.txt.html("");
                // if(selectNodes[0].id != null)
                //   this.getGroupList({curPage:this.groupCource.pageNo,limit:10,seriesId:selectNodes[0].id});
            },
            initEdit: function () {
                var E = window.wangEditor
                !this.editor && (this.editor = new E('#eidt-area'));
                this.editor.customConfig.uploadImgShowBase64 = true
                this.editor.create()
            },
            addActionTypeOneModal: function () {
                this.isok = false;
                if (!this.simpleLinkSettingActionSubSelectId) {
                    $.alert('尚未选择训练计划 不能添加')
                    return;
                }
                this.trainAddActionIds = [];
                $("#addActionTypeOneModal").modal('show')
            },
//					套餐新增
            addActionTypeModal: function () {
                this.isok = false;
                if (!this.mealLinkSettingActionSubSelectId) {
                    $.alert('尚未选择训练计划 不能添加')
                    return;
                }
                this.trainAddActionIds = [];
                $("#addActionTypeModal").modal('show')
            },
            modifyAction: function (index, actionType) {
                this.actionSettingData = JSON.parse(JSON.stringify(actionType));
                $('#modifyActionModal').modal('show')
            },
//					新增动作
            addAction: function (type) {
                this.addSeriesType = type;

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
                console.info(this.classRoomData.FrGroupClassRoomSeat.seatNumArr);
                this.old.cntCol = this.classRoomData.FrGroupClassRoomSeat.cntCol;
                this.old.cntRow = this.classRoomData.FrGroupClassRoomSeat.cntRow;
                $("#settingModal").modal('show')
            },
            settingSeatInfo: function () {
                let row = new Array(), col = new Array();
                for (let i = 0; i < this.classRoomData.FrGroupClassRoomSeat.cntRow; i++) {
                    col = [];
                    for (let j = 0; j < this.classRoomData.FrGroupClassRoomSeat.cntCol; j++) {
                        col[j] = 0;
                    }
                    row[i] = col;
                }

                return row;
            },
//					树形图
            initSelectableTree: function (shopId) {
                var vm = this, that = this;
//						修改 插件527-529行
                var url = $.stringFormat("{0}/frGroupSeries/tree", 'http://www.4006337366.com:8080/');
                Loading.prototype.show();
                axios.get(url, {params: {"shopId": shopId,"sdaduimId":that.sdaduimId}})
                    .then(function (res) {
                        if (res.data.code == 200) {
                            that.courseSeriess = res.data.data;
                            $('#course_tree').treeview({
                                data: vm.courseSeriess,
                                isShowModiyf: true,//是否显示修改
                                selectedColor: "#2d9bd2",//选择时的颜色
                                selectedBackColor: "#f0f5f7",//选中时的背景颜色
                                expandIcon: "glyphicon glyphicon-triangle-right",//收起时的图标
                                collapseIcon: "glyphicon glyphicon-triangle-bottom",//展开时的图标
                                showBorder: false,//是否显示边框
                                backColor: "#FFFFFF",
                                onNodeSelected: function (event, node) {
                                    //			            	在点击选中节点加删除字眼,不要可删除
                                    // 							(deleteMode && $("#course_tree").treeview("editNode", [node.nodeId, { text: node.text.indexOf(vm.deleteStr)===-1?node.text+=vm.deleteStr:node.text}])) || $("#course_tree").treeview("editNode", [node.nodeId, { text: node.text.replace(vm.deleteStr,'')}]);
                                    let parent = $('#course_tree').treeview('getParent', node.nodeId);
                                    if (node.id == "") {
                                        that.parenNode = parent;
                                        //清空数据
                                        // that.seriesData.name = "";
                                        // $("#addCourseModel").modal('show');
                                        that.addKcc();
                                    }
                                    if ((node.id != "" && that.parenNode.id != parent.id) || parent.id == undefined) {
                                        //加载列表
                                        that.parenNode = parent.id == undefined ? node : parent;
                                        that.getGroupList({curPage: 1, limit: 10, seriesId: that.parenNode.id,sdaduimId:that.sdaduimId});
                                    }
                                    console.log('节点选中')
                                },
                                onNodeUnselected: function (event, node) {
                                    console.log('节点未选中')
                                }
                            });
                            //树后面的修改
                            $treeNode = $('#course_tree');
                            $treeNode.on('click', '.modify-tree', function (event) {
                                //禁止选中节点
                                event.stopPropagation();
                                event.stopImmediatePropagation();
                                that.getSeriesInfo($(this).attr("id"));
                                $('#modifyModal').modal('show');
                            })
                        } else {
                            $.alert("服务器异常，获取数据失败")
                        }
                        Loading.prototype.hide();
                    })
                    .catch(function (error) {
                        Loading.prototype.hide();
                        $.alert("服务器异常，获取数据失败")
                    });
            },
            actionSettingMain: function () {
                this.actionSettingIndex = 0;
                let that = this;

                // let url = "http://localhost:8080/frActionSeries/list";
                let url = $.stringFormat("{0}/frActionSeries/list?ownType=2&sdaduimId="+this.sdaduimId, 'http://www.4006337366.com:8080/');
                Loading.prototype.show();
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

                let url = $.stringFormat("{0}/frActionSeries/actionList", 'http://www.4006337366.com:8080/');
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

                // if (!this.actionAddData.actionPrinceple || this.actionAddData.actionPrinceple == '') {
                //     $.alert('动作效果原理不能为空');
                //     return;
                // }
                if (!this.actionAddData.image || this.actionAddData.image == '') {
                    $.alert('动作图片不能为空');
                    return;
                }


                let that = this;
                $("#addActionImg" + index).attr('src', 'img/addImg.png');

                let url = $.stringFormat("{0}/frAction/addOrUpdate", 'http://www.4006337366.com:8080/');
                let data = this.actionAddData;
                data.seriesId = that.actions[that.actionSettingIndex].id;
                // Loading.prototype.show();
                // ajax提交

                data.ownType = 2;
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
                        $("#ActionFlie").val('')
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


                let url = $.stringFormat("{0}/frAction/batchFrAction", 'http://www.4006337366.com:8080/');
                let data = {
                    actionIds: this.changeActionIds.join(","),
                    seriesId: this.changeActionSelect,
                }
                // Loading.prototype.show();
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


                let url = $.stringFormat("{0}/frAction/addOrUpdate", 'http://www.4006337366.com:8080/');
                let data = this.actionSettingData;
                data.ownType = 2;
                console.log(data);
                if (data.name == '' || data.name == null) {
                    $.alert('名称不能为空');
                    return;
                }
                if (data.name == '' || data.name == null) {
                    $.alert('名称不能为空');
                    return;
                }
                if (data.diff == '' || data.diff == null) {
                    $.alert('难度不能为空');
                    return;
                }
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
            //单节训练课程设置 主要请求
            simpleLinkSettingMain: function (type) {
                if (type == 1) {
                    this.actionEduSettingIndex = 0;
                } else {
                    this.mealSettingIndex = 0
                }
                let that = this;

                // let url = "http://localhost:8080/ ";
                let url = $.stringFormat("{0}/frTrainingSeries/seriesAndActionList", 'http://www.4006337366.com:8080/');
                let data = {
                    type: type,
                    ownType: 2,
                    sdaduimId:this.sdaduimId,
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
                    sdaduimId:this.sdaduimId,
                };
                let url = '';
                if (this.addSeriesType == 0) {
                    url = $.stringFormat("{0}/frActionSeries/addFrActionSeries", 'http://www.4006337366.com:8080/');
                } else {
                    url = $.stringFormat("{0}/frTrainingSeries/addOrUpdate", 'http://www.4006337366.com:8080/');
                }
                jsonData.ownType = 2;
                // Loading.prototype.show();
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
            addTrain: function (parentId, type) {
                this.addTrainParentId = parentId;
                this.addTrainType = type;
                $('#addTrainModal').modal('show');
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

                let url = $.stringFormat("{0}/frTraningClass/list", 'http://www.4006337366.com:8080/');

                let jsonData = {
                    traningSeriesId: train.id,
                    type: type,
                    ownType: 2
                };
                // Loading.prototype.show();

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
            //单节训练课程设置页面 动作系列的点击事件
            actionEduClick: function (index, parentId, type) {
                console.info("===============actionEduClick")
                // $(event.target).addClass('active').siblings().removeClass('active'); 不可用  事件会传递

                let that = this;
                let url = $.stringFormat("{0}/frTrainingSeries/list", 'http://www.4006337366.com:8080/');
                let data = {
                    type: type,
                    parentId: parentId,
                    ownType: 2,
                    sdaduimId:this.sdaduimId,
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
            updateItem: function (itemId, type) {
                this.updateItemId = itemId;
                this.updateType = type;
                $('#updateItemModal').modal('show');
            },
            deleteItem: function (index, id, type) {

                var that = this;
                var url = $.stringFormat("{0}/frTrainingSeries/list", 'http://www.4006337366.com:8080/');
                var data = {
                    type: type,
                    parentId: id,
                    ownType: 1,
                    sdaduimId:this.sdaduimId,
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
            updateTrain: function (id, type) {
                //this.
                this.updateTrainId = id,
                    this.updateTrainType = type,
                    $('#updateTrainModal').modal('show');
            },
            deleteTrain: function (id, type) {
                var that = this;

                var url = $.stringFormat("{0}/frTrainingSeries/addOrUpdate", 'http://www.4006337366.com:8080/');
                var jsonData = {
                    id: id,
                    isUsing: 0,
                    type: type,
                    ownType: 2,
                    sdaduimId:this.sdaduimId,
                };
                // Loading.prototype.show();
                // ajax提交
                $.confirm({
                    title: '确认',
                    content: '确认删除?',
                    buttons: {
                        ok: {
                            text: '确认',
                            btnClass: 'btn-primary',
                            action: function () {
                                //确认后的操作
                                axios.post(url, jsonData)
                                    .then(function (res) {
                                        let resData = eval(res);
                                        if (type == 0) {
                                            that.actions.push(resData['data']['data']);
                                        } else if (type == 1) {
                                            for (var i in that.simpleLinkSettingActionSub) {
                                                if (that.simpleLinkSettingActionSub[i].id == resData.data.data.id) {
                                                    that.simpleLinkSettingActionSub.splice(i, 1);
                                                    if (that.simpleLinkSettingActionSub.length > 0) {
                                                        that.subTrainClick(that.simpleLinkSettingActionSub[0], 1);
                                                    }
                                                }
                                            }
                                            console.log(that.simpleLinkSettingAction);
                                        } else if (type == 2) {

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
                                        //$('#deleteTrainModal').modal('hide');
                                        //$.alert(resData['data']['msg']);

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
            addTrainConfirm: function () {
                if (!this.addTrainName || this.addTrainName == '') {
                    $.alert('名称不能为空');
                    return;
                }


                let that = this;

                let url = $.stringFormat("{0}/frTrainingSeries/addOrUpdate", 'http://www.4006337366.com:8080/');

                let jsonData = {
                    name: String(this.addTrainName),
                    isUsing: 1,
                    type: this.addTrainType,
                    parentId: this.addTrainParentId,
                    ownType: 2,
                    sdaduimId:this.sdaduimId,
                };
                // Loading.prototype.show();
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
            cancelTrainAdd: function () {
                this.addTrainName = '';
                this.addTrainType = '';
                this.addTrainParentId = '';

            },
            updateItemConfirm: function () {

                if (!this.updateItemName || this.updateItemName == '') {
                    $.alert('名称不能为空');
                    return;
                }
                var that = this;
                if(that.updateType==3){
                    var url = $.stringFormat("{0}/frTrainingSeries/updateAction", 'http://www.4006337366.com:8080/');
                    var jsonData = {
                        id: this.updateItemId,
                        name: String(this.updateItemName),
                    };
                }else {
                    var url = $.stringFormat("{0}/frTrainingSeries/addOrUpdate", 'http://www.4006337366.com:8080/');
                    var jsonData = {
                        id: this.updateItemId,
                        name: String(this.updateItemName),
                        isUsing: 1,
                        type: this.updateType,
                        ownType: 2,
                        sdaduimId:this.sdaduimId,
                    };
                }

                // Loading.prototype.show();
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
            cancelItemUpate: function () {
                // this.addTrainName ='';
                // this.addTrainType ='';
                // this.addTrainParentId ='';

            },
            deleteItemConfirm: function () {
                var that = this;
                if(this.deletItemType==3){
                    var url = $.stringFormat("{0}/frTrainingSeries/updateAction", 'http://www.4006337366.com:8080/');
                    var jsonData = {
                        id: this.deleteItemId,
                        isUsing: 0,
                    };
                }else{
                    var url = $.stringFormat("{0}/frTrainingSeries/addOrUpdate", 'http://www.4006337366.com:8080/');
                    var jsonData = {
                        id: this.deleteItemId,
                        isUsing: 0,
                        type: this.deletItemType,
                        ownType: 2,
                        sdaduimId:this.sdaduimId,
                    };
                }
                // Loading.prototype.show();
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


                        }else if(that.deletItemType == 3){
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
            canceldeleteItem: function () {


            },
            deleteTrainConfirm: function () {
                let that = this;

                let url = $.stringFormat("{0}/frTrainingSeries/addOrUpdate", 'http://www.4006337366.com:8080/');

                let jsonData = {
                    id: this.deleteTrainId,
                    isUsing: 0,
                    type: this.deletTrainType,
                    ownType: 2,
                    sdaduimId:this.sdaduimId,
                };
                // Loading.prototype.show();
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
            canceldeleteTrain: function () {

            },
            updateTrainConfirm: function () {

                if (!this.updateTrainName || this.updateTrainName == '') {
                    $.alert('名称不能为空');
                    return;
                }
                let that = this;

                let url = $.stringFormat("{0}/frTrainingSeries/addOrUpdate", 'http://www.4006337366.com:8080/');

                let jsonData = {
                    id: this.updateTrainId,
                    name: String(this.updateTrainName),
                    isUsing: 1,
                    type: this.updateTrainType,
                    ownType: 2,
                    sdaduimId:this.sdaduimId,
                };
                // Loading.prototype.show();
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
            cancelupdateTrain: function () {
                this.updateTrainId = '';
                this.updateTrainName = '';
                // this.addTrainName ='';
                // this.addTrainType ='';
                // this.addTrainParentId ='';
            },
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

                let url = $.stringFormat("{0}/frTraningClass/batchUpdateFrTraningClass", 'http://www.4006337366.com:8080/');

                let list = this.mealActionChanges || this.trainActionChanges;
                let count = list.length;
                for (let i = 0; i < count; i++) {
                    list[i].isUsing = 1;
                    list[i].type = type;
                    list[i].ownType = 2;
                }

                let jsonData = type == 1 ? this.trainActionChanges : this.mealActionChanges;
                // Loading.prototype.show();
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

                let url = $.stringFormat("{0}/frTraningClass/batchAddFrTraningClass", 'http://www.4006337366.com:8080/');
                if (type == 1) {
                    var jsonData = {
                        traningSeriesId: this.simpleLinkSettingActionSubSelectId,
                        actionIds: that.trainAddActionIds.join(','),
                        type: type,
                        ownType: 2
                    };
                } else if (type == 2) {
                    var jsonData = {
                        traningSeriesId: this.mealLinkSettingActionSubSelectId,
                        actionIds: that.trainAddActionIds.join(','),
                        type: type,
                        ownType: 2
                    };
                }

                // Loading.prototype.show();
                //  jsonData.ownType = 2;
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

                                    let url = $.stringFormat("{0}/frTraningClass/list", 'http://www.4006337366.com:8080/');

                                    let jsonData2 = {
                                        traningSeriesId: jsonData.traningSeriesId,
                                        type: type,
                                        ownType: 2
                                    };
                                    // Loading.prototype.show();

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

                                    let url = $.stringFormat("{0}/frTraningClass/list", 'http://www.4006337366.com:8080/');

                                    let jsonData2 = {
                                        traningSeriesId: jsonData.traningSeriesId,
                                        type: type,
                                        ownType: 2
                                    };
                                    // Loading.prototype.show();

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


                        let url = $.stringFormat("{0}/file/upload", 'http://www.4006337366.com:8080/');
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
                                    object.image = $.stringFormat('{0}{1}{2}', 'http://www.4006337366.com:8080/', $.cookie('imgPath'), res.data.imgUrl);
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
            delEduAction: function (index, action, type) {
                let that = this;

                let url = $.stringFormat("{0}/frTraningClass/addOrUpdate", 'http://www.4006337366.com:8080/');

                let jsonData = {
                    id: action.id,
                    isUsing: 0,
                };
                // Loading.prototype.show();

                $.confirm({
                    title: '确认',
                    content: '确认删除?',
                    buttons: {
                        ok: {
                            text: '确认',
                            btnClass: 'btn-primary',
                            action: function () {
                                //确认后的操作
                                jsonData.ownType = 1;
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

            //删除动作
            delAction: function (index, actionType) {
                let that = this;


                let url = $.stringFormat("{0}/frAction/addOrUpdate", 'http://www.4006337366.com:8080/');
                $.confirm({
                    title: '确认',
                    content: '确认删除?',
                    buttons: {
                        ok: {
                            text: '确认',
                            btnClass: 'btn-primary',
                            action: function () {
                                //确认后的操作
                                actionType.isUsing = 0;

                                // Loading.prototype.show();
                                // ajax提交
                                axios.post(url, actionType)
                                    .then(function (res) {
                                        let resData = eval(res);
                                        that.actions[that.actionSettingIndex].actionList.splice(index, 1);
                                        that.actions.splice(0, 0);

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

                    }
                } else {
                    if (type == 0) {
                        this.changeActionIds = [];
                    } else if (type == 1) {
                        this.trainAddActionIds = [];
                    }

                }

                //this.changeActionIds.join(","),

            },
            noFuc: function (val) {
                document.getElementById('premium_Max').value = val >= 0 ? val : 0;
            },

            shopClick: function () {
                var that = this;
                var options = $("#shopSelect option:selected");
                console.log(options.val())
                console.log(that.parenNode.id)
                that.initSelectableTree(options.val())
            },
        },
        watch: {
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
                        //   var i="";
                    }

                })
            },
        }
    })
});
