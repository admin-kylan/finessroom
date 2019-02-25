/**
 * by Kylan
 * @author
 * @date 2019-01-22
 */
Vue.component('edu-course-coach-date-list-children', {//模版挂载的标签名
    template: '#edu-course-coach-date-list-id',//id对应子组件的ID
    data() {
        return {
            index: 0,
            coachList: [],
            selectCoachId: '',
            selectCoachItem: {},
            //周
            roomList: [],
            selectRoomId: '',
            selectRoomName: '',
            selectRoomItem: {},
            resultListWeek: [],
            selectUniId: '',
            //月份
           // showDate: '',
            selectYear: new Date().getFullYear(),
            selectMonth: new Date().getMonth() + 1,
            beginMonthDate: '',
            endMonthDate: '',
            resultListMonth: [],
            //时间设置
            dateSetStatus: false,
            weekBeginDate: '',
            weekEndDate: '',
            //复制的对象
            copyItem: '',
            shopList: [],
            selectShopId: '',
            copyEduStatus: false,
        }
    },
    filters: {
        toDateHHmm(val){
            let date = new Date(val);
            let h = date.getHours();
            h = h < 10 ? ('0' + h) : h;
            let m = date.getMinutes();
            m = m < 10 ? ('0' + m) : m;
            let _time = "";
            _time += ' '+h + ':' + m;
            return _time;
        },
    },
    computed: {},
    watch: {
        selectCoachId(val){
            let a = this.coachList.find((item)=>{
                return item.coachId == val;
            });
            if(!a){
                a = {};
            }
            this.selectCoachItem = a;
            this.loadMonthEdu();
        },
        selectShopId(val){
            this.loadMonthEdu();
        },
        selectYear(){
            this.getMonthFirstAndEnd();
        },
        selectMonth(){
            this.getMonthFirstAndEnd();
        }
    },
    created() {
        this.loadMonth();
        //事件出发加载
        Event.$off(EDU_CONSTANT.listenerEducationMonthList)
        Event.$on(EDU_CONSTANT.listenerEducationMonthList, (item)=>{
            this.loadMonth();
        });

    },
    mounted() {


    },
    updated: function () {
    },
    methods: {
        S4(){
            return (((1+Math.random())*0x10000)|0).toString(16).substring(1);
        },
        LI(i,j){
            this.$emit('return-page', i, j)
        },
        copyCreateEdu(){
            //this.copyEduStatus = true
            Event.$emit(EDU_CONSTANT.listenerEducationCopyInfo)
        },
        loadMonth(){
            let code = $.cookie('code'), shopId = $.cookie('shopid');
            //获取教练
            axiosGetParams(EDUCATION_URL.findCoachList, {code: code, shopId: shopId}, (res) => {
                this.coachList = res;
                if(this.coachList){
                    this.selectCoachId = this.coachList[0]['coachId']
                }
                axiosGetParams(EDUCATION_URL.marketShop, {type: 0}, (res) => {
                    let obj = {};
                    $.each(res.data, (i, d) => {

                        obj.shopName = d.shopName;
                        obj.id = d.id;
                        this.shopList.push(obj);
                        obj = {};
                    });
                    this.selectShopId = this.shopList[0]['id'];
                    this.getMonthFirstAndEnd();
                });

            });

        },
        getMonthFirstAndEnd(){
            //当前月份
            let monthStartDate = new Date(parseInt(this.selectYear), parseInt(this.selectMonth) - 1, 1);
            let monthEndDate = new Date(parseInt(this.selectYear), parseInt(this.selectMonth) - 1, getMonthDays(parseInt(this.selectMonth) - 1));
            this.beginMonthDate = formatDate(monthStartDate);
            this.endMonthDate = formatDate(monthEndDate);
            this.loadMonthEdu()
        },
        nextMonthFunc(num){
            let date = new Date(this.selectYear + "-" + this.selectMonth + "-" + "01");
            if(num == 1){
                date.setMonth(date.getMonth() + 1);
            }
            if(num == -1){
                date.setMonth(date.getMonth() - 1);
            }
            this.selectYear = date.getFullYear();
            this.selectMonth = date.getMonth() + 1;

        },
        toDateHHmm(begin, end){
            //begin
            let date = new Date(begin);
            let h = date.getHours();
            h = h < 10 ? ('0' + h) : h;
            let m = date.getMinutes();
            m = m < 10 ? ('0' + m) : m;
            let _time = h + ":" + m;
            //end
            date = new Date(end);
            h = date.getHours();
            h = h < 10 ? ('0' + h) : h;
            m = date.getMinutes();
            m = m < 10 ? ('0' + m) : m;
            _time = _time + "~" + h + ":" + m;
            return _time;
        },
        loadMonthEdu(){
            let code = $.cookie('code'), shopId = $.cookie('shopid');
            if(!this.selectShopId){
                return false;
            }
            //设置当前需要保存的时间
            this.setMonthCalendar()
            let param = {
                shopId: this.selectShopId,
                code: code,
                coachId: this.selectCoachId,
                beginDate: this.beginMonthDate + " 00:00:00",
                endDate: this.endMonthDate + " 23:59:59",
                eduType: 1,
            };
           // this.resultListMonth = this.setMonthCalendar();
          //  console.log(param)
            axiosGetParams(EDUCATION_URL.findEduListByMonth, param, (res)=>{
            //    console.log(res);
                //清空list 避免重复
                this.resultListMonth.forEach(item=>{
                    item.list = [];
                });
                //console.log(res)
                $.each(res, (i,d)=>{
                   // console.log(d)
                    let date = new Date(d.beginDate).getDate();
                   // console.log(date)
                  //  debugger
                    this.resultListMonth.forEach(item=>{
                        if(item.status == true && item.day == date){
                            item.list.push(d);
                        }
                    });
                })
               // console.log(this.resultListMonth)

            })
        },
        setMonthCalendar(){
            //保存的对象
            let obj = {};
            let result = [];
            //当前的开始时间
            let beginDate = new Date(this.beginMonthDate);
            let endDate = new Date(this.endMonthDate);
            let prevDate = new Date(this.beginMonthDate);
            //计算第一天是星期几
            let firstWeekOfDate = beginDate.getDay();
            //计算这个月有几天
            let days = getMonthDays(beginDate.getMonth())
            //计算最后一天是星期几
            let endWeekOfDate = endDate.getDay();
            //计算上个月的时间并计算上个月的天数
            prevDate.setMonth(prevDate.getMonth() - 1);
            let prevDays = getMonthDays(prevDate.getMonth())
            //上个月还剩的天数
            let length = firstWeekOfDate == 0? 7: firstWeekOfDate;
            //计算这个月还剩的天数
            let nowLength = 7 - (endWeekOfDate == 0? 7: endWeekOfDate);
            for(let i = (prevDays - length +2); i <= prevDays; i++){
                obj.status = false;
                obj.day = i;
                result.push(obj);
                obj = {};
            }
            for(let i = 1; i < days + 1; i++){
                obj.status = true;
                obj.date = beginDate.getFullYear() + "-" + (beginDate.getMonth() + 1) + "-" + beginDate.getDate();
                obj.day = i;
                obj.id = (this.S4()+this.S4()+this.S4()+this.S4()+this.S4()+this.S4()+this.S4()+this.S4())
                obj.list = [];
                result.push(obj);
                obj = {};
            }
            for(let i = 1; i < nowLength + 1; i++){
                obj.status = false;
                obj.day = i;
                result.push(obj);
                obj = {};
            }
            this.resultListMonth = result;

        },
        //删除
        deleteEdu(item){
            let eduId = item.list[0].eduId;
            let conf = confirm("确认删除该课程！！！");
            if(conf == true){
                axiosPostParams(EDUCATION_URL.deleteEdu, {eduId: eduId}, ()=>{
                    item.list = []; //清空
                },()=>{
                    $.alert('删除失败！')
                })
            }
           // console.log(item)
        },
        //复制
        copyEdu(item){
            this.copyItem = item;
          //  console.log(item)
        },
        //粘贴
        pastEdu(obj, timeArr){
            //粘贴进去
            if(this.copyItem == ""){
                return false;
            }
            let time = obj.time;
            //要复制的对象
            let beginDate = getWeekStartDate() + " " + timeArr.split('~')[0] + ":00";
            let endDate = getWeekStartDate() + " " + timeArr.split('~')[1] + ":00";
            beginDate = new Date(beginDate);
            endDate = new Date(endDate);
            beginDate.setDate(beginDate.getDate() + time)
            endDate.setDate(endDate.getDate() + time)

            let item = this.copyItem.list[0];
            //计算当前的时间
            let param = {
                eduId: item.eduId,
                beginDate: timeFormatDate(beginDate, true, true),
                endDate: timeFormatDate(endDate, true, true),
            }

            let conf = confirm("确认进行复制排课！！！");
            if(conf == true){
                axiosPostParams(EDUCATION_URL.copyEducationByEduId, param, ()=>{
                    $.alert("复制成功！");
                    obj.list = this.copyItem.list;
                    this.copyItem = "";
                },()=>{
                    $.alert('排课失败！')
                })
            }
        },
        saveDateSet(){
            if(!this.weekBeginDate){
                $.alert('请输入开始时间')
                return false;
            }
            if(!this.weekEndDate){
                $.alert('请输入结束时间')
                return false;
            }
            let obj = {}
            let key = this.weekBeginDate + "~" + this.weekEndDate;
            obj.date = key; //key
            obj.array = []; //array
          //  obj.array.push(d);
            obj.id = (this.S4()+this.S4()+this.S4()+this.S4()+this.S4()+this.S4()+this.S4()+this.S4());
            obj.list = [
                {time: 1, list: [],id: (this.S4()+this.S4()+this.S4()+this.S4()+this.S4()+this.S4()+this.S4()+this.S4())},
                {time: 2, list: [],id: (this.S4()+this.S4()+this.S4()+this.S4()+this.S4()+this.S4()+this.S4()+this.S4())},
                {time: 3, list: [],id: (this.S4()+this.S4()+this.S4()+this.S4()+this.S4()+this.S4()+this.S4()+this.S4())},
                {time: 4, list: [],id: (this.S4()+this.S4()+this.S4()+this.S4()+this.S4()+this.S4()+this.S4()+this.S4())},
                {time: 5, list: [],id: (this.S4()+this.S4()+this.S4()+this.S4()+this.S4()+this.S4()+this.S4()+this.S4())},
                {time: 6, list: [],id: (this.S4()+this.S4()+this.S4()+this.S4()+this.S4()+this.S4()+this.S4()+this.S4())},
                {time: 0, list: [],id: (this.S4()+this.S4()+this.S4()+this.S4()+this.S4()+this.S4()+this.S4()+this.S4())}
            ];
            this.resultListWeek.push(obj);
            this.dateSetStatus = false;
        },
        monthEnter(item){
            this.selectUniId = item.id
        },
        monthLeave(){
            this.selectUniId = "";
        },
        deleteEduMonth(item,list,j){
            let eduId = item.eduId;
            let conf = confirm("确认删除该课程！！！");
            if(conf == true){
                axiosPostParams(EDUCATION_URL.deleteEdu, {eduId: eduId}, ()=>{
                    list.splice(j, 1);
                },()=>{
                    $.alert('删除失败！')
                })
            }

        },
        chooseEduSetting(obj){
            //如果课程结束，则不被点击
            if(obj.status == 2 || obj.status == 3){
                return false;
            }
            Event.$emit(EDU_CONSTANT.listenerClassSettingInfo, obj);

        }
    },
});