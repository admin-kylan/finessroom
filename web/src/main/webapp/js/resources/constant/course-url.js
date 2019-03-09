/**
 * Created by Kylan on 2018/12/16.
 */
const COURSE_URL = {
    marketShop: 'shop/getMarketShopList', //获取门店
    checkAgreement: 'frAgreement/checkCardAgreement', //检查协议编号
    addAgreement: 'frAgreement/addCardAgreement', //检查协议编号
    marketUserList: 'personnelInfo/getMarketUserList', //获取销售人员
    fetchSdaduim: 'shop/ShopSdaduimList', //选择场馆
    fetchTechnician: 'frCustomerCourse/getMarketUserList', // 获取技师，助教
    fetchUserInfo: 'frClient/getClient', //获取用户信息，根据用户Id
    getOrderNo: 'frCardOrderInfo/getOrderNo', //获取订单编号
    verifyPersonInfo: 'personnelInfo/getVerification', //用户授权验证
    verifyCard: 'frCardLimit/getVerification', //客户授权验证
    getClientListByMobile: 'frClient/getClientList', // 通过号码查询用户信息
    getCardListByMobile: 'frCard/queryByFrCardListByStatus', // 通过客户，查询会员卡
    queryByFrCardList: 'frCard/queryByFrCardList', // 通过客户，查询会员卡
    fetchOrderListByUserId: 'frCustomerCourse/fetchOrderListByUserId', //根据用户Id查询
    addSaveCustomer: 'frCustomerCourse/addSaveCustomer', //新购的结账
    customerStar: 'frCustomerCourse/customerStar', //项目启用
    customerRemnant: 'frCustomerCourse/customerRemnant', //项目补余
    customerExtension: 'frCustomerCourse/customerExtension', //项目延期
    customerTransfer: 'frCustomerCourse/customerTransfer', //项目转让
    customerReturnMoney: 'frCustomerCourse/customerReturnMoney', //退费操作
    returnAddProjects: 'frCustomerCourse/returnAddProjects', //项目查询退费
    turnProjects: 'frCustomerCourse/turnProjects', //查询转让记录
    turnProjectsDelete: 'frCustomerCourse/turnProjectsDelete', //冲销
    getCourseList: '/frCustomerCourse/getCourseList', //私教,团教
  //  frGroupCourseList: '/frCustomerCourse/list', //团教
    customerCommodityList: 'frCustomerCourse/list', //项目list
    getMarketShopList: 'shop/getMarketShopList', //查询所有门店
    getProjectListSelect: 'frCustomerCourse/getProjectListSelect', //查询选择框列表
    findStartProjectRecord: 'frCustomerCourse/findStartProjectRecord', //查询启用记录
    findCustomerRemnantRecord: 'frCustomerCourse/findCustomerRemnantRecord', //查询补余记录
    findCustomerExtensionRecord: 'frCustomerCourse/findCustomerExtensionRecord', //查询延期记录
    getCoursePackageCourseId: 'frCustomerCourse/getCoursePackageCourseId', //查询延期记录
    findPostTicketList: 'eduTicket/findPostTicketList', // 查询票券
};