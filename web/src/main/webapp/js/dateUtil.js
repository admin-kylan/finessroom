/**
 * 获取本周、本季度、本月、上月的开始日期、结束日期
 */
var now = new Date(); //当前日期
var nowDayOfWeek = now.getDay(); //今天本周的第几天
var nowDay = now.getDate(); //当前日
var nowMonth = now.getMonth(); //当前月
var nowYear = now.getYear(); //当前年
nowYear += (nowYear < 2000) ? 1900 : 0; //
var lastMonthDate = new Date(); //上月日期
lastMonthDate.setDate(1);
lastMonthDate.setMonth(lastMonthDate.getMonth() - 1);
var lastYear = lastMonthDate.getYear();
var lastMonth = lastMonthDate.getMonth();
//格式化日期：yyyy-MM-dd
function formatDate(date) {
    var myyear = date.getFullYear();
    var mymonth = date.getMonth() + 1;
    var myweekday = date.getDate();
    if (mymonth < 10) {
        mymonth = "0" + mymonth;
    }
    if (myweekday < 10) {
        myweekday = "0" + myweekday;
    }
    return (myyear + "-" + mymonth + "-" + myweekday);
}
//获得某月的天数
function getMonthDays(myMonth) {
    var monthStartDate = new Date(nowYear, myMonth, 1);
    var monthEndDate = new Date(nowYear, myMonth + 1, 1);
    var days = (monthEndDate - monthStartDate) / (1000 * 60 * 60 * 24);
    return days;
}
//获得本季度的开始月份
function getQuarterStartMonth() {
    var quarterStartMonth = 0;
    if (nowMonth < 3) {
        quarterStartMonth = 0;
    }
    if (2 < nowMonth && nowMonth < 6) {
        quarterStartMonth = 3;
    }
    if (5 < nowMonth && nowMonth < 9) {
        quarterStartMonth = 6;
    }
    if (nowMonth > 8) {
        quarterStartMonth = 9;
    }
    return quarterStartMonth;
}
//获得本周的开始日期
function getWeekStartDate() {
    var weekStartDate = new Date(nowYear, nowMonth, nowDay - nowDayOfWeek);
    return formatDate(weekStartDate);
}
//获得本周的结束日期
function getWeekEndDate() {
    var weekEndDate = new Date(nowYear, nowMonth, nowDay + (6 - nowDayOfWeek));
    return formatDate(weekEndDate);
}
//获得上周的开始日期
function getLastWeekStartDate() {
    var weekStartDate = new Date(nowYear, nowMonth, nowDay - nowDayOfWeek - 7);
    return formatDate(weekStartDate);
}
//获得上周的结束日期
function getLastWeekEndDate() {
    var weekEndDate = new Date(nowYear, nowMonth, nowDay - nowDayOfWeek - 1);
    return formatDate(weekEndDate);
}
//获得本月的开始日期
function getMonthStartDate() {
    var monthStartDate = new Date(nowYear, nowMonth, 1);
    return formatDate(monthStartDate);
}
//获得本月的结束日期
function getMonthEndDate() {
    var monthEndDate = new Date(nowYear, nowMonth, getMonthDays(nowMonth));
    return formatDate(monthEndDate);
}
//获得上月开始时间
function getLastMonthStartDate() {
    var lastMonthStartDate = new Date(nowYear, lastMonth, 1);
    return formatDate(lastMonthStartDate);
}
//获得上月结束时间
function getLastMonthEndDate() {
    var lastMonthEndDate = new Date(nowYear, lastMonth, getMonthDays(lastMonth));
    return formatDate(lastMonthEndDate);
}
//获得本季度的开始日期
function getQuarterStartDate() {
    var quarterStartDate = new Date(nowYear, getQuarterStartMonth(), 1);
    return formatDate(quarterStartDate);
}
//获得本季度的结束日期
function getQuarterEndDate() {
    var quarterEndMonth = getQuarterStartMonth() + 2;
    var quarterStartDate = new Date(nowYear, quarterEndMonth,
        getMonthDays(quarterEndMonth));
    return formatDate(quarterStartDate);
}
//获得今年的开始日期
function getYearStartDate() {
    var yearStartDate = new Date(nowYear,0,1);
    return formatDate(yearStartDate);
}
//获得今年的结束日期
function getYearEndDate() {
    var yearEndDate = new Date(nowYear,11,getMonthDays(11));
    return formatDate(yearEndDate);
}
//日期格式转换  1538305627000 ===>转换 2018-09-30 19:07
function timeFormatDate(time,type,typeT) {
    var date = new Date(time);
    var y = date.getFullYear();
    var MM = date.getMonth() + 1;
    MM = MM < 10 ? ('0' + MM) : MM;
    var d = date.getDate();
    d = d < 10 ? ('0' + d) : d;
    var _time = y + '-' + MM + '-' + d;
    if(type){
        var h = date.getHours();
        h = h < 10 ? ('0' + h) : h;
        var m = date.getMinutes();
        m = m < 10 ? ('0' + m) : m;
        _time += ' '+h + ':' + m;
        if(typeT){
            var s = date.getSeconds();
            s =  s < 10 ? ("0" + s) : s;
            _time += ':'+s;
        }
    }
    return _time;
}
//获取当前时间，
// 默认：YYYY-MM-DD
// type : true : yyyy-MM-dd HH:MM:SS
function getNowTime(type){
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = year + seperator1 + month + seperator1 + strDate;
    if(type){
        currentdate += ' '+date.getHours() + seperator2 + date.getMinutes()+ seperator2 + date.getSeconds();
    }
    return currentdate;
}

