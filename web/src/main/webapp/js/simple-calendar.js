class SimpleCalendar {
    //构造函数
    constructor(query, options) {
        //默认配置
        this._defaultOptions = {
            width: '500px',
            height: '500px',
            language: 'CH', //语言
            showLunarCalendar: true, //添加数据
            onSelect: () => {
            },
            timeRange: {
                startYear: 1900,
                endYear: 2049
            },
            timeZone: "", //时区
            mark: {
                '2016-5-5': '上学'
            },
            theme: {
                changeAble: false,
                weeks: {
                    backgroundColor: '#FBEC9C',
                    fontColor: '#4A4A4A',
                    fontSize: '20px'
                },
                days: {
                    backgroundColor: '#ffffff',
                    fontColor: '#565555',
                    fontSize: '24px'
                },
                todaycolor: 'orange',
                activeSelectColor: 'orange',
                invalidDays: '#C1C0C0'
            }

            //容器
        };
        this.container = document.querySelector(query);

        this._defaultOptions.width = this.container.style.offsetWidth;
        this._defaultOptions.height = this.container.style.offsetHeight;

        this._options = Object.assign({}, this._defaultOptions, options);

        //得到最终配置
        this._options = this.optionAssign(this._defaultOptions, options);

        this.create();
    }

    //用B更新A的属性 并返回结果
    optionAssign(optionsA, optionsB) {
        for (var key in optionsB) {
            if (typeof optionsA[key] === 'function') {
                if (typeof optionsB[key] === 'function') {
                    optionsA[key] = optionsB[key];
                } else {
                    console.warn(`${key} must be a function`);
                }
            } else if (typeof optionsA[key] !== 'object') optionsA[key] = optionsB[key];
            else {
                optionsA[key] = this.optionAssign(optionsA[key], optionsB[key]);
            }
        }
        return optionsA;
    }

    //生成日历样式
    create() {
        var root = this.container;
        root.innerHTML = '<div class="sc-header"> </div> <div class="sc-body"> </div>';
        root.style.width = this._options.width;
        root.style.height = this._options.height;
        root.className = 'sc-calendar';
        var header = root.querySelector('.sc-header');
        var scbody = root.querySelector('.sc-body');
        //actions
        header.innerHTML = header.innerHTML + '<div class="sc-actions">' + '      <div class="sc-yleft">' + '        &lsaquo;</div>     <div class="sc-yright">&rsaquo;</div>' + '  </div>';
        header.innerHTML = header.innerHTML + '<div class="sc-actions">' + '    <div class="sc-mleft">' + '  <img src="../img/prev.png"></div>' + '<select class="sc-select-year" name=""></select>    <select class="sc-select-month" name="">' + '    </select>' + '    <div class="sc-mright"><img src="../img/next.png"></div>' + '<div class="sc-pull-right"><div><strong class="sc-hui"></strong><span>已过期</span></div><div><strong class="sc-green"></strong><span>正在进行</span></div><div><strong class="sc-hei"></strong><span>未开始</span></div><div><strong class="sc-red"></strong><span>约满</span></div></div>' + '<div class="sc-pull-right"><div><strong class="sc-hei"></strong><span>上班时间</span></div><div><strong class="sc-hui"></strong><span>休息时间</span></div></div>' + '</div>';
        header.innerHTML = header.innerHTML + '<div class="sc-actions"><span class="sc-return-today">返回今天</span></div>';
        header.innerHTML = header.innerHTML + '<div class="sc-actions"><span class="sc-time"></span></div>';
        scbody.innerHTML = ' <div class="sc-week"> </div> <div class="sc-days"> </div>';
        var week = scbody.querySelector('.sc-week');
        var days = scbody.querySelector('.sc-days');
        for (var i = 0; i < 7; i++) {
            week.innerHTML = week.innerHTML + ' <div class="sc-week-item"></div>';
        }
        for (var i = 0; i < 35; i++) {
            days.innerHTML = days.innerHTML + '<div class="sc-item"><div class="day"></div><div class="dataday"></div><div class="sc-bottom">3123</div></div>';
        }
        //添加下拉框数据
        this.updateSelect(this.tyear, this.tmonth);
        //刷新日历
        this.update();
    }

    //刷新日历
    update(month = this.tmonth, year = this.tyear) {

        this.updateSize();
        this.updateWeek();
        this.updateEvent();
        this.addData(year, month);
    }

    //调整大小
    updateSize(width = this._options.width, height = this._options.height) {
        //将大小赋值给option
        this._options.width = width;
        this._options.height = height;

        this.container.style.width = width;
        this.container.style.height = height;

        //根据长度和宽度大小调整适合的样式
        if (parseInt(width) < 500) {
            var actions = this.arrayfrom(this.container.querySelectorAll('.sc-actions'));
            actions.forEach(function (v, i) {
                v.classList.add('sc-actions-big');
            });
        } else {
            var actions = this.arrayfrom(this.container.querySelectorAll('.sc-actions'));
            actions.forEach(function (v, i) {
                v.classList.remove('sc-actions-big');
            });
        }
        if (parseInt(height) < 400) {
            var items = this.arrayfrom(this.container.querySelectorAll('.sc-item'));
            var weeks = this.arrayfrom(this.container.querySelectorAll('.sc-week-item'));
            items.forEach(function (v, i) {
                v.querySelector('.day').classList.add('sc-item-small');
            });
            weeks.forEach(function (v, i) {
                v.classList.add('sc-item-small');
            });
        } else {
            var items = this.arrayfrom(this.container.querySelectorAll('.sc-item'));
            var weeks = this.arrayfrom(this.container.querySelectorAll('.sc-week-item'));
            items.forEach(function (v, i) {
                v.querySelector('.day').classList.remove('sc-item-small');
            });
            weeks.forEach(function (v, i) {
                v.classList.remove('sc-item-small');
            });
        }
    }

    //刷新下拉框 只有在初始化和设置语言后才会更新
    updateSelect(year, month) {
        //下拉框
        var selectYear = this.container.querySelector('.sc-select-year');
        var selectMonth = this.container.querySelector('.sc-select-month');
        selectYear.innerHTML = '';
        for (var j = this._options.timeRange.startYear; j < this._options.timeRange.endYear + 1; j++) {
            selectYear.innerHTML += '<option value="' + j + '">' + j + '年' + '</option>';
        }
        selectMonth.innerHTML = '';
        for (var i = 0; i < 12; i++) {
            var data = this.languageData['months_' + this._options.language];
            selectMonth.innerHTML += '<option value="' + (i + 1) + '">' + data[i] + '</option>';
        }

        selectYear.value = year;
        selectMonth.value = month;
    }

    //刷新星期
    updateWeek() {
        var weeks = this.arrayfrom(this.container.querySelectorAll('.sc-week-item'));
        var data = this.languageData['days_' + this._options.language];
        if (!data) {
            console.error('language error!');
        }
        weeks.forEach(function (v, i) {
            v.innerHTML = data[i];
        });
    }

    //添加数据
    addData(year, month) {

        var daysElement = this.arrayfrom(this.container.querySelectorAll('.sc-item'));
        var day = new Date(year, month - 1, 1);
        var week = day.getDay();
        if (week == 0) week = 7;

        //计算得到第一个格子的日期
        var thispageStart = new Date(Date.parse(day) - (week - 1) * 24 * 3600 * 1000);

        //对每一个格子遍历
        for (var i = 0; i < 35; i++) {
            daysElement[i].className = 'sc-item';
            var theday = new Date(Date.parse(thispageStart) + i * 24 * 3600 * 1000);
            var writeyear = theday.getFullYear();
            var writeday = theday.getDate();
            var writemonth = theday.getMonth() + 1;
            if (writemonth != month) {
                daysElement[i].classList.add('sc-othermenth');
            }
            daysElement[i].querySelector('.day').innerHTML = writeday;
            // 这里进行数据的交互判断(重点)

            // 判断年
            if (this.tyear >= year) {
                // 判断月
                if (this.tyear == year && this.tmonth <= month) {
                    // 判断日
                    if (this.tmonth == month && i < this.tday - 1) {

                        daysElement[i].querySelector('.dataday').innerHTML = "<div class='huihui'><p>健身一对一</p> <p>Klaus  10:00~10:15</p></div> <div class='huihui'><p>健身一对一</p> <p>Klaus  10:00~10:15</p></div><div class='huihui'><p>健身一对一</p> <p>Klaus  10:00~10:15</p></div>";

                    } else {

                        daysElement[i].querySelector('.dataday').innerHTML = "<div class='red' onclick='huired()'><p>特色私教</p> <p>Sherry 16:30~17:00</p></div> <div class='hui' onclick='hui()'><p>特色私教</p> <p>Sherry 16:30~17:00</p></div><div class='green' onclick='huigreen()'><p>健身一对一</p> <p>Klaus  10:00~10:15</p></div>";

                    }

                } else {

                    daysElement[i].querySelector('.dataday').innerHTML = "<div class='huihui'><p>健身一对一</p> <p>Klaus  10:00~10:15</p></div> <div class='huihui'><p>健身一对一</p> <p>Klaus  10:00~10:15</p></div><div class='huihui'><p>健身一对一</p> <p>Klaus  10:00~10:15</p></div>";

                }

            } else {
                daysElement[i].querySelector('.dataday').innerHTML = "<div class='red' onclick='huired()'><p>特色私教</p> <p>Sherry 16:30~17:00</p></div> <div class='hui' onclick='hui()'><p>特色私教</p> <p>Sherry 16:30~17:00</p></div><div class='green' onclick='huigreen()'><p>健身一对一</p> <p>Klaus  10:00~10:15</p></div>";
            }


            daysElement[i].querySelector('.sc-bottom').innerHTML = "<div><p>健身一对一</p> <p>Klaus  10:00~10:15</p><a class='btns'>X</a></div><div><p>健身一对一</p> <p>Klaus  10:00~10:15</p><a class='btns'>X</a></div><div><p>健身一对一</p> <p>Klaus  10:00~10:15</p><a class='btns'>X</a></div>";
            //添加today样式
            if (this.tyear == writeyear && this.tday == writeday && this.tmonth == writemonth) {
                this.selectDay = daysElement[i];
                daysElement[i].classList.add("sc-today");
            }
        }
    }

    //刷新事件
    updateEvent() {
        var daysElement = this.arrayfrom(this.container.querySelectorAll('.sc-item'));
        var container = this.container;
        var calendar = this;
        daysElement.forEach(function (v, i) {
            v.onmouseover = function (e) {
                this.classList.add('sc-active-day');
                if (($(this).index() + 1) % 7) {
                    $(this).find('.sc-bottom').show().css({
                        'left': $(this).width() - 20
                    });
                } else {
                    $(this).find('.sc-bottom').show().css({
                        'left': '-' + $(this).width() - 20
                    });
                }
            };
            v.onmouseout = function (e) {
                this.classList.remove('sc-active-day');
                $('.sc-bottom').hide();
            };
            v.onclick = function () {
                calendar.selectDay = v;
                var pre = container.querySelector('.sc-selected');
                if (pre) pre.classList.remove('sc-selected');
                this.classList.add('sc-selected');
                if (typeof calendar._options.onSelect === 'function') {
                    calendar._options.onSelect(calendar.getSelectedDay());
                }
            };
        });

        var selectYear = container.querySelector('.sc-select-year');
        var selectMonth = container.querySelector('.sc-select-month');
        selectYear.onchange = function () {
            var m = selectMonth.value;
            var y = this.value;
            calendar.update(m, y);
        };

        selectMonth.onchange = function () {
            var y = selectYear.value;
            var m = this.value;
            calendar.update(m, y);
        };

        var yearadd = container.querySelector('.sc-yright');
        var yearsub = container.querySelector('.sc-yleft');
        var monthadd = container.querySelector('.sc-mright');
        var monthsub = container.querySelector('.sc-mleft');

        yearadd.onclick = function () {
            var currentyear = selectYear.value;
            if (currentyear < 2099) currentyear++;
            selectYear.value = currentyear;
            calendar.update(this.tmonth, currentyear);
        };
        yearsub.onclick = function () {
            var currentyear = selectYear.value;
            if (currentyear > 1900) currentyear--;
            selectYear.value = currentyear;
            calendar.update(this.tmonth, currentyear);
        };
        monthadd.onclick = function () {
            var currentmonth = selectMonth.value;
            var currentyear = selectYear.value;
            if (currentmonth < 12) currentmonth++;
            else {
                currentmonth = 1;
                selectYear.value = ++currentyear;
            }
            ;
            selectMonth.value = currentmonth;
            calendar.update(currentmonth, currentyear);
        };
        monthsub.onclick = function () {
            var currentmonth = selectMonth.value;
            var currentyear = selectYear.value;
            if (currentmonth > 1) currentmonth--;
            else {
                currentmonth = 12;
                selectYear.value = --currentyear;
            }
            selectMonth.value = currentmonth;
            calendar.update(currentmonth, currentyear);
        };

        var returntoday = container.querySelector('.sc-return-today');
        returntoday.onclick = function () {
            selectYear.value = calendar.tyear;
            selectMonth.value = calendar.tmonth;
            calendar.update();
        };
    }

    //获取用户点击的日期
    getSelectedDay() {
        var selectYear = this.container.querySelector('.sc-select-year').value;
        var selectMonth = this.container.querySelector('.sc-select-month').value;
        var selectDay = this.selectDay.querySelector('.day').innerHTML;

        // 计算当前界面中的其他月份差
        let count = 0;
        if (this.selectDay.classList.contains('sc-othermenth')) {
            if (+selectDay < 15) count++;
            else count--;
        }
        return new Date(selectYear, selectMonth - 1 + count, selectDay);
    }

    //nodelist转数组
    arrayfrom(nidelist) {
        var array = [];
        [].forEach.call(nidelist, function (v) {
            array.push(v);
        });
        return array;
    }

    get options() {
        console.log(this._options);
    }
}

