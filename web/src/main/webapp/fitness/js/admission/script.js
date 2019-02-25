/**
 * by Kylan
 * @author
 * @date 2019-02-16
 */

var admissionManagement = new Vue({
    el: '#admissionManagement',
    data: {
        LiIndex: 0,
        item: {},
        code: $.cookie('code'),
        shopName: '',
        gymId: 'c7c5af32488caebb', // 健身馆id
        shopId: $.cookie('shopid'),
        inputSearch: '',//13645945925 //600000826
        password: '',
        outputSearch: '',
        countObject: {
            allCount: 0,
            nowCount: 0,
            outputCount: 0,
            manCount: 0,
            womenCount: 0,
            outputAllCount: 0,
        },
        searchInput: '',
        accessInfoList: [],
        accessInfoOutputList: [],
        inputHandNumCard: '', //弹出层输入的手牌
        clientInfoObject: {
            type: '',
            status: '',
            name: '--',
            sex: '--',
            birthday: '--',
            cardNo: '--',
            carSeries: '--',
            cardName: '--',
            totalInterests: '--',
            giftInterests: '--',
            endInterests: '--',
            cardBeginDate: '--',
            cardEndDate: '--',
            prevConsumDate: '--',
            intervalDate: '--',
            salePeople: '--',
            coachPeople: '--',
            serverPeople: '--',
            content: '--',
            Invalid: '--',
        },
        inputType: '', //入场类型
        inputHandNum: '', //输入手牌
        cardItemInfoList: [], //卡项目list
        selectCardItemInfoObj: {}
    },
    watch: {


    },
    filters: {
        toDateHHmm(val) {
            let date = new Date(val);
            let h = date.getHours();
            h = h < 10 ? ('0' + h) : h;
            let m = date.getMinutes();
            m = m < 10 ? ('0' + m) : m;
            let _time = "";
            _time += ' ' + h + ':' + m;
            return _time;
        },
        toDateyyyyMMddHHmm(val) {
            if(!val){
                return "";
            }
            return timeFormatDate(val, true)
        },
        toDateyyyyMMdd(val) {
            return timeFormatDate(val);
        },
        toDateyyyyMMddInfo(val) {

            if(!val || val == '--'){
                return "--";
            }
            return timeFormatDate(val);
        },
        remarksFilter(val){
            if(!val){
                return "无";
            }
            return val;
        },
        saleNum(val){
            if(val == true){
                return "-1"
            }
            return '1'
        },
        outputOption(val){
            if(val == 1){
                return "是"
            }
            return '否'
        },
        lessPriceOption(val){
            if(!val){
                return '0';
            }
            return val;
        }

    },
    created(){

        this.init();
    },
    mounted(){
        this.$nextTick(()=>{
            // $('.xm-modal').modal('show')
            // $('.y-pq-modal').modal('show')
            // $('.no-pq-modal').modal('show')
            // $('.pj-modal').modal('show')

            //修复高度
            $('body').on('click','.close',function(){
                $(this).parents('.modal').hide();
            })
            function mainheight() {
                var mainheight = $(window).height() - 50;
                $('.cWrap').height(mainheight);
                $('.content').height(mainheight);
                $(window).resize(function () {
                    mainheight = $(window).height() - 50;
                    $('.cWrap').height(mainheight);
                    $('.content').height(mainheight);
                })
            }
            mainheight();
        })
    },
    methods: {
        init(){
            //门店名称
            axiosGetParams(EDUCATION_URL.marketShop, {type: 0}, (res) => {
                $.each(res.data, (i, d) => {
                    if(this.shopId == d.id){
                        this.shopName = d.shopName
                    }
                })
            });
            this.loadInfoList();
            this.loadOutputInfoList();
            //查询 数量记录数
            axiosGetParams(ADMISSION_URl.calcNum, {}, (res) => {
                this.countObject = res;
            })
        },
        loadInfoList(){
            //查询记录
            axiosGetParams(ADMISSION_URl.loadAccessInfoList, {shopId: this.shopId, code: this.code, searchInput: this.searchInput}, (res) => {
                this.accessInfoList = res;
            })
        },
        loadOutputInfoList(){
            //查询 已经出场记录
            axiosGetParams(ADMISSION_URl.loadOutputInfoList, {shopId: this.shopId, code: this.code, searchInput: this.searchInput}, (res) => {
                this.accessInfoOutputList = res;
            })
        },
        showDialog(classStatus, status){
            let className = '';
            switch (classStatus) {
                case 1:
                    className = '.xm-modal';
                    break;
                case 2:
                    className = '.y-pq-modal';
                    break;
                case 3:
                    className = '.no-pq-modal';
                    break;
                case 4:
                    className = '.pj-modal';
                    break;
                case 5:
                    className = '.pj-modal-1';
                    break;
            }
            if(status === true){
                $(className).modal('show')
                return false
            }
            $(className).modal('hide')
        },
        changeNum(item){
            this.item = item;
            this.showDialog(5, true)

        },
        saveChangeHandNum(){

            if(this.item.handNum == this.inputHandNum){
                this.inputHandNum = "";
                alert("更换手牌错误，手牌更换前后必须不一样")
                return false
            }
            //更换手牌
            axiosPostParams(ADMISSION_URl.changeHandNum, {id: this.item.id, num: this.inputHandNum}, (res) => {
                this.showDialog(5);
                this.inputHandNum = "";
                alert('更换手牌成功！！！')
                this.loadInfoList();
            },(res)=>{
                alert(res.msg)
            })
        },
        cancelAccessInfo(item){
            let conf = confirm("确认冲销该数据！！！");
            if(conf == true){
                //冲销
                axiosPostParams(ADMISSION_URl.cancelAccessInfo, {id: item.id}, (res) => {
                    alert('冲销成功！！！')
                    this.loadInfoList();
                },(res)=>{
                    alert(res.msg)
                })
            }

        },
        outputMethod(){
            let param = {
                shopId: this.shopId,
                code: this.code,
                outputSearch: this.outputSearch,
                createId: $.cookie('id'),
                createName: $.cookie('name'),
            }
            //出场验证
            axiosPostParams(ADMISSION_URl.outputVerification, param, (res) => {
                alert("操作成功！！！")
                this.loadOutputInfoList();
            },(res)=>{
                alert(res.msg)
            })
        },
        inputMethod(){
            //.xm-modal
            //.y-pq-modal
            //.no-pq-modal
            //.pj-modal
            //刷卡入场/刷手机号入场 /刷票券入场
            if(!this.inputSearch){
                return false;
            }
            if(this.inputSearch.length == 11){
                this.inputType = 2
                //手机
                this.showDialog(4, true)
                return false
            }
            //否则查询卡号
            //查询不到就查询票券
            //console.log(this.inputSearch)
           // this.showDialog(1, true)
            this.saveToCardOption();
        },
        saveToCardOption(){
            let param = {
                code: this.code,
                shopId: this.shopId,
                cardNo: this.inputSearch,
            }
            //根据卡号查询
            axiosGetParams(ADMISSION_URl.loadCardListOfCardNo, param, (res) => {
                //验证通过, 则查询手机号对应的卡号集合
                console.log(res)
               /* if(!res || res.length == 0){
                    //查询票券入口
                    this.loadTicketList();
                    return false;
                }*/
                this.inputType = 1;
                this.cardItemInfoList = res;
                this.showDialog(1, true)
                this.selectCardItemInfoObj = {}; //清空数据

            })
        },
        selectItemFunc(obj, item){
            this.selectCardItemInfoObj = {
                obj: obj,
                item: item,
            }
        },
        inputTypeOption(){
            let name = "";
            switch (this.inputType) {
                case 1:
                    name = '刷卡入场';
                    break;
                case 2:
                    name = '刷手机号入场';
                    break;
                case 3:
                    name = '刷票券入场';
                    break;
                default:
                    name = "";
            }
            return name;
        },
        //查询信息 保存数据
        saveAccessInfo(){
            //判断是否有选择
            if(!this.selectCardItemInfoObj.item){
                alert("请选择项目")
                return false;
            }
            let now = new Date();
            let obj = this.selectCardItemInfoObj.obj;
            let item = this.selectCardItemInfoObj.item;
            this.loadClientInfo(item.cardNo, item.mobile)
            let accessInfo = {
                clientName: item.clientName,
                clientId: item.clientId,
                saleUseName: item.salesName,
                type: this.inputTypeOption(),
                saleProject: obj.itemName,
                saleProjectId: obj.itemId,
                usePeopleName: item.clientName,
                usePeopleId: item.clientId,
                status: 0,
                createDate: now,
                createUserName: $.cookie('name'),
                createUserId: $.cookie('id'),
                sex: item.sex,
                handNum: this.inputHandNumCard,
                isDone: 0,
                account: this.inputSearch,
                lessPrice: 0,
                shopId: item.shopId,
                shopName: item.shopName,
                isUse: 1,
                CustomerCode: $.cookie('code')

            };
            let orderObj = {
                cardId: item.cardId,
                mobile: item.mobile
            }
            let param = {
                accessInfo: accessInfo,
                orderObj: orderObj
            };
            this.selectCardItemInfoObj = {};
            this.showDialog(1);
            console.log(accessInfo)

            axiosPostParams(ADMISSION_URl.saveAccessInfo, param, (res) => {
                this.loadInfoList();
            })
        },
        loadClientInfo(cardNo, mobile){
            let param = {
                code: this.code,
                shopId: this.shopId,
                cardNo: cardNo,
                mobile: mobile,
            };
            axiosGetParams(ADMISSION_URl.loadInfo, param, (res) => {
                this.clientInfoObject = {
                    type: this.inputTypeOption(),
                    status: '正常',
                    name: res.clientName,
                    sex: res.sex,
                    birthday: res.birthday,
                    cardNo: res.cardNo,
                    carSeries: res.cardType,
                    cardName: res.cardTypeName,
                    totalInterests: res.haveRightsNum,
                    giftInterests: res.giftRightNum,
                    endInterests: res.totalAmt,
                    cardBeginDate: res.bindDate,
                    cardEndDate: res.invalidDate,
                    prevConsumDate: res.prevDate,
                    intervalDate: '--',
                    salePeople: res.salesName,
                    coachPeople: res.coachName,
                    serverPeople: res.consultantName,
                    content: '--',
                    Invalid: '--',
                };
                if(this.clientInfoObject.prevConsumDate){
                    this.clientInfoObject.intervalDate = getTwoBetweenDates(timeFormatDate(new Date()), timeFormatDate(new Date(this.clientInfoObject.prevConsumDate)));
                }
               // console.log(this.clientInfoObject)
            });
        },
        loadTicketList(){

        },
        saveTelOption(){
            let data = {
                password: this.password,
                code: this.code,
                shopId: this.shopId,
                mobile: this.inputSearch,
            }
            //保存
            axiosPostParams(ADMISSION_URl.versionClient, data, (res) => {
                //验证通过, 则查询手机号对应的卡号集合
                let param = {
                    code: this.code,
                    shopId: this.shopId,
                    mobile: this.inputSearch,
                }
                //根据卡号查询
                axiosGetParams(ADMISSION_URl.loadCardList, param, (res) => {
                    console.log(res)
                    this.showDialog(4)
                    this.inputType = 2;
                    this.cardItemInfoList = res;
                    this.showDialog(1, true)
                    this.selectCardItemInfoObj = {}; //清空数据

                })
            },(res)=>{
                console.log(res)
                alert(res.msg)
            })
            //验证成功后，执行查询


        }


    }
});

