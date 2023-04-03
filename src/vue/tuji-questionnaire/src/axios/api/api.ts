// 导入封装好的axios实例
import request from '../https'
import qs from "qs";

export const http = {
    /**
     * methods: 请求
     * @param url 请求地址
     * @param params 请求参数
     */
    get(url: any, params: any) {
        const config = {
            method: 'get',
            url: url,
            params:params,
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            transformRequest: [
                (data :any) => {
                    return qs.stringify(data)
                }
            ],
        }
        const customOptions: any = {
            // 是否开启取消重复请求, 默认为 false
            repeat_request_cancel: true,
            // 是否开启接口错误信息展示，默认为true
            error_message_show: true
        }
        //调用封装好的axios实例，下文同理
        return request(config, customOptions)
    },
    post(url: any, params: any) {
        const config = {
            method: 'post',
            url: url,
            data:params,
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            transformRequest: [
                (data :any) => {
                    return qs.stringify(data)
                }
            ],
        }
        const customOptions: any = {
            // 是否开启取消重复请求, 默认为 false
            repeat_request_cancel: true,
            // 是否开启接口错误信息展示，默认为true
            error_message_show: true
        }

        return request(config,customOptions)
    },
    put(url: any, params: any) {
        const config = {
            method: 'put',
            url: url,
            params:params,
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            transformRequest: [
                (data :any) => {
                    return qs.stringify(data)
                }
            ],
        }
        const customOptions: any = {
            // 是否开启取消重复请求, 默认为 false
            repeat_request_cancel: true,
            // 是否开启接口错误信息展示，默认为true
            error_message_show: true
        }

        return request(config,customOptions)
    },
    delete(url:any, params:any) {
        const config = {
            method: 'delete',
            url: url,
            params: params,
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            transformRequest: [
                (data :any) => {
                    return qs.stringify(data)
                }
            ],
        }
        const customOptions: any = {
            // 是否开启取消重复请求, 默认为 false
            repeat_request_cancel: true,
            // 是否开启接口错误信息展示，默认为true
            error_message_show: true
        }

        return request(config,customOptions)
    }
}