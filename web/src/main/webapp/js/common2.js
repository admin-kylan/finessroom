// startDate();
function startDate() {

    var test1 = $(".test1");
    for (var j = 0; j < test1.length; j++) {
        jeDate(test1[j], {
            format: "hh:mm",
            multiPane: false,
            range: " 至 ",
            donefun: function (obj) {
                console.log(obj.val)
                // right.cardConsumeSetDTO.cardConsumePladdsetDTO.useDayStar = obj.val
                this.val(obj.val)
            }
        });
    }
    var startDate = $(".startDate");
    jeDate('.startDate', {
        format: "YYYY-MM-DD",
        donefun: function (obj) {
            right.$data.Pladdsets.useDayStar = obj.val
        }
    });
    for (var j = 0; j < startDate.length-1; j++) {
        let that = this;
        // console.log(j);
        jeDate('.startDate' + j, {
            format: "YYYY-MM-DD",
            donefun: function (obj) {
                // 				console.log(obj.elem.classList[3].split("_")[1]);
                // 				console.log(right.$data.cardConsumeSetDTO.cardConsumePladdsetDTO[obj.elem.classList[3].split("_")[1]].useDayStar);
                right.$data.cardConsumeSetDTO.cardConsumePladdsetDTO[obj.elem.classList[3].split("_")[1]].useDayStar = obj.val
            }
        });
    }
    var endDate = $(".endDate");
    jeDate('.endDate', {
        format: "YYYY-MM-DD",
        donefun: function (obj) {
            right.$data.Pladdsets.useDayEnd = obj.val
        }
    });
    for (var j = 0; j < endDate.length-1; j++) {
        jeDate('.endDate' + j, {
            format: "YYYY-MM-DD",
            donefun: function (obj) {
                right.$data.cardConsumeSetDTO.cardConsumePladdsetDTO[obj.elem.classList[3].split("_")[1]].useDayStar = obj.val

            }
        });
    }
    var startTime = $(".startTime");
    jeDate('.startTime', {
        format: "hh:mm",
        donefun: function (obj) {
            right.$data.Pladdsets.useTimeStar = obj.val
        }
    });
    for (var j = 0; j < startTime.length-1; j++) {
        jeDate('.startTime' + j, {
            format: "hh:mm",
            donefun: function (obj) {
                right.$data.cardConsumeSetDTO.cardConsumePladdsetDTO[obj.elem.classList[3].split("_")[1]].useDayStar = obj.val
            }
        });
    }
    var endTime = $(".endTime");
    jeDate('.endTime', {
        format: "hh:mm",
        donefun: function (obj) {
            right.$data.Pladdsets.useTimeEnd = obj.val
        }
    });
    for (var j = 0; j < endTime.length-1; j++) {
        jeDate('.endTime' + j, {
            format: "hh:mm",
            donefun: function (obj) {
                right.$data.cardConsumeSetDTO.cardConsumePladdsetDTO[obj.elem.classList[3].split("_")[1]].useDayStar = obj.val
            }
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
// 创建设置时间
// function dsada(e) {
    
//     startDate();
//     table_hide();
// }

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
function isCardNo(card) {
    // 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X
    var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
    if (!reg.test(card)) {
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
    let reg = /^[0-9]+.?[0-9]*$/; //判断字符串是否为数字 ，判断正整数用/^[1-9]+[0-9]*]*$/
    if (!reg.test(value)) {
        return false;
    } else {
        return true;
    }
}

function table_hide() {

    var idenx = $('.mask .table_hide').find('tbody tr').length;
    for (var i = 0; i < idenx; i++) {
        $('.mask .table_hide').find('tbody tr').eq(i).find('td').eq(0).text(i + 1);
    }

}