import {http} from "@/axios/api/api";
function selectUserSurveyApi(params:any){
    return http.get('/system/survey/selectUserSurvey',params)
}
function surveyPreviewApi(params:any){
    return http.get('/system/survey/selectQuestionnaire',params)
}
export {
    selectUserSurveyApi,
    surveyPreviewApi
}