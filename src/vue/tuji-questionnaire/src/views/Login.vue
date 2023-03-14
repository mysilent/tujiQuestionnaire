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
        <el-form :model="user" :rules="rules" ref="user" class="demo-ruleForm ">
          <el-form-item prop="username">
            <el-input v-model="user.username" placeholder="账户名" style="height: 35px"></el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input v-model="user.password" placeholder="密码" style="height: 35px"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button @click="login(user)" class="button">登录</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>

</template>

<script>
import {loginAPI} from "@/axios/api/login.api";

export default {
  name: "login",
  data() {
    return {
      user: {
        username: '',
        password: ''
      },
      rules: {
        username: [
          {required: true, message: '请输入账户名称', trigger: 'blur'},
          {min: 3, max: 6, message: '长度在 3 到 5 个字符', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min: 3, max: 6, message: '长度在 3 到 5 个字符', trigger: 'blur'}
        ],
      }
    }
  },
  methods: {
    login(user) {
      this.$refs.user.validate((valid) => {
        if (valid) {
          loginAPI(user).then(map => {
            if (map.status !== 200) {
              this.$message.error("用户名或密码错误")
            } else {
              this.$router.push("/home")
            }
          })
        } else {
          this.$message.error("请正确填写")
        }
      })
    },
  }
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
</style>