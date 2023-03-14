import myAxios from '../http';
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
    });
}export function enrollAPI(paramsList) {
    return myAxios({
        url: '/system/user/enroll',
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
    });
}