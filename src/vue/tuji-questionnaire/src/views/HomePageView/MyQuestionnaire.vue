<template>
  <h1>我的问卷</h1>


  <div class="index">
    <div class="box-card" @mouseover="isActive=index" @mouseout="isActive=-1" v-for="(surveys,index) in survey" :key="index">
      <div class="header-div">
        <div class="image"></div>
        <div class="state"><span class="text">{{ '答题中' }}</span></div>
        <span class="tag"></span>
      </div>
      <div class="h"><span>{{surveys.surveyName}}</span></div>
      <div>
        <div class="text-div" v-bind:class="{ 'text-div-add': isActive===index}">
          <span style="flex: auto">{{ '1' }}份</span>
          <span>
        <span class="id">
      <span class="id-title">ID</span>
      <span class="id-num">{{ surveys.id }}</span>
        </span>
        </span>
        </div>
        <div class="bottom-div" v-bind:class="{ 'bottom-div-add':isActive===index}">
          <el-row>
            <el-button type="info" style="cursor: pointer" :icon="Search" circle @click="preview(surveys.id)"/>
            <el-button type="primary" :icon="Edit" circle/>
            <el-button type="warning" :icon="Star" circle/>
            <el-button type="danger" :icon="Delete" circle/>
          </el-row>
        </div>
      </div>
    </div>
  </div>

</template>

<script lang="ts" setup>

import {Search, Edit, Star, Delete} from "@element-plus/icons-vue";
import {reactive, ref} from "vue";
import {selectUserSurveyApi} from "@/axios/api/myquestionnaire.api";
import {useLoginStore} from '@/stores/UserLogin'
import {useRouter} from 'vue-router'
import {useSurveyPreviewStore} from '@/stores/userSurvey'


const router = useRouter();
const userLogin = useLoginStore();
const isActive = ref(-1);
const user = ref([])
const id = reactive({
  id:''
})
const surveyStore = useSurveyPreviewStore()

const survey:any =reactive([])
id.id = userLogin.id
selectUserSurveyApi(id).then(map=>{
  let i = 0
  for (i ;i<map.data.data.length;i++){
    survey.push(map.data.data[i]);
  }
})


function preview(id:any){
  surveyStore.$patch((state)=>{
    state.cont.id=id
  })
  router.push({
    name:"preview",
  })
}



</script>

<style scoped>
el-button {
  pointer-events: none
}

.index {
  display: grid;
  /*width: 220px;*/
  /*height: 210px;*/
  grid-template-columns: repeat(auto-fill, 220px);
  grid-gap: 26px;
  padding: 10px 32px;
}

.image {
  width: 100%;
  height: 100%;
  vertical-align: top;
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
}

.state {
  position: absolute;
  left: 8px;
  top: 8px;
}

.text {
  color: #1ED261;
  border: 1px solid #1ED261;
  background-color: #F4FDF7;
  border-radius: 4px;
  font-size: 12px;
  padding: 0 6px;
}

.text-div {
  display: flex;
  padding: 0 16px;
  height: 24px;
  transition: opacity 0.5s, transform 0.5s, height 1s ease;
  /*transition: opacity 0.3s, transform 0.3s ease, -webkit-transform 0.3s ease;*/
  /*transform: scale(1, 0.1);*/
}

.text-div-add {
  transform: scale(1, 0.1);
  display: flex;
  padding: 0 16px;
  height: 0;
  opacity: 0;
  transition: opacity 0.5s, transform 0.5s, height 0.7s ease;
}

.tag {
  position: absolute;
  right: 4px;
  bottom: 8px;
}

.id {
  background: #F6F6F6;
  border-radius: 4px;
  padding: 0 4px;
  font-size: 12px;
}

.id-title {
  border-right: 1px solid #ffffff;
  padding-right: 3px;
}

.id-num {
  padding-left: 3px;
}

.box-card {
  width: 220px;
  height: 210px;
  border-radius: 5px;
  transition: border-radius 0.3s, -webkit-box-shadow 0.3s, box-shadow 0.3s ease;
  overflow: hidden;
  -webkit-box-shadow: 2px 2px 3px #888888;
  box-shadow: 2px 2px 3px #888888;
}

.box-card:hover {
  border-radius: 5px;
  -webkit-box-shadow: 3px 3px 10px #888888;
  box-shadow: 3px 3px 10px #888888;
}

.bottom-div {
  width: 220px;
  height: 32px;
  display: grid;
  place-items: center;
  opacity: 0;
  transition: opacity 0.3s, height 0.5s ease;
}

.bottom-div-add {
  width: 220px;
  height: 32px;
  display: grid;
  place-items: center;
  /*transform: scale(1, 0.1);*/
  opacity: 1;
  transition: opacity 0.3s, height 0.5s ease;
  /*transition: opacity 0.3s, transform 0.3s ease;*/
}

.header-div {
  background-color: rgb(242, 242, 242);
  width: 220px;
  height: 122px;
  border-top-left-radius: 5px;
  border-top-right-radius: 5px;
}

.h {
  display: inline-block;
  width: 220px;
  height: 35px;
  font-size: 16px;
  line-height: 26px;
  padding: 8px 16px 8px;
}


</style>