//国际化，和一些节日数据，标记数据
SimpleCalendar.prototype.languageData = {
    days_CH: ["周一", "周二", "周三", "周四", "周五", "周六", "周日"],
    months_CH: ["1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"],
};
SimpleCalendar.prototype.tyear = new Date().getFullYear();
SimpleCalendar.prototype.tmonth = new Date().getMonth() + 1;
SimpleCalendar.prototype.tday = new Date().getDate();


class SimpleCalendar2 {
    //构造函数
    constructor(query, options) {
        //默认配置
        this._defaultOptions = {
            width: '500px',
            height: '500px',
            language: 'CH', //语言
            showLunarCalendar: true, //添加数据
            onSelect: () => {
            },
            timeRange: {
                startYear: 1900,
                endYear: 2049
            },
            timeZone: "", //时区
            mark: {
                '2016-5-5': '上学'
            },
            theme: {
                changeAble: false,
                weeks: {
                    backgroundColor: '#FBEC9C',
                    fontColor: '#4A4A4A',
                    fontSize: '20px'
                },
                days: {
                    backgroundColor: '#ffffff',
                    fontColor: '#565555',
                    fontSize: '24px'
                },
                todaycolor: 'orange',
                activeSelectColor: 'orange',
                invalidDays: '#C1C0C0'
            }

            //容器
        };
        this.container = document.querySelector(query);

        this._defaultOptions.width = this.container.style.offsetWidth;
        this._defaultOptions.height = this.container.style.offsetHeight;

        this._options = Object.assign({}, this._defaultOptions, options);

        //得到最终配置
        this._options = this.optionAssign(this._defaultOptions, options);

        this.create();
    }

