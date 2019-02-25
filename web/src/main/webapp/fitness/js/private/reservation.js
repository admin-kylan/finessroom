/**
 * by Kylan
 * @author
 * @date 2019-01-09
 */
Vue.component('edu-add-reservation-children', {//模版挂载的标签名
    template: '#edu-reservation-id',//id对应子组件的ID
    props: [],//预约会员的数组
    data() {
        return {
            eduId: '', //
            eduItem: {}, //
            searchInput: '18650808999',//1865080899
            clientInfo: {
                itemCard: {},
                itemClient: {},
            }, //会员信息,包括会员卡
            roomSeatItem: {
                seatNum: [],
            },
            clientInfoList: [],//用户的集合
            cardInfoList: [],//会员卡的集合
            clientBoxStatus: false,
            cardBoxStatus: false,
            hasClientInfo: false,//验证是否选择
            classObject:{//class类
                hui: false,
                hei: false,
                fen: false
            },
            clientList: [], //预约的会员
            seatName: '', //座位名字
            isSave: false,
            seatNumCopy: [],
            //
            dateList: [],
            checkItem: {}, //选择的课程
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
        toDateyyyyMMddHHmm(val) {
            return timeFormatDate(val, true)
        },
        toDateyyyyMMdd(val){
            return timeFormatDate(val)
        },
        reserveStatusFilter(val) {
            let str = "";
            if (val == 0) {
                str = "已取消"
            }
            if (val == 1) {
                str = "已预约"
            }
            if (val == 2) {
                str = "待确认"
            }
            return str;
        },
        remarksFilter(val) {
            if (!val) {
                return "无";
            }
            return val;
        },

    },
    computed: {},
    watch: {

    },
    created() {
        //监听事件
       // Event.$off(EDU_CONSTANT.listenerEduItem)
        Event.$on(EDU_CONSTANT.listenerEduItem, (eduId, eduItem)=>{
            this.eduId = eduId;
            this.eduItem = eduItem;
            this.findEduDateByEduId();
        })
    },
    mounted() {

    },
    updated: function () {
        // this.$nextTick(() => {
        //
        // })
    },
    methods: {
        findEduDateByEduId(){
            let param = {
                beginDate: timeFormatDate(new Date().getTime(), true, true),
                endDate: getWeekEndDate().trim() + " 23:59:59",
                eduId: this.eduId,
                courseId: this.eduItem.courseId,
            }
            axiosGetParams(EDUCATION_URL.findEduDateByEduId, param, (res) =>{
                this.dateList = res;
             //   console.log(res)
            })

        },
        //查询会员
        searchClient() {
            if (!this.searchInput) {
                $.alert("请输入预约会员名字或号码")
                return false;
            }
            axiosGetParams(EDUCATION_URL.getClientList, {
                CustomerCode: $.cookie('code'),
                mobile: this.searchInput
            }, (res) => {
                this.clientInfoList = res;
                this.clientBoxStatus = true;
            });
        },
        //查询的会员选择会员
        selectClient(item){

            this.clientInfo.itemClient = item;

            this.clientBoxStatus = false;
        },

        chooseDateForEdu(item){
            //判断是否选择会员
            if(!this.clientInfo.itemClient.id){
                $.alert("请先选择预约的用户")
                return false;
            }
            //判断当前人数是否够
            //判断是否约满 item.reserveTotalNum
            if(parseInt(item.reserveTotalNum) <= (parseInt(item.eduCurrentCount) + parseInt(item.eduToConfirmCount))){
                $.alert("该课程时间段预约人数已满。")
                return false;
            }
            this.checkItem = item;

        },
        save(){

            if(this.isSave == true){
                return false;
            }
          //  console.log(this.clientList.length)

            if(!this.checkItem.eduId){
                $.alert("请选择需要上课的时间")
                return false;
            }


            this.isSave = true;
            //预约是否确认
            let reserveStatus = 1;
            // if(this.eduItem.configReserveConfirm == true){
            //     reserveStatus = 2;
            // }
            let param = {
                educationId: this.eduId,
               // cardId: this.clientInfo.itemCard.id,
                seatId: this.roomSeatItem.roomSeatId,
                seatName: this.seatName,
                memberId: this.clientInfo.itemClient.id,
                memberName: this.clientInfo.itemClient.clientName,
                reserveClientId: $.cookie('id'),
                createUserId: $.cookie('id'),
                createUserName: $.cookie('name'),
                reserveClientName: $.cookie('name'),
                //
                memberType: this.clientInfo.itemClient.memberType, //预约类型
              //  memberCardNo: this.clientInfo.itemCard.cardNo,
                mobile: this.clientInfo.itemClient.mobile,
                deductionBalance: '',//后台判断是扣卡还是扣权益
                reserveDate: new Date().getTime(),
                createTime: new Date().getTime(),
                reserveStatus: reserveStatus,
                sex: this.clientInfo.itemClient.sex,
                isUse: 1,
              //  memberCardId: this.clientInfo.itemCard.id,
                //位置用的
                seatNum: this.seatNumCopy,
                eduType: 1

            };

            //提交预约
            axiosPostParams(EDUCATION_URL.saveEducationItem, param, (res)=>{
                this.isSave = false;
                //this.findRoomClientByEduId();
                Event.$emit(EDU_CONSTANT.listenerListItemList);// 刷新首页的列表
                //刷新详情页
                Event.$emit(EDU_CONSTANT.listenerEduItem, this.eduId, this.eduItem)//刷新详情页
                newCustomers_maskLayer_X();//
                $.alert("预约成功")


            }, (res)=>{
                $.alert(res.msg)
                this.isSave = false;
            })
        }
    },
});