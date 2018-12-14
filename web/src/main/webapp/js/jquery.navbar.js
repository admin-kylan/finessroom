//创建无图片格式左侧菜单栏，展开/收缩的图标放在左边，小菜单图标放在右侧
//Author：ydlemon
//Time：2018-05-30 09:57
//Time：2018-06-02 09:30 ydlemon Modify 新增参数btnType

function Navbar(options) {
    this.config = {
        containerClass: "", //绑定点击对象  传递的格式为：#id或.class
        menuClass: "", //点击"..."图标显示的菜单栏对象 传递的格式为：#id或.class
        arr: null, //数据源数组
        btnType: 0,//按钮类型 0代表"..."图标 1代表"向右"图标 当为1时menuClass 参数不可传递
        isNeedBtn: false,//是否需要"..."的图标
        callback: options.callback || function () {
        }
    };
    this.cache = {}; //数据缓存数据变量
    this.init(options);
}

Navbar.prototype = {
    constructor: Navbar,
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
     * html元素渲染
     */
    renderHtml: function () {

        //#region 定义变量
        var self = this,
            _config = self.config,
            _cache = self.cache;
        var menuUl = 'M' + new Date().getTime(); //主体菜单栏
        var subMenu = 'Sub' + new Date().getTime(); //子集菜单栏
        var spanClass = 'Span' + new Date().getTime(); //"..."图标对应class
        //#endregion

        //#region 创建样式
        var style = $("<style type=\"text/css\"> " +
            "       ul, li {" +
            "            list-style: none;" +
            "        }" +
            "        a:link," +
            "        a:visited {" +
            "            text-decoration: none;" +
            "        }" +
            "        /*菜单栏总体样式*/" +
            "        ." + menuUl + " {" +
            "            height: auto;" +
            "            width: 100%;" +
            "            overflow: hidden;" +
            "        }" +
            "        ." + menuUl + " li {" +
            "            display: block;" +
            "            margin-bottom: 1px;" +
            "            padding: 0;" +
            "            border: 0px;" +
            "        }" +
            "        ." + menuUl + " li:nth-child(1) {" +
            "            margin-top: 1px;" +
            "        }" +
            "        ." + menuUl + " li a {" +
            "            overflow: hidden;" +
            "            color: #323232;" +
            "            display: block;" +
            "            height: 34px;" +
            "            line-height: 34px;" +
            "            position: relative;" +
            "            transition: all .3s;" +
            "            text-decoration: none;" +
            "        }" +
            "        ." + menuUl + " li a span.noarrow {" +
            "            padding-left: 22px;" +
            "        }" +
            "        ." + menuUl + " li a .arrow:before {" +
            "            float: left;" +
            "            margin: 0;" +
            "            padding: 0;" +
            "            margin-left: 10px;" +
            "            margin-right: 6px;" +
            "            display: inline;" +
            "            font-size: 16px;" +
            "            font-family: FontAwesome;" +
            "            height: auto;" +
            "            content: \"\\f0da\";" +
            "            font-weight: 300;" +
            "            color: #999999;" +
            "            text-shadow: none;" +
            "        }" +
            "        ." + menuUl + " li a .arrow.open:before {" +
            "            float: left;" +
            "            margin: 0;" +
            "            padding: 0;" +
            "            margin-left: 10px;" +
            "            margin-right: 6px;" +
            "            margin-top: -4px;" +
            "            display: inline;" +
            "            font-family: FontAwesome;" +
            "            height: auto;" +
            "            font-size: 16px;" +
            "            content: \"\\f0dd\";" +
            "            font-weight: 300;" +
            "            color: #323232;" +
            "            text-shadow: none;" +
            "        }" +
            "        ." + menuUl + " li a .context:before {" +
            "            float: right;" +
            "            margin-right: 15px;" +
            "            display: inline;" +
            "            font-size: 16px;" +
            "            font-family: FontAwesome;" +
            "            height: auto;" +
            "            content: \"...\";" +
            "            font-weight: 300;" +
            "            text-shadow: none;" +
            "            cursor: pointer;" +
            "        }" +
            "        ." + menuUl + " li a .arrowRight:before {" +
            "            float: right;" +
            "            margin-right: 15px;" +
            "            display: inline;" +
            "            font-size: 16px;" +
            "            font-family: FontAwesome;" +
            "            height: auto;" +
            "            content: \"\\f178\";" +
            "            font-weight: 300;" +
            "            color: silver;" +
            "            text-shadow: none;" +
            "            cursor: pointer;" +
            "        }" +
            "        ." + menuUl + " li a.active," +
            "        ." + menuUl + " li a:hover {" +
            "            color: #3C3C3C;" +
            "            background-color: #EBEDF0;" +
            "        }" +
            "        ." + menuUl + " ." + subMenu + " li a.active," +
            "        ." + menuUl + " li a.active{" +
            "            color: #0080ff;" +
            "        }" +
            "        ." + menuUl + " ." + subMenu + " li a {" +
            "            display: block;" +
            "            height: 34px;" +
            "            line-height: 34px;" +
            "            color: rgb(50, 50, 50);" +
            "            clear: both;" +
            "        }" +
            "        ." + menuUl + " ." + subMenu + " li:nth-child(1) {" +
            "            margin-top: 1px;" +
            "        }" +
            "        ." + menuUl + " ." + subMenu + " {" +
            "            display: none;" +
            "            clear: both;" +
            "            margin: 0px 0px 1px 14px;" +
            "        }</style>");
        $("head").append(style);
        //#endregion

        //#region 创建HTML代码
        var tempData = self.selectTreeJson(_config.arr, "-1");
        if (tempData != undefined && tempData.length > 0) {
            self.createHtml(menuUl, subMenu, spanClass, tempData);
        }
        //#endregion

        self.bindNavbarEvent(menuUl, spanClass);
    },

    /**
     * 创建HTML代码
     * @param menuUl 主体菜单栏
     * @param subMenu 子集菜单栏
     * @param spanClass "..."图标对应class
     * @param arr 具有树形结构的数据源
     */
    createHtml: function (menuUl, subMenu, spanClass, arr) {
        var ulHtml = $("<ul class='" + menuUl + "'></ul>");
        var self = this,
            _config = self.config,
            _cache = self.cache;
        $(arr).each(function (index, item) {
            var lihtml = $("<li></li>");
            var linkA = $("<a class='" + item.ID + "' data-id='" + item.ID + "' data-parentid='" + item.ParentId + "' href='javascript:;' data-isclick='0'></a>")
            var firstSpan = $("<span>" + item.NavName + "</span>");
            linkA.append(firstSpan);
            if (_config.isNeedBtn) {
                var secondSpan = $("<span class='" + spanClass + "'></span>");
                linkA.append(secondSpan);
            }
            lihtml.append(linkA);
            if (item.child && item.child.length > 0) {
                firstSpan.addClass("arrow");
                self.createSubMenu(item.child, lihtml, subMenu, spanClass, _config.isNeedBtn);
            } else {
                firstSpan.addClass("noarrow");
            }
            ulHtml.append(lihtml);
        })
        $(_config.containerClass).append(ulHtml);
    },

    /**
     * 获取具有树形结构的数据源
     * @param oldArr 旧数据
     * @param pid parentid
     * @return {Array} 具有树形结构数组
     */
    selectTreeJson: function (oldArr, pid) {
        var newArr = [];
        var self = this;
        oldArr.map(function (item) {
            if (item.ParentId == pid) {
                var obj = {
                    ID: item.ID, //ID
                    ParentId: item.ParentId, //上级Id
                    NavName: item.NavName, //菜单栏文本名称
                }
                var child = self.selectTreeJson(oldArr, item.ID); //递归操作
                if (child.length > 0) {
                    obj.child = child
                }
                newArr.push(obj)
            }
        })
        return newArr;
    },

    /**
     * 创建子集菜单栏
     * @param oldArr 子菜单数组
     * @param lihtml  li项html
     * @param subMenu 子集父菜单ul class
     * @param spanClass "..."图标对应class
     * @param isNeedBtn 是否需要创建"..."图标
     */
    createSubMenu: function (oldArr, lihtml, subMenu, spanClass, isNeedBtn) {
        var self = this;
        var subUl = $("<ul class='" + subMenu + "'></ul>"),
            callee = arguments.callee,
            subLi;
        $(oldArr).each(function (index, item) {
            subLi = $("<li></li>");
            var linkA = $("<a class='" + item.ID + "' data-id='" + item.ID + "' data-parentid='" + item.ParentId + "' href='javascript:;'  data-isclick=0></a>")
            var firstSpan = $("<span>" + item.NavName + "</span>");
            linkA.append(firstSpan);
            if (isNeedBtn) {
                var secondSpan = $("<span class='" + spanClass + "'></span>")
                linkA.append(secondSpan);
            }
            subLi.append(linkA);
            if (item.child && item.child.length > 0) {
                firstSpan.addClass("arrow");
                callee(item.child, subLi, subMenu, spanClass, isNeedBtn);
            } else {
                firstSpan.addClass("noarrow");
            }
            $(subUl).append(subLi);
        });
        $(lihtml).append(subUl);

    },

    /**
     * 事件绑定
     * @param menuUl 主体菜单栏
     * @param spanClass "..."图标对应class
     */
    bindNavbarEvent: function (menuUl, spanClass) {
        var self = this,
            _config = self.config,
            _cache = self.cache;

        //#region 鼠标划入划出
        //鼠标滑过
        if (_config.isNeedBtn) {
            $("." + menuUl + " li a").bind("mouseenter", function () {
                var _self = $(this);
                _self.find("span").each(function (index) {
                    if (index == 1) {
                        if (_config.btnType == 0)
                            $(this).addClass("context");
                        else
                            $(this).addClass("arrowRight");
                    }
                })
            })

            //鼠标移出
            $("." + menuUl + " li a").bind("mouseleave", function () {
                var _self = $(this);
                _self.find("span").each(function (index) {
                    if (index == 1) {
                        if (!_self.hasClass("active")) {
                            if (_config.btnType == 0)
                                $(this).removeClass("context");
                            else
                                $(this).removeClass("arrowRight");
                        }
                        else {
                            if (_self.data("isclick") == 0) {
                                if (_config.btnType == 0)
                                    $(this).removeClass("context");
                                else
                                    $(this).removeClass("arrowRight");
                            }
                        }
                    }
                })
            })

        }
        //#endregion

        //#region 鼠标点击
        $("." + menuUl + "").delegate("li a", "click", function (e) {
            var _self = $(this);

            //#region 针对激活状态做处理
            if (_config.isNeedBtn) {
                if (_config.btnType == 0)
                    $("." + menuUl + " li a").find("span").removeClass("context");
                else
                    $("." + menuUl + " li a").find("span").removeClass("arrowRight");
                $(this).find("span").each(function (index) {
                    if (index == 1) {
                        if (_config.btnType == 0)
                            $(this).addClass("context");
                        else
                            $(this).addClass("arrowRight");
                    }
                })
                if (_config.btnType == 0)
                    $(_config.menuClass).css("display", "none");
            }

            //isclick 0代表没有点击"..."图标 反之1有
            $("." + menuUl + " li").find("a").each(function () {
                if ($(this).hasClass("active"))
                    $(this).data("isclick", 0);
            })
            //#endregion

            //#region 预备数据
            e.preventDefault(); //不执行跳转操作
            e.stopPropagation(); //禁止向上冒泡
            var parent = $(this).parent().parent(); //获取当前页签的父级的父级
            var labeul = $(this).parent("li").find(">ul");
            //#endregion

            //#region  展开与收缩
            if (!$(this).parent().hasClass('open')) {
                parent.find('ul').slideUp(300); //收缩
                parent.find("li").removeClass("open");
                parent.find('li a').removeClass("active").find(".arrow").removeClass("open");
                if ($(e.target).hasClass("context")) {
                    _self.data("isclick", 1);
                    if (!_self.hasClass("active"))
                        _self.addClass("active");
                    _config.callback(_self.data("id"), -1);
                    if ($(_config.menuClass).css("display") == "none") {
                        $(_config.menuClass).css({
                            "display": "block",
                            "left": _self.offset().left + _self.width() - $(_config.menuClass).width() - 2,
                            "top": $(e.target).offset().top + _self.height() - ($(e.target).height() / 2),
                        })
                    }
                    return;
                } else if ($(e.target).hasClass("arrowRight")) {
                    _self.data("isclick", 1);
                    if (!_self.hasClass("active"))
                        _self.addClass("active");
                    _config.callback(_self.data("id"), -1);
                    return;
                }
                $(this).parent("li").addClass("open").find(labeul).slideDown(300);
                $(this).addClass("active").find(".arrow").addClass("open");
                _config.callback(_self.data("id"), 0); //展开
            } else {
                if ($(e.target).hasClass("context")) {
                    _self.data("isclick", 1);
                    if (!_self.hasClass("active"))
                        _self.addClass("active");
                    _config.callback(_self.data("id"), -1);
                    if ($(_config.menuClass).css("display") == "none") {
                        $(_config.menuClass).css({
                            "display": "block",
                            "left": _self.offset().left + _self.width() - $(_config.menuClass).width() - 2,
                            "top": $(e.target).offset().top + _self.height() - ($(e.target).height() / 2),
                        })
                    }
                    return;
                } else if ($(e.target).hasClass("arrowRight")) {
                    _self.data("isclick", 1);
                    if (!_self.hasClass("active"))
                        _self.addClass("active");
                    _config.callback(_self.data("id"), -1);
                    return;
                }
                $(this).parent("li").removeClass("open").find(labeul).slideUp(300);
                if ($(this).parent().find("ul").length > 0) {
                    $(this).removeClass("active").find(".arrow").removeClass("open")
                } else {
                    $(this).addClass("active")
                }
                _config.callback(_self.data("id"), 1); //收缩
            }
            //#endregion

        })
        //#endregion

        //#region 判断点击事件是否指定区域
        if (_config.isNeedBtn) {
            if (_config.btnType == 0) {
                $(document).click(function (e) {
                    e = window.event || e; // 兼容IE7
                    var obj = $(e.srcElement || e.target);
                    if (!($(obj).is("" + _config.menuClass + "," + _config.menuClass + " *"))
                        && !($(obj).is("." + spanClass))) {

                        $(_config.menuClass).css({"display": "none"});
                    }
                });
            }
        }
        //#endregion
    }
}