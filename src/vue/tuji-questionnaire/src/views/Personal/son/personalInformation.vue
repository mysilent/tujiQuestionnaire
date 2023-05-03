<template>
  <div class="index">
    <div class="hard-box">
      <h1>个人信息</h1>
    </div>
    <div class="user-box">
      <h1>个人头像</h1>
    </div>
    <div class="box2">
      <el-card style="border-radius: 30px">
        <div slot="header">
        </div>
        <div class="user-card">
          <div class="user-card-item"><p>用户ID:{{ user.userId }}</p></div>
          <div class="user-card-item"><p>昵 称：{{ user.name }}</p></div>
          <div class="user-card-item"><p>性 别：{{ user.gender }}</p></div>
          <div class="user-card-item"><p>生 日：{{ user.birthday }}</p></div>
          <div class="user-card-item"><p>邮 箱：{{ user.email }}</p></div>
          <div class="user-card-item"><p>电 话：{{ user.phone }}</p></div>
          <div class="user-card-item"><p>激励值：{{ user.gold }}</p></div>
        </div>
      </el-card>
    </div>
    <div class="box">
      <img src="../../../imager/img.png" style=" width: 100%;height: 100%;object-fit:cover;">
    </div>
    <div class="bottom-button">
      <button class=" greenButton" style="margin:10px 40px" @click="showDialog">修改信息</button>
      <button class=" greenButton"> 上传头像</button>
    </div>
    <el-dialog style="display:table;" :before-close="showDialog" v-model="dialogFormVisible" title="请填写你要修改的信息~">
      <div style="display:flex;">
        <el-form style="display:-webkit-box;"
                 ref="ruleFormRef"
                 :model="users"
                 :rules="rules"
                 >
        <div class="wrapper">
          <el-form-item >
          <div class="input-data">
            <input type="text" required style="outline: none;" v-model="users.name">
            <div class="underline"></div>
            <label>您的昵称</label>
          </div>
          </el-form-item>
          <el-form-item prop="email">
          <div class="input-data" >
            <input type="text" required style="outline: none;" v-model="users.email">
            <div class="underline"></div>
            <label>您的邮箱</label>
          </div>
          </el-form-item>
          <el-form-item prop="phone">
          <div class="input-data">
            <input type="text" required style="outline: none;" v-model="users.phone">
            <div class="underline"></div>
            <label>您的电话</label>
          </div>
          </el-form-item>
        </div>
        <div class="wrapper">
          <div style="margin-top: 20px">
            <label>性别:</label>
            <el-radio-group v-model="users.gender" class="margin-lift-5">
              <el-radio label="男" size="large">男</el-radio>
              <el-radio label="女" size="large">女</el-radio>
            </el-radio-group>
          </div>
          <div style="margin-top: 30px">
            <div class="demo-date-picker">
              <div class="block">
                <span class="demonstration">请选择生日:</span>
                <el-date-picker
                    v-model="users.birthday"
                    type="date"
                    placeholder="提前祝你生日快乐~"
                    :disabled-date="disabledDate"
                    format="YYYY/MM/DD"
                    value-format="YYYY-MM-DD"
                />
              </div>
            </div>
          </div>
        </div>
        </el-form>
      </div>
      <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click="userDetailUpdate(users)">确定</el-button>
        <el-button @click="dialogFormVisible = false">
          取消
        </el-button>
      </span>
      </template>
    </el-dialog>
  </div>

</template>

<script lang="ts" setup>
import {computed, reactive, ref} from "vue";
import {UserDetailSelect, UserDetailUpdate} from "@/axios/api/Personal.api";
import {useLoginStore} from "@/stores/userLogin";
import type { FormInstance, FormRules } from 'element-plus'
import {ElMessage} from "element-plus";

const dialogFormVisible = ref(false)
const userStore = useLoginStore()
const usersId = computed(() => userStore.id)
const user = reactive({
  userId: '',
  name: '',
  gender: '',
  email: '',
  phone: '',
  birthday: '',
  gold:0
})
const users = reactive({
  userId: usersId,
  name: '',
  gender: '男',
  email: '',
  phone: '',
  birthday: '',
})

const surveyId = reactive({
  id: usersId
})
UserDetailSelect(surveyId).then(map => {
  user.userId = map.data.userId;
  user.name = map.data.name
  user.gender = map.data.gender
  user.email = map.data.email
  user.phone = map.data.phone
  user.birthday = map.data.birthday
  user.gold=map.data.gold
})

 const load=()=>{
   UserDetailSelect(surveyId).then(map => {
     user.userId = map.data.userId;
     user.name = map.data.name
     user.gender = map.data.gender
     user.email = map.data.email
     user.phone = map.data.phone
     user.birthday = map.data.birthday
     user.gold=map.data.gold
   })
 }
