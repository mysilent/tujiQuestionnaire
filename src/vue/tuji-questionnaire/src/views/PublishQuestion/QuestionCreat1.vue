<template>
  <div class="questionnaire-editor">
    <el-card>
      <h3>编辑问卷</h3>
      <el-form :model="survey" label-width="80px">
        <el-form-item label="问卷名称">
          <el-input v-model="survey.surveyName" placeholder="请输入问卷名称"></el-input>
        </el-form-item>
        <el-form-item label="问题列表">
          <el-row>
            <el-col :span="20">
              <el-input v-model="questions.questionDescription" placeholder="请输入问题"></el-input>
            </el-col>
            <el-col :span="4">
              <el-button type="primary" @click="addQuestion">添加问题</el-button>
            </el-col>
          </el-row>
          <div v-for="(item, index) in survey.questionDtoList" :key="index">
            <el-divider></el-divider>
            <el-form-item label="问题">
              <el-input v-model="item.questionDescription" placeholder="请输入问题"></el-input>
            </el-form-item>
            <el-form-item label="题型">
              <el-select v-model="item.questionType" placeholder="请选择题型">
                <el-option label="单选" value="1"></el-option>
                <el-option label="多选" value="2"></el-option>
                <el-option label="填空" value="3"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="选项">
              <div v-if="item.questionType === '1'">
                <el-radio-group v-model="item.questionType">
                  <el-radio v-for="(option, index) in item.optionList" :key="index" :label="option.optionSort">{{ option.optionName }}</el-radio>
                </el-radio-group>
              </div>
              <div v-else-if="item.questionType === '2'">
                <el-checkbox-group v-model="item.questionType">
                  <el-checkbox v-for="(option, index) in item.optionList" :key="index" :label="option.optionSort">{{option.optionName}}
                  </el-checkbox>
                </el-checkbox-group>
              </div>
              <div v-else-if="item.questionType === '3'">
<!--                <el-input v-model=""> </el-input>-->
                <el-select v-model="item.questionType" placeholder="请选择答案">

<!--                  <el-option v-for="(option, index) in item.optionList" :key="index" :label="option.optionSort"-->
<!--                             :value="option"></el-option>-->
                </el-select>
              </div>
            </el-form-item>
            <el-form-item label="选项列表">
              <el-row>
                <el-col :span="20">
                  <el-input v-model="options.optionName" placeholder="请输入选项"></el-input>
                </el-col>
                <el-col :span="4">
                  <el-button type="primary" @click="addItem(item)">添加选项</el-button>
                </el-col>
              </el-row>
              <div v-for="(option, index) in item.optionList" :key="index">
                <el-tag>{{ option.optionName }}</el-tag>
                <el-button type="text" icon="el-icon-delete" @click="removeItem(item, index)"></el-button>
              </div>
            </el-form-item>
            <el-form-item>
              <el-button type="danger" @click="removeQuestion(index)">删除问题</el-button>
            </el-form-item>
          </div>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm">提交</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script lang="ts">
import {defineComponent, reactive, ref} from 'vue'
import type QuestionDto from '@/type/QusetionDto'
import type OptionDto from "@/type/OptionDto";
import type SurveyCreateDto from "@/type/SurveyCreateDto";
export default defineComponent({
  data() {
    const questions: QuestionDto = {
      id: '',
      surveyId: '',
      questionType: '1',
      questionDescription: '',
      questionSort: -1,
      requiredFlag: '',
      questionPicId: '',
      optionList: [],
    }

    const options: OptionDto = {
      id: '',
      surveyId: '',
      questionId: '',
      optionName: '',
      optionSort: -1,
      optionPicId: '',
    }
    const survey: SurveyCreateDto = {
      id: '',
      surveyName: '',
      surveyDescription: '',
      startTime: '',
      endTime: '',
      status: '',
      surveySort: -1,
      topFlag: '',
      createDate: '',
      updateDate: '',
      creatorId: '',
      updatorId: '',
      surveyPicId: '',
      questionDtoList:[]
    }
    const answers: Record<string, string | string[]> = reactive({})
    let questionSort:number=1
    let optionSort =1
    return {
      questions,
      options,
      survey,
      questionSort,
      optionSort,
      // answers,
    }
  },
  components: {},
  methods: {
    addQuestion() {
      const question: QuestionDto = {
        id: '',
        surveyId: '',
        questionType: '1',
        questionDescription: '',
        questionSort: this.questionSort,
        requiredFlag: '',
        questionPicId: '',
        optionList: [],
      }
      this.survey.questionDtoList.push(question)
      question.questionDescription = ''
      this.questionSort++
    },
    removeQuestion(index: number) {
      this.survey.questionDtoList.splice(index, 1)
    },
    addItem(question: QuestionDto) {
      if (question.optionList){
        // const option:OptionDto=this.options
        const newOption = Object.assign({}, this.options);
        if (this.options.optionName) {
          // question.optionList.push(option)
          question.optionList.push(newOption);
          this.options.optionSort=this.optionSort
          this.optionSort++
          this.options.optionName = ''
        }
      }
    },
    removeItem(question: QuestionDto, index: number) {
      question.optionList.splice(index, 1)
    },
    submitForm() {
      console.log(this.survey)
    }
  }
})
</script>

<style scoped>
.questionnaire-editor {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
}
</style>