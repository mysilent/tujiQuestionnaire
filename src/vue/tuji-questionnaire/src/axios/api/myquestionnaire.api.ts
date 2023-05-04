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
function surveyPublish(params:any){
    return http.post_survey('/system/surveyGold/surveyPublish',params)
}
function userGoldApi(params:any){
    return http.get('/system/userGold/userGold',params)
}

export {
    createAnswerData,
    selectUserSurveyApi,
    surveyPreviewApi,
    deleteSurveyApi,
    reviseSurvey,
    surveyPublish,
    userGoldApi,

}