const mailReg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/
const userDetailUpdate = (users: any) => {
  if((mailReg.test(users.email)||users.email=='')&&(users.phone.length==11||users.phone=='')){
    UserDetailUpdate(users).then(map => {
      if (map.data.code == 200) {
        ElMessage({message: "修改成功~", type: "success"})
        load()
        dialogFormVisible.value = false
      }else {
        ElMessage.error("好像出错了QAQ,请联系管理员")
      }
    })
  }else {
    ElMessage.error("请正确填写电话或邮箱")
  }
}



// const link1 = ref("link1")
//
// onMounted(() => {
//   link1.value.click()
// })

const showDialog = () => {
  dialogFormVisible.value = true;
}


const disabledDate = (time: Date) => {
  return time.getTime() > Date.now()
}

const ruleFormRef = ref<FormInstance>()

const email = (rule: any, value: any, callback: any) => {
  const mailReg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/
    if (mailReg.test(value)) {
      callback()
    } else {
      ElMessage.error("请输入正确邮箱格式")
      callback(new Error('请输入正确的邮箱格式'))

    }
}
const phone = (rule: any, value: any, callback: any) => {
    if (value.length==11) {
      callback()
    } else {
      ElMessage.error("请输入正确号码")
      callback(new Error('请输入正确的手机号'))

    }
}

const rules = reactive<FormRules>({
  phone: [{ validator: phone, trigger: 'blur' }],
  email: [{ validator: email, trigger: 'blur' }],
})


</script>

<style scoped>
.dialog-footer button:first-child {
  margin-right: 10px;
}

.index {
  height: 200px;
}

.hard-box {
  width: 150px;
  top: 50px;
  left: 170px;
  position: absolute;
}

.user-box {
  width: 150px;
  top: 50px;
  left: 520px;
  position: absolute;
}

.user-card {
  height: 300px;
  display: grid;
}

.box {
  border-radius: 100px;
  width: 200px;
  height: 200px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
  top: 100px;
  left: 500px;
  position: absolute;
  animation: box-slide-in 3s forwards;
  overflow: hidden;
}

.box2 {
  border-radius: 30px;
  width: 300px;
  height: auto;
  background: linear-gradient(to bottom right, #f7d9aa, #e8a87c);
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
  top: 100px;
  left: 100px;
  position: absolute;
  animation: slide-in 3s forwards;
}

@keyframes slide-in {
  0% {
    opacity: 0;
    transform: translateX(-100px);
  }
  100% {
    opacity: 1;
    transform: translateX(0);
  }
}

@keyframes box-slide-in {
  0% {
    opacity: 0;
    transform: translateX(100px);
  }
  100% {
    opacity: 1;
    transform: translateX(0);
  }
}

.user-card-item {
  display: flex;
  align-items: center;
}

.bottom-button {
  top: 350px;
  left: 420px;
  width: 500px;
  height: 50px;
  position: absolute;
}

p {
  font-size: 16px;
}


.wrapper {
  width: 250px;
  background-color: #fff;
  /* 内边距（上下左右） */
  padding: 20px;
  /* 盒子阴影 */
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  /* 圆角 */
  border-radius: 8px;
}

.wrapper .input-data {
  /* 相对定位 */
  position: relative;
  width: 100%;
  height: 40px;
  margin-top: 20px;
}

.wrapper .input-data input {
  width: 100%;
  height: 100%;
  border: none;
  font-size: 17px;
  border-bottom: 2px solid #c0c0c0;
}

/* 输入框获得焦点时 */
.wrapper .input-data input:focus ~ label,
  /* 输入框的值为合法时 */
.wrapper .input-data input:valid ~ label {
  /* label上移，同时改变字号、字体颜色 */
  transform: translateY(-20px);
  font-size: 15px;
  color: #2c6fdb;
}

.wrapper .input-data label {
  /* 绝对定位 */
  position: absolute;
  bottom: 10px;
  left: 0;
  color: #808080;
  /* 这个属性可以使点击label穿透到输入框 */
  pointer-events: none;
  /* 现在动画有点生硬，在这里需要给动画加个过渡 */
  transition: all 0.3s ease;
}

.wrapper .input-data .underline {
  position: absolute;
  bottom: 0;
  height: 2px;
  width: 100%;
  background-color: #2c6fdb;
  /* 沿X轴缩小 */
  transform: scaleX(0);
  /* 这里同样给动画加个过渡 */
  transition: all 0.3s ease;
}

.wrapper .input-data input:focus ~ .underline,
.wrapper .input-data input:valid ~ .underline {
  /* 沿X轴放大 */
  transform: scaleX(1);
}
</style>