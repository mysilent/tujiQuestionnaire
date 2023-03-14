import axios from 'axios'

const request = axios.create({
    baseURL: 'http://localhost:8080',  // 注意！！ 这里是全局统一加上了 后端接口前缀 前缀，后端必须进行跨域配置！
    timeout: 5000
})
// request 拦截器
// 可以自请求发送前对请求做一些处理
// 比如统一加token，对请求参数统一加密
request.interceptors.request.use(config => {
    config.headers['Content-Type'] = 'application/json;charset=utf-8';

    // config.headers['token'] = user.token;  // 设置请求头
    return config
}, error => {
    return Promise.reject(error)
});

// response 拦截器
// 可以在接口响应后统一处理结果
request.interceptors.response.use(
    response => {
        let res = response.data;
        let code = response.status;
        let error = response.statusText;
        // 如果返回的状态码为200，说明接口请求成功，可以正常拿到数据
        // 否则的话抛出错误
        if (code === 200) {
            // 如果是返回的文件
            if (response.config.responseType === 'blob') {
                return res
            }
            // 兼容服务端返回的字符串数据
            if (typeof res === 'string') {
                res = res ? JSON.parse(res) : res
            }
            return res;
        } else {
            console.log('err' + error) // for debug
            return Promise.reject(error)
        }
    },
    error => {
        console.log('err' + error) // for debug
        return Promise.reject(error)
    }
)

export function get(url,params){
    return new Promise((resolve,reject) => {
        axios.get(url,{
            params : params
        }).then(res => {
            resolve(res)
        }).catch(err => {
            reject(err)
        })
    })
}

export function post(url,params){
    return new Promise((resolve,reject) => {
        axios.post(url,params)
            .then(res => {
                resolve(res.data)
            })
            .catch(err => {
                reject(err.data)
            })
    })
}

export default request
