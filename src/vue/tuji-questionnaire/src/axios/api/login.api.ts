import {http} from "@/axios/api/api";

function loginApi(params:any){
    return http.post('/system/user/login',params)
}
function enrollApi(params:any){
    return http.post('/system/user/enroll',params)
}
export {
    loginApi,
    enrollApi,
}