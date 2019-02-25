/**
 * by Kylan
 * @author
 * @date 2019-01-18
 */
Vue.component('edu-coach-information-children', {//模版挂载的标签名
    template: '#edu-coach-information-id',//id对应子组件的ID
    data() {
        return {
            eduId: '',
            eduItem: '',
        }
    },
    filters: {

    },
    computed: {},
    watch: {},
    created() {
        //初始化
        Event.$off(EDU_CONSTANT.listenerShowCoachInfo);
        Event.$on(EDU_CONSTANT.listenerShowCoachInfo, (eduId, eduItem)=>{
            this.eduId = eduId;
            this.eduItem = eduItem;
        })
    },
    mounted() {

    },
    updated: function () {

    },
    methods: {
        LI(index, index2){
            //this.$parent.LI(index, index2)
            this.$emit('return-page', index, index2);
            setTimeout(()=>{
                if(this.eduId){
                    Event.$emit(EDU_CONSTANT.listenerEduItem, this.eduId, this.eduItem)
                }
            },51)
        }
    },
});