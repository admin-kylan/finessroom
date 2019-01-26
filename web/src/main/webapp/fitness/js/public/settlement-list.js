/**
 * by Kylan
 * @author
 * @date 2019-01-21
 */
Vue.component('edu-settlement-children', {//模版挂载的标签名
    template: '#edu-settlement-id',//id对应子组件的ID
    data() {
        return {
            searchInput: '', //输入
            beginDate: '',
            endDate: '',
            resultList: [],
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
        toDateyyyyMMdd(val){
            return timeFormatDate(val)
        },
    },
    computed: {},
    watch: {},
    created() {
        this.beginDate = getMonthStartDate()
        this.endDate = getMonthEndDate()
        this.search()
    },
    mounted() {
        let that = this;
        this.$nextTick(()=>{
            //上课时间
            jeDate('#settlement-begin-date',{
              //  isinitVal: true,
                festival: true,
                format: 'YYYY-MM-DD',
                donefun(obj){
                    that.beginDate = obj.val;
                }
            });
            //上课时间
            jeDate('#settlement-end-date',{
                //isinitVal: true,
                festival: true,
                format: 'YYYY-MM-DD',
                donefun(obj){
                    that.endDate = obj.val;
                }
            });
        })
    },
    updated: function () {
    },
    methods: {
        search(){
            let param = {
                shopId: $.cookie('shopid'),
                searchInput: this.searchInput,
                beginDate: this.beginDate,
                endDate: this.endDate,
                code: $.cookie('code'),
                eduType: 0
            };
            axiosGetParams(EDUCATION_URL.searchSettlement, param, (res)=>{
                this.resultList = res
            })
        },
        printMemberOrder(){

        },
        cancelMemberOrder(item){
            let param = {id: item.id,eduClassSalesNum: item.classSalesNum};
            //冲销用的
            let buyEduDetail = {
                cardId: '', //会员卡ID
                clientId: '', //客户ID
                cardType: '1', //卡种类型（1：时间卡 2：小时卡 3：次卡 4：储值卡 5：充值卡 6：折扣卡）
                orderStatus: '1', //支付状态（0：支出 1：收入）
                orderPrice: 0,//金额（注意正负 支出就是  -0.00）
                orderRightsNum: 0, //权益 注意正负
                shopId: item.shopId, //基础表门店ID
                personnelId: $.cookie('id'), //操作员工id
                flag: '权益', //消费内容
                type: 1, //支付类型（1:权益 2:储值 3:其他消费）
                CustomerCode: $.cookie('code'),
                status: 0,//财务审核 （0，待审核1，已审核，2，审核不通过）
                auditStatus: 0,//相关复核 （0，待审核1，已审核，2，审核不通过）
                orderType: 1,//订单类型（1、新购；2、续卡；3、转卡；4、补卡（暂无）；5、储值；6、补余；7、退卡, 8、停卡，9，转让，10，卡升级【若有其他的，类型数字请往后添加】）
                orderAmt: 0, //剩余权益\剩余储值金额
                createUserId: $.cookie('id'),
                createUserName: $.cookie('name'),
                createTime: new Date(),
            };
            param.buyEduDetail = buyEduDetail;
            let message = confirm("确认冲销该条记录？")
            if(message == true){
                axiosPostParams(EDUCATION_URL.cancelMemberOrder, param, ()=>{
                    this.search();
                    $.alert("冲销成功")
                },(res)=>{
                    $.alert(res.msg)
                })
            }
        }
    },
});