<template>
  <div class="questionnaireBox">
    <div class="questionnaire-editor">
      <el-form :model="survey1.data" label-width="80px" :rules="rules" ref="ruleFormRef">
        <el-form-item label="问卷标题" prop="surveyName">
          <el-input v-model="survey1.data.surveyName" placeholder="请输入问卷名称"></el-input>
        </el-form-item>
        <el-form-item label="问卷描述" prop="surveyDescription">
          <el-input v-model="survey1.data.surveyDescription" placeholder="请输入问卷描述"></el-input>
        </el-form-item>
        <el-form-item label="截止日期" prop="endTime">
          <el-date-picker v-model="survey1.data.endTime" type="date" placeholder="请输入问卷截至日期"></el-date-picker>
        </el-form-item>
      </el-form>
      <el-divider></el-divider>
      <draggable :list="survey1.data.questionDtoList" item-key="index"
                 @end="onEndQuestion(survey1.data.questionDtoList)">
        <template #item="{element :question,index}">
          <div v-if="question.questionType === '1'" class="questionnaire">
            <div class="questionBox">
              <div style="font-size: 20px">
                {{ index + 1 }}.
              </div>
              <div class="question">
                <el-form :model="question" :rules="rules" ref="ruleFormRef">
                  <el-form-item prop="questionDescription">
                    <el-input type="textarea" autosize show-word-limit maxlength="100"
                              v-model="question.questionDescription"></el-input>
                  </el-form-item>
                </el-form>
              </div>
              <div class="button-deleteQuestion" style="justify-content: center">
                <el-button type="danger" @click="removeQuestion(index)">删除问题</el-button>
              </div>
            </div>
            <draggable :list="question.optionList" item-key="index" @end="onEndOption(question.optionList)">
              <template #item="{element :option,index}">
                <el-radio-group v-model="awer[question.questionSort]">
                  <div class="option">
                    <el-radio :key="index" :label="option.optionSort" style="height: auto;margin-right:0">
                      <el-form :model="option" :rules="rules" ref="ruleFormRef">
                        <el-form-item prop="optionName">
                          <el-input v-model="option.optionName"></el-input>
                        </el-form-item>
                      </el-form>
                    </el-radio>
                    <div class="icon-option-delete" @click="removeItem(question,index)">
                      <svg viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg" data-v-ea893728="">
                        <path fill="currentColor"
                              d="m466.752 512-90.496-90.496a32 32 0 0 1 45.248-45.248L512 466.752l90.496-90.496a32 32 0 1 1 45.248 45.248L557.248 512l90.496 90.496a32 32 0 1 1-45.248 45.248L512 557.248l-90.496 90.496a32 32 0 0 1-45.248-45.248L466.752 512z"></path>
                        <path fill="currentColor"
                              d="M512 896a384 384 0 1 0 0-768 384 384 0 0 0 0 768zm0 64a448 448 0 1 1 0-896 448 448 0 0 1 0 896z"></path>
                      </svg>
                    </div>
                  </div>

                </el-radio-group>
              </template>
            </draggable>
            <el-button type="primary" @click="addItem(question)">添加选项</el-button>
          </div>
          <div v-else-if="question.questionType === '2'" class="questionnaire">
            <div class="questionBox">
              <div style="font-size: 20px">
                {{ index + 1 }}.
              </div>
              <div class="question">
                <el-form :model="question" :rules="rules" ref="ruleFormRef">
                  <el-form-item prop="questionDescription">
                    <el-input type="textarea" autosize show-word-limit maxlength="100"
                              v-model="question.questionDescription"></el-input>
                  </el-form-item>
                </el-form>
              </div>
              <div class="button-deleteQuestion">
                <el-button type="danger" @click="removeQuestion(index)">删除问题</el-button>
              </div>
            </div>
            <draggable :list="question.optionList">
              <template #item="{element :option,index}">
                <el-checkbox-group v-model="awer[question.questionSort]">
                  <div class="option">
                    <el-checkbox :key="index" :label="option.optionSort" style="height: auto">
                      <el-form :model="option" :rules="rules" ref="ruleFormRef">
                        <el-form-item prop="optionName">
                          <el-input v-model="option.optionName"></el-input>
                        </el-form-item>
                      </el-form>
                    </el-checkbox>
                    <div class="icon-option-delete" @click="removeItem(question,index)">
                      <svg viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg" data-v-ea893728="">
                        <path fill="currentColor"
                              d="m466.752 512-90.496-90.496a32 32 0 0 1 45.248-45.248L512 466.752l90.496-90.496a32 32 0 1 1 45.248 45.248L557.248 512l90.496 90.496a32 32 0 1 1-45.248 45.248L512 557.248l-90.496 90.496a32 32 0 0 1-45.248-45.248L466.752 512z"></path>
                        <path fill="currentColor"
                              d="M512 896a384 384 0 1 0 0-768 384 384 0 0 0 0 768zm0 64a448 448 0 1 1 0-896 448 448 0 0 1 0 896z"></path>
                      </svg>
                    </div>
                  </div>
                </el-checkbox-group>
              </template>
            </draggable>
            <el-button type="primary" @click="addItem(question)">添加选项</el-button>

          </div>
          <div v-else-if="question.questionType === '3'" class="questionnaire">
            <div class="questionBox">
              <div style="font-size: 20px">
                {{ index + 1 }}.
              </div>
              <div class="question">
                <el-input type="textarea" autosize show-word-limit maxlength="100"
                          v-model="question.questionDescription"></el-input>
              </div>
              <div class="button-deleteQuestion">
                <el-button type="danger" @click="removeQuestion(index)">删除问题</el-button>
              </div>
            </div>
            <draggable :list="question.optionList" item-key="index">
              <template #item="{element :option,index}">
              </template>
            </draggable>
            <el-input></el-input>
          </div>
        </template>
      </draggable>
      <el-form-item>
        <el-button type="primary" @click="submitForm(ruleFormRef)">提交</el-button>
      </el-form-item>
    </div>
  </div>
  <div class="nav-wrapper">
    <el-tooltip effect="dark" placement="bottom" content="添加单选">
      <el-button type="primary" circle @click="addQuestion('1')">单选</el-button>
    </el-tooltip>
    <el-tooltip effect="dark" placement="bottom" content="添加多选">
      <el-button type="primary" circle @click="addQuestion('2')">多选</el-button>
    </el-tooltip>
    <el-tooltip effect="dark" placement="bottom" content="添加填空">
      <el-button type="primary" circle @click="addQuestion('3')">填空</el-button>
    </el-tooltip>
  </div>