/**
 * 计算两个字符串时间之间的天数
 * @param s1 开始时间：格式：2018-09-30
 * @param s2 结束时间：格式：2018-09-31
 */
function getTwoBetweenDates(s1,s2){
    var time = 0;
    if(s1 && s2){
        s1 = new Date(s1.replace(/-/g, "/"));
        s2 = new Date(s2.replace(/-/g, "/"));
        var days = s2.getTime() - s1.getTime();
        time = parseInt(days / (1000 * 60 * 60 * 24));
    }
    return time;
}

/**
 * 计算当前时间加上指定天数(负数为减去)
 * @param s1 开始时间：格式：2018-09-30
 * @param days Integer天数 : 30
 * @returns {string}  格式：2018-10-30
 */
function getNewData(s1, dayt,type) {
    var newDays = '';
    var days = dayt;
    if(!days || days == '' || 'undefined'== typeof(days)){
        days = 0;
    }
    if(s1 && days != 0){
        s1 = new Date(s1.replace(/-/g, "/"));
        var millSeconds = Math.abs(s1) + (days * 24 * 60 * 60 * 1000);
        var rDate = new Date(millSeconds);
        var year = rDate.getFullYear();
        var month = rDate.getMonth() + 1;
        if (month < 10) month = "0" + month;
        var date = rDate.getDate();
        if (date < 10) date = "0" + date;
        newDays = year + "-" + month + "-" + date
        if(type){
            var h = rDate.getHours();
            h = h < 10 ? ('0' + h) : h;
            var m = rDate.getMinutes();
            m = m < 10 ? ('0' + m) : m;
            newDays += ' '+h + ':' + m;
            var s = rDate.getSeconds();
            s =  s < 10 ? ("0" + s) : s;
            newDays += ':'+s;
        };
    }
    return newDays;
}

/**当前时间根据年月日，加上对应时间/*
*   功能:实现VBScript的DateAdd功能.
*   参数:interval,字符串表达式，表示要添加的时间间隔.
*   参数:number,数值表达式，表示要添加的时间间隔的个数.
*   参数:date,时间对象.
*   返回:新的时间对象.
*   var now = new Date();
*   var newDate = DateAdd( "d", 5, now);
*---------------   DateAdd(interval,number,date)   -----------------
*/
function DateAdd(interval, number, date) {
    if(number){
        number = parseInt(number);
        switch (interval) {
            case "年": {
                date.setFullYear(date.getFullYear() + number);
                return date;
                break;
            }
            case "q ": {
                date.setMonth(date.getMonth() + number * 3);
                return date;
                break;
            }
            case "月": {
                date.setMonth(date.getMonth() + number);
                return date;
                break;
            }
            case "周": {
                date.setDate(date.getDate() + number * 7);
                return date;
                break;
            }
            case "天": {
                date.setDate(date.getDate() + number);
                return date;
                break;
            }
            case "小时": {
                date.setHours(date.getHours() + number);
                return date;
                break;
            }
            case "分": {
                date.setMinutes(date.getMinutes() + number);
                return date;
                break;
            }
            case "秒": {
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
}