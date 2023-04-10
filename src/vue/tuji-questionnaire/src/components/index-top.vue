<template>
  <el-affix>
    <div class="top">
      <div class="logo">
        <div class="logo-top"></div>
        <img src="../components/icons/logo.svg"></div>
      <div style="width: 228px">
        <router-link tag="button" to="/home/questionnairePreview" class="button-link" active-class="router-link-active">
          问卷中心
        </router-link>
        <router-link tag="button" to="/home/publishQuestionnaire" class="button-link" active-class="router-link-active">
          发布问卷
        </router-link>
        <router-link tag="button" to="/home/myQuestionnaire" class="button-link " active-class="router-link-active">
          我的问卷
        </router-link>
      </div>
      <div>
        <el-dropdown class="user">
          <div class="button">王小虎
            <User class="block-col"></User>
          </div>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item>
                <el-icon>
                  <UserFilled/>
                </el-icon>&nbsp;个人中心
              </el-dropdown-item>
              <el-dropdown-item>
                <el-icon>
                  <Memo/>
                </el-icon>&nbsp;修改密码
              </el-dropdown-item>
              <el-dropdown-item @click="exit">
                <el-icon>
                  <SwitchButton/>
                </el-icon>&nbsp;退出
              </el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </div>
    </div>
  </el-affix>

</template>

<script>
import {Memo, SwitchButton, User, UserFilled} from "@element-plus/icons-vue";

export default {
  name: "index-top",
  components: {Memo, UserFilled, SwitchButton, User,},
  methods: {
    exit() {
      this.$router.push('/')
    },
  }
}
</script>

<style>
.top {
  width: 98.5vi;
  height: 40px;
  margin: 0;
  display: flex;
  justify-content: space-between;
  background-color: #f2f2f2;
  box-shadow:0 0  12px rgba(0, 0, 0, 0.12);
}

.logo-top {
  width: 75px;
  height: 7px;
}

.logo {
  float: right;
  width: 75px;
}

img {
  float: right;
  width: 30px;
}

.user {
  width: 150px;
  height: 40px;
  background-color: #f2f2f2;
}

.block-col {
  top: 3px;
  left: 2px;
  display: inline-flex;
  height: 14px;
  width: 14px;
}

.button {
  width: 100px;
  height: 40px;
  line-height: 36px;
  cursor: pointer;
}

.button-link {
  text-align: center;
  color: #181818;
  width: 76px;
  height: 40px;
  line-height: 36px;
  cursor: pointer;
  display: inline-block;
  /*鼠标移上去缓慢变色*/
  transition-property: border-bottom, color;
  transition-duration: .01s, .5s;
}

.button-link:hover {
  color: #20894d;
  cursor: pointer;

}

.router-link-active {
  color: #20894d;
  border-bottom: #20894d groove 3px;
  cursor: pointer;
}

</style>