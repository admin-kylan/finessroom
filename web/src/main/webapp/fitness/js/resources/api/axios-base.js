/**
 * Created by Kylan on 2018/12/15.
 */

var baseUrl = $.cookie('url');
/**
 * axios 传参数的 回调
 * @param url
 * @param params
 * @param success
 * @param error
 */
function axiosGetParams(url, params, success, error) {
    axios.get(baseUrl + url, {params: params})
        .then(function (response) {
            if (success) {
                let code = response.data.code;
                if(code === "200"){
                    success(response.data.data);
                }else{
                    if (error) {
                        error(response.data);
                    }
                }

            }

        })
        .catch(function (response) {
            if (error) {
                error(response);
            }
        });
}

/**
 * axios get 没有参数 回调
 * @param url
 * @param success
 * @param error
 */
function axiosGetFetch(url, success, error) {
    axios.get(baseUrl + url)
        .then(function (response) {
            if (success) {
                let code = response.data.code;
                if(code === "200"){
                    success(response.data.data);
                }else{
                    if (error) {
                        error(response.data);
                    }
                }

            }
        })
        .catch(function (response) {
            if (error) {
                error(response);
            }
        });
}

/**
 * axios post 传参数 回调
 * @param url
 * @param params
 * @param success
 * @param error
 */
function axiosPostParams(url, params, success, error) {
    var p = {};
    if (params) {
        p = params;
    }
    axios.post(baseUrl + url, p)
        .then(function (response) {
            if (success) {
                let code = response.data.code;
                if(code === "200"){
                    success(response.data.data);
                }else{
                    if (error) {
                        error(response.data);
                    }
                }

            }
        })
        .catch(function (response) {
            if (error) {
                error(response);
            }
        });
}

/**
 * axios post 传参数 回调
 * @param url
 * @param params
 * @param success
 * @param error
 */
function jqueryPostParams(url, params, success, error) {
    var p = {};
    if (params) {
        p = params;
    }
    $.ajax({
        type: "post",
        url: baseUrl + url,
        data: p,
        success: (response) => {
            if (success) {
                let code = response.code;
                if(code === "200"){
                    success(response.data);
                }else{
                    if (error) {
                        error(response.data);
                    }
                }

            }

        },
        error: (response) =>{
            if (error) {
                error(response);
            }
        }
    });
}


/**
 * axios post 传参数 回调
 * @param url
 * @param params
 * @param success
 * @param error
 */
function ajaxPostJsonParams(url, params, success, error) {
    var p = {};
    if (params) {
        p = params;
    }
    $.ajax({
        type: "post",
        url: baseUrl + url,
        data: p,
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        success: (response) => {
            if (success) {
                let code = response.code;
                if(code === "200"){
                    success(response.data);
                }else{
                    if (error) {
                        error(response.data);
                    }
                }

            }

        },
        error: (response) =>{
            if (error) {
                error(response);
            }
        }
    });
}

