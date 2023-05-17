<template>

  <el-menu
      default-active="2"
      class="el-menu-vertical-demo"
      style="height:100vh ;overflow-x: hidden ;border-right-color:transparent;  min-height: 400px;"
      :collapse="isCollapse"
      background-color="#545c64"
      active-text-color="#ffd04b"
      text-color="#fff"
      router="turn"
      popper-offset="-50px"
      :default-active="user"
  >
<!--    <div style="height: 50px;display: flex"> <img src="src/components/icons/logo.svg" style="left: 25px"></div>-->
    <div style="height: 60px;line-height: 60px; text-align: center">
      <img src="src/components/icons/logo.svg" style="height: 50px;position: relative;top:5px;left: -15px">
      <b style="color: azure" v-show="login">后台管理系统</b>
    </div>
    <el-menu-item index="BackgroundData">
      <el-icon><HomeFilled /></el-icon>
      <span>主    页</span>
    </el-menu-item>
    <el-sub-menu index="1">
      <template #title>
        <el-icon><Monitor /></el-icon>
        <span>数据管理</span>
      </template>
      <el-menu-item-group>
        <template #title><span>信息管理</span></template>
        <el-menu-item index="user">账户信息管理</el-menu-item>
        <el-menu-item index="administrator">管理员信息管理</el-menu-item>
      </el-menu-item-group>
      <el-menu-item-group title="问卷管理">
        <el-menu-item index="backgroundSurvey">问卷信息管理</el-menu-item>
        <el-menu-item index="Template">问卷模板管理</el-menu-item>
      </el-menu-item-group>
    </el-sub-menu>
    <el-sub-menu index="2">
      <template #title>
        <el-icon><ChatDotSquare /></el-icon>
        <span>申请处理</span>
      </template>
      <el-menu-item-group>
        <template #title><span>信息处理</span></template>
        <el-menu-item index="backgroundSurveyTemplate">问卷模板审核</el-menu-item>
        <el-menu-item index="">举报处理</el-menu-item>
      </el-menu-item-group>
    </el-sub-menu>
  </el-menu>
</template>
<script lang="ts">
import {defineComponent} from "vue";
import {ChatDotSquare, HomeFilled, Location, Monitor,} from '@element-plus/icons-vue'
    export default defineComponent({
      name:"sidebar",
      components: {HomeFilled, ChatDotSquare, Monitor, Location},
        props:{
            isCollapse:Boolean,
            login:Boolean
        },
    })

</script>
<style scoped>
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px
}

</style>