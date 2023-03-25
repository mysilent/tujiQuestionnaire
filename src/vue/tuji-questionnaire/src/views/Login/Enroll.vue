<template>
  <div class="enroll">
    <div class="enroll-box">
      <div class="owl" id="owl" v-bind:class="{'password': isActive===1}">
        <div class="duihua">
          <span style="font-size: 14px">如果你已经加入我们,就快去
            <span @click="login" style="color: #72afd3;cursor: pointer;">登录</span>吧！
          </span></div>
        <div class="hand"></div>
        <div class="hand hand-r"></div>
        <div class="arms">
          <div class="arm"></div>
          <div class="arm arm-r"></div>
        </div>
      </div>
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
            <el-input placeholder="密码" v-model="ruleForm.pass" type="password" autocomplete="off" @click="isActive=1" @blur="isActive=0"/>
          </el-form-item>
          <el-form-item prop="checkPass">
            <el-input placeholder="确认密码" v-model="ruleForm.checkPass" type="password" autocomplete="off" @click="isActive=1" @blur="isActive=0"/>
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
const isActive = ref(0)

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
const login = ()=>{
  router.push({
    path:"/login"
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
  width: 154px;
  font-size: 15px;
}

.enroll {
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

.enroll-box {
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


/* 接下来是猫头鹰的样式 */
.owl{
  width: 211px;
  height: 108px;
  /* 背景图片 */
  background: url("@/imager/owl-login.png") no-repeat;
  /* 绝对定位 */
  position: absolute;
  top: -100px;
  /* 水平居中 */
  left: 50%;
  transform: translateX(-50%);
}
.owl .hand{
  width: 34px;
  height: 34px;
  border-radius: 40px;
  background-color: #472d20;
  /* 绝对定位 */
  position: absolute;
  left: 12px;
  bottom: -8px;
  /* 沿Y轴缩放0.6倍（压扁） */
  transform: scaleY(0.6);
  /* 动画过渡 */
  transition: 0.3s ease-out;
}
.owl .hand.hand-r{
  left: 170px;
}
.owl.password .hand{
  transform: translateX(42px) translateY(-15px) scale(0.7);
}
.owl.password .hand.hand-r{
  transform: translateX(-42px) translateY(-15px) scale(0.7);
}
.owl .arms{
  position: absolute;
  top: 58px;
  width: 100%;
  height: 41px;
  overflow: hidden;
}
.owl .arms .arm{
  width: 40px;
  height: 65px;
  position: absolute;
  left: 20px;
  top: 40px;
  background: url("@/imager/owl-login-arm.png") no-repeat;
  transform: rotate(-20deg);
  transition: 0.3s ease-out;
}
.owl .arms .arm.arm-r{
  transform: rotate(20deg) scaleX(-1);
  left: 158px;
}
.owl.password .arms .arm{
  transform: translateY(-40px) translateX(40px);
}
.owl.password .arms .arm.arm-r{
  transform: translateY(-40px) translateX(-40px) scaleX(-1);
}

.duihua{
  width: 125px;
  height:50px;
  border-radius: 5px;
  background-color: #F6F6F6;
  position: relative;
  margin-left:175px;

}
.duihua:before{
  content: "";
  border-top: 30px solid transparent;
  border-bottom: 5px solid transparent;
  border-right: 20px solid #F6F6F6;
  position: absolute;
  top: 10px;
  left: -10px;
}
</style>