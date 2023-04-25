<template>
  <div class="wrapper-changPassword">
    <div class="card-changPassword">
      <div class="password-changPassword">
    <el-form ref="form" :model="form" label-width="100px" @submit.prevent="changePassword">
      <el-form-item label="旧密码" prop="oldPassword">
        <input type="password" v-model="form.oldPassword" class="input">
      </el-form-item>
      <el-form-item label="新密码" prop="newPassword" :error="newPasswordError" :error-message="newPasswordErrorMessage">
        <input type="password" v-model="form.newPassword" class="input">
      </el-form-item>
      <el-form-item label="确认密码" prop="confirmPassword" :error="confirmPasswordError" :error-message="confirmPasswordErrorMessage">
        <input type="password" v-model="form.confirmPassword" class="input">
      </el-form-item>
      <el-form-item>
        <button class="greenButton">修改密码</button>
      </el-form-item>
    </el-form>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import {ElForm, ElFormItem, ElInput, ElButton, ElMessage} from 'element-plus';
import {changePassword} from "@/axios/api/Personal.api";


export default defineComponent({
  components: { ElForm, ElFormItem, ElInput, ElButton },
  data() {
    return {
      form: {
        oldPassword: '',
        newPassword: '',
        confirmPassword: '',
      },
      newPasswordError: false,
      newPasswordErrorMessage: '',
      confirmPasswordError: false,
      confirmPasswordErrorMessage: '',
    };
  },
  methods: {
    changePassword() {
    changePassword(this.form).then(map=>{
      if (map.data.code!=200){
      ElMessage({message:map.data.msg,type:"error"})
      }else {
        ElMessage({message:map.data.msg,type:"success"})
      }
    })
    },
    validateNewPassword() {
      if (this.form.newPassword.length < 6) {
        this.newPasswordError = true;
        this.newPasswordErrorMessage = '密码长度不能少于6位！';
      } else {
        this.newPasswordError = false;
        this.newPasswordErrorMessage = '';
      }
    },
    validateConfirmPassword() {
      if (this.form.confirmPassword !== this.form.newPassword) {
        this.confirmPasswordError = true;
        this.confirmPasswordErrorMessage = '两次输入的密码不一致！';
      } else {
        this.confirmPasswordError = false;
        this.confirmPasswordErrorMessage = '';
      }
    },
  },
  watch: {
    'form.newPassword': 'validateNewPassword',
    'form.confirmPassword': 'validateConfirmPassword',
  },
});
</script>

<style>
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

.wrapper-changPassword{
  display: flex;
  justify-content: center;
  align-items: center;
  min-width: 1000px;
}
.card-changPassword{
  width: 50%;
  background: white;
  border-radius: 59px;
  animation:slide-in 2s forwards;
}
.password-changPassword{
  width: auto;
  left: -20px;
}
@keyframes slide-in {
 0%{
   opacity: 0;
 }
  100%{
    opacity: 1;
  }
}
</style>