</template>

<script lang="ts">
import type QuestionDto from "@/type/QusetionDto";
import type OptionDto from "@/type/OptionDto";
import type SurveyCreateDto from "@/type/SurveyCreateDto";
import draggable from 'vuedraggable'
import {defineComponent, reactive, ref} from 'vue'
import {Remove} from "@element-plus/icons-vue";
import {saveSurveyApi} from '@/axios/api/publishquestionnaire.api'
import {useLoginStore} from '@/stores/UserLogin'
import {ElMessage} from "element-plus";
import type {FormInstance, FormRules} from "element-plus";
import {useRouter} from "vue-router";

export default defineComponent({
  components: {
    Remove,
    draggable,
  },
  setup() {
    const router = useRouter();
    const ruleFormRef = ref<FormInstance>();
    const storeId = useLoginStore()
    const surveys: SurveyCreateDto = <SurveyCreateDto>({})
    const survey = ({
      id: '',
      surveyName: '',
      surveyDescription: '',
      startTime: '',
      endTime: '',
      status: '',
      surveySort: 0,
      topFlag: '',
      createDate: '',
      updateDate: '',
      creatorId: '',
      updatorId: '',
      surveyPicId: '',
      questionDtoList: []
    })
    const survey1 = reactive({data: []})
    let awer = ref([])
    survey.creatorId = storeId.id
    survey.status = "0"
    survey1.data = survey

    const SENSITIVE_REGEX = /[\u4e00-\u9fa5]|[^\w\s]/g;
    const username = (rule: any, value: any, callback: any) => {
      if (value === '') {
        callback(new Error('请输入问卷标题'))
      } else {
        if (SENSITIVE_REGEX.test(value)) {
          callback(new Error('输入内容包含敏感字符'));
        } else {
          callback();
        }
      }
    }
    const validatePass = (rule: any, value: any, callback: any) => {
      if (value === '') {
        callback(new Error('请输入问卷描述'))
      } else {
        if (SENSITIVE_REGEX.test(value)) {
          callback(new Error('输入内容包含敏感字符'));
        } else {
          callback();
        }
      }
    }
    const endTime = (rule: any, value: any, callback: any) => {
      if (value === '') {
        callback(new Error('请选择截止日期'))
      } else {
        callback();
      }
    }

    const validateSensitive = (rule: any, value: any, callback: any) => {
      if (value === '' || SENSITIVE_REGEX.test(value)) {
        if (value === '') {
          callback(new Error('请输入内容'))
        } else {
          callback(new Error('输入内容包含敏感字符'));
        }
      } else {
        callback();
      }
    }

    const rules = reactive<FormRules>({
      surveyName: [{required: true, validator: username, trigger: 'blur'}],
      surveyDescription: [{required: true, validator: validatePass, trigger: 'blur'}],
      endTime: [{required: true, validator: endTime, trigger: 'blur'}],
      questionDescription: [{required: true, validator: validateSensitive, trigger: 'blur'}],
      optionName: [{required: true, validator: validateSensitive, trigger: 'blur'}],
    })


    const submitForm = (formEl: FormInstance | undefined) => {
      if (!formEl) return
      formEl.validate((valid) => {
        if (valid) {
          saveSurveyApi(survey1.data).then((map: any) => {
            if (map.data.code == 200) {
              ElMessage({message: '添加成功', type: 'success'})
              router.push({
                path: '/home/myQuestionnaire'
              })
            }
          })
        } else {
          ElMessage({message: '请填写必填项', type: 'error'})
          return false
        }
      })
    }


    return {survey1, awer, surveys, ruleFormRef, router, submitForm, rules, validateSensitive}
  },
  methods: {
    addQuestion(type: any) {
      let questionSort: number = this.survey1.data.questionDtoList.length + 1
      const question: QuestionDto = {
        id: '',
        surveyId: '',
        questionType: type,
        questionDescription: '新问题' + questionSort,
        questionSort: questionSort,
        requiredFlag: '',
        questionPicId: '',
        optionList: [],
      }
      this.survey1.data.questionDtoList.push(question)
    },
    removeQuestion(index: number) {
      this.survey1.data.questionDtoList.splice(index, 1)
    },
    addItem(question: QuestionDto) {
      const optionSort: number = question.optionList.length + 1
      const options: OptionDto = {
        id: '',
        surveyId: '',
        questionId: '',
        optionName: '新选项' + optionSort,
        optionSort: optionSort,
        optionPicId: '',
      }
      question.optionList?.push(options)
    },
    removeItem(question: QuestionDto, index: number) {
      question.optionList.splice(index, 1)
    },
    onEndOption(optionList: any) {
      let sortIndex = 1
      for (let i = 0; i < optionList.length; i++) {
        optionList[i].optionSort = sortIndex
        sortIndex++
      }
    },
    onEndQuestion(questionList: any) {
      let sortIndex = 1
      for (let i = 0; i < questionList.length; i++) {
        questionList[i].questionSort = sortIndex
        sortIndex++
      }
    },
  }
})

</script>


<style scoped>
.questionnaire-editor {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #e4e7ed;
  box-shadow: 0 0 12px rgba(0, 0, 0, 0.12);
  background-color: #F6F6F6;
}

.questionnaireBox {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
}

.questionnaire {
  max-width: 800px;
  margin: 12px 0;
  padding: 20px;
  box-shadow: 0 2px 4px 0 rgba(0, 0, 0, .1);
  background-color: white;
}

.questionBox {
  display: flex;
  justify-content: center;
  align-items: center
}


.button-deleteQuestion {
  margin: 0;
  padding-left: 12px;
  width: 120px;
  height: auto;
  display: inline-block;
}


.nav-wrapper {
  border: #F6F6F6 1px solid;
  background-color: #F6F6F6;
  box-shadow: 0 2px 4px 0 rgba(0, 0, 0, .1);
  position: fixed;
  top: 100px;
  left: 50px;
  z-index: 999;
}
</style>