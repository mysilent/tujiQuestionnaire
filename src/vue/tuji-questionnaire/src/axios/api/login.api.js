import myAxios from '../http';
// import myAxios from "../api/request";
import qs from "qs";

export function loginAPI(paramsList) {
    return myAxios({
            url: '/system/user/login',
            method: 'post',
            data: paramsList,
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            transformRequest: [
                (data) => {
                    return qs.stringify(data)
                }
            ],
        }, {
            // 是否开启取消重复请求, 默认为 false
            repeat_request_cancel: true,
            // 是否开启接口错误信息展示，默认为true
            error_message_show: true
        }
    );
}
