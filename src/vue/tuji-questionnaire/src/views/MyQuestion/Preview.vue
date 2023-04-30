<template>
  <div style="background-color: #f5f5f5;height: 94vh">
    <div class="topbox">
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
      <button @click="updateTemplate" class="greenButton" style="left: 600px;top: -50px">申请成为模板</button>
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
import {surveyPreviewApi,} from "@/axios/api/myquestionnaire.api";
import {storeToRefs} from "pinia";
import {Back} from "@element-plus/icons-vue";
import {ElMessage} from "element-plus";
import {surveyTemplateApplication} from "@/axios/api/Background";

export default defineComponent({
  components: {Back},
  setup() {
    const answers: Record<string, string | string[]> = reactive({})
    const surveyPreview = useSurveyPreviewStore()
    const surveyId = ({
      id: surveyPreview.cont.id
    })
    const store = useSurveyStore()
    const {survey: survey} = storeToRefs(store)
    console.log(surveyPreview)
    if (surveyId.id!=null){

      surveyPreviewApi(surveyId).then(map => {
        store.setSurvey(map.data)
      })
    }else {
      ElMessage.error("好像出错了！")
    }

    const template = reactive({
      id: '',
      surveyName: '',
      surveyDescription: '',
      content: '',
      creatorId: '',
      createDate: '',
      updateDate: '',
      state: '',
    })

    const updateTemplate = () => {
      template.surveyDescription = survey.value.surveyDescription
      template.surveyName = survey.value.surveyName
      // 需要进行序列化，但是为了防止后端认为序列化后的数据为非法数据，带有敏感符号，还需要进行url编码
      template.content =  encodeURIComponent(JSON.stringify(survey.value.questionDtoList))
      template.creatorId = survey.value.creatorId
      template.id = survey.value.id
      surveyTemplateApplication(template).then(map => {
        if (map.data.code == 200) {
          ElMessage.success("操作成功")
        } else if (map.data.code == 1000) {
          ElMessage.error(map.data.msg)
        } else {
          ElMessage.error("好像出错了QAQ")
        }
      })
    }

    return {
      survey,
      answers,
      updateTemplate
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