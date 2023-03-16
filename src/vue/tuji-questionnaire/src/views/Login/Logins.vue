<template>
  <div class="login">
    <div class="login-box">
      <div class="input-box">
        <el-form :model="user" :rules="rules" ref="ruleFormRef" class="demo-ruleForm ">
          <el-form-item prop="username">
            <el-input v-model="user.username" placeholder="账户名" style="height: 35px"></el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input v-model="user.password" placeholder="密码" style="height: 35px"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm(ruleFormRef)" class="button">登录</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import {loginAPI} from "@/axios/api/logints.api";
import type { FormInstance, FormRules} from "element-plus";
import {ElMessage} from "element-plus";
import {useRouter} from "vue-router";
import {reactive, ref} from "vue";

const ruleFormRef = ref<FormInstance>()
const router = useRouter();

const user = reactive({
  username: '',
  password: ''
})

const username = (rule: any, value: any, callback: any) => {
  const mailReg = /^[a-zA-Z]{1}([a-zA-Z0-9]|[._]){7,15}$/;
  if (!value) {
    return callback(new Error('请输入用户名称'))
  } else if (mailReg.test(value)) {
    callback()
  } else {
    callback(new Error("请以英文开头，用户名长度8-16"))
  }
}

const validatePass = (rule: any, value: any, callback: any) => {
  if (value === '') {
    callback(new Error('请输入密码'))
  }else {
    callback();
  }
}

const rules = reactive<FormRules>({
  username: [{validator: username, trigger: 'blur'}],
  password: [{validator: validatePass, trigger: 'blur'}],
})


const submitForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return
  formEl.validate((valid) => {
    if (valid) {
      loginAPI(user).then((map:any) => {
        if (map.data.code !== 200) {
          ElMessage({message: '用户名或密码错误', type: 'error'})
        } else {
          console.log(map)
          localStorage.setItem("token", map.data.date.token)
          router.push({
            name: "home",
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
  /* 100%窗口高度 */
  height: 100vh;
  width: 100%;
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
</style>/style>