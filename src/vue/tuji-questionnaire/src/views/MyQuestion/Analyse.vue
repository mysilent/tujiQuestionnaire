<template>
  <div class="topbox"></div>
  <div class="questionnaire">
    <h1>{{ data.data.surveyName }}</h1>
    <p class="description">{{ data.data.surveyDescription }}</p>
    <el-divider></el-divider>
    <div v-for="(question, index) in data.data.answerDataDtoList" :key="index">
      <h3> {{ index + 1 }}.
        <span style="color:#828279">[{{ getQuestionType(question.questionType) }}]</span>
        {{ question.questionName }}</h3>
      <div v-if="question.questionType==='1'||question.questionType==='2'">
        <el-table :data="Surveydata.data[index]" :row-style="tableRowClassName" border style="width: 100%">
          <el-table-column prop="optionName" label="选项" width="180"/>
          <el-table-column prop="count" label="小计" width="180"/>
          <el-table-column prop="proportion" label="比例">
            <template #default="scope">
              <el-progress :percentage=scope.row.proportion></el-progress>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <div v-if="question.questionType==='3'">
        <el-button @click="dialog(question)">查看详细答案</el-button>
      </div>
    </div>
    <el-dialog
        v-model="dialogVisible"
        :header="questionName"
        width="60%"
        :before-close="handleClose"
        :close-on-click-modal="false"
    >
      <template #title>
        <div>
          {{ questionName }}
        </div>
      </template>
      <el-input v-model="question.content" label="搜索关键词" input-style="width:200px"></el-input>
      <el-button @click="lode">搜索</el-button>
      <div class="demo-pagination-block">
        <div class="demonstration">
          <el-table :data="questionContent.data" border style="width: 100%">
            <el-table-column prop="userId" label="用户id" width="180"/>
            <el-table-column prop="optionContent" label="答案" width="500"/>
            <el-table-column prop="" label="查看问卷">
              <template #default="scope">
                <el-button @click="lookSurvey(scope.row)">查看详情</el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
        <el-pagination
            v-model:current-page="question.pageNum"
            v-model:page-size="question.pageSize"
            :page-sizes="[5,10,20]"
            layout="total, sizes, pager, next, jumper"
            :total="total"
            @size-change="lode"
            @current-change="lode"
        />
      </div>
    </el-dialog>
    <div ref="container"></div>
  </div>

</template>

<script lang="ts" setup>
import {answerDataApi, inputAnswerApi} from "@/axios/api/myquestionnaire.api";
import {useAnswerDataStore, usePersonalAnswerStore} from "@/stores/userSurvey";
import {reactive, ref} from "vue";
import {useRouter} from "vue-router";

const PersonalAnswerStore= usePersonalAnswerStore()
const router = useRouter()
const Store = useAnswerDataStore()

const dialogVisible = ref(false)
const total = ref(0)
const questionName = ref("")
const question = reactive({
  surveyId: Store.cont.id,
  questionId: '',
  content: '',
  pageNum: 1,
  pageSize: 5,
})
const questionContent = reactive({data: []})
const lode = () => {
  inputAnswerApi(question).then(map => {
    questionContent.data = map.data.data["data"]
    total.value = map.data.data["total"]
  })
}

const dialog = (q: any) => {
  question.questionId = q.questionId
  questionName.value = q.questionName
  dialogVisible.value = true
  lode()
}
const handleClose = (done: () => void) => {
  done()
}

const lookSurvey = (row:any) => {
  PersonalAnswerStore.userId=row.userId
  let newUrl = router.resolve({
    path :'/home/myQuestionnaire/AnswerPreview'
  })
  window.open(newUrl.href,"_blank")
}


const id = reactive({
  id: Store.cont.id
})
const data = reactive({data: []})
answerDataApi(id).then(map => {
  data.data = map.data.data
  // 对传来数据进行处理
  //遍历问题表里面的问题
  for (let i in data.data.answerDataDtoList) {
    const tableQuestionData: any = []
    //若问题为单选执行该逻辑
    if (data.data.answerDataDtoList[i].questionType == '1') {
      //
      for (let j in data.data.oneOptionData) {
        if (data.data.oneOptionData[j].question_id === data.data.answerDataDtoList[i].questionId) {
          const tableOptionOneData =
              {
                optionName: data.data.oneOptionData[j].option_name,
                count: data.data.oneOptionData[j].count,
                proportion: data.data.oneOptionData[j].count / data.data.answerDataDtoList[i].count * 100,
              }
          tableQuestionData.push(tableOptionOneData)
        }
      }
      const tableQuestionAnyData =
          {
            optionName: "本次有效填写人数",
            count: data.data.answerDataDtoList[i].count,
            proportion: '',
          }
      tableQuestionData.push(tableQuestionAnyData)
      Surveydata.data.push(tableQuestionData)
    }
    //若为多选执行下面逻辑
    if (data.data.answerDataDtoList[i].questionType == '2') {
      for (let j in data.data.optionList) {
        if (data.data.optionList[j].questionId === data.data.answerDataDtoList[i].questionId) {
          const tableOptionAnyData =
              {
                optionName: '',
                count: 0,
                proportion: 0,
              }
          tableOptionAnyData.optionName = data.data.optionList[j].optionName
          let l = data.data.optionList[j].optionSort
          if (data.data.anyOptionData[data.data.optionList[j].questionId][l]) {
            tableOptionAnyData.count = data.data.anyOptionData[data.data.optionList[j].questionId][l]
            tableOptionAnyData.proportion = data.data.anyOptionData[data.data.optionList[j].questionId][l] / data.data.answerDataDtoList[i].count * 100

          } else {
            tableOptionAnyData.count = 0
            tableOptionAnyData.proportion = 0
          }
          tableQuestionData.push(tableOptionAnyData)
        }
      }
      const tableQuestionAnyData =
          {
            optionName: "本次有效填写人数",
            count: data.data.answerDataDtoList[i].count,
            proportion: '',
          }
      tableQuestionData.push(tableQuestionAnyData)
      Surveydata.data.push(tableQuestionData)
    }
    //若为填空执行下面逻辑
    if (data.data.answerDataDtoList[i].questionType == '3') {
    }
  }
})

const getQuestionType = (type: string) => {
  if (type == "1") {
    return "单选"
  } else if (type == "2") {
    return "多选"
  } else if (type == "3") {
    return "填空"
  }
}

const Surveydata = reactive({data: []})

const tableRowClassName = (pam: any) => {
  const style = {
    background: "#f2f6fc"
  }
  if (pam.row.proportion === '') {
    return style
  }
  return ''
}


</script>


<style scoped>
.topbox {
  height: 75px;
  display: grid;

}
</style>