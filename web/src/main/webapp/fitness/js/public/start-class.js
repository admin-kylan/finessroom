/**
 * by Kylan
 * @author
 * @date 2019-01-12
 */
Vue.component('edu-start-class-children', {//模版挂载的标签名
    template: '#edu-start-class-id',//id对应子组件的ID
    data() {
        return {
            eduId: '',
            eduItem: {},
        }
    },
    filters: {},
    computed: {},
    watch: {},
    created() {
        Event.$on(EDU_CONSTANT.listenerEduItem, (eduId, eduItem)=>{
            this.eduId = eduId;
            this.eduItem = eduItem;

        })
    },
    mounted() {

    },
    updated: function () {
        this.nextTick(() => {

        })
    },
    methods: {},
});