<template>
  <div style="background-color: #f5f5f5;height: 94vh">
    <div style="height: 75px;display: grid">
      <!--      撑其下面的div-->
      <div class="backbox">
      </div>
    </div>
    <div class="questionnaire">
      <h1>{{ survey.surveyName }}</h1>
      <p class="description">{{ survey.surveyDescription }}</p>
      <el-divider></el-divider>
      <div v-for="(question, index) in survey.questionDtoList" :key="index">
        <h3>{{ index + 1 }}. {{ question.questionDescription }}</h3>
        <div v-if="question.questionType === '1'">
          <el-radio-group v-model="answers[question.id]">
            <el-radio v-for="(option, index) in question.optionList" :key="index" :label="option.optionSort">
              {{ option.optionName }}
            </el-radio>
          </el-radio-group>
        </div>
        <div v-else-if="question.questionType === '2'">
          <el-checkbox-group v-model="answers[question.id]">
            <el-checkbox v-for="(option, index) in question.optionList" :key="index" :label="option.optionSort">
              {{ option.optionName }}
            </el-checkbox>
          </el-checkbox-group>
        </div>
        <div v-else>
          <el-input v-model="answers[question.id]" placeholder="请输入答案"></el-input>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { reactive} from 'vue'
import {usePersonalAnswerStore, useSurveyStore} from '@/stores/userSurvey'
import {surveyPreviewApi,} from "@/axios/api/myquestionnaire.api";
import {historyAnswerApi} from "@/axios/api/Personal.api"
import {storeToRefs} from "pinia";
import {useRouter} from "vue-router";

const router = useRouter()
const answers: Record<string, string | string[]> = reactive({})
const PersonalAnswerStore =usePersonalAnswerStore()
const surveyId = reactive({
  id: PersonalAnswerStore.surveyId
})
const answerId = reactive({
  surveyId: PersonalAnswerStore.surveyId,
  userId: PersonalAnswerStore.userId,
})
const store = useSurveyStore()
console.log(PersonalAnswerStore.surveyId)
surveyPreviewApi(surveyId).then(map => {

    store.setSurvey(map.data)

  historyAnswerApi(answerId).then(res => {
    console.log(res.data)
    for (let i in res.data.data) {
      answers[res.data.data[i].questionId] = JSON.parse(res.data.data[i].optionContent)
    }
  })
})
const {survey: survey} = storeToRefs(store)
</script>
<style scoped>

</style>