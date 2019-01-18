/**
 * by Kylan
 * @author
 * @date 2019-01-09
 */
Vue.component('edu-book-detail-children', {//模版挂载的标签名
    template: '#book-detail-id',//id对应子组件的ID
    props:['eduId'],
    data() {
        return {
            eduClientInfoList: [],
            toConfirmCheckbox: [], //选择 id
            isCheckedAll: false,
            nowMember: 0,
            unNowMember: 0,

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
        toDateyyyyMMddHHmm(val){
            return timeFormatDate(val, true)
        },
        reserveStatusFilter(val) {
            let str = "";
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
        remarksFilter(val){
            if(!val){
                return "无";
            }
            return val;
        }

    },
    computed: {},
    watch: {
        eduId(val){
            //课程
            axiosGetParams(EDUCATION_URL.findEducationById, {eduId: val}, (res) => {
                this.eduItem = res;
                this.loadClientInfo();
            });

        }
    },
    created() {

    },
    mounted() {

    },
    methods: {
        loadClientInfo(){
            //设置100毫秒后执行查询
            setTimeout((eduId)=>{
                let param1 = {
                    eduId: eduId,
                    reserveStatus: "", //取空，表示全部
                };
                this.eduClientInfoList = [];
                this.nowMember = 0;
                this.unNowMember = 0,
                axiosGetParams(EDUCATION_URL.findToConfirmList, param1, (res) => {

                    this.eduClientInfoList = res;
                    //设计人数
                    $.each(this.eduClientInfoList, (i, d)=>{
                        if(d.memberType == "现有客户"){
                            this.nowMember += 1
                        }else{
                            this.unNowMember += 1
                        }
                    })
                })
            },100, this.eduId)
        },
        returnPage(){
            this.$emit('return-page',0);
        },
        //选择框
        checkedOne(clientId){
            let idIndex = this.toConfirmCheckbox.indexOf(clientId)
            if (idIndex >= 0) {
                // 如果已经包含了该id, 则去除(单选按钮由选中变为非选中状态)
                this.toConfirmCheckbox.splice(idIndex, 1)
            } else {
                // 选中该checkbox
                this.toConfirmCheckbox.push(clientId)
            }
        },
        checkedAll(){
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
        changeStatusAll(status){
            this.toConfirmCheckbox.forEach((clientInfoId) =>{
                this.changeStatus(clientInfoId, status, "")
            })
        },
        //确定 //取消
        changeStatus(id, status, index){
            let param = {
                clientInfoId: id,
                status: 0
            }
            if(status == 1){
                param.status = 1
            }
            axiosPostParams(EDUCATION_URL.changeEduClientStatus, param, (res) => {
                //修改成功后，修改状态
                this.loadClientInfo();
            })
        },
    },
});