    //用B更新A的属性 并返回结果
    optionAssign(optionsA, optionsB) {
        for (var key in optionsB) {
            if (typeof optionsA[key] === 'function') {
                if (typeof optionsB[key] === 'function') {
                    optionsA[key] = optionsB[key];
                } else {
                    console.warn(`${key} must be a function`);
                }
            } else if (typeof optionsA[key] !== 'object') optionsA[key] = optionsB[key];
            else {
                optionsA[key] = this.optionAssign(optionsA[key], optionsB[key]);
            }
        }
        return optionsA;
    }

    //生成日历样式
    create() {
        var root = this.container;
        root.innerHTML = '<div class="sc-header"> </div> <div class="sc-body"> </div>';
        root.style.width = this._options.width;
        root.style.height = this._options.height;
        root.className = 'sc-calendar';
        var header = root.querySelector('.sc-header');
        var scbody = root.querySelector('.sc-body');
        //actions
        header.innerHTML = header.innerHTML + '<div class="sc-actions">' + '      <div class="sc-yleft">' + '        &lsaquo;</div>     <div class="sc-yright">&rsaquo;</div>' + '  </div>';
        header.innerHTML = header.innerHTML + '<div class="sc-actions">' + '    <div class="sc-mleft">' + '  <img src="../img/prev.png"></div>' + '<select class="sc-select-year" name=""></select>    <select class="sc-select-month" name="">' + '    </select>' + '    <div class="sc-mright"><img src="../img/next.png"></div>' + '<div class="sc-pull-right"><div><strong class="sc-hui"></strong><span>已过期</span></div><div><strong class="sc-green"></strong><span>正在进行</span></div><div><strong class="sc-hei"></strong><span>未开始</span></div><div><strong class="sc-red"></strong><span>约满</span></div></div>' + '<div class="sc-pull-right"><div><strong class="sc-hei"></strong><span>上班时间</span></div><div><strong class="sc-hui"></strong><span>休息时间</span></div></div>' + '</div>';
        header.innerHTML = header.innerHTML + '<div class="sc-actions"><span class="sc-return-today">返回今天</span></div>';
        header.innerHTML = header.innerHTML + '<div class="sc-actions"><span class="sc-time"></span></div>';
        scbody.innerHTML = ' <div class="sc-week"> </div> <div class="sc-days"> </div>';
        var week = scbody.querySelector('.sc-week');
        var days = scbody.querySelector('.sc-days');
        for (var i = 0; i < 7; i++) {
            week.innerHTML = week.innerHTML + ' <div class="sc-week-item"></div>';
        }
        for (var i = 0; i < 35; i++) {
            days.innerHTML = days.innerHTML + '<div class="sc-item"><div class="day"></div><div class="dataday"></div></div>';
        }
        //添加下拉框数据
        this.updateSelect(this.tyear, this.tmonth);
        //刷新日历
        this.update();
    }

