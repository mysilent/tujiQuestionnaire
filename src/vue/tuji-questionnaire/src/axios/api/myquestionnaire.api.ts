import {http} from "@/axios/api/api";
function selectUserSurveyApi(params:any){
    return http.get('/system/survey/selectUserSurvey',params)
}
function surveyPreviewApi(params:any){
    return http.get('/system/survey/selectQuestionnaire',params)
}
function deleteSurveyApi(params:any){
    return http.get('/system/survey/deleteQuestionnaire',params)
}function reviseSurvey(params:any){
    return http.post_survey('/system/survey/reviseQuestionnaire',params)
}
export {
    selectUserSurveyApi,
    surveyPreviewApi,
    deleteSurveyApi,
    reviseSurvey,
}