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
            searchInput: '',//1865080899
            clientInfo: {
                itemCard: {},
                itemClient: {},
            }, //会员信息,包括会员卡
            roomSeatItem: {},
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
        }
    },
    filters: {
        toDateHHmm(val) {
            let date = new Date(val);
            let hours = date.getHours();
            let minutes = date.getMinutes();
            let result = hours < 10 ? ("0" + hours) : hours + ":" + minutes < 10 ? ("0" + minutes) : minutes;
            return result;
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
        Event.$on(EDU_CONSTANT.listenerEduItem, (eduId, eduItem)=>{
            this.eduId = eduId;
            this.eduItem = eduItem;
            this.findRoomClientByEduId();
        })
    },
    mounted() {

    },
    updated: function () {
        this.$nextTick(() => {

        })
    },
    methods: {
        findRoomClientByEduId(){
            this.clientInfoList = [];
            this.clientInfo =  {
                itemCard: {},
                itemClient: {},
            };
            this.seatName = "";
            this.hasClientInfo = false;
            let param1 = {
                eduId: this.eduId,
                reserveStatus: "", //取空，表示全部
            };
            //查询已预约的会员数
            axiosGetParams(EDUCATION_URL.findToConfirmList, param1, (res) => {
                this.clientList = res;
                //获取教室
                axiosGetParams(EDUCATION_URL.getGroupRoomSeatById, {
                    roomId: this.eduItem.roomId
                }, (res) => {
                    //  console.log(res)
                    this.roomSeatItem = res;
                    this.roomSeatItem.seatNum = eval(this.roomSeatItem.seatNum);
                    this.seatNumCopy = eval(this.roomSeatItem.seatNum);

                });
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
            // clientInfo: {
            //     itemCard: {},
            //     itemClient: {},
            // },
            this.clientInfo.itemClient = item;
           // console.log(this.clientInfo)
            if(item['cards'].length == 1){

                this.clientInfo.itemCard = item['cards'][0];
                this.hasClientInfo = true;
                this.clientBoxStatus = false;
                this.cardBoxStatus = false;
                return false;
            }
            //现实第二个弹窗
            this.cardBoxStatus = true;
            this.cardInfoList = item['cards'];


        },
        //查询的会员选择会员
        selectCardObject(item){
            this.clientInfo.itemCard = item;
            this.hasClientInfo = true;
            //隐藏弹出框
            this.clientBoxStatus = false;
            this.cardBoxStatus = false;
            //console.log(this.clientInfo)
        },
        checkClientReserve(colNum){
            //判断这个位置是否被选择
            //fen 未选
            //fui 已选
            //hei 被占
            //判断是否选中
            //判断这个id在该用户集合中的状态
            let member = this.clientList.find((item)=>{
                return item.id == colNum;
            });
            if(!member){
               return 'fen';
            }
            //已预约
            if(member.reserveStatus == 1){
                return 'hui';
            }
            //待确认
            if(member.reserveStatus == 2){
                return 'hei';
            }
            return 'fen';
        },
        //fen 未选
        //fui 已选
        //hei 被占
        chooseSeat(trIndex, tdIndex, status, colNum){
            if(status == "hui" || status == 'hei'){
                return false;
            }
            if(!this.clientInfo.itemCard.cardNo){
                $.alert('请先选择会员卡信息');
                return false;
            }

            this.seatNumCopy = eval(JSON.stringify(this.roomSeatItem.seatNum));
            //
            this.seatName = (trIndex + 1) + "排" + (tdIndex + 1) + "列" + (trIndex * colNum + (tdIndex + 1)) + "号";
            this.seatNumCopy[trIndex][tdIndex] = "{replace}"
        },
        save(){

            if(this.isSave == true){
                return false;
            }
            //判断是否约满
            if(this.clientList.length == eduItem.reserveTotalNum){
                $.alert("该课程会员人数已约满")
                return false;
            }

            if(!this.eduId){
                $.alert("选择出错")
                return false;
            }
            if(!this.clientInfo.itemCard.id){
                $.alert("请选择会员卡")
                return false;
            }
            //roomSeatId
            if(!this.roomSeatItem.roomSeatId){
                $.alert("座位获取失败")
                return false;
            }
            //roomSeatId
            if(!this.seatName){
                $.alert("请选择座位")
                return false;
            }
            if(!this.clientInfo.itemClient.id){
                $.alert("请先选择预约的用户")
                return false;
            }

            this.isSave = true;
            //预约是否确认
            let reserveStatus = 1;
            if(this.eduItem.configReserveConfirm == true){
                reserveStatus = 2;
            }
            let param = {
                educationId: this.eduId,
                cardId: this.clientInfo.itemCard.id,
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
                memberCardNo: this.clientInfo.itemCard.cardNo,
                mobile: this.clientInfo.itemClient.mobile,
                deductionBalance: '',//后台判断是扣卡还是扣权益
                reserveDate: new Date().getTime(),
                createTime: new Date().getTime(),
                reserveStatus: reserveStatus,
                sex: this.clientInfo.itemClient.sex,
                isUse: 1,
                memberCardId: this.clientInfo.itemCard.id,
                seatNum: this.seatNumCopy,

            };

            //提交预约
            axiosPostParams(EDUCATION_URL.saveEducationItem, param, (res)=>{
                this.isSave = false;
                //this.findRoomClientByEduId();
                Event.$emit(EDU_CONSTANT.listenerListItemList);// 刷新首页的列表
                //刷新详情页
                Event.$emit(EDU_CONSTANT.listenerEduItem, this.eduId, this.eduItem)//刷新详情页
                $.alert("预约成功")
                newCustomers_maskLayer_X();//

            }, (res)=>{
                $.alert(res.msg)
                this.isSave = false;
            })
        }
    },
});