import myAxios from '../https';
import qs from 'qs';
import {getTokenAUTH} from "@/utils/auth";


export function selectUserSurveyAPI(paramsList: any) {
    return myAxios({
            url: '/system/survey/selectUserSurvey',
            method: 'get',
            params:paramsList,
            headers: {
                'token': getTokenAUTH()
            },
            transformRequest: [
                (data: string) => {
                    return qs.stringify(data)
                }
            ],
        }, {
            // 是否开启取消重复请求, 默认为 false
            repeat_request_cancel: false,
            // 是否开启接口错误信息展示，默认为true
            error_message_show: true
        }
    );
}
export default selectUserSurveyAPI;