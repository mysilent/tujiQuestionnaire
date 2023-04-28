<template>
  <el-affix>
    <div class="top">
      <div class="logo">
        <div class="logo-top"></div>
        <img src="../components/icons/logo.svg"></div>
      <div style="width: 300px">
        <nav>
          <div class="line" id="line" :style="{left:left1+'px',width:width1+'px'}"></div>
          <router-link id="link1" tag="a" to="/home/questionnaireCenter" class="button-link"
                       active-class="router-link-active">
            问卷中心
          </router-link>
          <router-link id="link2" tag="a" to="/home/publishQuestionnaire" class="button-link"
                       active-class="router-link-active">
            发布问卷
          </router-link>
          <router-link id="link3" tag="a" to="/home/myQuestionnaire" class="button-link "
                       active-class="router-link-active">
            我的问卷
          </router-link>
        </nav>
      </div>
      <div>
        <el-dropdown class="user">
          <div class="right-div">{{ nickname }}
            <User class="block-col"/>
          </div>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item @click="personal">
                <el-icon>
                  <UserFilled/>
                </el-icon>&nbsp;个人中心
              </el-dropdown-item>
              <el-dropdown-item @click="changePassword">
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

<script lang="ts">
import {Memo, SwitchButton, User, UserFilled} from "@element-plus/icons-vue";
import {storeToRefs} from "pinia";
import {useRouter} from "vue-router";
import {useLoginStore} from "@/stores/UserLogin";
import {defineComponent, onMounted, ref} from "vue";
import { createPinia } from 'pinia'
export default defineComponent({
  components:{Memo, SwitchButton, User, UserFilled},
  setup() {
    const store = useLoginStore()
    const router = useRouter()
    const {nickname: nickname} = storeToRefs(store)
    const exit = () => {
      clearUserData()
      router.push({
        path: '/'
      })
    }
    const personal = () => {
      let newUrl = router.resolve({
        path: "/personal"
      })
      window.open(newUrl.href, "_blank");
    }
    const changePassword=()=>{
      let newUrl = router.resolve({
        path: "/personal/personalCenter/changePassword"
      })
      window.open(newUrl.href, "_blank");
    }
    const left1 = ref(0);
    const width1 = ref(0);
    const Click = (link: string) => {
      const {left, width} = document.getElementById(link)!.getBoundingClientRect()
      left1.value = left - 559;
      width1.value = width;
    }
    onMounted(() => {
      document.getElementById("link1")!.addEventListener('click', () => Click("link1"));
      document.getElementById("link2")!.addEventListener('click', () => Click("link2"));
      document.getElementById("link3")!.addEventListener('click', () => Click("link3"));
    });




    const pinia = createPinia()

    function clearUserData() {
      // 清除用户的Pinia状态和数据
      // pinia.reset()
      // 删除保存在客户端的Token
      localStorage.removeItem('token')
      localStorage.removeItem('id')
      localStorage.removeItem('login')
    }
    return {
      exit,
      personal,
      nickname,
      left1,
      width1,
      Click,
      changePassword
    }
  }
})
</script>
<style>
.top {
  width: 98.5vi;
  height: 40px;
  margin: 0;
  display: flex;
  justify-content: space-between;
  background-color: #f2f2f2;
  box-shadow: 0 0 12px rgba(0, 0, 0, 0.12);
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

.right-div {
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
  color: #2775b6;
  cursor: pointer;
}

nav {
  width: auto;
  display: flex;
  justify-content: space-between;
  position: relative;
  margin-bottom: 4vmin;
}

nav a {
  font-size: 16px;
  color: #fff;
  text-decoration: none;
}

nav .line {
  position: absolute;
  width: 0;
  height: 0.5vmin;
  background: linear-gradient(200deg, #b1f4cf, #9890e3);
  border-radius: 1vmin;
  left: 0;
  bottom: -1%;
  transition: .8s cubic-bezier(0.68, -0.55, 0.265, 1.55);
}
</style>