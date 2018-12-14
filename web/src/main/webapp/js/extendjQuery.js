//jquery扩展方法全部写在这边
$.extend({
    getUrlVars: function () {
        var vars = [], hash;
        var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
        for (var i = 0; i < hashes.length; i++) {
            hash = hashes[i].split('=');
            vars.push(hash[0]);
            vars[hash[0]] = hash[1];
        }
        return vars;
    },
    getUrlVar: function (name) {
        return $.getUrlVars()[name];
    },
    /**
     * 获取16位的GUID
     * @return {string} 返回16位GUID
     */
    getGuid16: function () {
        var s = [];
        var hexDigits = "0123456789abcdef";
        for (var i = 0; i < 15; i++) {
            s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
        }
        var uuid = s.join("");
        return uuid;
    },
    /**
     * js获取文本显示宽度
     * @param str: 文本
     * @return 文本显示宽度
     */
    getTextWidth: function (str) {
        var w = $('body').append($('<span stlye="display:none;" id="textWidth"/>')).find('#textWidth').html(str).width();
        $('#textWidth').remove();
        return w;
    },
    /**
     * 时间格式化处理
     * @param now 时间
     * @param mask 格式 例如"yyyy-MM-dd HH:mm"
     */
    getFormatDate: function (now, mask) {
        var d = now;
        var zeroize = function (value, length) {
            if (!length) length = 2;
            value = String(value);
            for (var i = 0, zeros = ''; i < (length - value.length); i++) {
                zeros += '0';
            }
            return zeros + value;
        };
        return mask.replace(/"[^"]*"|'[^']*'|\b(?:d{1,4}|m{1,4}|yy(?:yy)?|([hHMstT])\1?|[lLZ])\b/g, function ($0) {
            switch ($0) {
                case 'd':
                    return d.getDate();
                case 'dd':
                    return zeroize(d.getDate());
                case 'ddd':
                    return ['Sun', 'Mon', 'Tue', 'Wed', 'Thr', 'Fri', 'Sat'][d.getDay()];
                case 'dddd':
                    return ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'][d.getDay()];
                case 'M':
                    return d.getMonth() + 1;
                case 'MM':
                    return zeroize(d.getMonth() + 1);
                case 'MMM':
                    return ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'][d.getMonth()];
                case 'MMMM':
                    return ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'][d.getMonth()];
                case 'yy':
                    return String(d.getFullYear()).substr(2);
                case 'yyyy':
                    return d.getFullYear();
                case 'h':
                    return d.getHours() % 12 || 12;
                case 'hh':
                    return zeroize(d.getHours() % 12 || 12);
                case 'H':
                    return d.getHours();
                case 'HH':
                    return zeroize(d.getHours());
                case 'm':
                    return d.getMinutes();
                case 'mm':
                    return zeroize(d.getMinutes());
                case 's':
                    return d.getSeconds();
                case 'ss':
                    return zeroize(d.getSeconds());
                case 'l':
                    return zeroize(d.getMilliseconds(), 3);
                case 'L':
                    var m = d.getMilliseconds();
                    if (m > 99) m = Math.round(m / 10);
                    return zeroize(m);
                case 'tt':
                    return d.getHours() < 12 ? 'am' : 'pm';
                case 'TT':
                    return d.getHours() < 12 ? 'AM' : 'PM';
                case 'Z':
                    return d.toUTCString().match(/[A-Z]+$/);
                // Return quoted strings with the surrounding quotes removed
                default:
                    return $0.substr(1, $0.length - 2);
            }
        });
    },
    /**
     * 字符格式化处理 处理的格式为=> stringFormat("{0}"你好,"中国")
     * @return {*} 返回正常格式字符
     */
    stringFormat: function () {
        if (arguments.length == 0)
            return null;
        var str = arguments[0];
        for (var i = 1; i < arguments.length; i++) {
            var re = new RegExp('\\{' + (i - 1) + '\\}', 'gm');
            str = str.replace(re, arguments[i]);
        }
        return str;
    },

    /*
     *   功能:实现VBScript的DateAdd功能.
     *   参数:interval,字符串表达式，表示要添加的时间间隔.
     *   参数:number,数值表达式，表示要添加的时间间隔的个数.
     *   参数:date,时间对象.
     *   返回:新的时间对象.
     *   var now = new Date();
     *   var newDate = DateAdd( "d", 5, now);
     *---------------   DateAdd(interval,number,date)   -----------------
     */
    dateAdd: function (interval, number, date) {
        switch (interval) {
            case "y": {
                date.setFullYear(date.getFullYear() + number);
                return date;
                break;
            }
            case "q": {
                date.setMonth(date.getMonth() + number * 3);
                return date;
                break;
            }
            case "m": {
                date.setMonth(date.getMonth() + number);
                return date;
                break;
            }
            case "w": {
                date.setDate(date.getDate() + number * 7);
                return date;
                break;
            }
            case "d": {
                date.setDate(date.getDate() + number);
                return date;
                break;
            }
            case "h": {
                date.setHours(date.getHours() + number);
                return date;
                break;
            }
            case "m": {
                date.setMinutes(date.getMinutes() + number);
                return date;
                break;
            }
            case "s": {
                date.setSeconds(date.getSeconds() + number);
                return date;
                break;
            }
            default: {
                date.setDate(date.getDate() + number);
                return date;
                break;
            }
        }

    }

});

/**
 * ydlemon 2018-08-13 改变元素大小
 * @param options 传递参数
 * @constructor
 */
$.fn.Resizable = function (options) {
    var parentNode = $(options.containerObj);//获取需要改变大小的容器对象 传递的格式为:".class"或"#id"
    var defaults = {   //默认参数
        minW: 150,
        minH: 150,
        maxW: 500,
        maxH: 500,
    }
    var opts = this.extend(defaults, options);
    var obj = $(this);
    obj.mousedown(function (e) {
        var e = e || event;    //区分IE和其他浏览器事件对象
        var x = e.pageX - obj.position().left;	//获取鼠标距离匹配元素的父元素左侧的距离
        var y = e.pageY - obj.position().top;//获取鼠标距离匹配元素的父元素顶端的距离

        $(document).mousemove(function (e) {
            var e = e || event;
            var _x = e.pageX - x;  //动态获取匹配元素距离其父元素左侧的宽度
            var _y = e.pageY - y;
            _x = _x < opts.minW ? opts.minW : _x;      //保证匹配元素的最小宽度为150px
            _x = _x > opts.maxW ? opts.maxW : _x;    //保证匹配元素的最大宽度为500px
            _y = _y < opts.minH ? opts.minH : _y;
            _y = _y > opts.maxH ? opts.maxH : _y;
            parentNode.css({width: _x, height: _y});
        }).mouseup(function () {
            $(this).unbind("mousemove");  //当鼠标抬起  删除移动事件   匹配元素宽高变化停止
        });
    });
}


