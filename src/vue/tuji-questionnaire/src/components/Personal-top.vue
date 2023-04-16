<template>
  <el-affix>
    <div class="top">
      <div class="logo">
        <div class="logo-top"></div>
        <img src="../components/icons/logo.svg">
      </div>
      <div class="top-text"><h3>个人中心</h3></div>
      <div style="width: 1200px">
      </div>
      <div>
        <el-dropdown class="user">
          <div class="button">{{nickname}}
            <User class="block-col"></User>
          </div>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item @click="personal">
                <el-icon>
                  <UserFilled/>
                </el-icon>&nbsp;个人中心
              </el-dropdown-item>
              <el-dropdown-item @click="">
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

<script lang="ts" setup>
import {Memo, SwitchButton, User, UserFilled} from "@element-plus/icons-vue";
import {storeToRefs} from "pinia";
import {useRouter} from "vue-router";
import {useLoginStore} from "@/stores/UserLogin";
const store = useLoginStore()
const router =useRouter()
const {nickname:nickname} = storeToRefs(store)

const exit=()=> {
  router.push({
    path:'/'
  })
}
const personal = () => {
  let newUrl= router.resolve({
    path: "/personal"
  })
  window.open(newUrl.href, "_blank");
}


</script>

<style scoped>
.top {
  width: 98.9vi;
  height: 40px;
  margin: 0;
  display: flex;
  justify-content: flex-start;
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
  /*display: inline-flex;*/
  height: 14px;
  width: 14px;
}

.button {
  width: 100px;
  height: 40px;
  line-height: 36px;
  cursor: pointer;
}
.top-text{
  width: 100px;
  display: inline-flex;
  align-items: center;
  flex-direction: row-reverse
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