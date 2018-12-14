/**
 * 以下的功能是创建分页按钮
 * author：Cezixn
 * datetime：2018-07-15 15:00 Create
 **/

(function ($) {

    /**
     * 初始化
     * @param element $(this)
     * @param options 传递参数
     */
    var Paging = function (element, options) {
        this.element = element;
        this.pageId = "page" + new Date().getTime();//给PageId加上时间戳，方便多次调用
        this.pageCount = options.pageCount || 1;//总页数,默认1
        this.pageSizeData = options.pageSizeData || [5, 10, 50, 100];//页大小,数组
        this.pageSize = options.pageSize || this.pageSizeData[0];//页面大小，默认10条/页
        this.align = options.align || "center";//位置[left,center,right]，默认center
        if (options.hasSize == undefined) {
            this.hasSize = true;//是否有页大小按钮，默认为true
        } else {
            this.hasSize = options.hasSize;
        }
        this.marginTop = options.marginTop || 0;//上方外边距,默认0
        //回调函数
        this.callBack = options.callBack || function () { };
        //返回PageId
        this.returnPageId = options.returnPageId || function () { };
        //创建按钮,样式
        this.CreatePageButton(this.pageId, this.pageCount, this.pageSize, this.pageSizeData, this.hasSize, this.marginTop, this.align, this.element);
    }

    /**
     * 创建按钮与样式
     * @param pageId 分页外层Id
     * @param pageCount 总页数
     * @param marginTop 上外边距
     * @param align 分页位置(left,center,right)
     * @param element 新增元素位置
     */
    Paging.prototype.CreatePageButton = function (pageId, pageCount, pageSize, pageSizeData, hasSize, marginTop, align, element) {
        var sizeStr = "";
        $.each(pageSizeData, function (index) {
            sizeStr += "<option value='" + pageSizeData[index] + "'>" + pageSizeData[index] + "</option>"
        });

        //分页按钮
        var str = "<div id='" + pageId + "'>" +
            "<ul class='pagination'>" +
            "<li><a>" +
            "显示 " +
            "<select id='pageSize'>" +
            sizeStr +
            "</select>" +
            " 条/页" +
            "</a></li>" +
            "<li id='firstPage'><a>" +
            "<span>首页</span>" +
            "</a></li>" +
            "<li id='Previous'><a>" +
            "<span>上一页</span>" +
            "</a></li>" +
            "<li id='Next'><a>" +
            "<span>下一页</span>" +
            "</a></li>" +
            "<li id='lastPage'><a>" +
            "<span>尾页</span>" +
            "</a></li>" +
            "<li><a>" +
            "当前第<span id='pageIndex'>1</span>页，" +
            "共<span id='pageCount'>" + pageCount + "</span>页"
        "</a></li>" +
        "</ul>" +
        "</div>";
        element.append(str);

        hasSize = hasSize ? "" : "#" + pageId + " li:first-child{display:none}";

        //按钮样式
        var render = "<style type=\"text/css\"> " +
            "#" + pageId + "{moz-user-select: -moz-none;-moz-user-select: none;-o-user-select: none;-khtml-user-select: none;-webkit-user-select: none;-ms-user-select: none;user-select: none;}" +
            "#" + pageId + "{width: 100%;text-align: " + align + ";margin:" + marginTop + "px 0px;}" +
            "#" + pageId + " ul{margin: 0;padding: 0;}" +
            "#" + pageId + " li{list-style: none;float: left;height: 25px;}" +
            "#" + pageId + " li.active a,li.active a:hover{background-color: transparent!important;border-color:transparent;border-left-color:#ddd;border-right-color:#ddd;color: #37A4FF;}" +
            "#" + pageId + " li.disabled a,li.disabled a:hover{color: #ddd;cursor: not-allowed;background-color: transparent!important;}" +
            "#" + pageId + " li a{box-sizing: content-box;display: block;cursor: default;height: 18px;line-height: 18px;font-size: 13px;padding: 3px 8px 3px 8px;color: #7C7C7C;text-decoration: none;background-color: transparent;border: 1px solid #ddd;margin-left: -1px;}" +
            "#" + pageId + " li#firstPage a,#" + pageId + " li#Previous a,#" + pageId + " li#Next a,#" + pageId + " li#lastPage a{font-size: 20px!important;padding: 0;line-height: 20px;height: 24px;padding: 0px 8px 0px 8px;}" +
            "#" + pageId + " li:first-child a{border-color:transparent;border-right-color:#ddd;color: #999999;}" +
            "#" + pageId + " li:last-child a{border-color:transparent;border-left-color:#ddd;color: #999999;}" +
            "#" + pageId + " li a:hover{background-color: #EEEEEE;}" +
            "#" + pageId + " li:first-child  a:hover{background-color: transparent;}" +
            "#" + pageId + " li:last-child  a:hover{background-color: transparent;}" +
            "#" + pageId + " select{background-color: transparent;}" +
            hasSize +
            "</style>";
        $("head").append(render);
        $("#" + pageId + " #pageSize").val(pageSize);

        this.returnPageId(pageId);
        //创建数字按钮
        $.newPaging(pageCount, pageId, this.callBack, true);

    };

    //实例化
    $.fn.extend({
        Paging: function (options) {
            return new Paging($(this), options);
        }
    });

    $.extend({
        /**
         * 分页数字按钮创建及绑定事件
         * @param pageCount 总页数
         * @param pageId 分页外层Id
         * @param callBack 回调函数
         * @param isInit 不用传递,判断是否初始化
         */
        newPaging: function (pageCount, pageId, callBack, isInit) {
            if (pageId == undefined) {
                callBack(alert("正确使用方法：$.newPaging('pageCount','pageId',function(index,size){...});"));
            }

            if (pageCount == "search") {
                pageCount = parseInt($("#" + pageId + " #pageCount").text(), 10);
                selectPage = -1;
                if (pageCount < 5) {
                    $("#" + pageId + " #numberA").first().trigger("click");//触发第一个按钮
                } else {
                    $("#" + pageId + " #numberA").parent().remove();; //清空分页按钮
                    for (var i = 1; i <= 5; i++) {
                        var str = "<li><a id='numberA' selectPage='" + i + "'>" + i + "</a></li>";
                        $("#" + pageId + " #Next").before(str);
                    }
                    $("#" + pageId + " #numberA").first().trigger("click");//触发第一个按钮
                }
                return false;
            } else {
                if (!isInit) {
                    if (pageCount == $("#" + pageId + " #pageCount").text()) return false;
                }

                pageCount == 0 ? (pageCount++) : pageCount;//为0时默认是一页
                selectPage = 1;

                //修改总页数
                $("#" + pageId + " #pageCount").text(parseInt(pageCount, 10));
            }

            //动态添加分页按钮
            $("#" + pageId + "  li#Next").removeClass("disabled");
            $("#" + pageId + "  li#lastPage").removeClass("disabled");
            if (pageCount <= 5) {
                $("#" + pageId + " #numberA").parent().remove(); //清空分页按钮
                for (var i = 1; i <= pageCount; i++) {
                    var str = "<li><a id='numberA' selectPage='" + i + "'>" + i + "</a></li>";
                    $("#" + pageId + " #Next").before(str);
                }
            } else {
                $("#" + pageId + " #numberA").parent().remove();; //清空分页按钮
                for (var i = 1; i <= 5; i++) {
                    var str = "<li><a id='numberA' selectPage='" + i + "'>" + i + "</a></li>";
                    $("#" + pageId + " #Next").before(str);
                }
            }

            //显示默认页（第一页）
            $("#" + pageId + " #numberA").eq(0).parent().attr("class", "active"); //第一页添加active
            $("#" + pageId + " li#firstPage").attr("class", "disabled"); //首页设为disabled
            $("#" + pageId + " li#Previous").attr("class", "disabled"); //上一页设为disabled
            if (pageCount == 1) { //如果总页数只有一页
                $("#" + pageId + "  li#Next").attr("class", "disabled"); //后一页设为disabled
                $("#" + pageId + "  li#lastPage").attr("class", "disabled"); //尾页设为disabled
            }

            $("#" + pageId).undelegate();//取消绑定事件
            //显示选择页的内容
            $("#" + pageId).delegate("#numberA", "click", function () {
                pageCount = parseInt($("#" + pageId + " #pageCount").text(), 10);
                if ($(this).attr("selectPage") == selectPage) {
                    return false;
                }
                $("#" + pageId + "  li").removeClass("active").removeClass("disabled"); //移除所有li的active、disabled
                selectPage = $(this).attr("selectPage"); //修改当前页

                //回调当前选择页及页大小
                callBack(selectPage, $("#" + pageId + " #pageSize").val());

                if (pageCount > 5) {
                    if (selectPage != 1 && selectPage != pageCount) {
                        var gap = $("#" + pageId + " #numberA").last().attr("selectPage") * 1 - $(this).attr("selectPage") * 1;
                        if (gap > 2) {
                            var who = $(this);
                            var index = 3;
                            for (var i = gap; i >= 3; i--) {
                                if ($("#" + pageId + " #numberA").first().attr("selectPage") != 1) {
                                    if (i == index) who = who.parent().prev().children();
                                    who.parent().before("<li><a id='numberA' selectPage='" + (who.attr("selectPage") * 1 - 1) + "'>" + (who.attr("selectPage") * 1 - 1) + "</a></li>");
                                    who = who.parent().prev().children();
                                    $("#" + pageId + " #numberA").last().parent().remove();
                                    index--;
                                }
                            }
                        }
                        if (gap < 2) {
                            var who = $(this);
                            var index = 1;
                            for (var i = gap; i <= 1; i++) {
                                if ($("#" + pageId + " #numberA").last().attr("selectPage") != pageCount) {
                                    if (i == index) who = who.parent().next().children();
                                    who.parent().after("<li><a id='numberA' selectPage='" + (who.attr("selectPage") * 1 + 1) + "'>" + (who.attr("selectPage") * 1 + 1) + "</a></li>");
                                    who = who.parent().next().children();
                                    $("#" + pageId + " #numberA").first().parent().remove();
                                    index++;
                                }
                            }
                        }
                    }
                }
                if (selectPage == "1") { //当当前页是第一页
                    $("#" + pageId + " li#firstPage").attr("class", "disabled"); //首页设为disabled
                    $("#" + pageId + " li#Previous").attr("class", "disabled"); //上一页设为disabled
                }
                if (selectPage == pageCount) { //当当前页是最后一页
                    $("#" + pageId + " li#lastPage").attr("class", "disabled"); //尾页设为disabled
                    $("#" + pageId + " li#Next").attr("class", "disabled"); //下一页设为disabled
                }
                $(this).parent().attr("class", "active"); //当前页按钮设置激活状态
            });

            //首页点击事件
            $("#" + pageId).delegate("#firstPage", "click", function () {
                pageCount = parseInt($("#" + pageId + " #pageCount").text(), 10);
                if (pageCount < 5) {
                    $("#" + pageId + " #numberA").first().trigger("click");//触发第一个按钮
                } else {
                    if (selectPage == 1) { //当当前页是第一页
                        return false;
                    } else {
                        $("#" + pageId + " #numberA").parent().remove();; //清空分页按钮
                        for (var i = 1; i <= 5; i++) {
                            var str = "<li><a id='numberA' selectPage='" + i + "'>" + i + "</a></li>";
                            $("#" + pageId + " #Next").before(str);
                        }
                        $("#" + pageId + " #numberA").first().trigger("click");//触发第一个按钮
                    }
                }
            });
            //尾页点击事件
            $("#" + pageId).delegate("#lastPage", "click", function () {
                pageCount = parseInt($("#" + pageId + " #pageCount").text(), 10);
                if (pageCount < 5) {
                    $("#" + pageId + " #numberA").last().trigger("click");//触发第一个按钮
                } else {
                    if (selectPage == pageCount) { //当当前页是最后一页
                        return false;
                    } else {
                        $("#" + pageId + " #numberA").parent().remove();; //清空分页按钮
                        for (var i = (pageCount - 4); i <= pageCount; i++) {
                            var str = "<li><a id='numberA' selectPage='" + i + "'>" + i + "</a></li>";
                            $("#" + pageId + " #Next").before(str);
                        }
                        $("#" + pageId + " #numberA").last().trigger("click");//触发最后一个按钮
                    }
                }
            });
            //前一页点击事件
            $("#" + pageId).delegate("#Previous", "click", function () {
                if (selectPage == 1) { //当当前页是第一页
                    return false;
                } else {
                    $("#" + pageId + " #numberA[selectPage=" + (selectPage * 1 - 1) + "]").trigger("click");//触发当前页的前一个按钮
                }
            });
            //后一页点击事件
            $("#" + pageId).delegate("#Next", "click", function () {
                pageCount = $("#" + pageId + " #pageCount").text();
                if (selectPage == pageCount) {//当当前页是最后一页
                    return false;
                } else {
                    $("#" + pageId + " #numberA[selectPage=" + (selectPage * 1 + 1) + "]").trigger("click");//触发当前页的后一个按钮
                }
            });

            //显示页数改变
            $("#" + pageId).delegate("#pageSize", "change", function () {
                callBack(1, $("#" + pageId + " #pageSize").val());
            });
        }
    });
})(jQuery);