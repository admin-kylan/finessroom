/**
 * by Kylan
 * @author
 * @date 2019-01-09
 */
Vue.component('edu-book-detail-children', {//模版挂载的标签名
    template: '#book-detail-id',//id对应子组件的ID
    props: [],
    data() {
        return {

            eduId: '', //
            eduItem: {}, //
            eduClientInfoList: [],
            toConfirmCheckbox: [], //选择 id
            isCheckedAll: false,
            nowMember: 0,
            unNowMember: 0,
            reserveNum: 0,
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
        reserveStatusFilter(val) {
            let str = "已取消";
            if(val == 0){
                str = "已取消"
            }
            if(val == 1){
                str = "已预约"
            }
            if(val == 2){
                str = "待确认"
            }
            return str;
        },
        remarksFilter(val) {
            if (!val) {
                return "无";
            }
            return val;
        }

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
            this.loadClientInfo();
        })
    },
    mounted() {

    },
    methods: {
        startClass(){
            if(this.eduItem.status == 0){
                startClass();
            }
        },
        loadClientInfo() {
            //设置100毫秒后执行查询
            // setTimeout((eduId)=>{
            //
            // },100, this.eduId)
            this.eduClientInfoList = [];
            this.nowMember = 0;
            this.unNowMember = 0;
            this.reserveNum = 0;
            let param1 = {
                eduId: this.eduId,
                reserveStatus: "", //取空，表示全部
            };
            axiosGetParams(EDUCATION_URL.findToConfirmList, param1, (res) => {
                this.eduClientInfoList = res;
                //设计人数
                $.each(this.eduClientInfoList, (i, d) => {
                    if (d.memberType == "现有客户") {
                        this.nowMember += 1
                    } else {
                        this.unNowMember += 1
                    }
                    //预约人数
                    if(d.reserveStatus == 1 || d.reserveStatus == 2){
                        this.reserveNum += 1;
                    }

                })
            })
        },
        returnPage() {
            this.$emit('return-page', 0);
        },
        //选择框
        checkedOne(clientId) {
            let idIndex = this.toConfirmCheckbox.indexOf(clientId)
            if (idIndex >= 0) {
                // 如果已经包含了该id, 则去除(单选按钮由选中变为非选中状态)
                this.toConfirmCheckbox.splice(idIndex, 1)
            } else {
                // 选中该checkbox
                this.toConfirmCheckbox.push(clientId)
            }
        },
        checkedAll() {
            this.isCheckedAll = !this.isCheckedAll;
            if (this.isCheckedAll) {
                // 全选时
                this.toConfirmCheckbox = [];
                this.eduClientInfoList.forEach((item) => {
                    this.toConfirmCheckbox.push(item.id);
                })
            } else {
                this.toConfirmCheckbox = []
            }
        },
        changeStatusAll(status) {
            let num = this.toConfirmCheckbox.length;
            let ind = '';
            this.toConfirmCheckbox.forEach((clientInfoId, index) => {
                if(num == (index + 1)){
                    ind = 'true';
                }
                this.changeStatus(clientInfoId, status, ind)
            })
        },
        //确定 //取消
        changeStatus(id, status, index) {

            let param = {
                clientInfoId: id,
                status: 0
            }
            if (status == 1) {
                param.status = 1
            }
            axiosPostParams(EDUCATION_URL.changeEduClientStatus, param, (res) => {
                //修改成功后，修改状态
                if(index == 'true'){
                   // this.loadClientInfo();
                    Event.$emit(EDU_CONSTANT.listenerListItemList);// 刷新首页的列表
                    //刷新详情页
                    Event.$emit(EDU_CONSTANT.listenerEduItem, this.eduId, this.eduItem)//刷新详情页
                }

            },(res)=>{
               $.alert(res.msg)
            })
        },
        //添加预约，添加预约限制
        addReserve(){
            if(this.eduItem.status != 0){
                return false;
            }
            let eduId = this.eduId, eduItem = this.eduItem;
            //判断当前预约是否可以在pc端预约
            if(eduItem.configOnlineReserve == true){
                $.alert("该团教课程只能在app上预约")
                return false;
            }
            //判断当前是否可以预约，时间判断
            if((new Date().getTime() - eduItem.configReserveTime) < 0){
                let date = new Date(eduItem.configReserveTime);
                $.alert("该课程还未开放预约,请在" + timeFormatDate(date, true) + "后预约。");
                return false;
            }
          //  this.$emit("listener-bench-bessage", eduId, eduItem);
            //eduItem.eduCurrentCount + eduItem.eduToConfirmCount
            //判断是否约满
            if(this.reserveNum == eduItem.reserveTotalNum){
                $.alert("该课程会员人数已约满")
                return false;
            }
            // this.eduItem.eduCurrentCount = this.reserveNum;
            // this.eduItem.eduToConfirmCount = 0;
            Event.$emit(EDU_CONSTANT.listenerEduItem, eduId, eduItem)
            reservation();

        }
    },
});