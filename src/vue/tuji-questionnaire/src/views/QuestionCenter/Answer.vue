<template>
  <div style="background-color: #f5f5f5;height: 94vh">
    <div style="height: 75px;display: grid">
      <!--      撑其下面的div-->
      <div class="backbox">
        <router-link tag="button" to="/home/QuestionnaireCenter" style=color:black;font-size:17px>
          <el-icon class="icon-float">
            <Back/>
          </el-icon>
          <span class="margin-lift-5">问卷中心</span></router-link>
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
      <button type="primary" class="submit-button" @click="submitSurvey(survey)">提交</button>
    </div>
  </div>
</template>

<script lang="ts">
import {defineComponent, reactive} from 'vue'
import {useSurveyPreviewStore, useSurveyStore} from '@/stores/userSurvey'
import {surveyPreviewApi} from "@/axios/api/myquestionnaire.api";
import {storeToRefs} from "pinia";
import {Back} from "@element-plus/icons-vue";
import {submitAnswer} from "@/axios/api/QuestionnaireCenter.api";
import {ElMessage} from "element-plus";
import {useLoginStore} from "@/stores/UserLogin";
import {useRouter} from "vue-router";


export default defineComponent({
  components: {Back},
  setup() {
    const userStore = useLoginStore()
    const router = useRouter()
    const answers: Record<string, string | string[]> = reactive({})
    const surveyPreview = useSurveyPreviewStore()
    const {cont: Id} = storeToRefs(surveyPreview)
    const surveyId = reactive({
      id: ''
    })
    surveyId.id = Id.value.id
    const store = useSurveyStore()
    const {survey: survey} = storeToRefs(store)
    surveyPreviewApi(surveyId).then(map => {
      store.setSurvey(map.data)
    })
    const surveys = {
      surveyName: '',
      userId: '',
      surveyId: '',
      questionList: [],
      answers: []
    }

    const submitSurvey = (survey: any) => {
      let questionList: string[] = []
      let question = survey.questionDtoList
      for (let i = 0; i < question.length; i++) {
        questionList[question[i].questionSort] = question[i].id
        surveys.questionList[i + 1] = question[i].id
        surveys.answers[i + 1] = JSON.stringify(answers[i + 1])
      }
      surveys.surveyId = survey.id
      surveys.userId = userStore.id
      surveys.surveyName = survey.surveyName
      submitAnswer(surveys).then(map => {
        if (map.data.code === 200) {
          if (map.data.data > 0) {
            ElMessage.success("恭喜获得" + map.data.data + "激励值~")
          } else
            ElMessage.success("回答完毕~")
          router.push({
            path: '/home/questionnaireCenter'
          })
        } else if (map.data.code === 1000) {
          ElMessage({message: map.data.msg, type: "error"})
        } else {
          ElMessage({message: "服务器出小差了QAQ", type: "error"})
        }
      })
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


.submit-button {
  display: block;
  margin: 20px auto 0;
  padding: 10px 20px;
  font-size: 18px;
  font-weight: bold;
  color: #fff;
  background-color: #007bff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}
</style>