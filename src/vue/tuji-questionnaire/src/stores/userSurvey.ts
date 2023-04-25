import { defineStore } from 'pinia'
import type SurveyCreateDto from '@/type/SurveyCreateDto'

export const useSurveyPreviewStore = defineStore('userSurveyPreviewStore',  {
    state: () => ({
        cont:{
            id:''
        }
    }),
    // 所有数据持久化
    // persist: true,
    // 持久化存储插件其他配置
    persist: {
        // 修改存储中使用的键名称，默认为当前 Store的 id
        key: 'id',
        // 修改为 sessionStorage，默认为 localStorage
        storage: window.localStorage,
        // 部分持久化状态的点符号路径数组，[]意味着没有状态被持久化(默认为undefined，持久化整个状态)
        // paths: ['nested.data'],
    },
})

interface State {
    survey: SurveyCreateDto
}

export const useSurveyStore = defineStore( 'userSurveyStore',{
    state: (): State => ({
        survey: {
            id: '',
            surveyName: '',
            surveyDescription: '',
            startTime:'',
            endTime: '',
            status: '',
            surveySort: -1,
            topFlag: '',
            createDate: '',
            updateDate: '',
            creatorId: '',
            updatorId: '',
            surveyPicId: '',
            questionDtoList: []
        },
    }),
    actions: {
        setSurvey(survey: SurveyCreateDto) {
            this.survey = survey
        },
    },
})
