/**
 * by Kylan
 * @author
 * @date 2019-01-18
 */
Vue.component('edu-coach-children', {//模版挂载的标签名
    template: '#edu-coach-id',//id对应子组件的ID
    data() {
        return {
            coachList: [],
            roomList: [],
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
        reserveStatusFilter(val){
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
    watch: {},
    created() {
        //初始化
        this.load();
        Event.$off(EDU_CONSTANT.listenerRestCoachRoomInfo)
        Event.$on(EDU_CONSTANT.listenerRestCoachRoomInfo, ()=>{
            this.load();
        })
    },
    mounted() {

    },
    updated: function () {

    },
    methods: {
        load(){
            let code = $.cookie('code'), shopId = $.cookie('shopid');
            let param = {
                shopId: shopId,
                CustomerCode: code,
                executeDate: new Date(),
                eduType: 0,
            };
            //加载教练
            axiosGetParams(EDUCATION_URL.findCoachEdu, param, (res)=>{
                //console.log(res)
                this.transferCoachList(res)
            });
            //加载教室
            axiosGetParams(EDUCATION_URL.findRoomEdu, param, (res)=>{
                //console.log(res)
                this.transferRoomList(res)
            });

        },
        transferRoomList(list){
           // console.log(list)
            //onclick="classroom()"
            let array = [];
            $.each(list, (i, d)=>{//查询出来的数据

                let flag = array.find((item)=>{
                    return item.roomId == d.eduRoomId;
                });

                if(!flag){//不存在
                    let obj = {
                        roomId: d.roomId,
                        roomName: d.roomName,
                        seatNum: d.seatNum,
                        colNum: d.colNum,
                        rowNum: d.rowNum,
                        shopId: d.shopId,
                        shopName: d.shopName,
                        sdaduimId: d.sdaduimId,
                        sdaduimName: d.sdaduimName,
                        list: [],
                    };
                    if(d.eduId){
                        obj.list.push(d);
                    }
                    array.push(obj)
                }else{
                    flag.list.push(d)
                }
            });
            this.roomList = array;
            //console.log(this.roomList)
        },
        transferCoachList(list){
            // let a = {
            //     id:'',//coachId
            //     list: [],//eduList
            // }
            let array = [];
            $.each(list, (i, d)=>{//查询出来的数据

                let flag = array.find((item)=>{
                    return item.persionCoachId == d.coachId;
                });

                if(!flag){//不存在
                    let obj = {
                        persionCoachId: d.persionCoachId,
                        persionCoachName: d.persionCoachName,
                        persionCoachMobile: d.persionCoachMobile,
                        persionCoachSex: d.persionCoachSex,
                        persionCoachJobNumber: d.persionCoachJobNumber,
                        list: [],
                    };
                    if(d.id){
                        obj.list.push(d);
                    }
                    array.push(obj)
                }else{
                    flag.list.push(d)
                }
            });
            this.coachList = array;
          //  console.log(this.coachList)
        },
        showCoachInfo(eduId, eduItem){
            Event.$emit(EDU_CONSTANT.listenerShowCoachInfo, eduId, eduItem);
            coachInformation();
        },
        //排课
        arrangementEdu(item){
            Event.$emit(EDU_CONSTANT.listenerArrangementInfo, item)
            classroom();
        }
    },
});