    //刷新日历
    update(month = this.tmonth, year = this.tyear) {
        this.updateSize();
        this.updateWeek();
        this.addData(year, month);
        this.updateEvent();
    }

    //调整大小
    updateSize(width = this._options.width, height = this._options.height) {
        //将大小赋值给option
        this._options.width = width;
        this._options.height = height;

        this.container.style.width = width;
        this.container.style.height = height;

        //根据长度和宽度大小调整适合的样式
        if (parseInt(width) < 500) {
            var actions = this.arrayfrom(this.container.querySelectorAll('.sc-actions'));
            actions.forEach(function (v, i) {
                v.classList.add('sc-actions-big');
            });
        } else {
            var actions = this.arrayfrom(this.container.querySelectorAll('.sc-actions'));
            actions.forEach(function (v, i) {
                v.classList.remove('sc-actions-big');
            });
        }
        if (parseInt(height) < 400) {
            var items = this.arrayfrom(this.container.querySelectorAll('.sc-item'));
            var weeks = this.arrayfrom(this.container.querySelectorAll('.sc-week-item'));
            items.forEach(function (v, i) {
                v.querySelector('.day').classList.add('sc-item-small');
            });
            weeks.forEach(function (v, i) {
                v.classList.add('sc-item-small');
            });
        } else {
            var items = this.arrayfrom(this.container.querySelectorAll('.sc-item'));
            var weeks = this.arrayfrom(this.container.querySelectorAll('.sc-week-item'));
            items.forEach(function (v, i) {
                v.querySelector('.day').classList.remove('sc-item-small');
            });
            weeks.forEach(function (v, i) {
                v.classList.remove('sc-item-small');
            });
        }
    }

