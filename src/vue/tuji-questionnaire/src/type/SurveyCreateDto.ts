import type QuestionDto from "@/type/QusetionDto"
interface SurveyCreateDto {
    "id": string,
    "surveyName": string,
    "surveyDescription": string,
    "startTime":string,
    "endTime": string,
    "status": string,
    "surveySort": number,
    "topFlag": string,
    "createDate": string,
    "updateDate": string,
    "creatorId": string,
    "updatorId": string,
    "surveyPicId": string,
    questionDtoList: QuestionDto[]
}

export default SurveyCreateDto