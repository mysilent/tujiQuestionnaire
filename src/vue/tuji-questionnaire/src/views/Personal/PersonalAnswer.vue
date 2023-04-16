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
          <el-radio-group v-model="answers[question.questionSort]">
            <el-radio v-for="(option, index) in question.optionList" :key="index" :label="option.optionSort">
              {{ option.optionName }}
            </el-radio>
          </el-radio-group>
        </div>
        <div v-else-if="question.questionType === '2'">
          <el-checkbox-group v-model="answers[question.questionSort]">
            <el-checkbox v-for="(option, index) in question.optionList" :key="index" :label="option.optionSort">
              {{ option.optionName }}
            </el-checkbox>
          </el-checkbox-group>
        </div>
        <div v-else>
          <el-input v-model="answers[question.questionSort]" placeholder="请输入答案"></el-input>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import {defineComponent, reactive} from 'vue'
import {useSurveyPreviewStore, useSurveyStore} from '@/stores/userSurvey'
import {surveyPreviewApi} from "@/axios/api/myquestionnaire.api";
import {storeToRefs} from "pinia";

export default defineComponent({
  setup() {
    const answers: Record<string, string | string[]> = reactive({})
    const surveyPreview = useSurveyPreviewStore()
    const surveyId = reactive({
      id: ''
    })
    surveyId.id = surveyPreview.cont.id
    const store = useSurveyStore()
    const {survey: survey} = storeToRefs(store)
    console.log(surveyId)
    surveyPreviewApi(surveyId).then(map => {
      store.setSurvey(map.data)
    })

    return {
      survey,
      answers
    }
  },
})
</script>
<style scoped>

</style>