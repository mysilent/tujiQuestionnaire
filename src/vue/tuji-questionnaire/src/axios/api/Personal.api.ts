import {http} from "@/axios/api/api";

function historyApi(params:any){
    return http.get('/system/userHistory/history',params)
}
function historyAnswerApi(params:any){
    return http.get('/system/answer/historyAnswer',params)
}
function changePassword(params:any){
    return http.post_survey('/system/user/changePassword',params)
}
function UserDetailSelect(params:any){
    return http.get('/system/userDetail/user',params)
}function UserDetailUpdate(params:any){
    return http.post_survey('/system/userDetail/userDetail',params)
}
export {
    historyApi,
    historyAnswerApi,
    changePassword,
    UserDetailSelect,
    UserDetailUpdate
}