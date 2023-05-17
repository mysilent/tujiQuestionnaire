<template>
  <div>
    <div style="margin: 10px 0 ">
      <el-input v-model="selectForm.survey_name" style="width: 200px" placeholder="请输入问卷名称"
                :suffix-icon="Tickets"
                class="ml-5"></el-input>
      <el-input v-model="selectForm.id" style="width: 200px" placeholder="请输入问卷id" :suffix-icon="Lock"
                class="ml-5"></el-input>
      <el-input v-model="selectForm.nickname" style="width: 200px" placeholder="请输入创建人" :suffix-icon="User"
                class="ml-5"></el-input>

      <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
    </div>
    <div style="margin: 10px 0">
      <!--      <el-button type="primary" @click="insertButton">新增</el-button>-->
      <el-button type="success">导入</el-button>
      <el-button type="success">导出</el-button>
    </div>
    <el-table :data="state.tableData" border stripe
              @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="id" label="问卷id" width="190"></el-table-column>
      <el-table-column prop="surveyName" label="问卷名称" width="190"></el-table-column>
      <el-table-column prop="nickname" label="创建人" width="150"></el-table-column>
      <el-table-column prop="startTime" label="创建时间" width="170"></el-table-column>
      <el-table-column prop="status" label="状态" width="150">
        <template #default="{row}">
          <span v-if="row.status === '1'" style="background-color:coral">未发布</span>
          <span v-else-if="row.status === '0'" style="background-color: greenyellow">已发布</span>
          <span v-else-if="row.status === '2'" style="background-color:red">已结束</span>
          <span v-else style="background-color:rosybrown">已销毁</span>
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template #default="{row}">
          <el-button type="success" :icon="Search" @click="look(row)">查看</el-button>
          <el-popconfirm
              confirm-button-text='确定'
              cancel-button-text='我在想想'
              icon="el-icon-info"
              icon-color="red"
              title="您确定删除吗？"
              @confirm="delete_button(row)" width="160"
          >
            <template #reference>
              <el-button type="danger" :icon="Close" style="margin-left: 25px" slot="reference">删除</el-button>
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
import {ElMessage} from "element-plus";
import {reactive} from 'vue';
import {Close, Search, Tickets, User, Lock} from "@element-plus/icons-vue";
import {selectSurvey} from "@/axios/api/Background";
import {useSurveyPreviewStore} from "@/stores/userSurvey";
import {useRouter} from "vue-router";
import {deleteSurveyApi} from "@/axios/api/myquestionnaire.api";

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
  nickname: '',
  survey_name: '',
  id: '',
})

const handleSizeChange = (pageSize: any) => {
  selectForm.pageSize = pageSize;
  load();
};

const handleCurrentChange = (pageNum: any) => {
  selectForm.pageNum = pageNum;
  load();
};

const delete_button = (row: any) => {
  deleteSurveyApi(row).then(map => {
    if (map.data.code == 200) {
      ElMessage.success("删除成功")
      load()
    } else {
      ElMessage.error("删除失败")
    }
  })
}

const handleSelectionChange = (val: any) => {
  state.multipleSelection = val;
};
const look = (row: any) => {
  store.cont.id = row.id
  let newUrl = router.resolve({path: "/BackgroundPreview"})
  window.open(newUrl.href, "_blank");
}

const load = () => {
  selectSurvey(selectForm).then(map => {
    state.tableData = map.data.data;
    state.total = map.data.total;
  })
};

selectSurvey(selectForm).then(map => {
  state.tableData = map.data.data;
  state.total = map.data.total;
})


</script>

<style scoped>

</style>