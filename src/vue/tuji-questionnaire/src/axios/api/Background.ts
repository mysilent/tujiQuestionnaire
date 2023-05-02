import {http} from "@/axios/api/api";

function surveyCreateData(params:any){
    return http.get('/background/backgroundData/surveyCreate',params)
}
function answerCreateData(params:any){
    return http.get('/background/backgroundData/answerCreate',params)
}
function BackgroundLogin(params:any){
    return http.get('/background/user/backgroundLogin',params)
}
function updateState(params:any){
    return http.post_login('/background/user/updateState',params)
}
function findPage(params:any){
    return http.get('/background/user/page',params)
}
function AdminFindPage(params:any){
    return http.get('/background/user/adminPage',params)
}
function AdminSave(params:any){
    return http.post_survey('/background/user/save',params)
}
function AdminUpdate(params:any){
    return http.post_survey('/background/user/update',params)
}
function AdminDelete(params:any){
    return http.delete('/background/user/delete',params)
}
function selectSurvey(params:any){
    return http.get('/background/survey/selectAll',params)
}
function selectTemplate(params:any){
    return http.get('/background/surveyTemplate/selectTemplate',params)
}
function surveyTemplateApplication(params:any){
    return http.post_survey('/background/surveyTemplate/templateApplication',params)
}
function selectTemplateApplication(params:any){
    return http.get('/background/surveyTemplate/selectTemplateApplication',params)
}
function templateApplicationYes(params:any){
    return http.get('/background/surveyTemplate/templateApplicationYes',params)
}
function templateApplicationNo(params:any){
    return http.get('/background/surveyTemplate/templateApplicationNo',params)
}
function selectTemplatePreview(params:any){
    return http.get('/background/surveyTemplate/selectTemplatePreview',params)
}

export {
    surveyCreateData,
    answerCreateData,
    BackgroundLogin,
    updateState,
    findPage,
    AdminFindPage,
    AdminSave,
    AdminUpdate,
    AdminDelete,
    selectSurvey,
    selectTemplate,
    surveyTemplateApplication,
    selectTemplateApplication,
    templateApplicationYes,
    templateApplicationNo,
    selectTemplatePreview,

}