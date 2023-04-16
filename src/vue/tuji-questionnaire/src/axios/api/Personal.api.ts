import {http} from "@/axios/api/api";

function historyApi(params:any){
    return http.get('/system/userHistory/history',params)
}
export {
    historyApi,
}