    //刷新下拉框 只有在初始化和设置语言后才会更新
    updateSelect(year, month) {
        //下拉框
        var selectYear = this.container.querySelector('.sc-select-year');
        var selectMonth = this.container.querySelector('.sc-select-month');
        selectYear.innerHTML = '';
        for (var j = this._options.timeRange.startYear; j < this._options.timeRange.endYear + 1; j++) {
            selectYear.innerHTML += '<option value="' + j + '">' + j + '年' + '</option>';
        }
        selectMonth.innerHTML = '';
        for (var i = 0; i < 12; i++) {
            var data = this.languageData['months_' + this._options.language];
            selectMonth.innerHTML += '<option value="' + (i + 1) + '">' + data[i] + '</option>';
        }

        selectYear.value = year;
        selectMonth.value = month;
    }

    //刷新星期
    updateWeek() {
        var weeks = this.arrayfrom(this.container.querySelectorAll('.sc-week-item'));
        var data = this.languageData['days_' + this._options.language];
        if (!data) {
            console.error('language error!');
        }
        weeks.forEach(function (v, i) {
            v.innerHTML = data[i];
        });
    }

    //添加数据
    addData(year, month) {
        var daysElement = this.arrayfrom(this.container.querySelectorAll('.sc-item'));
        var day = new Date(year, month - 1, 1);
        var week = day.getDay();
        if (week == 0) week = 7;

        //计算得到第一个格子的日期
        var thispageStart = new Date(Date.parse(day) - (week - 1) * 24 * 3600 * 1000);

        //对每一个格子遍历
        for (var i = 0; i < 35; i++) {
            daysElement[i].className = 'sc-item';
            var theday = new Date(Date.parse(thispageStart) + i * 24 * 3600 * 1000);
            var writeyear = theday.getFullYear();
            var writeday = theday.getDate();
            var writemonth = theday.getMonth() + 1;
            if (writemonth != month) {
                daysElement[i].classList.add('sc-othermenth');
            }

            daysElement[i].querySelector('.day').innerHTML = writeday + '<a class="toolorange" onclick="toolorange(this)">粘贴</a><a class="toolblur" onclick="toolblur(this)">复制</a>';
            // 这里进行数据的交互判断(重点)
            if (i < 10) {
                daysElement[i].querySelector('.dataday').innerHTML = "<p><span onclick='SetCoachTime(this)'>09:00~12:00</span> <a class='red' onclick='toolred(this)'>X</a></p><p><span onclick='SetCoachTime(this)'>09:00~12:00</span><a class='red' onclick='toolred(this)'>X</a></p>";
            } else {
                daysElement[i].querySelector('.dataday').innerHTML = "<p>+ 设置工作时间</p>";
            }
            //添加today样式
            if (this.tyear == writeyear && this.tday == writeday && this.tmonth == writemonth) {
                this.selectDay = daysElement[i];
                daysElement[i].classList.add("sc-today");
            }
        }
    }

    //刷新事件
    updateEvent() {
        var daysElement = this.arrayfrom(this.container.querySelectorAll('.sc-item'));
        var container = this.container;
        var calendar = this;
        daysElement.forEach(function (v, i) {
            v.onmouseover = function (e) {
                this.classList.add('sc-active-day');
                if ($(this).find('.dataday').html().trim() == '<p>+ 设置工作时间</p>') {
                    $(this).find('.toolorange').addClass('on');
                    $(this).find('.toolblur').removeClass('on');
                    $(this).find('.toolred').removeClass('on');
                } else {
                    $(this).find('.toolorange').removeClass('on');
                    $(this).find('.toolblur').addClass('on');
                    $(this).find('.toolred').addClass('on');
                }
            };
            v.onmouseout = function (e) {
                this.classList.remove('sc-active-day');
                $(this).find('.toolorange').removeClass('on');
                $(this).find('.toolblur').removeClass('on');
                $(this).find('.toolred').removeClass('on');
            };
            v.onclick = function () {
                calendar.selectDay = v;
                var pre = container.querySelector('.sc-selected');
                if (pre) pre.classList.remove('sc-selected');
                this.classList.add('sc-selected');
                if (typeof calendar._options.onSelect === 'function') {
                    calendar._options.onSelect(calendar.getSelectedDay());
                }
                if (!$(this).find('.dataday').html()) {
                    $(this).find('.dataday').html('<p>+ 设置工作时间</p>');
                }
            };
        });

        var selectYear = container.querySelector('.sc-select-year');
        var selectMonth = container.querySelector('.sc-select-month');
        selectYear.onchange = function () {
            var m = selectMonth.value;
            var y = this.value;
            calendar.update(m, y);
        };

        selectMonth.onchange = function () {
            var y = selectYear.value;
            var m = this.value;
            calendar.update(m, y);
        };

        var yearadd = container.querySelector('.sc-yright');
        var yearsub = container.querySelector('.sc-yleft');
        var monthadd = container.querySelector('.sc-mright');
        var monthsub = container.querySelector('.sc-mleft');

        yearadd.onclick = function () {
            var currentyear = selectYear.value;
            if (currentyear < 2099) currentyear++;
            selectYear.value = currentyear;
            calendar.update(this.tmonth, currentyear);
        };
        yearsub.onclick = function () {
            var currentyear = selectYear.value;
            if (currentyear > 1900) currentyear--;
            selectYear.value = currentyear;
            calendar.update(this.tmonth, currentyear);
        };
        monthadd.onclick = function () {
            var currentmonth = selectMonth.value;
            var currentyear = selectYear.value;
            if (currentmonth < 12) currentmonth++;
            else {
                currentmonth = 1;
                selectYear.value = ++currentyear;
            }
            ;
            selectMonth.value = currentmonth;
            calendar.update(currentmonth, currentyear);
        };
        monthsub.onclick = function () {
            var currentmonth = selectMonth.value;
            var currentyear = selectYear.value;
            if (currentmonth > 1) currentmonth--;
            else {
                currentmonth = 12;
                selectYear.value = --currentyear;
            }
            selectMonth.value = currentmonth;
            calendar.update(currentmonth, currentyear);
        };

        var returntoday = container.querySelector('.sc-return-today');
        returntoday.onclick = function () {
            selectYear.value = calendar.tyear;
            selectMonth.value = calendar.tmonth;
            calendar.update();
        };
    }

