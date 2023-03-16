import myAxios from '../https';
import qs from 'qs';

// import qs from '@types/qs';


export function enrollAPI(paramsList: any) {
    return myAxios({
            url: '/system/user/enroll',
            method: 'post',
            data: paramsList,
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            transformRequest: [
                (data: any) => {
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
export default enrollAPI;