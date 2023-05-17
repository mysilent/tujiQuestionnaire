<template>
  <div>
    <div style="margin: 10px 0 ">
    </div>
    <el-table :data="state.tableData" border stripe
    >
      <el-table-column prop="id" label="问卷id" width="190"></el-table-column>
      <el-table-column prop="surveyName" label="问卷名称" width="190"></el-table-column>
      <el-table-column prop="creatorId" label="创建人id" width="190"></el-table-column>
      <el-table-column prop="createDate" label="提交时间" width="170"></el-table-column>
      <el-table-column label="操作">
        <template #default="{row}">
          <el-button type="info" @click="look(row)">查看</el-button>
          <el-popconfirm
              confirm-button-text='确定'
              cancel-button-text='我在想想'
              icon="el-icon-info"
              icon-color="red"
              title="您确定通过吗？"
              @confirm="templateByYes(row)" width="160"
          >
            <template #reference>
              <el-button type="success" @click="">通过</el-button>
            </template>
          </el-popconfirm>
          <el-popconfirm
              confirm-button-text='确定'
              cancel-button-text='我在想想'
              icon="el-icon-info"
              icon-color="red"
              title="您确定不通过吗？"
              @confirm="templateByNo(row)" width="160"
          >
            <template #reference>
              <el-button type="danger" style="margin-left: 25px" slot="reference">不通过</el-button>
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
            :page-sizes="[2, 5, 10]"
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
import {selectTemplateApplication, templateApplicationNo, templateApplicationYes} from "@/axios/api/Background";
import {ElMessage} from "element-plus";

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
})
const handleSizeChange = (pageSize: any) => {
  selectForm.pageSize = pageSize;
  load();
};

const handleCurrentChange = (pageNum: any) => {
  selectForm.pageNum = pageNum;
  load();
};
const templateByYes = (row:any) => {
  let id={id:row.id}
templateApplicationYes(id).then(map=>{
  if (map.data.code==200) {
    ElMessage.success("操作成功")
    load()
  }
  else ElMessage.error("操作失败")
})
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
const look = (row: any) => {
  store.cont.id = row.id
  let newUrl = router.resolve({path: "/BackgroundTemplatePreview"})
  window.open(newUrl.href, "_blank");
}

const load = () => {
  selectTemplateApplication(selectForm).then(map => {
    state.tableData = map.data.data;
    state.total = map.data.total;
  })
};
selectTemplateApplication(selectForm).then(map => {
  console.log(map.data)
  state.tableData = map.data.data;
  state.total = map.data.total;
})


</script>

<style scoped>

</style>