    //获取用户点击的日期
    getSelectedDay() {
        var selectYear = this.container.querySelector('.sc-select-year').value;
        var selectMonth = this.container.querySelector('.sc-select-month').value;
        var selectDay = this.selectDay.querySelector('.day').innerHTML;

        // 计算当前界面中的其他月份差
        let count = 0;
        if (this.selectDay.classList.contains('sc-othermenth')) {
            if (+selectDay < 15) count++;
            else count--;
        }
        return new Date(selectYear, selectMonth - 1 + count, selectDay);
    }

    //nodelist转数组
    arrayfrom(nidelist) {
        var array = [];
        [].forEach.call(nidelist, function (v) {
            array.push(v);
        });
        return array;
    }

    get options() {
        console.log(this._options);
    }

}

//国际化，和一些节日数据，标记数据
SimpleCalendar2.prototype.languageData = {
    days_CH: ["周一", "周二", "周三", "周四", "周五", "周六", "周日"],
    months_CH: ["1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"],
};
SimpleCalendar2.prototype.tyear = new Date().getFullYear();
SimpleCalendar2.prototype.tmonth = new Date().getMonth() + 1;
SimpleCalendar2.prototype.tday = new Date().getDate();


class SimpleCalendar3 {
    //构造函数
    constructor(query, options) {
        //默认配置
        this._defaultOptions = {
            width: '500px',
            height: '500px',
            language: 'CH', //语言
            showLunarCalendar: true, //添加数据
            onSelect: () => {
            },
            timeRange: {
                startYear: 1900,
                endYear: 2049
            },
            timeZone: "", //时区
            mark: {
                '2016-5-5': '上学'
            },
            theme: {
                changeAble: false,
                weeks: {
                    backgroundColor: '#FBEC9C',
                    fontColor: '#4A4A4A',
                    fontSize: '20px'
                },
                days: {
                    backgroundColor: '#ffffff',
                    fontColor: '#565555',
                    fontSize: '24px'
                },
                todaycolor: 'orange',
                activeSelectColor: 'orange',
                invalidDays: '#C1C0C0'
            }

            //容器
        };
        this.container = document.querySelector(query);

        this._defaultOptions.width = this.container.style.offsetWidth;
        this._defaultOptions.height = this.container.style.offsetHeight;

        this._options = Object.assign({}, this._defaultOptions, options);

        //得到最终配置
        this._options = this.optionAssign(this._defaultOptions, options);

        this.create();

    }

    //用B更新A的属性 并返回结果
    optionAssign(optionsA, optionsB) {
        for (var key in optionsB) {
            if (typeof optionsA[key] === 'function') {
                if (typeof optionsB[key] === 'function') {
                    optionsA[key] = optionsB[key];
                } else {
                    console.warn(`${key} must be a function`);
                }
            } else if (typeof optionsA[key] !== 'object') optionsA[key] = optionsB[key];
            else {
                optionsA[key] = this.optionAssign(optionsA[key], optionsB[key]);
            }
        }
        return optionsA;
    }

