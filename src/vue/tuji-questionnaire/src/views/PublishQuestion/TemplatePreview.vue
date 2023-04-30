<template>
  <div style="background-color: #f5f5f5;height: 94vh">
    <div class="topbox">
      <div class="backbox">
      </div>
    </div>
    <div class="questionnaire">
      <button @click="copyTemplate" class="greenButton" style="left: 600px">复制此模板</button>
      <h1>{{ survey.surveyName }}</h1>
      <p class="description">{{ survey.surveyDescription }}</p>
      <el-divider></el-divider>
      <div v-for="(question, index) in survey.questionDtoList.sort((a,b)=>a.questionSort-b.questionSort)" :key="index">
        <h3>{{ index + 1 }}. {{ question.questionDescription }}</h3>
        <div v-if="question.questionType === '1'">
          <el-radio-group v-model="answers[question.questionSort]">
            <el-radio v-for="(option, index) in question.optionList.sort((a,b)=>a.optionSort-b.optionSort)" :key="index"
                      :label="option.optionSort">
              {{ option.optionName }}
            </el-radio>
          </el-radio-group>
        </div>
        <div v-else-if="question.questionType === '2'">
          <el-checkbox-group v-model="answers[question.questionSort]">
            <el-checkbox v-for="(option, index) in question.optionList " :key="index" :label="option.optionSort">
              {{ option.optionName }}
            </el-checkbox>
          </el-checkbox-group>
        </div>
        <div v-else-if="question.questionType === '3'">
          <el-input v-model="answers[question.questionSort]" placeholder="请输入答案"></el-input>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import {defineComponent, reactive} from 'vue'
import {useSurveyPreviewStore, useSurveyStore} from '@/stores/userSurvey'
import {storeToRefs} from "pinia";
import {Back} from "@element-plus/icons-vue";
import {selectTemplatePreview} from "@/axios/api/Background";
import {useRouter} from "vue-router";

export default defineComponent({
  components: {Back},
  setup() {
    const answers: Record<string, string | string[]> = reactive({})
    const router =useRouter()
    const surveyPreview = useSurveyPreviewStore()
    const surveyId = ({
      id: surveyPreview.cont.id
    })
    const store = useSurveyStore()
    const {survey: survey} = storeToRefs(store)
    selectTemplatePreview(surveyId).then(map => {
      map.data.questionDtoList=JSON.parse(map.data.content)
      store.setSurvey(map.data)
    })
    const copyTemplate=()=>{
      router.push({
        path:'/home/publishQuestionnaire/TemplateRevise'
      })
    }

    return {
      survey,
      answers,
      copyTemplate
    }
  },
  computed: {}
})
</script>
<style scoped>
.topbox {
  height: 75px;
  display: grid
}
</style>