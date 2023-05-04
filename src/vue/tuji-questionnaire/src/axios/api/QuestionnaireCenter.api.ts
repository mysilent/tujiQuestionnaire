import {http} from "@/axios/api/api";

function selectOtherUserSurvey(params:any){
    return http.get('/system/survey/selectOtherUserSurvey',params)
}
function submitAnswer(params:any){
    return http.post_survey('/system/answer/submitAnswer',params)
}
export {
    selectOtherUserSurvey,
    submitAnswer,
}