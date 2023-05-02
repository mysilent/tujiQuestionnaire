<template>
  <div>
    <div style="margin: 10px 0 ">
      <el-input v-model="selectForm.username" @blur="load" style="width: 200px" placeholder="请输入账户名"
                :suffix-icon="User"></el-input>
      <el-input v-model="selectForm.userId" style="width: 200px" placeholder="请输入ID号" :suffix-icon="Lock"
                class="ml-5"></el-input>
      <el-input v-model="selectForm.email" style="width: 200px" placeholder="请输入邮箱" :suffix-icon="Message"
                class="ml-5"></el-input>
      <el-input v-model="selectForm.phone" style="width: 200px" placeholder="请输入手机号" :suffix-icon="Phone"
                class="ml-5"></el-input>
      <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
    </div>
    <div style="margin: 10px 0">
      <el-button type="danger" @click="updatestateAll">批量封禁</el-button>
      <el-button type="success">导入</el-button>
      <el-button type="success">导出</el-button>
    </div>
    <el-table :data="state.tableData" border stripe
              @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="userId" label="id" width="190"></el-table-column>
      <el-table-column prop="username" label="账号" width="150"></el-table-column>
      <el-table-column prop="nickname" label="昵称" width="150"></el-table-column>
      <el-table-column prop="gender" label="性别" width="60"></el-table-column>
      <el-table-column prop="email" label="邮箱" width="200"></el-table-column>
      <el-table-column prop="phone" label="手机号" width="120"></el-table-column>
      <el-table-column prop="birthday" label="生日" width="120"></el-table-column>
      <el-table-column prop="state" label="账户状态" width="60">
        <template #default="{row}">
          <span v-if="row.state === 0" style="background-color: red">封禁</span>
          <span v-else-if="row.state === 1" style="background-color: greenyellow">可用</span>
          <span v-else style="background-color:rosybrown">已注销</span>
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template #default="{row}">
          <el-button :type="getButtonType(row.state)" @click="updateStates(row)">
            {{ getButtonText(row.state) }}
          </el-button>
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
import {Lock, Message, Phone, User} from "@element-plus/icons-vue";
import {findPage,updateState} from "@/axios/api/Background";

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
  username: '',
  userId: '',
  email: '',
  phone: '',
})

const handleSizeChange = (pageSize: any) => {
  selectForm.pageSize = pageSize;
  load();
};

const handleCurrentChange = (pageNum: any) => {
  selectForm.pageNum = pageNum;
  load();
};

const getButtonType = (state: any) => {
  if (state === 1) {
    return 'danger';
  } else if (state === 0) {
    return 'success';
  } else {
    return 'info';
  }
}
const getButtonText = (state: any) => {
  if (state === 1) {
    return '封禁';
  } else if (state === 0) {
    return '解封';
  } else {
    return '已注销';
  }
}


const handleSelectionChange = (val: any) => {
  state.multipleSelection = val;
};

const load = () => {
  findPage(selectForm).then(map => {
    state.tableData = map.data.data;
    state.total = map.data.total;
  })
};

findPage(selectForm).then(map => {
  state.tableData = map.data.data;
  state.total = map.data.total;
})

const updateStates = (row: any) => {
  console.log(row.state)
  if (row.state==1){
    row.state=0
  }
  else if (row.state==0){
    row.state=1
  }
  updateState(row).then(map=>{
    if (map.data.code==200){
      ElMessage.success("操作成功")
    }else {
      ElMessage.error("操作失败")
    }
  })

}
const updatestateAll = () => {
  console.log(state.multipleSelection)
}


</script>

<style scoped>

</style>