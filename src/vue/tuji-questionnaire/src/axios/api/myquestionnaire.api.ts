import {http} from "@/axios/api/api";
function createAnswerData(params:any){
    return http.get('/system/userHistory/createAnswerData',params)
}
function selectUserSurveyApi(params:any){
    return http.get('/system/survey/selectUserSurvey',params)
}
function surveyPreviewApi(params:any){
    return http.get('/system/survey/selectQuestionnaire',params)
}
function deleteSurveyApi(params:any){
    return http.get('/system/survey/deleteQuestionnaire',params)
}
function reviseSurvey(params:any){
    return http.post_survey('/system/survey/reviseQuestionnaire',params)
}
function reviseBySelectStatus(params:any){
    return http.get('/system/survey/reviseBySelectStatus',params)
}
function surveyPublish(params:any){
    return http.post_survey('/system/surveyGold/surveyPublish',params)
}
function surveyStop(params:any){
    return http.post_login('/system/surveyGold/surveyStop',params)
}
function userGoldApi(params:any){
    return http.get('/system/userGold/userGold',params)
}
function answerDataApi(params:any){
    return http.get('/system/answer/data',params)
}
function inputAnswerApi(params:any){
    return http.get('/system/answer/inputAnswer',params)
}

export {
    createAnswerData,
    selectUserSurveyApi,
    surveyPreviewApi,
    deleteSurveyApi,
    reviseSurvey,
    reviseBySelectStatus,
    surveyPublish,
    surveyStop,
    userGoldApi,
    answerDataApi,
    inputAnswerApi,

}