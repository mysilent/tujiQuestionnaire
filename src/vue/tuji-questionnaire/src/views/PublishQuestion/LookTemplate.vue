<template>
  <div class="top-view">
    <div class="search-box">
      <input v-model="selectForm.surveyName" type="text" class="search-txt" placeholder="请输入想搜索的模板名~"/>
      <a class="search-btn" @click="load()">
        <i class="fa fa-search" aria-hidden="true"></i>
      </a>
    </div>
    <div class="top-view-button">
      <div style="flex-direction: revert">
        <button class="greenButton" @click="creatSurvey">创建新问卷</button>
      </div>
    </div>
  </div>
  <div>
    <div class="bottom-view">
      <div class="li-box">
        <div class="template-card" v-for="(template,index) in state.tableData">
          <div class="divlib">
              <div class="heard" @click="TemplatePreview(template.id)">{{template.surveyName}}</div>
            <div class="title">
            {{template.surveyDescription}}
            </div>
          </div>
        </div>
      </div>
      <div class="bottom-block">
      <div class="block">
        <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="selectForm.pageNum"
            :page-sizes="[9,12,24]"
            :page-size="selectForm.pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="state.total">
        </el-pagination>
      </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import {selectTemplate} from "@/axios/api/Background";
import {reactive} from "vue";
import {useRouter} from "vue-router";
import {useSurveyPreviewStore} from "@/stores/userSurvey";
const templateId =useSurveyPreviewStore()
const router= useRouter()
const state = reactive({
  tableData: [],
  total: 0,
})
const selectForm = reactive({
  pageNum: 1,
  pageSize: 9,
  surveyName:'',
  surveyId:'',
})
const handleSizeChange = (pageSize: any) => {
  selectForm.pageSize = pageSize;
  load();
};

const handleCurrentChange = (pageNum: any) => {
  selectForm.pageNum = pageNum;
  load();
};

const creatSurvey=()=>{
  router.push({
    path:'/home/publishQuestionnaire/QuestionCreat'
  })
}
const TemplatePreview = (id:any) => {
 templateId.cont.id=id
router.push({
    path:'/home/publishQuestionnaire/TemplatePreview'
  })
}

selectTemplate(selectForm).then(map=>{
  state.tableData = map.data.data;
  state.total = map.data.total;
})
const load = () => {
  selectTemplate(selectForm).then(map=>{
    state.tableData = map.data.data;
    state.total = map.data.total;
  })
}

</script>

<style scoped>
.search-box {
  /* 绝对定位 水平垂直居中 */
  position: absolute;
  top: 45%;
  left: 40%;
  transform: translate(-50%, -50%);
  background-color: #2f3640;
  /*height: 40px;*/
  padding: 10px;
  border-radius: 40px;
}

.search-txt {
  border: none;
  background: none;
  outline: none;
  float: left;
  padding: 0;
  color: #fff;
  font-size: 16px;
  line-height: 40px;
  width: 0;
  /* 动画过渡 */
  transition: 0.4s;
}

.search-btn {
  color: #e84118;
  float: right;
  width: 40px;
  height: 40px;
  border-radius: 50%;
  background-color: #2f3640;
  /* 弹性布局 水平垂直居中 */
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
  /* 动画过渡 */
  transition: 0.4s;
}

.search-box:hover .search-txt {
  width: 200px;
  padding: 0 6px;
}

.search-box:hover .search-btn {
  background-color: #fff;
}

.top-view {
  display: flex;
  height: 120px;
  background: url('https://www.wjx.cn/images/pubsurvey/wjbanner.jpg');
}

.top-view-button {
  top: 30%;
  left: 50%;
  max-height: 50px;
}

.bottom-view {
  padding: 30px 0 76px 0;
  width: 1030px;
  margin: 0 auto;
}
.bottom-block{
  display: inline-flex;
  margin-top: 50px;
  margin-bottom: 50px;
  margin-left: 300px;
}


.divlib {
  display: block;
  width: 254px;
  height: 132px;
  margin: 32px auto 36px;
}

.title {
  font-size: 12px;
}

.heard {
  font-size: 22px;
  color: #08304c;
  margin-bottom: 7px;
  cursor: pointer;
  width: auto;
}

.li-box {
  margin-bottom: 24px;
}

.template-card {
  float: left;
  width: 314px;
  *width: 312px;
  background-color: #fff;
  margin-right: 24px;
  -webkit-box-shadow: 0 0 15px rgba(152, 152, 152, 0.3);
  box-shadow: 0 0 15px rgba(152, 152, 152, 0.3);
  transition: box-shadow .5s ease;
}
.template-card:hover{
  box-shadow: 0 0 15px rgba(34,162,245,0.5);
}
</style>