<template>
  <div>
    <div style="margin: 10px 0 ">
      <el-input v-model="selectForm.surveyId" style="width: 200px" placeholder="模板id"
                :suffix-icon="User"></el-input>
      <el-input v-model="selectForm.surveyName" style="width: 200px" placeholder="模板名称" :suffix-icon="Lock"
                class="ml-5"></el-input>
      <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
    </div>
    <el-table :data="state.tableData" border stripe
    >
      <el-table-column prop="id" label="模板id" width="190"></el-table-column>
      <el-table-column prop="surveyName" label="模板名称" width="190"></el-table-column>
      <el-table-column prop="creatorId" label="申请人id" width="190"></el-table-column>
      <el-table-column prop="createDate" label="申请时间" width="170"></el-table-column>
      <el-table-column label="操作">
        <template #default="{row}">
          <el-button type="info" @click="look(row)">查看</el-button>
          <el-popconfirm
              confirm-button-text='确定'
              cancel-button-text='我在想想'
              icon="el-icon-info"
              icon-color="red"
              title="您确定删除吗？"
              @confirm="templateByNo(row)" width="160"
          >
            <template #reference>
              <el-button type="danger" style="margin-left: 25px" slot="reference">删除</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <div>
      <div class="block">
        <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="selectForm.pageNum"
            :page-sizes="[2, 5, 10, 20]"
            :page-size="selectForm.pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="state.total">
        </el-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import {reactive} from 'vue';
import {useSurveyPreviewStore} from "@/stores/userSurvey";
import {useRouter} from "vue-router";
import {selectTemplate, templateApplicationNo,} from "@/axios/api/Background";
import {ElMessage} from "element-plus";
import {User} from "@element-plus/icons-vue";

const router = useRouter();
const store = useSurveyPreviewStore();

const state = reactive({
  tableData: [],
  dialogFormVisible: false,
  multipleSelection: [],
  total: 0,
})
const date = reactive({data: {}})
const selectForm = reactive({
  pageNum: 1,
  pageSize: 2,
  state: '0',
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
const look = (row: any) => {
  store.cont.id = row.id
  let newUrl = router.resolve({path: "/BackgroundTemplatePreview"})
  window.open(newUrl.href, "_blank");
}
const templateByNo = (row:any) => {
  let id={id:row.id}
  templateApplicationNo(id).then(map=>{
    if (map.data.code==200) {
      ElMessage.success("操作成功")
      load()
    }
    else ElMessage.error("操作失败")
  })
}

const load = () => {
  selectTemplate(selectForm).then(map => {
    state.tableData = map.data.data;
    state.total = map.data.total;
  })
};
selectTemplate(selectForm).then(map => {
  state.tableData = map.data.data;
  state.total = map.data.total;
})


</script>

<style scoped>

</style>