<template>
  <div class="index">
<!--    <el-affix :offset="40">-->
    <div style="display:grid;justify-content: center;">
        <div class="header-div">
          <div ></div>
          <div ></div>
          <div></div>
          <div class="line" id="line" :style="{left:left1+'px',width:width1+'px'}"></div>
          <button id="link1" class="header-button" @click="personalInformation">个人信息</button>
          <button id="link2" class="header-button" @click="historicalAnswers">历史答卷</button>
          <button id="link3" class="header-button" @click="changePassword">修改密码</button>
      </div>
    </div>
<!--    </el-affix>-->
    <div style="min-height: 87.5vh;display: grid;justify-content: center;background-image:url(../../../src/imager/background.png);background-size: cover;">
      <div class="main-div">
        <router-view></router-view>
      </div>
    </div>
  </div>

</template>

<script lang="ts" setup>

import {useRouter} from "vue-router";
import {onMounted, ref} from "vue";
const router = useRouter();

const index = ref(1)

    const personalInformation = () => {
      router.push({
        path:'/personal/personalCenter/personalInformation'
      })

    }
    const historicalAnswers = () => {
      router.push({
        path:"/personal/personalCenter/historicalAnswers"
      })
    }
    const changePassword = () => {
      router.push({
        path:"/personal/personalCenter/changePassword"
      })
    }


const left1 = ref(0);
const width1 = ref(0);
const Click = (link: string) => {
  const {left, width} = document.getElementById(link)!.getBoundingClientRect()
  left1.value = left;
  width1.value = width;
}
onMounted(() => {
  document.getElementById("link1")!.addEventListener('click', () => Click("link1"));
  document.getElementById("link2")!.addEventListener('click', () => Click("link2"));
  document.getElementById("link3")!.addEventListener('click', () => Click("link3"));
});


</script>

<style scoped>
.header-div{

  /*display:grid;*/
  justify-content: center;
  height: 50px;
  min-width: 98.8vi;
  background-color: #F6F6F6;
  display: grid;
  grid-template-columns: repeat(auto-fill, 130px);
  align-items: center;
  justify-items: center;

}
.main-div{
  display: flex;
  min-width: 1000px;
  max-height: 98vi;
  background: #F6F6F6;
  background: rgba(255,255,255,.05);
  box-shadow: 0 0 20px rgba(0,0,0,0.25);
  backdrop-filter: blur(20px);
  border-radius: 8px;
}

.header-button{
  font-size: initial;
  text-align: center;
  height: 40px;
  width: 80px;
  cursor: pointer;
  transition: all 0.3s ease-in-out;
  border:none;
  background-color: #F6F6F6;

  position: relative;
}


.index{
  /*background-image: linear-gradient(to right top, #051937, #004d7a, #008793, #00bf72, #a8eb12);*/
  min-height: 100%;
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

 .line {
  position: absolute;
  width: 0;
  height: 0.5vmin;
  background: linear-gradient(200deg, #b1f4cf, #9890e3);
  border-radius: 1vmin;
  left: 0;
  bottom: -2%;
  transition: .8s cubic-bezier(0.68, -0.55, 0.265, 1.55);
}

</style>