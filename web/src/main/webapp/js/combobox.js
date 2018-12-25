/**
 * 以下的功能是创建下拉框
 * author：yd_lemon
 * datetime：2018-05-10 15:50 Create */
(function ($) {

    //#region  定义
    var _ParentArray = [];//暂存层级关系数组
    var CachArry = [];//缓存数组
    //#endregion

    //#region 初始化
    /**
     *  初始化
     * @param element 元素 例如传 $(this)
     * @param options 传递参数
     * @constructor
     */
    var ComboBox = function (element, options) {
        this.element = element;
        //数据源数据
        this.arr = options.arr;
        this.needBorder = options.needBorder;//是否需要外边框
        this.offsetLeftX = options.offsetLeftX || 0;//向左偏移大小
        //回调函数
        this.callback = options.callback || function () {
        };
        //返回创建的class，方便再次数据动态创建使用
        this.returnclass = options.returnclass || function () {
        };
        //创建
        this.CreateComboBox(this.arr, this.element);
    };
    // #endregion

    //#region 创建下拉选项框
    /**
     * 创建下拉选项框
     * @param jsonarr  数据源数组
     * @param element 新增元素位置
     * @constructor
     */
    ComboBox.prototype.CreateComboBox = function (jsonarr, element) {
        var self = this;//传递全局参数
        //#region 创建下拉框
        var _SelectClass = new Date().getTime();
        _SelectClass = 'S' + _SelectClass;
        var _Select = $('' +
            '<div class="selectinf ' + _SelectClass + '" data-open=0 data-enable=true>\n' +
            '        <div class="text-contents"></div>\n' +
            '        <div class="down-up down"></div>\n' +
            '</div>');
        element.append(_Select);
        var _ComboboxClass = new Date().getTime();
        _ComboboxClass = 'C' + _ComboboxClass;
        var _Combobox = $('' +
            '<div class="combobox ' + _ComboboxClass + '" data-minwidth="0">\n' +
            '    <div class="search">\n' +
            '        <input type="text" class="search-text" placeholder="输入关键词搜索"/>\n' +
            '        <span class="fa fa-search search-btn"></span>\n' +
            '    </div>\n' +
            '    <div class="main"><ul class="navMenu2Cbb"></ul></div>\n' +
            '</div>');
        $("body").append(_Combobox);
        self.returnclass(_SelectClass, _ComboboxClass);
        //#endregion

        //#region 输入框绑定焦点事件
        $("." + _ComboboxClass).delegate("input", "blur", function () {
            $(this).parent().css("border", "1px silver solid");
        });

        $("." + _ComboboxClass).delegate("input", "focus", function () {
            $(this).parent().css("border", "1px #0B7CED solid");
        });
        //#endregion

        //#region 输入框绑定键盘事件及文本改变事件
        $("." + _ComboboxClass).delegate("input", "input", function () {//文本输入框
            if ($(this).val() == "") {
                $('.' + _ComboboxClass + ' .main .navMenu2Cbb').find("li").css("display", "block");
            }
        });

        $("." + _ComboboxClass).delegate("span", "click", function () {//搜索按钮
            var e = jQuery.Event("keypress");//模拟一个键盘事件
            e.keyCode = 13;//keyCode=13是回车
            $("." + _ComboboxClass + " .search .search-text").trigger(e);
        });

        $("." + _ComboboxClass).delegate("input", "keypress", function (event) {
            if (event.keyCode == "13") {
                var txt = $(this).val().toLowerCase();
                if (txt == "") return;//为空不执行
                var arr = ProcessingArray(txt, CachArry);
                if (arr.length > 0) {
                    _ParentArray = [];
                    $(arr).each(function (index, item) {
                        var obj = {
                            ID: item.ID,
                            ParentId: item.ParentId,
                            Name: item.Name,
                            IsSelect: item.IsSelect,
                        }
                        _ParentArray.push(obj);
                        if (item.ParentId == "-1") {
                            return true;
                        }
                        SelectParentArray(CachArry, item.ParentId);
                    })
                }
                $('.' + _ComboboxClass + ' .main .navMenu2Cbb').find("li").css("display", "none");
                if (_ParentArray.length > 0) {
                    _ParentArray.map(function (item) {
                        $('.' + _ComboboxClass + ' .main .navMenu2Cbb .' + item.ID).css("display", "block");
                    })
                }
            }
        })
        //#endregion

        //#region 设置初始化位置、高度和宽度
        if (!self.needBorder) {
            var height = $('.' + _SelectClass).parent().height();
            $('.' + _SelectClass).css({
                'width': "100%",
                'height': height,
                'line-height': height + "px",
                'border': "none"
            });
        } else {
            var height = $('.' + _SelectClass).parent().height() - 2;
            $('.' + _SelectClass).css({
                'height': height,
                'line-height': height + "px",
            });
        }
        //#endregion

        //#region  点击正常显示框展开和收缩
        $('.' + _SelectClass).bind('click', function () {//绑定点击事件
            var IsEnable = $('.' + _SelectClass).data("enable");
            if (!IsEnable) return;
            var IsOpen = $('.' + _SelectClass).data("open");
            if (IsOpen == 0) {
                $(this).data("open", 1);
                $('.' + _SelectClass + ' .down-up').removeClass("down").addClass("up");
                var minwidth = $('.' + _ComboboxClass).data("minwidth");
                var parentWidth = $(this).width();
                var width = parentWidth >= minwidth ? parentWidth : minwidth;
                $('.' + _ComboboxClass).css({
                    'top': $(this).offset().top + $(this).height() + 2,
                    'left': $(this).offset().left - self.offsetLeftX,
                    "width": width,
                }).slideDown(300);
            } else {
                $(this).data("open", 0);
                $('.' + _SelectClass + ' .down-up').removeClass("up").addClass("down");
                $('.' + _ComboboxClass).slideUp(300);
            }
        });
        //#endregion

        //#region 判断点击事件是否指定区域
        $(document).click(function (e) {
            e = window.event || e; // 兼容IE7
            obj = $(e.srcElement || e.target);
            if (!($(obj).is("." + _SelectClass + ",." + _SelectClass + " *"))
                && !($(obj).is("." + _ComboboxClass + ",." + _ComboboxClass + " *"))
            ) {
                $('.' + _SelectClass).data("open", 0);
                $('.' + _SelectClass + ' .down-up').removeClass("up").addClass("down");
                $('.' + _ComboboxClass).slideUp(300);
            }
        });
        //#endregion

        //#region 窗体大小改变事件
        $(window).resize(function () {
            if ($('.' + _ComboboxClass).css("display") != "none") {
                var minwidth = $('.' + _ComboboxClass).data("minwidth");
                var parentWidth = $('.' + _SelectClass).width();
                var width = parentWidth >= minwidth ? parentWidth : minwidth;
                $('.' + _ComboboxClass).css({
                    "width": width,
                    'top': $('.' + _SelectClass).offset().top + $('.' + _SelectClass).height() + 2,
                    'left': $('.' + _SelectClass).offset().left - self.offsetLeftX,
                });
            }
        });
        //#endregion

        //下拉框信息创建
        $.DropDownBox(jsonarr, _ComboboxClass, _SelectClass, self.callback, element);

    }
    //#endregion

    //#region 创建子集节点
    /**
     * 创建子集节点
     * @param submenu  子集元素集合
     * @param lihtml li项
     * @param textwidth 计算宽度
     * @param array 缓存数组
     * @constructor
     */
    CreateSubMenu = function (submenu, lihtml, textwidth, array) {
        var subUl = $("<ul class='sub-menu' style='display: none'></ul>"),
            callee = arguments.callee,
            subLi;
        $(submenu).each(function (index, item) {
            var obj = {
                ID: item.ID,
                ParentId: item.ParentId,
                Name: item.Name,
                IsSelect: item.IsSelect,
            }
            array.push(obj);
            var _width = $.getTextWidth(item.Name);//获取文字的宽度
            textwidth = textwidth >= _width ? textwidth : _width;//判断宽度最大
            var url = item.url || "javascript:;";
            subLi = $("<li class='" + item.ID + "' data-id='" + item.ID + "' data-parentId='" + item.ParentId + "' data-select='" + item.IsSelect + "'><a href='" + url + "' target='_blank'><span class='title'>" + item.Name + "</span></a></li>");
            if (item.SubMenu && item.SubMenu.length > 0) {
                $(subLi).children('a').append("<span class='arrow'></span>");
                callee(item.SubMenu, subLi, textwidth, array);
            }
            $(subUl).append(subLi);
        });
        $(lihtml).append(subUl);
    }
    //#endregion

    //#region 查找含有指定字符的数组
    /**
     * 查找含有指定字符的数组
     * @param txt 搜索文本
     * @param oldarr 数据源
     * @return {Array} 返回含有指定字符的数组
     * @constructor
     */
    ProcessingArray = function (txt, oldarr) {
        var newarr = [];
        oldarr.map(function (item) {
            if (item.Name.toLowerCase().indexOf(txt) >= 0) {
                var obj = {
                    ID: item.ID,
                    ParentId: item.ParentId,
                    Name: item.Name,
                    IsSelect: item.IsSelect,
                }
                newarr.push(obj);
            }
        });
        return newarr;
    }
    //#endregion

    //#region 获取需要层级关系数组
    /**
     * 获取需要层级关系数组
     * @param oldarr 旧数据源
     * @param pid 父节点Id
     * @constructor
     */
    SelectParentArray = function (oldarr, pid) {
        $(oldarr).each(function (index, item) {
            if (item.ID == pid) {
                var IsContinue = true;
                if (_ParentArray.length > 0) {
                    $(_ParentArray).each(function (_index, _item) {
                        if (_item.ID == item.ID) {
                            IsContinue = false;
                            return false;//退出循环
                        }
                    })
                }
                if (!IsContinue) {
                    return true;//退出本次循环，继续执行下一个循环
                }
                var obj = {
                    ID: item.ID,
                    ParentId: item.ParentId,
                    Name: item.Name,
                    IsSelect: item.IsSelect,
                }
                _ParentArray.push(obj);
                SelectParentArray(oldarr, item.ParentId);
            }
        })
    }
    //#endregion

    /**
     * 获取具有树形结构的数据源
     * @param oldArr 旧数据
     * @param pid parentid
     * @return {Array} 具有树形结构数组
     */
    SelectTreeJson = function (oldArr, pid) {
        var newArr = [];
        oldArr.map(function (item) {
            if (item.ParentId == pid) {
                var obj = {
                    ID: item.ID,
                    ParentId: item.ParentId,
                    Name: item.Name,
                    IsSelect: item.IsSelect,
                }
                var SubMenu = SelectTreeJson(oldArr, item.ID); //递归操作
                if (SubMenu.length > 0) {
                    obj.SubMenu = SubMenu
                }
                newArr.push(obj)
            }
        })
        return newArr;
    },

        $.fn.extend({
            ComboBox: function (options) {
                return new ComboBox($(this), options)
            }
        });
    $.extend({
        /**
         * 下拉框信息创建
         * @param arr 信息集合数组
         * @param ddbclass 下拉框信息组集合class
         * @param cbbclass 下拉框class
         * @param callback 回调函数
         * @param element 父节点元素集 例如$(this)
         * @constructor
         */
        DropDownBox: function (arr, ddbclass, cbbclass, callback, element) {
            $("." + cbbclass + ' .text-contents').text("");
            $("." + cbbclass + ' .text-contents').data("Id", "");
            $("." + ddbclass + " .search .search-text").text("");
            var width = element.outerWidth() - 2;
            $('.' + ddbclass + ' .main .navMenu2Cbb').empty();
            if ($(arr).length == 0) {
                $('.' + ddbclass).css("width", width);
                return;
            }
            CachArry = [];
            //#region 创建ul-li        
            var textwidth = 0;//计算文字所需要占的宽度
            arr = SelectTreeJson(arr, "-1");
            $(arr).each(function (index, item) {
                var obj = {
                    ID: item.ID,
                    ParentId: item.ParentId,
                    Name: item.Name,
                    IsSelect: item.IsSelect,
                }
                CachArry.push(obj);
                var _width = $.getTextWidth(item.Name);//获取文字的宽度
                textwidth = textwidth >= _width ? textwidth : _width;//判断宽度最大
                var url = item.url || "javascript:;";
                var lihtml = $("<li class='" + item.ID + "' data-id='" + item.ID + "' data-parentId='" + item.ParentId + "' data-select='" + item.IsSelect + "'><a href='" + url + "' target='_blank' class='inactive'><span class='title'>" +
                    item.Name + "</span></a></li>");
                if (item.SubMenu && item.SubMenu.length > 0) {
                    $(lihtml).children('a').append("<span class='arrow'></span>");
                    CreateSubMenu(item.SubMenu, lihtml, textwidth, CachArry);
                }
                $('.' + ddbclass + ' .main .navMenu2Cbb').append(lihtml);
            });
            //#endregion
            //#region  ul-li点击事件绑定
            $('.' + ddbclass + ' .main .navMenu2Cbb').undelegate('li a', 'click');
            $('.' + ddbclass + ' .main .navMenu2Cbb').delegate('li a', 'click', function (e) {
                e.stopPropagation();//禁止向上冒泡
                e.preventDefault();//系统不执行跳转
                var isselect = $(this).parent().data("select");//获取数据项是否可以选择
                if (isselect != "False" || isselect != "false") {
                    if (!$(e.target).hasClass("arrow")) {
                        $("." + cbbclass + ' .text-contents').text($(this)[0].text);
                        $("." + cbbclass + ' .text-contents').data("Id", $(this).parent().data("id"));
                        callback($(this).parent().data("id"));
                        $('.' + cbbclass).data("open", 0);
                        $('.' + cbbclass + ' .down-up').removeClass("up").addClass("down");
                        $('.' + ddbclass).slideUp(300);
                    }
                }
                var parent = $(this).parent().parent(); //获取当前页签的父级的父级
                var labeul = $(this).parent("li").find(">ul");
                if ($(this).parent().hasClass('open') == false) {
                    //展开未展开
                    parent.find('ul').slideUp(300);
                    parent.find("li").removeClass("open");
                    parent.find('li a').removeClass("active").find(".arrow").removeClass("open")
                    $(this).parent("li").addClass("open").find(labeul).slideDown(300);
                    $(this).addClass("active").find(".arrow").addClass("open");
                } else {
                    $(this).parent("li").removeClass("open").find(labeul).slideUp(300);
                    if ($(this).parent().find("ul").length > 0) {
                        $(this).removeClass("active").find(".arrow").removeClass("open")
                    } else {
                        $(this).addClass("active")
                    }
                }
            });
            //#endregion
            //#region  设置宽度
            textwidth += 45;
            $('.' + ddbclass).css("width", textwidth >= width ? textwidth : width);
            $('.' + ddbclass).data("minwidth", textwidth);//记录容器的最低宽度
            //#endregion
        }
    })
})(jQuery);