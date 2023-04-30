<template>
  <div class="login">
    <div class="login-box">
      <div class="input-box">
        <el-form :model="user" :rules="rules" ref="ruleFormRef" class="demo-ruleForm ">
          <el-form-item prop="username">
            <el-input v-model="user.username" placeholder="账户名" style="height: 35px"></el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input v-model="user.password" placeholder="密码" style="height: 35px" type="password"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="login(ruleFormRef)" class="button">登录</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import {BackgroundLogin} from "@/axios/api/Background"
import {reactive, ref} from "vue";
import {useRouter} from "vue-router";
import {ElMessage,} from "element-plus";
import type {FormInstance, FormRules} from "element-plus"
import {useAdminLoginStore} from "@/stores/UserLogin";

const loginStore =useAdminLoginStore()
const router =useRouter()
const ruleFormRef = ref<FormInstance>();
const user = reactive({
  username: '',
  password: ''
})
const username = (rule: any, value: any, callback: any) => {
  if (!value) {
    return callback(new Error('请输入用户名称'))
  } else {
    callback();
  }
}
const validatePass = (rule: any, value: any, callback: any) => {
  if (!value) {
    callback(new Error('请输入密码'))
  } else {
    callback();
  }
}
const rules = reactive<FormRules>({
  username: [{validator: username, trigger: 'blur'}],
  password: [{validator: validatePass, trigger: 'blur'}],
})
const login = (formEl: FormInstance | undefined) => {
  if (!formEl) return
  formEl.validate((valid) => {
    if (valid) {
      BackgroundLogin(user).then((map: any) => {
        if (map.data.code !== 200) {
          ElMessage({message: '用户名或密码错误', type: 'error'})
        } else {
          loginStore.$patch((state) => {
            state.id = map.data.data.id
            state.username = map.data.data.username
            state.name = map.data.data.name
            state.permissions=map.data.data.permissions
          })
          ElMessage({message: "登陆成功~", type: "success"})
          router.push({
            path: '/background'
          })
        }
      })
    } else {
      ElMessage({message: '请正确填写登录信息', type: 'error'})
      return false
    }
  })
}
</script>

<style scoped>
.button {
  border: none;
  height: 45px;
  background-color: lightseagreen;
  color: #fff;
  border-radius: 3px;
  cursor: pointer;
  width: 320px;
  font-size: 15px;
}

.login {
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
}

.login-box {
  position: relative;
  width: 320px;
}

.input-box {
  display: flex;
  flex-direction: column;
}




</style>