    //生成日历样式
    create() {
        var root = this.container;
        root.innerHTML = '<div class="sc-header"> </div> <div class="sc-body"> </div>';
        root.style.width = this._options.width;
        root.style.height = this._options.height;
        root.className = 'sc-calendar';
        var header = root.querySelector('.sc-header');
        var scbody = root.querySelector('.sc-body');
        //actions
        header.innerHTML = header.innerHTML + '<div class="sc-actions">' + '      <div class="sc-yleft">&lsaquo; &lsaquo;</div>     <div class="sc-yright">&rsaquo;</div>' + '  </div>';
        header.innerHTML = header.innerHTML + '<div class="sc-actions">' + '    <div class="sc-mleft">' + '  &lsaquo;</div>' + '<select class="sc-select-year" name=""></select>    <select class="sc-select-month" name="">' + '    </select>' + '    <div class="sc-mright">&rsaquo;</div>' + '<div class="sc-pull-right"><div><strong class="sc-hui"></strong><span>已过期</span></div><div><strong class="sc-green"></strong><span>正在进行</span></div><div><strong class="sc-hei"></strong><span>未开始</span></div><div><strong class="sc-red"></strong><span>约满</span></div></div>' + '<div class="sc-pull-right"><div><strong class="sc-hei"></strong><span>上班时间</span></div><div><strong class="sc-hui"></strong><span>休息时间</span></div></div>' + '</div>';
        header.innerHTML = header.innerHTML + '<div class="sc-actions"><span class="sc-return-today">返回今天</span></div>';
        header.innerHTML = header.innerHTML + '<div class="sc-actions"><span class="sc-time"></span></div>';
        scbody.innerHTML = ' <div class="sc-week"> </div> <div class="sc-days"> </div>';
        var week = scbody.querySelector('.sc-week');
        var days = scbody.querySelector('.sc-days');
        for (var i = 0; i < 7; i++) {
            week.innerHTML = week.innerHTML + ' <div class="sc-week-item"></div>';
        }
        for (var i = 0; i < 35; i++) {
            days.innerHTML = days.innerHTML + '<div class="sc-item"><div class="day"></div><div class="dataday"></div></div>';
        }
        //添加下拉框数据
        this.updateSelect(this.tyear, this.tmonth);
        //刷新日历
        this.update();
    }

    //刷新日历
    update(month = this.tmonth, year = this.tyear) {

        this.updateSize();
        this.updateWeek();
        this.updateEvent();
        this.addData(year, month);
    }

    //调整大小
    updateSize(width = this._options.width, height = this._options.height) {
        //将大小赋值给option
        this._options.width = width;
        this._options.height = height;

        this.container.style.width = width;
        this.container.style.height = height;

        //根据长度和宽度大小调整适合的样式
        if (parseInt(width) < 500) {
            var actions = this.arrayfrom(this.container.querySelectorAll('.sc-actions'));
            actions.forEach(function (v, i) {
                v.classList.add('sc-actions-big');
            });
        } else {
            var actions = this.arrayfrom(this.container.querySelectorAll('.sc-actions'));
            actions.forEach(function (v, i) {
                v.classList.remove('sc-actions-big');
            });
        }
        if (parseInt(height) < 400) {
            var items = this.arrayfrom(this.container.querySelectorAll('.sc-item'));
            var weeks = this.arrayfrom(this.container.querySelectorAll('.sc-week-item'));
            items.forEach(function (v, i) {
                v.querySelector('.day').classList.add('sc-item-small');
            });
            weeks.forEach(function (v, i) {
                v.classList.add('sc-item-small');
            });
        } else {
            var items = this.arrayfrom(this.container.querySelectorAll('.sc-item'));
            var weeks = this.arrayfrom(this.container.querySelectorAll('.sc-week-item'));
            items.forEach(function (v, i) {
                v.querySelector('.day').classList.remove('sc-item-small');
            });
            weeks.forEach(function (v, i) {
                v.classList.remove('sc-item-small');
            });
        }
    }

    //刷新下拉框 只有在初始化和设置语言后才会更新
    updateSelect(year, month) {
        //下拉框
        var selectYear = this.container.querySelector('.sc-select-year');
        var selectMonth = this.container.querySelector('.sc-select-month');
        selectYear.innerHTML = '';
        for (var j = this._options.timeRange.startYear; j < this._options.timeRange.endYear + 1; j++) {
            selectYear.innerHTML += '<option value="' + j + '">' + j + '年' + '</option>';
        }
        selectMonth.innerHTML = '';
        for (var i = 0; i < 12; i++) {
            var data = this.languageData['months_' + this._options.language];
            selectMonth.innerHTML += '<option value="' + (i + 1) + '">' + data[i] + '</option>';
        }

        selectYear.value = year;
        selectMonth.value = month;
    }

    //刷新星期
    updateWeek() {
        var weeks = this.arrayfrom(this.container.querySelectorAll('.sc-week-item'));
        var data = this.languageData['days_' + this._options.language];
        if (!data) {
            console.error('language error!');
        }
        weeks.forEach(function (v, i) {
            v.innerHTML = data[i];
        });
    }

    //添加数据
    addData(year, month) {

        var daysElement = this.arrayfrom(this.container.querySelectorAll('.sc-item'));
        var day = new Date(year, month - 1, 1);
        var week = day.getDay();
        if (week == 0) week = 7;

        //计算得到第一个格子的日期
        var thispageStart = new Date(Date.parse(day) - (week - 1) * 24 * 3600 * 1000);

        //对每一个格子遍历
        for (var i = 0; i < 35; i++) {
            daysElement[i].className = 'sc-item';
            var theday = new Date(Date.parse(thispageStart) + i * 24 * 3600 * 1000);
            var writeyear = theday.getFullYear();
            var writeday = theday.getDate();
            var writemonth = theday.getMonth() + 1;
            if (writemonth != month) {
                daysElement[i].classList.add('sc-othermenth');
            }
            daysElement[i].querySelector('.day').innerHTML = writeday;
            // 这里进行数据的交互判断(重点)

            //添加today样式
            if (this.tyear == writeyear && this.tday == writeday && this.tmonth == writemonth) {
                this.selectDay = daysElement[i];
                daysElement[i].classList.add("sc-today");
            }
        }
    }

