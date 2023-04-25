<template>
  <div class="login">
    <div class="login-box">
      <div class="owl" id="owl">
        <div class="hand"></div>
        <div class="hand hand-r"></div>
        <div class="arms">
          <div class="arm"></div>
          <div class="arm arm-r"></div>
        </div>
      </div>
      <div class="input-box">
        <el-form :model="user" :rules="rules" ref="user" class="demo-ruleForm ,s">
          <el-form-item prop="username">
            <el-input v-model="user.username" placeholder="账户名" style="height: 35px"></el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input v-model="user.password" placeholder="密码" style="height: 35px"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button @click="login(ruleFormRef)" class="button">登录</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import {reactive, ref} from "vue";
import {useRouter} from "vue-router";
import {ElMessage, FormInstance, FormRules} from "element-plus";
import {loginApi} from "@/axios/api/login.api";
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
      loginApi(user).then((map: any) => {
        if (map.data.code !== 200) {
          ElMessage({message: '用户名或密码错误', type: 'error'})
        } else {
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

<style>
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
  /* 100%窗口高度 */
  height: 100vh;
  /* 弹性布局 居中 */
  display: flex;
  justify-content: center;
  align-items: center;
  /* 渐变背景 */
  background: linear-gradient(200deg, #72afd3, #96fbc4);
}

.login-box {
  /* 相对定位 */
  position: relative;
  width: 320px;
}

.input-box {
  /* 弹性布局 垂直排列 */
  display: flex;
  flex-direction: column;
}

.input-box input:focus {
  outline: 1px solid lightseagreen;
}

</style>