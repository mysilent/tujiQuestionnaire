import {http} from "@/axios/api/api";

function findPage(params:any){
    return http.get('/background/user/page',params)
}
function AdminFindPage(params:any){
    return http.get('/background/user/adminPage',params)
}
export {findPage,
    AdminFindPage,
}