    //刷新事件

    updateEvent() {
        var start = [];
        var daysElement = this.arrayfrom(this.container.querySelectorAll('.sc-item'));
        var container = this.container;
        var calendar = this;
        daysElement.forEach(function (v, i) {
            v.onmouseover = function (e) {
                this.classList.add('sc-active-day');
                if (($(this).index() + 1) % 7) {
                    $(this).find('.sc-bottom').show().css({
                        'left': $(this).width() - 20
                    });
                } else {
                    $(this).find('.sc-bottom').show().css({
                        'left': '-' + $(this).width() - 20
                    });
                }
            };
            v.onmouseout = function (e) {
                this.classList.remove('sc-active-day');
                $('.sc-bottom').hide();
            };

            v.onclick = function () {
                calendar.selectDay = v;
                var pre = container.querySelectorAll('.sc-selected');
                this.classList.add('sc-selected');
                // start.push($(calendar.selectDay).index() + 1);
                // if (start.length == 2) {
                //   $(pre).removeClass('sc-selected');
                //   console.log(start)
                //   for (var i = start.sort()[0] - 1; i < start.sort()[start.length-1]; i++) {
                //     var scitem = container.querySelectorAll('.sc-item')[i];
                //         scitem.classList.add('sc-selected');
                //   }
                // }else{

                // }
                if (typeof calendar._options.onSelect === 'function') {
                    calendar._options.onSelect(calendar.getSelectedDay());

                }
            };
        });

        var selectYear = container.querySelector('.sc-select-year');
        var selectMonth = container.querySelector('.sc-select-month');
        selectYear.onchange = function () {
            var m = selectMonth.value;
            var y = this.value;
            calendar.update(m, y);
        };

        selectMonth.onchange = function () {
            var y = selectYear.value;
            var m = this.value;
            calendar.update(m, y);
        };

        var yearadd = container.querySelector('.sc-yright');
        var yearsub = container.querySelector('.sc-yleft');
        var monthadd = container.querySelector('.sc-mright');
        var monthsub = container.querySelector('.sc-mleft');

        yearadd.onclick = function () {
            var currentyear = selectYear.value;
            if (currentyear < 2099) currentyear++;
            selectYear.value = currentyear;
            calendar.update(this.tmonth, currentyear);
        };
        yearsub.onclick = function () {
            var currentyear = selectYear.value;
            if (currentyear > 1900) currentyear--;
            selectYear.value = currentyear;
            calendar.update(this.tmonth, currentyear);
        };
        monthadd.onclick = function () {
            var currentmonth = selectMonth.value;
            var currentyear = selectYear.value;
            if (currentmonth < 12) currentmonth++;
            else {
                currentmonth = 1;
                selectYear.value = ++currentyear;
            }
            ;
            selectMonth.value = currentmonth;
            calendar.update(currentmonth, currentyear);
        };
        monthsub.onclick = function () {
            var currentmonth = selectMonth.value;
            var currentyear = selectYear.value;
            if (currentmonth > 1) currentmonth--;
            else {
                currentmonth = 12;
                selectYear.value = --currentyear;
            }
            selectMonth.value = currentmonth;
            calendar.update(currentmonth, currentyear);
        };

        var returntoday = container.querySelector('.sc-return-today');
        returntoday.onclick = function () {
            selectYear.value = calendar.tyear;
            selectMonth.value = calendar.tmonth;
            calendar.update();
        };
    }

    //获取用户点击的日期
    getSelectedDay() {
        var selectYear = this.container.querySelector('.sc-select-year').value;
        var selectMonth = this.container.querySelector('.sc-select-month').value;
        var selectDay = this.selectDay.querySelector('.day').innerHTML;

        // 计算当前界面中的其他月份差
        let count = 0;
        if (this.selectDay.classList.contains('sc-othermenth')) {
            if (+selectDay < 15) count++;
            else count--;
        }
        return new Date(selectYear, selectMonth - 1 + count, selectDay);
    }

    //nodelist转数组
    arrayfrom(nidelist) {
        var array = [];
        [].forEach.call(nidelist, function (v) {
            array.push(v);
        });
        return array;
    }

    get options() {
        console.log(this._options);
    }
}

//国际化，和一些节日数据，标记数据
SimpleCalendar3.prototype.languageData = {
    days_CH: ["周一", "周二", "周三", "周四", "周五", "周六", "周日"],
    months_CH: ["1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"],
};
SimpleCalendar3.prototype.tyear = new Date().getFullYear();
SimpleCalendar3.prototype.tmonth = new Date().getMonth() + 1;
SimpleCalendar3.prototype.tday = new Date().getDate();      