<template>
  <div style="height: 75px;display: grid">
    <!--      撑其下面的div-->
    <div class="backbox">
      <router-link tag="button" to="/home/myQuestionnaire" style=color:black;font-size:17px>
        <el-icon class="icon-float">
          <Back/>
        </el-icon>
        <span class="margin-lift-5">我的问卷</span></router-link>
    </div>
  </div>
  <div class="questionnaire">
    <h1>{{ survey.surveyName }}</h1>
    <p class="description">{{ survey.surveyDescription }}</p>
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
        <h2>{{ question.questionDescription }}</h2>
        <el-input v-model="answers[question.questionSort]" placeholder="请输入答案"></el-input>
      </div>
    </div>
        <el-button type="primary" @click="submitSurvey">提交</el-button>
  </div>
</template>

<script lang="ts">
import {defineComponent, reactive} from 'vue'
import {useSurveyPreviewStore,useSurveyStore} from '@/stores/userSurvey'
import {surveyPreviewApi} from "@/axios/api/myquestionnaire.api";
import {storeToRefs} from "pinia";
import {Back} from "@element-plus/icons-vue";


export default defineComponent({
  components: {Back},
  setup() {
    const answers: Record<string, string | string[]> = reactive({})
    const surveyPreview = useSurveyPreviewStore()
    const {cont:Id} = storeToRefs(surveyPreview)
    const surveyId = ({
      id: ''
    })
    surveyId.id = Id.value.id
    const store = useSurveyStore()
    const {survey: survey} = storeToRefs(store)
    surveyPreviewApi(surveyId).then(map => {
      store.setSurvey(map.data)
    })

    const submitSurvey = () => {
      console.log(answers)
    }


    return {
      submitSurvey,
      survey,
      answers
    }
  },
})
</script>
<style scoped>
.questionnaire {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
  background-color: #f7f7f7;
  border: 1px solid #ccc;
  box-shadow: 0 0 5px rgba(0, 0, 0, 0.5);
}

.description {
  font-size: 16px;
  color: #666;
  margin-bottom: 20px;
}

.backbox {
  width: 100px;
  height: 50px;
  float: right;
  margin-left: 50px;
  margin-top: 25px;
}

h1 {
  background-color: #f7f7f7;
  font-size: 28px;
  font-weight: bold;
  inset: 0;
  text-align: center;

}
</style>