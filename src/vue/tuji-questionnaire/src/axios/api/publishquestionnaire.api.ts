import {http} from "@/axios/api/api"
function saveSurveyApi(params:any) {
    return http.post_survey('/system/survey/createQuestionnaire', params)
}
export {
    saveSurveyApi
}