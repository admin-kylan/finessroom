//该功能是 时间选择器 功能函数插件 该插件需要引用
// jedate.js、jquery-confirm.js、extendjQuery.js、jquery-3.3.1.min.js
//bootstrap.min.css、font-awesome.min.css、jquery-confirm.css、skin/jedate.css
//Author：ydlemon
//Time:2018-08-23 09:12
//Time:2018-08-24 17:05  ydlemon Modify 新增参数 isLimitTime=>是否需要限制搜索的时间段不能超过一年

function FileTime(options) {
    this.config = {
        clickObj: "", //绑定点击对象  传递的格式为：#id或.class
        isLimitTime:true,//是否需要限制搜索的时间段不能超过一年
        callback: options.callback || function () {
        }
    };
    this.init(options);
}

FileTime.prototype = {
    constructor: FileTime,
    /**
     * 初始化设置
     */
    init: function (options) {
        this.config = $.extend(this.config, options || {});
        var self = this,
            _config = self.config;
        self.renderHtml();
    },
    /**
     * 渲染html
     */
    renderHtml: function () {
        var self = this;
        var fileTimeClass = 'F' + new Date().getTime(); //创建class
        //#region 创建样式
        var style = $('<style type="text/css"> ' +
            '/*预约时间段选择*/' +
            '.' + fileTimeClass + ' {' +
            '    display: none;' +
            '    position: absolute;' +
            '    width: 440px;' +
            '    height: auto;' +
            '    background-color: #FFF;' +
            '    border-radius: 4px;' +
            '    border: 1px solid #99b2ca;' +
            '    -moz-box-shadow: 2px 2px 5px #99b2ca;' +
            '    -webkit-box-shadow: 2px 2px 5px #99b2ca;' +
            '    box-shadow: 2px 2px 5px #99b2ca;' +
            '    left: 120px;' +
            '    top: 120px;' +
            '    z-index: 999;' +
            '}' +
            '/*内容显示区域*/' +
            '.' + fileTimeClass + ' .timeContents {' +
            '    position: relative;' +
            '    float: left;' +
            '    margin: 20px;' +
            '    width: calc(100% - 40px);' +
            '}' +
            '.' + fileTimeClass + ' .right:before, /*显示位置 靠右*/' +
            '.' + fileTimeClass + ' .right:after, /*显示位置 靠右*/' +
            '.' + fileTimeClass + ' .timeContents:before, /*正常显示位置 靠左*/' +
            '.' + fileTimeClass + ' .timeContents:after { /*正常显示位置 靠左*/' +
            '    border: solid transparent;' +
            '    content: \' \';' +
            '    height: 0;' +
            '    width: 0;' +
            '    position: absolute;' +
            '}' +
            '.' + fileTimeClass + ' .timeContents:before { /*正常显示位置 靠左*/' +
            '    border-width: 8px;' +
            '    border-bottom-color: #99b2ca;' +
            '    top: -36px;' +
            '    left: -10px;' +
            '}' +
            '.' + fileTimeClass + ' .timeContents:after { /*正常显示位置 靠左*/' +
            '    top: -33px;' +
            '    left: -9px;' +
            '    border-width: 7px;' +
            '    border-bottom-color: #fff;' +
            '}' +
            '.' + fileTimeClass + ' .right:before { /*显示位置 靠右*/' +
            '    border-width: 8px;' +
            '    border-bottom-color: #99b2ca;' +
            '    top: -36px;' +
            '    left: calc(100% - 10px);' +
            '}' +
            '.' + fileTimeClass + ' .right:after { /*显示位置 靠右*/' +
            '    top: -33px;' +
            '    left: calc(100% - 9px);' +
            '    border-width: 7px;' +
            '    border-bottom-color: #fff;' +
            '}' +
            '/*按钮*/' +
            '.' + fileTimeClass + ' .timeContents > div.first {' +
            '    margin-bottom: 20px;' +
            '}' +
            '.' + fileTimeClass + ' .timeContents > div.first span {' +
            '    padding: 6px 4px;' +
            '    margin-right: 6px;' +
            '    background-color: #e4e4e4;' +
            '    border-radius: 4px;' +
            '    cursor: pointer;' +
            '}' +
            '.' + fileTimeClass + ' .timeContents > div.first span.actived {' +
            '    background-color: #0088CC;' +
            '    color: #ffffff;' +
            '}' +
            '.' + fileTimeClass + ' .timeContents > div.first span:nth-last-child(1) {' +
            '    margin-right: 0;' +
            '}' +
            '.' + fileTimeClass + ' .timeContents .second > div {' +
            '    float: left;' +
            '    width: auto;' +
            '}' +
            '.' + fileTimeClass + ' .timeContents .second > div input {' +
            '    border: 1px solid rgb(0, 184, 125);' +
            '    border-radius: 2px;' +
            '    height: 23px;' +
            '    width: 100px;' +
            '    text-indent: 6px;' +
            '    background: url("img/down.png") center right no-repeat;' +
            '    background-color: #ffffff;' +
            '    background-position-x: calc(100% - 5px);' +
            '    cursor: default;' +
            '}' +
            '/*按钮组*/' +
            '.' + fileTimeClass + ' .timeContents .second > div:nth-last-child(1) {' +
            '    float: right;' +
            '    margin-top: 5px;' +
            '}' +
            '.' + fileTimeClass + ' .timeContents .second > div:nth-last-child(1) span {' +
            '    padding: 6px 8px;' +
            '    margin-left: 6px;' +
            '    border-radius: 4px;' +
            '    cursor: pointer;' +
            '    color: #ffffff;' +
            '}</style>')
        $("head").append(style);
        //#endregion
        //#region 创建HTML代码
        var timeHtml = $("<!--预约时间段选择-->" +
            "<div class=\"" + fileTimeClass + "\">" +
            "    <!--显示内容-->" +
            "    <div class=\"timeContents\">" +
            "        <div class=\"first\">" +
            "            <span data-index=\"0\" class=\"actived\">今天</span>" +
            "            <span data-index=\"1\">昨天</span>" +
            "            <span data-index=\"2\">最近7天</span>" +
            "            <span data-index=\"3\">最近30天</span>" +
            "            <span data-index=\"4\">这个月</span>" +
            "            <span data-index=\"5\">上个月</span>" +
            "            <span data-index=\"6\">自由选择时间</span>" +
            "        </div>" +
            "        <div class=\"second\">" +
            "            <div class=\"\">" +
            "                <span>从</span>" +
            "                <input class=\"startTime\" type=\"text\" readonly=\"readonly\"/>" +
            "                <span>到</span>" +
            "                <input class=\"endTime\" type=\"text\" readonly=\"readonly\"/>" +
            "            </div>" +
            "            <div>" +
            "                <span data-index=\"0\" style=\"background-color: rgb(73, 161, 2455);\" class=\"cancel\">取消</span>" +
            "                <span data-index=\"1\" style=\"background-color: rgb(250, 121, 92);\" class=\"ok\">确定</span>" +
            "            </div>" +
            "        </div>" +
            "    </div>" +
            "</div>");
        if ($("#app").length > 0)
            $("#app").append(timeHtml);
        else
            $("body").append(timeHtml);
        //#endregion
        self.config.callback(fileTimeClass);//回调创建对象的class名称，方便下次操作
        self.bindEvent(fileTimeClass);//时间绑定
    },
    /**
     * 时间绑定
     * @param fileTimeClass 元素class名称
     */
    bindEvent: function (fileTimeClass) {
        var self = this,
            _config = self.config;
        //#region 事件按钮选择
        $("." + fileTimeClass + " .timeContents .first span").bind("click", function () {
            $("." + fileTimeClass + " .timeContents .first span.actived").removeClass("actived");
            $(this).addClass("actived");
            var index = $(this).data("index");
            var day1 = new Date();
            var day2 = new Date();
            switch (index) {
                case 0://今天
                    day1 = new Date();
                    day2 = new Date();
                    break;
                case 1://昨天
                    day1.setDate(day1.getDate() - 1);
                    day2 = new Date();
                    break;
                case 2://最近7天
                    day1.setDate(day1.getDate() - 7);
                    day2 = new Date();
                    break;
                case 3://最近30天
                    day1.setDate(day1.getDate() - 30);
                    day2 = new Date();
                    break;
                case 4://这个月
                    day1.setDate(1);
                    day2 = new Date(day2.getFullYear(), day2.getMonth() + 1, 0);
                    break;
                case 5://上个月
                    day1 = new Date(day1.getFullYear(), day1.getMonth() - 1, 1);
                    day2 = new Date(day2.getFullYear(), day2.getMonth(), 0);
                    break;
                case 6://自由选择时间
                    break;
            }
            $("." + fileTimeClass + " .timeContents .second .startTime").val($.getFormatDate(day1, "yyyy-MM-dd"));
            $("." + fileTimeClass + " .timeContents .second .endTime").val($.getFormatDate(day2, "yyyy-MM-dd"));
        })
        //#endregion

        //#region  取消
        $("." + fileTimeClass + " .cancel").bind("click", function () {
            $("." + fileTimeClass).slideUp();
        })
        //#endregion

        //#region 确定
        $("." + fileTimeClass + " .ok").bind("click", function () {
            var temp = $("." + fileTimeClass + " .timeContents .first span.actived");
            if(_config.isLimitTime) {
                if (temp.data("index") == 6) {//判断查询的间隔不能超过一年
                    var firstDate = new Date($("." + fileTimeClass + " .timeContents .second .startTime").val());
                    var secondDate = new Date($("." + fileTimeClass + " .timeContents .second .endTime").val());
                    firstDate = new Date(firstDate.getFullYear() + 1, firstDate.getMonth() + 1, -1);
                    if (secondDate > firstDate) {
                        $.alert("起始日期与结束日期间隔不超过一年!!!");
                        $("." + fileTimeClass + " .timeContents .second .endTime").val($.getFormatDate(firstDate, "yyyy-MM-dd"));
                        return;
                    }
                }
            }
            var startTime = $("." + fileTimeClass + " .timeContents .second .startTime").val();//开始时间 
            var endTime = $("." + fileTimeClass + " .timeContents .second .endTime").val();//结束时间
            var tempValue = $.stringFormat("{0} - {1}", startTime, endTime);
            $(_config.clickObj).val(tempValue);
            $(_config.clickObj).data("starttime", startTime);
            $(_config.clickObj).data("endtime", endTime);
            $("." + fileTimeClass).slideUp();
        })
        //#endregion

        //#region 创建时间选择器 for 预约记录查询->时间段选择开始时间和结束时间
        $("." + fileTimeClass + " .timeContents .second .startTime").val($.getFormatDate(new Date(), "yyyy-MM-dd"));
        $("." + fileTimeClass + " .timeContents .second .endTime").val($.getFormatDate(new Date(), "yyyy-MM-dd"));
        jeDate("." + fileTimeClass + " .timeContents .second .startTime", {
            format: "YYYY-MM-DD",
            isTime: false,
            isClear: false,
            minDate: "1970-01-01 00:00:00",
            onClose: false, //是否为选中日期后关闭弹层，为false时选中日期后关闭弹层
        })
        jeDate("." + fileTimeClass + " .timeContents .second .endTime", {
            format: "YYYY-MM-DD",
            isTime: false,
            isClear: false,
            minDate: "1970-01-01 00:00:00",
            onClose: false, //是否为选中日期后关闭弹层，为false时选中日期后关闭弹层
        })
        $("." + fileTimeClass + " .timeContents .second .startTime").bind("click", function () {
            $("." + fileTimeClass + " .timeContents .first span.actived").removeClass("actived");
            $("." + fileTimeClass + " .timeContents .first span:eq(6)").addClass("actived");
        })
        $("." + fileTimeClass + " .timeContents .second .endTime").bind("click", function () {
            $("." + fileTimeClass + " .timeContents .first span.actived").removeClass("actived");
            $("." + fileTimeClass + " .timeContents .first span:eq(6)").addClass("actived");
        })
        //#endregion
    },
    /**
     * 弹窗显示
     * @param clickObj 点击对象元素点击
     * @param fileTimeClass 创建时间选择器对象class名称
     * @param container 当前点击对象最外层对象 格式为 #id或.class
     */
    show: function (clickObj, fileTimeClass, container) {
        var self = this;
        self.config.clickObj = clickObj;//重置对象
        var left = $(clickObj).offset().left;
        var top = $(clickObj).offset().top + $(clickObj).height() + 10;
        $("." + fileTimeClass + " .timeContents .first span:eq(0)").click();//重置初始化
        var tempWidth = $(container).width();//获取当前点击对象最外层宽度
        var width = $("." + fileTimeClass).width();
        if (width + left > tempWidth) {
            $("." + fileTimeClass + " .timeContents").addClass("right");
            left = left - width + $(clickObj).width();
        } else
            $("." + fileTimeClass + " .timeContents").removeClass("right");
        $("." + fileTimeClass).css({
            "left": left,
            "top": top
        }).slideDown();
    },
    /**
     * 隐藏显示
     * @param fileTimeClass 创建时间选择器对象class名称
     */
    hide: function (fileTimeClass) {
        $("." + fileTimeClass).slideUp();
    }

}

