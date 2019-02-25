/**
 * by Kylan
 * @author
 * @date 2019-01-22
 */
Vue.component('edu-freeze-detail-children', {//模版挂载的标签名
    template: '#edu-freeze-detail-id',//id对应子组件的ID
    data() {
        return {
            resultList: [],
            beginDate: '',
            endDate: '',
            searchInput: '',

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
        toMoney(val){
            if(!val){
                return '0.00';
            }
            return parseFloat(val).toFixed(2);
        },
    },
    computed: {},
    watch: {},
    created() {

    },
    mounted() {
        let that = this;
        //上课时间
        jeDate('#freeze-begin-date',{
            //  isinitVal: true,
            festival: true,
            format: 'YYYY-MM-DD',
            donefun(obj, val){
                that.beginDate = obj.val;
            }
        });
        //上课时间
        jeDate('#freeze-end-date',{
            // isinitVal: true,
            festival: true,
            format: 'YYYY-MM-DD',
            donefun(obj, val){
                that.endDate = obj.val;
            }
        });
        this.search();
    },
    updated: function () {
    },
    methods: {
        search(){
            this.load();
        },
        load(){
            let param = {
                code: $.cookie('code'),
                shopId: $.cookie('shopid'),
                beginDate: this.beginDate,
                endDate: this.endDate,
                searchInput: this.searchInput,
                eduType: 1
            };
            axiosGetParams(EDUCATION_URL.findFrEducationFreezeClient, param, (res)=>{
                this.resultList = res;
            })
        },
        deleteFreeze(item, index){
            let conf = confirm("确认解冻该用户！！！");
            if(conf == true){
                axiosPostParams(EDUCATION_URL.deleteFreezeClient, {id: item.id}, ()=>{
                    this.resultList.splice(index, 1);
                    $.alert('解冻成功！')
                },()=>{
                    $.alert('解冻失败！')
                })
            }

        }
    },
});