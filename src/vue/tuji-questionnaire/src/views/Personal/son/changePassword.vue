<template>
  <div class="wrapper-changPassword">
    <div class="card-changPassword">
      <div class="password-changPassword">
        <el-form  ref="ruleFormRef"
                  :model="form"
                  status-icon
                  :rules="rules"
                  label-width="80px"
                  class="demo-ruleForm">
          <el-form-item label="旧密码" prop="oldPassword">
            <el-input v-model="form.oldPassword" show-password></el-input>
          </el-form-item>
          <el-form-item label="新密码" prop="newPassword">
            <el-input v-model="form.newPassword" show-password></el-input>
          </el-form-item>
          <el-form-item label="确认密码" prop="confirmPassword">
            <el-input v-model="form.confirmPassword" show-password></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="changeUserPassword(ruleFormRef)">提交</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import {reactive, ref,} from 'vue';
import {ElForm, ElFormItem, ElMessage, } from 'element-plus';
import type {FormInstance, FormRules} from 'element-plus';
import {changePassword} from "@/axios/api/Personal.api";
import {useLoginStore} from "@/stores/UserLogin";

const store = useLoginStore()
const ruleFormRef = ref<FormInstance>()
const form = reactive({
  oldPassword: '',
  newPassword: '',
  confirmPassword: '',
  username: store.username,
})

const validatePass = (rule: any, value: any, callback: any) => {
  if (value === '') {
    callback(new Error('请输入新密码'))
  } else {
    if (form.confirmPassword !== '') {
      if (!ruleFormRef.value) return
      ruleFormRef.value.validateField('checkPass', () => null)
    }
    callback()
  }
}
const validatePass2 = (rule: any, value: any, callback: any) => {
  if (value === '') {
    callback(new Error('请再次输入新密码'))
  } else if (value !== form.newPassword) {
    callback(new Error("两次输入密码不相同!"))
  } else {
    callback()
  }
}

const validatePass3 = (rule: any, value: any, callback: any) => {
  if (!value) {
    callback(new Error('请输入旧密码'))
  } else {
    callback()
  }
}
const rules = reactive<FormRules>({
  newPassword: [{ validator: validatePass, trigger: 'blur' }],
  confirmPassword: [{ validator: validatePass2, trigger: 'blur' }],
  oldPassword: [{ validator: validatePass3, trigger: 'blur' }],
})

const changeUserPassword = (formEl: FormInstance | undefined) => {
  if (!formEl) return
  formEl.validate((valid) => {
    if (valid) {
      changePassword(form).then(map => {
        if (map.data.code != 200) {
          ElMessage({message: map.data.msg, type: "error"})
        } else {
          ElMessage({message: map.data.msg, type: "success"})
          form.confirmPassword=''
          form.newPassword=''
          form.oldPassword=''
        }
      })
    } else {
      console.log('请正确填写')
      return false
    }
  })
}

</script>


<style scoped>
form {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 20px;
  font-size: 18px;
}

label {
  margin-right: 10px;
}

input[type=password] {
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  font-size: 16px;
}

.wrapper-changPassword {
  display: flex;
  justify-content: center;
  align-items: center;
  min-width: 1000px;
}

.card-changPassword {
  width: 50%;
  background: white;
  border-radius: 59px;
  animation: slide-in 2s forwards;
}

.password-changPassword {
  width: auto;
  left: -20px;
}

@keyframes slide-in {
  0% {
    opacity: 0;
  }
  100% {
    opacity: 1;
  }
}
</style>