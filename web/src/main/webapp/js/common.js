var test1 = $(".test1");
for (var j = 0; j < test1.length; j++) {
    jeDate(test1[j], {
        format: "hh:mm",
        multiPane: false,
        range: " 至 "
    });
}
startDate();
function startDate(){

    var startDate = $(".startDate");

    for (var j = 0; j < startDate.length; j++) {
        jeDate(startDate[j], {
            format: "YYYY-MM-DD",
        });
    }
    var endDate = $(".endDate");
    for (var j = 0; j < endDate.length; j++) {
        jeDate(endDate[j], {
            format: "YYYY-MM-DD",
        });
    }
    var startTime = $(".startTime");
    for (var j = 0; j < startTime.length; j++) {
        jeDate(startTime[j], {
            format: "hh:mm",
        });
    }
    var endTime = $(".endTime");
    for (var j = 0; j < endTime.length; j++) {
        jeDate(endTime[j], {
            format: "hh:mm",
        });
    }
    var dataData = $(".dataData");
    for (var j = 0; j < dataData.length; j++) {
        jeDate(dataData[j], {
            format: "YYYY-MM-DD hh:mm:ss",
        });
    }
}

function getParam(paramName) {
    paramValue = "", isFound = !1;
    if (this.location.search.indexOf("?") == 0 && this.location.search.indexOf("=") > 1) {
        arrSource = unescape(this.location.search).substring(1, this.location.search.length).split("&"), i = 0;
        while (i < arrSource.length && !isFound) arrSource[i].indexOf("=") > 0 && arrSource[i].split("=")[0].toLowerCase() == paramName.toLowerCase() && (paramValue = arrSource[i].split("=")[1], isFound = !0), i++
    }
    return paramValue == "" && (paramValue = null), paramValue
}

/**
 * 判断是否为手机号
 * @param pone
 * @returns {boolean}
 */
function isPoneAvailable(pone) {
    var myreg = /^[1][3,4,5,7,8][0-9]{9}$/;
    if (!myreg.test(pone)) {
        return false;
    } else {
        return true;
    }
}

/**
 * 判断是否为电话号码
 * @param tel
 * @returns {boolean}
 */
function isTelAvailable(tel) {
    var myreg = /^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
    if (!myreg.test(tel)) {
        return false;
    } else {
        return true;
    }
}

/**
 * 判断是否为身份证号码
 * @param card
 * @returns {boolean}
 */
function isCardNo(card){
    // 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X
    var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
    if(!reg.test(card)){
        return false;
    } else {
        return true;
    }
}

/**
 * 判断是否为数字
 * @param value
 */
function checkNum(value) {
    let reg=/^[0-9]+.?[0-9]*$/; //判断字符串是否为数字 ，判断正整数用/^[1-9]+[0-9]*]*$/
    if(!reg.test(value)){
        return false;
    } else {
        return true;
    }
}

