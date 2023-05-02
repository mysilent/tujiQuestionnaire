<template>
  <h1>问卷中心</h1>
    <div class="questionnaires">
      <div v-for="questionnaire in survey" :key="questionnaire.id" class="questionnaire-card">
        <div class="questionnaire-header">
          <h2 class="questionnaire-title">{{ questionnaire.surveyName }}</h2>
          <div class="questionnaire-actions">
            <button class="answer-button" @click="answer(questionnaire.id)">作答</button>
            <div class="dropdown">

              <el-popover :width="75">
                <template #reference>
                <button class="dropdown-button">...</button>
                </template>
              <div>
                <button class="dropdown-item">举 报</button>
                <button class="dropdown-item">分 享</button>
                <button class="dropdown-item">复制到...</button>
              </div>
              </el-popover>
            </div>
          </div>
        </div>
        <div class="questionnaire-body">
          <p class="questionnaire-description">{{ questionnaire.surveyDescription }}</p>
          <div class="questionnaire-meta">
                        <span class="questionnaire-date">{{ moment(questionnaire.createDate).format("YYYY-MM-DD") }}</span>
          </div>
        </div>
      </div>
    </div>

</template>

<script lang="ts" setup>

import {reactive, ref,} from "vue";
import {selectOtherUserSurvey} from "@/axios/api/QuestionnaireCenter.api";
import {useLoginStore} from '@/stores/UserLogin'
import {useRouter} from 'vue-router'
import {useSurveyPreviewStore} from '@/stores/userSurvey'
import moment from 'moment';

const router = useRouter();
const userLogin = useLoginStore();
const user = ref([])
const surveyStore = useSurveyPreviewStore()
const id = reactive({
  id: ''
})
const survey: any = reactive([])
id.id = userLogin.id
selectOtherUserSurvey(id).then(map => {
  let i = 0
  for (i; i < map.data.data.length; i++) {
    survey.push(map.data.data[i]);
  }
})

function answer(id: any) {
  surveyStore.$patch((state) => {
    state.cont.id = id
  })
  router.push({
    name: "answer",
  })
}
</script>

<style scoped>
el-button {
  pointer-events: none
}


.questionnaires {
  padding: 10px 26px;
  display: grid;
  grid-template-columns: repeat(auto-fill, 471px);
  flex-wrap: wrap;
  gap: 1rem;
}

.questionnaire-card {
  width: 465px;
  background-color: #fff;
  border-radius: 0.5rem;
  box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.1);
  overflow: hidden;
  transition: transform 0.3s;
}
.questionnaire-card:hover{
  transform: scale(1.05);
}

.questionnaire-header {
  align-items: center;
  display: flex;
  justify-content: space-between;
  padding: 1rem;
}

.questionnaire-title {
  font-size: 1.5rem;
  font-weight: bold;
  margin: 0;
}

.questionnaire-actions {
  display: flex;
}

.answer-button {
  background-color: #007aff;
  border: none;
  border-radius: 0.5rem;
  color: #fff;
  cursor: pointer;
  font-size: 1rem;
  font-weight: bold;
  padding: 0.5rem 1rem;
  margin-right: 0.5rem;
  transition: background-color 0.2s ease;
}

.answer-button:hover {
  background-color: #0062cc;
}

.dropdown {
  position: relative;
}

.dropdown-button {
  background-color: transparent;
  border: none;
  color: #007aff;
  cursor: pointer;
  font-size: 1.5rem;
  padding: 0;
  margin: 0;
}


.dropdown-button:hover {
  display: block;
}

.dropdown-item {
  background-color: transparent;
  border: none;
  color: #333;
  cursor: pointer;
  font-size: 1rem;
  padding: 0.5rem 1rem;
  transition: background-color 0.2s ease;
  width: 125px;
}

.dropdown-item:hover {
  background-color: #f5f5f5;
}

.questionnaire-body {
  padding: 1rem;
}

.questionnaire-description {
  font-size: 1rem;
  margin: 0;
}

.questionnaire-meta {
  display: flex;
  font-size: 0.8rem;
  margin-top: 0.5rem;
}

.questionnaire-author {
  margin-right: 1rem;
}

.questionnaire-date {
  color: #999;
}
</style>