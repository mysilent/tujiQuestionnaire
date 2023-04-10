import type OptionDto from "@/type/OptionDto"
interface QuestionDto {
    "id": string,
    "surveyId": string,
    "questionType": '1' | '2' | '3',
    "questionDescription":string,
    "questionSort":number,
    "requiredFlag": string,
    "questionPicId":string,
    "optionList": OptionDto[]
}
export default QuestionDto;