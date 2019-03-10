/**
 * by Kylan
 * @author
 * @date 2019-01-22
 */
Vue.component('edu-consume-detail-children', {//模版挂载的标签名
    template: '#edu-consume-detail-id',//id对应子组件的ID
    data() {
        return {
            index: '',
            classBeginDate: '',
            classEndDate: '',
            classSearchCourse: '', //项目课程
            classSearchCoach: '', //教练/销售人员
            //member
            memberMobile: '',
            memberBeginDate: '',
            memberEndDate: '',
            memberSearchCourse: '',//项目课程
            memberSearchCoach: '',//教练/销售人员
            //---
            cardTypeList:[],
            cardNameList:[],
            selectCardType: '',
            selectCardName: '',
            //- 结果数组
            memberResultList: [],
            classResultList: [],
        }
    },
    filters: {
        toCalcNum(val){
            if(!val){
                return 0;
            }
            return val;
        },
        toMoney(val){
            if(!val){
                return '0.00';
            }
            return parseFloat(val).toFixed(2);
        },
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
        toDateyyyyMMddHHmm(val){
            return timeFormatDate(val, true)
        },
        toDateyyyyMMdd(val){
            return timeFormatDate(val)
        },
        toSex(val){
            // if(val == '1'){
            //     return "女"
            // }
            // return '男'
            return val
        }
    },
    computed: {},
    watch: {
        index(val){
            if(val == 0){
                this.classBeginDate = getMonthStartDate()
                this.classEndDate = getMonthEndDate();
                this.classSearch();
            }
            if(val == 1){
                this.memberBeginDate = getMonthStartDate()
                this.memberEndDate = getMonthEndDate();
                this.memberSearch();
            }
        }
    },
    created() {
        //查询条件
        axiosGetFetch(EDUCATION_URL.findConsumeCondition, (res)=>{
            this.cardTypeList = res.cardType;
            this.cardNameList = res.cardName;
        })
        this.index = 0;
    },
    mounted() {
        let that = this;
        //上课时间
        jeDate('#consume-start-date',{
          //  isinitVal: true,
            festival: true,
            format: 'YYYY-MM-DD',
            donefun(obj, val){
                that.classBeginDate = obj.val;
            }
        });
        //上课时间
        jeDate('#consume-end-date',{
           // isinitVal: true,
            festival: true,
            format: 'YYYY-MM-DD',
            donefun(obj, val){
                that.classEndDate = obj.val;
            }
        });
        //上课时间
        jeDate('#consume-member-start-date',{
           // isinitVal: true,
            festival: true,
            format: 'YYYY-MM-DD',
            donefun(obj, val){
                that.memberBeginDate = obj.val;
            }
        });
        //上课时间
        jeDate('#consume-member-end-date',{
           // isinitVal: true,
            festival: true,
            format: 'YYYY-MM-DD',
            donefun(obj, val){
                that.memberEndDate = obj.val;
            }
        });
    },
    updated: function () {
    },
    methods: {
        classSearch(){
            let param = {
                beginDate: this.classBeginDate,
                endDate: this.classEndDate,
                searchCourse: this.classSearchCourse,
                searchCoach: this.classSearchCoach,
                shopId: $.cookie('shopid'),
                code: $.cookie('code'),
                eduType: 0,
            };

            axiosGetParams(EDUCATION_URL.findConsumeClass, param, (res)=>{
                this.classResultList = res;
            })
        },
        memberSearch(){
            let param = {
                beginDate: this.memberBeginDate,
                endDate: this.memberEndDate,
                searchCourse: this.memberSearchCourse,
                searchCoach: this.memberSearchCoach,
                shopId: $.cookie('shopid'),
                code: $.cookie('code'),
                cardType: this.selectCardType,
                cardName: this.selectCardName,
                eduType: 0,
            };
            axiosGetParams(EDUCATION_URL.findConsumeMember, param, (res)=>{
                this.memberResultList = res;
            })
        },
        //查看
        trainingDetails(item){
            Event.$emit(EDU_CONSTANT.listenerClassMemberInfo, item)
        },
        print: function () {
            var headhtml = "<html><head><title></title></head><body>";
            var foothtml = "</body>";
            // 获取div中的html内容
            // var newhtml = document.all.item(printpage).innerHTML;
            // 获取div中的html内容，jquery写法如下
            var newhtml = $(".tabW").html();
            // 获取原来的窗口界面body的html内容，并保存起来
            var oldhtml = document.body.innerHTML;

            // 给窗口界面重新赋值，赋自己拼接起来的html内容
            document.body.innerHTML = headhtml + newhtml + foothtml;
            // 调用window.print方法打印新窗口
            window.print();

            // 将原来窗口body的html值回填展示
            document.body.innerHTML = oldhtml;
            window.location.reload();
            return false;
        },
        //导出Excel
        exportExcel: function () {
            var that = this;
            var url = $.stringFormat('{0}/excel/myPotential', 'http://www.4006337366.com:8080/');
            var data=JSON.stringify(that.customerTable.list)
            $.ajax({
                url : url,
                data : data,
                type : 'POST',
                dataType : 'json',
                contentType: "application/json;charset=utf-8",
                success : function(res) {
                    alert(res.msg)
                }
            })
        },
    },
});