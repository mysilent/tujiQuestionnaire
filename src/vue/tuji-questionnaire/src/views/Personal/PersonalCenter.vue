<template>
  <div class="index">
    <el-affix :offset="40">
    <div style="display:grid;justify-content: center;">
        <div class="header-div">
          <div ></div>
          <div ></div>
          <button class="header-button" @click="personalInformation">个人信息</button>
          <button class="header-button" @click="historicalAnswers">历史答卷</button>
          <button class="header-button" @click="changePassword">修改密码</button>
      </div>
    </div>
    </el-affix>
    <div style="display: grid;justify-content: center;">
      <div class="main-div">
        <router-view></router-view>
      </div>
    </div>
  </div>

</template>

<script lang="ts" setup>

import {useRouter} from "vue-router";
import {ref} from "vue";
const router = useRouter();

const index = ref(1)

    const personalInformation = () => {
      console.log(router)
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
  min-height: 100px;
  background: #F6F6F6;
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


.header-button:hover {
  color: #9B4E9E;
}
.header-button:after{
  content: "";
  position: absolute;
  bottom: -3px;
  left: 0;
  width: 100%;
  height: 2px;
  background-color:#9B4E9E ;
  transform: scaleX(0);
  transition: transform 0.2s ease-in-out;
}
.header-button:focus::after{
  transform: scaleX(1);
}
.index{
background: linear-gradient(to top right, #9B4E9E, #F15F79);
  min-height: 94.5vh;
}

</style>