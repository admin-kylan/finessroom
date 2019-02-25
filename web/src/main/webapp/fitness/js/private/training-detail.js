/**
 * by Kylan
 * @author
 * @date 2019-01-22
 */
Vue.component('edu-training-detail-children', {//模版挂载的标签名
    template: '#edu-training-detail-id',//id对应子组件的ID
    data() {
        return {
            isShow: false,
            eduItem: {
                executeDatePlan:'',
                programBetter: ''
            },
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


    },
    mounted() {
        this.$nextTick(()=>{
            Event.$off(EDU_CONSTANT.listenerClassMemberInfo)
            Event.$on(EDU_CONSTANT.listenerClassMemberInfo, (item)=>{
                this.isShow = true;
                this.eduItem = item;
            })
        })
    },

    methods: {},
});