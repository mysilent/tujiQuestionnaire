<template>
  <div class="login">
    <div class="login-box">
      <div class="input-box">
        <el-form
            ref="ruleFormRef"
            :model="ruleForm"
            :rules="rules"
            class="demo-ruleForm"
        >
          <el-form-item prop="username">
            <el-input placeholder="用户名" v-model="ruleForm.username"/>
          </el-form-item>
          <el-form-item prop="pass">
            <el-input placeholder="密码" v-model="ruleForm.pass" type="textarea" autocomplete="off"/>
          </el-form-item>
          <el-form-item prop="checkPass">
            <el-input placeholder="确认密码" v-model="ruleForm.checkPass" type="textarea" autocomplete="off"/>
          </el-form-item>
          <el-form-item prop="email">
            <el-input placeholder="邮箱" v-model="ruleForm.email"/>
          </el-form-item>
          <el-form-item>
            <el-button @click="submitForm(ruleFormRef)" class="button">注册</el-button>
            <el-button @click="resetForm(ruleFormRef)" class="button">重置</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import {reactive, ref} from 'vue'
import type {FormInstance, FormRules} from 'element-plus'
import {ElMessage} from 'element-plus'
import EnrollApi from '@/axios/api/eroll.api'
import {useRouter} from 'vue-router'

const router = useRouter();
const ruleFormRef = ref<FormInstance>()

const username = (rule: any, value: any, callback: any) => {
  const mailReg = /^[a-zA-Z]{1}([a-zA-Z0-9]|[._]){7,15}$/;
  if (!value) {
    return callback(new Error('请填写用户名'))
  } else if (mailReg.test(value)) {
    callback()
  } else {
    callback(new Error("请以英文开头，用户名长度8-16"))
  }
}

const email = (rule: any, value: any, callback: any) => {
  const mailReg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/
  if (!value) {
    return callback(new Error('请填写邮箱'))
  }
  setTimeout(() => {
    if (mailReg.test(value)) {
      callback()
    } else {
      callback(new Error('请输入正确的邮箱格式'))
    }
  }, 100)
  callback();
}

const validatePass = (rule: any, value: any, callback: any) => {
  if (value === '') {
    callback(new Error('请输入密码'))
  } else {
    if (ruleForm.checkPass !== '') {
      if (!ruleFormRef.value) return
      ruleFormRef.value.validateField('checkPass', () => null)
    }
    callback()
  }
}
const validatePass2 = (rule: any, value: any, callback: any) => {
  if (value === '') {
    callback(new Error('请再次输入密码'))
  } else if (value !== ruleForm.pass) {
    callback(new Error("两次输入密码不相同!"))
  } else {
    callback()
  }
}

const ruleForm = reactive({
  username: '',
  pass: '',
  checkPass: '',
  email: '',
})

const rules = reactive<FormRules>({
  username: [{validator: username, trigger: 'blur'}],
  pass: [{validator: validatePass, trigger: 'blur'}],
  checkPass: [{validator: validatePass2, trigger: 'blur'}],
  email: [{validator: email, trigger: 'blur'}],
})

const submitForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return
  formEl.validate((valid) => {
    if (valid) {
      EnrollApi(ruleForm).then((map: any) => {
        if (map.data.code === 200) {
          router.push({path: '/login'})
          ElMessage({message: '注册成功请登录', type: 'success',})
        } else {
          ElMessage({message: map.data.msg, type: 'error',})
        }
      })
    } else {
      ElMessage.error('请正确填写注册信息.')
      return false
    }
  })
}
const resetForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return
  formEl.resetFields()
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
  width: 154px;
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
</style>