/**
 * by Kylan
 * @author
 * @date 2019-01-23
 */
Vue.component('edu-course-manage-children', {//模版挂载的标签名
    template: '#edu-course-manage-id',//id对应子组件的ID
    data() {
        return {
            shopList: [],
            selectShopId: '',
            beginDate: '',
            endDate: '',
            resultObject: {}
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
    },
    computed: {},
    watch: {
        selectShopId(){
            this.load();
        },
        beginDate(){
            this.load();
        },
        endDate(){
            this.load();
        }
    },
    created() {
        this.shopList = []; //清空门店
        let obj = {};
        axiosGetParams(EDUCATION_URL.marketShop, {type: 0}, (res) => {
            $.each(res.data, (i, d) => {
                obj.shopName = d.shopName;
                obj.id = d.id;
                this.shopList.push(obj);
                obj = {};
            })
        });
        this.beginDate = getMonthStartDate()
        this.endDate = getMonthEndDate()
        this.load();
    },
    mounted() {
        let that = this;
        //上课时间
        jeDate('#manage-begin-date',{
            //  isinitVal: true,
            festival: true,
            format: 'YYYY-MM-DD',
            donefun(obj, val){
                that.beginDate = obj.val;
            }
        });
        //上课时间
        jeDate('#manage-end-date',{
            // isinitVal: true,
            festival: true,
            format: 'YYYY-MM-DD',
            donefun(obj, val){
                that.endDate = obj.val;
            }
        });

    },
    updated: function () {
    },
    methods: {
        load(){

            let code = $.cookie('code'), shopId = $.cookie('shopid');
            let param = {
                shopId: this.selectShopId,
                beginDate: this.beginDate,
                endDate: this.endDate,
            };
            axiosGetParams(EDUCATION_URL.findManageCount, param, (res)=>{
                this.resultObject = res
            })
        }
    },
});