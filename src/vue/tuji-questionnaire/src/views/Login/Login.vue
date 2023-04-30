<template>
  <div class="login">
    <div class="login-box">
      <div class="shake-crazy">
        <div class="owl" id="owl" v-bind:class="{'password': isActive===1}">

          <div class="hand"></div>
          <div class="hand hand-r"></div>
          <div class="arms">
            <div class="arm"></div>
            <div class="arm arm-r"></div>
          </div>
        </div>
      </div>
      <div class="duihua">
        <span style="font-size: 14px">如果你还未加入我们,就快去<span @click="enroll" style="color: #72afd3;cursor: pointer;">注册</span>吧！</span>
      </div>
      <div class="input-box">
        <el-form :model="user" :rules="rules" ref="ruleFormRef" class="demo-ruleForm ">
          <el-form-item prop="username">
            <el-input v-model="user.username" placeholder="账户名" style="height: 35px"></el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input v-model="user.password" placeholder="密码" style="height: 35px" @click="isActive=1"
                      @blur="isActive=0" type="password"></el-input>
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
import {loginApi} from "@/axios/api/login.api";
import type {FormInstance, FormRules} from "element-plus";
import {ElMessage} from "element-plus";
import {useRouter} from "vue-router";
import {reactive, ref} from "vue";
import {useLoginStore} from '@/stores/UserLogin'

const userLogin = useLoginStore();
const ruleFormRef = ref<FormInstance>();
const router = useRouter();
const user = reactive({
  username: '',
  password: ''
})
const isActive = ref(0)

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
          ElMessage({message: map.data.msg, type: 'error'})
        } else {
          localStorage.setItem("token", map.data.data.token)
          userLogin.$patch((state) => {
            state.id = map.data.data.id
            state.username = map.data.data.username
            state.nickname = map.data.data.nickname
          })
          ElMessage({message: "登陆成功~", type: "success"})
          router.push({
            path: '/home'
          })
        }
      })
    } else {
      ElMessage({message: '请正确填写登录信息', type: 'error'})
      return false
    }
  })
}
const enroll = () => {
  router.push({
    path: "/enroll"
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


/* 接下来是猫头鹰的样式 */
.owl {
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

.owl .hand {
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

.owl .hand.hand-r {
  left: 170px;
}

.owl.password .hand {
  transform: translateX(42px) translateY(-15px) scale(0.7);
}

.owl.password .hand.hand-r {
  transform: translateX(-42px) translateY(-15px) scale(0.7);
}

.owl .arms {
  position: absolute;
  top: 58px;
  width: 100%;
  height: 41px;
  overflow: hidden;
}

.owl .arms .arm {
  width: 40px;
  height: 65px;
  position: absolute;
  left: 20px;
  top: 40px;
  background: url("@/imager/owl-login-arm.png") no-repeat;
  transform: rotate(-20deg);
  transition: 0.3s ease-out;
}

.owl .arms .arm.arm-r {
  transform: rotate(20deg) scaleX(-1);
  left: 158px;
}

.owl.password .arms .arm {
  transform: translateY(-40px) translateX(40px);
}

.owl.password .arms .arm.arm-r {
  transform: translateY(-40px) translateX(-40px) scaleX(-1);
}


.duihua {
  position: absolute;
  width: 125px;
  height: 50px;
  left: 60px;
  bottom: 200px;
  border-radius: 5px;
  background-color: #F6F6F6;
  /*position: relative;*/
  margin-left: 175px;

}

.duihua:before {
  content: "";
  border-top: 30px solid transparent;
  border-bottom: 5px solid transparent;
  border-right: 20px solid #F6F6F6;
  position: absolute;
  top: 10px;
  left: -10px;
}
</style>