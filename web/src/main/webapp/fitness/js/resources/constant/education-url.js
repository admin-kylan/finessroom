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
    saveEducationItem: '/frEducation/saveEducationItem', //根据roomId 查询
    findMemberOrderListByEduId: '/frEducation/findMemberOrderListByEduId', //查询已经付钱过的用户，或者全部用户
    findMemberCardByInput: '/frEducation/findMemberCardByInput', //根据卡号，手机号，票券，团购号查询卡号
    findShowTrainingPlan: '/frEducation/findShowTrainingPlan', //查看训练计划
    findCourseById: '/frEducation/findCourseById', //查看课程
    findSettCourseEdu: '/frEducation/findSettCourseEdu', //查看排课配置的课程
    findCourseByClientId: '/frEducation/findCourseByClientId', //查看该用户是否买了该课程
    updateProjectOrder: '/frEducation/updateProjectOrder', //保存修改对课程的修改
    updateCardDetail: '/frEducation/updateCardDetail', //更新卡的权益
    saveEduMemberOrder: '/frEducation/saveEduMemberOrder', //保存会员结算订单
    startEduClass: '/frEducation/startEduClass', //开始上课
    cancelMemberOrder: '/frEducation/cancelMemberOrder', //冲销
};