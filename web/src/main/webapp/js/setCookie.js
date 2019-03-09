//域名(ip+端口)
//const domain = window.location.host;
//const domain = 'www.4006337366.com:8080' || window.location.host;
const domain = window.location.host;
/**
 * 联登捷径接口服务器地址
 */
$.cookie('liandengUrl','http://huangxd1122.iask.in:17221/api/', {expires:7, path: '/'});
$.cookie("id", '49a744851b3d80e0', { path: '/'});//登录者ID
$.cookie("name", '欧解决', { path: '/'});//登录者姓名
$.cookie("token", 'test_token', { path: '/'});//token
$.cookie("shopid", '11kjhuni85d9f6ew', { path: '/'});//门店id
$.cookie("code", '003', { path: '/'});//客户代码
$.cookie("url", 'http://'+domain+'/', { path: '/'});//请求路径
$.cookie("mac", '', { path: '/'});//mac地址
$.cookie("ip", IPInfo());//ip地址
$.cookie("imgPath", '/files/', {path:  '/'});//图片路径

/**
 * 获取ip地址
 * @returns {string}
 * @constructor
 */
function IPInfo(){
    var ip = '';
    jQuery.getScript(
        "http://pv.sohu.com/cityjson?ie=utf-8",function(data){
            ip = returnCitySN["cip"];
            return ip;
        });
    return ip

}