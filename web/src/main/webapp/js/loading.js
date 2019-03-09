//创建加载动画 ydlemon Add 2018-07-05 14:04

function Loading(options) {
	this.config = {
        loadingtype: 0,//创建加载样式 0默认数据加载
    };
    this.init(options);
}
// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
// 例子：
// (new Date()).format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
// (new Date()).format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
Date.prototype.format = function(fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}
Loading.prototype = {
    constructor: Loading,
    /**
     * 初始化
     * @param options 参数集
     */
    init: function (options) {
        this.config = $.extend(this.config, options || {});
        var self = this,
        _config = self.config;
        // 渲染html结构
        self.renderHtml();
    },
    /**
     * html元素渲染
     */
    renderHtml: function () {
        //#region 创建样式
        var style = $("<style type=\"text/css\"> " +
            "#loading {" +
            "       display: none;" +
            "       position: absolute;" +
            "       width: 234px;" +
            "       height: 64px;" +
            "       left: 50%;" +
            "       top: 50%;" +
            "       z-index: 9999;" +
            "       border-radius: 4px;" +
            "       transform: translate(-50%, -50%);" +
            "       background-color: #FFF;" +
            "       border: 1px solid #ecedf2;" +
            // "       -moz-box-shadow: 2px 2px 5px #ecedf2;" +
            // "       -webkit-box-shadow: 2px 2px 5px #ecedf2;" +
            // "       box-shadow: 2px 2px 5px #ecedf2;" +
            "}" +
            "#loading-center {" +
            "       float: left;" +
            "       width: 30px;" +
            "       height: 30px;" +
            "       margin-top: 17px;" +
            "       margin-left: 30px;" +
            "       position: relative;" +
            "}" +
            "#loading-text {" +
            "       float: left;" +
            "       width: auto;" +
            "       height: 40px;" +
            "       line-height: 40px;" +
            "       margin-top: 12px;" +
            "       margin-left: 20px;" +
            "       font-size: 14px;" +
            "       color: #5d6c81;" +
            "}" +
            "#loading-center-absolute {" +
            "       position: absolute;" +
            "       left: 50%;" +
            "       top: 50%;" +
            "       height: 30px;" +
            "       width: 30px;" +
            "       margin-top: -15px;" +
            "       margin-left: -15px;" +
            "       -webkit-animation: loading-center-absolute 2s infinite;" +
            "       animation: loading-center-absolute 2s infinite;" +
            "}" +
            ".object {" +
            "       width: 8px;" +
            "       height: 8px;" +
            "       background-color: #4695E9;" +
            "       float: left;" +
            "       -moz-border-radius: 50% 50% 50% 50%;" +
            "       -webkit-border-radius: 50% 50% 50% 50%;" +
            "       border-radius: 50% 50% 50% 50%;" +
            "       margin-right: 14px;" +
            "       margin-bottom: 14px;" +
            "}" +
            ".object:nth-child(2n+0) {" +
            "       margin-right: 0px;" +
            "}" +
            "#object_one {" +
            "       -webkit-animation: object_one 2s infinite;" +
            "       animation: object_one 2s infinite;" +
            "}" +
            "#object_two {" +
            "       -webkit-animation: object_two 2s infinite;" +
            "       animation: object_two 2s infinite;" +
            "}" +
            "#object_three {" +
            "       -webkit-animation: object_three 2s infinite;" +
            "       animation: object_three 2s infinite;" +
            "}" +
            "#object_four {" +
            "       -webkit-animation: object_four 2s infinite;" +
            "       animation: object_four 2s infinite;" +
            "}" +
            "@-webkit-keyframes loading-center-absolute {" +
            "100% {" +
            "       -ms-transform: rotate(360deg);" +
            "       -webkit-transform: rotate(360deg);" +
            "       transform: rotate(360deg);" +
            "}" +
            "}" +
            "@keyframes loading-center-absolute {" +
            "100% {" +
            "       -ms-transform: rotate(360deg);" +
            "       -webkit-transform: rotate(360deg);" +
            "       transform: rotate(360deg);" +
            "}" +
            "}" +
            "@-webkit-keyframes object_one {" +
            "50% {" +
            "       -ms-transform: translate(20px, 20px);" +
            "       -webkit-transform: translate(20px, 20px);" +
            "       transform: translate(20px, 20px);" +
            "}" +
            "}" +
            "@keyframes object_one {" +
            "50% {" +
            "       -ms-transform: translate(20px, 20px);" +
            "       -webkit-transform: translate(20px, 20px);" +
            "       transform: translate(20px, 20px);" +
            "}" +
            "}" +
            "@-webkit-keyframes object_two {" +
            "50% {" +
            "       -ms-transform: translate(-20px, 20px);" +
            "       -webkit-transform: translate(-20px, 20px);" +
            "       transform: translate(-20px, 20px);" +
            "}" +
            "}" +
            "@keyframes object_two {" +
            "50% {" +
            "       -ms-transform: translate(-20px, 20px);" +
            "       -webkit-transform: translate(-20px, 20px);" +
            "       transform: translate(-20px, 20px);" +
            "}" +
            "}" +
            "@-webkit-keyframes object_three {" +
            "50% {" +
            "       -ms-transform: translate(20px, -20px);" +
            "       -webkit-transform: translate(20px, -20px);" +
            "       transform: translate(20px, -20px);" +
            "}" +
            "}" +
            "@keyframes object_three {" +
            "50% {" +
            "       -ms-transform: translate(20px, -20px);" +
            "       -webkit-transform: translate(20px, -20px);" +
            "       transform: translate(20px, -20px);" +
            "}" +
            "}" +
            "@-webkit-keyframes object_four {" +
            "50% {" +
            "       -ms-transform: translate(-20px, -20px);" +
            "       -webkit-transform: translate(-20px, -20px);" +
            "       transform: translate(-20px, -20px);" +
            "}" +
            "}" +
            "@keyframes object_four {" +
            "50% {" +
            "       -ms-transform: translate(-20px, -20px);" +
            "       -webkit-transform: translate(-20px, -20px);" +
            "       transform: translate(-20px, -20px);" +
            "}" +
            "}" +
            "#loadingMaskLayer {" +
            "    display: none;" +
            "    position: absolute;" +
            "    width: 100%;" +
            "    height: 100%;" +
            "    left: 0;" +
            "    top: 0;" +
            "    z-index: 999;" +
            "    background-color: #3C3C3C;" +
            "    filter: alpha(Opacity=30);" +
            "    -moz-opacity: 0.3;" +
            "    opacity: 0.3;" +
            "}" +
            "</style>");
        $("head").append(style);
        //#endregion
        //#region 创建html代码
        $('#loading').remove();
        console.log(this.config)
        var loading = $('<!--加载中动画-->' +
            '<div id="loading">' +
            '    <div id="loading-center">' +
            '        <div id="loading-center-absolute">' +
            '            <div class="object" id="object_one"></div>' +
            '            <div class="object" id="object_two"></div>' +
            '            <div class="object" id="object_three"></div>' +
            '            <div class="object" id="object_four"></div>' +
            '        </div>' +
            '    </div>' +
            '    <div id="loading-text">'+(this.config.text || "正在努力加载中......")+'</div>' +
            '</div>');
        $("body").append(loading);
        var maskLayer=$('<!--遮罩层-->' +
            '<div id="loadingMaskLayer"></div>');
        $("body").append(maskLayer);
        //#endregion
    },
    /**
     * 显示弹窗
     */
    show:function () {
        $("#loadingMaskLayer").fadeIn();
        $("#loading").fadeIn();
    },
    /**
     * 隐藏弹窗
     */
    hide:function () {
        $("#loadingMaskLayer").hide();
        $("#loading").hide();
    }
}
