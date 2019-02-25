/**
 * Created by Kylan on 2018/12/16.
 */
const EDUCATION_URL = {
    getOrderNo: 'frCardOrderInfo/getOrderNo', //获取订单编号
    findCoachList: '/frEducation/findCoachList', //获取教练数组
    findSdaduimList: '/frEducation/findSdaduimList', //获取场馆数组
    findEducationList: '/frEducation/findEducationList', //获取数组
    findToConfirmList: '/frEducation/findMemberReserveStatusList', //获取指定教学下的待确认会员
    changeEduClientStatus: '/frEducation/changeEduClientStatus', //更改状态
    findEducationById: '/frEducation/findEducationById', //根据ID 查询
    getClientList: '/frEducation/getClientList', //根据号码，名字模糊查询会员
    getGroupRoomSeatById: '/frEducation/getGroupRoomSeatById', //根据roomId 查询
    getGroupRoomByShopId: '/frEducation/getGroupRoomByShopId', //根据roomId 查询
    saveEducationItem: '/frEducation/saveEducationItem', //提交预约
    findMemberOrderListByEduId: '/frEducation/findMemberOrderListByEduId', //查询已经付钱过的用户，或者全部用户
    findMemberCardByInput: '/frEducation/findMemberCardByInput', //根据卡号，手机号，票券，团购号查询卡号
    findShowTrainingPlan: '/frEducation/findShowTrainingPlan', //查看训练计划
    findCourseById: '/frEducation/findCourseById', //查看课程id
    findSettCourseEdu: '/frEducation/findSettCourseEdu', //查看排课配置的课程
    findCourseByClientId: '/frEducation/findCourseByClientId', //查看该用户是否买了该课程
    updateProjectOrder: '/frEducation/updateProjectOrder', //保存修改对课程的修改
    updateCardDetail: '/frEducation/updateCardDetail', //更新卡的权益
    saveEduMemberOrder: '/frEducation/saveEduMemberOrder', //保存会员结算订单
    startEduClass: '/frEducation/startEduClass', //开始上课
    cancelMemberOrder: '/frEducation/cancelMemberOrder', //冲销
    //index = 1
    findCoachEdu: '/frEducation/findEducationCoachList', //查询教练
    findRoomEdu: '/frEducation/findEducationRoomList', //查询教室
    findCourseList: '/frEducation/findCourseList', //查看课程list
   // findShopSdaduimCourseList: '/frEducation/findShopSdaduimCourseList', //查看门店，场馆，团教私教课程list
    saveEducation: '/frEducation/saveEducation', //排课
    searchSettlement: '/frEducation/searchSettlement', //查询结账情况
    //消费情况
    findConsumeClass: '/frEducation/findConsumeClass', //查询课程消费明细情况
    findConsumeMember: '/frEducation/findConsumeMember', //查询个人消费情况
    findConsumeCondition: '/frEducation/findConsumeCondition', //查询个人消费情况
    //查询冻结会员
    findMemberFreezeList: '/frEducation/findMemberFreezeList', //查询冻结会员


    //查询管理
    findManageCount: '/frEducation/findManageCount', //查询管理统计
    marketShop: '/shop/getMarketShopList', //获取门店
    //复制课程
    copyEducation: '/frEducation/copyEducation',//复制课程
    //团课排课查询 月/周
    findEduListByWeek: 'frEducation/findEduListByWeek',
    findEduListByMonth: 'frEducation/findEduListByMonth',
    //删除课程
    deleteEdu: 'frEducation/deleteEdu',
    //复制课程
    copyEducationByEduId: '/frEducation/copyEducationByEduId',//复制课程
    findAllCourseForShopSdaduim: '/frEducation/findAllCourseForShopSdaduim',//查询门店下的私教团教的课程
    findFrEducationFreezeClient: '/frEducation/findFrEducationFreezeClient',//查询冻结用户
    deleteFreezeClient: '/frEducation/deleteFreezeClient',//删除冻结用户 解冻
    findEduCationInfoById: '/frEducation/findEduCationInfoById',//查询课程信息
    updateEducationInfo: '/frEducation/updateEducationInfo',//更新课程
    findEduDateByEduId: '/frEducation/findEduDateByEduId',//查询这一周的私教课程时间
};

const ADMISSION_URl = {
    versionClient: '/accessInfo/versionClient', //验证会员
    saveAccessInfo: '/accessInfo/saveAccessInfo',
    loadAccessInfoList: '/accessInfo/loadAccessInfoList',
    cancelAccessInfo: '/accessInfo/cancelAccessInfo',
    outputVerification: '/accessInfo/outputVerification', //出场验证
    loadOutputInfoList: '/accessInfo/loadOutputInfoList', //出场查询
    changeHandNum: '/accessInfo/changeHandNum', //出场查询
    calcNum: '/accessInfo/calcNum',
    loadCardList: '/accessInfo/loadCardList', //查询卡片list
    loadCardListOfCardNo: '/accessInfo/loadCardListOfCardNo', //查询卡片list
    loadInfo: '/accessInfo/loadInfo',
}