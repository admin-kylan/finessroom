/**
 * by Kylan
 * @author
 * @date 2019-01-23
 */
Vue.component('edu-copy-coach-class-children', {//模版挂载的标签名
    template: '#edu-copy-coach-class-id',//id对应子组件的ID
    data() {
        return {
            beginDate: '',
            endDate: '',
            stepTime: 0,
            coachList: [],
            selectCoachId: '',
            courseList: [],
            selectCourseId: '',
            copyBeginDate: '',
            copyEndDate: '',
            selectToCoachId: '',
            selectToCoachName: '',
            isSave: false,
            isShow: false,
        }
    },
    filters: {},
    computed: {},
    watch: {
        beginDate(val, old){
            //判断不能超过31天
            if(!this.endDate){
                return false;
            }
            let date = this.diffDate(val, this.endDate)
            if((date == false && date != 0) || parseInt(date) > 30){
                this.beginDate = old;
                return false;
            }
            this.stepTime = date
        },
        endDate(val, old){
            //判断不能超过31天
            if(!this.beginDate){
                return false;
            }
            let date = this.diffDate(this.beginDate, val)
            if((date == false && date != 0) || parseInt(date) > 30){
                this.endDate = old;
                return false;
            }
            this.stepTime = date;
        },
        copyBeginDate(val){
            //计算结束时间
            let date = new Date(val);
            date.setDate(date.getDate() + parseInt(this.stepTime))
            this.copyEndDate = timeFormatDate(date);
        },
        selectToCoachId(val){
            let a = this.coachList.find((item)=>{
                return item.coachId == val;
            });
            if(!a){
                a = {};
            }
            this.selectToCoachName = a.coachName;

        },
    },
    created() {
        Event.$off(EDU_CONSTANT.listenerEducationCopyInfo)
        Event.$on(EDU_CONSTANT.listenerEducationCopyInfo, ()=>{
            this.isShow = true;
            this.load();
        });
        this.load();
    },
    mounted() {
        let that = this;
        //
        jeDate('#copy-start-date',{
            // isinitVal: true,
            festival: true,
            format: 'YYYY-MM-DD',
            donefun(obj, val){
                that.beginDate = obj.val;
            }
        });
        //
        jeDate('#copy-end-date',{
            // isinitVal: true,
            festival: true,
            format: 'YYYY-MM-DD',
            donefun(obj, val){
                that.endDate = obj.val;
            }
        });
        jeDate('#copy-to-start-date',{
            // isinitVal: true,
            festival: true,
            format: 'YYYY-MM-DD',
            donefun(obj, val){
                //判断如果没有选择开始时间和结束时间，则不执行
                if(!that.beginDate || !that.endDate){
                    $.alert("请选择复制课程的时间范围");
                    that.copyBeginDate = "";
                    return false;
                }else{
                    that.copyBeginDate = obj.val;
                }

            }
        });
    },
    updated: function () {
    },
    methods: {
        LI(i,j){
            this.$emit('return-page', i, j)
        },
        load(){
            let code = $.cookie('code'), shopId = $.cookie('shopid');
            //获取教练
            axiosGetParams(EDUCATION_URL.findCoachList, {code: code, shopId: shopId}, (res) => {
                this.coachList = res;
                if(this.coachList){
                    this.selectCoachId = this.coachList[0]['coachId']
                }
            });
            //获取课程 下拉框
            axiosGetParams(EDUCATION_URL.findCourseList, {CustomerCode: code, shopId: shopId, eduType: 1}, (res) => {
                this.courseList = res;
                if(this.courseList){
                    this.selectCourseId = this.courseList[0]['courseId']

                }
            });
        },
        diffDate(date1, date2){
            let dateSpan, iDays;
            date1 = Date.parse(date1);
            date2 = Date.parse(date2);
            if(date1 - date2 > 0){
                $.alert("课程开始时间必须小于课程结束时间");
                return false;
            }
            dateSpan = date2 - date1;
            dateSpan = Math.abs(dateSpan);
            iDays = Math.floor(dateSpan /(24 * 3600 * 1000)) ;
            return iDays;
        },
        save(){
            if(!this.beginDate){
                $.alert("请选择开始时间")
                return false;
            }
            if(!this.endDate){
                $.alert("请选择结束时间")
                return false;
            }
            if(!this.copyBeginDate){
                $.alert("请选择复制到的开始时间")
                return false;
            }
            if(!this.selectToCoachId){
                $.alert("请选择复制给的教练")
                return false;
            }
            if((this.selectCoachId == this.selectToCoachId) && (this.beginDateOld == this.beginDateNew)){
                $.alert("无法选择相同时间相同教练进行复制课程")
                return false;
            }

            let stepTime = 0;
            stepTime = this.diffDate(this.beginDate, this.copyBeginDate);
            if(stepTime == false && stepTime != 0){
                $.alert("复制到的开始时间必须大于复制课程的开始时间。")
                return false;
            }
            if(this.isSave == true){
                return false;
            }

            let param = {
                beginDateOld: this.beginDate + " 00:00:00",
                endDateOld: this.endDate + " 23:59:59",
                coachId: this.selectCoachId,
                courseId: this.selectCourseId,
                beginDateNew: this.copyBeginDate,
                endDateNew: this.copyEndDate,
                selectToCoachId: this.selectToCoachId,
                selectToCoachName: this.selectToCoachName,
                stepTime: stepTime,
               // code: $.cookie('code'),
                shopId: $.cookie('shopid'),
                eduType: 1,
            }
            let conf = confirm("确认进行复制排课！！！");
            if(conf == true){
                this.isSave = true;
                axiosPostParams(EDUCATION_URL.copyEducation, param, ()=>{
                    $.alert("复制成功");
                    this.LI(5);
                    this.isSave = false;
                })
            }
            //console.log(param)
        }
    },
});