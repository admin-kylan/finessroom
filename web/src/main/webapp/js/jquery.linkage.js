//该功能函数用于创建联动效果下拉数据选择
//author:ydlemon
//time:2018-05-21 16:13

function Linkage(options) {
    this.config = {
        containerClass: "", //绑定点击对象，用于触发显示信息选择框 传递的格式为：#id或.class
        arr: null, //  JSON传进来的数据
        renderCallBack: null, // 渲染html结构后回调
        isConsiderParent: false,//是否需要考虑点击对象的父容器，通常用于计算宽度
        isAddWidth: false,//是否需要给对应的下拉信息框追加宽度
        addWith: 0,//追加的宽度大小 默认值为0 当isAddWidth为true时才生效
    };
    this.cache = [];//定义缓存数据变量
    this.init(options);
}

Linkage.prototype = {
    constructor: Linkage,
    /**
     * 初始化
     * @param options 参数集
     */
    init: function (options) {
        this.config = $.extend(this.config, options || {});
        var self = this,
            _config = self.config,
            _cache = self.cache;
        // 渲染html结构  
        self.renderHtml();
    },
    /**
     * HTML页面元素渲染
     */
    renderHtml: function () {

        //#region 定义变量
        var self = this,
            _config = self.config,
            _cache = self.cache;
        var _Linkage = new Date().getTime();
        _Linkage = 'Y' + _Linkage;
        var  contentsMain='C'+new Date().getTime();
        //#endregion

        //#region 创建div
        var divHtml = $('<div class="'+contentsMain+' ' + _Linkage + '">' +
            '    <!--顶部选择门店/部门/团队类似结构数据-->' +
            '    <div class="contents-top"></div>' +
            '    <!--筛选-->' +
            '    <div class="contents-center"></div>' +
            '    <!--底部操作-->' +
            '    <div class="contents-bottom">' +
            '        <span>点击完成即为选择完成</span>' +
            '        <span class="ok">完成</span>' +
            '        <span class="cancel">取消</span>' +
            '    </div>' +
            '</div>');
        $("body").append(divHtml);
        //#endregion

        //#region 创建样式
        var style = $("<style type=\"text/css\"> " +
            "      ul,li {" +
            "            list-style: none;" +
            "        }" +
            "/*选择框设置*/" +
            "        ."+contentsMain+" {" +
            "            display: none;" +
            "            height: 260px;" +
            "            position: absolute;" +
            "            left: 0;" +
            "            top: 0;" +
            "            border: 1px solid silver;" +
            "            background-color: #FFFFFF;" +
            "            -moz-box-shadow: 2px 2px 5px #909090;" +
            "            -webkit-box-shadow: 2px 2px 5px #909090;" +
            "            box-shadow: 2px 2px 5px #909090;" +
            "        }" +
            "        /*整体div元素*/" +
            "        ."+contentsMain+" > div {" +
            "            display: block;" +
            "            width: 100%;" +
            "            float: left;" +
            "        }" +
            "        /*顶部选择*/" +
            "        ."+contentsMain+" .contents-top {" +
            "            height: 29px;" +
            "            border-bottom: 1px solid silver;" +
            "        }" +
            "        ."+contentsMain+" .contents-top span {" +
            "            float: left;" +
            "            display: block;" +
            "            width: auto;" +
            "            height: 30px;" +
            "            line-height: 29px;" +
            "            margin-left: 10px;" +
            "            cursor: pointer;" +
            "        }" +
            "        ."+contentsMain+" .contents-top .selected {" +
            "            color: #c9302c;" +
            "            height: 28px;" +
            "            line-height: 28px;" +
            "            border-bottom: 1px solid #c9302c;" +
            "        }" +
            "        /*中间内容显示区域*/" +
            "        ."+contentsMain+" .contents-center {" +
            "            height: calc(100% - 60px);" +
            "            overflow-x: hidden;" +
            "            overflow-y: auto;" +
            "        }" +
            "        /*内容显示div*/" +
            "        ."+contentsMain+" .contents-center div {" +
            "            display: none;" +
            "            width: 100%;" +
            "            height: 100%;" +
            "            overflow-x: hidden;" +
            "            overflow-y: auto;" +
            "        }" +
            "        /*内容显示激活状态*/" +
            "        ."+contentsMain+" .contents-center .actived {" +
            "            display: block;" +
            "        }" +
            "        /*内容显示ul-li样式*/" +
            "        ."+contentsMain+" .contents-center ul li {" +
            "            display: block;" +
            "            width: 100%;" +
            "            height: 30px;" +
            "            line-height: 30px;" +
            "            text-indent: 10px;" +
            "            margin-bottom: 1px;" +
            "        }" +
            "        ."+contentsMain+" .contents-center ul li:nth-child(1) {" +
            "            margin-top: 1px;" +
            "        }" +
            "        /*内容显示ul-li鼠标滑过*/" +
            "        ."+contentsMain+" .contents-center ul li:hover {" +
            "            background-color: #8aa4be;" +
            "            color: #FFFFFF;" +
            "        }" +
            "        ."+contentsMain+" .contents-center ul .selected {" +
            "            color: #c9302c;" +
            "        }" +
            "        /*底部信息栏*/" +
            "        ."+contentsMain+" .contents-bottom {" +
            "            height: 30px;" +
            "            line-height: 30px;" +
            "            text-indent: 10px;" +
            "            background-color: #F2F2F1;" +
            "            color: #999999;" +
            "        }" +
            "        /*确定*/" +
            "        ."+contentsMain+" .contents-bottom span:nth-child(2) {" +
            "            display: block;" +
            "            float: right;" +
            "            margin-right: 20px;" +
            "            color: #0291ED;" +
            "            cursor: pointer;" +
            "        }" +
            "        /*取消*/" +
            "        ."+contentsMain+" .contents-bottom span:nth-child(3) {" +
            "            display: block;" +
            "            float: right;" +
            "            margin-right: 10px;" +
            "            cursor: pointer;" +
            "        }</style>");
        $("head").append(style);
        //#endregion

        //#region 创建内容显示HTML代码
        var tempData = self.selectEquativeData("-1");
        if (tempData != undefined && tempData.length > 0) {
            self.createHtml(_Linkage, tempData, "-1");
        }
        //#endregion

        //#region 事件绑定
        self.bindEvent(_Linkage);
        //#endregion

        //#region 回调函数
        _config.renderCallBack && $.isFunction(_config.renderCallBack) && _config.renderCallBack();
        //#endregion
    },
    /**
     * 创建选择信息栏
     * @param linkageClass 创建选择框对应的classname
     * @param arr 创建数据源
     * @param pid 父节点ID
     */
    createHtml: function (linkageClass, arr, pid) {
        var self = this,
            _config = self.config,
            _cache = self.cache;
        if (arr.length > 0) {
            var contents = $("." + linkageClass + " .contents-center");
            $(contents).find("div").removeClass("actived");//移除所有的选中状态
            var boxClassName = pid == "-1" ? "firstPage" : pid;
            var length = 0;//判断当前的pid是否创建过，如果是创建过，直接元素清除
            contents.find("div").each(function () {
                if ($(this).hasClass(boxClassName))
                    length++;
            })
            var box;//定义变量存储

            //#region 内容容器判断
            if (length > 0) {
                box = $("." + linkageClass + " .contents-center ." + boxClassName);
                box.empty();
            } else {//未创建
                //#region 切换标签页
                // 创建切换标签页
                var spanHtml = $("<span  class='" + boxClassName + " selected' data-selectid='" + boxClassName + "' data-id='-1'>请选择</span>")
                $("." + linkageClass + " .contents-top").append(spanHtml);
                //切换标签页事件绑定
                spanHtml.bind("click", function () {
                    $("." + linkageClass + " .contents-center").find("div").removeClass("actived");
                    var clsName = $(this).data("selectid");
                    $("." + linkageClass + " .contents-center ").find("div").each(function () {
                        if ($(this).hasClass(clsName))
                            $(this).addClass("actived");
                    })
                    $(this).parent().find("span").removeClass("selected");
                    $(this).addClass("selected");
                })
                //#endregion
                //#region 创建内容容器
                boxClassName += " actived";
                box = $("<div class='" + boxClassName + "'></div>")
                $(contents).append(box);
                //#endregion
            }
            //#endregion

            //#region 创建ul-li及事件绑定处理
            var ulHtml = $("<ul></ul>");
            box.append(ulHtml);
            $(arr).each(function (index, item) {
                var liHtml = $("<li class='" + item.ID + "' data-parentid='" + item.ParentId + "' data-index='" + index + "'>" + item.NavName + "</li>");
                ulHtml.append(liHtml);
                //绑定点击事件
                liHtml.bind("click", function () {

                    var ParentId = $(this).data("parentid");
                    var spanClass = ParentId == "-1" ? "firstPage" : ParentId;//获取点击对象的class

                    //#region 当前点击对象为已经创建对象
                    if (!$(this).hasClass("selected")) {//当前点击的对象不是已经选中的元素

                        //#region  对ul-li样式变化进行操作
                        $(this).parent().find("li").removeClass("selected");//移除所有的选中效果
                        var classId = $(this).attr("class");//暂存当前点击的class，因为待会将创建一个"selected"class名称
                        $(this).addClass("selected");
                        //#endregion

                        //#region  获取点击的对象数据 对应的标签页及内容进行移除操作
                        $("." + linkageClass + " .contents-top ." + spanClass).text($(this).text());
                        $("." + linkageClass + " .contents-top ." + spanClass).data("id", classId);
                        $("." + linkageClass + " .contents-top ." + spanClass).nextAll().remove();//将当前元素后面的所有元素进行移除
                        $(this).parent().parent().nextAll().remove();//移除整个div
                        //#endregion

                        //#region 数据查找，如果存在继续递归创建
                        var tempData = self.selectEquativeData(classId);//查找当前点击对象下属所有数据
                        if (tempData != undefined && tempData.length > 0) {
                            $("." + linkageClass + " .contents-top ." + spanClass).removeClass("selected");
                            self.createHtml(linkageClass, tempData, classId);
                        } else {
                            var selectText = "";
                            $("." + linkageClass + " .contents-top").find("span").each(function () {
                                if ($(this).text().trim() != "请选择")
                                    selectText += $(this).text().trim() + " ";
                            })
                            $(_config.containerClass).val(selectText);
                            if ($("." + linkageClass + " .contents-top .selected").text() == "请选择")
                                $(_config.containerClass).data("id", $("." + linkageClass + " .contents-top .selected").prev().data("id"));
                            else
                                $(_config.containerClass).data("id", $("." + linkageClass + " .contents-top .selected").data("id"));
                            $("." + linkageClass).slideUp(300);
                        }
                        //#endregion

                    }
                    //#endregion

                    //#region 点击的是已经选中的元素 直接执行切换的操作
                    else {
                        $("." + linkageClass + " .contents-top ." + spanClass).next().click();
                    }
                    //#endregion
                })
            })
            //#endregion

            //#region 判断方向结构数据是否有值，如果有值执行下一步操作
            if ($(_config.containerClass).data("assign") == "1" && $(_config.containerClass).data("isclick") == "1") {
                ulHtml.find("li").each(function () {
                    if (_cache.length > 0) {
                        if ($(this).hasClass(_cache[0].ID)) {
                            self.removeArray(_cache, _cache[0].ID);
                            $(this).click();
                            return false;
                        }
                    } else
                        $(_config.containerClass).data("assign", 0);
                })
            }
            //#endregion

        }
    },
    /**
     * 事件绑定
     * @param linkageClass linkageClass 创建选择框对应的classname
     */
    bindEvent: function (linkageClass) {
        var self = this,
            _config = self.config,
            _cache = self.cache;

        //#region 文本接收框事件绑定
        $(_config.containerClass).bind("click", function () {

            //#region  模拟点击操作
            $(this).data("isclick", "1");//当前是点击状态
            //判断当前点击的input对象的assign值是否为1，如果是1的话代表当前是赋值的，需要模拟点击
            if ($(this).data("assign") == "1") {
                var Id = $(this).data("id");
                if (Id != "-1") {//判断当前的值是否为"-1",如果是"-1"代表该值为默认值 不需要模拟点击操作
                    _cache = [];//重置数据
                    var obj = self.selectParentId(_config.arr, Id);//获取对象
                    _cache = self.selectParentData(_config.arr, obj.ParentId);//获取具有上级机构的数据
                    _cache.push(obj);//将当前的对象也进行追加操作
                    if (_cache.length > 0) {//判断当前获取的具有上级机构的数组长度大于0
                        $("." + linkageClass + " .contents-center ul").find("li").each(function () {
                            if (_cache.length > 0) {
                                if ($(this).hasClass(_cache[0].ID)) {
                                    self.removeArray(_cache, _cache[0].ID);
                                    $(this).click();
                                    return false;
                                }
                            }
                        })
                    }
                }
            }
            // #endregion

            //获取对应的参数 计算宽度
            var width = 0;
            if (_config.isConsiderParent)
                width = $(this).parents().width();
            else
                width = $(this).width();
            if (_config.isAddWidth)
                width += _config.addWith;
            width=width<230?230:width;
            var height = $(this).height();
            var left = $(this).offset().left;
            var top = $(this).offset().top;
            $("." + linkageClass).css({
                "width": width,
                "top": top + height + 2,
                "left": left,
            }).slideDown(300);
        })
        //#endregion

        //#region 点击确定
        $("." + linkageClass + " .contents-bottom .ok").bind("click", function () {
            var selectText = "";
            $("." + linkageClass + " .contents-top").find("span").each(function () {
                if ($(this).text().trim() != "请选择")
                    selectText += $(this).text().trim() + " ";
            })
            $(_config.containerClass).val(selectText);
            if ($("." + linkageClass + " .contents-top .selected").text() == "请选择")
                $(_config.containerClass).data("id", $("." + linkageClass + " .contents-top .selected").prev().data("id"));
            else
                $(_config.containerClass).data("id", $("." + linkageClass + " .contents-top .selected").data("id"));
            $("." + linkageClass).slideUp(300);
        })
        //#endregion

        //#region 点击取消
        $("." + linkageClass + " .contents-bottom .cancel").bind("click", function () {
            $("." + linkageClass).slideUp(300);
        })
        //#endregion

        //#region 窗体大小改变事件
        $(window).resize(function () {
            if ($("." + linkageClass).css("display") != "none") {
                //获取对应的参数 计算宽度
                var width = 0;
                if (_config.isConsiderParent)
                    width = $(_config.containerClass).parents().width();
                else
                    width = $(_config.containerClass).width();
                if (_config.isAddWidth)
                    width += _config.addWith;
                width=width<230?230:width;
                var height = $(_config.containerClass).height();
                var left = $(_config.containerClass).offset().left;
                var top = $(_config.containerClass).offset().top;
                $("." + linkageClass).css({
                    "width": width,
                    "top": top + height + 2,
                    "left": left,
                });
            }
        });
        //#endregion

        //#region 判断点击事件是否指定区域
        $(document).click(function (e) {
            e = window.event || e; // 兼容IE7
            var obj = $(e.srcElement || e.target);
            if (!($(obj).is("."+linkageClass+",."+linkageClass+" *"))
                && !($(obj).is(_config.containerClass))) {
                $("." + linkageClass).slideUp(300);
            }
        });
        //#endregion

    },
    /**
     * 获取指定parentId的同级数据
     * @param pid 父节点ID
     * @return {Array} 返回parentId相同的数据集合
     */
    selectEquativeData: function (pid) {
        var self = this,
            _config = self.config,
            _cache = self.cache;
        var tempData = [];//缓存数据
        $(_config.arr).each(function (index, item) {
            if (item.ParentId == pid) {
                var obj = {
                    ID: item.ID,
                    ParentId: item.ParentId,
                    NavName: item.NavName
                }
                tempData.push(obj);
            }
        });
        return tempData;
    },
    /**
     * 获取当前元素上级数据集合,用于反向模拟效果
     * @param arr 数据集
     * @param pid  父节点Id
     * @returns {Array} 返回具有上级的方向结构
     */
    selectParentData: function (arr, pid) {
        var self = this,
            _cache = self.cache;
        arr.map(function (item) {
            if (item.ID == pid) {
                var obj = {
                    ID: item.ID,
                    ParentId: item.ParentId,
                    NavName: item.NavName
                }
                self.selectParentData(arr, item.ParentId);
                _cache.push(obj);
            }
        })
        return _cache;
    },
    /**
     * 获取当前指定ID的ParentId
     * @param arr 数据源
     * @param id 当前数据ID
     * @return {*} 返回对象
     */
    selectParentId: function (arr, id) {
        var obj;
        $(arr).each(function (index, item) {
            if (item.ID == id) {
                obj = item;
                return false;//退出整个循环 todo:return true相当于continue;return false相当于break;
            }
        })
        return obj;
    },
    /**
     * 删除数据
     * @param arr 数据源
     * @param id 元素对应的Id
     */
    removeArray: function (arr, id) {
        var tempIndex = 0;
        $(arr).each(function (index, item) {
            if (item.ID == id) {
                tempIndex = index;
                return false;
            }
        })
        arr.splice(tempIndex, 1);
    }


}

