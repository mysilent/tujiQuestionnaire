<template>
  <div class="box-dev">
    <div class="box-card" v-for="(surveys,index) in survey"
         :key="index" @click="preview(surveys.surveyId)">
      <div class="header-div">
        <div class="image"><img src="../../../imager/preview-default.png"
                                style=" width: 100%;height: 100%;object-fit:cover;"></div>
        <span class="tag"></span>
      </div>
      <div class="h"><span>{{ surveys.surveyName }}</span></div>
      <div>
        <div class="text-div">
          <span>
        <span class="id">
      <span class="id-title">发布人ID</span>
      <span class="id-num">{{ surveys.userId }}</span>
        </span>
        </span>
          <span
              style="display: inline-grid;top: 5px;font-size: 10px;width: 100px;color: #888888">{{
              moment(surveys.createDate).format("YYYY-MM-DD")
            }}</span>
        </div>
      </div>
    </div>
  </div>

</template>

<script lang="ts" setup>

import {reactive, ref} from "vue";
import {useLoginStore} from '@/stores/UserLogin'
import {useRouter} from 'vue-router'
import {useSurveyPreviewStore} from '@/stores/userSurvey'
import {historyApi} from "@/axios/api/Personal.api";
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
historyApi(id).then(map => {
  let i = 0
  for (i; i < map.data.data.length; i++) {
    survey.push(map.data.data[i]);
  }
})
const preview=(id:string)=>{
  surveyStore.cont.id=id;
  let newUrl=router.resolve({
    path:"/personal/personalCenter/historicalAnswers/personalAnswer",
  })
  window.open(newUrl.href, "_blank");
}

</script>

<style scoped>
el-button {
  pointer-events: none
}

.box-dev {
  display: flex;
  min-height: 500px;
  height: auto;
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

.text-div {
  display: grid;
  padding: 0 16px;
  height: 24px;
  transition: opacity 0.5s, transform 0.5s, height 1s ease;
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
  border-right: 1px solid #f2f2f2;
  padding-right: 3px;
}

.id-num {
  background-color: #f2f2f2;
  padding-left: 3px;
}

.box-card {
  width: 220px;
  height: 210px;
  border-radius: 5px;
  transition: border-radius 0.3s, -webkit-box-shadow 0.3s, box-shadow 0.3s, transform 0.5s ease;
  overflow: hidden;
  -webkit-box-shadow: 2px 2px 7px #888888;
  box-shadow: 3px 2px 7px #888888;
}

.box-card:hover {
  border-radius: 5px;
  -webkit-box-shadow: 3px 3px 10px #888888;
  box-shadow: 3px 3px 10px #888888;
  transform: scale(1.05);
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